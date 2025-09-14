package com.progressive.pull.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api")
public class PdfServerController {
	
	
	//http://localhost:8082/api/pdf
	
	@GetMapping("/pdf")
    public ResponseEntity<InputStreamResource> getPdf() throws IOException {
        ClassPathResource pdfFile = new ClassPathResource("pdf/git-cheat-sheet-education.pdf");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=git-cheat-sheet-education.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfFile.getInputStream()));
    }

}
