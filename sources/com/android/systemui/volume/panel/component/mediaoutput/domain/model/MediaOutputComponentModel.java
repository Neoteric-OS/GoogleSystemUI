package com.android.systemui.volume.panel.component.mediaoutput.domain.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import com.android.systemui.volume.domain.model.AudioOutputDevice;
import com.android.systemui.volume.panel.component.mediaoutput.shared.model.MediaDeviceSession;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface MediaOutputComponentModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Calling implements MediaOutputComponentModel {
        public final AudioOutputDevice device;
        public final boolean isInAudioSharing;

        public Calling(AudioOutputDevice audioOutputDevice, boolean z) {
            this.device = audioOutputDevice;
            this.isInAudioSharing = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Calling)) {
                return false;
            }
            Calling calling = (Calling) obj;
            return Intrinsics.areEqual(this.device, calling.device) && this.isInAudioSharing == calling.isInAudioSharing;
        }

        @Override // com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel
        public final boolean getCanOpenAudioSwitcher() {
            return false;
        }

        @Override // com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel
        public final AudioOutputDevice getDevice() {
            return this.device;
        }

        public final int hashCode() {
            return Boolean.hashCode(false) + TransitionData$$ExternalSyntheticOutline0.m(this.device.hashCode() * 31, 31, this.isInAudioSharing);
        }

        @Override // com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel
        public final boolean isInAudioSharing() {
            return this.isInAudioSharing;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Calling(device=");
            sb.append(this.device);
            sb.append(", isInAudioSharing=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isInAudioSharing, ", canOpenAudioSwitcher=false)");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Idle implements MediaOutputComponentModel {
        public final boolean canOpenAudioSwitcher;
        public final AudioOutputDevice device;
        public final boolean isInAudioSharing;

        public Idle(AudioOutputDevice audioOutputDevice, boolean z, boolean z2) {
            this.device = audioOutputDevice;
            this.isInAudioSharing = z;
            this.canOpenAudioSwitcher = z2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Idle)) {
                return false;
            }
            Idle idle = (Idle) obj;
            return Intrinsics.areEqual(this.device, idle.device) && this.isInAudioSharing == idle.isInAudioSharing && this.canOpenAudioSwitcher == idle.canOpenAudioSwitcher;
        }

        @Override // com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel
        public final boolean getCanOpenAudioSwitcher() {
            return this.canOpenAudioSwitcher;
        }

        @Override // com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel
        public final AudioOutputDevice getDevice() {
            return this.device;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.canOpenAudioSwitcher) + TransitionData$$ExternalSyntheticOutline0.m(this.device.hashCode() * 31, 31, this.isInAudioSharing);
        }

        @Override // com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel
        public final boolean isInAudioSharing() {
            return this.isInAudioSharing;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Idle(device=");
            sb.append(this.device);
            sb.append(", isInAudioSharing=");
            sb.append(this.isInAudioSharing);
            sb.append(", canOpenAudioSwitcher=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.canOpenAudioSwitcher, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MediaSession implements MediaOutputComponentModel {
        public final boolean canOpenAudioSwitcher;
        public final AudioOutputDevice device;
        public final boolean isInAudioSharing;
        public final boolean isPlaybackActive;
        public final MediaDeviceSession session;

        public MediaSession(MediaDeviceSession mediaDeviceSession, boolean z, AudioOutputDevice audioOutputDevice, boolean z2, boolean z3) {
            this.session = mediaDeviceSession;
            this.isPlaybackActive = z;
            this.device = audioOutputDevice;
            this.isInAudioSharing = z2;
            this.canOpenAudioSwitcher = z3;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaSession)) {
                return false;
            }
            MediaSession mediaSession = (MediaSession) obj;
            return Intrinsics.areEqual(this.session, mediaSession.session) && this.isPlaybackActive == mediaSession.isPlaybackActive && Intrinsics.areEqual(this.device, mediaSession.device) && this.isInAudioSharing == mediaSession.isInAudioSharing && this.canOpenAudioSwitcher == mediaSession.canOpenAudioSwitcher;
        }

        @Override // com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel
        public final boolean getCanOpenAudioSwitcher() {
            return this.canOpenAudioSwitcher;
        }

        @Override // com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel
        public final AudioOutputDevice getDevice() {
            return this.device;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.canOpenAudioSwitcher) + TransitionData$$ExternalSyntheticOutline0.m((this.device.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(this.session.hashCode() * 31, 31, this.isPlaybackActive)) * 31, 31, this.isInAudioSharing);
        }

        @Override // com.android.systemui.volume.panel.component.mediaoutput.domain.model.MediaOutputComponentModel
        public final boolean isInAudioSharing() {
            return this.isInAudioSharing;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("MediaSession(session=");
            sb.append(this.session);
            sb.append(", isPlaybackActive=");
            sb.append(this.isPlaybackActive);
            sb.append(", device=");
            sb.append(this.device);
            sb.append(", isInAudioSharing=");
            sb.append(this.isInAudioSharing);
            sb.append(", canOpenAudioSwitcher=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.canOpenAudioSwitcher, ")");
        }
    }

    boolean getCanOpenAudioSwitcher();

    AudioOutputDevice getDevice();

    boolean isInAudioSharing();
}
