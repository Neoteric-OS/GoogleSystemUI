package androidx.compose.foundation.lazy;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyListMeasuredItem implements LazyListItemInfo, LazyLayoutMeasuredItem {
    public final int afterContentPadding;
    public final LazyLayoutItemAnimator animator;
    public final int beforeContentPadding;
    public final long constraints;
    public final Object contentType;
    public final int crossAxisSize;
    public final Alignment.Horizontal horizontalAlignment;
    public final int index;
    public final boolean isVertical;
    public final Object key;
    public final LayoutDirection layoutDirection;
    public int mainAxisLayoutSize = Integer.MIN_VALUE;
    public final int mainAxisSizeWithSpacings;
    public int maxMainAxisOffset;
    public int minMainAxisOffset;
    public boolean nonScrollableItem;
    public int offset;
    public final int[] placeableOffsets;
    public final List placeables;
    public final boolean reverseLayout;
    public final int size;
    public final int spacing;
    public final Alignment.Vertical verticalAlignment;
    public final long visualOffset;

    public LazyListMeasuredItem(int i, List list, boolean z, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z2, int i2, int i3, int i4, long j, Object obj, Object obj2, LazyLayoutItemAnimator lazyLayoutItemAnimator, long j2) {
        this.index = i;
        this.placeables = list;
        this.isVertical = z;
        this.horizontalAlignment = horizontal;
        this.verticalAlignment = vertical;
        this.layoutDirection = layoutDirection;
        this.reverseLayout = z2;
        this.beforeContentPadding = i2;
        this.afterContentPadding = i3;
        this.spacing = i4;
        this.visualOffset = j;
        this.key = obj;
        this.contentType = obj2;
        this.animator = lazyLayoutItemAnimator;
        this.constraints = j2;
        int size = list.size();
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            Placeable placeable = (Placeable) list.get(i7);
            boolean z3 = this.isVertical;
            i5 += z3 ? placeable.height : placeable.width;
            i6 = Math.max(i6, !z3 ? placeable.height : placeable.width);
        }
        this.size = i5;
        int i8 = i5 + this.spacing;
        this.mainAxisSizeWithSpacings = i8 >= 0 ? i8 : 0;
        this.crossAxisSize = i6;
        this.placeableOffsets = new int[this.placeables.size() * 2];
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    /* renamed from: getConstraints-msEJaDk, reason: not valid java name */
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
        return 0;
    }

    /* renamed from: getMainAxis--gyyYBs, reason: not valid java name */
    public final int m123getMainAxisgyyYBs(long j) {
        return (int) (this.isVertical ? j & 4294967295L : j >> 32);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public final int getMainAxisSizeWithSpacings() {
        return this.mainAxisSizeWithSpacings;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    /* renamed from: getOffset-Bjo55l4, reason: not valid java name */
    public final long mo124getOffsetBjo55l4(int i) {
        int[] iArr = this.placeableOffsets;
        return (iArr[r5 + 1] & 4294967295L) | (iArr[i * 2] << 32);
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
        return 1;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public final boolean isVertical() {
        return this.isVertical;
    }

    public final void place(Placeable.PlacementScope placementScope, boolean z) {
        GraphicsLayer graphicsLayer;
        if (this.mainAxisLayoutSize == Integer.MIN_VALUE) {
            InlineClassHelperKt.throwIllegalArgumentException("position() should be called first");
        }
        int size = this.placeables.size();
        for (int i = 0; i < size; i++) {
            Placeable placeable = (Placeable) this.placeables.get(i);
            int i2 = this.minMainAxisOffset;
            boolean z2 = this.isVertical;
            int i3 = i2 - (z2 ? placeable.height : placeable.width);
            int i4 = this.maxMainAxisOffset;
            long mo124getOffsetBjo55l4 = mo124getOffsetBjo55l4(i);
            LazyLayoutItemAnimation animation = this.animator.getAnimation(i, this.key);
            if (animation != null) {
                if (z) {
                    animation.lookaheadOffset = mo124getOffsetBjo55l4;
                } else {
                    if (!IntOffset.m674equalsimpl0(animation.lookaheadOffset, LazyLayoutItemAnimation.NotInitialized)) {
                        mo124getOffsetBjo55l4 = animation.lookaheadOffset;
                    }
                    long m676plusqkQi6aY = IntOffset.m676plusqkQi6aY(mo124getOffsetBjo55l4, ((IntOffset) ((SnapshotMutableStateImpl) animation.placementDelta$delegate).getValue()).packedValue);
                    if ((m123getMainAxisgyyYBs(mo124getOffsetBjo55l4) <= i3 && m123getMainAxisgyyYBs(m676plusqkQi6aY) <= i3) || (m123getMainAxisgyyYBs(mo124getOffsetBjo55l4) >= i4 && m123getMainAxisgyyYBs(m676plusqkQi6aY) >= i4)) {
                        animation.cancelPlacementAnimation();
                    }
                    mo124getOffsetBjo55l4 = m676plusqkQi6aY;
                }
                graphicsLayer = animation.layer;
            } else {
                graphicsLayer = null;
            }
            if (this.reverseLayout) {
                mo124getOffsetBjo55l4 = z2 ? (((int) (mo124getOffsetBjo55l4 >> 32)) << 32) | (4294967295L & ((this.mainAxisLayoutSize - ((int) (mo124getOffsetBjo55l4 & 4294967295L))) - (z2 ? placeable.height : placeable.width))) : (((int) (mo124getOffsetBjo55l4 & 4294967295L)) & 4294967295L) | (((this.mainAxisLayoutSize - ((int) (mo124getOffsetBjo55l4 >> 32))) - (z2 ? placeable.height : placeable.width)) << 32);
            }
            long m676plusqkQi6aY2 = IntOffset.m676plusqkQi6aY(mo124getOffsetBjo55l4, this.visualOffset);
            if (!z && animation != null) {
                animation.finalOffset = m676plusqkQi6aY2;
            }
            if (z2) {
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
        position(i, i3, i4);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public final void setNonScrollableItem() {
        this.nonScrollableItem = true;
    }

    public final void position(int i, int i2, int i3) {
        int i4;
        this.offset = i;
        boolean z = this.isVertical;
        this.mainAxisLayoutSize = z ? i3 : i2;
        List list = this.placeables;
        int size = list.size();
        for (int i5 = 0; i5 < size; i5++) {
            Placeable placeable = (Placeable) list.get(i5);
            int i6 = i5 * 2;
            int[] iArr = this.placeableOffsets;
            if (z) {
                Alignment.Horizontal horizontal = this.horizontalAlignment;
                if (horizontal == null) {
                    InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null horizontalAlignment when isVertical == true");
                    throw null;
                }
                iArr[i6] = horizontal.align(placeable.width, i2, this.layoutDirection);
                iArr[i6 + 1] = i;
                i4 = placeable.height;
            } else {
                iArr[i6] = i;
                int i7 = i6 + 1;
                Alignment.Vertical vertical = this.verticalAlignment;
                if (vertical == null) {
                    InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null verticalAlignment when isVertical == false");
                    throw null;
                }
                iArr[i7] = ((BiasAlignment.Vertical) vertical).align(placeable.height, i3);
                i4 = placeable.width;
            }
            i += i4;
        }
        this.minMainAxisOffset = -this.beforeContentPadding;
        this.maxMainAxisOffset = this.mainAxisLayoutSize + this.afterContentPadding;
    }
}
