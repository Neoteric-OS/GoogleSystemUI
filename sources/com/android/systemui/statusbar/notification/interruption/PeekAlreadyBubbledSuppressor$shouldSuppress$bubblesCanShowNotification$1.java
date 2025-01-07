package com.android.systemui.statusbar.notification.interruption;

import com.android.wm.shell.bubbles.BubbleController;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PeekAlreadyBubbledSuppressor$shouldSuppress$bubblesCanShowNotification$1 implements Function {
    public static final PeekAlreadyBubbledSuppressor$shouldSuppress$bubblesCanShowNotification$1 INSTANCE = new PeekAlreadyBubbledSuppressor$shouldSuppress$bubblesCanShowNotification$1();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        BubbleController bubbleController = BubbleController.this;
        return Boolean.valueOf(bubbleController.isShowingAsBubbleBar() ? true ^ bubbleController.mBubblePositioner.mImeVisible : true);
    }
}
