package androidx.slice.builders.impl;

import android.net.Uri;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Pair;
import androidx.slice.Slice;
import androidx.slice.SliceItem;
import androidx.slice.builders.ListBuilder;
import androidx.slice.builders.SliceAction;
import androidx.slice.core.SliceActionImpl;
import androidx.slice.core.SliceQuery;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ListBuilderImpl extends TemplateBuilderImpl implements ListBuilder {
    public boolean mFirstRowChecked;
    public boolean mFirstRowHasText;
    public boolean mIsFirstRowTypeValid;
    public Slice mSliceHeader;

    @Override // androidx.slice.builders.impl.ListBuilder
    public final void addRow(ListBuilder.RowBuilder rowBuilder) {
        List list;
        List list2;
        Slice.Builder builder = new Slice.Builder(this.mSliceBuilder);
        ArrayList arrayList = new ArrayList();
        Uri uri = rowBuilder.mUri;
        if (uri != null) {
            builder = new Slice.Builder(uri);
        }
        SliceAction sliceAction = rowBuilder.mPrimaryAction;
        CharSequence charSequence = rowBuilder.mTitle;
        SliceItem sliceItem = charSequence == null ? null : new SliceItem(charSequence, "text", (String) null, new String[]{"title"});
        CharSequence charSequence2 = rowBuilder.mContentDescription;
        if (charSequence2 == null) {
            charSequence2 = null;
        }
        List list3 = rowBuilder.mEndItems;
        List list4 = rowBuilder.mEndTypes;
        List list5 = rowBuilder.mEndLoads;
        int i = 0;
        while (true) {
            ArrayList arrayList2 = (ArrayList) list3;
            if (i >= arrayList2.size()) {
                break;
            }
            int intValue = ((Integer) ((ArrayList) list4).get(i)).intValue();
            if (intValue == 0) {
                list = list5;
                list2 = list3;
                Long l = (Long) arrayList2.get(i);
                l.getClass();
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                Uri.Builder appendPath = builder.mUri.buildUpon().appendPath("_gen");
                int i2 = builder.mChildId;
                builder.mChildId = i2 + 1;
                Uri build = appendPath.appendPath(String.valueOf(i2)).build();
                arrayList3.add(new SliceItem(l, "long", (String) null, new String[0]));
                String[] strArr = (String[]) arrayList4.toArray(new String[arrayList4.size()]);
                Slice slice = new Slice();
                slice.mSpec = null;
                slice.mItems = Slice.NO_ITEMS;
                slice.mUri = null;
                slice.mHints = strArr;
                slice.mItems = (SliceItem[]) arrayList3.toArray(new SliceItem[arrayList3.size()]);
                slice.mUri = build.toString();
                slice.mSpec = null;
                arrayList.add(slice);
            } else if (intValue != 1) {
                if (intValue == 2) {
                    SliceAction sliceAction2 = (SliceAction) arrayList2.get(i);
                    boolean booleanValue = ((Boolean) ((ArrayList) list5).get(i)).booleanValue();
                    Slice.Builder builder2 = new Slice.Builder(builder);
                    if (booleanValue) {
                        builder2.addHints("partial");
                    }
                    SliceActionImpl sliceActionImpl = sliceAction2.mSliceAction;
                    ObjectsCompat.requireNonNull(sliceActionImpl.mAction, "Action must be non-null");
                    builder2.addHints("shortcut");
                    builder2.addAction(sliceActionImpl.mAction, sliceActionImpl.buildSliceContent(builder2).build(), sliceActionImpl.getSubtype());
                    arrayList.add(builder2.build());
                }
                list = list5;
                list2 = list3;
            } else {
                Pair pair = (Pair) arrayList2.get(i);
                IconCompat iconCompat = (IconCompat) pair.first;
                int intValue2 = ((Integer) pair.second).intValue();
                boolean booleanValue2 = ((Boolean) ((ArrayList) list5).get(i)).booleanValue();
                Slice.Builder builder3 = new Slice.Builder(builder);
                ArrayList arrayList5 = new ArrayList();
                list = list5;
                if (intValue2 == 6) {
                    arrayList5.add("show_label");
                }
                if (intValue2 != 0) {
                    arrayList5.add("no_tint");
                }
                list2 = list3;
                if (intValue2 == 2 || intValue2 == 4) {
                    arrayList5.add("large");
                }
                if (intValue2 == 3 || intValue2 == 4) {
                    arrayList5.add("raw");
                }
                if (booleanValue2) {
                    arrayList5.add("partial");
                }
                iconCompat.getClass();
                if (Slice.isValidIcon(iconCompat)) {
                    builder3.addIcon(iconCompat, null, (String[]) arrayList5.toArray(new String[arrayList5.size()]));
                }
                if (booleanValue2) {
                    builder3.addHints("partial");
                }
                arrayList.add(builder3.build());
            }
            i++;
            list5 = list;
            list3 = list2;
        }
        boolean z = sliceItem != null;
        if (!this.mFirstRowChecked) {
            this.mFirstRowChecked = true;
            this.mIsFirstRowTypeValid = true;
            this.mFirstRowHasText = z;
        }
        boolean z2 = sliceItem != null;
        if (!this.mFirstRowChecked) {
            this.mFirstRowChecked = true;
            this.mIsFirstRowTypeValid = true;
            this.mFirstRowHasText = z2;
        }
        builder.addHints("list_item");
        Slice.Builder builder4 = this.mSliceBuilder;
        builder.mSpec = null;
        if (sliceItem != null) {
            builder.addItem(sliceItem);
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            Slice slice2 = (Slice) arrayList.get(i3);
            slice2.getClass();
            builder.addSubSlice(slice2, null);
        }
        if (charSequence2 != null) {
            builder.addText(charSequence2, "content_description", new String[0]);
        }
        if (sliceAction != null) {
            sliceAction.setPrimaryAction(builder);
        }
        Slice build2 = builder.build();
        builder4.getClass();
        builder4.addSubSlice(build2, null);
    }

    @Override // androidx.slice.builders.impl.TemplateBuilderImpl
    public final void apply(Slice.Builder builder) {
        this.mClock.getClass();
        long currentTimeMillis = System.currentTimeMillis();
        builder.mItems.add(new SliceItem(Long.valueOf(currentTimeMillis), "long", "millis", new String[]{"last_updated"}));
        Slice slice = this.mSliceHeader;
        if (slice != null) {
            builder.addSubSlice(slice, null);
        }
    }

    @Override // androidx.slice.builders.impl.TemplateBuilderImpl
    public final Slice build() {
        Slice build = super.build();
        boolean z = SliceQuery.find(build, (String) null, "partial") != null;
        boolean z2 = SliceQuery.find(build, "slice", "list_item") == null;
        final String[] strArr = {"shortcut", "title"};
        SliceItem find = SliceQuery.find(build, "action", strArr, (String[]) null);
        ArrayList arrayList = new ArrayList();
        ArrayDeque arrayDeque = new ArrayDeque();
        Collections.addAll(arrayDeque, build.mItems);
        SliceQuery.findAll(arrayDeque, new SliceQuery.Filter() { // from class: androidx.slice.core.SliceQuery$$ExternalSyntheticLambda0
            @Override // androidx.slice.core.SliceQuery.Filter
            public final boolean filter(SliceItem sliceItem) {
                return SliceQuery.checkFormat(sliceItem, "slice") && SliceQuery.hasHints(sliceItem, strArr);
            }
        }, arrayList);
        if (!z && !z2 && find == null && arrayList.isEmpty()) {
            throw new IllegalStateException("A slice requires a primary action; ensure one of your builders has called #setPrimaryAction with a valid SliceAction.");
        }
        boolean z3 = this.mFirstRowChecked;
        if (z3 && !this.mIsFirstRowTypeValid) {
            throw new IllegalStateException("A slice cannot have the first row be constructed from a GridRowBuilder, consider using #setHeader.");
        }
        if (!z3 || this.mFirstRowHasText) {
            return build;
        }
        throw new IllegalStateException("A slice requires the first row to have some text.");
    }

    @Override // androidx.slice.builders.impl.ListBuilder
    public final void setHeader(ListBuilder.HeaderBuilder headerBuilder) {
        this.mIsFirstRowTypeValid = true;
        this.mFirstRowHasText = true;
        this.mFirstRowChecked = true;
        Slice.Builder builder = new Slice.Builder(this.mSliceBuilder);
        Uri uri = headerBuilder.mUri;
        if (uri != null) {
            builder = new Slice.Builder(uri);
        }
        builder.addInt("layout_direction", 0, new String[0]);
        CharSequence charSequence = headerBuilder.mTitle;
        SliceItem sliceItem = charSequence == null ? null : new SliceItem(charSequence, "text", (String) null, new String[]{"title"});
        builder.mSpec = null;
        if (sliceItem != null) {
            builder.addItem(sliceItem);
        }
        if (sliceItem == null) {
            throw new IllegalStateException("Header requires a title or subtitle to be set.");
        }
        this.mSliceHeader = builder.build();
    }

    @Override // androidx.slice.builders.impl.ListBuilder
    public final void setTtl() {
        this.mSliceBuilder.mItems.add(new SliceItem((Object) (-1L), "long", "millis", new String[]{"ttl"}));
    }
}
