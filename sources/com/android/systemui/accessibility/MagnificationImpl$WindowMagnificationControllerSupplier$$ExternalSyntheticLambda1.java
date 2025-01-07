package com.android.systemui.accessibility;

import android.view.WindowManagerGlobal;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class MagnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda1 implements Supplier {
    @Override // java.util.function.Supplier
    public final Object get() {
        return WindowManagerGlobal.getWindowSession();
    }
}
