package okio;

import okio.Path;
import okio.internal.ResourceFileSystem;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class FileSystem {
    public static final JvmSystemFileSystem SYSTEM;

    static {
        JvmSystemFileSystem jvmSystemFileSystem;
        try {
            Class.forName("java.nio.file.Files");
            jvmSystemFileSystem = new NioSystemFileSystem();
        } catch (ClassNotFoundException unused) {
            jvmSystemFileSystem = new JvmSystemFileSystem();
        }
        SYSTEM = jvmSystemFileSystem;
        Path.Companion companion = Path.Companion;
        Path.Companion.get(System.getProperty("java.io.tmpdir"), false);
        new ResourceFileSystem(ResourceFileSystem.class.getClassLoader());
    }

    public final boolean exists(Path path) {
        return metadataOrNull(path) != null;
    }

    public abstract FileMetadata metadataOrNull(Path path);
}
