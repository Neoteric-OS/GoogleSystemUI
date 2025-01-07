package androidx.compose.foundation.interaction;

import kotlin.Unit;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MutableInteractionSourceImpl implements MutableInteractionSource {
    public final SharedFlowImpl interactions = SharedFlowKt.MutableSharedFlow$default(0, 16, BufferOverflow.DROP_OLDEST, 1);

    @Override // androidx.compose.foundation.interaction.MutableInteractionSource
    public final Object emit(Interaction interaction, ContinuationImpl continuationImpl) {
        Object emit = this.interactions.emit(interaction, continuationImpl);
        return emit == CoroutineSingletons.COROUTINE_SUSPENDED ? emit : Unit.INSTANCE;
    }

    @Override // androidx.compose.foundation.interaction.InteractionSource
    public final SharedFlowImpl getInteractions() {
        return this.interactions;
    }

    @Override // androidx.compose.foundation.interaction.MutableInteractionSource
    public final boolean tryEmit(Interaction interaction) {
        return this.interactions.tryEmit(interaction);
    }
}
