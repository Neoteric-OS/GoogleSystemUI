package com.android.systemui.plugins;

import android.graphics.Rect;
import android.view.View;
import com.android.systemui.plugins.annotations.DependsOn;
import com.android.systemui.plugins.annotations.ProvidesInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
@ProvidesInterface(version = 2)
@DependsOn(target = DarkReceiver.class)
/* loaded from: classes.dex */
public interface DarkIconDispatcher {
    public static final int DEFAULT_ICON_TINT = -1;
    public static final int DEFAULT_INVERSE_ICON_TINT = -16777216;
    public static final int VERSION = 2;
    public static final Rect sTmpRect = new Rect();
    public static final int[] sTmpInt2 = new int[2];

    static int getInverseTint(Collection collection, View view, int i) {
        return isInAreas(collection, view) ? i : DEFAULT_INVERSE_ICON_TINT;
    }

    static int getTint(Collection collection, View view, int i) {
        if (isInAreas(collection, view)) {
            return i;
        }
        return -1;
    }

    static boolean isInArea(Rect rect, Rect rect2) {
        if (rect.isEmpty()) {
            return true;
        }
        sTmpRect.set(rect);
        int i = rect2.left;
        int width = rect2.width();
        return Math.max(0, Math.min(i + width, rect.right) - Math.max(i, rect.left)) * 2 > width && (rect.top <= 0);
    }

    static boolean isInAreas(Collection collection, View view) {
        if (collection.isEmpty()) {
            return true;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (isInArea((Rect) it.next(), view)) {
                return true;
            }
        }
        return false;
    }

    void addDarkReceiver(DarkReceiver darkReceiver);

    void applyDark(DarkReceiver darkReceiver);

    void removeDarkReceiver(DarkReceiver darkReceiver);

    void setIconsDarkArea(ArrayList arrayList);

    static boolean isInAreas(Collection collection, Rect rect) {
        if (collection.isEmpty()) {
            return true;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (isInArea((Rect) it.next(), rect)) {
                return true;
            }
        }
        return false;
    }

    static boolean isInArea(Rect rect, View view) {
        if (rect.isEmpty()) {
            return true;
        }
        sTmpRect.set(rect);
        int[] iArr = sTmpInt2;
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        return Math.max(0, Math.min(view.getWidth() + i, rect.right) - Math.max(i, rect.left)) * 2 > view.getWidth() && (rect.top <= 0);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    @ProvidesInterface(version = 3)
    public interface DarkReceiver {
        public static final int VERSION = 3;

        void onDarkChanged(ArrayList arrayList, float f, int i);

        default void onDarkChangedWithContrast(ArrayList arrayList, int i, int i2) {
        }
    }
}
