package androidx.compose.material3;

import android.content.Context;
import androidx.compose.ui.graphics.ColorKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
abstract class ColorResourceHelper {
    /* renamed from: getColor-WaAFU9c, reason: not valid java name */
    public static long m201getColorWaAFU9c(int i, Context context) {
        return ColorKt.Color(context.getResources().getColor(i, context.getTheme()));
    }
}
