package androidx.compose.ui.text;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.modifiers.SelectableTextAnnotatedStringElement$$ExternalSyntheticOutline0;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextLayoutInput {
    public final long constraints;
    public final Density density;
    public final FontFamily.Resolver fontFamilyResolver;
    public final LayoutDirection layoutDirection;
    public final int maxLines;
    public final int overflow;
    public final List placeholders;
    public final boolean softWrap;
    public final TextStyle style;
    public final AnnotatedString text;

    public TextLayoutInput(AnnotatedString annotatedString, TextStyle textStyle, List list, int i, boolean z, int i2, Density density, LayoutDirection layoutDirection, FontFamily.Resolver resolver, long j) {
        this.text = annotatedString;
        this.style = textStyle;
        this.placeholders = list;
        this.maxLines = i;
        this.softWrap = z;
        this.overflow = i2;
        this.density = density;
        this.layoutDirection = layoutDirection;
        this.fontFamilyResolver = resolver;
        this.constraints = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextLayoutInput)) {
            return false;
        }
        TextLayoutInput textLayoutInput = (TextLayoutInput) obj;
        return Intrinsics.areEqual(this.text, textLayoutInput.text) && Intrinsics.areEqual(this.style, textLayoutInput.style) && Intrinsics.areEqual(this.placeholders, textLayoutInput.placeholders) && this.maxLines == textLayoutInput.maxLines && this.softWrap == textLayoutInput.softWrap && TextOverflow.m647equalsimpl0(this.overflow, textLayoutInput.overflow) && Intrinsics.areEqual(this.density, textLayoutInput.density) && this.layoutDirection == textLayoutInput.layoutDirection && Intrinsics.areEqual(this.fontFamilyResolver, textLayoutInput.fontFamilyResolver) && Constraints.m649equalsimpl0(this.constraints, textLayoutInput.constraints);
    }

    public final int hashCode() {
        return Long.hashCode(this.constraints) + ((this.fontFamilyResolver.hashCode() + ((this.layoutDirection.hashCode() + ((this.density.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.overflow, TransitionData$$ExternalSyntheticOutline0.m((PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(SelectableTextAnnotatedStringElement$$ExternalSyntheticOutline0.m(this.text.hashCode() * 31, 31, this.style), 31, this.placeholders) + this.maxLines) * 31, 31, this.softWrap), 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TextLayoutInput(text=");
        sb.append((Object) this.text);
        sb.append(", style=");
        sb.append(this.style);
        sb.append(", placeholders=");
        sb.append(this.placeholders);
        sb.append(", maxLines=");
        sb.append(this.maxLines);
        sb.append(", softWrap=");
        sb.append(this.softWrap);
        sb.append(", overflow=");
        int i = this.overflow;
        sb.append((Object) (TextOverflow.m647equalsimpl0(i, 1) ? "Clip" : TextOverflow.m647equalsimpl0(i, 2) ? "Ellipsis" : TextOverflow.m647equalsimpl0(i, 5) ? "MiddleEllipsis" : TextOverflow.m647equalsimpl0(i, 3) ? "Visible" : TextOverflow.m647equalsimpl0(i, 4) ? "StartEllipsis" : "Invalid"));
        sb.append(", density=");
        sb.append(this.density);
        sb.append(", layoutDirection=");
        sb.append(this.layoutDirection);
        sb.append(", fontFamilyResolver=");
        sb.append(this.fontFamilyResolver);
        sb.append(", constraints=");
        sb.append((Object) Constraints.m658toStringimpl(this.constraints));
        sb.append(')');
        return sb.toString();
    }
}
