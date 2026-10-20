package com.myst.mouthful.core.helper;

// Adapted from https://easings.net
public class Easing {
    public static double easeInExpo(float n) {
        return n == 0 ? 0 : Math.pow(2, 10 * n - 10);
    }
    public static double easeOutExpo(float n) {
        return n == 1 ? 1 : 1 - Math.pow(2, -10 * n);
    }
}
