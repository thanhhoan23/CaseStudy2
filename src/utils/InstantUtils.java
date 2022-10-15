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
//Quy đổi tiền qua VNĐ

    public static String convertVND (double value ) {
        String patternFormat = "###.### VNĐ";
        DecimalFormat decimalFormat = new DecimalFormat(patternFormat);
        return decimalFormat.format(value);
    }

}
