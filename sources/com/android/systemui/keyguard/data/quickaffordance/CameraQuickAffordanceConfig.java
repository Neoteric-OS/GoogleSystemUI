package com.android.systemui.keyguard.data.quickaffordance;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.pm.PackageManager;
import com.android.systemui.animation.Expandable$Companion$fromView$1;
import com.android.systemui.camera.CameraGestureHelper;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig;
import com.android.systemui.settings.UserTracker;
import com.android.wm.shell.R;
import dagger.Lazy;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CameraQuickAffordanceConfig implements KeyguardQuickAffordanceConfig {
    public final CoroutineDispatcher backgroundDispatcher;
    public final Lazy cameraGestureHelper;
    public final Context context;
    public final DevicePolicyManager devicePolicyManager;
    public final PackageManager packageManager;
    public final UserTracker userTracker;

    public CameraQuickAffordanceConfig(Context context, PackageManager packageManager, Lazy lazy, UserTracker userTracker, DevicePolicyManager devicePolicyManager, CoroutineDispatcher coroutineDispatcher) {
        this.context = context;
        this.packageManager = packageManager;
        this.cameraGestureHelper = lazy;
        this.userTracker = userTracker;
        this.devicePolicyManager = devicePolicyManager;
        this.backgroundDispatcher = coroutineDispatcher;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final String getKey() {
        return "camera";
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final Flow getLockScreenState() {
        return new SafeFlow(new CameraQuickAffordanceConfig$lockScreenState$1(this, null));
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final int getPickerIconResourceId() {
        return R.drawable.ic_camera;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getPickerScreenState(kotlin.coroutines.Continuation r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.android.systemui.keyguard.data.quickaffordance.CameraQuickAffordanceConfig$getPickerScreenState$1
            if (r0 == 0) goto L13
            r0 = r7
            com.android.systemui.keyguard.data.quickaffordance.CameraQuickAffordanceConfig$getPickerScreenState$1 r0 = (com.android.systemui.keyguard.data.quickaffordance.CameraQuickAffordanceConfig$getPickerScreenState$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L1a
        L13:
            com.android.systemui.keyguard.data.quickaffordance.CameraQuickAffordanceConfig$getPickerScreenState$1 r0 = new com.android.systemui.keyguard.data.quickaffordance.CameraQuickAffordanceConfig$getPickerScreenState$1
            kotlin.coroutines.jvm.internal.ContinuationImpl r7 = (kotlin.coroutines.jvm.internal.ContinuationImpl) r7
            r0.<init>(r6, r7)
        L1a:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L3d
            if (r2 == r5) goto L35
            if (r2 != r4) goto L2d
            kotlin.ResultKt.throwOnFailure(r7)
            goto L76
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L35:
            java.lang.Object r6 = r0.L$0
            com.android.systemui.keyguard.data.quickaffordance.CameraQuickAffordanceConfig r6 = (com.android.systemui.keyguard.data.quickaffordance.CameraQuickAffordanceConfig) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L5f
        L3d:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r6
            r0.label = r5
            android.content.pm.PackageManager r7 = r6.packageManager
            java.lang.String r2 = "android.hardware.camera.any"
            boolean r7 = r7.hasSystemFeature(r2)
            if (r7 == 0) goto L5a
            com.android.systemui.keyguard.data.quickaffordance.CameraQuickAffordanceConfig$isLaunchable$2 r7 = new com.android.systemui.keyguard.data.quickaffordance.CameraQuickAffordanceConfig$isLaunchable$2
            r7.<init>(r6, r3)
            kotlinx.coroutines.CoroutineDispatcher r2 = r6.backgroundDispatcher
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r2, r7, r0)
            goto L5c
        L5a:
            java.lang.Boolean r7 = java.lang.Boolean.FALSE
        L5c:
            if (r7 != r1) goto L5f
            return r1
        L5f:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L77
            r0.L$0 = r3
            r0.label = r4
            r6.getClass()
            com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig$PickerScreenState$Default r7 = new com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig$PickerScreenState$Default
            r7.<init>(r3)
            if (r7 != r1) goto L76
            return r1
        L76:
            return r7
        L77:
            com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig$PickerScreenState$UnavailableOnDevice r6 = com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig.PickerScreenState.UnavailableOnDevice.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.quickaffordance.CameraQuickAffordanceConfig.getPickerScreenState(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final KeyguardQuickAffordanceConfig.OnTriggeredResult onTriggered(Expandable$Companion$fromView$1 expandable$Companion$fromView$1) {
        ((CameraGestureHelper) this.cameraGestureHelper.get()).launchCamera(3);
        return KeyguardQuickAffordanceConfig.OnTriggeredResult.Handled.INSTANCE;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final String pickerName() {
        return this.context.getString(R.string.accessibility_camera_button);
    }
}
