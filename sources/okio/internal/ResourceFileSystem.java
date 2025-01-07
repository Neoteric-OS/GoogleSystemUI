package okio.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import okio.FileMetadata;
import okio.FileSystem;
import okio.JvmSystemFileSystem;
import okio.Path;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ResourceFileSystem extends FileSystem {
    public static final Companion Companion = null;
    public static final Path ROOT;
    public final ClassLoader classLoader;
    public final JvmSystemFileSystem systemFileSystem = FileSystem.SYSTEM;
    public final Lazy roots$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: okio.internal.ResourceFileSystem$roots$2
        {
            super(0);
        }

        /* JADX WARN: Code restructure failed: missing block: B:103:0x01e4, code lost:
        
            throw new java.io.IOException("bad zip: expected " + okio.internal.ZipFilesKt.getHex(101075792) + " but was " + okio.internal.ZipFilesKt.getHex(r7));
         */
        /* JADX WARN: Code restructure failed: missing block: B:104:0x01e5, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:115:0x01f3, code lost:
        
            throw new java.io.IOException("unsupported zip: spanned");
         */
        /* JADX WARN: Code restructure failed: missing block: B:118:0x01f4, code lost:
        
            kotlin.io.CloseableKt.closeFinally(r10, null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:119:0x0206, code lost:
        
            r8 = r7.centralDirectoryOffset;
            r6 = new java.util.ArrayList();
            r11 = new okio.RealBufferedSource(r13.source(r8));
         */
        /* JADX WARN: Code restructure failed: missing block: B:121:0x0216, code lost:
        
            r14 = r7.entryCount;
            r22 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:123:0x021c, code lost:
        
            if (r22 >= r14) goto L197;
         */
        /* JADX WARN: Code restructure failed: missing block: B:124:0x021e, code lost:
        
            r7 = okio.internal.ZipFilesKt.readEntry(r11);
            r16 = r14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:125:0x0228, code lost:
        
            if (r7.offset >= r8) goto L187;
         */
        /* JADX WARN: Code restructure failed: missing block: B:127:0x0234, code lost:
        
            if (((java.lang.Boolean) r4.invoke(r7)).booleanValue() == false) goto L198;
         */
        /* JADX WARN: Code restructure failed: missing block: B:128:0x0236, code lost:
        
            r6.add(r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:130:0x023d, code lost:
        
            r22 = r22 + 1;
            r14 = r16;
         */
        /* JADX WARN: Code restructure failed: missing block: B:134:0x024b, code lost:
        
            throw new java.io.IOException("bad zip: local file header offset >= central directory offset");
         */
        /* JADX WARN: Code restructure failed: missing block: B:138:0x024d, code lost:
        
            kotlin.io.CloseableKt.closeFinally(r11, null);
            r6 = new okio.ZipFileSystem(r2, r5, okio.internal.ZipFilesKt.buildIndex(r6));
         */
        /* JADX WARN: Code restructure failed: missing block: B:139:0x0259, code lost:
        
            kotlin.io.CloseableKt.closeFinally(r13, null);
            r6 = new kotlin.Pair(r6, okio.internal.ResourceFileSystem.ROOT);
         */
        /* JADX WARN: Code restructure failed: missing block: B:140:0x023a, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:143:0x026b, code lost:
        
            throw r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:145:0x026c, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:147:0x026e, code lost:
        
            kotlin.io.CloseableKt.closeFinally(r11, r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:148:0x0271, code lost:
        
            throw r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:149:0x01b3, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:158:0x01f8, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:159:0x01f9, code lost:
        
            r1 = r0;
            r6 = r13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:160:0x0204, code lost:
        
            r13 = r24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:162:0x0272, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:163:0x0273, code lost:
        
            r13 = r24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:165:0x0276, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:166:0x0277, code lost:
        
            r13 = r24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:168:0x02a6, code lost:
        
            r15.close();
         */
        /* JADX WARN: Code restructure failed: missing block: B:169:0x02a9, code lost:
        
            throw r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:171:0x027b, code lost:
        
            r13 = r24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:174:0x0282, code lost:
        
            throw new java.io.IOException("unsupported zip: spanned");
         */
        /* JADX WARN: Code restructure failed: missing block: B:176:0x0283, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00ed, code lost:
        
            r6 = r15.readShortLe() & 65535;
            r13 = r15.readShortLe() & 65535;
            r11 = r15.readShortLe() & 65535;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x0104, code lost:
        
            r24 = r8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x010c, code lost:
        
            if (r11 != (r15.readShortLe() & 65535)) goto L180;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x010e, code lost:
        
            if (r6 != 0) goto L181;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x0110, code lost:
        
            if (r13 != 0) goto L182;
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:0x0114, code lost:
        
            r15.skip(4);
            r6 = r15.readShortLe() & 65535;
            r7 = new okio.internal.EocdRecord(r6, r11, r15.readIntLe() & 4294967295L);
            r15.readUtf8(r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x013a, code lost:
        
            r15.close();
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x013d, code lost:
        
            r9 = r9 - 20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x0145, code lost:
        
            if (r9 <= 0) goto L90;
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x0147, code lost:
        
            r13 = r24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x0149, code lost:
        
            r10 = new okio.RealBufferedSource(r13.source(r9));
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x0159, code lost:
        
            if (r10.readIntLe() != 117853008) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x015b, code lost:
        
            r7 = r10.readIntLe();
            r14 = r10.readLongLe();
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x0168, code lost:
        
            if (r10.readIntLe() != 1) goto L190;
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:0x016a, code lost:
        
            if (r7 != 0) goto L192;
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x016c, code lost:
        
            r9 = new okio.RealBufferedSource(r13.source(r14));
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x0175, code lost:
        
            r7 = r9.readIntLe();
         */
        /* JADX WARN: Code restructure failed: missing block: B:88:0x017c, code lost:
        
            if (r7 != 101075792) goto L193;
         */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x017e, code lost:
        
            r9.skip(12);
            r7 = r9.readIntLe();
            r11 = r9.readIntLe();
            r27 = r9.readLongLe();
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:0x0195, code lost:
        
            if (r27 != r9.readLongLe()) goto L184;
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x0197, code lost:
        
            if (r7 != 0) goto L185;
         */
        /* JADX WARN: Code restructure failed: missing block: B:92:0x0199, code lost:
        
            if (r11 != 0) goto L186;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x019b, code lost:
        
            r9.skip(8);
            r7 = new okio.internal.EocdRecord(r6, r27, r9.readLongLe());
         */
        /* JADX WARN: Code restructure failed: missing block: B:95:0x01ae, code lost:
        
            kotlin.io.CloseableKt.closeFinally(r9, null);
         */
        /* JADX WARN: Code restructure failed: missing block: B:98:0x01bb, code lost:
        
            throw new java.io.IOException("unsupported zip: spanned");
         */
        /* JADX WARN: Finally extract failed */
        @Override // kotlin.jvm.functions.Function0
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invoke() {
            /*
                Method dump skipped, instructions count: 720
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.internal.ResourceFileSystem$roots$2.invoke():java.lang.Object");
        }
    });

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final boolean access$keepPath(Path path) {
            Companion companion = ResourceFileSystem.Companion;
            String name = path.name();
            return !name.regionMatches(true, name.length() - 6, ".class", 0, 6);
        }
    }

    static {
        Path.Companion companion = Path.Companion;
        ROOT = Path.Companion.get("/", false);
    }

    public ResourceFileSystem(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override // okio.FileSystem
    public final FileMetadata metadataOrNull(Path path) {
        Path path2;
        if (!Companion.access$keepPath(path)) {
            return null;
        }
        Path path3 = ROOT;
        path3.getClass();
        Path commonResolve = Path.commonResolve(path3, path, true);
        commonResolve.getClass();
        int access$rootLength = Path.access$rootLength(commonResolve);
        Path path4 = access$rootLength == -1 ? null : new Path(commonResolve.bytes.substring(0, access$rootLength));
        path3.getClass();
        int access$rootLength2 = Path.access$rootLength(path3);
        if (!Intrinsics.areEqual(path4, access$rootLength2 != -1 ? new Path(path3.bytes.substring(0, access$rootLength2)) : null)) {
            throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + commonResolve + " and " + path3).toString());
        }
        List segmentsBytes = commonResolve.getSegmentsBytes();
        List segmentsBytes2 = path3.getSegmentsBytes();
        ArrayList arrayList = (ArrayList) segmentsBytes;
        ArrayList arrayList2 = (ArrayList) segmentsBytes2;
        int min = Math.min(arrayList.size(), arrayList2.size());
        int i = 0;
        while (i < min && Intrinsics.areEqual(arrayList.get(i), arrayList2.get(i))) {
            i++;
        }
        if (i == min && commonResolve.bytes.getSize$external__okio__android_common__okio_lib() == path3.bytes.getSize$external__okio__android_common__okio_lib()) {
            path2 = Path.Companion.get(".", false);
        } else {
            if (segmentsBytes2.subList(i, arrayList2.size()).indexOf(Path.DOT_DOT) != -1) {
                throw new IllegalArgumentException(("Impossible relative path to resolve: " + commonResolve + " and " + path3).toString());
            }
            Buffer buffer = new Buffer();
            ByteString slash = Path.getSlash(path3);
            if (slash == null && (slash = Path.getSlash(commonResolve)) == null) {
                slash = Path.toSlash(Path.DIRECTORY_SEPARATOR);
            }
            int size = arrayList2.size();
            for (int i2 = i; i2 < size; i2++) {
                buffer.write(Path.DOT_DOT);
                buffer.write(slash);
            }
            int size2 = arrayList.size();
            while (i < size2) {
                buffer.write((ByteString) arrayList.get(i));
                buffer.write(slash);
                i++;
            }
            path2 = Path.toPath(buffer, false);
        }
        String utf8 = path2.bytes.utf8();
        for (Pair pair : (List) this.roots$delegate.getValue()) {
            FileMetadata metadataOrNull = ((FileSystem) pair.component1()).metadataOrNull(((Path) pair.component2()).resolve(utf8));
            if (metadataOrNull != null) {
                return metadataOrNull;
            }
        }
        return null;
    }
}
