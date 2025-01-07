package androidx.core.content.res;

import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GradientColorInflaterCompat$ColorStops {
    public final int[] mColors;
    public final float[] mOffsets;

    public GradientColorInflaterCompat$ColorStops(List list, List list2) {
        ArrayList arrayList = (ArrayList) list;
        int size = arrayList.size();
        this.mColors = new int[size];
        this.mOffsets = new float[size];
        for (int i = 0; i < size; i++) {
            this.mColors[i] = ((Integer) arrayList.get(i)).intValue();
            this.mOffsets[i] = ((Float) ((ArrayList) list2).get(i)).floatValue();
        }
    }

    public GradientColorInflaterCompat$ColorStops(int i, int i2) {
        this.mColors = new int[]{i, i2};
        this.mOffsets = new float[]{0.0f, 1.0f};
    }

    public GradientColorInflaterCompat$ColorStops(int i, int i2, int i3) {
        this.mColors = new int[]{i, i2, i3};
        this.mOffsets = new float[]{0.0f, 0.5f, 1.0f};
    }
}
