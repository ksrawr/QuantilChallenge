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
	private int time_start;
	private String date_end;
	private int time_end;
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
			System.out.println("Content of " + fileName + "is:");
			lines.forEach((String line) -> {
//				String contents[] = line.split(" ");
//				long num = Long.parseLong(contents[0]);
//				Date date = new java.util.Date(num);
//				SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
//				sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
//				String formattedDate = sdf.format(date);
				String contents[] = line.split(" ");
//				String timeStamp = this.convertTimeStamp(contents[0]);
				long num = Long.parseLong(contents[0]);
				Date date = new java.util.Date(num);
				SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
				sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
				String formattedDate = sdf.format(date);
				String ipAddress = contents[1];
				String cpuID = contents[2];
				String cpuUsage = contents[3];
				System.out.println(contents[0]);
				System.out.println(formattedDate);
				System.out.println(ipAddress);
				System.out.println(cpuID);
				System.out.println(cpuUsage);
//				if(timeStamp === this.date_end) 
//				System.out.println(line);
			});
			System.out.println("————————————————————————————————");
		});
	}
	
	public String convertTimeStamp(String time) {
		long num = Long.parseLong(time);
		Date date = new java.util.Date(num);
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
		String formattedDate = sdf.format(date);
		date = null;
		sdf = null;
		return formattedDate;
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
		String temp[] = time.split(":");
		String number = temp[0] + temp[1];
		this.time_start = Integer.parseInt(number);
	}
	
	public void setTimeEnd(String date, String time) {
		this.date_end = date;
		String temp[] = time.split(":");
		String number = temp[0] + temp[1];
		this.time_end = Integer.parseInt(number);
	}
}
