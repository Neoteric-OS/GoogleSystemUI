package com.android.settingslib.volume.shared.model;

import android.media.AudioSystem;
import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AudioStreamModel {
    public final int audioStream;
    public final boolean isAffectedByMute;
    public final boolean isAffectedByRingerMode;
    public final boolean isMuted;
    public final int maxVolume;
    public final int minVolume;
    public final int volume;

    public AudioStreamModel(int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3) {
        this.audioStream = i;
        this.volume = i2;
        this.minVolume = i3;
        this.maxVolume = i4;
        this.isAffectedByMute = z;
        this.isAffectedByRingerMode = z2;
        this.isMuted = z3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioStreamModel)) {
            return false;
        }
        AudioStreamModel audioStreamModel = (AudioStreamModel) obj;
        int i = audioStreamModel.audioStream;
        Set set = AudioStream.supportedStreamTypes;
        return this.audioStream == i && this.volume == audioStreamModel.volume && this.minVolume == audioStreamModel.minVolume && this.maxVolume == audioStreamModel.maxVolume && this.isAffectedByMute == audioStreamModel.isAffectedByMute && this.isAffectedByRingerMode == audioStreamModel.isAffectedByRingerMode && this.isMuted == audioStreamModel.isMuted;
    }

    public final int hashCode() {
        Set set = AudioStream.supportedStreamTypes;
        return Boolean.hashCode(this.isMuted) + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.maxVolume, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.minVolume, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.volume, Integer.hashCode(this.audioStream) * 31, 31), 31), 31), 31, this.isAffectedByMute), 31, this.isAffectedByRingerMode);
    }

    public final String toString() {
        Set set = AudioStream.supportedStreamTypes;
        StringBuilder m = ActivityResultRegistry$$ExternalSyntheticOutline0.m("AudioStreamModel(audioStream=", AudioSystem.streamToString(this.audioStream), ", volume=");
        m.append(this.volume);
        m.append(", minVolume=");
        m.append(this.minVolume);
        m.append(", maxVolume=");
        m.append(this.maxVolume);
        m.append(", isAffectedByMute=");
        m.append(this.isAffectedByMute);
        m.append(", isAffectedByRingerMode=");
        m.append(this.isAffectedByRingerMode);
        m.append(", isMuted=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(m, this.isMuted, ")");
    }
}
