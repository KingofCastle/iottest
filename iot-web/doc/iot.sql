/*
Navicat MySQL Data Transfer

Source Server         : iottest
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : iottest

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2017-05-16 16:47:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for data_gps
-- ----------------------------
DROP TABLE IF EXISTS `data_gps`;
CREATE TABLE `data_gps` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `sensor_id` varchar(64) default NULL,
  `time` datetime default NULL COMMENT '数据时间',
  `longitude` varchar(100) default NULL COMMENT '经度',
  `latitude` varchar(100) default NULL COMMENT '纬度',
  `speed` varchar(100) default NULL COMMENT '速度',
  `create_by` varchar(64) default NULL COMMENT '创建者',
  `create_date` datetime default NULL COMMENT '创建时间',
  `update_by` varchar(64) default NULL COMMENT '更新者',
  `update_date` datetime default NULL COMMENT '更新时间',
  `remarks` varchar(255) default NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL default '0' COMMENT '删除标记',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='GPS表';

-- ----------------------------
-- Table structure for data_humidity
-- ----------------------------
DROP TABLE IF EXISTS `data_humidity`;
CREATE TABLE `data_humidity` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `sensor_id` varchar(64) default NULL,
  `time` datetime default NULL COMMENT '数据时间',
  `value` varchar(64) default NULL COMMENT '数据值',
  `create_by` varchar(64) default NULL COMMENT '创建者',
  `create_date` datetime default NULL COMMENT '创建时间',
  `update_by` varchar(64) default NULL COMMENT '更新者',
  `update_date` datetime default NULL COMMENT '更新时间',
  `remarks` varchar(255) default NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL default '0' COMMENT '删除标记',
  PRIMARY KEY  (`id`),
  KEY `data_humidity_sensor_id` USING BTREE (`sensor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='湿度表';

-- ----------------------------
-- Table structure for data_temperature
-- ----------------------------
DROP TABLE IF EXISTS `data_temperature`;
CREATE TABLE `data_temperature` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `sensor_id` varchar(64) default NULL COMMENT '传感器id',
  `time` datetime default NULL COMMENT '数据时间',
  `value` varchar(64) default NULL COMMENT '数据值',
  `create_by` varchar(64) default NULL COMMENT '创建者',
  `create_date` datetime default NULL COMMENT '创建时间',
  `update_by` varchar(64) default NULL COMMENT '更新者',
  `update_date` datetime default NULL COMMENT '更新时间',
  `remarks` varchar(255) default NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL default '0' COMMENT '删除标记',
  PRIMARY KEY  (`id`),
  KEY `data_temperature_sensor_id` USING BTREE (`sensor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='温度表';

-- ----------------------------
-- Table structure for sys_company
-- ----------------------------
DROP TABLE IF EXISTS `sys_company`;
CREATE TABLE `sys_company` (
  `id` varchar(64) NOT NULL,
  `code` varchar(64) default NULL COMMENT '企业编码',
  `api_key` varchar(64) default NULL,
  `api_secret` varchar(64) default NULL,
  `company_login` varchar(64) NOT NULL COMMENT '企业账号',
  `password` varchar(100) default NULL COMMENT '密码',
  `company_name` varchar(100) default NULL COMMENT '企业名称',
  `address` varchar(255) default NULL COMMENT '联系地址',
  `telephone` varchar(255) default NULL COMMENT '联系电话',
  `email` varchar(200) default NULL COMMENT '邮箱',
  `introduction` varchar(200) default NULL COMMENT '企业简介',
  `create_by` varchar(64) default NULL COMMENT '创建者',
  `create_date` datetime default NULL COMMENT '创建时间',
  `update_by` varchar(64) default NULL COMMENT '更新者',
  `update_date` datetime default NULL COMMENT '更新时间',
  `remarks` varchar(255) default NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL default '0' COMMENT '删除标记',
  PRIMARY KEY  (`id`),
  KEY `sys_company_api_key` USING BTREE (`api_key`),
  KEY `sys_company_company_login` USING BTREE (`company_login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业表';

-- ----------------------------
-- Table structure for sys_device
-- ----------------------------
DROP TABLE IF EXISTS `sys_device`;
CREATE TABLE `sys_device` (
  `id` varchar(64) NOT NULL,
  `code` varchar(64) default NULL COMMENT '编号',
  `type` varchar(64) default NULL COMMENT '类型',
  `name` varchar(64) default NULL COMMENT '名称',
  `introduction` varchar(200) default NULL COMMENT '简介',
  `tags` varchar(200) default NULL COMMENT '标签',
  `create_by` varchar(64) default NULL COMMENT '创建者',
  `create_date` datetime default NULL COMMENT '创建时间',
  `update_by` varchar(64) default NULL COMMENT '更新者',
  `update_date` datetime default NULL COMMENT '更新时间',
  `remarks` varchar(255) default NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL default '0' COMMENT '删除标记',
  PRIMARY KEY  (`id`),
  KEY `sys_device_code` USING BTREE (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备表';


-- ----------------------------
-- Table structure for sys_sensor
-- ----------------------------
DROP TABLE IF EXISTS `sys_sensor`;
CREATE TABLE `sys_sensor` (
  `id` varchar(64) NOT NULL,
  `code` varchar(64) default NULL COMMENT '编号',
  `company_id` varchar(64) default NULL COMMENT '所属公司',
  `device_id` varchar(64) default NULL,
  `type` varchar(64) default NULL COMMENT '类型',
  `name` varchar(64) default NULL COMMENT '名称',
  `introduction` varchar(200) default NULL COMMENT '简介',
  `tags` varchar(200) default NULL COMMENT '标签',
  `create_by` varchar(64) default NULL COMMENT '创建者',
  `create_date` datetime default NULL COMMENT '创建时间',
  `update_by` varchar(64) default NULL COMMENT '更新者',
  `update_date` datetime default NULL COMMENT '更新时间',
  `remarks` varchar(255) default NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL default '0' COMMENT '删除标记',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='传感器表';
