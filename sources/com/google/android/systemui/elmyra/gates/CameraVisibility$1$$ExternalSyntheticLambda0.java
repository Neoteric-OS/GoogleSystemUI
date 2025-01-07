package com.google.android.systemui.elmyra.gates;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CameraVisibility$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ CameraVisibility f$0;

    @Override // java.lang.Runnable
    public final void run() {
        CameraVisibility cameraVisibility = this.f$0;
        boolean isCameraShowing = cameraVisibility.isCameraShowing();
        if (cameraVisibility.mCameraShowing != isCameraShowing) {
            cameraVisibility.mCameraShowing = isCameraShowing;
            cameraVisibility.notifyListener();
        }
    }
}
