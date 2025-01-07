package com.android.systemui.communal.domain.interactor;

import android.appwidget.AppWidgetProviderInfo;
import android.os.UserHandle;
import com.android.systemui.communal.domain.model.CommunalContentModel;
import com.android.systemui.communal.shared.model.CommunalWidgetContentModel;
import com.android.systemui.communal.widgets.CommunalAppWidgetHost;
import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalInteractor$widgetContent$3 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CommunalInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalInteractor$widgetContent$3(CommunalInteractor communalInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = communalInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        CommunalInteractor$widgetContent$3 communalInteractor$widgetContent$3 = new CommunalInteractor$widgetContent$3(this.this$0, (Continuation) obj3);
        communalInteractor$widgetContent$3.L$0 = (List) obj;
        return communalInteractor$widgetContent$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CommunalContentModel.WidgetContent pendingWidget;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List<CommunalWidgetContentModel> list = (List) this.L$0;
        CommunalInteractor communalInteractor = this.this$0;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        for (CommunalWidgetContentModel communalWidgetContentModel : list) {
            if (communalWidgetContentModel instanceof CommunalWidgetContentModel.Available) {
                CommunalWidgetContentModel.Available available = (CommunalWidgetContentModel.Available) communalWidgetContentModel;
                int i = available.appWidgetId;
                AppWidgetProviderInfo appWidgetProviderInfo = ((CommunalWidgetContentModel.Available) communalWidgetContentModel).providerInfo;
                CommunalAppWidgetHost communalAppWidgetHost = communalInteractor.appWidgetHost;
                UserHandle profile = appWidgetProviderInfo.getProfile();
                pendingWidget = new CommunalContentModel.WidgetContent.Widget(i, available.rank, appWidgetProviderInfo, communalAppWidgetHost, communalInteractor.userManager.isManagedProfile(profile.getIdentifier()) && communalInteractor.userManager.isQuietModeEnabled(profile));
            } else {
                if (!(communalWidgetContentModel instanceof CommunalWidgetContentModel.Pending)) {
                    throw new NoWhenBranchMatchedException();
                }
                CommunalWidgetContentModel.Pending pending = (CommunalWidgetContentModel.Pending) communalWidgetContentModel;
                CommunalWidgetContentModel.Pending pending2 = (CommunalWidgetContentModel.Pending) communalWidgetContentModel;
                pendingWidget = new CommunalContentModel.WidgetContent.PendingWidget(pending.appWidgetId, pending.rank, pending2.componentName, pending2.icon);
            }
            arrayList.add(pendingWidget);
        }
        return arrayList;
    }
}
