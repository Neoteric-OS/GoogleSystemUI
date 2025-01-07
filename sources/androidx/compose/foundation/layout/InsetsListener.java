package androidx.compose.foundation.layout;

import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class InsetsListener extends WindowInsetsAnimationCompat.Callback implements Runnable, OnApplyWindowInsetsListener, View.OnAttachStateChangeListener {
    public final WindowInsetsHolder composeInsets;
    public boolean prepared;
    public boolean runningAnimation;
    public WindowInsetsCompat savedInsets;

    public InsetsListener(WindowInsetsHolder windowInsetsHolder) {
        super(!windowInsetsHolder.consumes ? 1 : 0);
        this.composeInsets = windowInsetsHolder;
    }

    @Override // androidx.core.view.OnApplyWindowInsetsListener
    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        this.savedInsets = windowInsetsCompat;
        WindowInsetsHolder windowInsetsHolder = this.composeInsets;
        windowInsetsHolder.getClass();
        WindowInsetsCompat.Impl impl = windowInsetsCompat.mImpl;
        windowInsetsHolder.imeAnimationTarget.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(impl.getInsets(8)));
        if (!this.prepared && !this.runningAnimation) {
            WindowInsetsHolder windowInsetsHolder2 = this.composeInsets;
            windowInsetsHolder2.getClass();
            windowInsetsHolder2.imeAnimationSource.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(impl.getInsets(8)));
            WindowInsetsHolder.update$default(this.composeInsets, windowInsetsCompat);
        }
        return this.composeInsets.consumes ? WindowInsetsCompat.CONSUMED : windowInsetsCompat;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public final void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        this.prepared = false;
        this.runningAnimation = false;
        WindowInsetsCompat windowInsetsCompat = this.savedInsets;
        if (windowInsetsAnimationCompat.mImpl.mWrapped.getDurationMillis() != 0 && windowInsetsCompat != null) {
            WindowInsetsHolder windowInsetsHolder = this.composeInsets;
            windowInsetsHolder.getClass();
            WindowInsetsCompat.Impl impl = windowInsetsCompat.mImpl;
            windowInsetsHolder.imeAnimationSource.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(impl.getInsets(8)));
            WindowInsetsHolder windowInsetsHolder2 = this.composeInsets;
            windowInsetsHolder2.getClass();
            windowInsetsHolder2.imeAnimationTarget.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(impl.getInsets(8)));
            WindowInsetsHolder.update$default(this.composeInsets, windowInsetsCompat);
        }
        this.savedInsets = null;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public final void onPrepare() {
        this.prepared = true;
        this.runningAnimation = true;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public final WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat, List list) {
        WindowInsetsHolder.update$default(this.composeInsets, windowInsetsCompat);
        return this.composeInsets.consumes ? WindowInsetsCompat.CONSUMED : windowInsetsCompat;
    }

    @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
    public final WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat.BoundsCompat boundsCompat) {
        this.prepared = false;
        return boundsCompat;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        view.requestApplyInsets();
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.prepared) {
            this.prepared = false;
            this.runningAnimation = false;
            WindowInsetsCompat windowInsetsCompat = this.savedInsets;
            if (windowInsetsCompat != null) {
                WindowInsetsHolder windowInsetsHolder = this.composeInsets;
                windowInsetsHolder.getClass();
                windowInsetsHolder.imeAnimationSource.setValue$foundation_layout_release(WindowInsets_androidKt.toInsetsValues(windowInsetsCompat.mImpl.getInsets(8)));
                WindowInsetsHolder.update$default(this.composeInsets, windowInsetsCompat);
                this.savedInsets = null;
            }
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
