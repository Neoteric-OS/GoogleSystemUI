package com.android.systemui.statusbar.phone;

import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.shared.model.ScrimAlpha;
import com.android.systemui.keyguard.shared.model.TransitionState;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ScrimController$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ScrimController f$0;

    public /* synthetic */ ScrimController$$ExternalSyntheticLambda1(ScrimController scrimController, int i) {
        this.$r8$classId = i;
        this.f$0 = scrimController;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        ScrimController scrimController = this.f$0;
        switch (i) {
            case 0:
                boolean booleanValue = ((Boolean) obj).booleanValue();
                scrimController.mWallpaperSupportsAmbientMode = booleanValue;
                for (ScrimState scrimState : ScrimState.values()) {
                    scrimState.mWallpaperSupportsAmbientMode = booleanValue;
                }
                break;
            case 1:
                TransitionStep transitionStep = (TransitionStep) obj;
                scrimController.getClass();
                float f = ScrimState.KEYGUARD.mBehindAlpha;
                float f2 = transitionStep.value;
                KeyguardState keyguardState = KeyguardState.LOCKSCREEN;
                KeyguardState keyguardState2 = transitionStep.to;
                if (keyguardState2 == keyguardState) {
                    scrimController.mBehindAlpha = f * f2;
                } else if (keyguardState2 == KeyguardState.GLANCEABLE_HUB) {
                    scrimController.mBehindAlpha = (1.0f - f2) * f;
                }
                scrimController.mScrimBehind.setViewAlpha(scrimController.mBehindAlpha);
                break;
            case 2:
                scrimController.getClass();
                TransitionState transitionState = ((TransitionStep) obj).transitionState;
                scrimController.mIsBouncerToGoneTransitionRunning = transitionState == TransitionState.RUNNING;
                if (transitionState == TransitionState.STARTED) {
                    scrimController.mExpansionAffectsAlpha = false;
                    scrimController.internalTransitionTo(null, ScrimState.UNLOCKED);
                }
                if (transitionState == TransitionState.FINISHED || transitionState == TransitionState.CANCELED) {
                    scrimController.mExpansionAffectsAlpha = true;
                    if (((KeyguardStateControllerImpl) scrimController.mKeyguardStateController).mKeyguardFadingAway) {
                        scrimController.mStatusBarKeyguardViewManager.onKeyguardFadedAway$1();
                    }
                    scrimController.dispatchScrimsVisible();
                    break;
                }
                break;
            default:
                ScrimAlpha scrimAlpha = (ScrimAlpha) obj;
                scrimController.getClass();
                scrimAlpha.getClass();
                scrimController.mInFrontAlpha = 0.0f;
                scrimController.mScrimInFront.setViewAlpha(0.0f);
                float f3 = scrimAlpha.notificationsAlpha;
                scrimController.mNotificationsAlpha = f3;
                scrimController.mNotificationsScrim.setViewAlpha(f3);
                float f4 = scrimAlpha.behindAlpha;
                scrimController.mBehindAlpha = f4;
                scrimController.mScrimBehind.setViewAlpha(f4);
                break;
        }
    }
}
