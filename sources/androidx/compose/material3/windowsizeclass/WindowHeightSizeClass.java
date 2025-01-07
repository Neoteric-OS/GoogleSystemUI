package androidx.compose.material3.windowsizeclass;

import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowHeightSizeClass implements Comparable {
    public static final List AllSizeClassList;
    public static final Set DefaultSizeClasses;
    public final int value;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        /* renamed from: breakpoint-sr04XMo, reason: not valid java name */
        public static float m250breakpointsr04XMo(int i) {
            return WindowHeightSizeClass.m248equalsimpl0(i, 2) ? 900 : WindowHeightSizeClass.m248equalsimpl0(i, 1) ? 480 : 0;
        }
    }

    static {
        int i = 0;
        int i2 = 1;
        int i3 = 2;
        DefaultSizeClasses = SetsKt.setOf(new WindowHeightSizeClass(i), new WindowHeightSizeClass(i2), new WindowHeightSizeClass(i3));
        List listOf = CollectionsKt__CollectionsKt.listOf(new WindowHeightSizeClass(i3), new WindowHeightSizeClass(i2), new WindowHeightSizeClass(i));
        AllSizeClassList = listOf;
        CollectionsKt.toSet(listOf);
    }

    public /* synthetic */ WindowHeightSizeClass(int i) {
        this.value = i;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m248equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m249toStringimpl(int i) {
        return "WindowHeightSizeClass.".concat(m248equalsimpl0(i, 0) ? "Compact" : m248equalsimpl0(i, 1) ? "Medium" : m248equalsimpl0(i, 2) ? "Expanded" : "");
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Float.compare(Companion.m250breakpointsr04XMo(this.value), Companion.m250breakpointsr04XMo(((WindowHeightSizeClass) obj).value));
    }

    public final boolean equals(Object obj) {
        return (obj instanceof WindowHeightSizeClass) && this.value == ((WindowHeightSizeClass) obj).value;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m249toStringimpl(this.value);
    }
}
