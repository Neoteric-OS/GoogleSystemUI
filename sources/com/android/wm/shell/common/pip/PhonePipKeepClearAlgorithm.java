package com.android.wm.shell.common.pip;

import android.graphics.Rect;
import android.util.ArraySet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PhonePipKeepClearAlgorithm {
    public int mImeOffset;
    public boolean mKeepClearAreaGravityEnabled;
    public int mKeepClearAreasPadding;

    public static boolean tryOffset(Rect rect, Rect rect2, Rect rect3, int i, int i2) {
        Rect rect4 = new Rect(rect);
        rect4.offset(i, i2);
        if (Rect.intersects(rect2, rect4) || !rect3.contains(rect4)) {
            return false;
        }
        rect.offsetTo(rect4.left, rect4.top);
        return true;
    }

    public final Rect findUnoccludedPosition(Rect rect, Set set, Set set2, Rect rect2) {
        ArraySet arraySet = (ArraySet) set;
        if (arraySet.isEmpty() && ((ArraySet) set2).isEmpty()) {
            return rect;
        }
        ArraySet arraySet2 = new ArraySet();
        if (!arraySet.isEmpty()) {
            arraySet2.addAll((Collection) arraySet);
        }
        ArraySet arraySet3 = (ArraySet) set2;
        if (!arraySet3.isEmpty()) {
            arraySet2.addAll((Collection) arraySet3);
        }
        Rect rect3 = new Rect(rect);
        Iterator it = arraySet2.iterator();
        while (it.hasNext()) {
            Rect rect4 = (Rect) it.next();
            Rect rect5 = new Rect(rect4);
            int i = -this.mKeepClearAreasPadding;
            rect5.inset(i, i);
            if (Rect.intersects(rect4, rect3) && !tryOffset(rect3, rect5, rect2, 0, rect5.top - rect3.bottom) && !tryOffset(rect3, rect5, rect2, rect5.left - rect3.right, 0) && !tryOffset(rect3, rect5, rect2, 0, rect5.bottom - rect3.top)) {
                tryOffset(rect3, rect5, rect2, rect5.right - rect3.left, 0);
            }
        }
        return rect3;
    }
}
