package com.android.systemui.shade;

import com.android.systemui.camera.CameraGestureHelper;
import com.android.systemui.statusbar.phone.KeyguardBypassController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CameraLauncher {
    public final CameraGestureHelper mCameraGestureHelper;
    public final KeyguardBypassController mKeyguardBypassController;

    public CameraLauncher(CameraGestureHelper cameraGestureHelper, KeyguardBypassController keyguardBypassController) {
        this.mCameraGestureHelper = cameraGestureHelper;
        this.mKeyguardBypassController = keyguardBypassController;
    }
}
