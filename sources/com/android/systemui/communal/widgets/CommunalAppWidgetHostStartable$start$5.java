package com.android.systemui.communal.widgets;

import android.content.pm.UserInfo;
import android.os.UserHandle;
import com.android.systemui.communal.shared.model.CommunalWidgetContentModel;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.kotlin.WithPrev;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalAppWidgetHostStartable$start$5 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CommunalAppWidgetHostStartable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalAppWidgetHostStartable$start$5(CommunalAppWidgetHostStartable communalAppWidgetHostStartable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalAppWidgetHostStartable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        CommunalAppWidgetHostStartable$start$5 communalAppWidgetHostStartable$start$5 = new CommunalAppWidgetHostStartable$start$5(this.this$0, continuation);
        communalAppWidgetHostStartable$start$5.L$0 = obj;
        return communalAppWidgetHostStartable$start$5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CommunalAppWidgetHostStartable$start$5 communalAppWidgetHostStartable$start$5 = (CommunalAppWidgetHostStartable$start$5) create((Pair) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        communalAppWidgetHostStartable$start$5.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Integer valueOf;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Pair pair = (Pair) this.L$0;
        WithPrev withPrev = (WithPrev) pair.component1();
        List list = (List) pair.component2();
        if (((Boolean) withPrev.newValue).booleanValue()) {
            CommunalAppWidgetHostStartable communalAppWidgetHostStartable = this.this$0;
            List userProfiles = ((UserTrackerImpl) communalAppWidgetHostStartable.userTracker).getUserProfiles();
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(userProfiles, 10));
            Iterator it = userProfiles.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(((UserInfo) it.next()).id));
            }
            Set set = CollectionsKt.toSet(arrayList);
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : list) {
                CommunalWidgetContentModel communalWidgetContentModel = (CommunalWidgetContentModel) obj2;
                if (communalWidgetContentModel instanceof CommunalWidgetContentModel.Available) {
                    UserHandle profile = ((CommunalWidgetContentModel.Available) communalWidgetContentModel).providerInfo.getProfile();
                    valueOf = profile != null ? Integer.valueOf(profile.getIdentifier()) : null;
                } else {
                    if (!(communalWidgetContentModel instanceof CommunalWidgetContentModel.Pending)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    valueOf = Integer.valueOf(((CommunalWidgetContentModel.Pending) communalWidgetContentModel).user.getIdentifier());
                }
                if (!CollectionsKt.contains(set, valueOf)) {
                    arrayList2.add(obj2);
                }
            }
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                communalAppWidgetHostStartable.communalInteractor.widgetRepository.deleteWidget(((CommunalWidgetContentModel) it2.next()).getAppWidgetId());
            }
        }
        return Unit.INSTANCE;
    }
}
