package com.android.systemui.media.controls.ui.controller;

import android.database.ContentObserver;
import com.android.systemui.media.controls.ui.binder.SeekBarObserver;
import com.android.systemui.util.settings.GlobalSettingsImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaCarouselController$animationScaleObserver$1 extends ContentObserver {
    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        MediaPlayerData.INSTANCE.getClass();
        for (MediaControlPanel mediaControlPanel : MediaPlayerData.mediaPlayers.values()) {
            SeekBarObserver seekBarObserver = mediaControlPanel.mSeekBarObserver;
            if (seekBarObserver != null) {
                String string = ((GlobalSettingsImpl) mediaControlPanel.mGlobalSettings).getString("animator_duration_scale");
                float f = 1.0f;
                if (string != null) {
                    try {
                        f = Float.parseFloat(string);
                    } catch (NumberFormatException unused) {
                    }
                }
                seekBarObserver.animationEnabled = f > 0.0f;
            }
        }
    }
}
