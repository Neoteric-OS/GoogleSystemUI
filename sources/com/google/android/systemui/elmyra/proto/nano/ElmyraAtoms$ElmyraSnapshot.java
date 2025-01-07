package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ElmyraAtoms$ElmyraSnapshot extends MessageNano {
    public SnapshotProtos$Snapshot snapshot = null;
    public ChassisProtos$Chassis chassis = null;

    public ElmyraAtoms$ElmyraSnapshot() {
        this.cachedSize = -1;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        SnapshotProtos$Snapshot snapshotProtos$Snapshot = this.snapshot;
        int computeMessageSize = snapshotProtos$Snapshot != null ? CodedOutputByteBufferNano.computeMessageSize(1, snapshotProtos$Snapshot) : 0;
        ChassisProtos$Chassis chassisProtos$Chassis = this.chassis;
        return chassisProtos$Chassis != null ? computeMessageSize + CodedOutputByteBufferNano.computeMessageSize(2, chassisProtos$Chassis) : computeMessageSize;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            }
            if (readTag == 10) {
                if (this.snapshot == null) {
                    this.snapshot = new SnapshotProtos$Snapshot();
                }
                codedInputByteBufferNano.readMessage(this.snapshot);
            } else if (readTag == 18) {
                if (this.chassis == null) {
                    this.chassis = new ChassisProtos$Chassis();
                }
                codedInputByteBufferNano.readMessage(this.chassis);
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        SnapshotProtos$Snapshot snapshotProtos$Snapshot = this.snapshot;
        if (snapshotProtos$Snapshot != null) {
            codedOutputByteBufferNano.writeMessage(1, snapshotProtos$Snapshot);
        }
        ChassisProtos$Chassis chassisProtos$Chassis = this.chassis;
        if (chassisProtos$Chassis != null) {
            codedOutputByteBufferNano.writeMessage(2, chassisProtos$Chassis);
        }
    }
}
