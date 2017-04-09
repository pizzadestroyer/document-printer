package print;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;

import javax.print.PrintService;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentPrinter {

	final private DocumentService documentService;
	
	@Autowired
	public DocumentPrinter() {
		this.documentService = new DocumentServiceImpl();
	}
	
	public void printDocument(Object data) throws PrinterException, IOException {
		PDDocument document = documentService.getDocument(data);
		
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintService(getPrintService());
		job.setPageable(new PDFPageable(document));
		
		if (job.printDialog()) {
			job.print();
		}
		
		document.close();
	}
	
	private PrintService getPrintService() {
		PrintService[] printServices = PrinterJob.lookupPrintServices();	
		return printServices[0];
	}
	
}
