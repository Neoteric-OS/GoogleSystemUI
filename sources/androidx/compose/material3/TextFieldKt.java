package androidx.compose.material3;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.selection.SimpleLayoutKt$$ExternalSyntheticOutline0;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.tokens.TypeScaleTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextFieldKt {
    public static final float TextFieldWithLabelVerticalPadding = 8;

    public static final void TextFieldLayout(final Modifier modifier, final Function2 function2, final Function2 function22, final Function3 function3, final Function2 function23, final Function2 function24, final Function2 function25, final Function2 function26, final boolean z, final TextFieldLabelPosition textFieldLabelPosition, final float f, final Function2 function27, final Function2 function28, final PaddingValues paddingValues, Composer composer, final int i, final int i2) {
        int i3;
        int i4;
        int i5;
        ComposerImpl composerImpl;
        PaddingValues paddingValues2;
        LayoutDirection layoutDirection;
        float f2;
        float f3;
        int i6;
        boolean z2;
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(-208045858);
        if ((i & 6) == 0) {
            i3 = (composerImpl2.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerImpl2.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i3 |= composerImpl2.changedInstance(function22) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerImpl2.changedInstance(function3) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i3 |= composerImpl2.changedInstance(function23) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i3 |= composerImpl2.changedInstance(function24) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i3 |= composerImpl2.changedInstance(function25) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i3 |= composerImpl2.changedInstance(function26) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            i3 |= composerImpl2.changed(z) ? 67108864 : 33554432;
        }
        if ((i & 805306368) == 0) {
            i3 |= composerImpl2.changed(textFieldLabelPosition) ? 536870912 : 268435456;
        }
        int i7 = i3;
        if ((i2 & 6) == 0) {
            i4 = (composerImpl2.changed(f) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            i4 |= composerImpl2.changedInstance(function27) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i4 |= composerImpl2.changedInstance(function28) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i4 |= composerImpl2.changed(paddingValues) ? 2048 : 1024;
        }
        if ((i7 & 306783379) == 306783378 && (i4 & 1171) == 1170 && composerImpl2.getSkipping()) {
            composerImpl2.skipToGroupEnd();
            composerImpl = composerImpl2;
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            float f4 = TextFieldImplKt.TextFieldPadding;
            long j = MaterialTheme.getTypography(composerImpl2).bodySmall.paragraphStyle.lineHeight;
            long j2 = TypeScaleTokens.BodySmallLineHeight;
            if ((j & 1095216660480L) != 4294967296L) {
                j = j2;
            }
            float mo46toDpGaN1DYA = ((Density) composerImpl2.consume(CompositionLocalsKt.LocalDensity)).mo46toDpGaN1DYA(j) / 2;
            boolean changed = ((i7 & 1879048192) == 536870912) | ((i7 & 234881024) == 67108864) | ((i4 & 14) == 4) | ((i4 & 7168) == 2048) | composerImpl2.changed(mo46toDpGaN1DYA);
            Object rememberedValue = composerImpl2.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.Empty) {
                i5 = i4;
                composerImpl = composerImpl2;
                paddingValues2 = paddingValues;
                TextFieldMeasurePolicy textFieldMeasurePolicy = new TextFieldMeasurePolicy(z, textFieldLabelPosition, f, paddingValues, mo46toDpGaN1DYA);
                composerImpl.updateRememberedValue(textFieldMeasurePolicy);
                rememberedValue = textFieldMeasurePolicy;
            } else {
                paddingValues2 = paddingValues;
                composerImpl = composerImpl2;
                i5 = i4;
            }
            TextFieldMeasurePolicy textFieldMeasurePolicy2 = (TextFieldMeasurePolicy) rememberedValue;
            LayoutDirection layoutDirection2 = (LayoutDirection) composerImpl.consume(CompositionLocalsKt.LocalLayoutDirection);
            int i8 = composerImpl.compoundKeyHash;
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
            Function2 function29 = ComposeUiNode.Companion.SetMeasurePolicy;
            Updater.m259setimpl(composerImpl, textFieldMeasurePolicy2, function29);
            Function2 function210 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope, function210);
            Function2 function211 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i8))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i8, composerImpl, i8, function211);
            }
            Function2 function212 = ComposeUiNode.Companion.SetModifier;
            Updater.m259setimpl(composerImpl, materializeModifier, function212);
            function27.invoke(composerImpl, Integer.valueOf((i5 >> 3) & 14));
            composerImpl.startReplaceGroup(1341896391);
            BiasAlignment biasAlignment = Alignment.Companion.Center;
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            if (function23 != null) {
                Modifier layoutId = LayoutIdKt.layoutId(companion, "Leading");
                StaticProvidableCompositionLocal staticProvidableCompositionLocal = InteractiveComponentSizeKt.LocalMinimumInteractiveComponentSize;
                Modifier then = layoutId.then(MinimumInteractiveModifier.INSTANCE);
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
                int i9 = composerImpl.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, then);
                composerImpl.startReusableNode();
                layoutDirection = layoutDirection2;
                if (composerImpl.inserting) {
                    composerImpl.createNode(function0);
                } else {
                    composerImpl.useNode();
                }
                Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, function29);
                Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function210);
                if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i9))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i9, composerImpl, i9, function211);
                }
                Updater.m259setimpl(composerImpl, materializeModifier2, function212);
                SimpleLayoutKt$$ExternalSyntheticOutline0.m((i7 >> 12) & 14, function23, composerImpl, true);
            } else {
                layoutDirection = layoutDirection2;
            }
            composerImpl.end(false);
            composerImpl.startReplaceGroup(1341905642);
            if (function24 != null) {
                Modifier layoutId2 = LayoutIdKt.layoutId(companion, "Trailing");
                StaticProvidableCompositionLocal staticProvidableCompositionLocal2 = InteractiveComponentSizeKt.LocalMinimumInteractiveComponentSize;
                Modifier then2 = layoutId2.then(MinimumInteractiveModifier.INSTANCE);
                MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
                int i10 = composerImpl.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope3 = composerImpl.currentCompositionLocalScope();
                Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composerImpl, then2);
                composerImpl.startReusableNode();
                if (composerImpl.inserting) {
                    composerImpl.createNode(function0);
                } else {
                    composerImpl.useNode();
                }
                Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy2, function29);
                Updater.m259setimpl(composerImpl, currentCompositionLocalScope3, function210);
                if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i10))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i10, composerImpl, i10, function211);
                }
                Updater.m259setimpl(composerImpl, materializeModifier3, function212);
                SimpleLayoutKt$$ExternalSyntheticOutline0.m((i7 >> 15) & 14, function24, composerImpl, true);
            }
            composerImpl.end(false);
            LayoutDirection layoutDirection3 = layoutDirection;
            float calculateStartPadding = PaddingKt.calculateStartPadding(paddingValues2, layoutDirection3);
            float calculateEndPadding = PaddingKt.calculateEndPadding(paddingValues2, layoutDirection3);
            float textFieldHorizontalIconPadding = TextFieldImplKt.textFieldHorizontalIconPadding(composerImpl);
            if (function23 != null) {
                calculateStartPadding = RangesKt.coerceAtLeast(calculateStartPadding - textFieldHorizontalIconPadding, 0);
            }
            if (function24 != null) {
                calculateEndPadding = RangesKt.coerceAtLeast(calculateEndPadding - textFieldHorizontalIconPadding, 0);
            }
            composerImpl.startReplaceGroup(1341938716);
            BiasAlignment biasAlignment2 = Alignment.Companion.TopStart;
            if (function25 != null) {
                Modifier m102paddingqDBjuR0$default = PaddingKt.m102paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m110heightInVpY3zN4$default(LayoutIdKt.layoutId(companion, "Prefix"), TextFieldImplKt.MinTextLineHeight, 0.0f, 2), 3), calculateStartPadding, 0.0f, TextFieldImplKt.PrefixSuffixTextPadding, 0.0f, 10);
                MeasurePolicy maybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment2, false);
                int i11 = composerImpl.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope4 = composerImpl.currentCompositionLocalScope();
                Modifier materializeModifier4 = ComposedModifierKt.materializeModifier(composerImpl, m102paddingqDBjuR0$default);
                composerImpl.startReusableNode();
                if (composerImpl.inserting) {
                    composerImpl.createNode(function0);
                } else {
                    composerImpl.useNode();
                }
                Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy3, function29);
                Updater.m259setimpl(composerImpl, currentCompositionLocalScope4, function210);
                if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i11))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i11, composerImpl, i11, function211);
                }
                Updater.m259setimpl(composerImpl, materializeModifier4, function212);
                SimpleLayoutKt$$ExternalSyntheticOutline0.m((i7 >> 18) & 14, function25, composerImpl, true);
            }
            composerImpl.end(false);
            composerImpl.startReplaceGroup(1341950682);
            if (function26 != null) {
                Modifier m102paddingqDBjuR0$default2 = PaddingKt.m102paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m110heightInVpY3zN4$default(LayoutIdKt.layoutId(companion, "Suffix"), TextFieldImplKt.MinTextLineHeight, 0.0f, 2), 3), TextFieldImplKt.PrefixSuffixTextPadding, 0.0f, calculateEndPadding, 0.0f, 10);
                MeasurePolicy maybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment2, false);
                int i12 = composerImpl.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope5 = composerImpl.currentCompositionLocalScope();
                Modifier materializeModifier5 = ComposedModifierKt.materializeModifier(composerImpl, m102paddingqDBjuR0$default2);
                composerImpl.startReusableNode();
                if (composerImpl.inserting) {
                    composerImpl.createNode(function0);
                } else {
                    composerImpl.useNode();
                }
                Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy4, function29);
                Updater.m259setimpl(composerImpl, currentCompositionLocalScope5, function210);
                if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i12))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i12, composerImpl, i12, function211);
                }
                Updater.m259setimpl(composerImpl, materializeModifier5, function212);
                SimpleLayoutKt$$ExternalSyntheticOutline0.m((i7 >> 21) & 14, function26, composerImpl, true);
            }
            composerImpl.end(false);
            Modifier m102paddingqDBjuR0$default3 = PaddingKt.m102paddingqDBjuR0$default(companion, calculateStartPadding, 0.0f, calculateEndPadding, 0.0f, 10);
            composerImpl.startReplaceGroup(1341977333);
            if (function22 != null) {
                f2 = calculateStartPadding;
                f3 = calculateEndPadding;
                i6 = 3;
                Modifier then3 = SizeKt.wrapContentHeight$default(SizeKt.m110heightInVpY3zN4$default(LayoutIdKt.layoutId(companion, "Label"), MathHelpersKt.lerp(TextFieldImplKt.MinTextLineHeight, TextFieldImplKt.MinFocusedLabelLineHeight, f), 0.0f, 2), 3).then(m102paddingqDBjuR0$default3);
                MeasurePolicy maybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment2, false);
                int i13 = composerImpl.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope6 = composerImpl.currentCompositionLocalScope();
                Modifier materializeModifier6 = ComposedModifierKt.materializeModifier(composerImpl, then3);
                composerImpl.startReusableNode();
                if (composerImpl.inserting) {
                    composerImpl.createNode(function0);
                } else {
                    composerImpl.useNode();
                }
                Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy5, function29);
                Updater.m259setimpl(composerImpl, currentCompositionLocalScope6, function210);
                if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i13))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i13, composerImpl, i13, function211);
                }
                Updater.m259setimpl(composerImpl, materializeModifier6, function212);
                SimpleLayoutKt$$ExternalSyntheticOutline0.m((i7 >> 6) & 14, function22, composerImpl, true);
            } else {
                f2 = calculateStartPadding;
                f3 = calculateEndPadding;
                i6 = 3;
            }
            composerImpl.end(false);
            Modifier m102paddingqDBjuR0$default4 = PaddingKt.m102paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m110heightInVpY3zN4$default(companion, TextFieldImplKt.MinTextLineHeight, 0.0f, 2), i6), function25 == null ? f2 : 0, 0.0f, function26 == null ? f3 : 0, 0.0f, 10);
            composerImpl.startReplaceGroup(1342001451);
            if (function3 != null) {
                function3.invoke(LayoutIdKt.layoutId(companion, "Hint").then(m102paddingqDBjuR0$default4), composerImpl, Integer.valueOf((i7 >> 6) & 112));
            }
            composerImpl.end(false);
            Modifier then4 = LayoutIdKt.layoutId(companion, "TextField").then(m102paddingqDBjuR0$default4);
            MeasurePolicy maybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment2, true);
            int i14 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope7 = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier7 = ComposedModifierKt.materializeModifier(composerImpl, then4);
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function0);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy6, function29);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope7, function210);
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i14))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i14, composerImpl, i14, function211);
            }
            Updater.m259setimpl(composerImpl, materializeModifier7, function212);
            function2.invoke(composerImpl, Integer.valueOf((i7 >> 3) & 14));
            composerImpl.end(true);
            composerImpl.startReplaceGroup(1342012448);
            if (function28 != null) {
                Modifier padding = PaddingKt.padding(SizeKt.wrapContentHeight$default(SizeKt.m110heightInVpY3zN4$default(LayoutIdKt.layoutId(companion, "Supporting"), TextFieldImplKt.MinSupportingTextLineHeight, 0.0f, 2), 3), TextFieldDefaults.m238supportingTextPaddinga9UjIt4$material3_release$default());
                MeasurePolicy maybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment2, false);
                int i15 = composerImpl.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope8 = composerImpl.currentCompositionLocalScope();
                Modifier materializeModifier8 = ComposedModifierKt.materializeModifier(composerImpl, padding);
                composerImpl.startReusableNode();
                if (composerImpl.inserting) {
                    composerImpl.createNode(function0);
                } else {
                    composerImpl.useNode();
                }
                Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy7, function29);
                Updater.m259setimpl(composerImpl, currentCompositionLocalScope8, function210);
                if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i15))) {
                    AnimatedContentKt$$ExternalSyntheticOutline0.m(i15, composerImpl, i15, function211);
                }
                Updater.m259setimpl(composerImpl, materializeModifier8, function212);
                z2 = true;
                SimpleLayoutKt$$ExternalSyntheticOutline0.m((i5 >> 6) & 14, function28, composerImpl, true);
            } else {
                z2 = true;
            }
            composerImpl.end(false);
            composerImpl.end(z2);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.material3.TextFieldKt$TextFieldLayout$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    TextFieldKt.TextFieldLayout(Modifier.this, function2, function22, function3, function23, function24, function25, function26, z, textFieldLabelPosition, f, function27, function28, paddingValues, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
