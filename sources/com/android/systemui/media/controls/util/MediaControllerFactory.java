package com.android.systemui.media.controls.util;

import android.content.Context;
import android.media.session.MediaController;
import android.media.session.MediaSession;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaControllerFactory {
    public final Context mContext;

    public MediaControllerFactory(Context context) {
        this.mContext = context;
    }

    public final MediaController create(MediaSession.Token token) {
        return new MediaController(this.mContext, token);
    }
}
