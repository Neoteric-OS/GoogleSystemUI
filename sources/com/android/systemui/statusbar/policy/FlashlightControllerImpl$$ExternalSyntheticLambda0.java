package com.android.systemui.statusbar.policy;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Log;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class FlashlightControllerImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ FlashlightControllerImpl f$0;

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        FlashlightControllerImpl flashlightControllerImpl = this.f$0;
        if (flashlightControllerImpl.mHasFlashlight && flashlightControllerImpl.mCameraId.get() == null) {
            try {
                AtomicReference atomicReference = flashlightControllerImpl.mCameraId;
                String[] cameraIdList = flashlightControllerImpl.mCameraManager.getCameraIdList();
                int length = cameraIdList.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        str = null;
                        break;
                    }
                    str = cameraIdList[i];
                    CameraCharacteristics cameraCharacteristics = flashlightControllerImpl.mCameraManager.getCameraCharacteristics(str);
                    Boolean bool = (Boolean) cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                    if (bool != null && bool.booleanValue() && num != null && num.intValue() == 1) {
                        break;
                    } else {
                        i++;
                    }
                }
                atomicReference.set(str);
                if (flashlightControllerImpl.mCameraId.get() != null) {
                    flashlightControllerImpl.mCameraManager.registerTorchCallback(flashlightControllerImpl.mExecutor, flashlightControllerImpl.mTorchCallback);
                }
            } catch (Throwable th) {
                Log.e("FlashlightController", "Couldn't initialize.", th);
            }
        }
    }
}
