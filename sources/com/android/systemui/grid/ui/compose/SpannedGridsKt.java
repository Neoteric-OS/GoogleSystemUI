package com.android.systemui.grid.ui.compose;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.CollectionInfo;
import androidx.compose.ui.semantics.CollectionItemInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SpannedGridsKt {
    /* renamed from: SpannedGrid-KhTvWYU, reason: not valid java name */
    public static final void m806SpannedGridKhTvWYU(final int i, final float f, final float f2, final List list, final boolean z, Modifier modifier, final Function4 function4, Composer composer, final int i2, final int i3) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(850672506);
        int i4 = i3 & 32;
        Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
        Modifier modifier2 = i4 != 0 ? companion : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        final Arrangement.SpacedAligned m78spacedBy0680j_4 = Arrangement.m78spacedBy0680j_4(f);
        int i5 = 0;
        for (Object obj : list) {
            int i6 = i5 + 1;
            if (i5 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            int intValue = ((Number) obj).intValue();
            if (1 > intValue || intValue > i) {
                throw new IllegalStateException(PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(i5, intValue, "Span out of bounds. Span at index ", " has value of ", " which is outside of the expected rance of [1, "), i, "]").toString());
            }
            i5 = i6;
        }
        if (z) {
            float f3 = 0;
            if (Float.compare(f, f3) < 0) {
                throw new IllegalStateException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Negative columnSpacing ", Dp.m669toStringimpl(f)).toString());
            }
            if (Float.compare(f2, f3) < 0) {
                throw new IllegalStateException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Negative rowSpacing ", Dp.m669toStringimpl(f2)).toString());
            }
        } else {
            float f4 = 0;
            if (Float.compare(f2, f4) < 0) {
                throw new IllegalStateException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Negative columnSpacing ", Dp.m669toStringimpl(f2)).toString());
            }
            if (Float.compare(f, f4) < 0) {
                throw new IllegalStateException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Negative rowSpacing ", Dp.m669toStringimpl(f)).toString());
            }
        }
        composerImpl.startReplaceGroup(442401553);
        boolean changed = ((((i2 & 14) ^ 6) > 4 && composerImpl.changed(i)) || (i2 & 6) == 4) | composerImpl.changed(list);
        Object rememberedValue = composerImpl.rememberedValue();
        Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
        if (changed || rememberedValue == composer$Companion$Empty$1) {
            Iterator it = list.iterator();
            int i7 = 0;
            int i8 = 1;
            while (it.hasNext()) {
                int intValue2 = ((Number) it.next()).intValue();
                i7 += intValue2;
                if (i7 > i) {
                    i8++;
                    i7 = intValue2;
                }
            }
            rememberedValue = Integer.valueOf(i8);
            composerImpl.updateRememberedValue(rememberedValue);
        }
        final int intValue3 = ((Number) rememberedValue).intValue();
        composerImpl.end(false);
        composerImpl.startReplaceGroup(442402003);
        Object rememberedValue2 = composerImpl.rememberedValue();
        Object obj2 = rememberedValue2;
        if (rememberedValue2 == composer$Companion$Empty$1) {
            SpannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$1 spannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$1 = new SpannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$1();
            spannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$1.sizes = new int[0];
            spannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$1.positions = new int[0];
            composerImpl.updateRememberedValue(spannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$1);
            obj2 = spannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$1;
        }
        final SpannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$1 spannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$12 = (SpannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$1) obj2;
        composerImpl.end(false);
        Modifier semantics = SemanticsModifierKt.semantics(modifier2, false, new Function1() { // from class: com.android.systemui.grid.ui.compose.SpannedGridsKt$SpannedGrid$7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj3) {
                CollectionInfo collectionInfo = new CollectionInfo(list.size(), 1);
                KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
                SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.CollectionInfo;
                KProperty kProperty = SemanticsPropertiesKt.$$delegatedProperties[20];
                semanticsPropertyKey.setValue((SemanticsPropertyReceiver) obj3, collectionInfo);
                return Unit.INSTANCE;
            }
        });
        final Modifier modifier3 = modifier2;
        Modifier.Companion companion2 = companion;
        MeasurePolicy measurePolicy = new MeasurePolicy() { // from class: com.android.systemui.grid.ui.compose.SpannedGridsKt$SpannedGrid$8
            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* renamed from: measure-3p2s80s */
            public final MeasureResult mo2measure3p2s80s(MeasureScope measureScope, List list2, long j) {
                final List list3;
                MeasureResult layout$1;
                Iterator it2;
                int i9;
                int i10;
                int i11;
                int i12;
                int i13;
                int[] iArr;
                int i14;
                long Constraints$default;
                if (list2.size() != list.size()) {
                    throw new IllegalStateException("Check failed.");
                }
                final boolean z2 = z;
                int m655getMaxWidthimpl = z2 ? Constraints.m655getMaxWidthimpl(j) : Constraints.m654getMaxHeightimpl(j);
                if (m655getMaxWidthimpl == Integer.MAX_VALUE) {
                    throw new IllegalStateException("Width must be constrained");
                }
                SpannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$1 spannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$13 = spannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$12;
                int length = spannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$13.sizes.length;
                int i15 = i;
                if (length != i15) {
                    spannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$13.sizes = new int[i15];
                    spannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$13.positions = new int[i15];
                }
                int mo45roundToPx0680j_4 = measureScope.mo45roundToPx0680j_4(f);
                int[] iArr2 = spannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$13.sizes;
                if (iArr2.length != i15) {
                    throw new IllegalStateException("Check failed.");
                }
                int i16 = m655getMaxWidthimpl - ((i15 - 1) * mo45roundToPx0680j_4);
                int i17 = i16 / i15;
                int i18 = i16 % i15;
                IntProgressionIterator it3 = ArraysKt.getIndices(iArr2).iterator();
                while (true) {
                    int i19 = 0;
                    if (!it3.hasNext) {
                        break;
                    }
                    int nextInt = it3.nextInt();
                    if (nextInt < i18) {
                        i19 = 1;
                    }
                    iArr2[nextInt] = i19 + i17;
                }
                int[] iArr3 = spannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$13.sizes;
                m78spacedBy0680j_4.arrange(measureScope, m655getMaxWidthimpl, iArr3, LayoutDirection.Ltr, spannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$13.positions);
                final int[] iArr4 = spannedGridsKt$SpannedGrid$slotPositionsAndSizesCache$1$13.positions;
                int mo45roundToPx0680j_42 = measureScope.mo45roundToPx0680j_4(f2);
                int i20 = intValue3;
                int i21 = (i20 - 1) * mo45roundToPx0680j_42;
                int m654getMaxHeightimpl = z2 ? Constraints.m654getMaxHeightimpl(j) : Constraints.m655getMaxWidthimpl(j);
                int max = m654getMaxHeightimpl != Integer.MAX_VALUE ? Math.max(0, (m654getMaxHeightimpl - i21) / i20) : Integer.MAX_VALUE;
                int[] iArr5 = new int[i20];
                for (int i22 = 0; i22 < i20; i22++) {
                    iArr5[i22] = 0;
                }
                List list4 = list;
                final ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
                Iterator it4 = list2.iterator();
                int i23 = 0;
                int i24 = 0;
                int i25 = 0;
                while (it4.hasNext()) {
                    Object next = it4.next();
                    int i26 = i23 + 1;
                    if (i23 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                        throw null;
                    }
                    Measurable measurable = (Measurable) next;
                    int intValue4 = ((Number) list4.get(i23)).intValue();
                    List list5 = list4;
                    if (i24 + intValue4 > i15) {
                        i11 = m655getMaxWidthimpl;
                        it2 = it4;
                        i12 = i25 + 1;
                        i9 = 0;
                        i10 = 1;
                    } else {
                        it2 = it4;
                        i9 = i24;
                        i10 = 1;
                        i11 = m655getMaxWidthimpl;
                        i12 = i25;
                    }
                    if (intValue4 == i10) {
                        i13 = iArr3[i9];
                    } else {
                        int i27 = (i9 + intValue4) - 1;
                        i13 = (iArr4[i27] + iArr3[i27]) - iArr4[i9];
                    }
                    int i28 = i15;
                    if (i13 < 0) {
                        i13 = 0;
                    }
                    if (z2) {
                        iArr = iArr3;
                        i14 = max;
                        Constraints$default = ConstraintsKt.Constraints$default(i13, i13, 0, max, 4);
                    } else {
                        iArr = iArr3;
                        i14 = max;
                        Constraints$default = ConstraintsKt.Constraints$default(0, max, i13, i13, 1);
                    }
                    Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(Constraints$default);
                    PlaceResult placeResult = new PlaceResult(i9, i12, mo479measureBRTryo0);
                    int i29 = i9 + intValue4;
                    iArr5[i12] = Math.max(iArr5[i12], z2 ? mo479measureBRTryo0.height : mo479measureBRTryo0.width);
                    arrayList.add(placeResult);
                    it4 = it2;
                    max = i14;
                    i15 = i28;
                    i23 = i26;
                    iArr3 = iArr;
                    i25 = i12;
                    m655getMaxWidthimpl = i11;
                    i24 = i29;
                    list4 = list5;
                }
                int i30 = m655getMaxWidthimpl;
                int i31 = 0;
                for (int i32 = 0; i32 < i20; i32++) {
                    i31 += iArr5[i32];
                }
                int i33 = i31 + i21;
                Integer num = 0;
                if (i20 == 0) {
                    list3 = Collections.singletonList(num);
                } else {
                    ArrayList arrayList2 = new ArrayList(i20 + 1);
                    arrayList2.add(num);
                    for (int i34 = 0; i34 < i20; i34++) {
                        num = Integer.valueOf(num.intValue() + iArr5[i34] + mo45roundToPx0680j_42);
                        arrayList2.add(num);
                    }
                    list3 = arrayList2;
                }
                layout$1 = measureScope.layout$1(z2 ? i30 : i33, z2 ? i33 : i30, MapsKt.emptyMap(), new Function1() { // from class: com.android.systemui.grid.ui.compose.SpannedGridsKt$SpannedGrid$8.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj3;
                        List<PlaceResult> list6 = arrayList;
                        boolean z3 = z2;
                        int[] iArr6 = iArr4;
                        List list7 = list3;
                        for (PlaceResult placeResult2 : list6) {
                            Placeable placeable = placeResult2.placeable;
                            int i35 = placeResult2.slotIndex;
                            int i36 = placeResult2.mainAxisGroup;
                            placementScope.placeRelative(placeable, z3 ? iArr6[i35] : ((Number) list7.get(i36)).intValue(), z3 ? ((Number) list7.get(i36)).intValue() : iArr6[i35], 0.0f);
                        }
                        return Unit.INSTANCE;
                    }
                });
                return layout$1;
            }
        };
        OpaqueKey opaqueKey2 = ComposerKt.invocation;
        int i9 = composerImpl.compoundKeyHash;
        PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, semantics);
        ComposeUiNode.Companion.getClass();
        Function0 function0 = ComposeUiNode.Companion.Constructor;
        composerImpl.startReusableNode();
        if (composerImpl.inserting) {
            composerImpl.createNode(function0);
        } else {
            composerImpl.useNode();
        }
        Updater.m259setimpl(composerImpl, measurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
        Updater.m259setimpl(composerImpl, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
        Function2 function2 = ComposeUiNode.Companion.SetCompositeKeyHash;
        if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i9))) {
            AnimatedContentKt$$ExternalSyntheticOutline0.m(i9, composerImpl, i9, function2);
        }
        Updater.m259setimpl(composerImpl, materializeModifier, ComposeUiNode.Companion.SetModifier);
        composerImpl.startReplaceGroup(442402179);
        IntRange until = RangesKt.until(0, list.size());
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(until, 10));
        IntProgressionIterator it2 = until.iterator();
        while (it2.hasNext) {
            final int nextInt = it2.nextInt();
            composerImpl.startReplaceGroup(52122772);
            boolean changed2 = composerImpl.changed(nextInt) | ((((57344 & i2) ^ 24576) > 16384 && composerImpl.changed(z)) || (i2 & 24576) == 16384);
            Object rememberedValue3 = composerImpl.rememberedValue();
            if (changed2 || rememberedValue3 == composer$Companion$Empty$1) {
                rememberedValue3 = new Function1() { // from class: com.android.systemui.grid.ui.compose.SpannedGridsKt$SpannedGrid$6$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj3;
                        CollectionItemInfo collectionItemInfo = z ? new CollectionItemInfo(nextInt, 0) : new CollectionItemInfo(0, nextInt);
                        KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
                        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.CollectionItemInfo;
                        KProperty kProperty = SemanticsPropertiesKt.$$delegatedProperties[21];
                        semanticsPropertyKey.setValue(semanticsPropertyReceiver, collectionItemInfo);
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue3);
            }
            composerImpl.end(false);
            Modifier.Companion companion3 = companion2;
            Modifier semantics2 = SemanticsModifierKt.semantics(companion3, false, (Function1) rememberedValue3);
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.TopStart, false);
            OpaqueKey opaqueKey3 = ComposerKt.invocation;
            int i10 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, semantics2);
            ComposeUiNode.Companion.getClass();
            Function0 function02 = ComposeUiNode.Companion.Constructor;
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function02);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, ComposeUiNode.Companion.SetResolvedCompositionLocals);
            Function2 function22 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i10))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i10, composerImpl, i10, function22);
            }
            Updater.m259setimpl(composerImpl, materializeModifier2, ComposeUiNode.Companion.SetModifier);
            function4.invoke(BoxScopeInstance.INSTANCE, Integer.valueOf(nextInt), composerImpl, Integer.valueOf(((i2 >> 12) & 896) | 6));
            composerImpl.end(true);
            arrayList.add(Unit.INSTANCE);
            companion2 = companion3;
        }
        composerImpl.end(false);
        composerImpl.end(true);
        OpaqueKey opaqueKey4 = ComposerKt.invocation;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.grid.ui.compose.SpannedGridsKt$SpannedGrid$9
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    ((Number) obj4).intValue();
                    SpannedGridsKt.m806SpannedGridKhTvWYU(i, f, f2, list, z, modifier3, function4, (Composer) obj3, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    return Unit.INSTANCE;
                }
            };
        }
    }

    /* renamed from: VerticalSpannedGrid-ZUYZQmM, reason: not valid java name */
    public static final void m807VerticalSpannedGridZUYZQmM(final int i, final float f, final float f2, final List list, Modifier modifier, final Function4 function4, Composer composer, final int i2, final int i3) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1752940620);
        final Modifier modifier2 = (i3 & 16) != 0 ? Modifier.Companion.$$INSTANCE : modifier;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        int i4 = i2 << 3;
        m806SpannedGridKhTvWYU(i, f, f2, list, true, modifier2, function4, composerImpl, (i2 & 14) | 28672 | (i2 & 112) | (i2 & 896) | (458752 & i4) | (i4 & 3670016), 0);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: com.android.systemui.grid.ui.compose.SpannedGridsKt$VerticalSpannedGrid$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    SpannedGridsKt.m807VerticalSpannedGridZUYZQmM(i, f, f2, list, modifier2, function4, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
                    return Unit.INSTANCE;
                }
            };
        }
    }
}
