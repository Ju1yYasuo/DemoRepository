/*
Navicat MySQL Data Transfer

Source Server         : 172.16.2.11
Source Server Version : 50649
Source Host           : 172.16.2.11:3306
Source Database       : china_aero_engine

Target Server Type    : MYSQL
Target Server Version : 50649
File Encoding         : 65001

Date: 2021-07-13 17:50:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bi_alarm
-- ----------------------------
DROP TABLE IF EXISTS `bi_alarm`;
CREATE TABLE `bi_alarm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '预警名称',
  `type` int(11) DEFAULT NULL COMMENT '预警类型id',
  `level` int(11) DEFAULT NULL COMMENT '预警级别id',
  `threshold` varchar(50) DEFAULT NULL COMMENT '预警阈值',
  `kpi` varchar(50) DEFAULT NULL COMMENT 'kpi',
  `default_handler` varchar(50) DEFAULT NULL COMMENT '预设处理人',
  `whether_set` int(11) DEFAULT NULL COMMENT '是否设置',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预警阈值信息';

-- ----------------------------
-- Table structure for bi_environment_info
-- ----------------------------
DROP TABLE IF EXISTS `bi_environment_info`;
CREATE TABLE `bi_environment_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pm_two_five` decimal(5,1) DEFAULT NULL COMMENT 'PM2.5(ug)',
  `pm_ten` decimal(5,1) DEFAULT NULL COMMENT 'PM10(ug)',
  `wind_speed` decimal(5,1) DEFAULT NULL COMMENT '风速(level)',
  `noise` decimal(5,1) DEFAULT NULL COMMENT '噪音(dB)',
  `humidity` decimal(5,1) DEFAULT NULL COMMENT '湿度(%rh)',
  `temperature` decimal(5,1) DEFAULT NULL COMMENT '温度(℃)',
  `muck_protection_rate` decimal(5,1) DEFAULT NULL COMMENT '渣土防护率(%)',
  `topsoil_protection_rate` decimal(5,1) DEFAULT NULL COMMENT '表土保护率(%)',
  `environmental_rectification_rate` decimal(5,1) DEFAULT NULL COMMENT '环境整改率(%)',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='环境信息';

-- ----------------------------
-- Table structure for bi_message_info
-- ----------------------------
DROP TABLE IF EXISTS `bi_message_info`;
CREATE TABLE `bi_message_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` varchar(255) DEFAULT NULL COMMENT '消息内容',
  `type` int(11) DEFAULT NULL COMMENT '消息类型id',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息通知';

-- ----------------------------
-- Table structure for bi_safety_info
-- ----------------------------
DROP TABLE IF EXISTS `bi_safety_info`;
CREATE TABLE `bi_safety_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(50) DEFAULT NULL COMMENT '安全整改编号',
  `type` int(11) DEFAULT NULL COMMENT '安全类型id',
  `check_type` int(11) DEFAULT NULL COMMENT '检查类型id',
  `check_company` varchar(255) DEFAULT NULL COMMENT '被检测单位',
  `project_name` varchar(255) DEFAULT NULL COMMENT '工程名称',
  `check_time` datetime DEFAULT NULL COMMENT '检查时间',
  `participants` varchar(255) DEFAULT NULL COMMENT '参加检测人员id',
  `request_time` datetime DEFAULT NULL COMMENT '要求时间',
  `check_record` varchar(2000) DEFAULT NULL COMMENT '检测记录（富文本）',
  `rectification_situation` int(11) DEFAULT NULL COMMENT '整改情况id',
  `complet_time` datetime DEFAULT NULL COMMENT '完成时间',
  `rectification_person_liable` int(11) DEFAULT NULL COMMENT '整改责任人id',
  `rectification_details` varchar(2000) DEFAULT NULL COMMENT '整改详情（富文本）',
  `whether_request` int(11) DEFAULT NULL COMMENT '是否符合要求id',
  `safety_officer_opinion` varchar(1000) DEFAULT NULL COMMENT '施工单位安全员意见',
  `project_manager_opinion` varchar(1000) DEFAULT NULL COMMENT '施工单位项目经理意见',
  `chief_supervisor_opinion` varchar(1000) DEFAULT NULL COMMENT '监理单位总监意见',
  `project_director_opinion` varchar(1000) DEFAULT NULL COMMENT '建设单位项目主管意见',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(1000) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='安全整改信息';

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(30) DEFAULT NULL COMMENT '部门名称',
  `super` int(11) DEFAULT NULL COMMENT '上级部门id',
  `code` varchar(30) DEFAULT NULL COMMENT '部门编码',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='部门信息';

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `value` varchar(50) DEFAULT NULL COMMENT '字典值',
  `type` varchar(20) DEFAULT NULL COMMENT '字典类型',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态（0正常，1弃用）',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='字典值';

-- ----------------------------
-- Table structure for sys_dictionary_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary_type`;
CREATE TABLE `sys_dictionary_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '字典类型名称',
  `type` varchar(20) DEFAULT NULL COMMENT '字典类型',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态（0正常，1弃用）',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='字典类型';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` varchar(255) DEFAULT NULL COMMENT '日志内容',
  `type` int(11) DEFAULT NULL COMMENT '日志类型',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志信息';

-- ----------------------------
-- Table structure for sys_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_permissions`;
CREATE TABLE `sys_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `permissions_name` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `parent_permissions` int(11) DEFAULT NULL COMMENT '父权限id',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限信息';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色信息';

-- ----------------------------
-- Table structure for sys_role_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permissions`;
CREATE TABLE `sys_role_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `permissions_id` int(11) DEFAULT NULL COMMENT '权限ID',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限信息';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `sex` enum('男','女') DEFAULT NULL COMMENT '性别（枚举型，1为男，2为女）',
  `age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `card_number` varchar(30) DEFAULT NULL COMMENT '卡号',
  `department` int(11) DEFAULT NULL COMMENT '部门id',
  `position` int(11) DEFAULT NULL COMMENT '岗位id',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `id_number` varchar(20) DEFAULT NULL COMMENT '身份证号码',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `emergency_contact` varchar(30) DEFAULT NULL COMMENT '紧急联系人',
  `emergency_contact_phone` varchar(20) DEFAULT NULL COMMENT '紧急联系人电话',
  `face_picture` varchar(50) DEFAULT NULL COMMENT '脸图文件名',
  `log_status` tinyint(4) DEFAULT NULL COMMENT '登录状态（0未登录，1已登录，2锁定）',
  `log_last_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `log_error_number` tinyint(4) DEFAULT NULL COMMENT '登录错误次数',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '插入时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_name_password` (`user_name`,`password`) USING BTREE COMMENT '登录索引'
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='人员信息';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) DEFAULT NULL COMMENT '人员ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT '' COMMENT '注备',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员角色信息';
