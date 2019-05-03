import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import static java.nio.file.StandardOpenOption.*;
import java.io.*;

public class Simulator {

	private String ipAddress;
	private int cpuID = 0;
	private int cpuUsage;
	private final String delimiter = " ";
	private String fileName;
	private String pathString;
	private Path p;
	
	public Simulator(String fileName) {
		this.fileName = "src/" + fileName;		
	}
	
	public String generateLogHeader() {	
		String header = "timestamp" + this.delimiter + "IP" + this.delimiter + "cpu_id" + this.delimiter + "usage";
		return header;
	}
	
	public String generateLog(long timeStamp) {
		
		Random rand = new Random();
		ipAddress = "192.68.1." + (10 + rand.nextInt(10));
		
		Random r = new Random();
		cpuID = r.nextInt(2);
		
		Random cpuUsageRand = new Random();
		cpuUsage = cpuUsageRand.nextInt(101);
		
		String line = "" + timeStamp + delimiter +  ipAddress + delimiter + cpuID + delimiter + cpuUsage;
		
		return line;
	}
	
	public boolean emptyFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.pathString));
			if(br.readLine() == null) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
	
	public void createFile() {
		try {
			this.pathString = this.fileName + "/log.txt"; 
			p = Paths.get(this.pathString);
			File file = new File(this.pathString);
			int inc = 0;
			while(file.exists()) {
				this.pathString = this.fileName + "/log" + (inc++) + ".txt";
				file = new File(this.pathString);
			}
			p = Paths.get(this.pathString);
			Files.createFile(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeLogToFile(String log) {
		String s = log + System.getProperty("line.separator");
	    byte []data = s.getBytes();

	    try (OutputStream out = new BufferedOutputStream(
	      Files.newOutputStream(this.p, CREATE, APPEND))) {
	      out.write(data, 0, data.length);
	    } catch (IOException x) {
	      System.err.println(x);
	    }
	}
}
