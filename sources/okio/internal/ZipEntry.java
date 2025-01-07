package okio.internal;

import java.util.ArrayList;
import java.util.List;
import okio.Path;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ZipEntry {
    public final Path canonicalPath;
    public final List children;
    public final boolean isDirectory;
    public final Long lastModifiedAtMillis;
    public final long offset;
    public final long size;

    public /* synthetic */ ZipEntry(Path path) {
        this(path, true, -1L, -1L, -1, null, -1L);
    }

    public ZipEntry(Path path, boolean z, long j, long j2, int i, Long l, long j3) {
        this.canonicalPath = path;
        this.isDirectory = z;
        this.size = j2;
        this.lastModifiedAtMillis = l;
        this.offset = j3;
        this.children = new ArrayList();
    }
}
