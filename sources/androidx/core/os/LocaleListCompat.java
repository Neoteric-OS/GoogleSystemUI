package androidx.core.os;

import android.os.LocaleList;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LocaleListCompat {
    public static final LocaleListCompat sEmptyLocaleList = new LocaleListCompat(new LocaleListPlatformWrapper(new LocaleList(new Locale[0])));
    public final LocaleListPlatformWrapper mImpl;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Api21Impl {
        public static final /* synthetic */ int $r8$clinit = 0;

        static {
            Locale[] localeArr = new Locale[2];
            new Locale("en", "XA");
            new Locale("ar", "XB");
        }
    }

    public LocaleListCompat(LocaleListPlatformWrapper localeListPlatformWrapper) {
        this.mImpl = localeListPlatformWrapper;
    }

    public static LocaleListCompat forLanguageTags(String str) {
        if (str == null || str.isEmpty()) {
            return sEmptyLocaleList;
        }
        String[] split = str.split(",", -1);
        int length = split.length;
        Locale[] localeArr = new Locale[length];
        for (int i = 0; i < length; i++) {
            String str2 = split[i];
            int i2 = Api21Impl.$r8$clinit;
            localeArr[i] = Locale.forLanguageTag(str2);
        }
        return new LocaleListCompat(new LocaleListPlatformWrapper(new LocaleList(localeArr)));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof LocaleListCompat) {
            if (this.mImpl.equals(((LocaleListCompat) obj).mImpl)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.mImpl.mLocaleList.hashCode();
    }

    public final String toString() {
        return this.mImpl.mLocaleList.toString();
    }
}
