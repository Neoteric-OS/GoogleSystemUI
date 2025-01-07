package androidx.slice.widget;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.appcompat.view.menu.CascadingMenuPopup$$ExternalSyntheticOutline0;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.ArrayUtils;
import androidx.slice.Slice;
import androidx.slice.SliceItem;
import androidx.slice.core.SliceAction;
import androidx.slice.core.SliceActionImpl;
import androidx.slice.core.SliceQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ListContent extends SliceContent {
    public final RowContent mHeaderContent;
    public final SliceActionImpl mPrimaryAction;
    public final ArrayList mRowItems;
    public final RowContent mSeeMoreContent;
    public final List mSliceActions;

    public ListContent(Slice slice) {
        ArrayList arrayList;
        if (slice != null) {
            init(new SliceItem(slice, "slice", (String) null, Arrays.asList(slice.mHints)));
            this.mRowIndex = -1;
        }
        this.mRowItems = new ArrayList();
        if (this.mSliceItem == null || slice == null) {
            return;
        }
        SliceItem find = SliceQuery.find(slice, "slice", "actions");
        List findAll = find != null ? SliceQuery.findAll(find, "slice", new String[]{"actions", "shortcut"}, null) : null;
        if (findAll != null) {
            arrayList = new ArrayList(findAll.size());
            for (int i = 0; i < findAll.size(); i++) {
                arrayList.add(new SliceActionImpl((SliceItem) findAll.get(i)));
            }
        } else {
            arrayList = null;
        }
        this.mSliceActions = arrayList;
        SliceItem find2 = SliceQuery.find(slice, "slice", (String[]) null, new String[]{"list_item", "shortcut", "actions", "keywords", "ttl", "last_updated", "horizontal", "selection_option"});
        find2 = (find2 == null || !"slice".equals(find2.mFormat) || find2.hasAnyHints("actions", "keywords", "see_more") || SliceQuery.find(find2, "text", (String) null) == null) ? null : find2;
        if (find2 != null) {
            RowContent rowContent = new RowContent(find2, 0);
            this.mHeaderContent = rowContent;
            this.mRowItems.add(rowContent);
        }
        SliceItem findTopLevelItem = SliceQuery.findTopLevelItem(slice, null, null, new String[]{"see_more"});
        if (findTopLevelItem == null || !"slice".equals(findTopLevelItem.mFormat)) {
            findTopLevelItem = null;
        } else {
            List asList = Arrays.asList(findTopLevelItem.getSlice().mItems);
            if (asList.size() == 1 && "action".equals(((SliceItem) asList.get(0)).mFormat)) {
                findTopLevelItem = (SliceItem) asList.get(0);
            }
        }
        if (findTopLevelItem != null) {
            this.mSeeMoreContent = new RowContent(findTopLevelItem, -1);
        }
        List asList2 = Arrays.asList(slice.mItems);
        for (int i2 = 0; i2 < asList2.size(); i2++) {
            SliceItem sliceItem = (SliceItem) asList2.get(i2);
            String str = sliceItem.mFormat;
            if (!sliceItem.hasAnyHints("actions", "see_more", "keywords", "ttl", "last_updated") && ("action".equals(str) || "slice".equals(str))) {
                if (this.mHeaderContent == null && !ArrayUtils.contains(sliceItem.mHints, "list_item")) {
                    RowContent rowContent2 = new RowContent(sliceItem, 0);
                    this.mHeaderContent = rowContent2;
                    this.mRowItems.add(0, rowContent2);
                } else if (ArrayUtils.contains(sliceItem.mHints, "list_item")) {
                    if (ArrayUtils.contains(sliceItem.mHints, "horizontal")) {
                        this.mRowItems.add(new GridContent(sliceItem, i2));
                    } else {
                        this.mRowItems.add(new RowContent(sliceItem, i2));
                    }
                }
            }
        }
        if (this.mHeaderContent == null && this.mRowItems.size() >= 1) {
            RowContent rowContent3 = (RowContent) this.mRowItems.get(0);
            this.mHeaderContent = rowContent3;
            rowContent3.mIsHeader = true;
        }
        if (this.mRowItems.size() > 0 && (CascadingMenuPopup$$ExternalSyntheticOutline0.m(this.mRowItems, 1) instanceof GridContent)) {
            ((GridContent) CascadingMenuPopup$$ExternalSyntheticOutline0.m(this.mRowItems, 1)).mIsLastIndex = true;
        }
        RowContent rowContent4 = this.mHeaderContent;
        SliceItem sliceItem2 = rowContent4 != null ? rowContent4.mPrimaryAction : null;
        sliceItem2 = sliceItem2 == null ? SliceQuery.find(this.mSliceItem, "action", new String[]{"shortcut", "title"}, (String[]) null) : sliceItem2;
        sliceItem2 = sliceItem2 == null ? SliceQuery.find(this.mSliceItem, "action", (String) null) : sliceItem2;
        this.mPrimaryAction = sliceItem2 != null ? new SliceActionImpl(sliceItem2) : null;
    }

    public static int getRowType(SliceContent sliceContent, boolean z, List list) {
        if (sliceContent == null) {
            return 0;
        }
        if (sliceContent instanceof GridContent) {
            return 1;
        }
        RowContent rowContent = (RowContent) sliceContent;
        SliceItem sliceItem = rowContent.mPrimaryAction;
        SliceActionImpl sliceActionImpl = sliceItem != null ? new SliceActionImpl(sliceItem) : null;
        SliceItem sliceItem2 = rowContent.mRange;
        if (sliceItem2 != null) {
            return "action".equals(sliceItem2.mFormat) ? 4 : 5;
        }
        if (rowContent.mSelection != null) {
            return 6;
        }
        if (sliceActionImpl != null && sliceActionImpl.isToggle()) {
            return 3;
        }
        if (!z || list == null) {
            return rowContent.mToggleItems.size() > 0 ? 3 : 0;
        }
        for (int i = 0; i < list.size(); i++) {
            if (((SliceAction) list.get(i)).isToggle()) {
                return 3;
            }
        }
        return 0;
    }

    @Override // androidx.slice.widget.SliceContent
    public final int getHeight(SliceStyle sliceStyle, SliceViewPolicy sliceViewPolicy) {
        sliceStyle.getClass();
        sliceViewPolicy.getClass();
        int i = sliceViewPolicy.mMaxHeight;
        boolean z = sliceViewPolicy.mScrollable;
        int listItemsHeight = sliceStyle.getListItemsHeight(this.mRowItems, sliceViewPolicy);
        if (i > 0) {
            i = Math.max(this.mHeaderContent.getHeight(sliceStyle, sliceViewPolicy), i);
        }
        int i2 = i > 0 ? i : sliceStyle.mListLargeHeight;
        if (listItemsHeight - i2 >= sliceStyle.mListMinScrollHeight && !sliceStyle.mExpandToAvailableHeight) {
            listItemsHeight = i2;
        } else if (i > 0) {
            listItemsHeight = Math.min(i2, listItemsHeight);
        }
        return !z ? sliceStyle.getListItemsHeight(sliceStyle.getListItemsForNonScrollingList(this, listItemsHeight, sliceViewPolicy).mDisplayedItems, sliceViewPolicy) : listItemsHeight;
    }

    public final SliceActionImpl getShortcut(Context context) {
        SliceItem sliceItem;
        SliceItem sliceItem2;
        Intent launchIntentForPackage;
        SliceActionImpl sliceActionImpl = this.mPrimaryAction;
        if (sliceActionImpl != null) {
            return sliceActionImpl;
        }
        SliceItem sliceItem3 = this.mSliceItem;
        SliceActionImpl sliceActionImpl2 = null;
        if (sliceItem3 != null) {
            SliceItem find = SliceQuery.find(sliceItem3, "action", new String[]{"title", "shortcut"}, (String[]) null);
            if (find != null) {
                sliceItem = SliceQuery.find(find, "image", "title");
                sliceItem2 = SliceQuery.find(find, "text", (String) null);
            } else {
                sliceItem = null;
                sliceItem2 = null;
            }
            if (find == null) {
                find = SliceQuery.find(this.mSliceItem, "action", (String) null);
            }
            if (sliceItem == null) {
                sliceItem = SliceQuery.find(this.mSliceItem, "image", "title");
            }
            if (sliceItem2 == null) {
                sliceItem2 = SliceQuery.find(this.mSliceItem, "text", "title");
            }
            if (sliceItem == null) {
                sliceItem = SliceQuery.find(this.mSliceItem, "image", (String) null);
            }
            if (sliceItem2 == null) {
                sliceItem2 = SliceQuery.find(this.mSliceItem, "text", (String) null);
            }
            int parseImageMode = sliceItem != null ? SliceActionImpl.parseImageMode(sliceItem) : 5;
            if (context != null) {
                SliceItem find2 = SliceQuery.find(this.mSliceItem, "slice", (String) null);
                if (find2 != null) {
                    Uri parse = Uri.parse(find2.getSlice().mUri);
                    IconCompat iconCompat = sliceItem != null ? (IconCompat) sliceItem.mObj : null;
                    CharSequence charSequence = sliceItem2 != null ? (CharSequence) sliceItem2.mObj : null;
                    PackageManager packageManager = context.getPackageManager();
                    ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(parse.getAuthority(), 0);
                    ApplicationInfo applicationInfo = resolveContentProvider != null ? resolveContentProvider.applicationInfo : null;
                    if (applicationInfo != null) {
                        if (iconCompat == null) {
                            Drawable applicationIcon = packageManager.getApplicationIcon(applicationInfo);
                            if (applicationIcon instanceof BitmapDrawable) {
                                Bitmap bitmap = ((BitmapDrawable) applicationIcon).getBitmap();
                                PorterDuff.Mode mode = IconCompat.DEFAULT_TINT_MODE;
                                bitmap.getClass();
                                iconCompat = new IconCompat(1);
                                iconCompat.mObj1 = bitmap;
                            } else {
                                Bitmap createBitmap = Bitmap.createBitmap(applicationIcon.getIntrinsicWidth(), applicationIcon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                                Canvas canvas = new Canvas(createBitmap);
                                applicationIcon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                                applicationIcon.draw(canvas);
                                PorterDuff.Mode mode2 = IconCompat.DEFAULT_TINT_MODE;
                                createBitmap.getClass();
                                IconCompat iconCompat2 = new IconCompat(1);
                                iconCompat2.mObj1 = createBitmap;
                                iconCompat = iconCompat2;
                            }
                            parseImageMode = 2;
                        }
                        if (charSequence == null) {
                            charSequence = packageManager.getApplicationLabel(applicationInfo);
                        }
                        if (find == null && (launchIntentForPackage = packageManager.getLaunchIntentForPackage(applicationInfo.packageName)) != null) {
                            PendingIntent activity = PendingIntent.getActivity(context, 0, launchIntentForPackage, 67108864);
                            ArrayList arrayList = new ArrayList();
                            ArrayList arrayList2 = new ArrayList();
                            String[] strArr = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
                            Slice slice = new Slice();
                            slice.mSpec = null;
                            slice.mItems = Slice.NO_ITEMS;
                            slice.mUri = null;
                            slice.mHints = strArr;
                            slice.mItems = (SliceItem[]) arrayList.toArray(new SliceItem[arrayList.size()]);
                            slice.mUri = parse.toString();
                            slice.mSpec = null;
                            find = new SliceItem(activity, slice, (String) null, new String[0]);
                        }
                    }
                    if (find == null) {
                        find = new SliceItem(PendingIntent.getActivity(context, 0, new Intent(), 67108864), (Slice) null, (String) null, (String[]) null);
                    }
                    if (charSequence != null && iconCompat != null) {
                        sliceActionImpl2 = new SliceActionImpl(find.getAction(), iconCompat, parseImageMode, charSequence);
                    }
                }
            } else if (sliceItem != null && find != null && sliceItem2 != null) {
                return new SliceActionImpl(find.getAction(), (IconCompat) sliceItem.mObj, parseImageMode, (CharSequence) sliceItem2.mObj);
            }
        }
        return sliceActionImpl2;
    }

    public final boolean isValid() {
        return this.mSliceItem != null && this.mRowItems.size() > 0;
    }
}
