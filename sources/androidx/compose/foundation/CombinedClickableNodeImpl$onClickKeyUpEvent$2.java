package androidx.compose.foundation;

import androidx.compose.foundation.CombinedClickableNodeImpl;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CombinedClickableNodeImpl$onClickKeyUpEvent$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ long $keyCode;
    long J$0;
    long J$1;
    int label;
    final /* synthetic */ CombinedClickableNodeImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombinedClickableNodeImpl$onClickKeyUpEvent$2(CombinedClickableNodeImpl combinedClickableNodeImpl, long j, Continuation continuation) {
        super(2, continuation);
        this.this$0 = combinedClickableNodeImpl;
        this.$keyCode = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CombinedClickableNodeImpl$onClickKeyUpEvent$2(this.this$0, this.$keyCode, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CombinedClickableNodeImpl$onClickKeyUpEvent$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        long doubleTapTimeoutMillis;
        long j;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ViewConfiguration viewConfiguration = (ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this.this$0, CompositionLocalsKt.LocalViewConfiguration);
            viewConfiguration.getClass();
            doubleTapTimeoutMillis = viewConfiguration.getDoubleTapTimeoutMillis();
            j = 40;
            this.J$0 = 40L;
            this.J$1 = doubleTapTimeoutMillis;
            this.label = 1;
            if (DelayKt.delay(40L, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.onClick.invoke();
                return Unit.INSTANCE;
            }
            doubleTapTimeoutMillis = this.J$1;
            j = this.J$0;
            ResultKt.throwOnFailure(obj);
        }
        CombinedClickableNodeImpl.DoubleKeyClickState doubleKeyClickState = (CombinedClickableNodeImpl.DoubleKeyClickState) this.this$0.doubleKeyClickStates.get(this.$keyCode);
        if (doubleKeyClickState != null) {
            doubleKeyClickState.doubleTapMinTimeMillisElapsed = true;
        }
        this.label = 2;
        if (DelayKt.delay(doubleTapTimeoutMillis - j, this) == coroutineSingletons) {
            return coroutineSingletons;
        }
        this.this$0.onClick.invoke();
        return Unit.INSTANCE;
    }
}
