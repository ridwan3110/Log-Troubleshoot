/*
 Navicat Premium Data Transfer

 Source Server         : localhostmysql
 Source Server Type    : MySQL
 Source Server Version : 50624
 Source Host           : localhost:3306
 Source Schema         : log

 Target Server Type    : MySQL
 Target Server Version : 50624
 File Encoding         : 65001

 Date: 19/06/2019 10:17:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for et
-- ----------------------------
DROP TABLE IF EXISTS `et`;
CREATE TABLE `et`  (
  `No_Perangkat` int(11) NOT NULL AUTO_INCREMENT,
  `Nama_Perangkat` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ID` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IP` int(11) NOT NULL,
  PRIMARY KEY (`No_Perangkat`) USING BTREE,
  INDEX `fk_stasiun_ipnya_idx`(`IP`) USING BTREE,
  CONSTRAINT `fk_stasiun_ipnya` FOREIGN KEY (`IP`) REFERENCES `stasiun` (`IP`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 2001 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
  `Nomer` bigint(20) NOT NULL AUTO_INCREMENT,
  `No_User` int(11) NOT NULL,
  `d_login` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `d_logout` timestamp(0) NULL DEFAULT '0000-00-00 00:00:00',
  `login_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`Nomer`) USING BTREE,
  INDEX `fk_user_login`(`No_User`) USING BTREE,
  CONSTRAINT `fk_user_login` FOREIGN KEY (`No_User`) REFERENCES `user` (`No_User`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for penyebab
-- ----------------------------
DROP TABLE IF EXISTS `penyebab`;
CREATE TABLE `penyebab`  (
  `No_Penyebab` int(11) NOT NULL AUTO_INCREMENT,
  `Penyebab` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Kategori` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `No_PVM` int(11) NOT NULL,
  PRIMARY KEY (`No_Penyebab`) USING BTREE,
  INDEX `fk_perangkat_vm_idx`(`No_PVM`) USING BTREE,
  CONSTRAINT `fk_perangkat_vm` FOREIGN KEY (`No_PVM`) REFERENCES `perangkat_vm` (`No_PVM`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 466 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for perangkat_vm
-- ----------------------------
DROP TABLE IF EXISTS `perangkat_vm`;
CREATE TABLE `perangkat_vm`  (
  `No_PVM` int(11) NOT NULL AUTO_INCREMENT,
  `Nama_Perangkat` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Jenis` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`No_PVM`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 203 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for projek
-- ----------------------------
DROP TABLE IF EXISTS `projek`;
CREATE TABLE `projek`  (
  `No_Projek` int(11) NOT NULL AUTO_INCREMENT,
  `Nama_Projek` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `initial` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `b_active` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`No_Projek`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for stasiun
-- ----------------------------
DROP TABLE IF EXISTS `stasiun`;
CREATE TABLE `stasiun`  (
  `IP` int(11) NOT NULL,
  `Nama_Stasiun` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `No_Projek` int(11) NOT NULL,
  PRIMARY KEY (`IP`) USING BTREE,
  INDEX `fk_projek_id_idx`(`No_Projek`) USING BTREE,
  CONSTRAINT `fk_projek_id` FOREIGN KEY (`No_Projek`) REFERENCES `projek` (`No_Projek`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for trouble_damri
-- ----------------------------
DROP TABLE IF EXISTS `trouble_damri`;
CREATE TABLE `trouble_damri`  (
  `No` int(11) NOT NULL AUTO_INCREMENT,
  `Tanggal_Masalah` date NOT NULL,
  `Jam_Masalah` time(0) NOT NULL,
  `Tanggal_Done` date NOT NULL,
  `Jam_Done` time(0) NOT NULL,
  `IP` int(11) NOT NULL,
  `Problem` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `No_Penyebab` int(11) NOT NULL,
  `Solusi` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `No_Perangkat` int(11) NOT NULL,
  `Saldo_Sebelum` bigint(20) NULL DEFAULT NULL,
  `Saldo_Sesudah` bigint(20) NULL DEFAULT NULL,
  `Status` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `No_User` int(11) NOT NULL,
  PRIMARY KEY (`No`) USING BTREE,
  INDEX `fk_stasiun_ip_idx`(`IP`) USING BTREE,
  INDEX `fk_ET_idx`(`No_Perangkat`) USING BTREE,
  INDEX `fk_user_idx`(`No_User`) USING BTREE,
  INDEX `fk_penyebab_NO_idx`(`No_Penyebab`) USING BTREE,
  CONSTRAINT `fk_ET00` FOREIGN KEY (`No_Perangkat`) REFERENCES `et` (`No_Perangkat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_penyebab_NO0` FOREIGN KEY (`No_Penyebab`) REFERENCES `penyebab` (`No_Penyebab`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_stasiun_ip00` FOREIGN KEY (`IP`) REFERENCES `stasiun` (`IP`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user00` FOREIGN KEY (`No_User`) REFERENCES `user` (`No_User`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for trouble_et
-- ----------------------------
DROP TABLE IF EXISTS `trouble_et`;
CREATE TABLE `trouble_et`  (
  `No` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Tanggal_Masalah` date NOT NULL,
  `Jam_Masalah` time(0) NOT NULL,
  `Tanggal_Done` date NOT NULL,
  `Jam_Done` time(0) NOT NULL,
  `JenisLaporan` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `No_Projek` int(11) NOT NULL,
  `IP` int(11) NOT NULL,
  `No_Perangkat` int(11) NOT NULL,
  `No_PVM` int(11) NULL DEFAULT NULL,
  `Problem` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `No_Penyebab` int(11) NULL DEFAULT NULL,
  `Solusi` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Status` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `No_User` int(11) NOT NULL,
  `Sumber` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RefNumber` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `RefnoTrouble` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Teknisi` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pic_before` longblob NULL,
  `pic_after` longblob NULL,
  `totaldowntime` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Arah_gate` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`No`) USING BTREE,
  INDEX `fk_stasiun_ip_idx`(`IP`) USING BTREE,
  INDEX `fk_ET_idx`(`No_Perangkat`) USING BTREE,
  INDEX `fk_user_idx`(`No_User`) USING BTREE,
  INDEX `fk_projek_Noprojek_idx`(`No_Projek`) USING BTREE,
  INDEX `fk_problem_no_problem_idx`(`No_Penyebab`) USING BTREE,
  INDEX `fk_pervm_nopvm_idx`(`No_PVM`) USING BTREE,
  INDEX `id`(`No`) USING BTREE,
  INDEX `ind_status`(`Status`) USING BTREE,
  INDEX `ind_penyebab`(`No_Penyebab`) USING BTREE,
  INDEX `ind_tgldone`(`Tanggal_Done`) USING BTREE,
  INDEX `ind_projek`(`No_Projek`) USING BTREE,
  CONSTRAINT `fk_ET` FOREIGN KEY (`No_Perangkat`) REFERENCES `et` (`No_Perangkat`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pervm_nopvm` FOREIGN KEY (`No_PVM`) REFERENCES `perangkat_vm` (`No_PVM`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_problem_no_problem` FOREIGN KEY (`No_Penyebab`) REFERENCES `penyebab` (`No_Penyebab`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_projek_Noprojek` FOREIGN KEY (`No_Projek`) REFERENCES `projek` (`No_Projek`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_stasiun_ip` FOREIGN KEY (`IP`) REFERENCES `stasiun` (`IP`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user` FOREIGN KEY (`No_User`) REFERENCES `user` (`No_User`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for trouble_vm
-- ----------------------------
DROP TABLE IF EXISTS `trouble_vm`;
CREATE TABLE `trouble_vm`  (
  `No` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Tanggal_Masalah` date NOT NULL,
  `Jam_Masalah` time(0) NOT NULL,
  `Tanggal_Done` date NOT NULL,
  `Jam_Done` time(0) NOT NULL,
  `IP` int(11) NOT NULL,
  `No_Perangkat` int(11) NOT NULL,
  `Problem` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Solusi` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `No_PVM` int(11) NOT NULL,
  `No_Penyebab` int(11) NOT NULL,
  `Status` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Analisa` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `No_User` int(11) NOT NULL,
  `No_Projek` int(11) NOT NULL,
  PRIMARY KEY (`No`) USING BTREE,
  INDEX `fk_stasiun_ip_idx`(`IP`) USING BTREE,
  INDEX `fk_ET_idx`(`No_Perangkat`) USING BTREE,
  INDEX `fk_user_idx`(`No_User`) USING BTREE,
  INDEX `fk_PVM_no_idx`(`No_PVM`) USING BTREE,
  INDEX `fk_penyebab_NO_idx`(`No_Penyebab`) USING BTREE,
  INDEX `fk_projek_No_idx`(`No_Projek`) USING BTREE,
  CONSTRAINT `fk_ET0` FOREIGN KEY (`No_Perangkat`) REFERENCES `et` (`No_Perangkat`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PVM_no` FOREIGN KEY (`No_PVM`) REFERENCES `perangkat_vm` (`No_PVM`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_penyebab_NO` FOREIGN KEY (`No_Penyebab`) REFERENCES `penyebab` (`No_Penyebab`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_projek_No` FOREIGN KEY (`No_Projek`) REFERENCES `projek` (`No_Projek`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_stasiun_ip0` FOREIGN KEY (`IP`) REFERENCES `stasiun` (`IP`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user0` FOREIGN KEY (`No_User`) REFERENCES `user` (`No_User`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `No_User` int(11) NOT NULL AUTO_INCREMENT,
  `Nama_User` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Username` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Alamat` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Telepon` int(11) NOT NULL,
  `Status` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`No_User`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- View structure for aplikasigate
-- ----------------------------
DROP VIEW IF EXISTS `aplikasigate`;
CREATE ALGORITHM = UNDEFINED DEFINER = ``@`%` SQL SECURITY DEFINER VIEW `aplikasigate` AS (select `trouble_et`.`Tanggal_Done` AS `Tanggal_Done`,`perangkat_vm`.`Jenis` AS `Jenis`,`perangkat_vm`.`Nama_Perangkat` AS `Nama_Perangkat`,`penyebab`.`Penyebab` AS `Penyebab`,count(0) AS `jumlah` from (((`trouble_et` join `penyebab` on((`trouble_et`.`No_Penyebab` = `penyebab`.`No_Penyebab`))) join `perangkat_vm` on((`trouble_et`.`No_PVM` = `perangkat_vm`.`No_PVM`))) join `projek` on((`trouble_et`.`No_Projek` = `projek`.`No_Projek`))) where (`projek`.`Nama_Projek` = 'KCI') group by `perangkat_vm`.`Nama_Perangkat`,`penyebab`.`Penyebab`) ;

SET FOREIGN_KEY_CHECKS = 1;
