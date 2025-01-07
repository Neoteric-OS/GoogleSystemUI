package com.android.systemui.doze;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DozeLogger$logCarModeStarted$2 extends Lambda implements Function1 {
    public static final DozeLogger$logCarModeStarted$2 INSTANCE = new DozeLogger$logCarModeStarted$2();

    public DozeLogger$logCarModeStarted$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return "Doze car mode started";
    }
}
