package okio;

import java.util.ArrayList;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FileMetadata {
    public final Long createdAtMillis;
    public final Map extras;
    public final boolean isDirectory;
    public final boolean isRegularFile;
    public final Long lastAccessedAtMillis;
    public final Long lastModifiedAtMillis;
    public final Long size;

    public FileMetadata(boolean z, boolean z2, Long l, Long l2, Long l3, Long l4) {
        Map emptyMap = MapsKt.emptyMap();
        this.isRegularFile = z;
        this.isDirectory = z2;
        this.size = l;
        this.createdAtMillis = l2;
        this.lastModifiedAtMillis = l3;
        this.lastAccessedAtMillis = l4;
        this.extras = MapsKt.toMap(emptyMap);
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        if (this.isRegularFile) {
            arrayList.add("isRegularFile");
        }
        if (this.isDirectory) {
            arrayList.add("isDirectory");
        }
        Long l = this.size;
        if (l != null) {
            arrayList.add("byteCount=" + l);
        }
        Long l2 = this.createdAtMillis;
        if (l2 != null) {
            arrayList.add("createdAt=" + l2);
        }
        Long l3 = this.lastModifiedAtMillis;
        if (l3 != null) {
            arrayList.add("lastModifiedAt=" + l3);
        }
        Long l4 = this.lastAccessedAtMillis;
        if (l4 != null) {
            arrayList.add("lastAccessedAt=" + l4);
        }
        if (!this.extras.isEmpty()) {
            arrayList.add("extras=" + this.extras);
        }
        return CollectionsKt.joinToString$default(arrayList, ", ", "FileMetadata(", ")", null, 56);
    }
}
