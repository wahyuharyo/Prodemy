CREATE TABLE t_user (
   ID             INT(11) NOT NULL AUTO_INCREMENT,
   EMAIL          VARCHAR(100) NOT NULL,
   PASSWORD       VARCHAR(100) NOT NULL,
   FIRSTNAME      VARCHAR(50),
   LASTNAME       VARCHAR(50),
   PROFILEIMAGE	  VARCHAR(50),
   CONSTRAINT PK_USER PRIMARY KEY (ID)
);

CREATE TABLE role (
    ID            VARCHAR(100) NOT NULL,
	NAME          VARCHAR(100) NOT NULL,
	CONSTRAINT PK_ROLE PRIMARY KEY (ID)
);

CREATE TABLE userrole (
    EMAIL         VARCHAR(100) NOT NULL,
	ROLEID        VARCHAR(100) NOT NULL,
	CONSTRAINT PK_USERROLE PRIMARY KEY (EMAIL,ROLEID)
);


INSERT INTO t_user (EMAIL, PASSWORD, FIRSTNAME, LASTNAME) VALUES ('admin@test.com','$2a$12$fV0rJ7oF3NWAm3RIV38WBOy4bipG9YkmulyBG8.zFqaYXnHyGPz4a','Admin','Admin');
INSERT INTO role (ID,NAME) VALUES ('ROLE_ADMIN','Role Administrator');
INSERT INTO role (ID,NAME) VALUES ('ROLE_USER','Role User');
INSERT INTO userrole VALUES ('admin@test.com','ROLE_ADMIN');


CREATE TABLE t_banner (
   ID             INT(11) NOT NULL AUTO_INCREMENT,
   BANNERNAME          VARCHAR(100) NOT NULL,
   BANNERIMAGE       VARCHAR(100) NOT NULL,
   DESCRIPTION      VARCHAR(150),
   CONSTRAINT PK_USER PRIMARY KEY (ID)
);

INSERT INTO t_banner (BANNERNAME, BANNERIMAGE, DESCRIPTION) VALUES 
('Banner 1', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet'),
('Banner 2', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet'),
('Banner 3', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet'),
('Banner 4', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet'),
('Banner 5', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet'),
('Banner 6', 'https://nutech-integrasi.app/dummy.jpg', 'Lerem Ipsum Dolor sit amet');

CREATE TABLE t_services (
   ID             INT(11) NOT NULL AUTO_INCREMENT,
   SERVICE_CODE      VARCHAR(100) NOT NULL,
   SERVICE_NAME      VARCHAR(100) NOT NULL,
   SERVICE_ICON      VARCHAR(100) NOT NULL,
   SERVIICE_TARIF	INT(20) NOT NULL,
   CONSTRAINT PK_USER PRIMARY KEY (ID)
);

INSERT INTO t_services (SERVICE_CODE, SERVICE_NAME, SERVICE_ICON, SERVIICE_TARIF) VALUES
('PAJAK', 'Pajak PBB', 'https://nutech-integrasi.app/dummy.jpg', 40000),
('PLN', 'Listrik', 'https://nutech-integrasi.app/dummy.jpg', 10000),
('PDAM', 'PDAM Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', 40000),
('PULSA', 'Pulsa', 'https://nutech-integrasi.app/dummy.jpg', 40000),
('PGN', 'PGN Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', 50000),
('MUSIK', 'Musik Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', 50000),
('TV', 'TV Berlangganan', 'https://nutech-integrasi.app/dummy.jpg', 50000),
('PAKET_DATA', 'Paket data', 'https://nutech-integrasi.app/dummy.jpg', 50000),
('VOUCHER_GAME', 'Voucher Game', 'https://nutech-integrasi.app/dummy.jpg', 100000),
('VOUCHER_MAKANAN', 'Voucher Makanan"', 'https://nutech-integrasi.app/dummy.jpg', 100000),
('QURBAN', 'Qurban', 'https://nutech-integrasi.app/dummy.jpg', 200000),
('ZAKAT', 'Zakat', 'https://nutech-integrasi.app/dummy.jpg', 300000);