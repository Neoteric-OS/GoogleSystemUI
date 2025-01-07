package androidx.appsearch.safeparcel;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GenericDocumentParcelCreator implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        Bundle readBundle = parcel.readBundle(GenericDocumentParcelCreator.class.getClassLoader());
        String string = readBundle.getString("namespace");
        String string2 = readBundle.getString("id");
        String string3 = readBundle.getString("schemaType");
        if (string == null || string2 == null || string3 == null) {
            throw new IllegalArgumentException("GenericDocumentParcel bundle doesn't have namespace, id, or schemaType.");
        }
        ArrayMap arrayMap = new ArrayMap(0);
        ArrayList<String> stringArrayList = readBundle.getStringArrayList("parentTypes");
        ArrayList arrayList = stringArrayList != null ? new ArrayList(stringArrayList) : null;
        int i = readBundle.getInt("score");
        long j = readBundle.getLong("creationTimestampMillis");
        long j2 = readBundle.getLong("ttlMillis");
        if (j2 < 0) {
            throw new IllegalArgumentException("Document ttlMillis cannot be negative.");
        }
        Bundle bundle = readBundle.getBundle("properties");
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                PropertyParcel propertyParcel = (PropertyParcel) bundle.getParcelable(str);
                Objects.requireNonNull(propertyParcel);
                arrayMap.put(str, propertyParcel);
            }
        }
        if (j == -1) {
            j = System.currentTimeMillis();
        }
        return new GenericDocumentParcel(string, string2, string3, j, j2, i, new ArrayList(arrayMap.values()), arrayList);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new GenericDocumentParcel[i];
    }
}
