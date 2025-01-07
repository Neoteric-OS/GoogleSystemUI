package androidx.compose.runtime;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.compose.runtime.SnapshotMutableLongStateImpl;
import androidx.compose.runtime.snapshots.Snapshot;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ParcelableSnapshotMutableLongState extends SnapshotMutableLongStateImpl implements Parcelable {
    public static final Parcelable.Creator CREATOR = new ParcelableSnapshotMutableLongState$Companion$CREATOR$1();

    public ParcelableSnapshotMutableLongState(long j) {
        SnapshotMutableLongStateImpl.LongStateStateRecord longStateStateRecord = new SnapshotMutableLongStateImpl.LongStateStateRecord(j);
        if (Snapshot.Companion.isInSnapshot()) {
            SnapshotMutableLongStateImpl.LongStateStateRecord longStateStateRecord2 = new SnapshotMutableLongStateImpl.LongStateStateRecord(j);
            longStateStateRecord2.snapshotId = 1;
            longStateStateRecord.next = longStateStateRecord2;
        }
        this.next = longStateStateRecord;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(getLongValue());
    }
}
