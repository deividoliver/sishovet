-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sishovetdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sishovetdb` ;

-- -----------------------------------------------------
-- Schema sishovetdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sishovetdb` DEFAULT CHARACTER SET utf8 ;
USE `sishovetdb` ;

-- -----------------------------------------------------
-- Table `sishovetdb`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sishovetdb`.`usuario` ;

CREATE TABLE IF NOT EXISTS `sishovetdb`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `senha` VARCHAR(100) NOT NULL,
  `perfil` CHAR NOT NULL COMMENT 'A - Administrador\nS - Secretaria\nM - Medico Veterinario\nL - Laboratorista',
  `habilitado` TINYINT(1) NOT NULL DEFAULT 0,
  `crmvpa` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sishovetdb`.`proprietario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sishovetdb`.`proprietario` ;

CREATE TABLE IF NOT EXISTS `sishovetdb`.`proprietario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `nascimento` DATE NOT NULL,
  `rg` VARCHAR(7) NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `telefone_1` VARCHAR(11) NOT NULL,
  `telefone_2` VARCHAR(11) NULL,
  `email` VARCHAR(100) NULL,
  `cep` VARCHAR(8) NULL,
  `endereco` VARCHAR(200) NOT NULL,
  `numero` VARCHAR(10) NOT NULL,
  `complemento` VARCHAR(200) NOT NULL,
  `usuario` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_proprietario_usuario_idx` (`usuario` ASC),
  CONSTRAINT `fk_proprietario_usuario`
    FOREIGN KEY (`usuario`)
    REFERENCES `sishovetdb`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sishovetdb`.`especie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sishovetdb`.`especie` ;

CREATE TABLE IF NOT EXISTS `sishovetdb`.`especie` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sishovetdb`.`raca`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sishovetdb`.`raca` ;

CREATE TABLE IF NOT EXISTS `sishovetdb`.`raca` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `descricao` VARCHAR(100) NULL,
  `especie` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_raca_especie1_idx` (`especie` ASC),
  CONSTRAINT `fk_raca_especie1`
    FOREIGN KEY (`especie`)
    REFERENCES `sishovetdb`.`especie` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sishovetdb`.`animal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sishovetdb`.`animal` ;

CREATE TABLE IF NOT EXISTS `sishovetdb`.`animal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `castrado` TINYINT(1) NOT NULL DEFAULT 0,
  `nascimento` DATE NULL,
  `sexo` CHAR NOT NULL COMMENT 'F - para feminino\nM - para masculino',
  `filhos` INT NULL DEFAULT 0,
  `proprietario` INT NOT NULL,
  `usuario` INT NOT NULL,
  `raca` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_animal_proprietario1_idx` (`proprietario` ASC),
  INDEX `fk_animal_usuario1_idx` (`usuario` ASC),
  INDEX `fk_animal_raca1_idx` (`raca` ASC),
  CONSTRAINT `fk_animal_proprietario1`
    FOREIGN KEY (`proprietario`)
    REFERENCES `sishovetdb`.`proprietario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_animal_usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `sishovetdb`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_animal_raca1`
    FOREIGN KEY (`raca`)
    REFERENCES `sishovetdb`.`raca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sishovetdb`.`atendimento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sishovetdb`.`atendimento` ;

CREATE TABLE IF NOT EXISTS `sishovetdb`.`atendimento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dia` DATE NOT NULL,
  `retorno` DATE NULL,
  `proprietario` INT NOT NULL,
  `animal` INT NOT NULL,
  `custo` DOUBLE NULL,
  `status` CHAR NULL COMMENT 'A - Adicionado\nT - Triagem\nP - Procedimento',
  PRIMARY KEY (`id`),
  INDEX `fk_atendimento_proprietario1_idx` (`proprietario` ASC),
  INDEX `fk_atendimento_animal1_idx` (`animal` ASC),
  CONSTRAINT `fk_atendimento_proprietario1`
    FOREIGN KEY (`proprietario`)
    REFERENCES `sishovetdb`.`proprietario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_atendimento_animal1`
    FOREIGN KEY (`animal`)
    REFERENCES `sishovetdb`.`animal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sishovetdb`.`exame`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sishovetdb`.`exame` ;

