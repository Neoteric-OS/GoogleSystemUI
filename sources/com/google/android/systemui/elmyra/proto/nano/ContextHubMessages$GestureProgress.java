package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ContextHubMessages$GestureProgress extends MessageNano {
    public float progress;

    public static ContextHubMessages$GestureProgress parseFrom(byte[] bArr) {
        ContextHubMessages$GestureProgress contextHubMessages$GestureProgress = new ContextHubMessages$GestureProgress();
        contextHubMessages$GestureProgress.progress = 0.0f;
        contextHubMessages$GestureProgress.cachedSize = -1;
        return (ContextHubMessages$GestureProgress) MessageNano.mergeFrom(contextHubMessages$GestureProgress, bArr);
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        if (Float.floatToIntBits(this.progress) != Float.floatToIntBits(0.0f)) {
            return CodedOutputByteBufferNano.computeFloatSize(1);
        }
        return 0;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            }
            if (readTag == 13) {
                this.progress = codedInputByteBufferNano.readFloat();
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        if (Float.floatToIntBits(this.progress) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.writeFloat(1, this.progress);
        }
    }
}
