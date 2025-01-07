package androidx.compose.foundation.layout;

import androidx.collection.IntIntPair;
import androidx.compose.ui.unit.Constraints;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FlowLayoutBuildingBlocks {
    public final long constraints;
    public final int crossAxisSpacing;
    public final int mainAxisSpacing;
    public final int maxItemsInMainAxis;
    public final int maxLines;
    public final FlowLayoutOverflowState overflow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class WrapEllipsisInfo {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WrapInfo {
        public final boolean isLastItemInContainer;
        public final boolean isLastItemInLine;

        public WrapInfo(boolean z, boolean z2) {
            this.isLastItemInLine = z;
            this.isLastItemInContainer = z2;
        }
    }

    public FlowLayoutBuildingBlocks(int i, FlowLayoutOverflowState flowLayoutOverflowState, long j, int i2, int i3, int i4) {
        this.maxItemsInMainAxis = i;
        this.overflow = flowLayoutOverflowState;
        this.constraints = j;
        this.maxLines = i2;
        this.mainAxisSpacing = i3;
        this.crossAxisSpacing = i4;
    }

    public final WrapEllipsisInfo getWrapEllipsisInfo(WrapInfo wrapInfo, boolean z, int i, int i2, int i3, int i4) {
        if (!wrapInfo.isLastItemInContainer) {
            return null;
        }
        this.overflow.getClass();
        return null;
    }

    /* renamed from: getWrapInfo-OpUlnko, reason: not valid java name */
    public final WrapInfo m88getWrapInfoOpUlnko(boolean z, int i, long j, IntIntPair intIntPair, int i2, int i3, int i4, boolean z2, boolean z3) {
        int i5 = i3 + i4;
        if (intIntPair == null) {
            return new WrapInfo(true, true);
        }
        this.overflow.getClass();
        if (i2 < this.maxLines) {
            long j2 = intIntPair.packedValue;
            if (((int) (j & 4294967295L)) - ((int) (j2 & 4294967295L)) >= 0) {
                if (i != 0 && (i >= this.maxItemsInMainAxis || ((int) (j >> 32)) - ((int) (j2 >> 32)) < 0)) {
                    return z2 ? new WrapInfo(true, true) : new WrapInfo(true, m88getWrapInfoOpUlnko(z, 0, IntIntPair.m0constructorimpl(Constraints.m655getMaxWidthimpl(this.constraints), (((int) (j & 4294967295L)) - this.crossAxisSpacing) - i4), new IntIntPair(IntIntPair.m0constructorimpl(((int) (j2 >> 32)) - this.mainAxisSpacing, (int) (j2 & 4294967295L))), i2 + 1, i5, 0, true, false).isLastItemInContainer);
                }
                Math.max(i4, (int) (j2 & 4294967295L));
                return new WrapInfo(false, false);
            }
        }
        return new WrapInfo(true, true);
    }
}
