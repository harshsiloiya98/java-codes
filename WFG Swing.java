import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


public class Jfile {

	JFrame window = new JFrame("Q1 GUI");
	JDialog d = new JDialog(window,"Q1- GUI",true);
	//JButton select = new JButton("Select");
	JButton process = new JButton("Process");
	JTextArea area = new JTextArea();
	 JButton showFileDialogButton = new JButton("Select");
	 private String filename = "god help";
	  String ext ="";

	public Jfile() 
	{
		
		d.setSize(900,900);
		area.setBounds(10,30, 200,200);  
		//showFileDialogButton.setBounds(200,200,100,30);
		//process.setBounds(500,200,100,30);
		d.add(area);
		//window.add(process);
		//d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		d.setLocationRelativeTo(null);
		d.setLayout(null);
		d.setVisible(true);
	}
	public String showFileChooser()
	{
      
		final JFileChooser  fileDialog = new JFileChooser();
      showFileDialogButton = new JButton("Select");
      showFileDialogButton.setBounds(250,200,100,30);
      d.add(showFileDialogButton);
       
      showFileDialogButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 
            int returnVal = fileDialog.showOpenDialog(window);
            
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileDialog.getSelectedFile();
                filename = file.getPath();
                //System.out.println(filename);
                d.setVisible(false);
            }   
         }
      });
     
      	d.setVisible(true);
       
      return filename;
        
   }
	public static String docreader(String filename)throws IOException
	{
		  HWPFDocument docx = new HWPFDocument(new FileInputStream(filename));
	      WordExtractor extr = new WordExtractor(docx);
	      String content = extr.getText();
	      return content;
	}
	public static String docxreader(String filename)throws IOException
	{
		  XWPFDocument docx = new XWPFDocument(new FileInputStream(filename));
	      XWPFWordExtractor extr = new XWPFWordExtractor(docx);
	      String content = extr.getText();
	      return content;
	}
	public static String pdfreader(String filename)throws IOException
	{
		File f = new File(filename);
		PDDocument docpdf = PDDocument.load(f);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String content = pdfStripper.getText(docpdf);
		return content;
	}
	

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
   private void displaytable()
   {
	   
   	String filepath = showFileChooser();
	String extn = (filepath.split("\\.(?=[^\\.]+$)"))[1];
   	process = new JButton("Process");
   	process.setBounds(500,200,100,30);
   	d.add(process);
    
   	process.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 d.setVisible(false);
            String text = area.getText();
			String[] stop_w = text.split(",");
			
			
			String finaltext = "";
			if(extn.equals("pdf"))
		{
			try {
				finaltext = pdfreader(filepath);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (extn.equals("docx"))
		{
			try {
				finaltext = docxreader(filepath);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (extn.equals("doc"))
		{
			try {
				finaltext = docreader(filepath);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
 		String splitter = "[ .,?!\n]+";
		String[] tokens = finaltext.split(splitter);

		ArrayList<String> al = new ArrayList<String>();
		for (String x : tokens)
			if (binarySearch(stop_w, x.toLowerCase()) == -1)
				al.add(x);
			Set<String> s = new HashSet<String>(al);
			String[] finalecount = new String[s.size()];
			int i = 0;
		for (String y : s)
		{
			
			finalecount[i]=(Integer.toString(Collections.frequency(al, y)));
			i++;
		}
		String[] finalewords = s.toArray(new String[s.size()]);
		String[][] count = new String[s.size()][2];
		for(int p =0;p<s.size();p++)
		{
			count[p][0] = finalewords[p];
			count[p][1] = finalecount[p];
		}
		JFrame f = new JFrame();
		String column[] = {"word","frequency"};
		JTable wordcount = new JTable(count,column);
		wordcount.setBounds(300,400,200,300);          
		JScrollPane sp = new JScrollPane(wordcount);    
		f.add(sp);          
		f.setSize(300,400);    
		f.setVisible(true);    
         }
      });
   	d.setVisible(true);
   }

	public static void main(String[] args)
	{
		Jfile demo = new Jfile();
		demo.displaytable();
	}

}
