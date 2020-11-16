
/* 若存在先删除 */
DROP TABLE IF EXISTS `city_extend`;
/* 城市表 */
CREATE TABLE city_extend (
  `id` varchar(64)  NOT NULL COMMENT '主键 用UUID生成',
  `name` varchar(64) NULL COMMENT '名称',
  `shortName` varchar(10) NULL COMMENT '简称',
  `fullName` varchar(128) NULL COMMENT '全称',
  `province` varchar(32) NULL COMMENT '省份', 
  `postalCode` varchar(16) NULL COMMENT '邮政编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市表';

/* 初始化数据 */
INSERT INTO city_extend (id, name, shortName, fullName, province, postalCode) 
VALUES ('2001', '湛江', '湛', '中国广东省湛江市', '广东', '524200');

INSERT INTO city_extend (id, name, shortName, fullName, province, postalCode) 
VALUES ('2002', '深圳', '深', '中国广东省深圳市', '广东', '524206');


/* 城市-扩展表 */
CREATE TABLE city_extend_ext (
  `id` varchar(64)  NOT NULL COMMENT '主键 用UUID生成',
  `cityId` varchar(64)  NOT NULL COMMENT '外键: city_extend.id',
  PRIMARY KEY (`id`),
  FOREIGN KEY (cityId) REFERENCES city_extend(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市-扩展表';

INSERT INTO city_extend_ext (id, cityId) VALUES ('5001', '2001');
INSERT INTO city_extend_ext (id, cityId) VALUES ('5002', '2002');

--`txt1` varchar(128) NULL COMMENT '扩展字段1',
-- `txt2` varchar(128) NULL COMMENT '扩展字段2',
--`txt3` varchar(128) NULL COMMENT '扩展字段3',























