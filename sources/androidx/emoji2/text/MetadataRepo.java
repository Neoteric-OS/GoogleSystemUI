package androidx.emoji2.text;

import android.graphics.Typeface;
import android.util.SparseArray;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.flatbuffer.MetadataItem;
import androidx.emoji2.text.flatbuffer.MetadataList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MetadataRepo {
    public final char[] mEmojiCharArray;
    public final MetadataList mMetadataList;
    public final Node mRootNode = new Node(1024);
    public final Typeface mTypeface;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Node {
        public final SparseArray mChildren;
        public TypefaceEmojiRasterizer mData;

        public Node(int i) {
            this.mChildren = new SparseArray(i);
        }

        public final void put(TypefaceEmojiRasterizer typefaceEmojiRasterizer, int i, int i2) {
            int codepointAt = typefaceEmojiRasterizer.getCodepointAt(i);
            SparseArray sparseArray = this.mChildren;
            Node node = sparseArray == null ? null : (Node) sparseArray.get(codepointAt);
            if (node == null) {
                node = new Node(1);
                this.mChildren.put(typefaceEmojiRasterizer.getCodepointAt(i), node);
            }
            if (i2 > i) {
                node.put(typefaceEmojiRasterizer, i + 1, i2);
            } else {
                node.mData = typefaceEmojiRasterizer;
            }
        }
    }

    public MetadataRepo(Typeface typeface, MetadataList metadataList) {
        int i;
        int i2;
        this.mTypeface = typeface;
        this.mMetadataList = metadataList;
        int __offset = metadataList.__offset(6);
        if (__offset != 0) {
            int i3 = __offset + metadataList.bb_pos;
            i = metadataList.bb.getInt(metadataList.bb.getInt(i3) + i3);
        } else {
            i = 0;
        }
        this.mEmojiCharArray = new char[i * 2];
        int __offset2 = metadataList.__offset(6);
        if (__offset2 != 0) {
            int i4 = __offset2 + metadataList.bb_pos;
            i2 = metadataList.bb.getInt(metadataList.bb.getInt(i4) + i4);
        } else {
            i2 = 0;
        }
        for (int i5 = 0; i5 < i2; i5++) {
            TypefaceEmojiRasterizer typefaceEmojiRasterizer = new TypefaceEmojiRasterizer(this, i5);
            MetadataItem metadataItem = typefaceEmojiRasterizer.getMetadataItem();
            int __offset3 = metadataItem.__offset(4);
            Character.toChars(__offset3 != 0 ? metadataItem.bb.getInt(__offset3 + metadataItem.bb_pos) : 0, this.mEmojiCharArray, i5 * 2);
            Preconditions.checkArgument("invalid metadata codepoint length", typefaceEmojiRasterizer.getCodepointsLength() > 0);
            this.mRootNode.put(typefaceEmojiRasterizer, 0, typefaceEmojiRasterizer.getCodepointsLength() - 1);
        }
    }
}
