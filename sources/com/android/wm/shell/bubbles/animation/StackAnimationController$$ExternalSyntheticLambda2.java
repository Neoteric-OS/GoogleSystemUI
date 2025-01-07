package com.android.wm.shell.bubbles.animation;

import android.view.View;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.BadgedImageView;
import com.android.wm.shell.bubbles.BubbleStackView$$ExternalSyntheticLambda25;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StackAnimationController$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ StackAnimationController$$ExternalSyntheticLambda2(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                StackAnimationController stackAnimationController = (StackAnimationController) this.f$0;
                List list = (List) this.f$1;
                stackAnimationController.getClass();
                int i = 0;
                while (i < list.size()) {
                    View view = (View) list.get(i);
                    view.setZ(i < 2 ? (stackAnimationController.mMaxBubbles * stackAnimationController.mElevation) - i : 0.0f);
                    BadgedImageView badgedImageView = (BadgedImageView) view;
                    if (i == 0) {
                        badgedImageView.showDotAndBadge(!stackAnimationController.isStackOnLeftSide());
                    } else {
                        badgedImageView.hideDotAndBadge(!stackAnimationController.isStackOnLeftSide());
                    }
                    i++;
                }
                break;
            default:
                View view2 = (View) this.f$0;
                BubbleStackView$$ExternalSyntheticLambda25 bubbleStackView$$ExternalSyntheticLambda25 = (BubbleStackView$$ExternalSyntheticLambda25) this.f$1;
                view2.setTag(R.id.reorder_animator_tag, null);
                bubbleStackView$$ExternalSyntheticLambda25.run();
                break;
        }
    }
}
