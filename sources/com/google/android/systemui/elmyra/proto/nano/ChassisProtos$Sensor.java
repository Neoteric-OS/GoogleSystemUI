package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChassisProtos$Sensor extends MessageNano {
    public static volatile ChassisProtos$Sensor[] _emptyArray;
    public int source = 0;
    public int gain = 0;
    public float sensitivity = 0.0f;
    public ElmyraFilters$Filter[] filters = ElmyraFilters$Filter.emptyArray();

    public ChassisProtos$Sensor() {
        this.cachedSize = -1;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        int i;
        int i2 = this.source;
        int i3 = 0;
        if (i2 != 0) {
            i = CodedOutputByteBufferNano.computeRawVarint32Size(i2) + CodedOutputByteBufferNano.computeTagSize(1);
        } else {
            i = 0;
        }
        int i4 = this.gain;
        if (i4 != 0) {
            i += CodedOutputByteBufferNano.computeInt32Size(2, i4);
        }
        if (Float.floatToIntBits(this.sensitivity) != Float.floatToIntBits(0.0f)) {
            i += CodedOutputByteBufferNano.computeFloatSize(3);
        }
        ElmyraFilters$Filter[] elmyraFilters$FilterArr = this.filters;
        if (elmyraFilters$FilterArr != null && elmyraFilters$FilterArr.length > 0) {
            while (true) {
                ElmyraFilters$Filter[] elmyraFilters$FilterArr2 = this.filters;
                if (i3 >= elmyraFilters$FilterArr2.length) {
                    break;
                }
                ElmyraFilters$Filter elmyraFilters$Filter = elmyraFilters$FilterArr2[i3];
                if (elmyraFilters$Filter != null) {
                    i = CodedOutputByteBufferNano.computeMessageSize(4, elmyraFilters$Filter) + i;
                }
                i3++;
            }
        }
        return i;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            }
            if (readTag == 8) {
                this.source = codedInputByteBufferNano.readRawVarint32();
            } else if (readTag == 16) {
                this.gain = codedInputByteBufferNano.readRawVarint32();
            } else if (readTag == 29) {
                this.sensitivity = codedInputByteBufferNano.readFloat();
            } else if (readTag == 34) {
                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 34);
                ElmyraFilters$Filter[] elmyraFilters$FilterArr = this.filters;
                int length = elmyraFilters$FilterArr == null ? 0 : elmyraFilters$FilterArr.length;
                int i = repeatedFieldArrayLength + length;
                ElmyraFilters$Filter[] elmyraFilters$FilterArr2 = new ElmyraFilters$Filter[i];
                if (length != 0) {
                    System.arraycopy(elmyraFilters$FilterArr, 0, elmyraFilters$FilterArr2, 0, length);
                }
                while (length < i - 1) {
                    ElmyraFilters$Filter elmyraFilters$Filter = new ElmyraFilters$Filter();
                    elmyraFilters$FilterArr2[length] = elmyraFilters$Filter;
                    codedInputByteBufferNano.readMessage(elmyraFilters$Filter);
                    codedInputByteBufferNano.readTag();
                    length++;
                }
                ElmyraFilters$Filter elmyraFilters$Filter2 = new ElmyraFilters$Filter();
                elmyraFilters$FilterArr2[length] = elmyraFilters$Filter2;
                codedInputByteBufferNano.readMessage(elmyraFilters$Filter2);
                this.filters = elmyraFilters$FilterArr2;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        int i = this.source;
        int i2 = 0;
        if (i != 0) {
            codedOutputByteBufferNano.writeTag(1, 0);
            codedOutputByteBufferNano.writeRawVarint32(i);
        }
        int i3 = this.gain;
        if (i3 != 0) {
            codedOutputByteBufferNano.writeInt32(2, i3);
        }
        if (Float.floatToIntBits(this.sensitivity) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.writeFloat(3, this.sensitivity);
        }
        ElmyraFilters$Filter[] elmyraFilters$FilterArr = this.filters;
        if (elmyraFilters$FilterArr == null || elmyraFilters$FilterArr.length <= 0) {
            return;
        }
        while (true) {
            ElmyraFilters$Filter[] elmyraFilters$FilterArr2 = this.filters;
            if (i2 >= elmyraFilters$FilterArr2.length) {
                return;
            }
            ElmyraFilters$Filter elmyraFilters$Filter = elmyraFilters$FilterArr2[i2];
            if (elmyraFilters$Filter != null) {
                codedOutputByteBufferNano.writeMessage(4, elmyraFilters$Filter);
            }
            i2++;
        }
    }
}
