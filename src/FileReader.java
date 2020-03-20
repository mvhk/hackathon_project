import java.io.BufferedReader;
import java.text.DecimalFormat;

public class FileReader {
	String a[] = new String[1000];
	public static void main(String[] args) throws Exception {
		final BufferedReader br = new BufferedReader(
				new java.io.FileReader("C:\\Users\\haris\\eclipse-workspace\\Hackathon_project\\sample.txt"));
		DecimalFormat df = new DecimalFormat("#0.00");
		br.readLine();

		while (br.ready()) {

			add(new Data(br.readLine()));

		}
	}

	private static void add(Data datas) {
		final String requiredCPUdata = Data.input1();
	}

}
