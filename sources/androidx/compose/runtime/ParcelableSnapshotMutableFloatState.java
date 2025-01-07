package androidx.compose.runtime;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import androidx.compose.runtime.snapshots.Snapshot;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ParcelableSnapshotMutableFloatState extends SnapshotMutableFloatStateImpl implements Parcelable {
    public static final Parcelable.Creator CREATOR = new ParcelableSnapshotMutableFloatState$Companion$CREATOR$1();

    public ParcelableSnapshotMutableFloatState(float f) {
        SnapshotMutableFloatStateImpl.FloatStateStateRecord floatStateStateRecord = new SnapshotMutableFloatStateImpl.FloatStateStateRecord(f);
        if (Snapshot.Companion.isInSnapshot()) {
            SnapshotMutableFloatStateImpl.FloatStateStateRecord floatStateStateRecord2 = new SnapshotMutableFloatStateImpl.FloatStateStateRecord(f);
            floatStateStateRecord2.snapshotId = 1;
            floatStateStateRecord.next = floatStateStateRecord2;
        }
        this.next = floatStateStateRecord;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(getFloatValue());
    }
}
