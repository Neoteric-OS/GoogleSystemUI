package androidx.compose.ui.text.font;

import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceFontFamilyNameFont extends AndroidFont {
    public final String familyName;
    public final FontWeight weight;

    public DeviceFontFamilyNameFont(String str, FontWeight fontWeight, FontVariation$Settings fontVariation$Settings) {
        super(fontVariation$Settings);
        this.familyName = str;
        this.weight = fontWeight;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceFontFamilyNameFont)) {
            return false;
        }
        DeviceFontFamilyNameFont deviceFontFamilyNameFont = (DeviceFontFamilyNameFont) obj;
        if (Intrinsics.areEqual(this.familyName, deviceFontFamilyNameFont.familyName) && Intrinsics.areEqual(this.weight, deviceFontFamilyNameFont.weight) && FontStyle.m609equalsimpl0(0, 0)) {
            return this.variationSettings.equals(deviceFontFamilyNameFont.variationSettings);
        }
        return false;
    }

    public final int hashCode() {
        return this.variationSettings.settings.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(0, ((this.familyName.hashCode() * 31) + this.weight.weight) * 31, 31);
    }

    public final String toString() {
        return "Font(familyName=\"" + ((Object) ("DeviceFontFamilyName(name=" + this.familyName + ')')) + "\", weight=" + this.weight + ", style=" + ((Object) FontStyle.m610toStringimpl(0)) + ')';
    }
}
