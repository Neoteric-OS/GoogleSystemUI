package androidx.compose.foundation.text.modifiers;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextStringSimpleElement extends ModifierNodeElement {
    public final ColorProducer color;
    public final FontFamily.Resolver fontFamilyResolver;
    public final int maxLines;
    public final int minLines;
    public final int overflow;
    public final boolean softWrap;
    public final TextStyle style;
    public final String text;

    public TextStringSimpleElement(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, ColorProducer colorProducer) {
        this.text = str;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.overflow = i;
        this.softWrap = z;
        this.maxLines = i2;
        this.minLines = i3;
        this.color = colorProducer;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        TextStringSimpleNode textStringSimpleNode = new TextStringSimpleNode();
        textStringSimpleNode.text = this.text;
        textStringSimpleNode.style = this.style;
        textStringSimpleNode.fontFamilyResolver = this.fontFamilyResolver;
        textStringSimpleNode.overflow = this.overflow;
        textStringSimpleNode.softWrap = this.softWrap;
        textStringSimpleNode.maxLines = this.maxLines;
        textStringSimpleNode.minLines = this.minLines;
        textStringSimpleNode.overrideColor = this.color;
        return textStringSimpleNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextStringSimpleElement)) {
            return false;
        }
        TextStringSimpleElement textStringSimpleElement = (TextStringSimpleElement) obj;
        return Intrinsics.areEqual(this.color, textStringSimpleElement.color) && Intrinsics.areEqual(this.text, textStringSimpleElement.text) && Intrinsics.areEqual(this.style, textStringSimpleElement.style) && Intrinsics.areEqual(this.fontFamilyResolver, textStringSimpleElement.fontFamilyResolver) && Intrinsics.areEqual((Object) null, (Object) null) && TextOverflow.m647equalsimpl0(this.overflow, textStringSimpleElement.overflow) && this.softWrap == textStringSimpleElement.softWrap && this.maxLines == textStringSimpleElement.maxLines && this.minLines == textStringSimpleElement.minLines;
    }

    public final int hashCode() {
        int m = (((TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.overflow, (this.fontFamilyResolver.hashCode() + SelectableTextAnnotatedStringElement$$ExternalSyntheticOutline0.m(this.text.hashCode() * 31, 31, this.style)) * 31, 31), 31, this.softWrap) + this.maxLines) * 31) + this.minLines) * 31;
        ColorProducer colorProducer = this.color;
        return (m + (colorProducer != null ? colorProducer.hashCode() : 0)) * 31;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x001e, code lost:
    
        if (r3.spanStyle.hasSameNonLayoutAttributes$ui_text_release(r0.spanStyle) != false) goto L10;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void update(androidx.compose.ui.Modifier.Node r11) {
        /*
            r10 = this;
            androidx.compose.foundation.text.modifiers.TextStringSimpleNode r11 = (androidx.compose.foundation.text.modifiers.TextStringSimpleNode) r11
            androidx.compose.ui.graphics.ColorProducer r0 = r11.overrideColor
            androidx.compose.ui.graphics.ColorProducer r1 = r10.color
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            r11.overrideColor = r1
            r1 = 0
            r2 = 1
            androidx.compose.ui.text.TextStyle r3 = r10.style
            if (r0 == 0) goto L26
            androidx.compose.ui.text.TextStyle r0 = r11.style
            if (r3 == r0) goto L21
            androidx.compose.ui.text.SpanStyle r4 = r3.spanStyle
            androidx.compose.ui.text.SpanStyle r0 = r0.spanStyle
            boolean r0 = r4.hasSameNonLayoutAttributes$ui_text_release(r0)
            if (r0 == 0) goto L26
            goto L24
        L21:
            r3.getClass()
        L24:
            r0 = r1
            goto L27
        L26:
            r0 = r2
        L27:
            java.lang.String r4 = r11.text
            java.lang.String r5 = r10.text
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            r6 = 0
            if (r4 == 0) goto L33
            goto L38
        L33:
            r11.text = r5
            r11.textSubstitution = r6
            r1 = r2
        L38:
            androidx.compose.ui.text.TextStyle r4 = r11.style
            boolean r4 = r4.hasSameLayoutAffectingAttributes(r3)
            r4 = r4 ^ r2
            r11.style = r3
            int r3 = r11.minLines
            int r5 = r10.minLines
            if (r3 == r5) goto L4a
            r11.minLines = r5
            r4 = r2
        L4a:
            int r3 = r11.maxLines
            int r5 = r10.maxLines
            if (r3 == r5) goto L53
            r11.maxLines = r5
            r4 = r2
        L53:
            boolean r3 = r11.softWrap
            boolean r5 = r10.softWrap
            if (r3 == r5) goto L5c
            r11.softWrap = r5
            r4 = r2
        L5c:
            androidx.compose.ui.text.font.FontFamily$Resolver r3 = r11.fontFamilyResolver
            androidx.compose.ui.text.font.FontFamily$Resolver r5 = r10.fontFamilyResolver
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r5)
            if (r3 != 0) goto L69
            r11.fontFamilyResolver = r5
            r4 = r2
        L69:
            int r3 = r11.overflow
            int r10 = r10.overflow
            boolean r3 = androidx.compose.ui.text.style.TextOverflow.m647equalsimpl0(r3, r10)
            if (r3 != 0) goto L76
            r11.overflow = r10
            r4 = r2
        L76:
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r6)
            if (r10 != 0) goto L7d
            goto L7e
        L7d:
            r2 = r4
        L7e:
            if (r1 != 0) goto L82
            if (r2 == 0) goto La5
        L82:
            androidx.compose.foundation.text.modifiers.ParagraphLayoutCache r10 = r11.getLayoutCache()
            java.lang.String r3 = r11.text
            androidx.compose.ui.text.TextStyle r4 = r11.style
            androidx.compose.ui.text.font.FontFamily$Resolver r5 = r11.fontFamilyResolver
            int r6 = r11.overflow
            boolean r7 = r11.softWrap
            int r8 = r11.maxLines
            int r9 = r11.minLines
            r10.text = r3
            r10.style = r4
            r10.fontFamilyResolver = r5
            r10.overflow = r6
            r10.softWrap = r7
            r10.maxLines = r8
            r10.minLines = r9
            r10.markDirty()
        La5:
            boolean r10 = r11.isAttached
            if (r10 != 0) goto Laa
            goto Lc4
        Laa:
            if (r1 != 0) goto Lb2
            if (r0 == 0) goto Lb5
            kotlin.jvm.functions.Function1 r10 = r11.semanticsTextLayoutResult
            if (r10 == 0) goto Lb5
        Lb2:
            androidx.compose.ui.node.SemanticsModifierNodeKt.invalidateSemantics(r11)
        Lb5:
            if (r1 != 0) goto Lb9
            if (r2 == 0) goto Lbf
        Lb9:
            androidx.compose.ui.node.LayoutModifierNodeKt.invalidateMeasurement(r11)
            androidx.compose.ui.node.DrawModifierNodeKt.invalidateDraw(r11)
        Lbf:
            if (r0 == 0) goto Lc4
            androidx.compose.ui.node.DrawModifierNodeKt.invalidateDraw(r11)
        Lc4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.modifiers.TextStringSimpleElement.update(androidx.compose.ui.Modifier$Node):void");
    }
}
