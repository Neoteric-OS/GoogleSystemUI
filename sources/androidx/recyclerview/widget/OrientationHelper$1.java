package androidx.recyclerview.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OrientationHelper$1 {
    public final /* synthetic */ int $r8$classId;
    public final RecyclerView.LayoutManager mLayoutManager;
    public int mLastTotalSpace = Integer.MIN_VALUE;
    public final Rect mTmpRect = new Rect();

    public OrientationHelper$1(RecyclerView.LayoutManager layoutManager, int i) {
        this.$r8$classId = i;
        this.mLayoutManager = layoutManager;
    }

    public static OrientationHelper$1 createOrientationHelper(RecyclerView.LayoutManager layoutManager, int i) {
        if (i == 0) {
            return new OrientationHelper$1(layoutManager, 0);
        }
        if (i == 1) {
            return new OrientationHelper$1(layoutManager, 1);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    public final int getDecoratedEnd(View view) {
        switch (this.$r8$classId) {
            case 0:
                return this.mLayoutManager.getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).rightMargin;
            default:
                return this.mLayoutManager.getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).bottomMargin;
        }
    }

    public final int getDecoratedMeasurement(View view) {
        switch (this.$r8$classId) {
            case 0:
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                this.mLayoutManager.getClass();
                return RecyclerView.LayoutManager.getDecoratedMeasuredWidth(view) + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            default:
                RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) view.getLayoutParams();
                this.mLayoutManager.getClass();
                return RecyclerView.LayoutManager.getDecoratedMeasuredHeight(view) + ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin;
        }
    }

    public final int getDecoratedMeasurementInOther(View view) {
        switch (this.$r8$classId) {
            case 0:
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                this.mLayoutManager.getClass();
                return RecyclerView.LayoutManager.getDecoratedMeasuredHeight(view) + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            default:
                RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) view.getLayoutParams();
                this.mLayoutManager.getClass();
                return RecyclerView.LayoutManager.getDecoratedMeasuredWidth(view) + ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin + ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin;
        }
    }

    public final int getDecoratedStart(View view) {
        switch (this.$r8$classId) {
            case 0:
                return this.mLayoutManager.getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).leftMargin;
            default:
                return this.mLayoutManager.getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).topMargin;
        }
    }

    public final int getEnd() {
        switch (this.$r8$classId) {
            case 0:
                return this.mLayoutManager.mWidth;
            default:
                return this.mLayoutManager.mHeight;
        }
    }

    public final int getEndAfterPadding() {
        switch (this.$r8$classId) {
            case 0:
                RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
                return layoutManager.mWidth - layoutManager.getPaddingRight();
            default:
                RecyclerView.LayoutManager layoutManager2 = this.mLayoutManager;
                return layoutManager2.mHeight - layoutManager2.getPaddingBottom();
        }
    }

    public final int getEndPadding() {
        switch (this.$r8$classId) {
            case 0:
                return this.mLayoutManager.getPaddingRight();
            default:
                return this.mLayoutManager.getPaddingBottom();
        }
    }

    public final int getMode() {
        switch (this.$r8$classId) {
            case 0:
                return this.mLayoutManager.mWidthMode;
            default:
                return this.mLayoutManager.mHeightMode;
        }
    }

    public final int getStartAfterPadding() {
        switch (this.$r8$classId) {
            case 0:
                return this.mLayoutManager.getPaddingLeft();
            default:
                return this.mLayoutManager.getPaddingTop();
        }
    }

    public final int getTotalSpace() {
        switch (this.$r8$classId) {
            case 0:
                RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
                return (layoutManager.mWidth - layoutManager.getPaddingLeft()) - layoutManager.getPaddingRight();
            default:
                RecyclerView.LayoutManager layoutManager2 = this.mLayoutManager;
                return (layoutManager2.mHeight - layoutManager2.getPaddingTop()) - layoutManager2.getPaddingBottom();
        }
    }

    public final int getTotalSpaceChange() {
        if (Integer.MIN_VALUE == this.mLastTotalSpace) {
            return 0;
        }
        return getTotalSpace() - this.mLastTotalSpace;
    }

    public final int getTransformedEndWithDecoration(View view) {
        switch (this.$r8$classId) {
            case 0:
                this.mLayoutManager.getTransformedBoundingBox(this.mTmpRect, view);
                return this.mTmpRect.right;
            default:
                this.mLayoutManager.getTransformedBoundingBox(this.mTmpRect, view);
                return this.mTmpRect.bottom;
        }
    }

    public final int getTransformedStartWithDecoration(View view) {
        switch (this.$r8$classId) {
            case 0:
                this.mLayoutManager.getTransformedBoundingBox(this.mTmpRect, view);
                return this.mTmpRect.left;
            default:
                this.mLayoutManager.getTransformedBoundingBox(this.mTmpRect, view);
                return this.mTmpRect.top;
        }
    }

    public final void offsetChildren(int i) {
        switch (this.$r8$classId) {
            case 0:
                this.mLayoutManager.offsetChildrenHorizontal(i);
                break;
            default:
                this.mLayoutManager.offsetChildrenVertical(i);
                break;
        }
    }
}
