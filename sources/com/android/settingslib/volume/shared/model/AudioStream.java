package com.android.settingslib.volume.shared.model;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.media.AudioSystem;
import java.util.Set;
import kotlin.collections.SetsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AudioStream {
    public static final Set supportedStreamTypes = SetsKt.setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
    public final int value;

    public /* synthetic */ AudioStream(int i) {
        this.value = i;
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static void m772constructorimpl(int i) {
        if (!supportedStreamTypes.contains(Integer.valueOf(i))) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unsupported stream=").toString());
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof AudioStream) {
            return this.value == ((AudioStream) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return AudioSystem.streamToString(this.value);
    }
}
