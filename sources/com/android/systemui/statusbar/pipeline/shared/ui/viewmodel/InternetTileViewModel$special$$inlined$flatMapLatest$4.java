package com.android.systemui.statusbar.pipeline.shared.ui.viewmodel;

import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InternetTileViewModel$special$$inlined$flatMapLatest$4 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ InternetTileViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InternetTileViewModel$special$$inlined$flatMapLatest$4(InternetTileViewModel internetTileViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = internetTileViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        InternetTileViewModel$special$$inlined$flatMapLatest$4 internetTileViewModel$special$$inlined$flatMapLatest$4 = new InternetTileViewModel$special$$inlined$flatMapLatest$4(this.this$0, (Continuation) obj3);
        internetTileViewModel$special$$inlined$flatMapLatest$4.L$0 = (FlowCollector) obj;
        internetTileViewModel$special$$inlined$flatMapLatest$4.L$1 = obj2;
        return internetTileViewModel$special$$inlined$flatMapLatest$4.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Text text;
        Flow flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Icon.Resource resource = (Icon.Resource) this.L$1;
            if (resource == null) {
                flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 = this.this$0.notConnectedFlow;
            } else {
                ContentDescription contentDescription = resource.contentDescription;
                if (contentDescription != null) {
                    InternetTileModel.Inactive inactive = InternetTileViewModel.NOT_CONNECTED_NETWORKS_UNAVAILABLE;
                    if (contentDescription instanceof ContentDescription.Loaded) {
                        text = new Text.Loaded(((ContentDescription.Loaded) contentDescription).description);
                    } else {
                        if (!(contentDescription instanceof ContentDescription.Resource)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        text = new Text.Resource(((ContentDescription.Resource) contentDescription).res);
                    }
                } else {
                    text = null;
                }
                flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(new InternetTileModel.Active(null, text, new Integer(resource.res), null, null, contentDescription, 9));
            }
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2, this) == coroutineSingletons) {
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
