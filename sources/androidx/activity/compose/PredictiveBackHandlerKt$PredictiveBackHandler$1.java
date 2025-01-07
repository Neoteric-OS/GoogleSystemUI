package androidx.activity.compose;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PredictiveBackHandlerKt$PredictiveBackHandler$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ PredictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1 $backCallBack;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ Ref$ObjectRef $onBackInstance;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PredictiveBackHandlerKt$PredictiveBackHandler$1(PredictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1 predictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1, boolean z, Ref$ObjectRef ref$ObjectRef, Continuation continuation) {
        super(2, continuation);
        this.$backCallBack = predictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1;
        this.$enabled = z;
        this.$onBackInstance = ref$ObjectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new PredictiveBackHandlerKt$PredictiveBackHandler$1(this.$backCallBack, this.$enabled, this.$onBackInstance, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        PredictiveBackHandlerKt$PredictiveBackHandler$1 predictiveBackHandlerKt$PredictiveBackHandler$1 = (PredictiveBackHandlerKt$PredictiveBackHandler$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        predictiveBackHandlerKt$PredictiveBackHandler$1.invokeSuspend(unit);
        return unit;
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.FunctionReferenceImpl] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        PredictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1 predictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1 = this.$backCallBack;
        predictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1.isEnabled = this.$enabled;
        ?? r2 = predictiveBackHandlerKt$PredictiveBackHandler$backCallBack$1$1.enabledChangedCallback;
        if (r2 != 0) {
            r2.invoke();
        }
        if (!this.$enabled) {
            OnBackInstance onBackInstance = (OnBackInstance) this.$onBackInstance.element;
            if (onBackInstance != null) {
                onBackInstance.channel.close(null);
            }
            this.$onBackInstance.element = null;
        }
        return Unit.INSTANCE;
    }
}
