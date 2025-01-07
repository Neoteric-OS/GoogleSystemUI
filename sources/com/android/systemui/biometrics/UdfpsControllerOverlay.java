package com.android.systemui.biometrics;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.fingerprint.IUdfpsOverlayControllerCallback;
import android.os.Build;
import android.util.RotationUtils;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor;
import com.android.systemui.biometrics.shared.model.UdfpsOverlayParams;
import com.android.systemui.biometrics.ui.view.UdfpsTouchOverlay;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.power.shared.model.WakefulnessModel;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.LockscreenShadeTransitionController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.SystemUIDialogManager;
import com.android.systemui.statusbar.phone.UnlockedScreenOffAnimationController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import dagger.Lazy;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UdfpsControllerOverlay {
    public final AccessibilityManager accessibilityManager;
    public UdfpsControllerOverlay$addViewNowOrLater$$inlined$Runnable$1 addViewRunnable;
    public final IUdfpsOverlayControllerCallback controllerCallback;
    public final WindowManager.LayoutParams coreLayoutParams;
    public final UdfpsControllerOverlay$special$$inlined$map$1 currentStateUpdatedToOffAodOrDozing;
    public final Lazy defaultUdfpsTouchOverlayViewModel;
    public final Lazy deviceEntryUdfpsTouchOverlayViewModel;
    public final LayoutInflater inflater;
    public final KeyguardStateController keyguardStateController;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public StandaloneCoroutine listenForCurrentKeyguardState;
    public final UdfpsController$UdfpsOverlayController$$ExternalSyntheticLambda6 onTouch;
    public UdfpsOverlayParams overlayParams;
    public UdfpsControllerOverlay$show$3$1 overlayTouchListener;
    public UdfpsTouchOverlay overlayTouchView;
    public final PowerInteractor powerInteractor;
    public final long requestId;
    public final int requestReason;
    public final CoroutineScope scope;
    public Rect sensorBounds;
    public final StatusBarStateController statusBarStateController;
    public final UdfpsDisplayMode udfpsDisplayModeProvider;
    public final UdfpsOverlayInteractor udfpsOverlayInteractor;
    public final ViewCaptureAwareWindowManager windowManager;

    public UdfpsControllerOverlay(Context context, LayoutInflater layoutInflater, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, AccessibilityManager accessibilityManager, StatusBarStateController statusBarStateController, StatusBarKeyguardViewManager statusBarKeyguardViewManager, KeyguardUpdateMonitor keyguardUpdateMonitor, SystemUIDialogManager systemUIDialogManager, DumpManager dumpManager, LockscreenShadeTransitionController lockscreenShadeTransitionController, ConfigurationController configurationController, KeyguardStateController keyguardStateController, UnlockedScreenOffAnimationController unlockedScreenOffAnimationController, UdfpsDisplayMode udfpsDisplayMode, long j, int i, IUdfpsOverlayControllerCallback iUdfpsOverlayControllerCallback, UdfpsController$UdfpsOverlayController$$ExternalSyntheticLambda6 udfpsController$UdfpsOverlayController$$ExternalSyntheticLambda6, ActivityTransitionAnimator activityTransitionAnimator, PrimaryBouncerInteractor primaryBouncerInteractor, AlternateBouncerInteractor alternateBouncerInteractor, UdfpsKeyguardAccessibilityDelegate udfpsKeyguardAccessibilityDelegate, KeyguardTransitionInteractor keyguardTransitionInteractor, SelectedUserInteractor selectedUserInteractor, Lazy lazy, Lazy lazy2, ShadeInteractor shadeInteractor, UdfpsOverlayInteractor udfpsOverlayInteractor, PowerInteractor powerInteractor, CoroutineScope coroutineScope) {
        boolean z = Build.IS_DEBUGGABLE;
        this.inflater = layoutInflater;
        this.windowManager = viewCaptureAwareWindowManager;
        this.accessibilityManager = accessibilityManager;
        this.statusBarStateController = statusBarStateController;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.keyguardStateController = keyguardStateController;
        this.udfpsDisplayModeProvider = udfpsDisplayMode;
        this.requestId = j;
        this.requestReason = i;
        this.controllerCallback = iUdfpsOverlayControllerCallback;
        this.onTouch = udfpsController$UdfpsOverlayController$$ExternalSyntheticLambda6;
        this.deviceEntryUdfpsTouchOverlayViewModel = lazy;
        this.defaultUdfpsTouchOverlayViewModel = lazy2;
        this.udfpsOverlayInteractor = udfpsOverlayInteractor;
        this.powerInteractor = powerInteractor;
        this.scope = coroutineScope;
        this.currentStateUpdatedToOffAodOrDozing = new UdfpsControllerOverlay$special$$inlined$map$1(new UdfpsControllerOverlay$special$$inlined$map$1(keyguardTransitionInteractor.currentKeyguardState, 1), 0);
        this.overlayParams = new UdfpsOverlayParams();
        this.sensorBounds = new Rect();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2024, 0, -3);
        layoutParams.setTitle("UdfpsControllerOverlay");
        layoutParams.setFitInsetsTypes(0);
        layoutParams.gravity = 51;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.flags = 25166120;
        layoutParams.privateFlags = 538968064;
        layoutParams.accessibilityTitle = " ";
        layoutParams.inputFeatures = 4;
        this.coreLayoutParams = layoutParams;
    }

    public final void addViewNowOrLater(UdfpsTouchOverlay udfpsTouchOverlay) {
        this.addViewRunnable = new UdfpsControllerOverlay$addViewNowOrLater$$inlined$Runnable$1(this, udfpsTouchOverlay);
        if (!((WakefulnessModel) ((StateFlowImpl) this.powerInteractor.detailedWakefulness.$$delegate_0).getValue()).isAwake()) {
            StandaloneCoroutine standaloneCoroutine = this.listenForCurrentKeyguardState;
            if (standaloneCoroutine != null) {
                standaloneCoroutine.cancel(null);
            }
            this.listenForCurrentKeyguardState = BuildersKt.launch$default(this.scope, null, null, new UdfpsControllerOverlay$addViewNowOrLater$2(this, null), 3);
            return;
        }
        UdfpsControllerOverlay$addViewNowOrLater$$inlined$Runnable$1 udfpsControllerOverlay$addViewNowOrLater$$inlined$Runnable$1 = this.addViewRunnable;
        if (udfpsControllerOverlay$addViewNowOrLater$$inlined$Runnable$1 != null) {
            StandaloneCoroutine standaloneCoroutine2 = this.listenForCurrentKeyguardState;
            if (standaloneCoroutine2 != null) {
                standaloneCoroutine2.cancel(null);
            }
            udfpsControllerOverlay$addViewNowOrLater$$inlined$Runnable$1.run();
        }
        this.addViewRunnable = null;
    }

    public final void updateDimensions(WindowManager.LayoutParams layoutParams) {
        Rect rect;
        int i = this.requestReason;
        boolean z = i == 1 || i == 2;
        if (this.accessibilityManager.isTouchExplorationEnabled() && z) {
            rect = new Rect(this.overlayParams.sensorBounds);
        } else {
            UdfpsOverlayParams udfpsOverlayParams = this.overlayParams;
            rect = new Rect(0, 0, udfpsOverlayParams.naturalDisplayWidth, udfpsOverlayParams.naturalDisplayHeight);
        }
        int i2 = this.overlayParams.rotation;
        if (i2 == 1 || i2 == 3) {
            KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.keyguardStateController;
            if (keyguardStateControllerImpl.mShowing && (this.keyguardUpdateMonitor.mGoingToSleep || !keyguardStateControllerImpl.mOccluded)) {
                Surface.rotationToString(i2);
                boolean z2 = keyguardStateControllerImpl.mOccluded;
            } else {
                Surface.rotationToString(i2);
                UdfpsOverlayParams udfpsOverlayParams2 = this.overlayParams;
                RotationUtils.rotateBounds(rect, udfpsOverlayParams2.naturalDisplayWidth, udfpsOverlayParams2.naturalDisplayHeight, i2);
                Rect rect2 = this.sensorBounds;
                UdfpsOverlayParams udfpsOverlayParams3 = this.overlayParams;
                RotationUtils.rotateBounds(rect2, udfpsOverlayParams3.naturalDisplayWidth, udfpsOverlayParams3.naturalDisplayHeight, i2);
            }
        }
        layoutParams.x = rect.left;
        layoutParams.y = rect.top;
        layoutParams.height = rect.height();
        layoutParams.width = rect.width();
    }
}
