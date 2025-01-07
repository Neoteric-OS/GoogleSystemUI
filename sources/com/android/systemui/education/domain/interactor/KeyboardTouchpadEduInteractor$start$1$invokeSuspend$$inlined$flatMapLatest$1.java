package com.android.systemui.education.domain.interactor;

import com.android.systemui.education.data.model.EduDeviceConnectionTime;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyboardTouchpadEduInteractor$start$1$invokeSuspend$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ KeyboardTouchpadEduInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyboardTouchpadEduInteractor$start$1$invokeSuspend$$inlined$flatMapLatest$1(KeyboardTouchpadEduInteractor keyboardTouchpadEduInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = keyboardTouchpadEduInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyboardTouchpadEduInteractor$start$1$invokeSuspend$$inlined$flatMapLatest$1 keyboardTouchpadEduInteractor$start$1$invokeSuspend$$inlined$flatMapLatest$1 = new KeyboardTouchpadEduInteractor$start$1$invokeSuspend$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        keyboardTouchpadEduInteractor$start$1$invokeSuspend$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        keyboardTouchpadEduInteractor$start$1$invokeSuspend$$inlined$flatMapLatest$1.L$1 = obj2;
        return keyboardTouchpadEduInteractor$start$1$invokeSuspend$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            EduDeviceConnectionTime eduDeviceConnectionTime = (EduDeviceConnectionTime) this.L$1;
            ArrayList arrayList = new ArrayList();
            if (eduDeviceConnectionTime.touchpadFirstConnectionTime != null) {
                arrayList.add(this.this$0.contextualEducationInteractor.backGestureModelFlow);
                arrayList.add(this.this$0.contextualEducationInteractor.homeGestureModelFlow);
                arrayList.add(this.this$0.contextualEducationInteractor.overviewGestureModelFlow);
            }
            if (eduDeviceConnectionTime.keyboardFirstConnectionTime != null) {
                arrayList.add(this.this$0.contextualEducationInteractor.allAppsGestureModelFlow);
            }
            ChannelLimitedFlowMerge merge = FlowKt.merge(arrayList);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, merge, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
