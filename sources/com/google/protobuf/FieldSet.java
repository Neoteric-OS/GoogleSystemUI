package com.google.protobuf;

import com.android.app.viewcapture.data.ViewNode;
import com.google.protobuf.Internal;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FieldSet {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final SmallSortedMap$1 fields = new SmallSortedMap$1(16);
    public boolean isImmutable;

    static {
        new FieldSet(0);
    }

    public FieldSet() {
    }

    public static void writeElement(CodedOutputStream codedOutputStream, WireFormat$FieldType wireFormat$FieldType, int i, Object obj) {
        if (wireFormat$FieldType == WireFormat$FieldType.GROUP) {
            codedOutputStream.writeTag(i, 3);
            ((GeneratedMessageLite) ((MessageLite) obj)).writeTo(codedOutputStream);
            codedOutputStream.writeTag(i, 4);
            return;
        }
        codedOutputStream.writeTag(i, wireFormat$FieldType.getWireType());
        switch (wireFormat$FieldType.ordinal()) {
            case 0:
                codedOutputStream.writeFixed64NoTag(Double.doubleToRawLongBits(((Double) obj).doubleValue()));
                break;
            case 1:
                codedOutputStream.writeFixed32NoTag(Float.floatToRawIntBits(((Float) obj).floatValue()));
                break;
            case 2:
                codedOutputStream.writeUInt64NoTag(((Long) obj).longValue());
                break;
            case 3:
                codedOutputStream.writeUInt64NoTag(((Long) obj).longValue());
                break;
            case 4:
                codedOutputStream.writeInt32NoTag(((Integer) obj).intValue());
                break;
            case 5:
                codedOutputStream.writeFixed64NoTag(((Long) obj).longValue());
                break;
            case 6:
                codedOutputStream.writeFixed32NoTag(((Integer) obj).intValue());
                break;
            case 7:
                codedOutputStream.write(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0);
                break;
            case 8:
                if (!(obj instanceof ByteString)) {
                    codedOutputStream.writeStringNoTag((String) obj);
                    break;
                } else {
                    codedOutputStream.writeBytesNoTag((ByteString) obj);
                    break;
                }
            case 9:
                ((GeneratedMessageLite) ((MessageLite) obj)).writeTo(codedOutputStream);
                break;
            case 10:
                codedOutputStream.writeMessageNoTag((MessageLite) obj);
                break;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                if (!(obj instanceof ByteString)) {
                    byte[] bArr = (byte[]) obj;
                    codedOutputStream.writeByteArrayNoTag(bArr.length, bArr);
                    break;
                } else {
                    codedOutputStream.writeBytesNoTag((ByteString) obj);
                    break;
                }
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                codedOutputStream.writeUInt32NoTag(((Integer) obj).intValue());
                break;
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                if (!(obj instanceof Internal.EnumLite)) {
                    codedOutputStream.writeInt32NoTag(((Integer) obj).intValue());
                    break;
                } else {
                    codedOutputStream.writeInt32NoTag(((Internal.EnumLite) obj).getNumber());
                    break;
                }
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                codedOutputStream.writeFixed32NoTag(((Integer) obj).intValue());
                break;
            case 15:
                codedOutputStream.writeFixed64NoTag(((Long) obj).longValue());
                break;
            case 16:
                int intValue = ((Integer) obj).intValue();
                codedOutputStream.writeUInt32NoTag((intValue >> 31) ^ (intValue << 1));
                break;
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                long longValue = ((Long) obj).longValue();
                codedOutputStream.writeUInt64NoTag((longValue >> 63) ^ (longValue << 1));
                break;
        }
    }

    public final Object clone() {
        FieldSet fieldSet = new FieldSet();
        SmallSortedMap$1 smallSortedMap$1 = this.fields;
        if (smallSortedMap$1.entryList.size() > 0) {
            Map.Entry arrayEntryAt = smallSortedMap$1.getArrayEntryAt(0);
            if (arrayEntryAt.getKey() != null) {
                throw new ClassCastException();
            }
            arrayEntryAt.getValue();
            throw null;
        }
        Iterator it = smallSortedMap$1.getOverflowEntries().iterator();
        if (!it.hasNext()) {
            return fieldSet;
        }
        Map.Entry entry = (Map.Entry) it.next();
        if (entry.getKey() != null) {
            throw new ClassCastException();
        }
        entry.getValue();
        throw null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FieldSet) {
            return this.fields.equals(((FieldSet) obj).fields);
        }
        return false;
    }

    public final int hashCode() {
        return this.fields.hashCode();
    }

    public final void makeImmutable() {
        SmallSortedMap$1 smallSortedMap$1;
        if (this.isImmutable) {
            return;
        }
        int i = 0;
        while (true) {
            smallSortedMap$1 = this.fields;
            if (i >= smallSortedMap$1.entryList.size()) {
                break;
            }
            Map.Entry arrayEntryAt = smallSortedMap$1.getArrayEntryAt(i);
            if (arrayEntryAt.getValue() instanceof GeneratedMessageLite) {
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) arrayEntryAt.getValue();
                generatedMessageLite.getClass();
                Protobuf protobuf = Protobuf.INSTANCE;
                protobuf.getClass();
                protobuf.schemaFor(generatedMessageLite.getClass()).makeImmutable(generatedMessageLite);
                generatedMessageLite.markImmutable();
            }
            i++;
        }
        if (!smallSortedMap$1.isImmutable) {
            if (smallSortedMap$1.entryList.size() > 0) {
                smallSortedMap$1.getArrayEntryAt(0).getKey().getClass();
                throw new ClassCastException();
            }
            Iterator it = smallSortedMap$1.getOverflowEntries().iterator();
            if (it.hasNext()) {
                ((Map.Entry) it.next()).getKey().getClass();
                throw new ClassCastException();
            }
        }
        if (!smallSortedMap$1.isImmutable) {
            smallSortedMap$1.overflowEntries = smallSortedMap$1.overflowEntries.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(smallSortedMap$1.overflowEntries);
            smallSortedMap$1.overflowEntriesDescending = smallSortedMap$1.overflowEntriesDescending.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(smallSortedMap$1.overflowEntriesDescending);
            smallSortedMap$1.isImmutable = true;
        }
        this.isImmutable = true;
    }

    public FieldSet(int i) {
        makeImmutable();
        makeImmutable();
    }
}
