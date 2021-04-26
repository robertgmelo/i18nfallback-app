package com.robertgmelo.i18nfallbackapp;

import static java.nio.charset.StandardCharsets.US_ASCII;

public class FWUtils {

    public static byte[] packLanguage(final String language) {
        return packLanguageOrRegion(language, (byte) 'a');
    }

    public static String unpackLanguage(byte[] language) {
        return unpackLanguageOrRegion(language, 0x61);
    }

    public static String printPack(final byte[] pack) {
        StringBuilder builder = new StringBuilder();
        if (pack != null && pack.length > 0) {
            for (byte b : pack) {
                builder.append(String.format("%02X", b));
            }
        }
        return builder.toString();
    }

    private static byte[] packLanguageOrRegion(final String in, final byte base) {
        final byte[] out = new byte[2];
        if (in == null) {
            out[0] = 0;
            out[1] = 0;
        } else if (in.length() < 3 || in.charAt(2) == 0 || in.charAt(2) == '-') {
            out[0] = (byte) in.charAt(0);
            out[1] = (byte) in.charAt(1);
        } else {
            byte first = (byte) ((in.charAt(0) - base) & 0x007f);
            byte second = (byte) ((in.charAt(1) - base) & 0x007f);
            byte third = (byte) ((in.charAt(2) - base) & 0x007f);

            out[0] = (byte) (0x80 | (third << 2) | (second >> 3));
            out[1] = (byte) ((second << 5) | first);
        }
        return out;
    }

    private static String unpackLanguageOrRegion(byte[] value, int base) {
        if (value[0] == 0 && value[1] == 0) {
            return "";
        }
        if ((value[0] & 0x80) != 0) {
            byte[] result = new byte[3];
            result[0] = (byte) (base + (value[1] & 0x1F));
            result[1] = (byte) (base + ((value[1] & 0xE0) >>> 5) + ((value[0] & 0x03) << 3));
            result[2] = (byte) (base + ((value[0] & 0x7C) >>> 2));
            return new String(result, US_ASCII);
        }
        return new String(value, US_ASCII);
    }

}
