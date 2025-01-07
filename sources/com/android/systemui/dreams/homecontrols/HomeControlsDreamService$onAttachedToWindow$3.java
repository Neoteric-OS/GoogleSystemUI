package com.android.systemui.dreams.homecontrols;

import android.window.TaskFragmentInfo;
import com.android.systemui.log.core.Logger;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class HomeControlsDreamService$onAttachedToWindow$3 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        HomeControlsDreamService homeControlsDreamService = (HomeControlsDreamService) this.receiver;
        int i = HomeControlsDreamService.$r8$clinit;
        homeControlsDreamService.getClass();
        if (((TaskFragmentInfo) obj).isEmpty()) {
            Logger.d$default(homeControlsDreamService.logger, "Finishing dream due to TaskFragment being empty", null, 2, null);
            homeControlsDreamService.endDream(true);
        }
        return Unit.INSTANCE;
    }
}
