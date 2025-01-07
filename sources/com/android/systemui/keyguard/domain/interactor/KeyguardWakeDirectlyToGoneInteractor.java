package com.android.systemui.keyguard.domain.interactor;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.android.internal.widget.LockPatternUtils;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SystemSettingsImpl;
import com.android.systemui.util.time.SystemClock;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.DistinctFlowImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardWakeDirectlyToGoneInteractor {
    public final KeyguardWakeDirectlyToGoneInteractor$broadcastReceiver$1 broadcastReceiver;
    public final Flow canWakeDirectlyToGone;
    public boolean isAwake;
    public final KeyguardWakeDirectlyToGoneInteractor$special$$inlined$map$1 isLockscreenDisabled;
    public final LockPatternUtils lockPatternUtils;
    public final PowerInteractor powerInteractor;
    public final KeyguardRepositoryImpl repository;
    public final SecureSettings secureSettings;
    public final SelectedUserInteractor selectedUserInteractor;
    public final SystemSettingsImpl systemSettings;
    public int timeoutCounter;
    public final KeyguardTransitionInteractor transitionInteractor;

    public KeyguardWakeDirectlyToGoneInteractor(CoroutineScope coroutineScope, Context context, KeyguardRepositoryImpl keyguardRepositoryImpl, SystemClock systemClock, AlarmManager alarmManager, KeyguardTransitionInteractor keyguardTransitionInteractor, PowerInteractor powerInteractor, SecureSettings secureSettings, LockPatternUtils lockPatternUtils, SystemSettingsImpl systemSettingsImpl, SelectedUserInteractor selectedUserInteractor) {
        this.repository = keyguardRepositoryImpl;
        this.transitionInteractor = keyguardTransitionInteractor;
        this.powerInteractor = powerInteractor;
        this.secureSettings = secureSettings;
        this.lockPatternUtils = lockPatternUtils;
        this.systemSettings = systemSettingsImpl;
        this.selectedUserInteractor = selectedUserInteractor;
        final DistinctFlowImpl distinctFlowImpl = powerInteractor.isAwake;
        this.canWakeDirectlyToGone = FlowKt.distinctUntilChanged(FlowKt.combine(keyguardRepositoryImpl.isKeyguardEnabled, new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ KeyguardWakeDirectlyToGoneInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, KeyguardWakeDirectlyToGoneInteractor keyguardWakeDirectlyToGoneInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = keyguardWakeDirectlyToGoneInteractor;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L54
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Boolean r5 = (java.lang.Boolean) r5
                        r5.getClass()
                        com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor r5 = r4.this$0
                        com.android.systemui.user.domain.interactor.SelectedUserInteractor r6 = r5.selectedUserInteractor
                        int r6 = r6.getSelectedUserId()
                        com.android.internal.widget.LockPatternUtils r5 = r5.lockPatternUtils
                        boolean r5 = r5.isLockScreenDisabled(r6)
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L54
                        return r1
                    L54:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = DistinctFlowImpl.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, keyguardRepositoryImpl.biometricUnlockState, keyguardRepositoryImpl.canIgnoreAuthAndReturnToGone, new KeyguardWakeDirectlyToGoneInteractor$canWakeDirectlyToGone$1(5, null)));
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor$broadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                if ("com.android.internal.policy.impl.PhoneWindowManager.DELAYED_KEYGUARD".equals(intent.getAction())) {
                    int intExtra = intent.getIntExtra("count", 0);
                    KeyguardWakeDirectlyToGoneInteractor keyguardWakeDirectlyToGoneInteractor = KeyguardWakeDirectlyToGoneInteractor.this;
                    synchronized (this) {
                        if (keyguardWakeDirectlyToGoneInteractor.timeoutCounter == intExtra) {
                            KeyguardRepositoryImpl keyguardRepositoryImpl2 = keyguardWakeDirectlyToGoneInteractor.repository;
                            Boolean bool = Boolean.FALSE;
                            StateFlowImpl stateFlowImpl = keyguardRepositoryImpl2._canIgnoreAuthAndReturnToGone;
                            stateFlowImpl.getClass();
                            stateFlowImpl.updateState(null, bool);
                        }
                    }
                }
            }
        };
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardWakeDirectlyToGoneInteractor$setOrCancelAlarmFromWakefulness$1(this, null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new KeyguardWakeDirectlyToGoneInteractor$listenForWakeToClearCanIgnoreAuth$1(this, null), 3);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.android.internal.policy.impl.PhoneWindowManager.DELAYED_KEYGUARD");
        intentFilter.setPriority(1000);
        context.registerReceiver(broadcastReceiver, intentFilter, "com.android.systemui.permission.SELF", null, 2);
    }
}
