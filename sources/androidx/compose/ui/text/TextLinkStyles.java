package androidx.compose.ui.text;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextLinkStyles {
    public final SpanStyle focusedStyle;
    public final SpanStyle hoveredStyle;
    public final SpanStyle pressedStyle;
    public final SpanStyle style;

    public TextLinkStyles(SpanStyle spanStyle, SpanStyle spanStyle2, SpanStyle spanStyle3, SpanStyle spanStyle4) {
        this.style = spanStyle;
        this.focusedStyle = spanStyle2;
        this.hoveredStyle = spanStyle3;
        this.pressedStyle = spanStyle4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TextLinkStyles)) {
            return false;
        }
        TextLinkStyles textLinkStyles = (TextLinkStyles) obj;
        return Intrinsics.areEqual(this.style, textLinkStyles.style) && Intrinsics.areEqual(this.focusedStyle, textLinkStyles.focusedStyle) && Intrinsics.areEqual(this.hoveredStyle, textLinkStyles.hoveredStyle) && Intrinsics.areEqual(this.pressedStyle, textLinkStyles.pressedStyle);
    }

    public final int hashCode() {
        SpanStyle spanStyle = this.style;
        int hashCode = (spanStyle != null ? spanStyle.hashCode() : 0) * 31;
        SpanStyle spanStyle2 = this.focusedStyle;
        int hashCode2 = (hashCode + (spanStyle2 != null ? spanStyle2.hashCode() : 0)) * 31;
        SpanStyle spanStyle3 = this.hoveredStyle;
        int hashCode3 = (hashCode2 + (spanStyle3 != null ? spanStyle3.hashCode() : 0)) * 31;
        SpanStyle spanStyle4 = this.pressedStyle;
        return hashCode3 + (spanStyle4 != null ? spanStyle4.hashCode() : 0);
    }
}
