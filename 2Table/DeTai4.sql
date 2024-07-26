CREATE TABLE `house` (
  `maGiaoDich` int(11) NOT NULL,
  `ngayGiaoDich` date NOT NULL,
  `donGia` int(11) NOT NULL,
  `loaiNha` varchar(255) NOT NULL,
  `diaChi` varchar(255) NOT NULL,
  `dienTich` double NOT NULL,
  primary key (`maGiaoDich`)
);

INSERT INTO `house` (`maGiaoDich`, `ngayGiaoDich`, `donGia`, `loaiNha`, `diaChi`, `dienTich`) VALUES
(1, '2024/01/13', 50000000, 'Cao cấp', 'Đà Lạt', 100),
(2, '2024/01/23', 15000000, 'Bình thường', 'Bình Thuận', 50),
(3, '2024/02/03', 25000000, 'Bình thường', 'Bình Dương', 30),
(4, '2024/02/25', 65000000, 'Cao cấp', 'HCM', 90),
(5, '2024/02/28', 20000000, 'Cao cấp', 'Vũng Tàu', 100),
(6, '2024/03/02', 12000000, 'Bình thường', 'HCM', 40),
(7, '2024/03/23', 20000000, 'Bình thường', 'Đồng Nai', 50),
(8, '2024/04/15', 10000000, 'Bình thường', 'Long An', 50),
(9, '2024/05/20', 30000000, 'Cao cấp', 'HCM', 80),
(10, '2024/05/25', 45000000, 'Cao cấp', 'Đà Lạt', 60),
(11, '2024/06/01', 25000000, 'Bình thường', 'Bình Phước', 40),
(12, '2024/06/21', 55000000, 'Bình thường', 'Hà Nội', 60),
(13, '2024/07/16', 25000000, 'Bình thường', 'Cà Mau', 70),
(14, '2024/07/23', 35000000, 'Bình thường', 'HCM', 80);

CREATE TABLE `land` (
  `maGiaoDich` int(11) NOT NULL,
  `ngayGiaoDich` date NOT NULL,
  `donGia` int(11) NOT NULL,
  `loaiDat` varchar(255) NOT NULL,
  `dienTich` double NOT NULL,
  primary key (`maGiaoDich`)
);

INSERT INTO `land` (`maGiaoDich`, `ngayGiaoDich`, `donGia`, `loaiDat`, `dienTich`) VALUES
(1, '2024/01/02', 25000000, 'A', 30),
(2, '2024/01/25', 15000000, 'B', 40),
(3, '2024/02/10', 20000000, 'C', 100),
(4, '2024/03/12', 13500000, 'C', 65),
(5, '2024/03/25', 25500000, 'B', 70),
(6, '2024/03/27', 11500000, 'B', 150),
(7, '2024/04/03', 1000000, 'A', 23),
(8, '2024/04/10', 6000000, 'C', 66),
(9, '2024/04/15', 22500000, 'B', 87),
(10, '2024/04/23', 9500000, 'C', 80),
(11, '2024/05/03', 14000000, 'A', 38),
(12, '2024/05/17', 9500000, 'C', 40),
(13, '2024/05/20', 10500000, 'B', 75),
(14, '2024/06/08', 23500000, 'A', 40),
(15, '2024/06/28', 7850000, 'B', 110),
(16, '2024/07/23', 10500000, 'B', 90);