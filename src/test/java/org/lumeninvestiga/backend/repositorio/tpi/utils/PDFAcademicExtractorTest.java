package org.lumeninvestiga.backend.repositorio.tpi.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class PDFAcademicExtractorTest {

    private PDFAcademicExtractor extractor;

    @BeforeEach
    void setUp() {
        extractor = new PDFAcademicExtractor();
    }

    @Test
    void testReadArticleByBytes() throws IOException {
        PDFAcademicExtractor spyExtractor = Mockito.spy(extractor);
        byte[] mockPdfBytes = new byte[]{1, 2, 3};
        String mockText = "Universidad de Lima \n" +
                "Facultad de Ingeniería y Arquitectura \n" +
                "Carrera de Ingeniería de Sistemas \n" +
                "\n" +
                "**CLASIFICACIÓN DE IMÁGENES POR FROTIS SANGUÍNEO PARA RECONOCER TIPOS DE GLÓBULOS BLANCOS UTILIZANDO REDES NEURONALES CONVOLUCIONALES **\n" +
                "Trabajo de investigación para optar el Título Profesional de Ingeniero de Sistemas \n" +
                "**Jose Daniel Linares Soplopuco **\n" +
                "**Código 20184082 **\n" +
                "**Asesor**\n" +
                "**Edwin Jonathan Escobedo Cardenas**\n" +
                "Lima – Perú \n" +
                "09/07/2023.";

        Mockito.doReturn(mockText).when(spyExtractor).extractTextFromPDF(any(byte[].class), eq(1), eq(2));

        List<String> result = spyExtractor.readArticleByBytes(mockPdfBytes);

        assertEquals(6, result.size());
        assertEquals("2023-1", result.get(3));
        assertEquals("No encontrado", result.get(4)); // Resumen no está presente en este texto
        assertEquals("No encontrado", result.get(5)); // Palabras clave no están presentes en este texto
    }

    @Test
    void testReadFichaByBytes() throws IOException {
        PDFAcademicExtractor spyExtractor = Mockito.spy(extractor);
        byte[] mockPdfBytes = new byte[]{1, 2, 3};
        String mockText = "Ficha de Investigación\n" +
                "Curso: Taller de Propuesta de Investigación\n" +
                "1. Integrante (s): ● Jose Daniel Linares Soplopuco\n" +
                "2. Título del tema de Investigación: Nota: Clasificación de imágenes por frotis sanguíneo para reconocer tipos de glóbulos blancos utilizando redes neuronales convolucionales.\n" +
                "3. Área y Sub-área: (https://dl.acm.org/ccs) CSS -> Computing methodologies -> Artificial intelligence -> Computer vision -> Computer vision problems -> Object recognition\n" +
                "4. Objetivo(s) de Desarrollo Sostenible vinculado(s): ODS 3: Salud y bienestar.";

        Mockito.doReturn(mockText).when(spyExtractor).extractTextFromPDF(any(byte[].class), eq(1), eq(1));

        List<String> result = spyExtractor.readFichaByBytes(mockPdfBytes);

        assertEquals(4, result.size());
        assertEquals("Clasificación de imágenes por frotis sanguíneo para reconocer tipos de glóbulos blancos utilizando redes neuronales convolucionales.", result.get(0));
        assertEquals("Jose Daniel Linares Soplopuco", result.get(1));
        assertEquals("Computing methodologies, Artificial intelligence", result.get(2));
    }

    @Test
    void testExtractArticleValues() throws Exception {
        String text = "Universidad de Lima \n" +
                "Facultad de Ingeniería y Arquitectura \n" +
                "Carrera de Ingeniería de Sistemas \n" +
                "\n" +
                "**CLASIFICACIÓN DE IMÁGENES POR FROTIS SANGUÍNEO PARA RECONOCER TIPOS DE GLÓBULOS BLANCOS UTILIZANDO REDES NEURONALES CONVOLUCIONALES **\n" +
                "Trabajo de investigación para optar el Título Profesional de Ingeniero de Sistemas \n" +
                "**Jose Daniel Linares Soplopuco **\n" +
                "**Código 20184082 **\n" +
                "**Asesor**\n" +
                "**Edwin Jonathan Escobedo Cardenas**\n" +
                "Lima – Perú \n" +
                "09/07/2023.";

        List<String> result = extractor.extractArticleValues(text);

        assertEquals(6, result.size());
        assertEquals("No encontrado", result.get(4)); // Resumen no está presente en este texto
        assertEquals("No encontrado", result.get(5)); // Palabras clave no están presentes en este texto
    }

    @Test
    void testExtractFichaValues() throws Exception {
        String text = "Ficha de Investigación\n" +
                "Curso: Taller de Propuesta de Investigación\n" +
                "1. Integrante (s): ● Jose Daniel Linares Soplopuco\n" +
                "2. Título del tema de Investigación: Nota: Clasificación de imágenes por frotis sanguíneo para reconocer tipos de glóbulos blancos utilizando redes neuronales convolucionales.\n" +
                "3. Área y Sub-área: (https://dl.acm.org/ccs) CSS -> Computing methodologies -> Artificial intelligence -> Computer vision -> Computer vision problems -> Object recognition\n" +
                "4. Objetivo(s) de Desarrollo Sostenible vinculado(s): ODS 3: Salud y bienestar.";

        List<String> result = extractor.extractFichaValues(text);

        assertEquals(4, result.size());
        assertEquals("Clasificación de imágenes por frotis sanguíneo para reconocer tipos de glóbulos blancos utilizando redes neuronales convolucionales.", result.get(0));
        assertEquals("Jose Daniel Linares Soplopuco", result.get(1));
        assertEquals("Computing methodologies, Artificial intelligence", result.get(2));
        assertEquals("No encontrado", result.get(3));
    }

    @Test
    void testExtractSecondAndThirdWords() throws Exception {
        String input = "CSS -> Computing methodologies -> Artificial intelligence -> Computer vision -> Computer vision problems -> Object recognition";

        assertEquals("Computing methodologies, Artificial intelligence", extractor.extractSecondAndThirdWords(input));
    }

    @Test
    void testExtractSecondAndThirdWordsException() {
        String input = "CSS";

        assertThrows(IllegalArgumentException.class, () -> extractor.extractSecondAndThirdWords(input));
    }
}
