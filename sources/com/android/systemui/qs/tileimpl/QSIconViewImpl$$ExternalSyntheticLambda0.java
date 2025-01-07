package com.android.systemui.qs.tileimpl;

import android.widget.ImageView;
import com.android.systemui.plugins.qs.QSTile;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class QSIconViewImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ QSIconViewImpl f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ ImageView f$2;
    public final /* synthetic */ QSTile.State f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ QSIconViewImpl$$ExternalSyntheticLambda0(QSIconViewImpl qSIconViewImpl, long j, ImageView imageView, QSTile.State state, boolean z) {
        this.f$0 = qSIconViewImpl;
        this.f$1 = j;
        this.f$2 = imageView;
        this.f$3 = state;
        this.f$4 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        QSIconViewImpl qSIconViewImpl = this.f$0;
        long j = this.f$1;
        ImageView imageView = this.f$2;
        QSTile.State state = this.f$3;
        boolean z = this.f$4;
        if (qSIconViewImpl.mScheduledIconChangeTransactionId == j) {
            qSIconViewImpl.updateIcon(imageView, state, z);
        }
    }
}
