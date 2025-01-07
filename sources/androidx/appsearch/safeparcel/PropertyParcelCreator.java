package androidx.appsearch.safeparcel;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.appsearch.app.EmbeddingVector;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PropertyParcelCreator implements Parcelable.Creator {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        EmbeddingVector[] embeddingVectorArr;
        String[] strArr;
        long[] jArr;
        double[] dArr;
        boolean[] zArr;
        GenericDocumentParcel[] genericDocumentParcelArr;
        byte[] byteArray;
        boolean[] zArr2;
        Bundle readBundle = parcel.readBundle(PropertyParcelCreator.class.getClassLoader());
        Objects.requireNonNull(readBundle);
        String string = readBundle.getString("propertyName");
        Objects.requireNonNull(string);
        String[] stringArray = readBundle.getStringArray("stringArray");
        long[] longArray = readBundle.getLongArray("longArray");
        double[] doubleArray = readBundle.getDoubleArray("doubleArray");
        boolean[] booleanArray = readBundle.getBooleanArray("booleanArray");
        ArrayList parcelableArrayList = readBundle.getParcelableArrayList("bytesArray");
        Parcelable[] parcelableArray = readBundle.getParcelableArray("docArray");
        ArrayList parcelableArrayList2 = readBundle.getParcelableArrayList("embeddingArray");
        byte[][] bArr = null;
        if (stringArray != null) {
            strArr = stringArray;
            jArr = null;
            dArr = null;
        } else {
            if (longArray == null) {
                if (doubleArray != null) {
                    dArr = doubleArray;
                    strArr = null;
                    jArr = null;
                    zArr = null;
                    zArr2 = zArr;
                    embeddingVectorArr = zArr2;
                    genericDocumentParcelArr = zArr2;
                    return new PropertyParcel(string, strArr, jArr, dArr, zArr, bArr, genericDocumentParcelArr, embeddingVectorArr);
                }
                if (booleanArray != null) {
                    zArr = booleanArray;
                    strArr = null;
                    jArr = null;
                    dArr = null;
                    zArr2 = null;
                    embeddingVectorArr = zArr2;
                    genericDocumentParcelArr = zArr2;
                    return new PropertyParcel(string, strArr, jArr, dArr, zArr, bArr, genericDocumentParcelArr, embeddingVectorArr);
                }
                int i = 0;
                if (parcelableArrayList != null) {
                    byte[][] bArr2 = new byte[parcelableArrayList.size()][];
                    while (i < parcelableArrayList.size()) {
                        Bundle bundle = (Bundle) parcelableArrayList.get(i);
                        if (bundle != null && (byteArray = bundle.getByteArray("byteArray")) != null) {
                            bArr2[i] = byteArray;
                        }
                        i++;
                    }
                    strArr = null;
                    jArr = null;
                    dArr = null;
                    zArr = null;
                    genericDocumentParcelArr = 0;
                    embeddingVectorArr = null;
                    bArr = bArr2;
                } else if (parcelableArray != null && parcelableArray.length > 0) {
                    GenericDocumentParcel[] genericDocumentParcelArr2 = new GenericDocumentParcel[parcelableArray.length];
                    System.arraycopy(parcelableArray, 0, genericDocumentParcelArr2, 0, parcelableArray.length);
                    genericDocumentParcelArr = genericDocumentParcelArr2;
                    strArr = null;
                    jArr = null;
                    dArr = null;
                    zArr = null;
                    embeddingVectorArr = null;
                } else {
                    if (parcelableArrayList2 == null) {
                        throw new IllegalArgumentException("property bundle passed in doesn't have any value set.");
                    }
                    EmbeddingVector[] embeddingVectorArr2 = new EmbeddingVector[parcelableArrayList2.size()];
                    while (i < parcelableArrayList2.size()) {
                        Bundle bundle2 = (Bundle) parcelableArrayList2.get(i);
                        if (bundle2 != null) {
                            float[] floatArray = bundle2.getFloatArray("embeddingValue");
                            String string2 = bundle2.getString("embeddingModelSignature");
                            if (floatArray != null && string2 != null) {
                                embeddingVectorArr2[i] = new EmbeddingVector(floatArray, string2);
                            }
                        }
                        i++;
                    }
                    embeddingVectorArr = embeddingVectorArr2;
                    strArr = null;
                    jArr = null;
                    dArr = null;
                    zArr = null;
                    genericDocumentParcelArr = 0;
                }
                return new PropertyParcel(string, strArr, jArr, dArr, zArr, bArr, genericDocumentParcelArr, embeddingVectorArr);
            }
            jArr = longArray;
            strArr = null;
            dArr = null;
        }
        zArr = dArr;
        zArr2 = zArr;
        embeddingVectorArr = zArr2;
        genericDocumentParcelArr = zArr2;
        return new PropertyParcel(string, strArr, jArr, dArr, zArr, bArr, genericDocumentParcelArr, embeddingVectorArr);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new PropertyParcel[i];
    }
}
