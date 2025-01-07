package androidx.compose.material3.windowsizeclass;

import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowSizeClass {
    public final int heightSizeClass;
    public final int widthSizeClass;

    public WindowSizeClass(int i, int i2) {
        this.widthSizeClass = i;
        this.heightSizeClass = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || WindowSizeClass.class != obj.getClass()) {
            return false;
        }
        WindowSizeClass windowSizeClass = (WindowSizeClass) obj;
        return WindowWidthSizeClass.m251equalsimpl0(this.widthSizeClass, windowSizeClass.widthSizeClass) && WindowHeightSizeClass.m248equalsimpl0(this.heightSizeClass, windowSizeClass.heightSizeClass);
    }

    public final int hashCode() {
        Set set = WindowWidthSizeClass.DefaultSizeClasses;
        int hashCode = Integer.hashCode(this.widthSizeClass) * 31;
        Set set2 = WindowHeightSizeClass.DefaultSizeClasses;
        return Integer.hashCode(this.heightSizeClass) + hashCode;
    }

    public final String toString() {
        return "WindowSizeClass(" + ((Object) WindowWidthSizeClass.m252toStringimpl(this.widthSizeClass)) + ", " + ((Object) WindowHeightSizeClass.m249toStringimpl(this.heightSizeClass)) + ')';
    }
}
