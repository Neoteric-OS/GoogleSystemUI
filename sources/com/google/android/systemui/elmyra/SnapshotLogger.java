package com.google.android.systemui.elmyra;

import android.os.Binder;
import com.android.systemui.Dumpable;
import com.google.android.systemui.elmyra.proto.nano.ChassisProtos$SensorEvent;
import com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$Event;
import com.google.android.systemui.elmyra.proto.nano.SnapshotProtos$Snapshot;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SnapshotLogger implements Dumpable {
    public final int mSnapshotCapacity;
    public final List mSnapshots;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Snapshot {
        public final SnapshotProtos$Snapshot mSnapshot;
        public final long mTimestamp;

        public Snapshot(SnapshotProtos$Snapshot snapshotProtos$Snapshot, long j) {
            this.mSnapshot = snapshotProtos$Snapshot;
            this.mTimestamp = j;
        }
    }

    public SnapshotLogger(int i) {
        this.mSnapshotCapacity = i;
        this.mSnapshots = new ArrayList(i);
    }

    public final void addSnapshot(SnapshotProtos$Snapshot snapshotProtos$Snapshot, long j) {
        if (((ArrayList) this.mSnapshots).size() == this.mSnapshotCapacity) {
            this.mSnapshots.remove(0);
        }
        this.mSnapshots.add(new Snapshot(snapshotProtos$Snapshot, j));
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            dumpInternal(printWriter);
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    public final void dumpInternal(PrintWriter printWriter) {
        printWriter.println("Dumping Elmyra Snapshots");
        for (int i = 0; i < ((ArrayList) this.mSnapshots).size(); i++) {
            SnapshotProtos$Snapshot snapshotProtos$Snapshot = ((Snapshot) ((ArrayList) this.mSnapshots).get(i)).mSnapshot;
            printWriter.println("SystemTime: " + ((Snapshot) ((ArrayList) this.mSnapshots).get(i)).mTimestamp);
            printWriter.println("Snapshot: " + i);
            printWriter.print("header {");
            printWriter.print("  identifier: " + snapshotProtos$Snapshot.header.identifier);
            printWriter.print("  gesture_type: " + snapshotProtos$Snapshot.header.gestureType);
            printWriter.print("  feedback: " + snapshotProtos$Snapshot.header.feedback);
            printWriter.print("}");
            for (int i2 = 0; i2 < snapshotProtos$Snapshot.events.length; i2++) {
                printWriter.print("events {");
                SnapshotProtos$Event snapshotProtos$Event = snapshotProtos$Snapshot.events[i2];
                int i3 = snapshotProtos$Event.typesCase_;
                if (i3 == 2) {
                    StringBuilder sb = new StringBuilder("  gesture_stage: ");
                    SnapshotProtos$Event snapshotProtos$Event2 = snapshotProtos$Snapshot.events[i2];
                    sb.append(snapshotProtos$Event2.typesCase_ == 2 ? ((Integer) snapshotProtos$Event2.types_).intValue() : 0);
                    printWriter.print(sb.toString());
                } else if (i3 == 1) {
                    ChassisProtos$SensorEvent chassisProtos$SensorEvent = i3 == 1 ? (ChassisProtos$SensorEvent) snapshotProtos$Event.types_ : null;
                    printWriter.print("  sensor_event {");
                    printWriter.print("    timestamp: " + chassisProtos$SensorEvent.timestamp);
                    for (int i4 = 0; i4 < chassisProtos$SensorEvent.values.length; i4++) {
                        printWriter.print("    values: " + chassisProtos$SensorEvent.values[i4]);
                    }
                    printWriter.print("  }");
                }
                printWriter.print("}");
            }
            printWriter.println("sensitivity_setting: " + snapshotProtos$Snapshot.sensitivitySetting);
            printWriter.println();
        }
        this.mSnapshots.clear();
        printWriter.println("Finished Dumping Elmyra Snapshots");
    }
}
