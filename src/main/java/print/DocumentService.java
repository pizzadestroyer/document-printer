package print;

import org.apache.pdfbox.pdmodel.PDDocument;

public interface DocumentService {

	PDDocument getDocument(Object data);
	
}
