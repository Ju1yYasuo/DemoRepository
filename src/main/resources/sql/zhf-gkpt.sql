/*
Navicat MySQL Data Transfer

Source Server         : 172.16.2.11
Source Server Version : 50649
Source Host           : 172.16.2.11:3306
Source Database       : zhf-gkpt

Target Server Type    : MYSQL
Target Server Version : 50649
File Encoding         : 65001

Date: 2021-07-28 16:18:27
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `humidity` decimal(5,1) DEFAULT NULL COMMENT '湿度(%)',
  `temperature` decimal(5,1) DEFAULT NULL COMMENT '温度(℃)',
  `muck_protection_rate` decimal(5,1) DEFAULT NULL COMMENT '渣土防护率(%)',
  `topsoil_protection_rate` decimal(5,1) DEFAULT NULL COMMENT '表土保护率(%)',
  `environmental_rectification_rate` decimal(5,1) DEFAULT NULL COMMENT '环境整改率(%)',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='环境信息';

-- ----------------------------
-- Records of bi_environment_info
-- ----------------------------
INSERT INTO `bi_environment_info` VALUES ('1', '300.0', '917.0', '36.1', '77.5', '517.0', '424.0', '554.0', '722.0', '879.0', '0', 'ciy7b5', '2021-07-15 17:40:27', '2021-07-20 15:52:00');
INSERT INTO `bi_environment_info` VALUES ('2', '300.0', '917.0', '998.0', '970.0', '517.0', '424.0', '554.0', '722.0', '879.0', '0', 'ciy7b5', '2021-07-13 17:44:26', '2021-07-20 15:52:11');
INSERT INTO `bi_environment_info` VALUES ('3', '300.0', '917.0', '998.0', '970.0', '517.0', '424.0', '554.0', '722.0', '879.0', '0', 'ciy7b5', '2021-07-15 16:21:13', '2021-07-20 16:00:37');
INSERT INTO `bi_environment_info` VALUES ('4', '809.0', '202.0', '224.0', '556.0', '120.0', '283.0', '228.0', '44.0', '12.0', '0', 'rifynr', '2021-07-15 16:22:04', null);
INSERT INTO `bi_environment_info` VALUES ('5', '11.0', '202.0', '224.0', '556.0', '120.0', '283.0', '228.0', '44.0', '12.0', '0', 'rifynr', '2021-07-20 15:45:49', null);
INSERT INTO `bi_environment_info` VALUES ('6', '1.0', '202.0', '224.0', '556.0', '120.0', '283.0', '228.0', '44.0', '12.0', '0', 'rifynr', '2021-07-20 15:46:17', null);
INSERT INTO `bi_environment_info` VALUES ('7', '123.0', '202.0', '224.0', '556.0', '120.0', '283.0', '228.0', '44.0', '12.0', '0', 'rifynr', '2021-07-20 15:46:40', null);
INSERT INTO `bi_environment_info` VALUES ('8', '123.0', '202.0', '224.0', '556.0', '120.0', '283.0', '228.0', '44.0', '12.0', '0', 'rifynr', '2021-07-20 15:46:50', null);
INSERT INTO `bi_environment_info` VALUES ('9', '123.0', '202.0', '224.0', '556.0', '120.0', '283.0', '228.0', '44.0', '12.0', '0', 'rifynr', '2021-07-20 15:46:50', null);
INSERT INTO `bi_environment_info` VALUES ('10', '1.0', '2.0', '3.0', '4.0', '5.0', '6.0', '7.0', '8.0', '9.0', '0', 'rifynr', '2021-07-20 15:47:10', null);
INSERT INTO `bi_environment_info` VALUES ('11', '1.0', '2.0', '3.0', '4.0', '5.0', '6.0', '7.0', '8.0', '9.0', '0', 'rifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynr', '2021-07-20 15:47:30', null);
INSERT INTO `bi_environment_info` VALUES ('12', '1.0', '2.0', '3.0', '4.0', '5.0', '6.0', '7.0', '8.0', '9.0', '0', 'rifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynr', '2021-07-20 15:47:31', null);
INSERT INTO `bi_environment_info` VALUES ('13', '1.0', '2.0', '3.0', '4.0', '5.0', '6.0', '7.0', '8.0', '9.0', '0', 'rifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynr', '2021-07-20 15:48:24', null);
INSERT INTO `bi_environment_info` VALUES ('14', '1.0', '2.0', '3.0', '4.0', '5.0', '6.0', '7.0', '8.0', '9.0', '0', 'rifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynrrifynr', '2021-07-20 15:48:30', null);
INSERT INTO `bi_environment_info` VALUES ('15', '1.0', '2.0', '3.0', '4.0', '5.0', '6.0', '7.0', '8.0', '9.0', '0', 'asdasdas', '2021-07-21 15:52:00', null);
INSERT INTO `bi_environment_info` VALUES ('16', '55.0', '41.0', '33.0', '322.0', '12.0', '125.0', '55.0', '44.0', '66.0', '0', null, '2021-07-22 00:00:00', null);
INSERT INTO `bi_environment_info` VALUES ('17', '52.0', '114.0', '55.0', '67.0', '35.0', '36.1', '123.0', '333.0', '100.0', '0', null, '2021-07-24 00:00:00', null);
INSERT INTO `bi_environment_info` VALUES ('18', '53.0', '115.0', '56.0', '68.0', '36.0', '37.1', '124.0', '15.0', '101.0', '0', null, '2021-07-25 00:00:00', null);

-- ----------------------------
-- Table structure for bi_inandout
-- ----------------------------
DROP TABLE IF EXISTS `bi_inandout`;
CREATE TABLE `bi_inandout` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `orderId` varchar(50) NOT NULL COMMENT '流程order',
  `taskId` varchar(30) NOT NULL COMMENT '任务ID',
  `title` varchar(30) NOT NULL COMMENT '标题',
  `applyUser` int(11) NOT NULL COMMENT '申请人ID',
  `applyUserName` varchar(50) NOT NULL COMMENT '申请人姓名',
  `reason` varchar(255) DEFAULT NULL COMMENT '申请原因',
  `num` tinyint(4) NOT NULL DEFAULT '1' COMMENT '申请人数',
  `startTime` datetime NOT NULL COMMENT '有效开始时间',
  `endTime` datetime NOT NULL COMMENT '有效结束时间',
  `emergency` tinyint(4) DEFAULT '1' COMMENT '紧急程度，1低，2中，3高',
  `step` tinyint(4) DEFAULT '1' COMMENT '1发起，2审核，3完成，0取消',
  `delete` tinyint(4) DEFAULT '0' COMMENT '是否删除，0未删除，1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='进出数据申请表';

-- ----------------------------
-- Records of bi_inandout
-- ----------------------------

-- ----------------------------
-- Table structure for bi_inandout_dept
-- ----------------------------
DROP TABLE IF EXISTS `bi_inandout_dept`;
CREATE TABLE `bi_inandout_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(50) NOT NULL COMMENT '部门eface系统ID',
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `parent_code` varchar(50) NOT NULL COMMENT '父级部门eface系统ID',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1043 DEFAULT CHARSET=utf8 COMMENT='eface系统的部门组织关系';

-- ----------------------------
-- Records of bi_inandout_dept
-- ----------------------------
INSERT INTO `bi_inandout_dept` VALUES ('1033', '12844a1b66214edbb6ed5231d5abe37e', '中航发人员实名制系统（测试环境） (1)', 'root', '1');
INSERT INTO `bi_inandout_dept` VALUES ('1034', 'befc09bba75245df8579', '组织部门1 (0)', '12844a1b66214edbb6ed5231d5abe37e', '1');
INSERT INTO `bi_inandout_dept` VALUES ('1035', 'abfa5ab446364a93a063', '组织部门2 (0)', '12844a1b66214edbb6ed5231d5abe37e', '1');
INSERT INTO `bi_inandout_dept` VALUES ('1036', '75a427152a194735b2e4', '组织部门3 (0)', '12844a1b66214edbb6ed5231d5abe37e', '1');
INSERT INTO `bi_inandout_dept` VALUES ('1037', '986fb66d2330466d9b3e', '组织部门4 (0)', '12844a1b66214edbb6ed5231d5abe37e', '1');
INSERT INTO `bi_inandout_dept` VALUES ('1038', '9d70a6ec2a9f4647be5e', '外部公司 (1)', '12844a1b66214edbb6ed5231d5abe37e', '1');
INSERT INTO `bi_inandout_dept` VALUES ('1039', 'c82c12da2c22479f8a11', '智慧企业 (1)', '9d70a6ec2a9f4647be5e', '1');
INSERT INTO `bi_inandout_dept` VALUES ('1040', '8fc98875f9f54e16ae8a', 'dahuici (0)', 'c82c12da2c22479f8a11', '1');
INSERT INTO `bi_inandout_dept` VALUES ('1041', 'f765f9174280414881e7', '中铁二建 (0)', '9d70a6ec2a9f4647be5e', '1');
INSERT INTO `bi_inandout_dept` VALUES ('1042', '399c89754df2421f9d02', '右键新增组织 (0)', '12844a1b66214edbb6ed5231d5abe37e', '1');

-- ----------------------------
-- Table structure for bi_inandout_file
-- ----------------------------
DROP TABLE IF EXISTS `bi_inandout_file`;
CREATE TABLE `bi_inandout_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `intandoutId` int(11) NOT NULL COMMENT '申请ID',
  `path` varchar(255) NOT NULL COMMENT '路径',
  `name` varchar(50) NOT NULL COMMENT '附件名称',
  `createTime` datetime NOT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='进出数据申请附件信息';

-- ----------------------------
-- Records of bi_inandout_file
-- ----------------------------

-- ----------------------------
-- Table structure for bi_inandout_user
-- ----------------------------
DROP TABLE IF EXISTS `bi_inandout_user`;
CREATE TABLE `bi_inandout_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `intandoutId` int(11) NOT NULL COMMENT '申请ID',
  `userName` varchar(50) NOT NULL COMMENT '用户姓名',
  `carNo` varchar(18) NOT NULL COMMENT '身份证号',
  `tel` varchar(15) NOT NULL COMMENT '手机号码',
  `deptName` varchar(50) NOT NULL COMMENT '部门名称',
  `deptId` varchar(50) NOT NULL COMMENT '部门ID，bi_eface_dept 的code字段',
  `sex` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0女，1男',
  `job` varchar(100) DEFAULT NULL COMMENT '工作岗位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='进出数据申请用户信息';

-- ----------------------------
-- Records of bi_inandout_user
-- ----------------------------

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
-- Records of bi_message_info
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='安全整改信息';

-- ----------------------------
-- Records of bi_safety_info
-- ----------------------------
INSERT INTO `bi_safety_info` VALUES ('10', 'qweq', '7', '4', 'adsas', 'fdsf', '2021-07-16 09:36:13', '10,11', '2021-07-16 09:36:25', 'adadsaas', '9', '2021-07-16 09:36:35', '10', 'dasdasd', '11', 'adasdda', 'asdasdad', 'fsdfsdf', 'dasdasds', '0', null, null, null);
INSERT INTO `bi_safety_info` VALUES ('11', 'asdasd', '7', '4', 'sfdsd', 'sfdf', null, null, null, null, '9', null, null, null, '11', null, null, null, null, '0', null, null, null);
INSERT INTO `bi_safety_info` VALUES ('12', 'sdfsdf', '7', '5', null, null, null, null, null, null, '9', null, null, null, '11', null, null, null, null, '0', null, null, null);
INSERT INTO `bi_safety_info` VALUES ('13', 'asd', '8', '6', null, null, null, null, null, null, '10', null, null, null, '12', null, null, null, null, '0', null, null, null);

-- ----------------------------
-- Table structure for bi_threshold
-- ----------------------------
DROP TABLE IF EXISTS `bi_threshold`;
CREATE TABLE `bi_threshold` (
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
-- Records of bi_threshold
-- ----------------------------

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(30) DEFAULT NULL COMMENT '部门名称',
  `super_id` int(11) DEFAULT NULL COMMENT '上级部门id',
  `code` varchar(30) DEFAULT NULL COMMENT '部门编码',
  `whether_company` int(4) DEFAULT NULL COMMENT '是否是公司id',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_name` (`name`) COMMENT '部门名称唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='部门信息';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('10', '技术开发部', '0', 'werw', '12', '0', null, '2021-07-07 17:06:51', '2021-07-07 17:06:53');
INSERT INTO `sys_department` VALUES ('11', '人力资源部', '0', 'sd', '12', '0', null, null, null);
INSERT INTO `sys_department` VALUES ('12', '市场部', '0', 'sfsd', '12', '0', null, null, null);
INSERT INTO `sys_department` VALUES ('13', '中建八局第三建筑有限公司', '12', 'dsfs', '11', '0', null, null, null);
INSERT INTO `sys_department` VALUES ('14', '成都苏宁工程咨询有限公司', '12', 'fsdfs', '11', '0', null, null, null);
INSERT INTO `sys_department` VALUES ('15', '成都苏宁工程咨询有限公司子公司', '14', 'fsd', '11', '0', null, null, null);
INSERT INTO `sys_department` VALUES ('16', '技术开发部分部', '10', 'adasdasda', '12', '0', null, '2021-07-27 11:00:22', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='字典值';

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES ('1', 'Java开发', 'sys_user_position', '0', '0', null, '2021-07-09 10:16:45', null);
INSERT INTO `sys_dictionary` VALUES ('2', '前端开发', 'sys_user_position', '0', '0', null, '2021-07-09 10:16:49', null);
INSERT INTO `sys_dictionary` VALUES ('3', 'UI设计', 'sys_user_position', '0', '0', null, '2021-07-09 10:16:59', null);
INSERT INTO `sys_dictionary` VALUES ('4', '施工管理安全检查', 'bi_safety_check_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('5', '施工现场安全文明生产检查', 'bi_safety_check_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('6', '安全专项检查', 'bi_safety_check_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('7', '安全检查', 'bi_safety_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('8', '安全隐患', 'bi_safety_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('9', '已整改', 'bi_safety_situation', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('10', '未整改', 'bi_safety_situation', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('11', '是', 'sys_whether', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('12', '否', 'sys_whether', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('13', '工种1', 'sys_work_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('14', '工种2', 'sys_work_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('15', '工种3', 'sys_work_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('16', '一级', 'sys_job_level', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('17', '二级', 'sys_job_level', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('18', '三级', 'sys_job_level', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('19', 'T1', 'sys_helmet_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('20', 'T2', 'sys_helmet_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary` VALUES ('21', 'T3', 'sys_helmet_type', '0', '0', null, null, null);

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_type` (`type`) COMMENT '字典类型唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='字典类型';

-- ----------------------------
-- Records of sys_dictionary_type
-- ----------------------------
INSERT INTO `sys_dictionary_type` VALUES ('1', '用户岗位', 'sys_user_position', '0', '0', null, null, null);
INSERT INTO `sys_dictionary_type` VALUES ('2', '安全整改检查类型', 'bi_safety_check_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary_type` VALUES ('3', '安全整改安全类型', 'bi_safety_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary_type` VALUES ('4', '安全整改整改情况', 'bi_safety_situation', '0', '0', null, null, null);
INSERT INTO `sys_dictionary_type` VALUES ('5', '是否', 'sys_whether', '0', '0', null, null, null);
INSERT INTO `sys_dictionary_type` VALUES ('6', '工种', 'sys_work_type', '0', '0', null, null, null);
INSERT INTO `sys_dictionary_type` VALUES ('7', '职业等级', 'sys_job_level', '0', '0', null, null, null);
INSERT INTO `sys_dictionary_type` VALUES ('8', '安全帽类型', 'sys_helmet_type', '0', '0', null, null, null);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) DEFAULT NULL COMMENT '操作账户',
  `content` varchar(255) DEFAULT NULL COMMENT '日志内容',
  `type` int(11) DEFAULT NULL COMMENT '日志类型',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=610 DEFAULT CHARSET=utf8 COMMENT='日志信息';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_parameter
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameter`;
CREATE TABLE `sys_parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) DEFAULT NULL COMMENT '参数名称',
  `parameter_key` varchar(50) DEFAULT NULL COMMENT '键',
  `value` varchar(255) DEFAULT NULL COMMENT '值',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='系统参数';

-- ----------------------------
-- Records of sys_parameter
-- ----------------------------
INSERT INTO `sys_parameter` VALUES ('10', '安全生产天数开始时间', 'start_time_safe_production_days', '2021-08-01', '0', '', '2021-07-23 15:23:34', null);

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_permissions_name` (`permissions_name`) COMMENT '权限名称唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='权限信息';

-- ----------------------------
-- Records of sys_permissions
-- ----------------------------
INSERT INTO `sys_permissions` VALUES ('1', '基础模块', '0', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('2', '环境信息', '1', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('3', '查看环境信息', '2', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('4', '新增环境信息', '2', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('5', '修改环境信息', '2', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('6', '删除环境信息', '2', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('7', '安全信息整改', '1', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('8', '查看安全信息整改', '7', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('9', '新增安全信息整改', '7', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('10', '修改安全信息整改', '7', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('11', '删除安全信息整改', '7', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('12', '用户信息', '17', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('13', '查看用户信息', '12', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('14', '新增用户信息', '12', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('15', '修改用户信息', '12', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('16', '删除用户信息', '12', '0', null, null, null);
INSERT INTO `sys_permissions` VALUES ('17', '系统模块', '0', '0', null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='角色信息';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('10', '超级管理员', '0', null, null, null);
INSERT INTO `sys_role` VALUES ('11', '环境信息管理员', '0', null, null, null);
INSERT INTO `sys_role` VALUES ('12', '环境信息用户', '0', '管理员', null, null);
INSERT INTO `sys_role` VALUES ('13', '环境信息观察者', '0', null, null, null);
INSERT INTO `sys_role` VALUES ('14', '安全整改管理员', '0', null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='角色权限信息';

-- ----------------------------
-- Records of sys_role_permissions
-- ----------------------------
INSERT INTO `sys_role_permissions` VALUES ('1', '10', '3', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('2', '10', '4', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('3', '10', '5', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('4', '10', '6', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('5', '11', '3', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('6', '11', '4', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('7', '11', '5', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('8', '11', '6', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('9', '12', '3', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('10', '12', '4', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('11', '12', '5', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('12', '13', '3', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('13', '10', '8', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('14', '10', '9', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('15', '10', '10', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('16', '10', '11', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('17', '14', '8', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('18', '14', '9', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('19', '14', '10', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('20', '14', '11', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('21', '10', '13', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('22', '10', '14', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('23', '10', '15', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('24', '10', '16', '0', null, null, null);
INSERT INTO `sys_role_permissions` VALUES ('25', '13', '1', '0', null, null, null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(20) DEFAULT NULL COMMENT '账号',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `sex` enum('男','女') DEFAULT NULL COMMENT '性别（枚举型，1为男，2为女）',
  `age` tinyint(4) DEFAULT NULL COMMENT '年龄',
  `card_number` varchar(30) DEFAULT NULL COMMENT '卡号',
  `department` int(11) DEFAULT NULL COMMENT '部门id',
  `position` int(11) DEFAULT NULL COMMENT '岗位id',
  `work_type` int(11) DEFAULT NULL COMMENT '工种',
  `job_code` varchar(50) DEFAULT NULL COMMENT '职业代码',
  `job_level` int(11) DEFAULT NULL COMMENT '职业等级',
  `helmet_type` int(11) DEFAULT NULL COMMENT '安全帽等级',
  `bound_helmet` varchar(50) DEFAULT NULL COMMENT '绑定安全帽',
  `user_number` varchar(50) DEFAULT NULL COMMENT '人员编号',
  `whether_screen` int(11) DEFAULT NULL COMMENT '是否进入大屏id',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `id_number` varchar(20) DEFAULT NULL COMMENT '身份证号码',
  `emergency_contact` varchar(30) DEFAULT NULL COMMENT '紧急联系人',
  `emergency_contact_phone` varchar(20) DEFAULT NULL COMMENT '紧急联系人电话',
  `face_picture` varchar(50) DEFAULT NULL COMMENT '脸图文件名',
  `account_expiration_date` datetime DEFAULT NULL COMMENT '账号到期时间',
  `log_status` tinyint(4) DEFAULT NULL COMMENT '登录状态（0未登录，1已登录，2锁定）',
  `log_last_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `log_error_number` tinyint(4) DEFAULT NULL COMMENT '登录错误次数',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除（0未删除，1已删除）',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '插入时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_name` (`user_name`) USING BTREE COMMENT '用户名索引'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='人员信息';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('10', 'julyyasuo', '79f88134f817f20172476f5ecabc16d0', '超级管理员', '男', '22', '777', '10', '1', '13', 'dfg33', '16', '19', 'fsdsdf', '2342', '11', '18200178194', '510811199510170899', null, '18200178194', 'julyyasuo.jpg', '2022-10-01 10:00:42', '0', '2021-07-19 07:59:06', '0', '0', 'admin', '2021-07-07 16:15:18', '2021-07-07 16:15:12');
INSERT INTO `sys_user` VALUES ('11', 'julyriven', '79f88134f817f20172476f5ecabc16d0', '环境信息管理员', '女', '26', '888', '10', '2', '13', 'dfg33', '17', '19', 'sfe', '34234232', '11', null, null, null, null, null, '2022-10-01 10:00:42', '0', '2021-07-19 08:00:52', '0', '0', null, '2021-07-23 10:29:00', '2021-07-15 16:16:42');
INSERT INTO `sys_user` VALUES ('12', 'julyzed', '79f88134f817f20172476f5ecabc16d0', '环境信息用户', '男', '25', '999', '11', '2', '13', 'dfg33', '18', '19', 'qe', '2332', '12', null, null, null, null, null, '2022-10-01 10:00:42', '0', '2021-07-19 08:06:52', '0', '0', null, '2021-07-23 10:29:03', '2021-07-19 08:06:52');
INSERT INTO `sys_user` VALUES ('13', 'julyleesin', '79f88134f817f20172476f5ecabc16d0', '观察者', '男', '25', '666', '12', '3', '14', 'dfg33', '18', '20', 'dsfsd', '43', '11', null, null, null, null, null, '2022-10-01 10:00:42', '1', '2021-07-28 16:08:07', '0', '0', null, '2021-07-23 10:29:06', '2021-07-11 08:04:51');
INSERT INTO `sys_user` VALUES ('14', '修杰.龙', '79f88134f817f20172476f5ecabc16d0', '安全整改管理员', '女', '62', 'x5jsxl', '13', '1', '14', 'dfg33', '18', '20', 'fs', '435', '12', 'yznn1a', '5h068i', 'fer6yp', '17832745794', '2u4q75', '2022-10-01 10:00:42', '0', '2021-07-08 08:00:00', '0', '0', '8c0vp6', '2021-07-08 17:47:59', '2021-07-08 17:50:10');
INSERT INTO `sys_user` VALUES ('15', '奥数第4', '79f88134f817f20172476f5ecabc16d0', '环境安全管理员', '女', '30', 'dj9p2n', '13', '1', '14', 'dfg33', '17', '20', 'sdf', '353', '12', 'cowqvu', '2nd141', 'ow1hoo', '15130934036', 'dobpga', '2022-10-01 10:00:42', '0', '2021-07-12 08:00:00', '0', '0', 'v82q8a', '2021-07-12 13:39:42', null);
INSERT INTO `sys_user` VALUES ('16', '奥数第', 'c4ca4238a0b923820dcc509a6f75849b', '伟祺.江', '女', '30', 'dj9p2n', '13', '3', '15', 'dfg33', '17', '20', 'fds', '2422', '11', 'cowqvu', '2nd141', 'ow1hoo', '15130934036', 'dobpga', '2022-10-01 10:00:42', '0', '2021-07-19 07:48:52', '0', '0', 'v82q8a', '2021-07-12 13:39:42', '2021-07-22 17:17:03');
INSERT INTO `sys_user` VALUES ('17', '瑞霖.叶', '79f88134f817f20172476f5ecabc16d0', '瑞霖.叶', '男', '64', 'hurnlt', '14', '2', '15', 'dfg33', '16', '20', 'sdf', '434', '11', 'i22t1u', '2dzlv3', '8klhw1', '17052490900', 'p12etj', '2022-10-01 10:00:42', '0', '2021-07-13 08:00:00', '0', '0', 'f4u8v1', '2021-07-13 16:28:00', null);
INSERT INTO `sys_user` VALUES ('18', '晓啸.姚', '6c7daaf2d25476fa022d540dd0c52092', '晓啸.姚', '男', '64', 'y5g1ta', '12', '1', '15', '660', '17', '20', 'e529zq', 'gr23', '12', 'g0dbrh', 'i5wkv7', '0zhiio', '15692871028', 'x1d8fc', '2022-10-01 10:00:42', '0', '2021-07-15 16:19:43', '0', '0', '49l5cm', '2021-07-23 10:29:08', '2021-07-19 08:45:49');
INSERT INTO `sys_user` VALUES ('19', 'asd', '6c7daaf2d25476fa022d540dd0c52092', '晓啸.姚', '男', '64', 'y5g1ta', '13', '3', '13', '660', '17', '21', 'e529zq', 'fdg', '12', 'g0dbrh', 'i5wkv7', '0zhiio', '15692871028', 'x1d8fc', '2022-10-01 10:00:42', null, null, '0', '0', '49l5cm', '2021-07-19 08:47:18', '2021-07-22 17:16:55');
INSERT INTO `sys_user` VALUES ('20', '伟祺.江', 'e8bca8838002403fdfcda5b48dc673cf', '伟祺.江', '男', '30', 'j0zz8g', '13', '2', '13', 'dfg33', '18', '21', 'sdf', '2342', '12', 'j09lue', '9oytv4', 'r9cn90', '15130934036', 'zdbq2z', '2022-10-01 10:00:42', null, null, '0', '0', '1buhhk', '2021-07-20 15:03:18', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='人员角色信息';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '10', '10', '0', '', null, null);
INSERT INTO `sys_user_role` VALUES ('2', '11', '11', '0', '', null, null);
INSERT INTO `sys_user_role` VALUES ('3', '12', '12', '0', '', null, null);
INSERT INTO `sys_user_role` VALUES ('4', '13', '13', '0', '', null, null);
INSERT INTO `sys_user_role` VALUES ('5', '14', '14', '0', '', null, null);
INSERT INTO `sys_user_role` VALUES ('6', '15', '11', '0', '', null, null);
INSERT INTO `sys_user_role` VALUES ('7', '15', '14', '0', '', null, null);

-- ----------------------------
-- Table structure for wf_cc_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_cc_order`;
CREATE TABLE `wf_cc_order` (
  `order_Id` varchar(32) DEFAULT NULL COMMENT '流程实例ID',
  `actor_Id` varchar(50) DEFAULT NULL COMMENT '参与者ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '发起人',
  `create_Time` varchar(50) DEFAULT NULL COMMENT '抄送时间',
  `finish_Time` varchar(50) DEFAULT NULL COMMENT '完成时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  KEY `IDX_CCORDER_ORDER` (`order_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='抄送实例表';

-- ----------------------------
-- Records of wf_cc_order
-- ----------------------------

-- ----------------------------
-- Table structure for wf_hist_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_order`;
CREATE TABLE `wf_hist_order` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `process_Id` varchar(32) NOT NULL COMMENT '流程定义ID',
  `order_State` tinyint(1) NOT NULL COMMENT '状态',
  `creator` varchar(50) DEFAULT NULL COMMENT '发起人',
  `create_Time` varchar(50) NOT NULL COMMENT '发起时间',
  `end_Time` varchar(50) DEFAULT NULL COMMENT '完成时间',
  `expire_Time` varchar(50) DEFAULT NULL COMMENT '期望完成时间',
  `priority` tinyint(1) DEFAULT NULL COMMENT '优先级',
  `parent_Id` varchar(32) DEFAULT NULL COMMENT '父流程ID',
  `order_No` varchar(50) DEFAULT NULL COMMENT '流程实例编号',
  `variable` varchar(2000) DEFAULT NULL COMMENT '附属变量json存储',
  PRIMARY KEY (`id`),
  KEY `IDX_HIST_ORDER_PROCESSID` (`process_Id`),
  KEY `IDX_HIST_ORDER_NO` (`order_No`),
  KEY `FK_HIST_ORDER_PARENTID` (`parent_Id`),
  CONSTRAINT `FK_HIST_ORDER_PARENTID` FOREIGN KEY (`parent_Id`) REFERENCES `wf_hist_order` (`id`),
  CONSTRAINT `FK_HIST_ORDER_PROCESSID` FOREIGN KEY (`process_Id`) REFERENCES `wf_process` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='历史流程实例表';

-- ----------------------------
-- Records of wf_hist_order
-- ----------------------------

-- ----------------------------
-- Table structure for wf_hist_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_task`;
CREATE TABLE `wf_hist_task` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `order_Id` varchar(32) NOT NULL COMMENT '流程实例ID',
  `task_Name` varchar(100) NOT NULL COMMENT '任务名称',
  `display_Name` varchar(200) NOT NULL COMMENT '任务显示名称',
  `task_Type` tinyint(1) NOT NULL COMMENT '任务类型',
  `perform_Type` tinyint(1) DEFAULT NULL COMMENT '参与类型',
  `task_State` tinyint(1) NOT NULL COMMENT '任务状态',
  `operator` varchar(50) DEFAULT NULL COMMENT '任务处理人',
  `create_Time` varchar(50) NOT NULL COMMENT '任务创建时间',
  `finish_Time` varchar(50) DEFAULT NULL COMMENT '任务完成时间',
  `expire_Time` varchar(50) DEFAULT NULL COMMENT '任务期望完成时间',
  `action_Url` varchar(200) DEFAULT NULL COMMENT '任务处理url',
  `parent_Task_Id` varchar(32) DEFAULT NULL COMMENT '父任务ID',
  `variable` varchar(2000) DEFAULT NULL COMMENT '附属变量json存储',
  PRIMARY KEY (`id`),
  KEY `IDX_HIST_TASK_ORDER` (`order_Id`),
  KEY `IDX_HIST_TASK_TASKNAME` (`task_Name`),
  KEY `IDX_HIST_TASK_PARENTTASK` (`parent_Task_Id`),
  CONSTRAINT `FK_HIST_TASK_ORDERID` FOREIGN KEY (`order_Id`) REFERENCES `wf_hist_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='历史任务表';

-- ----------------------------
-- Records of wf_hist_task
-- ----------------------------

-- ----------------------------
-- Table structure for wf_hist_task_actor
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_task_actor`;
CREATE TABLE `wf_hist_task_actor` (
  `task_Id` varchar(32) NOT NULL COMMENT '任务ID',
  `actor_Id` varchar(50) NOT NULL COMMENT '参与者ID',
  KEY `IDX_HIST_TASKACTOR_TASK` (`task_Id`),
  CONSTRAINT `FK_HIST_TASKACTOR` FOREIGN KEY (`task_Id`) REFERENCES `wf_hist_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='历史任务参与者表';

-- ----------------------------
-- Records of wf_hist_task_actor
-- ----------------------------

-- ----------------------------
-- Table structure for wf_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_order`;
CREATE TABLE `wf_order` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `parent_Id` varchar(32) DEFAULT NULL COMMENT '父流程ID',
  `process_Id` varchar(32) NOT NULL COMMENT '流程定义ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '发起人',
  `create_Time` varchar(50) NOT NULL COMMENT '发起时间',
  `expire_Time` varchar(50) DEFAULT NULL COMMENT '期望完成时间',
  `last_Update_Time` varchar(50) DEFAULT NULL COMMENT '上次更新时间',
  `last_Updator` varchar(50) DEFAULT NULL COMMENT '上次更新人',
  `priority` tinyint(1) DEFAULT NULL COMMENT '优先级',
  `parent_Node_Name` varchar(100) DEFAULT NULL COMMENT '父流程依赖的节点名称',
  `order_No` varchar(50) DEFAULT NULL COMMENT '流程实例编号',
  `variable` varchar(2000) DEFAULT NULL COMMENT '附属变量json存储',
  `version` int(3) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `IDX_ORDER_PROCESSID` (`process_Id`),
  KEY `IDX_ORDER_NO` (`order_No`),
  KEY `FK_ORDER_PARENTID` (`parent_Id`),
  CONSTRAINT `FK_ORDER_PARENTID` FOREIGN KEY (`parent_Id`) REFERENCES `wf_order` (`id`),
  CONSTRAINT `FK_ORDER_PROCESSID` FOREIGN KEY (`process_Id`) REFERENCES `wf_process` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程实例表';

-- ----------------------------
-- Records of wf_order
-- ----------------------------

-- ----------------------------
-- Table structure for wf_process
-- ----------------------------
DROP TABLE IF EXISTS `wf_process`;
CREATE TABLE `wf_process` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(100) DEFAULT NULL COMMENT '流程名称',
  `display_Name` varchar(200) DEFAULT NULL COMMENT '流程显示名称',
  `type` varchar(100) DEFAULT NULL COMMENT '流程类型',
  `instance_Url` varchar(200) DEFAULT NULL COMMENT '实例url',
  `state` tinyint(1) DEFAULT NULL COMMENT '流程是否可用',
  `content` longblob COMMENT '流程模型定义',
  `version` int(2) DEFAULT NULL COMMENT '版本',
  `create_Time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  KEY `IDX_PROCESS_NAME` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程定义表';

-- ----------------------------
-- Records of wf_process
-- ----------------------------

-- ----------------------------
-- Table structure for wf_surrogate
-- ----------------------------
DROP TABLE IF EXISTS `wf_surrogate`;
CREATE TABLE `wf_surrogate` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `process_Name` varchar(100) DEFAULT NULL COMMENT '流程名称',
  `operator` varchar(50) DEFAULT NULL COMMENT '授权人',
  `surrogate` varchar(50) DEFAULT NULL COMMENT '代理人',
  `odate` varchar(64) DEFAULT NULL COMMENT '操作时间',
  `sdate` varchar(64) DEFAULT NULL COMMENT '开始时间',
  `edate` varchar(64) DEFAULT NULL COMMENT '结束时间',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `IDX_SURROGATE_OPERATOR` (`operator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='委托代理表';

-- ----------------------------
-- Records of wf_surrogate
-- ----------------------------

-- ----------------------------
-- Table structure for wf_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_task`;
CREATE TABLE `wf_task` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `order_Id` varchar(32) NOT NULL COMMENT '流程实例ID',
  `task_Name` varchar(100) NOT NULL COMMENT '任务名称',
  `display_Name` varchar(200) NOT NULL COMMENT '任务显示名称',
  `task_Type` tinyint(1) NOT NULL COMMENT '任务类型',
  `perform_Type` tinyint(1) DEFAULT NULL COMMENT '参与类型',
  `operator` varchar(50) DEFAULT NULL COMMENT '任务处理人',
  `create_Time` varchar(50) DEFAULT NULL COMMENT '任务创建时间',
  `finish_Time` varchar(50) DEFAULT NULL COMMENT '任务完成时间',
  `expire_Time` varchar(50) DEFAULT NULL COMMENT '任务期望完成时间',
  `action_Url` varchar(200) DEFAULT NULL COMMENT '任务处理的url',
  `parent_Task_Id` varchar(32) DEFAULT NULL COMMENT '父任务ID',
  `variable` varchar(2000) DEFAULT NULL COMMENT '附属变量json存储',
  `version` tinyint(1) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `IDX_TASK_ORDER` (`order_Id`),
  KEY `IDX_TASK_TASKNAME` (`task_Name`),
  KEY `IDX_TASK_PARENTTASK` (`parent_Task_Id`),
  CONSTRAINT `FK_TASK_ORDERID` FOREIGN KEY (`order_Id`) REFERENCES `wf_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';

-- ----------------------------
-- Records of wf_task
-- ----------------------------

-- ----------------------------
-- Table structure for wf_task_actor
-- ----------------------------
DROP TABLE IF EXISTS `wf_task_actor`;
CREATE TABLE `wf_task_actor` (
  `task_Id` varchar(32) NOT NULL COMMENT '任务ID',
  `actor_Id` varchar(50) NOT NULL COMMENT '参与者ID',
  KEY `IDX_TASKACTOR_TASK` (`task_Id`),
  CONSTRAINT `FK_TASK_ACTOR_TASKID` FOREIGN KEY (`task_Id`) REFERENCES `wf_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务参与者表';

-- ----------------------------
-- Records of wf_task_actor
-- ----------------------------
