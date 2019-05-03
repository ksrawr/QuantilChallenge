import java.util.Calendar;
import java.util.TimeZone;

public class SimulatorAPP {

	public static void main(String[] args) {

		Simulator simulator = new Simulator("log");
		
		simulator.createFile();
				
		simulator.writeLogToFile(simulator.generateLogHeader());
		
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.add(Calendar.MILLISECOND, 0);
		cal.add(Calendar.SECOND, 0);
		cal.add(Calendar.MINUTE, 0);
		cal.add(Calendar.HOUR_OF_DAY, 0);
		
		for(int minutesInDay = 0; minutesInDay < 1440; minutesInDay++) {
			simulator.createFile();
			if(simulator.emptyFile()) {
				simulator.writeLogToFile(simulator.generateLogHeader());
			}
			simulator.writeLogToFile(simulator.generateLog(cal.getTimeInMillis()/1000L));
			cal.add(Calendar.MINUTE, 1);
		}
	}
}
