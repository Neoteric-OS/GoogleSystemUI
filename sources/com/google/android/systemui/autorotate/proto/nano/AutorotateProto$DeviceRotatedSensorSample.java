package com.google.android.systemui.autorotate.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AutorotateProto$DeviceRotatedSensorSample extends MessageNano {
    public static volatile AutorotateProto$DeviceRotatedSensorSample[] _emptyArray;
    public int timestampOffset = 0;
    public int sensorType = 0;
    public float xValue = 0.0f;
    public float yValue = 0.0f;
    public float zValue = 0.0f;

    public AutorotateProto$DeviceRotatedSensorSample() {
        this.cachedSize = -1;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        int i = this.timestampOffset;
        int computeInt32Size = i != 0 ? CodedOutputByteBufferNano.computeInt32Size(1, i) : 0;
        int i2 = this.sensorType;
        if (i2 != 0) {
            computeInt32Size += CodedOutputByteBufferNano.computeInt32Size(2, i2);
        }
        if (Float.floatToIntBits(this.xValue) != Float.floatToIntBits(0.0f)) {
            computeInt32Size += CodedOutputByteBufferNano.computeFloatSize(3);
        }
        if (Float.floatToIntBits(this.yValue) != Float.floatToIntBits(0.0f)) {
            computeInt32Size += CodedOutputByteBufferNano.computeFloatSize(4);
        }
        return Float.floatToIntBits(this.zValue) != Float.floatToIntBits(0.0f) ? computeInt32Size + CodedOutputByteBufferNano.computeFloatSize(5) : computeInt32Size;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            }
            if (readTag == 8) {
                this.timestampOffset = codedInputByteBufferNano.readRawVarint32();
            } else if (readTag == 16) {
                int readRawVarint32 = codedInputByteBufferNano.readRawVarint32();
                if (readRawVarint32 == 0 || readRawVarint32 == 1 || readRawVarint32 == 2) {
                    this.sensorType = readRawVarint32;
                }
            } else if (readTag == 29) {
                this.xValue = codedInputByteBufferNano.readFloat();
            } else if (readTag == 37) {
                this.yValue = codedInputByteBufferNano.readFloat();
            } else if (readTag == 45) {
                this.zValue = codedInputByteBufferNano.readFloat();
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        int i = this.timestampOffset;
        if (i != 0) {
            codedOutputByteBufferNano.writeInt32(1, i);
        }
        int i2 = this.sensorType;
        if (i2 != 0) {
            codedOutputByteBufferNano.writeInt32(2, i2);
        }
        if (Float.floatToIntBits(this.xValue) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.writeFloat(3, this.xValue);
        }
        if (Float.floatToIntBits(this.yValue) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.writeFloat(4, this.yValue);
        }
        if (Float.floatToIntBits(this.zValue) != Float.floatToIntBits(0.0f)) {
            codedOutputByteBufferNano.writeFloat(5, this.zValue);
        }
    }
}
