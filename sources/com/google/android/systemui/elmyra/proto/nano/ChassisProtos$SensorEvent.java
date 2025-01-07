package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChassisProtos$SensorEvent extends MessageNano {
    public long timestamp = 0;
    public float[] values = WireFormatNano.EMPTY_FLOAT_ARRAY;

    public ChassisProtos$SensorEvent() {
        this.cachedSize = -1;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        long j = this.timestamp;
        int computeRawVarint64Size = j != 0 ? CodedOutputByteBufferNano.computeRawVarint64Size(j) + CodedOutputByteBufferNano.computeTagSize(1) : 0;
        float[] fArr = this.values;
        return fArr.length > 0 ? (fArr.length * 4) + computeRawVarint64Size + fArr.length : computeRawVarint64Size;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            }
            if (readTag == 8) {
                this.timestamp = codedInputByteBufferNano.readRawVarint64();
            } else if (readTag == 18) {
                int readRawVarint32 = codedInputByteBufferNano.readRawVarint32();
                int pushLimit = codedInputByteBufferNano.pushLimit(readRawVarint32);
                float[] fArr = this.values;
                int length = fArr.length;
                int i = (readRawVarint32 / 4) + length;
                float[] fArr2 = new float[i];
                if (length != 0) {
                    System.arraycopy(fArr, 0, fArr2, 0, length);
                }
                while (length < i) {
                    fArr2[length] = codedInputByteBufferNano.readFloat();
                    length++;
                }
                this.values = fArr2;
                codedInputByteBufferNano.currentLimit = pushLimit;
                int i2 = codedInputByteBufferNano.bufferSize + codedInputByteBufferNano.bufferSizeAfterLimit;
                codedInputByteBufferNano.bufferSize = i2;
                if (i2 > pushLimit) {
                    int i3 = i2 - pushLimit;
                    codedInputByteBufferNano.bufferSizeAfterLimit = i3;
                    codedInputByteBufferNano.bufferSize = i2 - i3;
                } else {
                    codedInputByteBufferNano.bufferSizeAfterLimit = 0;
                }
            } else if (readTag == 21) {
                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 21);
                float[] fArr3 = this.values;
                int length2 = fArr3.length;
                int i4 = repeatedFieldArrayLength + length2;
                float[] fArr4 = new float[i4];
                if (length2 != 0) {
                    System.arraycopy(fArr3, 0, fArr4, 0, length2);
                }
                while (length2 < i4 - 1) {
                    fArr4[length2] = codedInputByteBufferNano.readFloat();
                    codedInputByteBufferNano.readTag();
                    length2++;
                }
                fArr4[length2] = codedInputByteBufferNano.readFloat();
                this.values = fArr4;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        long j = this.timestamp;
        int i = 0;
        if (j != 0) {
            codedOutputByteBufferNano.writeTag(1, 0);
            codedOutputByteBufferNano.writeRawVarint64(j);
        }
        if (this.values.length <= 0) {
            return;
        }
        while (true) {
            float[] fArr = this.values;
            if (i >= fArr.length) {
                return;
            }
            codedOutputByteBufferNano.writeFloat(2, fArr[i]);
            i++;
        }
    }
}
