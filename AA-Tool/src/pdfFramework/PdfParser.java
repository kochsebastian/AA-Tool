/**
 * 
 */
package pdfFramework;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author SebastianKoch
 *
 */
public class PdfParser {
	public PdfParser() {}
	private void test() {
		String home = System.getProperty("user.home");
		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(home + File.separator + "Documents" + File.separator  + "Java.pdf"));
			doc.open();
			doc.add(new Paragraph("Example"));
			doc.close();
		}catch(Exception e) {
			
		}
	}

	
}
