import java.io.*;
import java.util.*;

public class Problem1 {
	public static void main(String[] args) {
		int sum = 0;
		for (String x : args) {
			int y = Integer.parseInt(x);
			sum = sum + y;
		}
		System.out.println(args.length + ", " + sum);
	}
}