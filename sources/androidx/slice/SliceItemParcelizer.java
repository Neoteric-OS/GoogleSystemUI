package androidx.slice;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Html;
import android.text.Spanned;
import androidx.core.util.Pair;
import androidx.versionedparcelable.VersionedParcel;
import androidx.versionedparcelable.VersionedParcelable;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliceItemParcelizer {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x006f, code lost:
    
        if (r7.equals("text") == false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.slice.SliceItem read(androidx.versionedparcelable.VersionedParcel r10) {
        /*
            Method dump skipped, instructions count: 326
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.slice.SliceItemParcelizer.read(androidx.versionedparcelable.VersionedParcel):androidx.slice.SliceItem");
    }

    public static void write(SliceItem sliceItem, VersionedParcel versionedParcel) {
        SliceItemHolder sliceItemHolder;
        Object obj;
        versionedParcel.getClass();
        String str = sliceItem.mFormat;
        obj = sliceItem.mObj;
        sliceItemHolder = new SliceItemHolder();
        sliceItemHolder.mVersionedParcelable = null;
        sliceItemHolder.mParcelable = null;
        sliceItemHolder.mStr = null;
        sliceItemHolder.mInt = 0;
        sliceItemHolder.mLong = 0L;
        sliceItemHolder.mBundle = null;
        str.getClass();
        switch (str) {
            case "action":
                Pair pair = (Pair) obj;
                Object obj2 = pair.first;
                if (!(obj2 instanceof PendingIntent)) {
                    throw new IllegalArgumentException("Cannot write callback to parcel");
                }
                sliceItemHolder.mParcelable = (Parcelable) obj2;
                sliceItemHolder.mVersionedParcelable = (VersionedParcelable) pair.second;
                break;
            case "bundle":
                sliceItemHolder.mBundle = (Bundle) obj;
                break;
            case "int":
                sliceItemHolder.mInt = ((Integer) obj).intValue();
                break;
            case "long":
                sliceItemHolder.mLong = ((Long) obj).longValue();
                break;
            case "text":
                sliceItemHolder.mStr = obj instanceof Spanned ? Html.toHtml((Spanned) obj, 0) : (String) obj;
                break;
            case "image":
            case "slice":
                sliceItemHolder.mVersionedParcelable = (VersionedParcelable) obj;
                break;
            case "input":
                sliceItemHolder.mParcelable = (Parcelable) obj;
                break;
        }
        sliceItem.mHolder = sliceItemHolder;
        if (!Arrays.equals(Slice.NO_HINTS, sliceItem.mHints)) {
            versionedParcel.writeArray(1, sliceItem.mHints);
        }
        if (!"text".equals(sliceItem.mFormat)) {
            versionedParcel.writeString(2, sliceItem.mFormat);
        }
        String str2 = sliceItem.mSubType;
        if (str2 != null) {
            versionedParcel.writeString(3, str2);
        }
        SliceItemHolder sliceItemHolder2 = sliceItem.mHolder;
        versionedParcel.setOutputField(4);
        versionedParcel.writeVersionedParcelable(sliceItemHolder2);
    }
}
