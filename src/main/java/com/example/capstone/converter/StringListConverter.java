package com.example.capstone.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String SEPARATOR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return attribute != null ? String.join(SEPARATOR, attribute) : null;
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return dbData != null ? Arrays.stream(dbData.split(SEPARATOR)).collect(Collectors.toList()) : null;
    }
}
