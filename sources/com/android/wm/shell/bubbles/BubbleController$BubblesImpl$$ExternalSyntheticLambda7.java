package com.android.wm.shell.bubbles;

import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$BubblesImpl$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleController.BubblesImpl f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ BubbleController$BubblesImpl$$ExternalSyntheticLambda7(BubbleController.BubblesImpl bubblesImpl, boolean z, int i) {
        this.$r8$classId = i;
        this.f$0 = bubblesImpl;
        this.f$1 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                BubbleController.BubblesImpl bubblesImpl = this.f$0;
                boolean z = this.f$1;
                BubbleStackView bubbleStackView = BubbleController.this.mStackView;
                if (bubbleStackView != null) {
                    bubbleStackView.mSensitiveNotificationProtectionActive = z;
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 5894965654239984660L, 3, Boolean.valueOf(z));
                        break;
                    }
                }
                break;
            case 1:
                BubbleController.BubblesImpl bubblesImpl2 = this.f$0;
                boolean z2 = this.f$1;
                BubbleController bubbleController = BubbleController.this;
                BubbleStackView bubbleStackView2 = bubbleController.mStackView;
                if (bubbleStackView2 != null) {
                    BubbleData bubbleData = bubbleController.mBubbleData;
                    boolean z3 = (z2 || bubbleData.mExpanded) ? false : true;
                    bubbleStackView2.mTemporarilyInvisible = z3;
                    bubbleStackView2.updateTemporarilyInvisibleAnimation(z3);
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 3679537687983308475L, 15, Boolean.valueOf(z2), Boolean.valueOf(bubbleData.mExpanded));
                        break;
                    }
                }
                break;
            case 2:
                BubbleController.this.onStatusBarStateChanged(this.f$1);
                break;
            default:
                BubbleController.BubblesImpl bubblesImpl3 = this.f$0;
                boolean z4 = this.f$1;
                BubbleController bubbleController2 = BubbleController.this;
                BubbleStackView bubbleStackView3 = bubbleController2.mStackView;
                if (bubbleStackView3 != null && bubbleStackView3.mIsExpanded) {
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -3454367840069733869L, 3, Boolean.valueOf(z4));
                    }
                    if (!z4) {
                        bubbleController2.mStackView.startMonitoringSwipeUpGesture();
                        break;
                    } else {
                        bubbleController2.mStackView.stopMonitoringSwipeUpGestureInternal();
                        break;
                    }
                }
                break;
        }
    }
}
