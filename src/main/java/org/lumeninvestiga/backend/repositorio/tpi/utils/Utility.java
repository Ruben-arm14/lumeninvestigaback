package org.lumeninvestiga.backend.repositorio.tpi.utils;

import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utility {
    public static int getCurrentPage(Pageable pageable) {
        int page = pageable.getPageNumber();
        if(pageable.getPageNumber() != 0) {
            page -=1;
        }
        return page;
    }

    public static String calculatePeriod(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);

        int year = date.getYear();
        int month = date.getMonthValue();

        if (month <= 2) {
            return year + "-0";
        } else if (month >= 4 && month <= 7) {
            return year + "-1";
        } else if (month >= 8 && month <= 12) {
            return year + "-2";
        } else {
            return "No válido";
        }
    }

    private Utility() {
    }
}
