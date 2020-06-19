-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 19, 2019 lúc 11:42 AM
-- Phiên bản máy phục vụ: 10.1.33-MariaDB
-- Phiên bản PHP: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `finalexam_java`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `info`
--

CREATE TABLE `info` (
  `ID` int(11) NOT NULL,
  `Name` text NOT NULL,
  `Class` text NOT NULL,
  `Math` float NOT NULL,
  `Phys` float NOT NULL,
  `Chem` float NOT NULL,
  `Aver` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `info`
--

INSERT INTO `info` (`ID`, `Name`, `Class`, `Math`, `Phys`, `Chem`, `Aver`) VALUES
(3, 'Thanh Dat', '18IT1', 8, 9, 10, 9),
(5, 'Hoang Vu', '18IT1', 8, 8, 9, 8.33333),
(6, 'Huong Mai', '18IT4', 8.5, 8, 9, 8.5),
(7, 'Hong Hanh', '18IT1', 8, 9, 9.5, 8.83333),
(8, 'My Duyen', '18IT2', 8.5, 9.25, 9, 8.91667),
(9, 'Mai Nhi', '18IT2', 8, 9, 9.5, 8.83333),
(10, 'Phuoc Dai', '18IT1', 6, 7.5, 8, 7.16667),
(11, 'Duc Bao', '18CE', 7, 7.5, 8, 7.5);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `info`
--
ALTER TABLE `info`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `info`
--
ALTER TABLE `info`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
