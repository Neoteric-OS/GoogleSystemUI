package okio;

import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;
import kotlin.collections.AbstractList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Options extends AbstractList implements RandomAccess {
    public final ByteString[] byteStrings;
    public final int[] trie;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static void buildTrieRecursive(long j, Buffer buffer, int i, List list, int i2, int i3, List list2) {
            int i4;
            int i5;
            int i6;
            int i7;
            Buffer buffer2;
            int i8 = i;
            if (i2 >= i3) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            for (int i9 = i2; i9 < i3; i9++) {
                if (((ByteString) ((ArrayList) list).get(i9)).getSize$external__okio__android_common__okio_lib() < i8) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
            }
            ArrayList arrayList = (ArrayList) list;
            ByteString byteString = (ByteString) arrayList.get(i2);
            ByteString byteString2 = (ByteString) arrayList.get(i3 - 1);
            int i10 = -1;
            if (i8 == byteString.getSize$external__okio__android_common__okio_lib()) {
                int intValue = ((Number) list2.get(i2)).intValue();
                int i11 = i2 + 1;
                ByteString byteString3 = (ByteString) arrayList.get(i11);
                i4 = i11;
                i5 = intValue;
                byteString = byteString3;
            } else {
                i4 = i2;
                i5 = -1;
            }
            if (byteString.internalGet$external__okio__android_common__okio_lib(i8) == byteString2.internalGet$external__okio__android_common__okio_lib(i8)) {
                int min = Math.min(byteString.getSize$external__okio__android_common__okio_lib(), byteString2.getSize$external__okio__android_common__okio_lib());
                int i12 = 0;
                for (int i13 = i8; i13 < min && byteString.internalGet$external__okio__android_common__okio_lib(i13) == byteString2.internalGet$external__okio__android_common__okio_lib(i13); i13++) {
                    i12++;
                }
                long j2 = 4;
                long j3 = (buffer.size / j2) + j + 2 + i12 + 1;
                buffer.writeInt(-i12);
                buffer.writeInt(i5);
                int i14 = i12 + i8;
                while (i8 < i14) {
                    buffer.writeInt(byteString.internalGet$external__okio__android_common__okio_lib(i8) & 255);
                    i8++;
                }
                if (i4 + 1 == i3) {
                    if (i14 != ((ByteString) arrayList.get(i4)).getSize$external__okio__android_common__okio_lib()) {
                        throw new IllegalStateException("Check failed.");
                    }
                    buffer.writeInt(((Number) list2.get(i4)).intValue());
                    return;
                } else {
                    Buffer buffer3 = new Buffer();
                    buffer.writeInt(((int) ((buffer3.size / j2) + j3)) * (-1));
                    buildTrieRecursive(j3, buffer3, i14, list, i4, i3, list2);
                    buffer.writeAll(buffer3);
                    return;
                }
            }
            int i15 = 1;
            for (int i16 = i4 + 1; i16 < i3; i16++) {
                if (((ByteString) arrayList.get(i16 - 1)).internalGet$external__okio__android_common__okio_lib(i8) != ((ByteString) arrayList.get(i16)).internalGet$external__okio__android_common__okio_lib(i8)) {
                    i15++;
                }
            }
            long j4 = 4;
            long j5 = (buffer.size / j4) + j + 2 + (i15 * 2);
            buffer.writeInt(i15);
            buffer.writeInt(i5);
            for (int i17 = i4; i17 < i3; i17++) {
                byte internalGet$external__okio__android_common__okio_lib = ((ByteString) arrayList.get(i17)).internalGet$external__okio__android_common__okio_lib(i8);
                if (i17 == i4 || internalGet$external__okio__android_common__okio_lib != ((ByteString) arrayList.get(i17 - 1)).internalGet$external__okio__android_common__okio_lib(i8)) {
                    buffer.writeInt(internalGet$external__okio__android_common__okio_lib & 255);
                }
            }
            Buffer buffer4 = new Buffer();
            int i18 = i4;
            while (i18 < i3) {
                byte internalGet$external__okio__android_common__okio_lib2 = ((ByteString) arrayList.get(i18)).internalGet$external__okio__android_common__okio_lib(i8);
                int i19 = i18 + 1;
                int i20 = i19;
                while (true) {
                    if (i20 >= i3) {
                        i6 = i3;
                        break;
                    } else {
                        if (internalGet$external__okio__android_common__okio_lib2 != ((ByteString) arrayList.get(i20)).internalGet$external__okio__android_common__okio_lib(i8)) {
                            i6 = i20;
                            break;
                        }
                        i20++;
                    }
                }
                if (i19 == i6 && i8 + 1 == ((ByteString) arrayList.get(i18)).getSize$external__okio__android_common__okio_lib()) {
                    buffer.writeInt(((Number) list2.get(i18)).intValue());
                    i7 = i6;
                    buffer2 = buffer4;
                } else {
                    buffer.writeInt(((int) ((buffer4.size / j4) + j5)) * i10);
                    i7 = i6;
                    buffer2 = buffer4;
                    buildTrieRecursive(j5, buffer4, i8 + 1, list, i18, i6, list2);
                }
                buffer4 = buffer2;
                i18 = i7;
                i10 = -1;
            }
            buffer.writeAll(buffer4);
        }
    }

    public Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x00d9, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final okio.Options of(okio.ByteString... r11) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Options.of(okio.ByteString[]):okio.Options");
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ByteString) {
            return super.contains((ByteString) obj);
        }
        return false;
    }

    @Override // java.util.List
    public final Object get(int i) {
        return this.byteStrings[i];
    }

    @Override // kotlin.collections.AbstractCollection
    public final int getSize() {
        return this.byteStrings.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ByteString) {
            return super.indexOf((ByteString) obj);
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ByteString) {
            return super.lastIndexOf((ByteString) obj);
        }
        return -1;
    }
}
