package androidx.slice;

import androidx.slice.core.SliceQuery;
import androidx.slice.widget.ListContent;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliceMetadata {
    public long mExpiry;
    public long mLastUpdated;
    public ListContent mListContent;
    public Slice mSlice;
    public List mSliceActions;

    public final int getLoadingState() {
        boolean z = SliceQuery.find(this.mSlice, (String) null, "partial") != null;
        if (this.mListContent.isValid()) {
            return z ? 1 : 2;
        }
        return 0;
    }

    public final boolean isExpired() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.mExpiry;
        return (j == 0 || j == -1 || currentTimeMillis <= j) ? false : true;
    }
}
