package okio;

import java.util.Map;
import kotlin.ExceptionsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Path;
import okio.internal.ZipEntry;
import okio.internal.ZipFilesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ZipFileSystem extends FileSystem {
    public static final Path ROOT;
    public final Map entries;
    public final JvmSystemFileSystem fileSystem;
    public final Path zipPath;

    static {
        Path.Companion companion = Path.Companion;
        ROOT = Path.Companion.get("/", false);
    }

    public ZipFileSystem(Path path, JvmSystemFileSystem jvmSystemFileSystem, Map map) {
        this.zipPath = path;
        this.fileSystem = jvmSystemFileSystem;
        this.entries = map;
    }

    @Override // okio.FileSystem
    public final FileMetadata metadataOrNull(Path path) {
        FileMetadata fileMetadata;
        Throwable th;
        Path path2 = ROOT;
        path2.getClass();
        ZipEntry zipEntry = (ZipEntry) this.entries.get(okio.internal.Path.commonResolve(path2, path, true));
        Throwable th2 = null;
        if (zipEntry == null) {
            return null;
        }
        boolean z = zipEntry.isDirectory;
        FileMetadata fileMetadata2 = new FileMetadata(!z, z, z ? null : Long.valueOf(zipEntry.size), null, zipEntry.lastModifiedAtMillis, null);
        long j = zipEntry.offset;
        if (j == -1) {
            return fileMetadata2;
        }
        JvmFileHandle openReadOnly = this.fileSystem.openReadOnly(this.zipPath);
        try {
            RealBufferedSource realBufferedSource = new RealBufferedSource(openReadOnly.source(j));
            try {
                fileMetadata = ZipFilesKt.readOrSkipLocalHeader(realBufferedSource, fileMetadata2);
                Intrinsics.checkNotNull(fileMetadata);
                try {
                    realBufferedSource.close();
                    th = null;
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                try {
                    realBufferedSource.close();
                } catch (Throwable th5) {
                    ExceptionsKt.addSuppressed(th4, th5);
                }
                th = th4;
                fileMetadata = null;
            }
        } catch (Throwable th6) {
            try {
                openReadOnly.close();
            } catch (Throwable th7) {
                ExceptionsKt.addSuppressed(th6, th7);
            }
            th2 = th6;
            fileMetadata = null;
        }
        if (th != null) {
            throw th;
        }
        Intrinsics.checkNotNull(fileMetadata);
        try {
            openReadOnly.close();
        } catch (Throwable th8) {
            th2 = th8;
        }
        if (th2 != null) {
            throw th2;
        }
        Intrinsics.checkNotNull(fileMetadata);
        return fileMetadata;
    }
}
