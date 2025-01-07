package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.unit.Velocity;
import kotlin.coroutines.Continuation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface NestedScrollConnection {
    /* renamed from: onPostFling-RZ2iAVY */
    default Object mo69onPostFlingRZ2iAVY(long j, long j2, Continuation continuation) {
        return new Velocity(0L);
    }

    /* renamed from: onPostScroll-DzOQY0M */
    default long mo70onPostScrollDzOQY0M(int i, long j, long j2) {
        return 0L;
    }

    /* renamed from: onPreFling-QWom1Mo */
    default Object mo227onPreFlingQWom1Mo(long j, Continuation continuation) {
        return new Velocity(0L);
    }

    /* renamed from: onPreScroll-OzD1aCk */
    default long mo139onPreScrollOzD1aCk(long j, int i) {
        return 0L;
    }
}
