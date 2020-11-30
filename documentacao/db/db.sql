CREATE TABLE IF NOT EXISTS `db_mateus`.`empresas` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
    )
ENGINE = InnoDB charset = utf8 collate = utf8_bin;

INSERT INTO empresas(nome) values("Empresa de teste");

CREATE TABLE IF NOT EXISTS `db_mateus`.`filiais` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL UNIQUE,
  `cnpj` VARCHAR(15) NOT NULL UNIQUE,  
  `empresa_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(`empresa_id`) REFERENCES empresas(`id`)
    )
ENGINE = InnoDB charset = utf8 collate = utf8_bin;

CREATE TABLE IF NOT EXISTS `db_mateus`.`produtos` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `sku` VARCHAR(45) NOT NULL UNIQUE,
  `descricao` VARCHAR(45) NOT NULL UNIQUE,
  `quantidade` DECIMAL(5,2) NOT NULL,
  `preco_unitario` DECIMAL(10,2) NOT NULL,
  `url_foto` text,
  
  PRIMARY KEY (`id`)
    )
ENGINE = InnoDB charset = utf8 collate = utf8_bin;


CREATE TABLE IF NOT EXISTS `db_mateus`.`estoques` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `quantidade` DECIMAL(7,2) NOT NULL DEFAULT 0.0,
  `filial_id` BIGINT NOT NULL,  
  `produto_id` BIGINT NOT NULL,  
  
  PRIMARY KEY (`id`),
  FOREIGN KEY (filial_id) REFERENCES db_mateus.filiais(id),
  FOREIGN KEY (produto_id) REFERENCES db_mateus.produtos(id)
      )
ENGINE = InnoDB charset = utf8 collate = utf8_bin;
