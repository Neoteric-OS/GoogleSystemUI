package androidx.compose.runtime;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.snapshots.Snapshot;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ParcelableSnapshotMutableIntState extends SnapshotMutableIntStateImpl implements Parcelable {
    public static final Parcelable.Creator CREATOR = new ParcelableSnapshotMutableIntState$Companion$CREATOR$1();

    public ParcelableSnapshotMutableIntState(int i) {
        SnapshotMutableIntStateImpl.IntStateStateRecord intStateStateRecord = new SnapshotMutableIntStateImpl.IntStateStateRecord(i);
        if (Snapshot.Companion.isInSnapshot()) {
            SnapshotMutableIntStateImpl.IntStateStateRecord intStateStateRecord2 = new SnapshotMutableIntStateImpl.IntStateStateRecord(i);
            intStateStateRecord2.snapshotId = 1;
            intStateStateRecord.next = intStateStateRecord2;
        }
        this.next = intStateStateRecord;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getIntValue());
    }
}
