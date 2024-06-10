package org.lumeninvestiga.backend.repositorio.tpi.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PDFAcademicExtractor {
    private static String extractData(String text, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return "No encontrado";
    }

    private List<String> receivingValues(String text) {
        String title, author, summary, keywords;

        // Falta el primero
        title = extractData(text, "^([A-Z\\s]+?)\\bAutor(es)\\b\n");
        author = extractData(text, "(?i)Autor\\(es\\)?\\s*:?\\s*(.*?)(?=\\n\\d|\\n\\s*$)");
        summary = extractData(text, "Resumen\\s*:([\\s\\S]+?)(?=Palabras clave?\\s*:|$)");
        keywords = extractData(text, "Palabras clave\\s*:([\\s\\S]+?)(?=Abstract?\\s*:|$)");

        return List.of(title, author, summary, keywords);
    }

    private String readArticle(String path) throws IOException {
        // Leer el archivo PDF y convertirlo en un arreglo de bytes
        Path uri = Paths.get(path);
        byte[] pdfBytes = Files.readAllBytes(uri);
        String text;

        try (
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pdfBytes);
                PDDocument document = PDDocument.load(byteArrayInputStream)
        ) {
            // Utilizar PDFTextStripper para extraer el texto del PDF
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(2);
            pdfStripper.setEndPage(2);

            text = pdfStripper.getText(document);
        }
        return text;
    }
}
