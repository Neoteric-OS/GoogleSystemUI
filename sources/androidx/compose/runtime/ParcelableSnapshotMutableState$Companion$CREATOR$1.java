package androidx.compose.runtime;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ParcelableSnapshotMutableState$Companion$CREATOR$1 implements Parcelable.ClassLoaderCreator {
    @Override // android.os.Parcelable.ClassLoaderCreator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return createFromParcel(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new ParcelableSnapshotMutableState[i];
    }

    public static ParcelableSnapshotMutableState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        SnapshotMutationPolicy snapshotMutationPolicy;
        if (classLoader == null) {
            classLoader = ParcelableSnapshotMutableState$Companion$CREATOR$1.class.getClassLoader();
        }
        Object readValue = parcel.readValue(classLoader);
        int readInt = parcel.readInt();
        if (readInt == 0) {
            snapshotMutationPolicy = NeverEqualPolicy.INSTANCE;
        } else if (readInt == 1) {
            snapshotMutationPolicy = StructuralEqualityPolicy.INSTANCE;
        } else {
            if (readInt != 2) {
                throw new IllegalStateException(GenericDocument$$ExternalSyntheticOutline0.m("Unsupported MutableState policy ", " was restored", readInt));
            }
            snapshotMutationPolicy = ReferentialEqualityPolicy.INSTANCE;
        }
        return new ParcelableSnapshotMutableState(readValue, snapshotMutationPolicy);
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return createFromParcel(parcel, (ClassLoader) null);
    }
}
