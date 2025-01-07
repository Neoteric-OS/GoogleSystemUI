package androidx.datastore.preferences.protobuf;

import com.android.app.viewcapture.data.ViewNode;
import java.nio.charset.Charset;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CodedInputStreamReader {
    public int endGroupTag;
    public final CodedInputStream input;
    public int nextTag = 0;
    public int tag;

    public CodedInputStreamReader(CodedInputStream codedInputStream) {
        Charset charset = Internal.UTF_8;
        this.input = codedInputStream;
        codedInputStream.wrapper = this;
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
                throw new InvalidProtocolBufferException("Failed to parse the message.");
            }
        } finally {
            this.endGroupTag = i;
        }
    }

    public final void mergeMessageFieldInternal(Object obj, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        CodedInputStream codedInputStream = this.input;
        int readUInt32 = codedInputStream.readUInt32();
        if (codedInputStream.recursionDepth >= 100) {
            throw new InvalidProtocolBufferException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int pushLimit = codedInputStream.pushLimit(readUInt32);
        codedInputStream.recursionDepth++;
        schema.mergeFrom(obj, this, extensionRegistryLite);
        codedInputStream.checkLastTagWas(0);
        codedInputStream.recursionDepth--;
        codedInputStream.popLimit(pushLimit);
    }

    public final void readBoolList(List list) {
        int readTag;
        int i = this.tag & 7;
        CodedInputStream codedInputStream = this.input;
        if (i == 0) {
            do {
                list.add(Boolean.valueOf(codedInputStream.readBool()));
                if (codedInputStream.isAtEnd()) {
                    return;
                } else {
                    readTag = codedInputStream.readTag();
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
            return;
        }
        if (i != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead = codedInputStream.getTotalBytesRead() + codedInputStream.readUInt32();
        do {
            list.add(Boolean.valueOf(codedInputStream.readBool()));
        } while (codedInputStream.getTotalBytesRead() < totalBytesRead);
        requirePosition(totalBytesRead);
    }

    public final ByteString readBytes() {
        requireWireType(2);
        return this.input.readBytes();
    }

    public final void readBytesList(List list) {
        int readTag;
        if ((this.tag & 7) != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            list.add(readBytes());
            CodedInputStream codedInputStream = this.input;
            if (codedInputStream.isAtEnd()) {
                return;
            } else {
                readTag = codedInputStream.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    public final void readDoubleList(List list) {
        int readTag;
        int i = this.tag & 7;
        CodedInputStream codedInputStream = this.input;
        if (i == 1) {
            do {
                list.add(Double.valueOf(codedInputStream.readDouble()));
                if (codedInputStream.isAtEnd()) {
                    return;
                } else {
                    readTag = codedInputStream.readTag();
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
            return;
        }
        if (i != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int readUInt32 = codedInputStream.readUInt32();
        if ((readUInt32 & 7) != 0) {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        int totalBytesRead = codedInputStream.getTotalBytesRead() + readUInt32;
        do {
            list.add(Double.valueOf(codedInputStream.readDouble()));
        } while (codedInputStream.getTotalBytesRead() < totalBytesRead);
    }

    public final void readEnumList(List list) {
        int readTag;
        int i = this.tag & 7;
        CodedInputStream codedInputStream = this.input;
        if (i == 0) {
            do {
                list.add(Integer.valueOf(codedInputStream.readEnum()));
                if (codedInputStream.isAtEnd()) {
                    return;
                } else {
                    readTag = codedInputStream.readTag();
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
            return;
        }
        if (i != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead = codedInputStream.getTotalBytesRead() + codedInputStream.readUInt32();
        do {
            list.add(Integer.valueOf(codedInputStream.readEnum()));
        } while (codedInputStream.getTotalBytesRead() < totalBytesRead);
        requirePosition(totalBytesRead);
    }

    public final Object readField(WireFormat$FieldType wireFormat$FieldType, Class cls, ExtensionRegistryLite extensionRegistryLite) {
        int ordinal = wireFormat$FieldType.ordinal();
        CodedInputStream codedInputStream = this.input;
        switch (ordinal) {
            case 0:
                requireWireType(1);
                return Double.valueOf(codedInputStream.readDouble());
            case 1:
                requireWireType(5);
                return Float.valueOf(codedInputStream.readFloat());
            case 2:
                requireWireType(0);
                return Long.valueOf(codedInputStream.readInt64());
            case 3:
                requireWireType(0);
                return Long.valueOf(codedInputStream.readUInt64());
            case 4:
                requireWireType(0);
                return Integer.valueOf(codedInputStream.readInt32());
            case 5:
                requireWireType(1);
                return Long.valueOf(codedInputStream.readFixed64());
            case 6:
                requireWireType(5);
                return Integer.valueOf(codedInputStream.readFixed32());
            case 7:
                requireWireType(0);
                return Boolean.valueOf(codedInputStream.readBool());
            case 8:
                requireWireType(2);
                return codedInputStream.readStringRequireUtf8();
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
                return Integer.valueOf(codedInputStream.readUInt32());
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                requireWireType(0);
                return Integer.valueOf(codedInputStream.readEnum());
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                requireWireType(5);
                return Integer.valueOf(codedInputStream.readSFixed32());
            case 15:
                requireWireType(1);
                return Long.valueOf(codedInputStream.readSFixed64());
            case 16:
                requireWireType(0);
                return Integer.valueOf(codedInputStream.readSInt32());
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                requireWireType(0);
                return Long.valueOf(codedInputStream.readSInt64());
        }
    }

    public final void readFixed32List(List list) {
        int readTag;
        int i = this.tag & 7;
        CodedInputStream codedInputStream = this.input;
        if (i == 2) {
            int readUInt32 = codedInputStream.readUInt32();
            if ((readUInt32 & 3) != 0) {
                throw new InvalidProtocolBufferException("Failed to parse the message.");
            }
            int totalBytesRead = codedInputStream.getTotalBytesRead() + readUInt32;
            do {
                list.add(Integer.valueOf(codedInputStream.readFixed32()));
            } while (codedInputStream.getTotalBytesRead() < totalBytesRead);
            return;
        }
        if (i != 5) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            list.add(Integer.valueOf(codedInputStream.readFixed32()));
            if (codedInputStream.isAtEnd()) {
                return;
            } else {
                readTag = codedInputStream.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    public final void readFixed64List(List list) {
        int readTag;
        int i = this.tag & 7;
        CodedInputStream codedInputStream = this.input;
        if (i == 1) {
            do {
                list.add(Long.valueOf(codedInputStream.readFixed64()));
                if (codedInputStream.isAtEnd()) {
                    return;
                } else {
                    readTag = codedInputStream.readTag();
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
            return;
        }
        if (i != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int readUInt32 = codedInputStream.readUInt32();
        if ((readUInt32 & 7) != 0) {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        int totalBytesRead = codedInputStream.getTotalBytesRead() + readUInt32;
        do {
            list.add(Long.valueOf(codedInputStream.readFixed64()));
        } while (codedInputStream.getTotalBytesRead() < totalBytesRead);
    }

    public final void readFloatList(List list) {
        int readTag;
        int i = this.tag & 7;
        CodedInputStream codedInputStream = this.input;
        if (i == 2) {
            int readUInt32 = codedInputStream.readUInt32();
            if ((readUInt32 & 3) != 0) {
                throw new InvalidProtocolBufferException("Failed to parse the message.");
            }
            int totalBytesRead = codedInputStream.getTotalBytesRead() + readUInt32;
            do {
                list.add(Float.valueOf(codedInputStream.readFloat()));
            } while (codedInputStream.getTotalBytesRead() < totalBytesRead);
            return;
        }
        if (i != 5) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            list.add(Float.valueOf(codedInputStream.readFloat()));
            if (codedInputStream.isAtEnd()) {
                return;
            } else {
                readTag = codedInputStream.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    public final void readInt32List(List list) {
        int readTag;
        int i = this.tag & 7;
        CodedInputStream codedInputStream = this.input;
        if (i == 0) {
            do {
                list.add(Integer.valueOf(codedInputStream.readInt32()));
                if (codedInputStream.isAtEnd()) {
                    return;
                } else {
                    readTag = codedInputStream.readTag();
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
            return;
        }
        if (i != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead = codedInputStream.getTotalBytesRead() + codedInputStream.readUInt32();
        do {
            list.add(Integer.valueOf(codedInputStream.readInt32()));
        } while (codedInputStream.getTotalBytesRead() < totalBytesRead);
        requirePosition(totalBytesRead);
    }

    public final void readInt64List(List list) {
        int readTag;
        int i = this.tag & 7;
        CodedInputStream codedInputStream = this.input;
        if (i == 0) {
            do {
                list.add(Long.valueOf(codedInputStream.readInt64()));
                if (codedInputStream.isAtEnd()) {
                    return;
                } else {
                    readTag = codedInputStream.readTag();
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
            return;
        }
        if (i != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead = codedInputStream.getTotalBytesRead() + codedInputStream.readUInt32();
        do {
            list.add(Long.valueOf(codedInputStream.readInt64()));
        } while (codedInputStream.getTotalBytesRead() < totalBytesRead);
        requirePosition(totalBytesRead);
    }

    public final void readSFixed32List(List list) {
        int readTag;
        int i = this.tag & 7;
        CodedInputStream codedInputStream = this.input;
        if (i == 2) {
            int readUInt32 = codedInputStream.readUInt32();
            if ((readUInt32 & 3) != 0) {
                throw new InvalidProtocolBufferException("Failed to parse the message.");
            }
            int totalBytesRead = codedInputStream.getTotalBytesRead() + readUInt32;
            do {
                list.add(Integer.valueOf(codedInputStream.readSFixed32()));
            } while (codedInputStream.getTotalBytesRead() < totalBytesRead);
            return;
        }
        if (i != 5) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            list.add(Integer.valueOf(codedInputStream.readSFixed32()));
            if (codedInputStream.isAtEnd()) {
                return;
            } else {
                readTag = codedInputStream.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    public final void readSFixed64List(List list) {
        int readTag;
        int i = this.tag & 7;
        CodedInputStream codedInputStream = this.input;
        if (i == 1) {
            do {
                list.add(Long.valueOf(codedInputStream.readSFixed64()));
                if (codedInputStream.isAtEnd()) {
                    return;
                } else {
                    readTag = codedInputStream.readTag();
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
            return;
        }
        if (i != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int readUInt32 = codedInputStream.readUInt32();
        if ((readUInt32 & 7) != 0) {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
        int totalBytesRead = codedInputStream.getTotalBytesRead() + readUInt32;
        do {
            list.add(Long.valueOf(codedInputStream.readSFixed64()));
        } while (codedInputStream.getTotalBytesRead() < totalBytesRead);
    }

    public final void readSInt32List(List list) {
        int readTag;
        int i = this.tag & 7;
        CodedInputStream codedInputStream = this.input;
        if (i == 0) {
            do {
                list.add(Integer.valueOf(codedInputStream.readSInt32()));
                if (codedInputStream.isAtEnd()) {
                    return;
                } else {
                    readTag = codedInputStream.readTag();
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
            return;
        }
        if (i != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead = codedInputStream.getTotalBytesRead() + codedInputStream.readUInt32();
        do {
            list.add(Integer.valueOf(codedInputStream.readSInt32()));
        } while (codedInputStream.getTotalBytesRead() < totalBytesRead);
        requirePosition(totalBytesRead);
    }

    public final void readSInt64List(List list) {
        int readTag;
        int i = this.tag & 7;
        CodedInputStream codedInputStream = this.input;
        if (i == 0) {
            do {
                list.add(Long.valueOf(codedInputStream.readSInt64()));
                if (codedInputStream.isAtEnd()) {
                    return;
                } else {
                    readTag = codedInputStream.readTag();
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
            return;
        }
        if (i != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead = codedInputStream.getTotalBytesRead() + codedInputStream.readUInt32();
        do {
            list.add(Long.valueOf(codedInputStream.readSInt64()));
        } while (codedInputStream.getTotalBytesRead() < totalBytesRead);
        requirePosition(totalBytesRead);
    }

    public final void readStringListInternal(List list, boolean z) {
        String readString;
        int readTag;
        int readTag2;
        if ((this.tag & 7) != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        boolean z2 = list instanceof LazyStringList;
        CodedInputStream codedInputStream = this.input;
        if (z2 && !z) {
            LazyStringList lazyStringList = (LazyStringList) list;
            do {
                lazyStringList.add(readBytes());
                if (codedInputStream.isAtEnd()) {
                    return;
                } else {
                    readTag2 = codedInputStream.readTag();
                }
            } while (readTag2 == this.tag);
            this.nextTag = readTag2;
            return;
        }
        do {
            if (z) {
                requireWireType(2);
                readString = codedInputStream.readStringRequireUtf8();
            } else {
                requireWireType(2);
                readString = codedInputStream.readString();
            }
            list.add(readString);
            if (codedInputStream.isAtEnd()) {
                return;
            } else {
                readTag = codedInputStream.readTag();
            }
        } while (readTag == this.tag);
        this.nextTag = readTag;
    }

    public final void readUInt32List(List list) {
        int readTag;
        int i = this.tag & 7;
        CodedInputStream codedInputStream = this.input;
        if (i == 0) {
            do {
                list.add(Integer.valueOf(codedInputStream.readUInt32()));
                if (codedInputStream.isAtEnd()) {
                    return;
                } else {
                    readTag = codedInputStream.readTag();
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
            return;
        }
        if (i != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead = codedInputStream.getTotalBytesRead() + codedInputStream.readUInt32();
        do {
            list.add(Integer.valueOf(codedInputStream.readUInt32()));
        } while (codedInputStream.getTotalBytesRead() < totalBytesRead);
        requirePosition(totalBytesRead);
    }

    public final void readUInt64List(List list) {
        int readTag;
        int i = this.tag & 7;
        CodedInputStream codedInputStream = this.input;
        if (i == 0) {
            do {
                list.add(Long.valueOf(codedInputStream.readUInt64()));
                if (codedInputStream.isAtEnd()) {
                    return;
                } else {
                    readTag = codedInputStream.readTag();
                }
            } while (readTag == this.tag);
            this.nextTag = readTag;
            return;
        }
        if (i != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int totalBytesRead = codedInputStream.getTotalBytesRead() + codedInputStream.readUInt32();
        do {
            list.add(Long.valueOf(codedInputStream.readUInt64()));
        } while (codedInputStream.getTotalBytesRead() < totalBytesRead);
        requirePosition(totalBytesRead);
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
        CodedInputStream codedInputStream = this.input;
        if (codedInputStream.isAtEnd() || (i = this.tag) == this.endGroupTag) {
            return false;
        }
        return codedInputStream.skipField(i);
    }
}
