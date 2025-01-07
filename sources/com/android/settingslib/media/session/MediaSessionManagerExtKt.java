package com.android.settingslib.media.session;

import android.media.session.MediaSessionManager;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MediaSessionManagerExtKt {
    public static final Flow getActiveMediaChanges(MediaSessionManager mediaSessionManager) {
        return FlowKt.buffer$default(FlowKt.callbackFlow(new MediaSessionManagerExtKt$activeMediaChanges$1(mediaSessionManager, null)), -1);
    }

    public static final Flow getDefaultRemoteSessionChanged(MediaSessionManager mediaSessionManager) {
        return FlowKt.buffer$default(FlowKt.callbackFlow(new MediaSessionManagerExtKt$defaultRemoteSessionChanged$1(mediaSessionManager, null)), -1);
    }
}
