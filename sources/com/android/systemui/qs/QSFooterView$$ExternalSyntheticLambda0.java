package com.android.systemui.qs;

import android.widget.TextView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class QSFooterView$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ QSFooterView f$0;

    @Override // java.lang.Runnable
    public final void run() {
        QSFooterView qSFooterView = this.f$0;
        qSFooterView.mBuildText.setVisibility((qSFooterView.mExpanded && qSFooterView.mShouldShowBuildText) ? 0 : 4);
        TextView textView = qSFooterView.mBuildText;
        textView.setLongClickable(textView.getVisibility() == 0);
        qSFooterView.setClickable(false);
    }
}
