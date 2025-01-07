package androidx.slice;

import android.app.PendingIntent;
import android.net.Uri;
import androidx.compose.runtime.OpaqueKey$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Slice extends CustomVersionedParcelable {
    public static final String[] NO_HINTS = new String[0];
    public static final SliceItem[] NO_ITEMS = new SliceItem[0];
    public SliceSpec mSpec = null;
    public SliceItem[] mItems = NO_ITEMS;
    public String[] mHints = NO_HINTS;
    public String mUri = null;

    public static void appendHints(StringBuilder sb, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        sb.append('(');
        int length = strArr.length - 1;
        for (int i = 0; i < length; i++) {
            sb.append(strArr[i]);
            sb.append(", ");
        }
        sb.append(strArr[length]);
        sb.append(")");
    }

    public static boolean isValidIcon(IconCompat iconCompat) {
        if (iconCompat == null) {
            return false;
        }
        if (iconCompat.mType != 2 || iconCompat.getResId() != 0) {
            return true;
        }
        throw new IllegalArgumentException("Failed to add icon, invalid resource id: " + iconCompat.getResId());
    }

    public final String toString() {
        return toString("");
    }

    public final String toString(String str) {
        StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, "Slice ");
        String[] strArr = this.mHints;
        if (strArr.length > 0) {
            appendHints(m, strArr);
            m.append(' ');
        }
        m.append('[');
        m.append(this.mUri);
        m.append("] {\n");
        String str2 = str + "  ";
        int i = 0;
        while (true) {
            SliceItem[] sliceItemArr = this.mItems;
            if (i >= sliceItemArr.length) {
                return OpaqueKey$$ExternalSyntheticOutline0.m(m, str, '}');
            }
            m.append(sliceItemArr[i].toString(str2));
            i++;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public int mChildId;
        public SliceSpec mSpec;
        public final Uri mUri;
        public final ArrayList mItems = new ArrayList();
        public final ArrayList mHints = new ArrayList();

        public Builder(Uri uri) {
            this.mUri = uri;
        }

        public final void addAction(PendingIntent pendingIntent, Slice slice, String str) {
            pendingIntent.getClass();
            slice.getClass();
            this.mItems.add(new SliceItem(pendingIntent, slice, str, slice.mHints));
        }

        public final void addHints(String... strArr) {
            this.mHints.addAll(Arrays.asList(strArr));
        }

        public final void addIcon(IconCompat iconCompat, String str, String... strArr) {
            iconCompat.getClass();
            if (Slice.isValidIcon(iconCompat)) {
                this.mItems.add(new SliceItem(iconCompat, "image", str, strArr));
            }
        }

        public final void addInt(String str, int i, String... strArr) {
            this.mItems.add(new SliceItem(Integer.valueOf(i), "int", str, strArr));
        }

        public final void addItem(SliceItem sliceItem) {
            this.mItems.add(sliceItem);
        }

        public final void addSubSlice(Slice slice, String str) {
            slice.getClass();
            this.mItems.add(new SliceItem(slice, "slice", str, slice.mHints));
        }

        public final void addText(CharSequence charSequence, String str, String... strArr) {
            this.mItems.add(new SliceItem(charSequence, "text", str, strArr));
        }

        public final Slice build() {
            ArrayList arrayList = this.mItems;
            ArrayList arrayList2 = this.mHints;
            String[] strArr = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
            Uri uri = this.mUri;
            SliceSpec sliceSpec = this.mSpec;
            Slice slice = new Slice();
            slice.mSpec = null;
            slice.mItems = Slice.NO_ITEMS;
            slice.mUri = null;
            slice.mHints = strArr;
            slice.mItems = (SliceItem[]) arrayList.toArray(new SliceItem[arrayList.size()]);
            slice.mUri = uri.toString();
            slice.mSpec = sliceSpec;
            return slice;
        }

        public Builder(Builder builder) {
            Uri.Builder appendPath = builder.mUri.buildUpon().appendPath("_gen");
            int i = builder.mChildId;
            builder.mChildId = i + 1;
            this.mUri = appendPath.appendPath(String.valueOf(i)).build();
        }
    }
}
