import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Query {
	
	private String pathString;
	private String ipAddress;
	private int cpu_id;
	private String date_start;
	private String time_start;
	private String date_end;
	private String time_end;
	private Map<String, List<String>> fileLines  = new TreeMap<String, List<String>>();
	
	public Query(String pathString) {
		this.pathString = pathString;
	}
	
	public boolean legalPath() {
		File file = new File(this.pathString);
		if(file.exists()) {
			return true;
		}
		return false;
	}
	
	public void readDirectory() {
		final File folder = new File(this.pathString);
		File []listOfFiles = folder.listFiles();
		ArrayList<String> paths = new ArrayList<>();
		for (File name : listOfFiles) {
			paths.add(name.toString());
		}
		
		for (String file : paths ) {
			try {
				Path p = Paths.get(file);
				List<String> s = Files.readAllLines(p);
				fileLines.put(file, s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void findMatches() {
		this.fileLines.forEach((String fileName, List<String> lines) -> {
			lines.forEach((String line) -> {
				String contents[] = line.split(" ");
				long num = Long.parseLong(contents[0]);
				Date date = new java.util.Date(num);
				SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
				sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
				String formattedDate = sdf.format(date);
			});
		});
	}
	
	public void setIP(String IP) {
		this.ipAddress = IP;
	}
	
	public void setCPUID(String s) {
		int id = Integer.parseInt(s);
		this.cpu_id = id;
	}
	
	public void setTimeStart(String date, String time) {
		this.date_start = date;
		this.time_start = time;
	}
	
	public void setTimeEnd(String date, String time) {
		this.date_end = date;
		this.time_end = time;
	}
}
