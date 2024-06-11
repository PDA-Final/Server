package com.pda.userapplication.outadapters.jpa.converter;

import com.pda.tofinenums.user.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserRole userRole) {
        if (userRole.equals(UserRole.ADMIN))
            return 0;

        if (userRole.equals(UserRole.NORMAL))
            return 1;

        if (userRole.equals(UserRole.CORP))
            return 2;

        if (userRole.equals(UserRole.FINFLUENCER))
            return 3;


        throw new IllegalArgumentException("Not valid User Role in Converter");
    }

    @Override
    public UserRole convertToEntityAttribute(Integer integer) {
        if (integer == 0)
            return UserRole.ADMIN;

        if (integer == 1)
            return UserRole.NORMAL;

        if (integer == 2)
            return UserRole.CORP;

        if (integer == 3)
            return UserRole.FINFLUENCER;

        throw new IllegalArgumentException("Not valid User Role in Converter");
    }
}
