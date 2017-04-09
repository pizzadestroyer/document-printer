package print;

import java.io.IOException;
import java.lang.reflect.Field;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class DocumentServiceImpl implements DocumentService {

	private static PDFont font = PDType1Font.COURIER;
	
	private float ty = 700;
	
	@Override
	public PDDocument getDocument(Object data) {
		PDDocument document = new PDDocument();
		PDPage page = new PDPage();
		document.addPage(page);
				
		PDPageContentStream contentStream;
		
		try {
			contentStream = new PDPageContentStream(document, page);

			Class<?> dataClass = data.getClass();
			Field[] fields = dataClass.getDeclaredFields();
			
			for (int i = 0; i < fields.length; i++) {
				drawText(contentStream, fields[i], data);
			}
			
			contentStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		
		return document;
	}

	private void drawText(PDPageContentStream contentStream, Field field, Object data) throws IOException, 
			IllegalArgumentException, 
			IllegalAccessException, 
			InstantiationException {
		contentStream.beginText();
		contentStream.setFont(font, 12);
		contentStream.newLineAtOffset(100, ty);
		
		ty -= 10;
		field.setAccessible(true);
		
		Object objectValue = field.get(data);
		contentStream.showText(String.valueOf(objectValue));
		
		contentStream.endText();
	}
}
