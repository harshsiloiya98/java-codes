import java.io.*;
import java.util.*;

public class Problem3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Input: ");
		String inp = br.readLine();
		String[] inp_temp = inp.split(",");
		String txt = inp_temp[0];
		int a = Integer.parseInt(inp_temp[1].trim());
		int b = Integer.parseInt(inp_temp[2].trim());
		System.out.println("Output: " + txt.substring(a, b+1));
		br.close();
	}
}