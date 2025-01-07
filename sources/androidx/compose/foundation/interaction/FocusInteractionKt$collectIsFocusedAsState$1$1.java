package androidx.compose.foundation.interaction;

import androidx.compose.runtime.MutableState;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FocusInteractionKt$collectIsFocusedAsState$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ MutableState $isFocused;
    final /* synthetic */ InteractionSource $this_collectIsFocusedAsState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FocusInteractionKt$collectIsFocusedAsState$1$1(InteractionSource interactionSource, MutableState mutableState, Continuation continuation) {
        super(2, continuation);
        this.$this_collectIsFocusedAsState = interactionSource;
        this.$isFocused = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FocusInteractionKt$collectIsFocusedAsState$1$1(this.$this_collectIsFocusedAsState, this.$isFocused, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FocusInteractionKt$collectIsFocusedAsState$1$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        final ArrayList arrayList = new ArrayList();
        SharedFlowImpl sharedFlowImpl = ((MutableInteractionSourceImpl) this.$this_collectIsFocusedAsState).interactions;
        final MutableState mutableState = this.$isFocused;
        FlowCollector flowCollector = new FlowCollector() { // from class: androidx.compose.foundation.interaction.FocusInteractionKt$collectIsFocusedAsState$1$1.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj2, Continuation continuation) {
                Interaction interaction = (Interaction) obj2;
                if (interaction instanceof FocusInteraction$Focus) {
                    arrayList.add(interaction);
                } else if (interaction instanceof FocusInteraction$Unfocus) {
                    arrayList.remove(((FocusInteraction$Unfocus) interaction).focus);
                }
                mutableState.setValue(Boolean.valueOf(!arrayList.isEmpty()));
                return Unit.INSTANCE;
            }
        };
        this.label = 1;
        sharedFlowImpl.collect(flowCollector, this);
        return coroutineSingletons;
    }
}
