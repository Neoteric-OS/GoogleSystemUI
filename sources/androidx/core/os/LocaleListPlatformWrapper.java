package androidx.core.os;

import android.os.LocaleList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LocaleListPlatformWrapper {
    public final LocaleList mLocaleList;

    public LocaleListPlatformWrapper(Object obj) {
        this.mLocaleList = (LocaleList) obj;
    }

    public final boolean equals(Object obj) {
        return this.mLocaleList.equals(((LocaleListPlatformWrapper) obj).mLocaleList);
    }

    public final int hashCode() {
        return this.mLocaleList.hashCode();
    }

    public final String toString() {
        return this.mLocaleList.toString();
    }
}
