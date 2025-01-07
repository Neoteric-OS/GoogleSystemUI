package com.google.android.systemui.columbus.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusProto$GestureDetected extends MessageNano {
    public float[] featureVector;
    public int gestureType;

    public static ColumbusProto$GestureDetected parseFrom(byte[] bArr) {
        ColumbusProto$GestureDetected columbusProto$GestureDetected = new ColumbusProto$GestureDetected();
        columbusProto$GestureDetected.gestureType = 0;
        columbusProto$GestureDetected.featureVector = WireFormatNano.EMPTY_FLOAT_ARRAY;
        columbusProto$GestureDetected.cachedSize = -1;
        return (ColumbusProto$GestureDetected) MessageNano.mergeFrom(columbusProto$GestureDetected, bArr);
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        int i = this.gestureType;
        int computeInt32Size = i != 0 ? CodedOutputByteBufferNano.computeInt32Size(1, i) : 0;
        float[] fArr = this.featureVector;
        return (fArr == null || fArr.length <= 0) ? computeInt32Size : (fArr.length * 4) + computeInt32Size + fArr.length;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            }
            if (readTag == 8) {
                int readRawVarint32 = codedInputByteBufferNano.readRawVarint32();
                if (readRawVarint32 == 0 || readRawVarint32 == 1 || readRawVarint32 == 2) {
                    this.gestureType = readRawVarint32;
                }
            } else if (readTag == 18) {
                int readRawVarint322 = codedInputByteBufferNano.readRawVarint32();
                int pushLimit = codedInputByteBufferNano.pushLimit(readRawVarint322);
                int i = readRawVarint322 / 4;
                float[] fArr = this.featureVector;
                int length = fArr == null ? 0 : fArr.length;
                int i2 = i + length;
                float[] fArr2 = new float[i2];
                if (length != 0) {
                    System.arraycopy(fArr, 0, fArr2, 0, length);
                }
                while (length < i2) {
                    fArr2[length] = codedInputByteBufferNano.readFloat();
                    length++;
                }
                this.featureVector = fArr2;
                codedInputByteBufferNano.currentLimit = pushLimit;
                int i3 = codedInputByteBufferNano.bufferSize + codedInputByteBufferNano.bufferSizeAfterLimit;
                codedInputByteBufferNano.bufferSize = i3;
                if (i3 > pushLimit) {
                    int i4 = i3 - pushLimit;
                    codedInputByteBufferNano.bufferSizeAfterLimit = i4;
                    codedInputByteBufferNano.bufferSize = i3 - i4;
                } else {
                    codedInputByteBufferNano.bufferSizeAfterLimit = 0;
                }
            } else if (readTag == 21) {
                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 21);
                float[] fArr3 = this.featureVector;
                int length2 = fArr3 == null ? 0 : fArr3.length;
                int i5 = repeatedFieldArrayLength + length2;
                float[] fArr4 = new float[i5];
                if (length2 != 0) {
                    System.arraycopy(fArr3, 0, fArr4, 0, length2);
                }
                while (length2 < i5 - 1) {
                    fArr4[length2] = codedInputByteBufferNano.readFloat();
                    codedInputByteBufferNano.readTag();
                    length2++;
                }
                fArr4[length2] = codedInputByteBufferNano.readFloat();
                this.featureVector = fArr4;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        int i = this.gestureType;
        if (i != 0) {
            codedOutputByteBufferNano.writeInt32(1, i);
        }
        float[] fArr = this.featureVector;
        if (fArr == null || fArr.length <= 0) {
            return;
        }
        int i2 = 0;
        while (true) {
            float[] fArr2 = this.featureVector;
            if (i2 >= fArr2.length) {
                return;
            }
            codedOutputByteBufferNano.writeFloat(2, fArr2[i2]);
            i2++;
        }
    }
}
