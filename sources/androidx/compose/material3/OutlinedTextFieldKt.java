package androidx.compose.material3;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.selection.SimpleLayoutKt$$ExternalSyntheticOutline0;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class OutlinedTextFieldKt {
    public static final float OutlinedTextFieldInnerPadding = 4;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r0v27 */
    public static final void OutlinedTextFieldLayout(final Modifier modifier, final Function2 function2, final Function3 function3, final Function2 function22, final Function2 function23, final Function2 function24, final Function2 function25, final Function2 function26, final boolean z, final TextFieldLabelPosition textFieldLabelPosition, final float f, final Function1 function1, final Function2 function27, final Function2 function28, final PaddingValues paddingValues, Composer composer, final int i, final int i2) {
        int i3;
        int i4;
        int i5;
        PaddingValues paddingValues2;
        Function2 function29;
        ComposerImpl composerImpl;
        LayoutDirection layoutDirection;
        float f2;
        float f3;
        boolean z2;
        ComposerImpl composerImpl2;
        ComposerImpl composerImpl3 = (ComposerImpl) composer;
        composerImpl3.startRestartGroup(-1532419413);
        if ((i & 6) == 0) {
            i3 = (composerImpl3.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerImpl3.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerImpl3.changedInstance(function3) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerImpl3.changedInstance(function22) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i3 |= composerImpl3.changedInstance(function23) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i3 |= composerImpl3.changedInstance(function24) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i3 |= composerImpl3.changedInstance(function25) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i3 |= composerImpl3.changedInstance(function26) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            i3 |= composerImpl3.changed(z) ? 67108864 : 33554432;
        }
        if ((i & 805306368) == 0) {
            i3 |= composerImpl3.changed(textFieldLabelPosition) ? 536870912 : 268435456;
        }
        int i6 = i3;
        if ((i2 & 6) == 0) {
            i4 = (composerImpl3.changed(f) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            i4 |= composerImpl3.changedInstance(function1) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i4 |= composerImpl3.changedInstance(function27) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i4 |= composerImpl3.changedInstance(function28) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i4 |= composerImpl3.changed(paddingValues) ? 16384 : 8192;
        }
        if ((i6 & 306783379) == 306783378 && (i4 & 9363) == 9362 && composerImpl3.getSkipping()) {
            composerImpl3.skipToGroupEnd();
            composerImpl2 = composerImpl3;
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            float textFieldHorizontalIconPadding = TextFieldImplKt.textFieldHorizontalIconPadding(composerImpl3);
            boolean changed = ((i4 & 112) == 32) | ((i6 & 234881024) == 67108864) | ((i6 & 1879048192) == 536870912) | ((i4 & 14) == 4) | ((57344 & i4) == 16384) | composerImpl3.changed(textFieldHorizontalIconPadding);
            Object rememberedValue = composerImpl3.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.Empty) {
                i5 = i4;
                ComposerImpl composerImpl4 = composerImpl3;
                paddingValues2 = paddingValues;
                function29 = function27;
                OutlinedTextFieldMeasurePolicy outlinedTextFieldMeasurePolicy = new OutlinedTextFieldMeasurePolicy(function1, z, textFieldLabelPosition, f, paddingValues, textFieldHorizontalIconPadding);
                composerImpl4.updateRememberedValue(outlinedTextFieldMeasurePolicy);
                rememberedValue = outlinedTextFieldMeasurePolicy;
                composerImpl = composerImpl4;
            } else {
                paddingValues2 = paddingValues;
                composerImpl = composerImpl3;
                i5 = i4;
                function29 = function27;
            }
            OutlinedTextFieldMeasurePolicy outlinedTextFieldMeasurePolicy2 = (OutlinedTextFieldMeasurePolicy) rememberedValue;
            LayoutDirection layoutDirection2 = (LayoutDirection) composerImpl.consume(CompositionLocalsKt.LocalLayoutDirection);
            int i7 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, modifier);
            ComposeUiNode.Companion.getClass();
            Function0 function0 = ComposeUiNode.Companion.Constructor;
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function0);
            } else {
                composerImpl.useNode();
            }
            Function2 function210 = ComposeUiNode.Companion.SetMeasurePolicy;
            Updater.m259setimpl(composerImpl, outlinedTextFieldMeasurePolicy2, function210);
            Function2 function211 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope, function211);
            Function2 function212 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i7))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i7, composerImpl, i7, function212);
            }
            Function2 function213 = ComposeUiNode.Companion.SetModifier;
            Updater.m259setimpl(composerImpl, materializeModifier, function213);
            function29.invoke(composerImpl, Integer.valueOf((i5 >> 6) & 14));
            composerImpl.startReplaceGroup(250744357);
            BiasAlignment biasAlignment = Alignment.Companion.Center;
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            if (function23 != null) {
                Modifier then = LayoutIdKt.layoutId(companion, "Leading").then(MinimumInteractiveModifier.INSTANCE);
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
                int i8 = composerImpl.compoundKeyHash;
                f2 = textFieldHorizontalIconPadding;
                PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, then);
                composerImpl.startReusableNode();
                layoutDirection = layoutDirection2;
                if (composerImpl.inserting) {
                    composerImpl.createNode(function0);
                } else {
                    composerImpl.useNode();
                }
                Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, function210);
                Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function211);
                if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i8))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i8, composerImpl, i8, function212);
                }
                Updater.m259setimpl(composerImpl, materializeModifier2, function213);
                SimpleLayoutKt$$ExternalSyntheticOutline0.m((i6 >> 12) & 14, function23, composerImpl, true);
            } else {
                layoutDirection = layoutDirection2;
                f2 = textFieldHorizontalIconPadding;
            }
            ?? r0 = 0;
            composerImpl.end(false);
            composerImpl.startReplaceGroup(250753608);
            if (function24 != null) {
                Modifier then2 = LayoutIdKt.layoutId(companion, "Trailing").then(MinimumInteractiveModifier.INSTANCE);
                MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
                int i9 = composerImpl.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope3 = composerImpl.currentCompositionLocalScope();
                Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composerImpl, then2);
                composerImpl.startReusableNode();
                if (composerImpl.inserting) {
                    composerImpl.createNode(function0);
                } else {
                    composerImpl.useNode();
                }
                Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy2, function210);
                Updater.m259setimpl(composerImpl, currentCompositionLocalScope3, function211);
                if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i9))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i9, composerImpl, i9, function212);
                }
                Updater.m259setimpl(composerImpl, materializeModifier3, function213);
                SimpleLayoutKt$$ExternalSyntheticOutline0.m((i6 >> 15) & 14, function24, composerImpl, true);
                r0 = 0;
            }
            composerImpl.end(r0);
            LayoutDirection layoutDirection3 = layoutDirection;
            float calculateStartPadding = PaddingKt.calculateStartPadding(paddingValues2, layoutDirection3);
            float calculateEndPadding = PaddingKt.calculateEndPadding(paddingValues2, layoutDirection3);
            if (function23 != null) {
                calculateStartPadding = RangesKt.coerceAtLeast(calculateStartPadding - f2, (float) r0);
            }
            if (function24 != null) {
                calculateEndPadding = RangesKt.coerceAtLeast(calculateEndPadding - f2, (float) r0);
            }
            composerImpl.startReplaceGroup(250784346);
            BiasAlignment biasAlignment2 = Alignment.Companion.TopStart;
            if (function25 != null) {
                Modifier m102paddingqDBjuR0$default = PaddingKt.m102paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m110heightInVpY3zN4$default(LayoutIdKt.layoutId(companion, "Prefix"), TextFieldImplKt.MinTextLineHeight, 0.0f, 2), 3), calculateStartPadding, 0.0f, TextFieldImplKt.PrefixSuffixTextPadding, 0.0f, 10);
                MeasurePolicy maybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment2, false);
                int i10 = composerImpl.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope4 = composerImpl.currentCompositionLocalScope();
                Modifier materializeModifier4 = ComposedModifierKt.materializeModifier(composerImpl, m102paddingqDBjuR0$default);
                composerImpl.startReusableNode();
                if (composerImpl.inserting) {
                    composerImpl.createNode(function0);
                } else {
                    composerImpl.useNode();
                }
                Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy3, function210);
                Updater.m259setimpl(composerImpl, currentCompositionLocalScope4, function211);
                if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i10))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i10, composerImpl, i10, function212);
                }
                Updater.m259setimpl(composerImpl, materializeModifier4, function213);
                SimpleLayoutKt$$ExternalSyntheticOutline0.m((i6 >> 18) & 14, function25, composerImpl, true);
            }
            composerImpl.end(false);
            composerImpl.startReplaceGroup(250796312);
            if (function26 != null) {
                Modifier m102paddingqDBjuR0$default2 = PaddingKt.m102paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m110heightInVpY3zN4$default(LayoutIdKt.layoutId(companion, "Suffix"), TextFieldImplKt.MinTextLineHeight, 0.0f, 2), 3), TextFieldImplKt.PrefixSuffixTextPadding, 0.0f, calculateEndPadding, 0.0f, 10);
                MeasurePolicy maybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment2, false);
                int i11 = composerImpl.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope5 = composerImpl.currentCompositionLocalScope();
                Modifier materializeModifier5 = ComposedModifierKt.materializeModifier(composerImpl, m102paddingqDBjuR0$default2);
                composerImpl.startReusableNode();
                f3 = calculateEndPadding;
                if (composerImpl.inserting) {
                    composerImpl.createNode(function0);
                } else {
                    composerImpl.useNode();
                }
                Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy4, function210);
                Updater.m259setimpl(composerImpl, currentCompositionLocalScope5, function211);
                if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i11))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i11, composerImpl, i11, function212);
                }
                Updater.m259setimpl(composerImpl, materializeModifier5, function213);
                SimpleLayoutKt$$ExternalSyntheticOutline0.m((i6 >> 21) & 14, function26, composerImpl, true);
            } else {
                f3 = calculateEndPadding;
            }
            composerImpl.end(false);
            float f4 = TextFieldImplKt.MinTextLineHeight;
            Modifier wrapContentHeight$default = SizeKt.wrapContentHeight$default(SizeKt.m110heightInVpY3zN4$default(companion, f4, 0.0f, 2), 3);
            if (function25 != null) {
                calculateStartPadding = 0;
            }
            Modifier m102paddingqDBjuR0$default3 = PaddingKt.m102paddingqDBjuR0$default(wrapContentHeight$default, calculateStartPadding, 0.0f, function26 == null ? f3 : 0, 0.0f, 10);
            composerImpl.startReplaceGroup(250818601);
            if (function3 != null) {
                function3.invoke(LayoutIdKt.layoutId(companion, "Hint").then(m102paddingqDBjuR0$default3), composerImpl, Integer.valueOf((i6 >> 3) & 112));
            }
            composerImpl.end(false);
            Modifier then3 = LayoutIdKt.layoutId(companion, "TextField").then(m102paddingqDBjuR0$default3);
            MeasurePolicy maybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment2, true);
            int i12 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope6 = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier6 = ComposedModifierKt.materializeModifier(composerImpl, then3);
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function0);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy5, function210);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope6, function211);
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i12))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i12, composerImpl, i12, function212);
            }
            Updater.m259setimpl(composerImpl, materializeModifier6, function213);
            function2.invoke(composerImpl, Integer.valueOf((i6 >> 3) & 14));
            composerImpl.end(true);
            composerImpl.startReplaceGroup(250842739);
            if (function22 != null) {
                Modifier then4 = LayoutIdKt.layoutId(SizeKt.wrapContentHeight$default(SizeKt.m110heightInVpY3zN4$default(companion, MathHelpersKt.lerp(f4, TextFieldImplKt.MinFocusedLabelLineHeight, f), 0.0f, 2), 3), "Label").then(companion);
                MeasurePolicy maybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment2, false);
                int i13 = composerImpl.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope7 = composerImpl.currentCompositionLocalScope();
                Modifier materializeModifier7 = ComposedModifierKt.materializeModifier(composerImpl, then4);
                composerImpl.startReusableNode();
                if (composerImpl.inserting) {
                    composerImpl.createNode(function0);
                } else {
                    composerImpl.useNode();
                }
                Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy6, function210);
                Updater.m259setimpl(composerImpl, currentCompositionLocalScope7, function211);
                if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i13))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i13, composerImpl, i13, function212);
                }
                Updater.m259setimpl(composerImpl, materializeModifier7, function213);
                SimpleLayoutKt$$ExternalSyntheticOutline0.m((i6 >> 9) & 14, function22, composerImpl, true);
            }
            composerImpl.end(false);
            composerImpl.startReplaceGroup(250856518);
            if (function28 != null) {
                Modifier padding = PaddingKt.padding(SizeKt.wrapContentHeight$default(SizeKt.m110heightInVpY3zN4$default(LayoutIdKt.layoutId(companion, "Supporting"), TextFieldImplKt.MinSupportingTextLineHeight, 0.0f, 2), 3), TextFieldDefaults.m238supportingTextPaddinga9UjIt4$material3_release$default());
                MeasurePolicy maybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment2, false);
                int i14 = composerImpl.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope8 = composerImpl.currentCompositionLocalScope();
                Modifier materializeModifier8 = ComposedModifierKt.materializeModifier(composerImpl, padding);
                composerImpl.startReusableNode();
                if (composerImpl.inserting) {
                    composerImpl.createNode(function0);
                } else {
                    composerImpl.useNode();
                }
                Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy7, function210);
                Updater.m259setimpl(composerImpl, currentCompositionLocalScope8, function211);
                if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i14))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i14, composerImpl, i14, function212);
                }
                Updater.m259setimpl(composerImpl, materializeModifier8, function213);
                z2 = true;
                SimpleLayoutKt$$ExternalSyntheticOutline0.m((i5 >> 9) & 14, function28, composerImpl, true);
            } else {
                z2 = true;
            }
            composerImpl.end(false);
            composerImpl.end(z2);
            composerImpl2 = composerImpl;
        }
        RecomposeScopeImpl endRestartGroup = composerImpl2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextFieldLayout$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    OutlinedTextFieldKt.OutlinedTextFieldLayout(Modifier.this, function2, function3, function22, function23, function24, function25, function26, z, textFieldLabelPosition, f, function1, function27, function28, paddingValues, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final Modifier outlineCutout(Modifier modifier, final Function0 function0, final BiasAlignment.Horizontal horizontal, final PaddingValues paddingValues) {
        return DrawModifierKt.drawWithContent(modifier, new Function1() { // from class: androidx.compose.material3.OutlinedTextFieldKt$outlineCutout$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ContentDrawScope contentDrawScope = (ContentDrawScope) obj;
                long j = ((Size) Function0.this.invoke()).packedValue;
                float m329getWidthimpl = Size.m329getWidthimpl(j);
                if (m329getWidthimpl > 0.0f) {
                    LayoutNodeDrawScope layoutNodeDrawScope = (LayoutNodeDrawScope) contentDrawScope;
                    float mo51toPx0680j_4 = layoutNodeDrawScope.mo51toPx0680j_4(OutlinedTextFieldKt.OutlinedTextFieldInnerPadding);
                    float mo51toPx0680j_42 = layoutNodeDrawScope.mo51toPx0680j_4(paddingValues.mo104calculateLeftPaddingu2uoSUM(layoutNodeDrawScope.getLayoutDirection()));
                    float mo51toPx0680j_43 = layoutNodeDrawScope.mo51toPx0680j_4(paddingValues.mo105calculateRightPaddingu2uoSUM(layoutNodeDrawScope.getLayoutDirection()));
                    Alignment.Horizontal horizontal2 = horizontal;
                    int roundToInt = MathKt.roundToInt(m329getWidthimpl);
                    CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
                    float align = horizontal2.align(roundToInt, MathKt.roundToInt((Size.m329getWidthimpl(canvasDrawScope.mo432getSizeNHjbRc()) - mo51toPx0680j_42) - mo51toPx0680j_43), layoutNodeDrawScope.getLayoutDirection()) + mo51toPx0680j_42;
                    float f = 2;
                    float f2 = m329getWidthimpl / f;
                    float f3 = align + f2;
                    float coerceAtLeast = RangesKt.coerceAtLeast((f3 - f2) - mo51toPx0680j_4, 0.0f);
                    float coerceAtMost = RangesKt.coerceAtMost(f3 + f2 + mo51toPx0680j_4, Size.m329getWidthimpl(canvasDrawScope.mo432getSizeNHjbRc()));
                    float m327getHeightimpl = Size.m327getHeightimpl(j);
                    float f4 = (-m327getHeightimpl) / f;
                    float f5 = m327getHeightimpl / f;
                    CanvasDrawScope$drawContext$1 canvasDrawScope$drawContext$1 = canvasDrawScope.drawContext;
                    long m418getSizeNHjbRc = canvasDrawScope$drawContext$1.m418getSizeNHjbRc();
                    canvasDrawScope$drawContext$1.getCanvas().save();
                    try {
                        canvasDrawScope$drawContext$1.transform.m420clipRectN_I0leg(coerceAtLeast, f4, coerceAtMost, f5, 0);
                        layoutNodeDrawScope.drawContent();
                    } finally {
                        BorderModifierNode$drawRoundRectBorder$1$$ExternalSyntheticOutline0.m(canvasDrawScope$drawContext$1, m418getSizeNHjbRc);
                    }
                } else {
                    ((LayoutNodeDrawScope) contentDrawScope).drawContent();
                }
                return Unit.INSTANCE;
            }
        });
    }
}
