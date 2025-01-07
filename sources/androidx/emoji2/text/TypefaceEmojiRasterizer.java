package androidx.emoji2.text;

import androidx.emoji2.text.flatbuffer.MetadataItem;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.nio.ByteBuffer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TypefaceEmojiRasterizer {
    public static final ThreadLocal sMetadataItem = new ThreadLocal();
    public volatile int mCache = 0;
    public final int mIndex;
    public final MetadataRepo mMetadataRepo;

    public TypefaceEmojiRasterizer(MetadataRepo metadataRepo, int i) {
        this.mMetadataRepo = metadataRepo;
        this.mIndex = i;
    }

    public final int getCodepointAt(int i) {
        MetadataItem metadataItem = getMetadataItem();
        int __offset = metadataItem.__offset(16);
        if (__offset == 0) {
            return 0;
        }
        ByteBuffer byteBuffer = metadataItem.bb;
        int i2 = __offset + metadataItem.bb_pos;
        return byteBuffer.getInt((i * 4) + byteBuffer.getInt(i2) + i2 + 4);
    }

    public final int getCodepointsLength() {
        MetadataItem metadataItem = getMetadataItem();
        int __offset = metadataItem.__offset(16);
        if (__offset == 0) {
            return 0;
        }
        int i = __offset + metadataItem.bb_pos;
        return metadataItem.bb.getInt(metadataItem.bb.getInt(i) + i);
    }

    public final MetadataItem getMetadataItem() {
        ThreadLocal threadLocal = sMetadataItem;
        MetadataItem metadataItem = (MetadataItem) threadLocal.get();
        if (metadataItem == null) {
            metadataItem = new MetadataItem();
            threadLocal.set(metadataItem);
        }
        MetadataList metadataList = this.mMetadataRepo.mMetadataList;
        int __offset = metadataList.__offset(6);
        if (__offset != 0) {
            int i = __offset + metadataList.bb_pos;
            int i2 = (this.mIndex * 4) + metadataList.bb.getInt(i) + i + 4;
            int i3 = metadataList.bb.getInt(i2) + i2;
            ByteBuffer byteBuffer = metadataList.bb;
            metadataItem.bb = byteBuffer;
            if (byteBuffer != null) {
                metadataItem.bb_pos = i3;
                int i4 = i3 - byteBuffer.getInt(i3);
                metadataItem.vtable_start = i4;
                metadataItem.vtable_size = metadataItem.bb.getShort(i4);
            } else {
                metadataItem.bb_pos = 0;
                metadataItem.vtable_start = 0;
                metadataItem.vtable_size = 0;
            }
        }
        return metadataItem;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", id:");
        MetadataItem metadataItem = getMetadataItem();
        int __offset = metadataItem.__offset(4);
        sb.append(Integer.toHexString(__offset != 0 ? metadataItem.bb.getInt(__offset + metadataItem.bb_pos) : 0));
        sb.append(", codepoints:");
        int codepointsLength = getCodepointsLength();
        for (int i = 0; i < codepointsLength; i++) {
            sb.append(Integer.toHexString(getCodepointAt(i)));
            sb.append(" ");
        }
        return sb.toString();
    }
}
