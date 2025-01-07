package com.android.systemui.media.dialog;

import com.android.settingslib.media.MediaDevice;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MediaSwitchingController$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        MediaDevice mediaDevice = (MediaDevice) obj;
        switch (this.$r8$classId) {
            case 0:
                return mediaDevice.getId();
            default:
                return new MediaItem(mediaDevice, mediaDevice.getName(), 0);
        }
    }
}
