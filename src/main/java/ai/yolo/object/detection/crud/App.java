package ai.yolo.object.detection.crud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

public class App {

    // Java class matching YOLO JSON output
    static class Detection {
        double xmin;
        double ymin;
        double xmax;
        double ymax;
        double confidence;

        @SerializedName("class")
        int classId; // 'class' is reserved in Java

        String name;

        @Override
        public String toString() {
            return name + " [" + confidence + "] at (" + xmin + "," + ymin + ") -> (" + xmax + "," + ymax + ")";
        }
    }

    public static void main(String[] args) {
        try {
            // Run YOLO Python script
            ProcessBuilder pb = new ProcessBuilder("python", "run_detector.py", "images/input.jpg");
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Read Python stdout and keep only the last line (the JSON array)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String jsonOutput = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    jsonOutput = line; // overwrite until last line
                }
            }
            process.waitFor();

            if (jsonOutput == null) {
                throw new RuntimeException("No JSON output received from Python script!");
            }

            System.out.println("Raw JSON Output: " + jsonOutput);

            // Parse JSON
            Gson gson = new Gson();
            List<Detection> detectionList = new ArrayList<>();
            JsonElement element = JsonParser.parseString(jsonOutput);

            if (element.isJsonArray()) {
                Detection[] detections = gson.fromJson(element, Detection[].class);
                for (Detection d : detections) detectionList.add(d);
            } else if (element.isJsonObject()) {
                Detection detection = gson.fromJson(element, Detection.class);
                detectionList.add(detection);
            } else {
                System.out.println("Unexpected JSON format: " + jsonOutput);
            }

            // Print parsed detections
            System.out.println("Parsed Detections:");
            for (Detection d : detectionList) {
                System.out.println(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
