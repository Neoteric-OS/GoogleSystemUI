package com.android.systemui.dreams.homecontrols.domain.interactor;

import android.content.SharedPreferences;
import android.content.pm.UserInfo;
import com.android.systemui.controls.panels.AuthorizedPanelsRepository;
import com.android.systemui.controls.panels.AuthorizedPanelsRepositoryImpl;
import com.android.systemui.controls.panels.AuthorizedPanelsRepositoryImpl$observeAuthorizedPanels$$inlined$map$1$2;
import com.android.systemui.util.kotlin.FlowKt$emitOnStart$1;
import com.android.systemui.util.kotlin.SharedPreferencesExt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HomeControlsComponentInteractor$special$$inlined$flatMapLatest$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ AuthorizedPanelsRepository $authorizedPanelsRepository$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeControlsComponentInteractor$special$$inlined$flatMapLatest$2(Continuation continuation, AuthorizedPanelsRepository authorizedPanelsRepository) {
        super(3, continuation);
        this.$authorizedPanelsRepository$inlined = authorizedPanelsRepository;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        HomeControlsComponentInteractor$special$$inlined$flatMapLatest$2 homeControlsComponentInteractor$special$$inlined$flatMapLatest$2 = new HomeControlsComponentInteractor$special$$inlined$flatMapLatest$2((Continuation) obj3, this.$authorizedPanelsRepository$inlined);
        homeControlsComponentInteractor$special$$inlined$flatMapLatest$2.L$0 = (FlowCollector) obj;
        homeControlsComponentInteractor$special$$inlined$flatMapLatest$2.L$1 = obj2;
        return homeControlsComponentInteractor$special$$inlined$flatMapLatest$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            UserInfo userInfo = (UserInfo) this.L$1;
            AuthorizedPanelsRepositoryImpl authorizedPanelsRepositoryImpl = (AuthorizedPanelsRepositoryImpl) this.$authorizedPanelsRepository$inlined;
            SharedPreferences instantiateSharedPrefs = authorizedPanelsRepositoryImpl.instantiateSharedPrefs(userInfo.getUserHandle());
            FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new FlowKt$emitOnStart$1(2, null), SharedPreferencesExt.observe(instantiateSharedPrefs));
            this.label = 1;
            FlowKt.ensureActive(flowCollector);
            Object collect = flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.collect(new AuthorizedPanelsRepositoryImpl$observeAuthorizedPanels$$inlined$map$1$2(flowCollector, authorizedPanelsRepositoryImpl, instantiateSharedPrefs), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
