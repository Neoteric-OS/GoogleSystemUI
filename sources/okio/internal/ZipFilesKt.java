package okio.internal;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$LongRef;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.FileMetadata;
import okio.Path;
import okio.RealBufferedSource;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ZipFilesKt {
    public static final Map buildIndex(List list) {
        Path.Companion companion = Path.Companion;
        Path path = Path.Companion.get("/", false);
        Map mutableMapOf = MapsKt.mutableMapOf(new Pair(path, new ZipEntry(path)));
        for (ZipEntry zipEntry : CollectionsKt.sortedWith(list, new ZipFilesKt$buildIndex$$inlined$sortedBy$1())) {
            if (((ZipEntry) mutableMapOf.put(zipEntry.canonicalPath, zipEntry)) == null) {
                while (true) {
                    Path path2 = zipEntry.canonicalPath;
                    Path parent = path2.parent();
                    if (parent != null) {
                        ZipEntry zipEntry2 = (ZipEntry) mutableMapOf.get(parent);
                        if (zipEntry2 != null) {
                            zipEntry2.children.add(path2);
                            break;
                        }
                        ZipEntry zipEntry3 = new ZipEntry(parent);
                        mutableMapOf.put(parent, zipEntry3);
                        zipEntry3.children.add(path2);
                        zipEntry = zipEntry3;
                    }
                }
            }
        }
        return mutableMapOf;
    }

    public static final String getHex(int i) {
        CharsKt.checkRadix(16);
        return "0x".concat(Integer.toString(i, 16));
    }

    public static final ZipEntry readEntry(final RealBufferedSource realBufferedSource) {
        Long valueOf;
        Long l;
        long j;
        int readIntLe = realBufferedSource.readIntLe();
        if (readIntLe != 33639248) {
            throw new IOException(GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("bad zip: expected ", getHex(33639248), " but was ", getHex(readIntLe)));
        }
        realBufferedSource.skip(4L);
        short readShortLe = realBufferedSource.readShortLe();
        int i = readShortLe & 65535;
        if ((readShortLe & 1) != 0) {
            throw new IOException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("unsupported zip: general purpose bit flag=", getHex(i)));
        }
        int readShortLe2 = realBufferedSource.readShortLe() & 65535;
        short readShortLe3 = realBufferedSource.readShortLe();
        int i2 = readShortLe3 & 65535;
        short readShortLe4 = realBufferedSource.readShortLe();
        int i3 = readShortLe4 & 65535;
        if (i2 == -1) {
            valueOf = null;
        } else {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.set(14, 0);
            gregorianCalendar.set(((i3 >> 9) & 127) + 1980, ((i3 >> 5) & 15) - 1, readShortLe4 & 31, (i2 >> 11) & 31, (i2 >> 5) & 63, (readShortLe3 & 31) << 1);
            valueOf = Long.valueOf(gregorianCalendar.getTime().getTime());
        }
        Long l2 = valueOf;
        realBufferedSource.readIntLe();
        final Ref$LongRef ref$LongRef = new Ref$LongRef();
        ref$LongRef.element = realBufferedSource.readIntLe() & 4294967295L;
        final Ref$LongRef ref$LongRef2 = new Ref$LongRef();
        ref$LongRef2.element = realBufferedSource.readIntLe() & 4294967295L;
        int readShortLe5 = realBufferedSource.readShortLe() & 65535;
        int readShortLe6 = realBufferedSource.readShortLe() & 65535;
        int readShortLe7 = realBufferedSource.readShortLe() & 65535;
        realBufferedSource.skip(8L);
        final Ref$LongRef ref$LongRef3 = new Ref$LongRef();
        ref$LongRef3.element = realBufferedSource.readIntLe() & 4294967295L;
        String readUtf8 = realBufferedSource.readUtf8(readShortLe5);
        if (StringsKt.contains$default((CharSequence) readUtf8, (char) 0)) {
            throw new IOException("bad zip: filename contains 0x00");
        }
        if (ref$LongRef2.element == 4294967295L) {
            j = 8;
            l = l2;
        } else {
            l = l2;
            j = 0;
        }
        if (ref$LongRef.element == 4294967295L) {
            j += 8;
        }
        if (ref$LongRef3.element == 4294967295L) {
            j += 8;
        }
        final long j2 = j;
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        readExtra(realBufferedSource, readShortLe6, new Function2() { // from class: okio.internal.ZipFilesKt$readEntry$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                int intValue = ((Number) obj).intValue();
                long longValue = ((Number) obj2).longValue();
                if (intValue == 1) {
                    Ref$BooleanRef ref$BooleanRef2 = Ref$BooleanRef.this;
                    if (ref$BooleanRef2.element) {
                        throw new IOException("bad zip: zip64 extra repeated");
                    }
                    ref$BooleanRef2.element = true;
                    if (longValue < j2) {
                        throw new IOException("bad zip: zip64 extra too short");
                    }
                    Ref$LongRef ref$LongRef4 = ref$LongRef2;
                    long j3 = ref$LongRef4.element;
                    if (j3 == 4294967295L) {
                        j3 = realBufferedSource.readLongLe();
                    }
                    ref$LongRef4.element = j3;
                    Ref$LongRef ref$LongRef5 = ref$LongRef;
                    ref$LongRef5.element = ref$LongRef5.element == 4294967295L ? realBufferedSource.readLongLe() : 0L;
                    Ref$LongRef ref$LongRef6 = ref$LongRef3;
                    ref$LongRef6.element = ref$LongRef6.element == 4294967295L ? realBufferedSource.readLongLe() : 0L;
                }
                return Unit.INSTANCE;
            }
        });
        if (j2 > 0 && !ref$BooleanRef.element) {
            throw new IOException("bad zip: zip64 extra required but absent");
        }
        realBufferedSource.readUtf8(readShortLe7);
        Path.Companion companion = Path.Companion;
        return new ZipEntry(Path.Companion.get("/", false).resolve(readUtf8), readUtf8.endsWith("/"), ref$LongRef.element, ref$LongRef2.element, readShortLe2, l, ref$LongRef3.element);
    }

    public static final void readExtra(RealBufferedSource realBufferedSource, int i, Function2 function2) {
        long j = i;
        while (j != 0) {
            if (j < 4) {
                throw new IOException("bad zip: truncated header in extra field");
            }
            int readShortLe = realBufferedSource.readShortLe() & 65535;
            long readShortLe2 = realBufferedSource.readShortLe() & 65535;
            long j2 = j - 4;
            if (j2 < readShortLe2) {
                throw new IOException("bad zip: truncated value in extra field");
            }
            realBufferedSource.require(readShortLe2);
            long j3 = realBufferedSource.bufferField.size;
            function2.invoke(Integer.valueOf(readShortLe), Long.valueOf(readShortLe2));
            Buffer buffer = realBufferedSource.bufferField;
            long j4 = (buffer.size + readShortLe2) - j3;
            if (j4 < 0) {
                throw new IOException(AnnotationValue$1$$ExternalSyntheticOutline0.m(readShortLe, "unsupported zip: too many bytes processed for "));
            }
            if (j4 > 0) {
                buffer.skip(j4);
            }
            j = j2 - readShortLe2;
        }
    }

    public static final FileMetadata readOrSkipLocalHeader(final RealBufferedSource realBufferedSource, FileMetadata fileMetadata) {
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ref$ObjectRef.element = fileMetadata != null ? fileMetadata.lastModifiedAtMillis : null;
        final Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
        final Ref$ObjectRef ref$ObjectRef3 = new Ref$ObjectRef();
        int readIntLe = realBufferedSource.readIntLe();
        if (readIntLe != 67324752) {
            throw new IOException(GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("bad zip: expected ", getHex(67324752), " but was ", getHex(readIntLe)));
        }
        realBufferedSource.skip(2L);
        short readShortLe = realBufferedSource.readShortLe();
        int i = readShortLe & 65535;
        if ((readShortLe & 1) != 0) {
            throw new IOException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("unsupported zip: general purpose bit flag=", getHex(i)));
        }
        realBufferedSource.skip(18L);
        int readShortLe2 = realBufferedSource.readShortLe() & 65535;
        realBufferedSource.skip(realBufferedSource.readShortLe() & 65535);
        if (fileMetadata == null) {
            realBufferedSource.skip(readShortLe2);
            return null;
        }
        readExtra(realBufferedSource, readShortLe2, new Function2() { // from class: okio.internal.ZipFilesKt$readOrSkipLocalHeader$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                int intValue = ((Number) obj).intValue();
                long longValue = ((Number) obj2).longValue();
                if (intValue == 21589) {
                    if (longValue < 1) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    byte readByte = realBufferedSource.readByte();
                    boolean z = (readByte & 1) == 1;
                    boolean z2 = (readByte & 2) == 2;
                    boolean z3 = (readByte & 4) == 4;
                    BufferedSource bufferedSource = realBufferedSource;
                    long j = z ? 5L : 1L;
                    if (z2) {
                        j += 4;
                    }
                    if (z3) {
                        j += 4;
                    }
                    if (longValue < j) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    if (z) {
                        ref$ObjectRef.element = Long.valueOf(bufferedSource.readIntLe() * 1000);
                    }
                    if (z2) {
                        ref$ObjectRef2.element = Long.valueOf(realBufferedSource.readIntLe() * 1000);
                    }
                    if (z3) {
                        ref$ObjectRef3.element = Long.valueOf(realBufferedSource.readIntLe() * 1000);
                    }
                }
                return Unit.INSTANCE;
            }
        });
        return new FileMetadata(fileMetadata.isRegularFile, fileMetadata.isDirectory, fileMetadata.size, (Long) ref$ObjectRef3.element, (Long) ref$ObjectRef.element, (Long) ref$ObjectRef2.element);
    }
}
