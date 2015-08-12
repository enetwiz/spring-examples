CREATE TABLE `entities` (
  `id` int(11) NOT NULL,
  `label` varchar(50) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;