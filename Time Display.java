import java.io.*;
import java.util.*;
import java.text.*;

public class solution {
	public static class Runtime implements Runnable {
		public void run() {
			Date d = new Date();
			SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
			String q = s.format(d);
			System.out.printf("\r %s", q);
		}
	}
	public static void main(String[] args) {
		Runtime r = new Runtime();
		Thread t = new Thread(r);
		try {
			while (true) {
				t.run();
				t.sleep(1000);
			}
		}
		catch(InterruptedException e) {
			System.out.println(e);
		}
	}
}