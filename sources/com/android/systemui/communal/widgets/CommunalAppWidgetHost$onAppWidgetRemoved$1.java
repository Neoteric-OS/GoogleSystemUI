package com.android.systemui.communal.widgets;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.log.core.Logger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.SharedFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalAppWidgetHost$onAppWidgetRemoved$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $appWidgetId;
    int label;
    final /* synthetic */ CommunalAppWidgetHost this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalAppWidgetHost$onAppWidgetRemoved$1(CommunalAppWidgetHost communalAppWidgetHost, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalAppWidgetHost;
        this.$appWidgetId = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalAppWidgetHost$onAppWidgetRemoved$1(this.this$0, this.$appWidgetId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CommunalAppWidgetHost$onAppWidgetRemoved$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Logger logger = this.this$0.logger;
            AnonymousClass1 anonymousClass1 = new Function1() { // from class: com.android.systemui.communal.widgets.CommunalAppWidgetHost$onAppWidgetRemoved$1.1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj2).getInt1(), "App widget removed from system: ");
                }
            };
            int i2 = this.$appWidgetId;
            LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), LogLevel.INFO, anonymousClass1, null);
            obtain.setInt1(i2);
            logger.getBuffer().commit(obtain);
            SharedFlowImpl sharedFlowImpl = this.this$0._appWidgetIdToRemove;
            Integer num = new Integer(this.$appWidgetId);
            this.label = 1;
            if (sharedFlowImpl.emit(num, this) == coroutineSingletons) {
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
