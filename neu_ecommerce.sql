/*
 Navicat Premium Data Transfer

 Source Server         : conn_localhost
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : neu_ecommerce

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 02/07/2024 15:09:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '管理员唯一标识符',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员用户名，用于登录的唯一标识',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员密码，存储时应为哈希值',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '管理员头像',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '管理员邮箱',
  `role` int NOT NULL COMMENT '角色',
  `func_id` int NULL DEFAULT NULL COMMENT '功能',
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '管理员联系方式',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员注册表1' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES (1, '曹操', 'e10adc3949ba59abbe56e057f20f883e', '', 'waccfgh.cn', 1, NULL, '111111');
INSERT INTO `admins` VALUES (2, '曹丕', 'e10adc3949ba59abbe56e057f20f883e', '', '', 1, NULL, '');
INSERT INTO `admins` VALUES (3, '曹植', 'e10adc3949ba59abbe56e057f20f883e', '', '', 1, NULL, '');
INSERT INTO `admins` VALUES (4, '司马懿', 'e10adc3949ba59abbe56e057f20f883e', '', '', 1, NULL, '');
INSERT INTO `admins` VALUES (5, '夏侯惇', 'e10adc3949ba59abbe56e057f20f883e', '', '', 1, NULL, '');
INSERT INTO `admins` VALUES (6, '张辽', 'e10adc3949ba59abbe56e057f20f883e', '', '', 1, NULL, '');
INSERT INTO `admins` VALUES (7, '徐晃', 'e10adc3949ba59abbe56e057f20f883e', '', '', 1, NULL, '');

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '注册信息的唯一标识符',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌名称',
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '品牌logo',
  `contact_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `contact_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人邮箱（不同于登录邮箱）',
  `address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '品牌地址',
  `registration_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册日期，默认为当前时间戳',
  `brands_id` int NULL DEFAULT NULL COMMENT '与登录表的外键关联',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '品牌方注册品牌的表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES (1, 'YONEX', NULL, '小飞', '18104556966', '2388975573@qq.com', '燕山大学西校区', '2024-07-01 19:45:16', 1);
INSERT INTO `brand` VALUES (2, '波力', NULL, '小李', NULL, NULL, NULL, '2024-07-01 20:38:16', 7);
INSERT INTO `brand` VALUES (3, '胜利', NULL, '小刚', NULL, NULL, NULL, '2024-07-01 20:38:49', 3);
INSERT INTO `brand` VALUES (4, '李宁', NULL, '小天', NULL, NULL, NULL, '2024-07-01 20:39:16', 6);

-- ----------------------------
-- Table structure for brands
-- ----------------------------
DROP TABLE IF EXISTS `brands`;
CREATE TABLE `brands`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识符',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名，用于登录的唯一标识',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码，存储时应为哈希值',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '邮箱',
  `role` int NOT NULL COMMENT '角色',
  `func_id` int NULL DEFAULT NULL COMMENT '功能',
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '品牌商联系方式',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '品牌方人员注册表3' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of brands
-- ----------------------------
INSERT INTO `brands` VALUES (1, '孙权', 'e10adc3949ba59abbe56e057f20f883e', '', '', 3, NULL, '');
INSERT INTO `brands` VALUES (2, '周瑜', 'e10adc3949ba59abbe56e057f20f883e', '', '', 3, NULL, '');
INSERT INTO `brands` VALUES (3, '鲁肃', 'e10adc3949ba59abbe56e057f20f883e', '', '', 3, NULL, '');
INSERT INTO `brands` VALUES (4, '吕蒙', 'e10adc3949ba59abbe56e057f20f883e', '', '', 3, NULL, '');
INSERT INTO `brands` VALUES (5, '张昭', 'e10adc3949ba59abbe56e057f20f883e', '', '', 3, NULL, '');
INSERT INTO `brands` VALUES (6, '大乔', 'e10adc3949ba59abbe56e057f20f883e', '', '', 3, NULL, '');
INSERT INTO `brands` VALUES (7, '小乔', 'e10adc3949ba59abbe56e057f20f883e', '', '', 3, NULL, '');

-- ----------------------------
-- Table structure for functions
-- ----------------------------
DROP TABLE IF EXISTS `functions`;
CREATE TABLE `functions`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '菜单项的唯一标识符',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单项的显示名称',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '菜单项的状态，0: 禁用, 1: 启用',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '访问路径',
  `role` int NULL DEFAULT NULL COMMENT '与角色相关联',
  `level` int NULL DEFAULT NULL COMMENT '菜单的等级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表，包含菜单项的基本信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of functions
-- ----------------------------

-- ----------------------------
-- Table structure for merchants
-- ----------------------------
DROP TABLE IF EXISTS `merchants`;
CREATE TABLE `merchants`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识符',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名，用于登录的唯一标识',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码，存储时应为哈希值',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '头像',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '邮箱',
  `role` int NOT NULL COMMENT '角色',
  `func_id` int NULL DEFAULT NULL COMMENT '功能',
  `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '商家联系方式',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商家人员注册表2' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of merchants
-- ----------------------------
INSERT INTO `merchants` VALUES (1, '刘备', 'e10adc3949ba59abbe56e057f20f883e', '', '', 2, NULL, '');
INSERT INTO `merchants` VALUES (2, '关羽', 'e10adc3949ba59abbe56e057f20f883e', '', '', 2, NULL, '');
INSERT INTO `merchants` VALUES (3, '张飞', 'e10adc3949ba59abbe56e057f20f883e', '', '', 2, NULL, '');
INSERT INTO `merchants` VALUES (4, '诸葛亮', 'e10adc3949ba59abbe56e057f20f883e', '', '', 2, NULL, '');
INSERT INTO `merchants` VALUES (5, '赵云', 'e10adc3949ba59abbe56e057f20f883e', '', '', 2, NULL, '');
INSERT INTO `merchants` VALUES (6, '马超', 'e10adc3949ba59abbe56e057f20f883e', '', '', 2, NULL, '');
INSERT INTO `merchants` VALUES (7, '黄忠', 'e10adc3949ba59abbe56e057f20f883e', '', '', 2, NULL, '');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单ID，主键，自增',
  `store_id` int NOT NULL COMMENT '借卖方ID，外键',
  `product_id` int NOT NULL COMMENT '商品ID，外键',
  `brand_id` int NOT NULL COMMENT '品牌方ID',
  `rent_time` int NULL DEFAULT NULL COMMENT '租借时间',
  `quantity` int NOT NULL COMMENT '数量，不允许为空',
  `rent_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '租金，不允许为空',
  `purchase_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '买金，不允许为空',
  `order_date` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单日期，默认为当前时间',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单总价，不允许为空',
  `logistics_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '物流单号',
  `logistics_type` int NOT NULL COMMENT '订单物流类型',
  `postage` decimal(10, 0) NULL DEFAULT NULL COMMENT '邮费',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `status` enum('待发货','未支付','已发货','已送达','已取消') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '未支付' COMMENT '状态：只能是待发货或未支付或已发货或已送达或已取消',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 1, 1, 1, 5, 8, NULL, NULL, '2024-07-02 13:35:41', NULL, NULL, 1, NULL, '燕山大学东校区', '未支付');
INSERT INTO `orders` VALUES (2, 1, 1, 1, 14, 30, 3000.00, NULL, '2024-07-02 14:52:29', NULL, NULL, 1, NULL, '黑龙江省绥化市北林区', '未支付');
INSERT INTO `orders` VALUES (3, 1, 1, 1, NULL, 30, NULL, 30000.00, '2024-07-02 14:56:05', NULL, NULL, 1, NULL, '黑龙江省绥化市北林区', '未支付');
INSERT INTO `orders` VALUES (4, 1, 1, 1, 5, 8, 4000.00, NULL, '2024-07-02 15:02:25', NULL, NULL, 2, NULL, '黑龙江省齐齐哈尔市建华区', '已取消');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品ID，主键，自增',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称，不允许为空',
  `brand_id` int NOT NULL COMMENT '品牌方ID',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品描述',
  `product_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址',
  `rent_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '租金价格，不允许为空',
  `purchase_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '买金价格，不允许为空',
  `stock_quantity` int NULL DEFAULT NULL COMMENT '库存数量，不允许为空',
  `status` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '上架中' COMMENT '商品状态，只能说上架中或已下架',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (1, '123456', 1, '每个羽球人的最终梦想', 'http:www.google.com', 100.00, 1000.00, 500, '上架中');
INSERT INTO `products` VALUES (2, '天斧88dpro', 1, NULL, NULL, NULL, NULL, NULL, '上架中');
INSERT INTO `products` VALUES (3, '天斧77pro', 1, NULL, NULL, NULL, NULL, NULL, '上架中');

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '购物车唯一标识',
  `store_id` int NOT NULL COMMENT '用户ID，关联用户表',
  `product_id` int NOT NULL COMMENT '商品ID，关联商品表',
  `quantity` int NULL DEFAULT 1 COMMENT '数量',
  `rent_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品租借单价（租借时的价格）',
  `purchase_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品购买单价（购买时的价格）',
  `total_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品总价（单价乘以数量）',
  `added_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加到购物车的时间',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '想要操作的类型，借还是买',
  `status` int NULL DEFAULT 0 COMMENT '是否选择，0为不选，1为选',
  `rent_time` int NULL DEFAULT NULL COMMENT '想要租借的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '购物车表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------
INSERT INTO `shopping_cart` VALUES (1, 1, 1, 8, 100.00, 1000.00, 4000.00, '2024-07-02 13:03:39', '借', 1, 5);

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '注册信息的唯一标识符',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '网店名称',
  `contact_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人电话',
  `contact_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人邮箱（不同于登录邮箱）',
  `address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网店地址',
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '网店logo',
  `registration_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册日期，默认为当前时间戳',
  `merchants_id` int NOT NULL COMMENT '与登录表中的网店ID相关联',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '网店注册表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (1, 'YONEX专卖店', '李宗伟', NULL, NULL, NULL, NULL, '2024-07-01 19:52:46', 1);
INSERT INTO `store` VALUES (2, 'nike专卖店', '乔丹', NULL, NULL, NULL, NULL, '2024-07-01 20:31:07', 2);
INSERT INTO `store` VALUES (3, '李宁专卖店', '林丹', NULL, NULL, NULL, NULL, '2024-07-01 20:32:07', 3);
INSERT INTO `store` VALUES (4, '胜利专卖店', '李子嘉', NULL, NULL, NULL, NULL, '2024-07-01 20:32:58', 4);
INSERT INTO `store` VALUES (5, '彪马专卖店', '桃田', NULL, NULL, NULL, NULL, '2024-07-01 20:34:26', 6);

-- ----------------------------
-- Table structure for wallet_transaction
-- ----------------------------
DROP TABLE IF EXISTS `wallet_transaction`;
CREATE TABLE `wallet_transaction`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '交易记录的唯一标识符',
  `wallet_id` int NOT NULL COMMENT '关联电子钱包表的电子钱包ID',
  `trade_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '交易类型',
  `amount` decimal(10, 2) NOT NULL COMMENT '交易金额',
  `transaction_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易时间，默认为当前时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '电子钱包交易记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wallet_transaction
-- ----------------------------

-- ----------------------------
-- Table structure for wallets
-- ----------------------------
DROP TABLE IF EXISTS `wallets`;
CREATE TABLE `wallets`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '电子钱包的唯一标识符',
  `balance` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '电子钱包的当前余额',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电子钱包的账户',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电子钱包的密码',
  `brand_id` int NULL DEFAULT NULL,
  `store_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `brand_id`(`brand_id` ASC) USING BTREE,
  UNIQUE INDEX `store_id`(`store_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '电子钱包表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wallets
-- ----------------------------

-- ----------------------------
-- Table structure for zebra_account
-- ----------------------------
DROP TABLE IF EXISTS `zebra_account`;
CREATE TABLE `zebra_account`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '账号ID，主键，自增',
  `account_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名，用于登录和展示，不可重复',
  `brand_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码，加密存储',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱，用于找回密码和接收通知，不可重复',
  `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号，用于找回密码和接收短信通知，不可重复',
  `registration_datetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account_name`(`account_name` ASC) USING BTREE,
  UNIQUE INDEX `email`(`email` ASC) USING BTREE,
  UNIQUE INDEX `phone_number`(`phone_number` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '品牌方的斑马账号表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of zebra_account
-- ----------------------------
INSERT INTO `zebra_account` VALUES (3, '斑马', '1', 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, '2024-07-01 22:15:32');

SET FOREIGN_KEY_CHECKS = 1;
