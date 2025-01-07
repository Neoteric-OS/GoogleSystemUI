package com.android.systemui.scene.domain.resolver;

import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.AdaptedFunctionReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class HomeSceneFamilyResolver$resolvedScene$1 extends AdaptedFunctionReference implements Function5 {
    @Override // kotlin.jvm.functions.Function5
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        boolean booleanValue = ((Boolean) obj3).booleanValue();
        boolean booleanValue2 = ((Boolean) obj4).booleanValue();
        ((HomeSceneFamilyResolver) this.receiver).getClass();
        return HomeSceneFamilyResolver.homeScene(((Boolean) obj).booleanValue(), (Boolean) obj2, booleanValue, booleanValue2);
    }
}
