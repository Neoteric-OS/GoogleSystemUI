package androidx.compose.foundation.lazy.layout;

import android.os.Build;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PrefetchScheduler_androidKt {
    public static final PrefetchScheduler_androidKt$RobolectricImpl$1 RobolectricImpl;

    static {
        RobolectricImpl = Build.FINGERPRINT.toLowerCase(Locale.ROOT).equals("robolectric") ? new PrefetchScheduler_androidKt$RobolectricImpl$1() : null;
    }
}
