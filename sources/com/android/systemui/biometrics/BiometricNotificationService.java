package com.android.systemui.biometrics;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.biometrics.BiometricSourceType;
import android.hardware.biometrics.BiometricStateListener;
import android.hardware.face.FaceManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.CoreStartable;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.wm.shell.R;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricNotificationService implements CoreStartable {
    public final BiometricNotificationBroadcastReceiver mBroadcastReceiver;
    public final Context mContext;
    public final FaceManager mFaceManager;
    public boolean mFaceNotificationQueued;
    public final AnonymousClass3 mFaceStateListener;
    public final FingerprintManager mFingerprintManager;
    public boolean mFingerprintNotificationQueued;
    public final FingerprintReEnrollNotification mFingerprintReEnrollNotification;
    public boolean mFingerprintReenrollRequired;
    public final AnonymousClass3 mFingerprintStateListener;
    public final Handler mHandler;
    public boolean mIsFingerprintReenrollForced;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public NotificationChannel mNotificationChannel;
    public final NotificationManager mNotificationManager;
    public final AnonymousClass1 mKeyguardStateControllerCallback = new KeyguardStateController.Callback() { // from class: com.android.systemui.biometrics.BiometricNotificationService.1
        public boolean mIsShowing = true;

        @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
        public final void onKeyguardShowingChanged() {
            final BiometricNotificationService biometricNotificationService = BiometricNotificationService.this;
            boolean z = ((KeyguardStateControllerImpl) biometricNotificationService.mKeyguardStateController).mShowing;
            if (z || z == this.mIsShowing) {
                this.mIsShowing = z;
                return;
            }
            this.mIsShowing = z;
            int intForUser = Settings.Secure.getIntForUser(biometricNotificationService.mContext.getContentResolver(), "face_unlock_re_enroll", 0, -2);
            Handler handler = biometricNotificationService.mHandler;
            if (intForUser == 1 && !biometricNotificationService.mFaceNotificationQueued) {
                Log.d("BiometricNotificationService", "Face re-enroll notification queued.");
                biometricNotificationService.mFaceNotificationQueued = true;
                final String string = biometricNotificationService.mContext.getString(R.string.face_re_enroll_notification_title);
                final String string2 = biometricNotificationService.mContext.getString(R.string.biometric_re_enroll_notification_content);
                final String string3 = biometricNotificationService.mContext.getString(R.string.face_re_enroll_notification_name);
                final int i = 0;
                handler.postDelayed(new Runnable() { // from class: com.android.systemui.biometrics.BiometricNotificationService$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i) {
                            case 0:
                                biometricNotificationService.showNotification("face_action_show_reenroll_dialog", string, string2, string3, 1, false);
                                break;
                            default:
                                BiometricNotificationService biometricNotificationService2 = biometricNotificationService;
                                biometricNotificationService2.showNotification("fingerprint_action_show_reenroll_dialog", string, string2, string3, 2, biometricNotificationService2.mIsFingerprintReenrollForced);
                                break;
                        }
                    }
                }, 5000L);
            }
            if (!biometricNotificationService.mFingerprintReenrollRequired || biometricNotificationService.mFingerprintNotificationQueued) {
                return;
            }
            biometricNotificationService.mFingerprintReenrollRequired = false;
            Log.d("BiometricNotificationService", "Fingerprint re-enroll notification queued.");
            biometricNotificationService.mFingerprintNotificationQueued = true;
            final String string4 = biometricNotificationService.mContext.getString(R.string.fingerprint_re_enroll_notification_title);
            final String string5 = biometricNotificationService.mContext.getString(R.string.biometric_re_enroll_notification_content);
            final String string6 = biometricNotificationService.mContext.getString(R.string.fingerprint_re_enroll_notification_name);
            final int i2 = 1;
            handler.postDelayed(new Runnable() { // from class: com.android.systemui.biometrics.BiometricNotificationService$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i2) {
                        case 0:
                            biometricNotificationService.showNotification("face_action_show_reenroll_dialog", string4, string5, string6, 1, false);
                            break;
                        default:
                            BiometricNotificationService biometricNotificationService2 = biometricNotificationService;
                            biometricNotificationService2.showNotification("fingerprint_action_show_reenroll_dialog", string4, string5, string6, 2, biometricNotificationService2.mIsFingerprintReenrollForced);
                            break;
                    }
                }
            }, 5000L);
        }
    };
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.biometrics.BiometricNotificationService.2
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onBiometricError(int i, String str, BiometricSourceType biometricSourceType) {
            if (i == 16 && biometricSourceType == BiometricSourceType.FACE) {
                Settings.Secure.putIntForUser(BiometricNotificationService.this.mContext.getContentResolver(), "face_unlock_re_enroll", 1, -2);
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onBiometricHelp(int i, String str, BiometricSourceType biometricSourceType) {
            if (biometricSourceType == BiometricSourceType.FINGERPRINT) {
                BiometricNotificationService biometricNotificationService = BiometricNotificationService.this;
                if (biometricNotificationService.mFingerprintReEnrollNotification.isFingerprintReEnrollRequested(i)) {
                    biometricNotificationService.mFingerprintReenrollRequired = true;
                    biometricNotificationService.mIsFingerprintReenrollForced = biometricNotificationService.mFingerprintReEnrollNotification.isFingerprintReEnrollForced(i);
                }
            }
        }
    };

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.biometrics.BiometricNotificationService$1] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.biometrics.BiometricNotificationService$3] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.systemui.biometrics.BiometricNotificationService$3] */
    public BiometricNotificationService(Context context, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardStateController keyguardStateController, Handler handler, NotificationManager notificationManager, BiometricNotificationBroadcastReceiver biometricNotificationBroadcastReceiver, Optional optional, FingerprintManager fingerprintManager, FaceManager faceManager) {
        final int i = 0;
        this.mFaceStateListener = new BiometricStateListener(this) { // from class: com.android.systemui.biometrics.BiometricNotificationService.3
            public final /* synthetic */ BiometricNotificationService this$0;

            {
                this.this$0 = this;
            }

            public final void onEnrollmentsChanged(int i2, int i3, boolean z) {
                switch (i) {
                    case 0:
                        this.this$0.mNotificationManager.cancelAsUser("BiometricNotificationService", 1, UserHandle.CURRENT);
                        break;
                    default:
                        this.this$0.mNotificationManager.cancelAsUser("BiometricNotificationService", 2, UserHandle.CURRENT);
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mFingerprintStateListener = new BiometricStateListener(this) { // from class: com.android.systemui.biometrics.BiometricNotificationService.3
            public final /* synthetic */ BiometricNotificationService this$0;

            {
                this.this$0 = this;
            }

            public final void onEnrollmentsChanged(int i22, int i3, boolean z) {
                switch (i2) {
                    case 0:
                        this.this$0.mNotificationManager.cancelAsUser("BiometricNotificationService", 1, UserHandle.CURRENT);
                        break;
                    default:
                        this.this$0.mNotificationManager.cancelAsUser("BiometricNotificationService", 2, UserHandle.CURRENT);
                        break;
                }
            }
        };
        this.mContext = context;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mKeyguardStateController = keyguardStateController;
        this.mHandler = handler;
        this.mNotificationManager = notificationManager;
        this.mBroadcastReceiver = biometricNotificationBroadcastReceiver;
        this.mFingerprintReEnrollNotification = (FingerprintReEnrollNotification) optional.orElse(new FingerprintReEnrollNotificationImpl());
        this.mFingerprintManager = fingerprintManager;
        this.mFaceManager = faceManager;
    }

    public final void showNotification(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, boolean z) {
        if (i == 1) {
            this.mFaceNotificationQueued = false;
        } else if (i == 2) {
            this.mFingerprintNotificationQueued = false;
        }
        if (this.mNotificationManager == null) {
            Log.e("BiometricNotificationService", "Failed to show notification " + str + ". Notification manager is null!");
            return;
        }
        Intent intent = new Intent(str);
        intent.putExtra("is_reenroll_forced", z);
        Context context = this.mContext;
        UserHandle userHandle = UserHandle.CURRENT;
        Notification build = new Notification.Builder(this.mContext, "BiometricHiPriNotificationChannel").setCategory("sys").setSmallIcon(android.R.drawable.ic_jog_dial_answer_and_hold).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence3).setContentIntent(PendingIntent.getBroadcastAsUser(context, 0, intent, 201326592, userHandle)).setAutoCancel(true).setLocalOnly(true).setOnlyAlertOnce(true).setVisibility(-1).build();
        this.mNotificationManager.createNotificationChannel(this.mNotificationChannel);
        this.mNotificationManager.notifyAsUser("BiometricNotificationService", i, build, userHandle);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mKeyguardUpdateMonitor.registerCallback(this.mKeyguardUpdateMonitorCallback);
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).addCallback(this.mKeyguardStateControllerCallback);
        this.mNotificationChannel = new NotificationChannel("BiometricHiPriNotificationChannel", " Biometric Unlock", 4);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("fingerprint_action_show_reenroll_dialog");
        intentFilter.addAction("face_action_show_reenroll_dialog");
        this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter, 2);
        FingerprintManager fingerprintManager = this.mFingerprintManager;
        if (fingerprintManager != null) {
            fingerprintManager.registerBiometricStateListener(this.mFingerprintStateListener);
        }
        FaceManager faceManager = this.mFaceManager;
        if (faceManager != null) {
            faceManager.registerBiometricStateListener(this.mFaceStateListener);
        }
        Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "face_unlock_re_enroll", 0, -2);
    }
}
