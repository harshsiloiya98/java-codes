import java.io.*;
import java.util.*;
import java.net.*;

public class Problem3 {

	private static final String USER_AGENT = "Mozilla/5.0";

	static void initiateGetQuery(String url, String input) throws Exception {
		url = url + "?input=" + URLEncoder.encode(input, "utf-8");
		URL site = new URL(url);
		HttpURLConnection huc = (HttpURLConnection)site.openConnection();
		huc.setRequestMethod("GET");
		huc.setRequestProperty("User-Agent", USER_AGENT);
		BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
		String line;
		StringBuffer s = new StringBuffer();
		while ((line = br.readLine()) != null)
			s.append(line);
		br.close();
		System.out.println(s.toString());
		huc.disconnect();
	}

	static void initiatePostQuery(String url, String input) throws Exception {
		URL site = new URL(url);
		String param = "input=" + URLEncoder.encode(input, "utf-8");
		HttpURLConnection huc = (HttpURLConnection)site.openConnection();
		//huc.setDoInput(true);
		huc.setDoOutput(true);
		huc.setInstanceFollowRedirects(false);
		huc.setRequestMethod("POST");
		huc.setRequestProperty("User-Agent", USER_AGENT);
		huc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		huc.setRequestProperty("charset", "utf-8");
		huc.setRequestProperty("Accept-Language", "en-US,en:q=0.5");
		huc.setRequestProperty("Content-Length", Integer.toString(param.getBytes("utf-8").length));
		huc.setUseCaches(false);
		DataOutputStream dos = new DataOutputStream(huc.getOutputStream());
		dos.writeBytes(param);
		dos.flush();
		dos.close();
		BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
		String line;
		StringBuffer s = new StringBuffer();
		while ((line = br.readLine()) != null)
			s.append(line);
		br.close();
		System.out.println(s.toString());
		huc.disconnect();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter input: ");
		String input = br.readLine();
		try {
			System.out.println("GET Query :-");
			initiateGetQuery("https://www.cse.iitb.ac.in/~safeer/get_hash.php", input);
			System.out.println("POST Query :-");
			initiatePostQuery("https://www.cse.iitb.ac.in/~safeer/post_hash.php", input);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		br.close();
	}
}