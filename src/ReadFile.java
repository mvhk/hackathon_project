import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.json.simple.JSONObject;

public class ReadFile {
	static BufferedReader br = null;
	static int itr = 0;
	static String some;

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();

		try {
			String line;
			br = new BufferedReader(new FileReader("C:\\Users\\haris\\Downloads\\sample-input.txt"));
			while ((line = br.readLine()) != null) {
				StringTokenizer stringTokenizer = new StringTokenizer(line, " ");

				while (stringTokenizer.hasMoreElements()) {

					int x = 0;
					while (x < 8) {
						stringTokenizer.nextElement().toString();
						x++;
					}

//					required line
					Double reqCPU = Double.parseDouble(stringTokenizer.nextElement().toString());
					while (x < 11) {
						stringTokenizer.nextElement().toString();
						x++;
					}

					StringBuilder sb = new StringBuilder();
					sb.append(itr + "s" + reqCPU);
					itr++;
					some = itr + "s";
					obj.put(some, reqCPU);

					System.out.println(sb.toString());
				}

			}
			System.out.println(obj);
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
