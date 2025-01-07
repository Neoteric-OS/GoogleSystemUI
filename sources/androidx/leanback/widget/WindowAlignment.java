package androidx.leanback.widget;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowAlignment {
    public final Axis horizontal;
    public final Axis mMainAxis;
    public final Axis mSecondAxis;
    public final Axis vertical;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Axis {
        public int mMaxScroll;
        public int mMinScroll;
        public int mMinEdge = Integer.MIN_VALUE;
        public int mMaxEdge = Integer.MAX_VALUE;

        public final int getScroll(int i) {
            int i2;
            int i3;
            int i4 = (int) ((0 * 50.0f) / 100.0f);
            int i5 = this.mMinEdge;
            boolean z = i5 == Integer.MIN_VALUE;
            int i6 = this.mMaxEdge;
            boolean z2 = i6 == Integer.MAX_VALUE;
            return (z || i - i5 > i4) ? (z2 || i6 - i > 0 - i4) ? i - i4 : (z || i6 >= (i2 = this.mMinScroll)) ? i6 : i2 : (z2 || i5 <= (i3 = this.mMaxScroll)) ? i5 : i3;
        }

        public final String toString() {
            return " min:" + this.mMinEdge + " " + this.mMinScroll + " max:" + this.mMaxEdge + " " + this.mMaxScroll;
        }

        public final void updateMinMax(int i, int i2) {
            this.mMinEdge = i;
            this.mMaxEdge = i2;
            boolean z = i == Integer.MIN_VALUE;
            boolean z2 = i2 == Integer.MAX_VALUE;
            if (!z) {
                this.mMinScroll = i;
            }
            if (!z2) {
                this.mMaxScroll = i2;
            }
            if (z2 || z) {
                return;
            }
            this.mMaxScroll = Math.max(this.mMinScroll, this.mMaxScroll);
        }
    }

    public WindowAlignment() {
        Axis axis = new Axis();
        this.vertical = axis;
        Axis axis2 = new Axis();
        this.horizontal = axis2;
        this.mMainAxis = axis2;
        this.mSecondAxis = axis;
    }

    public final String toString() {
        return "horizontal=" + this.horizontal + "; vertical=" + this.vertical;
    }
}
