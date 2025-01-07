package com.android.systemui.statusbar.gesture;

import android.view.InputEvent;
import android.view.MotionEvent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GesturePointerEventDetector extends GenericGestureDetector {
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public GesturePointerEventDetector() {
        /*
            r1 = this;
            java.lang.Class<com.android.systemui.statusbar.gesture.GesturePointerEventDetector> r0 = com.android.systemui.statusbar.gesture.GesturePointerEventDetector.class
            kotlin.jvm.internal.ClassReference r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            java.lang.String r0 = r0.getSimpleName()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r1.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.gesture.GesturePointerEventDetector.<init>():void");
    }

    @Override // com.android.systemui.statusbar.gesture.GenericGestureDetector
    public final void onInputEvent(InputEvent inputEvent) {
        if (inputEvent instanceof MotionEvent) {
            onGestureDetected$frameworks__base__packages__SystemUI__android_common__SystemUI_core((MotionEvent) inputEvent);
        }
    }
}
