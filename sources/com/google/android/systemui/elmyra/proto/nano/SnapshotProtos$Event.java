package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SnapshotProtos$Event extends MessageNano {
    public static volatile SnapshotProtos$Event[] _emptyArray;
    public int typesCase_ = 0;
    public Object types_ = null;

    public SnapshotProtos$Event() {
        this.cachedSize = -1;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        int computeMessageSize = this.typesCase_ == 1 ? CodedOutputByteBufferNano.computeMessageSize(1, (MessageNano) this.types_) : 0;
        if (this.typesCase_ != 2) {
            return computeMessageSize;
        }
        return computeMessageSize + CodedOutputByteBufferNano.computeRawVarint32Size(((Integer) this.types_).intValue()) + CodedOutputByteBufferNano.computeTagSize(2);
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            }
            if (readTag == 10) {
                if (this.typesCase_ != 1) {
                    this.types_ = new ChassisProtos$SensorEvent();
                }
                codedInputByteBufferNano.readMessage((MessageNano) this.types_);
                this.typesCase_ = 1;
            } else if (readTag == 16) {
                this.types_ = Integer.valueOf(codedInputByteBufferNano.readRawVarint32());
                this.typesCase_ = 2;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (this.typesCase_ == 1) {
            codedOutputByteBufferNano.writeMessage(1, (MessageNano) this.types_);
        }
        if (this.typesCase_ == 2) {
            int intValue = ((Integer) this.types_).intValue();
            codedOutputByteBufferNano.writeTag(2, 0);
            codedOutputByteBufferNano.writeRawVarint32(intValue);
        }
    }
}
