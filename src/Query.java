import java.io.File;
import java.util.ArrayList;

public class Query {
	
	private String pathString;
	private String ipAddress;
	private int cpu_id;
	private String date_start;
	private String time_start;
	private String date_end;
	private String time_end;
	
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
				
			}
		}
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
