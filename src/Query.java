import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
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
	private String cpu_id;
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
		System.out.println("CPU" + this.cpu_id + " usage on " + this.ipAddress + ":");
		this.fileLines.forEach((String fileName, List<String> lines) -> {
			lines.forEach((String line) -> {
				
				String contents[] = line.split(" ");
				long num = Long.parseLong(contents[0]);
				Date date = new Date(num * 1000);
				SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
				sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
				String formattedDate = sdf.format(date);
				
				String ipAddress = contents[1];
				String cpuID = contents[2];
				String cpuUsage = contents[3];
				
				String fdContents[] = formattedDate.split(" ");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm");
				
				try {
					Date dateAfter = sdf2.parse(this.date_start);
					Date dateBefore = sdf2.parse(this.date_end);
					Date fileDate = sdf2.parse(fdContents[0]);

					Date timeStart = sdf3.parse(this.time_start);
					Date timeEnd = sdf3.parse(this.time_end);
					Date fileTime = sdf3.parse(fdContents[1]);
					
					if(ipAddress.equals(this.ipAddress) && cpuID.equals(this.cpu_id)) {
						if(fileDate.equals(dateAfter) && fileDate.equals(dateBefore)) {
							if(fileTime.equals(timeStart)) {
								System.out.print("(" + fdContents[0] + " " + fdContents[1] + " " + cpuUsage + "%), ");
							} else if(fileTime.after(timeStart)) {
								System.out.print("(" + fdContents[0] + " " + fdContents[1] + " " + cpuUsage + "%), ");
							} else if(fileTime.before(timeEnd)) {
								System.out.print("(" + fdContents[0] + " " + fdContents[1] + " " + cpuUsage + "%), ");
							}
						}
					} 
				} catch (ParseException e) {
					e.printStackTrace();
				}
			});
		});
		System.out.print("\n");
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
		this.cpu_id = s;
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
