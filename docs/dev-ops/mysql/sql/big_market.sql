/*
 Navicat Premium Dump SQL

 Source Server         : docker-mysql
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : 198.18.0.1:13306
 Source Schema         : big_market

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 16/07/2024 15:24:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `award_id` int NOT NULL COMMENT '抽奖奖品ID,内部流转',
  `award_key` varchar(32) NOT NULL COMMENT '奖品对接标识',
  `award_config` varchar(32) NOT NULL COMMENT '奖品配置信息',
  `award_desc` varchar(128) NOT NULL COMMENT '奖品内容描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of award
-- ----------------------------
BEGIN;
INSERT INTO `award` (`id`, `award_id`, `award_key`, `award_config`, `award_desc`, `create_time`, `update_time`) VALUES (1, 101, 'user_credit_random', '1,100', '用户积分', '2024-07-16 15:20:05', '2024-07-16 15:20:05');
INSERT INTO `award` (`id`, `award_id`, `award_key`, `award_config`, `award_desc`, `create_time`, `update_time`) VALUES (2, 102, 'openai_use_count', '5', 'OpenAI 增加使用次数', '2024-07-16 15:20:42', '2024-07-16 15:20:42');
INSERT INTO `award` (`id`, `award_id`, `award_key`, `award_config`, `award_desc`, `create_time`, `update_time`) VALUES (3, 103, 'openai_use_count', '10', 'OpenAI 增加使用次数', '2024-07-16 15:22:33', '2024-07-16 15:22:33');
INSERT INTO `award` (`id`, `award_id`, `award_key`, `award_config`, `award_desc`, `create_time`, `update_time`) VALUES (4, 104, 'openai_use_count', '20', 'OpenAI 增加使用次数', '2024-07-16 15:22:35', '2024-07-16 15:22:35');
INSERT INTO `award` (`id`, `award_id`, `award_key`, `award_config`, `award_desc`, `create_time`, `update_time`) VALUES (5, 105, 'open_ai_model', 'gpt-4', 'OpenAI 增加模型', '2024-07-16 15:24:03', '2024-07-16 15:24:03');
INSERT INTO `award` (`id`, `award_id`, `award_key`, `award_config`, `award_desc`, `create_time`, `update_time`) VALUES (6, 106, 'open_ai_model', 'dall-e-2', 'OpenAI 增加模型', '2024-07-16 15:24:03', '2024-07-16 15:24:03');
INSERT INTO `award` (`id`, `award_id`, `award_key`, `award_config`, `award_desc`, `create_time`, `update_time`) VALUES (7, 107, 'open_ai_model', 'dall-e-3', 'OpenAI 增加模型', '2024-07-16 15:24:04', '2024-07-16 15:24:04');
INSERT INTO `award` (`id`, `award_id`, `award_key`, `award_config`, `award_desc`, `create_time`, `update_time`) VALUES (8, 108, 'openai_use_count', '100', 'OpenAI 增加使用次数', '2024-07-16 15:23:50', '2024-07-16 15:23:50');
INSERT INTO `award` (`id`, `award_id`, `award_key`, `award_config`, `award_desc`, `create_time`, `update_time`) VALUES (9, 109, 'open_ai_model', 'gpt-4,dall-e-2,dall-e-3', 'OpenAI 增加模型', '2024-07-16 15:24:07', '2024-07-16 15:24:07');
COMMIT;

-- ----------------------------
-- Table structure for strategy
-- ----------------------------
DROP TABLE IF EXISTS `strategy`;
CREATE TABLE `strategy` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `strategy_id` int NOT NULL COMMENT '抽奖的策略ID',
  `strategy_desc` varchar(128) NOT NULL COMMENT '抽奖策略描述',
  `rule_models` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '策略模型',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of strategy
-- ----------------------------
BEGIN;
INSERT INTO `strategy` (`id`, `strategy_id`, `strategy_desc`, `rule_models`, `create_time`, `update_time`) VALUES (1, 10001, '抽奖策略A', NULL, '2024-07-15 21:13:51', '2024-07-15 21:13:51');
COMMIT;

-- ----------------------------
-- Table structure for strategy_award
-- ----------------------------
DROP TABLE IF EXISTS `strategy_award`;
CREATE TABLE `strategy_award` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `strategy_id` int NOT NULL COMMENT '策略ID',
  `award_id` int NOT NULL COMMENT '奖品ID',
  `award_title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '奖品标题',
  `award_subtitle` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '奖品副标题',
  `award_count` int NOT NULL COMMENT '奖品总数量',
  `award_count_surplus` int NOT NULL COMMENT '奖品剩余数量',
  `award_rate` decimal(6,4) NOT NULL COMMENT '奖品中奖概率',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `rule_models` varchar(256) DEFAULT NULL COMMENT '规则模型',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of strategy_award
-- ----------------------------
BEGIN;
INSERT INTO `strategy_award` (`id`, `strategy_id`, `award_id`, `award_title`, `award_subtitle`, `award_count`, `award_count_surplus`, `award_rate`, `sort`, `rule_models`, `create_time`, `update_time`) VALUES (1, 10001, 101, '随机积分', NULL, 80000, 80000, 80.0000, 1, NULL, '2024-07-15 21:15:35', '2024-07-15 21:15:35');
INSERT INTO `strategy_award` (`id`, `strategy_id`, `award_id`, `award_title`, `award_subtitle`, `award_count`, `award_count_surplus`, `award_rate`, `sort`, `rule_models`, `create_time`, `update_time`) VALUES (2, 10001, 102, '5次使用', NULL, 10000, 10000, 10.0000, 2, NULL, '2024-07-15 21:17:00', '2024-07-15 21:17:00');
INSERT INTO `strategy_award` (`id`, `strategy_id`, `award_id`, `award_title`, `award_subtitle`, `award_count`, `award_count_surplus`, `award_rate`, `sort`, `rule_models`, `create_time`, `update_time`) VALUES (3, 10001, 103, '10次使用', NULL, 5000, 5000, 5.0000, 3, NULL, '2024-07-15 21:17:39', '2024-07-15 21:18:29');
INSERT INTO `strategy_award` (`id`, `strategy_id`, `award_id`, `award_title`, `award_subtitle`, `award_count`, `award_count_surplus`, `award_rate`, `sort`, `rule_models`, `create_time`, `update_time`) VALUES (4, 10001, 104, '20次使用', NULL, 4000, 4000, 4.0000, 4, NULL, '2024-07-15 21:18:28', '2024-07-15 21:18:30');
INSERT INTO `strategy_award` (`id`, `strategy_id`, `award_id`, `award_title`, `award_subtitle`, `award_count`, `award_count_surplus`, `award_rate`, `sort`, `rule_models`, `create_time`, `update_time`) VALUES (5, 10001, 105, '增加GPT-4对话模型', NULL, 600, 600, 0.6000, 5, NULL, '2024-07-15 21:19:11', '2024-07-15 21:19:11');
INSERT INTO `strategy_award` (`id`, `strategy_id`, `award_id`, `award_title`, `award_subtitle`, `award_count`, `award_count_surplus`, `award_rate`, `sort`, `rule_models`, `create_time`, `update_time`) VALUES (6, 10001, 106, '增加dall-e-2绘画模型', '抽奖1次后解锁', 200, 200, 0.2000, 6, 'rule_lock_award', '2024-07-15 21:20:12', '2024-07-15 21:40:36');
INSERT INTO `strategy_award` (`id`, `strategy_id`, `award_id`, `award_title`, `award_subtitle`, `award_count`, `award_count_surplus`, `award_rate`, `sort`, `rule_models`, `create_time`, `update_time`) VALUES (7, 10001, 107, '增加dall-e-3绘画模型', '抽奖2次后解锁', 199, 199, 0.1900, 7, 'rule_lock,rule_lock_award', '2024-07-15 21:21:00', '2024-07-15 21:40:18');
INSERT INTO `strategy_award` (`id`, `strategy_id`, `award_id`, `award_title`, `award_subtitle`, `award_count`, `award_count_surplus`, `award_rate`, `sort`, `rule_models`, `create_time`, `update_time`) VALUES (8, 10001, 108, '解锁全部模型', '抽奖6次后解锁', 1, 1, 0.0001, 9, 'rule_lock_award', '2024-07-15 21:21:30', '2024-07-15 21:40:38');
COMMIT;

-- ----------------------------
-- Table structure for strategy_rule
-- ----------------------------
DROP TABLE IF EXISTS `strategy_rule`;
CREATE TABLE `strategy_rule` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `strategy_id` int NOT NULL COMMENT '策略ID',
  `award_id` int DEFAULT NULL COMMENT '奖品ID',
  `rule_type` int NOT NULL DEFAULT '0' COMMENT '抽奖规则类型[1.策略规则 2.奖品规则]',
  `rule_model` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '抽奖规则模型[rule_lock]',
  `rule_value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '抽奖规则值',
  `rule_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '抽奖规则描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of strategy_rule
-- ----------------------------
BEGIN;
INSERT INTO `strategy_rule` (`id`, `strategy_id`, `award_id`, `rule_type`, `rule_model`, `rule_value`, `rule_desc`, `create_time`, `update_time`) VALUES (1, 10001, 101, 2, 'rule_random', '1,1000', '随机积分策略', '2024-07-15 21:25:34', '2024-07-15 21:25:34');
INSERT INTO `strategy_rule` (`id`, `strategy_id`, `award_id`, `rule_type`, `rule_model`, `rule_value`, `rule_desc`, `create_time`, `update_time`) VALUES (2, 10001, 107, 2, 'rule_lock', '1', '抽奖1次解锁', '2024-07-15 21:26:40', '2024-07-15 21:32:23');
INSERT INTO `strategy_rule` (`id`, `strategy_id`, `award_id`, `rule_type`, `rule_model`, `rule_value`, `rule_desc`, `create_time`, `update_time`) VALUES (3, 10001, 108, 2, 'rule_lock', '2', '抽奖2次后解锁', '2024-07-15 21:31:44', '2024-07-15 21:32:20');
INSERT INTO `strategy_rule` (`id`, `strategy_id`, `award_id`, `rule_type`, `rule_model`, `rule_value`, `rule_desc`, `create_time`, `update_time`) VALUES (4, 10001, 109, 2, 'rule_lock', '6', '抽奖6次后解锁', '2024-07-15 21:32:13', '2024-07-15 21:32:13');
INSERT INTO `strategy_rule` (`id`, `strategy_id`, `award_id`, `rule_type`, `rule_model`, `rule_value`, `rule_desc`, `create_time`, `update_time`) VALUES (5, 10001, 107, 2, 'rule_lock_award', '1,500', '随机积分兜底', '2024-07-15 21:37:15', '2024-07-15 21:37:15');
INSERT INTO `strategy_rule` (`id`, `strategy_id`, `award_id`, `rule_type`, `rule_model`, `rule_value`, `rule_desc`, `create_time`, `update_time`) VALUES (6, 10001, NULL, 1, 'rule_weight', '6000:102,103,104,105,106,107,108,109', '消耗6000积分后兜底', '2024-07-15 21:44:15', '2024-07-15 21:44:15');
INSERT INTO `strategy_rule` (`id`, `strategy_id`, `award_id`, `rule_type`, `rule_model`, `rule_value`, `rule_desc`, `create_time`, `update_time`) VALUES (7, 10001, NULL, 1, 'rule_black', '1', '黑名单用户', '2024-07-15 21:45:08', '2024-07-15 21:45:08');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
