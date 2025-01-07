package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.deviceentry.shared.model.ErrorFaceAuthenticationStatus;
import kotlin.Pair;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class BiometricMessageInteractor$faceErrorMessage$3 extends AdaptedFunctionReference implements Function3 {
    public static final BiometricMessageInteractor$faceErrorMessage$3 INSTANCE = new BiometricMessageInteractor$faceErrorMessage$3();

    public BiometricMessageInteractor$faceErrorMessage$3() {
        super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        Boolean bool = (Boolean) obj2;
        bool.booleanValue();
        return new Pair((ErrorFaceAuthenticationStatus) obj, bool);
    }
}
