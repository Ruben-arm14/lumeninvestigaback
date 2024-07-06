package org.lumeninvestiga.backend.repositorio.tpi.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.lumeninvestiga.backend.repositorio.tpi.utils.Utility.calculatePeriod;

@Component
public class PDFAcademicExtractor {
    public PDFAcademicExtractor() {}

    public List<String> readArticleByBytes(byte[] pdfBytes) throws IOException {
        String text = extractTextFromPDF(pdfBytes, 1, 2);
        return extractArticleValues(text);
    }

    public List<String> readFichaByBytes(byte[] pdfBytes) throws IOException {
        String text = extractTextFromPDF(pdfBytes, 1, 1);
        return extractFichaValues(text);
    }

    private String extractTextFromPDF(byte[] pdfBytes, int startPage, int endPage) throws IOException {
        try (PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfBytes))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(startPage);
            pdfStripper.setEndPage(endPage);
            return pdfStripper.getText(document);
        }
    }

    private List<String> extractArticleValues(String text) {
        String title = extractData(text, "^([A-Z\\s]+?)\\bAutor(es)\\b\n");
        String author = extractData(text, "(?i)Autor\\(es\\)?\\s*:?\\s*(.*?)(?=\\n\\d|\\n\\s*$)");
        String advisor = extractData(text, "(?s)Asesor\\s*\n(.+?)(?=\n|$)");
        String period = extractData(text, "(?s)(\\d{2}/\\d{2}/\\d{4})");
        String summary = extractData(text, "Resumen\\s*:([\\s\\S]+?)(?=Palabras clave?\\s*:|$)");
        String keywords = extractData(text, "Palabras clave\\s*:([\\s\\S]+?)(?=Abstract?\\s*:|$)");
        return List.of(title, author, advisor, calculatePeriod(period), summary, keywords);
    }

    private List<String> extractFichaValues(String text) {
        String title = extractData(text, "(?s)2\\s*\\.\\s*Título del tema de Investigación:\\s*(?:Nota:\\s*)?(.+?)(?=\\d+\\s*\\.)");
        String author = extractData(text, "(?s)1\\s*\\.\\s*Integrante\\s*\\(s\\):\\s*●\\s*(.+?)(?=\\d+\\s*\\.)");
        String area = extractData(text, "(?s)3\\s*\\.\\s*Área y Sub-área:\\s*(?:\\(https://dl\\.acm\\.org/ccs\\)\\s*)?(.+?)(?=\\d+\\s*\\.)");
        String ods = extractData(text, "(?s)4\\s*\\.\\s*Objetivo\\(s\\) de Desarrollo Sostenible vinculado\\(s\\):\\s*(.+?)(?=\\d+\\s*\\.)");
        String areas = extractSecondAndThirdWords(area);
        return List.of(title, author, areas, ods);
    }

    private static String extractData(String text, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim().replace("\r\n", "");
        }
        return "No encontrado";
    }

    private String extractSecondAndThirdWords(String text) {
        if(text.contains("->")) {
            String[] words = text.split("->");
            if (words.length >= 3) {
                return words[1].trim() + ", " + words[2].trim();
            }
            throw new IllegalArgumentException("Texto no contiene suficientes palabras separadas por '->'");
        }
        String[] words = text.split(",");
        if (words.length >= 3) {
            return words[1].trim() + ", " + words[2].trim();
        }
        throw new IllegalArgumentException("Texto no contiene suficientes palabras separadas por ','");
    }
}
