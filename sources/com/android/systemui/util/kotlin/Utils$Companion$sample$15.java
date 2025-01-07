package com.android.systemui.util.kotlin;

import com.android.systemui.util.kotlin.Utils;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class Utils$Companion$sample$15 extends AdaptedFunctionReference implements Function3 {
    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        Sextuple sextuple = (Sextuple) obj2;
        ((Utils.Companion) this.receiver).getClass();
        return new Septuple(obj, sextuple.first, sextuple.second, sextuple.third, sextuple.fourth, sextuple.fifth, sextuple.sixth);
    }
}
