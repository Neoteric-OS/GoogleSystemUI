package com.google.android.systemui.autorotate.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AutorotateProto$DeviceRotatedSensorData extends MessageNano {
    public AutorotateProto$DeviceRotatedSensorHeader header = null;
    public AutorotateProto$DeviceRotatedSensorSample[] sample;

    public AutorotateProto$DeviceRotatedSensorData() {
        if (AutorotateProto$DeviceRotatedSensorSample._emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                try {
                    if (AutorotateProto$DeviceRotatedSensorSample._emptyArray == null) {
                        AutorotateProto$DeviceRotatedSensorSample._emptyArray = new AutorotateProto$DeviceRotatedSensorSample[0];
                    }
                } finally {
                }
            }
        }
        this.sample = AutorotateProto$DeviceRotatedSensorSample._emptyArray;
        this.cachedSize = -1;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        AutorotateProto$DeviceRotatedSensorHeader autorotateProto$DeviceRotatedSensorHeader = this.header;
        int i = 0;
        int computeMessageSize = autorotateProto$DeviceRotatedSensorHeader != null ? CodedOutputByteBufferNano.computeMessageSize(1, autorotateProto$DeviceRotatedSensorHeader) : 0;
        AutorotateProto$DeviceRotatedSensorSample[] autorotateProto$DeviceRotatedSensorSampleArr = this.sample;
        if (autorotateProto$DeviceRotatedSensorSampleArr != null && autorotateProto$DeviceRotatedSensorSampleArr.length > 0) {
            while (true) {
                AutorotateProto$DeviceRotatedSensorSample[] autorotateProto$DeviceRotatedSensorSampleArr2 = this.sample;
                if (i >= autorotateProto$DeviceRotatedSensorSampleArr2.length) {
                    break;
                }
                AutorotateProto$DeviceRotatedSensorSample autorotateProto$DeviceRotatedSensorSample = autorotateProto$DeviceRotatedSensorSampleArr2[i];
                if (autorotateProto$DeviceRotatedSensorSample != null) {
                    computeMessageSize = CodedOutputByteBufferNano.computeMessageSize(2, autorotateProto$DeviceRotatedSensorSample) + computeMessageSize;
                }
                i++;
            }
        }
        return computeMessageSize;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                break;
            }
            if (readTag == 10) {
                if (this.header == null) {
                    this.header = new AutorotateProto$DeviceRotatedSensorHeader();
                }
                codedInputByteBufferNano.readMessage(this.header);
            } else if (readTag == 18) {
                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 18);
                AutorotateProto$DeviceRotatedSensorSample[] autorotateProto$DeviceRotatedSensorSampleArr = this.sample;
                int length = autorotateProto$DeviceRotatedSensorSampleArr == null ? 0 : autorotateProto$DeviceRotatedSensorSampleArr.length;
                int i = repeatedFieldArrayLength + length;
                AutorotateProto$DeviceRotatedSensorSample[] autorotateProto$DeviceRotatedSensorSampleArr2 = new AutorotateProto$DeviceRotatedSensorSample[i];
                if (length != 0) {
                    System.arraycopy(autorotateProto$DeviceRotatedSensorSampleArr, 0, autorotateProto$DeviceRotatedSensorSampleArr2, 0, length);
                }
                while (length < i - 1) {
                    AutorotateProto$DeviceRotatedSensorSample autorotateProto$DeviceRotatedSensorSample = new AutorotateProto$DeviceRotatedSensorSample();
                    autorotateProto$DeviceRotatedSensorSampleArr2[length] = autorotateProto$DeviceRotatedSensorSample;
                    codedInputByteBufferNano.readMessage(autorotateProto$DeviceRotatedSensorSample);
                    codedInputByteBufferNano.readTag();
                    length++;
                }
                AutorotateProto$DeviceRotatedSensorSample autorotateProto$DeviceRotatedSensorSample2 = new AutorotateProto$DeviceRotatedSensorSample();
                autorotateProto$DeviceRotatedSensorSampleArr2[length] = autorotateProto$DeviceRotatedSensorSample2;
                codedInputByteBufferNano.readMessage(autorotateProto$DeviceRotatedSensorSample2);
                this.sample = autorotateProto$DeviceRotatedSensorSampleArr2;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        AutorotateProto$DeviceRotatedSensorHeader autorotateProto$DeviceRotatedSensorHeader = this.header;
        if (autorotateProto$DeviceRotatedSensorHeader != null) {
            codedOutputByteBufferNano.writeMessage(1, autorotateProto$DeviceRotatedSensorHeader);
        }
        AutorotateProto$DeviceRotatedSensorSample[] autorotateProto$DeviceRotatedSensorSampleArr = this.sample;
        if (autorotateProto$DeviceRotatedSensorSampleArr == null || autorotateProto$DeviceRotatedSensorSampleArr.length <= 0) {
            return;
        }
        int i = 0;
        while (true) {
            AutorotateProto$DeviceRotatedSensorSample[] autorotateProto$DeviceRotatedSensorSampleArr2 = this.sample;
            if (i >= autorotateProto$DeviceRotatedSensorSampleArr2.length) {
                return;
            }
            AutorotateProto$DeviceRotatedSensorSample autorotateProto$DeviceRotatedSensorSample = autorotateProto$DeviceRotatedSensorSampleArr2[i];
            if (autorotateProto$DeviceRotatedSensorSample != null) {
                codedOutputByteBufferNano.writeMessage(2, autorotateProto$DeviceRotatedSensorSample);
            }
            i++;
        }
    }
}
