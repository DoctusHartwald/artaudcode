package com.voleo.entity.bridge;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.hibernate.search.bridge.StringBridge;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

public class PDFStringBridge implements StringBridge {

	@Override
	public String objectToString(Object o) {
		byte[] content = (byte[])o;
		ByteArrayInputStream contentStream = new ByteArrayInputStream(content);
		PDDocument pdfDoc = null;
		try {
			pdfDoc = PDDocument.load(contentStream);
			PDFTextStripper stripper = new PDFTextStripper();
			String result = stripper.getText(pdfDoc);
			pdfDoc.close();
			return result;
		} catch (IOException e) {
			// Should not occurs as IO is done from memory.
			return "";
		}
	}
}
