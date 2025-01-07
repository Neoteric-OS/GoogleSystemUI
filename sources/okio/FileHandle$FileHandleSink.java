package okio;

import java.io.Closeable;
import java.io.Flushable;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FileHandle$FileHandleSink implements Closeable, Flushable {
    public boolean closed;
    public final JvmFileHandle fileHandle;
    public long position = 0;

    public FileHandle$FileHandleSink(JvmFileHandle jvmFileHandle) {
        this.fileHandle = jvmFileHandle;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        ReentrantLock reentrantLock = this.fileHandle.lock;
        reentrantLock.lock();
        try {
            JvmFileHandle jvmFileHandle = this.fileHandle;
            int i = jvmFileHandle.openStreamCount - 1;
            jvmFileHandle.openStreamCount = i;
            if (i == 0) {
                if (jvmFileHandle.closed) {
                    reentrantLock.unlock();
                    JvmFileHandle jvmFileHandle2 = this.fileHandle;
                    synchronized (jvmFileHandle2) {
                        jvmFileHandle2.randomAccessFile.close();
                    }
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.io.Flushable
    public final void flush() {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        JvmFileHandle jvmFileHandle = this.fileHandle;
        synchronized (jvmFileHandle) {
            jvmFileHandle.randomAccessFile.getFD().sync();
        }
    }

    public final void write(Buffer buffer, long j) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        JvmFileHandle jvmFileHandle = this.fileHandle;
        long j2 = this.position;
        jvmFileHandle.getClass();
        SegmentedByteString.checkOffsetAndCount(buffer.size, 0L, j);
        long j3 = j2 + j;
        while (j2 < j3) {
            Segment segment = buffer.head;
            Intrinsics.checkNotNull(segment);
            int min = (int) Math.min(j3 - j2, segment.limit - segment.pos);
            byte[] bArr = segment.data;
            int i = segment.pos;
            synchronized (jvmFileHandle) {
                jvmFileHandle.randomAccessFile.seek(j2);
                jvmFileHandle.randomAccessFile.write(bArr, i, min);
            }
            int i2 = segment.pos + min;
            segment.pos = i2;
            long j4 = min;
            j2 += j4;
            buffer.size -= j4;
            if (i2 == segment.limit) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            }
        }
        this.position += j;
    }
}
