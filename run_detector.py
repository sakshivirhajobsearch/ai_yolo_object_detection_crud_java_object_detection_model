import torch
import json

# Load YOLOv5 model (small version)
model = torch.hub.load('ultralytics/yolov5', 'yolov5s', pretrained=True)

# Run detection on an image
results = model('images/input.jpg')

# Convert results to JSON array
detections = []
for *xyxy, conf, cls in results.xyxy[0]:
    detections.append({
        "xmin": float(xyxy[0]),
        "ymin": float(xyxy[1]),
        "xmax": float(xyxy[2]),
        "ymax": float(xyxy[3]),
        "confidence": float(conf),
        "class": int(cls),
        "name": model.names[int(cls)]
    })

# Print only JSON
print(json.dumps(detections))
