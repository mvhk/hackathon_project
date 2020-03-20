import java.util.StringTokenizer;

public class Data {
	static String input1;

	public Data(String string) {
		final StringTokenizer tok = new StringTokenizer(string, ",", false);
		tok.nextToken();
		tok.nextToken();
		tok.nextToken();
		tok.nextToken();
		tok.nextToken();
		tok.nextToken();
		tok.nextToken();
		tok.nextToken();
		input1 = tok.nextToken();

	}

	public static String input1() {
		return input1;
	}
}
