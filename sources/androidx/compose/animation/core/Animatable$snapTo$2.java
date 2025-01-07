package androidx.compose.animation.core;

import androidx.compose.runtime.SnapshotMutableStateImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class Animatable$snapTo$2 extends SuspendLambda implements Function1 {
    final /* synthetic */ Object $targetValue;
    int label;
    final /* synthetic */ Animatable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Animatable$snapTo$2(Animatable animatable, Object obj, Continuation continuation) {
        super(1, continuation);
        this.this$0 = animatable;
        this.$targetValue = obj;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Animatable$snapTo$2 animatable$snapTo$2 = new Animatable$snapTo$2(this.this$0, this.$targetValue, (Continuation) obj);
        Unit unit = Unit.INSTANCE;
        animatable$snapTo$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Animatable.access$endAnimation(this.this$0);
        Object access$clampToBounds = Animatable.access$clampToBounds(this.this$0, this.$targetValue);
        ((SnapshotMutableStateImpl) this.this$0.internalState.value$delegate).setValue(access$clampToBounds);
        ((SnapshotMutableStateImpl) this.this$0.targetValue$delegate).setValue(access$clampToBounds);
        return Unit.INSTANCE;
    }
}
