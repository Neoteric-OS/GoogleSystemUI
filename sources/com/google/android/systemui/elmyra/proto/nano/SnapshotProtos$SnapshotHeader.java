package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SnapshotProtos$SnapshotHeader extends MessageNano {
    public long identifier = 0;
    public int gestureType = 0;
    public int feedback = 0;

    public SnapshotProtos$SnapshotHeader() {
        this.cachedSize = -1;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        long j = this.identifier;
        int computeInt64Size = j != 0 ? CodedOutputByteBufferNano.computeInt64Size(j, 1) : 0;
        int i = this.gestureType;
        if (i != 0) {
            computeInt64Size += CodedOutputByteBufferNano.computeInt32Size(2, i);
        }
        int i2 = this.feedback;
        return i2 != 0 ? computeInt64Size + CodedOutputByteBufferNano.computeInt32Size(3, i2) : computeInt64Size;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            }
            if (readTag == 8) {
                this.identifier = codedInputByteBufferNano.readRawVarint64();
            } else if (readTag == 16) {
                int readRawVarint32 = codedInputByteBufferNano.readRawVarint32();
                if (readRawVarint32 == 0 || readRawVarint32 == 1 || readRawVarint32 == 2 || readRawVarint32 == 3 || readRawVarint32 == 4) {
                    this.gestureType = readRawVarint32;
                }
            } else if (readTag == 24) {
                int readRawVarint322 = codedInputByteBufferNano.readRawVarint32();
                if (readRawVarint322 == 0 || readRawVarint322 == 1 || readRawVarint322 == 2) {
                    this.feedback = readRawVarint322;
                }
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        long j = this.identifier;
        if (j != 0) {
            codedOutputByteBufferNano.writeInt64(j, 1);
        }
        int i = this.gestureType;
        if (i != 0) {
            codedOutputByteBufferNano.writeInt32(2, i);
        }
        int i2 = this.feedback;
        if (i2 != 0) {
            codedOutputByteBufferNano.writeInt32(3, i2);
        }
    }
}
