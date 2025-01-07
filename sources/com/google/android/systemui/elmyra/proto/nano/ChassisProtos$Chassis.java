package com.google.android.systemui.elmyra.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChassisProtos$Chassis extends MessageNano {
    public ElmyraFilters$Filter[] defaultFilters;
    public ChassisProtos$Sensor[] sensors;

    public ChassisProtos$Chassis() {
        if (ChassisProtos$Sensor._emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                try {
                    if (ChassisProtos$Sensor._emptyArray == null) {
                        ChassisProtos$Sensor._emptyArray = new ChassisProtos$Sensor[0];
                    }
                } finally {
                }
            }
        }
        this.sensors = ChassisProtos$Sensor._emptyArray;
        this.defaultFilters = ElmyraFilters$Filter.emptyArray();
        this.cachedSize = -1;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        int i;
        ChassisProtos$Sensor[] chassisProtos$SensorArr = this.sensors;
        int i2 = 0;
        if (chassisProtos$SensorArr != null && chassisProtos$SensorArr.length > 0) {
            int i3 = 0;
            i = 0;
            while (true) {
                ChassisProtos$Sensor[] chassisProtos$SensorArr2 = this.sensors;
                if (i3 >= chassisProtos$SensorArr2.length) {
                    break;
                }
                ChassisProtos$Sensor chassisProtos$Sensor = chassisProtos$SensorArr2[i3];
                if (chassisProtos$Sensor != null) {
                    i += CodedOutputByteBufferNano.computeMessageSize(1, chassisProtos$Sensor);
                }
                i3++;
            }
        } else {
            i = 0;
        }
        ElmyraFilters$Filter[] elmyraFilters$FilterArr = this.defaultFilters;
        if (elmyraFilters$FilterArr != null && elmyraFilters$FilterArr.length > 0) {
            while (true) {
                ElmyraFilters$Filter[] elmyraFilters$FilterArr2 = this.defaultFilters;
                if (i2 >= elmyraFilters$FilterArr2.length) {
                    break;
                }
                ElmyraFilters$Filter elmyraFilters$Filter = elmyraFilters$FilterArr2[i2];
                if (elmyraFilters$Filter != null) {
                    i = CodedOutputByteBufferNano.computeMessageSize(2, elmyraFilters$Filter) + i;
                }
                i2++;
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
            if (readTag == 10) {
                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                ChassisProtos$Sensor[] chassisProtos$SensorArr = this.sensors;
                int length = chassisProtos$SensorArr == null ? 0 : chassisProtos$SensorArr.length;
                int i = repeatedFieldArrayLength + length;
                ChassisProtos$Sensor[] chassisProtos$SensorArr2 = new ChassisProtos$Sensor[i];
                if (length != 0) {
                    System.arraycopy(chassisProtos$SensorArr, 0, chassisProtos$SensorArr2, 0, length);
                }
                while (length < i - 1) {
                    ChassisProtos$Sensor chassisProtos$Sensor = new ChassisProtos$Sensor();
                    chassisProtos$SensorArr2[length] = chassisProtos$Sensor;
                    codedInputByteBufferNano.readMessage(chassisProtos$Sensor);
                    codedInputByteBufferNano.readTag();
                    length++;
                }
                ChassisProtos$Sensor chassisProtos$Sensor2 = new ChassisProtos$Sensor();
                chassisProtos$SensorArr2[length] = chassisProtos$Sensor2;
                codedInputByteBufferNano.readMessage(chassisProtos$Sensor2);
                this.sensors = chassisProtos$SensorArr2;
            } else if (readTag == 18) {
                int repeatedFieldArrayLength2 = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 18);
                ElmyraFilters$Filter[] elmyraFilters$FilterArr = this.defaultFilters;
                int length2 = elmyraFilters$FilterArr == null ? 0 : elmyraFilters$FilterArr.length;
                int i2 = repeatedFieldArrayLength2 + length2;
                ElmyraFilters$Filter[] elmyraFilters$FilterArr2 = new ElmyraFilters$Filter[i2];
                if (length2 != 0) {
                    System.arraycopy(elmyraFilters$FilterArr, 0, elmyraFilters$FilterArr2, 0, length2);
                }
                while (length2 < i2 - 1) {
                    ElmyraFilters$Filter elmyraFilters$Filter = new ElmyraFilters$Filter();
                    elmyraFilters$FilterArr2[length2] = elmyraFilters$Filter;
                    codedInputByteBufferNano.readMessage(elmyraFilters$Filter);
                    codedInputByteBufferNano.readTag();
                    length2++;
                }
                ElmyraFilters$Filter elmyraFilters$Filter2 = new ElmyraFilters$Filter();
                elmyraFilters$FilterArr2[length2] = elmyraFilters$Filter2;
                codedInputByteBufferNano.readMessage(elmyraFilters$Filter2);
                this.defaultFilters = elmyraFilters$FilterArr2;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        ChassisProtos$Sensor[] chassisProtos$SensorArr = this.sensors;
        int i = 0;
        if (chassisProtos$SensorArr != null && chassisProtos$SensorArr.length > 0) {
            int i2 = 0;
            while (true) {
                ChassisProtos$Sensor[] chassisProtos$SensorArr2 = this.sensors;
                if (i2 >= chassisProtos$SensorArr2.length) {
                    break;
                }
                ChassisProtos$Sensor chassisProtos$Sensor = chassisProtos$SensorArr2[i2];
                if (chassisProtos$Sensor != null) {
                    codedOutputByteBufferNano.writeMessage(1, chassisProtos$Sensor);
                }
                i2++;
            }
        }
        ElmyraFilters$Filter[] elmyraFilters$FilterArr = this.defaultFilters;
        if (elmyraFilters$FilterArr == null || elmyraFilters$FilterArr.length <= 0) {
            return;
        }
        while (true) {
            ElmyraFilters$Filter[] elmyraFilters$FilterArr2 = this.defaultFilters;
            if (i >= elmyraFilters$FilterArr2.length) {
                return;
            }
            ElmyraFilters$Filter elmyraFilters$Filter = elmyraFilters$FilterArr2[i];
            if (elmyraFilters$Filter != null) {
                codedOutputByteBufferNano.writeMessage(2, elmyraFilters$Filter);
            }
            i++;
        }
    }
}
