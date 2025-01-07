package com.android.systemui.accessibility.data.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CaptioningModel {
    public final boolean isSystemAudioCaptioningEnabled;
    public final boolean isSystemAudioCaptioningUiEnabled;

    public CaptioningModel(boolean z, boolean z2) {
        this.isSystemAudioCaptioningUiEnabled = z;
        this.isSystemAudioCaptioningEnabled = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CaptioningModel)) {
            return false;
        }
        CaptioningModel captioningModel = (CaptioningModel) obj;
        return this.isSystemAudioCaptioningUiEnabled == captioningModel.isSystemAudioCaptioningUiEnabled && this.isSystemAudioCaptioningEnabled == captioningModel.isSystemAudioCaptioningEnabled;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isSystemAudioCaptioningEnabled) + (Boolean.hashCode(this.isSystemAudioCaptioningUiEnabled) * 31);
    }

    public final String toString() {
        return "CaptioningModel(isSystemAudioCaptioningUiEnabled=" + this.isSystemAudioCaptioningUiEnabled + ", isSystemAudioCaptioningEnabled=" + this.isSystemAudioCaptioningEnabled + ")";
    }
}
