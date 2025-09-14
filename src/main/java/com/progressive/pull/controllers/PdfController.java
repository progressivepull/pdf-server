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


@RestController
@RequestMapping("/api")
public class PdfController {
	
	private final PdfService pdfService ;
	
    public PdfController() {
        this.pdfService = new PdfService();
    }
	
	
    //http://localhost:8082/
    @GetMapping("/")
    public String index() {
        return "index";
    }
	
	
	//http://localhost:8082/api/pdf/folder
	@GetMapping("/pdf/folder")
    public ResponseEntity<InputStreamResource> getPdf() throws IOException {
        ClassPathResource pdfFile = new ClassPathResource("pdf/git-cheat-sheet-education.pdf");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=git-cheat-sheet-education.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfFile.getInputStream()));
    }
	
    @GetMapping("/pdf/generate")
    public ResponseEntity<InputStreamResource> downloadPdf() {
        ByteArrayInputStream pdfStream = pdfService.generatePdf();

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sample.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(pdfStream));
    }

}
