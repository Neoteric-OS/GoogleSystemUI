package com.android.settingslib.bluetooth;

import android.content.Context;
import android.media.AudioManager;
import android.media.audiopolicy.AudioProductStrategy;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HearingAidAudioRoutingHelper {
    public final AudioManager mAudioManager;

    public HearingAidAudioRoutingHelper(Context context) {
        this.mAudioManager = (AudioManager) context.getSystemService(AudioManager.class);
    }

    public List getAudioProductStrategies() {
        return AudioManager.getAudioProductStrategies();
    }

    public final boolean removePreferredDeviceForStrategies(List list) {
        Iterator it = list.iterator();
        boolean z = true;
        while (it.hasNext()) {
            AudioProductStrategy audioProductStrategy = (AudioProductStrategy) it.next();
            if (this.mAudioManager.getPreferredDeviceForStrategy(audioProductStrategy) != null) {
                z &= this.mAudioManager.removePreferredDeviceForStrategy(audioProductStrategy);
            }
        }
        return z;
    }
}
