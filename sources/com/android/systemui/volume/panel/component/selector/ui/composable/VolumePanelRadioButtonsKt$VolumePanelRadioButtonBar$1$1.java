package com.android.systemui.volume.panel.component.selector.ui.composable;

import androidx.compose.animation.core.Animatable;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class VolumePanelRadioButtonsKt$VolumePanelRadioButtonBar$1$1 extends Lambda implements Function1 {
    final /* synthetic */ CoroutineScope $coroutineScope;
    final /* synthetic */ Animatable $offsetAnimatable;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.volume.panel.component.selector.ui.composable.VolumePanelRadioButtonsKt$VolumePanelRadioButtonBar$1$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ int $it;
        final /* synthetic */ Animatable $offsetAnimatable;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Animatable animatable, int i, Continuation continuation) {
            super(2, continuation);
            this.$offsetAnimatable = animatable;
            this.$it = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$offsetAnimatable, this.$it, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (((Number) this.$offsetAnimatable.internalState.getValue()).intValue() == -1) {
                    Animatable animatable = this.$offsetAnimatable;
                    Integer num = new Integer(this.$it);
                    this.label = 1;
                    if (animatable.snapTo(num, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    Animatable animatable2 = this.$offsetAnimatable;
                    Integer num2 = new Integer(this.$it);
                    this.label = 2;
                    if (Animatable.animateTo$default(animatable2, num2, null, null, null, this, 14) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                }
            } else {
                if (i != 1 && i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VolumePanelRadioButtonsKt$VolumePanelRadioButtonBar$1$1(ContextScope contextScope, Animatable animatable) {
        super(1);
        this.$coroutineScope = contextScope;
        this.$offsetAnimatable = animatable;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        BuildersKt.launch$default(this.$coroutineScope, null, null, new AnonymousClass1(this.$offsetAnimatable, ((Number) obj).intValue(), null), 3);
        return Unit.INSTANCE;
    }
}
