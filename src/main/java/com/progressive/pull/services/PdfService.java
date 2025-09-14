package com.progressive.pull.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

// Service class responsible for generating PDF documents
public class PdfService {

    /**
     * Generates a simple PDF containing a static message.
     * 
     * @return ByteArrayInputStream representing the generated PDF content
     */
    public ByteArrayInputStream generatePdf() {
        try {
            // Create a new PDF document
            Document document = new Document();

            // Output stream to hold the PDF bytes
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            // Associate the document with the output stream via PdfWriter
            PdfWriter.getInstance(document, out);

            // Open the document for writing
            document.open();

            // Add a paragraph with sample text
            document.add(new Paragraph("Hello, this is your generated PDF!"));

            // Close the document to finalize the content
            document.close();

            // Convert the output stream to an input stream for downstream use
            return new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {
            // Wrap and rethrow any exceptions as a runtime error
            throw new RuntimeException("PDF generation failed", e);
        }
    }
}