CREATE TABLE IF NOT EXISTS `sishovetdb`.`exame` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `descricao` VARCHAR(500) NULL,
  `custo` DOUBLE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sishovetdb`.`resultado_exame`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sishovetdb`.`resultado_exame` ;

CREATE TABLE IF NOT EXISTS `sishovetdb`.`resultado_exame` (
  `id` VARCHAR(45) NOT NULL COMMENT 'id_atendimento#id_exame',
  `coleta` DATE NULL,
  `resultado` DATE NULL,
  `validade` DATE NULL,
  `descricao` VARCHAR(500) NULL,
  `valor` DOUBLE NULL DEFAULT 0.00,
  `custo` DOUBLE NULL,
  `desconto` DOUBLE NULL,
  `isento` TINYINT(1) NULL,
  `atendimento` INT NOT NULL,
  `exame` INT NOT NULL,
  `animal` INT NOT NULL,
  `responsavel` INT NULL,
  INDEX `fk_resultadoExame_animal1_idx` (`animal` ASC),
  INDEX `fk_resultadoExame_usuario1_idx` (`responsavel` ASC),
  INDEX `fk_resultado_exame_atendimento1_idx` (`atendimento` ASC),
  INDEX `fk_resultado_exame_exame1_idx` (`exame` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_resultadoExame_animal1`
    FOREIGN KEY (`animal`)
    REFERENCES `sishovetdb`.`animal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resultadoExame_usuario1`
    FOREIGN KEY (`responsavel`)
    REFERENCES `sishovetdb`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resultado_exame_atendimento1`
    FOREIGN KEY (`atendimento`)
    REFERENCES `sishovetdb`.`atendimento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resultado_exame_exame1`
    FOREIGN KEY (`exame`)
    REFERENCES `sishovetdb`.`exame` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sishovetdb`.`procedimento_medico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sishovetdb`.`procedimento_medico` ;

CREATE TABLE IF NOT EXISTS `sishovetdb`.`procedimento_medico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `dia` DATETIME NOT NULL,
  `descricao` TEXT NOT NULL,
  `hash_code` INT NOT NULL,
  `atendimento` INT NOT NULL,
  `usuario` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_procedimentoMedico_atendimento1_idx` (`atendimento` ASC),
  INDEX `fk_procedimentoMedico_usuario1_idx` (`usuario` ASC),
  CONSTRAINT `fk_procedimentoMedico_atendimento1`
    FOREIGN KEY (`atendimento`)
    REFERENCES `sishovetdb`.`atendimento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_procedimentoMedico_usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `sishovetdb`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sishovetdb`.`vacina`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sishovetdb`.`vacina` ;

CREATE TABLE IF NOT EXISTS `sishovetdb`.`vacina` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `volume` DOUBLE NULL,
  `dia` DATE NULL,
  `descricao` VARCHAR(500) NULL,
  `valor` DECIMAL NULL DEFAULT 0.00,
  `atendimento` INT NOT NULL,
  `usuario` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_vacina_atendimento1_idx` (`atendimento` ASC),
  INDEX `fk_vacina_usuario1_idx` (`usuario` ASC),
  CONSTRAINT `fk_vacina_atendimento1`
    FOREIGN KEY (`atendimento`)
    REFERENCES `sishovetdb`.`atendimento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vacina_usuario1`
    FOREIGN KEY (`usuario`)
    REFERENCES `sishovetdb`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sishovetdb`.`triagem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sishovetdb`.`triagem` ;

CREATE TABLE IF NOT EXISTS `sishovetdb`.`triagem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `peso` DOUBLE NOT NULL,
  `patologia` VARCHAR(500) NULL,
  `dia` DATETIME NOT NULL,
  `animal` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_triagem_animal1_idx` (`animal` ASC),
  CONSTRAINT `fk_triagem_animal1`
    FOREIGN KEY (`animal`)
    REFERENCES `sishovetdb`.`animal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
