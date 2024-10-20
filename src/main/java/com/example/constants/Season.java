package com.example.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Season {
    SUMMER("summer"),
    WINTER("winter"),
    SPRING("spring"),
    FALL("fall");
    private final String label;

    public static Season getSeason(final String label) {
        return Arrays.stream(Season.values())
                .filter(seasons -> seasons.label.equals(label))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

}
