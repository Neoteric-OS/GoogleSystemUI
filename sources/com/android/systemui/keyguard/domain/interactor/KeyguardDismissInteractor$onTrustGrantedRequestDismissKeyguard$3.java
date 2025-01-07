package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.shared.model.TrustModel;
import com.android.systemui.util.kotlin.Quad;
import com.android.systemui.util.kotlin.Utils;
import kotlin.Triple;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class KeyguardDismissInteractor$onTrustGrantedRequestDismissKeyguard$3 extends AdaptedFunctionReference implements Function3 {
    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        Triple triple = (Triple) obj2;
        ((Utils.Companion) this.receiver).getClass();
        return new Quad((TrustModel) obj, triple.getFirst(), triple.getSecond(), triple.getThird());
    }
}
