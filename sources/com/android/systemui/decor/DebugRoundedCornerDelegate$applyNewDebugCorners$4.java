package com.android.systemui.decor;

import android.util.Size;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DebugRoundedCornerDelegate$applyNewDebugCorners$4 extends Lambda implements Function0 {
    final /* synthetic */ DebugRoundedCornerDelegate this$0;

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        DebugRoundedCornerDelegate debugRoundedCornerDelegate = this.this$0;
        debugRoundedCornerDelegate.hasBottom = false;
        debugRoundedCornerDelegate.bottomRoundedDrawable = null;
        debugRoundedCornerDelegate.bottomRoundedSize = new Size(0, 0);
        return Unit.INSTANCE;
    }
}
