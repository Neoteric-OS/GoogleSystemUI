package com.android.wm.shell.windowdecor;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.Size;
import android.view.MotionEvent;
import android.window.flags.DesktopModeFlags;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DragResizeWindowGeometry {
    public final TaskCorners mFineTaskCorners;
    public final TaskCorners mLargeTaskCorners;
    public final int mResizeHandleEdgeInset;
    public final int mResizeHandleEdgeOutset;
    public final int mTaskCornerRadius;
    public final TaskEdges mTaskEdges;
    public final Size mTaskSize;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TaskCorners {
        public final int mCornerSize;
        public final Rect mLeftBottomCornerBounds;
        public final Rect mLeftTopCornerBounds;
        public final Rect mRightBottomCornerBounds;
        public final Rect mRightTopCornerBounds;

        public TaskCorners(int i, Size size) {
            this.mCornerSize = i;
            int i2 = i / 2;
            int i3 = -i2;
            this.mLeftTopCornerBounds = new Rect(i3, i3, i2, i2);
            this.mRightTopCornerBounds = new Rect(size.getWidth() - i2, i3, size.getWidth() + i2, i2);
            this.mLeftBottomCornerBounds = new Rect(i3, size.getHeight() - i2, i2, size.getHeight() + i2);
            this.mRightBottomCornerBounds = new Rect(size.getWidth() - i2, size.getHeight() - i2, size.getWidth() + i2, size.getHeight() + i2);
        }

        public final int calculateCornersCtrlType(float f, float f2) {
            int i = (int) f;
            int i2 = (int) f2;
            if (this.mLeftTopCornerBounds.contains(i, i2)) {
                return 5;
            }
            if (this.mLeftBottomCornerBounds.contains(i, i2)) {
                return 9;
            }
            if (this.mRightTopCornerBounds.contains(i, i2)) {
                return 6;
            }
            return this.mRightBottomCornerBounds.contains(i, i2) ? 10 : 0;
        }

        public final boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TaskCorners)) {
                return false;
            }
            TaskCorners taskCorners = (TaskCorners) obj;
            return this.mCornerSize == taskCorners.mCornerSize && this.mLeftTopCornerBounds.equals(taskCorners.mLeftTopCornerBounds) && this.mRightTopCornerBounds.equals(taskCorners.mRightTopCornerBounds) && this.mLeftBottomCornerBounds.equals(taskCorners.mLeftBottomCornerBounds) && this.mRightBottomCornerBounds.equals(taskCorners.mRightBottomCornerBounds);
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(this.mCornerSize), this.mLeftTopCornerBounds, this.mRightTopCornerBounds, this.mLeftBottomCornerBounds, this.mRightBottomCornerBounds);
        }

        public final String toString() {
            return "TaskCorners of size " + this.mCornerSize + " for the top left " + this.mLeftTopCornerBounds + " top right " + this.mRightTopCornerBounds + " bottom left " + this.mLeftBottomCornerBounds + " bottom right " + this.mRightBottomCornerBounds;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TaskEdges {
        public final Rect mBottomEdgeBounds;
        public final Rect mLeftEdgeBounds;
        public final Rect mRightEdgeBounds;
        public final Rect mTopEdgeBounds;

        public TaskEdges(Size size, int i, int i2) {
            int i3 = -i;
            Rect rect = new Rect(i3, i3, size.getWidth() + i, i);
            this.mTopEdgeBounds = rect;
            Rect rect2 = new Rect(i3, 0, i2, size.getHeight());
            this.mLeftEdgeBounds = rect2;
            Rect rect3 = new Rect(size.getWidth() - i2, 0, size.getWidth() + i, size.getHeight());
            this.mRightEdgeBounds = rect3;
            Rect rect4 = new Rect(i3, size.getHeight() - i2, size.getWidth() + i, size.getHeight() + i);
            this.mBottomEdgeBounds = rect4;
            Region region = new Region();
            region.union(rect);
            region.union(rect2);
            region.union(rect3);
            region.union(rect4);
        }

        public final boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TaskEdges)) {
                return false;
            }
            TaskEdges taskEdges = (TaskEdges) obj;
            return this.mTopEdgeBounds.equals(taskEdges.mTopEdgeBounds) && this.mLeftEdgeBounds.equals(taskEdges.mLeftEdgeBounds) && this.mRightEdgeBounds.equals(taskEdges.mRightEdgeBounds) && this.mBottomEdgeBounds.equals(taskEdges.mBottomEdgeBounds);
        }

        public final int hashCode() {
            return Objects.hash(this.mTopEdgeBounds, this.mLeftEdgeBounds, this.mRightEdgeBounds, this.mBottomEdgeBounds);
        }

        public final String toString() {
            return "TaskEdges for the top " + this.mTopEdgeBounds + " left " + this.mLeftEdgeBounds + " right " + this.mRightEdgeBounds + " bottom " + this.mBottomEdgeBounds;
        }
    }

    public DragResizeWindowGeometry(int i, Size size, int i2, int i3, int i4, int i5) {
        this.mTaskCornerRadius = i;
        this.mTaskSize = size;
        this.mResizeHandleEdgeOutset = i2;
        this.mResizeHandleEdgeInset = i3;
        this.mLargeTaskCorners = new TaskCorners(i5, size);
        this.mFineTaskCorners = new TaskCorners(i4, size);
        this.mTaskEdges = new TaskEdges(size, i2, i3);
    }

    public static boolean isEdgeResizePermitted(MotionEvent motionEvent) {
        if (!DesktopModeFlags.ENABLE_WINDOWING_EDGE_DRAG_RESIZE.isTrue()) {
            return motionEvent.getToolType(0) == 3;
        }
        if (motionEvent.getToolType(0) == 2 || motionEvent.getToolType(0) == 3) {
            return true;
        }
        return motionEvent.isFromSource(8194) && motionEvent.getToolType(0) == 1;
    }

    public final int calculateCtrlType(float f, float f2, boolean z, boolean z2) {
        boolean isTrue = DesktopModeFlags.ENABLE_WINDOWING_EDGE_DRAG_RESIZE.isTrue();
        TaskCorners taskCorners = this.mFineTaskCorners;
        if (!isTrue) {
            return z ? taskCorners.calculateCornersCtrlType(f, f2) : calculateEdgeResizeCtrlType(f, f2);
        }
        int calculateCornersCtrlType = z ? this.mLargeTaskCorners.calculateCornersCtrlType(f, f2) : taskCorners.calculateCornersCtrlType(f, f2);
        return (calculateCornersCtrlType == 0 && z2) ? calculateEdgeResizeCtrlType(f, f2) : calculateCornersCtrlType;
    }

    public final int calculateEdgeResizeCtrlType(float f, float f2) {
        int i;
        int i2;
        int i3 = this.mTaskCornerRadius;
        int i4 = f < ((float) i3) ? 1 : 0;
        if (f > this.mTaskSize.getWidth() - i3) {
            i4 |= 2;
        }
        if (f2 < i3) {
            i4 |= 4;
        }
        if (f2 > this.mTaskSize.getHeight() - i3) {
            i4 |= 8;
        }
        if ((i4 & 3) == 0 || (i4 & 12) == 0) {
            int i5 = this.mResizeHandleEdgeInset;
            if (f <= i5 || f2 <= i5 || f >= this.mTaskSize.getWidth() - i5 || f2 >= this.mTaskSize.getHeight() - i5) {
                return i4;
            }
            return 0;
        }
        if (i4 == 5) {
            i = i3;
            i2 = i;
        } else if (i4 == 6) {
            i = this.mTaskSize.getWidth() - i3;
            i2 = i3;
        } else if (i4 == 9) {
            i2 = this.mTaskSize.getHeight() - i3;
            i = i3;
        } else {
            if (i4 != 10) {
                throw new IllegalArgumentException("ctrlType should be complex, but it's 0x" + Integer.toHexString(i4));
            }
            i = this.mTaskSize.getWidth() - i3;
            i2 = this.mTaskSize.getHeight() - i3;
        }
        Point point = new Point(i, i2);
        double hypot = Math.hypot(f - point.x, f2 - point.y);
        if (hypot >= this.mResizeHandleEdgeOutset + i3 || hypot < i3) {
            return 0;
        }
        return i4;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DragResizeWindowGeometry)) {
            return false;
        }
        DragResizeWindowGeometry dragResizeWindowGeometry = (DragResizeWindowGeometry) obj;
        return this.mTaskCornerRadius == dragResizeWindowGeometry.mTaskCornerRadius && this.mTaskSize.equals(dragResizeWindowGeometry.mTaskSize) && this.mResizeHandleEdgeOutset == dragResizeWindowGeometry.mResizeHandleEdgeOutset && this.mResizeHandleEdgeInset == dragResizeWindowGeometry.mResizeHandleEdgeInset && this.mFineTaskCorners.equals(dragResizeWindowGeometry.mFineTaskCorners) && this.mLargeTaskCorners.equals(dragResizeWindowGeometry.mLargeTaskCorners) && this.mTaskEdges.equals(dragResizeWindowGeometry.mTaskEdges);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.mTaskCornerRadius), this.mTaskSize, Integer.valueOf(this.mResizeHandleEdgeOutset), Integer.valueOf(this.mResizeHandleEdgeInset), this.mFineTaskCorners, this.mLargeTaskCorners, this.mTaskEdges);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x002d, code lost:
    
        r8 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean shouldHandleEvent(android.view.MotionEvent r7, android.graphics.Point r8) {
        /*
            r6 = this;
            r0 = 0
            float r1 = r7.getX(r0)
            int r2 = r8.x
            float r2 = (float) r2
            float r1 = r1 + r2
            float r2 = r7.getY(r0)
            int r8 = r8.y
            float r8 = (float) r8
            float r2 = r2 + r8
            android.window.flags.DesktopModeFlags r8 = android.window.flags.DesktopModeFlags.ENABLE_WINDOWING_EDGE_DRAG_RESIZE
            boolean r8 = r8.isTrue()
            r3 = 4098(0x1002, float:5.743E-42)
            r4 = 1
            com.android.wm.shell.windowdecor.DragResizeWindowGeometry$TaskCorners r5 = r6.mFineTaskCorners
            if (r8 == 0) goto L49
            int r8 = r7.getSource()
            r8 = r8 & r3
            if (r8 != r3) goto L31
            com.android.wm.shell.windowdecor.DragResizeWindowGeometry$TaskCorners r8 = r6.mLargeTaskCorners
            int r8 = r8.calculateCornersCtrlType(r1, r2)
            if (r8 == 0) goto L2f
        L2d:
            r8 = r4
            goto L38
        L2f:
            r8 = r0
            goto L38
        L31:
            int r8 = r5.calculateCornersCtrlType(r1, r2)
            if (r8 == 0) goto L2f
            goto L2d
        L38:
            if (r8 != 0) goto L48
            boolean r7 = isEdgeResizePermitted(r7)
            if (r7 == 0) goto L48
            int r6 = r6.calculateEdgeResizeCtrlType(r1, r2)
            if (r6 == 0) goto L47
            r0 = r4
        L47:
            r8 = r0
        L48:
            return r8
        L49:
            int r7 = r7.getSource()
            r7 = r7 & r3
            if (r7 != r3) goto L58
            int r6 = r5.calculateCornersCtrlType(r1, r2)
            if (r6 == 0) goto L5f
        L56:
            r0 = r4
            goto L5f
        L58:
            int r6 = r6.calculateEdgeResizeCtrlType(r1, r2)
            if (r6 == 0) goto L5f
            goto L56
        L5f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.windowdecor.DragResizeWindowGeometry.shouldHandleEvent(android.view.MotionEvent, android.graphics.Point):boolean");
    }

    public final void union(Region region) {
        TaskEdges taskEdges = this.mTaskEdges;
        region.union(taskEdges.mTopEdgeBounds);
        region.union(taskEdges.mLeftEdgeBounds);
        region.union(taskEdges.mRightEdgeBounds);
        region.union(taskEdges.mBottomEdgeBounds);
        if (DesktopModeFlags.ENABLE_WINDOWING_EDGE_DRAG_RESIZE.isTrue()) {
            TaskCorners taskCorners = this.mLargeTaskCorners;
            region.union(taskCorners.mLeftTopCornerBounds);
            region.union(taskCorners.mRightTopCornerBounds);
            region.union(taskCorners.mLeftBottomCornerBounds);
            region.union(taskCorners.mRightBottomCornerBounds);
            return;
        }
        TaskCorners taskCorners2 = this.mFineTaskCorners;
        region.union(taskCorners2.mLeftTopCornerBounds);
        region.union(taskCorners2.mRightTopCornerBounds);
        region.union(taskCorners2.mLeftBottomCornerBounds);
        region.union(taskCorners2.mRightBottomCornerBounds);
    }
}
