package androidx.compose.ui.text.font;

import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TypefaceRequest {
    public final FontFamily fontFamily;
    public final int fontStyle;
    public final int fontSynthesis;
    public final FontWeight fontWeight;
    public final Object resourceLoaderCacheKey;

    public TypefaceRequest(FontFamily fontFamily, FontWeight fontWeight, int i, int i2, Object obj) {
        this.fontFamily = fontFamily;
        this.fontWeight = fontWeight;
        this.fontStyle = i;
        this.fontSynthesis = i2;
        this.resourceLoaderCacheKey = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypefaceRequest)) {
            return false;
        }
        TypefaceRequest typefaceRequest = (TypefaceRequest) obj;
        return Intrinsics.areEqual(this.fontFamily, typefaceRequest.fontFamily) && Intrinsics.areEqual(this.fontWeight, typefaceRequest.fontWeight) && FontStyle.m609equalsimpl0(this.fontStyle, typefaceRequest.fontStyle) && FontSynthesis.m611equalsimpl0(this.fontSynthesis, typefaceRequest.fontSynthesis) && Intrinsics.areEqual(this.resourceLoaderCacheKey, typefaceRequest.resourceLoaderCacheKey);
    }

    public final int hashCode() {
        FontFamily fontFamily = this.fontFamily;
        int m = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.fontSynthesis, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.fontStyle, (((fontFamily == null ? 0 : fontFamily.hashCode()) * 31) + this.fontWeight.weight) * 31, 31), 31);
        Object obj = this.resourceLoaderCacheKey;
        return m + (obj != null ? obj.hashCode() : 0);
    }

    public final String toString() {
        return "TypefaceRequest(fontFamily=" + this.fontFamily + ", fontWeight=" + this.fontWeight + ", fontStyle=" + ((Object) FontStyle.m610toStringimpl(this.fontStyle)) + ", fontSynthesis=" + ((Object) FontSynthesis.m612toStringimpl(this.fontSynthesis)) + ", resourceLoaderCacheKey=" + this.resourceLoaderCacheKey + ')';
    }
}
