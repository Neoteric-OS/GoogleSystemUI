package com.android.settingslib.media;

import android.media.AudioAttributes;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class InputRouteManager {
    static final AudioAttributes INPUT_ATTRIBUTES = new AudioAttributes.Builder().setCapturePreset(1).build();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface InputDeviceCallback {
    }
}
