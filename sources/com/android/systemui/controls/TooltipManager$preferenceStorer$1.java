package com.android.systemui.controls;

import android.content.Context;
import com.android.systemui.Prefs;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TooltipManager$preferenceStorer$1 extends Lambda implements Function1 {
    final /* synthetic */ Context $context;
    final /* synthetic */ TooltipManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TooltipManager$preferenceStorer$1(Context context, TooltipManager tooltipManager) {
        super(1);
        this.$context = context;
        this.this$0 = tooltipManager;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        int intValue = ((Number) obj).intValue();
        Context context = this.$context;
        this.this$0.getClass();
        Prefs.get(context).edit().putInt("ControlsStructureSwipeTooltipCount", intValue).apply();
        return Unit.INSTANCE;
    }
}
