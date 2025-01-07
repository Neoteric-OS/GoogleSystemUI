package androidx.slice.widget;

import android.content.Context;
import android.widget.FrameLayout;
import com.android.systemui.volume.VolumePanelDialog$$ExternalSyntheticLambda1;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SliceChildView extends FrameLayout {
    public int mInsetBottom;
    public int mInsetEnd;
    public int mInsetStart;
    public int mInsetTop;
    public long mLastUpdated;
    public SliceAdapter mLoadingListener;
    public VolumePanelDialog$$ExternalSyntheticLambda1 mObserver;
    public RowStyle mRowStyle;
    public boolean mShowLastUpdated;
    public SliceStyle mSliceStyle;
    public int mTintColor;
    public SliceViewPolicy mViewPolicy;

    public SliceChildView(Context context) {
        super(context);
        this.mTintColor = -1;
        this.mLastUpdated = -1L;
    }

    public abstract void resetView();

    public void setInsets(int i, int i2, int i3, int i4) {
        this.mInsetStart = i;
        this.mInsetTop = i2;
        this.mInsetEnd = i3;
        this.mInsetBottom = i4;
    }

    public void setLastUpdated(long j) {
        this.mLastUpdated = j;
    }

    public void setPolicy(SliceViewPolicy sliceViewPolicy) {
        this.mViewPolicy = sliceViewPolicy;
    }

    public void setShowLastUpdated(boolean z) {
        this.mShowLastUpdated = z;
    }

    public void setStyle(SliceStyle sliceStyle, RowStyle rowStyle) {
        this.mSliceStyle = sliceStyle;
        this.mRowStyle = rowStyle;
    }

    public void setTint(int i) {
        this.mTintColor = i;
    }

    public void setAllowTwoLines(boolean z) {
    }

    public void setLoadingActions(Set set) {
    }

    public void setSliceActionListener(VolumePanelDialog$$ExternalSyntheticLambda1 volumePanelDialog$$ExternalSyntheticLambda1) {
    }

    public void setSliceActions(List list) {
    }

    public void setSliceContent(ListContent listContent) {
    }

    public void setSliceItem(SliceContent sliceContent, boolean z, int i, int i2, VolumePanelDialog$$ExternalSyntheticLambda1 volumePanelDialog$$ExternalSyntheticLambda1) {
    }
}
