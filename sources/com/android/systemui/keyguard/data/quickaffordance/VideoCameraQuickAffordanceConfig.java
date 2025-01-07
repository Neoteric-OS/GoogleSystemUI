package com.android.systemui.keyguard.data.quickaffordance;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import com.android.systemui.ActivityIntentHelper;
import com.android.systemui.animation.Expandable$Companion$fromView$1;
import com.android.systemui.camera.CameraIntentsWrapper;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.wm.shell.R;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class VideoCameraQuickAffordanceConfig implements KeyguardQuickAffordanceConfig {
    public final ActivityIntentHelper activityIntentHelper;
    public final CoroutineDispatcher backgroundDispatcher;
    public final CameraIntentsWrapper cameraIntents;
    public final Context context;
    public final DevicePolicyManager devicePolicyManager;
    public final UserTracker userTracker;

    public VideoCameraQuickAffordanceConfig(Context context, CameraIntentsWrapper cameraIntentsWrapper, ActivityIntentHelper activityIntentHelper, UserTracker userTracker, DevicePolicyManager devicePolicyManager, CoroutineDispatcher coroutineDispatcher) {
        this.context = context;
        this.activityIntentHelper = activityIntentHelper;
        this.userTracker = userTracker;
        this.devicePolicyManager = devicePolicyManager;
        this.backgroundDispatcher = coroutineDispatcher;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final String getKey() {
        return "video_camera";
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final Flow getLockScreenState() {
        return new SafeFlow(new VideoCameraQuickAffordanceConfig$lockScreenState$1(this, null));
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final int getPickerIconResourceId() {
        return R.drawable.ic_videocam;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getPickerScreenState(kotlin.coroutines.Continuation r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.android.systemui.keyguard.data.quickaffordance.VideoCameraQuickAffordanceConfig$getPickerScreenState$1
            if (r0 == 0) goto L13
            r0 = r6
            com.android.systemui.keyguard.data.quickaffordance.VideoCameraQuickAffordanceConfig$getPickerScreenState$1 r0 = (com.android.systemui.keyguard.data.quickaffordance.VideoCameraQuickAffordanceConfig$getPickerScreenState$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L1a
        L13:
            com.android.systemui.keyguard.data.quickaffordance.VideoCameraQuickAffordanceConfig$getPickerScreenState$1 r0 = new com.android.systemui.keyguard.data.quickaffordance.VideoCameraQuickAffordanceConfig$getPickerScreenState$1
            kotlin.coroutines.jvm.internal.ContinuationImpl r6 = (kotlin.coroutines.jvm.internal.ContinuationImpl) r6
            r0.<init>(r5, r6)
        L1a:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3c
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r6)
            goto L63
        L2c:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L34:
            java.lang.Object r5 = r0.L$0
            com.android.systemui.keyguard.data.quickaffordance.VideoCameraQuickAffordanceConfig r5 = (com.android.systemui.keyguard.data.quickaffordance.VideoCameraQuickAffordanceConfig) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4a
        L3c:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.isLaunchable$1(r0)
            if (r6 != r1) goto L4a
            return r1
        L4a:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L64
            r6 = 0
            r0.L$0 = r6
            r0.label = r3
            r5.getClass()
            com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig$PickerScreenState$Default r5 = new com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig$PickerScreenState$Default
            r5.<init>(r6)
            if (r5 != r1) goto L62
            return r1
        L62:
            r6 = r5
        L63:
            return r6
        L64:
            com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig$PickerScreenState$UnavailableOnDevice r5 = com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig.PickerScreenState.UnavailableOnDevice.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.quickaffordance.VideoCameraQuickAffordanceConfig.getPickerScreenState(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object isLaunchable$1(ContinuationImpl continuationImpl) {
        UserTracker userTracker = this.userTracker;
        ((UserTrackerImpl) userTracker).getUserId();
        Intent intent = new Intent("android.media.action.VIDEO_CAMERA");
        intent.putExtra("com.android.systemui.camera_launch_source", 3);
        if (this.activityIntentHelper.getTargetActivityInfo(intent, ((UserTrackerImpl) userTracker).getUserId(), true) == null) {
            return Boolean.FALSE;
        }
        return BuildersKt.withContext(this.backgroundDispatcher, new VideoCameraQuickAffordanceConfig$isLaunchable$2(this, null), continuationImpl);
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final KeyguardQuickAffordanceConfig.OnTriggeredResult onTriggered(Expandable$Companion$fromView$1 expandable$Companion$fromView$1) {
        ((UserTrackerImpl) this.userTracker).getUserId();
        Intent intent = new Intent("android.media.action.VIDEO_CAMERA");
        intent.putExtra("com.android.systemui.camera_launch_source", 3);
        return new KeyguardQuickAffordanceConfig.OnTriggeredResult.StartActivity(intent, false);
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig
    public final String pickerName() {
        return this.context.getString(R.string.video_camera);
    }
}
