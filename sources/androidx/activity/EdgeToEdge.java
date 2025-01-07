package androidx.activity;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import androidx.core.view.WindowInsetsControllerCompat;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class EdgeToEdge {
    static {
        Color.argb(230, 255, 255, 255);
        Color.argb(128, 27, 27, 27);
    }

    public static void enable$default(ComponentActivity componentActivity) {
        SystemBarStyle$Companion$auto$1 systemBarStyle$Companion$auto$1 = new Function1() { // from class: androidx.activity.SystemBarStyle$Companion$auto$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf((((Resources) obj).getConfiguration().uiMode & 48) == 32);
            }
        };
        View decorView = componentActivity.getWindow().getDecorView();
        boolean booleanValue = ((Boolean) systemBarStyle$Companion$auto$1.invoke(decorView.getResources())).booleanValue();
        boolean booleanValue2 = ((Boolean) systemBarStyle$Companion$auto$1.invoke(decorView.getResources())).booleanValue();
        Window window = componentActivity.getWindow();
        window.setDecorFitsSystemWindows(false);
        window.setStatusBarColor(0);
        window.setNavigationBarColor(0);
        window.setStatusBarContrastEnforced(false);
        window.setNavigationBarContrastEnforced(true);
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(window, decorView);
        windowInsetsControllerCompat.setAppearanceLightStatusBars(!booleanValue);
        windowInsetsControllerCompat.setAppearanceLightNavigationBars(!booleanValue2);
        componentActivity.getWindow().getAttributes().layoutInDisplayCutoutMode = 3;
    }
}
