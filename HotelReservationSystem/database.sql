-- database.sql
-- Hotel Reservation System Database Schema

CREATE DATABASE IF NOT EXISTS hotel_db;
USE hotel_db;

-- ---------- USER TABLE ----------
CREATE TABLE IF NOT EXISTS user (
    userId    INT AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(50)  NOT NULL UNIQUE,
    password  VARCHAR(100) NOT NULL,
    role      ENUM('ADMIN','RECEPTIONIST','GUEST') NOT NULL
);

-- ---------- ROOM TABLE ----------
CREATE TABLE IF NOT EXISTS room (
    roomId  INT AUTO_INCREMENT PRIMARY KEY,
    type    VARCHAR(50)    NOT NULL,
    price   DECIMAL(10,2)  NOT NULL,
    status  ENUM('AVAILABLE','OCCUPIED','MAINTENANCE') NOT NULL DEFAULT 'AVAILABLE'
);

-- ---------- RESERVATION TABLE ----------
CREATE TABLE IF NOT EXISTS reservation (
    resId      INT AUTO_INCREMENT PRIMARY KEY,
    roomId     INT NOT NULL,
    userId     INT NOT NULL,
    guestName  VARCHAR(100) NOT NULL,
    checkIn    DATE NOT NULL,
    checkOut   DATE NOT NULL,
    FOREIGN KEY (roomId) REFERENCES room(roomId)   ON DELETE CASCADE,
    FOREIGN KEY (userId) REFERENCES user(userId)   ON DELETE CASCADE
);

-- ---------- SAMPLE DATA ----------
INSERT INTO user (username, password, role) VALUES
('admin',       'admin123', 'ADMIN'),
('reception',   'recep123', 'RECEPTIONIST'),
('guest',       'guest123', 'GUEST');

INSERT INTO room (type, price, status) VALUES
('Single Deluxe',   2500.00, 'AVAILABLE'),
('Double Deluxe',   4000.00, 'AVAILABLE'),
('Suite',           7500.00, 'AVAILABLE'),
('Family Room',     5500.00, 'OCCUPIED'),
('Single Economy',  1500.00, 'AVAILABLE');

INSERT INTO reservation (roomId, userId, guestName, checkIn, checkOut) VALUES
(4, 2, 'John Smith', '2024-06-01', '2024-06-05');