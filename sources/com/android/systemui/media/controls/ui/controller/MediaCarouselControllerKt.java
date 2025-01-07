package com.android.systemui.media.controls.ui.controller;

import android.content.Intent;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MediaCarouselControllerKt {
    public static final Intent settingsIntent = new Intent().setAction("android.settings.ACTION_MEDIA_CONTROLS_SETTINGS");
    public static final boolean DEBUG = Log.isLoggable("MediaCarouselController", 3);
}
