package com.android.systemui.haptics.slider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliderEvent {
    public final float currentProgress;
    public final SliderEventType type;

    public SliderEvent(SliderEventType sliderEventType, float f) {
        this.type = sliderEventType;
        this.currentProgress = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SliderEvent)) {
            return false;
        }
        SliderEvent sliderEvent = (SliderEvent) obj;
        return this.type == sliderEvent.type && Float.compare(this.currentProgress, sliderEvent.currentProgress) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.currentProgress) + (this.type.hashCode() * 31);
    }

    public final String toString() {
        return "SliderEvent(type=" + this.type + ", currentProgress=" + this.currentProgress + ")";
    }
}
