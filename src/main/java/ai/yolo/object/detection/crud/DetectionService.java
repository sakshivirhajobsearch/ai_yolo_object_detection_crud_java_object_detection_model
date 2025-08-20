package ai.yolo.object.detection.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetectionService {

	public void createDetection(Detection d) throws SQLException {
		Connection conn = Database.connect();
		String sql = "INSERT INTO detections (image_path, label, confidence) VALUES (?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, d.getImagePath());
		stmt.setString(2, d.getLabel());
		stmt.setFloat(3, d.getConfidence());
		stmt.executeUpdate();
		conn.close();
	}

	public List<Detection> readDetections() throws SQLException {
		Connection conn = Database.connect();
		ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM detections");
		List<Detection> list = new ArrayList<>();
		while (rs.next()) {
			Detection d = new Detection();
			d.setId(rs.getInt("id"));
			d.setImagePath(rs.getString("image_path"));
			d.setLabel(rs.getString("label"));
			d.setConfidence(rs.getFloat("confidence"));
			list.add(d);
		}
		conn.close();
		return list;
	}

	// updateDetection, deleteDetection similarly
}
