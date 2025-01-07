package com.android.wm.shell.common;

import com.android.wm.shell.pip.phone.PipController$$ExternalSyntheticLambda12;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TabletopModeController$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ boolean f$0;

    public /* synthetic */ TabletopModeController$$ExternalSyntheticLambda0(boolean z) {
        this.f$0 = z;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((PipController$$ExternalSyntheticLambda12) obj).onTabletopModeChanged(this.f$0);
    }
}
