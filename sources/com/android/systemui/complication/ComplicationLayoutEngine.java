package com.android.systemui.complication;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.touch.TouchInsetManager;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ComplicationLayoutEngine {
    public final int mFadeInDuration;
    public final int mFadeOutDuration;
    public final ConstraintLayout mLayout;
    public final HashMap mEntries = new HashMap();
    public final HashMap mPositions = new HashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Margins {
        public Margins(int i, int i2, int i3, int i4) {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class ViewEntry implements Comparable {
    }

    public ComplicationLayoutEngine(ConstraintLayout constraintLayout, int i, int i2, int i3, int i4, int i5, TouchInsetManager.TouchInsetSession touchInsetSession, int i6, int i7) {
        this.mLayout = constraintLayout;
        this.mFadeInDuration = i6;
        this.mFadeOutDuration = i7;
        HashMap hashMap = new HashMap();
        Margins margins = new Margins(i2, 0, 0, 0);
        Margins margins2 = new Margins(0, i3, 0, 0);
        Margins margins3 = new Margins(0, 0, i4, 0);
        Margins margins4 = new Margins(0, 0, 0, i5);
        addToMapping(hashMap, 5, 8, margins2);
        addToMapping(hashMap, 5, 2, margins);
        addToMapping(hashMap, 6, 8, margins4);
        addToMapping(hashMap, 6, 1, margins);
        addToMapping(hashMap, 9, 4, margins2);
        addToMapping(hashMap, 9, 2, margins3);
        addToMapping(hashMap, 10, 4, margins4);
        addToMapping(hashMap, 10, 1, margins3);
    }

    public static void addToMapping(HashMap hashMap, int i, int i2, Margins margins) {
        if (!hashMap.containsKey(Integer.valueOf(i))) {
            hashMap.put(Integer.valueOf(i), new HashMap());
        }
        ((HashMap) hashMap.get(Integer.valueOf(i))).put(Integer.valueOf(i2), margins);
    }
}
