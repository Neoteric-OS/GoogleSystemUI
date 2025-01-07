package com.android.systemui.keyguard;

import com.android.systemui.keyguard.KeyguardIndicationRotateTextViewController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardIndicationRotateTextViewController$ShowNextIndication$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ KeyguardIndicationRotateTextViewController.ShowNextIndication f$0;

    @Override // java.lang.Runnable
    public final void run() {
        KeyguardIndicationRotateTextViewController keyguardIndicationRotateTextViewController = this.f$0.this$0;
        keyguardIndicationRotateTextViewController.showIndication(keyguardIndicationRotateTextViewController.mIndicationQueue.size() == 0 ? -1 : ((Integer) keyguardIndicationRotateTextViewController.mIndicationQueue.get(0)).intValue());
    }
}
