package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.CoreStartable;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;
import com.android.systemui.statusbar.policy.domain.interactor.DeviceProvisioningInteractor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardTransitionBootInteractor implements CoreStartable {
    public final DeviceEntryInteractor deviceEntryInteractor;
    public final DeviceProvisioningInteractor deviceProvisioningInteractor;
    public final InternalKeyguardTransitionInteractor internalTransitionInteractor;
    public final KeyguardTransitionRepositoryImpl repository;
    public final CoroutineScope scope;
    public final Lazy showLockscreenOnBoot$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor$showLockscreenOnBoot$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            final KeyguardTransitionBootInteractor keyguardTransitionBootInteractor = KeyguardTransitionBootInteractor.this;
            final Flow flow = keyguardTransitionBootInteractor.deviceProvisioningInteractor.isDeviceProvisioned;
            return new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor$showLockscreenOnBoot$2$invoke$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor$showLockscreenOnBoot$2$invoke$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;
                    public final /* synthetic */ KeyguardTransitionBootInteractor this$0;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor$showLockscreenOnBoot$2$invoke$$inlined$map$1$2$1, reason: invalid class name */
                    public final class AnonymousClass1 extends ContinuationImpl {
                        Object L$0;
                        Object L$1;
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

                    public AnonymousClass2(FlowCollector flowCollector, KeyguardTransitionBootInteractor keyguardTransitionBootInteractor) {
                        this.$this_unsafeFlow = flowCollector;
                        this.this$0 = keyguardTransitionBootInteractor;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:21:0x009e A[RETURN] */
                    /* JADX WARN: Removed duplicated region for block: B:25:0x0076  */
                    /* JADX WARN: Removed duplicated region for block: B:28:0x008b A[RETURN] */
                    /* JADX WARN: Removed duplicated region for block: B:29:0x008c  */
                    /* JADX WARN: Removed duplicated region for block: B:30:0x004f  */
                    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object emit(java.lang.Object r9, kotlin.coroutines.Continuation r10) {
                        /*
                            r8 = this;
                            boolean r0 = r10 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor$showLockscreenOnBoot$2$invoke$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r10
                            com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor$showLockscreenOnBoot$2$invoke$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor$showLockscreenOnBoot$2$invoke$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor$showLockscreenOnBoot$2$invoke$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor$showLockscreenOnBoot$2$invoke$$inlined$map$1$2$1
                            r0.<init>(r10)
                        L18:
                            java.lang.Object r10 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 3
                            r4 = 2
                            r5 = 1
                            r6 = 0
                            if (r2 == 0) goto L4f
                            if (r2 == r5) goto L3f
                            if (r2 == r4) goto L37
                            if (r2 != r3) goto L2f
                            kotlin.ResultKt.throwOnFailure(r10)
                            goto L9f
                        L2f:
                            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                            r8.<init>(r9)
                            throw r8
                        L37:
                            java.lang.Object r8 = r0.L$0
                            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
                            kotlin.ResultKt.throwOnFailure(r10)
                            goto L8f
                        L3f:
                            java.lang.Object r8 = r0.L$1
                            kotlinx.coroutines.flow.FlowCollector r8 = (kotlinx.coroutines.flow.FlowCollector) r8
                            java.lang.Object r9 = r0.L$0
                            com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor$showLockscreenOnBoot$2$invoke$$inlined$map$1$2 r9 = (com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor$showLockscreenOnBoot$2$invoke$$inlined$map$1.AnonymousClass2) r9
                            kotlin.ResultKt.throwOnFailure(r10)
                            r7 = r10
                            r10 = r8
                            r8 = r9
                            r9 = r7
                            goto L6d
                        L4f:
                            kotlin.ResultKt.throwOnFailure(r10)
                            java.lang.Boolean r9 = (java.lang.Boolean) r9
                            boolean r9 = r9.booleanValue()
                            kotlinx.coroutines.flow.FlowCollector r10 = r8.$this_unsafeFlow
                            if (r9 != 0) goto L79
                            com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor r9 = r8.this$0
                            com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor r9 = r9.deviceEntryInteractor
                            r0.L$0 = r8
                            r0.L$1 = r10
                            r0.label = r5
                            java.lang.Object r9 = r9.isAuthenticationRequired(r0)
                            if (r9 != r1) goto L6d
                            return r1
                        L6d:
                            java.lang.Boolean r9 = (java.lang.Boolean) r9
                            boolean r9 = r9.booleanValue()
                            if (r9 == 0) goto L76
                            goto L79
                        L76:
                            java.lang.Boolean r8 = java.lang.Boolean.FALSE
                            goto L92
                        L79:
                            com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor r8 = r8.this$0
                            com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor r8 = r8.deviceEntryInteractor
                            r0.L$0 = r10
                            r0.L$1 = r6
                            r0.label = r4
                            com.android.systemui.deviceentry.data.repository.DeviceEntryRepositoryImpl r8 = r8.repository
                            java.lang.Object r8 = r8.isLockscreenEnabled(r0)
                            if (r8 != r1) goto L8c
                            return r1
                        L8c:
                            r7 = r10
                            r10 = r8
                            r8 = r7
                        L8f:
                            r7 = r10
                            r10 = r8
                            r8 = r7
                        L92:
                            r0.L$0 = r6
                            r0.L$1 = r6
                            r0.label = r3
                            java.lang.Object r8 = r10.emit(r8, r0)
                            if (r8 != r1) goto L9f
                            return r1
                        L9f:
                            kotlin.Unit r8 = kotlin.Unit.INSTANCE
                            return r8
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionBootInteractor$showLockscreenOnBoot$2$invoke$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                    Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, keyguardTransitionBootInteractor), continuation);
                    return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            };
        }
    });

    public KeyguardTransitionBootInteractor(CoroutineScope coroutineScope, DeviceEntryInteractor deviceEntryInteractor, DeviceProvisioningInteractor deviceProvisioningInteractor, InternalKeyguardTransitionInteractor internalKeyguardTransitionInteractor, KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl) {
        this.scope = coroutineScope;
        this.deviceEntryInteractor = deviceEntryInteractor;
        this.deviceProvisioningInteractor = deviceProvisioningInteractor;
        this.internalTransitionInteractor = internalKeyguardTransitionInteractor;
        this.repository = keyguardTransitionRepositoryImpl;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        BuildersKt.launch$default(this.scope, null, null, new KeyguardTransitionBootInteractor$start$1(this, null), 3);
    }
}
