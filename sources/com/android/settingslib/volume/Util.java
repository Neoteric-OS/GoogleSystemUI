package com.android.settingslib.volume;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.media.AudioAttributes;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import com.android.compose.PlatformSliderColors$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Util {
    public static final int[] AUDIO_MANAGER_FLAGS = {1, 16, 4, 2, 8, 2048, 128, 4096, 1024};
    public static final String[] AUDIO_MANAGER_FLAG_NAMES = {"SHOW_UI", "VIBRATE", "PLAY_SOUND", "ALLOW_RINGER_MODES", "REMOVE_SOUND_AND_VIBRATE", "SHOW_VIBRATE_HINT", "SHOW_SILENT_HINT", "FROM_KEY", "SHOW_UI_WARNINGS"};

    public static String audioManagerFlagsToString(int i) {
        int[] iArr = AUDIO_MANAGER_FLAGS;
        String[] strArr = AUDIO_MANAGER_FLAG_NAMES;
        if (i == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 9; i2++) {
            if ((iArr[i2] & i) != 0) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(strArr[i2]);
            }
            i &= ~iArr[i2];
        }
        if (i != 0) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append("UNKNOWN_");
            sb.append(i);
        }
        return sb.toString();
    }

    public static String playbackInfoToString(MediaController.PlaybackInfo playbackInfo) {
        if (playbackInfo == null) {
            return null;
        }
        int playbackType = playbackInfo.getPlaybackType();
        String m = playbackType != 1 ? playbackType != 2 ? AnnotationValue$1$$ExternalSyntheticOutline0.m(playbackType, "UNKNOWN_") : "REMOTE" : "LOCAL";
        int volumeControl = playbackInfo.getVolumeControl();
        String m2 = volumeControl != 0 ? volumeControl != 1 ? volumeControl != 2 ? AnnotationValue$1$$ExternalSyntheticOutline0.m(volumeControl, "VOLUME_CONTROL_UNKNOWN_") : "VOLUME_CONTROL_ABSOLUTE" : "VOLUME_CONTROL_RELATIVE" : "VOLUME_CONTROL_FIXED";
        int currentVolume = playbackInfo.getCurrentVolume();
        int maxVolume = playbackInfo.getMaxVolume();
        AudioAttributes audioAttributes = playbackInfo.getAudioAttributes();
        StringBuilder m3 = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(currentVolume, maxVolume, "PlaybackInfo[vol=", ",max=", ",type=");
        PlatformSliderColors$$ExternalSyntheticOutline0.m(m3, m, ",vc=", m2, "],atts=");
        m3.append(audioAttributes);
        return m3.toString();
    }

    public static String playbackStateToString(PlaybackState playbackState) {
        if (playbackState == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int state = playbackState.getState();
        sb.append(state != 0 ? state != 1 ? state != 2 ? state != 3 ? AnnotationValue$1$$ExternalSyntheticOutline0.m(state, "UNKNOWN_") : "STATE_PLAYING" : "STATE_PAUSED" : "STATE_STOPPED" : "STATE_NONE");
        sb.append(" ");
        sb.append(playbackState);
        return sb.toString();
    }
}
