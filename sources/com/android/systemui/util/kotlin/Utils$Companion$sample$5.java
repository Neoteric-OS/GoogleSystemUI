package com.android.systemui.util.kotlin;

import kotlin.Triple;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.AdaptedFunctionReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class Utils$Companion$sample$5 extends AdaptedFunctionReference implements Function4 {
    public static final Utils$Companion$sample$5 INSTANCE = new Utils$Companion$sample$5();

    public Utils$Companion$sample$5() {
        super(4, Triple.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", 4);
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return new Triple(obj, obj2, obj3);
    }
}
