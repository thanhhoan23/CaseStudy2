package utils;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InstantUtils {
    public static final String PATTERN = "dd - MM - yyyy";

    public static String instantConvertString(Instant instant) {
        return instantToString(instant, null);
    }

    public static String instantToString(Instant instant, String patternFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFormat != null ? patternFormat : PATTERN).withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
//    public static String doubleToVND(double value) {
//        String patternVND = ",### VND";
//        DecimalFormat decimalFormat = new DecimalFormat(patternVND);
//        return decimalFormat.format(value);
//    }
//Quy đổi tiền qua VNĐ

    public static String convertVND (double value ) {
        String patternVND = ",### VND";
        DecimalFormat decimalFormat = new DecimalFormat(patternVND);
        return decimalFormat.format(value);
    }
    public static String quantityProducts(double value) {
        String patternVND = "###";
        DecimalFormat decimalFormat = new DecimalFormat(patternVND);
        return decimalFormat.format(value);
    }
}
