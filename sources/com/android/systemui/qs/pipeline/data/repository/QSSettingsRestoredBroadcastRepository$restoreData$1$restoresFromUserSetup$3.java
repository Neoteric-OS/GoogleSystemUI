package com.android.systemui.qs.pipeline.data.repository;

import com.android.systemui.qs.pipeline.data.model.RestoreData;
import com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class QSSettingsRestoredBroadcastRepository$restoreData$1$restoresFromUserSetup$3 extends SuspendLambda implements Function2 {
    final /* synthetic */ QSPipelineLogger $logger;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSSettingsRestoredBroadcastRepository$restoreData$1$restoresFromUserSetup$3(QSPipelineLogger qSPipelineLogger, Continuation continuation) {
        super(2, continuation);
        this.$logger = qSPipelineLogger;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        QSSettingsRestoredBroadcastRepository$restoreData$1$restoresFromUserSetup$3 qSSettingsRestoredBroadcastRepository$restoreData$1$restoresFromUserSetup$3 = new QSSettingsRestoredBroadcastRepository$restoreData$1$restoresFromUserSetup$3(this.$logger, continuation);
        qSSettingsRestoredBroadcastRepository$restoreData$1$restoresFromUserSetup$3.L$0 = obj;
        return qSSettingsRestoredBroadcastRepository$restoreData$1$restoresFromUserSetup$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        QSSettingsRestoredBroadcastRepository$restoreData$1$restoresFromUserSetup$3 qSSettingsRestoredBroadcastRepository$restoreData$1$restoresFromUserSetup$3 = (QSSettingsRestoredBroadcastRepository$restoreData$1$restoresFromUserSetup$3) create((RestoreData) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        qSSettingsRestoredBroadcastRepository$restoreData$1$restoresFromUserSetup$3.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.$logger.logSettingsRestoredOnUserSetupComplete(((RestoreData) this.L$0).userId);
        return Unit.INSTANCE;
    }
}
