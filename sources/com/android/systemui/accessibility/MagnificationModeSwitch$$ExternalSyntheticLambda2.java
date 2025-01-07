package com.android.systemui.accessibility;

import android.graphics.Rect;
import java.util.Collections;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MagnificationModeSwitch$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MagnificationModeSwitch f$0;

    public /* synthetic */ MagnificationModeSwitch$$ExternalSyntheticLambda2(MagnificationModeSwitch magnificationModeSwitch, int i) {
        this.$r8$classId = i;
        this.f$0 = magnificationModeSwitch;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        MagnificationModeSwitch magnificationModeSwitch = this.f$0;
        switch (i) {
            case 0:
                Rect draggableWindowBounds = magnificationModeSwitch.getDraggableWindowBounds();
                if (!magnificationModeSwitch.mDraggableWindowBounds.equals(draggableWindowBounds)) {
                    magnificationModeSwitch.mDraggableWindowBounds.set(draggableWindowBounds);
                    magnificationModeSwitch.stickToScreenEdge(magnificationModeSwitch.mToLeftScreenEdge);
                    break;
                }
                break;
            case 1:
                magnificationModeSwitch.mImageView.animate().alpha(0.0f).setDuration(300L).withEndAction(new MagnificationModeSwitch$$ExternalSyntheticLambda2(magnificationModeSwitch, 4)).start();
                magnificationModeSwitch.mIsFadeOutAnimating = true;
                break;
            case 2:
                magnificationModeSwitch.mImageView.setSystemGestureExclusionRects(Collections.singletonList(new Rect(0, 0, magnificationModeSwitch.mImageView.getWidth(), magnificationModeSwitch.mImageView.getHeight())));
                break;
            case 3:
                magnificationModeSwitch.mImageView.animate().alpha(1.0f).setDuration(300L).start();
                break;
            default:
                magnificationModeSwitch.removeButton();
                break;
        }
    }
}
