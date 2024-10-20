package com.example.constants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class SeasonTest {

    // Below test will execute all enum constant.
    @ParameterizedTest
    @EnumSource(Season.class)
    void testEnum(final Season season) {
        assertEquals(season, Season.getSeason(season.getLabel()));
    }

    // Below test will only include SUMMER and WINTER from enum constant.

    @ParameterizedTest
    @EnumSource(value = Season.class, names ={ "SUMMER", "WINTER" })
    void testEnum2(final Season season) {
        assertEquals(season, Season.getSeason(season.getLabel()));
    }
    // Below test will exclude SUMMER and WINTER from enum constant due to mode being excluded

    @ParameterizedTest
    @EnumSource(value = Season.class, names ={ "SUMMER", "WINTER" }, mode = EnumSource.Mode.EXCLUDE)
    void testEnum3(final Season season) {
        assertEquals(season, Season.getSeason(season.getLabel()));
    }

}