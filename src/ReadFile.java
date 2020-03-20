import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.json.simple.JSONObject;

public class ReadFile {
	static BufferedReader br = null;
//	iterator
	static int itr = 0;
// helper at adding to json
	static String some;
//	array for calculating the max and avg
	static double[] arr = new double[1000];
	static double total = 0;

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();

		try {
			String line;
			br = new BufferedReader(new FileReader("C:\\Users\\haris\\Downloads\\sample-input.txt"));
			while ((line = br.readLine()) != null) {
				StringTokenizer stringTokenizer = new StringTokenizer(line, " ");

				while (stringTokenizer.hasMoreElements()) {
//iterating to required cpu value in a row
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

//					StringBuilder sb = new StringBuilder();
//					sb.append(itr + "s" + reqCPU);
//					iterator

					itr++;
//					pushing it to json
					some = itr + "s";
					obj.put(some, reqCPU);
					arr[itr] = reqCPU;
//					System.out.println(sb.toString());
				}

			}
			for (int i = 0; i < arr.length; i++) {
				total = total + arr[i];
			}
			double average = total / arr.length;

//			  System.out.println(total);
//			  System.out.println(average);

			obj.put("total", total);
			obj.put("average", average);
//			  
//			printing the json
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
