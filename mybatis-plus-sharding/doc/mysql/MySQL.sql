/* 若存在先删除 */
DROP TABLE IF EXISTS `city`;
/* 城市表 */
CREATE TABLE city (
  `id` varchar(64)  NOT NULL COMMENT '主键 用UUID生成',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `shortName` varchar(10) NOT NULL COMMENT '简称',
  `fullName` varchar(128) NOT NULL COMMENT '全称',
  `province` varchar(32) NOT NULL COMMENT '省份', 
  `postalCode` varchar(16) NOT NULL COMMENT '邮政编码',   
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市表';

/* 初始化数据 */
INSERT INTO city (id, name, shortName, fullName, province, postalCode) 
VALUES (2001, '湛江', '湛', '中国广东省湛江市', '广东', '524200');

INSERT INTO city (id, name, shortName, fullName, province, postalCode) 
VALUES (2002, '深圳', '深', '中国广东省深圳市', '广东', '524206');