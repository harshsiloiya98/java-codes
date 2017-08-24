import java.io.*;
import java.util.*;

public class Problem5 {
	static int binarySearch(String[] arr, String key) {
		Arrays.sort(arr);
		int l = 0;
		int h = arr.length - 1;
		int mid = 0;
		while (l <= h) {
			mid = (l + h) / 2;
			if (arr[mid].compareTo(key) < 0)
				l = mid + 1;
			else if (arr[mid].compareTo(key) > 0)
				h = mid - 1;
			else
				return mid;
		}
		return -1;
	}
	static String readfile(String filename) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(filename));
		StringBuilder s = new StringBuilder();
		String line = input.readLine();
		while (line != null) {
			s.append(line);
			s.append("\n");
			line = input.readLine();
		}
		input.close();
		return s.toString();
	}
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String file = input.readLine();
		String file_c = readfile(file);
		String splitter = "[ .,?!\n]+";
		String[] tokens = file_c.split(splitter);
		String[] stop_words = {"and", "the", "is", "in", "at", "of", "his", "her", "him"};
		ArrayList<String> al = new ArrayList<String>();
		for (String x : tokens)
			if (binarySearch(stop_words, x.toLowerCase()) == -1)
				al.add(x);
		Set<String> s = new HashSet<String>(al);
		for (String y : s)
			System.out.println(y + " = " + Collections.frequency(al, y));
		input.close();
	}
}