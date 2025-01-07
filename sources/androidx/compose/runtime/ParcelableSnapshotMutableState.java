package androidx.compose.runtime;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ParcelableSnapshotMutableState extends SnapshotMutableStateImpl implements Parcelable {
    public static final Parcelable.Creator CREATOR = new ParcelableSnapshotMutableState$Companion$CREATOR$1();

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int i2;
        parcel.writeValue(getValue());
        NeverEqualPolicy neverEqualPolicy = NeverEqualPolicy.INSTANCE;
        SnapshotMutationPolicy snapshotMutationPolicy = this.policy;
        if (Intrinsics.areEqual(snapshotMutationPolicy, neverEqualPolicy)) {
            i2 = 0;
        } else if (Intrinsics.areEqual(snapshotMutationPolicy, StructuralEqualityPolicy.INSTANCE)) {
            i2 = 1;
        } else {
            if (!Intrinsics.areEqual(snapshotMutationPolicy, ReferentialEqualityPolicy.INSTANCE)) {
                throw new IllegalStateException("Only known types of MutableState's SnapshotMutationPolicy are supported");
            }
            i2 = 2;
        }
        parcel.writeInt(i2);
    }
}
