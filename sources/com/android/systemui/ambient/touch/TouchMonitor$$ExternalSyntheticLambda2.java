package com.android.systemui.ambient.touch;

import com.android.systemui.ambient.touch.TouchMonitor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class TouchMonitor$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ TouchMonitor$$ExternalSyntheticLambda2(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        TouchMonitor.TouchSessionImpl touchSessionImpl = (TouchMonitor.TouchSessionImpl) obj;
        switch (this.$r8$classId) {
            case 0:
                TouchMonitor.TouchSessionImpl.m782$$Nest$monRemoved(touchSessionImpl);
                return;
        }
        while (touchSessionImpl != null) {
            TouchMonitor.TouchSessionImpl.m782$$Nest$monRemoved(touchSessionImpl);
            touchSessionImpl = null;
        }
    }
}
