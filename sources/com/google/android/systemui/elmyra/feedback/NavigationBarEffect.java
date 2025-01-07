package com.google.android.systemui.elmyra.feedback;

import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NavigationBarEffect implements FeedbackEffect, NavigationModeController.ModeChangedListener {
    public final CentralSurfaces mCentralSurfaces;
    public final List mFeedbackEffects = new ArrayList();
    public int mNavMode;

    public NavigationBarEffect(CentralSurfaces centralSurfaces, NavigationModeController navigationModeController) {
        this.mCentralSurfaces = centralSurfaces;
        this.mNavMode = navigationModeController.addListener(this);
    }

    public abstract List findFeedbackEffects(NavigationBarView navigationBarView);

    public abstract boolean isActiveFeedbackEffect(FeedbackEffect feedbackEffect);

    @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
    public final void onNavigationModeChanged(int i) {
        this.mNavMode = i;
        refreshFeedbackEffects();
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onProgress(int i, float f) {
        refreshFeedbackEffects();
        for (int i2 = 0; i2 < ((ArrayList) this.mFeedbackEffects).size(); i2++) {
            FeedbackEffect feedbackEffect = (FeedbackEffect) ((ArrayList) this.mFeedbackEffects).get(i2);
            if (isActiveFeedbackEffect(feedbackEffect)) {
                feedbackEffect.onProgress(i, f);
            }
        }
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onRelease() {
        refreshFeedbackEffects();
        for (int i = 0; i < ((ArrayList) this.mFeedbackEffects).size(); i++) {
            ((FeedbackEffect) ((ArrayList) this.mFeedbackEffects).get(i)).onRelease();
        }
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        refreshFeedbackEffects();
        for (int i = 0; i < ((ArrayList) this.mFeedbackEffects).size(); i++) {
            ((FeedbackEffect) ((ArrayList) this.mFeedbackEffects).get(i)).onResolve(detectionProperties);
        }
    }

    public final void refreshFeedbackEffects() {
        NavigationBarView navigationBarView = ((CentralSurfacesImpl) this.mCentralSurfaces).getNavigationBarView();
        if (navigationBarView == null || QuickStepContract.isGesturalMode(this.mNavMode)) {
            reset$1();
            return;
        }
        if (!validateFeedbackEffects(this.mFeedbackEffects)) {
            this.mFeedbackEffects.clear();
        }
        if (this.mFeedbackEffects.isEmpty()) {
            this.mFeedbackEffects.addAll(findFeedbackEffects(navigationBarView));
        }
    }

    public void reset$1() {
        this.mFeedbackEffects.clear();
    }

    public abstract boolean validateFeedbackEffects(List list);
}
