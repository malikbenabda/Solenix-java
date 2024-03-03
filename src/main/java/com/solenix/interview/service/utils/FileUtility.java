package com.solenix.interview.service.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solenix.interview.model.Position;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.*;

public class FileUtility {

    public static double findClosestPosition(List<Position> positions, Date occurrenceTime) {
        Optional<Position> optionalPosition = positions.stream()
                .min(Comparator.comparingLong(p -> Math.abs(Duration.between(p.getTimestamp().toInstant(), occurrenceTime.toInstant()).toMillis())));
        return optionalPosition.map(Position::getPosition).orElse(0.0);
    }
    public static <T> List<T> loadData(String fileName, TypeReference<List<T>> typeReference) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = FileUtility.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream != null) {
                return objectMapper.readValue(inputStream, typeReference);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
