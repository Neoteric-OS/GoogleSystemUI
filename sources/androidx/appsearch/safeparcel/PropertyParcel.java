package androidx.appsearch.safeparcel;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.appsearch.app.EmbeddingVector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PropertyParcel extends AbstractSafeParcelable implements Parcelable {
    public static final Parcelable.Creator CREATOR = new PropertyParcelCreator();
    public final boolean[] mBooleanValues;
    public final byte[][] mBytesValues;
    public final GenericDocumentParcel[] mDocumentValues;
    public final double[] mDoubleValues;
    public final EmbeddingVector[] mEmbeddingValues;
    public Integer mHashCode;
    public final long[] mLongValues;
    public final String mPropertyName;
    public final String[] mStringValues;

    public PropertyParcel(String str, String[] strArr, long[] jArr, double[] dArr, boolean[] zArr, byte[][] bArr, GenericDocumentParcel[] genericDocumentParcelArr, EmbeddingVector[] embeddingVectorArr) {
        Objects.requireNonNull(str);
        this.mPropertyName = str;
        this.mStringValues = strArr;
        this.mLongValues = jArr;
        this.mDoubleValues = dArr;
        this.mBooleanValues = zArr;
        this.mBytesValues = bArr;
        this.mDocumentValues = genericDocumentParcelArr;
        this.mEmbeddingValues = embeddingVectorArr;
        int i = strArr != null ? 1 : 0;
        i = jArr != null ? i + 1 : i;
        i = dArr != null ? i + 1 : i;
        i = zArr != null ? i + 1 : i;
        i = bArr != null ? i + 1 : i;
        i = genericDocumentParcelArr != null ? i + 1 : i;
        i = embeddingVectorArr != null ? i + 1 : i;
        if (i == 0 || i > 1) {
            throw new IllegalArgumentException("One and only one type array can be set in PropertyParcel");
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PropertyParcel)) {
            return false;
        }
        PropertyParcel propertyParcel = (PropertyParcel) obj;
        if (this.mPropertyName.equals(propertyParcel.mPropertyName)) {
            return Arrays.equals(this.mStringValues, propertyParcel.mStringValues) && Arrays.equals(this.mLongValues, propertyParcel.mLongValues) && Arrays.equals(this.mDoubleValues, propertyParcel.mDoubleValues) && Arrays.equals(this.mBooleanValues, propertyParcel.mBooleanValues) && Arrays.deepEquals(this.mBytesValues, propertyParcel.mBytesValues) && Arrays.equals(this.mDocumentValues, propertyParcel.mDocumentValues) && Arrays.deepEquals(this.mEmbeddingValues, propertyParcel.mEmbeddingValues);
        }
        return false;
    }

    public final int hashCode() {
        int deepHashCode;
        if (this.mHashCode == null) {
            String[] strArr = this.mStringValues;
            if (strArr != null) {
                deepHashCode = Arrays.hashCode(strArr);
            } else {
                long[] jArr = this.mLongValues;
                if (jArr != null) {
                    deepHashCode = Arrays.hashCode(jArr);
                } else {
                    double[] dArr = this.mDoubleValues;
                    if (dArr != null) {
                        deepHashCode = Arrays.hashCode(dArr);
                    } else {
                        boolean[] zArr = this.mBooleanValues;
                        if (zArr != null) {
                            deepHashCode = Arrays.hashCode(zArr);
                        } else {
                            byte[][] bArr = this.mBytesValues;
                            if (bArr != null) {
                                deepHashCode = Arrays.deepHashCode(bArr);
                            } else {
                                GenericDocumentParcel[] genericDocumentParcelArr = this.mDocumentValues;
                                if (genericDocumentParcelArr != null) {
                                    deepHashCode = Arrays.hashCode(genericDocumentParcelArr);
                                } else {
                                    EmbeddingVector[] embeddingVectorArr = this.mEmbeddingValues;
                                    deepHashCode = embeddingVectorArr != null ? Arrays.deepHashCode(embeddingVectorArr) : 0;
                                }
                            }
                        }
                    }
                }
            }
            this.mHashCode = Integer.valueOf(Objects.hash(this.mPropertyName, Integer.valueOf(deepHashCode)));
        }
        return this.mHashCode.intValue();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        bundle.putString("propertyName", this.mPropertyName);
        String[] strArr = this.mStringValues;
        long[] jArr = this.mLongValues;
        double[] dArr = this.mDoubleValues;
        boolean[] zArr = this.mBooleanValues;
        byte[][] bArr = this.mBytesValues;
        GenericDocumentParcel[] genericDocumentParcelArr = this.mDocumentValues;
        EmbeddingVector[] embeddingVectorArr = this.mEmbeddingValues;
        if (strArr != null) {
            bundle.putStringArray("stringArray", strArr);
        } else if (jArr != null) {
            bundle.putLongArray("longArray", jArr);
        } else if (dArr != null) {
            bundle.putDoubleArray("doubleArray", dArr);
        } else if (zArr != null) {
            bundle.putBooleanArray("booleanArray", zArr);
        } else {
            int i2 = 0;
            if (bArr != null) {
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>(bArr.length);
                while (i2 < bArr.length) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putByteArray("byteArray", bArr[i2]);
                    arrayList.add(bundle2);
                    i2++;
                }
                bundle.putParcelableArrayList("bytesArray", arrayList);
            } else if (genericDocumentParcelArr != null) {
                bundle.putParcelableArray("docArray", genericDocumentParcelArr);
            } else if (embeddingVectorArr != null) {
                ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>(embeddingVectorArr.length);
                while (i2 < embeddingVectorArr.length) {
                    Bundle bundle3 = new Bundle();
                    bundle3.putFloatArray("embeddingValue", embeddingVectorArr[i2].mValues);
                    bundle3.putString("embeddingModelSignature", embeddingVectorArr[i2].mModelSignature);
                    arrayList2.add(bundle3);
                    i2++;
                }
                bundle.putParcelableArrayList("embeddingArray", arrayList2);
            }
        }
        parcel.writeBundle(bundle);
    }
}
