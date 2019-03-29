/*
 Navicat MySQL Data Transfer

 Source Server         : CRM
 Source Server Type    : MySQL
 Source Server Version : 50554
 Source Host           : 121.199.10.160:3306
 Source Schema         : CRM

 Target Server Type    : MySQL
 Target Server Version : 50554
 File Encoding         : 65001

 Date: 21/07/2017 14:49:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for T_CAMPAIGN
-- ----------------------------
DROP TABLE IF EXISTS `T_CAMPAIGN`;
CREATE TABLE `T_CAMPAIGN`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动名称',
  `startDate` datetime NOT NULL COMMENT '开始日期',
  `endDate` datetime NOT NULL COMMENT '结束日期',
  `country` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '国家',
  `province` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '省',
  `city` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地级市',
  `region` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '区',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '详细地址',
  `state` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `exhibitioHall` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '展馆，来自主数据的展馆',
  `sponsor` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主办方，数据是客户',
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_CASE
-- ----------------------------
DROP TABLE IF EXISTS `T_CASE`;
CREATE TABLE `T_CASE`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '案例名',
  `url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '假定每个案例都有至少一个图片',
  `type` int(11) NOT NULL COMMENT '1,工厂，2，设计师',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_CONTACT
-- ----------------------------
DROP TABLE IF EXISTS `T_CONTACT`;
CREATE TABLE `T_CONTACT`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人姓名',
  `mobilePhone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人电话',
  `position` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系人职位',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` int(11) NOT NULL COMMENT '１，可用；０，不可用',
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_CONTRACT
-- ----------------------------
DROP TABLE IF EXISTS `T_CONTRACT`;
CREATE TABLE `T_CONTRACT`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `signDay` date NOT NULL COMMENT '签署日期',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '合同名称',
  `code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '合同编码',
  `projectId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目编号,项目名称',
  `campaignId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '市场活动',
  `exhibitionNumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '展位号',
  `area` float(50, 0) NOT NULL COMMENT '面积',
  `amount` float(50, 0) NOT NULL COMMENT '合同总价',
  `category` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '合同分类',
  `tax` float(20, 0) NOT NULL COMMENT '税',
  `paymentMethod` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '付款方式',
  `initialPaymentTime` date NOT NULL COMMENT '首付时间',
  `middlePaymentTime` date NOT NULL COMMENT '中款时间',
  `finalPaymentTime` date NOT NULL COMMENT '尾款时间',
  `billingInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开票信息',
  `firstParty` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '合同甲方',
  `secondParty` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '合同乙方',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_CONTRACT_LEDGER
-- ----------------------------
DROP TABLE IF EXISTS `T_CONTRACT_LEDGER`;
CREATE TABLE `T_CONTRACT_LEDGER`  (
  `contractId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ledgerId` char(0) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`contractId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_CUSTOMER
-- ----------------------------
DROP TABLE IF EXISTS `T_CUSTOMER`;
CREATE TABLE `T_CUSTOMER`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `companyName` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业名称',
  `companyId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `industry` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属行业',
  `nature` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业性质',
  `country` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '国家',
  `province` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省',
  `city` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '城市',
  `region` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '区域',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `level` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '平台等级',
  `state` int(11) NOT NULL COMMENT '１，可用；０，不可用；２，锁定',
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_CUSTOMER_CONTACT
-- ----------------------------
DROP TABLE IF EXISTS `T_CUSTOMER_CONTACT`;
CREATE TABLE `T_CUSTOMER_CONTACT`  (
  `customerId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户主键',
  `contactId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人主键'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_DESIGNER
-- ----------------------------
DROP TABLE IF EXISTS `T_DESIGNER`;
CREATE TABLE `T_DESIGNER`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `referrer` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '推荐人，从销售列表选择',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设计师中文名',
  `nameEN` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设计师英文名',
  `mobilePhone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设计师电话',
  `wechat` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设计师微信',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设计师QQ邮箱',
  `country` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '国家',
  `province` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '省',
  `city` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '城市（地级市）',
  `area` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '区　县级市',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `englishAbility` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '英文能力',
  `goodAtIndustry` varchar(370) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '擅长行业',
  `goodAtArea` varchar(370) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '擅长面积 100、100~300、300以上',
  `designStyle` varchar(370) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设计风格',
  `experience` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '从业年限',
  `gender` int(11) NOT NULL COMMENT '性别',
  `previous` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '最近上家公司',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `platformLevel` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '平台等级',
  `platformCreditLevel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '平台信用度评价',
  `state` int(11) NOT NULL COMMENT '１，可用；０，不可用',
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_DICTIONARY
-- ----------------------------
DROP TABLE IF EXISTS `T_DICTIONARY`;
CREATE TABLE `T_DICTIONARY`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `sort` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `value` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` int(1) NOT NULL,
  `description` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`, `sort`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_DICTIONARY_FUNC
-- ----------------------------
DROP TABLE IF EXISTS `T_DICTIONARY_FUNC`;
CREATE TABLE `T_DICTIONARY_FUNC`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ver` int(11) DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_EXHIBITION_HALL
-- ----------------------------
DROP TABLE IF EXISTS `T_EXHIBITION_HALL`;
CREATE TABLE `T_EXHIBITION_HALL`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '展馆名称',
  `start` datetime NOT NULL COMMENT '开展时间',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `createTime` datetime NOT NULL,
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_FACTORY
-- ----------------------------
DROP TABLE IF EXISTS `T_FACTORY`;
CREATE TABLE `T_FACTORY`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `referrer` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '推荐人，从销售列表选择',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业名称',
  `director` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '厂长',
  `firstOrderTime` datetime DEFAULT NULL COMMENT '首单时间，应是该工厂首个完成项目的创建时间',
  `country` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '国家',
  `province` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '省',
  `city` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '城市（地级市）',
  `area` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '区　县级市',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `factoryArea` float(11, 0) NOT NULL COMMENT '厂房面积',
  `photo` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片url，多个图片以逗号分割',
  `registeredCapital` float(11, 0) DEFAULT NULL COMMENT '注册资金（18，2）',
  `taxpayerType` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '纳税人身份（一般纳税人、小规模纳税人、未注册公司）',
  `fixedEmployeeCount` int(11) DEFAULT NULL COMMENT '固定工人数量',
  `goodAtIndustry` varchar(370) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '擅长行业',
  `goodAtMaterial` varchar(370) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '擅长材料',
  `goodAtArea` varchar(370) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '擅长面积 100、100~300、300以上',
  `platformLevel` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '平台等级A/B/C',
  `valueAddedTaxAccount` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '增值税发票账号',
  `taxNumber` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '税号',
  `state` int(11) NOT NULL COMMENT '１，可用；０，不可用',
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_FACTORY_CASE
-- ----------------------------
DROP TABLE IF EXISTS `T_FACTORY_CASE`;
CREATE TABLE `T_FACTORY_CASE`  (
  `factoryId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工厂主键',
  `caseId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '案例主键',
  PRIMARY KEY (`factoryId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_LEDGER
-- ----------------------------
DROP TABLE IF EXISTS `T_LEDGER`;
CREATE TABLE `T_LEDGER`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '款项类型',
  `paymentMethod` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '支付方式',
  `costCenter` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '成本中心',
  `paymentTime` date NOT NULL COMMENT '付款时间',
  `paymentAmount` float(20, 0) NOT NULL COMMENT '付款金额',
  `operator` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '经办人',
  `reasonForChange` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '变更原因',
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_PARAMS
-- ----------------------------
DROP TABLE IF EXISTS `T_PARAMS`;
CREATE TABLE `T_PARAMS`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `value` varchar(740) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_PERMISSION
-- ----------------------------
DROP TABLE IF EXISTS `T_PERMISSION`;
CREATE TABLE `T_PERMISSION`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parentId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `icon` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_PM
-- ----------------------------
DROP TABLE IF EXISTS `T_PM`;
CREATE TABLE `T_PM`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `referrer` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '推荐人，从销售列表选择',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设计师中文名',
  `nameEN` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设计师英文名',
  `mobilePhone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设计师电话',
  `wechat` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设计师微信',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设计师QQ邮箱',
  `country` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '国家',
  `province` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '省',
  `city` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '城市（地级市）',
  `area` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '区　县级市',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `englishAbility` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '英文能力',
  `goodAtIndustry` varchar(370) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '擅长行业',
  `goodAtArea` varchar(370) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '擅长面积 100、100~300、300以上',
  `designStyle` varchar(370) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设计风格',
  `experience` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '从业年限',
  `gender` int(11) NOT NULL COMMENT '性别',
  `previous` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '最近上家公司',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `state` int(11) NOT NULL COMMENT '１，可用；０，不可用',
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_PM_CASE
-- ----------------------------
DROP TABLE IF EXISTS `T_PM_CASE`;
CREATE TABLE `T_PM_CASE`  (
  `pmId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工厂主键',
  `caseId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '案例主键',
  PRIMARY KEY (`pmId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_PRODUCTION
-- ----------------------------
DROP TABLE IF EXISTS `T_PRODUCTION`;
CREATE TABLE `T_PRODUCTION`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品分类',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '细节描述',
  `unit` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位',
  `way` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方式',
  `low` float(20, 0) NOT NULL COMMENT '低价',
  `middle` float(20, 0) NOT NULL COMMENT '中价',
  `high` float(20, 0) NOT NULL COMMENT '高价',
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_PROJECT
-- ----------------------------
DROP TABLE IF EXISTS `T_PROJECT`;
CREATE TABLE `T_PROJECT`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目名称',
  `type` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `customerId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户,办展不可选',
  `campaignId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '市场活动',
  `code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目编号',
  `budget` float(20, 0) NOT NULL COMMENT '预算',
  `exhibitionNumber` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '展位号',
  `area` float(30, 0) NOT NULL COMMENT '面积',
  `setupTime` datetime NOT NULL COMMENT '搭建时间',
  `tearDownTime` datetime NOT NULL COMMENT '撤展时间',
  `cost` float(20, 0) DEFAULT NULL COMMENT '项目成本',
  `degreeOfImportance` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '重要程度',
  `potential` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目潜力',
  `designProgress` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设计进度',
  `pmId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目经理',
  `projectProgress` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目进度',
  `factoryProgress` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工厂进度',
  `projectScore` float(10, 0) DEFAULT NULL COMMENT '项目评分',
  `designScore` float(10, 0) DEFAULT NULL COMMENT '设计评分',
  `pmScore` float(10, 0) DEFAULT NULL COMMENT '项目经理评分',
  `developSaleId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开发销售',
  `traceSaleId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '跟进销售',
  `photo` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '项目图片',
  `state` int(11) NOT NULL COMMENT '１，可用；０，不可用',
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_PROJECT_DESIGNER
-- ----------------------------
DROP TABLE IF EXISTS `T_PROJECT_DESIGNER`;
CREATE TABLE `T_PROJECT_DESIGNER`  (
  `projectId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `designerId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`projectId`, `designerId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_PROJECT_FACTORY
-- ----------------------------
DROP TABLE IF EXISTS `T_PROJECT_FACTORY`;
CREATE TABLE `T_PROJECT_FACTORY`  (
  `projectId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `factoryId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`projectId`, `factoryId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_QUOTATION
-- ----------------------------
DROP TABLE IF EXISTS `T_QUOTATION`;
CREATE TABLE `T_QUOTATION`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `priceLevel` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '价格档次',
  `productionId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品主键',
  `unitPrice` float(20, 0) NOT NULL COMMENT '单价',
  `count` float(20, 0) NOT NULL COMMENT '数量',
  `workContent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工作内容',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `T_ROLE`;
CREATE TABLE `T_ROLE`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` int(11) NOT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_ROLE_PERMISSION
-- ----------------------------
DROP TABLE IF EXISTS `T_ROLE_PERMISSION`;
CREATE TABLE `T_ROLE_PERMISSION`  (
  `roleId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permissionId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_SALE
-- ----------------------------
DROP TABLE IF EXISTS `T_SALE`;
CREATE TABLE `T_SALE`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '销售姓名',
  `channel` varchar(370) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '销售渠道代码',
  `mobilePhone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '销售电话',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '销售QQ邮箱',
  `state` int(11) NOT NULL COMMENT '１，可用；０，不可用',
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_SPECTATOR
-- ----------------------------
DROP TABLE IF EXISTS `T_SPECTATOR`;
CREATE TABLE `T_SPECTATOR`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '观众名称',
  `mobilePhone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `country` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '国家',
  `province` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '省',
  `city` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '城市（地级市）',
  `area` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '区　县级市',
  `hobbies` varchar(370) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '兴趣爱好',
  `createTime` datetime NOT NULL,
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_USER
-- ----------------------------
DROP TABLE IF EXISTS `T_USER`;
CREATE TABLE `T_USER`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nameEN` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `mobilePhone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `openId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` smallint(6) DEFAULT 0 COMMENT '1，男；2，女',
  `state` smallint(6) DEFAULT 1 COMMENT '1.可用，2.锁定，3.禁用',
  `referrer` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '(设计师、项目经理用)推荐人，从销售列表选择',
  `wechat` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '(设计师、项目经理用)设计师微信',
  `englishAbility` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '(设计师、项目经理用)英文能力',
  `goodAtIndustry` varchar(370) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '(设计师、项目经理用)擅长行业',
  `goodAtArea` varchar(370) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '(设计师、项目经理用)擅长面积 100、100~300、300以上',
  `designStyle` varchar(370) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '(设计师用)设计风格',
  `experience` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '(设计师、项目经理用)从业年限',
  `previous` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '(设计师、项目经理用)最近上家公司',
  `birthday` datetime DEFAULT NULL COMMENT '(设计师、项目经理用)出生日期',
  `platformLevel` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '(设计师用)平台等级',
  `platformCreditLevel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '(设计师用)平台信用度评价',
  `channel` varchar(370) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '(销售用)销售渠道代码',
  `hobbies` varchar(370) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '(观众用)兴趣爱好',
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `mobilePhone`(`mobilePhone`) USING BTREE,
  UNIQUE INDEX `openId`(`openId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_USER_CASE
-- ----------------------------
DROP TABLE IF EXISTS `T_USER_CASE`;
CREATE TABLE `T_USER_CASE`  (
  `designerId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '工厂主键',
  `caseId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '案例主键',
  PRIMARY KEY (`designerId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_USER_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `T_USER_ROLE`;
CREATE TABLE `T_USER_ROLE`  (
  `userId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `roleId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for T_VENDOR_APPOINTMENT
-- ----------------------------
DROP TABLE IF EXISTS `T_VENDOR_APPOINTMENT`;
CREATE TABLE `T_VENDOR_APPOINTMENT`  (
  `id` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `vendorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `allDay` tinyint(255) NOT NULL,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `type` int(255) NOT NULL,
  `createTime` datetime NOT NULL,
  `creatorId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `modifierId` char(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
