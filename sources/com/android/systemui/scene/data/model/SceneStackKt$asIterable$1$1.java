package com.android.systemui.scene.data.model;

import com.android.compose.animation.scene.SceneKey;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceBuilderIterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SceneStackKt$asIterable$1$1 extends RestrictedSuspendLambda implements Function2 {
    final /* synthetic */ SceneStack $this_asIterable;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SceneStackKt$asIterable$1$1(SceneStack sceneStack, Continuation continuation) {
        super(continuation);
        this.$this_asIterable = sceneStack;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SceneStackKt$asIterable$1$1 sceneStackKt$asIterable$1$1 = new SceneStackKt$asIterable$1$1(this.$this_asIterable, continuation);
        sceneStackKt$asIterable$1$1.L$0 = obj;
        return sceneStackKt$asIterable$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SceneStackKt$asIterable$1$1) create((SequenceBuilderIterator) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SequenceBuilderIterator sequenceBuilderIterator = (SequenceBuilderIterator) this.L$0;
            SceneStack sceneStack = this.$this_asIterable;
            if (!Intrinsics.areEqual(sceneStack, EmptyStack.INSTANCE) && (sceneStack instanceof StackedNodes)) {
                SceneKey sceneKey = ((StackedNodes) this.$this_asIterable).head;
                this.L$0 = sequenceBuilderIterator;
                this.label = 1;
                sequenceBuilderIterator.yield(sceneKey, this);
                return coroutineSingletons;
            }
        } else if (i == 1) {
            SequenceBuilderIterator sequenceBuilderIterator2 = (SequenceBuilderIterator) this.L$0;
            ResultKt.throwOnFailure(obj);
            SceneStackKt$asIterable$$inlined$Iterable$1 sceneStackKt$asIterable$$inlined$Iterable$1 = new SceneStackKt$asIterable$$inlined$Iterable$1(((StackedNodes) this.$this_asIterable).tail);
            this.L$0 = null;
            this.label = 2;
            if (sequenceBuilderIterator2.yieldAll(sceneStackKt$asIterable$$inlined$Iterable$1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
