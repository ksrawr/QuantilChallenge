import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

public class Simulator {

	long timeStamp;
	String ipAddress;
	int cpuID = 0;
	int cpuUsage;
	final String delimiter = "-------";
	File file;
	
	public Simulator(File file) {
		this.file = file; 
	}
	
	public String generateLog() {
		timeStamp = System.currentTimeMillis() / 1000L;
		
		Random rand = new Random();
		ipAddress = "192.68.1." + (10 + rand.nextInt(2));
		
		Random r = new Random();
		cpuID = r.nextInt(2);
		
		Random cpuUsageRand = new Random();
		cpuUsage = cpuUsageRand.nextInt(101);
		
		String line = "" + timeStamp + delimiter +  ipAddress + delimiter + cpuID + delimiter + cpuUsage;
		
		return line;
	}
	
	public void writeLogToFile(String log) {
		try {
			FileOutputStream fos = new FileOutputStream(this.file);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(log);
			bw.newLine();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} 
	}
}
