package androidx.compose.material3.windowsizeclass;

import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowWidthSizeClass implements Comparable {
    public static final List AllSizeClassList;
    public static final Set DefaultSizeClasses;
    public final int value;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        /* renamed from: breakpoint-fhkHA5s, reason: not valid java name */
        public static float m253breakpointfhkHA5s(int i) {
            return WindowWidthSizeClass.m251equalsimpl0(i, 2) ? 840 : WindowWidthSizeClass.m251equalsimpl0(i, 1) ? 600 : 0;
        }
    }

    static {
        int i = 0;
        int i2 = 1;
        int i3 = 2;
        DefaultSizeClasses = SetsKt.setOf(new WindowWidthSizeClass(i), new WindowWidthSizeClass(i2), new WindowWidthSizeClass(i3));
        List listOf = CollectionsKt__CollectionsKt.listOf(new WindowWidthSizeClass(i3), new WindowWidthSizeClass(i2), new WindowWidthSizeClass(i));
        AllSizeClassList = listOf;
        CollectionsKt.toSet(listOf);
    }

    public /* synthetic */ WindowWidthSizeClass(int i) {
        this.value = i;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m251equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m252toStringimpl(int i) {
        return "WindowWidthSizeClass.".concat(m251equalsimpl0(i, 0) ? "Compact" : m251equalsimpl0(i, 1) ? "Medium" : m251equalsimpl0(i, 2) ? "Expanded" : "");
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Float.compare(Companion.m253breakpointfhkHA5s(this.value), Companion.m253breakpointfhkHA5s(((WindowWidthSizeClass) obj).value));
    }

    public final boolean equals(Object obj) {
        return (obj instanceof WindowWidthSizeClass) && this.value == ((WindowWidthSizeClass) obj).value;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m252toStringimpl(this.value);
    }
}
