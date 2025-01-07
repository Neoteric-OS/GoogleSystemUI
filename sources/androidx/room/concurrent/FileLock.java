package androidx.room.concurrent;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FileLock {
    public FileChannel lockChannel;
    public final String lockFilename;

    public FileLock(String str) {
        this.lockFilename = str.concat(".lck");
    }

    public final void lock() {
        String str = this.lockFilename;
        if (this.lockChannel != null) {
            return;
        }
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            FileChannel channel = new FileOutputStream(file).getChannel();
            this.lockChannel = channel;
            if (channel != null) {
                channel.lock();
            }
        } catch (Throwable th) {
            FileChannel fileChannel = this.lockChannel;
            if (fileChannel != null) {
                fileChannel.close();
            }
            this.lockChannel = null;
            throw new IllegalStateException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Unable to lock file: '", str, "'."), th);
        }
    }
}
