package com.android.wm.shell.windowdecor;

import android.graphics.Region;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DragResizeInputListener$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ DragResizeInputListener f$0;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f$0.updateSinkInputChannel((Region) obj);
    }
}
