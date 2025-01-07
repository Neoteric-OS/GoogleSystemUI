package com.android.systemui.util.kotlin;

import kotlin.Pair;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class Utils$Companion$sample$2 extends AdaptedFunctionReference implements Function3 {
    public static final Utils$Companion$sample$2 INSTANCE = new Utils$Companion$sample$2();

    public Utils$Companion$sample$2() {
        super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return new Pair(obj, obj2);
    }
}
