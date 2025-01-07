package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.ui.view.layout.blueprints.transitions.IntraBlueprintTransition;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardBlueprintInteractor$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ KeyguardBlueprintInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardBlueprintInteractor$start$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ KeyguardBlueprintInteractor this$0;

        public /* synthetic */ AnonymousClass1(KeyguardBlueprintInteractor keyguardBlueprintInteractor, int i) {
            this.$r8$classId = i;
            this.this$0 = keyguardBlueprintInteractor;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.keyguardBlueprintRepository.applyBlueprint((String) obj);
                    break;
                default:
                    ((Boolean) obj).getClass();
                    this.this$0.refreshBlueprint(IntraBlueprintTransition.Type.NoTransition);
                    break;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardBlueprintInteractor$start$1(KeyguardBlueprintInteractor keyguardBlueprintInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardBlueprintInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyguardBlueprintInteractor$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyguardBlueprintInteractor$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            KeyguardBlueprintInteractor keyguardBlueprintInteractor = this.this$0;
            KeyguardBlueprintInteractor$special$$inlined$map$1 keyguardBlueprintInteractor$special$$inlined$map$1 = keyguardBlueprintInteractor.blueprintId;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(keyguardBlueprintInteractor, 0);
            this.label = 1;
            if (keyguardBlueprintInteractor$special$$inlined$map$1.collect(anonymousClass1, this) == coroutineSingletons) {
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
