package com.android.systemui.smartspace.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SmartspaceProto$SmartspaceFeatureDimension extends MessageNano {
    private static volatile SmartspaceProto$SmartspaceFeatureDimension[] _emptyArray;
    public int featureDimensionId;
    public int featureDimensionValue;

    public SmartspaceProto$SmartspaceFeatureDimension() {
        clear();
    }

    public static SmartspaceProto$SmartspaceFeatureDimension[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                try {
                    if (_emptyArray == null) {
                        _emptyArray = new SmartspaceProto$SmartspaceFeatureDimension[0];
                    }
                } finally {
                }
            }
        }
        return _emptyArray;
    }

    public SmartspaceProto$SmartspaceFeatureDimension clear() {
        this.featureDimensionId = 0;
        this.featureDimensionValue = 0;
        this.cachedSize = -1;
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public int computeSerializedSize() {
        int i = this.featureDimensionId;
        int computeInt32Size = i != 0 ? CodedOutputByteBufferNano.computeInt32Size(1, i) : 0;
        int i2 = this.featureDimensionValue;
        return i2 != 0 ? computeInt32Size + CodedOutputByteBufferNano.computeInt32Size(2, i2) : computeInt32Size;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        int i = this.featureDimensionId;
        if (i != 0) {
            codedOutputByteBufferNano.writeInt32(1, i);
        }
        int i2 = this.featureDimensionValue;
        if (i2 != 0) {
            codedOutputByteBufferNano.writeInt32(2, i2);
        }
    }

    @Override // com.google.protobuf.nano.MessageNano
    public SmartspaceProto$SmartspaceFeatureDimension mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                return this;
            }
            if (readTag == 8) {
                this.featureDimensionId = codedInputByteBufferNano.readRawVarint32();
            } else if (readTag == 16) {
                this.featureDimensionValue = codedInputByteBufferNano.readRawVarint32();
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                return this;
            }
        }
    }
}
