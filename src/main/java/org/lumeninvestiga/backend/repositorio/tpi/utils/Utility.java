package org.lumeninvestiga.backend.repositorio.tpi.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.lumeninvestiga.backend.repositorio.tpi.security.TokenJwtConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

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
    //TODO: Analizar la forma de crear un JwtService Interface. (CONSULTARLO)
    public static String extractTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authHeader) && authHeader.startsWith(TokenJwtConfig.PREFIX_TOKEN)) {
            return authHeader.substring(7);
        }
        throw new IllegalArgumentException("Token no encontrado en la solicitud");
    }

    private Utility() {
    }
}
