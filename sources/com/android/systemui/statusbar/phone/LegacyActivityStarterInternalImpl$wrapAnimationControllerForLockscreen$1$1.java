package com.android.systemui.statusbar.phone;

import android.util.Log;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.DelegateTransitionAnimatorController;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LegacyActivityStarterInternalImpl$wrapAnimationControllerForLockscreen$1$1 extends DelegateTransitionAnimatorController {
    public final /* synthetic */ boolean $dismissShade;
    public final /* synthetic */ LegacyActivityStarterInternalImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LegacyActivityStarterInternalImpl$wrapAnimationControllerForLockscreen$1$1(ActivityTransitionAnimator.Controller controller, LegacyActivityStarterInternalImpl legacyActivityStarterInternalImpl, boolean z) {
        super(controller);
        this.this$0 = legacyActivityStarterInternalImpl;
        this.$dismissShade = z;
    }

    @Override // com.android.systemui.animation.DelegateTransitionAnimatorController, com.android.systemui.animation.ActivityTransitionAnimator.Controller
    public final void onIntentStarted(boolean z) {
        CentralSurfaces centralSurfaces$1;
        this.delegate.onIntentStarted(z);
        if (!z || (centralSurfaces$1 = this.this$0.getCentralSurfaces$1()) == null) {
            return;
        }
        ((CentralSurfacesImpl) centralSurfaces$1).setIsLaunchingActivityOverLockscreen(true, this.$dismissShade);
    }

    @Override // com.android.systemui.animation.DelegateTransitionAnimatorController, com.android.systemui.animation.ActivityTransitionAnimator.Controller
    public final void onTransitionAnimationCancelled() {
        CentralSurfaces centralSurfaces$1 = this.this$0.getCentralSurfaces$1();
        if (centralSurfaces$1 != null) {
            ((CentralSurfacesImpl) centralSurfaces$1).setIsLaunchingActivityOverLockscreen(false, false);
        }
        this.delegate.onTransitionAnimationCancelled();
    }

    @Override // com.android.systemui.animation.DelegateTransitionAnimatorController, com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationEnd(boolean z) {
        CentralSurfaces centralSurfaces$1 = this.this$0.getCentralSurfaces$1();
        if (centralSurfaces$1 != null) {
            ((CentralSurfacesImpl) centralSurfaces$1).setIsLaunchingActivityOverLockscreen(false, false);
        }
        this.delegate.onTransitionAnimationEnd(z);
    }

    @Override // com.android.systemui.animation.DelegateTransitionAnimatorController, com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationStart(boolean z) {
        super.onTransitionAnimationStart(z);
        LegacyActivityStarterInternalImpl legacyActivityStarterInternalImpl = this.this$0;
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) legacyActivityStarterInternalImpl.keyguardStateController;
        if (!keyguardStateControllerImpl.mShowing || keyguardStateControllerImpl.mKeyguardGoingAway) {
            return;
        }
        Log.d("LegacyActivityStarterInternalImpl", "Setting occluded = true in #startActivity.");
        ((KeyguardViewMediator) legacyActivityStarterInternalImpl.keyguardViewMediatorLazy.get()).setOccluded(true, true);
    }
}
