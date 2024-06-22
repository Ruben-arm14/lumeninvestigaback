package org.lumeninvestiga.backend.repositorio.tpi.utils;

import org.springframework.data.domain.Pageable;

public class Utility {
    public static int getCurrentPage(Pageable pageable) {
        int page = pageable.getPageNumber();
        if(pageable.getPageNumber() != 0) {
            page -=1;
        }
        return page;
    }

    private Utility() {
    }
}
