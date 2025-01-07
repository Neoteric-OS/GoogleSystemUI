package androidx.compose.foundation;

import androidx.compose.foundation.relocation.BringIntoViewParent;
import androidx.compose.foundation.relocation.ScrollIntoView;
import androidx.compose.ui.layout.LayoutCoordinates;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FocusableNode$onFocusStateChange$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ LayoutCoordinates $layoutCoordinates;
    final /* synthetic */ BringIntoViewParent $parent;
    int label;
    final /* synthetic */ FocusableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FocusableNode$onFocusStateChange$1(FocusableNode focusableNode, BringIntoViewParent bringIntoViewParent, LayoutCoordinates layoutCoordinates, Continuation continuation) {
        super(2, continuation);
        this.this$0 = focusableNode;
        this.$parent = bringIntoViewParent;
        this.$layoutCoordinates = layoutCoordinates;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FocusableNode$onFocusStateChange$1(this.this$0, this.$parent, this.$layoutCoordinates, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FocusableNode$onFocusStateChange$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.isAttached) {
                BringIntoViewParent bringIntoViewParent = this.$parent;
                LayoutCoordinates layoutCoordinates = this.$layoutCoordinates;
                this.label = 1;
                if (ScrollIntoView.scrollIntoView(bringIntoViewParent, layoutCoordinates, null, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
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
