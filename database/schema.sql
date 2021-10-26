CREATE DATABASE HieuSach;
GO
use HieuSach;
GO
CREATE TABLE NhanVien (MaNv int IDENTITY NOT NULL, TaiKhoanID int NOT NULL, TenNv nvarchar(50) NULL, SoDienThoai varchar(15) NULL, DiaChi nvarchar(255) NULL, CaLamViec int NULL, ChucNang int NOT NULL, CaLamViec2 int NULL, PRIMARY KEY (MaNv));
CREATE TABLE SanPham (MaSP int IDENTITY NOT NULL, MaNCC int NOT NULL, MaLoai int NOT NULL, TenSp nvarchar(255) NULL, GiaSp float(10) NOT NULL, PRIMARY KEY (MaSP));
CREATE TABLE KhachHang (MaKH int IDENTITY NOT NULL, HoTen nvarchar(50) NULL, SoDienThoai varchar(15) NULL, DiaChi nvarchar(255) NULL, TaiKhoanID int NOT NULL, PRIMARY KEY (MaKH));
CREATE TABLE HoaDon (MaHD int IDENTITY NOT NULL, MaNV int NOT NULL, MaKH int NOT NULL, TongTien float(10) NOT NULL, NgayMua date NULL, PRIMARY KEY (MaHD));
CREATE TABLE ChiTietHoaDon (ID int IDENTITY NOT NULL, MaSP int NOT NULL, MaHD int NOT NULL, SoLuong int NOT NULL, DonGia float(10) NOT NULL, PRIMARY KEY (ID));
CREATE TABLE TaiKhoan (ID int IDENTITY NOT NULL, TaiKhoan varchar(50) NULL, MatKhau varchar(255) NULL, PRIMARY KEY (ID));
CREATE TABLE LoaiSanPham (MaLoai int IDENTITY NOT NULL, TenLoai nvarchar(255) NULL, PRIMARY KEY (MaLoai));
CREATE TABLE NhaCungCap (MaNCC int IDENTITY NOT NULL, TenNCC nvarchar(255) NULL, DiaChi nvarchar(255) NULL, SoDienThoai varchar(255) NULL, PRIMARY KEY (MaNCC));
CREATE TABLE DonDatHang (MaDDH int IDENTITY NOT NULL, maKH int NOT NULL, TongTien float(10) NOT NULL, NgayDat date NULL, tinhTrang int NULL, PRIMARY KEY (MaDDH));
CREATE TABLE ChiTietDonDatHang (ID int IDENTITY NOT NULL, MaDDH int NOT NULL, MaSP int NOT NULL, SoLuong int NOT NULL, DonGia float(10) NOT NULL, PRIMARY KEY (ID));
ALTER TABLE SanPham ADD CONSTRAINT FKSanPham622519 FOREIGN KEY (MaLoai) REFERENCES LoaiSanPham (MaLoai);
ALTER TABLE NhanVien ADD CONSTRAINT FKNhanVien63380 FOREIGN KEY (TaiKhoanID) REFERENCES TaiKhoan (ID);
ALTER TABLE SanPham ADD CONSTRAINT FKSanPham756167 FOREIGN KEY (MaNCC) REFERENCES NhaCungCap (MaNCC);
ALTER TABLE HoaDon ADD CONSTRAINT FKHoaDon785667 FOREIGN KEY (MaKH) REFERENCES KhachHang (MaKH);
ALTER TABLE HoaDon ADD CONSTRAINT FKHoaDon185080 FOREIGN KEY (MaNV) REFERENCES NhanVien (MaNv);
ALTER TABLE ChiTietHoaDon ADD CONSTRAINT FKChiTietHoa443020 FOREIGN KEY (MaHD) REFERENCES HoaDon (MaHD);
ALTER TABLE ChiTietHoaDon ADD CONSTRAINT FKChiTietHoa746492 FOREIGN KEY (MaSP) REFERENCES SanPham (MaSP);
ALTER TABLE DonDatHang ADD CONSTRAINT FKDonDatHang885023 FOREIGN KEY (maKH) REFERENCES KhachHang (MaKH);
ALTER TABLE ChiTietDonDatHang ADD CONSTRAINT FKChiTietDon328320 FOREIGN KEY (MaSP) REFERENCES SanPham (MaSP);
ALTER TABLE ChiTietDonDatHang ADD CONSTRAINT FKChiTietDon937162 FOREIGN KEY (MaDDH) REFERENCES DonDatHang (MaDDH);
ALTER TABLE KhachHang ADD CONSTRAINT FKKhachHang937708 FOREIGN KEY (TaiKhoanID) REFERENCES TaiKhoan (ID);