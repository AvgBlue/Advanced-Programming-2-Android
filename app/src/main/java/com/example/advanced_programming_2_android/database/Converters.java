package com.example.advanced_programming_2_android.database;
import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converters {
    // List<String> converter
    @TypeConverter
    public static List<String> fromString(String value) {
        return Arrays.asList(value.split(","));
    }

    @TypeConverter
    public static String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String item : list) {
            sb.append(item);
            sb.append(",");
        }
        return sb.toString();
    }

    // List<Integer> converter
    @TypeConverter
    public static List<Integer> fromIntegerString(String value) {
        List<Integer> integerList = new ArrayList<>();
        String[] integerStrings = value.split(",");
        for (String integerString : integerStrings) {
            integerList.add(Integer.parseInt(integerString));
        }
        return integerList;
    }

    @TypeConverter
    public static String listToIntegerString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (Integer item : list) {
            sb.append(item);
            sb.append(",");
        }
        return sb.toString();
    }
}
