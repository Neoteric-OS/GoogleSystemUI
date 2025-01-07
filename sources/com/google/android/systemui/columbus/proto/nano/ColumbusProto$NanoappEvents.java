package com.google.android.systemui.columbus.proto.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusProto$NanoappEvents extends MessageNano {
    public ColumbusProto$NanoappEvent[] batchedEvents;

    public static ColumbusProto$NanoappEvents parseFrom(byte[] bArr) {
        ColumbusProto$NanoappEvents columbusProto$NanoappEvents = new ColumbusProto$NanoappEvents();
        if (ColumbusProto$NanoappEvent._emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                try {
                    if (ColumbusProto$NanoappEvent._emptyArray == null) {
                        ColumbusProto$NanoappEvent._emptyArray = new ColumbusProto$NanoappEvent[0];
                    }
                } finally {
                }
            }
        }
        columbusProto$NanoappEvents.batchedEvents = ColumbusProto$NanoappEvent._emptyArray;
        columbusProto$NanoappEvents.cachedSize = -1;
        return (ColumbusProto$NanoappEvents) MessageNano.mergeFrom(columbusProto$NanoappEvents, bArr);
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final int computeSerializedSize() {
        ColumbusProto$NanoappEvent[] columbusProto$NanoappEventArr = this.batchedEvents;
        int i = 0;
        if (columbusProto$NanoappEventArr == null || columbusProto$NanoappEventArr.length <= 0) {
            return 0;
        }
        int i2 = 0;
        while (true) {
            ColumbusProto$NanoappEvent[] columbusProto$NanoappEventArr2 = this.batchedEvents;
            if (i >= columbusProto$NanoappEventArr2.length) {
                return i2;
            }
            ColumbusProto$NanoappEvent columbusProto$NanoappEvent = columbusProto$NanoappEventArr2[i];
            if (columbusProto$NanoappEvent != null) {
                i2 = CodedOutputByteBufferNano.computeMessageSize(1, columbusProto$NanoappEvent) + i2;
            }
            i++;
        }
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
                ColumbusProto$NanoappEvent[] columbusProto$NanoappEventArr = this.batchedEvents;
                int length = columbusProto$NanoappEventArr == null ? 0 : columbusProto$NanoappEventArr.length;
                int i = repeatedFieldArrayLength + length;
                ColumbusProto$NanoappEvent[] columbusProto$NanoappEventArr2 = new ColumbusProto$NanoappEvent[i];
                if (length != 0) {
                    System.arraycopy(columbusProto$NanoappEventArr, 0, columbusProto$NanoappEventArr2, 0, length);
                }
                while (length < i - 1) {
                    ColumbusProto$NanoappEvent columbusProto$NanoappEvent = new ColumbusProto$NanoappEvent();
                    columbusProto$NanoappEventArr2[length] = columbusProto$NanoappEvent;
                    codedInputByteBufferNano.readMessage(columbusProto$NanoappEvent);
                    codedInputByteBufferNano.readTag();
                    length++;
                }
                ColumbusProto$NanoappEvent columbusProto$NanoappEvent2 = new ColumbusProto$NanoappEvent();
                columbusProto$NanoappEventArr2[length] = columbusProto$NanoappEvent2;
                codedInputByteBufferNano.readMessage(columbusProto$NanoappEvent2);
                this.batchedEvents = columbusProto$NanoappEventArr2;
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                break;
            }
        }
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public final void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) {
        ColumbusProto$NanoappEvent[] columbusProto$NanoappEventArr = this.batchedEvents;
        if (columbusProto$NanoappEventArr == null || columbusProto$NanoappEventArr.length <= 0) {
            return;
        }
        int i = 0;
        while (true) {
            ColumbusProto$NanoappEvent[] columbusProto$NanoappEventArr2 = this.batchedEvents;
            if (i >= columbusProto$NanoappEventArr2.length) {
                return;
            }
            ColumbusProto$NanoappEvent columbusProto$NanoappEvent = columbusProto$NanoappEventArr2[i];
            if (columbusProto$NanoappEvent != null) {
                codedOutputByteBufferNano.writeMessage(1, columbusProto$NanoappEvent);
            }
            i++;
        }
    }
}
