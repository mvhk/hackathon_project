import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ReadFile {
	static BufferedReader br = null;
	static int itr = 0;

	public static void main(String[] args) {
		try {
			String line;
			br = new BufferedReader(new FileReader("C:\\Users\\haris\\Downloads\\sample-input.txt"));
			while ((line = br.readLine()) != null) {
				System.out.println(line);

				StringTokenizer stringTokenizer = new StringTokenizer(line, " ");

				while (stringTokenizer.hasMoreElements()) {

					Integer rand1 = Integer.parseInt(stringTokenizer.nextElement().toString());
					String rand2 = stringTokenizer.nextElement().toString();
					Integer rand3 = Integer.parseInt(stringTokenizer.nextElement().toString());
					Integer rand4 = Integer.parseInt(stringTokenizer.nextElement().toString());
					String rand5 = stringTokenizer.nextElement().toString();
					String rand6 = stringTokenizer.nextElement().toString();
					String rand7 = stringTokenizer.nextElement().toString();
					String rand8 = stringTokenizer.nextElement().toString();
//					required line
					Double reqCPU = Double.parseDouble(stringTokenizer.nextElement().toString());
					String rand9 = stringTokenizer.nextElement().toString();
					String rand10 = stringTokenizer.nextElement().toString();
					String rand11 = stringTokenizer.nextElement().toString();

					StringBuilder sb = new StringBuilder();
					sb.append(itr + "s" + reqCPU);
					itr++;
					System.out.println(sb.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
