package androidx.compose.foundation.text.modifiers;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.TextDelegateKt;
import androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Density;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextAnnotatedStringNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, SemanticsModifierNode {
    public MultiParagraphLayoutCache _layoutCache;
    public Map baselineCache;
    public FontFamily.Resolver fontFamilyResolver;
    public int maxLines;
    public int minLines;
    public Function1 onPlaceholderLayout;
    public Function1 onShowTranslation;
    public Function1 onTextLayout;
    public int overflow;
    public ColorProducer overrideColor;
    public List placeholders;
    public Function1 semanticsTextLayoutResult;
    public boolean softWrap;
    public TextStyle style;
    public AnnotatedString text;
    public TextSubstitutionValue textSubstitution;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TextSubstitutionValue {
        public boolean isShowingSubstitution = false;
        public MultiParagraphLayoutCache layoutCache = null;
        public final AnnotatedString original;
        public AnnotatedString substitution;

        public TextSubstitutionValue(AnnotatedString annotatedString, AnnotatedString annotatedString2) {
            this.original = annotatedString;
            this.substitution = annotatedString2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TextSubstitutionValue)) {
                return false;
            }
            TextSubstitutionValue textSubstitutionValue = (TextSubstitutionValue) obj;
            return Intrinsics.areEqual(this.original, textSubstitutionValue.original) && Intrinsics.areEqual(this.substitution, textSubstitutionValue.substitution) && this.isShowingSubstitution == textSubstitutionValue.isShowingSubstitution && Intrinsics.areEqual(this.layoutCache, textSubstitutionValue.layoutCache);
        }

        public final int hashCode() {
            int m = TransitionData$$ExternalSyntheticOutline0.m((this.substitution.hashCode() + (this.original.hashCode() * 31)) * 31, 31, this.isShowingSubstitution);
            MultiParagraphLayoutCache multiParagraphLayoutCache = this.layoutCache;
            return m + (multiParagraphLayoutCache == null ? 0 : multiParagraphLayoutCache.hashCode());
        }

        public final String toString() {
            return "TextSubstitutionValue(original=" + ((Object) this.original) + ", substitution=" + ((Object) this.substitution) + ", isShowingSubstitution=" + this.isShowingSubstitution + ", layoutCache=" + this.layoutCache + ')';
        }
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        Function1 function1 = this.semanticsTextLayoutResult;
        if (function1 == null) {
            function1 = new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode$applySemantics$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    TextLayoutResult textLayoutResult;
                    List list = (List) obj;
                    TextLayoutResult textLayoutResult2 = TextAnnotatedStringNode.this.getLayoutCache().layoutCache;
                    if (textLayoutResult2 != null) {
                        TextLayoutInput textLayoutInput = textLayoutResult2.layoutInput;
                        AnnotatedString annotatedString = textLayoutInput.text;
                        TextAnnotatedStringNode textAnnotatedStringNode = TextAnnotatedStringNode.this;
                        TextStyle textStyle = textAnnotatedStringNode.style;
                        ColorProducer colorProducer = textAnnotatedStringNode.overrideColor;
                        textLayoutResult = new TextLayoutResult(new TextLayoutInput(annotatedString, TextStyle.m605mergedA7vx0o$default(textStyle, colorProducer != null ? colorProducer.mo206invoke0d7_KjU() : Color.Unspecified, 0L, null, null, null, 0L, null, 0, 0L, 16777214), textLayoutInput.placeholders, textLayoutInput.maxLines, textLayoutInput.softWrap, textLayoutInput.overflow, textLayoutInput.density, textLayoutInput.layoutDirection, textLayoutInput.fontFamilyResolver, textLayoutInput.constraints), textLayoutResult2.multiParagraph, textLayoutResult2.size);
                        list.add(textLayoutResult);
                    } else {
                        textLayoutResult = null;
                    }
                    return Boolean.valueOf(textLayoutResult != null);
                }
            };
            this.semanticsTextLayoutResult = function1;
        }
        AnnotatedString annotatedString = this.text;
        KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
        SemanticsConfiguration semanticsConfiguration = (SemanticsConfiguration) semanticsPropertyReceiver;
        semanticsConfiguration.set(SemanticsProperties.Text, Collections.singletonList(annotatedString));
        TextSubstitutionValue textSubstitutionValue = this.textSubstitution;
        if (textSubstitutionValue != null) {
            AnnotatedString annotatedString2 = textSubstitutionValue.substitution;
            SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.TextSubstitution;
            KProperty[] kPropertyArr2 = SemanticsPropertiesKt.$$delegatedProperties;
            KProperty kProperty = kPropertyArr2[14];
            semanticsPropertyKey.setValue(semanticsPropertyReceiver, annotatedString2);
            boolean z = textSubstitutionValue.isShowingSubstitution;
            SemanticsPropertyKey semanticsPropertyKey2 = SemanticsProperties.IsShowingTextSubstitution;
            KProperty kProperty2 = kPropertyArr2[15];
            semanticsPropertyKey2.setValue(semanticsPropertyReceiver, Boolean.valueOf(z));
        }
        semanticsConfiguration.set(SemanticsActions.SetTextSubstitution, new AccessibilityAction(null, new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode$applySemantics$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                AnnotatedString annotatedString3 = (AnnotatedString) obj;
                TextAnnotatedStringNode textAnnotatedStringNode = TextAnnotatedStringNode.this;
                TextAnnotatedStringNode.TextSubstitutionValue textSubstitutionValue2 = textAnnotatedStringNode.textSubstitution;
                if (textSubstitutionValue2 == null) {
                    TextAnnotatedStringNode.TextSubstitutionValue textSubstitutionValue3 = new TextAnnotatedStringNode.TextSubstitutionValue(textAnnotatedStringNode.text, annotatedString3);
                    MultiParagraphLayoutCache multiParagraphLayoutCache = new MultiParagraphLayoutCache(annotatedString3, textAnnotatedStringNode.style, textAnnotatedStringNode.fontFamilyResolver, textAnnotatedStringNode.overflow, textAnnotatedStringNode.softWrap, textAnnotatedStringNode.maxLines, textAnnotatedStringNode.minLines, textAnnotatedStringNode.placeholders);
                    multiParagraphLayoutCache.setDensity$foundation_release(textAnnotatedStringNode.getLayoutCache().density);
                    textSubstitutionValue3.layoutCache = multiParagraphLayoutCache;
                    textAnnotatedStringNode.textSubstitution = textSubstitutionValue3;
                } else if (!Intrinsics.areEqual(annotatedString3, textSubstitutionValue2.substitution)) {
                    textSubstitutionValue2.substitution = annotatedString3;
                    MultiParagraphLayoutCache multiParagraphLayoutCache2 = textSubstitutionValue2.layoutCache;
                    if (multiParagraphLayoutCache2 != null) {
                        TextStyle textStyle = textAnnotatedStringNode.style;
                        FontFamily.Resolver resolver = textAnnotatedStringNode.fontFamilyResolver;
                        int i = textAnnotatedStringNode.overflow;
                        boolean z2 = textAnnotatedStringNode.softWrap;
                        int i2 = textAnnotatedStringNode.maxLines;
                        int i3 = textAnnotatedStringNode.minLines;
                        List list = textAnnotatedStringNode.placeholders;
                        multiParagraphLayoutCache2.text = annotatedString3;
                        multiParagraphLayoutCache2.style = textStyle;
                        multiParagraphLayoutCache2.fontFamilyResolver = resolver;
                        multiParagraphLayoutCache2.overflow = i;
                        multiParagraphLayoutCache2.softWrap = z2;
                        multiParagraphLayoutCache2.maxLines = i2;
                        multiParagraphLayoutCache2.minLines = i3;
                        multiParagraphLayoutCache2.placeholders = list;
                        multiParagraphLayoutCache2.paragraphIntrinsics = null;
                        multiParagraphLayoutCache2.layoutCache = null;
                        multiParagraphLayoutCache2.cachedIntrinsicHeight = -1;
                        multiParagraphLayoutCache2.cachedIntrinsicHeightInputWidth = -1;
                        Unit unit = Unit.INSTANCE;
                    }
                }
                TextAnnotatedStringNode textAnnotatedStringNode2 = TextAnnotatedStringNode.this;
                textAnnotatedStringNode2.getClass();
                SemanticsModifierNodeKt.invalidateSemantics(textAnnotatedStringNode2);
                LayoutModifierNodeKt.invalidateMeasurement(textAnnotatedStringNode2);
                DrawModifierNodeKt.invalidateDraw(textAnnotatedStringNode2);
                return Boolean.TRUE;
            }
        }));
        semanticsConfiguration.set(SemanticsActions.ShowTextSubstitution, new AccessibilityAction(null, new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode$applySemantics$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean booleanValue = ((Boolean) obj).booleanValue();
                TextAnnotatedStringNode textAnnotatedStringNode = TextAnnotatedStringNode.this;
                TextAnnotatedStringNode.TextSubstitutionValue textSubstitutionValue2 = textAnnotatedStringNode.textSubstitution;
                if (textSubstitutionValue2 == null) {
                    return Boolean.FALSE;
                }
                Function1 function12 = textAnnotatedStringNode.onShowTranslation;
                if (function12 != null) {
                    function12.invoke(textSubstitutionValue2);
                }
                TextAnnotatedStringNode textAnnotatedStringNode2 = TextAnnotatedStringNode.this;
                TextAnnotatedStringNode.TextSubstitutionValue textSubstitutionValue3 = textAnnotatedStringNode2.textSubstitution;
                if (textSubstitutionValue3 != null) {
                    textSubstitutionValue3.isShowingSubstitution = booleanValue;
                }
                SemanticsModifierNodeKt.invalidateSemantics(textAnnotatedStringNode2);
                LayoutModifierNodeKt.invalidateMeasurement(textAnnotatedStringNode2);
                DrawModifierNodeKt.invalidateDraw(textAnnotatedStringNode2);
                return Boolean.TRUE;
            }
        }));
        semanticsConfiguration.set(SemanticsActions.ClearTextSubstitution, new AccessibilityAction(null, new Function0() { // from class: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode$applySemantics$4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TextAnnotatedStringNode textAnnotatedStringNode = TextAnnotatedStringNode.this;
                textAnnotatedStringNode.textSubstitution = null;
                SemanticsModifierNodeKt.invalidateSemantics(textAnnotatedStringNode);
                LayoutModifierNodeKt.invalidateMeasurement(textAnnotatedStringNode);
                DrawModifierNodeKt.invalidateDraw(textAnnotatedStringNode);
                return Boolean.TRUE;
            }
        }));
        ((SemanticsConfiguration) semanticsPropertyReceiver).set(SemanticsActions.GetTextLayoutResult, new AccessibilityAction(null, function1));
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(LayoutNodeDrawScope layoutNodeDrawScope) {
        if (this.isAttached) {
            Canvas canvas = layoutNodeDrawScope.canvasDrawScope.drawContext.getCanvas();
            TextLayoutResult textLayoutResult = getLayoutCache(layoutNodeDrawScope).layoutCache;
            if (textLayoutResult == null) {
                throw new IllegalStateException("You must call layoutWithConstraints first");
            }
            long j = textLayoutResult.size;
            float f = (int) (j >> 32);
            MultiParagraph multiParagraph = textLayoutResult.multiParagraph;
            boolean z = true;
            boolean z2 = ((f > multiParagraph.width ? 1 : (f == multiParagraph.width ? 0 : -1)) < 0 || multiParagraph.didExceedMaxLines || (((float) ((int) (j & 4294967295L))) > multiParagraph.height ? 1 : (((float) ((int) (j & 4294967295L))) == multiParagraph.height ? 0 : -1)) < 0) && !TextOverflow.m647equalsimpl0(this.overflow, 3);
            if (z2) {
                Rect m324Recttz77jQw = RectKt.m324Recttz77jQw(0L, (Float.floatToRawIntBits((int) (j & 4294967295L)) & 4294967295L) | (Float.floatToRawIntBits((int) (j >> 32)) << 32));
                canvas.save();
                Canvas.m360clipRectmtrdDE$default(canvas, m324Recttz77jQw);
            }
            try {
                SpanStyle spanStyle = this.style.spanStyle;
                TextDecoration textDecoration = spanStyle.textDecoration;
                if (textDecoration == null) {
                    textDecoration = TextDecoration.None;
                }
                TextDecoration textDecoration2 = textDecoration;
                Shadow shadow = spanStyle.shadow;
                if (shadow == null) {
                    shadow = Shadow.None;
                }
                Shadow shadow2 = shadow;
                DrawStyle drawStyle = spanStyle.drawStyle;
                if (drawStyle == null) {
                    drawStyle = Fill.INSTANCE;
                }
                DrawStyle drawStyle2 = drawStyle;
                Brush brush = spanStyle.textForegroundStyle.getBrush();
                if (brush != null) {
                    MultiParagraph.m586painthn5TExg$default(multiParagraph, canvas, brush, this.style.spanStyle.textForegroundStyle.getAlpha(), shadow2, textDecoration2, drawStyle2);
                } else {
                    ColorProducer colorProducer = this.overrideColor;
                    long mo206invoke0d7_KjU = colorProducer != null ? colorProducer.mo206invoke0d7_KjU() : Color.Unspecified;
                    if (mo206invoke0d7_KjU == 16) {
                        mo206invoke0d7_KjU = this.style.m606getColor0d7_KjU() != 16 ? this.style.m606getColor0d7_KjU() : Color.Black;
                    }
                    MultiParagraph.m585paintLG529CI$default(multiParagraph, canvas, mo206invoke0d7_KjU, shadow2, textDecoration2, drawStyle2);
                }
                if (z2) {
                    canvas.restore();
                }
                TextSubstitutionValue textSubstitutionValue = this.textSubstitution;
                if (!((textSubstitutionValue == null || !textSubstitutionValue.isShowingSubstitution) ? TextAnnotatedStringNodeKt.hasLinks(this.text) : false)) {
                    List list = this.placeholders;
                    if (list != null && !list.isEmpty()) {
                        z = false;
                    }
                    if (z) {
                        return;
                    }
                }
                layoutNodeDrawScope.drawContent();
            } catch (Throwable th) {
                if (z2) {
                    canvas.restore();
                }
                throw th;
            }
        }
    }

    public final MultiParagraphLayoutCache getLayoutCache() {
        if (this._layoutCache == null) {
            this._layoutCache = new MultiParagraphLayoutCache(this.text, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, this.placeholders);
        }
        MultiParagraphLayoutCache multiParagraphLayoutCache = this._layoutCache;
        Intrinsics.checkNotNull(multiParagraphLayoutCache);
        return multiParagraphLayoutCache;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCache(lookaheadCapablePlaceable).intrinsicHeight(i, lookaheadCapablePlaceable.getLayoutDirection());
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return TextDelegateKt.ceilToIntPx(getLayoutCache(lookaheadCapablePlaceable).setLayoutDirection(lookaheadCapablePlaceable.getLayoutDirection()).getMaxIntrinsicWidth());
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00fb  */
    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.ui.layout.MeasureResult mo6measure3p2s80s(androidx.compose.ui.layout.MeasureScope r8, androidx.compose.ui.layout.Measurable r9, long r10) {
        /*
            Method dump skipped, instructions count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode.mo6measure3p2s80s(androidx.compose.ui.layout.MeasureScope, androidx.compose.ui.layout.Measurable, long):androidx.compose.ui.layout.MeasureResult");
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int minIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCache(lookaheadCapablePlaceable).intrinsicHeight(i, lookaheadCapablePlaceable.getLayoutDirection());
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int minIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return TextDelegateKt.ceilToIntPx(getLayoutCache(lookaheadCapablePlaceable).setLayoutDirection(lookaheadCapablePlaceable.getLayoutDirection()).getMinIntrinsicWidth());
    }

    public final MultiParagraphLayoutCache getLayoutCache(Density density) {
        MultiParagraphLayoutCache multiParagraphLayoutCache;
        TextSubstitutionValue textSubstitutionValue = this.textSubstitution;
        if (textSubstitutionValue != null && textSubstitutionValue.isShowingSubstitution && (multiParagraphLayoutCache = textSubstitutionValue.layoutCache) != null) {
            multiParagraphLayoutCache.setDensity$foundation_release(density);
            return multiParagraphLayoutCache;
        }
        MultiParagraphLayoutCache layoutCache = getLayoutCache();
        layoutCache.setDensity$foundation_release(density);
        return layoutCache;
    }
}
