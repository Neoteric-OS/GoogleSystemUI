package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyGridMeasuredItem implements LazyGridItemInfo, LazyLayoutMeasuredItem {
    public final int afterContentPadding;
    public final LazyLayoutItemAnimator animator;
    public final int beforeContentPadding;
    public int column;
    public final long constraints;
    public final Object contentType;
    public final int crossAxisSize;
    public final int index;
    public final boolean isVertical;
    public final Object key;
    public final int lane;
    public final LayoutDirection layoutDirection;
    public int mainAxisLayoutSize = Integer.MIN_VALUE;
    public final int mainAxisSize;
    public final int mainAxisSizeWithSpacings;
    public int maxMainAxisOffset;
    public int minMainAxisOffset;
    public boolean nonScrollableItem;
    public long offset;
    public final List placeables;
    public final boolean reverseLayout;
    public int row;
    public final long size;
    public final int span;
    public final long visualOffset;

    public LazyGridMeasuredItem(int i, Object obj, boolean z, int i2, int i3, boolean z2, LayoutDirection layoutDirection, int i4, int i5, List list, long j, Object obj2, LazyLayoutItemAnimator lazyLayoutItemAnimator, long j2, int i6, int i7) {
        this.index = i;
        this.key = obj;
        this.isVertical = z;
        this.crossAxisSize = i2;
        this.reverseLayout = z2;
        this.layoutDirection = layoutDirection;
        this.beforeContentPadding = i4;
        this.afterContentPadding = i5;
        this.placeables = list;
        this.visualOffset = j;
        this.contentType = obj2;
        this.animator = lazyLayoutItemAnimator;
        this.constraints = j2;
        this.lane = i6;
        this.span = i7;
        int size = list.size();
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            Placeable placeable = (Placeable) list.get(i9);
            i8 = Math.max(i8, this.isVertical ? placeable.height : placeable.width);
        }
        this.mainAxisSize = i8;
        int i10 = i8 + i3;
        this.mainAxisSizeWithSpacings = i10 >= 0 ? i10 : 0;
        this.size = this.isVertical ? (i8 & 4294967295L) | (this.crossAxisSize << 32) : (this.crossAxisSize & 4294967295L) | (i8 << 32);
        this.offset = 0L;
        this.row = -1;
        this.column = -1;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    /* renamed from: getConstraints-msEJaDk */
    public final long mo122getConstraintsmsEJaDk() {
        return this.constraints;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public final int getIndex() {
        return this.index;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public final Object getKey() {
        return this.key;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public final int getLane() {
        return this.lane;
    }

    /* renamed from: getMainAxis--gyyYBs$1, reason: not valid java name */
    public final int m128getMainAxisgyyYBs$1(long j) {
        return (int) (this.isVertical ? j & 4294967295L : j >> 32);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public final int getMainAxisSizeWithSpacings() {
        return this.mainAxisSizeWithSpacings;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    /* renamed from: getOffset-Bjo55l4 */
    public final long mo124getOffsetBjo55l4(int i) {
        return this.offset;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public final Object getParentData(int i) {
        return ((Placeable) this.placeables.get(i)).getParentData();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public final int getPlaceablesCount() {
        return this.placeables.size();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public final int getSpan() {
        return this.span;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public final boolean isVertical() {
        return this.isVertical;
    }

    public final void place(Placeable.PlacementScope placementScope) {
        GraphicsLayer graphicsLayer;
        if (this.mainAxisLayoutSize == Integer.MIN_VALUE) {
            InlineClassHelperKt.throwIllegalArgumentException("position() should be called first");
        }
        int size = this.placeables.size();
        for (int i = 0; i < size; i++) {
            Placeable placeable = (Placeable) this.placeables.get(i);
            int i2 = this.minMainAxisOffset;
            boolean z = this.isVertical;
            int i3 = i2 - (z ? placeable.height : placeable.width);
            int i4 = this.maxMainAxisOffset;
            long j = this.offset;
            LazyLayoutItemAnimation animation = this.animator.getAnimation(i, this.key);
            if (animation != null) {
                long m676plusqkQi6aY = IntOffset.m676plusqkQi6aY(j, ((IntOffset) ((SnapshotMutableStateImpl) animation.placementDelta$delegate).getValue()).packedValue);
                if ((m128getMainAxisgyyYBs$1(j) <= i3 && m128getMainAxisgyyYBs$1(m676plusqkQi6aY) <= i3) || (m128getMainAxisgyyYBs$1(j) >= i4 && m128getMainAxisgyyYBs$1(m676plusqkQi6aY) >= i4)) {
                    animation.cancelPlacementAnimation();
                }
                graphicsLayer = animation.layer;
                j = m676plusqkQi6aY;
            } else {
                graphicsLayer = null;
            }
            if (this.reverseLayout) {
                j = ((z ? (int) (j >> 32) : (this.mainAxisLayoutSize - ((int) (j >> 32))) - (z ? placeable.height : placeable.width)) << 32) | ((z ? (this.mainAxisLayoutSize - ((int) (j & 4294967295L))) - (z ? placeable.height : placeable.width) : (int) (j & 4294967295L)) & 4294967295L);
            }
            long m676plusqkQi6aY2 = IntOffset.m676plusqkQi6aY(j, this.visualOffset);
            if (animation != null) {
                animation.finalOffset = m676plusqkQi6aY2;
            }
            if (z) {
                if (graphicsLayer != null) {
                    placementScope.getClass();
                    Placeable.PlacementScope.access$handleMotionFrameOfReferencePlacement(placementScope, placeable);
                    placeable.mo492placeAtf8xVGno(IntOffset.m676plusqkQi6aY(m676plusqkQi6aY2, placeable.apparentToRealOffset), 0.0f, graphicsLayer);
                } else {
                    Placeable.PlacementScope.m498placeWithLayeraW9wM$default(placementScope, placeable, m676plusqkQi6aY2, null, 6);
                }
            } else if (graphicsLayer != null) {
                Placeable.PlacementScope.m497placeRelativeWithLayeraW9wM$default(placementScope, placeable, m676plusqkQi6aY2, graphicsLayer);
            } else {
                Placeable.PlacementScope.m496placeRelativeWithLayeraW9wM$default(placementScope, placeable, m676plusqkQi6aY2);
            }
        }
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public final void position(int i, int i2, int i3, int i4) {
        position(i, i2, i3, i4, -1, -1);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public final void setNonScrollableItem() {
        this.nonScrollableItem = true;
    }

    public final void position(int i, int i2, int i3, int i4, int i5, int i6) {
        long j;
        long j2;
        boolean z = this.isVertical;
        int i7 = z ? i4 : i3;
        this.mainAxisLayoutSize = i7;
        if (!z) {
            i3 = i4;
        }
        if (z) {
            if (this.layoutDirection == LayoutDirection.Rtl) {
                i2 = (i3 - i2) - this.crossAxisSize;
            }
        }
        if (z) {
            j = i2 << 32;
            j2 = i;
        } else {
            j = i << 32;
            j2 = i2;
        }
        this.offset = (j2 & 4294967295L) | j;
        this.row = i5;
        this.column = i6;
        this.minMainAxisOffset = -this.beforeContentPadding;
        this.maxMainAxisOffset = i7 + this.afterContentPadding;
    }
}
