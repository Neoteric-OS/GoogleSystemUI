package com.google.protobuf.nano;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CodedInputByteBufferNano {
    public final byte[] buffer;
    public int bufferPos;
    public int bufferSize;
    public int bufferSizeAfterLimit;
    public final int bufferStart;
    public int currentLimit = Integer.MAX_VALUE;
    public int lastTag;
    public int recursionDepth;

    public CodedInputByteBufferNano(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.bufferStart = i;
        this.bufferSize = i2 + i;
        this.bufferPos = i;
    }

    public final int pushLimit(int i) {
        if (i < 0) {
            throw new InvalidProtocolBufferNanoException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        int i2 = i + this.bufferPos;
        int i3 = this.currentLimit;
        if (i2 > i3) {
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        this.currentLimit = i2;
        int i4 = this.bufferSize + this.bufferSizeAfterLimit;
        this.bufferSize = i4;
        if (i4 > i2) {
            int i5 = i4 - i2;
            this.bufferSizeAfterLimit = i5;
            this.bufferSize = i4 - i5;
        } else {
            this.bufferSizeAfterLimit = 0;
        }
        return i3;
    }

    public final float readFloat() {
        return Float.intBitsToFloat(readRawLittleEndian32());
    }

    public final void readMessage(MessageNano messageNano) {
        int readRawVarint32 = readRawVarint32();
        if (this.recursionDepth >= 64) {
            throw new InvalidProtocolBufferNanoException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int pushLimit = pushLimit(readRawVarint32);
        this.recursionDepth++;
        messageNano.mergeFrom(this);
        if (this.lastTag != 0) {
            throw new InvalidProtocolBufferNanoException("Protocol message end-group tag did not match expected tag.");
        }
        this.recursionDepth--;
        this.currentLimit = pushLimit;
        int i = this.bufferSize + this.bufferSizeAfterLimit;
        this.bufferSize = i;
        if (i <= pushLimit) {
            this.bufferSizeAfterLimit = 0;
            return;
        }
        int i2 = i - pushLimit;
        this.bufferSizeAfterLimit = i2;
        this.bufferSize = i - i2;
    }

    public final byte readRawByte() {
        int i = this.bufferPos;
        if (i == this.bufferSize) {
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        this.bufferPos = i + 1;
        return this.buffer[i];
    }

    public final int readRawLittleEndian32() {
        byte readRawByte = readRawByte();
        byte readRawByte2 = readRawByte();
        byte readRawByte3 = readRawByte();
        return ((readRawByte() & 255) << 24) | (readRawByte & 255) | ((readRawByte2 & 255) << 8) | ((readRawByte3 & 255) << 16);
    }

    public final long readRawLittleEndian64() {
        return ((readRawByte() & 255) << 8) | (readRawByte() & 255) | ((readRawByte() & 255) << 16) | ((readRawByte() & 255) << 24) | ((readRawByte() & 255) << 32) | ((readRawByte() & 255) << 40) | ((readRawByte() & 255) << 48) | ((readRawByte() & 255) << 56);
    }

    public final int readRawVarint32() {
        int i;
        byte readRawByte = readRawByte();
        if (readRawByte >= 0) {
            return readRawByte;
        }
        int i2 = readRawByte & Byte.MAX_VALUE;
        byte readRawByte2 = readRawByte();
        if (readRawByte2 >= 0) {
            i = readRawByte2 << 7;
        } else {
            i2 |= (readRawByte2 & Byte.MAX_VALUE) << 7;
            byte readRawByte3 = readRawByte();
            if (readRawByte3 >= 0) {
                i = readRawByte3 << 14;
            } else {
                i2 |= (readRawByte3 & Byte.MAX_VALUE) << 14;
                byte readRawByte4 = readRawByte();
                if (readRawByte4 < 0) {
                    int i3 = i2 | ((readRawByte4 & Byte.MAX_VALUE) << 21);
                    byte readRawByte5 = readRawByte();
                    int i4 = i3 | (readRawByte5 << 28);
                    if (readRawByte5 >= 0) {
                        return i4;
                    }
                    for (int i5 = 0; i5 < 5; i5++) {
                        if (readRawByte() >= 0) {
                            return i4;
                        }
                    }
                    throw new InvalidProtocolBufferNanoException("CodedInputStream encountered a malformed varint.");
                }
                i = readRawByte4 << 21;
            }
        }
        return i | i2;
    }

    public final long readRawVarint64() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            j |= (r3 & Byte.MAX_VALUE) << i;
            if ((readRawByte() & 128) == 0) {
                return j;
            }
        }
        throw new InvalidProtocolBufferNanoException("CodedInputStream encountered a malformed varint.");
    }

    public final String readString() {
        int readRawVarint32 = readRawVarint32();
        int i = this.bufferSize;
        int i2 = this.bufferPos;
        int i3 = i - i2;
        byte[] bArr = this.buffer;
        if (readRawVarint32 <= i3 && readRawVarint32 > 0) {
            String str = new String(bArr, i2, readRawVarint32, InternalNano.UTF_8);
            this.bufferPos += readRawVarint32;
            return str;
        }
        if (readRawVarint32 < 0) {
            throw new InvalidProtocolBufferNanoException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        int i4 = i2 + readRawVarint32;
        int i5 = this.currentLimit;
        if (i4 > i5) {
            skipRawBytes(i5 - i2);
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        if (readRawVarint32 > i3) {
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        byte[] bArr2 = new byte[readRawVarint32];
        System.arraycopy(bArr, i2, bArr2, 0, readRawVarint32);
        this.bufferPos += readRawVarint32;
        return new String(bArr2, InternalNano.UTF_8);
    }

    public final int readTag() {
        if (this.bufferPos == this.bufferSize) {
            this.lastTag = 0;
            return 0;
        }
        int readRawVarint32 = readRawVarint32();
        this.lastTag = readRawVarint32;
        if (readRawVarint32 != 0) {
            return readRawVarint32;
        }
        throw new InvalidProtocolBufferNanoException("Protocol message contained an invalid tag (zero).");
    }

    public final boolean skipField(int i) {
        int readTag;
        int i2 = i & 7;
        if (i2 == 0) {
            readRawVarint32();
            return true;
        }
        if (i2 == 1) {
            readRawLittleEndian64();
            return true;
        }
        if (i2 == 2) {
            skipRawBytes(readRawVarint32());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 != 5) {
                throw new InvalidProtocolBufferNanoException("Protocol message tag had invalid wire type.");
            }
            readRawLittleEndian32();
            return true;
        }
        do {
            readTag = readTag();
            if (readTag == 0) {
                break;
            }
        } while (skipField(readTag));
        if (this.lastTag == (((i >>> 3) << 3) | 4)) {
            return true;
        }
        throw new InvalidProtocolBufferNanoException("Protocol message end-group tag did not match expected tag.");
    }

    public final void skipRawBytes(int i) {
        if (i < 0) {
            throw new InvalidProtocolBufferNanoException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        int i2 = this.bufferPos;
        int i3 = i2 + i;
        int i4 = this.currentLimit;
        if (i3 > i4) {
            skipRawBytes(i4 - i2);
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        if (i > this.bufferSize - i2) {
            throw InvalidProtocolBufferNanoException.truncatedMessage();
        }
        this.bufferPos = i3;
    }
}
