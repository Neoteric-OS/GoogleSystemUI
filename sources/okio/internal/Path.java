package okio.internal;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* renamed from: okio.internal.-Path, reason: invalid class name */
/* loaded from: classes2.dex */
public abstract class Path {
    public static final ByteString ANY_SLASH;
    public static final ByteString BACKSLASH;
    public static final ByteString DOT;
    public static final ByteString DOT_DOT;
    public static final ByteString SLASH;

    static {
        ByteString byteString = ByteString.EMPTY;
        SLASH = ByteString.Companion.encodeUtf8("/");
        BACKSLASH = ByteString.Companion.encodeUtf8("\\");
        ANY_SLASH = ByteString.Companion.encodeUtf8("/\\");
        DOT = ByteString.Companion.encodeUtf8(".");
        DOT_DOT = ByteString.Companion.encodeUtf8("..");
    }

    public static final int access$rootLength(okio.Path path) {
        if (path.bytes.getSize$external__okio__android_common__okio_lib() == 0) {
            return -1;
        }
        if (path.bytes.internalGet$external__okio__android_common__okio_lib(0) != 47) {
            if (path.bytes.internalGet$external__okio__android_common__okio_lib(0) != 92) {
                if (path.bytes.getSize$external__okio__android_common__okio_lib() <= 2 || path.bytes.internalGet$external__okio__android_common__okio_lib(1) != 58 || path.bytes.internalGet$external__okio__android_common__okio_lib(2) != 92) {
                    return -1;
                }
                char internalGet$external__okio__android_common__okio_lib = (char) path.bytes.internalGet$external__okio__android_common__okio_lib(0);
                return (('a' > internalGet$external__okio__android_common__okio_lib || internalGet$external__okio__android_common__okio_lib >= '{') && ('A' > internalGet$external__okio__android_common__okio_lib || internalGet$external__okio__android_common__okio_lib >= '[')) ? -1 : 3;
            }
            if (path.bytes.getSize$external__okio__android_common__okio_lib() > 2 && path.bytes.internalGet$external__okio__android_common__okio_lib(1) == 92) {
                int indexOf = path.bytes.indexOf(BACKSLASH, 2);
                return indexOf == -1 ? path.bytes.getSize$external__okio__android_common__okio_lib() : indexOf;
            }
        }
        return 1;
    }

    public static final okio.Path commonResolve(okio.Path path, okio.Path path2, boolean z) {
        path2.getClass();
        if (access$rootLength(path2) != -1 || path2.volumeLetter() != null) {
            return path2;
        }
        ByteString slash = getSlash(path);
        if (slash == null && (slash = getSlash(path2)) == null) {
            slash = toSlash(okio.Path.DIRECTORY_SEPARATOR);
        }
        Buffer buffer = new Buffer();
        buffer.write(path.bytes);
        if (buffer.size > 0) {
            buffer.write(slash);
        }
        buffer.write(path2.bytes);
        return toPath(buffer, z);
    }

    public static final ByteString getSlash(okio.Path path) {
        ByteString byteString = path.bytes;
        ByteString byteString2 = SLASH;
        if (ByteString.indexOf$default(byteString, byteString2) != -1) {
            return byteString2;
        }
        ByteString byteString3 = path.bytes;
        ByteString byteString4 = BACKSLASH;
        if (ByteString.indexOf$default(byteString3, byteString4) != -1) {
            return byteString4;
        }
        return null;
    }

    public static final okio.Path toPath(Buffer buffer, boolean z) {
        ByteString byteString;
        ByteString byteString2;
        char c;
        ByteString byteString3;
        ByteString readByteString;
        Buffer buffer2 = new Buffer();
        ByteString byteString4 = null;
        int i = 0;
        while (true) {
            if (!buffer.rangeEquals(SLASH)) {
                byteString = BACKSLASH;
                if (!buffer.rangeEquals(byteString)) {
                    break;
                }
            }
            byte readByte = buffer.readByte();
            if (byteString4 == null) {
                byteString4 = toSlash(readByte);
            }
            i++;
        }
        boolean z2 = i >= 2 && Intrinsics.areEqual(byteString4, byteString);
        ByteString byteString5 = ANY_SLASH;
        if (z2) {
            Intrinsics.checkNotNull(byteString4);
            byteString4.write$external__okio__android_common__okio_lib(buffer2, byteString4.getSize$external__okio__android_common__okio_lib());
            byteString4.write$external__okio__android_common__okio_lib(buffer2, byteString4.getSize$external__okio__android_common__okio_lib());
        } else if (i > 0) {
            Intrinsics.checkNotNull(byteString4);
            byteString4.write$external__okio__android_common__okio_lib(buffer2, byteString4.getSize$external__okio__android_common__okio_lib());
        } else {
            long indexOfElement = buffer.indexOfElement(byteString5, 0L);
            if (byteString4 == null) {
                byteString4 = indexOfElement == -1 ? toSlash(okio.Path.DIRECTORY_SEPARATOR) : toSlash(buffer.getByte(indexOfElement));
            }
            if (Intrinsics.areEqual(byteString4, byteString)) {
                byteString2 = byteString4;
                if (buffer.size >= 2 && buffer.getByte(1L) == 58 && (('a' <= (c = (char) buffer.getByte(0L)) && c < '{') || ('A' <= c && c < '['))) {
                    if (indexOfElement == 2) {
                        buffer2.write(buffer, 3L);
                    } else {
                        buffer2.write(buffer, 2L);
                    }
                }
            } else {
                byteString2 = byteString4;
            }
            byteString4 = byteString2;
        }
        boolean z3 = buffer2.size > 0;
        ArrayList arrayList = new ArrayList();
        while (true) {
            boolean exhausted = buffer.exhausted();
            byteString3 = DOT;
            if (exhausted) {
                break;
            }
            long indexOfElement2 = buffer.indexOfElement(byteString5, 0L);
            if (indexOfElement2 == -1) {
                readByteString = buffer.readByteString(buffer.size);
            } else {
                readByteString = buffer.readByteString(indexOfElement2);
                buffer.readByte();
            }
            ByteString byteString6 = DOT_DOT;
            if (readByteString.equals(byteString6)) {
                if (!z3 || !arrayList.isEmpty()) {
                    if (!z || (!z3 && (arrayList.isEmpty() || Intrinsics.areEqual(CollectionsKt.last(arrayList), byteString6)))) {
                        arrayList.add(readByteString);
                    } else if (!z2 || arrayList.size() != 1) {
                        if (!arrayList.isEmpty()) {
                            arrayList.remove(CollectionsKt__CollectionsKt.getLastIndex(arrayList));
                        }
                    }
                }
            } else if (!readByteString.equals(byteString3) && !readByteString.equals(ByteString.EMPTY)) {
                arrayList.add(readByteString);
            }
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i2 > 0) {
                buffer2.write(byteString4);
            }
            buffer2.write((ByteString) arrayList.get(i2));
        }
        if (buffer2.size == 0) {
            buffer2.write(byteString3);
        }
        return new okio.Path(buffer2.readByteString(buffer2.size));
    }

    public static final ByteString toSlash(String str) {
        if (Intrinsics.areEqual(str, "/")) {
            return SLASH;
        }
        if (Intrinsics.areEqual(str, "\\")) {
            return BACKSLASH;
        }
        throw new IllegalArgumentException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("not a directory separator: ", str));
    }

    public static final ByteString toSlash(byte b) {
        if (b == 47) {
            return SLASH;
        }
        if (b == 92) {
            return BACKSLASH;
        }
        throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(b, "not a directory separator: "));
    }
}
