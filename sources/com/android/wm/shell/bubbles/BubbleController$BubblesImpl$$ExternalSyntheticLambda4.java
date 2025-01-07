package com.android.wm.shell.bubbles;

import android.os.RemoteException;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewRootImpl;
import android.window.ScreenCapture;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.bar.BubbleBarLayerView;
import com.android.wm.shell.shared.bubbles.BubbleBarLocation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$BubblesImpl$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Object f$2;

    public /* synthetic */ BubbleController$BubblesImpl$$ExternalSyntheticLambda4(BubbleController.BubblesImpl bubblesImpl, int i, ScreenCapture.SynchronousScreenCaptureListener synchronousScreenCaptureListener) {
        this.$r8$classId = 0;
        this.f$0 = bubblesImpl;
        this.f$1 = i;
        this.f$2 = synchronousScreenCaptureListener;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ViewRootImpl viewRootImpl;
        SurfaceControl surfaceControl;
        BubbleBarLayerView bubbleBarLayerView;
        switch (this.$r8$classId) {
            case 0:
                BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) this.f$0;
                int i = this.f$1;
                ScreenCapture.SynchronousScreenCaptureListener synchronousScreenCaptureListener = (ScreenCapture.SynchronousScreenCaptureListener) this.f$2;
                BubbleController bubbleController = BubbleController.this;
                bubbleController.getClass();
                try {
                    View view = bubbleController.mStackView;
                    if (view == null) {
                        view = bubbleController.mLayerView;
                    }
                    bubbleController.mWmService.captureDisplay(i, (view == null || (viewRootImpl = view.getViewRootImpl()) == null || (surfaceControl = viewRootImpl.getSurfaceControl()) == null) ? null : new ScreenCapture.CaptureArgs.Builder().setExcludeLayers(new SurfaceControl[]{surfaceControl}).build(), synchronousScreenCaptureListener);
                    break;
                } catch (RemoteException unused) {
                    Log.e("Bubbles", "Failed to capture screenshot");
                    return;
                }
                break;
            case 1:
                BubbleController.IBubblesImpl iBubblesImpl = (BubbleController.IBubblesImpl) this.f$0;
                BubbleBarLocation bubbleBarLocation = (BubbleBarLocation) this.f$2;
                int i2 = this.f$1;
                BubbleController bubbleController2 = iBubblesImpl.mController;
                BubblePositioner bubblePositioner = bubbleController2.mBubblePositioner;
                bubblePositioner.mBubbleBarLocation = bubbleBarLocation;
                bubblePositioner.mBubbleBarTopOnScreen = i2;
                BubbleViewProvider bubbleViewProvider = bubbleController2.mBubbleData.mSelectedBubble;
                if (bubbleViewProvider != null && bubbleViewProvider != null && (bubbleBarLayerView = bubbleController2.mLayerView) != null) {
                    bubbleBarLayerView.showExpandedView(bubbleViewProvider);
                    break;
                }
                break;
            default:
                ((BubbleController.IBubblesImpl) this.f$0).mController.expandStackAndSelectBubbleFromLauncher((String) this.f$2, this.f$1);
                break;
        }
    }

    public /* synthetic */ BubbleController$BubblesImpl$$ExternalSyntheticLambda4(BubbleController.IBubblesImpl iBubblesImpl, Object obj, int i, int i2) {
        this.$r8$classId = i2;
        this.f$0 = iBubblesImpl;
        this.f$2 = obj;
        this.f$1 = i;
    }
}
