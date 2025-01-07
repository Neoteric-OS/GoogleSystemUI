package com.android.systemui.biometrics;

import android.hardware.fingerprint.IUdfpsOverlayControllerCallback;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UdfpsShell$showOverlay$1 extends IUdfpsOverlayControllerCallback.Stub {
    public final void onUserCanceled() {
        Log.e("UdfpsShell", "User cancelled");
    }
}
