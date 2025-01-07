package androidx.compose.material3;

import androidx.compose.animation.AnimatedContentKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.gestures.DraggableElement;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.internal.AccessibilityUtilKt;
import androidx.compose.material3.tokens.SliderTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.Composer$Companion$Empty$1;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnRemeasuredModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.ClosedFloatRange;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SliderKt {
    public static final long ThumbSize;
    public static final float ThumbTrackGapSize;
    public static final float ThumbWidth;
    public static final float TrackHeight = SliderTokens.InactiveTrackHeight;
    public static final float TrackInsideCornerSize;

    static {
        float f = SliderTokens.HandleWidth;
        ThumbWidth = f;
        ThumbSize = DpKt.m670DpSizeYgX7TsA(f, SliderTokens.HandleHeight);
        ThumbTrackGapSize = SliderTokens.ActiveHandleLeadingSpace;
        TrackInsideCornerSize = 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01c5  */
    /* JADX WARN: Type inference failed for: r3v7, types: [androidx.compose.material3.SliderKt$Slider$7, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r3v9, types: [androidx.compose.material3.SliderKt$Slider$6, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Slider(final float r25, final kotlin.jvm.functions.Function1 r26, androidx.compose.ui.Modifier r27, boolean r28, kotlin.jvm.functions.Function0 r29, androidx.compose.material3.SliderColors r30, androidx.compose.foundation.interaction.MutableInteractionSource r31, int r32, kotlin.jvm.functions.Function3 r33, kotlin.jvm.functions.Function3 r34, kotlin.ranges.ClosedFloatingPointRange r35, androidx.compose.runtime.Composer r36, final int r37, final int r38, final int r39) {
        /*
            Method dump skipped, instructions count: 730
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.Slider(float, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function0, androidx.compose.material3.SliderColors, androidx.compose.foundation.interaction.MutableInteractionSource, int, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, kotlin.ranges.ClosedFloatingPointRange, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r25v0, types: [java.lang.Object, kotlin.jvm.functions.Function3] */
    /* JADX WARN: Type inference failed for: r26v0, types: [java.lang.Object, kotlin.jvm.functions.Function3] */
    /* JADX WARN: Type inference failed for: r3v22, types: [androidx.compose.ui.Modifier] */
    public static final void SliderImpl(final Modifier modifier, final SliderState sliderState, final boolean z, final MutableInteractionSource mutableInteractionSource, final Function3 function3, final Function3 function32, Composer composer, final int i) {
        int i2;
        ComposerImpl composerImpl;
        Modifier then;
        ComposerImpl composerImpl2 = (ComposerImpl) composer;
        composerImpl2.startRestartGroup(1390990089);
        if ((i & 6) == 0) {
            i2 = (composerImpl2.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerImpl2.changedInstance(sliderState) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerImpl2.changed(z) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerImpl2.changed(mutableInteractionSource) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerImpl2.changedInstance(function3) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerImpl2.changedInstance(function32) ? 131072 : 65536;
        }
        int i3 = i2;
        if ((i3 & 74899) == 74898 && composerImpl2.getSkipping()) {
            composerImpl2.skipToGroupEnd();
            composerImpl = composerImpl2;
        } else {
            OpaqueKey opaqueKey = ComposerKt.invocation;
            sliderState.isRtl = composerImpl2.consume(CompositionLocalsKt.LocalLayoutDirection) == LayoutDirection.Rtl;
            Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
            Modifier.Companion pointerInput = z ? SuspendingPointerInputFilterKt.pointerInput(sliderState, mutableInteractionSource, new SliderKt$sliderTapModifier$1(sliderState, null)) : companion;
            Orientation orientation = Orientation.Horizontal;
            boolean z2 = sliderState.isRtl;
            boolean booleanValue = ((Boolean) ((SnapshotMutableStateImpl) sliderState.isDragging$delegate).getValue()).booleanValue();
            boolean changedInstance = composerImpl2.changedInstance(sliderState);
            Object rememberedValue = composerImpl2.rememberedValue();
            Composer$Companion$Empty$1 composer$Companion$Empty$1 = Composer.Companion.Empty;
            if (changedInstance || rememberedValue == composer$Companion$Empty$1) {
                rememberedValue = new SliderKt$SliderImpl$drag$1$1(sliderState, null);
                composerImpl2.updateRememberedValue(rememberedValue);
            }
            composerImpl = composerImpl2;
            then = companion.then(new DraggableElement(sliderState, orientation, (r19 & 4) != 0 ? true : z, (r19 & 8) != 0 ? null : mutableInteractionSource, (r19 & 16) != 0 ? false : booleanValue, DraggableKt.NoOpOnDragStarted, (Function3) rememberedValue, (r19 & 128) != 0 ? false : z2));
            StaticProvidableCompositionLocal staticProvidableCompositionLocal = InteractiveComponentSizeKt.LocalMinimumInteractiveComponentSize;
            Modifier then2 = SemanticsModifierKt.semantics(SizeKt.m112requiredSizeInqDBjuR0$default(modifier.then(MinimumInteractiveModifier.INSTANCE), ThumbWidth, TrackHeight, 0.0f, 0.0f, 12), false, new Function1() { // from class: androidx.compose.material3.SliderKt$sliderSemantics$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj;
                    if (!z) {
                        SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
                    }
                    final SliderState sliderState2 = sliderState;
                    Function1 function1 = new Function1() { // from class: androidx.compose.material3.SliderKt$sliderSemantics$1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            int i4;
                            float coerceIn = RangesKt.coerceIn(((Number) obj2).floatValue(), ((ClosedFloatRange) SliderState.this.valueRange)._start, ((ClosedFloatRange) SliderState.this.valueRange)._endInclusive);
                            int i5 = SliderState.this.steps;
                            boolean z3 = true;
                            if (i5 > 0 && (i4 = i5 + 1) >= 0) {
                                float f = coerceIn;
                                float f2 = f;
                                int i6 = 0;
                                while (true) {
                                    float lerp = MathHelpersKt.lerp(((ClosedFloatRange) SliderState.this.valueRange)._start, ((ClosedFloatRange) SliderState.this.valueRange)._endInclusive, i6 / (SliderState.this.steps + 1));
                                    float f3 = lerp - coerceIn;
                                    if (Math.abs(f3) <= f) {
                                        f = Math.abs(f3);
                                        f2 = lerp;
                                    }
                                    if (i6 == i4) {
                                        break;
                                    }
                                    i6++;
                                }
                                coerceIn = f2;
                            }
                            if (coerceIn == SliderState.this.getValue()) {
                                z3 = false;
                            } else {
                                if (coerceIn != SliderState.this.getValue()) {
                                    SliderState sliderState3 = SliderState.this;
                                    Function1 function12 = sliderState3.onValueChange;
                                    if (function12 != null) {
                                        function12.invoke(Float.valueOf(coerceIn));
                                    } else {
                                        sliderState3.setValue(coerceIn);
                                    }
                                }
                                Function0 function0 = SliderState.this.onValueChangeFinished;
                                if (function0 != null) {
                                    function0.invoke();
                                }
                            }
                            return Boolean.valueOf(z3);
                        }
                    };
                    KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
                    ((SemanticsConfiguration) semanticsPropertyReceiver).set(SemanticsActions.SetProgress, new AccessibilityAction(null, function1));
                    return Unit.INSTANCE;
                }
            }).then(AccessibilityUtilKt.IncreaseHorizontalSemanticsBounds);
            float value = sliderState.getValue();
            ClosedFloatRange closedFloatRange = (ClosedFloatRange) sliderState.valueRange;
            Modifier then3 = FocusableKt.focusable(ProgressSemanticsKt.progressSemantics(then2, value, new ClosedFloatRange(closedFloatRange._start, closedFloatRange._endInclusive), sliderState.steps), z, mutableInteractionSource).then(pointerInput).then(then);
            boolean changedInstance2 = composerImpl.changedInstance(sliderState);
            Object rememberedValue2 = composerImpl.rememberedValue();
            if (changedInstance2 || rememberedValue2 == composer$Companion$Empty$1) {
                rememberedValue2 = new MeasurePolicy() { // from class: androidx.compose.material3.SliderKt$SliderImpl$2$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* renamed from: measure-3p2s80s */
                    public final MeasureResult mo2measure3p2s80s(MeasureScope measureScope, List list, long j) {
                        MeasureResult layout$1;
                        int size = list.size();
                        for (int i4 = 0; i4 < size; i4++) {
                            Measurable measurable = (Measurable) list.get(i4);
                            if (LayoutIdKt.getLayoutId(measurable) == SliderComponents.THUMB) {
                                final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(j);
                                int size2 = list.size();
                                for (int i5 = 0; i5 < size2; i5++) {
                                    Measurable measurable2 = (Measurable) list.get(i5);
                                    if (LayoutIdKt.getLayoutId(measurable2) == SliderComponents.TRACK) {
                                        final Placeable mo479measureBRTryo02 = measurable2.mo479measureBRTryo0(Constraints.m648copyZbe2FdA$default(ConstraintsKt.m667offsetNN6EwU$default(-mo479measureBRTryo0.width, 0, j, 2), 0, 0, 0, 0, 11));
                                        int i6 = mo479measureBRTryo0.width + mo479measureBRTryo02.width;
                                        int max = Math.max(mo479measureBRTryo02.height, mo479measureBRTryo0.height);
                                        float f = mo479measureBRTryo02.height;
                                        SliderState sliderState2 = SliderState.this;
                                        ((SnapshotMutableFloatStateImpl) sliderState2.trackHeight$delegate).setFloatValue(f);
                                        ((SnapshotMutableIntStateImpl) sliderState2.totalWidth$delegate).setIntValue(i6);
                                        final int i7 = mo479measureBRTryo0.width / 2;
                                        final int roundToInt = MathKt.roundToInt(sliderState2.getCoercedValueAsFraction$material3_release() * mo479measureBRTryo02.width);
                                        final int i8 = (max - mo479measureBRTryo02.height) / 2;
                                        final int i9 = (max - mo479measureBRTryo0.height) / 2;
                                        layout$1 = measureScope.layout$1(i6, max, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.material3.SliderKt$SliderImpl$2$1.1
                                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj) {
                                                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                                                placementScope.placeRelative(Placeable.this, i7, i8, 0.0f);
                                                placementScope.placeRelative(mo479measureBRTryo0, roundToInt, i9, 0.0f);
                                                return Unit.INSTANCE;
                                            }
                                        });
                                        return layout$1;
                                    }
                                }
                                throw new NoSuchElementException("Collection contains no element matching the predicate.");
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue2);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) rememberedValue2;
            int i4 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composerImpl, then3);
            ComposeUiNode.Companion.getClass();
            Function0 function0 = ComposeUiNode.Companion.Constructor;
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function0);
            } else {
                composerImpl.useNode();
            }
            Function2 function2 = ComposeUiNode.Companion.SetMeasurePolicy;
            Updater.m259setimpl(composerImpl, measurePolicy, function2);
            Function2 function22 = ComposeUiNode.Companion.SetResolvedCompositionLocals;
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope, function22);
            Function2 function23 = ComposeUiNode.Companion.SetCompositeKeyHash;
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i4))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i4, composerImpl, i4, function23);
            }
            Function2 function24 = ComposeUiNode.Companion.SetModifier;
            Updater.m259setimpl(composerImpl, materializeModifier, function24);
            Modifier wrapContentWidth$default = SizeKt.wrapContentWidth$default(LayoutIdKt.layoutId(companion, SliderComponents.THUMB), 3);
            boolean changedInstance3 = composerImpl.changedInstance(sliderState);
            Object rememberedValue3 = composerImpl.rememberedValue();
            if (changedInstance3 || rememberedValue3 == composer$Companion$Empty$1) {
                rememberedValue3 = new Function1() { // from class: androidx.compose.material3.SliderKt$SliderImpl$1$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        long j = ((IntSize) obj).packedValue;
                        ((SnapshotMutableFloatStateImpl) SliderState.this.thumbWidth$delegate).setFloatValue((int) (j >> 32));
                        return Unit.INSTANCE;
                    }
                };
                composerImpl.updateRememberedValue(rememberedValue3);
            }
            Modifier onSizeChanged = OnRemeasuredModifierKt.onSizeChanged(wrapContentWidth$default, (Function1) rememberedValue3);
            BiasAlignment biasAlignment = Alignment.Companion.TopStart;
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
            int i5 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope2 = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composerImpl, onSizeChanged);
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function0);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy, function2);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope2, function22);
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i5))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i5, composerImpl, i5, function23);
            }
            Updater.m259setimpl(composerImpl, materializeModifier2, function24);
            int i6 = (i3 >> 3) & 14;
            function3.invoke(sliderState, composerImpl, Integer.valueOf(((i3 >> 9) & 112) | i6));
            composerImpl.end(true);
            Modifier layoutId = LayoutIdKt.layoutId(companion, SliderComponents.TRACK);
            MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(biasAlignment, false);
            int i7 = composerImpl.compoundKeyHash;
            PersistentCompositionLocalMap currentCompositionLocalScope3 = composerImpl.currentCompositionLocalScope();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composerImpl, layoutId);
            composerImpl.startReusableNode();
            if (composerImpl.inserting) {
                composerImpl.createNode(function0);
            } else {
                composerImpl.useNode();
            }
            Updater.m259setimpl(composerImpl, maybeCachedBoxMeasurePolicy2, function2);
            Updater.m259setimpl(composerImpl, currentCompositionLocalScope3, function22);
            if (composerImpl.inserting || !Intrinsics.areEqual(composerImpl.rememberedValue(), Integer.valueOf(i7))) {
                AnimatedContentKt$$ExternalSyntheticOutline0.m(i7, composerImpl, i7, function23);
            }
            Updater.m259setimpl(composerImpl, materializeModifier3, function24);
            function32.invoke(sliderState, composerImpl, Integer.valueOf(i6 | ((i3 >> 12) & 112)));
            composerImpl.end(true);
            composerImpl.end(true);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.material3.SliderKt$SliderImpl$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    SliderKt.SliderImpl(Modifier.this, sliderState, z, mutableInteractionSource, function3, function32, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                    return Unit.INSTANCE;
                }
            };
        }
    }

    public static final float access$snapValueToTick(float f, float[] fArr, float f2, float f3) {
        Float valueOf;
        if (fArr.length == 0) {
            valueOf = null;
        } else {
            float f4 = fArr[0];
            int length = fArr.length - 1;
            if (length == 0) {
                valueOf = Float.valueOf(f4);
            } else {
                float abs = Math.abs(MathHelpersKt.lerp(f2, f3, f4) - f);
                IntProgressionIterator it = new IntRange(1, length, 1).iterator();
                while (it.hasNext) {
                    float f5 = fArr[it.nextInt()];
                    float abs2 = Math.abs(MathHelpersKt.lerp(f2, f3, f5) - f);
                    if (Float.compare(abs, abs2) > 0) {
                        f4 = f5;
                        abs = abs2;
                    }
                }
                valueOf = Float.valueOf(f4);
            }
        }
        return valueOf != null ? MathHelpersKt.lerp(f2, f3, valueOf.floatValue()) : f;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x004e  */
    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.compose.material3.SliderKt$Slider$11, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r0v4, types: [androidx.compose.material3.SliderKt$Slider$10, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Slider(final androidx.compose.material3.SliderState r17, androidx.compose.ui.Modifier r18, boolean r19, androidx.compose.material3.SliderColors r20, androidx.compose.foundation.interaction.MutableInteractionSource r21, kotlin.jvm.functions.Function3 r22, kotlin.jvm.functions.Function3 r23, androidx.compose.runtime.Composer r24, final int r25, final int r26) {
        /*
            Method dump skipped, instructions count: 436
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.Slider(androidx.compose.material3.SliderState, androidx.compose.ui.Modifier, boolean, androidx.compose.material3.SliderColors, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }
}
