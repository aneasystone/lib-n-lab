DROP TABLE IF EXISTS person;

CREATE TABLE `person` (
  `id` int(13) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(33) DEFAULT NULL COMMENT '姓名',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `person` (`name`, `age`) VALUES
('zhangsan', 30),
('lisi', 31),
('wanger', 32),
('mazi', 33);