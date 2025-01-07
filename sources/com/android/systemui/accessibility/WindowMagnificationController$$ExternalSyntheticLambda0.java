package com.android.systemui.accessibility;

import android.graphics.Insets;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import android.view.WindowInsets;
import android.view.WindowMetrics;
import android.view.accessibility.IMagnificationConnectionCallback;
import com.android.systemui.accessibility.MagnificationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class WindowMagnificationController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ WindowMagnificationController f$0;

    public /* synthetic */ WindowMagnificationController$$ExternalSyntheticLambda0(WindowMagnificationController windowMagnificationController, int i) {
        this.$r8$classId = i;
        this.f$0 = windowMagnificationController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        IMagnificationConnectionCallback iMagnificationConnectionCallback;
        int i = this.$r8$classId;
        WindowMagnificationController windowMagnificationController = this.f$0;
        switch (i) {
            case 0:
                windowMagnificationController.applyTapExcludeRegion();
                break;
            case 1:
                MagnificationImpl.AnonymousClass3 anonymousClass3 = windowMagnificationController.mWindowMagnifierCallback;
                int i2 = windowMagnificationController.mDisplayId;
                Rect rect = windowMagnificationController.mSourceBounds;
                MagnificationConnectionImpl magnificationConnectionImpl = MagnificationImpl.this.mMagnificationConnectionImpl;
                if (magnificationConnectionImpl != null && (iMagnificationConnectionCallback = magnificationConnectionImpl.mConnectionCallback) != null) {
                    try {
                        iMagnificationConnectionCallback.onSourceBoundsChanged(i2, rect);
                        break;
                    } catch (RemoteException e) {
                        Log.e("WindowMagnificationConnectionImpl", "Failed to inform source bounds changed", e);
                        return;
                    }
                }
                break;
            case 2:
                WindowMetrics currentWindowMetrics = windowMagnificationController.mWm.getCurrentWindowMetrics();
                Insets insets = currentWindowMetrics.getWindowInsets().getInsets(WindowInsets.Type.systemGestures());
                int i3 = insets.bottom != 0 ? currentWindowMetrics.getBounds().bottom - insets.bottom : -1;
                if (i3 != windowMagnificationController.mSystemGestureTop) {
                    windowMagnificationController.mSystemGestureTop = i3;
                    windowMagnificationController.updateSysUIState(false);
                    break;
                }
                break;
            case 3:
                if (windowMagnificationController.isActivated()) {
                    windowMagnificationController.mMirrorView.setStateDescription(windowMagnificationController.formatStateDescription(windowMagnificationController.mScale));
                    break;
                }
                break;
            default:
                windowMagnificationController.maybeRepositionButton();
                break;
        }
    }
}
