package okio;

import androidx.core.animation.ValueAnimator$$ExternalSyntheticOutline0;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RealBufferedSource implements BufferedSource {
    public final Buffer bufferField = new Buffer();
    public boolean closed;
    public final Source source;

    public RealBufferedSource(Source source) {
        this.source = source;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public final void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.source.close();
        Buffer buffer = this.bufferField;
        buffer.skip(buffer.size);
    }

    @Override // okio.BufferedSource
    public final Buffer getBuffer() {
        return this.bufferField;
    }

    public final long indexOfElement(ByteString byteString) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        long j = 0;
        while (true) {
            long indexOfElement = this.bufferField.indexOfElement(byteString, j);
            if (indexOfElement != -1) {
                return indexOfElement;
            }
            Buffer buffer = this.bufferField;
            long j2 = buffer.size;
            if (this.source.read(buffer, 8192L) == -1) {
                return -1L;
            }
            j = Math.max(j, j2);
        }
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.closed;
    }

    @Override // okio.Source
    public final long read(Buffer buffer, long j) {
        if (j < 0) {
            throw new IllegalArgumentException(ValueAnimator$$ExternalSyntheticOutline0.m(j, "byteCount < 0: ").toString());
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        Buffer buffer2 = this.bufferField;
        if (buffer2.size == 0 && this.source.read(buffer2, 8192L) == -1) {
            return -1L;
        }
        return this.bufferField.read(buffer, Math.min(j, this.bufferField.size));
    }

    @Override // okio.BufferedSource
    public final byte readByte() {
        require(1L);
        return this.bufferField.readByte();
    }

    @Override // okio.BufferedSource
    public final int readIntLe() {
        require(4L);
        return this.bufferField.readIntLe();
    }

    @Override // okio.BufferedSource
    public final long readLongLe() {
        require(8L);
        return this.bufferField.readLongLe();
    }

    public final short readShortLe() {
        short s;
        require(2L);
        Buffer buffer = this.bufferField;
        if (buffer.size < 2) {
            throw new EOFException();
        }
        Segment segment = buffer.head;
        Intrinsics.checkNotNull(segment);
        int i = segment.pos;
        int i2 = segment.limit;
        if (i2 - i < 2) {
            s = (short) ((buffer.readByte() & 255) | ((buffer.readByte() & 255) << 8));
        } else {
            int i3 = i + 1;
            byte[] bArr = segment.data;
            int i4 = (bArr[i] & 255) << 8;
            int i5 = i + 2;
            int i6 = (bArr[i3] & 255) | i4;
            buffer.size -= 2;
            if (i5 == i2) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i5;
            }
            s = (short) i6;
        }
        return (short) (((s & 255) << 8) | ((65280 & s) >>> 8));
    }

    public final String readUtf8(long j) {
        require(j);
        Buffer buffer = this.bufferField;
        buffer.getClass();
        return buffer.readString(j, Charsets.UTF_8);
    }

    @Override // okio.BufferedSource
    public final boolean request(long j) {
        Buffer buffer;
        if (j < 0) {
            throw new IllegalArgumentException(ValueAnimator$$ExternalSyntheticOutline0.m(j, "byteCount < 0: ").toString());
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        do {
            buffer = this.bufferField;
            if (buffer.size >= j) {
                return true;
            }
        } while (this.source.read(buffer, 8192L) != -1);
        return false;
    }

    public final void require(long j) {
        if (!request(j)) {
            throw new EOFException();
        }
    }

    public final void skip(long j) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            Buffer buffer = this.bufferField;
            if (buffer.size == 0 && this.source.read(buffer, 8192L) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, this.bufferField.size);
            this.bufferField.skip(min);
            j -= min;
        }
    }

    public final String toString() {
        return "buffer(" + this.source + ")";
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        Buffer buffer = this.bufferField;
        if (buffer.size == 0 && this.source.read(buffer, 8192L) == -1) {
            return -1;
        }
        return this.bufferField.read(byteBuffer);
    }
}
