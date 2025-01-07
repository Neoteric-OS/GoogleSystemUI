package okio;

import androidx.core.animation.ValueAnimator$$ExternalSyntheticOutline0;
import java.io.Closeable;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class JvmFileHandle implements Closeable {
    public boolean closed;
    public final ReentrantLock lock = new ReentrantLock();
    public int openStreamCount;
    public final RandomAccessFile randomAccessFile;
    public final boolean readWrite;

    public JvmFileHandle(boolean z, RandomAccessFile randomAccessFile) {
        this.readWrite = z;
        this.randomAccessFile = randomAccessFile;
    }

    public static FileHandle$FileHandleSink sink$default(JvmFileHandle jvmFileHandle) {
        if (!jvmFileHandle.readWrite) {
            throw new IllegalStateException("file handle is read-only");
        }
        ReentrantLock reentrantLock = jvmFileHandle.lock;
        reentrantLock.lock();
        try {
            if (jvmFileHandle.closed) {
                throw new IllegalStateException("closed");
            }
            jvmFileHandle.openStreamCount++;
            reentrantLock.unlock();
            return new FileHandle$FileHandleSink(jvmFileHandle);
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.openStreamCount != 0) {
                return;
            }
            synchronized (this) {
                this.randomAccessFile.close();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void flush() {
        if (!this.readWrite) {
            throw new IllegalStateException("file handle is read-only");
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            synchronized (this) {
                this.randomAccessFile.getFD().sync();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public final long size() {
        long length;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            synchronized (this) {
                length = this.randomAccessFile.length();
            }
            return length;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [okio.FileHandle$FileHandleSource] */
    public final FileHandle$FileHandleSource source(final long j) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            this.openStreamCount++;
            reentrantLock.unlock();
            return new Source(this, j) { // from class: okio.FileHandle$FileHandleSource
                public boolean closed;
                public final JvmFileHandle fileHandle;
                public long position;

                {
                    this.fileHandle = this;
                    this.position = j;
                }

                @Override // java.io.Closeable, java.lang.AutoCloseable
                public final void close() {
                    if (this.closed) {
                        return;
                    }
                    this.closed = true;
                    ReentrantLock reentrantLock2 = this.fileHandle.lock;
                    reentrantLock2.lock();
                    try {
                        JvmFileHandle jvmFileHandle = this.fileHandle;
                        int i = jvmFileHandle.openStreamCount - 1;
                        jvmFileHandle.openStreamCount = i;
                        if (i == 0) {
                            if (jvmFileHandle.closed) {
                                reentrantLock2.unlock();
                                JvmFileHandle jvmFileHandle2 = this.fileHandle;
                                synchronized (jvmFileHandle2) {
                                    jvmFileHandle2.randomAccessFile.close();
                                }
                            }
                        }
                    } finally {
                        reentrantLock2.unlock();
                    }
                }

                @Override // okio.Source
                public final long read(Buffer buffer, long j2) {
                    long j3;
                    long j4;
                    int i;
                    int i2;
                    if (this.closed) {
                        throw new IllegalStateException("closed");
                    }
                    JvmFileHandle jvmFileHandle = this.fileHandle;
                    long j5 = this.position;
                    jvmFileHandle.getClass();
                    if (j2 < 0) {
                        throw new IllegalArgumentException(ValueAnimator$$ExternalSyntheticOutline0.m(j2, "byteCount < 0: ").toString());
                    }
                    long j6 = j2 + j5;
                    long j7 = j5;
                    while (true) {
                        if (j7 >= j6) {
                            break;
                        }
                        Segment writableSegment$external__okio__android_common__okio_lib = buffer.writableSegment$external__okio__android_common__okio_lib(1);
                        byte[] bArr = writableSegment$external__okio__android_common__okio_lib.data;
                        int i3 = writableSegment$external__okio__android_common__okio_lib.limit;
                        int min = (int) Math.min(j6 - j7, 8192 - i3);
                        synchronized (jvmFileHandle) {
                            jvmFileHandle.randomAccessFile.seek(j7);
                            i = 0;
                            while (true) {
                                if (i >= min) {
                                    break;
                                }
                                int read = jvmFileHandle.randomAccessFile.read(bArr, i3, min - i);
                                if (read != -1) {
                                    i += read;
                                } else if (i == 0) {
                                    i2 = -1;
                                    i = -1;
                                }
                            }
                            i2 = -1;
                        }
                        if (i == i2) {
                            if (writableSegment$external__okio__android_common__okio_lib.pos == writableSegment$external__okio__android_common__okio_lib.limit) {
                                buffer.head = writableSegment$external__okio__android_common__okio_lib.pop();
                                SegmentPool.recycle(writableSegment$external__okio__android_common__okio_lib);
                            }
                            if (j5 == j7) {
                                j4 = -1;
                                j3 = -1;
                            }
                        } else {
                            writableSegment$external__okio__android_common__okio_lib.limit += i;
                            long j8 = i;
                            j7 += j8;
                            buffer.size += j8;
                        }
                    }
                    j3 = j7 - j5;
                    j4 = -1;
                    if (j3 != j4) {
                        this.position += j3;
                    }
                    return j3;
                }
            };
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }
}
