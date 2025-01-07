package com.android.wm.shell.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HandlerMapping extends MessageNano {
    private static volatile HandlerMapping[] _emptyArray;
    public int id;
    public String name;

    public HandlerMapping() {
        clear();
    }

    public static HandlerMapping[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                try {
                    if (_emptyArray == null) {
                        _emptyArray = new HandlerMapping[0];
                    }
                } finally {
                }
            }
        }
        return _emptyArray;
    }

    public HandlerMapping clear() {
        this.id = 0;
        this.name = "";
        this.cachedSize = -1;
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public int computeSerializedSize() {
        return CodedOutputByteBufferNano.computeStringSize(2, this.name) + CodedOutputByteBufferNano.computeInt32Size(1, this.id);
    }

    @Override // com.google.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        codedOutputByteBufferNano.writeInt32(1, this.id);
        codedOutputByteBufferNano.writeString(2, this.name);
    }

    @Override // com.google.protobuf.nano.MessageNano
    public HandlerMapping mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                return this;
            }
            if (readTag == 8) {
                this.id = codedInputByteBufferNano.readRawVarint32();
            } else if (readTag == 18) {
                this.name = codedInputByteBufferNano.readString();
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                return this;
            }
        }
    }
}
