package androidx.compose.foundation.text;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.HoverableElement;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.input.pointer.PointerHoverIconModifierElement;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerIcon_androidKt;
import androidx.compose.ui.platform.AndroidUriHandler;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.UriHandler;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.LinkAnnotation;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextLinkStyles;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Arrays;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.SpreadBuilder;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextLinkScope {
    public AnnotatedString text;
    public final MutableState textLayoutResult$delegate = SnapshotStateKt.mutableStateOf$default(null);
    public final SnapshotStateList annotators = new SnapshotStateList();

    public TextLinkScope(AnnotatedString annotatedString) {
        this.text = annotatedString.flatMapAnnotations(new Function1() { // from class: androidx.compose.foundation.text.TextLinkScope.1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                TextLinkStyles styles;
                SpanStyle spanStyle;
                AnnotatedString.Range range = (AnnotatedString.Range) obj;
                Object obj2 = range.item;
                if (!(obj2 instanceof LinkAnnotation) || (styles = ((LinkAnnotation) obj2).getStyles()) == null || (styles.style == null && styles.focusedStyle == null && styles.hoveredStyle == null && styles.pressedStyle == null)) {
                    return CollectionsKt__CollectionsKt.arrayListOf(range);
                }
                TextLinkStyles styles2 = ((LinkAnnotation) range.item).getStyles();
                if (styles2 == null || (spanStyle = styles2.style) == null) {
                    spanStyle = new SpanStyle(0L, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, 65535);
                }
                return CollectionsKt__CollectionsKt.arrayListOf(range, new AnnotatedString.Range(range.start, range.end, spanStyle));
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r30v0, types: [androidx.compose.foundation.text.TextLinkScope, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v1, types: [androidx.compose.runtime.Composer, androidx.compose.runtime.ComposerImpl] */
    /* JADX WARN: Type inference failed for: r5v32, types: [androidx.compose.ui.Modifier] */
    public final void LinksComposables(final int i, Composer composer) {
        final AndroidPath androidPath;
        Modifier then;
        Modifier then2;
        Modifier m33combinedClickableauXiCPI;
        char c;
        SpanStyle spanStyle;
        ?? clip;
        TextLayoutResult textLayoutResult;
        ?? r3 = (ComposerImpl) composer;
        r3.startRestartGroup(1154651354);
        int i2 = (i & 6) == 0 ? (r3.changedInstance(this) ? 4 : 2) | i : i;
        if ((i2 & 3) == 2 && r3.getSkipping()) {
            r3.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            final UriHandler uriHandler = (UriHandler) r3.consume(CompositionLocalsKt.LocalUriHandler);
            AnnotatedString annotatedString = this.text;
            List linkAnnotations = annotatedString.getLinkAnnotations(annotatedString.text.length());
            int size = linkAnnotations.size();
            for (int i3 = 0; i3 < size; i3++) {
                final AnnotatedString.Range range = (AnnotatedString.Range) linkAnnotations.get(i3);
                if (((Boolean) new TextLinkScope$shouldMeasureLinks$1(this).invoke()).booleanValue() && (textLayoutResult = (TextLayoutResult) ((SnapshotMutableStateImpl) this.textLayoutResult$delegate).getValue()) != null) {
                    int i4 = range.start;
                    int i5 = range.end;
                    androidPath = textLayoutResult.getPathForRange(i4, i5);
                    int i6 = range.start;
                    Rect boundingBox = textLayoutResult.getBoundingBox(i6);
                    androidPath.m355translatek4lQ0M(((Float.floatToRawIntBits(textLayoutResult.getLineForOffset(i6) == textLayoutResult.getLineForOffset(i5) ? Math.min(textLayoutResult.getBoundingBox(i5 - 1).left, boundingBox.left) : 0.0f) << 32) | (Float.floatToRawIntBits(boundingBox.top) & 4294967295L)) ^ (-9223372034707292160L));
                } else {
                    androidPath = null;
                }
                Shape shape = androidPath != null ? new Shape() { // from class: androidx.compose.foundation.text.TextLinkScope$shapeForRange$1$1
                    @Override // androidx.compose.ui.graphics.Shape
                    /* renamed from: createOutline-Pq9zytI */
                    public final Outline mo37createOutlinePq9zytI(long j, LayoutDirection layoutDirection, Density density) {
                        return new Outline.Generic(AndroidPath.this);
                    }
                } : null;
                Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                if (shape != null && (clip = ClipKt.clip(companion, shape)) != 0) {
                    companion = clip;
                }
                Object rememberedValue = r3.rememberedValue();
                Object obj = Composer.Companion.Empty;
                if (rememberedValue == obj) {
                    rememberedValue = InteractionSourceKt.MutableInteractionSource();
                    r3.updateRememberedValue(rememberedValue);
                }
                MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) rememberedValue;
                then = SemanticsModifierKt.semantics(companion, false, new Function1() { // from class: androidx.compose.foundation.text.TextLinkScope$LinksComposables$1$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.LinkTestMarker;
                        Unit unit = Unit.INSTANCE;
                        ((SemanticsConfiguration) ((SemanticsPropertyReceiver) obj2)).set(semanticsPropertyKey, unit);
                        return unit;
                    }
                }).then(new TextRangeLayoutModifier(new TextLinkScope$$ExternalSyntheticLambda0(this, range.start, range.end))).then(new HoverableElement(mutableInteractionSource));
                PointerIcon.Companion.getClass();
                then2 = then.then(new PointerHoverIconModifierElement(PointerIcon_androidKt.pointerIconHand));
                boolean changedInstance = r3.changedInstance(this) | r3.changed(range) | r3.changedInstance(uriHandler);
                Object rememberedValue2 = r3.rememberedValue();
                if (changedInstance || rememberedValue2 == obj) {
                    rememberedValue2 = new Function0() { // from class: androidx.compose.foundation.text.TextLinkScope$LinksComposables$1$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            TextLinkScope textLinkScope = TextLinkScope.this;
                            LinkAnnotation linkAnnotation = (LinkAnnotation) range.item;
                            UriHandler uriHandler2 = uriHandler;
                            textLinkScope.getClass();
                            if (linkAnnotation instanceof LinkAnnotation.Url) {
                                linkAnnotation.getClass();
                                try {
                                    ((AndroidUriHandler) uriHandler2).openUri(((LinkAnnotation.Url) linkAnnotation).url);
                                } catch (IllegalArgumentException unused) {
                                }
                            } else if (linkAnnotation instanceof LinkAnnotation.Clickable) {
                                linkAnnotation.getClass();
                            }
                            return Unit.INSTANCE;
                        }
                    };
                    r3.updateRememberedValue(rememberedValue2);
                }
                m33combinedClickableauXiCPI = ClickableKt.m33combinedClickableauXiCPI(then2, mutableInteractionSource, null, true, null, null, null, (r17 & 64) != 0 ? null : null, null, true, (Function0) rememberedValue2);
                BoxKt.Box(m33combinedClickableauXiCPI, r3, 0);
                r3.startReplaceGroup(680162897);
                LinkAnnotation linkAnnotation = (LinkAnnotation) range.item;
                TextLinkStyles styles = linkAnnotation.getStyles();
                if (styles == null || (styles.style == null && styles.focusedStyle == null && styles.hoveredStyle == null && styles.pressedStyle == null)) {
                    c = 2;
                } else {
                    Object rememberedValue3 = r3.rememberedValue();
                    if (rememberedValue3 == obj) {
                        rememberedValue3 = new LinkStateInteractionSourceObserver(mutableInteractionSource);
                        r3.updateRememberedValue(rememberedValue3);
                    }
                    final LinkStateInteractionSourceObserver linkStateInteractionSourceObserver = (LinkStateInteractionSourceObserver) rememberedValue3;
                    Unit unit = Unit.INSTANCE;
                    Object rememberedValue4 = r3.rememberedValue();
                    if (rememberedValue4 == obj) {
                        spanStyle = null;
                        rememberedValue4 = new TextLinkScope$LinksComposables$1$3$1(linkStateInteractionSourceObserver, null);
                        r3.updateRememberedValue(rememberedValue4);
                    } else {
                        spanStyle = null;
                    }
                    EffectsKt.LaunchedEffect((Composer) r3, unit, (Function2) rememberedValue4);
                    c = 2;
                    Boolean valueOf = Boolean.valueOf((((SnapshotMutableIntStateImpl) linkStateInteractionSourceObserver.interactionState).getIntValue() & 2) != 0);
                    MutableIntState mutableIntState = linkStateInteractionSourceObserver.interactionState;
                    Boolean valueOf2 = Boolean.valueOf((((SnapshotMutableIntStateImpl) mutableIntState).getIntValue() & 1) != 0);
                    Boolean valueOf3 = Boolean.valueOf((((SnapshotMutableIntStateImpl) mutableIntState).getIntValue() & 4) != 0);
                    TextLinkStyles styles2 = linkAnnotation.getStyles();
                    SpanStyle spanStyle2 = styles2 != null ? styles2.style : spanStyle;
                    TextLinkStyles styles3 = linkAnnotation.getStyles();
                    SpanStyle spanStyle3 = styles3 != null ? styles3.focusedStyle : spanStyle;
                    TextLinkStyles styles4 = linkAnnotation.getStyles();
                    SpanStyle spanStyle4 = styles4 != null ? styles4.hoveredStyle : spanStyle;
                    TextLinkStyles styles5 = linkAnnotation.getStyles();
                    if (styles5 != null) {
                        spanStyle = styles5.pressedStyle;
                    }
                    Object[] objArr = {valueOf, valueOf2, valueOf3, spanStyle2, spanStyle3, spanStyle4, spanStyle};
                    boolean changedInstance2 = r3.changedInstance(this) | r3.changed(range);
                    Object rememberedValue5 = r3.rememberedValue();
                    if (changedInstance2 || rememberedValue5 == obj) {
                        rememberedValue5 = new Function1() { // from class: androidx.compose.foundation.text.TextLinkScope$LinksComposables$1$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                TextLinkStyles styles6;
                                TextLinkStyles styles7;
                                TextLinkStyles styles8;
                                TextAnnotatorScope textAnnotatorScope = (TextAnnotatorScope) obj2;
                                TextLinkScope textLinkScope = TextLinkScope.this;
                                TextLinkStyles styles9 = ((LinkAnnotation) range.item).getStyles();
                                final SpanStyle spanStyle5 = null;
                                SpanStyle spanStyle6 = styles9 != null ? styles9.style : null;
                                SpanStyle spanStyle7 = ((((SnapshotMutableIntStateImpl) linkStateInteractionSourceObserver.interactionState).getIntValue() & 1) == 0 || (styles8 = ((LinkAnnotation) range.item).getStyles()) == null) ? null : styles8.focusedStyle;
                                textLinkScope.getClass();
                                if (spanStyle6 != null) {
                                    spanStyle7 = spanStyle6.merge(spanStyle7);
                                }
                                SpanStyle spanStyle8 = ((((SnapshotMutableIntStateImpl) linkStateInteractionSourceObserver.interactionState).getIntValue() & 2) == 0 || (styles7 = ((LinkAnnotation) range.item).getStyles()) == null) ? null : styles7.hoveredStyle;
                                if (spanStyle7 != null) {
                                    spanStyle8 = spanStyle7.merge(spanStyle8);
                                }
                                if ((((SnapshotMutableIntStateImpl) linkStateInteractionSourceObserver.interactionState).getIntValue() & 4) != 0 && (styles6 = ((LinkAnnotation) range.item).getStyles()) != null) {
                                    spanStyle5 = styles6.pressedStyle;
                                }
                                if (spanStyle8 != null) {
                                    spanStyle5 = spanStyle8.merge(spanStyle5);
                                }
                                final AnnotatedString.Range range2 = range;
                                textAnnotatorScope.getClass();
                                final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
                                textAnnotatorScope.styledText = textAnnotatorScope.initialText.mapAnnotations(new Function1() { // from class: androidx.compose.foundation.text.TextAnnotatorScope$replaceStyle$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj3) {
                                        AnnotatedString.Range range3;
                                        AnnotatedString.Range range4 = (AnnotatedString.Range) obj3;
                                        if (Ref$BooleanRef.this.element && (range4.item instanceof SpanStyle)) {
                                            AnnotatedString.Range range5 = range2;
                                            int i7 = range5.start;
                                            int i8 = range4.start;
                                            if (i8 == i7) {
                                                int i9 = range5.end;
                                                int i10 = range4.end;
                                                if (i10 == i9) {
                                                    SpanStyle spanStyle9 = spanStyle5;
                                                    if (spanStyle9 == null) {
                                                        spanStyle9 = new SpanStyle(0L, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, 65535);
                                                    }
                                                    range3 = new AnnotatedString.Range(i8, i10, spanStyle9);
                                                    Ref$BooleanRef.this.element = Intrinsics.areEqual(range2, range4);
                                                    return range3;
                                                }
                                            }
                                        }
                                        range3 = range4;
                                        Ref$BooleanRef.this.element = Intrinsics.areEqual(range2, range4);
                                        return range3;
                                    }
                                });
                                return Unit.INSTANCE;
                            }
                        };
                        r3.updateRememberedValue(rememberedValue5);
                    }
                    StyleAnnotation(objArr, (Function1) rememberedValue5, r3, (i2 << 6) & 896);
                }
                r3.end(false);
            }
            OpaqueKey opaqueKey2 = ComposerKt.invocation;
        }
        RecomposeScopeImpl endRestartGroup = r3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.foundation.text.TextLinkScope$LinksComposables$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    TextLinkScope.this.LinksComposables(RecomposeScopeImplKt.updateChangedFlags(i | 1), (Composer) obj2);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public final void StyleAnnotation(final Object[] objArr, final Function1 function1, Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-2083052099);
        int i2 = (i & 48) == 0 ? (composerImpl.changedInstance(function1) ? 32 : 16) | i : i;
        if ((i & 384) == 0) {
            i2 |= composerImpl.changedInstance(this) ? 256 : 128;
        }
        composerImpl.startMovableGroup(-416667799, Integer.valueOf(objArr.length));
        for (Object obj : objArr) {
            i2 |= composerImpl.changedInstance(obj) ? 4 : 0;
        }
        composerImpl.end(false);
        if ((i2 & 14) == 0) {
            i2 |= 2;
        }
        if ((i2 & 147) == 146 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add(function1);
            spreadBuilder.addSpread(objArr);
            Object[] array = spreadBuilder.list.toArray(new Object[spreadBuilder.list.size()]);
            boolean changedInstance = ((i2 & 112) == 32) | composerImpl.changedInstance(this);
            Object rememberedValue = composerImpl.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.Empty) {
                rememberedValue = new Function1() { // from class: androidx.compose.foundation.text.TextLinkScope$StyleAnnotation$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        TextLinkScope.this.annotators.add(function1);
                        final TextLinkScope textLinkScope = TextLinkScope.this;
                        final Function1 function12 = function1;
                        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.TextLinkScope$StyleAnnotation$1$1$invoke$$inlined$onDispose$1
                            @Override // androidx.compose.runtime.DisposableEffectResult
                            public final void dispose() {
                                TextLinkScope.this.annotators.remove(function12);
                            }
                        };
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue);
            }
            EffectsKt.DisposableEffect(array, (Function1) rememberedValue, (Composer) composerImpl);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.foundation.text.TextLinkScope$StyleAnnotation$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    ((Number) obj3).intValue();
                    TextLinkScope textLinkScope = TextLinkScope.this;
                    Object[] objArr2 = objArr;
                    textLinkScope.StyleAnnotation(Arrays.copyOf(objArr2, objArr2.length), function1, (Composer) obj2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
