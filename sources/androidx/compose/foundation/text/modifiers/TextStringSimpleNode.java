package androidx.compose.foundation.text.modifiers;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.TextDelegateKt;
import androidx.compose.foundation.text.modifiers.MinLinesConstrainer;
import androidx.compose.foundation.text.modifiers.TextStringSimpleNode;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.HorizontalAlignmentLine;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DelegatableNodeKt;
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
import androidx.compose.ui.text.AndroidParagraph;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextStringSimpleNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, SemanticsModifierNode {
    public ParagraphLayoutCache _layoutCache;
    public Map baselineCache;
    public FontFamily.Resolver fontFamilyResolver;
    public int maxLines;
    public int minLines;
    public int overflow;
    public ColorProducer overrideColor;
    public Function1 semanticsTextLayoutResult;
    public boolean softWrap;
    public TextStyle style;
    public String text;
    public TextSubstitutionValue textSubstitution;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TextSubstitutionValue {
        public boolean isShowingSubstitution = false;
        public ParagraphLayoutCache layoutCache = null;
        public final String original;
        public String substitution;

        public TextSubstitutionValue(String str, String str2) {
            this.original = str;
            this.substitution = str2;
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
            int m = TransitionData$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.substitution, this.original.hashCode() * 31, 31), 31, this.isShowingSubstitution);
            ParagraphLayoutCache paragraphLayoutCache = this.layoutCache;
            return m + (paragraphLayoutCache == null ? 0 : paragraphLayoutCache.hashCode());
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("TextSubstitution(layoutCache=");
            sb.append(this.layoutCache);
            sb.append(", isShowingSubstitution=");
            return ChangeSize$$ExternalSyntheticOutline0.m(sb, this.isShowingSubstitution, ')');
        }
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        Function1 function1 = this.semanticsTextLayoutResult;
        if (function1 == null) {
            function1 = new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$applySemantics$1
                {
                    super(1);
                }

                /* JADX WARN: Removed duplicated region for block: B:10:0x00a5  */
                /* JADX WARN: Removed duplicated region for block: B:14:0x00a7  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x009f  */
                @Override // kotlin.jvm.functions.Function1
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invoke(java.lang.Object r31) {
                    /*
                        r30 = this;
                        r0 = r30
                        r1 = r31
                        java.util.List r1 = (java.util.List) r1
                        androidx.compose.foundation.text.modifiers.TextStringSimpleNode r2 = androidx.compose.foundation.text.modifiers.TextStringSimpleNode.this
                        androidx.compose.foundation.text.modifiers.ParagraphLayoutCache r2 = r2.getLayoutCache()
                        androidx.compose.foundation.text.modifiers.TextStringSimpleNode r0 = androidx.compose.foundation.text.modifiers.TextStringSimpleNode.this
                        androidx.compose.ui.text.TextStyle r3 = r0.style
                        androidx.compose.ui.graphics.ColorProducer r0 = r0.overrideColor
                        if (r0 == 0) goto L19
                        long r4 = r0.mo206invoke0d7_KjU()
                        goto L1b
                    L19:
                        long r4 = androidx.compose.ui.graphics.Color.Unspecified
                    L1b:
                        r15 = 0
                        r17 = 16777214(0xfffffe, float:2.3509884E-38)
                        r6 = 0
                        r8 = 0
                        r9 = 0
                        r10 = 0
                        r11 = 0
                        r13 = 0
                        r14 = 0
                        androidx.compose.ui.text.TextStyle r0 = androidx.compose.ui.text.TextStyle.m605mergedA7vx0o$default(r3, r4, r6, r8, r9, r10, r11, r13, r14, r15, r17)
                        androidx.compose.ui.unit.LayoutDirection r3 = r2.intrinsicsLayoutDirection
                        r4 = 0
                        if (r3 != 0) goto L35
                    L32:
                        r7 = r4
                        goto L9d
                    L35:
                        androidx.compose.ui.unit.Density r5 = r2.density
                        if (r5 != 0) goto L3a
                        goto L32
                    L3a:
                        androidx.compose.ui.text.AnnotatedString r6 = new androidx.compose.ui.text.AnnotatedString
                        java.lang.String r7 = r2.text
                        r6.<init>(r7)
                        androidx.compose.ui.text.AndroidParagraph r7 = r2.paragraph
                        if (r7 != 0) goto L46
                        goto L32
                    L46:
                        androidx.compose.ui.text.ParagraphIntrinsics r7 = r2.paragraphIntrinsics
                        if (r7 != 0) goto L4b
                        goto L32
                    L4b:
                        long r7 = r2.prevConstraints
                        r9 = -8589934589(0xfffffffe00000003, double:NaN)
                        long r13 = r7 & r9
                        androidx.compose.ui.text.TextLayoutResult r7 = new androidx.compose.ui.text.TextLayoutResult
                        androidx.compose.ui.text.TextLayoutInput r8 = new androidx.compose.ui.text.TextLayoutInput
                        kotlin.collections.EmptyList r9 = kotlin.collections.EmptyList.INSTANCE
                        int r10 = r2.maxLines
                        boolean r11 = r2.softWrap
                        int r12 = r2.overflow
                        androidx.compose.ui.text.font.FontFamily$Resolver r15 = r2.fontFamilyResolver
                        r18 = r8
                        r19 = r6
                        r20 = r0
                        r21 = r9
                        r22 = r10
                        r23 = r11
                        r24 = r12
                        r25 = r5
                        r26 = r3
                        r27 = r15
                        r28 = r13
                        r18.<init>(r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
                        androidx.compose.ui.text.MultiParagraph r3 = new androidx.compose.ui.text.MultiParagraph
                        androidx.compose.ui.text.MultiParagraphIntrinsics r12 = new androidx.compose.ui.text.MultiParagraphIntrinsics
                        r18 = r12
                        r19 = r6
                        r20 = r0
                        r21 = r9
                        r22 = r5
                        r23 = r15
                        r18.<init>(r19, r20, r21, r22, r23)
                        int r15 = r2.maxLines
                        int r0 = r2.overflow
                        r11 = r3
                        r16 = r0
                        r11.<init>(r12, r13, r15, r16)
                        long r5 = r2.layoutSize
                        r7.<init>(r8, r3, r5)
                    L9d:
                        if (r7 == 0) goto La3
                        r1.add(r7)
                        r4 = r7
                    La3:
                        if (r4 == 0) goto La7
                        r0 = 1
                        goto La8
                    La7:
                        r0 = 0
                    La8:
                        java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$applySemantics$1.invoke(java.lang.Object):java.lang.Object");
                }
            };
            this.semanticsTextLayoutResult = function1;
        }
        AnnotatedString annotatedString = new AnnotatedString(this.text);
        KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
        SemanticsConfiguration semanticsConfiguration = (SemanticsConfiguration) semanticsPropertyReceiver;
        semanticsConfiguration.set(SemanticsProperties.Text, Collections.singletonList(annotatedString));
        TextSubstitutionValue textSubstitutionValue = this.textSubstitution;
        if (textSubstitutionValue != null) {
            boolean z = textSubstitutionValue.isShowingSubstitution;
            SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.IsShowingTextSubstitution;
            KProperty[] kPropertyArr2 = SemanticsPropertiesKt.$$delegatedProperties;
            KProperty kProperty = kPropertyArr2[15];
            semanticsPropertyKey.setValue(semanticsPropertyReceiver, Boolean.valueOf(z));
            AnnotatedString annotatedString2 = new AnnotatedString(textSubstitutionValue.substitution);
            SemanticsPropertyKey semanticsPropertyKey2 = SemanticsProperties.TextSubstitution;
            KProperty kProperty2 = kPropertyArr2[14];
            semanticsPropertyKey2.setValue(semanticsPropertyReceiver, annotatedString2);
        }
        semanticsConfiguration.set(SemanticsActions.SetTextSubstitution, new AccessibilityAction(null, new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$applySemantics$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                TextStringSimpleNode textStringSimpleNode = TextStringSimpleNode.this;
                String str = ((AnnotatedString) obj).text;
                TextStringSimpleNode.TextSubstitutionValue textSubstitutionValue2 = textStringSimpleNode.textSubstitution;
                if (textSubstitutionValue2 == null) {
                    TextStringSimpleNode.TextSubstitutionValue textSubstitutionValue3 = new TextStringSimpleNode.TextSubstitutionValue(textStringSimpleNode.text, str);
                    ParagraphLayoutCache paragraphLayoutCache = new ParagraphLayoutCache(str, textStringSimpleNode.style, textStringSimpleNode.fontFamilyResolver, textStringSimpleNode.overflow, textStringSimpleNode.softWrap, textStringSimpleNode.maxLines, textStringSimpleNode.minLines);
                    paragraphLayoutCache.setDensity$foundation_release(textStringSimpleNode.getLayoutCache().density);
                    textSubstitutionValue3.layoutCache = paragraphLayoutCache;
                    textStringSimpleNode.textSubstitution = textSubstitutionValue3;
                } else if (!Intrinsics.areEqual(str, textSubstitutionValue2.substitution)) {
                    textSubstitutionValue2.substitution = str;
                    ParagraphLayoutCache paragraphLayoutCache2 = textSubstitutionValue2.layoutCache;
                    if (paragraphLayoutCache2 != null) {
                        TextStyle textStyle = textStringSimpleNode.style;
                        FontFamily.Resolver resolver = textStringSimpleNode.fontFamilyResolver;
                        int i = textStringSimpleNode.overflow;
                        boolean z2 = textStringSimpleNode.softWrap;
                        int i2 = textStringSimpleNode.maxLines;
                        int i3 = textStringSimpleNode.minLines;
                        paragraphLayoutCache2.text = str;
                        paragraphLayoutCache2.style = textStyle;
                        paragraphLayoutCache2.fontFamilyResolver = resolver;
                        paragraphLayoutCache2.overflow = i;
                        paragraphLayoutCache2.softWrap = z2;
                        paragraphLayoutCache2.maxLines = i2;
                        paragraphLayoutCache2.minLines = i3;
                        paragraphLayoutCache2.markDirty();
                        Unit unit = Unit.INSTANCE;
                    }
                }
                TextStringSimpleNode textStringSimpleNode2 = TextStringSimpleNode.this;
                textStringSimpleNode2.getClass();
                SemanticsModifierNodeKt.invalidateSemantics(textStringSimpleNode2);
                LayoutModifierNodeKt.invalidateMeasurement(textStringSimpleNode2);
                DrawModifierNodeKt.invalidateDraw(textStringSimpleNode2);
                return Boolean.TRUE;
            }
        }));
        semanticsConfiguration.set(SemanticsActions.ShowTextSubstitution, new AccessibilityAction(null, new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$applySemantics$3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                boolean booleanValue = ((Boolean) obj).booleanValue();
                TextStringSimpleNode textStringSimpleNode = TextStringSimpleNode.this;
                TextStringSimpleNode.TextSubstitutionValue textSubstitutionValue2 = textStringSimpleNode.textSubstitution;
                if (textSubstitutionValue2 == null) {
                    return Boolean.FALSE;
                }
                textSubstitutionValue2.isShowingSubstitution = booleanValue;
                SemanticsModifierNodeKt.invalidateSemantics(textStringSimpleNode);
                LayoutModifierNodeKt.invalidateMeasurement(textStringSimpleNode);
                DrawModifierNodeKt.invalidateDraw(textStringSimpleNode);
                return Boolean.TRUE;
            }
        }));
        semanticsConfiguration.set(SemanticsActions.ClearTextSubstitution, new AccessibilityAction(null, new Function0() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$applySemantics$4
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                TextStringSimpleNode textStringSimpleNode = TextStringSimpleNode.this;
                textStringSimpleNode.textSubstitution = null;
                SemanticsModifierNodeKt.invalidateSemantics(textStringSimpleNode);
                LayoutModifierNodeKt.invalidateMeasurement(textStringSimpleNode);
                DrawModifierNodeKt.invalidateDraw(textStringSimpleNode);
                return Boolean.TRUE;
            }
        }));
        ((SemanticsConfiguration) semanticsPropertyReceiver).set(SemanticsActions.GetTextLayoutResult, new AccessibilityAction(null, function1));
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(LayoutNodeDrawScope layoutNodeDrawScope) {
        if (this.isAttached) {
            ParagraphLayoutCache layoutCache = getLayoutCache(layoutNodeDrawScope);
            AndroidParagraph androidParagraph = layoutCache.paragraph;
            if (androidParagraph == null) {
                InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("no paragraph (layoutCache=" + this._layoutCache + ", textSubstitution=" + this.textSubstitution + ')');
                throw null;
            }
            Canvas canvas = layoutNodeDrawScope.canvasDrawScope.drawContext.getCanvas();
            boolean z = layoutCache.didOverflow;
            if (z) {
                long j = layoutCache.layoutSize;
                canvas.save();
                canvas.mo335clipRectN_I0leg(0.0f, 0.0f, (int) (j >> 32), (int) (j & 4294967295L), 1);
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
                    androidParagraph.m584painthn5TExg(canvas, brush, this.style.spanStyle.textForegroundStyle.getAlpha(), shadow2, textDecoration2, drawStyle2);
                } else {
                    ColorProducer colorProducer = this.overrideColor;
                    long mo206invoke0d7_KjU = colorProducer != null ? colorProducer.mo206invoke0d7_KjU() : Color.Unspecified;
                    if (mo206invoke0d7_KjU == 16) {
                        mo206invoke0d7_KjU = this.style.m606getColor0d7_KjU() != 16 ? this.style.m606getColor0d7_KjU() : Color.Black;
                    }
                    androidParagraph.m583paintLG529CI(canvas, mo206invoke0d7_KjU, shadow2, textDecoration2, drawStyle2);
                }
                if (z) {
                    canvas.restore();
                }
            } catch (Throwable th) {
                if (z) {
                    canvas.restore();
                }
                throw th;
            }
        }
    }

    public final ParagraphLayoutCache getLayoutCache() {
        if (this._layoutCache == null) {
            this._layoutCache = new ParagraphLayoutCache(this.text, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines);
        }
        ParagraphLayoutCache paragraphLayoutCache = this._layoutCache;
        Intrinsics.checkNotNull(paragraphLayoutCache);
        return paragraphLayoutCache;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCache(lookaheadCapablePlaceable).intrinsicHeight(i, lookaheadCapablePlaceable.getLayoutDirection());
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return TextDelegateKt.ceilToIntPx(getLayoutCache(lookaheadCapablePlaceable).setLayoutDirection(lookaheadCapablePlaceable.getLayoutDirection()).getMaxIntrinsicWidth());
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        long j2;
        int i;
        boolean z;
        ParagraphIntrinsics paragraphIntrinsics;
        ParagraphLayoutCache layoutCache = getLayoutCache(measureScope);
        LayoutDirection layoutDirection = measureScope.getLayoutDirection();
        if (layoutCache.minLines > 1) {
            TextStyle textStyle = layoutCache.style;
            MinLinesConstrainer minLinesConstrainer = layoutCache.mMinLinesConstrainer;
            Density density = layoutCache.density;
            Intrinsics.checkNotNull(density);
            MinLinesConstrainer from = MinLinesConstrainer.Companion.from(minLinesConstrainer, layoutDirection, textStyle, density, layoutCache.fontFamilyResolver);
            layoutCache.mMinLinesConstrainer = from;
            j2 = from.m178coerceMinLinesOh53vG4$foundation_release(j, layoutCache.minLines);
        } else {
            j2 = j;
        }
        AndroidParagraph androidParagraph = layoutCache.paragraph;
        if (androidParagraph == null || (paragraphIntrinsics = layoutCache.paragraphIntrinsics) == null || paragraphIntrinsics.getHasStaleResolvedFonts() || layoutDirection != layoutCache.intrinsicsLayoutDirection || (!Constraints.m649equalsimpl0(j2, layoutCache.prevConstraints) && (Constraints.m655getMaxWidthimpl(j2) != Constraints.m655getMaxWidthimpl(layoutCache.prevConstraints) || Constraints.m654getMaxHeightimpl(j2) < androidParagraph.getHeight() || androidParagraph.layout.didExceedMaxLines))) {
            ParagraphIntrinsics layoutDirection2 = layoutCache.setLayoutDirection(layoutDirection);
            long m177finalConstraintstfFHcEY = LayoutUtilsKt.m177finalConstraintstfFHcEY(j2, layoutCache.softWrap, layoutCache.overflow, layoutDirection2.getMaxIntrinsicWidth());
            boolean z2 = layoutCache.softWrap;
            int i2 = layoutCache.overflow;
            int i3 = layoutCache.maxLines;
            if (z2 || !(TextOverflow.m647equalsimpl0(i2, 2) || TextOverflow.m647equalsimpl0(i2, 4) || TextOverflow.m647equalsimpl0(i2, 5))) {
                if (i3 < 1) {
                    i3 = 1;
                }
                i = i3;
            } else {
                i = 1;
            }
            AndroidParagraph androidParagraph2 = new AndroidParagraph((AndroidParagraphIntrinsics) layoutDirection2, i, layoutCache.overflow, m177finalConstraintstfFHcEY);
            layoutCache.prevConstraints = j2;
            long m662constrain4WqzIAM = ConstraintsKt.m662constrain4WqzIAM(j2, (TextDelegateKt.ceilToIntPx(androidParagraph2.getHeight()) & 4294967295L) | (TextDelegateKt.ceilToIntPx(androidParagraph2.getWidth()) << 32));
            layoutCache.layoutSize = m662constrain4WqzIAM;
            layoutCache.didOverflow = !TextOverflow.m647equalsimpl0(layoutCache.overflow, 3) && (((float) ((int) (m662constrain4WqzIAM >> 32))) < androidParagraph2.getWidth() || ((float) ((int) (m662constrain4WqzIAM & 4294967295L))) < androidParagraph2.getHeight());
            layoutCache.paragraph = androidParagraph2;
            z = true;
        } else {
            if (!Constraints.m649equalsimpl0(j2, layoutCache.prevConstraints)) {
                AndroidParagraph androidParagraph3 = layoutCache.paragraph;
                Intrinsics.checkNotNull(androidParagraph3);
                long m662constrain4WqzIAM2 = ConstraintsKt.m662constrain4WqzIAM(j2, (TextDelegateKt.ceilToIntPx(androidParagraph3.getHeight()) & 4294967295L) | (TextDelegateKt.ceilToIntPx(Math.min(androidParagraph3.paragraphIntrinsics.layoutIntrinsics.getMaxIntrinsicWidth(), androidParagraph3.getWidth())) << 32));
                layoutCache.layoutSize = m662constrain4WqzIAM2;
                layoutCache.didOverflow = !TextOverflow.m647equalsimpl0(layoutCache.overflow, 3) && (((float) ((int) (m662constrain4WqzIAM2 >> 32))) < androidParagraph3.getWidth() || ((float) ((int) (m662constrain4WqzIAM2 & 4294967295L))) < androidParagraph3.getHeight());
                layoutCache.prevConstraints = j2;
            }
            z = false;
        }
        ParagraphIntrinsics paragraphIntrinsics2 = layoutCache.paragraphIntrinsics;
        if (paragraphIntrinsics2 != null) {
            paragraphIntrinsics2.getHasStaleResolvedFonts();
        }
        AndroidParagraph androidParagraph4 = layoutCache.paragraph;
        Intrinsics.checkNotNull(androidParagraph4);
        long j3 = layoutCache.layoutSize;
        if (z) {
            DelegatableNodeKt.m503requireCoordinator64DMado(this, 2).invalidateLayer();
            Map map = this.baselineCache;
            if (map == null) {
                map = new HashMap(2);
                this.baselineCache = map;
            }
            HorizontalAlignmentLine horizontalAlignmentLine = AlignmentLineKt.FirstBaseline;
            TextLayout textLayout = androidParagraph4.layout;
            map.put(horizontalAlignmentLine, Integer.valueOf(Math.round(textLayout.getLineBaseline(0))));
            map.put(AlignmentLineKt.LastBaseline, Integer.valueOf(Math.round(textLayout.getLineBaseline(textLayout.lineCount - 1))));
        }
        int i4 = (int) (j3 >> 32);
        int i5 = (int) (j3 & 4294967295L);
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(Constraints.Companion.m660fitPrioritizingWidthZbe2FdA(i4, i4, i5, i5));
        Map map2 = this.baselineCache;
        Intrinsics.checkNotNull(map2);
        return measureScope.layout$1(i4, i5, map2, new Function1() { // from class: androidx.compose.foundation.text.modifiers.TextStringSimpleNode$measure$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Placeable.PlacementScope) obj).place(Placeable.this, 0, 0, 0.0f);
                return Unit.INSTANCE;
            }
        });
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int minIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCache(lookaheadCapablePlaceable).intrinsicHeight(i, lookaheadCapablePlaceable.getLayoutDirection());
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int minIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return TextDelegateKt.ceilToIntPx(getLayoutCache(lookaheadCapablePlaceable).setLayoutDirection(lookaheadCapablePlaceable.getLayoutDirection()).getMinIntrinsicWidth());
    }

    public final ParagraphLayoutCache getLayoutCache(Density density) {
        ParagraphLayoutCache paragraphLayoutCache;
        TextSubstitutionValue textSubstitutionValue = this.textSubstitution;
        if (textSubstitutionValue != null && textSubstitutionValue.isShowingSubstitution && (paragraphLayoutCache = textSubstitutionValue.layoutCache) != null) {
            paragraphLayoutCache.setDensity$foundation_release(density);
            return paragraphLayoutCache;
        }
        ParagraphLayoutCache layoutCache = getLayoutCache();
        layoutCache.setDensity$foundation_release(density);
        return layoutCache;
    }
}
