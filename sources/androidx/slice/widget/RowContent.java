package androidx.slice.widget;

import android.text.TextUtils;
import android.util.Log;
import androidx.slice.ArrayUtils;
import androidx.slice.SliceItem;
import androidx.slice.core.SliceActionImpl;
import androidx.slice.core.SliceQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RowContent extends SliceContent {
    public final ArrayList mEndItems;
    public boolean mIsHeader;
    public final int mLineCount;
    public final SliceItem mPrimaryAction;
    public final SliceItem mRange;
    public final SliceItem mSelection;
    public boolean mShowActionDivider;
    public boolean mShowBottomDivider;
    public boolean mShowTitleItems;
    public final SliceItem mStartItem;
    public final SliceItem mSubtitleItem;
    public final SliceItem mSummaryItem;
    public final SliceItem mTitleItem;
    public final ArrayList mToggleItems;

    public RowContent(SliceItem sliceItem, int i) {
        super(sliceItem, i);
        boolean z;
        this.mEndItems = new ArrayList();
        this.mToggleItems = new ArrayList();
        this.mLineCount = 0;
        boolean z2 = i == 0;
        if (ArrayUtils.contains(sliceItem.mHints, "end_of_section")) {
            this.mShowBottomDivider = true;
        }
        this.mIsHeader = z2;
        if (!isValidRow(sliceItem)) {
            Log.w("RowContent", "Provided SliceItem is invalid for RowContent");
            return;
        }
        ArrayList arrayList = (ArrayList) SliceQuery.findAll(sliceItem, null, new String[]{"title"}, new String[]{null});
        if (arrayList.size() > 0) {
            String str = ((SliceItem) arrayList.get(0)).mFormat;
            if (("action".equals(str) && SliceQuery.find((SliceItem) arrayList.get(0), "image", (String[]) null, (String[]) null) != null) || "slice".equals(str) || "long".equals(str) || "image".equals(str)) {
                this.mStartItem = (SliceItem) arrayList.get(0);
            }
        }
        String[] strArr = {"shortcut", "title"};
        List findAll = SliceQuery.findAll(sliceItem, "slice", strArr, null);
        findAll.addAll(SliceQuery.findAll(sliceItem, "action", strArr, null));
        if (findAll.isEmpty() && "action".equals(sliceItem.mFormat) && Arrays.asList(sliceItem.getSlice().mItems).size() == 1) {
            this.mPrimaryAction = sliceItem;
        } else {
            if (this.mStartItem != null) {
                ArrayList arrayList2 = (ArrayList) findAll;
                if (arrayList2.size() > 1 && arrayList2.get(0) == this.mStartItem) {
                    this.mPrimaryAction = (SliceItem) arrayList2.get(1);
                }
            }
            ArrayList arrayList3 = (ArrayList) findAll;
            if (arrayList3.size() > 0) {
                this.mPrimaryAction = (SliceItem) arrayList3.get(0);
            }
        }
        ArrayList filterInvalidItems = filterInvalidItems(sliceItem);
        if (filterInvalidItems.size() == 1 && (("action".equals(((SliceItem) filterInvalidItems.get(0)).mFormat) || "slice".equals(((SliceItem) filterInvalidItems.get(0)).mFormat)) && !((SliceItem) filterInvalidItems.get(0)).hasAnyHints("shortcut", "title") && isValidRow((SliceItem) filterInvalidItems.get(0)))) {
            sliceItem = (SliceItem) filterInvalidItems.get(0);
            filterInvalidItems = filterInvalidItems(sliceItem);
            z = true;
        } else {
            z = false;
        }
        if ("range".equals(sliceItem.mSubType)) {
            if (SliceQuery.findSubtype(sliceItem, "action", "range") == null || z) {
                this.mRange = sliceItem;
            } else {
                filterInvalidItems.remove(this.mStartItem);
                if (filterInvalidItems.size() != 1) {
                    SliceItem findSubtype = SliceQuery.findSubtype(sliceItem, "action", "range");
                    this.mRange = findSubtype;
                    ArrayList filterInvalidItems2 = filterInvalidItems(findSubtype);
                    filterInvalidItems2.remove(getInputRangeThumb());
                    filterInvalidItems.remove(this.mRange);
                    filterInvalidItems.addAll(filterInvalidItems2);
                } else if (isValidRow((SliceItem) filterInvalidItems.get(0))) {
                    sliceItem = (SliceItem) filterInvalidItems.get(0);
                    filterInvalidItems = filterInvalidItems(sliceItem);
                    this.mRange = sliceItem;
                    filterInvalidItems.remove(getInputRangeThumb());
                }
            }
        }
        if ("selection".equals(sliceItem.mSubType)) {
            this.mSelection = sliceItem;
        }
        if (filterInvalidItems.size() > 0) {
            SliceItem sliceItem2 = this.mStartItem;
            if (sliceItem2 != null) {
                filterInvalidItems.remove(sliceItem2);
            }
            SliceItem sliceItem3 = this.mPrimaryAction;
            if (sliceItem3 != null) {
                filterInvalidItems.remove(sliceItem3);
            }
            ArrayList arrayList4 = new ArrayList();
            for (int i2 = 0; i2 < filterInvalidItems.size(); i2++) {
                SliceItem sliceItem4 = (SliceItem) filterInvalidItems.get(i2);
                if ("text".equals(sliceItem4.mFormat)) {
                    SliceItem sliceItem5 = this.mTitleItem;
                    if ((sliceItem5 == null || !ArrayUtils.contains(sliceItem5.mHints, "title")) && ArrayUtils.contains(sliceItem4.mHints, "title") && !ArrayUtils.contains(sliceItem4.mHints, "summary")) {
                        this.mTitleItem = sliceItem4;
                    } else if (this.mSubtitleItem == null && !ArrayUtils.contains(sliceItem4.mHints, "summary")) {
                        this.mSubtitleItem = sliceItem4;
                    } else if (this.mSummaryItem == null && ArrayUtils.contains(sliceItem4.mHints, "summary")) {
                        this.mSummaryItem = sliceItem4;
                    }
                } else {
                    arrayList4.add(sliceItem4);
                }
            }
            SliceItem sliceItem6 = this.mTitleItem;
            if (sliceItem6 != null && (ArrayUtils.contains(sliceItem6.mHints, "partial") || !TextUtils.isEmpty((CharSequence) sliceItem6.mObj))) {
                this.mLineCount++;
            }
            SliceItem sliceItem7 = this.mSubtitleItem;
            if (sliceItem7 != null && (ArrayUtils.contains(sliceItem7.mHints, "partial") || !TextUtils.isEmpty((CharSequence) sliceItem7.mObj))) {
                this.mLineCount++;
            }
            SliceItem sliceItem8 = this.mStartItem;
            boolean z3 = sliceItem8 != null && "long".equals(sliceItem8.mFormat);
            for (int i3 = 0; i3 < arrayList4.size(); i3++) {
                SliceItem sliceItem9 = (SliceItem) arrayList4.get(i3);
                boolean z4 = SliceQuery.find(sliceItem9, "action", (String[]) null, (String[]) null) != null;
                if (!"long".equals(sliceItem9.mFormat)) {
                    if (z4) {
                        SliceActionImpl sliceActionImpl = new SliceActionImpl(sliceItem9);
                        if (sliceActionImpl.isToggle()) {
                            this.mToggleItems.add(sliceActionImpl);
                        }
                    }
                    this.mEndItems.add(sliceItem9);
                } else if (!z3) {
                    this.mEndItems.add(sliceItem9);
                    z3 = true;
                }
            }
        }
        isValid();
    }

    public static ArrayList filterInvalidItems(SliceItem sliceItem) {
        ArrayList arrayList = new ArrayList();
        for (SliceItem sliceItem2 : Arrays.asList(sliceItem.getSlice().mItems)) {
            if (isValidRowContent(sliceItem, sliceItem2)) {
                arrayList.add(sliceItem2);
            }
        }
        return arrayList;
    }

    public static boolean isValidRow(SliceItem sliceItem) {
        if (sliceItem == null) {
            return false;
        }
        if ("slice".equals(sliceItem.mFormat) || "action".equals(sliceItem.mFormat)) {
            List asList = Arrays.asList(sliceItem.getSlice().mItems);
            if (ArrayUtils.contains(sliceItem.mHints, "see_more") && asList.isEmpty()) {
                return true;
            }
            for (int i = 0; i < asList.size(); i++) {
                if (isValidRowContent(sliceItem, (SliceItem) asList.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValidRowContent(SliceItem sliceItem, SliceItem sliceItem2) {
        if (sliceItem2.hasAnyHints("keywords", "ttl", "last_updated", "horizontal") || "content_description".equals(sliceItem2.mSubType) || "selection_option_key".equals(sliceItem2.mSubType) || "selection_option_value".equals(sliceItem2.mSubType)) {
            return false;
        }
        String str = sliceItem2.mFormat;
        return "image".equals(str) || "text".equals(str) || "long".equals(str) || "action".equals(str) || "input".equals(str) || "slice".equals(str) || ("int".equals(str) && "range".equals(sliceItem.mSubType));
    }

    @Override // androidx.slice.widget.SliceContent
    public final int getHeight(SliceStyle sliceStyle, SliceViewPolicy sliceViewPolicy) {
        int i;
        int i2;
        sliceStyle.getClass();
        int i3 = sliceViewPolicy.mMaxSmallHeight;
        if (i3 <= 0) {
            i3 = sliceStyle.mRowMaxHeight;
        }
        if (this.mRange != null) {
            if (((!this.mIsHeader || this.mShowTitleItems) ? this.mStartItem : null) != null) {
                return sliceStyle.mRowInlineRangeHeight;
            }
            int i4 = this.mLineCount;
            i = i4 == 0 ? 0 : i4 > 1 ? sliceStyle.mRowTextWithRangeHeight : sliceStyle.mRowSingleTextWithRangeHeight;
            i2 = sliceStyle.mRowRangeHeight;
        } else {
            if (this.mSelection == null) {
                if (this.mLineCount <= 1 && !this.mIsHeader) {
                    i3 = sliceStyle.mRowMinHeight;
                }
                return i3;
            }
            i = this.mLineCount > 1 ? sliceStyle.mRowTextWithSelectionHeight : sliceStyle.mRowSingleTextWithSelectionHeight;
            i2 = sliceStyle.mRowSelectionHeight;
        }
        return i + i2;
    }

    public final SliceItem getInputRangeThumb() {
        SliceItem sliceItem = this.mRange;
        if (sliceItem == null) {
            return null;
        }
        List asList = Arrays.asList(sliceItem.getSlice().mItems);
        for (int i = 0; i < asList.size(); i++) {
            if ("image".equals(((SliceItem) asList.get(i)).mFormat)) {
                return (SliceItem) asList.get(i);
            }
        }
        return null;
    }

    public final boolean isDefaultSeeMore() {
        return "action".equals(this.mSliceItem.mFormat) && ArrayUtils.contains(this.mSliceItem.getSlice().mHints, "see_more") && Arrays.asList(this.mSliceItem.getSlice().mItems).isEmpty();
    }

    public final boolean isValid() {
        return (this.mSliceItem == null || (this.mStartItem == null && this.mPrimaryAction == null && this.mTitleItem == null && this.mSubtitleItem == null && this.mEndItems.size() <= 0 && this.mRange == null && this.mSelection == null && !isDefaultSeeMore())) ? false : true;
    }
}
