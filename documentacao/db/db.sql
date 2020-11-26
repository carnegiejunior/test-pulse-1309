CREATE TABLE IF NOT EXISTS `db_mateus`.`produtos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `sku` VARCHAR(45) NOT NULL UNIQUE,
  `descricao` VARCHAR(45) NOT NULL UNIQUE,
  `quantidade` DECIMAL(7,2) NOT NULL,
  `preco_unitario` DECIMAL(7,2) NOT NULL,
  `url_foto` text,
  
  PRIMARY KEY (`id`)
    )
ENGINE = InnoDB charset = utf8 collate = utf8_bin;