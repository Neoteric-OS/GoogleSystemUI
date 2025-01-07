package com.android.systemui.communal.ui.viewmodel;

import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.interactor.CommunalPrefsInteractor;
import com.android.systemui.communal.ui.viewmodel.PopupType;
import com.android.systemui.settings.UserTrackerImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalViewModel$onDismissCtaTile$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ CommunalViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalViewModel$onDismissCtaTile$1(CommunalViewModel communalViewModel, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalViewModel$onDismissCtaTile$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CommunalViewModel$onDismissCtaTile$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CommunalInteractor communalInteractor = this.this$0.communalInteractor;
            this.label = 1;
            CommunalPrefsInteractor communalPrefsInteractor = communalInteractor.communalPrefsInteractor;
            Object ctaDismissed = communalPrefsInteractor.repository.setCtaDismissed(((UserTrackerImpl) communalPrefsInteractor.userTracker).getUserInfo(), this);
            if (ctaDismissed != coroutineSingletons) {
                ctaDismissed = unit;
            }
            if (ctaDismissed != coroutineSingletons) {
                ctaDismissed = unit;
            }
            if (ctaDismissed == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.this$0.setCurrentPopupType(PopupType.CtaTile.INSTANCE);
        return unit;
    }
}
