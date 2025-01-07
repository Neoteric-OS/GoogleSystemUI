package com.google.android.systemui.assist.uihints;

import android.hardware.input.InputManagerGlobal;
import android.os.Handler;
import android.os.Looper;
import android.view.InputDevice;
import android.view.MotionEvent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SwipeHandler {
    public final Handler mUiHandler = new Handler(Looper.getMainLooper());

    public static void injectMotionEvent(int i, long j, float f, float f2, float f3) {
        int i2;
        int[] deviceIds = InputDevice.getDeviceIds();
        int length = deviceIds.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                i2 = 0;
                break;
            }
            int i4 = deviceIds[i3];
            if (InputDevice.getDevice(i4).supportsSource(4098)) {
                i2 = i4;
                break;
            }
            i3++;
        }
        MotionEvent obtain = MotionEvent.obtain(j, j, i, f, f2, f3, 1.0f, 0, 1.0f, 1.0f, i2, 0);
        obtain.setSource(4098);
        InputManagerGlobal.getInstance().injectInputEvent(obtain, 0);
    }
}
