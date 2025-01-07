package com.android.wm.shell.bubbles;

import android.graphics.Rect;
import android.view.TouchDelegate;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.BubbleExpandedView;
import com.android.wm.shell.taskview.TaskView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleExpandedView$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleExpandedView f$0;

    public /* synthetic */ BubbleExpandedView$$ExternalSyntheticLambda2(BubbleExpandedView bubbleExpandedView, int i) {
        this.$r8$classId = i;
        this.f$0 = bubbleExpandedView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        BubbleExpandedView bubbleExpandedView = this.f$0;
        switch (i) {
            case 0:
                BubbleExpandedView.AnonymousClass1 anonymousClass1 = BubbleExpandedView.BOTTOM_CLIP_PROPERTY;
                int dimensionPixelSize = bubbleExpandedView.getResources().getDimensionPixelSize(R.dimen.bubble_manage_button_touch_area_height);
                Rect rect = new Rect();
                bubbleExpandedView.mManageButton.getHitRect(rect);
                int height = (dimensionPixelSize - rect.height()) / 2;
                rect.top -= height;
                rect.bottom += height;
                bubbleExpandedView.setTouchDelegate(new TouchDelegate(rect, bubbleExpandedView.mManageButton));
                break;
            case 1:
                TaskView taskView = bubbleExpandedView.mTaskView;
                if (taskView != null) {
                    taskView.onLocationChanged();
                    break;
                }
                break;
            default:
                bubbleExpandedView.mOverflowView.show();
                break;
        }
    }
}
