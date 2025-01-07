package com.android.systemui.keyguard.domain.interactor;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.power.shared.model.WakefulnessModel;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.util.kotlin.FlowKt;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.AdaptedFunctionReference;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.DistinctFlowImpl;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__DistinctKt;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardWakeDirectlyToGoneInteractor$setOrCancelAlarmFromWakefulness$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ KeyguardWakeDirectlyToGoneInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$setOrCancelAlarmFromWakefulness$1$3, reason: invalid class name */
    final /* synthetic */ class AnonymousClass3 extends AdaptedFunctionReference implements Function3 {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(3, Pair.class, "<init>", "<init>(Ljava/lang/Object;Ljava/lang/Object;)V", 4);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Boolean bool = (Boolean) obj2;
            bool.booleanValue();
            return new Pair((WakefulnessModel) obj, bool);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$setOrCancelAlarmFromWakefulness$1$4, reason: invalid class name */
    public final class AnonymousClass4 implements FlowCollector {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ KeyguardWakeDirectlyToGoneInteractor this$0;

        public /* synthetic */ AnonymousClass4(KeyguardWakeDirectlyToGoneInteractor keyguardWakeDirectlyToGoneInteractor, int i) {
            this.$r8$classId = i;
            this.this$0 = keyguardWakeDirectlyToGoneInteractor;
        }

        /* JADX WARN: Code restructure failed: missing block: B:24:0x0095, code lost:
        
            if (r11.lockPatternUtils.isSecure(r12) != false) goto L24;
         */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.lang.Object r12, kotlin.coroutines.Continuation r13) {
            /*
                r11 = this;
                int r13 = r11.$r8$classId
                switch(r13) {
                    case 0: goto L1c;
                    default: goto L5;
                }
            L5:
                java.lang.Boolean r12 = (java.lang.Boolean) r12
                r12.getClass()
                com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor r11 = r11.this$0
                com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl r11 = r11.repository
                java.lang.Boolean r12 = java.lang.Boolean.FALSE
                kotlinx.coroutines.flow.StateFlowImpl r11 = r11._canIgnoreAuthAndReturnToGone
                r11.getClass()
                r13 = 0
                r11.updateState(r13, r12)
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            L1c:
                kotlin.Pair r12 = (kotlin.Pair) r12
                java.lang.Object r13 = r12.component1()
                com.android.systemui.power.shared.model.WakefulnessModel r13 = (com.android.systemui.power.shared.model.WakefulnessModel) r13
                java.lang.Object r12 = r12.component2()
                java.lang.Boolean r12 = (java.lang.Boolean) r12
                boolean r12 = r12.booleanValue()
                boolean r0 = r13.isAwake()
                com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor r11 = r11.this$0
                r11.isAwake = r0
                if (r0 != 0) goto La5
                if (r12 == 0) goto La5
                com.android.systemui.user.domain.interactor.SelectedUserInteractor r12 = r11.selectedUserInteractor
                int r0 = r12.getSelectedUserId()
                java.lang.String r1 = "lock_screen_lock_after_timeout"
                r2 = 5000(0x1388, float:7.006E-42)
                com.android.systemui.util.settings.SecureSettings r3 = r11.secureSettings
                int r1 = r3.getIntForUser(r1, r2, r0)
                long r1 = (long) r1
                com.android.internal.widget.LockPatternUtils r3 = r11.lockPatternUtils
                android.app.admin.DevicePolicyManager r3 = r3.getDevicePolicyManager()
                r4 = 0
                long r5 = r3.getMaximumTimeToLock(r4, r0)
                r7 = 0
                int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r3 > 0) goto L5d
                goto L75
            L5d:
                com.android.systemui.util.settings.SystemSettingsImpl r3 = r11.systemSettings
                java.lang.String r9 = "screen_off_timeout"
                r10 = 30000(0x7530, float:4.2039E-41)
                int r0 = r3.getIntForUser(r9, r10, r0)
                long r9 = (long) r0
                long r9 = java.lang.Math.max(r9, r7)
                long r5 = r5 - r9
                long r0 = java.lang.Math.min(r5, r1)
                long r1 = java.lang.Math.max(r0, r7)
            L75:
                com.android.systemui.power.shared.model.WakeSleepReason r0 = com.android.systemui.power.shared.model.WakeSleepReason.TIMEOUT
                com.android.systemui.power.shared.model.WakeSleepReason r13 = r13.lastSleepReason
                if (r13 != r0) goto L7f
                int r0 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
                if (r0 > 0) goto L98
            L7f:
                com.android.systemui.power.shared.model.WakeSleepReason r0 = com.android.systemui.power.shared.model.WakeSleepReason.POWER_BUTTON
                if (r13 != r0) goto Lad
                int r12 = r12.getSelectedUserId()
                com.android.internal.widget.LockPatternUtils r13 = r11.lockPatternUtils
                boolean r13 = r13.getPowerButtonInstantlyLocks(r12)
                if (r13 != 0) goto Lad
                com.android.internal.widget.LockPatternUtils r13 = r11.lockPatternUtils
                boolean r12 = r13.isSecure(r12)
                if (r12 != 0) goto L98
                goto Lad
            L98:
                com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl r11 = r11.repository
                java.lang.Boolean r12 = java.lang.Boolean.TRUE
                kotlinx.coroutines.flow.StateFlowImpl r11 = r11._canIgnoreAuthAndReturnToGone
                r11.getClass()
                r11.updateState(r4, r12)
                goto Lad
            La5:
                if (r0 == 0) goto Lad
                int r12 = r11.timeoutCounter
                int r12 = r12 + 1
                r11.timeoutCounter = r12
            Lad:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$setOrCancelAlarmFromWakefulness$1.AnonymousClass4.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardWakeDirectlyToGoneInteractor$setOrCancelAlarmFromWakefulness$1(KeyguardWakeDirectlyToGoneInteractor keyguardWakeDirectlyToGoneInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardWakeDirectlyToGoneInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new KeyguardWakeDirectlyToGoneInteractor$setOrCancelAlarmFromWakefulness$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyguardWakeDirectlyToGoneInteractor$setOrCancelAlarmFromWakefulness$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DistinctFlowImpl distinctUntilChangedBy$FlowKt__DistinctKt = FlowKt__DistinctKt.distinctUntilChangedBy$FlowKt__DistinctKt(this.this$0.powerInteractor.detailedWakefulness, new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$setOrCancelAlarmFromWakefulness$1.1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(((WakefulnessModel) obj2).isAwake());
                }
            }, FlowKt__DistinctKt.defaultAreEquivalent);
            KeyguardTransitionInteractor keyguardTransitionInteractor = this.this$0.transitionInteractor;
            SceneKey sceneKey = Scenes.Bouncer;
            KeyguardState keyguardState = KeyguardState.GONE;
            keyguardTransitionInteractor.getClass();
            keyguardState.checkValidState();
            SafeFlow sample = FlowKt.sample(distinctUntilChangedBy$FlowKt__DistinctKt, kotlinx.coroutines.flow.FlowKt.distinctUntilChanged(new KeyguardTransitionInteractor$special$$inlined$map$1(keyguardTransitionInteractor.currentKeyguardState)), AnonymousClass3.INSTANCE);
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.this$0, 0);
            this.label = 1;
            if (sample.collect(anonymousClass4, this) == coroutineSingletons) {
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
