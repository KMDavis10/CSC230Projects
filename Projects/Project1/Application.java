import java.io.*;

public class Application {
	public static void main (String args[]) throws IOException {
		readWebsite data;
		data = new readWebsite();
		data.readData();
		data.listRTowns();
		data.listCounties();
		data.listTypes();
		data.totalCounties();
		data.zipCodes("PORTSMOUTH");
		data.writeFile();
	}
}
