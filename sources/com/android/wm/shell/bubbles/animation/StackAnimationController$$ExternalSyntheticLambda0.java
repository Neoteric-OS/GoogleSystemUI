package com.android.wm.shell.bubbles.animation;

import android.view.View;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.animation.StackAnimationController;
import com.android.wm.shell.common.FloatingContentCoordinator;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StackAnimationController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ StackAnimationController$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                StackAnimationController stackAnimationController = (StackAnimationController) obj;
                stackAnimationController.setStackPosition(stackAnimationController.mPositioner.getRestingPosition());
                stackAnimationController.mStackMovedToStartPosition = true;
                stackAnimationController.mLayout.setVisibility(0);
                if (stackAnimationController.mLayout.getChildCount() > 0) {
                    FloatingContentCoordinator floatingContentCoordinator = stackAnimationController.mFloatingContentCoordinator;
                    floatingContentCoordinator.updateContentBounds();
                    Map map = floatingContentCoordinator.allContentBounds;
                    StackAnimationController.AnonymousClass1 anonymousClass1 = stackAnimationController.mStackFloatingContent;
                    map.put(anonymousClass1, anonymousClass1.getFloatingBoundsOnScreen());
                    floatingContentCoordinator.maybeMoveConflictingContent(anonymousClass1);
                    stackAnimationController.animateInBubble(stackAnimationController.mLayout.getChildAt(0), 0);
                    break;
                }
                break;
            default:
                ((View) obj).setTag(R.id.reorder_animator_tag, null);
                break;
        }
    }
}
