package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SnapshotProtos$Snapshots extends MessageNano {
    public SnapshotProtos$Snapshot[] snapshots;

    public SnapshotProtos$Snapshots() {
        if (SnapshotProtos$Snapshot._emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                try {
                    if (SnapshotProtos$Snapshot._emptyArray == null) {
                        SnapshotProtos$Snapshot._emptyArray = new SnapshotProtos$Snapshot[0];
                    }
                } finally {
                }
            }
        }
        this.snapshots = SnapshotProtos$Snapshot._emptyArray;
        this.cachedSize = -1;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        SnapshotProtos$Snapshot[] snapshotProtos$SnapshotArr = this.snapshots;
        int i = 0;
        if (snapshotProtos$SnapshotArr == null || snapshotProtos$SnapshotArr.length <= 0) {
            return 0;
        }
        int i2 = 0;
        while (true) {
            SnapshotProtos$Snapshot[] snapshotProtos$SnapshotArr2 = this.snapshots;
            if (i >= snapshotProtos$SnapshotArr2.length) {
                return i2;
            }
            SnapshotProtos$Snapshot snapshotProtos$Snapshot = snapshotProtos$SnapshotArr2[i];
            if (snapshotProtos$Snapshot != null) {
                i2 = CodedOutputByteBufferNano.computeMessageSize(1, snapshotProtos$Snapshot) + i2;
            }
            i++;
        }
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            }
            if (readTag == 10) {
                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                SnapshotProtos$Snapshot[] snapshotProtos$SnapshotArr = this.snapshots;
                int length = snapshotProtos$SnapshotArr == null ? 0 : snapshotProtos$SnapshotArr.length;
                int i = repeatedFieldArrayLength + length;
                SnapshotProtos$Snapshot[] snapshotProtos$SnapshotArr2 = new SnapshotProtos$Snapshot[i];
                if (length != 0) {
                    System.arraycopy(snapshotProtos$SnapshotArr, 0, snapshotProtos$SnapshotArr2, 0, length);
                }
                while (length < i - 1) {
                    SnapshotProtos$Snapshot snapshotProtos$Snapshot = new SnapshotProtos$Snapshot();
                    snapshotProtos$SnapshotArr2[length] = snapshotProtos$Snapshot;
                    codedInputByteBufferNano.readMessage(snapshotProtos$Snapshot);
                    codedInputByteBufferNano.readTag();
                    length++;
                }
                SnapshotProtos$Snapshot snapshotProtos$Snapshot2 = new SnapshotProtos$Snapshot();
                snapshotProtos$SnapshotArr2[length] = snapshotProtos$Snapshot2;
                codedInputByteBufferNano.readMessage(snapshotProtos$Snapshot2);
                this.snapshots = snapshotProtos$SnapshotArr2;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        SnapshotProtos$Snapshot[] snapshotProtos$SnapshotArr = this.snapshots;
        if (snapshotProtos$SnapshotArr == null || snapshotProtos$SnapshotArr.length <= 0) {
            return;
        }
        int i = 0;
        while (true) {
            SnapshotProtos$Snapshot[] snapshotProtos$SnapshotArr2 = this.snapshots;
            if (i >= snapshotProtos$SnapshotArr2.length) {
                return;
            }
            SnapshotProtos$Snapshot snapshotProtos$Snapshot = snapshotProtos$SnapshotArr2[i];
            if (snapshotProtos$Snapshot != null) {
                codedOutputByteBufferNano.writeMessage(1, snapshotProtos$Snapshot);
            }
            i++;
        }
    }
}
