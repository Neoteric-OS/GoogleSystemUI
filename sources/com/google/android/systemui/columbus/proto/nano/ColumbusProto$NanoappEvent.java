package com.google.android.systemui.columbus.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusProto$NanoappEvent extends MessageNano {
    public static volatile ColumbusProto$NanoappEvent[] _emptyArray;
    public long timestamp = 0;
    public int type = 0;

    public ColumbusProto$NanoappEvent() {
        this.cachedSize = -1;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        long j = this.timestamp;
        int computeRawVarint64Size = j != 0 ? CodedOutputByteBufferNano.computeRawVarint64Size(j) + CodedOutputByteBufferNano.computeTagSize(1) : 0;
        int i = this.type;
        return i != 0 ? computeRawVarint64Size + CodedOutputByteBufferNano.computeInt32Size(2, i) : computeRawVarint64Size;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag != 0) {
                if (readTag == 8) {
                    this.timestamp = codedInputByteBufferNano.readRawVarint64();
                } else if (readTag == 16) {
                    int readRawVarint32 = codedInputByteBufferNano.readRawVarint32();
                    switch (readRawVarint32) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            this.type = readRawVarint32;
                            break;
                    }
                } else if (!codedInputByteBufferNano.skipField(readTag)) {
                }
            }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        long j = this.timestamp;
        if (j != 0) {
            codedOutputByteBufferNano.writeTag(1, 0);
            codedOutputByteBufferNano.writeRawVarint64(j);
        }
        int i = this.type;
        if (i != 0) {
            codedOutputByteBufferNano.writeInt32(2, i);
        }
    }
}
