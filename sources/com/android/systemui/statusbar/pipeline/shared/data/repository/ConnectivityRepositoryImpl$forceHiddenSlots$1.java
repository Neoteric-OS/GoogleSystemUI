package com.android.systemui.statusbar.pipeline.shared.data.repository;

import com.android.systemui.statusbar.pipeline.shared.ConnectivityInputLogger;
import com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl;
import com.android.systemui.tuner.TunerService;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ConnectivityRepositoryImpl$forceHiddenSlots$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ ConnectivityInputLogger $logger;
    final /* synthetic */ TunerService $tunerService;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ConnectivityRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConnectivityRepositoryImpl$forceHiddenSlots$1(TunerService tunerService, ConnectivityInputLogger connectivityInputLogger, ConnectivityRepositoryImpl connectivityRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.$tunerService = tunerService;
        this.$logger = connectivityInputLogger;
        this.this$0 = connectivityRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ConnectivityRepositoryImpl$forceHiddenSlots$1 connectivityRepositoryImpl$forceHiddenSlots$1 = new ConnectivityRepositoryImpl$forceHiddenSlots$1(this.$tunerService, this.$logger, this.this$0, continuation);
        connectivityRepositoryImpl$forceHiddenSlots$1.L$0 = obj;
        return connectivityRepositoryImpl$forceHiddenSlots$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ConnectivityRepositoryImpl$forceHiddenSlots$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$forceHiddenSlots$1$callback$1, com.android.systemui.tuner.TunerService$Tunable] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ConnectivityInputLogger connectivityInputLogger = this.$logger;
            final ConnectivityRepositoryImpl connectivityRepositoryImpl = this.this$0;
            final ?? r1 = new TunerService.Tunable() { // from class: com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$forceHiddenSlots$1$callback$1
                @Override // com.android.systemui.tuner.TunerService.Tunable
                public final void onTuningChanged(String str, String str2) {
                    Set set;
                    if (str.equals("icon_blacklist")) {
                        ConnectivityInputLogger.this.logTuningChanged(str2);
                        ConnectivityRepositoryImpl connectivityRepositoryImpl2 = connectivityRepositoryImpl;
                        if (str2 == null || (set = ConnectivityRepositoryImpl.Companion.access$toSlotSet(StringsKt.split$default(str2, new String[]{","}, 0, 6), connectivityRepositoryImpl2.connectivitySlots)) == null) {
                            set = connectivityRepositoryImpl2.defaultHiddenIcons;
                        }
                        ((ProducerCoroutine) producerScope).mo1790trySendJP2dKIU(set);
                    }
                }
            };
            this.$tunerService.addTunable(r1, "icon_blacklist");
            final TunerService tunerService = this.$tunerService;
            Function0 function0 = new Function0() { // from class: com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$forceHiddenSlots$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    TunerService.this.removeTunable(r1);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
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
