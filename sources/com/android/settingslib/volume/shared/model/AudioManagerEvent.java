package com.android.settingslib.volume.shared.model;

import android.media.AudioSystem;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface AudioManagerEvent {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InternalRingerModeChanged implements AudioManagerEvent {
        public static final InternalRingerModeChanged INSTANCE = new InternalRingerModeChanged();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof InternalRingerModeChanged);
        }

        public final int hashCode() {
            return 648091418;
        }

        public final String toString() {
            return "InternalRingerModeChanged";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StreamDevicesChanged implements AudioManagerEvent {
        public static final StreamDevicesChanged INSTANCE = new StreamDevicesChanged();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof StreamDevicesChanged);
        }

        public final int hashCode() {
            return 1307421940;
        }

        public final String toString() {
            return "StreamDevicesChanged";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StreamMasterMuteChanged implements AudioManagerEvent {
        public static final StreamMasterMuteChanged INSTANCE = new StreamMasterMuteChanged();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof StreamMasterMuteChanged);
        }

        public final int hashCode() {
            return 591006620;
        }

        public final String toString() {
            return "StreamMasterMuteChanged";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StreamMuteChanged implements StreamAudioManagerEvent {
        public final int audioStream;

        public StreamMuteChanged(int i) {
            this.audioStream = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StreamMuteChanged)) {
                return false;
            }
            int i = ((StreamMuteChanged) obj).audioStream;
            Set set = AudioStream.supportedStreamTypes;
            return this.audioStream == i;
        }

        @Override // com.android.settingslib.volume.shared.model.StreamAudioManagerEvent
        /* renamed from: getAudioStream-2ffMKO0, reason: not valid java name */
        public final int mo771getAudioStream2ffMKO0() {
            return this.audioStream;
        }

        public final int hashCode() {
            Set set = AudioStream.supportedStreamTypes;
            return Integer.hashCode(this.audioStream);
        }

        public final String toString() {
            Set set = AudioStream.supportedStreamTypes;
            return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("StreamMuteChanged(audioStream=", AudioSystem.streamToString(this.audioStream), ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StreamVolumeChanged implements StreamAudioManagerEvent {
        public final int audioStream;

        public StreamVolumeChanged(int i) {
            this.audioStream = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StreamVolumeChanged)) {
                return false;
            }
            int i = ((StreamVolumeChanged) obj).audioStream;
            Set set = AudioStream.supportedStreamTypes;
            return this.audioStream == i;
        }

        @Override // com.android.settingslib.volume.shared.model.StreamAudioManagerEvent
        /* renamed from: getAudioStream-2ffMKO0 */
        public final int mo771getAudioStream2ffMKO0() {
            return this.audioStream;
        }

        public final int hashCode() {
            Set set = AudioStream.supportedStreamTypes;
            return Integer.hashCode(this.audioStream);
        }

        public final String toString() {
            Set set = AudioStream.supportedStreamTypes;
            return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("StreamVolumeChanged(audioStream=", AudioSystem.streamToString(this.audioStream), ")");
        }
    }
}
