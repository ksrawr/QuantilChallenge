import java.io.*;

public class QueryApp {

	public static void main(String[] args) throws java.io.IOException {
		
		String commandLine;
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
		Query query = new Query("src/log");
		
//		if(query.legalPath()) {
//			query.readDirectory();
//			query.setIP("192.68.1.19");
//			query.setCPUID("0");
//			query.setTimeStart("2019-05-04", "0:00");
//			query.setTimeEnd("2019-05-04", "24:00");
//			query.findMatches();
//		}
		
		if(query.legalPath()) {
			while(true) {
				System.out.print(">QUERY ");
				commandLine = console.readLine();
				
				if(commandLine.equals("exit")) {
					System.exit(0);
				}
				
				String[] params = commandLine.split(" ");
				
				query.readDirectory();
				query.setIP(params[0]);
				query.setCPUID(params[1]);
				query.setTimeStart(params[2], params[3]);
				query.setTimeEnd(params[4], params[5]);
				query.findMatches();
				
			}
		}
	}

}
