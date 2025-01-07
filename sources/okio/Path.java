package okio;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Path implements Comparable {
    public static final Companion Companion = null;
    public static final String DIRECTORY_SEPARATOR = File.separator;
    public final ByteString bytes;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static Path get(String str, boolean z) {
            ByteString byteString = okio.internal.Path.SLASH;
            Buffer buffer = new Buffer();
            buffer.writeUtf8(str, 0, str.length());
            return okio.internal.Path.toPath(buffer, z);
        }

        public static Path get$default(File file) {
            Companion companion = Path.Companion;
            return get(file.toString(), false);
        }
    }

    public Path(ByteString byteString) {
        this.bytes = byteString;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.bytes.compareTo(((Path) obj).bytes);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof Path) && Intrinsics.areEqual(((Path) obj).bytes, this.bytes);
    }

    public final List getSegmentsBytes() {
        ArrayList arrayList = new ArrayList();
        int access$rootLength = okio.internal.Path.access$rootLength(this);
        if (access$rootLength == -1) {
            access$rootLength = 0;
        } else if (access$rootLength < this.bytes.getSize$external__okio__android_common__okio_lib() && this.bytes.internalGet$external__okio__android_common__okio_lib(access$rootLength) == 92) {
            access$rootLength++;
        }
        int size$external__okio__android_common__okio_lib = this.bytes.getSize$external__okio__android_common__okio_lib();
        int i = access$rootLength;
        while (access$rootLength < size$external__okio__android_common__okio_lib) {
            if (this.bytes.internalGet$external__okio__android_common__okio_lib(access$rootLength) == 47 || this.bytes.internalGet$external__okio__android_common__okio_lib(access$rootLength) == 92) {
                arrayList.add(this.bytes.substring(i, access$rootLength));
                i = access$rootLength + 1;
            }
            access$rootLength++;
        }
        if (i < this.bytes.getSize$external__okio__android_common__okio_lib()) {
            ByteString byteString = this.bytes;
            arrayList.add(byteString.substring(i, byteString.getSize$external__okio__android_common__okio_lib()));
        }
        return arrayList;
    }

    public final int hashCode() {
        return this.bytes.hashCode();
    }

    public final String name() {
        ByteString byteString = okio.internal.Path.SLASH;
        int lastIndexOf$default = ByteString.lastIndexOf$default(this.bytes, okio.internal.Path.SLASH);
        if (lastIndexOf$default == -1) {
            lastIndexOf$default = ByteString.lastIndexOf$default(this.bytes, okio.internal.Path.BACKSLASH);
        }
        return (lastIndexOf$default != -1 ? ByteString.substring$default(this.bytes, lastIndexOf$default + 1, 0, 2) : (volumeLetter() == null || this.bytes.getSize$external__okio__android_common__okio_lib() != 2) ? this.bytes : ByteString.EMPTY).utf8();
    }

    public final Path parent() {
        ByteString byteString = this.bytes;
        ByteString byteString2 = okio.internal.Path.DOT;
        if (Intrinsics.areEqual(byteString, byteString2)) {
            return null;
        }
        ByteString byteString3 = this.bytes;
        ByteString byteString4 = okio.internal.Path.SLASH;
        if (Intrinsics.areEqual(byteString3, byteString4)) {
            return null;
        }
        ByteString byteString5 = this.bytes;
        ByteString byteString6 = okio.internal.Path.BACKSLASH;
        if (Intrinsics.areEqual(byteString5, byteString6)) {
            return null;
        }
        ByteString byteString7 = this.bytes;
        ByteString byteString8 = okio.internal.Path.DOT_DOT;
        if (byteString7.rangeEquals(byteString7.getSize$external__okio__android_common__okio_lib() - byteString8.getSize$external__okio__android_common__okio_lib(), byteString8, byteString8.getSize$external__okio__android_common__okio_lib())) {
            if (this.bytes.getSize$external__okio__android_common__okio_lib() == 2) {
                return null;
            }
            ByteString byteString9 = this.bytes;
            if (byteString9.rangeEquals(byteString9.getSize$external__okio__android_common__okio_lib() - 3, byteString4, 1)) {
                return null;
            }
            ByteString byteString10 = this.bytes;
            if (byteString10.rangeEquals(byteString10.getSize$external__okio__android_common__okio_lib() - 3, byteString6, 1)) {
                return null;
            }
        }
        int lastIndexOf$default = ByteString.lastIndexOf$default(this.bytes, byteString4);
        if (lastIndexOf$default == -1) {
            lastIndexOf$default = ByteString.lastIndexOf$default(this.bytes, byteString6);
        }
        if (lastIndexOf$default == 2 && volumeLetter() != null) {
            if (this.bytes.getSize$external__okio__android_common__okio_lib() == 3) {
                return null;
            }
            return new Path(ByteString.substring$default(this.bytes, 0, 3, 1));
        }
        if (lastIndexOf$default == 1) {
            ByteString byteString11 = this.bytes;
            byteString11.getClass();
            if (byteString11.rangeEquals(0, byteString6, byteString6.getSize$external__okio__android_common__okio_lib())) {
                return null;
            }
        }
        if (lastIndexOf$default != -1 || volumeLetter() == null) {
            return lastIndexOf$default == -1 ? new Path(byteString2) : lastIndexOf$default == 0 ? new Path(ByteString.substring$default(this.bytes, 0, 1, 1)) : new Path(ByteString.substring$default(this.bytes, 0, lastIndexOf$default, 1));
        }
        if (this.bytes.getSize$external__okio__android_common__okio_lib() == 2) {
            return null;
        }
        return new Path(ByteString.substring$default(this.bytes, 0, 2, 1));
    }

    public final Path resolve(String str) {
        Buffer buffer = new Buffer();
        buffer.writeUtf8(str, 0, str.length());
        return okio.internal.Path.commonResolve(this, okio.internal.Path.toPath(buffer, false), false);
    }

    public final File toFile() {
        return new File(this.bytes.utf8());
    }

    public final String toString() {
        return this.bytes.utf8();
    }

    public final Character volumeLetter() {
        if (ByteString.indexOf$default(this.bytes, okio.internal.Path.SLASH) != -1 || this.bytes.getSize$external__okio__android_common__okio_lib() < 2 || this.bytes.internalGet$external__okio__android_common__okio_lib(1) != 58) {
            return null;
        }
        char internalGet$external__okio__android_common__okio_lib = (char) this.bytes.internalGet$external__okio__android_common__okio_lib(0);
        if (('a' > internalGet$external__okio__android_common__okio_lib || internalGet$external__okio__android_common__okio_lib >= '{') && ('A' > internalGet$external__okio__android_common__okio_lib || internalGet$external__okio__android_common__okio_lib >= '[')) {
            return null;
        }
        return Character.valueOf(internalGet$external__okio__android_common__okio_lib);
    }
}
