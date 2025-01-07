package com.android.systemui.biometrics;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class UdfpsLogger$e$2 extends Lambda implements Function1 {
    final /* synthetic */ String $msg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UdfpsLogger$e$2(String str) {
        super(1);
        this.$msg = str;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return this.$msg;
    }
}
