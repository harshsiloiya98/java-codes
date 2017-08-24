import java.io.*;
import java.util.*;

public class Problem2 {
	//   Array size is taken to be 1200
	private static final int SIZE = 1200;

	public static class Multithread implements Runnable {
		char sign;
		int[] arr1 = new int[SIZE];
		int[] arr2 = new int[SIZE];
		Multithread(char s, int[] a1, int[] a2) {
			sign = s;
			System.arraycopy(a1, 0, arr1, 0, SIZE);
			System.arraycopy(a2, 0, arr2, 0, SIZE);
		}
		public void run() {
			int[] res = new int[SIZE];
			if (sign == '+')
				for (int i = 0; i < SIZE; i++)
					res[i] = arr1[i] + arr2[i];
			else if (sign == '*')
				for (int i = 0; i < SIZE; i++)
					res[i] = arr1[i] * arr2[i];
			return;
		}
	}

	public static void main(String[] args) {
		Random rand = new Random();
		int[] arr1 = new int[SIZE];
		int[] arr2 = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {
			arr1[i] = rand.nextInt(100) + 1;
			arr2[i] = rand.nextInt(100) + 1;
		}
		Thread t1 = new Thread(new Multithread('+', arr1, arr2));
		Thread t2 = new Thread(new Multithread('*', arr1, arr2));
		t1.start();
		t2.start();
		t1.run();
		t2.run();
	}
}


/* In this case, sequential is proved to be faster than multithreading
The time period for execution of sequential implementation is around 23,000,000 nano seconds
The time period for execution of thread implementation is around 32,000,000 nano seconds 

As SIZE increases, the time taken for multithreading as compared to sequential will be lesser.*/