package com.pda.boardapplication.utils;

import com.pda.tofinenums.user.UserRole;

import java.util.HashMap;
import java.util.Map;

public class UserUtils {
    private static Map<UserRole, Integer> roleCode = new HashMap<>() {{
        put(UserRole.NORMAL, 0);
        put(UserRole.FINFLUENCER, 1);
        put(UserRole.CORP, 2);
        put(UserRole.ADMIN, 3);
    }};

    public static int getUserRoleCode(UserRole userRole) {
        return roleCode.get(userRole);
    }
}
