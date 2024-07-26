
CREATE TABLE `transaction` (
  `maGiaoDich` int(11) NOT NULL,
  `ngayGiaoDich` date NOT NULL,
  `donGia` int(11) NOT NULL,
  `loaiDat` varchar(255) NOT NULL,
  `loaiNha` varchar(255) NOT NULL,
  `diaChi` varchar(255) NOT NULL,
  `dienTich` double NOT NULL,
  primary key (`maGiaoDich`)
);

INSERT INTO `transaction` (`maGiaoDich`, `ngayGiaoDich`, `donGia`, `loaiDat`, `loaiNha`, `diaChi`, `dienTich`) VALUES
(1, '2024/01/02', 2500, 'A', '', '', 30),
(2, '2024/01/13', 5000, '', 'Cao cấp', 'Đà Lạt', 100),
(3, '2024/01/23', 1500, '', 'Thường', 'Bình Thuận', 50),
(4, '2024/01/25', 1500, 'B', '', '', 40),
(5, '2024/02/03', 2500, '', 'Thường', 'Bình Dương', 30),
(6, '2024/02/10', 2000, 'C', '', '', 100),
(7, '2024/02/25', 6500, '', 'Cao cấp', 'HCM', 90),
(8, '2024/02/28', 2000, '', 'Cao cấp', 'Vũng Tàu', 100),
(9, '2024/03/02', 1200, '', 'Thường', 'HCM', 40),
(10, '2024/03/12', 1350, 'C', '', '', 65),
(11, '2024/03/23', 2000, '', 'Thường', 'Đồng Nai', 50),
(12, '2024/03/25', 2550, 'B', '', '', 70),
(13, '2024/03/27', 1150, 'B', '', '', 150),
(14, '2024/04/03', 100, 'A', '', '', 23),
(15, '2024/04/15', 2250, 'B', '', '', 87),
(16, '2024/04/15', 1000, '', 'Thường', 'Long An', 50),
(17, '2024/04/23', 950, 'C', '', '', 80),
(18, '2024/05/17', 950, 'C', '', '', 40),
(19, '2024/05/20', 1050, 'B', '', '', 75),
(20, '2024/05/25', 4500, '', 'Cao cấp', 'Đà Lạt', 60);
