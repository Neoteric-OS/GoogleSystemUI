package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Table {
    public ByteBuffer bb;
    public int bb_pos;
    public int vtable_size;
    public int vtable_start;

    public Table() {
        if (Utf8Safe.DEFAULT == null) {
            Utf8Safe.DEFAULT = new Utf8Safe();
        }
    }

    public final int __offset(int i) {
        if (i < this.vtable_size) {
            return this.bb.getShort(this.vtable_start + i);
        }
        return 0;
    }
}
