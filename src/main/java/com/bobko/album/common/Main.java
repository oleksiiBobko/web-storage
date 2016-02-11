package com.bobko.album.common;

import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        String regex = "^(?!\\.)([a-zA-Z0-9-_\\.!?*+]*)@([a-zA-Z0-9-_\\.!?*+]*)$";
        boolean b = Pattern.matches(regex, "ole..ksii.bobko@somemail.org.kz");
        System.err.println(b);
    }
}
