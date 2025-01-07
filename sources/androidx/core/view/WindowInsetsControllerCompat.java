package androidx.core.view;

import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import androidx.core.view.SoftwareKeyboardControllerCompat;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowInsetsControllerCompat {
    public final Impl35 mImpl;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Impl35 {
        public final WindowInsetsController mInsetsController;
        public final Window mWindow;

        public Impl35(Window window) {
            this.mInsetsController = window.getInsetsController();
            this.mWindow = window;
        }
    }

    public WindowInsetsControllerCompat(Window window, View view) {
        new SoftwareKeyboardControllerCompat.Impl30(view).mView = view;
        this.mImpl = new Impl35(window);
    }

    public final void setAppearanceLightNavigationBars(boolean z) {
        Impl35 impl35 = this.mImpl;
        if (z) {
            Window window = impl35.mWindow;
            if (window != null) {
                View decorView = window.getDecorView();
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 16);
            }
            impl35.mInsetsController.setSystemBarsAppearance(16, 16);
            return;
        }
        Window window2 = impl35.mWindow;
        if (window2 != null) {
            View decorView2 = window2.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() & (-17));
        }
        impl35.mInsetsController.setSystemBarsAppearance(0, 16);
    }

    public final void setAppearanceLightStatusBars(boolean z) {
        Impl35 impl35 = this.mImpl;
        if (z) {
            Window window = impl35.mWindow;
            if (window != null) {
                View decorView = window.getDecorView();
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 8192);
            }
            impl35.mInsetsController.setSystemBarsAppearance(8, 8);
            return;
        }
        Window window2 = impl35.mWindow;
        if (window2 != null) {
            View decorView2 = window2.getDecorView();
            decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() & (-8193));
        }
        impl35.mInsetsController.setSystemBarsAppearance(0, 8);
    }
}
