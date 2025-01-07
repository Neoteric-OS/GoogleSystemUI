package androidx.appsearch.app;

import androidx.appsearch.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EmbeddingVector extends AbstractSafeParcelable {
    public Integer mHashCode;
    public final String mModelSignature;
    public final float[] mValues;

    public EmbeddingVector(float[] fArr, String str) {
        this.mValues = fArr;
        if (fArr.length == 0) {
            throw new IllegalArgumentException("Embedding values cannot be empty.");
        }
        this.mModelSignature = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof EmbeddingVector)) {
            return false;
        }
        EmbeddingVector embeddingVector = (EmbeddingVector) obj;
        return Arrays.equals(this.mValues, embeddingVector.mValues) && this.mModelSignature.equals(embeddingVector.mModelSignature);
    }

    public final int hashCode() {
        if (this.mHashCode == null) {
            this.mHashCode = Integer.valueOf(Objects.hash(Integer.valueOf(Arrays.hashCode(this.mValues)), this.mModelSignature));
        }
        return this.mHashCode.intValue();
    }
}
