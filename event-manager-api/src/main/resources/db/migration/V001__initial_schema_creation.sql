CREATE TABLE `instituicao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `evento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instituicao_id` int(11) NOT NULL,
  `nome` varchar(500) NOT NULL,
  `data_atualizacao` timestamp NOT NULL,
  `data_inicial` timestamp NOT NULL,
  `data_final` timestamp NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  `finalizado` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `instituicao_id` (`instituicao_id`),
  CONSTRAINT `FK_evento_instituicao_id` FOREIGN KEY (`instituicao_id`) REFERENCES `instituicao` (`id`)
);
