package com.android.systemui.complication;

import android.view.ViewGroup;
import java.util.HashMap;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ComplicationLayoutParams extends ViewGroup.LayoutParams {
    static {
        HashMap hashMap = new HashMap();
        hashMap.put(2, 2);
        hashMap.put(1, 1);
        hashMap.put(4, 4);
        hashMap.put(8, 8);
    }

    public static void iteratePositions(Consumer consumer) {
        for (int i = 1; i <= 8; i <<= 1) {
            if ((3 & i) == i) {
                consumer.accept(Integer.valueOf(i));
            }
        }
    }
}
