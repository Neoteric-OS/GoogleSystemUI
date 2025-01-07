package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ViewBoundsCheck {
    public final BoundFlags mBoundFlags;
    public final RecyclerView.LayoutManager.AnonymousClass1 mCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BoundFlags {
        public int mBoundFlags;
        public int mChildEnd;
        public int mChildStart;
        public int mRvEnd;
        public int mRvStart;

        public final boolean boundsMatch() {
            int i = this.mBoundFlags;
            int i2 = 2;
            if ((i & 7) != 0) {
                int i3 = this.mChildStart;
                int i4 = this.mRvStart;
                if (((i3 > i4 ? 1 : i3 == i4 ? 2 : 4) & i) == 0) {
                    return false;
                }
            }
            if ((i & 112) != 0) {
                int i5 = this.mChildStart;
                int i6 = this.mRvEnd;
                if ((((i5 > i6 ? 1 : i5 == i6 ? 2 : 4) << 4) & i) == 0) {
                    return false;
                }
            }
            if ((i & 1792) != 0) {
                int i7 = this.mChildEnd;
                int i8 = this.mRvStart;
                if ((((i7 > i8 ? 1 : i7 == i8 ? 2 : 4) << 8) & i) == 0) {
                    return false;
                }
            }
            if ((i & 28672) != 0) {
                int i9 = this.mChildEnd;
                int i10 = this.mRvEnd;
                if (i9 > i10) {
                    i2 = 1;
                } else if (i9 != i10) {
                    i2 = 4;
                }
                if (((i2 << 12) & i) == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public ViewBoundsCheck(RecyclerView.LayoutManager.AnonymousClass1 anonymousClass1) {
        this.mCallback = anonymousClass1;
        BoundFlags boundFlags = new BoundFlags();
        boundFlags.mBoundFlags = 0;
        this.mBoundFlags = boundFlags;
    }

    public final View findOneViewWithinBoundFlags(int i, int i2, int i3, int i4) {
        View childAt;
        RecyclerView.LayoutManager.AnonymousClass1 anonymousClass1 = this.mCallback;
        int parentStart = anonymousClass1.getParentStart();
        int parentEnd = anonymousClass1.getParentEnd();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            switch (anonymousClass1.$r8$classId) {
                case 0:
                    childAt = anonymousClass1.this$0.getChildAt(i);
                    break;
                default:
                    childAt = anonymousClass1.this$0.getChildAt(i);
                    break;
            }
            int childStart = anonymousClass1.getChildStart(childAt);
            int childEnd = anonymousClass1.getChildEnd(childAt);
            BoundFlags boundFlags = this.mBoundFlags;
            boundFlags.mRvStart = parentStart;
            boundFlags.mRvEnd = parentEnd;
            boundFlags.mChildStart = childStart;
            boundFlags.mChildEnd = childEnd;
            if (i3 != 0) {
                boundFlags.mBoundFlags = i3;
                if (boundFlags.boundsMatch()) {
                    return childAt;
                }
            }
            if (i4 != 0) {
                boundFlags.mBoundFlags = i4;
                if (boundFlags.boundsMatch()) {
                    view = childAt;
                }
            }
            i += i5;
        }
        return view;
    }

    public final boolean isViewWithinBoundFlags(View view) {
        RecyclerView.LayoutManager.AnonymousClass1 anonymousClass1 = this.mCallback;
        int parentStart = anonymousClass1.getParentStart();
        int parentEnd = anonymousClass1.getParentEnd();
        int childStart = anonymousClass1.getChildStart(view);
        int childEnd = anonymousClass1.getChildEnd(view);
        BoundFlags boundFlags = this.mBoundFlags;
        boundFlags.mRvStart = parentStart;
        boundFlags.mRvEnd = parentEnd;
        boundFlags.mChildStart = childStart;
        boundFlags.mChildEnd = childEnd;
        boundFlags.mBoundFlags = 24579;
        return boundFlags.boundsMatch();
    }
}
