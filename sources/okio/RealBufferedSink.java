package okio;

import java.io.Closeable;
import java.io.Flushable;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RealBufferedSink implements Closeable, Flushable, WritableByteChannel {
    public final Buffer bufferField = new Buffer();
    public boolean closed;
    public final FileHandle$FileHandleSink sink;

    public RealBufferedSink(FileHandle$FileHandleSink fileHandle$FileHandleSink) {
        this.sink = fileHandle$FileHandleSink;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public final void close() {
        if (this.closed) {
            return;
        }
        try {
            Buffer buffer = this.bufferField;
            long j = buffer.size;
            if (j > 0) {
                this.sink.write(buffer, j);
            }
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            this.sink.close();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        this.closed = true;
        if (th != null) {
            throw th;
        }
    }

    public final void emitCompleteSegments() {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        Buffer buffer = this.bufferField;
        long j = buffer.size;
        if (j == 0) {
            j = 0;
        } else {
            Segment segment = buffer.head;
            Intrinsics.checkNotNull(segment);
            Segment segment2 = segment.prev;
            Intrinsics.checkNotNull(segment2);
            if (segment2.limit < 8192 && segment2.owner) {
                j -= r5 - segment2.pos;
            }
        }
        if (j > 0) {
            this.sink.write(this.bufferField, j);
        }
    }

    @Override // java.io.Flushable
    public final void flush() {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        Buffer buffer = this.bufferField;
        long j = buffer.size;
        if (j > 0) {
            this.sink.write(buffer, j);
        }
        this.sink.flush();
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.closed;
    }

    public final String toString() {
        return "buffer(" + this.sink + ")";
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        int write = this.bufferField.write(byteBuffer);
        emitCompleteSegments();
        return write;
    }
}
