package com.android.systemui.dreams;

import android.animation.Animator;
import android.view.View;
import com.android.systemui.ambient.statusbar.ui.AmbientStatusBarView;
import com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController;
import com.android.systemui.complication.ComplicationHostViewController;
import com.android.systemui.dreams.ui.viewmodel.DreamViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.Logger;
import com.android.systemui.statusbar.BlurUtils;
import com.android.systemui.statusbar.CrossFadeHelper;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DreamOverlayAnimationsController {
    public final DreamViewModel dreamViewModel;
    public final Logger logger;
    public Animator mAnimator;
    public final BlurUtils mBlurUtils;
    public final ComplicationHostViewController mComplicationHostViewController;
    public final Map mCurrentAlphaAtPosition = new LinkedHashMap();
    public float mCurrentBlurRadius;
    public final int mDreamBlurRadius;
    public final long mDreamInBlurAnimDurationMs;
    public final long mDreamInComplicationsAnimDurationMs;
    public final int mDreamInTranslationYDistance;
    public final long mDreamInTranslationYDurationMs;
    public RepeatWhenAttachedKt$repeatWhenAttached$1 mLifecycleFlowHandle;
    public final DreamOverlayStateController mOverlayStateController;
    public final AmbientStatusBarViewController mStatusBarViewController;
    public View view;

    public DreamOverlayAnimationsController(BlurUtils blurUtils, ComplicationHostViewController complicationHostViewController, AmbientStatusBarViewController ambientStatusBarViewController, DreamOverlayStateController dreamOverlayStateController, int i, DreamViewModel dreamViewModel, long j, long j2, int i2, long j3, LogBuffer logBuffer) {
        this.mBlurUtils = blurUtils;
        this.mComplicationHostViewController = complicationHostViewController;
        this.mStatusBarViewController = ambientStatusBarViewController;
        this.mOverlayStateController = dreamOverlayStateController;
        this.mDreamBlurRadius = i;
        this.dreamViewModel = dreamViewModel;
        this.mDreamInBlurAnimDurationMs = j;
        this.mDreamInComplicationsAnimDurationMs = j2;
        this.mDreamInTranslationYDistance = i2;
        this.mDreamInTranslationYDurationMs = j3;
        this.logger = new Logger(logBuffer, "DreamOverlayAnimationsController");
    }

    public static final void access$setElementsAlphaAtPosition(DreamOverlayAnimationsController dreamOverlayAnimationsController, float f, int i, boolean z) {
        dreamOverlayAnimationsController.mCurrentAlphaAtPosition.put(Integer.valueOf(i), Float.valueOf(f));
        for (View view : dreamOverlayAnimationsController.mComplicationHostViewController.getViewsAtPosition(i)) {
            if (z) {
                CrossFadeHelper.fadeOut(view, 1 - f, false);
            } else {
                CrossFadeHelper.fadeIn(view, f, false);
            }
        }
        if (i == 1) {
            AmbientStatusBarViewController ambientStatusBarViewController = dreamOverlayAnimationsController.mStatusBarViewController;
            ambientStatusBarViewController.updateVisibility$1();
            if (((AmbientStatusBarView) ambientStatusBarViewController.mView).getVisibility() != 0) {
                return;
            }
            if (z) {
                CrossFadeHelper.fadeOut(ambientStatusBarViewController.mView, 1.0f - f, false);
            } else {
                CrossFadeHelper.fadeIn(ambientStatusBarViewController.mView, f, false);
            }
        }
    }

    public static final void access$setElementsTranslationYAtPosition(DreamOverlayAnimationsController dreamOverlayAnimationsController, float f, int i) {
        Iterator it = dreamOverlayAnimationsController.mComplicationHostViewController.getViewsAtPosition(i).iterator();
        while (it.hasNext()) {
            ((View) it.next()).setTranslationY(f);
        }
        if (i == 1) {
            ((AmbientStatusBarView) dreamOverlayAnimationsController.mStatusBarViewController.mView).setTranslationY(f);
        }
    }

    public final void cancelAnimations() {
        Animator animator = this.mAnimator;
        if (animator != null) {
            animator.cancel();
        }
        this.mAnimator = null;
        DreamOverlayStateController dreamOverlayStateController = this.mOverlayStateController;
        dreamOverlayStateController.getClass();
        dreamOverlayStateController.modifyState(1, 8);
    }
}
