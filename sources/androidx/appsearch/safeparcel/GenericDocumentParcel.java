package androidx.appsearch.safeparcel;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GenericDocumentParcel extends AbstractSafeParcelable implements Parcelable {
    public static final Parcelable.Creator CREATOR = new GenericDocumentParcelCreator();
    public final long mCreationTimestampMillis;
    public Integer mHashCode;
    public final String mId;
    public final String mNamespace;
    public final List mParentTypes;
    public final List mProperties;
    public final ArrayMap mPropertyMap;
    public final String mSchemaType;
    public final int mScore;
    public final long mTtlMillis;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public long mCreationTimestampMillis;
        public String mId;
        public String mNamespace;
        public List mParentTypes;
        public ArrayMap mPropertyMap;
        public String mSchemaType;
        public int mScore;
        public long mTtlMillis;
    }

    public GenericDocumentParcel(String str, String str2, String str3, long j, long j2, int i, List list, List list2) {
        ArrayList arrayList = (ArrayList) list;
        ArrayMap arrayMap = new ArrayMap(arrayList.size());
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            PropertyParcel propertyParcel = (PropertyParcel) arrayList.get(i2);
            arrayMap.put(propertyParcel.mPropertyName, propertyParcel);
        }
        Objects.requireNonNull(str);
        this.mNamespace = str;
        Objects.requireNonNull(str2);
        this.mId = str2;
        Objects.requireNonNull(str3);
        this.mSchemaType = str3;
        this.mCreationTimestampMillis = j;
        this.mTtlMillis = j2;
        this.mScore = i;
        this.mProperties = list;
        this.mPropertyMap = arrayMap;
        this.mParentTypes = list2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GenericDocumentParcel)) {
            return false;
        }
        GenericDocumentParcel genericDocumentParcel = (GenericDocumentParcel) obj;
        return this.mNamespace.equals(genericDocumentParcel.mNamespace) && this.mId.equals(genericDocumentParcel.mId) && this.mSchemaType.equals(genericDocumentParcel.mSchemaType) && this.mTtlMillis == genericDocumentParcel.mTtlMillis && this.mCreationTimestampMillis == genericDocumentParcel.mCreationTimestampMillis && this.mScore == genericDocumentParcel.mScore && Objects.equals(this.mProperties, genericDocumentParcel.mProperties) && Objects.equals(this.mPropertyMap, genericDocumentParcel.mPropertyMap) && Objects.equals(this.mParentTypes, genericDocumentParcel.mParentTypes);
    }

    public final int hashCode() {
        if (this.mHashCode == null) {
            this.mHashCode = Integer.valueOf(Objects.hash(this.mNamespace, this.mId, this.mSchemaType, Long.valueOf(this.mTtlMillis), Integer.valueOf(this.mScore), Long.valueOf(this.mCreationTimestampMillis), Integer.valueOf(Objects.hashCode(this.mProperties)), Integer.valueOf(Objects.hashCode(this.mPropertyMap)), Integer.valueOf(Objects.hashCode(this.mParentTypes))));
        }
        return this.mHashCode.intValue();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putString("namespace", this.mNamespace);
        bundle.putString("id", this.mId);
        bundle.putString("schemaType", this.mSchemaType);
        bundle.putStringArrayList("parentTypes", (ArrayList) this.mParentTypes);
        bundle.putInt("score", this.mScore);
        bundle.putLong("creationTimestampMillis", this.mCreationTimestampMillis);
        bundle.putLong("ttlMillis", this.mTtlMillis);
        Bundle bundle2 = new Bundle();
        List list = this.mProperties;
        for (int i2 = 0; i2 < list.size(); i2++) {
            PropertyParcel propertyParcel = (PropertyParcel) list.get(i2);
            bundle2.putParcelable(propertyParcel.mPropertyName, propertyParcel);
        }
        bundle.putBundle("properties", bundle2);
        parcel.writeBundle(bundle);
    }
}
