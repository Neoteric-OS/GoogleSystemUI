package com.android.systemui.camera;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.IActivityTaskManager;
import android.app.IApplicationThread;
import android.app.ProfilerInfo;
import android.app.admin.DevicePolicyManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Trace;
import android.util.Log;
import com.android.systemui.ActivityIntentHelper;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CameraGestureHelper {
    public final ActivityIntentHelper activityIntentHelper;
    public final ActivityManager activityManager;
    public final ActivityStarter activityStarter;
    public final IActivityTaskManager activityTaskManager;
    public final CameraIntentsWrapper cameraIntents;
    public final ContentResolver contentResolver;
    public final Context context;
    public final DevicePolicyManager devicePolicyManager;
    public final KeyguardStateController keyguardStateController;
    public final NotificationLockscreenUserManager lockscreenUserManager;
    public final PackageManager packageManager;
    public final SelectedUserInteractor selectedUserInteractor;
    public final StatusBarKeyguardViewManager statusBarKeyguardViewManager;
    public final Executor uiExecutor;

    public CameraGestureHelper(Context context, KeyguardStateController keyguardStateController, StatusBarKeyguardViewManager statusBarKeyguardViewManager, PackageManager packageManager, ActivityManager activityManager, ActivityStarter activityStarter, ActivityIntentHelper activityIntentHelper, IActivityTaskManager iActivityTaskManager, CameraIntentsWrapper cameraIntentsWrapper, ContentResolver contentResolver, Executor executor, SelectedUserInteractor selectedUserInteractor, DevicePolicyManager devicePolicyManager, NotificationLockscreenUserManager notificationLockscreenUserManager) {
        this.context = context;
        this.keyguardStateController = keyguardStateController;
        this.statusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.packageManager = packageManager;
        this.activityManager = activityManager;
        this.activityStarter = activityStarter;
        this.activityIntentHelper = activityIntentHelper;
        this.activityTaskManager = iActivityTaskManager;
        this.cameraIntents = cameraIntentsWrapper;
        this.contentResolver = contentResolver;
        this.uiExecutor = executor;
        this.selectedUserInteractor = selectedUserInteractor;
        this.devicePolicyManager = devicePolicyManager;
        this.lockscreenUserManager = notificationLockscreenUserManager;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0035, code lost:
    
        if ((r6.devicePolicyManager.getKeyguardDisabledFeatures(null, r1.mCurrentUserId) & 2) != 0) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean canCameraGestureBeLaunched(int r7) {
        /*
            r6 = this;
            android.app.admin.DevicePolicyManager r0 = r6.devicePolicyManager
            com.android.systemui.statusbar.NotificationLockscreenUserManager r1 = r6.lockscreenUserManager
            com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl r1 = (com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl) r1
            int r2 = r1.mCurrentUserId
            r3 = 0
            boolean r0 = r0.getCameraDisabled(r3, r2)
            r2 = 0
            if (r0 == 0) goto L11
            goto L38
        L11:
            com.android.systemui.statusbar.policy.KeyguardStateController r0 = r6.keyguardStateController
            com.android.systemui.statusbar.policy.KeyguardStateControllerImpl r0 = (com.android.systemui.statusbar.policy.KeyguardStateControllerImpl) r0
            boolean r0 = r0.mShowing
            if (r0 == 0) goto L39
            com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager r0 = r6.statusBarKeyguardViewManager
            com.android.systemui.user.domain.interactor.SelectedUserInteractor r4 = r0.mSelectedUserInteractor
            int r4 = r4.getSelectedUserId()
            com.android.keyguard.KeyguardSecurityModel r0 = r0.mKeyguardSecurityModel
            com.android.keyguard.KeyguardSecurityModel$SecurityMode r0 = r0.getSecurityMode(r4)
            com.android.keyguard.KeyguardSecurityModel$SecurityMode r4 = com.android.keyguard.KeyguardSecurityModel.SecurityMode.None
            if (r0 == r4) goto L39
            android.app.admin.DevicePolicyManager r0 = r6.devicePolicyManager
            int r1 = r1.mCurrentUserId
            int r0 = r0.getKeyguardDisabledFeatures(r3, r1)
            r0 = r0 & 2
            if (r0 != 0) goto L38
            goto L39
        L38:
            return r2
        L39:
            android.content.pm.PackageManager r0 = r6.packageManager
            com.android.systemui.user.domain.interactor.SelectedUserInteractor r1 = r6.selectedUserInteractor
            int r4 = r1.getSelectedUserId()
            android.content.Intent r4 = r6.getStartCameraIntent(r4)
            r5 = 65536(0x10000, float:9.1835E-41)
            int r1 = r1.getSelectedUserId()
            android.content.pm.ResolveInfo r0 = r0.resolveActivityAsUser(r4, r5, r1)
            if (r0 == 0) goto L58
            android.content.pm.ActivityInfo r0 = r0.activityInfo
            if (r0 == 0) goto L58
            java.lang.String r0 = r0.packageName
            goto L59
        L58:
            r0 = r3
        L59:
            if (r0 == 0) goto L80
            r1 = 1
            if (r7 != 0) goto L7f
            android.app.ActivityManager r6 = r6.activityManager
            java.util.List r6 = r6.getRunningTasks(r1)
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L7f
            java.lang.Object r6 = r6.get(r2)
            android.app.ActivityManager$RunningTaskInfo r6 = (android.app.ActivityManager.RunningTaskInfo) r6
            android.content.ComponentName r6 = r6.topActivity
            if (r6 == 0) goto L78
            java.lang.String r3 = r6.getPackageName()
        L78:
            boolean r6 = r0.equals(r3)
            if (r6 == 0) goto L7f
            goto L80
        L7f:
            r2 = r1
        L80:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.camera.CameraGestureHelper.canCameraGestureBeLaunched(int):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x004f, code lost:
    
        if (r3.getPackageManager().getApplicationInfoAsUser(r1, 0, r4).enabled != false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.content.Intent getStartCameraIntent(int r4) {
        /*
            r3 = this;
            com.android.systemui.statusbar.policy.KeyguardStateController r0 = r3.keyguardStateController
            com.android.systemui.statusbar.policy.KeyguardStateControllerImpl r0 = (com.android.systemui.statusbar.policy.KeyguardStateControllerImpl) r0
            boolean r1 = r0.mCanDismissLockScreen
            boolean r0 = r0.mSecure
            com.android.systemui.camera.CameraIntentsWrapper r3 = r3.cameraIntents
            if (r0 == 0) goto L27
            if (r1 != 0) goto L27
            android.content.Context r3 = r3.context
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "android.media.action.STILL_IMAGE_CAMERA_SECURE"
            r0.<init>(r1)
            java.lang.String r3 = com.android.systemui.camera.CameraIntents$Companion.getOverrideCameraPackage(r4, r3)
            if (r3 == 0) goto L20
            r0.setPackage(r3)
        L20:
            r3 = 8388608(0x800000, float:1.1754944E-38)
            android.content.Intent r3 = r0.addFlags(r3)
            goto L65
        L27:
            android.content.Context r3 = r3.context
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "android.media.action.STILL_IMAGE_CAMERA"
            r0.<init>(r1)
            android.content.res.Resources r1 = r3.getResources()
            r2 = 2131952333(0x7f1302cd, float:1.9541106E38)
            java.lang.String r1 = r1.getString(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L52
            if (r2 != 0) goto L5e
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L52
            r2 = 0
            android.content.pm.ApplicationInfo r3 = r3.getApplicationInfoAsUser(r1, r2, r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L52
            boolean r3 = r3.enabled     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L52
            if (r3 == 0) goto L5e
            goto L5f
        L52:
            r3 = move-exception
            java.lang.String r4 = "Missing cameraGesturePackage "
            java.lang.String r4 = r4.concat(r1)
            java.lang.String r1 = "CameraIntents"
            android.util.Log.w(r1, r4, r3)
        L5e:
            r1 = 0
        L5f:
            if (r1 == 0) goto L64
            r0.setPackage(r1)
        L64:
            r3 = r0
        L65:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.camera.CameraGestureHelper.getStartCameraIntent(int):android.content.Intent");
    }

    public final void launchCamera(int i) {
        SelectedUserInteractor selectedUserInteractor = this.selectedUserInteractor;
        final Intent startCameraIntent = getStartCameraIntent(selectedUserInteractor.getSelectedUserId());
        startCameraIntent.putExtra("com.android.systemui.camera_launch_source", i);
        boolean z = this.activityIntentHelper.getTargetActivityInfo(startCameraIntent, selectedUserInteractor.getSelectedUserId(), false) == null;
        String action = startCameraIntent.getAction();
        if (!(action != null ? action.equals("android.media.action.STILL_IMAGE_CAMERA_SECURE") : false) || z) {
            this.activityStarter.startActivity(startCameraIntent, false);
        } else {
            this.uiExecutor.execute(new Runnable() { // from class: com.android.systemui.camera.CameraGestureHelper$launchCamera$1
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityOptions makeBasic = ActivityOptions.makeBasic();
                    makeBasic.setDisallowEnterPictureInPictureWhileLaunching(true);
                    makeBasic.setRotationAnimationHint(3);
                    try {
                        CameraGestureHelper cameraGestureHelper = CameraGestureHelper.this;
                        IActivityTaskManager iActivityTaskManager = cameraGestureHelper.activityTaskManager;
                        String basePackageName = cameraGestureHelper.context.getBasePackageName();
                        String attributionTag = CameraGestureHelper.this.context.getAttributionTag();
                        Intent intent = startCameraIntent;
                        iActivityTaskManager.startActivityAsUser((IApplicationThread) null, basePackageName, attributionTag, intent, intent.resolveTypeIfNeeded(CameraGestureHelper.this.contentResolver), (IBinder) null, (String) null, 0, 268435456, (ProfilerInfo) null, makeBasic.toBundle(), CameraGestureHelper.this.selectedUserInteractor.getSelectedUserId());
                    } catch (RemoteException e) {
                        Log.w("CameraGestureHelper", "Unable to start camera activity", e);
                    }
                }
            });
        }
        KeyguardViewMediator.AnonymousClass4 anonymousClass4 = this.statusBarKeyguardViewManager.mViewMediatorCallback;
        anonymousClass4.getClass();
        Trace.beginSection("KeyguardViewMediator.mViewMediatorCallback#readyForKeyguardDone");
        KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
        if (keyguardViewMediator.mKeyguardDonePending) {
            keyguardViewMediator.mKeyguardDonePending = false;
            keyguardViewMediator.tryKeyguardDone();
        }
        Trace.endSection();
    }
}
