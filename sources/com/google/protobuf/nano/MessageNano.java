package com.google.protobuf.nano;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class MessageNano {
    protected volatile int cachedSize = -1;

    public static final MessageNano mergeFrom(MessageNano messageNano, byte[] bArr, int i, int i2) throws InvalidProtocolBufferNanoException {
        try {
            CodedInputByteBufferNano codedInputByteBufferNano = new CodedInputByteBufferNano(bArr, i, i2);
            messageNano.mergeFrom(codedInputByteBufferNano);
            if (codedInputByteBufferNano.lastTag == 0) {
                return messageNano;
            }
            throw new InvalidProtocolBufferNanoException("Protocol message end-group tag did not match expected tag.");
        } catch (InvalidProtocolBufferNanoException e) {
            throw e;
        } catch (IOException unused) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    public static final void toByteArray(MessageNano messageNano, byte[] bArr, int i, int i2) {
        try {
            CodedOutputByteBufferNano codedOutputByteBufferNano = new CodedOutputByteBufferNano(bArr, i, i2);
            messageNano.writeTo(codedOutputByteBufferNano);
            if (codedOutputByteBufferNano.buffer.remaining() == 0) {
            } else {
                throw new IllegalStateException("Did not write as much data as expected.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public abstract int computeSerializedSize();

    public int getCachedSize() {
        if (this.cachedSize < 0) {
            getSerializedSize();
        }
        return this.cachedSize;
    }

    public int getSerializedSize() {
        int computeSerializedSize = computeSerializedSize();
        this.cachedSize = computeSerializedSize;
        return computeSerializedSize;
    }

    public abstract MessageNano mergeFrom(CodedInputByteBufferNano codedInputByteBufferNano);

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            MessageNanoPrinter.print(null, this, new StringBuffer(), stringBuffer);
            return stringBuffer.toString();
        } catch (IllegalAccessException e) {
            return "Error printing proto: " + e.getMessage();
        } catch (InvocationTargetException e2) {
            return "Error printing proto: " + e2.getMessage();
        }
    }

    public abstract void writeTo(CodedOutputByteBufferNano codedOutputByteBufferNano);

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public MessageNano m1770clone() throws CloneNotSupportedException {
        return (MessageNano) super.clone();
    }

    public static final byte[] toByteArray(MessageNano messageNano) {
        int serializedSize = messageNano.getSerializedSize();
        byte[] bArr = new byte[serializedSize];
        toByteArray(messageNano, bArr, 0, serializedSize);
        return bArr;
    }

    public static final MessageNano mergeFrom(MessageNano messageNano, byte[] bArr) throws InvalidProtocolBufferNanoException {
        return mergeFrom(messageNano, bArr, 0, bArr.length);
    }
}
