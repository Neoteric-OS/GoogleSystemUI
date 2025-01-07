package com.android.systemui.communal.ui.compose;

import android.content.ComponentName;
import android.os.UserHandle;
import com.android.systemui.communal.ui.viewmodel.BaseCommunalViewModel;
import com.android.systemui.communal.widgets.WidgetConfigurator;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ContentListStateKt$rememberContentListState$1$1 extends Lambda implements Function3 {
    final /* synthetic */ BaseCommunalViewModel $viewModel;
    final /* synthetic */ WidgetConfigurator $widgetConfigurator;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentListStateKt$rememberContentListState$1$1(BaseCommunalViewModel baseCommunalViewModel, WidgetConfigurator widgetConfigurator) {
        super(3);
        this.$viewModel = baseCommunalViewModel;
        this.$widgetConfigurator = widgetConfigurator;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int intValue = ((Number) obj3).intValue();
        this.$viewModel.onAddWidget((ComponentName) obj, (UserHandle) obj2, Integer.valueOf(intValue), this.$widgetConfigurator);
        return Unit.INSTANCE;
    }
}
