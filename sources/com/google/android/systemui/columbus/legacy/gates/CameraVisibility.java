package com.google.android.systemui.columbus.legacy.gates;

import android.app.IActivityManager;
import android.app.TaskStackListener;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.systemui.columbus.legacy.actions.Action;
import com.google.android.systemui.columbus.legacy.gates.Gate;
import dagger.Lazy;
import java.util.Iterator;
import java.util.List;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CameraVisibility extends Gate {
    public final Lazy activityManager;
    public final CoroutineDispatcher bgDispatcher;
    public boolean cameraShowing;
    public boolean exceptionActive;
    public final List exceptions;
    public final KeyguardVisibility keyguardGate;
    public final PackageManager packageManager;
    public final PowerState powerState;
    public final CameraVisibility$taskStackListener$1 taskStackListener = new TaskStackListener() { // from class: com.google.android.systemui.columbus.legacy.gates.CameraVisibility$taskStackListener$1
        public final void onTaskStackChanged() {
            CameraVisibility cameraVisibility = CameraVisibility.this;
            BuildersKt.launch$default(cameraVisibility.coroutineScope, null, null, new CameraVisibility$updateCameraIsShowing$1(cameraVisibility, null), 3);
        }
    };
    public final CameraVisibility$gateListener$1 gateListener = new Gate.Listener() { // from class: com.google.android.systemui.columbus.legacy.gates.CameraVisibility$gateListener$1
        @Override // com.google.android.systemui.columbus.legacy.gates.Gate.Listener
        public final void onGateChanged(Gate gate) {
            CameraVisibility cameraVisibility = CameraVisibility.this;
            BuildersKt.launch$default(cameraVisibility.coroutineScope, null, null, new CameraVisibility$updateCameraIsShowing$1(cameraVisibility, null), 3);
        }
    };
    public final CameraVisibility$actionListener$1 actionListener = new Action.Listener() { // from class: com.google.android.systemui.columbus.legacy.gates.CameraVisibility$actionListener$1
        @Override // com.google.android.systemui.columbus.legacy.actions.Action.Listener
        public final void onActionAvailabilityChanged(Action action) {
            CameraVisibility cameraVisibility = CameraVisibility.this;
            BuildersKt.launch$default(cameraVisibility.coroutineScope, null, null, new CameraVisibility$actionListener$1$onActionAvailabilityChanged$1(cameraVisibility, null), 3);
        }
    };

    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.android.systemui.columbus.legacy.gates.CameraVisibility$taskStackListener$1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.google.android.systemui.columbus.legacy.gates.CameraVisibility$gateListener$1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.google.android.systemui.columbus.legacy.gates.CameraVisibility$actionListener$1] */
    public CameraVisibility(Context context, List list, KeyguardVisibility keyguardVisibility, PowerState powerState, Lazy lazy, CoroutineDispatcher coroutineDispatcher) {
        this.exceptions = list;
        this.keyguardGate = keyguardVisibility;
        this.powerState = powerState;
        this.activityManager = lazy;
        this.bgDispatcher = coroutineDispatcher;
        this.packageManager = context.getPackageManager();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$isCameraShowing(com.google.android.systemui.columbus.legacy.gates.CameraVisibility r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r5.getClass()
            boolean r0 = r6 instanceof com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraShowing$1
            if (r0 == 0) goto L16
            r0 = r6
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraShowing$1 r0 = (com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraShowing$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraShowing$1 r0 = new com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraShowing$1
            r0.<init>(r5, r6)
        L1b:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3d
            if (r2 == r4) goto L35
            if (r2 != r3) goto L2d
            kotlin.ResultKt.throwOnFailure(r6)
            goto L67
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            java.lang.Object r5 = r0.L$0
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility r5 = (com.google.android.systemui.columbus.legacy.gates.CameraVisibility) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L53
        L3d:
            kotlin.ResultKt.throwOnFailure(r6)
            com.google.android.systemui.columbus.legacy.gates.PowerState r6 = r5.powerState
            boolean r6 = r6.isBlocking()
            if (r6 != 0) goto L69
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r5.isCameraTopActivity(r0)
            if (r6 != r1) goto L53
            goto L6b
        L53:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L69
            r6 = 0
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r6 = r5.isCameraInForeground(r0)
            if (r6 != r1) goto L67
            goto L6b
        L67:
            r1 = r6
            goto L6b
        L69:
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
        L6b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.gates.CameraVisibility.access$isCameraShowing(com.google.android.systemui.columbus.legacy.gates.CameraVisibility, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object isCameraInForeground(kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraInForeground$1
            if (r0 == 0) goto L13
            r0 = r6
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraInForeground$1 r0 = (com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraInForeground$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraInForeground$1 r0 = new com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraInForeground$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: android.os.RemoteException -> L27 android.content.pm.PackageManager.NameNotFoundException -> L4d
            goto L45
        L27:
            r5 = move-exception
            goto L46
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.CoroutineDispatcher r6 = r5.bgDispatcher     // Catch: android.os.RemoteException -> L27 android.content.pm.PackageManager.NameNotFoundException -> L4d
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraInForeground$2 r2 = new com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraInForeground$2     // Catch: android.os.RemoteException -> L27 android.content.pm.PackageManager.NameNotFoundException -> L4d
            r4 = 0
            r2.<init>(r5, r4)     // Catch: android.os.RemoteException -> L27 android.content.pm.PackageManager.NameNotFoundException -> L4d
            r0.label = r3     // Catch: android.os.RemoteException -> L27 android.content.pm.PackageManager.NameNotFoundException -> L4d
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r2, r0)     // Catch: android.os.RemoteException -> L27 android.content.pm.PackageManager.NameNotFoundException -> L4d
            if (r6 != r1) goto L45
            return r1
        L45:
            return r6
        L46:
            java.lang.String r6 = "Columbus/CameraVis"
            java.lang.String r0 = "Could not check camera foreground status"
            android.util.Log.e(r6, r0, r5)
        L4d:
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.gates.CameraVisibility.isCameraInForeground(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object isCameraTopActivity(kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraTopActivity$1
            if (r0 == 0) goto L13
            r0 = r6
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraTopActivity$1 r0 = (com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraTopActivity$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraTopActivity$1 r0 = new com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraTopActivity$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: android.os.RemoteException -> L27
            goto L45
        L27:
            r5 = move-exception
            goto L46
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.CoroutineDispatcher r6 = r5.bgDispatcher     // Catch: android.os.RemoteException -> L27
            com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraTopActivity$2 r2 = new com.google.android.systemui.columbus.legacy.gates.CameraVisibility$isCameraTopActivity$2     // Catch: android.os.RemoteException -> L27
            r4 = 0
            r2.<init>(r5, r4)     // Catch: android.os.RemoteException -> L27
            r0.label = r3     // Catch: android.os.RemoteException -> L27
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r2, r0)     // Catch: android.os.RemoteException -> L27
            if (r6 != r1) goto L45
            return r1
        L45:
            return r6
        L46:
            java.lang.String r6 = "Columbus/CameraVis"
            java.lang.String r0 = "unable to check task stack"
            android.util.Log.e(r6, r0, r5)
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.gates.CameraVisibility.isCameraTopActivity(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onActivate() {
        KeyguardVisibility keyguardVisibility = this.keyguardGate;
        CameraVisibility$gateListener$1 cameraVisibility$gateListener$1 = this.gateListener;
        keyguardVisibility.registerListener(cameraVisibility$gateListener$1);
        this.powerState.registerListener(cameraVisibility$gateListener$1);
        try {
            ((IActivityManager) this.activityManager.get()).registerTaskStackListener(this.taskStackListener);
        } catch (RemoteException e) {
            Log.e("Columbus/CameraVis", "Could not register task stack listener", e);
        }
        BuildersKt.launch$default(this.coroutineScope, null, null, new CameraVisibility$onActivate$1(this, null), 3);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onDeactivate() {
        KeyguardVisibility keyguardVisibility = this.keyguardGate;
        CameraVisibility$gateListener$1 cameraVisibility$gateListener$1 = this.gateListener;
        keyguardVisibility.unregisterListener(cameraVisibility$gateListener$1);
        this.powerState.unregisterListener(cameraVisibility$gateListener$1);
        Iterator it = this.exceptions.iterator();
        while (it.hasNext()) {
            ((Action) it.next()).listeners.remove(this.actionListener);
        }
        try {
            ((IActivityManager) this.activityManager.get()).unregisterTaskStackListener(this.taskStackListener);
        } catch (RemoteException e) {
            Log.e("Columbus/CameraVis", "Could not unregister task stack listener", e);
        }
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final String toString() {
        return super.toString() + BuildersKt.runBlocking(this.mainDispatcher, new CameraVisibility$toString$1(this, null));
    }
}
