package com.android.systemui.media.dialog;

import com.android.settingslib.media.MediaDevice;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaItem {
    public final Optional mMediaDeviceOptional;
    public final int mMediaItemType;
    public final String mTitle;

    public MediaItem(MediaDevice mediaDevice, String str, int i) {
        this.mMediaDeviceOptional = Optional.ofNullable(mediaDevice);
        this.mTitle = str;
        this.mMediaItemType = i;
    }
}
