package com.android.wm.shell.bubbles;

import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleStackView$$ExternalSyntheticLambda41 implements Consumer {
    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        BubbleExpandedView bubbleExpandedView = ((Bubble) obj).mExpandedView;
        if (bubbleExpandedView != null) {
            bubbleExpandedView.applyThemeAttrs();
        }
    }
}
