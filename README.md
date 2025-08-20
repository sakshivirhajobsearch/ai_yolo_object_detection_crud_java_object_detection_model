# YOLO Object Detection with Java CRUD Backend

## 📌 Overview

This project performs object detection using YOLOv5 (Python) and stores detection metadata in a Java-based backend.

## 📁 Project Structure

- `run_detector.py`: Runs YOLOv5 detection and saves the image.
- `weights/`: YOLOv5 model file (`yolov5s.pt`)
- `images/`: Input images for detection.
- `detected/`: Output images with detected bounding boxes.
- `java-backend/`: Java backend for CRUD operations.
- `db/init.sql`: SQL schema for the `detections` table.

## 🚀 How to Run

### 1. Setup Python Environment

```bash
pip install torch torchvision opencv-python numpy
