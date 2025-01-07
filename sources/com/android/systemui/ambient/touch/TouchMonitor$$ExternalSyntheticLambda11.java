package com.android.systemui.ambient.touch;

import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.InputEvent;
import com.android.systemui.shared.system.InputChannelCompat$InputEventListener;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class TouchMonitor$$ExternalSyntheticLambda11 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ TouchMonitor$$ExternalSyntheticLambda11(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                ((TouchMonitor) obj2).mMaxBounds = (Rect) obj;
                break;
            case 1:
                ((InputChannelCompat$InputEventListener) obj).onInputEvent((InputEvent) obj2);
                break;
            default:
                ((Consumer) obj2).accept((GestureDetector.OnGestureListener) obj);
                break;
        }
    }
}
