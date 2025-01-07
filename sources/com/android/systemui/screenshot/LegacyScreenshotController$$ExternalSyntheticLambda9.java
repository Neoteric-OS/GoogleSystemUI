package com.android.systemui.screenshot;

import android.animation.Animator;
import android.content.Context;
import android.view.View;
import android.widget.Toast;
import com.android.systemui.screenshot.LegacyScreenshotController;
import com.android.systemui.screenshot.ui.ScreenshotAnimationController;
import com.android.systemui.screenshot.ui.viewmodel.ScreenshotViewModel;
import com.android.wm.shell.R;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class LegacyScreenshotController$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ LegacyScreenshotController$$ExternalSyntheticLambda9(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                LegacyScreenshotController legacyScreenshotController = (LegacyScreenshotController) obj;
                legacyScreenshotController.getClass();
                legacyScreenshotController.mViewProxy.requestDismissal(ScreenshotEvent.SCREENSHOT_INTERACTION_TIMEOUT, null);
                break;
            case 1:
                ((LegacyScreenshotController) obj).removeWindow();
                break;
            case 2:
                Toast.makeText((Context) ((LegacyScreenshotController) obj).mContext, R.string.screenshot_saved_title, 0).show();
                break;
            default:
                ScreenshotShelfViewProxy screenshotShelfViewProxy = (ScreenshotShelfViewProxy) obj;
                ScreenshotViewModel screenshotViewModel = screenshotShelfViewProxy.viewModel;
                screenshotViewModel._scrollableRect.setValue(null);
                screenshotViewModel._scrollingScrim.setValue(null);
                ScreenshotAnimationController screenshotAnimationController = screenshotShelfViewProxy.animationController;
                Animator animator = screenshotAnimationController.animator;
                if (animator != null) {
                    animator.cancel();
                }
                Iterator it = screenshotAnimationController.fadeUI.iterator();
                while (it.hasNext()) {
                    ((View) it.next()).setAlpha(1.0f);
                }
                screenshotAnimationController.screenshotPreview.setAlpha(1.0f);
                LegacyScreenshotController.AnonymousClass2 anonymousClass2 = screenshotShelfViewProxy.callbacks;
                if (anonymousClass2 != null) {
                    anonymousClass2.onUserInteraction();
                    break;
                }
                break;
        }
    }
}
