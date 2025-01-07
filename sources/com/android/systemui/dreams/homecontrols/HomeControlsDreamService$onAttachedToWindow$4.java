package com.android.systemui.dreams.homecontrols;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class HomeControlsDreamService$onAttachedToWindow$4 extends Lambda implements Function0 {
    final /* synthetic */ HomeControlsDreamService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeControlsDreamService$onAttachedToWindow$4(HomeControlsDreamService homeControlsDreamService) {
        super(0);
        this.this$0 = homeControlsDreamService;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        HomeControlsDreamService homeControlsDreamService = this.this$0;
        int i = HomeControlsDreamService.$r8$clinit;
        homeControlsDreamService.endDream(false);
        return Unit.INSTANCE;
    }
}
