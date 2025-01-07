package androidx.slice.core;

import android.text.TextUtils;
import androidx.slice.ArrayUtils;
import androidx.slice.Slice;
import androidx.slice.SliceItem;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SliceQuery {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Filter {
        boolean filter(SliceItem sliceItem);
    }

    public static boolean checkFormat(SliceItem sliceItem, String str) {
        return str == null || str.equals(sliceItem.mFormat);
    }

    public static SliceItem find(Slice slice, String str, String str2) {
        return find(slice, str, new String[]{str2}, new String[]{null});
    }

    public static List findAll(SliceItem sliceItem, String str, String[] strArr, String[] strArr2) {
        ArrayList arrayList = new ArrayList();
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(sliceItem);
        findAll(arrayDeque, new SliceQuery$$ExternalSyntheticLambda1(str, strArr, strArr2, 2), arrayList);
        return arrayList;
    }

    public static SliceItem findSliceItem(Deque deque, Filter filter) {
        while (!deque.isEmpty()) {
            SliceItem sliceItem = (SliceItem) ((ArrayDeque) deque).poll();
            if (filter.filter(sliceItem)) {
                return sliceItem;
            }
            if (sliceItem != null && ("slice".equals(sliceItem.mFormat) || "action".equals(sliceItem.mFormat))) {
                Collections.addAll(deque, sliceItem.getSlice().mItems);
            }
        }
        return null;
    }

    public static SliceItem findSubtype(Slice slice, String str, String str2) {
        if (slice == null) {
            return null;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        Collections.addAll(arrayDeque, slice.mItems);
        return findSliceItem(arrayDeque, new SliceQuery$$ExternalSyntheticLambda3(str, str2, 1));
    }

    public static SliceItem findTopLevelItem(Slice slice, String str, String str2, String[] strArr) {
        for (SliceItem sliceItem : slice.mItems) {
            if (checkFormat(sliceItem, str) && ((str2 == null || str2.equals(sliceItem.mSubType)) && hasHints(sliceItem, strArr))) {
                return sliceItem;
            }
        }
        return null;
    }

    public static boolean hasAnyHints(SliceItem sliceItem, String... strArr) {
        if (strArr == null) {
            return false;
        }
        for (String str : strArr) {
            if (ArrayUtils.contains(sliceItem.mHints, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasHints(SliceItem sliceItem, String... strArr) {
        if (strArr == null) {
            return true;
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str) && !ArrayUtils.contains(sliceItem.mHints, str)) {
                return false;
            }
        }
        return true;
    }

    public static SliceItem find(SliceItem sliceItem, String str, String str2) {
        return find(sliceItem, str, new String[]{str2}, new String[]{null});
    }

    public static SliceItem find(Slice slice, String str, String[] strArr, String[] strArr2) {
        if (slice == null) {
            return null;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        Collections.addAll(arrayDeque, slice.mItems);
        return findSliceItem(arrayDeque, new SliceQuery$$ExternalSyntheticLambda1(str, strArr, strArr2, 0));
    }

    public static void findAll(Deque deque, Filter filter, List list) {
        while (!deque.isEmpty()) {
            SliceItem sliceItem = (SliceItem) ((ArrayDeque) deque).poll();
            if (filter.filter(sliceItem)) {
                list.add(sliceItem);
            }
            if (sliceItem != null && ("slice".equals(sliceItem.mFormat) || "action".equals(sliceItem.mFormat))) {
                Collections.addAll(deque, sliceItem.getSlice().mItems);
            }
        }
    }

    public static SliceItem findSubtype(SliceItem sliceItem, String str, String str2) {
        if (sliceItem == null) {
            return null;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(sliceItem);
        return findSliceItem(arrayDeque, new SliceQuery$$ExternalSyntheticLambda3(str, str2, 0));
    }

    public static SliceItem find(SliceItem sliceItem, String str, String[] strArr, String[] strArr2) {
        if (sliceItem == null) {
            return null;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(sliceItem);
        return findSliceItem(arrayDeque, new SliceQuery$$ExternalSyntheticLambda1(str, strArr, strArr2, 1));
    }
}
