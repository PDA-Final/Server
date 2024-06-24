package com.pda.boardapplication.utils;

import com.pda.exceptionhandler.exceptions.BadRequestException;

import java.util.HashMap;
import java.util.Map;

public class CategoryUtils {

    public static Map<String, Integer> CATEGORY_TO_ID = new HashMap<>() {{
        put("정보", 1);
        put("재미", 2);
        put("투자", 3);
        put("기업", 4);
        put("고급", 5);
    }};

    /**
     * Verify category name and return category id
     * @param category category name
     * @return category id, null if empty
     * @throws BadRequestException given category name is not valid
     */
    public static Integer verifyCategory(String category) {
        if(category == null || category.isBlank())
            return null;

        Integer ret = CATEGORY_TO_ID.get(category);

        if(ret == null)
            throw new BadRequestException("Invalid category name");

        return ret;
    }
}
