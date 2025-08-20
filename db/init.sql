CREATE DATABASE IF NOT EXISTS yolo_db;
USE yolo_db;

CREATE TABLE detections (
  id INT AUTO_INCREMENT PRIMARY KEY,
  image_path VARCHAR(255),
  label VARCHAR(50),
  confidence FLOAT
);
