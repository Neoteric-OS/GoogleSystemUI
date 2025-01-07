package com.android.systemui.ambient.touch;

import com.android.systemui.ambient.touch.TouchMonitor;
import java.util.Collection;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class TouchMonitor$$ExternalSyntheticLambda13 implements Function {
    public final /* synthetic */ int $r8$classId;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return Integer.valueOf(((TouchMonitor.TouchSessionImpl) obj).hashCode());
            case 1:
                return ((Integer) obj).toString();
            case 2:
                return ((TouchMonitor.TouchSessionImpl) obj).mEventListeners;
            case 3:
                return ((Collection) obj).stream();
            default:
                return ((TouchMonitor.TouchSessionImpl) obj).mGestureListeners;
        }
    }
}
