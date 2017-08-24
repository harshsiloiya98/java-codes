import java.util.*;
import java.io.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Problem1 {

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

	static class PDF {
		public static String pdfreader(String filename) throws IOException {
			File f = new File(filename);
			PDDocument docpdf = PDDocument.load(f);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			String content = pdfStripper.getText(docpdf);
			return content;
		}
	}

	static class DOCX {
		public static String docxreader(String filename) throws IOException {
		  	XWPFDocument docx = new XWPFDocument(new FileInputStream(filename));
	      	XWPFWordExtractor extr = new XWPFWordExtractor(docx);
	      	String content = extr.getText();
	      	return content;
		}
	}

	static class DOC {
		public static String docreader(String filename) throws IOException {
			HWPFDocument docx = new HWPFDocument(new FileInputStream(filename));
	      	WordExtractor extr = new WordExtractor(docx);
	      	String content = extr.getText();
	      	return content;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String filename = input.readLine(); 
		String ext = (filename.split("\\.(?=[^\\.]+$)"))[1];
		String finaltext = "";
		if(ext.equals("pdf")) {
			finaltext = PDF.pdfreader(filename);
		}
		else if (ext.equals("docx")) {
			finaltext = DOCX.docxreader(filename);
		}
		else if (ext.equals("doc")) {
			finaltext = DOC.docreader(filename);
		}
		// now to count the frequency
		String splitter = "[ .,?!\n]+";
		String[] tokens = finaltext.split(splitter);
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