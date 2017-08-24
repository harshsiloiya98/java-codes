import java.io.*;
import java.util.*;

public class Problem2 {
	static double mean(int[] a) {
		int sum = 0;
		for (int x : a)
			sum += x;
		return sum / a.length;
	}
	static double stddev(int[] a) {
		int sum = 0;
		double m = mean(a);
		for (int x : a) 
			sum += Math.pow(x - m, 2);
		return Math.sqrt(sum / (a.length - 1));
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		Random randomGenerator = new Random();
		for (int i = 0; i < n; i++)
			arr[i] = randomGenerator.nextInt(100) + 1;  //random no.s between 1-100
		System.out.println("Mean = " + mean(arr));
		System.out.println("Standard deviation = " + stddev(arr));
		br.close();
	}
}