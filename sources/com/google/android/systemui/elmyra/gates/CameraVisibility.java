package com.google.android.systemui.elmyra.gates;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.TaskStackListener;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.util.Log;
import com.android.wm.shell.R;
import com.google.android.systemui.elmyra.actions.Action;
import com.google.android.systemui.elmyra.actions.CameraAction;
import com.google.android.systemui.elmyra.gates.Gate;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CameraVisibility extends Gate {
    public final IActivityManager mActivityManager;
    public final CameraAction mCameraAction;
    public final String mCameraPackageName;
    public boolean mCameraShowing;
    public final Set mExceptions;
    public final AnonymousClass2 mGateListener;
    public final KeyguardVisibility mKeyguardGate;
    public final PackageManager mPackageManager;
    public final PowerState mPowerState;
    public final AnonymousClass1 mTaskStackListener;
    public final Executor mUpdateExecutor;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.systemui.elmyra.gates.CameraVisibility$1] */
    public CameraVisibility(Context context, Executor executor, CameraAction cameraAction, PowerState powerState, PackageManager packageManager, KeyguardVisibility keyguardVisibility, Set set) {
        super(executor);
        this.mTaskStackListener = new TaskStackListener() { // from class: com.google.android.systemui.elmyra.gates.CameraVisibility.1
            public final void onTaskStackChanged() {
                CameraVisibility cameraVisibility = CameraVisibility.this;
                cameraVisibility.mUpdateExecutor.execute(new CameraVisibility$1$$ExternalSyntheticLambda0(cameraVisibility));
            }
        };
        Gate.Listener listener = new Gate.Listener() { // from class: com.google.android.systemui.elmyra.gates.CameraVisibility.2
            @Override // com.google.android.systemui.elmyra.gates.Gate.Listener
            public final void onGateChanged(Gate gate) {
                CameraVisibility cameraVisibility = CameraVisibility.this;
                cameraVisibility.mUpdateExecutor.execute(new CameraVisibility$1$$ExternalSyntheticLambda0(cameraVisibility));
            }
        };
        this.mCameraAction = cameraAction;
        this.mExceptions = set;
        this.mPackageManager = packageManager;
        this.mActivityManager = ActivityManager.getService();
        this.mKeyguardGate = keyguardVisibility;
        this.mPowerState = powerState;
        keyguardVisibility.mListener = listener;
        powerState.mListener = listener;
        this.mCameraPackageName = context.getResources().getString(R.string.google_camera_app_package_name);
        this.mUpdateExecutor = executor;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final boolean isBlocked() {
        Iterator it = this.mExceptions.iterator();
        while (it.hasNext()) {
            if (((Action) it.next()).isAvailable()) {
                return false;
            }
        }
        return this.mCameraShowing && !this.mCameraAction.isAvailable();
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0069, code lost:
    
        if (r7.importance != 100) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006b, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0030 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isCameraShowing() {
        /*
            r9 = this;
            java.lang.String r0 = "Elmyra/CameraVisibility"
            java.lang.String r1 = r9.mCameraPackageName
            r2 = 0
            r3 = 1
            android.app.IActivityManager r4 = android.app.ActivityManager.getService()     // Catch: android.os.RemoteException -> L27
            java.util.List r4 = r4.getTasks(r3)     // Catch: android.os.RemoteException -> L27
            boolean r5 = r4.isEmpty()     // Catch: android.os.RemoteException -> L27
            if (r5 == 0) goto L16
        L14:
            r4 = r2
            goto L2e
        L16:
            java.lang.Object r4 = r4.get(r2)     // Catch: android.os.RemoteException -> L27
            android.app.ActivityManager$RunningTaskInfo r4 = (android.app.ActivityManager.RunningTaskInfo) r4     // Catch: android.os.RemoteException -> L27
            android.content.ComponentName r4 = r4.topActivity     // Catch: android.os.RemoteException -> L27
            java.lang.String r4 = r4.getPackageName()     // Catch: android.os.RemoteException -> L27
            boolean r4 = r4.equalsIgnoreCase(r1)     // Catch: android.os.RemoteException -> L27
            goto L2e
        L27:
            r4 = move-exception
            java.lang.String r5 = "unable to check task stack"
            android.util.Log.e(r0, r5, r4)
            goto L14
        L2e:
            if (r4 == 0) goto L81
            android.app.IActivityManager r4 = r9.mActivityManager     // Catch: android.os.RemoteException -> L3d android.content.pm.PackageManager.NameNotFoundException -> L75
            android.content.pm.UserInfo r4 = r4.getCurrentUser()     // Catch: android.os.RemoteException -> L3d android.content.pm.PackageManager.NameNotFoundException -> L75
            android.content.pm.PackageManager r5 = r9.mPackageManager     // Catch: android.os.RemoteException -> L3d android.content.pm.PackageManager.NameNotFoundException -> L75
            if (r4 == 0) goto L3f
            int r4 = r4.id     // Catch: android.os.RemoteException -> L3d android.content.pm.PackageManager.NameNotFoundException -> L75
            goto L40
        L3d:
            r1 = move-exception
            goto L70
        L3f:
            r4 = r2
        L40:
            android.content.pm.ApplicationInfo r4 = r5.getApplicationInfoAsUser(r1, r2, r4)     // Catch: android.os.RemoteException -> L3d android.content.pm.PackageManager.NameNotFoundException -> L75
            int r4 = r4.uid     // Catch: android.os.RemoteException -> L3d android.content.pm.PackageManager.NameNotFoundException -> L75
            android.app.IActivityManager r5 = r9.mActivityManager     // Catch: android.os.RemoteException -> L3d android.content.pm.PackageManager.NameNotFoundException -> L75
            java.util.List r5 = r5.getRunningAppProcesses()     // Catch: android.os.RemoteException -> L3d android.content.pm.PackageManager.NameNotFoundException -> L75
            r6 = r2
        L4d:
            int r7 = r5.size()     // Catch: android.os.RemoteException -> L3d android.content.pm.PackageManager.NameNotFoundException -> L75
            if (r6 >= r7) goto L75
            java.lang.Object r7 = r5.get(r6)     // Catch: android.os.RemoteException -> L3d android.content.pm.PackageManager.NameNotFoundException -> L75
            android.app.ActivityManager$RunningAppProcessInfo r7 = (android.app.ActivityManager.RunningAppProcessInfo) r7     // Catch: android.os.RemoteException -> L3d android.content.pm.PackageManager.NameNotFoundException -> L75
            int r8 = r7.uid     // Catch: android.os.RemoteException -> L3d android.content.pm.PackageManager.NameNotFoundException -> L75
            if (r8 != r4) goto L6d
            java.lang.String r8 = r7.processName     // Catch: android.os.RemoteException -> L3d android.content.pm.PackageManager.NameNotFoundException -> L75
            boolean r8 = r8.equalsIgnoreCase(r1)     // Catch: android.os.RemoteException -> L3d android.content.pm.PackageManager.NameNotFoundException -> L75
            if (r8 == 0) goto L6d
            int r0 = r7.importance     // Catch: android.os.RemoteException -> L3d android.content.pm.PackageManager.NameNotFoundException -> L75
            r1 = 100
            if (r0 != r1) goto L75
            r0 = r3
            goto L76
        L6d:
            int r6 = r6 + 1
            goto L4d
        L70:
            java.lang.String r4 = "Could not check camera foreground status"
            android.util.Log.e(r0, r4, r1)
        L75:
            r0 = r2
        L76:
            if (r0 == 0) goto L81
            com.google.android.systemui.elmyra.gates.PowerState r9 = r9.mPowerState
            boolean r9 = r9.isBlocking()
            if (r9 != 0) goto L81
            r2 = r3
        L81:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.elmyra.gates.CameraVisibility.isCameraShowing():boolean");
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onActivate() {
        this.mKeyguardGate.activate();
        this.mPowerState.activate();
        this.mCameraShowing = isCameraShowing();
        try {
            this.mActivityManager.registerTaskStackListener(this.mTaskStackListener);
        } catch (RemoteException e) {
            Log.e("Elmyra/CameraVisibility", "Could not register task stack listener", e);
        }
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onDeactivate() {
        this.mKeyguardGate.deactivate();
        this.mPowerState.deactivate();
        try {
            this.mActivityManager.unregisterTaskStackListener(this.mTaskStackListener);
        } catch (RemoteException e) {
            Log.e("Elmyra/CameraVisibility", "Could not unregister task stack listener", e);
        }
    }
}
