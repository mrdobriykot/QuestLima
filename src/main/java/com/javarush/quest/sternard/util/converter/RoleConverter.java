package com.javarush.quest.sternard.util.converter;

import com.javarush.quest.sternard.entities.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public final class RoleConverter implements AttributeConverter<Role, Long> {
    private RoleConverter() {
    }

    @Override
    public Long convertToDatabaseColumn(Role attr) {
        if (attr == null) {
            return null;
        }
        return attr.getId();
    }

    @Override
    public Role convertToEntityAttribute(Long roleId) {
        if (roleId == null) {
            return null;
        }
        return Role.getRole(roleId);
    }

}
