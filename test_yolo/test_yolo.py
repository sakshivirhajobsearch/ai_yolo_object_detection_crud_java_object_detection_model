import torch
import torchvision
import cv2
import numpy as np
import os

print("Torch version:", torch.__version__)
print("Torchvision version:", torchvision.__version__)
print("OpenCV version:", cv2.__version__)
print("NumPy version:", np.__version__)

# Create images directory if not exists
os.makedirs("images", exist_ok=True)

# Create dummy image
dummy_image_path = os.path.join("images", "dummy_image.jpg")
image = np.zeros((100, 100, 3), dtype=np.uint8)
cv2.imwrite(dummy_image_path, image)
print(f"Dummy image created at: {dummy_image_path}")
