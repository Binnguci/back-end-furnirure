package com.binnguci.furniture.mapper;

import com.binnguci.furniture.dto.UserDTO;
import com.binnguci.furniture.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class UserMapperTest {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void testToDTO() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setUsername("testuser");
        userEntity.setPassword("password");
        userEntity.setEmail("test@example.com");
        userEntity.setPhone("123456789");
        userEntity.setAddress("Test Address");
        userEntity.setEnabled((short) 1);
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());

        UserDTO userDTO = mapper.toDTO(userEntity);

        assertThat(userDTO).isNotNull();
        assertThat(userDTO.getId()).isEqualTo(userEntity.getId());
        assertThat(userDTO.getUsername()).isEqualTo(userEntity.getUsername());
        assertThat(userDTO.getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(userDTO.getPhone()).isEqualTo(userEntity.getPhone());
        assertThat(userDTO.getAddress()).isEqualTo(userEntity.getAddress());
        assertThat(userDTO.getEnabled()).isEqualTo(userEntity.getEnabled());
        assertThat(userDTO.getCreatedAt()).isEqualTo(userEntity.getCreatedAt());
        assertThat(userDTO.getUpdatedAt()).isEqualTo(userEntity.getUpdatedAt());
    }

    @Test
    public void testToEntity() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setUsername("testuser");
        userDTO.setEmail("test@example.com");
        userDTO.setPhone("123456789");
        userDTO.setAddress("Test Address");
        userDTO.setEnabled((short) 1);
        userDTO.setCreatedAt(LocalDateTime.now());
        userDTO.setUpdatedAt(LocalDateTime.now());

        UserEntity userEntity = mapper.toEntity(userDTO);

        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getId()).isEqualTo(userDTO.getId());
        assertThat(userEntity.getUsername()).isEqualTo(userDTO.getUsername());
        assertThat(userEntity.getEmail()).isEqualTo(userDTO.getEmail());
        assertThat(userEntity.getPhone()).isEqualTo(userDTO.getPhone());
        assertThat(userEntity.getAddress()).isEqualTo(userDTO.getAddress());
        assertThat(userEntity.getEnabled()).isEqualTo(userDTO.getEnabled());
        assertThat(userEntity.getCreatedAt()).isEqualTo(userDTO.getCreatedAt());
        assertThat(userEntity.getUpdatedAt()).isEqualTo(userDTO.getUpdatedAt());
    }
}