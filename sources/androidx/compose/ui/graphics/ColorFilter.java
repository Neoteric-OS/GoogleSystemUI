package androidx.compose.ui.graphics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ColorFilter {
    public static final Companion Companion = null;
    public final android.graphics.ColorFilter nativeColorFilter;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        /* renamed from: tint-xETnrds$default, reason: not valid java name */
        public static BlendModeColorFilter m370tintxETnrds$default(long j) {
            return new BlendModeColorFilter(j, 5);
        }
    }

    public ColorFilter(android.graphics.ColorFilter colorFilter) {
        this.nativeColorFilter = colorFilter;
    }
}
