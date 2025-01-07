package com.android.systemui.smartspace.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SmartspaceProto$SmartspaceCardDimensionalInfo extends MessageNano {
    public SmartspaceProto$SmartspaceFeatureDimension[] featureDimensions;

    public SmartspaceProto$SmartspaceCardDimensionalInfo() {
        clear();
    }

    public SmartspaceProto$SmartspaceCardDimensionalInfo clear() {
        this.featureDimensions = SmartspaceProto$SmartspaceFeatureDimension.emptyArray();
        this.cachedSize = -1;
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public int computeSerializedSize() {
        SmartspaceProto$SmartspaceFeatureDimension[] smartspaceProto$SmartspaceFeatureDimensionArr = this.featureDimensions;
        int i = 0;
        if (smartspaceProto$SmartspaceFeatureDimensionArr == null || smartspaceProto$SmartspaceFeatureDimensionArr.length <= 0) {
            return 0;
        }
        int i2 = 0;
        while (true) {
            SmartspaceProto$SmartspaceFeatureDimension[] smartspaceProto$SmartspaceFeatureDimensionArr2 = this.featureDimensions;
            if (i >= smartspaceProto$SmartspaceFeatureDimensionArr2.length) {
                return i2;
            }
            SmartspaceProto$SmartspaceFeatureDimension smartspaceProto$SmartspaceFeatureDimension = smartspaceProto$SmartspaceFeatureDimensionArr2[i];
            if (smartspaceProto$SmartspaceFeatureDimension != null) {
                i2 = CodedOutputByteBufferNano.computeMessageSize(1, smartspaceProto$SmartspaceFeatureDimension) + i2;
            }
            i++;
        }
    }

    @Override // com.google.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        SmartspaceProto$SmartspaceFeatureDimension[] smartspaceProto$SmartspaceFeatureDimensionArr = this.featureDimensions;
        if (smartspaceProto$SmartspaceFeatureDimensionArr == null || smartspaceProto$SmartspaceFeatureDimensionArr.length <= 0) {
            return;
        }
        int i = 0;
        while (true) {
            SmartspaceProto$SmartspaceFeatureDimension[] smartspaceProto$SmartspaceFeatureDimensionArr2 = this.featureDimensions;
            if (i >= smartspaceProto$SmartspaceFeatureDimensionArr2.length) {
                return;
            }
            SmartspaceProto$SmartspaceFeatureDimension smartspaceProto$SmartspaceFeatureDimension = smartspaceProto$SmartspaceFeatureDimensionArr2[i];
            if (smartspaceProto$SmartspaceFeatureDimension != null) {
                codedOutputByteBufferNano.writeMessage(1, smartspaceProto$SmartspaceFeatureDimension);
            }
            i++;
        }
    }

    @Override // com.google.protobuf.nano.MessageNano
    public SmartspaceProto$SmartspaceCardDimensionalInfo mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                return this;
            }
            if (readTag == 10) {
                int repeatedFieldArrayLength = WireFormatNano.getRepeatedFieldArrayLength(codedInputByteBufferNano, 10);
                SmartspaceProto$SmartspaceFeatureDimension[] smartspaceProto$SmartspaceFeatureDimensionArr = this.featureDimensions;
                int length = smartspaceProto$SmartspaceFeatureDimensionArr == null ? 0 : smartspaceProto$SmartspaceFeatureDimensionArr.length;
                int i = repeatedFieldArrayLength + length;
                SmartspaceProto$SmartspaceFeatureDimension[] smartspaceProto$SmartspaceFeatureDimensionArr2 = new SmartspaceProto$SmartspaceFeatureDimension[i];
                if (length != 0) {
                    System.arraycopy(smartspaceProto$SmartspaceFeatureDimensionArr, 0, smartspaceProto$SmartspaceFeatureDimensionArr2, 0, length);
                }
                while (length < i - 1) {
                    SmartspaceProto$SmartspaceFeatureDimension smartspaceProto$SmartspaceFeatureDimension = new SmartspaceProto$SmartspaceFeatureDimension();
                    smartspaceProto$SmartspaceFeatureDimensionArr2[length] = smartspaceProto$SmartspaceFeatureDimension;
                    codedInputByteBufferNano.readMessage(smartspaceProto$SmartspaceFeatureDimension);
                    codedInputByteBufferNano.readTag();
                    length++;
                }
                SmartspaceProto$SmartspaceFeatureDimension smartspaceProto$SmartspaceFeatureDimension2 = new SmartspaceProto$SmartspaceFeatureDimension();
                smartspaceProto$SmartspaceFeatureDimensionArr2[length] = smartspaceProto$SmartspaceFeatureDimension2;
                codedInputByteBufferNano.readMessage(smartspaceProto$SmartspaceFeatureDimension2);
                this.featureDimensions = smartspaceProto$SmartspaceFeatureDimensionArr2;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                return this;
            }
        }
    }
}
