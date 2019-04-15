create database toeic
use toeic
CREATE TABLE `toeic`.`phanquyen` (
  `id_phanquyen` varchar(10) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_phanquyen`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE TABLE `toeic`.`nguoidung` (
  `id_nguoidung` INT NOT NULL AUTO_INCREMENT,
  `id_phanquyen` varchar(10) NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `pass` VARCHAR(45) NOT NULL,
  `first_name` NVARCHAR(50) NOT NULL,
  `last_name` NVARCHAR(50) NOT NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id_nguoidung`),
  key `fk_phanquyen_id` (`id_phanquyen`),
  key `user_name` (`user_name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

alter table `toeic`.`nguoidung`  add constraint `fk_loainguoidung_nguoidung` foreign key (`id_phanquyen`) references `toeic`.`phanquyen` (`id_phanquyen`) ON DELETE NO ACTION ON UPDATE CASCADE

CREATE TABLE `toeic`.`diem` (
  `id_diem` INT NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `diem` int default '0',
  PRIMARY KEY (`id_diem`),
  key `user_name` (`user_name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;
alter table `toeic`.`diem`  add constraint `fk_nguoidung_diem` foreign key (`user_name`) references `nguoidung` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
  
CREATE TABLE `toeic`.`cauhoi` (
  `id_cauhoi` INT NOT NULL,
  `id_theloai` varchar(100) not NULL,
  `cauhoi` text default NULL,
  `A` VARCHAR(100)  default NULL,
  `B` VARCHAR(100) default NULL,
  `C` VARCHAR(100) default NULL,
  `D` VARCHAR(100) default NULL,
  `dapan` varchar(100) default NULL,
  `image` blob,
  PRIMARY KEY (`id_cauhoi`),
  key `id_theloai` (`id_theloai`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

alter table `toeic`.`cauhoi` add constraint `fk_theloai_cauhoi`foreign key (`id_theloai`) references `theloai` (`id_theloai`)


CREATE TABLE `toeic`.`theloai` (
  `id_theloai` varchar(100) NOT NULL,
  `name_theloai` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_theloai`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


