package com.binnguci.furniture.service.email;

import jakarta.annotation.PreDestroy;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements IEmailService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void sendMailOTP(String to, String otp) {
        log.info("Sending OTP email to {}", to);
        String subject = "[XÁC NHẬN OTP TỪ FURNITURE]";
        String content = generateOtpEmailContent(otp);
        sendMail(to, subject, content);
    }

    private String generateOtpEmailContent(String otp) {
        if (otp == null || otp.isBlank()) {
            throw new IllegalArgumentException("OTP không được rỗng hoặc trống");
        }
        return String.format(TEMPLATE_OTP_EMAIL, otp);
    }


    private void sendMail(String to, String subject, String content) {
        log.info("Preparing to send email to {}", to);
        executorService.submit(() -> {
            log.info("Submitting email task to executor");
            try {
                MimeMessage message = createMimeMessage(to, subject, content);
                log.info("MimeMessage created successfully");
                mailSender.send(message);
                log.info("Email sent successfully to {}", to);
            } catch (MessagingException ex) {
                log.error("Failed to send email to {}", to, ex);
            } catch (Exception e) {
                log.error("Unexpected error occurred while sending email to {}", to, e);
            }
        });
    }


    private MimeMessage createMimeMessage(String to, String subject, String content) throws MessagingException {
        log.info("Creating MimeMessage for email to {}", to);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(new InternetAddress(username));
        helper.setTo(new InternetAddress(to));
        helper.setSubject(subject);
        helper.setText(content, true);
        return message;
    }

    @PreDestroy
    public void shutdown() {
        executorService.shutdown();
    }

    private static final String TEMPLATE_OTP_EMAIL = """
            <div style="font-family: Arial, sans-serif; color: #333;">
                <p style="font-size: 16px;">Chào bạn,</p>
                <p style="font-size: 16px;">
                    Bạn đã yêu cầu lấy lại mật khẩu cho tài khoản của mình. Dưới đây là mã OTP để xác nhận:
                </p>                        
                <p style="font-size: 18px; font-weight: bold; color: #D19C97; text-align: center;">
                    Mã OTP: %s
                </p>
                <p style="font-size: 16px;">
                    Vui lòng nhập mã này vào ứng dụng để tiếp tục quá trình lấy lại mật khẩu.
                </p>
                <p style="font-size: 16px;">
                    Nếu bạn không yêu cầu lấy lại mật khẩu, vui lòng bỏ qua email này.
                </p>
                <p style="font-size: 16px;">Trân trọng,</p>
                <p style="font-size: 16px; font-style: italic;">Đội ngũ hỗ trợ</p>
            </div>
            """;

}
