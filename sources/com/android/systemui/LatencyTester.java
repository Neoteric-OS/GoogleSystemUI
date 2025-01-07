package com.android.systemui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.biometrics.BiometricSourceType;
import android.os.Build;
import android.provider.DeviceConfig;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.concurrency.DelayableExecutor;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LatencyTester implements CoreStartable {
    public static final boolean DEFAULT_ENABLED = Build.IS_ENG;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final AnonymousClass1 mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.LatencyTester.1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.android.systemui.latency.ACTION_FINGERPRINT_WAKE".equals(action)) {
                LatencyTester.m776$$Nest$mfakeWakeAndUnlock(LatencyTester.this, BiometricSourceType.FINGERPRINT);
            } else if ("com.android.systemui.latency.ACTION_FACE_WAKE".equals(action)) {
                LatencyTester.m776$$Nest$mfakeWakeAndUnlock(LatencyTester.this, BiometricSourceType.FACE);
            }
        }
    };
    public final DeviceConfigProxy mDeviceConfigProxy;
    public boolean mEnabled;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final SelectedUserInteractor mSelectedUserInteractor;

    /* renamed from: -$$Nest$mfakeWakeAndUnlock, reason: not valid java name */
    public static void m776$$Nest$mfakeWakeAndUnlock(LatencyTester latencyTester, BiometricSourceType biometricSourceType) {
        if (latencyTester.mEnabled) {
            BiometricSourceType biometricSourceType2 = BiometricSourceType.FACE;
            SelectedUserInteractor selectedUserInteractor = latencyTester.mSelectedUserInteractor;
            KeyguardUpdateMonitor keyguardUpdateMonitor = latencyTester.mKeyguardUpdateMonitor;
            if (biometricSourceType == biometricSourceType2) {
                keyguardUpdateMonitor.onFaceAuthenticated(selectedUserInteractor.getSelectedUserId(), true);
            } else if (biometricSourceType == BiometricSourceType.FINGERPRINT) {
                keyguardUpdateMonitor.onFingerprintAuthenticated(selectedUserInteractor.getSelectedUserId(), true);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.LatencyTester$1] */
    public LatencyTester(BroadcastDispatcher broadcastDispatcher, DeviceConfigProxy deviceConfigProxy, DelayableExecutor delayableExecutor, KeyguardUpdateMonitor keyguardUpdateMonitor, SelectedUserInteractor selectedUserInteractor) {
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mDeviceConfigProxy = deviceConfigProxy;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mSelectedUserInteractor = selectedUserInteractor;
        updateEnabled();
        DeviceConfig.OnPropertiesChangedListener onPropertiesChangedListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.systemui.LatencyTester$$ExternalSyntheticLambda0
            public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                LatencyTester.this.updateEnabled();
            }
        };
        deviceConfigProxy.getClass();
        DeviceConfig.addOnPropertiesChangedListener("latency_tracker", delayableExecutor, onPropertiesChangedListener);
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("mEnabled="), this.mEnabled, printWriter);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateEnabled() {
        /*
            r4 = this;
            boolean r0 = r4.mEnabled
            boolean r1 = android.os.Build.IS_DEBUGGABLE
            if (r1 == 0) goto L19
            com.android.systemui.util.DeviceConfigProxy r1 = r4.mDeviceConfigProxy
            r1.getClass()
            java.lang.String r1 = "enabled"
            boolean r2 = com.android.systemui.LatencyTester.DEFAULT_ENABLED
            java.lang.String r3 = "latency_tracker"
            boolean r1 = android.provider.DeviceConfig.getBoolean(r3, r1, r2)
            if (r1 == 0) goto L19
            r1 = 1
            goto L1a
        L19:
            r1 = 0
        L1a:
            r4.mEnabled = r1
            if (r1 == r0) goto L3c
            com.android.systemui.broadcast.BroadcastDispatcher r0 = r4.mBroadcastDispatcher
            if (r1 == 0) goto L37
            android.content.IntentFilter r1 = new android.content.IntentFilter
            r1.<init>()
            java.lang.String r2 = "com.android.systemui.latency.ACTION_FINGERPRINT_WAKE"
            r1.addAction(r2)
            java.lang.String r2 = "com.android.systemui.latency.ACTION_FACE_WAKE"
            r1.addAction(r2)
            com.android.systemui.LatencyTester$1 r4 = r4.mBroadcastReceiver
            r0.registerReceiver(r4, r1)
            goto L3c
        L37:
            com.android.systemui.LatencyTester$1 r4 = r4.mBroadcastReceiver
            r0.unregisterReceiver(r4)
        L3c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.LatencyTester.updateEnabled():void");
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
    }
}
