
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
(12, '2024/03/25', 2550, 'B', '', '', 70);

