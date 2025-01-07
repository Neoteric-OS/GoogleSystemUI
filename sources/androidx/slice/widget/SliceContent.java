package androidx.slice.widget;

import androidx.slice.SliceItem;
import androidx.slice.core.SliceQuery;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SliceContent {
    public SliceItem mColorItem;
    public SliceItem mContentDescr;
    public SliceItem mLayoutDirItem;
    public int mRowIndex;
    public SliceItem mSliceItem;

    public SliceContent(SliceItem sliceItem, int i) {
        init(sliceItem);
        this.mRowIndex = i;
    }

    public abstract int getHeight(SliceStyle sliceStyle, SliceViewPolicy sliceViewPolicy);

    public final int getLayoutDir() {
        SliceItem sliceItem = this.mLayoutDirItem;
        if (sliceItem == null) {
            return -1;
        }
        int i = sliceItem.getInt();
        if (i == 2 || i == 3 || i == 1 || i == 0) {
            return i;
        }
        return -1;
    }

    public final void init(SliceItem sliceItem) {
        this.mSliceItem = sliceItem;
        if ("slice".equals(sliceItem.mFormat) || "action".equals(sliceItem.mFormat)) {
            SliceQuery.findTopLevelItem(sliceItem.getSlice(), "int", "color", null);
            this.mLayoutDirItem = SliceQuery.findTopLevelItem(sliceItem.getSlice(), "int", "layout_direction", null);
        }
        this.mContentDescr = SliceQuery.findSubtype(sliceItem, "text", "content_description");
    }
}
