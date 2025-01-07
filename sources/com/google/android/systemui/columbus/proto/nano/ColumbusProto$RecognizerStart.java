package com.google.android.systemui.columbus.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusProto$RecognizerStart extends MessageNano {
    public final /* synthetic */ int $r8$classId;
    public float sensitivity;

    public ColumbusProto$RecognizerStart(int i) {
        this.$r8$classId = i;
        switch (i) {
            case 1:
                this.sensitivity = 0.0f;
                this.cachedSize = -1;
                break;
            default:
                this.sensitivity = 0.0f;
                this.cachedSize = -1;
                break;
        }
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        switch (this.$r8$classId) {
            case 0:
                if (Float.floatToIntBits(this.sensitivity) != Float.floatToIntBits(0.0f)) {
                    break;
                }
                break;
            default:
                if (Float.floatToIntBits(this.sensitivity) != Float.floatToIntBits(0.0f)) {
                    break;
                }
                break;
        }
        return CodedOutputByteBufferNano.computeFloatSize(1);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.protobuf.nano.MessageNano
    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        switch (this.$r8$classId) {
            case 0:
                while (true) {
                    int readTag = codedInputByteBufferNano.readTag();
                    if (readTag == 0) {
                        break;
                    } else if (readTag == 13) {
                        this.sensitivity = codedInputByteBufferNano.readFloat();
                    } else if (!codedInputByteBufferNano.skipField(readTag)) {
                        break;
                    }
                }
            default:
                while (true) {
                    int readTag2 = codedInputByteBufferNano.readTag();
                    if (readTag2 == 0) {
                        break;
                    } else if (readTag2 == 13) {
                        this.sensitivity = codedInputByteBufferNano.readFloat();
                    } else if (!codedInputByteBufferNano.skipField(readTag2)) {
                        break;
                    }
                }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        switch (this.$r8$classId) {
            case 0:
                if (Float.floatToIntBits(this.sensitivity) != Float.floatToIntBits(0.0f)) {
                    codedOutputByteBufferNano.writeFloat(1, this.sensitivity);
                    break;
                }
                break;
            default:
                if (Float.floatToIntBits(this.sensitivity) != Float.floatToIntBits(0.0f)) {
                    codedOutputByteBufferNano.writeFloat(1, this.sensitivity);
                    break;
                }
                break;
        }
    }
}
