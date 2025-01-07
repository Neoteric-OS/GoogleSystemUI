package com.android.systemui.shade;

import android.animation.ValueAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class QuickSettingsControllerImpl$$ExternalSyntheticLambda1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ QuickSettingsControllerImpl f$0;

    public /* synthetic */ QuickSettingsControllerImpl$$ExternalSyntheticLambda1(QuickSettingsControllerImpl quickSettingsControllerImpl, int i) {
        this.$r8$classId = i;
        this.f$0 = quickSettingsControllerImpl;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = this.$r8$classId;
        QuickSettingsControllerImpl quickSettingsControllerImpl = this.f$0;
        switch (i) {
            case 0:
                quickSettingsControllerImpl.getClass();
                quickSettingsControllerImpl.setExpansionHeight(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            default:
                NotificationPanelViewController$$ExternalSyntheticLambda11 notificationPanelViewController$$ExternalSyntheticLambda11 = quickSettingsControllerImpl.mExpansionHeightSetToMaxListener;
                if (notificationPanelViewController$$ExternalSyntheticLambda11 != null) {
                    notificationPanelViewController$$ExternalSyntheticLambda11.onExpansionHeightSetToMax(true);
                }
                quickSettingsControllerImpl.mQs.setHeightOverride(((Integer) quickSettingsControllerImpl.mSizeChangeAnimator.getAnimatedValue()).intValue());
                break;
        }
    }
}
