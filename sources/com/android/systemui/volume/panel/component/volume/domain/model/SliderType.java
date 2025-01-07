package com.android.systemui.volume.panel.component.volume.domain.model;

import android.media.AudioSystem;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.settingslib.volume.shared.model.AudioStream;
import com.android.systemui.volume.panel.component.mediaoutput.shared.model.MediaDeviceSession;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SliderType {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MediaDeviceCast implements SliderType {
        public final MediaDeviceSession session;

        public MediaDeviceCast(MediaDeviceSession mediaDeviceSession) {
            this.session = mediaDeviceSession;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof MediaDeviceCast) && Intrinsics.areEqual(this.session, ((MediaDeviceCast) obj).session);
        }

        public final int hashCode() {
            return this.session.hashCode();
        }

        public final String toString() {
            return "MediaDeviceCast(session=" + this.session + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Stream implements SliderType {
        public final int stream;

        public Stream(int i) {
            this.stream = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Stream)) {
                return false;
            }
            int i = ((Stream) obj).stream;
            Set set = AudioStream.supportedStreamTypes;
            return this.stream == i;
        }

        public final int hashCode() {
            Set set = AudioStream.supportedStreamTypes;
            return Integer.hashCode(this.stream);
        }

        public final String toString() {
            Set set = AudioStream.supportedStreamTypes;
            return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Stream(stream=", AudioSystem.streamToString(this.stream), ")");
        }
    }
}
