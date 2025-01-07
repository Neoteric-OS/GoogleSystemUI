package androidx.slice.builders;

import android.app.slice.SliceManager;
import android.app.slice.SliceSpec;
import android.content.Context;
import android.net.Uri;
import androidx.collection.ArraySet;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.util.Pair;
import androidx.slice.Slice;
import androidx.slice.SliceProvider;
import androidx.slice.SliceSpecs;
import androidx.slice.SystemClock;
import androidx.slice.builders.impl.ListBuilderBasicImpl;
import androidx.slice.builders.impl.ListBuilderImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ListBuilder {
    public final androidx.slice.builders.impl.ListBuilder mImpl;
    public final List mSpecs;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HeaderBuilder {
        public CharSequence mTitle;
        public final Uri mUri;

        public HeaderBuilder(Uri uri) {
            this.mUri = uri;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RowBuilder {
        public CharSequence mContentDescription;
        public SliceAction mPrimaryAction;
        public CharSequence mTitle;
        public final Uri mUri;
        public final List mEndItems = new ArrayList();
        public final List mEndTypes = new ArrayList();
        public final List mEndLoads = new ArrayList();

        public RowBuilder(Uri uri) {
            this.mUri = uri;
        }

        public final void addEndItem(IconCompat iconCompat) {
            this.mEndItems.add(new Pair(iconCompat, 0));
            this.mEndTypes.add(1);
            this.mEndLoads.add(Boolean.FALSE);
        }
    }

    public ListBuilder(Context context, Uri uri) {
        ArrayList arrayList;
        Slice.Builder builder = new Slice.Builder(uri);
        androidx.slice.builders.impl.ListBuilder listBuilder = null;
        if (SliceProvider.sSpecs != null) {
            arrayList = new ArrayList(SliceProvider.sSpecs);
        } else {
            Set<SliceSpec> pinnedSpecs = ((SliceManager) context.getSystemService(SliceManager.class)).getPinnedSpecs(uri);
            ArraySet arraySet = new ArraySet(0);
            if (pinnedSpecs != null) {
                Iterator<SliceSpec> it = pinnedSpecs.iterator();
                while (it.hasNext()) {
                    SliceSpec next = it.next();
                    arraySet.add(next == null ? null : new androidx.slice.SliceSpec(next.getType(), next.getRevision()));
                }
            }
            arrayList = new ArrayList(arraySet);
        }
        this.mSpecs = arrayList;
        androidx.slice.SliceSpec sliceSpec = SliceSpecs.LIST_V2;
        if (checkCompatible(sliceSpec)) {
            ArraySet arraySet2 = SliceProvider.sSpecs;
            listBuilder = new ListBuilderImpl(builder, sliceSpec, new SystemClock());
        } else {
            androidx.slice.SliceSpec sliceSpec2 = SliceSpecs.LIST;
            if (checkCompatible(sliceSpec2)) {
                ArraySet arraySet3 = SliceProvider.sSpecs;
                listBuilder = new ListBuilderImpl(builder, sliceSpec2, new SystemClock());
            } else {
                androidx.slice.SliceSpec sliceSpec3 = SliceSpecs.BASIC;
                if (checkCompatible(sliceSpec3)) {
                    listBuilder = new ListBuilderBasicImpl(builder, sliceSpec3, new SystemClock());
                }
            }
        }
        if (listBuilder == null) {
            throw new IllegalArgumentException("No valid specs found");
        }
        this.mImpl = listBuilder;
        listBuilder.setTtl();
    }

    public final boolean checkCompatible(androidx.slice.SliceSpec sliceSpec) {
        int size = this.mSpecs.size();
        for (int i = 0; i < size; i++) {
            androidx.slice.SliceSpec sliceSpec2 = (androidx.slice.SliceSpec) this.mSpecs.get(i);
            if (sliceSpec2.mType.equals(sliceSpec.mType) && sliceSpec2.mRevision >= sliceSpec.mRevision) {
                return true;
            }
        }
        return false;
    }
}
