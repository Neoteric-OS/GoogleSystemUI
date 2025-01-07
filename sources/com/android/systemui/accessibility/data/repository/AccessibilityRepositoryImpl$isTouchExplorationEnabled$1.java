package com.android.systemui.accessibility.data.repository;

import android.view.accessibility.AccessibilityManager;
import com.android.app.tracing.FlowTracing;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AccessibilityRepositoryImpl$isTouchExplorationEnabled$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ AccessibilityManager $manager;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessibilityRepositoryImpl$isTouchExplorationEnabled$1(AccessibilityManager accessibilityManager, Continuation continuation) {
        super(2, continuation);
        this.$manager = accessibilityManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        AccessibilityRepositoryImpl$isTouchExplorationEnabled$1 accessibilityRepositoryImpl$isTouchExplorationEnabled$1 = new AccessibilityRepositoryImpl$isTouchExplorationEnabled$1(this.$manager, continuation);
        accessibilityRepositoryImpl$isTouchExplorationEnabled$1.L$0 = obj;
        return accessibilityRepositoryImpl$isTouchExplorationEnabled$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((AccessibilityRepositoryImpl$isTouchExplorationEnabled$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.view.accessibility.AccessibilityManager$TouchExplorationStateChangeListener, com.android.systemui.accessibility.data.repository.AccessibilityRepositoryKt$sam$android_view_accessibility_AccessibilityManager_TouchExplorationStateChangeListener$0] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            final AccessibilityRepositoryImpl$isTouchExplorationEnabled$1$listener$1 accessibilityRepositoryImpl$isTouchExplorationEnabled$1$listener$1 = new AccessibilityRepositoryImpl$isTouchExplorationEnabled$1$listener$1(1, producerScope, ProducerScope.class, "trySend", "trySend-JP2dKIU(Ljava/lang/Object;)Ljava/lang/Object;", 8);
            final ?? r3 = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: com.android.systemui.accessibility.data.repository.AccessibilityRepositoryKt$sam$android_view_accessibility_AccessibilityManager_TouchExplorationStateChangeListener$0
                @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
                public final /* synthetic */ void onTouchExplorationStateChanged(boolean z) {
                    Function1.this.invoke(Boolean.valueOf(z));
                }
            };
            this.$manager.addTouchExplorationStateChangeListener(r3);
            ProducerCoroutine producerCoroutine = (ProducerCoroutine) producerScope;
            producerCoroutine.mo1790trySendJP2dKIU(Boolean.valueOf(this.$manager.isTouchExplorationEnabled()));
            FlowTracing flowTracing = FlowTracing.INSTANCE;
            final AccessibilityManager accessibilityManager = this.$manager;
            Function0 function0 = new Function0() { // from class: com.android.systemui.accessibility.data.repository.AccessibilityRepositoryImpl$isTouchExplorationEnabled$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    accessibilityManager.removeTouchExplorationStateChangeListener(r3);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (FlowTracing.tracedAwaitClose(producerCoroutine, "AccessibilityRepository", function0, this) == coroutineSingletons) {
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
