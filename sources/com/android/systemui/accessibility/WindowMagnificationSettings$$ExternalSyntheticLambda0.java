package com.android.systemui.accessibility;

import android.graphics.Rect;
import com.android.systemui.accessibility.WindowMagnificationSettings;
import java.util.Collections;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class WindowMagnificationSettings$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ WindowMagnificationSettings$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                WindowMagnificationSettings windowMagnificationSettings = (WindowMagnificationSettings) obj;
                Rect draggableWindowBounds$1 = windowMagnificationSettings.getDraggableWindowBounds$1();
                if (!windowMagnificationSettings.mDraggableWindowBounds.equals(draggableWindowBounds$1)) {
                    windowMagnificationSettings.mDraggableWindowBounds.set(draggableWindowBounds$1);
                    break;
                }
                break;
            case 1:
                WindowMagnificationSettings windowMagnificationSettings2 = (WindowMagnificationSettings) obj;
                windowMagnificationSettings2.mSettingView.setSystemGestureExclusionRects(Collections.singletonList(new Rect(0, 0, windowMagnificationSettings2.mSettingView.getWidth(), windowMagnificationSettings2.mSettingView.getHeight())));
                break;
            default:
                ((WindowMagnificationSettings.AnonymousClass1) obj).this$0.updateUIControlsIfNeeded();
                break;
        }
    }
}
