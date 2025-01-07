package com.android.systemui.doze;

import android.app.IWallpaperManager;
import android.util.Log;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import com.android.systemui.statusbar.phone.DozeParameters;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DozeWallpaperState implements DozeMachine.Part {
    public static final boolean DEBUG = Log.isLoggable("DozeWallpaperState", 3);
    public final BiometricUnlockController mBiometricUnlockController;
    public final DozeParameters mDozeParameters;
    public boolean mIsAmbientMode;
    public final IWallpaperManager mWallpaperManagerService;

    public DozeWallpaperState(IWallpaperManager iWallpaperManager, BiometricUnlockController biometricUnlockController, DozeParameters dozeParameters) {
        this.mWallpaperManagerService = iWallpaperManager;
        this.mBiometricUnlockController = biometricUnlockController;
        this.mDozeParameters = dozeParameters;
    }

    @Override // com.android.systemui.doze.DozeMachine.Part
    public final void dump(PrintWriter printWriter) {
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "DozeWallpaperState:", " isAmbientMode: "), this.mIsAmbientMode, printWriter, " hasWallpaperService: "), this.mWallpaperManagerService != null, printWriter);
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0032, code lost:
    
        if (r9.mFadedAwayAfterWakeAndUnlock == false) goto L24;
     */
    @Override // com.android.systemui.doze.DozeMachine.Part
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void transitionTo(com.android.systemui.doze.DozeMachine.State r8, com.android.systemui.doze.DozeMachine.State r9) {
        /*
            r7 = this;
            java.lang.String r0 = "DozeWallpaperState"
            java.lang.String r1 = "AOD wallpaper state changed to: "
            int r2 = r9.ordinal()
            r3 = 1
            r4 = 0
            switch(r2) {
                case 2: goto Lf;
                case 3: goto Ld;
                case 4: goto Lf;
                case 5: goto Lf;
                case 6: goto Lf;
                case 7: goto Ld;
                case 8: goto Lf;
                case 9: goto Ld;
                case 10: goto Lf;
                case 11: goto Lf;
                case 12: goto Lf;
                default: goto Ld;
            }
        Ld:
            r2 = r4
            goto L10
        Lf:
            r2 = r3
        L10:
            com.android.systemui.statusbar.phone.DozeParameters r5 = r7.mDozeParameters
            if (r2 == 0) goto L17
            boolean r8 = r5.mControlScreenOffAnimation
            goto L39
        L17:
            com.android.systemui.doze.DozeMachine$State r6 = com.android.systemui.doze.DozeMachine.State.DOZE_PULSING
            if (r8 != r6) goto L21
            com.android.systemui.doze.DozeMachine$State r8 = com.android.systemui.doze.DozeMachine.State.FINISH
            if (r9 != r8) goto L21
            r8 = r3
            goto L22
        L21:
            r8 = r4
        L22:
            boolean r9 = r5.getDisplayNeedsBlanking()
            if (r9 != 0) goto L34
            com.android.systemui.statusbar.phone.BiometricUnlockController r9 = r7.mBiometricUnlockController
            boolean r5 = r9.isWakeAndUnlock()
            if (r5 != 0) goto L34
            boolean r9 = r9.mFadedAwayAfterWakeAndUnlock
            if (r9 == 0) goto L38
        L34:
            if (r8 == 0) goto L37
            goto L38
        L37:
            r3 = r4
        L38:
            r8 = r3
        L39:
            boolean r9 = r7.mIsAmbientMode
            if (r2 == r9) goto L82
            r7.mIsAmbientMode = r2
            android.app.IWallpaperManager r9 = r7.mWallpaperManagerService
            if (r9 == 0) goto L82
            if (r8 == 0) goto L48
            r8 = 500(0x1f4, double:2.47E-321)
            goto L4a
        L48:
            r8 = 0
        L4a:
            boolean r2 = com.android.systemui.doze.DozeWallpaperState.DEBUG     // Catch: android.os.RemoteException -> L6f
            if (r2 == 0) goto L67
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: android.os.RemoteException -> L6f
            r2.<init>(r1)     // Catch: android.os.RemoteException -> L6f
            boolean r1 = r7.mIsAmbientMode     // Catch: android.os.RemoteException -> L6f
            r2.append(r1)     // Catch: android.os.RemoteException -> L6f
            java.lang.String r1 = ", animationDuration: "
            r2.append(r1)     // Catch: android.os.RemoteException -> L6f
            r2.append(r8)     // Catch: android.os.RemoteException -> L6f
            java.lang.String r1 = r2.toString()     // Catch: android.os.RemoteException -> L6f
            android.util.Log.i(r0, r1)     // Catch: android.os.RemoteException -> L6f
        L67:
            android.app.IWallpaperManager r1 = r7.mWallpaperManagerService     // Catch: android.os.RemoteException -> L6f
            boolean r2 = r7.mIsAmbientMode     // Catch: android.os.RemoteException -> L6f
            r1.setInAmbientMode(r2, r8)     // Catch: android.os.RemoteException -> L6f
            goto L82
        L6f:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Cannot notify state to WallpaperManagerService: "
            r8.<init>(r9)
            boolean r7 = r7.mIsAmbientMode
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            android.util.Log.w(r0, r7)
        L82:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.doze.DozeWallpaperState.transitionTo(com.android.systemui.doze.DozeMachine$State, com.android.systemui.doze.DozeMachine$State):void");
    }
}
