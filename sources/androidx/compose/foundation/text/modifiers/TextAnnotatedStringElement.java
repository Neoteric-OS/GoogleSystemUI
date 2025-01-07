package androidx.compose.foundation.text.modifiers;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextOverflow;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextAnnotatedStringElement extends ModifierNodeElement {
    public final ColorProducer color;
    public final FontFamily.Resolver fontFamilyResolver;
    public final int maxLines;
    public final int minLines;
    public final Function1 onPlaceholderLayout;
    public final Function1 onShowTranslation;
    public final Function1 onTextLayout;
    public final int overflow;
    public final List placeholders;
    public final boolean softWrap;
    public final TextStyle style;
    public final AnnotatedString text;

    public TextAnnotatedStringElement(AnnotatedString annotatedString, TextStyle textStyle, FontFamily.Resolver resolver, Function1 function1, int i, boolean z, int i2, int i3, List list, Function1 function12, ColorProducer colorProducer, Function1 function13) {
        this.text = annotatedString;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.onTextLayout = function1;
        this.overflow = i;
        this.softWrap = z;
        this.maxLines = i2;
        this.minLines = i3;
        this.placeholders = list;
        this.onPlaceholderLayout = function12;
        this.color = colorProducer;
        this.onShowTranslation = function13;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        List list = this.placeholders;
        Function1 function1 = this.onPlaceholderLayout;
        Function1 function12 = this.onShowTranslation;
        AnnotatedString annotatedString = this.text;
        TextStyle textStyle = this.style;
        FontFamily.Resolver resolver = this.fontFamilyResolver;
        Function1 function13 = this.onTextLayout;
        int i = this.overflow;
        boolean z = this.softWrap;
        int i2 = this.maxLines;
        int i3 = this.minLines;
        ColorProducer colorProducer = this.color;
        TextAnnotatedStringNode textAnnotatedStringNode = new TextAnnotatedStringNode();
        textAnnotatedStringNode.text = annotatedString;
        textAnnotatedStringNode.style = textStyle;
        textAnnotatedStringNode.fontFamilyResolver = resolver;
        textAnnotatedStringNode.onTextLayout = function13;
        textAnnotatedStringNode.overflow = i;
        textAnnotatedStringNode.softWrap = z;
        textAnnotatedStringNode.maxLines = i2;
        textAnnotatedStringNode.minLines = i3;
        textAnnotatedStringNode.placeholders = list;
        textAnnotatedStringNode.onPlaceholderLayout = function1;
        textAnnotatedStringNode.overrideColor = colorProducer;
        textAnnotatedStringNode.onShowTranslation = function12;
        return textAnnotatedStringNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextAnnotatedStringElement)) {
            return false;
        }
        TextAnnotatedStringElement textAnnotatedStringElement = (TextAnnotatedStringElement) obj;
        return Intrinsics.areEqual(this.color, textAnnotatedStringElement.color) && Intrinsics.areEqual(this.text, textAnnotatedStringElement.text) && Intrinsics.areEqual(this.style, textAnnotatedStringElement.style) && Intrinsics.areEqual(this.placeholders, textAnnotatedStringElement.placeholders) && Intrinsics.areEqual(this.fontFamilyResolver, textAnnotatedStringElement.fontFamilyResolver) && this.onTextLayout == textAnnotatedStringElement.onTextLayout && this.onShowTranslation == textAnnotatedStringElement.onShowTranslation && TextOverflow.m647equalsimpl0(this.overflow, textAnnotatedStringElement.overflow) && this.softWrap == textAnnotatedStringElement.softWrap && this.maxLines == textAnnotatedStringElement.maxLines && this.minLines == textAnnotatedStringElement.minLines && this.onPlaceholderLayout == textAnnotatedStringElement.onPlaceholderLayout && Intrinsics.areEqual((Object) null, (Object) null);
    }

    public final int hashCode() {
        int hashCode = (this.fontFamilyResolver.hashCode() + SelectableTextAnnotatedStringElement$$ExternalSyntheticOutline0.m(this.text.hashCode() * 31, 31, this.style)) * 31;
        Function1 function1 = this.onTextLayout;
        int m = (((TransitionData$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.overflow, (hashCode + (function1 != null ? function1.hashCode() : 0)) * 31, 31), 31, this.softWrap) + this.maxLines) * 31) + this.minLines) * 31;
        List list = this.placeholders;
        int hashCode2 = (m + (list != null ? list.hashCode() : 0)) * 31;
        Function1 function12 = this.onPlaceholderLayout;
        int hashCode3 = (hashCode2 + (function12 != null ? function12.hashCode() : 0)) * 961;
        ColorProducer colorProducer = this.color;
        int hashCode4 = (hashCode3 + (colorProducer != null ? colorProducer.hashCode() : 0)) * 31;
        Function1 function13 = this.onShowTranslation;
        return hashCode4 + (function13 != null ? function13.hashCode() : 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x001c, code lost:
    
        if (r1.spanStyle.hasSameNonLayoutAttributes$ui_text_release(r0.spanStyle) != false) goto L10;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00b2  */
    @Override // androidx.compose.ui.node.ModifierNodeElement
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void update(androidx.compose.ui.Modifier.Node r13) {
        /*
            Method dump skipped, instructions count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.modifiers.TextAnnotatedStringElement.update(androidx.compose.ui.Modifier$Node):void");
    }
}
