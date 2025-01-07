package okio;

import java.nio.channels.ReadableByteChannel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface BufferedSource extends Source, ReadableByteChannel {
    Buffer getBuffer();

    byte readByte();

    int readIntLe();

    long readLongLe();

    boolean request(long j);
}
