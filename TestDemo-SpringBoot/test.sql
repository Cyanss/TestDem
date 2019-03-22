/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.7.18-log : Database - coupon
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `test`;

/*Table structure for table `change_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名称',
  `user_phone` varchar(13) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户手机号',
  `user_email` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户邮箱',
  `user_password` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户密码',
  `user_auth` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户权限',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  key `idx_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `plugin_info`;

CREATE TABLE `plugin_info` (
  `plugin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '插件编号',
  `user_id` int(11) NOT NULL COMMENT '用户编号',
  `plugin_name` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '插件名称',
  `plugin_type` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '插件类型',
  `plugin_icon` varchar(500) COLLATE utf8mb4_unicode_ci COMMENT '插件图标',
  `plugin_env` varchar(100) COLLATE utf8mb4_unicode_ci  COMMENT '插件环境',
  `plugin_keyword` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '插件关键字',
  `plugin_url` varchar(500) COLLATE utf8mb4_unicode_ci COMMENT '插件地址',
  `plugin_content` varchar(1000) COLLATE utf8mb4_unicode_ci COMMENT '插件内容描述',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`plugin_id`),
  key `idx_user_id` (`user_id`),
  key `idx_plugin_name` (`plugin_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
   `id` bigint(15) unsigned zerofill NOT NULL AUTO_INCREMENT,
   `resource_id` varchar(15) DEFAULT NULL COMMENT '资源id',
   `file_name` varchar(100) NOT NULL COMMENT '上传后的文件名',
   `file_origin_name` varchar(100) NOT NULL COMMENT '原始文件名',
   `file_path` varchar(100) NOT NULL COMMENT '上传后的路径',
   `file_type` varchar(100) NOT NULL COMMENT '文件类型',
   `md5` varchar(255) DEFAULT NULL,
   `valid` tinyint(2) NOT NULL COMMENT '文件是否有效(true/1: 有效 ;false/0: 无效)',
   `is_delete` tinyint(2) NOT NULL COMMENT '是否删除(true/1 : 已删除 ; false/0: 未删除)',
   `size` bigint(50) NOT NULL COMMENT '文件大小',
   `upload_time` varchar(50) NOT NULL COMMENT '上传时间',
   `delete_time` varchar(50) DEFAULT NULL COMMENT '删除时间',
   `created_by` varchar(20) DEFAULT NULL,
   `created_date` date DEFAULT NULL,
   `updated_by` varchar(20) DEFAULT NULL,
   `updated_date` date DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
