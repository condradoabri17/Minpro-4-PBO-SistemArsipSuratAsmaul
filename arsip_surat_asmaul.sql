-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 10, 2026 at 01:11 PM
-- Server version: 8.4.3
-- PHP Version: 8.3.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `arsip_surat_asmaul`
--

-- --------------------------------------------------------

--
-- Table structure for table `penghuni`
--

CREATE TABLE `penghuni` (
  `nim` varchar(20) NOT NULL,
  `nama` varchar(150) NOT NULL,
  `asal_daerah` varchar(100) NOT NULL,
  `fakultas` varchar(100) NOT NULL,
  `jurusan` varchar(100) NOT NULL,
  `status` enum('CALON PENGHUNI AKTIF','PENGHUNI AKTIF') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `keterangan` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `penghuni`
--

INSERT INTO `penghuni` (`nim`, `nama`, `asal_daerah`, `fakultas`, `jurusan`, `status`, `keterangan`) VALUES
('2409086008', 'Aditya Harsa Arga Putra', 'Balikpapan', 'TEKNIK', 'S1-Teknik Geologi', 'PENGHUNI AKTIF', NULL),
('2509066031', 'Reno Abdul Virman', 'Bau-Bau', 'TEKNIK', 'S1-Teknik Kimia', 'PENGHUNI AKTIF', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `surat_keluar`
--

CREATE TABLE `surat_keluar` (
  `id_surat_keluar` int NOT NULL,
  `urutan_surat` int NOT NULL,
  `nomor_surat` varchar(100) NOT NULL,
  `perihal` varchar(255) NOT NULL,
  `kategori` int NOT NULL,
  `tanggal_keluar` date NOT NULL,
  `penerima` varchar(150) NOT NULL
) ;

--
-- Dumping data for table `surat_keluar`
--

INSERT INTO `surat_keluar` (`id_surat_keluar`, `urutan_surat`, `nomor_surat`, `perihal`, `kategori`, `tanggal_keluar`, `penerima`) VALUES
(1, 1, '03/001/UKMAsramaPutra/2026', 'Surat Permohonan Kegiatan', 3, '2026-09-21', 'Fakultas Teknik'),
(2, 2, '01/002/UKMAsramaPutra/2026', 'Surat Undangan Kegiatan', 1, '2026-09-23', 'Universitas Mulawarman');

-- --------------------------------------------------------

--
-- Table structure for table `surat_masuk`
--

CREATE TABLE `surat_masuk` (
  `id_surat_masuk` int NOT NULL,
  `urutan_surat` int NOT NULL,
  `nomor_surat` varchar(100) NOT NULL,
  `perihal` varchar(255) NOT NULL,
  `tanggal_masuk` date NOT NULL,
  `pengirim` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `surat_masuk`
--

INSERT INTO `surat_masuk` (`id_surat_masuk`, `urutan_surat`, `nomor_surat`, `perihal`, `tanggal_masuk`, `pengirim`) VALUES
(1, 1, '001/Fakultas-Teknik/2026', 'Surat Undangan Rapat', '2026-09-20', 'Fakultas Teknik'),
(2, 2, '002/Universitas-Mulawarman/2026', 'Surat Pemberitahuan Kegiatan', '2026-09-22', 'Universitas Mulawarman');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `penghuni`
--
ALTER TABLE `penghuni`
  ADD PRIMARY KEY (`nim`);

--
-- Indexes for table `surat_keluar`
--
ALTER TABLE `surat_keluar`
  ADD PRIMARY KEY (`id_surat_keluar`),
  ADD UNIQUE KEY `urutan_surat` (`urutan_surat`),
  ADD UNIQUE KEY `nomor_surat` (`nomor_surat`);

--
-- Indexes for table `surat_masuk`
--
ALTER TABLE `surat_masuk`
  ADD PRIMARY KEY (`id_surat_masuk`),
  ADD UNIQUE KEY `urutan_surat` (`urutan_surat`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `surat_keluar`
--
ALTER TABLE `surat_keluar`
  MODIFY `id_surat_keluar` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `surat_masuk`
--
ALTER TABLE `surat_masuk`
  MODIFY `id_surat_masuk` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
