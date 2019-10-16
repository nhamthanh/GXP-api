package com.example.demo.utils;

import com.example.demo.constant.Constant;

import io.jenetics.jpx.Length;

public class GPXUtils {
    // Function to convert Length to int
    public static String lengthToString(Length number) {
        if (number == null) {
            return Constant.ZERO;
        }
        return number.toString();
    }
}
