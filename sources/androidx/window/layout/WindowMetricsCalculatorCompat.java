package androidx.window.layout;

import android.content.Context;
import android.view.WindowManager;
import androidx.core.view.WindowInsetsCompat;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowMetricsCalculatorCompat implements WindowMetricsCalculator {
    public final WindowMetrics computeCurrentWindowMetrics(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(WindowManager.class);
        return new WindowMetrics(windowManager.getCurrentWindowMetrics().getBounds(), WindowInsetsCompat.toWindowInsetsCompat(null, windowManager.getCurrentWindowMetrics().getWindowInsets()), windowManager.getCurrentWindowMetrics().getDensity());
    }
}
