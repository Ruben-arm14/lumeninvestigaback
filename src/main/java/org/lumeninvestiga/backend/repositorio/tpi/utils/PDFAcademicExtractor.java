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
    private List<String> receivingArticleValues(String text) {
        String title, author, summary, keywords;

        // Falta el primero
        title = extractData(text, "^([A-Z\\s]+?)\\bAutor(es)\\b\n");
        author = extractData(text, "(?i)Autor\\(es\\)?\\s*:?\\s*(.*?)(?=\\n\\d|\\n\\s*$)");
        summary = extractData(text, "Resumen\\s*:([\\s\\S]+?)(?=Palabras clave?\\s*:|$)");
        keywords = extractData(text, "Palabras clave\\s*:([\\s\\S]+?)(?=Abstract?\\s*:|$)");

        return List.of(title, author, summary, keywords);
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
            listText = receivingArticleValues(text);
        }
        return listText;
    }
    //TODO: Organizar lógica para reducir líneas de código.
    private List<String> receivingFichaValues(String text) {
        String title, author, area, ods,  areas;

        // Falta el primero
        title = extractData(text, "(?s)2\\s*\\.\\s*Título del tema de Investigación:\\s*(?:Nota:\\s*)?(.+?)(?=\\d+\\s*\\.)");
        author = extractData(text, "(?s)1\\s*\\.\\s*Integrante\\s*\\(s\\):\\s*●\\s*(.+?)(?=\\d+\\s*\\.)");
        area = extractData(text, "(?s)3\\s*\\.\\s*Área y Sub-área:\\s*(?:\\(https://dl\\.acm\\.org/ccs\\)\\s*)?(.+?)(?=\\d+\\s*\\.)");
        ods = extractData(text, "(?s)4\\s*\\.\\s*Objetivo\\(s\\) de Desarrollo Sostenible vinculado\\(s\\):\\s*(.+?)(?=\\d+\\s*\\.)");

        areas = extractSecondAndThirdWords(area);
        // Te devuelve areas como un "area, subArea"
        return List.of(title, author, areas, ods);
    }

    public List<String> readFichaByBytes(byte[] pdfBytes) throws IOException {
        String text;
        List<String> listText;

        try (
                PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfBytes))
        ) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(1);

            text = pdfStripper.getText(document);
            listText = receivingFichaValues(text);
        }
        return listText;
    }

    // Método privado
    private String extractSecondAndThirdWords(String text) {
        String[] words = text.split("->");
        if (words.length >= 3) {
            return words[1].trim() + ", " + words[2].trim();
        }
        //TODO: INVALID_OPERATION EXCEPTION
        throw new RuntimeException();
    }
}
