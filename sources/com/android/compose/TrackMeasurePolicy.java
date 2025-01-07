package com.android.compose;

import androidx.compose.material3.SliderState;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TrackMeasurePolicy implements MeasurePolicy {
    public final boolean enabled;
    public final boolean isRtl;
    public final Function1 onDrawingStateMeasured;
    public final SliderState sliderState;
    public final int thumbSize;

    public TrackMeasurePolicy(SliderState sliderState, boolean z, int i, boolean z2, Function1 function1) {
        this.sliderState = sliderState;
        this.enabled = z;
        this.thumbSize = i;
        this.isRtl = z2;
        this.onDrawingStateMeasured = function1;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo2measure3p2s80s(MeasureScope measureScope, List list, long j) {
        Object obj;
        Object obj2;
        DrawingState drawingState;
        MeasureResult layout$1;
        int m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(j) + this.thumbSize;
        int m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(j);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Measurable measurable = (Measurable) list.get(i);
            if (LayoutIdKt.getLayoutId(measurable) == TrackComponent.Background) {
                final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(ConstraintsKt.Constraints(m655getMaxWidthimpl, m655getMaxWidthimpl, m654getMaxHeightimpl, m654getMaxHeightimpl));
                int size2 = list.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size2) {
                        obj = null;
                        break;
                    }
                    obj = list.get(i2);
                    if (LayoutIdKt.getLayoutId((Measurable) obj) == TrackComponent.Icon) {
                        break;
                    }
                    i2++;
                }
                Measurable measurable2 = (Measurable) obj;
                final Placeable mo479measureBRTryo02 = measurable2 != null ? measurable2.mo479measureBRTryo0(ConstraintsKt.Constraints(m654getMaxHeightimpl, m654getMaxHeightimpl, m654getMaxHeightimpl, m654getMaxHeightimpl)) : null;
                int i3 = mo479measureBRTryo02 != null ? mo479measureBRTryo02.width : 0;
                int i4 = this.enabled ? (m655getMaxWidthimpl - i3) / 2 : m655getMaxWidthimpl - i3;
                int size3 = list.size();
                int i5 = 0;
                while (true) {
                    if (i5 >= size3) {
                        obj2 = null;
                        break;
                    }
                    obj2 = list.get(i5);
                    if (LayoutIdKt.getLayoutId((Measurable) obj2) == TrackComponent.Label) {
                        break;
                    }
                    i5++;
                }
                Measurable measurable3 = (Measurable) obj2;
                final Placeable mo479measureBRTryo03 = measurable3 != null ? measurable3.mo479measureBRTryo0(ConstraintsKt.Constraints(0, i4, m654getMaxHeightimpl, m654getMaxHeightimpl)) : null;
                boolean z = this.isRtl;
                SliderState sliderState = this.sliderState;
                if (z) {
                    float f = m655getMaxWidthimpl;
                    float f2 = m654getMaxHeightimpl;
                    drawingState = new DrawingState(true, f, f2, (1 - PlatformSliderKt.access$getCoercedNormalizedValue(sliderState)) * (m655getMaxWidthimpl - i3), f, f2, i3, mo479measureBRTryo03 != null ? mo479measureBRTryo03.width : 0.0f);
                } else {
                    float f3 = m654getMaxHeightimpl;
                    float f4 = i3;
                    drawingState = new DrawingState(false, m655getMaxWidthimpl, f3, 0.0f, (PlatformSliderKt.access$getCoercedNormalizedValue(sliderState) * (m655getMaxWidthimpl - i3)) + f4, f3, f4, mo479measureBRTryo03 != null ? mo479measureBRTryo03.width : 0.0f);
                }
                this.onDrawingStateMeasured.invoke(drawingState);
                layout$1 = measureScope.layout$1(m655getMaxWidthimpl, m654getMaxHeightimpl, MapsKt.emptyMap(), new Function1() { // from class: com.android.compose.TrackMeasurePolicy$measure$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj3;
                        placementScope.placeRelative(Placeable.this, 0, 0, TrackComponent.Background.getZIndex());
                        Placeable placeable = mo479measureBRTryo02;
                        if (placeable != null) {
                            placementScope.placeRelative(placeable, 0, 0, TrackComponent.Icon.getZIndex());
                        }
                        Placeable placeable2 = mo479measureBRTryo03;
                        if (placeable2 != null) {
                            placementScope.placeRelative(placeable2, 0, 0, TrackComponent.Label.getZIndex());
                        }
                        return Unit.INSTANCE;
                    }
                });
                return layout$1;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }
}
