package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ContextHubMessages$GestureDetected extends MessageNano {
    public boolean hapticConsumed;
    public boolean hostSuspended;

    public static ContextHubMessages$GestureDetected parseFrom(byte[] bArr) {
        ContextHubMessages$GestureDetected contextHubMessages$GestureDetected = new ContextHubMessages$GestureDetected();
        contextHubMessages$GestureDetected.hostSuspended = false;
        contextHubMessages$GestureDetected.hapticConsumed = false;
        contextHubMessages$GestureDetected.cachedSize = -1;
        return (ContextHubMessages$GestureDetected) MessageNano.mergeFrom(contextHubMessages$GestureDetected, bArr);
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        int computeTagSize = this.hostSuspended ? CodedOutputByteBufferNano.computeTagSize(1) + 1 : 0;
        return this.hapticConsumed ? computeTagSize + CodedOutputByteBufferNano.computeTagSize(2) + 1 : computeTagSize;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            }
            if (readTag == 8) {
                this.hostSuspended = codedInputByteBufferNano.readRawVarint32() != 0;
            } else if (readTag == 16) {
                this.hapticConsumed = codedInputByteBufferNano.readRawVarint32() != 0;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        boolean z = this.hostSuspended;
        if (z) {
            codedOutputByteBufferNano.writeTag(1, 0);
            codedOutputByteBufferNano.writeRawByte(z ? 1 : 0);
        }
        boolean z2 = this.hapticConsumed;
        if (z2) {
            codedOutputByteBufferNano.writeTag(2, 0);
            codedOutputByteBufferNano.writeRawByte(z2 ? 1 : 0);
        }
    }
}
