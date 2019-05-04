import java.io.*;

public class QueryApp {

	public static void main(String[] args) throws java.io.IOException {
		
		String commandLine;
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
		Query query = new Query("src/log");
		
		if(query.legalPath()) {
			query.readDirectory();
			query.findMatches();
		}
//		if(query.legalPath()) {
//			while(true) {
//				System.out.print(">QUERY ");
//				commandLine = console.readLine();
//				
//				String[] params = commandLine.split(" ");
//				
//				
//			}
//		}
	}

}
