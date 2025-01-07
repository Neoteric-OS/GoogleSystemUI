package okio;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RealBufferedSource$inputStream$1 extends InputStream {
    public final /* synthetic */ RealBufferedSource this$0;

    public RealBufferedSource$inputStream$1(RealBufferedSource realBufferedSource) {
        this.this$0 = realBufferedSource;
    }

    @Override // java.io.InputStream
    public final int available() {
        RealBufferedSource realBufferedSource = this.this$0;
        if (realBufferedSource.closed) {
            throw new IOException("closed");
        }
        return (int) Math.min(realBufferedSource.bufferField.size, Integer.MAX_VALUE);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.this$0.close();
    }

    @Override // java.io.InputStream
    public final int read() {
        RealBufferedSource realBufferedSource = this.this$0;
        if (realBufferedSource.closed) {
            throw new IOException("closed");
        }
        Buffer buffer = realBufferedSource.bufferField;
        if (buffer.size == 0 && realBufferedSource.source.read(buffer, 8192L) == -1) {
            return -1;
        }
        return this.this$0.bufferField.readByte() & 255;
    }

    public final String toString() {
        return this.this$0 + ".inputStream()";
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        if (!this.this$0.closed) {
            SegmentedByteString.checkOffsetAndCount(bArr.length, i, i2);
            RealBufferedSource realBufferedSource = this.this$0;
            Buffer buffer = realBufferedSource.bufferField;
            if (buffer.size == 0 && realBufferedSource.source.read(buffer, 8192L) == -1) {
                return -1;
            }
            return this.this$0.bufferField.read(bArr, i, i2);
        }
        throw new IOException("closed");
    }
}
