/*
Navicat MySQL Data Transfer

Source Server         : 54.223.121.3
Source Server Version : 50640
Source Host           : 54.223.121.3:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2019-03-29 09:00:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_audience`
-- ----------------------------
DROP TABLE IF EXISTS `t_audience`;
CREATE TABLE `t_audience` (
  `id` char(36) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `mobilePhone` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `hobby` varchar(200) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `region` varchar(100) NOT NULL DEFAULT '',
  `campaignName` varchar(100) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `creatorId` char(36) CHARACTER SET utf8 NOT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `modifierId` char(36) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`,`region`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_audience
-- ----------------------------

-- ----------------------------
-- Table structure for `t_audience_campaign`
-- ----------------------------
DROP TABLE IF EXISTS `t_audience_campaign`;
CREATE TABLE `t_audience_campaign` (
  `campaignId` char(36) DEFAULT NULL,
  `audienceId` char(36) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_audience_campaign
-- ----------------------------

-- ----------------------------
-- Table structure for `t_campaign`
-- ----------------------------
DROP TABLE IF EXISTS `t_campaign`;
CREATE TABLE `t_campaign` (
  `id` char(36) NOT NULL COMMENT '主键',
  `name` varchar(200) NOT NULL COMMENT '活动名称',
  `startDate` datetime NOT NULL COMMENT '开始日期',
  `endDate` datetime NOT NULL COMMENT '结束日期',
  `type` varchar(50) DEFAULT NULL COMMENT '活动类型',
  `country` char(36) NOT NULL COMMENT '国家',
  `province` char(36) DEFAULT NULL COMMENT '省',
  `city` char(36) DEFAULT NULL COMMENT '地级市',
  `region` char(36) DEFAULT NULL COMMENT '区',
  `address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `state` char(36) NOT NULL,
  `exhibitioHall` char(36) NOT NULL COMMENT '展馆，来自主数据的展馆',
  `sponsor` char(36) NOT NULL COMMENT '主办方，数据是客户',
  `creatorId` char(36) NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_campaign
-- ----------------------------

-- ----------------------------
-- Table structure for `t_case`
-- ----------------------------
DROP TABLE IF EXISTS `t_case`;
CREATE TABLE `t_case` (
  `id` char(36) NOT NULL COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '案例名',
  `url` varchar(1000) NOT NULL COMMENT '假定每个案例都有至少一个图片',
  `type` int(11) NOT NULL COMMENT '1,工厂，2，设计师',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_case
-- ----------------------------

-- ----------------------------
-- Table structure for `t_contact`
-- ----------------------------
DROP TABLE IF EXISTS `t_contact`;
CREATE TABLE `t_contact` (
  `id` char(36) NOT NULL COMMENT '主键',
  `name` varchar(60) NOT NULL COMMENT '联系人姓名',
  `mobilePhone` varchar(25) NOT NULL COMMENT '联系人电话',
  `position` varchar(100) DEFAULT NULL COMMENT '联系人职位',
  `email` varchar(50) NOT NULL,
  `state` int(11) NOT NULL COMMENT '１，可用；０，不可用',
  `creatorId` char(36) NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_contact
-- ----------------------------

-- ----------------------------
-- Table structure for `t_contract`
-- ----------------------------
DROP TABLE IF EXISTS `t_contract`;
CREATE TABLE `t_contract` (
  `id` char(36) NOT NULL,
  `signDay` date NOT NULL COMMENT '签署日期',
  `name` varchar(200) NOT NULL COMMENT '合同名称',
  `code` varchar(30) NOT NULL COMMENT '合同编码',
  `projectId` char(36) NOT NULL COMMENT '项目编号,项目名称',
  `campaignId` char(36) NOT NULL COMMENT '市场活动',
  `exhibitionNumber` varchar(30) NOT NULL COMMENT '展位号',
  `area` float(50,0) NOT NULL COMMENT '面积',
  `amount` float(50,0) NOT NULL COMMENT '合同总价',
  `category` char(36) NOT NULL COMMENT '合同分类',
  `tax` float(20,0) NOT NULL COMMENT '税',
  `paymentMethod` char(36) NOT NULL COMMENT '付款方式',
  `initialPaymentTime` date NOT NULL COMMENT '首付时间',
  `middlePaymentTime` date NOT NULL COMMENT '中款时间',
  `finalPaymentTime` date NOT NULL COMMENT '尾款时间',
  `billingInfo` varchar(255) NOT NULL COMMENT '开票信息',
  `firstParty` char(36) NOT NULL COMMENT '合同甲方',
  `secondParty` char(36) NOT NULL COMMENT '合同乙方',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `creatorId` char(36) NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_contract
-- ----------------------------

-- ----------------------------
-- Table structure for `t_contract_ledger`
-- ----------------------------
DROP TABLE IF EXISTS `t_contract_ledger`;
CREATE TABLE `t_contract_ledger` (
  `contractId` char(36) NOT NULL,
  `ledgerId` char(0) NOT NULL,
  PRIMARY KEY (`contractId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_contract_ledger
-- ----------------------------

-- ----------------------------
-- Table structure for `t_customer`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` char(36) NOT NULL COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '企业名称',
  `companyName` varchar(200) NOT NULL COMMENT '企业名称',
  `companyId` varchar(36) DEFAULT NULL,
  `ownerId` varchar(36) DEFAULT NULL,
  `industry` varchar(200) NOT NULL COMMENT '所属行业',
  `nature` varchar(200) NOT NULL COMMENT '企业性质',
  `country` varchar(200) CHARACTER SET utf8mb4 NOT NULL COMMENT '国家',
  `province` varchar(200) NOT NULL COMMENT '省',
  `city` varchar(200) NOT NULL COMMENT '城市',
  `region` varchar(200) NOT NULL COMMENT '区域',
  `address` varchar(255) DEFAULT NULL,
  `level` char(1) NOT NULL COMMENT '平台等级',
  `state` int(11) NOT NULL COMMENT '１，可用；０，不可用；２，锁定',
  `creatorId` char(36) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifierId` char(36) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_customer
-- ----------------------------

-- ----------------------------
-- Table structure for `t_customer_contact`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_contact`;
CREATE TABLE `t_customer_contact` (
  `customerId` char(36) NOT NULL COMMENT '客户主键',
  `contactId` char(36) NOT NULL COMMENT '联系人主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_customer_contact
-- ----------------------------

-- ----------------------------
-- Table structure for `t_customer_history`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_history`;
CREATE TABLE `t_customer_history` (
  `historyId` varchar(36) DEFAULT NULL,
  `customerId` varchar(36) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_customer_history
-- ----------------------------

-- ----------------------------
-- Table structure for `t_designer`
-- ----------------------------
DROP TABLE IF EXISTS `t_designer`;
CREATE TABLE `t_designer` (
  `id` char(36) NOT NULL COMMENT '主键',
  `referrer` char(36) NOT NULL COMMENT '推荐人，从销售列表选择',
  `name` varchar(60) NOT NULL COMMENT '设计师中文名',
  `nameEN` varchar(60) DEFAULT NULL COMMENT '设计师英文名',
  `mobilePhone` varchar(25) NOT NULL COMMENT '设计师电话',
  `wechat` varchar(50) DEFAULT NULL COMMENT '设计师微信',
  `email` varchar(50) NOT NULL COMMENT '设计师QQ邮箱',
  `country` char(36) NOT NULL COMMENT '国家',
  `province` char(36) DEFAULT NULL COMMENT '省',
  `city` char(36) DEFAULT NULL COMMENT '城市（地级市）',
  `area` char(36) DEFAULT NULL COMMENT '区　县级市',
  `address` varchar(200) NOT NULL COMMENT '地址',
  `englishAbility` char(36) NOT NULL COMMENT '英文能力',
  `goodAtIndustry` varchar(370) NOT NULL COMMENT '擅长行业',
  `goodAtArea` varchar(370) NOT NULL COMMENT '擅长面积 100、100~300、300以上',
  `designStyle` varchar(370) NOT NULL COMMENT '设计风格',
  `experience` varchar(20) NOT NULL COMMENT '从业年限',
  `gender` int(11) NOT NULL COMMENT '性别',
  `previous` varchar(200) NOT NULL COMMENT '最近上家公司',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `platformLevel` char(36) NOT NULL COMMENT '平台等级',
  `platformCreditLevel` varchar(255) DEFAULT NULL COMMENT '平台信用度评价',
  `state` int(11) NOT NULL COMMENT '１，可用；０，不可用',
  `creatorId` char(36) NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_designer
-- ----------------------------

-- ----------------------------
-- Table structure for `t_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionary`;
CREATE TABLE `t_dictionary` (
  `id` varchar(36) NOT NULL DEFAULT '',
  `sort` varchar(11) NOT NULL,
  `code` varchar(40) NOT NULL,
  `value` varchar(20) NOT NULL,
  `state` int(1) NOT NULL,
  `description` varchar(20) NOT NULL,
  `creatorId` char(36) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifierId` char(36) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`sort`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for `t_dictionary_func`
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionary_func`;
CREATE TABLE `t_dictionary_func` (
  `id` char(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ver` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_dictionary_func
-- ----------------------------

-- ----------------------------
-- Table structure for `t_exhibition_hall`
-- ----------------------------
DROP TABLE IF EXISTS `t_exhibition_hall`;
CREATE TABLE `t_exhibition_hall` (
  `id` char(36) NOT NULL,
  `name` varchar(200) NOT NULL COMMENT '展馆名称',
  `start` datetime NOT NULL COMMENT '开展时间',
  `country` varchar(200) DEFAULT NULL,
  `address` varchar(255) NOT NULL COMMENT '地址',
  `area` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `region` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `city` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `creatorId` char(36) NOT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `modifierId` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_exhibition_hall
-- ----------------------------

-- ----------------------------
-- Table structure for `t_factory`
-- ----------------------------
DROP TABLE IF EXISTS `t_factory`;
CREATE TABLE `t_factory` (
  `id` char(36) NOT NULL COMMENT '主键',
  `referrer` char(36) NOT NULL COMMENT '推荐人，从销售列表选择',
  `name` varchar(200) NOT NULL COMMENT '企业名称',
  `director` varchar(60) NOT NULL COMMENT '厂长',
  `firstOrderTime` datetime DEFAULT NULL COMMENT '首单时间，应是该工厂首个完成项目的创建时间',
  `country` char(36) NOT NULL COMMENT '国家',
  `province` char(36) DEFAULT NULL COMMENT '省',
  `city` char(36) DEFAULT NULL COMMENT '城市（地级市）',
  `area` char(36) DEFAULT NULL COMMENT '区　县级市',
  `address` varchar(200) NOT NULL COMMENT '地址',
  `factoryArea` float(11,0) NOT NULL COMMENT '厂房面积',
  `photo` varchar(1000) NOT NULL COMMENT '图片url，多个图片以逗号分割',
  `registeredCapital` float(11,0) DEFAULT NULL COMMENT '注册资金（18，2）',
  `taxpayerType` char(36) NOT NULL COMMENT '纳税人身份（一般纳税人、小规模纳税人、未注册公司）',
  `fixedEmployeeCount` int(11) DEFAULT NULL COMMENT '固定工人数量',
  `goodAtIndustry` varchar(370) NOT NULL COMMENT '擅长行业',
  `goodAtMaterial` varchar(370) NOT NULL COMMENT '擅长材料',
  `goodAtArea` varchar(370) NOT NULL COMMENT '擅长面积 100、100~300、300以上',
  `platformLevel` char(36) NOT NULL COMMENT '平台等级A/B/C',
  `valueAddedTaxAccount` varchar(100) DEFAULT NULL COMMENT '增值税发票账号',
  `taxNumber` varchar(100) DEFAULT NULL COMMENT '税号',
  `state` int(11) NOT NULL COMMENT '１，可用；０，不可用',
  `creatorId` char(36) NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_factory
-- ----------------------------

-- ----------------------------
-- Table structure for `t_factory_case`
-- ----------------------------
DROP TABLE IF EXISTS `t_factory_case`;
CREATE TABLE `t_factory_case` (
  `factoryId` char(36) NOT NULL COMMENT '工厂主键',
  `caseId` char(36) NOT NULL COMMENT '案例主键',
  PRIMARY KEY (`factoryId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_factory_case
-- ----------------------------

-- ----------------------------
-- Table structure for `t_factory_contact`
-- ----------------------------
DROP TABLE IF EXISTS `t_factory_contact`;
CREATE TABLE `t_factory_contact` (
  `id` char(36) NOT NULL DEFAULT '',
  `factoryId` char(36) DEFAULT NULL,
  `contactId` char(36) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `city` varchar(200) DEFAULT NULL,
  `platformLevel` varchar(200) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_factory_contact
-- ----------------------------

-- ----------------------------
-- Table structure for `t_follow_history`
-- ----------------------------
DROP TABLE IF EXISTS `t_follow_history`;
CREATE TABLE `t_follow_history` (
  `id` varchar(36) NOT NULL DEFAULT '',
  `followDetail` varchar(200) DEFAULT NULL,
  `followTime` varchar(200) DEFAULT NULL,
  `nextTime` varchar(255) DEFAULT NULL,
  `creatorId` char(36) CHARACTER SET utf8 NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) CHARACTER SET utf8 DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_follow_history
-- ----------------------------

-- ----------------------------
-- Table structure for `t_ledger`
-- ----------------------------
DROP TABLE IF EXISTS `t_ledger`;
CREATE TABLE `t_ledger` (
  `id` char(36) NOT NULL,
  `category` char(36) NOT NULL COMMENT '款项类型',
  `paymentMethod` char(36) NOT NULL COMMENT '支付方式',
  `costCenter` char(36) NOT NULL COMMENT '成本中心',
  `paymentTime` date NOT NULL COMMENT '付款时间',
  `paymentAmount` float(20,0) NOT NULL COMMENT '付款金额',
  `operator` varchar(30) NOT NULL COMMENT '经办人',
  `reasonForChange` varchar(100) DEFAULT NULL COMMENT '变更原因',
  `creatorId` char(36) NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_ledger
-- ----------------------------

-- ----------------------------
-- Table structure for `t_params`
-- ----------------------------
DROP TABLE IF EXISTS `t_params`;
CREATE TABLE `t_params` (
  `id` char(36) NOT NULL,
  `title` varchar(100) NOT NULL,
  `value` varchar(740) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_params
-- ----------------------------
INSERT INTO `t_params` VALUES ('22b328ca-f6a5-42cf-86c1-c8ef719a85b6', '供应商空闲时间参数', '4');
INSERT INTO `t_params` VALUES ('5551aea7-f6e6-4914-a042-d8bdcef8f342', '项目经理', '2');
INSERT INTO `t_params` VALUES ('5c5cde48-bd14-4006-9381-58c9972c3752', '设计师', '3');
INSERT INTO `t_params` VALUES ('f1441df7-a1e3-40ec-98c5-028697cb80f3', '销售', '1');

-- ----------------------------
-- Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` char(36) NOT NULL,
  `parentId` char(36) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `url` varchar(255) NOT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `icon` varchar(40) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `t_pm`
-- ----------------------------
DROP TABLE IF EXISTS `t_pm`;
CREATE TABLE `t_pm` (
  `id` char(36) NOT NULL COMMENT '主键',
  `referrer` char(36) NOT NULL COMMENT '推荐人，从销售列表选择',
  `name` varchar(60) NOT NULL COMMENT '设计师中文名',
  `nameEN` varchar(60) DEFAULT NULL COMMENT '设计师英文名',
  `mobilePhone` varchar(25) NOT NULL COMMENT '设计师电话',
  `wechat` varchar(50) DEFAULT NULL COMMENT '设计师微信',
  `email` varchar(50) NOT NULL COMMENT '设计师QQ邮箱',
  `country` char(36) NOT NULL COMMENT '国家',
  `province` char(36) DEFAULT NULL COMMENT '省',
  `city` char(36) DEFAULT NULL COMMENT '城市（地级市）',
  `area` char(36) DEFAULT NULL COMMENT '区　县级市',
  `address` varchar(200) NOT NULL COMMENT '地址',
  `englishAbility` char(36) NOT NULL COMMENT '英文能力',
  `goodAtIndustry` varchar(370) NOT NULL COMMENT '擅长行业',
  `goodAtArea` varchar(370) NOT NULL COMMENT '擅长面积 100、100~300、300以上',
  `designStyle` varchar(370) NOT NULL COMMENT '设计风格',
  `experience` varchar(20) NOT NULL COMMENT '从业年限',
  `gender` int(11) NOT NULL COMMENT '性别',
  `previous` varchar(200) NOT NULL COMMENT '最近上家公司',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `state` int(11) NOT NULL COMMENT '１，可用；０，不可用',
  `creatorId` char(36) NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_pm
-- ----------------------------

-- ----------------------------
-- Table structure for `t_pm_case`
-- ----------------------------
DROP TABLE IF EXISTS `t_pm_case`;
CREATE TABLE `t_pm_case` (
  `pmId` char(36) NOT NULL COMMENT '工厂主键',
  `caseId` char(36) NOT NULL COMMENT '案例主键',
  PRIMARY KEY (`pmId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_pm_case
-- ----------------------------

-- ----------------------------
-- Table structure for `t_production`
-- ----------------------------
DROP TABLE IF EXISTS `t_production`;
CREATE TABLE `t_production` (
  `id` char(36) NOT NULL,
  `category` char(36) NOT NULL COMMENT '产品分类',
  `name` varchar(200) NOT NULL COMMENT '产品名称',
  `desc` varchar(200) NOT NULL COMMENT '细节描述',
  `unit` char(36) NOT NULL COMMENT '单位',
  `way` char(36) NOT NULL COMMENT '方式',
  `low` float(20,0) NOT NULL COMMENT '低价',
  `middle` float(20,0) NOT NULL COMMENT '中价',
  `high` float(20,0) NOT NULL COMMENT '高价',
  `creatorId` char(36) NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_production
-- ----------------------------

-- ----------------------------
-- Table structure for `t_project`
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `id` char(36) NOT NULL,
  `name` varchar(200) NOT NULL COMMENT '项目名称',
  `type` char(36) NOT NULL COMMENT '类型',
  `ownerId` char(36) DEFAULT NULL,
  `customerId` char(36) DEFAULT NULL COMMENT '客户,办展不可选',
  `campaignId` char(36) NOT NULL COMMENT '市场活动',
  `code` varchar(30) NOT NULL COMMENT '项目编号',
  `budget` float(20,0) NOT NULL COMMENT '预算',
  `exhibitionNumber` varchar(30) NOT NULL COMMENT '展位号',
  `area` float(30,0) NOT NULL COMMENT '面积',
  `setupTime` datetime NOT NULL COMMENT '搭建时间',
  `tearDownTime` datetime NOT NULL COMMENT '撤展时间',
  `cost` float(20,0) DEFAULT NULL COMMENT '项目成本',
  `degreeOfImportance` char(36) NOT NULL COMMENT '重要程度',
  `potential` char(36) DEFAULT NULL COMMENT '项目潜力',
  `designProgress` char(36) DEFAULT NULL COMMENT '设计进度',
  `pmId` char(36) DEFAULT NULL COMMENT '项目经理',
  `projectProgress` char(36) DEFAULT NULL COMMENT '项目进度',
  `factoryProgress` char(36) DEFAULT NULL COMMENT '工厂进度',
  `projectScore` float(10,0) DEFAULT NULL COMMENT '项目评分',
  `designScore` float(10,0) DEFAULT NULL COMMENT '设计评分',
  `pmScore` float(10,0) DEFAULT NULL COMMENT '项目经理评分',
  `developSaleId` char(36) NOT NULL COMMENT '开发销售',
  `traceSaleId` char(36) DEFAULT NULL COMMENT '跟进销售',
  `photo` varchar(500) DEFAULT NULL COMMENT '项目图片',
  `state` int(11) NOT NULL COMMENT '１，可用；０，不可用',
  `creatorId` char(36) NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_project
-- ----------------------------

-- ----------------------------
-- Table structure for `t_project_designer`
-- ----------------------------
DROP TABLE IF EXISTS `t_project_designer`;
CREATE TABLE `t_project_designer` (
  `projectId` char(36) NOT NULL,
  `designerId` char(36) NOT NULL,
  PRIMARY KEY (`projectId`,`designerId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_project_designer
-- ----------------------------

-- ----------------------------
-- Table structure for `t_project_factory`
-- ----------------------------
DROP TABLE IF EXISTS `t_project_factory`;
CREATE TABLE `t_project_factory` (
  `projectId` char(36) NOT NULL,
  `factoryId` char(36) NOT NULL,
  PRIMARY KEY (`projectId`,`factoryId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_project_factory
-- ----------------------------

-- ----------------------------
-- Table structure for `t_quotation`
-- ----------------------------
DROP TABLE IF EXISTS `t_quotation`;
CREATE TABLE `t_quotation` (
  `id` char(36) NOT NULL,
  `projectId` char(36) DEFAULT NULL,
  `priceLevel` char(36) NOT NULL COMMENT '价格档次',
  `productionId` char(36) DEFAULT NULL COMMENT '产品主键',
  `unitPrice` float(20,0) NOT NULL COMMENT '单价',
  `count` float(20,0) NOT NULL COMMENT '数量',
  `workContent` varchar(255) NOT NULL COMMENT '工作内容',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creatorId` char(36) NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_quotation
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` char(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  `state` int(11) NOT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `creatorId` char(36) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifierId` char(36) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('467c9fc4-148e-4c23-98fd-87b5c0947f72', 'admin', '1', '管理员', '1', '2019-03-29 07:52:51', '1', '2019-03-29 07:52:51');

-- ----------------------------
-- Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `roleId` char(36) NOT NULL,
  `permissionId` char(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sale`
-- ----------------------------
DROP TABLE IF EXISTS `t_sale`;
CREATE TABLE `t_sale` (
  `id` char(36) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '销售姓名',
  `channel` varchar(370) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '销售渠道代码',
  `mobilePhone` varchar(25) NOT NULL COMMENT '销售电话',
  `email` varchar(100) NOT NULL COMMENT '销售QQ邮箱',
  `state` int(11) NOT NULL COMMENT '１，可用；０，不可用',
  `creatorId` char(36) NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_sale
-- ----------------------------

-- ----------------------------
-- Table structure for `t_spectator`
-- ----------------------------
DROP TABLE IF EXISTS `t_spectator`;
CREATE TABLE `t_spectator` (
  `id` char(36) NOT NULL,
  `name` varchar(200) NOT NULL COMMENT '观众名称',
  `mobilePhone` varchar(25) NOT NULL COMMENT '电话',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `country` char(36) NOT NULL COMMENT '国家',
  `province` char(36) DEFAULT NULL COMMENT '省',
  `city` char(36) DEFAULT NULL COMMENT '城市（地级市）',
  `area` char(36) DEFAULT NULL COMMENT '区　县级市',
  `hobbies` varchar(370) NOT NULL COMMENT '兴趣爱好',
  `createTime` datetime NOT NULL,
  `creatorId` char(36) NOT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `modifierId` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_spectator
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` char(36) NOT NULL,
  `name` varchar(80) NOT NULL,
  `nameEN` varchar(80) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `mobilePhone` varchar(25) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `openId` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `gender` smallint(6) DEFAULT '0' COMMENT '1，男；2，女',
  `state` smallint(6) DEFAULT '1' COMMENT '1.可用，2.锁定，3.禁用',
  `referrer` char(36) NOT NULL COMMENT '(设计师、项目经理用)推荐人，从销售列表选择',
  `wechat` varchar(50) DEFAULT NULL COMMENT '(设计师、项目经理用)设计师微信',
  `englishAbility` char(36) NOT NULL COMMENT '(设计师、项目经理用)英文能力',
  `goodAtIndustry` varchar(370) NOT NULL COMMENT '(设计师、项目经理用)擅长行业',
  `goodAtArea` varchar(370) NOT NULL COMMENT '(设计师、项目经理用)擅长面积 100、100~300、300以上',
  `designStyle` varchar(370) NOT NULL COMMENT '(设计师用)设计风格',
  `experience` varchar(20) NOT NULL COMMENT '(设计师、项目经理用)从业年限',
  `previous` varchar(200) NOT NULL COMMENT '(设计师、项目经理用)最近上家公司',
  `birthday` datetime DEFAULT NULL COMMENT '(设计师、项目经理用)出生日期',
  `platformLevel` char(36) NOT NULL COMMENT '(设计师用)平台等级',
  `platformCreditLevel` varchar(255) DEFAULT NULL COMMENT '(设计师用)平台信用度评价',
  `channel` varchar(370) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '(销售用)销售渠道代码',
  `hobbies` varchar(370) NOT NULL COMMENT '(观众用)兴趣爱好',
  `city` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `country` varchar(200) DEFAULT NULL,
  `region` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `creatorId` char(36) NOT NULL,
  `createTime` datetime NOT NULL,
  `modifierId` char(36) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `designerCategory` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `mobilePhone` (`mobilePhone`) USING BTREE,
  UNIQUE KEY `openId` (`openId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', null, '1332323321', 'adfa@qq.com', null, '123abc', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', null, '1', '1', '1', '1', null, null, null, '1', '2019-03-29 07:51:25', '1', '2019-03-29 07:54:22', null);

-- ----------------------------
-- Table structure for `t_user_case`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_case`;
CREATE TABLE `t_user_case` (
  `designerId` char(36) NOT NULL COMMENT '工厂主键',
  `caseId` char(36) NOT NULL COMMENT '案例主键',
  PRIMARY KEY (`designerId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user_case
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `userId` char(36) NOT NULL,
  `roleId` char(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '467c9fc4-148e-4c23-98fd-87b5c0947f72');

-- ----------------------------
-- Table structure for `t_vendor_appointment`
-- ----------------------------
DROP TABLE IF EXISTS `t_vendor_appointment`;
CREATE TABLE `t_vendor_appointment` (
  `id` char(36) NOT NULL,
  `vendorId` char(36) NOT NULL,
  `title` varchar(255) NOT NULL,
  `allDay` tinyint(255) NOT NULL,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `type` int(255) NOT NULL,
  `createTime` datetime NOT NULL,
  `creatorId` char(36) NOT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `modifierId` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_vendor_appointment
-- ----------------------------
