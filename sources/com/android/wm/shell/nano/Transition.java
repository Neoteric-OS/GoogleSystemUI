package com.android.wm.shell.nano;

import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import java.io.IOException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Transition extends MessageNano {
    private static volatile Transition[] _emptyArray;
    public long abortTimeNs;
    public long dispatchTimeNs;
    public int handler;
    public int id;
    public long mergeRequestTimeNs;
    public int mergeTarget;
    public long mergeTimeNs;

    public Transition() {
        clear();
    }

    public static Transition[] emptyArray() {
        if (_emptyArray == null) {
            synchronized (InternalNano.LAZY_INIT_LOCK) {
                try {
                    if (_emptyArray == null) {
                        _emptyArray = new Transition[0];
                    }
                } finally {
                }
            }
        }
        return _emptyArray;
    }

    public Transition clear() {
        this.id = 0;
        this.dispatchTimeNs = 0L;
        this.handler = 0;
        this.mergeTimeNs = 0L;
        this.mergeRequestTimeNs = 0L;
        this.mergeTarget = 0;
        this.abortTimeNs = 0L;
        this.cachedSize = -1;
        return this;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public int computeSerializedSize() {
        int computeInt32Size = CodedOutputByteBufferNano.computeInt32Size(1, this.id);
        long j = this.dispatchTimeNs;
        if (j != 0) {
            computeInt32Size += CodedOutputByteBufferNano.computeInt64Size(j, 2);
        }
        int i = this.handler;
        if (i != 0) {
            computeInt32Size += CodedOutputByteBufferNano.computeInt32Size(3, i);
        }
        long j2 = this.mergeTimeNs;
        if (j2 != 0) {
            computeInt32Size += CodedOutputByteBufferNano.computeInt64Size(j2, 4);
        }
        long j3 = this.mergeRequestTimeNs;
        if (j3 != 0) {
            computeInt32Size += CodedOutputByteBufferNano.computeInt64Size(j3, 5);
        }
        int i2 = this.mergeTarget;
        if (i2 != 0) {
            computeInt32Size += CodedOutputByteBufferNano.computeInt32Size(6, i2);
        }
        long j4 = this.abortTimeNs;
        return j4 != 0 ? computeInt32Size + CodedOutputByteBufferNano.computeInt64Size(j4, 7) : computeInt32Size;
    }

    @Override // com.google.protobuf.nano.MessageNano
    public void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano) throws IOException {
        codedOutputByteBufferNano.writeInt32(1, this.id);
        long j = this.dispatchTimeNs;
        if (j != 0) {
            codedOutputByteBufferNano.writeInt64(j, 2);
        }
        int i = this.handler;
        if (i != 0) {
            codedOutputByteBufferNano.writeInt32(3, i);
        }
        long j2 = this.mergeTimeNs;
        if (j2 != 0) {
            codedOutputByteBufferNano.writeInt64(j2, 4);
        }
        long j3 = this.mergeRequestTimeNs;
        if (j3 != 0) {
            codedOutputByteBufferNano.writeInt64(j3, 5);
        }
        int i2 = this.mergeTarget;
        if (i2 != 0) {
            codedOutputByteBufferNano.writeInt32(6, i2);
        }
        long j4 = this.abortTimeNs;
        if (j4 != 0) {
            codedOutputByteBufferNano.writeInt64(j4, 7);
        }
    }

    @Override // com.google.protobuf.nano.MessageNano
    public Transition mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano) throws IOException {
        while (true) {
            int readTag = codedInputByteBufferNano.readTag();
            if (readTag == 0) {
                return this;
            }
            if (readTag == 8) {
                this.id = codedInputByteBufferNano.readRawVarint32();
            } else if (readTag == 16) {
                this.dispatchTimeNs = codedInputByteBufferNano.readRawVarint64();
            } else if (readTag == 24) {
                this.handler = codedInputByteBufferNano.readRawVarint32();
            } else if (readTag == 32) {
                this.mergeTimeNs = codedInputByteBufferNano.readRawVarint64();
            } else if (readTag == 40) {
                this.mergeRequestTimeNs = codedInputByteBufferNano.readRawVarint64();
            } else if (readTag == 48) {
                this.mergeTarget = codedInputByteBufferNano.readRawVarint32();
            } else if (readTag == 56) {
                this.abortTimeNs = codedInputByteBufferNano.readRawVarint64();
            } else if (!codedInputByteBufferNano.skipField(readTag)) {
                return this;
            }
        }
    }
}
