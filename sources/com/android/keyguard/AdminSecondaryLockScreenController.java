package com.android.keyguard;

import android.app.admin.IKeyguardCallback;
import android.app.admin.IKeyguardClient;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.SurfaceControlViewHost;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.android.keyguard.AdminSecondaryLockScreenController;
import com.android.keyguard.KeyguardSecurityContainerController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import java.util.NoSuchElementException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AdminSecondaryLockScreenController {
    public IKeyguardClient mClient;
    public final Context mContext;
    public final Handler mHandler;
    public final KeyguardSecurityContainerController.AnonymousClass3 mKeyguardCallback;
    public final KeyguardSecurityContainer mParent;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public final KeyguardUpdateMonitor mUpdateMonitor;
    public final AdminSecurityView mView;
    public final AnonymousClass1 mConnection = new ServiceConnection() { // from class: com.android.keyguard.AdminSecondaryLockScreenController.1
        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            AdminSecondaryLockScreenController.this.mClient = IKeyguardClient.Stub.asInterface(iBinder);
            if (AdminSecondaryLockScreenController.this.mView.isAttachedToWindow()) {
                AdminSecondaryLockScreenController adminSecondaryLockScreenController = AdminSecondaryLockScreenController.this;
                if (adminSecondaryLockScreenController.mClient != null) {
                    AdminSecondaryLockScreenController.m748$$Nest$monSurfaceReady(adminSecondaryLockScreenController);
                    try {
                        iBinder.linkToDeath(AdminSecondaryLockScreenController.this.mKeyguardClientDeathRecipient, 0);
                    } catch (RemoteException e) {
                        Log.e("AdminSecondaryLockScreenController", "Lost connection to secondary lockscreen service", e);
                        AdminSecondaryLockScreenController adminSecondaryLockScreenController2 = AdminSecondaryLockScreenController.this;
                        adminSecondaryLockScreenController2.dismiss(adminSecondaryLockScreenController2.mSelectedUserInteractor.getSelectedUserId());
                    }
                }
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            AdminSecondaryLockScreenController.this.mClient = null;
        }
    };
    public final AdminSecondaryLockScreenController$$ExternalSyntheticLambda0 mKeyguardClientDeathRecipient = new IBinder.DeathRecipient() { // from class: com.android.keyguard.AdminSecondaryLockScreenController$$ExternalSyntheticLambda0
        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            AdminSecondaryLockScreenController.this.hide();
            Log.d("AdminSecondaryLockScreenController", "KeyguardClient service died");
        }
    };
    public final AnonymousClass2 mCallback = new AnonymousClass2();
    public final KeyguardUpdateMonitorCallback mUpdateCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.keyguard.AdminSecondaryLockScreenController.3
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onSecondaryLockscreenRequirementChanged(int i) {
            AdminSecondaryLockScreenController adminSecondaryLockScreenController = AdminSecondaryLockScreenController.this;
            if (((Intent) adminSecondaryLockScreenController.mUpdateMonitor.mSecondaryLockscreenRequirement.get(Integer.valueOf(i))) == null) {
                adminSecondaryLockScreenController.dismiss(i);
            }
        }
    };
    protected SurfaceHolder.Callback mSurfaceHolderCallback = new AnonymousClass4();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.AdminSecondaryLockScreenController$2, reason: invalid class name */
    public final class AnonymousClass2 extends IKeyguardCallback.Stub {
        public AnonymousClass2() {
        }

        public final void onDismiss() {
            AdminSecondaryLockScreenController.this.mHandler.post(new AdminSecondaryLockScreenController$2$$ExternalSyntheticLambda0(this, 0));
        }

        public final void onRemoteContentReady(SurfaceControlViewHost.SurfacePackage surfacePackage) {
            Handler handler = AdminSecondaryLockScreenController.this.mHandler;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            if (surfacePackage != null) {
                AdminSecondaryLockScreenController.this.mView.setChildSurfacePackage(surfacePackage);
            } else {
                AdminSecondaryLockScreenController.this.mHandler.post(new AdminSecondaryLockScreenController$2$$ExternalSyntheticLambda0(this, 1));
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AdminSecurityView extends SurfaceView {
        public SurfaceHolder.Callback mSurfaceHolderCallback;

        @Override // android.view.SurfaceView, android.view.View
        public final void onAttachedToWindow() {
            super.onAttachedToWindow();
            getHolder().addCallback(this.mSurfaceHolderCallback);
        }

        @Override // android.view.SurfaceView, android.view.View
        public final void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            getHolder().removeCallback(this.mSurfaceHolderCallback);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final Context mContext;
        public final Handler mHandler;
        public final KeyguardSecurityContainer mParent;
        public final SelectedUserInteractor mSelectedUserInteractor;
        public final KeyguardUpdateMonitor mUpdateMonitor;

        public Factory(Context context, KeyguardSecurityContainer keyguardSecurityContainer, KeyguardUpdateMonitor keyguardUpdateMonitor, Handler handler, SelectedUserInteractor selectedUserInteractor) {
            this.mContext = context;
            this.mParent = keyguardSecurityContainer;
            this.mUpdateMonitor = keyguardUpdateMonitor;
            this.mHandler = handler;
            this.mSelectedUserInteractor = selectedUserInteractor;
        }
    }

    /* renamed from: -$$Nest$monSurfaceReady, reason: not valid java name */
    public static void m748$$Nest$monSurfaceReady(AdminSecondaryLockScreenController adminSecondaryLockScreenController) {
        adminSecondaryLockScreenController.getClass();
        try {
            IBinder hostToken = adminSecondaryLockScreenController.mView.getHostToken();
            if (hostToken != null) {
                adminSecondaryLockScreenController.mClient.onCreateKeyguardSurface(hostToken, adminSecondaryLockScreenController.mCallback);
            } else {
                adminSecondaryLockScreenController.hide();
            }
        } catch (RemoteException e) {
            Log.e("AdminSecondaryLockScreenController", "Error in onCreateKeyguardSurface", e);
            adminSecondaryLockScreenController.dismiss(adminSecondaryLockScreenController.mSelectedUserInteractor.getSelectedUserId());
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.keyguard.AdminSecondaryLockScreenController$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.keyguard.AdminSecondaryLockScreenController$$ExternalSyntheticLambda0] */
    public AdminSecondaryLockScreenController(Context context, KeyguardSecurityContainer keyguardSecurityContainer, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardSecurityContainerController.AnonymousClass3 anonymousClass3, Handler handler, SelectedUserInteractor selectedUserInteractor) {
        this.mContext = context;
        this.mHandler = handler;
        this.mParent = keyguardSecurityContainer;
        this.mUpdateMonitor = keyguardUpdateMonitor;
        this.mKeyguardCallback = anonymousClass3;
        SurfaceHolder.Callback callback = this.mSurfaceHolderCallback;
        AdminSecurityView adminSecurityView = new AdminSecurityView(context);
        adminSecurityView.mSurfaceHolderCallback = callback;
        adminSecurityView.setZOrderOnTop(true);
        this.mView = adminSecurityView;
        adminSecurityView.setId(View.generateViewId());
        this.mSelectedUserInteractor = selectedUserInteractor;
    }

    public final void dismiss(int i) {
        this.mHandler.removeCallbacksAndMessages(null);
        if (this.mView.isAttachedToWindow() && i == this.mSelectedUserInteractor.getSelectedUserId()) {
            hide();
            KeyguardSecurityContainerController.AnonymousClass3 anonymousClass3 = this.mKeyguardCallback;
            if (anonymousClass3 != null) {
                anonymousClass3.dismiss(true, i, true, KeyguardSecurityModel.SecurityMode.Invalid);
            }
        }
    }

    public final void hide() {
        AdminSecurityView adminSecurityView = this.mView;
        if (adminSecurityView.isAttachedToWindow()) {
            this.mParent.removeView(adminSecurityView);
        }
        IKeyguardClient iKeyguardClient = this.mClient;
        if (iKeyguardClient != null) {
            try {
                iKeyguardClient.asBinder().unlinkToDeath(this.mKeyguardClientDeathRecipient, 0);
            } catch (NoSuchElementException unused) {
                Log.w("AdminSecondaryLockScreenController", "IKeyguardClient death recipient already released");
            }
            this.mContext.unbindService(this.mConnection);
            this.mClient = null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.AdminSecondaryLockScreenController$4, reason: invalid class name */
    public final class AnonymousClass4 implements SurfaceHolder.Callback {
        public AnonymousClass4() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public final void surfaceCreated(SurfaceHolder surfaceHolder) {
            final int selectedUserId = AdminSecondaryLockScreenController.this.mSelectedUserInteractor.getSelectedUserId();
            AdminSecondaryLockScreenController adminSecondaryLockScreenController = AdminSecondaryLockScreenController.this;
            adminSecondaryLockScreenController.mUpdateMonitor.registerCallback(adminSecondaryLockScreenController.mUpdateCallback);
            AdminSecondaryLockScreenController adminSecondaryLockScreenController2 = AdminSecondaryLockScreenController.this;
            if (adminSecondaryLockScreenController2.mClient != null) {
                AdminSecondaryLockScreenController.m748$$Nest$monSurfaceReady(adminSecondaryLockScreenController2);
            }
            AdminSecondaryLockScreenController.this.mHandler.postDelayed(new Runnable() { // from class: com.android.keyguard.AdminSecondaryLockScreenController$4$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AdminSecondaryLockScreenController.AnonymousClass4 anonymousClass4 = AdminSecondaryLockScreenController.AnonymousClass4.this;
                    AdminSecondaryLockScreenController.this.dismiss(selectedUserId);
                    Log.w("AdminSecondaryLockScreenController", "Timed out waiting for secondary lockscreen content.");
                }
            }, 500L);
        }

        @Override // android.view.SurfaceHolder.Callback
        public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            AdminSecondaryLockScreenController adminSecondaryLockScreenController = AdminSecondaryLockScreenController.this;
            adminSecondaryLockScreenController.mUpdateMonitor.removeCallback(adminSecondaryLockScreenController.mUpdateCallback);
        }

        @Override // android.view.SurfaceHolder.Callback
        public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }
    }
}
