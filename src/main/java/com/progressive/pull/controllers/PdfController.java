package com.progressive.pull.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progressive.pull.services.PdfService;

import java.io.IOException;
import java.io.ByteArrayInputStream;

/**
 * REST controller for handling PDF-related endpoints.
 */
@RestController
@RequestMapping("/api")
public class PdfController {

    // Injecting the PdfService to handle PDF generation logic
    private final PdfService pdfService;

    // Constructor initializes PdfService manually (could be improved with @Autowired or @Service annotation)
    public PdfController() {
        this.pdfService = new PdfService();
    }

    /**
     * Basic index endpoint for sanity check.
     * Accessible via: http://localhost:8082/
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Serves a static PDF file from the classpath.
     * Accessible via: http://localhost:8082/api/pdf/folder
     * 
     * URL: http://localhost:8082/api/pdf/folder
     * 
     * @return ResponseEntity containing the PDF as an InputStreamResource
     */
    
    @GetMapping("/pdf/folder")
    public ResponseEntity<InputStreamResource> getPdf() throws IOException {
        // Load the static PDF from resources/pdf directory
        ClassPathResource pdfFile = new ClassPathResource("pdf/git-cheat-sheet-education.pdf");

        // Build the HTTP response with appropriate headers and content type
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=git-cheat-sheet-education.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfFile.getInputStream()));
    }

    /**
     * Dynamically generates a PDF and returns it as a downloadable file.
     * Accessible via: http://localhost:8082/api/pdf/generate
     * 
     * URL: http://localhost:8082/api/pdf/folder
     * 
     * @return ResponseEntity containing the generated PDF stream
     */
    @GetMapping("/pdf/generate")
    public ResponseEntity<InputStreamResource> downloadPdf() {
        // Generate PDF content using PdfService
        ByteArrayInputStream pdfStream = pdfService.generatePdf();

        // Build the HTTP response with download headers
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sample.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(pdfStream));
    }
}
