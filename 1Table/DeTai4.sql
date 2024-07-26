CREATE TABLE `giaodich` (
  `maGiaoDich` int(11) NOT NULL,
  `ngayGiaoDich` date NOT NULL,
  `donGia` int(11) NOT NULL,
  `loaiNha` varchar(255) NOT NULL,
  `loaiDat` varchar(255) NOT NULL,
  `diaChi` varchar(255) NOT NULL,
  `dienTich` double NOT NULL,
  primary key (`maGiaoDich`)
);

INSERT INTO `giaodich` (`maGiaoDich`, `ngayGiaoDich`, `donGia`, `loaiNha`, `loaiDat`, `diaChi`, `dienTich`) VALUES
(1, '2024/01/13', 50000000, 'Cao cấp', '', 'Đà Lạt', 100),
(2, '2024/01/14', 1000000, '', 'A', '', 100);