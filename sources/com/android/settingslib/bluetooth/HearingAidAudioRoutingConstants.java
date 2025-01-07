package com.android.settingslib.bluetooth;

import android.media.AudioDeviceAttributes;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class HearingAidAudioRoutingConstants {
    public static final int[] CALL_ROUTING_ATTRIBUTES = {2};
    public static final int[] MEDIA_ROUTING_ATTRIBUTES = {1, 11, 3};
    public static final int[] RINGTONE_ROUTING_ATTRIBUTES = {6};
    public static final int[] NOTIFICATION_ROUTING_ATTRIBUTES = {5};
    public static final AudioDeviceAttributes DEVICE_SPEAKER_OUT = new AudioDeviceAttributes(2, 2, "");
}
