-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: furniture2
-- ------------------------------------------------------
-- Server version	8.0.39-0ubuntu0.24.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items`
(
    `id`         char(36)    NOT NULL,
    `product_id` int         NOT NULL,
    `cart_id`    varchar(12) NOT NULL,
    `quantity`   int              DEFAULT '1',
    `created_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp   NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `product_id` (`product_id`),
    KEY `cart_id` (`cart_id`),
    CONSTRAINT `cart_items_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
    CONSTRAINT `cart_items_ibfk_2` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_items`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts`
(
    `id`         varchar(12) NOT NULL,
    `user_id`    varchar(12) NOT NULL,
    `quantity`   int              DEFAULT '0',
    `created_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp   NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `carts`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories`
(
    `id`         int                                                           NOT NULL AUTO_INCREMENT,
    `name`       varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `is_active`  tinyint(1)                                                             DEFAULT '1',
    `created_at` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp                                                     NULL     DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories`
    DISABLE KEYS */;
INSERT INTO `categories`
VALUES (1, 'Ghế', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (2, 'Bàn', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (3, 'Tủ', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (4, 'Chén', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (5, 'Dĩa', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (6, 'Giường', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (7, 'Kệ', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (8, 'Đèn', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (9, 'Sofa', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (10, 'Thảm', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (11, 'Gương', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (12, 'Phụ kiện trang trí', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL);
/*!40000 ALTER TABLE `categories`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items`
(
    `id`         bigint    NOT NULL AUTO_INCREMENT,
    `order_id`   bigint    NOT NULL,
    `product_id` int       NOT NULL,
    `quantity`   int       NOT NULL,
    `price`      double    NOT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_order_item_order` (`order_id`),
    KEY `FK_order_item_product` (`product_id`),
    CONSTRAINT `FK_order_item_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
    CONSTRAINT `FK_order_item_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders`
(
    `id`           bigint                                                                  NOT NULL AUTO_INCREMENT,
    `user_id`      varchar(12)                                                             NOT NULL,
    `total_amount` double                                                                  NOT NULL,
    `status`       enum ('Chờ xác nhận','Đã duyệt','Đang giao hàng','Hoàn thành','Đã hủy') NOT NULL DEFAULT 'Chờ xác nhận',
    `payment`      varchar(100)                                                            NOT NULL,
    `address`      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci           NOT NULL,
    `created_at`   timestamp                                                               NULL     DEFAULT CURRENT_TIMESTAMP,
    `updated_at`   timestamp                                                               NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at`   timestamp                                                               NULL     DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `orders`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products`
(
    `id`          int                                                           NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `description` text,
    `price`       decimal(18, 0)                                                     DEFAULT NULL,
    `stock`       int                                                                DEFAULT '0',
    `image`       varchar(255)                                                       DEFAULT NULL,
    `category_id` int                                                           NOT NULL,
    `supplier_id` int                                                           NOT NULL,
    `is_active`   tinyint(1)                                                         DEFAULT '1',
    `created_at`  timestamp                                                     NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  timestamp                                                     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at`  timestamp                                                     NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_category` (`category_id`),
    KEY `supplier_id` (`supplier_id`),
    KEY `idx_name_price` (`name`, `price`),
    CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE,
    CONSTRAINT `products_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products`
    DISABLE KEYS */;
INSERT INTO `products`
VALUES (1, 'Đèn Spotlight', NULL, 25500000, 12,
        'https://www.decor-walther.com/img/products/0219400/Studio-S_Perspektive-3_chrom.png', 6, 13, 1,
        '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (2, 'Hộp khăn giấy', NULL, 10000000, 5,
        'https://www.decor-walther.com/img/products/0830400/0830400_KB%2082_chrom-web.png', 10, 13, 1,
        '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (3, 'Đèn cầm tay mini vàng', NULL, 26118408, 5,
        'https://www.saint-louis.com/media/catalog/product/…ble_lamp_saint-louis_crystal_lighting_gold_or.jpg', 6, 7, 1,
        '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (4, 'Đĩa sứ họa tiết cánh hoa', NULL, 5058723, 8,
        '	https://www.fuerstenberg-porzellan.com/thumbnail/d…0919800/img1_fl2011325900_platzteller_600x600.png', 5,
        10, 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (5, 'Đĩa ăn Pasta đáy lõm', NULL, 1457132, 5,
        'https://www.fuerstenberg-porzellan.com/thumbnail/8a/a1/ca/1710921104/img1_tf_68328weiss_gourmetteller_tief_600x600.png',
        5, 10, 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (6, 'Gương trang điểm', NULL, 34472846, 2, 'https://www.decor-walther.com/img/products/0110900/0110900.png', 11,
        13, 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (7, 'Đèn treo tường Flannel xám', NULL, 33266604, 1,
        'https://www.saint-louis.com/media/catalog/product/cache/e1648193c4bd079fe5e6c9e530413384/1/0/10170716-1.jpg',
        6, 7, 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL);
/*!40000 ALTER TABLE `products`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion_orders`
--

DROP TABLE IF EXISTS `promotion_orders`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion_orders`
(
    `id`           int    NOT NULL AUTO_INCREMENT,
    `promotion_id` int    NOT NULL,
    `order_id`     bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY `promotion_id` (`promotion_id`),
    KEY `order_id` (`order_id`),
    CONSTRAINT `promotion_orders_ibfk_1` FOREIGN KEY (`promotion_id`) REFERENCES `promotions` (`id`),
    CONSTRAINT `promotion_orders_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion_orders`
--

LOCK TABLES `promotion_orders` WRITE;
/*!40000 ALTER TABLE `promotion_orders`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion_orders`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotions`
--

DROP TABLE IF EXISTS `promotions`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotions`
(
    `id`                  int         NOT NULL AUTO_INCREMENT,
    `code`                varchar(50) NOT NULL,
    `description`         text,
    `discount_percent`    double           DEFAULT '0',
    `max_discount_amount` double           DEFAULT '0',
    `start_date`          timestamp   NOT NULL,
    `end_date`            timestamp   NOT NULL,
    `usage_limit`         int              DEFAULT NULL,
    `is_active`           tinyint(1)       DEFAULT '1',
    `created_at`          timestamp   NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`          timestamp   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at`          timestamp   NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `code` (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotions`
--

LOCK TABLES `promotions` WRITE;
/*!40000 ALTER TABLE `promotions`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `promotions`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `product_id` int         NOT NULL,
    `user_id`    varchar(12) NOT NULL,
    `rating`     tinyint     NOT NULL,
    `comment`    text,
    `created_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp   NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `product_id` (`product_id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
    CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles`
(
    `id`          int         NOT NULL AUTO_INCREMENT,
    `name`        varchar(50) NOT NULL,
    `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
    `created_at`  timestamp   NULL                                              DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  timestamp   NULL                                              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at`  timestamp   NULL                                              DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles`
    DISABLE KEYS */;
INSERT INTO `roles`
VALUES (1, 'ADMIN', 'Quản trị viên hệ thống, có toàn quyền quyết định trong hệ thống', '2024-09-28 08:44:00',
        '2024-09-28 08:44:00', NULL),
       (2, 'MOD', 'Người điều hành nội dung', '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (3, 'USER', 'Người dùng thông thường', '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL);
/*!40000 ALTER TABLE `roles`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_product`
--

DROP TABLE IF EXISTS `room_product`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_product`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `room_id`    int NOT NULL,
    `product_id` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_room` (`room_id`),
    KEY `fk_product` (`product_id`),
    CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_room` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 27
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_product`
--

LOCK TABLES `room_product` WRITE;
/*!40000 ALTER TABLE `room_product`
    DISABLE KEYS */;
INSERT INTO `room_product`
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 3, 1),
       (4, 4, 1),
       (5, 5, 1),
       (6, 6, 1),
       (7, 7, 1),
       (8, 1, 2),
       (9, 2, 2),
       (10, 3, 2),
       (11, 5, 2),
       (12, 6, 2),
       (13, 7, 2),
       (14, 1, 3),
       (15, 6, 4),
       (16, 3, 4),
       (17, 6, 5),
       (18, 3, 5),
       (19, 1, 6),
       (20, 1, 7),
       (21, 2, 7),
       (22, 3, 7),
       (23, 4, 7),
       (24, 5, 7),
       (25, 6, 7),
       (26, 7, 7);
/*!40000 ALTER TABLE `room_product`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms`
(
    `id`         int                                                           NOT NULL AUTO_INCREMENT,
    `name`       varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `is_active`  tinyint(1)                                                             DEFAULT '1',
    `created_at` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp                                                     NULL     DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms`
    DISABLE KEYS */;
INSERT INTO `rooms`
VALUES (1, 'Phòng ngủ', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (2, 'Phòng khách', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (3, 'Phòng bếp', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (4, 'Phòng tắm', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (5, 'Phòng làm việc', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (6, 'Phòng ăn', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL),
       (7, 'Phòng sách', 1, '2024-09-28 08:43:59', '2024-09-28 08:43:59', NULL);
/*!40000 ALTER TABLE `rooms`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers`
(
    `id`            int          NOT NULL AUTO_INCREMENT,
    `name`          varchar(255) NOT NULL,
    `contact_email` varchar(255)      DEFAULT NULL,
    `contact_phone` varchar(20)       DEFAULT NULL,
    `address`       text,
    `country`       varchar(100)      DEFAULT NULL,
    `website`       varchar(255)      DEFAULT NULL,
    `is_active`     tinyint(1)        DEFAULT '1',
    `created_at`    timestamp    NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    timestamp    NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at`    timestamp    NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers`
    DISABLE KEYS */;
INSERT INTO `suppliers`
VALUES (1, 'IKEA', 'info@ikea.com', '+46 771 123 456', 'Älmhult, Sweden', 'Thụy Điển', 'https://www.ikea.com', 1,
        '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (2, 'Wayfair', 'service@wayfair.com', '+1 877 929 3247', '4 Copley Place, Boston, MA, USA', 'Hoa Kỳ',
        'https://www.wayfair.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (3, 'Ashley Furniture', 'support@ashleyfurniture.com', '+1 800 477 2222', 'One Ashley Way, Arcadia, WI, USA',
        'Hoa Kỳ', 'https://www.ashleyfurniture.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (4, 'Herman Miller', 'info@hermanmiller.com', '+1 888 443 4357', 'Zeeland, Michigan, USA', 'Hoa Kỳ',
        'https://www.hermanmiller.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (5, 'Steelcase', 'customercare@steelcase.com', '+1 800 333 9939', 'Grand Rapids, Michigan, USA', 'Hoa Kỳ',
        'https://www.steelcase.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (6, 'Muji', 'info@muji.com', '+81 3 3989 4531', 'Tokyo, Japan', 'Japan', 'https://www.muji.com', 1,
        '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (7, 'Saint Louis', 'contact@saint-louis.com', '+33 3 87 06 40 50',
        'Rue Coëtlosquet, Saint-Louis-lès-Bitche, France', 'Pháp', 'https://www.saint-louis.com', 1,
        '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (8, 'Cire Trudon', 'info@ciretrudon.com', '+33 1 42 77 00 34', '78 Rue de Seine, Paris, France', 'Pháp',
        'https://www.ciretrudon.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (9, 'Arcahorn', 'info@arcahorn.com', '+39 0733 616030', 'Via Velluti, 41, Recanati, Italy', 'Ý',
        'https://www.arcahorn.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (10, 'Fürstenberg', 'info@fuerstenberg-porzellan.com', '+49 5271 401 0',
        'Meinbrexener Str. 2, Fürstenberg, Germany', 'Đức', 'https://www.fuerstenberg-porzellan.com', 1,
        '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (11, 'Riviere', 'info@rivieredecor.it', '+39 0322 938174', 'Via Sesiana 9, Gozzano, Italy', 'Ý',
        'https://www.rivieredecor.it', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (12, 'Ralph Lauren', 'customer.service@ralphlauren.com', '+1 888 475 7674', '650 Madison Avenue, New York, USA',
        'Hoa Kỳ', 'https://www.ralphlauren.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL),
       (13, 'Décor Walther', 'info@decor-walther.com', '+49 69 971 4700', 'Liebfrauenberg 37, Frankfurt, Germany',
        'Đức', 'https://www.decor-walther.com', 1, '2024-09-28 08:44:00', '2024-09-28 08:44:00', NULL);
/*!40000 ALTER TABLE `suppliers`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_logs`
--

DROP TABLE IF EXISTS `user_logs`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_logs`
(
    `id`         int       NOT NULL AUTO_INCREMENT,
    `user_id`    varchar(12)    DEFAULT NULL,
    `action`     varchar(255)   DEFAULT NULL,
    `message`    text,
    `log_level`  varchar(50)    DEFAULT NULL,
    `ip_address` varchar(50)    DEFAULT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`),
    CONSTRAINT `user_logs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_logs`
--

LOCK TABLES `user_logs` WRITE;
/*!40000 ALTER TABLE `user_logs`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `user_logs`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users`
(
    `id`              varchar(30)  NOT NULL,
    `username`        varchar(255) NOT NULL,
    `email`           varchar(255) NOT NULL,
    `password`        varchar(255)                                                  DEFAULT NULL,
    `phone`           varchar(20)                                                   DEFAULT NULL,
    `full_name`       varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
    `oauth2_id`       varchar(255)                                                  DEFAULT NULL,
    `oauth2_provider` varchar(50)                                                   DEFAULT NULL,
    `otp`             varchar(6)                                                    DEFAULT NULL,
    `otp_expiry`      timestamp    NULL                                             DEFAULT NULL,
    `role_id`         int          NOT NULL,
    `is_active`       tinyint(1)                                                    DEFAULT '0',
    `created_at`      timestamp    NULL                                             DEFAULT CURRENT_TIMESTAMP,
    `updated_at`      timestamp    NULL                                             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at`      timestamp    NULL                                             DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`),
    UNIQUE KEY `email` (`email`),
    UNIQUE KEY `oauth2_id` (`oauth2_id`),
    KEY `role_id` (`role_id`),
    CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `users`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlists`
--

DROP TABLE IF EXISTS `wishlists`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wishlists`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `user_id`    varchar(12) NOT NULL,
    `product_id` int         NOT NULL,
    `created_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp   NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted_at` timestamp   NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_wishlist_user` (`user_id`),
    KEY `FK_wishlist_product` (`product_id`),
    CONSTRAINT `FK_wishlist_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE,
    CONSTRAINT `FK_wishlist_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlists`
--

LOCK TABLES `wishlists` WRITE;
/*!40000 ALTER TABLE `wishlists`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `wishlists`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2024-09-28 16:18:00
