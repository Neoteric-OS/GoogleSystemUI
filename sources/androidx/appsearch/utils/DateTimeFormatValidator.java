package androidx.appsearch.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DateTimeFormatValidator {
    public static boolean validateDateFormat(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.US);
        simpleDateFormat.setLenient(false);
        try {
            Date parse = simpleDateFormat.parse(str2);
            if (parse != null) {
                return str2.equals(simpleDateFormat.format(parse));
            }
            return false;
        } catch (ParseException unused) {
            return false;
        }
    }
}
