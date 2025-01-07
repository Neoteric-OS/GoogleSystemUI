package androidx.compose.ui.layout;

import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.node.MotionReferencePlacementDelegate;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Placeable implements Measured {
    public int height;
    public long measuredSize;
    public int width;
    public long measurementConstraints = PlaceableKt.DefaultConstraints;
    public long apparentToRealOffset = 0;

    public Placeable() {
        long j = 0;
        this.measuredSize = (j & 4294967295L) | (j << 32);
    }

    public int getMeasuredHeight() {
        return (int) (this.measuredSize & 4294967295L);
    }

    public int getMeasuredWidth() {
        return (int) (this.measuredSize >> 32);
    }

    public final void onMeasuredSizeChanged() {
        this.width = RangesKt.coerceIn((int) (this.measuredSize >> 32), Constraints.m657getMinWidthimpl(this.measurementConstraints), Constraints.m655getMaxWidthimpl(this.measurementConstraints));
        this.height = RangesKt.coerceIn((int) (this.measuredSize & 4294967295L), Constraints.m656getMinHeightimpl(this.measurementConstraints), Constraints.m654getMaxHeightimpl(this.measurementConstraints));
        int i = this.width;
        long j = this.measuredSize;
        this.apparentToRealOffset = (((i - ((int) (j >> 32))) / 2) << 32) | (4294967295L & ((r0 - ((int) (j & 4294967295L))) / 2));
    }

    /* renamed from: placeAt-f8xVGno, reason: not valid java name */
    public void mo492placeAtf8xVGno(long j, float f, GraphicsLayer graphicsLayer) {
        mo480placeAtf8xVGno(j, f, (Function1) null);
    }

    /* renamed from: placeAt-f8xVGno */
    public abstract void mo480placeAtf8xVGno(long j, float f, Function1 function1);

    /* renamed from: setMeasuredSize-ozmzZPI, reason: not valid java name */
    public final void m493setMeasuredSizeozmzZPI(long j) {
        if (IntSize.m683equalsimpl0(this.measuredSize, j)) {
            return;
        }
        this.measuredSize = j;
        onMeasuredSizeChanged();
    }

    /* renamed from: setMeasurementConstraints-BRTryo0, reason: not valid java name */
    public final void m494setMeasurementConstraintsBRTryo0(long j) {
        if (Constraints.m649equalsimpl0(this.measurementConstraints, j)) {
            return;
        }
        this.measurementConstraints = j;
        onMeasuredSizeChanged();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class PlacementScope {
        public boolean motionFrameOfReferencePlacement;

        /* JADX WARN: Multi-variable type inference failed */
        public static final void access$handleMotionFrameOfReferencePlacement(PlacementScope placementScope, Placeable placeable) {
            placementScope.getClass();
            if (placeable instanceof MotionReferencePlacementDelegate) {
                ((MotionReferencePlacementDelegate) placeable).setPlacedUnderMotionFrameOfReference(placementScope.motionFrameOfReferencePlacement);
            }
        }

        /* renamed from: place-70tqf50$default, reason: not valid java name */
        public static void m495place70tqf50$default(PlacementScope placementScope, Placeable placeable, long j) {
            placementScope.getClass();
            access$handleMotionFrameOfReferencePlacement(placementScope, placeable);
            placeable.mo480placeAtf8xVGno(IntOffset.m676plusqkQi6aY(j, placeable.apparentToRealOffset), 0.0f, (Function1) null);
        }

        public static void placeRelativeWithLayer$default(PlacementScope placementScope, Placeable placeable, int i, int i2) {
            Function1 function1 = PlaceableKt.DefaultLayerBlock;
            long j = (i << 32) | (i2 & 4294967295L);
            if (placementScope.getParentLayoutDirection() == LayoutDirection.Ltr || placementScope.getParentWidth() == 0) {
                access$handleMotionFrameOfReferencePlacement(placementScope, placeable);
                placeable.mo480placeAtf8xVGno(IntOffset.m676plusqkQi6aY(j, placeable.apparentToRealOffset), 0.0f, function1);
            } else {
                int parentWidth = (placementScope.getParentWidth() - placeable.width) - ((int) (j >> 32));
                access$handleMotionFrameOfReferencePlacement(placementScope, placeable);
                placeable.mo480placeAtf8xVGno(IntOffset.m676plusqkQi6aY((parentWidth << 32) | (((int) (j & 4294967295L)) & 4294967295L), placeable.apparentToRealOffset), 0.0f, function1);
            }
        }

        /* renamed from: placeRelativeWithLayer-aW-9-wM$default, reason: not valid java name */
        public static void m497placeRelativeWithLayeraW9wM$default(PlacementScope placementScope, Placeable placeable, long j, GraphicsLayer graphicsLayer) {
            if (placementScope.getParentLayoutDirection() == LayoutDirection.Ltr || placementScope.getParentWidth() == 0) {
                access$handleMotionFrameOfReferencePlacement(placementScope, placeable);
                placeable.mo492placeAtf8xVGno(IntOffset.m676plusqkQi6aY(j, placeable.apparentToRealOffset), 0.0f, graphicsLayer);
            } else {
                int parentWidth = (placementScope.getParentWidth() - placeable.width) - ((int) (j >> 32));
                access$handleMotionFrameOfReferencePlacement(placementScope, placeable);
                placeable.mo492placeAtf8xVGno(IntOffset.m676plusqkQi6aY((((int) (j & 4294967295L)) & 4294967295L) | (parentWidth << 32), placeable.apparentToRealOffset), 0.0f, graphicsLayer);
            }
        }

        public static void placeWithLayer$default(PlacementScope placementScope, Placeable placeable, int i, int i2, Function1 function1, int i3) {
            if ((i3 & 8) != 0) {
                function1 = PlaceableKt.DefaultLayerBlock;
            }
            placementScope.getClass();
            access$handleMotionFrameOfReferencePlacement(placementScope, placeable);
            placeable.mo480placeAtf8xVGno(IntOffset.m676plusqkQi6aY((i2 & 4294967295L) | (i << 32), placeable.apparentToRealOffset), 0.0f, function1);
        }

        /* renamed from: placeWithLayer-aW-9-wM$default, reason: not valid java name */
        public static void m498placeWithLayeraW9wM$default(PlacementScope placementScope, Placeable placeable, long j, Function1 function1, int i) {
            if ((i & 4) != 0) {
                function1 = PlaceableKt.DefaultLayerBlock;
            }
            placementScope.getClass();
            access$handleMotionFrameOfReferencePlacement(placementScope, placeable);
            placeable.mo480placeAtf8xVGno(IntOffset.m676plusqkQi6aY(j, placeable.apparentToRealOffset), 0.0f, function1);
        }

        public abstract LayoutCoordinates getCoordinates();

        public abstract LayoutDirection getParentLayoutDirection();

        public abstract int getParentWidth();

        public final void place(Placeable placeable, int i, int i2, float f) {
            access$handleMotionFrameOfReferencePlacement(this, placeable);
            placeable.mo480placeAtf8xVGno(IntOffset.m676plusqkQi6aY((i2 & 4294967295L) | (i << 32), placeable.apparentToRealOffset), f, (Function1) null);
        }

        public final void placeRelative(Placeable placeable, int i, int i2, float f) {
            long j = (i << 32) | (i2 & 4294967295L);
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                access$handleMotionFrameOfReferencePlacement(this, placeable);
                placeable.mo480placeAtf8xVGno(IntOffset.m676plusqkQi6aY(j, placeable.apparentToRealOffset), f, (Function1) null);
            } else {
                int parentWidth = (getParentWidth() - placeable.width) - ((int) (j >> 32));
                access$handleMotionFrameOfReferencePlacement(this, placeable);
                placeable.mo480placeAtf8xVGno(IntOffset.m676plusqkQi6aY((parentWidth << 32) | (((int) (j & 4294967295L)) & 4294967295L), placeable.apparentToRealOffset), f, (Function1) null);
            }
        }

        /* renamed from: placeRelativeWithLayer-aW-9-wM$default, reason: not valid java name */
        public static void m496placeRelativeWithLayeraW9wM$default(PlacementScope placementScope, Placeable placeable, long j) {
            Function1 function1 = PlaceableKt.DefaultLayerBlock;
            if (placementScope.getParentLayoutDirection() != LayoutDirection.Ltr && placementScope.getParentWidth() != 0) {
                int parentWidth = (placementScope.getParentWidth() - placeable.width) - ((int) (j >> 32));
                access$handleMotionFrameOfReferencePlacement(placementScope, placeable);
                placeable.mo480placeAtf8xVGno(IntOffset.m676plusqkQi6aY((((int) (j & 4294967295L)) & 4294967295L) | (parentWidth << 32), placeable.apparentToRealOffset), 0.0f, function1);
                return;
            }
            access$handleMotionFrameOfReferencePlacement(placementScope, placeable);
            placeable.mo480placeAtf8xVGno(IntOffset.m676plusqkQi6aY(j, placeable.apparentToRealOffset), 0.0f, function1);
        }
    }
}
