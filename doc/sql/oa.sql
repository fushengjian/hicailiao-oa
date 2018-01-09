/*
Navicat MySQL Data Transfer

Source Server         : MyDataBase
Source Server Version : 50605
Source Host           : localhost:3306
Source Database       : oa

Target Server Type    : MYSQL
Target Server Version : 50605
File Encoding         : 65001

Date: 2018-01-09 16:23:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oa_accessory
-- ----------------------------
DROP TABLE IF EXISTS `oa_accessory`;
CREATE TABLE `oa_accessory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` tinyint(1) NOT NULL,
  `ext` varchar(255) DEFAULT NULL COMMENT '图片类型',
  `height` int(11) NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '图片名称',
  `path` varchar(255) DEFAULT NULL COMMENT '图片相对路径',
  `size` float NOT NULL COMMENT '图片大小',
  `width` int(11) NOT NULL,
  `album_id` bigint(20) DEFAULT NULL COMMENT '相册ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '相片所属用户ID',
  `config_id` bigint(20) DEFAULT NULL,
  `url_hash` int(11) DEFAULT NULL,
  `displayName` varchar(255) DEFAULT NULL COMMENT '图片展示名称，用于相册中搜索图片',
  PRIMARY KEY (`id`),
  KEY `FK9BF2D721537B6C51` (`user_id`),
  KEY `FK9BF2D7218603E3C3` (`album_id`),
  KEY `FK9BF2D721707C8F90` (`config_id`),
  KEY `FK9BF2D7218D8B425` (`config_id`),
  KEY `FK9BF2D72130E5FE9C` (`user_id`),
  KEY `FK9BF2D72155EB9AD8` (`album_id`),
  CONSTRAINT `FK9BF2D72130E5FE9C` FOREIGN KEY (`user_id`) REFERENCES `shop_user` (`id`),
  CONSTRAINT `FK9BF2D721537B6C51` FOREIGN KEY (`user_id`) REFERENCES `shop_user` (`id`),
  CONSTRAINT `FK9BF2D72155EB9AD8` FOREIGN KEY (`album_id`) REFERENCES `shop_album` (`id`),
  CONSTRAINT `FK9BF2D721707C8F90` FOREIGN KEY (`config_id`) REFERENCES `shop_sysconfig` (`id`),
  CONSTRAINT `FK9BF2D7218603E3C3` FOREIGN KEY (`album_id`) REFERENCES `shop_album` (`id`),
  CONSTRAINT `FK9BF2D7218D8B425` FOREIGN KEY (`config_id`) REFERENCES `shop_sysconfig` (`id`),
  CONSTRAINT `FKfjvkrc9yfs7yxsuqbvh3ah2g1` FOREIGN KEY (`user_id`) REFERENCES `shop_user` (`id`),
  CONSTRAINT `FKh4mylbrgud55003v3et15hm6t` FOREIGN KEY (`album_id`) REFERENCES `shop_album` (`id`),
  CONSTRAINT `FKonm9a9b7vrcl9essbv81fpqgq` FOREIGN KEY (`config_id`) REFERENCES `shop_sysconfig` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=431420 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_accessory
-- ----------------------------

-- ----------------------------
-- Table structure for oa_area
-- ----------------------------
DROP TABLE IF EXISTS `oa_area`;
CREATE TABLE `oa_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `areaName` varchar(255) DEFAULT NULL COMMENT '区域名称',
  `level` int(11) NOT NULL COMMENT '区域级别,0第一级，1第二级，2第三级',
  `sequence` int(11) NOT NULL COMMENT '排序',
  `parent_id` bigint(20) DEFAULT NULL,
  `common` bit(1) DEFAULT b'0' COMMENT '常用地区：1是，0否',
  PRIMARY KEY (`id`),
  KEY `FK7D6B3B1ED79E13D4` (`parent_id`),
  KEY `FK7D6B3B1EB508A61F` (`parent_id`),
  CONSTRAINT `FK7D6B3B1EB508A61F` FOREIGN KEY (`parent_id`) REFERENCES `shop_area` (`id`),
  CONSTRAINT `FK7D6B3B1ED79E13D4` FOREIGN KEY (`parent_id`) REFERENCES `shop_area` (`id`),
  CONSTRAINT `FKmjwu8197awe2b88ouldaqvfq4` FOREIGN KEY (`parent_id`) REFERENCES `shop_area` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4525507 DEFAULT CHARSET=utf8 COMMENT='地区表';

-- ----------------------------
-- Records of oa_area
-- ----------------------------

-- ----------------------------
-- Table structure for oa_res
-- ----------------------------
DROP TABLE IF EXISTS `oa_res`;
CREATE TABLE `oa_res` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  `resName` varchar(255) DEFAULT NULL,
  `sequence` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1452 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_res
-- ----------------------------

-- ----------------------------
-- Table structure for oa_role
-- ----------------------------
DROP TABLE IF EXISTS `oa_role`;
CREATE TABLE `oa_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `display` bit(1) NOT NULL,
  `info` varchar(255) DEFAULT NULL,
  `roleCode` varchar(255) DEFAULT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  `sequence` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `rg_id` bigint(20) DEFAULT NULL,
  `urlAddr` varchar(100) DEFAULT NULL COMMENT 'url',
  PRIMARY KEY (`id`),
  KEY `FK7D72EB07C95D7AF7` (`rg_id`),
  KEY `FK7D72EB0761B99F8C` (`rg_id`),
  CONSTRAINT `FK1rlm6gkgdyf0tqds8snso72sv` FOREIGN KEY (`rg_id`) REFERENCES `shop_rolegroup` (`id`),
  CONSTRAINT `FK7D72EB0761B99F8C` FOREIGN KEY (`rg_id`) REFERENCES `shop_rolegroup` (`id`),
  CONSTRAINT `FK7D72EB07C95D7AF7` FOREIGN KEY (`rg_id`) REFERENCES `shop_rolegroup` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=245 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_role
-- ----------------------------

-- ----------------------------
-- Table structure for oa_rolegroup
-- ----------------------------
DROP TABLE IF EXISTS `oa_rolegroup`;
CREATE TABLE `oa_rolegroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sequence` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_rolegroup
-- ----------------------------

-- ----------------------------
-- Table structure for oa_role_res
-- ----------------------------
DROP TABLE IF EXISTS `oa_role_res`;
CREATE TABLE `oa_role_res` (
  `role_id` bigint(20) NOT NULL,
  `res_id` bigint(20) NOT NULL,
  KEY `FK6494F768261DF063` (`res_id`),
  KEY `FK6494F768AE50A871` (`role_id`),
  KEY `FK6494F7682D4268B8` (`res_id`),
  KEY `FK6494F7688BBB3ABC` (`role_id`),
  CONSTRAINT `FK3dq92ogm3ln8tnoolg42n9tgk` FOREIGN KEY (`res_id`) REFERENCES `shop_res` (`id`),
  CONSTRAINT `FK6494F768261DF063` FOREIGN KEY (`res_id`) REFERENCES `shop_res` (`id`),
  CONSTRAINT `FK6494F7682D4268B8` FOREIGN KEY (`res_id`) REFERENCES `shop_res` (`id`),
  CONSTRAINT `FK6494F7688BBB3ABC` FOREIGN KEY (`role_id`) REFERENCES `shop_role` (`id`),
  CONSTRAINT `FK6494F768AE50A871` FOREIGN KEY (`role_id`) REFERENCES `shop_role` (`id`),
  CONSTRAINT `FKi0ox2hlwmi62npx2aoipoxnst` FOREIGN KEY (`role_id`) REFERENCES `shop_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_role_res
-- ----------------------------

-- ----------------------------
-- Table structure for oa_sysconfig
-- ----------------------------
DROP TABLE IF EXISTS `oa_sysconfig`;
CREATE TABLE `oa_sysconfig` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `bigHeight` int(11) NOT NULL,
  `bigWidth` int(11) NOT NULL,
  `closeReason` longtext,
  `codeStat` longtext,
  `complaint_time` int(11) NOT NULL,
  `consumptionRatio` int(11) NOT NULL,
  `copyright` varchar(255) DEFAULT NULL,
  `creditrule` longtext COMMENT '卖家信用',
  `deposit` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `emailEnable` bit(1) NOT NULL,
  `emailHost` varchar(255) DEFAULT NULL,
  `emailPort` int(11) NOT NULL,
  `emailPws` varchar(255) DEFAULT NULL,
  `emailTest` varchar(255) DEFAULT NULL,
  `emailUser` varchar(255) DEFAULT NULL,
  `emailUserName` varchar(255) DEFAULT NULL,
  `everyIndentLimit` int(11) NOT NULL,
  `gold` bit(1) NOT NULL,
  `goldMarketValue` int(11) NOT NULL,
  `groupBuy` bit(1) NOT NULL,
  `hotSearch` varchar(255) DEFAULT NULL,
  `imageFilesize` int(11) NOT NULL,
  `imageSaveType` varchar(255) DEFAULT NULL,
  `imageSuffix` varchar(255) DEFAULT NULL,
  `indentComment` int(11) NOT NULL,
  `integral` bit(1) NOT NULL COMMENT '会员积分管理  为0禁用  为1开启',
  `integralRate` int(11) NOT NULL,
  `integralStore` bit(1) NOT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `memberDayLogin` int(11) NOT NULL,
  `memberRegister` int(11) NOT NULL,
  `middleHeight` int(11) NOT NULL,
  `middleWidth` int(11) NOT NULL,
  `securityCodeConsult` bit(1) NOT NULL,
  `securityCodeLogin` bit(1) NOT NULL,
  `securityCodeRegister` bit(1) NOT NULL,
  `securityCodeType` varchar(255) DEFAULT NULL,
  `share_code` longtext,
  `smallHeight` int(11) NOT NULL,
  `smallWidth` int(11) NOT NULL,
  `smsEnbale` bit(1) NOT NULL,
  `smsPassword` varchar(255) DEFAULT NULL,
  `smsTest` varchar(255) DEFAULT NULL,
  `smsURL` varchar(255) DEFAULT NULL,
  `smsUserName` varchar(255) DEFAULT NULL,
  `store_allow` bit(1) NOT NULL COMMENT '是否允许开店  1 允许  0 不允许',
  `store_payment` longtext,
  `sysLanguage` varchar(255) DEFAULT NULL,
  `templates` longtext COMMENT '模版名称',
  `title` varchar(255) DEFAULT NULL,
  `uploadFilePath` varchar(255) DEFAULT NULL,
  `user_creditrule` longtext COMMENT '买家信用',
  `visitorConsult` bit(1) NOT NULL,
  `voucher` bit(1) NOT NULL,
  `websiteName` varchar(255) DEFAULT NULL,
  `websiteState` bit(1) NOT NULL,
  `ztc_price` int(11) NOT NULL,
  `ztc_status` bit(1) NOT NULL,
  `goodsImage_id` bigint(20) DEFAULT NULL,
  `memberIcon_id` bigint(20) DEFAULT NULL,
  `storeImage_id` bigint(20) DEFAULT NULL,
  `websiteLogo_id` bigint(20) DEFAULT NULL,
  `domain_allow_count` int(11) DEFAULT '0',
  `second_domain_open` bit(1) DEFAULT b'0',
  `sys_domain` longtext,
  `qq_login` bit(1) DEFAULT b'0',
  `qq_login_id` varchar(255) DEFAULT NULL,
  `qq_login_key` varchar(255) DEFAULT NULL,
  `qq_domain_code` longtext,
  `sina_domain_code` longtext,
  `sina_login` bit(1) DEFAULT b'0',
  `sina_login_id` varchar(255) DEFAULT NULL,
  `sina_login_key` varchar(255) DEFAULT NULL,
  `imageWebServer` varchar(255) DEFAULT NULL,
  `lucene_update` datetime DEFAULT NULL,
  `alipay_fenrun` int(11) DEFAULT '0',
  `balance_fenrun` int(11) DEFAULT '0',
  `auto_order_confirm` int(11) DEFAULT '7',
  `auto_order_notice` int(11) DEFAULT '3',
  `bargain_maximum` int(11) DEFAULT '0',
  `bargain_rebate` decimal(3,2) DEFAULT NULL,
  `bargain_state` longtext,
  `bargain_status` int(11) DEFAULT '0',
  `bargain_title` varchar(255) DEFAULT NULL,
  `service_qq_list` longtext,
  `service_telphone_list` longtext,
  `sys_delivery_maximum` int(11) DEFAULT '0',
  `uc_bbs` bit(1) DEFAULT b'0',
  `kuaidi_id` longtext,
  `uc_api` varchar(255) DEFAULT NULL,
  `uc_appid` varchar(255) DEFAULT NULL,
  `uc_database` varchar(255) DEFAULT NULL,
  `uc_database_port` varchar(255) DEFAULT NULL,
  `uc_database_pws` varchar(255) DEFAULT NULL,
  `uc_database_url` varchar(255) DEFAULT NULL,
  `uc_database_username` varchar(255) DEFAULT NULL,
  `uc_ip` varchar(255) DEFAULT NULL,
  `uc_key` varchar(255) DEFAULT NULL,
  `uc_table_preffix` varchar(255) DEFAULT NULL,
  `currency_code` varchar(255) DEFAULT '������������������������������������������������������',
  `bargain_validity` int(11) DEFAULT '3',
  `delivery_amount` int(11) DEFAULT '50',
  `delivery_status` int(11) DEFAULT '0',
  `delivery_title` varchar(255) DEFAULT NULL,
  `websiteCss` varchar(255) DEFAULT 'blue',
  `combin_amount` int(11) DEFAULT '50',
  `combin_count` int(11) DEFAULT '3',
  `ztc_goods_view` int(11) DEFAULT '0',
  `auto_order_evaluate` int(11) DEFAULT '7',
  `auto_order_return` int(11) DEFAULT '7',
  `weixin_store` bit(1) DEFAULT b'0',
  `weixin_amount` int(11) DEFAULT '50',
  `config_payment_type` int(11) DEFAULT '0',
  `weixin_account` varchar(255) DEFAULT NULL,
  `weixin_appId` varchar(255) DEFAULT NULL,
  `weixin_appSecret` varchar(255) DEFAULT NULL,
  `weixin_token` varchar(255) DEFAULT NULL,
  `weixin_welecome_content` longtext,
  `store_weixin_logo_id` bigint(20) DEFAULT NULL,
  `weixin_qr_img_id` bigint(20) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `generator` varchar(255) DEFAULT NULL,
  `misc_webserver` varchar(255) DEFAULT NULL,
  `chaturl` varchar(255) DEFAULT NULL COMMENT '聊天地址url',
  `groupBuyapply` bit(1) NOT NULL,
  `websiteLoader_id` bigint(20) DEFAULT NULL COMMENT '加载图ID',
  PRIMARY KEY (`id`),
  KEY `FK40DBBC5E900C422F` (`websiteLogo_id`),
  KEY `FK40DBBC5E771DBD5B` (`storeImage_id`),
  KEY `FK40DBBC5ED57299C2` (`memberIcon_id`),
  KEY `FK40DBBC5EE23B16D0` (`goodsImage_id`),
  KEY `FK40DBBC5ECA8E3053` (`weixin_qr_img_id`),
  KEY `FK40DBBC5E8244156B` (`store_weixin_logo_id`),
  KEY `FK40DBBC5E286866C4` (`websiteLogo_id`),
  KEY `FK40DBBC5EF79E1F0` (`storeImage_id`),
  KEY `FK40DBBC5E62EA54E8` (`weixin_qr_img_id`),
  KEY `FK40DBBC5E6DCEBE57` (`memberIcon_id`),
  KEY `FK40DBBC5E1AA03A00` (`store_weixin_logo_id`),
  KEY `FK40DBBC5E7A973B65` (`goodsImage_id`),
  KEY `FK9veee65fag9n0nwnpnni58nub` (`websiteLoader_id`),
  CONSTRAINT `FK28wpjeiaogh3t3qisqw7vcqki` FOREIGN KEY (`goodsImage_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK40DBBC5E1AA03A00` FOREIGN KEY (`store_weixin_logo_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK40DBBC5E286866C4` FOREIGN KEY (`websiteLogo_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK40DBBC5E62EA54E8` FOREIGN KEY (`weixin_qr_img_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK40DBBC5E6DCEBE57` FOREIGN KEY (`memberIcon_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK40DBBC5E771DBD5B` FOREIGN KEY (`storeImage_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK40DBBC5E7A973B65` FOREIGN KEY (`goodsImage_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK40DBBC5E8244156B` FOREIGN KEY (`store_weixin_logo_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK40DBBC5E900C422F` FOREIGN KEY (`websiteLogo_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK40DBBC5ECA8E3053` FOREIGN KEY (`weixin_qr_img_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK40DBBC5ED57299C2` FOREIGN KEY (`memberIcon_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK40DBBC5EE23B16D0` FOREIGN KEY (`goodsImage_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK40DBBC5EF79E1F0` FOREIGN KEY (`storeImage_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK5u2xk655b2wyphsdt9j0as47a` FOREIGN KEY (`storeImage_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK9loabbhtmg6dcc2vwppg9ul25` FOREIGN KEY (`store_weixin_logo_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK9veee65fag9n0nwnpnni58nub` FOREIGN KEY (`websiteLoader_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FKao6yhcj4lthjntsb4oto40lx9` FOREIGN KEY (`websiteLogo_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FKipynsfv2tp2wnogvd80ffiupq` FOREIGN KEY (`weixin_qr_img_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FKphkud973slh157t7tcpa9nqmr` FOREIGN KEY (`memberIcon_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `shop_sysconfig_ibfk_1` FOREIGN KEY (`websiteLoader_id`) REFERENCES `shop_accessory` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_sysconfig
-- ----------------------------

-- ----------------------------
-- Table structure for oa_syslog
-- ----------------------------
DROP TABLE IF EXISTS `oa_syslog`;
CREATE TABLE `oa_syslog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `content` longtext,
  `ip` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEEA95FA8537B6C51` (`user_id`),
  KEY `FKEEA95FA830E5FE9C` (`user_id`),
  CONSTRAINT `FKEEA95FA830E5FE9C` FOREIGN KEY (`user_id`) REFERENCES `shop_user` (`id`),
  CONSTRAINT `FKEEA95FA8537B6C51` FOREIGN KEY (`user_id`) REFERENCES `shop_user` (`id`),
  CONSTRAINT `FKe1hmhaeyarin0iwbbab09btuy` FOREIGN KEY (`user_id`) REFERENCES `shop_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_syslog
-- ----------------------------

-- ----------------------------
-- Table structure for oa_user
-- ----------------------------
DROP TABLE IF EXISTS `oa_user`;
CREATE TABLE `oa_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` tinyint(1) NOT NULL,
  `MSN` varchar(255) DEFAULT NULL,
  `QQ` varchar(255) DEFAULT NULL,
  `WW` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `availableBalance` decimal(12,2) DEFAULT '0.00' COMMENT '可用余额',
  `birthday` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `emailValid` int(11) NOT NULL DEFAULT '0',
  `freezeBlance` decimal(12,2) DEFAULT '0.00' COMMENT '冻结余额',
  `gold` int(11) NOT NULL DEFAULT '0' COMMENT '金币余额',
  `integral` int(11) NOT NULL DEFAULT '0' COMMENT '用户获得的积分',
  `lastLoginDate` datetime DEFAULT NULL,
  `lastLoginIp` varchar(255) DEFAULT NULL,
  `loginCount` int(11) NOT NULL DEFAULT '0',
  `loginDate` datetime DEFAULT NULL,
  `loginIp` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `report` int(11) NOT NULL,
  `sex` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `trueName` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `userRole` varchar(255) DEFAULT NULL,
  `user_credit` int(11) NOT NULL,
  `photo_id` bigint(20) DEFAULT NULL,
  `store_id` bigint(20) DEFAULT NULL,
  `qq_openid` varchar(255) DEFAULT NULL COMMENT 'QQ绑定串',
  `sina_openid` varchar(255) DEFAULT NULL COMMENT '新浪绑定串',
  `store_quick_menu` longtext,
  `parent_id` bigint(20) DEFAULT NULL,
  `years` int(11) DEFAULT '0',
  `area_id` bigint(20) DEFAULT NULL,
  `config` tinyblob,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `FK7D74565C920D7683` (`store_id`),
  KEY `FK7D74565CE62DDD43` (`photo_id`),
  KEY `FK7D74565CD7A72F12` (`parent_id`),
  KEY `FK7D74565C2FB91D11` (`area_id`),
  KEY `FK7D74565CD23AF5C` (`area_id`),
  KEY `FK7D74565CB511C15D` (`parent_id`),
  KEY `FK7D74565C61F52D98` (`store_id`),
  KEY `FK7D74565C7E8A01D8` (`photo_id`),
  CONSTRAINT `FK4i46okwbcd6b92hg1ryjetybj` FOREIGN KEY (`area_id`) REFERENCES `shop_area` (`id`),
  CONSTRAINT `FK6frr9u70sllbarglr1pirpcyd` FOREIGN KEY (`photo_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK7D74565C2FB91D11` FOREIGN KEY (`area_id`) REFERENCES `shop_area` (`id`),
  CONSTRAINT `FK7D74565C7E8A01D8` FOREIGN KEY (`photo_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK7D74565CB511C15D` FOREIGN KEY (`parent_id`) REFERENCES `shop_user` (`id`),
  CONSTRAINT `FK7D74565CD23AF5C` FOREIGN KEY (`area_id`) REFERENCES `shop_area` (`id`),
  CONSTRAINT `FK7D74565CD7A72F12` FOREIGN KEY (`parent_id`) REFERENCES `shop_user` (`id`),
  CONSTRAINT `FK7D74565CE62DDD43` FOREIGN KEY (`photo_id`) REFERENCES `shop_accessory` (`id`),
  CONSTRAINT `FK7np5ja1qnhhhcnvpqwngf5j24` FOREIGN KEY (`parent_id`) REFERENCES `shop_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33067 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_user
-- ----------------------------

-- ----------------------------
-- Table structure for oa_userconfig
-- ----------------------------
DROP TABLE IF EXISTS `oa_userconfig`;
CREATE TABLE `oa_userconfig` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKA058A49E537B6C51` (`user_id`),
  KEY `FKA058A49E30E5FE9C` (`user_id`),
  CONSTRAINT `FK7ro5k1qvb96o4nkbs1vgq6vbu` FOREIGN KEY (`user_id`) REFERENCES `shop_user` (`id`),
  CONSTRAINT `FKA058A49E30E5FE9C` FOREIGN KEY (`user_id`) REFERENCES `shop_user` (`id`),
  CONSTRAINT `FKA058A49E537B6C51` FOREIGN KEY (`user_id`) REFERENCES `shop_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_userconfig
-- ----------------------------

-- ----------------------------
-- Table structure for oa_user_role
-- ----------------------------
DROP TABLE IF EXISTS `oa_user_role`;
CREATE TABLE `oa_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK2E3F7C79AE50A871` (`role_id`),
  KEY `FK2E3F7C79537B6C51` (`user_id`),
  KEY `FK2E3F7C798BBB3ABC` (`role_id`),
  KEY `FK2E3F7C7930E5FE9C` (`user_id`),
  CONSTRAINT `FK2E3F7C7930E5FE9C` FOREIGN KEY (`user_id`) REFERENCES `shop_user` (`id`),
  CONSTRAINT `FK2E3F7C79537B6C51` FOREIGN KEY (`user_id`) REFERENCES `shop_user` (`id`),
  CONSTRAINT `FK2E3F7C798BBB3ABC` FOREIGN KEY (`role_id`) REFERENCES `shop_role` (`id`),
  CONSTRAINT `FK2E3F7C79AE50A871` FOREIGN KEY (`role_id`) REFERENCES `shop_role` (`id`),
  CONSTRAINT `FK838c62nlateeftot6uug22t1s` FOREIGN KEY (`user_id`) REFERENCES `shop_user` (`id`),
  CONSTRAINT `FKc2tubjuawo9ymf3na04640qak` FOREIGN KEY (`role_id`) REFERENCES `shop_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_area
-- ----------------------------
DROP TABLE IF EXISTS `sys_area`;
CREATE TABLE `sys_area` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` bigint(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `name` varchar(100) NOT NULL COMMENT '区域名称',
  `type` char(1) DEFAULT NULL COMMENT '区域类型',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_area_parent_id` (`parent_id`),
  KEY `sys_area_parent_ids` (`parent_ids`(255)),
  KEY `sys_area_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='区域表';

-- ----------------------------
-- Records of sys_area
-- ----------------------------
INSERT INTO `sys_area` VALUES ('1', '0', '0,', '100000', '中国', '1', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_area` VALUES ('8', '1', '0,1,', '898', '海南省', '2', '1', '2013-05-27 08:00:00', '1', '2015-12-04 10:11:21', '', '0');
INSERT INTO `sys_area` VALUES ('9', '8', '0,1,8,', '100200', '三亚市', '3', '1', '2015-12-05 08:23:17', '1', '2015-12-05 08:23:17', '', '0');
INSERT INTO `sys_area` VALUES ('10', '8', '0,1,8,', '100100', '海口市', '3', '1', '2015-12-04 10:11:46', '1', '2015-12-04 10:12:00', '', '0');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` int(11) NOT NULL COMMENT '排序（升序）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '正常', '0', 'del_flag', '删除标记', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('2', '删除', '1', 'del_flag', '删除标记', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('3', '显示', '1', 'show_hide', '显示/隐藏', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('4', '隐藏', '0', 'show_hide', '显示/隐藏', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('5', '是', '1', 'yes_no', '是/否', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('6', '否', '0', 'yes_no', '是/否', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('7', '红色', 'red', 'color', '颜色值', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('8', '绿色', 'green', 'color', '颜色值', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('9', '蓝色', 'blue', 'color', '颜色值', '30', '1', '2013-05-27 08:00:00', '1', '2015-12-05 08:41:53', null, '0');
INSERT INTO `sys_dict` VALUES ('17', '国家', '1', 'sys_area_type', '区域类型', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('18', '省份、直辖市', '2', 'sys_area_type', '区域类型', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('19', '地市', '3', 'sys_area_type', '区域类型', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('20', '区县', '4', 'sys_area_type', '区域类型', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('21', '公司', '1', 'sys_office_type', '机构类型', '60', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('22', '部门', '2', 'sys_office_type', '机构类型', '70', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('23', '一级', '1', 'sys_office_grade', '机构等级', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('24', '二级', '2', 'sys_office_grade', '机构等级', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('25', '三级', '3', 'sys_office_grade', '机构等级', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('26', '四级', '4', 'sys_office_grade', '机构等级', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('27', '所有数据', '1', 'sys_data_scope', '数据范围', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('28', '所在公司及以下数据', '2', 'sys_data_scope', '数据范围', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('29', '所在公司数据', '3', 'sys_data_scope', '数据范围', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('30', '所在部门及以下数据', '4', 'sys_data_scope', '数据范围', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('31', '所在部门数据', '5', 'sys_data_scope', '数据范围', '50', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('32', '仅本人数据', '8', 'sys_data_scope', '数据范围', '90', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('33', '按明细设置', '9', 'sys_data_scope', '数据范围', '100', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('34', '系统管理', '1', 'sys_user_type', '用户类型', '10', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('35', '部门经理', '2', 'sys_user_type', '用户类型', '20', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('36', '普通用户', '3', 'sys_user_type', '用户类型', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('60', '调休', '4', 'oa_leave_type', '请假类型', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('61', '婚假', '5', 'oa_leave_type', '请假类型', '60', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('62', '接入日志', '1', 'sys_log_type', '日志类型', '30', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('63', '异常日志', '2', 'sys_log_type', '日志类型', '40', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('64', '菜单', '0', 'menu_type', '菜单功能类型', '10', '1', '2015-12-04 20:08:40', '1', '2015-12-04 20:08:40', null, '0');
INSERT INTO `sys_dict` VALUES ('65', '功能菜单', '1', 'menu_type', '菜单功能类型', '20', '1', '2015-12-04 20:08:57', '1', '2015-12-04 20:08:57', null, '0');
INSERT INTO `sys_dict` VALUES ('66', '功能按钮', '2', 'menu_type', '菜单功能类型', '30', '1', '2015-12-04 20:09:09', '1', '2015-12-04 20:13:33', null, '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) DEFAULT '1' COMMENT '日志类型',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(5) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `exception` text COMMENT '异常信息',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`),
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_date` (`create_date`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', '1', '1', '2015-12-15 17:03:24', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36', '/test/sys/menu/save', 'POST', 'id=&parent.id=1&parent.name=顶级菜单&name=设置&href=&type=0&isShow=1&sort=30&permission=', '');
INSERT INTO `sys_log` VALUES ('2', '1', '1', '2015-12-15 17:04:05', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36', '/test/sys/menu/save', 'POST', 'id=&parent.id=1&parent.name=顶级菜单&name=商品&href=&type=0&isShow=1&sort=40&permission=', '');
INSERT INTO `sys_log` VALUES ('3', '1', '1', '2015-12-15 17:19:26', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36', '/test/sys/menu/save', 'POST', 'id=&parent.id=1&parent.name=顶级菜单&name=痁铺&href=&type=0&isShow=1&sort=50&permission=', '');
INSERT INTO `sys_log` VALUES ('4', '1', '1', '2015-12-15 17:22:12', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36', '/test/sys/menu/save', 'POST', 'id=&parent.id=1&parent.name=顶级菜单&name=会员 &href=&type=0&isShow=1&sort=60&permission=', '');
INSERT INTO `sys_log` VALUES ('5', '1', '1', '2015-12-15 17:23:32', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36', '/test/sys/menu/save', 'POST', 'id=&parent.id=1&parent.name=顶级菜单&name=交易&href=&type=0&isShow=1&sort=70&permission=', '');
INSERT INTO `sys_log` VALUES ('6', '1', '1', '2015-12-15 17:23:53', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36', '/test/sys/menu/save', 'POST', 'id=&parent.id=1&parent.name=顶级菜单&name=网站&href=&type=0&isShow=1&sort=80&permission=', '');
INSERT INTO `sys_log` VALUES ('7', '1', '1', '2015-12-15 17:24:59', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36', '/test/sys/menu/save', 'POST', 'id=&parent.id=1&parent.name=顶级菜单&name=运营&href=&type=0&isShow=1&sort=90&permission=', '');
INSERT INTO `sys_log` VALUES ('8', '1', '1', '2015-12-15 17:25:10', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36', '/test/sys/menu/save', 'POST', 'id=&parent.id=1&parent.name=顶级菜单&name=工具&href=&type=0&isShow=1&sort=100&permission=', '');
INSERT INTO `sys_log` VALUES ('20', '2', '2', '2015-12-17 17:56:43', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36', '/test/sys/user/list', 'GET', '', 'org.apache.jasper.JasperException: /WEB-INF/templates/error/403.jsp (line: 2, column: 1) File \"/WEB-INF/views/include/taglib.jsp\" not found');
INSERT INTO `sys_log` VALUES ('21', '2', '2', '2015-12-17 17:57:37', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36', '/test/sys/user/list', 'GET', '', 'org.apache.jasper.JasperException: /WEB-INF/templates/error/403.jsp (line: 2, column: 1) File \"/WEB-INF/views/include/taglib.jsp\" not found');
INSERT INTO `sys_log` VALUES ('22', '2', '2', '2015-12-17 17:57:40', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36', '/test/sys/user/list', 'GET', '', 'org.apache.jasper.JasperException: java.lang.ClassNotFoundException: org.apache.jsp.WEB_002dINF.templates.error._403_jsp');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` bigint(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '菜单名称',
  `href` varchar(255) DEFAULT NULL COMMENT '链接',
  `target` varchar(20) DEFAULT NULL COMMENT '目标',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `sort` int(11) NOT NULL COMMENT '排序（升序）',
  `is_show` char(1) NOT NULL COMMENT '是否在菜单中显示',
  `type` tinyint(4) DEFAULT '0' COMMENT '功能类型：见字典menu_type',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_menu_parent_id` (`parent_id`),
  KEY `sys_menu_parent_ids` (`parent_ids`(255)),
  KEY `sys_menu_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=1015 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '0,', '顶级菜单', null, null, null, '0', '1', '0', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '0,1,', '系统设置', '', null, null, '900', '1', '0', '', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '0');
INSERT INTO `sys_menu` VALUES ('3', '2', '0,1,2,', '系统设置', null, null, null, '980', '1', '0', null, '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '0');
INSERT INTO `sys_menu` VALUES ('4', '3', '0,1,2,3,', '菜单管理', '/sys/menu/list.do', null, 'list-alt', '30', '1', '0', null, '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '0');
INSERT INTO `sys_menu` VALUES ('5', '4', '0,1,2,3,4,', '查看', '', null, null, '30', '0', '2', 'sys:menu:view', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '0');
INSERT INTO `sys_menu` VALUES ('6', '4', '0,1,2,3,4,', '修改', '', null, null, '30', '0', '2', 'sys:menu:edit', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '0');
INSERT INTO `sys_menu` VALUES ('7', '3', '0,1,2,3,', '角色管理', '/sys/role/list.do', null, 'lock', '50', '1', '0', null, '1', '2017-10-10 14:56:40', '1', '2017-10-10 14:56:40', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '7', '0,1,2,3,7,', '查看', '', null, null, '30', '0', '2', 'sys:role:view', '1', '2017-10-10 14:56:40', '1', '2017-10-10 14:56:40', null, '0');
INSERT INTO `sys_menu` VALUES ('9', '7', '0,1,2,3,7,', '修改', '', null, null, '30', '0', '2', 'sys:role:edit', '1', '2017-10-10 14:56:40', '1', '2017-10-10 14:56:40', null, '0');
INSERT INTO `sys_menu` VALUES ('10', '3', '0,1,2,3,', '字典管理', '/sys/dict/list.do', null, 'th-list', '60', '1', '0', null, '1', '2017-10-10 14:56:40', '1', '2017-10-10 14:56:40', null, '0');
INSERT INTO `sys_menu` VALUES ('11', '10', '0,1,2,3,10,', '查看', '', null, null, '30', '0', '2', 'sys:dict:view', '1', '2017-10-10 14:56:40', '1', '2017-10-10 14:56:40', null, '0');
INSERT INTO `sys_menu` VALUES ('12', '10', '0,1,2,3,10,', '修改', '', null, null, '30', '0', '2', 'sys:dict:edit', '1', '2017-10-10 14:56:40', '1', '2017-10-10 14:56:40', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '2', '0,1,2,', '机构用户', '', null, null, '970', '1', '0', '', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '13', '0,1,2,13,', '机构管理', '/sys/office/list.do', null, 'th-large', '40', '1', '0', null, '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '17', '0,1,2,13,17,', '查看', '', null, null, '30', '0', '2', 'sys:office:view', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '17', '0,1,2,13,17,', '修改', '', null, null, '30', '0', '2', 'sys:office:edit', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '13', '0,1,2,13,', '用户管理', '/sys/user/list.do', null, 'user', '30', '1', '0', '', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '20', '0,1,2,13,20,', '查看', '', null, null, '30', '0', '2', 'sys:user:view', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '20', '0,1,2,13,20,', '修改', '', null, null, '30', '0', '2', 'sys:user:edit', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '1', '0,1,', '设置', '', null, null, '30', '1', '0', '', '1', '2017-10-10 14:56:30', '1', '2017-10-10 14:56:30', null, '1');
INSERT INTO `sys_menu` VALUES ('24', '1', '0,1,', '商品', '', null, null, '40', '1', '0', '', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', null, '1');
INSERT INTO `sys_menu` VALUES ('25', '1', '0,1,', '店铺', '', null, null, '50', '1', '0', '', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', null, '1');
INSERT INTO `sys_menu` VALUES ('26', '1', '0,1,', '会员', '', null, null, '60', '1', '0', '', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', null, '1');
INSERT INTO `sys_menu` VALUES ('27', '1', '0,1,', '交易', '', null, null, '70', '1', '0', '', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('28', '1', '0,1,', '网站', '', null, null, '80', '1', '0', '', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('29', '1', '0,1,', '运营', '', null, null, '90', '1', '0', '', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('30', '1', '0,1,', '工具', '', null, null, '100', '1', '0', '', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '1');
INSERT INTO `sys_menu` VALUES ('508', '23', '0,1,23,', 'Email设置', '/admin/set_email.htm', null, null, '4', '1', '0', 'email_set', '1', '2017-10-10 14:56:30', '1', '2017-10-10 14:56:30', 'Email设置', '1');
INSERT INTO `sys_menu` VALUES ('509', '23', '0,1,23,', '短信设置', '/admin/set_sms.htm', null, null, '5', '1', '0', 'sms_set', '1', '2017-10-10 14:56:30', '1', '2017-10-10 14:56:30', '短信设置', '1');
INSERT INTO `sys_menu` VALUES ('513', '581', '0,1,23,581,', '保存配置', '/admin/sys_config_save.htm', null, null, '1', '0', '2', 'sys_config_save', '1', '2017-10-10 14:56:30', '1', '2017-10-10 14:56:30', '保存商城配置', '1');
INSERT INTO `sys_menu` VALUES ('514', '23', '0,1,23,', '二级域名', '/admin/set_second_domain.htm', null, null, '6', '1', '0', 'second_domain_set', '1', '2017-10-10 14:56:30', '1', '2017-10-10 14:56:30', '二级域名设置', '1');
INSERT INTO `sys_menu` VALUES ('515', '587', '0,1,23,913,587,', '保存分润设置', '/admin/set_fenrun_save.htm', null, null, '1', '0', '2', 'set_fenrun_save', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '分润管理', '1');
INSERT INTO `sys_menu` VALUES ('517', '514', '0,1,23,514,', '修改', '/admin/set_second_domain_save.htm', null, null, '1', '0', '2', 'second_domain_set', '1', '2017-10-10 14:56:30', '1', '2017-10-10 14:56:30', '二级域名设置', '1');
INSERT INTO `sys_menu` VALUES ('518', '520', '0,1,29,920,520,', '活动添加', '/admin/activity_add.htm', null, null, '1', '1', '2', 'activity_add', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '活动管理', '1');
INSERT INTO `sys_menu` VALUES ('519', '520', '0,1,29,920,520,', '活动删除', '/admin/activity_del.htm', null, null, '2', '1', '2', 'activity_del', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '活动管理', '1');
INSERT INTO `sys_menu` VALUES ('520', '920', '0,1,29,920,', '活动管理', '/admin/activity_list.htm', null, null, '1', '1', '0', 'activity_list', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '活动管理', '1');
INSERT INTO `sys_menu` VALUES ('521', '520', '0,1,29,920,520,', '活动编辑', '/admin/activity_edit.htm', null, null, '3', '1', '2', 'activity_edit', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '活动管理', '1');
INSERT INTO `sys_menu` VALUES ('522', '520', '0,1,29,920,520,', '活动保存', '/admin/activity_save.htm', null, null, '4', '1', '2', 'activity_save', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '活动管理', '1');
INSERT INTO `sys_menu` VALUES ('523', '520', '0,1,29,920,520,', '活动AJAX更新', '/admin/activity_ajax.htm', null, null, '5', '1', '2', 'activity_ajax', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '活动管理', '1');
INSERT INTO `sys_menu` VALUES ('524', '520', '0,1,29,920,520,', '活动商品列表', '/admin/activity_goods_list.htm', null, null, '6', '1', '2', 'activity_goods_list', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '活动管理', '1');
INSERT INTO `sys_menu` VALUES ('525', '520', '0,1,29,920,520,', '活动通过', '/admin/activity_goods_audit.htm', null, null, '8', '1', '2', 'activity_goods_audit', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '活动管理', '1');
INSERT INTO `sys_menu` VALUES ('526', '520', '0,1,29,920,520,', '活动拒绝', '/admin/activity_goods_refuse.htm', null, null, '10', '1', '2', 'activity_goods_refuse', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '活动管理', '1');
INSERT INTO `sys_menu` VALUES ('535', '29', '0,1,29,', '广告管理', '/admin/advert_list.htm', null, null, '5', '1', '0', 'advert_list', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '广告管理', '1');
INSERT INTO `sys_menu` VALUES ('536', '535', '0,1,29,535,', '广告增加', '/admin/advert_add.htm', null, null, '2', '1', '1', 'advert_add', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '广告管理', '1');
INSERT INTO `sys_menu` VALUES ('537', '535', '0,1,29,535,', '广告编辑', '/admin/advert_edit.htm', null, null, '3', '0', '2', 'advert_edit', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '广告管理', '1');
INSERT INTO `sys_menu` VALUES ('538', '535', '0,1,29,535,', '广告查看', '/admin/advert_view.htm', null, null, '4', '1', '2', 'advert_view', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '广告管理', '1');
INSERT INTO `sys_menu` VALUES ('539', '535', '0,1,29,535,', '广告审核', '/admin/advert_audit.htm', null, null, '5', '1', '2', 'advert_audit', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '广告管理', '1');
INSERT INTO `sys_menu` VALUES ('540', '535', '0,1,29,535,', '广告保存', '/admin/advert_save.htm', null, null, '6', '1', '0', 'advert_save', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '广告管理', '1');
INSERT INTO `sys_menu` VALUES ('541', '535', '0,1,29,535,', '广告删除', '/admin/advert_del.htm', null, null, '8', '1', '2', 'advert_del', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '广告管理', '1');
INSERT INTO `sys_menu` VALUES ('542', '544', '0,1,29,535,544,', '广告位添加', '/admin/adv_pos_add.htm', null, null, '1', '1', '2', 'adv_pos_add', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '广告管理', '1');
INSERT INTO `sys_menu` VALUES ('543', '544', '0,1,29,535,544,', '广告位保存', '/admin/adv_pos_save.htm', null, null, '2', '1', '2', 'adv_pos_save', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '广告管理', '1');
INSERT INTO `sys_menu` VALUES ('544', '535', '0,1,29,535,', '所有广告位', '/admin/adv_pos_list.htm', null, null, '9', '1', '1', 'adv_pos_list', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '广告管理', '1');
INSERT INTO `sys_menu` VALUES ('545', '544', '0,1,29,535,544,', '广告位编辑', '/admin/adv_pos_edit.htm', null, null, '3', '1', '2', 'adv_pos_edit', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '广告管理', '1');
INSERT INTO `sys_menu` VALUES ('546', '544', '0,1,29,535,544,', '广告位删除', '/admin/adv_pos_del.htm', null, null, '4', '1', '0', 'adv_pos_del', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '广告管理', '1');
INSERT INTO `sys_menu` VALUES ('547', '535', '0,1,29,535,', '未审批广告', '/admin/advert_list_audit.htm', null, null, '1', '1', '1', 'advert_list_audit', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '广告管理', '1');
INSERT INTO `sys_menu` VALUES ('548', '912', '0,1,23,912,', '常用地区', '/admin/area_list.htm', null, null, '1', '1', '0', 'area_list', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '常用地区', '1');
INSERT INTO `sys_menu` VALUES ('549', '548', '0,1,23,912,548,', '编辑', '/admin/area_save.htm', null, null, '1', '0', '2', 'area_edit', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '常用地区', '1');
INSERT INTO `sys_menu` VALUES ('550', '548', '0,1,23,912,548,', '删除', '/admin/area_del.htm', null, null, '3', '0', '2', 'area_delete', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '常用地区', '1');
INSERT INTO `sys_menu` VALUES ('551', '548', '0,1,23,912,548,', '地区导入', '/admin/area_import.htm', null, null, '4', '0', '2', 'area_import', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '常用地区', '1');
INSERT INTO `sys_menu` VALUES ('552', '548', '0,1,23,912,548,', '编辑(ajax)', '/admin/area_ajax.htm', null, null, '2', '0', '2', 'area_edit_ajax', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '常用地区', '1');
INSERT INTO `sys_menu` VALUES ('553', '554', '0,1,28,554,', '文章分类添加', '/admin/articleclass_add.htm', null, null, '1', '0', '2', 'articleclass_add', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '文章分类', '1');
INSERT INTO `sys_menu` VALUES ('554', '28', '0,1,28,', '文章分类', '/admin/articleclass_list.htm', null, null, '1', '1', '0', 'articleclass_list', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '文章分类', '1');
INSERT INTO `sys_menu` VALUES ('555', '554', '0,1,28,554,', '文章分类保存', '/admin/articleclass_save.htm', null, null, '2', '0', '0', 'articleclass_save', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '文章分类', '1');
INSERT INTO `sys_menu` VALUES ('556', '554', '0,1,28,554,', '文章分类删除', '/admin/articleclass_del.htm', null, null, '3', '1', '0', 'articleclass_del', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '文章分类', '1');
INSERT INTO `sys_menu` VALUES ('557', '554', '0,1,28,554,', '文章下级分类', '/admin/articleclass_data.htm', null, null, '4', '0', '2', 'articleclass_data', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '文章分类', '1');
INSERT INTO `sys_menu` VALUES ('558', '554', '0,1,28,554,', '文章分类编辑', '/admin/articleclass_edit.htm', null, null, '5', '0', '2', 'articleclass_edit', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '文章分类', '1');
INSERT INTO `sys_menu` VALUES ('559', '554', '0,1,28,554,', '文章分类AJAX更新', '/admin/articleclass_ajax.htm', null, null, '6', '0', '2', 'articleclass_ajax', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '文章分类', '1');
INSERT INTO `sys_menu` VALUES ('560', '561', '0,1,28,561,', '文章添加', '/admin/article_add.htm', null, null, '1', '0', '2', 'article_add', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '文章管理', '1');
INSERT INTO `sys_menu` VALUES ('561', '28', '0,1,28,', '文章管理', '/admin/article_list.htm', null, null, '2', '1', '0', 'article_list', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '文章管理', '1');
INSERT INTO `sys_menu` VALUES ('562', '561', '0,1,28,561,', '文章保存', '/admin/article_save.htm', null, null, '2', '0', '2', 'article_save', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '文章管理', '1');
INSERT INTO `sys_menu` VALUES ('563', '561', '0,1,28,561,', '文章删除', '/admin/article_del.htm', null, null, '3', '0', '2', 'article_del', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '文章管理', '1');
INSERT INTO `sys_menu` VALUES ('564', '561', '0,1,28,561,', '文章编辑', '/admin/article_edit.htm', null, null, '4', '0', '2', 'article_edit', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '文章管理', '1');
INSERT INTO `sys_menu` VALUES ('565', '561', '0,1,28,561,', '文章AJAX更新', '/admin/article_ajax.htm', null, null, '5', '0', '2', 'article_ajax', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '文章管理', '1');
INSERT INTO `sys_menu` VALUES ('566', '920', '0,1,29,920,', '天天特价', '/admin/bargain_list.htm', null, null, '2', '1', '0', 'bargain_list', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '天天特价', '1');
INSERT INTO `sys_menu` VALUES ('567', '566', '0,1,29,920,566,', '特价添加', '/admin/bargain_add.htm', null, null, '2', '1', '2', 'bargain_add', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '天天特价', '1');
INSERT INTO `sys_menu` VALUES ('568', '566', '0,1,29,920,566,', '特价保存', '/admin/bargain_save.htm', null, null, '2', '1', '0', 'bargain_save', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '天天特价', '1');
INSERT INTO `sys_menu` VALUES ('569', '566', '0,1,29,920,566,', '特价删除', '/admin/bargain_del.htm', null, null, '4', '1', '0', 'bargain_del', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '天天特价', '1');
INSERT INTO `sys_menu` VALUES ('570', '566', '0,1,29,920,566,', '今日特价ajax更新', '/admin/bargain_ajax.htm', null, null, '5', '1', '2', 'bargain_ajax', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '天天特价', '1');
INSERT INTO `sys_menu` VALUES ('571', '566', '0,1,29,920,566,', '特价设置', '/admin/set_bargain.htm', null, null, '1', '1', '1', 'set_bargain', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '天天特价', '1');
INSERT INTO `sys_menu` VALUES ('572', '566', '0,1,29,920,566,', '时间查询设置', '/admin/date_query_set.htm', null, null, '6', '1', '2', 'date_query_set', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '天天特价', '1');
INSERT INTO `sys_menu` VALUES ('573', '566', '0,1,29,920,566,', '通用设置保存', '/admin/set_bargain_save.htm', null, null, '7', '1', '2', 'set_bargain_save', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '天天特价', '1');
INSERT INTO `sys_menu` VALUES ('574', '566', '0,1,29,920,566,', '特价商品列表', '/admin/bargain_goods_list.htm', null, null, '8', '1', '2', 'bargain_goods_list', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '天天特价', '1');
INSERT INTO `sys_menu` VALUES ('575', '566', '0,1,29,920,566,', '特价商品通过', '/admin/bargain_goods_audit.htm', null, null, '30', '1', '2', 'bargain_goods_audit', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '天天特价', '1');
INSERT INTO `sys_menu` VALUES ('576', '566', '0,1,29,920,566,', '特价商品审核数超出', '/admin/bargain_audits_out.htm', null, null, '31', '1', '2', 'bargain_audits_out', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '天天特价', '1');
INSERT INTO `sys_menu` VALUES ('577', '566', '0,1,29,920,566,', '特价拒绝', '/admin/bargain_goods_refuse.htm', null, null, '32', '1', '2', 'bargain_goods_refuse', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '天天特价', '1');
INSERT INTO `sys_menu` VALUES ('581', '23', '0,1,23,', '站点设置', '/admin/set_site.htm', null, null, '1', '1', '0', 'site_set', '1', '2017-10-10 14:56:30', '1', '2017-10-10 14:56:30', '站点设置', '1');
INSERT INTO `sys_menu` VALUES ('582', '23', '0,1,23,', '上传设置', '/admin/set_image.htm', null, null, '2', '1', '0', 'upload_set', '1', '2017-10-10 14:56:30', '1', '2017-10-10 14:56:30', '上传设置', '1');
INSERT INTO `sys_menu` VALUES ('585', '23', '0,1,23,', 'SEO设置', '/admin/set_seo.htm', null, null, '3', '1', '0', 'seo_set', '1', '2017-10-10 14:56:30', '1', '2017-10-10 14:56:30', 'SEO设置', '1');
INSERT INTO `sys_menu` VALUES ('587', '913', '0,1,23,913,', '分润设置', '/admin/set_fenrun.htm', null, null, '2', '1', '0', 'set_fenrun', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '分润管理', '1');
INSERT INTO `sys_menu` VALUES ('593', '30', '0,1,30,', '缓存管理', '/admin/cache_list.htm', null, null, '1', '1', '0', 'cache_list', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '更新缓存', '1');
INSERT INTO `sys_menu` VALUES ('594', '593', '0,1,30,593,', '更新缓存', '/admin/update_cache.htm', null, null, '1', '1', '2', 'update_cache', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '更新缓存', '1');
INSERT INTO `sys_menu` VALUES ('595', '596', '0,1,29,920,596,', '组合销售商品列表', '/admin/combin_goods.htm', null, null, '4', '1', '2', 'combin_goods', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '组合销售', '1');
INSERT INTO `sys_menu` VALUES ('596', '920', '0,1,29,920,', '组合销售', '/admin/set_combin.htm', null, null, '4', '1', '0', 'set_combin', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '组合销售', '1');
INSERT INTO `sys_menu` VALUES ('597', '596', '0,1,29,920,596,', '组合销售设置保存', '/admin/set_combin_save.htm', null, null, '2', '1', '0', 'set_combin_save', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '组合销售', '1');
INSERT INTO `sys_menu` VALUES ('598', '596', '0,1,29,920,596,', '组合销售商品审核', '/admin/combin_goods_audit.htm', null, null, '3', '1', '0', 'combin_goods_audit', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '组合销售', '1');
INSERT INTO `sys_menu` VALUES ('599', '596', '0,1,29,920,596,', '组合销售商品拒绝', '/admin/combin_goods_refuse.htm', null, null, '4', '1', '2', 'combin_goods_refuse', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '组合销售', '1');
INSERT INTO `sys_menu` VALUES ('600', '27', '0,1,27,', '投诉管理', '/admin/complaint_list.htm', null, null, '6', '1', '0', 'complaint_list', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '投诉管理', '1');
INSERT INTO `sys_menu` VALUES ('601', '600', '0,1,27,600,', '投诉设置', '/admin/complaint_set.htm', null, null, '3', '0', '1', 'complaint_set', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '投诉管理', '1');
INSERT INTO `sys_menu` VALUES ('602', '601', '0,1,27,600,601,', '投诉设置保存', '/admin/complaint_set_save.htm', null, null, '30', '1', '0', 'complaint_set_save', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '投诉管理', '1');
INSERT INTO `sys_menu` VALUES ('603', '600', '0,1,27,600,', '投诉详情', '/admin/complaint_view.htm', null, null, '4', '0', '0', 'complaint_view', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '投诉管理', '1');
INSERT INTO `sys_menu` VALUES ('604', '600', '0,1,27,600,', '投诉图片', '/admin/complaint_img.htm', null, null, '5', '0', '2', 'complaint_img', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '投诉管理', '1');
INSERT INTO `sys_menu` VALUES ('605', '600', '0,1,27,600,', '投诉审核', '/admin/complaint_audit.htm', null, null, '6', '0', '2', 'complaint_audit', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '投诉管理', '1');
INSERT INTO `sys_menu` VALUES ('606', '600', '0,1,27,600,', '投诉关闭', '/admin/complaint_close.htm', null, null, '7', '0', '2', 'complaint_close', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '投诉管理', '1');
INSERT INTO `sys_menu` VALUES ('607', '600', '0,1,27,600,', '发布投诉对话', '/admin/complaint_talk.htm', null, null, '8', '0', '2', 'complaint_talk', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '投诉管理', '1');
INSERT INTO `sys_menu` VALUES ('608', '600', '0,1,27,600,', '投诉仲裁', '/admin/complaint_handle_close.htm', null, null, '9', '0', '2', 'complaint_handle_close', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '投诉管理', '1');
INSERT INTO `sys_menu` VALUES ('609', '600', '0,1,27,600,', '投诉主题添加', '/admin/complaintsubject_add.htm', null, null, '2', '0', '1', 'complaintsubject_add', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '投诉管理', '1');
INSERT INTO `sys_menu` VALUES ('610', '600', '0,1,27,600,', '投诉主题', '/admin/complaintsubject_list.htm', null, null, '1', '0', '1', 'complaintsubject_list', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '投诉管理', '1');
INSERT INTO `sys_menu` VALUES ('611', '610', '0,1,27,600,610,', '投诉主题保存', '/admin/complaintsubject_save.htm', null, null, '1', '0', '2', 'complaintsubject_save', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '投诉管理', '1');
INSERT INTO `sys_menu` VALUES ('612', '610', '0,1,27,600,610,', '投诉主题删除', '/admin/complaintsubject_del.htm', null, null, '2', '0', '2', 'complaintsubject_del', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '投诉管理', '1');
INSERT INTO `sys_menu` VALUES ('613', '610', '0,1,27,600,610,', '投诉主题编辑', '/admin/complaintsubject_edit.htm', null, null, '3', '1', '2', 'complaintsubject_edit', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '投诉管理', '1');
INSERT INTO `sys_menu` VALUES ('614', '27', '0,1,27,', '咨询管理', '/admin/consult_list.htm', null, null, '3', '1', '0', 'consult_list', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '咨询管理', '1');
INSERT INTO `sys_menu` VALUES ('615', '614', '0,1,27,614,', '咨询删除', '/admin/consult_del.htm', null, null, '1', '0', '2', 'consult_del', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '咨询管理', '1');
INSERT INTO `sys_menu` VALUES ('616', '29', '0,1,29,', '优惠券管理', '/admin/coupon_list.htm', null, null, '4', '1', '0', 'coupon_list', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '优惠券管理', '1');
INSERT INTO `sys_menu` VALUES ('617', '616', '0,1,29,616,', '新增优惠券', '/admin/coupon_add.htm', null, null, '1', '0', '1', 'coupon_add', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '优惠券管理', '1');
INSERT INTO `sys_menu` VALUES ('618', '616', '0,1,29,616,', '优惠券保存', '/admin/coupon_save.htm', null, null, '2', '0', '2', 'coupon_save', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '优惠券管理', '1');
INSERT INTO `sys_menu` VALUES ('619', '616', '0,1,29,616,', '优惠券发放', '/admin/coupon_send.htm', null, null, '3', '0', '2', 'coupon_send', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '优惠券管理', '1');
INSERT INTO `sys_menu` VALUES ('620', '616', '0,1,29,616,', '优惠券AJAX更新', '/admin/coupon_ajax.htm', null, null, '4', '0', '2', 'coupon_ajax', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '优惠券管理', '1');
INSERT INTO `sys_menu` VALUES ('621', '616', '0,1,29,616,', '优惠券保存成功', '/admin/coupon_success.htm', null, null, '5', '0', '2', 'coupon_success', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '优惠券管理', '1');
INSERT INTO `sys_menu` VALUES ('622', '616', '0,1,29,616,', '优惠券发放保存', '/admin/coupon_send_save.htm', null, null, '6', '0', '2', 'coupon_send_save', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '优惠券管理', '1');
INSERT INTO `sys_menu` VALUES ('623', '616', '0,1,29,616,', '发放列表', '/admin/coupon_info_list.htm', null, null, '7', '0', '2', 'coupon_info_list', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '优惠券管理', '1');
INSERT INTO `sys_menu` VALUES ('624', '626', '0,1,30,626,', '数据库备份保存', '/admin/database_backup.htm', null, null, '1', '1', '2', 'database_backup', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '数据库管理', '1');
INSERT INTO `sys_menu` VALUES ('625', '626', '0,1,30,626,', '数据库分卷备份', '/admin/database_bund_backup.htm', null, null, '2', '1', '0', 'database_bund_backup', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '数据库管理', '1');
INSERT INTO `sys_menu` VALUES ('626', '30', '0,1,30,', '数据库管理', '/admin/database_list.htm', null, null, '2', '1', '0', 'database_list', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '数据库管理', '1');
INSERT INTO `sys_menu` VALUES ('627', '626', '0,1,30,626,', '数据库导入引导', '/admin/database_store.htm', null, null, '3', '1', '0', 'database_store', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '数据库管理', '1');
INSERT INTO `sys_menu` VALUES ('628', '626', '0,1,30,626,', '数据库分卷导入', '/admin/database_bund_store.htm', null, null, '30', '1', '2', 'database_bund_store', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '数据库管理', '1');
INSERT INTO `sys_menu` VALUES ('629', '626', '0,1,30,626,', '数据库备份', '/admin/database_add.htm', null, null, '5', '1', '2', 'database_add', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '数据库管理', '1');
INSERT INTO `sys_menu` VALUES ('630', '626', '0,1,30,626,', '数据库备份删除', '/admin/database_del.htm', null, null, '6', '1', '2', 'database_del', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '数据库管理', '1');
INSERT INTO `sys_menu` VALUES ('631', '920', '0,1,29,920,', '买就送', '/admin/set_delivery.htm', null, null, '3', '1', '0', 'set_delivery', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '买就送', '1');
INSERT INTO `sys_menu` VALUES ('632', '631', '0,1,29,920,631,', '买就送设置保存', '/admin/set_delivery_save.htm', null, null, '1', '1', '2', 'set_delivery_save', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '买就送', '1');
INSERT INTO `sys_menu` VALUES ('633', '631', '0,1,29,920,631,', '买就送商品列表', '/admin/delivery_goods_list.htm', null, null, '2', '1', '2', 'delivery_goods_list', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '买就送', '1');
INSERT INTO `sys_menu` VALUES ('634', '631', '0,1,29,920,631,', '买就送商品审核', '/admin/delivery_goods_audit.htm', null, null, '3', '1', '2', 'delivery_goods_audit', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '买就送', '1');
INSERT INTO `sys_menu` VALUES ('635', '631', '0,1,29,920,631,', '买就送拒绝', '/admin/delivery_goods_refuse.htm', null, null, '4', '1', '0', 'delivery_goods_refuse', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '买就送', '1');
INSERT INTO `sys_menu` VALUES ('636', '637', '0,1,28,637,', '系统文章新增', '/admin/document_add.htm', null, null, '1', '0', '0', 'document_add', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '系统文章', '1');
INSERT INTO `sys_menu` VALUES ('637', '28', '0,1,28,', '系统文章', '/admin/document_list.htm', null, null, '3', '1', '0', 'document_list', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '系统文章', '1');
INSERT INTO `sys_menu` VALUES ('638', '637', '0,1,28,637,', '系统文章保存', '/admin/document_save.htm', null, null, '2', '0', '2', 'document_save', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '系统文章', '1');
INSERT INTO `sys_menu` VALUES ('639', '637', '0,1,28,637,', '系统文章删除', '/admin/document_del.htm', null, null, '3', '0', '2', 'document_del', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '系统文章', '1');
INSERT INTO `sys_menu` VALUES ('640', '637', '0,1,28,637,', '系统文章编辑', '/admin/document_edit.htm', null, null, '4', '0', '0', 'document_edit', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '系统文章', '1');
INSERT INTO `sys_menu` VALUES ('641', '637', '0,1,28,637,', '系统文章AJAX更新', '/admin/document_ajax.htm', null, null, '5', '0', '2', 'document_ajax', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '系统文章', '1');
INSERT INTO `sys_menu` VALUES ('642', '27', '0,1,27,', '商品评价', '/admin/evaluate_list.htm', null, null, '5', '1', '0', 'evaluate_list', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '商品评价', '1');
INSERT INTO `sys_menu` VALUES ('643', '642', '0,1,27,642,', '商品评价编辑', '/admin/evaluate_edit.htm', null, null, '1', '0', '2', 'evaluate_edit', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '商品评价', '1');
INSERT INTO `sys_menu` VALUES ('644', '642', '0,1,27,642,', '商品评价编辑保存', '/admin/evaluate_save.htm', null, null, '2', '0', '2', 'evaluate_save', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '商品评价', '1');
INSERT INTO `sys_menu` VALUES ('645', '914', '0,1,23,914,', '快递设置', '/admin/set_kuaidi.htm', null, null, '1', '1', '0', 'set_kuaidi', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '快递设置', '1');
INSERT INTO `sys_menu` VALUES ('646', '645', '0,1,23,914,645,', '保存快递设置', '/admin/set_kuaidi_save.htm', null, null, '1', '0', '2', 'set_kuaidi_save', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '快递设置', '1');
INSERT INTO `sys_menu` VALUES ('647', '914', '0,1,23,914,', '快递公司', '/admin/express_company_list.htm', null, null, '2', '1', '0', 'express_company_list', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '快递公司', '1');
INSERT INTO `sys_menu` VALUES ('648', '647', '0,1,23,914,647,', '添加', '/admin/express_company_add.htm', null, null, '1', '0', '2', 'express_company_add', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '快递公司', '1');
INSERT INTO `sys_menu` VALUES ('649', '647', '0,1,23,914,647,', '编辑', '/admin/express_company_edit.htm', null, null, '2', '0', '2', 'express_company_edit', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '快递公司', '1');
INSERT INTO `sys_menu` VALUES ('650', '647', '0,1,23,914,647,', '保存', '/admin/express_company_save.htm', null, null, '3', '0', '2', 'express_company_save', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '快递公司', '1');
INSERT INTO `sys_menu` VALUES ('651', '647', '0,1,23,914,647,', '删除', '/admin/express_company_del.htm', null, null, '4', '0', '2', 'express_company_del', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '快递公司', '1');
INSERT INTO `sys_menu` VALUES ('652', '647', '0,1,23,914,647,', 'Ajax更新数据', '/admin/express_company_ajax.htm', null, null, '5', '0', '2', 'express_company_ajax', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '快递公司', '1');
INSERT INTO `sys_menu` VALUES ('653', '917', '0,1,29,917,', '购买记录', '/admin/gold_record.htm', null, null, '1', '1', '0', 'gold_record', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '金币管理', '1');
INSERT INTO `sys_menu` VALUES ('654', '917', '0,1,29,917,', '购买日志', '/admin/gold_log.htm', null, null, '2', '1', '1', 'gold_log', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '金币管理', '1');
INSERT INTO `sys_menu` VALUES ('655', '653', '0,1,29,917,653,', '编辑', '/admin/gold_record_edit.htm', null, null, '3', '1', '2', 'gold_record_edit', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '金币管理', '1');
INSERT INTO `sys_menu` VALUES ('656', '653', '0,1,29,917,653,', '保存', '/admin/gold_record_save.htm', null, null, '2', '0', '2', 'gold_record_save', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '金币管理', '1');
INSERT INTO `sys_menu` VALUES ('657', '654', '0,1,29,917,654,', '删除', '/admin/gold_record_del.htm', null, null, '1', '0', '2', 'gold_record_del', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '金币管理', '1');
INSERT INTO `sys_menu` VALUES ('658', '653', '0,1,29,917,653,', '查看', '/admin/gold_record_view.htm', null, null, '1', '0', '2', 'gold_record_view', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '金币管理', '1');
INSERT INTO `sys_menu` VALUES ('659', '660', '0,1,24,660,', '品牌添加', '/admin/goods_brand_add.htm', null, null, '1', '0', '2', 'goods_brand_add', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '品牌管理', '1');
INSERT INTO `sys_menu` VALUES ('660', '24', '0,1,24,', '品牌管理', '/admin/goods_brand_list.htm', null, null, '2', '1', '0', 'goods_brand_list', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '品牌管理', '1');
INSERT INTO `sys_menu` VALUES ('661', '660', '0,1,24,660,', '品牌保存', '/admin/goods_band_save.htm', null, null, '2', '0', '2', 'goods_band_save', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '品牌管理', '1');
INSERT INTO `sys_menu` VALUES ('662', '660', '0,1,24,660,', '品牌删除', '/admin/goods_brand_del.htm', null, null, '3', '0', '2', 'goods_brand_del', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '品牌管理', '1');
INSERT INTO `sys_menu` VALUES ('663', '660', '0,1,24,660,', '品牌申请列表', '/admin/goods_brand_audit.htm', null, null, '4', '0', '0', 'goods_brand_audit', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '品牌管理', '1');
INSERT INTO `sys_menu` VALUES ('664', '663', '0,1,24,660,663,', '审核通过', '/admin/goods_brands_pass.htm', null, null, '1', '0', '2', 'goods_brands_pass', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '品牌管理', '1');
INSERT INTO `sys_menu` VALUES ('665', '663', '0,1,24,660,663,', '审核拒绝', '/admin/goods_brands_refuse.htm', null, null, '2', '0', '2', 'goods_brands_refuse', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '品牌管理', '1');
INSERT INTO `sys_menu` VALUES ('666', '660', '0,1,24,660,', '商品品牌编辑', '/admin/goods_brand_edit.htm', null, null, '5', '0', '2', 'goods_brand_edit', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '品牌管理', '1');
INSERT INTO `sys_menu` VALUES ('667', '660', '0,1,24,660,', '品牌AJAX更新', '/admin/goods_brand_ajax.htm', null, null, '6', '0', '2', 'goods_brand_ajax', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '品牌管理', '1');
INSERT INTO `sys_menu` VALUES ('668', '24', '0,1,24,', '分类管理', '/admin/goods_class_list.htm', null, null, '1', '1', '0', 'goods_class_list', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '分类管理', '1');
INSERT INTO `sys_menu` VALUES ('669', '668', '0,1,24,668,', '商品分类添加', '/admin/goods_class_add.htm', null, null, '1', '0', '2', 'goods_class_add', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '分类管理', '1');
INSERT INTO `sys_menu` VALUES ('670', '668', '0,1,24,668,', '商品分类编辑', '/admin/goods_class_edit.htm', null, null, '2', '0', '0', 'goods_class_edit', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '分类管理', '1');
INSERT INTO `sys_menu` VALUES ('671', '668', '0,1,24,668,', '商品分类保存', '/admin/goods_class_save.htm', null, null, '3', '0', '2', 'goods_class_save', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '分类管理', '1');
INSERT INTO `sys_menu` VALUES ('672', '668', '0,1,24,668,', '商品分类下级加载', '/admin/goods_class_data.htm', null, null, '4', '0', '2', 'goods_class_data', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '分类管理', '1');
INSERT INTO `sys_menu` VALUES ('673', '668', '0,1,24,668,', '商品分类Ajax更新', '/admin/goods_class_ajax.htm', null, null, '5', '0', '2', 'goods_class_ajax', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '分类管理', '1');
INSERT INTO `sys_menu` VALUES ('674', '668', '0,1,24,668,', '商品分类批量删除', '/admin/goods_class_del.htm', null, null, '6', '0', '2', 'goods_class_del', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '分类管理', '1');
INSERT INTO `sys_menu` VALUES ('676', '24', '0,1,24,', '首页楼层', '/admin/goods_floor_list.htm', null, null, '6', '1', '0', 'goods_floor_list', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('677', '676', '0,1,24,676,', '楼层添加', '/admin/goods_floor_add.htm', null, null, '1', '0', '2', 'goods_floor_add', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('678', '676', '0,1,24,676,', '楼层编辑', '/admin/goods_floor_edit.htm', null, null, '2', '0', '2', 'goods_floor_edit', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('679', '676', '0,1,24,676,', '楼层保存', '/admin/goods_floor_save.htm', null, null, '3', '0', '2', 'goods_floor_save', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('680', '676', '0,1,24,676,', '楼层删除', '/admin/goods_floor_del.htm', null, null, '4', '0', '2', 'goods_floor_del', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('681', '676', '0,1,24,676,', '楼层Ajax更新', '/admin/goods_floor_ajax.htm', null, null, '5', '0', '2', 'goods_floor_ajax', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('682', '676', '0,1,24,676,', '楼层下级加载', '/admin/goods_floor_data.htm', null, null, '6', '0', '2', 'goods_floor_data', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '分类管理', '1');
INSERT INTO `sys_menu` VALUES ('683', '676', '0,1,24,676,', '楼层模板编辑', '/admin/goods_floor_template.htm', null, null, '7', '0', '1', 'goods_floor_template', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('684', '683', '0,1,24,676,683,', '模板商品分类编辑', '/admin/goods_floor_class.htm', null, null, '1', '0', '2', 'goods_floor_class', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('685', '683', '0,1,24,676,683,', '模板分类商品编辑', '/admin/goods_floor_gc_goods.htm', null, null, '3', '0', '2', 'goods_floor_gc_goods', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('686', '683', '0,1,24,676,683,', '模板左下方广告编辑', '/admin/goods_floor_left_adv.htm', null, null, '10', '0', '2', 'goods_floor_left_adv', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('687', '683', '0,1,24,676,683,', '模板品牌编辑', '/admin/goods_floor_brand.htm', null, null, '7', '0', '2', 'goods_floor_brand', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('688', '683', '0,1,24,676,683,', '模板商品分类加载', '/admin/goods_floor_class_load.htm', null, null, '5', '0', '2', 'goods_floor_class_load', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('689', '683', '0,1,24,676,683,', '模板商品分类保存', '/admin/goods_floor_class_save.htm', null, null, '2', '0', '2', 'goods_floor_class_save', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('690', '683', '0,1,24,676,683,', '模板分类商品保存', '/admin/goods_floor_gc_goods_save.htm', null, null, '4', '0', '2', 'goods_floor_gc_goods_save', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('691', '683', '0,1,24,676,683,', '模板右侧商品列表编辑', '/admin/goods_floor_list_goods.htm', null, null, '12', '0', '2', 'goods_floor_list_goods', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('692', '683', '0,1,24,676,683,', '模板右侧商品列表保存', '/admin/goods_floor_list_goods_save.htm', null, null, '13', '0', '2', 'goods_floor_list_goods_save', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('693', '683', '0,1,24,676,683,', '楼层模板左下方广告保存', '/admin/goods_floor_left_adv_save.htm', null, null, '11', '0', '2', 'goods_floor_left_adv_save', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('694', '683', '0,1,24,676,683,', '模板右下方广告编辑', '/admin/goods_floor_right_adv.htm', null, null, '14', '0', '2', 'goods_floor_right_adv', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('695', '683', '0,1,24,676,683,', '模板右下方广告保存', '/admin/goods_floor_right_adv_save.htm', null, null, '15', '0', '2', 'goods_floor_right_adv_save', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('696', '683', '0,1,24,676,683,', '模板品牌保存', '/admin/goods_floor_brand_save.htm', null, null, '8', '1', '0', 'goods_floor_brand_save', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('697', '683', '0,1,24,676,683,', '模板品牌加载', '/admin/goods_floor_brand_load.htm', null, null, '9', '0', '2', 'goods_floor_brand_load', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('698', '683', '0,1,24,676,683,', '楼层模板分类商品加载', '/admin/goods_floor_list_goods_load.htm', null, null, '6', '0', '2', 'goods_floor_list_goods_load', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '首页楼层', '1');
INSERT INTO `sys_menu` VALUES ('699', '24', '0,1,24,', '商品管理', '/admin/goods_list.htm', null, null, '3', '1', '0', 'goods_list', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '商品管理', '1');
INSERT INTO `sys_menu` VALUES ('700', '699', '0,1,24,699,', '违规下架', '/admin/goods_sale.htm', null, null, '1', '0', '2', 'goods_sale', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '商品管理', '1');
INSERT INTO `sys_menu` VALUES ('701', '699', '0,1,24,699,', '编辑', '/admin/goods_edit.htm', null, null, '2', '0', '2', 'goods_edit', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '商品管理', '1');
INSERT INTO `sys_menu` VALUES ('702', '699', '0,1,24,699,', '保存', '/admin/goods_save.htm', null, null, '3', '0', '2', 'goods_save', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '商品管理', '1');
INSERT INTO `sys_menu` VALUES ('703', '699', '0,1,24,699,', '删除', '/admin/goods_del.htm', null, null, '4', '1', '2', 'goods_del', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '商品管理', '1');
INSERT INTO `sys_menu` VALUES ('704', '699', '0,1,24,699,', '违规商品列表', '/admin/goods_outline.htm', null, null, '5', '0', '0', 'goods_outline', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '商品管理', '1');
INSERT INTO `sys_menu` VALUES ('705', '699', '0,1,24,699,', 'AJAX更新', '/admin/goods_ajax.htm', null, null, '6', '0', '2', 'goods_ajax', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '商品管理', '1');
INSERT INTO `sys_menu` VALUES ('706', '707', '0,1,24,707,', '添加', '/admin/goods_spec_add.htm', null, null, '1', '0', '2', 'goods_spec_add', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '规格管理', '1');
INSERT INTO `sys_menu` VALUES ('707', '24', '0,1,24,', '规格管理', '/admin/goods_spec_list.htm', null, null, '5', '1', '0', 'goods_spec_list', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '规格管理', '1');
INSERT INTO `sys_menu` VALUES ('708', '707', '0,1,24,707,', '编辑', '/admin/goods_spec_save.htm', null, null, '3', '0', '2', 'goods_spec_save', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '规格管理', '1');
INSERT INTO `sys_menu` VALUES ('709', '707', '0,1,24,707,', '删除', '/admin/goods_spec_del.htm', null, null, '4', '0', '2', 'goods_spec_del', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '规格管理', '1');
INSERT INTO `sys_menu` VALUES ('710', '707', '0,1,24,707,', '规格属性AJAX删除', '/admin/goods_property_delete.htm', null, null, '5', '0', '2', 'goods_property_delete', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '规格管理', '1');
INSERT INTO `sys_menu` VALUES ('711', '707', '0,1,24,707,', '查看', '/admin/goods_spec_edit.htm', null, null, '2', '0', '1', 'goods_spec_edit', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '规格管理', '1');
INSERT INTO `sys_menu` VALUES ('712', '707', '0,1,24,707,', '规格AJAX更新', '/admin/goods_spec_ajax.htm', null, null, '6', '0', '2', 'goods_spec_ajax', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '规格管理', '1');
INSERT INTO `sys_menu` VALUES ('713', '714', '0,1,24,714,', '添加', '/admin/goods_type_add.htm', null, null, '1', '0', '2', 'goods_type_add', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '类型管理', '1');
INSERT INTO `sys_menu` VALUES ('714', '24', '0,1,24,', '类型管理', '/admin/goods_type_list.htm', null, null, '4', '1', '0', 'goods_type_list', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '类型管理', '1');
INSERT INTO `sys_menu` VALUES ('715', '714', '0,1,24,714,', '保存', '/admin/goods_type_save.htm', null, null, '3', '0', '2', 'goods_type_save', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '类型管理', '1');
INSERT INTO `sys_menu` VALUES ('716', '714', '0,1,24,714,', '删除', '/admin/goods_type_del.htm', null, null, '4', '0', '2', 'goods_type_del', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '类型管理', '1');
INSERT INTO `sys_menu` VALUES ('717', '714', '0,1,24,714,', '商品类型属性AJAX删除', '/admin/goods_type_property_delete.htm', null, null, '5', '0', '2', 'goods_type_property_delete', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '类型管理', '1');
INSERT INTO `sys_menu` VALUES ('718', '714', '0,1,24,714,', '查看', '/admin/goods_type_edit.htm', null, null, '2', '0', '2', 'goods_type_edit', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '类型管理', '1');
INSERT INTO `sys_menu` VALUES ('719', '714', '0,1,24,714,', '类型AJAX更新', '/admin/goods_type_ajax.htm', null, null, '6', '0', '2', 'goods_type_ajax', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', '类型管理', '1');
INSERT INTO `sys_menu` VALUES ('720', '721', '0,1,29,735,721,', '团购区域增加', '/admin/group_area_add.htm', null, null, '1', '1', '2', 'group_area_add', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('721', '735', '0,1,29,735,', '团购区域', '/admin/group_area_list.htm', null, null, '2', '1', '0', 'group_area_list', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('722', '721', '0,1,29,735,721,', '团购区域保存', '/admin/group_area_save.htm', null, null, '2', '1', '2', 'group_area_save', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('723', '721', '0,1,29,735,721,', '团购区域删除', '/admin/group_area_del.htm', null, null, '3', '1', '2', 'group_area_del', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('725', '721', '0,1,29,735,721,', '团购区域编辑', '/admin/group_area_edit.htm', null, null, '4', '1', '2', 'group_area_edit', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('726', '721', '0,1,29,735,721,', '团购区域Ajax更新', '/admin/group_area_ajax.htm', null, null, '5', '1', '2', 'group_area_ajax', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('727', '728', '0,1,29,735,728,', '团购分类增加', '/admin/group_class_add.htm', null, null, '1', '1', '2', 'group_class_add', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('728', '735', '0,1,29,735,', '团购分类', '/admin/group_class_list.htm', null, null, '1', '1', '0', 'group_class_list', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('729', '728', '0,1,29,735,728,', '团购分类保存', '/admin/group_class_save.htm', null, null, '2', '1', '0', 'group_class_save', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('730', '728', '0,1,29,735,728,', '团购分类删除', '/admin/group_class_del.htm', null, null, '3', '1', '2', 'group_class_del', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('732', '728', '0,1,29,735,728,', '团购分类编辑', '/admin/group_class_edit.htm', null, null, '4', '1', '2', 'group_class_edit', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('733', '728', '0,1,29,735,728,', '团购分类Ajax更新', '/admin/group_class_ajax.htm', null, null, '30', '1', '2', 'group_class_ajax', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('734', '735', '0,1,29,735,', '团购申请列表', '/admin/group_goods_list.htm', null, null, '4', '1', '2', 'group_goods_list', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('735', '29', '0,1,29,', '团购管理', '/admin/group_list.htm', null, null, '6', '1', '0', 'group_list', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('736', '735', '0,1,29,735,', '团购增加', '/admin/group_add.htm', null, null, '5', '1', '2', 'group_add', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('737', '735', '0,1,29,735,', '团购编辑', '/admin/group_edit.htm', null, null, '6', '1', '2', 'group_edit', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('738', '735', '0,1,29,735,', '团购保存', '/admin/group_save.htm', null, null, '7', '1', '2', 'group_save', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('739', '735', '0,1,29,735,', '团购删除', '/admin/group_del.htm', null, null, '8', '1', '2', 'group_del', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('740', '735', '0,1,29,735,', '团购关闭', '/admin/group_close.htm', null, null, '9', '1', '0', 'group_close', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('741', '735', '0,1,29,735,', '团购商品审核推荐', '/admin/group_goods_recommend.htm', null, null, '10', '1', '2', 'group_goods_recommend', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('742', '735', '0,1,29,735,', '团购商品审核通过', '/admin/group_goods_audit.htm', null, null, '11', '1', '2', 'group_goods_audit', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('743', '735', '0,1,29,735,', '团购商品审核拒绝', '/admin/group_goods_refuse.htm', null, null, '12', '1', '2', 'group_goods_refuse', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('744', '745', '0,1,29,735,745,', '团购价格区间添加', '/admin/group_price_add.htm', null, null, '1', '1', '2', 'group_price_add', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('745', '735', '0,1,29,735,', '价格区间', '/admin/group_price_list.htm', null, null, '3', '1', '1', 'group_price_list', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('746', '745', '0,1,29,735,745,', '团购价格区间保存', '/admin/group_price_save.htm', null, null, '2', '1', '2', 'group_price_save', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('747', '745', '0,1,29,735,745,', '团购价格区间删除', '/admin/group_price_del.htm', null, null, '3', '1', '0', 'group_price_del', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('748', '745', '0,1,29,735,745,', '团购价格区间编辑', '/admin/group_price_edit.htm', null, null, '4', '1', '2', 'group_price_edit', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '团购管理', '1');
INSERT INTO `sys_menu` VALUES ('749', '750', '0,1,26,750,', '会员相册图片删除', '/admin/user_pic_del.htm', null, null, '1', '0', '2', 'user_pic_del', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '会员管理', '1');
INSERT INTO `sys_menu` VALUES ('750', '26', '0,1,26,', '图片管理', '/admin/user_photo_list.htm', null, null, '8', '1', '0', 'user_photo_list', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '会员管理', '1');
INSERT INTO `sys_menu` VALUES ('751', '750', '0,1,26,750,', '会员相册删除', '/admin/user_photo_del.htm', null, null, '2', '1', '0', 'user_photo_del', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '会员管理', '1');
INSERT INTO `sys_menu` VALUES ('752', '750', '0,1,26,750,', '会员相册图片列表', '/admin/user_pic_list.htm', null, null, '3', '0', '1', 'user_pic_list', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '会员管理', '1');
INSERT INTO `sys_menu` VALUES ('753', '756', '0,1,29,919,756,', '取消积分订单', '/admin/integral_order_cancel.htm', null, null, '1', '1', '2', 'integral_order_cancel', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分商城', '1');
INSERT INTO `sys_menu` VALUES ('754', '756', '0,1,29,919,756,', '订单费用调整保存', '/admin/integral_order_fee_save.htm', null, null, '3', '1', '2', 'integral_order_fee_save', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分商城', '1');
INSERT INTO `sys_menu` VALUES ('755', '756', '0,1,29,919,756,', '发货设置保存', '/admin/integral_order_ship_save.htm', null, null, '2', '1', '2', 'integral_order_ship_save', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分商城', '1');
INSERT INTO `sys_menu` VALUES ('756', '919', '0,1,29,919,', '积分商城', '/admin/integral_goods.htm', null, null, '2', '1', '0', 'integral_goods', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分商城', '1');
INSERT INTO `sys_menu` VALUES ('757', '756', '0,1,29,919,756,', '积分礼品添加', '/admin/integral_goods_add.htm', null, null, '4', '1', '0', 'integral_goods_add', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分商城', '1');
INSERT INTO `sys_menu` VALUES ('758', '756', '0,1,29,919,756,', '积分礼品编辑', '/admin/integral_goods_edit.htm', null, null, '5', '1', '0', 'integral_goods_edit', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分商城', '1');
INSERT INTO `sys_menu` VALUES ('759', '756', '0,1,29,919,756,', '积分礼品保存', '/admin/integral_goods_save.htm', null, null, '6', '1', '2', 'integral_goods_save', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分商城', '1');
INSERT INTO `sys_menu` VALUES ('760', '756', '0,1,29,919,756,', '积分礼品删除', '/admin/integral_goods_del.htm', null, null, '7', '1', '1', 'integral_goods_del', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分商城', '1');
INSERT INTO `sys_menu` VALUES ('761', '756', '0,1,29,919,756,', '积分礼品兑换列表', '/admin/integral_order.htm', null, null, '8', '1', '2', 'integral_order', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分商城', '1');
INSERT INTO `sys_menu` VALUES ('762', '756', '0,1,29,919,756,', '积分礼品兑换详情', '/admin/integral_order_view.htm', null, null, '9', '1', '2', 'integral_order_view', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分商城', '1');
INSERT INTO `sys_menu` VALUES ('763', '756', '0,1,29,919,756,', '订单确认付款', '/admin/integral_order_payok.htm', null, null, '10', '1', '0', 'integral_order_payok', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分商城', '1');
INSERT INTO `sys_menu` VALUES ('764', '756', '0,1,29,919,756,', '订单删除', '/admin/integral_order_del.htm', null, null, '11', '1', '2', 'integral_order_del', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分商城', '1');
INSERT INTO `sys_menu` VALUES ('765', '756', '0,1,29,919,756,', '订单费用调整', '/admin/integral_order_fee.htm', null, null, '12', '1', '0', 'integral_order_fee', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分商城', '1');
INSERT INTO `sys_menu` VALUES ('766', '756', '0,1,29,919,756,', '发货设置', '/admin/integral_order_ship.htm', null, null, '13', '1', '0', 'integral_order_ship', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分商城', '1');
INSERT INTO `sys_menu` VALUES ('767', '26', '0,1,26,', '积分明细', '/admin/integrallog_list.htm', null, null, '5', '1', '0', 'integrallog_list', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '积分明细', '1');
INSERT INTO `sys_menu` VALUES ('768', '26', '0,1,26,', '积分管理', '/admin/user_integral.htm', null, null, '4', '1', '0', 'user_integral', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '积分管理', '1');
INSERT INTO `sys_menu` VALUES ('769', '768', '0,1,26,768,', '积分动态获取', '/admin/verify_user_integral.htm', null, null, '1', '0', '2', 'verify_user_integral', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '积分管理', '1');
INSERT INTO `sys_menu` VALUES ('770', '768', '0,1,26,768,', '积分管理保存', '/admin/user_integral_save.htm', null, null, '2', '1', '0', 'user_integral_save', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '积分管理', '1');
INSERT INTO `sys_menu` VALUES ('771', '30', '0,1,30,', '全文检索', '/admin/lucene.htm', null, null, '3', '1', '0', 'lucene', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '全文检索', '1');
INSERT INTO `sys_menu` VALUES ('772', '771', '0,1,30,771,', '全文检索关键字保存', '/admin/lucene_hot_save.htm', null, null, '1', '1', '2', 'lucene_hot_save', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', '全文检索', '1');
INSERT INTO `sys_menu` VALUES ('773', '774', '0,1,28,774,', '页面导航添加', '/admin/navigation_add.htm', null, null, '1', '0', '2', 'navigation_add', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '页面导航', '1');
INSERT INTO `sys_menu` VALUES ('774', '28', '0,1,28,', '页面导航', '/admin/navigation_list.htm', null, null, '5', '1', '0', 'navigation_list', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '页面导航', '1');
INSERT INTO `sys_menu` VALUES ('775', '774', '0,1,28,774,', '页面导航保存', '/admin/navigation_save.htm', null, null, '2', '0', '2', 'navigation_save', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '页面导航', '1');
INSERT INTO `sys_menu` VALUES ('776', '774', '0,1,28,774,', '页面导航删除', '/admin/navigation_del.htm', null, null, '3', '0', '2', 'navigation_del', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '页面导航', '1');
INSERT INTO `sys_menu` VALUES ('777', '774', '0,1,28,774,', '页面导航编辑', '/admin/navigation_edit.htm', null, null, '4', '0', '2', 'navigation_edit', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '页面导航', '1');
INSERT INTO `sys_menu` VALUES ('778', '774', '0,1,28,774,', '页面导航AJAX更新', '/admin/navigation_ajax.htm', null, null, '5', '0', '2', 'navigation_ajax', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '页面导航', '1');
INSERT INTO `sys_menu` VALUES ('779', '29', '0,1,29,', '基本设置', '/admin/operation_base_set.htm', null, null, '1', '1', '0', 'operation_base_set', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '基本设置', '1');
INSERT INTO `sys_menu` VALUES ('780', '779', '0,1,29,779,', '基本设置保存', '/admin/base_set_save.htm', null, null, '1', '0', '2', 'base_set_save', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '基本设置', '1');
INSERT INTO `sys_menu` VALUES ('781', '919', '0,1,29,919,', '积分规则', '/admin/operation_integral_rule.htm', null, null, '1', '1', '0', 'operation_integral_rule', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分规则', '1');
INSERT INTO `sys_menu` VALUES ('782', '781', '0,1,29,919,781,', '积分规则保存', '/admin/integral_rule_save.htm', null, null, '1', '1', '0', 'integral_rule_save', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', '积分设置', '1');
INSERT INTO `sys_menu` VALUES ('783', '27', '0,1,27,', '订单管理', '/admin/order_list.htm', null, null, '1', '1', '0', 'order_list', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '订单管理', '1');
INSERT INTO `sys_menu` VALUES ('784', '783', '0,1,27,783,', '订单详情', '/admin/order_view.htm', null, null, '1', '0', '2', 'order_view', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '订单管理', '1');
INSERT INTO `sys_menu` VALUES ('785', '786', '0,1,27,786,', '订单设置保存', '/admin/set_order_confirm_save.htm', null, null, '1', '0', '2', 'set_order_confirm_save', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '订单设置', '1');
INSERT INTO `sys_menu` VALUES ('786', '27', '0,1,27,', '订单设置', '/admin/set_order_confirm.htm', null, null, '2', '1', '0', 'set_order_confirm', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '订单设置', '1');
INSERT INTO `sys_menu` VALUES ('787', '788', '0,1,28,788,', '合作伙伴添加', '/admin/partner_add.htm', null, null, '1', '0', '2', 'partner_add', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '合作伙伴', '1');
INSERT INTO `sys_menu` VALUES ('788', '28', '0,1,28,', '合作伙伴', '/admin/partner_list.htm', null, null, '4', '1', '0', 'partner_list', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '合作伙伴', '1');
INSERT INTO `sys_menu` VALUES ('789', '788', '0,1,28,788,', '合作伙伴保存', '/admin/partner_save.htm', null, null, '2', '0', '2', 'partner_save', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '合作伙伴', '1');
INSERT INTO `sys_menu` VALUES ('790', '788', '0,1,28,788,', '合作伙伴删除', '/admin/partner_del.htm', null, null, '3', '0', '2', 'partner_del', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '合作伙伴', '1');
INSERT INTO `sys_menu` VALUES ('791', '788', '0,1,28,788,', '合作伙伴编辑', '/admin/partner_edit.htm', null, null, '4', '0', '2', 'partner_edit', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', '合作伙伴', '1');
INSERT INTO `sys_menu` VALUES ('792', '913', '0,1,23,913,', '支付方式', '/admin/payment_list.htm', null, null, '1', '1', '0', 'payment_list', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '支付方式', '1');
INSERT INTO `sys_menu` VALUES ('793', '792', '0,1,23,913,792,', '启/禁用', '/admin/payment_set.htm', null, null, '1', '0', '2', 'payment_set', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '支付方式', '1');
INSERT INTO `sys_menu` VALUES ('794', '792', '0,1,23,913,792,', '查看', '/admin/payment_edit.htm', null, null, '2', '0', '2', 'payment_view', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '支付方式', '1');
INSERT INTO `sys_menu` VALUES ('795', '792', '0,1,23,913,792,', '编辑', '/admin/payment_save.htm', null, null, '3', '0', '2', 'payment_edit', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '支付方式', '1');
INSERT INTO `sys_menu` VALUES ('796', '792', '0,1,23,913,792,', '平台支付方式设置', '/admin/payment_config_set.htm', null, null, '4', '0', '2', 'payment_config_set', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '支付方式', '1');
INSERT INTO `sys_menu` VALUES ('797', '803', '0,1,26,803,', '提现管理', '/admin/predeposit_cash.htm', null, null, '1', '0', '1', 'predeposit_cash', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '预存款管理', '1');
INSERT INTO `sys_menu` VALUES ('798', '797', '0,1,26,803,797,', '提现申请编辑', '/admin/predeposit_cash_edit.htm', null, null, '1', '0', '2', 'predeposit_cash_edit', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '预存款管理', '1');
INSERT INTO `sys_menu` VALUES ('799', '797', '0,1,26,803,797,', '提现申请编辑保存', '/admin/predeposit_cash_save.htm', null, null, '2', '0', '2', 'predeposit_cash_save', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '预存款管理', '1');
INSERT INTO `sys_menu` VALUES ('800', '797', '0,1,26,803,797,', '提现申请详情', '/admin/predeposit_cash_view.htm', null, null, '3', '0', '2', 'predeposit_cash_view', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '预存款管理', '1');
INSERT INTO `sys_menu` VALUES ('801', '26', '0,1,26,', '预存款明细', '/admin/predepositlog_list.htm', null, null, '7', '1', '0', 'predepositlog_list', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '预存款明细', '1');
INSERT INTO `sys_menu` VALUES ('802', '803', '0,1,26,803,', '预存款手动修改保存', '/admin/predeposit_modify_save.htm', null, null, '2', '0', '0', 'predeposit_modify_save', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '预存款管理', '1');
INSERT INTO `sys_menu` VALUES ('803', '26', '0,1,26,', '预存款管理', '/admin/predeposit_list.htm', null, null, '6', '0', '2', 'predeposit_list', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '预存款管理', '1');
INSERT INTO `sys_menu` VALUES ('805', '803', '0,1,26,803,', '预存款编辑', '/admin/predeposit_edit.htm', null, null, '3', '0', '2', 'predeposit_edit', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '预存款管理', '1');
INSERT INTO `sys_menu` VALUES ('806', '803', '0,1,26,803,', '预存款保存', '/admin/predeposit_save.htm', null, null, '4', '0', '2', 'predeposit_save', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '预存款管理', '1');
INSERT INTO `sys_menu` VALUES ('807', '803', '0,1,26,803,', '预存款手动修改', '/admin/predeposit_modify.htm', null, null, '5', '1', '0', 'predeposit_modify', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '预存款管理', '1');
INSERT INTO `sys_menu` VALUES ('808', '803', '0,1,26,803,', '加载用户预存款信息', '/admin/predeposit_user.htm', null, null, '6', '0', '2', 'predeposit_user', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '预存款管理', '1');
INSERT INTO `sys_menu` VALUES ('809', '27', '0,1,27,', '举报管理', '/admin/report_list.htm', null, null, '4', '1', '0', 'report_list', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '举报管理', '1');
INSERT INTO `sys_menu` VALUES ('810', '809', '0,1,27,809,', '已处理举报列表', '/admin/report_handle_list.htm', null, null, '3', '0', '1', 'report_handle_list', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '举报管理', '1');
INSERT INTO `sys_menu` VALUES ('811', '809', '0,1,27,809,', '举报处理', '/admin/report_handle.htm', null, null, '1', '0', '2', 'report_handle', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '举报管理', '1');
INSERT INTO `sys_menu` VALUES ('812', '809', '0,1,27,809,', '举报处理保存', '/admin/report_handle_save.htm', null, null, '2', '0', '2', 'report_handle_save', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '举报管理', '1');
INSERT INTO `sys_menu` VALUES ('813', '809', '0,1,27,809,', '举报主题增加', '/admin/reportsubject_add.htm', null, null, '6', '0', '1', 'reportsubject_add', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '举报管理', '1');
INSERT INTO `sys_menu` VALUES ('814', '809', '0,1,27,809,', '举报主题', '/admin/reportsubject_list.htm', null, null, '5', '0', '1', 'reportsubject_list', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '举报管理', '1');
INSERT INTO `sys_menu` VALUES ('815', '814', '0,1,27,809,814,', '举报主题保存', '/admin/reportsubject_save.htm', null, null, '1', '0', '2', 'reportsubject_save', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '举报管理', '1');
INSERT INTO `sys_menu` VALUES ('816', '814', '0,1,27,809,814,', '举报主题删除', '/admin/reportsubject_del.htm', null, null, '2', '0', '2', 'reportsubject_del', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '举报管理', '1');
INSERT INTO `sys_menu` VALUES ('817', '814', '0,1,27,809,814,', '举报主题编辑', '/admin/reportsubject_edit.htm', null, null, '3', '0', '2', 'reportsubject_edit', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '举报管理', '1');
INSERT INTO `sys_menu` VALUES ('818', '819', '0,1,27,809,819,', '举报类型增加', '/admin/reporttype_add.htm', null, null, '1', '0', '2', 'reporttype_add', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '举报管理', '1');
INSERT INTO `sys_menu` VALUES ('819', '809', '0,1,27,809,', '举报类型', '/admin/reporttype_list.htm', null, null, '4', '0', '1', 'reporttype_list', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '举报管理', '1');
INSERT INTO `sys_menu` VALUES ('820', '819', '0,1,27,809,819,', '举报类型保存', '/admin/reporttype_save.htm', null, null, '2', '0', '2', 'reporttype_save', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '举报管理', '1');
INSERT INTO `sys_menu` VALUES ('821', '819', '0,1,27,809,819,', '举报类型删除', '/admin/reporttype_del.htm', null, null, '3', '0', '2', 'reporttype_del', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '举报管理', '1');
INSERT INTO `sys_menu` VALUES ('822', '819', '0,1,27,809,819,', '举报类型编辑', '/admin/reporttype_edit.htm', null, null, '4', '0', '2', 'reporttype_edit', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '举报管理', '1');
INSERT INTO `sys_menu` VALUES ('823', '915', '0,1,26,915,', '会员动态', '/admin/sns_user.htm', null, null, '1', '1', '0', 'sns_user', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '会员管理', '1');
INSERT INTO `sys_menu` VALUES ('824', '823', '0,1,26,915,823,', '会员动态删除', '/admin/sns_del.htm', null, null, '1', '0', '2', 'sns_del', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '会员管理', '1');
INSERT INTO `sys_menu` VALUES ('825', '915', '0,1,26,915,', '店铺动态', '/admin/sns_store.htm', null, null, '2', '1', '0', 'sns_store', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '会员管理', '1');
INSERT INTO `sys_menu` VALUES ('826', '825', '0,1,26,915,825,', '店铺动态删除', '/admin/sns_store_del.htm', null, null, '1', '0', '2', 'sns_store_del', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '会员管理', '1');
INSERT INTO `sys_menu` VALUES ('827', '915', '0,1,26,915,', 'sns动态设置可见度', '/admin/sns_set_display.htm', null, null, '3', '0', '2', 'sns_set_display', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', '会员管理', '1');
INSERT INTO `sys_menu` VALUES ('851', '852', '0,1,25,852,', '店铺分类添加', '/admin/storeclass_add.htm', null, null, '1', '0', '0', 'storeclass_add', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '店铺分类', '1');
INSERT INTO `sys_menu` VALUES ('852', '25', '0,1,25,', '店铺分类', '/admin/storeclass_list.htm', null, null, '5', '1', '0', 'storeclass_list', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '店铺分类', '1');
INSERT INTO `sys_menu` VALUES ('853', '852', '0,1,25,852,', '店铺分类保存', '/admin/storeclass_save.htm', null, null, '2', '1', '2', 'storeclass_save', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '店铺分类', '1');
INSERT INTO `sys_menu` VALUES ('854', '852', '0,1,25,852,', '店铺分类删除', '/admin/storeclass_del.htm', null, null, '3', '0', '2', 'storeclass_del', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '店铺分类', '1');
INSERT INTO `sys_menu` VALUES ('855', '852', '0,1,25,852,', '店铺分类下级数据加载', '/admin/storeclass_data.htm', null, null, '4', '0', '2', 'storeclass_data', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '店铺分类', '1');
INSERT INTO `sys_menu` VALUES ('856', '852', '0,1,25,852,', '店铺分类编辑', '/admin/storeclass_edit.htm', null, null, '5', '0', '2', 'storeclass_edit', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '店铺分类', '1');
INSERT INTO `sys_menu` VALUES ('857', '852', '0,1,25,852,', '店铺分类AJAX保存', '/admin/storeclass_ajax.htm', null, null, '7', '1', '0', 'storeclass_ajax', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '店铺分类', '1');
INSERT INTO `sys_menu` VALUES ('858', '859', '0,1,25,859,', '店铺等级模板保存', '/admin/storegrade_template_save.htm', null, null, '1', '0', '2', 'storegrade_template_save', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺等级', '1');
INSERT INTO `sys_menu` VALUES ('859', '25', '0,1,25,', '店铺等级', '/admin/storegrade_list.htm', null, null, '4', '1', '0', 'storegrade_list', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺等级', '1');
INSERT INTO `sys_menu` VALUES ('860', '859', '0,1,25,859,', '店铺等级添加', '/admin/storegrade_add.htm', null, null, '2', '0', '2', 'storegrade_add', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺等级', '1');
INSERT INTO `sys_menu` VALUES ('861', '859', '0,1,25,859,', '店铺等级编辑', '/admin/storegrade_edit.htm', null, null, '3', '0', '2', 'storegrade_edit', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺等级', '1');
INSERT INTO `sys_menu` VALUES ('862', '859', '0,1,25,859,', '店铺等级保存', '/admin/storegrade_save.htm', null, null, '4', '0', '2', 'storegrade_save', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '店铺等级', '1');
INSERT INTO `sys_menu` VALUES ('863', '859', '0,1,25,859,', '店铺等级删除', '/admin/storegrade_del.htm', null, null, '5', '1', '0', 'storegrade_del', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '店铺等级', '1');
INSERT INTO `sys_menu` VALUES ('864', '859', '0,1,25,859,', '店铺等级AJAX更新', '/admin/storegrade_ajax.htm', null, null, '6', '1', '0', 'storegrade_ajax', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '店铺等级', '1');
INSERT INTO `sys_menu` VALUES ('865', '859', '0,1,25,859,', '店铺等级模板设置', '/admin/storegrade_template.htm', null, null, '8', '0', '2', 'storegrade_template', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '店铺等级', '1');
INSERT INTO `sys_menu` VALUES ('866', '25', '0,1,25,', '店铺管理', '/admin/store_list.htm', null, null, '2', '1', '0', 'store_list', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺管理', '1');
INSERT INTO `sys_menu` VALUES ('867', '866', '0,1,25,866,', '添加', '/admin/store_add.htm', null, null, '1', '0', '2', 'store_add', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺管理', '1');
INSERT INTO `sys_menu` VALUES ('868', '866', '0,1,25,866,', '添加2', '/admin/store_new.htm', null, null, '2', '0', '2', 'store_new', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺管理', '1');
INSERT INTO `sys_menu` VALUES ('869', '866', '0,1,25,866,', '查看', '/admin/store_edit.htm', null, null, '3', '0', '0', 'store_edit', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺管理', '1');
INSERT INTO `sys_menu` VALUES ('870', '866', '0,1,25,866,', '编辑', '/admin/store_save.htm', null, null, '4', '0', '2', 'store_save', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺管理', '1');
INSERT INTO `sys_menu` VALUES ('871', '866', '0,1,25,866,', '删除', '/admin/store_del.htm', null, null, '5', '0', '0', 'store_del', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺管理', '1');
INSERT INTO `sys_menu` VALUES ('872', '866', '0,1,25,866,', 'AJAX更新', '/admin/store_ajax.htm', null, null, '6', '0', '2', 'store_ajax', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺管理', '1');
INSERT INTO `sys_menu` VALUES ('873', '25', '0,1,25,', '卖家信用', '/admin/store_base.htm', null, null, '1', '1', '0', 'store_credit', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '基本设置', '1');
INSERT INTO `sys_menu` VALUES ('874', '873', '0,1,25,873,', '保存', '/admin/store_set_save.htm', null, null, '1', '0', '2', 'store_set_save', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '基本设置', '1');
INSERT INTO `sys_menu` VALUES ('875', '25', '0,1,25,', '店铺模板', '/admin/store_template.htm', null, null, '3', '1', '0', 'store_template', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺模板', '1');
INSERT INTO `sys_menu` VALUES ('876', '875', '0,1,25,875,', '添加', '/admin/store_template_add.htm', null, null, '1', '0', '2', 'store_template_add', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺模板', '1');
INSERT INTO `sys_menu` VALUES ('877', '875', '0,1,25,875,', '保存', '/admin/store_template_save.htm', null, null, '2', '0', '0', 'store_template_save', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺模板', '1');
INSERT INTO `sys_menu` VALUES ('878', '866', '0,1,25,866,', '店铺升级管理', '/admin/store_gradelog_list.htm', null, null, '7', '1', '1', 'store_gradelog_list', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺管理', '1');
INSERT INTO `sys_menu` VALUES ('879', '878', '0,1,25,866,878,', '店铺升级编辑', '/admin/store_gradelog_edit.htm', null, null, '1', '0', '2', 'store_gradelog_edit', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺管理', '1');
INSERT INTO `sys_menu` VALUES ('880', '878', '0,1,25,866,878,', '店铺升级保存', '/admin/store_gradelog_save.htm', null, null, '2', '0', '2', 'store_gradelog_save', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺管理', '1');
INSERT INTO `sys_menu` VALUES ('881', '878', '0,1,25,866,878,', '店铺升级日志查看', '/admin/store_gradelog_view.htm', null, null, '3', '0', '2', 'store_gradelog_view', '1', '2017-10-10 14:56:33', '1', '2017-10-10 14:56:33', '店铺管理', '1');
INSERT INTO `sys_menu` VALUES ('882', '883', '0,1,23,883,', '添加', '/admin/template_add.htm', null, null, '2', '0', '2', 'notice_template_add', '1', '2017-10-10 14:56:30', '1', '2017-10-10 14:56:30', '通知模板', '1');
INSERT INTO `sys_menu` VALUES ('883', '23', '0,1,23,', '通知模板', '/admin/template_list.htm', null, null, '7', '1', '0', 'notice_template_list', '1', '2017-10-10 14:56:30', '1', '2017-10-10 14:56:30', '通知模板', '1');
INSERT INTO `sys_menu` VALUES ('884', '883', '0,1,23,883,', '查看', '/admin/template_edit.htm', null, null, '1', '0', '2', 'notice_template_view', '1', '2017-10-10 14:56:30', '1', '2017-10-10 14:56:30', '通知模板', '1');
INSERT INTO `sys_menu` VALUES ('885', '883', '0,1,23,883,', '编辑', '/admin/template_save.htm', null, null, '3', '0', '2', 'notice_template_edit', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '通知模板', '1');
INSERT INTO `sys_menu` VALUES ('887', '883', '0,1,23,883,', '开启', '/admin/template_open.htm', null, null, '4', '0', '2', 'notice_template_open', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '通知模板', '1');
INSERT INTO `sys_menu` VALUES ('888', '883', '0,1,23,883,', '复制', '/admin/template_copy.htm', null, null, '5', '0', '2', 'notice_template_copy', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '通知模板', '1');
INSERT INTO `sys_menu` VALUES ('889', '912', '0,1,23,912,', '运费区域', '/admin/trans_area_list.htm', null, null, '2', '1', '0', 'trans_area_list', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '运费区域', '1');
INSERT INTO `sys_menu` VALUES ('890', '889', '0,1,23,912,889,', '编辑', '/admin/trans_area_save.htm', null, null, '1', '0', '2', 'trans_area_edit', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '运费区域', '1');
INSERT INTO `sys_menu` VALUES ('891', '889', '0,1,23,912,889,', '删除', '/admin/trans_area_del.htm', null, null, '2', '0', '2', 'trans_area_delete', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '运费区域', '1');
INSERT INTO `sys_menu` VALUES ('892', '889', '0,1,23,912,889,', 'Ajax更新', '/admin/trans_area_ajax.htm', null, null, '3', '0', '2', 'trans_area_edit_ajax', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', '运费区域', '1');
INSERT INTO `sys_menu` VALUES ('893', '921', '0,1,30,921,', 'UC配置', '/admin/ucenter.htm', null, null, '1', '1', '0', 'ucenter', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', 'UC整合', '1');
INSERT INTO `sys_menu` VALUES ('894', '893', '0,1,30,921,893,', 'UC信息保存', '/admin/ucenter_save.htm', null, null, '30', '1', '2', 'ucenter_save', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', 'UC整合', '1');
INSERT INTO `sys_menu` VALUES ('895', '26', '0,1,26,', '会员信用', '/admin/user_creditrule.htm', null, null, '2', '1', '0', 'user_creditrule', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '会员信用', '1');
INSERT INTO `sys_menu` VALUES ('896', '26', '0,1,26,', '会员管理', '/admin/user_list.htm', null, null, '1', '1', '0', 'user_list', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '会员管理', '1');
INSERT INTO `sys_menu` VALUES ('897', '896', '0,1,26,896,', '会员添加', '/admin/user_add.htm', null, null, '1', '0', '2', 'user_add', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '会员管理', '1');
INSERT INTO `sys_menu` VALUES ('898', '896', '0,1,26,896,', '会员编辑', '/admin/user_edit.htm', null, null, '2', '0', '2', 'user_edit', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '会员管理', '1');
INSERT INTO `sys_menu` VALUES ('899', '896', '0,1,26,896,', '会员保存', '/admin/user_save.htm', null, null, '3', '0', '2', 'user_save', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '会员管理', '1');
INSERT INTO `sys_menu` VALUES ('900', '896', '0,1,26,896,', '会员删除', '/admin/user_del.htm', null, null, '4', '1', '2', 'user_del', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '会员管理', '1');
INSERT INTO `sys_menu` VALUES ('901', '26', '0,1,26,', '会员通知', '/admin/user_msg.htm', null, null, '3', '1', '0', 'user_msg', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '会员通知', '1');
INSERT INTO `sys_menu` VALUES ('902', '901', '0,1,26,901,', '会员通知发送', '/admin/user_msg_send.htm', null, null, '1', '0', '2', 'user_msg_send', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '会员通知', '1');
INSERT INTO `sys_menu` VALUES ('903', '895', '0,1,26,895,', '会员信用保存', '/admin/user_creditrule_save.htm', null, null, '1', '0', '0', 'user_creditrule_save', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', '会员信用', '1');
INSERT INTO `sys_menu` VALUES ('904', '918', '0,1,29,918,', '直通车设置', '/admin/ztc_set.htm', null, null, '1', '1', '1', 'ztc_set', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '竞价直通车', '1');
INSERT INTO `sys_menu` VALUES ('905', '904', '0,1,29,918,904,', '保存', '/admin/ztc_set_save.htm', null, null, '1', '0', '2', 'ztc_set_save', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '竞价直通车', '1');
INSERT INTO `sys_menu` VALUES ('906', '918', '0,1,29,918,', '直通车申请', '/admin/ztc_apply.htm', null, null, '2', '0', '1', 'ztc_apply', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '竞价直通车', '1');
INSERT INTO `sys_menu` VALUES ('907', '918', '0,1,29,918,', '直通车商品', '/admin/ztc_goods.htm', null, null, '3', '1', '1', 'ztc_goods', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '竞价直通车', '1');
INSERT INTO `sys_menu` VALUES ('908', '918', '0,1,29,918,', '金币日志', '/admin/ztc_gold_log.htm', null, null, '4', '1', '1', 'ztc_gold_log', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '竞价直通车', '1');
INSERT INTO `sys_menu` VALUES ('909', '906', '0,1,29,918,906,', '审核编辑', '/admin/ztc_apply_edit.htm', null, null, '1', '0', '2', 'ztc_apply_edit', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '竞价直通车', '1');
INSERT INTO `sys_menu` VALUES ('910', '906', '0,1,29,918,906,', '查看', '/admin/ztc_apply_view.htm', null, null, '2', '0', '2', 'ztc_apply_view', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '竞价直通车', '1');
INSERT INTO `sys_menu` VALUES ('911', '906', '0,1,29,918,906,', '审核保存', '/admin/ztc_apply_save.htm', null, null, '3', '0', '2', 'ztc_apply_save', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', '竞价直通车', '1');
INSERT INTO `sys_menu` VALUES ('912', '23', '0,1,23,', '地区管理', '', null, null, '8', '1', '0', '', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', null, '1');
INSERT INTO `sys_menu` VALUES ('913', '23', '0,1,23,', '支付管理', '', null, null, '9', '1', '0', '', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', null, '1');
INSERT INTO `sys_menu` VALUES ('914', '23', '0,1,23,', '快递管理', '', null, null, '10', '1', '0', '', '1', '2017-10-10 14:56:31', '1', '2017-10-10 14:56:31', null, '1');
INSERT INTO `sys_menu` VALUES ('915', '26', '0,1,26,', 'SNS管理', '', null, null, '9', '1', '0', '', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', null, '1');
INSERT INTO `sys_menu` VALUES ('916', '803', '0,1,26,803,', '预存款查看', '/admin/predeposit_view.htm', null, null, '7', '0', '2', 'predeposit_view', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', null, '1');
INSERT INTO `sys_menu` VALUES ('917', '29', '0,1,29,', '金币管理', '/admin/gold_record.htm', null, null, '2', '1', '0', 'gold_record', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', null, '1');
INSERT INTO `sys_menu` VALUES ('918', '29', '0,1,29,', '竞价直通车', '/admin/ztc_set.htm', null, null, '2', '1', '0', 'ztc_set', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', null, '1');
INSERT INTO `sys_menu` VALUES ('919', '29', '0,1,29,', '积分中心', '', null, null, '7', '1', '0', '', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', null, '1');
INSERT INTO `sys_menu` VALUES ('920', '29', '0,1,29,', '促销管理', '', null, null, '8', '1', '0', '', '1', '2017-10-10 14:56:38', '1', '2017-10-10 14:56:38', null, '1');
INSERT INTO `sys_menu` VALUES ('921', '30', '0,1,30,', '外部系统', '', null, null, '4', '1', '0', '', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '1');
INSERT INTO `sys_menu` VALUES ('922', '27', '0,1,27,', '采料订单结算中心', '/admin/order_checkout_list.htm', null, null, '30', '1', '0', 'order_checkout_list', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('923', '27', '0,1,27,', '订单退款管理', '/admin/order_refund_list.htm', null, null, '31', '1', '0', 'order_refund_list', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('924', '923', '0,1,27,923,', '订单退款管理查看权限', '', null, null, '30', '0', '1', 'order_refund_list', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('925', '922', '0,1,27,922,', '采料订单结算中心查看权限', '', null, null, '30', '0', '1', 'order_checkout_list', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('926', '922', '0,1,27,922,', '查看', '', null, null, '30', '0', '1', 'order_checkout_view', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('927', '923', '0,1,27,923,', '退款操作', '', null, null, '30', '0', '1', 'order_refund_view', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('928', '25', '0,1,25,', '店铺结账', '/admin/store_payment_list.htm', null, null, '30', '1', '0', 'store_payment', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', null, '1');
INSERT INTO `sys_menu` VALUES ('929', '928', '0,1,25,928,', '审核', '/admin/store_payment_view.htm', null, null, '1', '0', '0', 'store_payment_view', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', null, '1');
INSERT INTO `sys_menu` VALUES ('930', '928', '0,1,25,928,', '编辑', '/admin/store_payment_save.htm', null, null, '3', '0', '2', 'store_payment_save', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', null, '1');
INSERT INTO `sys_menu` VALUES ('931', '928', '0,1,25,928,', '查看', '/admin/store_payment_apply_list.htm', null, null, '3', '0', '2', 'store_payment_apply_list', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', null, '1');
INSERT INTO `sys_menu` VALUES ('932', '922', '0,1,27,922,', '审核', '/admin/order_checkout_pending.htm', null, null, '3', '0', '0', 'order_checkout_pending', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('933', '27', '0,1,27,', '采料订单结算审核中心', '/admin/order_checkout_pending_list.htm', null, null, '31', '0', '0', 'order_checkout_pending_list', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('934', '933', '0,1,27,933,', '查看', '/admin/order_checkout_pending_view.htm', null, null, '30', '0', '1', 'order_checkout_pending_view', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('935', '933', '0,1,27,933,', '保存', '/admin/order_checkout_pending_save.htm', null, null, '3', '0', '2', 'order_checkout_pending_save', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('936', '27', '0,1,27,', '订单执行操作', '/admin/order_copy_list.htm', null, null, '30', '1', '0', 'order_copy_list', '1', '2017-03-15 14:16:28', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('937', '936', '0,1,27,936,', '查看', '/admin/order_copy_view.htm', null, null, '30', '1', '2', 'order_copy_view', '1', '2017-03-10 10:53:04', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('938', '933', '0,1,27,933,', '查看执行记录', '/admin/order_SettlementLog_list.htm', null, null, '30', '1', '1', 'order_SettlementLog_list', '1', '2017-03-13 11:54:21', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('939', '936', '0,1,27,936,', '查看执行详情', '/admin/order_orderFormCopy_view.htm', null, null, '30', '1', '1', 'order_orderFormCopy_view', '1', '2017-03-15 10:10:40', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('940', '27', '0,1,27,', '查看执行记录', '/admin/order_SettlementLog_list.htm', null, null, '30', '1', '0', 'order_SettlementLog_list', '1', '2017-03-16 08:38:31', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('941', '27', '0,1,27,', '订单统计菜单', '/admin/order_checkoutCount_list.htm', null, null, '30', '1', '0', 'order_checkoutCount_list', '1', '2017-03-17 16:58:23', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('942', '936', '0,1,27,936,', '删除！！！', '/admin/order_copy_list_delete.htm', null, null, '30', '1', '2', 'order_copy_list_delete', '1', '2017-03-20 10:48:00', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('943', '27', '0,1,27,', '订单结算中心', '', null, null, '30', '1', '0', '', '1', '2017-10-10 14:56:35', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('944', '943', '0,1,27,943,', '订单结算执行', '/admin/order_execute_list.htm', null, null, '1', '1', '0', 'order_execute_list', '1', '2017-03-31 16:16:56', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('945', '943', '0,1,27,943,', '结算执行记录', '/admin/order_settlementLog_list.htm', null, null, '2', '1', '0', 'order_settlementLog_list', '1', '2017-03-31 16:36:56', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('946', '943', '0,1,27,943,', '订单结算', '/admin/order_checkoutCount_list.htm', null, null, '3', '1', '0', 'order_checkoutCount_list', '1', '2017-03-20 16:19:45', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('947', '944', '0,1,27,943,944,', '查看订单详情', '/admin/order_execute_view.htm', null, null, '1', '1', '1', 'order_execute_view', '1', '2017-03-31 16:17:17', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('948', '944', '0,1,27,943,944,', '查看执行记录', '/admin/order_settlementLog_view.htm', null, null, '2', '1', '1', 'order_settlementLog_view', '1', '2017-03-31 16:42:09', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('949', '944', '0,1,27,943,944,', '删除！！！', '/admin/order_copy_list_delete.htm', null, null, '30', '1', '2', 'order_copy_list_delete', '1', '2017-03-20 16:22:20', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('950', '944', '0,1,27,943,944,', '订单结算审核', '', null, null, '4', '1', '0', '', '1', '2017-03-20 16:24:18', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('951', '943', '0,1,27,943,', '订单结算审核', '/admin/order_pending_list.htm', null, null, '4', '1', '0', 'order_pending_list', '1', '2017-07-24 16:48:17', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('952', '951', '0,1,27,943,951,', '审核', '/admin/order_pending_view.htm', null, null, '1', '1', '2', 'order_pending_view', '1', '2017-03-21 14:07:53', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('953', '951', '0,1,27,943,951,', '审核', 'order_pending_view', null, null, '30', '1', '1', 'order_pending_view', '1', '2017-03-21 14:15:16', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('954', '951', '0,1,27,943,951,', '查看订单详情', '/admin/order_chechout_pending_view.htm', null, null, '2', '1', '1', 'order_chechout_pending_view', '1', '2017-03-22 11:06:03', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('955', '946', '0,1,27,943,946,', '查看明细', '/admin/order_checkoutCount_detailed_list.htm', null, null, '30', '1', '1', 'order_checkoutCount_detailed_list', '1', '2017-03-31 09:15:06', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('956', '29', '0,1,29,', '优惠券管理列表', '', null, null, '4', '1', '0', '', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', null, '1');
INSERT INTO `sys_menu` VALUES ('957', '956', '0,1,29,956,', '优惠券申请审核列表', '/admin/coupon_application_list.htm', null, null, '2', '1', '0', 'coupon_application_list', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', null, '1');
INSERT INTO `sys_menu` VALUES ('958', '956', '0,1,29,956,', '所有优惠券列表', '/admin/all_coupon_list.htm', null, null, '1', '1', '0', 'all_coupon_list', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', null, '1');
INSERT INTO `sys_menu` VALUES ('959', '957', '0,1,29,956,957,', '优惠券审核功能', '/admin/coupon_application.htm', null, null, '1', '1', '2', 'coupon_application', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', null, '1');
INSERT INTO `sys_menu` VALUES ('960', '957', '0,1,29,956,957,', '审核保存', '/admin/coupon_application_save.htm', null, null, '2', '1', '2', 'coupon_application_save', '1', '2017-10-10 14:56:37', '1', '2017-10-10 14:56:37', null, '1');
INSERT INTO `sys_menu` VALUES ('961', '30', '0,1,30,', '店铺空间管理更新', '/admin/storeSpace.htm', null, null, '30', '1', '0', 'storeSpace_manage', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '1');
INSERT INTO `sys_menu` VALUES ('962', '961', '0,1,30,961,', '开始更新', '/admin/update_storeSpace.htm', null, null, '30', '1', '2', 'update_storeSpace', '1', '2017-10-10 14:56:39', '1', '2017-10-10 14:56:39', null, '1');
INSERT INTO `sys_menu` VALUES ('963', '699', '0,1,24,699,', '商品违规下架详情', '/admin/goods_sale_detail.htm', null, null, '7', '0', '0', 'goods_sale_detail', '1', '2017-10-10 14:56:32', '1', '2017-10-10 14:56:32', null, '1');
INSERT INTO `sys_menu` VALUES ('964', '943', '0,1,27,943,', '执行七天', '/admin/checkout_execute.htm', null, null, '5', '1', '0', 'checkout_execute', '1', '2017-07-24 16:48:23', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('965', '943', '0,1,27,943,', '结算单申请列表', '/admin/settlement_list.htm', null, null, '1', '1', '0', 'settlement_list', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('966', '965', '0,1,27,943,965,', '结算单详情', '/admin/settlement_detail.htm', null, null, '1', '1', '2', 'settlement_detail', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('967', '965', '0,1,27,943,965,', '提交审核', '/admin/settlement_apply.htm1', null, null, '30', '1', '2', 'settlement_apply1', '1', '2017-11-07 16:46:16', '1', '2017-11-07 16:46:16', null, '1');
INSERT INTO `sys_menu` VALUES ('968', '943', '0,1,27,943,', '结算单审核列表', '/admin/settlement_confirm.htm', null, null, '3', '1', '0', 'settlement_confirm', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('969', '968', '0,1,27,943,968,', '结算单审核操作', '/admin/settlement_confirm_save.htm', null, null, '2', '1', '1', 'settlement_confirm_save', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('970', '968', '0,1,27,943,968,', '结算单详情', '/admin/settlement_detail.htm', null, null, '1', '1', '2', 'settlement_detail', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('971', '943', '0,1,27,943,', '结算单确认转账列表', '/admin/settlement_transferAccounts.htm', null, null, '2', '1', '0', 'settlement_transferAccounts', '1', '2017-08-04 15:48:29', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('972', '971', '0,1,27,943,971,', '结算单确认转账列表确认操作', '/admin/settlement_transferAccounts_confirm.htm', null, null, '1', '1', '2', 'settlement_transferAccounts_confirm', '1', '2017-08-04 16:22:01', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('973', '971', '0,1,27,943,971,', '结算单详情', '/admin/settlement_detail.htm', null, null, '2', '1', '2', 'settlement_detail', '1', '2017-08-04 16:25:12', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('974', '971', '0,1,27,943,971,', '生成excel表单', '/admin/settlement_excel.htm', null, null, '3', '1', '2', 'settlement_excel', '1', '2017-08-05 18:18:47', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('975', '971', '0,1,27,943,971,', '结算单查看excel表', '/admin/settlement_excel_view.htm', null, null, '3', '1', '2', 'settlement_excel_view', '1', '2017-08-16 09:34:41', '1', '2017-10-10 14:56:35', null, '1');
INSERT INTO `sys_menu` VALUES ('976', '943', '0,1,27,943,', '结算单生成EXCEL列表', '/admin/settlement_excel.htm', null, null, '2', '1', '0', 'settlement_excel', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('977', '976', '0,1,27,943,976,', '生成EXCEL', '/admin/settlement_execute_excel.htm', null, null, '1', '1', '2', 'settlement_execute_excel', '1', '2017-10-10 14:56:36', '1', '2017-10-10 14:56:36', null, '1');
INSERT INTO `sys_menu` VALUES ('978', '896', '0,1,26,896,', '会员禁用', '/admin/user_disable.htm', null, null, '30', '1', '2', 'user_disable', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', null, '1');
INSERT INTO `sys_menu` VALUES ('979', '896', '0,1,26,896,', '会员启用', '/admin/user_enable.htm', null, null, '30', '1', '2', 'user_enable', '1', '2017-10-10 14:56:34', '1', '2017-10-10 14:56:34', null, '1');
INSERT INTO `sys_menu` VALUES ('980', '2', '0,1,2,', 'app系统版本管理', '', null, null, '30', '1', '0', '', '1', '2017-10-10 10:48:22', '1', '2017-10-10 14:56:39', null, '1');
INSERT INTO `sys_menu` VALUES ('981', '3', '0,1,2,3,', 'app系统版本管理', '', null, null, '30', '1', '0', '', '1', '2017-10-10 10:50:03', '1', '2017-10-10 14:56:39', null, '1');
INSERT INTO `sys_menu` VALUES ('982', '3', '0,1,2,3,', 'app系统版本管理', '/admin/app_system_version.htm', null, null, '30', '1', '0', '', '1', '2017-10-10 11:33:11', '1', '2017-10-10 14:56:39', null, '1');
INSERT INTO `sys_menu` VALUES ('983', '30', '0,1,30,', 'app系统版本管理', '', null, null, '30', '1', '0', '', '1', '2017-10-10 14:57:58', '1', '2017-10-10 14:57:58', null, '1');
INSERT INTO `sys_menu` VALUES ('984', '983', '0,1,30,983,', '增加app新版', '/admin/app_system_version_add.htm', null, null, '30', '0', '0', 'set_version_add', '1', '2017-10-12 09:26:43', '1', '2017-10-12 09:26:43', null, '1');
INSERT INTO `sys_menu` VALUES ('985', '983', '0,1,30,983,', '查看版本信息', '/admin/app_system_version_list.htm', null, null, '30', '1', '0', 'set_version_list', '1', '2017-10-12 10:06:51', '1', '2017-10-12 10:06:51', null, '1');
INSERT INTO `sys_menu` VALUES ('986', '984', '0,1,30,983,984,', '按增app版本保存', '/admin/app_system_version_save.htm', null, null, '30', '0', '0', 'set_version_save', '1', '2017-10-12 10:45:57', '1', '2017-10-12 10:45:57', null, '1');
INSERT INTO `sys_menu` VALUES ('987', '983', '0,1,30,983,', '编辑app新版本', '/admin/app_system_version_edit.htm', null, null, '30', '0', '2', 'set_version_edit', '1', '2017-10-11 10:23:53', '1', '2017-10-11 10:23:53', null, '1');
INSERT INTO `sys_menu` VALUES ('988', '983', '0,1,30,983,', '删除app新版本', '/admin/app_system_version_del.htm', null, null, '30', '0', '2', 'set_version_del', '1', '2017-10-11 11:22:19', '1', '2017-10-11 11:22:19', null, '1');
INSERT INTO `sys_menu` VALUES ('989', '923', '0,1,27,923,', '退货退款操作', '/admin/goods_return_refund_view.htm', null, null, '30', '1', '2', 'goods_return_refund_view', '1', '2017-10-27 15:55:30', '1', '2017-10-27 15:55:30', null, '1');
INSERT INTO `sys_menu` VALUES ('990', '943', '0,1,27,943,', '结算单确认已付款列表', '/admin/settlement_complete.htm', null, null, '4', '1', '0', 'settlement_complete', '1', '2017-10-27 16:19:41', '1', '2017-10-27 16:19:41', null, '1');
INSERT INTO `sys_menu` VALUES ('991', '990', '0,1,27,943,990,', '结算单确认已付款', '/admin/settlement_complete_confirm.htm', null, null, '1', '1', '2', 'settlement_complete_confirm', '1', '2017-10-28 11:58:02', '1', '2017-10-28 11:58:02', null, '1');
INSERT INTO `sys_menu` VALUES ('992', '927', '0,1,27,923,927,', '订单取消退款操作', '/admin/order_cancel_dorefund.htm', null, null, '30', '1', '2', 'order_cancel_dorefund', '1', '2017-10-28 18:22:30', '1', '2017-10-28 18:22:30', null, '1');
INSERT INTO `sys_menu` VALUES ('993', '989', '0,1,27,923,989,', '根据退货进行退款操作', '/admin/goods_return_dorefund.htm', null, null, '30', '1', '2', 'goods_return_dorefund', '1', '2017-10-28 17:48:24', '1', '2017-10-28 17:48:24', null, '1');
INSERT INTO `sys_menu` VALUES ('994', '1', '0,1,', '申请与审核', '', null, null, '110', '1', '0', '', '1', '2017-11-22 17:17:31', '1', '2017-11-22 17:17:31', null, '1');
INSERT INTO `sys_menu` VALUES ('995', '994', '0,1,994,', '申请信息', '/admin/store_apply_list.htm', null, null, '1', '1', '0', 'store_apply_list', '1', '2017-11-24 14:46:31', '1', '2017-11-24 14:46:31', null, '1');
INSERT INTO `sys_menu` VALUES ('996', '994', '0,1,994,', '审核信息', '/admin/store_audit_list.htm', null, null, '2', '1', '0', 'store_audit_list', '1', '2017-11-28 09:11:12', '1', '2017-11-28 09:11:12', null, '1');
INSERT INTO `sys_menu` VALUES ('997', '995', '0,1,994,995,', '申请详情', '/admin/store_apply_detail.htm', null, null, '1', '1', '0', 'store_apply_detail', '1', '2017-11-24 15:22:23', '1', '2017-11-24 15:22:23', null, '1');
INSERT INTO `sys_menu` VALUES ('998', '995', '0,1,994,995,', '审核申请信息', '/admin/store_create_edit.htm', null, null, '1', '0', '1', 'store_create_edit', '1', '2017-12-01 09:28:13', '1', '2017-12-01 09:28:13', null, '1');
INSERT INTO `sys_menu` VALUES ('999', '996', '0,1,994,996,', '审核信息保存', '/admin/store_audit_save.htm', null, null, '30', '0', '1', 'store_audit_save', '1', '2017-11-27 17:51:25', '1', '2017-11-28 09:11:12', null, '1');
INSERT INTO `sys_menu` VALUES ('1000', '995', '0,1,994,995,', '认证申请', '/admin/store_attestation_edit.htm', null, null, '2', '0', '0', 'store_attestation_edit', '1', '2017-12-01 09:50:27', '1', '2017-12-01 09:50:27', null, '1');
INSERT INTO `sys_menu` VALUES ('1001', '996', '0,1,994,996,', '认证审核信息保存', '/admin/store_attestation_save.htm', null, null, '30', '0', '0', 'store_attestation_save', '1', '2017-12-01 15:31:38', '1', '2017-12-01 15:31:38', null, '1');
INSERT INTO `sys_menu` VALUES ('1002', '866', '0,1,25,866,', '开店审核', '/admin/create_store_audit.htm', null, null, '30', '0', '2', 'create_store_audit', '1', '2017-12-18 10:39:16', '1', '2017-12-18 10:39:16', null, '1');
INSERT INTO `sys_menu` VALUES ('1003', '866', '0,1,25,866,', '开店审核保存', '/admin/create_store_audit_save.htm', null, null, '30', '0', '2', 'create_store_audit_save', '1', '2017-12-18 10:39:45', '1', '2017-12-18 10:39:45', null, '1');
INSERT INTO `sys_menu` VALUES ('1004', '866', '0,1,25,866,', '开店申请列表', '/admin/store_apply_list.htm', null, null, '30', '0', '2', 'store_apply_list', '1', '2017-12-18 10:42:44', '1', '2017-12-18 10:42:44', null, '1');
INSERT INTO `sys_menu` VALUES ('1005', '866', '0,1,25,866,', '审核列表', '/admin/store_audit_list.htm', null, null, '30', '0', '2', 'store_audit_list', '1', '2017-12-18 14:36:44', '1', '2017-12-18 14:36:44', null, '1');
INSERT INTO `sys_menu` VALUES ('1006', '866', '0,1,25,866,', '查看申请资料详情', '/admin/store_apply_detail.htm', null, null, '30', '0', '2', 'store_apply_detail', '1', '2017-12-18 15:57:47', '1', '2017-12-18 15:57:47', null, '1');
INSERT INTO `sys_menu` VALUES ('1007', '866', '0,1,25,866,', '店铺信息变更列表', '/admin/store_information_change_list.htm', null, null, '30', '0', '2', 'store_information_change_list', '1', '2017-12-29 16:23:07', '1', '2017-12-29 16:23:07', null, '1');
INSERT INTO `sys_menu` VALUES ('1008', '25', '0,1,25,', '店铺信息变更审核', '/admin/store_message_change_audit.htm', null, null, '30', '0', '2', 'store_message_change_audit', '1', '2017-12-20 14:43:23', '1', '2017-12-20 14:43:23', null, '1');
INSERT INTO `sys_menu` VALUES ('1009', '866', '0,1,25,866,', '店铺信息变更审核保存', '/admin/store_information_change_audit_save.htm', null, null, '30', '0', '2', 'store_information_change_audit_save', '1', '2017-12-29 16:23:41', '1', '2017-12-29 16:23:41', null, '1');
INSERT INTO `sys_menu` VALUES ('1010', '29', '0,1,29,', '水印管理', '', null, null, '10', '1', '0', '', '1', '2017-12-22 14:54:23', '1', '2017-12-22 14:54:23', null, '1');
INSERT INTO `sys_menu` VALUES ('1011', '1010', '0,1,29,1010,', '水印设置', '/admin/watermark.htm', null, null, '1', '1', '0', 'watermark', '1', '2017-12-22 15:16:17', '1', '2017-12-22 15:16:17', null, '1');
INSERT INTO `sys_menu` VALUES ('1012', '1011', '0,1,29,1010,1011,', '水印保存', '/admin/watermark_save.htm', null, null, '1', '1', '2', 'watermark_save', '1', '2017-12-22 14:57:59', '1', '2017-12-22 15:16:17', null, '1');
INSERT INTO `sys_menu` VALUES ('1013', '866', '0,1,25,866,', '店铺信息变更申请详情', '/admin/store_information_change_detail.htm', null, null, '30', '0', '2', 'store_information_change_detail', '1', '2017-12-29 16:46:45', '1', '2017-12-29 16:46:45', null, '1');
INSERT INTO `sys_menu` VALUES ('1014', '866', '0,1,25,866,', '店铺信息变更审核', '/admin/store_information_change_audit.htm', null, null, '30', '0', '2', 'store_information_change_audit', '1', '2017-12-29 16:51:22', '1', '2017-12-29 16:51:22', null, '1');

-- ----------------------------
-- Table structure for sys_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_office`;
CREATE TABLE `sys_office` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` bigint(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `area_id` bigint(64) NOT NULL COMMENT '归属区域',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `name` varchar(100) NOT NULL COMMENT '机构名称',
  `type` char(1) NOT NULL COMMENT '机构类型',
  `grade` char(1) NOT NULL COMMENT '机构等级',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `master` varchar(100) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `fax` varchar(200) DEFAULT NULL COMMENT '传真',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_office_parent_id` (`parent_id`),
  KEY `sys_office_parent_ids` (`parent_ids`(255)),
  KEY `sys_office_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of sys_office
-- ----------------------------
INSERT INTO `sys_office` VALUES ('1', '0', '0,', '4524432', '100000', '采料网', '1', '1', null, null, null, null, null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_office` VALUES ('2', '1', '0,1,', '4524432', '200000', '海南采料科技有限公司', '1', '1', '', '', '', '', '', '', '1', '2015-12-05 08:51:53', '1', '2015-12-05 08:51:53', '运营公司', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `office_id` bigint(64) DEFAULT NULL COMMENT '归属机构',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `data_scope` char(1) DEFAULT NULL COMMENT '数据范围',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '1', '系统管理员', '1', '1', '2017-08-30 19:12:12', '1', '2017-08-30 19:12:12', null, '0');
INSERT INTO `sys_role` VALUES ('2', '2', '运营人员', '9', '1', '2015-12-18 11:25:59', '1', '2015-12-18 11:25:59', null, '1');
INSERT INTO `sys_role` VALUES ('3', '2', '结算申请人员', '9', '1', '2017-08-04 16:26:15', '1', '2017-08-04 16:26:15', null, '1');
INSERT INTO `sys_role` VALUES ('4', '2', '结算审核人员', '9', '1', '2017-08-04 15:01:08', '1', '2017-08-04 15:01:08', null, '1');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(64) NOT NULL COMMENT '角色编号',
  `menu_id` bigint(64) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '6');
INSERT INTO `sys_role_menu` VALUES ('1', '7');
INSERT INTO `sys_role_menu` VALUES ('1', '8');
INSERT INTO `sys_role_menu` VALUES ('1', '9');
INSERT INTO `sys_role_menu` VALUES ('1', '10');
INSERT INTO `sys_role_menu` VALUES ('1', '11');
INSERT INTO `sys_role_menu` VALUES ('1', '12');
INSERT INTO `sys_role_menu` VALUES ('1', '13');
INSERT INTO `sys_role_menu` VALUES ('1', '17');
INSERT INTO `sys_role_menu` VALUES ('1', '18');
INSERT INTO `sys_role_menu` VALUES ('1', '19');
INSERT INTO `sys_role_menu` VALUES ('1', '20');
INSERT INTO `sys_role_menu` VALUES ('1', '21');
INSERT INTO `sys_role_menu` VALUES ('1', '22');
INSERT INTO `sys_role_menu` VALUES ('1', '23');
INSERT INTO `sys_role_menu` VALUES ('1', '24');
INSERT INTO `sys_role_menu` VALUES ('1', '25');
INSERT INTO `sys_role_menu` VALUES ('1', '26');
INSERT INTO `sys_role_menu` VALUES ('1', '27');
INSERT INTO `sys_role_menu` VALUES ('1', '28');
INSERT INTO `sys_role_menu` VALUES ('1', '29');
INSERT INTO `sys_role_menu` VALUES ('1', '30');
INSERT INTO `sys_role_menu` VALUES ('1', '508');
INSERT INTO `sys_role_menu` VALUES ('1', '509');
INSERT INTO `sys_role_menu` VALUES ('1', '513');
INSERT INTO `sys_role_menu` VALUES ('1', '514');
INSERT INTO `sys_role_menu` VALUES ('1', '515');
INSERT INTO `sys_role_menu` VALUES ('1', '517');
INSERT INTO `sys_role_menu` VALUES ('1', '518');
INSERT INTO `sys_role_menu` VALUES ('1', '519');
INSERT INTO `sys_role_menu` VALUES ('1', '520');
INSERT INTO `sys_role_menu` VALUES ('1', '521');
INSERT INTO `sys_role_menu` VALUES ('1', '522');
INSERT INTO `sys_role_menu` VALUES ('1', '523');
INSERT INTO `sys_role_menu` VALUES ('1', '524');
INSERT INTO `sys_role_menu` VALUES ('1', '525');
INSERT INTO `sys_role_menu` VALUES ('1', '526');
INSERT INTO `sys_role_menu` VALUES ('1', '535');
INSERT INTO `sys_role_menu` VALUES ('1', '536');
INSERT INTO `sys_role_menu` VALUES ('1', '537');
INSERT INTO `sys_role_menu` VALUES ('1', '538');
INSERT INTO `sys_role_menu` VALUES ('1', '539');
INSERT INTO `sys_role_menu` VALUES ('1', '540');
INSERT INTO `sys_role_menu` VALUES ('1', '541');
INSERT INTO `sys_role_menu` VALUES ('1', '542');
INSERT INTO `sys_role_menu` VALUES ('1', '543');
INSERT INTO `sys_role_menu` VALUES ('1', '544');
INSERT INTO `sys_role_menu` VALUES ('1', '545');
INSERT INTO `sys_role_menu` VALUES ('1', '546');
INSERT INTO `sys_role_menu` VALUES ('1', '547');
INSERT INTO `sys_role_menu` VALUES ('1', '548');
INSERT INTO `sys_role_menu` VALUES ('1', '549');
INSERT INTO `sys_role_menu` VALUES ('1', '550');
INSERT INTO `sys_role_menu` VALUES ('1', '551');
INSERT INTO `sys_role_menu` VALUES ('1', '552');
INSERT INTO `sys_role_menu` VALUES ('1', '553');
INSERT INTO `sys_role_menu` VALUES ('1', '554');
INSERT INTO `sys_role_menu` VALUES ('1', '555');
INSERT INTO `sys_role_menu` VALUES ('1', '556');
INSERT INTO `sys_role_menu` VALUES ('1', '557');
INSERT INTO `sys_role_menu` VALUES ('1', '558');
INSERT INTO `sys_role_menu` VALUES ('1', '559');
INSERT INTO `sys_role_menu` VALUES ('1', '560');
INSERT INTO `sys_role_menu` VALUES ('1', '561');
INSERT INTO `sys_role_menu` VALUES ('1', '562');
INSERT INTO `sys_role_menu` VALUES ('1', '563');
INSERT INTO `sys_role_menu` VALUES ('1', '564');
INSERT INTO `sys_role_menu` VALUES ('1', '565');
INSERT INTO `sys_role_menu` VALUES ('1', '566');
INSERT INTO `sys_role_menu` VALUES ('1', '567');
INSERT INTO `sys_role_menu` VALUES ('1', '568');
INSERT INTO `sys_role_menu` VALUES ('1', '569');
INSERT INTO `sys_role_menu` VALUES ('1', '570');
INSERT INTO `sys_role_menu` VALUES ('1', '571');
INSERT INTO `sys_role_menu` VALUES ('1', '572');
INSERT INTO `sys_role_menu` VALUES ('1', '573');
INSERT INTO `sys_role_menu` VALUES ('1', '574');
INSERT INTO `sys_role_menu` VALUES ('1', '575');
INSERT INTO `sys_role_menu` VALUES ('1', '576');
INSERT INTO `sys_role_menu` VALUES ('1', '577');
INSERT INTO `sys_role_menu` VALUES ('1', '581');
INSERT INTO `sys_role_menu` VALUES ('1', '582');
INSERT INTO `sys_role_menu` VALUES ('1', '585');
INSERT INTO `sys_role_menu` VALUES ('1', '587');
INSERT INTO `sys_role_menu` VALUES ('1', '593');
INSERT INTO `sys_role_menu` VALUES ('1', '594');
INSERT INTO `sys_role_menu` VALUES ('1', '595');
INSERT INTO `sys_role_menu` VALUES ('1', '596');
INSERT INTO `sys_role_menu` VALUES ('1', '597');
INSERT INTO `sys_role_menu` VALUES ('1', '598');
INSERT INTO `sys_role_menu` VALUES ('1', '599');
INSERT INTO `sys_role_menu` VALUES ('1', '600');
INSERT INTO `sys_role_menu` VALUES ('1', '601');
INSERT INTO `sys_role_menu` VALUES ('1', '602');
INSERT INTO `sys_role_menu` VALUES ('1', '603');
INSERT INTO `sys_role_menu` VALUES ('1', '604');
INSERT INTO `sys_role_menu` VALUES ('1', '605');
INSERT INTO `sys_role_menu` VALUES ('1', '606');
INSERT INTO `sys_role_menu` VALUES ('1', '607');
INSERT INTO `sys_role_menu` VALUES ('1', '608');
INSERT INTO `sys_role_menu` VALUES ('1', '609');
INSERT INTO `sys_role_menu` VALUES ('1', '610');
INSERT INTO `sys_role_menu` VALUES ('1', '611');
INSERT INTO `sys_role_menu` VALUES ('1', '612');
INSERT INTO `sys_role_menu` VALUES ('1', '613');
INSERT INTO `sys_role_menu` VALUES ('1', '614');
INSERT INTO `sys_role_menu` VALUES ('1', '615');
INSERT INTO `sys_role_menu` VALUES ('1', '616');
INSERT INTO `sys_role_menu` VALUES ('1', '617');
INSERT INTO `sys_role_menu` VALUES ('1', '618');
INSERT INTO `sys_role_menu` VALUES ('1', '619');
INSERT INTO `sys_role_menu` VALUES ('1', '620');
INSERT INTO `sys_role_menu` VALUES ('1', '621');
INSERT INTO `sys_role_menu` VALUES ('1', '622');
INSERT INTO `sys_role_menu` VALUES ('1', '623');
INSERT INTO `sys_role_menu` VALUES ('1', '624');
INSERT INTO `sys_role_menu` VALUES ('1', '625');
INSERT INTO `sys_role_menu` VALUES ('1', '626');
INSERT INTO `sys_role_menu` VALUES ('1', '627');
INSERT INTO `sys_role_menu` VALUES ('1', '628');
INSERT INTO `sys_role_menu` VALUES ('1', '629');
INSERT INTO `sys_role_menu` VALUES ('1', '630');
INSERT INTO `sys_role_menu` VALUES ('1', '631');
INSERT INTO `sys_role_menu` VALUES ('1', '632');
INSERT INTO `sys_role_menu` VALUES ('1', '633');
INSERT INTO `sys_role_menu` VALUES ('1', '634');
INSERT INTO `sys_role_menu` VALUES ('1', '635');
INSERT INTO `sys_role_menu` VALUES ('1', '636');
INSERT INTO `sys_role_menu` VALUES ('1', '637');
INSERT INTO `sys_role_menu` VALUES ('1', '638');
INSERT INTO `sys_role_menu` VALUES ('1', '639');
INSERT INTO `sys_role_menu` VALUES ('1', '640');
INSERT INTO `sys_role_menu` VALUES ('1', '641');
INSERT INTO `sys_role_menu` VALUES ('1', '642');
INSERT INTO `sys_role_menu` VALUES ('1', '643');
INSERT INTO `sys_role_menu` VALUES ('1', '644');
INSERT INTO `sys_role_menu` VALUES ('1', '645');
INSERT INTO `sys_role_menu` VALUES ('1', '646');
INSERT INTO `sys_role_menu` VALUES ('1', '647');
INSERT INTO `sys_role_menu` VALUES ('1', '648');
INSERT INTO `sys_role_menu` VALUES ('1', '649');
INSERT INTO `sys_role_menu` VALUES ('1', '650');
INSERT INTO `sys_role_menu` VALUES ('1', '651');
INSERT INTO `sys_role_menu` VALUES ('1', '652');
INSERT INTO `sys_role_menu` VALUES ('1', '653');
INSERT INTO `sys_role_menu` VALUES ('1', '654');
INSERT INTO `sys_role_menu` VALUES ('1', '655');
INSERT INTO `sys_role_menu` VALUES ('1', '656');
INSERT INTO `sys_role_menu` VALUES ('1', '657');
INSERT INTO `sys_role_menu` VALUES ('1', '658');
INSERT INTO `sys_role_menu` VALUES ('1', '659');
INSERT INTO `sys_role_menu` VALUES ('1', '660');
INSERT INTO `sys_role_menu` VALUES ('1', '661');
INSERT INTO `sys_role_menu` VALUES ('1', '662');
INSERT INTO `sys_role_menu` VALUES ('1', '663');
INSERT INTO `sys_role_menu` VALUES ('1', '664');
INSERT INTO `sys_role_menu` VALUES ('1', '665');
INSERT INTO `sys_role_menu` VALUES ('1', '666');
INSERT INTO `sys_role_menu` VALUES ('1', '667');
INSERT INTO `sys_role_menu` VALUES ('1', '668');
INSERT INTO `sys_role_menu` VALUES ('1', '669');
INSERT INTO `sys_role_menu` VALUES ('1', '670');
INSERT INTO `sys_role_menu` VALUES ('1', '671');
INSERT INTO `sys_role_menu` VALUES ('1', '672');
INSERT INTO `sys_role_menu` VALUES ('1', '673');
INSERT INTO `sys_role_menu` VALUES ('1', '674');
INSERT INTO `sys_role_menu` VALUES ('1', '676');
INSERT INTO `sys_role_menu` VALUES ('1', '677');
INSERT INTO `sys_role_menu` VALUES ('1', '678');
INSERT INTO `sys_role_menu` VALUES ('1', '679');
INSERT INTO `sys_role_menu` VALUES ('1', '680');
INSERT INTO `sys_role_menu` VALUES ('1', '681');
INSERT INTO `sys_role_menu` VALUES ('1', '682');
INSERT INTO `sys_role_menu` VALUES ('1', '683');
INSERT INTO `sys_role_menu` VALUES ('1', '684');
INSERT INTO `sys_role_menu` VALUES ('1', '685');
INSERT INTO `sys_role_menu` VALUES ('1', '686');
INSERT INTO `sys_role_menu` VALUES ('1', '687');
INSERT INTO `sys_role_menu` VALUES ('1', '688');
INSERT INTO `sys_role_menu` VALUES ('1', '689');
INSERT INTO `sys_role_menu` VALUES ('1', '690');
INSERT INTO `sys_role_menu` VALUES ('1', '691');
INSERT INTO `sys_role_menu` VALUES ('1', '692');
INSERT INTO `sys_role_menu` VALUES ('1', '693');
INSERT INTO `sys_role_menu` VALUES ('1', '694');
INSERT INTO `sys_role_menu` VALUES ('1', '695');
INSERT INTO `sys_role_menu` VALUES ('1', '696');
INSERT INTO `sys_role_menu` VALUES ('1', '697');
INSERT INTO `sys_role_menu` VALUES ('1', '698');
INSERT INTO `sys_role_menu` VALUES ('1', '699');
INSERT INTO `sys_role_menu` VALUES ('1', '700');
INSERT INTO `sys_role_menu` VALUES ('1', '701');
INSERT INTO `sys_role_menu` VALUES ('1', '702');
INSERT INTO `sys_role_menu` VALUES ('1', '703');
INSERT INTO `sys_role_menu` VALUES ('1', '704');
INSERT INTO `sys_role_menu` VALUES ('1', '705');
INSERT INTO `sys_role_menu` VALUES ('1', '706');
INSERT INTO `sys_role_menu` VALUES ('1', '707');
INSERT INTO `sys_role_menu` VALUES ('1', '708');
INSERT INTO `sys_role_menu` VALUES ('1', '709');
INSERT INTO `sys_role_menu` VALUES ('1', '710');
INSERT INTO `sys_role_menu` VALUES ('1', '711');
INSERT INTO `sys_role_menu` VALUES ('1', '712');
INSERT INTO `sys_role_menu` VALUES ('1', '713');
INSERT INTO `sys_role_menu` VALUES ('1', '714');
INSERT INTO `sys_role_menu` VALUES ('1', '715');
INSERT INTO `sys_role_menu` VALUES ('1', '716');
INSERT INTO `sys_role_menu` VALUES ('1', '717');
INSERT INTO `sys_role_menu` VALUES ('1', '718');
INSERT INTO `sys_role_menu` VALUES ('1', '719');
INSERT INTO `sys_role_menu` VALUES ('1', '720');
INSERT INTO `sys_role_menu` VALUES ('1', '721');
INSERT INTO `sys_role_menu` VALUES ('1', '722');
INSERT INTO `sys_role_menu` VALUES ('1', '723');
INSERT INTO `sys_role_menu` VALUES ('1', '725');
INSERT INTO `sys_role_menu` VALUES ('1', '726');
INSERT INTO `sys_role_menu` VALUES ('1', '727');
INSERT INTO `sys_role_menu` VALUES ('1', '728');
INSERT INTO `sys_role_menu` VALUES ('1', '729');
INSERT INTO `sys_role_menu` VALUES ('1', '730');
INSERT INTO `sys_role_menu` VALUES ('1', '732');
INSERT INTO `sys_role_menu` VALUES ('1', '733');
INSERT INTO `sys_role_menu` VALUES ('1', '734');
INSERT INTO `sys_role_menu` VALUES ('1', '735');
INSERT INTO `sys_role_menu` VALUES ('1', '736');
INSERT INTO `sys_role_menu` VALUES ('1', '737');
INSERT INTO `sys_role_menu` VALUES ('1', '738');
INSERT INTO `sys_role_menu` VALUES ('1', '739');
INSERT INTO `sys_role_menu` VALUES ('1', '740');
INSERT INTO `sys_role_menu` VALUES ('1', '741');
INSERT INTO `sys_role_menu` VALUES ('1', '742');
INSERT INTO `sys_role_menu` VALUES ('1', '743');
INSERT INTO `sys_role_menu` VALUES ('1', '744');
INSERT INTO `sys_role_menu` VALUES ('1', '745');
INSERT INTO `sys_role_menu` VALUES ('1', '746');
INSERT INTO `sys_role_menu` VALUES ('1', '747');
INSERT INTO `sys_role_menu` VALUES ('1', '748');
INSERT INTO `sys_role_menu` VALUES ('1', '749');
INSERT INTO `sys_role_menu` VALUES ('1', '750');
INSERT INTO `sys_role_menu` VALUES ('1', '751');
INSERT INTO `sys_role_menu` VALUES ('1', '752');
INSERT INTO `sys_role_menu` VALUES ('1', '753');
INSERT INTO `sys_role_menu` VALUES ('1', '754');
INSERT INTO `sys_role_menu` VALUES ('1', '755');
INSERT INTO `sys_role_menu` VALUES ('1', '756');
INSERT INTO `sys_role_menu` VALUES ('1', '757');
INSERT INTO `sys_role_menu` VALUES ('1', '758');
INSERT INTO `sys_role_menu` VALUES ('1', '759');
INSERT INTO `sys_role_menu` VALUES ('1', '760');
INSERT INTO `sys_role_menu` VALUES ('1', '761');
INSERT INTO `sys_role_menu` VALUES ('1', '762');
INSERT INTO `sys_role_menu` VALUES ('1', '763');
INSERT INTO `sys_role_menu` VALUES ('1', '764');
INSERT INTO `sys_role_menu` VALUES ('1', '765');
INSERT INTO `sys_role_menu` VALUES ('1', '766');
INSERT INTO `sys_role_menu` VALUES ('1', '767');
INSERT INTO `sys_role_menu` VALUES ('1', '768');
INSERT INTO `sys_role_menu` VALUES ('1', '769');
INSERT INTO `sys_role_menu` VALUES ('1', '770');
INSERT INTO `sys_role_menu` VALUES ('1', '771');
INSERT INTO `sys_role_menu` VALUES ('1', '772');
INSERT INTO `sys_role_menu` VALUES ('1', '773');
INSERT INTO `sys_role_menu` VALUES ('1', '774');
INSERT INTO `sys_role_menu` VALUES ('1', '775');
INSERT INTO `sys_role_menu` VALUES ('1', '776');
INSERT INTO `sys_role_menu` VALUES ('1', '777');
INSERT INTO `sys_role_menu` VALUES ('1', '778');
INSERT INTO `sys_role_menu` VALUES ('1', '779');
INSERT INTO `sys_role_menu` VALUES ('1', '780');
INSERT INTO `sys_role_menu` VALUES ('1', '781');
INSERT INTO `sys_role_menu` VALUES ('1', '782');
INSERT INTO `sys_role_menu` VALUES ('1', '783');
INSERT INTO `sys_role_menu` VALUES ('1', '784');
INSERT INTO `sys_role_menu` VALUES ('1', '785');
INSERT INTO `sys_role_menu` VALUES ('1', '786');
INSERT INTO `sys_role_menu` VALUES ('1', '787');
INSERT INTO `sys_role_menu` VALUES ('1', '788');
INSERT INTO `sys_role_menu` VALUES ('1', '789');
INSERT INTO `sys_role_menu` VALUES ('1', '790');
INSERT INTO `sys_role_menu` VALUES ('1', '791');
INSERT INTO `sys_role_menu` VALUES ('1', '792');
INSERT INTO `sys_role_menu` VALUES ('1', '793');
INSERT INTO `sys_role_menu` VALUES ('1', '794');
INSERT INTO `sys_role_menu` VALUES ('1', '795');
INSERT INTO `sys_role_menu` VALUES ('1', '796');
INSERT INTO `sys_role_menu` VALUES ('1', '797');
INSERT INTO `sys_role_menu` VALUES ('1', '798');
INSERT INTO `sys_role_menu` VALUES ('1', '799');
INSERT INTO `sys_role_menu` VALUES ('1', '800');
INSERT INTO `sys_role_menu` VALUES ('1', '801');
INSERT INTO `sys_role_menu` VALUES ('1', '802');
INSERT INTO `sys_role_menu` VALUES ('1', '803');
INSERT INTO `sys_role_menu` VALUES ('1', '805');
INSERT INTO `sys_role_menu` VALUES ('1', '806');
INSERT INTO `sys_role_menu` VALUES ('1', '807');
INSERT INTO `sys_role_menu` VALUES ('1', '808');
INSERT INTO `sys_role_menu` VALUES ('1', '809');
INSERT INTO `sys_role_menu` VALUES ('1', '810');
INSERT INTO `sys_role_menu` VALUES ('1', '811');
INSERT INTO `sys_role_menu` VALUES ('1', '812');
INSERT INTO `sys_role_menu` VALUES ('1', '813');
INSERT INTO `sys_role_menu` VALUES ('1', '814');
INSERT INTO `sys_role_menu` VALUES ('1', '815');
INSERT INTO `sys_role_menu` VALUES ('1', '816');
INSERT INTO `sys_role_menu` VALUES ('1', '817');
INSERT INTO `sys_role_menu` VALUES ('1', '818');
INSERT INTO `sys_role_menu` VALUES ('1', '819');
INSERT INTO `sys_role_menu` VALUES ('1', '820');
INSERT INTO `sys_role_menu` VALUES ('1', '821');
INSERT INTO `sys_role_menu` VALUES ('1', '822');
INSERT INTO `sys_role_menu` VALUES ('1', '823');
INSERT INTO `sys_role_menu` VALUES ('1', '824');
INSERT INTO `sys_role_menu` VALUES ('1', '825');
INSERT INTO `sys_role_menu` VALUES ('1', '826');
INSERT INTO `sys_role_menu` VALUES ('1', '827');
INSERT INTO `sys_role_menu` VALUES ('1', '851');
INSERT INTO `sys_role_menu` VALUES ('1', '852');
INSERT INTO `sys_role_menu` VALUES ('1', '853');
INSERT INTO `sys_role_menu` VALUES ('1', '854');
INSERT INTO `sys_role_menu` VALUES ('1', '855');
INSERT INTO `sys_role_menu` VALUES ('1', '856');
INSERT INTO `sys_role_menu` VALUES ('1', '857');
INSERT INTO `sys_role_menu` VALUES ('1', '858');
INSERT INTO `sys_role_menu` VALUES ('1', '859');
INSERT INTO `sys_role_menu` VALUES ('1', '860');
INSERT INTO `sys_role_menu` VALUES ('1', '861');
INSERT INTO `sys_role_menu` VALUES ('1', '862');
INSERT INTO `sys_role_menu` VALUES ('1', '863');
INSERT INTO `sys_role_menu` VALUES ('1', '864');
INSERT INTO `sys_role_menu` VALUES ('1', '865');
INSERT INTO `sys_role_menu` VALUES ('1', '866');
INSERT INTO `sys_role_menu` VALUES ('1', '867');
INSERT INTO `sys_role_menu` VALUES ('1', '868');
INSERT INTO `sys_role_menu` VALUES ('1', '869');
INSERT INTO `sys_role_menu` VALUES ('1', '870');
INSERT INTO `sys_role_menu` VALUES ('1', '871');
INSERT INTO `sys_role_menu` VALUES ('1', '872');
INSERT INTO `sys_role_menu` VALUES ('1', '873');
INSERT INTO `sys_role_menu` VALUES ('1', '874');
INSERT INTO `sys_role_menu` VALUES ('1', '875');
INSERT INTO `sys_role_menu` VALUES ('1', '876');
INSERT INTO `sys_role_menu` VALUES ('1', '877');
INSERT INTO `sys_role_menu` VALUES ('1', '878');
INSERT INTO `sys_role_menu` VALUES ('1', '879');
INSERT INTO `sys_role_menu` VALUES ('1', '880');
INSERT INTO `sys_role_menu` VALUES ('1', '881');
INSERT INTO `sys_role_menu` VALUES ('1', '882');
INSERT INTO `sys_role_menu` VALUES ('1', '883');
INSERT INTO `sys_role_menu` VALUES ('1', '884');
INSERT INTO `sys_role_menu` VALUES ('1', '885');
INSERT INTO `sys_role_menu` VALUES ('1', '887');
INSERT INTO `sys_role_menu` VALUES ('1', '888');
INSERT INTO `sys_role_menu` VALUES ('1', '889');
INSERT INTO `sys_role_menu` VALUES ('1', '890');
INSERT INTO `sys_role_menu` VALUES ('1', '891');
INSERT INTO `sys_role_menu` VALUES ('1', '892');
INSERT INTO `sys_role_menu` VALUES ('1', '893');
INSERT INTO `sys_role_menu` VALUES ('1', '894');
INSERT INTO `sys_role_menu` VALUES ('1', '895');
INSERT INTO `sys_role_menu` VALUES ('1', '896');
INSERT INTO `sys_role_menu` VALUES ('1', '897');
INSERT INTO `sys_role_menu` VALUES ('1', '898');
INSERT INTO `sys_role_menu` VALUES ('1', '899');
INSERT INTO `sys_role_menu` VALUES ('1', '900');
INSERT INTO `sys_role_menu` VALUES ('1', '901');
INSERT INTO `sys_role_menu` VALUES ('1', '902');
INSERT INTO `sys_role_menu` VALUES ('1', '903');
INSERT INTO `sys_role_menu` VALUES ('1', '904');
INSERT INTO `sys_role_menu` VALUES ('1', '905');
INSERT INTO `sys_role_menu` VALUES ('1', '906');
INSERT INTO `sys_role_menu` VALUES ('1', '907');
INSERT INTO `sys_role_menu` VALUES ('1', '908');
INSERT INTO `sys_role_menu` VALUES ('1', '909');
INSERT INTO `sys_role_menu` VALUES ('1', '910');
INSERT INTO `sys_role_menu` VALUES ('1', '911');
INSERT INTO `sys_role_menu` VALUES ('1', '912');
INSERT INTO `sys_role_menu` VALUES ('1', '913');
INSERT INTO `sys_role_menu` VALUES ('1', '914');
INSERT INTO `sys_role_menu` VALUES ('1', '915');
INSERT INTO `sys_role_menu` VALUES ('1', '916');
INSERT INTO `sys_role_menu` VALUES ('1', '917');
INSERT INTO `sys_role_menu` VALUES ('1', '918');
INSERT INTO `sys_role_menu` VALUES ('1', '919');
INSERT INTO `sys_role_menu` VALUES ('1', '920');
INSERT INTO `sys_role_menu` VALUES ('1', '921');
INSERT INTO `sys_role_menu` VALUES ('1', '922');
INSERT INTO `sys_role_menu` VALUES ('1', '923');
INSERT INTO `sys_role_menu` VALUES ('1', '978');
INSERT INTO `sys_role_menu` VALUES ('1', '979');
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '13');
INSERT INTO `sys_role_menu` VALUES ('2', '17');
INSERT INTO `sys_role_menu` VALUES ('2', '18');
INSERT INTO `sys_role_menu` VALUES ('2', '19');
INSERT INTO `sys_role_menu` VALUES ('2', '23');
INSERT INTO `sys_role_menu` VALUES ('2', '548');
INSERT INTO `sys_role_menu` VALUES ('2', '549');
INSERT INTO `sys_role_menu` VALUES ('2', '550');
INSERT INTO `sys_role_menu` VALUES ('2', '551');
INSERT INTO `sys_role_menu` VALUES ('2', '552');
INSERT INTO `sys_role_menu` VALUES ('2', '645');
INSERT INTO `sys_role_menu` VALUES ('2', '646');
INSERT INTO `sys_role_menu` VALUES ('2', '647');
INSERT INTO `sys_role_menu` VALUES ('2', '648');
INSERT INTO `sys_role_menu` VALUES ('2', '649');
INSERT INTO `sys_role_menu` VALUES ('2', '650');
INSERT INTO `sys_role_menu` VALUES ('2', '651');
INSERT INTO `sys_role_menu` VALUES ('2', '652');
INSERT INTO `sys_role_menu` VALUES ('2', '882');
INSERT INTO `sys_role_menu` VALUES ('2', '883');
INSERT INTO `sys_role_menu` VALUES ('2', '884');
INSERT INTO `sys_role_menu` VALUES ('2', '885');
INSERT INTO `sys_role_menu` VALUES ('2', '887');
INSERT INTO `sys_role_menu` VALUES ('2', '888');
INSERT INTO `sys_role_menu` VALUES ('2', '889');
INSERT INTO `sys_role_menu` VALUES ('2', '890');
INSERT INTO `sys_role_menu` VALUES ('2', '891');
INSERT INTO `sys_role_menu` VALUES ('2', '892');
INSERT INTO `sys_role_menu` VALUES ('2', '912');
INSERT INTO `sys_role_menu` VALUES ('2', '914');
INSERT INTO `sys_role_menu` VALUES ('3', '1');
INSERT INTO `sys_role_menu` VALUES ('3', '27');
INSERT INTO `sys_role_menu` VALUES ('3', '943');
INSERT INTO `sys_role_menu` VALUES ('3', '965');
INSERT INTO `sys_role_menu` VALUES ('3', '966');
INSERT INTO `sys_role_menu` VALUES ('3', '967');
INSERT INTO `sys_role_menu` VALUES ('3', '971');
INSERT INTO `sys_role_menu` VALUES ('3', '972');
INSERT INTO `sys_role_menu` VALUES ('3', '973');
INSERT INTO `sys_role_menu` VALUES ('4', '1');
INSERT INTO `sys_role_menu` VALUES ('4', '27');
INSERT INTO `sys_role_menu` VALUES ('4', '943');
INSERT INTO `sys_role_menu` VALUES ('4', '968');
INSERT INTO `sys_role_menu` VALUES ('4', '969');
INSERT INTO `sys_role_menu` VALUES ('4', '970');

-- ----------------------------
-- Table structure for sys_role_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_office`;
CREATE TABLE `sys_role_office` (
  `role_id` bigint(64) NOT NULL COMMENT '角色编号',
  `office_id` bigint(64) NOT NULL COMMENT '机构编号',
  PRIMARY KEY (`role_id`,`office_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-机构';

-- ----------------------------
-- Records of sys_role_office
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `company_id` bigint(64) NOT NULL COMMENT '归属公司',
  `office_id` bigint(64) NOT NULL COMMENT '归属部门',
  `login_name` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `no` varchar(100) DEFAULT NULL COMMENT '工号',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(200) DEFAULT NULL COMMENT '手机',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_user_office_id` (`office_id`),
  KEY `sys_user_login_name` (`login_name`),
  KEY `sys_user_company_id` (`company_id`),
  KEY `sys_user_update_date` (`update_date`),
  KEY `sys_user_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', '2', 'admin', 'a4a627622a4bb73e95fdbabcc51c36610dd277898923451f43b37c68', '0001', 'Administrator', 'admin@hicailiao.com', '66892833', '13198916918', '', '127.0.0.1', '2018-01-09 16:22:17', '1', '2016-08-05 11:38:51', '1', '2016-08-05 11:38:51', '最高管理员', '0');
INSERT INTO `sys_user` VALUES ('2', '1', '2', 'test', '64da1c2ba8010a1ecc3fa91e6cf4c7fcefe1777decd47e422a58b681', 'test', 'test', '', '', '', '1', '0:0:0:0:0:0:0:1', '2016-08-05 11:38:27', '1', '2016-08-05 11:38:16', '1', '2016-08-05 11:38:16', '测试', '1');
INSERT INTO `sys_user` VALUES ('4', '1', '2', '系统用户', 'd1637dd9445a404ad072b23195e6fc925146fa9ee754552fa1bf8e7b', '系统用户', '系统用户', '', '', '', '1', null, null, '1', '2017-04-19 09:58:02', '1', '2017-04-19 09:58:02', '', '1');
INSERT INTO `sys_user` VALUES ('5', '1', '2', 'apply', 'd4b49d5e950d3f2ea5f136a2879d66f38227ac67e89b3085e05adec3', '0003', '结算单申请人员', '', '', '', '', '127.0.0.1', '2017-08-18 16:54:45', '1', '2017-08-04 14:48:33', '1', '2017-08-04 14:48:33', '结算单申请人员', '1');
INSERT INTO `sys_user` VALUES ('6', '1', '2', 'confirm', '493c402ad6e09e8f9dc967f36b1fc062806294d96b9a53fc048c7ecf', '0004', '结算单审核人员', '', '', '', '', '127.0.0.1', '2017-08-04 15:01:21', '1', '2017-08-04 14:48:43', '1', '2017-08-04 14:48:43', '', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(64) NOT NULL COMMENT '用户编号',
  `role_id` bigint(64) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '1');
INSERT INTO `sys_user_role` VALUES ('4', '1');
INSERT INTO `sys_user_role` VALUES ('5', '3');
INSERT INTO `sys_user_role` VALUES ('6', '4');
