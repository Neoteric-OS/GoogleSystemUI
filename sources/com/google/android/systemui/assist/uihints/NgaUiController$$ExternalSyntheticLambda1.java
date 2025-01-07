package com.google.android.systemui.assist.uihints;

import com.google.android.systemui.assist.AssistManagerGoogle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NgaUiController$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ NgaUiController f$0;

    public /* synthetic */ NgaUiController$$ExternalSyntheticLambda1(NgaUiController ngaUiController) {
        this.f$0 = ngaUiController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        NgaUiController ngaUiController = this.f$0;
        ((AssistManagerGoogle) ngaUiController.mAssistManager.get()).hideAssist();
        ngaUiController.hide();
    }
}
