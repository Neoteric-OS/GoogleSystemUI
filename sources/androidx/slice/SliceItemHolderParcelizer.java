package androidx.slice;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.slice.SliceItemHolder;
import androidx.versionedparcelable.VersionedParcel;
import androidx.versionedparcelable.VersionedParcelParcel;
import androidx.versionedparcelable.VersionedParcelable;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliceItemHolderParcelizer {
    private static SliceItemHolder.SliceItemPool sBuilder = new SliceItemHolder.SliceItemPool();

    public static SliceItemHolder read(VersionedParcel versionedParcel) {
        SliceItemHolder sliceItemHolder;
        SliceItemHolder.SliceItemPool sliceItemPool = sBuilder;
        if (sliceItemPool.mCached.size() > 0) {
            ArrayList arrayList = sliceItemPool.mCached;
            sliceItemHolder = (SliceItemHolder) arrayList.remove(arrayList.size() - 1);
        } else {
            SliceItemHolder sliceItemHolder2 = new SliceItemHolder();
            sliceItemHolder2.mVersionedParcelable = null;
            sliceItemHolder2.mParcelable = null;
            sliceItemHolder2.mStr = null;
            sliceItemHolder2.mInt = 0;
            sliceItemHolder2.mLong = 0L;
            sliceItemHolder2.mBundle = null;
            sliceItemHolder2.mPool = sliceItemPool;
            sliceItemHolder = sliceItemHolder2;
        }
        VersionedParcelable versionedParcelable = sliceItemHolder.mVersionedParcelable;
        if (versionedParcel.readField(1)) {
            versionedParcelable = versionedParcel.readVersionedParcelable();
        }
        sliceItemHolder.mVersionedParcelable = versionedParcelable;
        sliceItemHolder.mParcelable = versionedParcel.readParcelable(sliceItemHolder.mParcelable, 2);
        sliceItemHolder.mStr = versionedParcel.readString(3, sliceItemHolder.mStr);
        sliceItemHolder.mInt = versionedParcel.readInt(sliceItemHolder.mInt, 4);
        long j = sliceItemHolder.mLong;
        if (versionedParcel.readField(5)) {
            j = ((VersionedParcelParcel) versionedParcel).mParcel.readLong();
        }
        sliceItemHolder.mLong = j;
        Bundle bundle = sliceItemHolder.mBundle;
        if (versionedParcel.readField(6)) {
            bundle = ((VersionedParcelParcel) versionedParcel).mParcel.readBundle(VersionedParcelParcel.class.getClassLoader());
        }
        sliceItemHolder.mBundle = bundle;
        return sliceItemHolder;
    }

    public static void write(SliceItemHolder sliceItemHolder, VersionedParcel versionedParcel) {
        versionedParcel.getClass();
        VersionedParcelable versionedParcelable = sliceItemHolder.mVersionedParcelable;
        if (versionedParcelable != null) {
            versionedParcel.setOutputField(1);
            versionedParcel.writeVersionedParcelable(versionedParcelable);
        }
        Parcelable parcelable = sliceItemHolder.mParcelable;
        if (parcelable != null) {
            versionedParcel.writeParcelable(parcelable, 2);
        }
        String str = sliceItemHolder.mStr;
        if (str != null) {
            versionedParcel.writeString(3, str);
        }
        int i = sliceItemHolder.mInt;
        if (i != 0) {
            versionedParcel.writeInt(i, 4);
        }
        long j = sliceItemHolder.mLong;
        if (0 != j) {
            versionedParcel.setOutputField(5);
            ((VersionedParcelParcel) versionedParcel).mParcel.writeLong(j);
        }
        Bundle bundle = sliceItemHolder.mBundle;
        if (bundle != null) {
            versionedParcel.setOutputField(6);
            ((VersionedParcelParcel) versionedParcel).mParcel.writeBundle(bundle);
        }
    }
}
