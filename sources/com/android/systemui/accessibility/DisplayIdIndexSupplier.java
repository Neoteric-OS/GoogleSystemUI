package com.android.systemui.accessibility;

import android.hardware.display.DisplayManager;
import android.util.SparseArray;
import android.view.Display;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DisplayIdIndexSupplier {
    public final DisplayManager mDisplayManager;
    public final SparseArray mSparseArray = new SparseArray();

    public DisplayIdIndexSupplier(DisplayManager displayManager) {
        this.mDisplayManager = displayManager;
    }

    public abstract Object createInstance(Display display);

    public final Object get(int i) {
        Object obj = this.mSparseArray.get(i);
        if (obj != null) {
            return obj;
        }
        Display display = this.mDisplayManager.getDisplay(i);
        if (display == null) {
            return null;
        }
        Object createInstance = createInstance(display);
        this.mSparseArray.put(i, createInstance);
        return createInstance;
    }
}
