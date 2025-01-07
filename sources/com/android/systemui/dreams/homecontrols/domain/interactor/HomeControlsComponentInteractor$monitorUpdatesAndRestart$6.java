package com.android.systemui.dreams.homecontrols.domain.interactor;

import com.android.systemui.common.shared.model.PackageChangeModel;
import kotlin.Pair;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class HomeControlsComponentInteractor$monitorUpdatesAndRestart$6 extends AdaptedFunctionReference implements Function3 {
    public static final HomeControlsComponentInteractor$monitorUpdatesAndRestart$6 INSTANCE = new HomeControlsComponentInteractor$monitorUpdatesAndRestart$6();

    public HomeControlsComponentInteractor$monitorUpdatesAndRestart$6() {
        super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        long longValue = ((Number) obj2).longValue();
        int i = HomeControlsComponentInteractor.$r8$clinit;
        return new Pair((PackageChangeModel.UpdateStarted) obj, new Long(longValue));
    }
}
