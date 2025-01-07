package androidx.window.layout;

import androidx.core.view.WindowInsetsCompat;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface WindowMetricsCalculator {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final Function1 decorator = WindowMetricsCalculator$Companion$decorator$1.INSTANCE;
        public static final WindowMetricsCalculatorCompat windowMetricsCalculatorCompat;

        static {
            WindowMetricsCalculatorCompat windowMetricsCalculatorCompat2 = new WindowMetricsCalculatorCompat();
            CollectionsKt__CollectionsKt.arrayListOf(1, 2, 4, 8, 16, 32, 64, 128);
            windowMetricsCalculatorCompat = windowMetricsCalculatorCompat2;
        }

        public static WindowMetricsCalculatorCompat getOrCreate() {
            ((WindowMetricsCalculator$Companion$decorator$1) decorator).getClass();
            return windowMetricsCalculatorCompat;
        }

        public static WindowMetrics translateWindowMetrics$window_release(android.view.WindowMetrics windowMetrics) {
            return new WindowMetrics(windowMetrics.getBounds(), WindowInsetsCompat.toWindowInsetsCompat(null, windowMetrics.getWindowInsets()), windowMetrics.getDensity());
        }
    }
}
