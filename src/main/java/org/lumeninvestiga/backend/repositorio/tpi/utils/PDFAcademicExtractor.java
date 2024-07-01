package org.lumeninvestiga.backend.repositorio.tpi.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class PDFAcademicExtractor {

    public PDFAcademicExtractor() {
    }

    private static String extractData(String text, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return "No encontrado";
    }
    //Segundo
    private List<String> receivingValues(String text) {
        String title, author, summary, keywords;

        // Falta el primero
        title = extractData(text, "^([A-Z\\s]+?)\\bAutor(es)\\b\n");
        author = extractData(text, "(?i)Autor\\(es\\)?\\s*:?\\s*(.*?)(?=\\n\\d|\\n\\s*$)");
        summary = extractData(text, "Resumen\\s*:([\\s\\S]+?)(?=Palabras clave?\\s*:|$)");
        keywords = extractData(text, "Palabras clave\\s*:([\\s\\S]+?)(?=Abstract?\\s*:|$)");

        return List.of(title, author, summary, keywords);
    }

    public List<String> readArticleByPath(String path) throws IOException {
        // Leer el archivo PDF y convertirlo en un arreglo de bytes
        Path uri = Paths.get(path);
        byte[] pdfBytes = Files.readAllBytes(uri);
        String text;
        List<String> listText;

        try (
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pdfBytes);
                PDDocument document = PDDocument.load(byteArrayInputStream)
        ) {
            // Utilizar PDFTextStripper para extraer el texto del PDF
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(3);
            pdfStripper.setEndPage(3);

            text = pdfStripper.getText(document);
            listText = receivingValues(text);
        }
        return listText;
    }

    //TODO: Probando con arreglo de Bytes
    public List<String> readArticleByBytes(byte[] pdfBytes) throws IOException {
        String text;
        List<String> listText;

        try (
                PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfBytes))
        ) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(3);
            pdfStripper.setEndPage(3);

            text = pdfStripper.getText(document);
            listText = receivingValues(text);
        }
        return listText;
    }
}
