package ru.herooo.html_parser.utils;

import java.io.File;

public class CustomUtils {
    public String getExtension(String pathOrFileName) {
        int dotLastIndex = pathOrFileName.lastIndexOf('.');
        return dotLastIndex != -1 ? pathOrFileName.substring(dotLastIndex) : null;
    }

    public String getExtension(File file) {
        return getExtension(file.getAbsolutePath());
    }

    public String getStringWithRandomSymbols(int length) {
        String lettersBuffer = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_()[]+-!";
        StringBuffer newString = new StringBuffer();
        for (int i = 0; i < length; i++) {
            newString.append(lettersBuffer.charAt(RandomObj.rnd.nextInt(lettersBuffer.length())));
        }

        return newString.toString();
    }

}

