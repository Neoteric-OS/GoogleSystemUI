package kotlin.random;

import java.io.Serializable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class XorWowRandom extends Random implements Serializable {
    private static final long serialVersionUID = 0;
    private int addend;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    public XorWowRandom(int i, int i2) {
        int i3 = ~i;
        this.x = i;
        this.y = i2;
        this.z = 0;
        this.w = 0;
        this.v = i3;
        this.addend = (i << 10) ^ (i2 >>> 4);
        if ((i | i2 | i3) == 0) {
            throw new IllegalArgumentException("Initial state must have at least one non-zero element.");
        }
        for (int i4 = 0; i4 < 64; i4++) {
            int i5 = this.x;
            int i6 = i5 ^ (i5 >>> 2);
            this.x = this.y;
            this.y = this.z;
            this.z = this.w;
            int i7 = this.v;
            this.w = i7;
            this.v = ((i6 ^ (i6 << 1)) ^ i7) ^ (i7 << 4);
            this.addend += 362437;
        }
    }
}
