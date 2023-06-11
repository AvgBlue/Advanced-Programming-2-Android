package com.example.advanced_programming_2_android.database;
import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class Converters {
    // list<String> converter
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
}
