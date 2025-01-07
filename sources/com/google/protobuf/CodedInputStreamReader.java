package com.google.protobuf;

import com.android.app.viewcapture.data.ViewNode;
import com.google.protobuf.ByteString;
import java.nio.charset.Charset;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CodedInputStreamReader {
    public int endGroupTag;
    public final CodedInputStream$StreamDecoder input;
    public int nextTag = 0;
    public int tag;

    public CodedInputStreamReader(CodedInputStream$StreamDecoder codedInputStream$StreamDecoder) {
        Charset charset = Internal.UTF_8;
        this.input = codedInputStream$StreamDecoder;
        codedInputStream$StreamDecoder.wrapper = this;
    }

    public static void verifyPackedFixed32Length(int i) {
        if ((i & 3) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    public static void verifyPackedFixed64Length(int i) {
        if ((i & 7) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    public final int getFieldNumber() {
        int i = this.nextTag;
        if (i != 0) {
            this.tag = i;
            this.nextTag = 0;
        } else {
            this.tag = this.input.readTag();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.endGroupTag) {
            return Integer.MAX_VALUE;
        }
        return i2 >>> 3;
    }

    public final void mergeGroupFieldInternal(Object obj, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        int i = this.endGroupTag;
        this.endGroupTag = ((this.tag >>> 3) << 3) | 4;
        try {
            schema.mergeFrom(obj, this, extensionRegistryLite);
            if (this.tag == this.endGroupTag) {
            } else {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } finally {
            this.endGroupTag = i;
        }
    }

    public final void mergeMessageFieldInternal(Object obj, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        int readUInt32 = codedInputStream$StreamDecoder.readUInt32();
        if (codedInputStream$StreamDecoder.recursionDepth >= 100) {
            throw new InvalidProtocolBufferException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int pushLimit = codedInputStream$StreamDecoder.pushLimit(readUInt32);
        codedInputStream$StreamDecoder.recursionDepth++;
        schema.mergeFrom(obj, this, extensionRegistryLite);
        codedInputStream$StreamDecoder.checkLastTagWas(0);
        codedInputStream$StreamDecoder.recursionDepth--;
        codedInputStream$StreamDecoder.popLimit(pushLimit);
    }

    public final void readBoolList(List list) {
        int readTag;
        int readTag2;
        boolean z = list instanceof BooleanArrayList;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (!z) {
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    list.add(Boolean.valueOf(codedInputStream$StreamDecoder.readBool()));
                    if (codedInputStream$StreamDecoder.isAtEnd()) {
                        return;
                    } else {
                        readTag = codedInputStream$StreamDecoder.readTag();
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int totalBytesRead = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
            do {
                list.add(Boolean.valueOf(codedInputStream$StreamDecoder.readBool()));
            } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead);
            requirePosition(totalBytesRead);
            return;
        }
        BooleanArrayList booleanArrayList = (BooleanArrayList) list;
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                booleanArrayList.addBoolean(codedInputStream$StreamDecoder.readBool());
                if (codedInputStream$StreamDecoder.isAtEnd()) {
                    return;
                } else {
                    readTag2 = codedInputStream$StreamDecoder.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead2 = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
        do {
            booleanArrayList.addBoolean(codedInputStream$StreamDecoder.readBool());
        } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead2);
        requirePosition(totalBytesRead2);
    }

    public final ByteString readBytes() {
        ByteString literalByteString;
        requireWireType(2);
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        int readRawVarint32 = codedInputStream$StreamDecoder.readRawVarint32();
        int i = codedInputStream$StreamDecoder.bufferSize;
        int i2 = codedInputStream$StreamDecoder.pos;
        int i3 = i - i2;
        byte[] bArr = codedInputStream$StreamDecoder.buffer;
        if (readRawVarint32 <= i3 && readRawVarint32 > 0) {
            ByteString copyFrom = ByteString.copyFrom(bArr, i2, readRawVarint32);
            codedInputStream$StreamDecoder.pos += readRawVarint32;
            return copyFrom;
        }
        if (readRawVarint32 == 0) {
            return ByteString.EMPTY;
        }
        byte[] readRawBytesSlowPathOneChunk = codedInputStream$StreamDecoder.readRawBytesSlowPathOneChunk(readRawVarint32);
        if (readRawBytesSlowPathOneChunk != null) {
            literalByteString = ByteString.copyFrom(readRawBytesSlowPathOneChunk, 0, readRawBytesSlowPathOneChunk.length);
        } else {
            int i4 = codedInputStream$StreamDecoder.pos;
            int i5 = codedInputStream$StreamDecoder.bufferSize;
            int i6 = i5 - i4;
            codedInputStream$StreamDecoder.totalBytesRetired += i5;
            codedInputStream$StreamDecoder.pos = 0;
            codedInputStream$StreamDecoder.bufferSize = 0;
            List<byte[]> readRawBytesSlowPathRemainingChunks = codedInputStream$StreamDecoder.readRawBytesSlowPathRemainingChunks(readRawVarint32 - i6);
            byte[] bArr2 = new byte[readRawVarint32];
            System.arraycopy(bArr, i4, bArr2, 0, i6);
            for (byte[] bArr3 : readRawBytesSlowPathRemainingChunks) {
                System.arraycopy(bArr3, 0, bArr2, i6, bArr3.length);
                i6 += bArr3.length;
            }
            ByteString byteString = ByteString.EMPTY;
            literalByteString = new ByteString.LiteralByteString(bArr2);
        }
        return literalByteString;
    }

    public final void readBytesList(List list) {
        int readTag;
        if ((this.tag & 7) != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            list.add(readBytes());
            CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
            if (codedInputStream$StreamDecoder.isAtEnd()) {
                return;
            } else {
                readTag = codedInputStream$StreamDecoder.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    public final void readDoubleList(List list) {
        int readTag;
        int readTag2;
        boolean z = list instanceof DoubleArrayList;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (!z) {
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    list.add(Double.valueOf(codedInputStream$StreamDecoder.readDouble()));
                    if (codedInputStream$StreamDecoder.isAtEnd()) {
                        return;
                    } else {
                        readTag = codedInputStream$StreamDecoder.readTag();
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int readUInt32 = codedInputStream$StreamDecoder.readUInt32();
            verifyPackedFixed64Length(readUInt32);
            int totalBytesRead = codedInputStream$StreamDecoder.getTotalBytesRead() + readUInt32;
            do {
                list.add(Double.valueOf(codedInputStream$StreamDecoder.readDouble()));
            } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead);
            return;
        }
        DoubleArrayList doubleArrayList = (DoubleArrayList) list;
        int i2 = this.tag & 7;
        if (i2 == 1) {
            do {
                doubleArrayList.addDouble(codedInputStream$StreamDecoder.readDouble());
                if (codedInputStream$StreamDecoder.isAtEnd()) {
                    return;
                } else {
                    readTag2 = codedInputStream$StreamDecoder.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int readUInt322 = codedInputStream$StreamDecoder.readUInt32();
        verifyPackedFixed64Length(readUInt322);
        int totalBytesRead2 = codedInputStream$StreamDecoder.getTotalBytesRead() + readUInt322;
        do {
            doubleArrayList.addDouble(codedInputStream$StreamDecoder.readDouble());
        } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead2);
    }

    public final void readEnumList(List list) {
        int readTag;
        int readTag2;
        boolean z = list instanceof IntArrayList;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (!z) {
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(codedInputStream$StreamDecoder.readEnum()));
                    if (codedInputStream$StreamDecoder.isAtEnd()) {
                        return;
                    } else {
                        readTag = codedInputStream$StreamDecoder.readTag();
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int totalBytesRead = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
            do {
                list.add(Integer.valueOf(codedInputStream$StreamDecoder.readEnum()));
            } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead);
            requirePosition(totalBytesRead);
            return;
        }
        IntArrayList intArrayList = (IntArrayList) list;
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                intArrayList.addInt(codedInputStream$StreamDecoder.readEnum());
                if (codedInputStream$StreamDecoder.isAtEnd()) {
                    return;
                } else {
                    readTag2 = codedInputStream$StreamDecoder.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead2 = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
        do {
            intArrayList.addInt(codedInputStream$StreamDecoder.readEnum());
        } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead2);
        requirePosition(totalBytesRead2);
    }

    public final Object readField(WireFormat$FieldType wireFormat$FieldType, Class cls, ExtensionRegistryLite extensionRegistryLite) {
        int ordinal = wireFormat$FieldType.ordinal();
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        switch (ordinal) {
            case 0:
                requireWireType(1);
                return Double.valueOf(codedInputStream$StreamDecoder.readDouble());
            case 1:
                requireWireType(5);
                return Float.valueOf(codedInputStream$StreamDecoder.readFloat());
            case 2:
                requireWireType(0);
                return Long.valueOf(codedInputStream$StreamDecoder.readInt64());
            case 3:
                requireWireType(0);
                return Long.valueOf(codedInputStream$StreamDecoder.readUInt64());
            case 4:
                requireWireType(0);
                return Integer.valueOf(codedInputStream$StreamDecoder.readInt32());
            case 5:
                requireWireType(1);
                return Long.valueOf(codedInputStream$StreamDecoder.readFixed64());
            case 6:
                requireWireType(5);
                return Integer.valueOf(codedInputStream$StreamDecoder.readFixed32());
            case 7:
                requireWireType(0);
                return Boolean.valueOf(codedInputStream$StreamDecoder.readBool());
            case 8:
                requireWireType(2);
                return codedInputStream$StreamDecoder.readStringRequireUtf8();
            case 9:
            default:
                throw new IllegalArgumentException("unsupported field type.");
            case 10:
                requireWireType(2);
                Schema schemaFor = Protobuf.INSTANCE.schemaFor(cls);
                GeneratedMessageLite newInstance = schemaFor.newInstance();
                mergeMessageFieldInternal(newInstance, schemaFor, extensionRegistryLite);
                schemaFor.makeImmutable(newInstance);
                return newInstance;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                return readBytes();
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                requireWireType(0);
                return Integer.valueOf(codedInputStream$StreamDecoder.readUInt32());
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                requireWireType(0);
                return Integer.valueOf(codedInputStream$StreamDecoder.readEnum());
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                requireWireType(5);
                return Integer.valueOf(codedInputStream$StreamDecoder.readSFixed32());
            case 15:
                requireWireType(1);
                return Long.valueOf(codedInputStream$StreamDecoder.readSFixed64());
            case 16:
                requireWireType(0);
                return Integer.valueOf(codedInputStream$StreamDecoder.readSInt32());
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                requireWireType(0);
                return Long.valueOf(codedInputStream$StreamDecoder.readSInt64());
        }
    }

    public final void readFixed32List(List list) {
        int readTag;
        int readTag2;
        boolean z = list instanceof IntArrayList;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (!z) {
            int i = this.tag & 7;
            if (i == 2) {
                int readUInt32 = codedInputStream$StreamDecoder.readUInt32();
                verifyPackedFixed32Length(readUInt32);
                int totalBytesRead = codedInputStream$StreamDecoder.getTotalBytesRead() + readUInt32;
                do {
                    list.add(Integer.valueOf(codedInputStream$StreamDecoder.readFixed32()));
                } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead);
                return;
            }
            if (i != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                list.add(Integer.valueOf(codedInputStream$StreamDecoder.readFixed32()));
                if (codedInputStream$StreamDecoder.isAtEnd()) {
                    return;
                } else {
                    readTag = codedInputStream$StreamDecoder.readTag();
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
            return;
        }
        IntArrayList intArrayList = (IntArrayList) list;
        int i2 = this.tag & 7;
        if (i2 == 2) {
            int readUInt322 = codedInputStream$StreamDecoder.readUInt32();
            verifyPackedFixed32Length(readUInt322);
            int totalBytesRead2 = codedInputStream$StreamDecoder.getTotalBytesRead() + readUInt322;
            do {
                intArrayList.addInt(codedInputStream$StreamDecoder.readFixed32());
            } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead2);
            return;
        }
        if (i2 != 5) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            intArrayList.addInt(codedInputStream$StreamDecoder.readFixed32());
            if (codedInputStream$StreamDecoder.isAtEnd()) {
                return;
            } else {
                readTag2 = codedInputStream$StreamDecoder.readTag();
            }
        } while (readTag2 == this.tag);
        this.nextTag = readTag2;
    }

    public final void readFixed64List(List list) {
        int readTag;
        int readTag2;
        boolean z = list instanceof LongArrayList;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (!z) {
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    list.add(Long.valueOf(codedInputStream$StreamDecoder.readFixed64()));
                    if (codedInputStream$StreamDecoder.isAtEnd()) {
                        return;
                    } else {
                        readTag = codedInputStream$StreamDecoder.readTag();
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int readUInt32 = codedInputStream$StreamDecoder.readUInt32();
            verifyPackedFixed64Length(readUInt32);
            int totalBytesRead = codedInputStream$StreamDecoder.getTotalBytesRead() + readUInt32;
            do {
                list.add(Long.valueOf(codedInputStream$StreamDecoder.readFixed64()));
            } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead);
            return;
        }
        LongArrayList longArrayList = (LongArrayList) list;
        int i2 = this.tag & 7;
        if (i2 == 1) {
            do {
                longArrayList.addLong(codedInputStream$StreamDecoder.readFixed64());
                if (codedInputStream$StreamDecoder.isAtEnd()) {
                    return;
                } else {
                    readTag2 = codedInputStream$StreamDecoder.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int readUInt322 = codedInputStream$StreamDecoder.readUInt32();
        verifyPackedFixed64Length(readUInt322);
        int totalBytesRead2 = codedInputStream$StreamDecoder.getTotalBytesRead() + readUInt322;
        do {
            longArrayList.addLong(codedInputStream$StreamDecoder.readFixed64());
        } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead2);
    }

    public final void readFloatList(List list) {
        int readTag;
        int readTag2;
        boolean z = list instanceof FloatArrayList;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (!z) {
            int i = this.tag & 7;
            if (i == 2) {
                int readUInt32 = codedInputStream$StreamDecoder.readUInt32();
                verifyPackedFixed32Length(readUInt32);
                int totalBytesRead = codedInputStream$StreamDecoder.getTotalBytesRead() + readUInt32;
                do {
                    list.add(Float.valueOf(codedInputStream$StreamDecoder.readFloat()));
                } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead);
                return;
            }
            if (i != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                list.add(Float.valueOf(codedInputStream$StreamDecoder.readFloat()));
                if (codedInputStream$StreamDecoder.isAtEnd()) {
                    return;
                } else {
                    readTag = codedInputStream$StreamDecoder.readTag();
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
            return;
        }
        FloatArrayList floatArrayList = (FloatArrayList) list;
        int i2 = this.tag & 7;
        if (i2 == 2) {
            int readUInt322 = codedInputStream$StreamDecoder.readUInt32();
            verifyPackedFixed32Length(readUInt322);
            int totalBytesRead2 = codedInputStream$StreamDecoder.getTotalBytesRead() + readUInt322;
            do {
                floatArrayList.addFloat(codedInputStream$StreamDecoder.readFloat());
            } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead2);
            return;
        }
        if (i2 != 5) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            floatArrayList.addFloat(codedInputStream$StreamDecoder.readFloat());
            if (codedInputStream$StreamDecoder.isAtEnd()) {
                return;
            } else {
                readTag2 = codedInputStream$StreamDecoder.readTag();
            }
        } while (readTag2 == this.tag);
        this.nextTag = readTag2;
    }

    public final void readInt32List(List list) {
        int readTag;
        int readTag2;
        boolean z = list instanceof IntArrayList;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (!z) {
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(codedInputStream$StreamDecoder.readInt32()));
                    if (codedInputStream$StreamDecoder.isAtEnd()) {
                        return;
                    } else {
                        readTag = codedInputStream$StreamDecoder.readTag();
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int totalBytesRead = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
            do {
                list.add(Integer.valueOf(codedInputStream$StreamDecoder.readInt32()));
            } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead);
            requirePosition(totalBytesRead);
            return;
        }
        IntArrayList intArrayList = (IntArrayList) list;
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                intArrayList.addInt(codedInputStream$StreamDecoder.readInt32());
                if (codedInputStream$StreamDecoder.isAtEnd()) {
                    return;
                } else {
                    readTag2 = codedInputStream$StreamDecoder.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead2 = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
        do {
            intArrayList.addInt(codedInputStream$StreamDecoder.readInt32());
        } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead2);
        requirePosition(totalBytesRead2);
    }

    public final void readInt64List(List list) {
        int readTag;
        int readTag2;
        boolean z = list instanceof LongArrayList;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (!z) {
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    list.add(Long.valueOf(codedInputStream$StreamDecoder.readInt64()));
                    if (codedInputStream$StreamDecoder.isAtEnd()) {
                        return;
                    } else {
                        readTag = codedInputStream$StreamDecoder.readTag();
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int totalBytesRead = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
            do {
                list.add(Long.valueOf(codedInputStream$StreamDecoder.readInt64()));
            } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead);
            requirePosition(totalBytesRead);
            return;
        }
        LongArrayList longArrayList = (LongArrayList) list;
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                longArrayList.addLong(codedInputStream$StreamDecoder.readInt64());
                if (codedInputStream$StreamDecoder.isAtEnd()) {
                    return;
                } else {
                    readTag2 = codedInputStream$StreamDecoder.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead2 = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
        do {
            longArrayList.addLong(codedInputStream$StreamDecoder.readInt64());
        } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead2);
        requirePosition(totalBytesRead2);
    }

    public final void readSFixed32List(List list) {
        int readTag;
        int readTag2;
        boolean z = list instanceof IntArrayList;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (!z) {
            int i = this.tag & 7;
            if (i == 2) {
                int readUInt32 = codedInputStream$StreamDecoder.readUInt32();
                verifyPackedFixed32Length(readUInt32);
                int totalBytesRead = codedInputStream$StreamDecoder.getTotalBytesRead() + readUInt32;
                do {
                    list.add(Integer.valueOf(codedInputStream$StreamDecoder.readSFixed32()));
                } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead);
                return;
            }
            if (i != 5) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                list.add(Integer.valueOf(codedInputStream$StreamDecoder.readSFixed32()));
                if (codedInputStream$StreamDecoder.isAtEnd()) {
                    return;
                } else {
                    readTag = codedInputStream$StreamDecoder.readTag();
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
            return;
        }
        IntArrayList intArrayList = (IntArrayList) list;
        int i2 = this.tag & 7;
        if (i2 == 2) {
            int readUInt322 = codedInputStream$StreamDecoder.readUInt32();
            verifyPackedFixed32Length(readUInt322);
            int totalBytesRead2 = codedInputStream$StreamDecoder.getTotalBytesRead() + readUInt322;
            do {
                intArrayList.addInt(codedInputStream$StreamDecoder.readSFixed32());
            } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead2);
            return;
        }
        if (i2 != 5) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            intArrayList.addInt(codedInputStream$StreamDecoder.readSFixed32());
            if (codedInputStream$StreamDecoder.isAtEnd()) {
                return;
            } else {
                readTag2 = codedInputStream$StreamDecoder.readTag();
            }
        } while (readTag2 == this.tag);
        this.nextTag = readTag2;
    }

    public final void readSFixed64List(List list) {
        int readTag;
        int readTag2;
        boolean z = list instanceof LongArrayList;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (!z) {
            int i = this.tag & 7;
            if (i == 1) {
                do {
                    list.add(Long.valueOf(codedInputStream$StreamDecoder.readSFixed64()));
                    if (codedInputStream$StreamDecoder.isAtEnd()) {
                        return;
                    } else {
                        readTag = codedInputStream$StreamDecoder.readTag();
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int readUInt32 = codedInputStream$StreamDecoder.readUInt32();
            verifyPackedFixed64Length(readUInt32);
            int totalBytesRead = codedInputStream$StreamDecoder.getTotalBytesRead() + readUInt32;
            do {
                list.add(Long.valueOf(codedInputStream$StreamDecoder.readSFixed64()));
            } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead);
            return;
        }
        LongArrayList longArrayList = (LongArrayList) list;
        int i2 = this.tag & 7;
        if (i2 == 1) {
            do {
                longArrayList.addLong(codedInputStream$StreamDecoder.readSFixed64());
                if (codedInputStream$StreamDecoder.isAtEnd()) {
                    return;
                } else {
                    readTag2 = codedInputStream$StreamDecoder.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int readUInt322 = codedInputStream$StreamDecoder.readUInt32();
        verifyPackedFixed64Length(readUInt322);
        int totalBytesRead2 = codedInputStream$StreamDecoder.getTotalBytesRead() + readUInt322;
        do {
            longArrayList.addLong(codedInputStream$StreamDecoder.readSFixed64());
        } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead2);
    }

    public final void readSInt32List(List list) {
        int readTag;
        int readTag2;
        boolean z = list instanceof IntArrayList;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (!z) {
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(codedInputStream$StreamDecoder.readSInt32()));
                    if (codedInputStream$StreamDecoder.isAtEnd()) {
                        return;
                    } else {
                        readTag = codedInputStream$StreamDecoder.readTag();
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int totalBytesRead = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
            do {
                list.add(Integer.valueOf(codedInputStream$StreamDecoder.readSInt32()));
            } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead);
            requirePosition(totalBytesRead);
            return;
        }
        IntArrayList intArrayList = (IntArrayList) list;
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                intArrayList.addInt(codedInputStream$StreamDecoder.readSInt32());
                if (codedInputStream$StreamDecoder.isAtEnd()) {
                    return;
                } else {
                    readTag2 = codedInputStream$StreamDecoder.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead2 = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
        do {
            intArrayList.addInt(codedInputStream$StreamDecoder.readSInt32());
        } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead2);
        requirePosition(totalBytesRead2);
    }

    public final void readSInt64List(List list) {
        int readTag;
        int readTag2;
        boolean z = list instanceof LongArrayList;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (!z) {
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    list.add(Long.valueOf(codedInputStream$StreamDecoder.readSInt64()));
                    if (codedInputStream$StreamDecoder.isAtEnd()) {
                        return;
                    } else {
                        readTag = codedInputStream$StreamDecoder.readTag();
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int totalBytesRead = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
            do {
                list.add(Long.valueOf(codedInputStream$StreamDecoder.readSInt64()));
            } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead);
            requirePosition(totalBytesRead);
            return;
        }
        LongArrayList longArrayList = (LongArrayList) list;
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                longArrayList.addLong(codedInputStream$StreamDecoder.readSInt64());
                if (codedInputStream$StreamDecoder.isAtEnd()) {
                    return;
                } else {
                    readTag2 = codedInputStream$StreamDecoder.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead2 = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
        do {
            longArrayList.addLong(codedInputStream$StreamDecoder.readSInt64());
        } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead2);
        requirePosition(totalBytesRead2);
    }

    public final void readStringListInternal(List list, boolean z) {
        String readString;
        int readTag;
        int readTag2;
        if ((this.tag & 7) != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        boolean z2 = list instanceof LazyStringList;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (z2 && !z) {
            LazyStringList lazyStringList = (LazyStringList) list;
            do {
                lazyStringList.add(readBytes());
                if (codedInputStream$StreamDecoder.isAtEnd()) {
                    return;
                } else {
                    readTag2 = codedInputStream$StreamDecoder.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        do {
            if (z) {
                requireWireType(2);
                readString = codedInputStream$StreamDecoder.readStringRequireUtf8();
            } else {
                requireWireType(2);
                readString = codedInputStream$StreamDecoder.readString();
            }
            list.add(readString);
            if (codedInputStream$StreamDecoder.isAtEnd()) {
                return;
            } else {
                readTag = codedInputStream$StreamDecoder.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    public final void readUInt32List(List list) {
        int readTag;
        int readTag2;
        boolean z = list instanceof IntArrayList;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (!z) {
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    list.add(Integer.valueOf(codedInputStream$StreamDecoder.readUInt32()));
                    if (codedInputStream$StreamDecoder.isAtEnd()) {
                        return;
                    } else {
                        readTag = codedInputStream$StreamDecoder.readTag();
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int totalBytesRead = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
            do {
                list.add(Integer.valueOf(codedInputStream$StreamDecoder.readUInt32()));
            } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead);
            requirePosition(totalBytesRead);
            return;
        }
        IntArrayList intArrayList = (IntArrayList) list;
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                intArrayList.addInt(codedInputStream$StreamDecoder.readUInt32());
                if (codedInputStream$StreamDecoder.isAtEnd()) {
                    return;
                } else {
                    readTag2 = codedInputStream$StreamDecoder.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead2 = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
        do {
            intArrayList.addInt(codedInputStream$StreamDecoder.readUInt32());
        } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead2);
        requirePosition(totalBytesRead2);
    }

    public final void readUInt64List(List list) {
        int readTag;
        int readTag2;
        boolean z = list instanceof LongArrayList;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (!z) {
            int i = this.tag & 7;
            if (i == 0) {
                do {
                    list.add(Long.valueOf(codedInputStream$StreamDecoder.readUInt64()));
                    if (codedInputStream$StreamDecoder.isAtEnd()) {
                        return;
                    } else {
                        readTag = codedInputStream$StreamDecoder.readTag();
                    }
                } while (readTag == this.tag);
                this.nextTag = readTag;
                return;
            }
            if (i != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int totalBytesRead = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
            do {
                list.add(Long.valueOf(codedInputStream$StreamDecoder.readUInt64()));
            } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead);
            requirePosition(totalBytesRead);
            return;
        }
        LongArrayList longArrayList = (LongArrayList) list;
        int i2 = this.tag & 7;
        if (i2 == 0) {
            do {
                longArrayList.addLong(codedInputStream$StreamDecoder.readUInt64());
                if (codedInputStream$StreamDecoder.isAtEnd()) {
                    return;
                } else {
                    readTag2 = codedInputStream$StreamDecoder.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        if (i2 != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead2 = codedInputStream$StreamDecoder.getTotalBytesRead() + codedInputStream$StreamDecoder.readUInt32();
        do {
            longArrayList.addLong(codedInputStream$StreamDecoder.readUInt64());
        } while (codedInputStream$StreamDecoder.getTotalBytesRead() < totalBytesRead2);
        requirePosition(totalBytesRead2);
    }

    public final void requirePosition(int i) {
        if (this.input.getTotalBytesRead() != i) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    public final void requireWireType(int i) {
        if ((this.tag & 7) != i) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    public final boolean skipField() {
        int i;
        CodedInputStream$StreamDecoder codedInputStream$StreamDecoder = this.input;
        if (codedInputStream$StreamDecoder.isAtEnd() || (i = this.tag) == this.endGroupTag) {
            return false;
        }
        return codedInputStream$StreamDecoder.skipField(i);
    }
}
