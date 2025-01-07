package com.android.systemui.doze;

import com.android.app.tracing.TraceUtils;
import com.android.systemui.statusbar.phone.DozeServiceHost;
import com.android.systemui.statusbar.phone.DozeServiceHost$$ExternalSyntheticLambda2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class DozeUi$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DozeServiceHost f$0;

    public /* synthetic */ DozeUi$$ExternalSyntheticLambda0(DozeServiceHost dozeServiceHost) {
        this.f$0 = dozeServiceHost;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DozeServiceHost dozeServiceHost = this.f$0;
        dozeServiceHost.getClass();
        TraceUtils.trace("DozeServiceHost#dozeTimeTick", new DozeServiceHost$$ExternalSyntheticLambda2(dozeServiceHost));
    }
}
