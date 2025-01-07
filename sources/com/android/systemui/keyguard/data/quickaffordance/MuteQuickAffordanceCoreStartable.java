package com.android.systemui.keyguard.data.quickaffordance;

import com.android.systemui.CoreStartable;
import com.android.systemui.keyguard.data.repository.KeyguardQuickAffordanceRepository;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.util.RingerModeTrackerImpl;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MuteQuickAffordanceCoreStartable implements CoreStartable {
    public final CoroutineDispatcher backgroundDispatcher;
    public final CoroutineScope coroutineScope;
    public final KeyguardQuickAffordanceRepository keyguardQuickAffordanceRepository;
    public final MuteQuickAffordanceCoreStartable$observer$1 observer = new MuteQuickAffordanceCoreStartable$observer$1(this);
    public final RingerModeTrackerImpl ringerModeTracker;
    public final UserFileManager userFileManager;
    public final UserTracker userTracker;

    public MuteQuickAffordanceCoreStartable(UserTracker userTracker, RingerModeTrackerImpl ringerModeTrackerImpl, UserFileManager userFileManager, KeyguardQuickAffordanceRepository keyguardQuickAffordanceRepository, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        this.userTracker = userTracker;
        this.ringerModeTracker = ringerModeTrackerImpl;
        this.userFileManager = userFileManager;
        this.keyguardQuickAffordanceRepository = keyguardQuickAffordanceRepository;
        this.coroutineScope = coroutineScope;
        this.backgroundDispatcher = coroutineDispatcher;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        final ReadonlyStateFlow readonlyStateFlow = this.keyguardQuickAffordanceRepository.selections;
        FlowKt.launchIn(new Flow() { // from class: com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceCoreStartable$start$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceCoreStartable$start$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ MuteQuickAffordanceCoreStartable this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceCoreStartable$start$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, MuteQuickAffordanceCoreStartable muteQuickAffordanceCoreStartable) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = muteQuickAffordanceCoreStartable;
                }

                /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
                    /*
                        r7 = this;
                        boolean r0 = r9 instanceof com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceCoreStartable$start$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r9
                        com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceCoreStartable$start$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceCoreStartable$start$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceCoreStartable$start$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceCoreStartable$start$$inlined$map$1$2$1
                        r0.<init>(r9)
                    L18:
                        java.lang.Object r9 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        kotlin.Unit r3 = kotlin.Unit.INSTANCE
                        r4 = 1
                        if (r2 == 0) goto L32
                        if (r2 != r4) goto L2a
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto La0
                    L2a:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L32:
                        kotlin.ResultKt.throwOnFailure(r9)
                        java.util.Map r8 = (java.util.Map) r8
                        java.util.Collection r8 = r8.values()
                        java.lang.Iterable r8 = (java.lang.Iterable) r8
                        boolean r9 = r8 instanceof java.util.Collection
                        com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceCoreStartable r2 = r7.this$0
                        if (r9 == 0) goto L4d
                        r9 = r8
                        java.util.Collection r9 = (java.util.Collection) r9
                        boolean r9 = r9.isEmpty()
                        if (r9 == 0) goto L4d
                        goto L8c
                    L4d:
                        java.util.Iterator r8 = r8.iterator()
                    L51:
                        boolean r9 = r8.hasNext()
                        if (r9 == 0) goto L8c
                        java.lang.Object r9 = r8.next()
                        java.util.List r9 = (java.util.List) r9
                        if (r9 == 0) goto L66
                        boolean r5 = r9.isEmpty()
                        if (r5 == 0) goto L66
                        goto L51
                    L66:
                        java.util.Iterator r9 = r9.iterator()
                    L6a:
                        boolean r5 = r9.hasNext()
                        if (r5 == 0) goto L51
                        java.lang.Object r5 = r9.next()
                        com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig r5 = (com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceConfig) r5
                        java.lang.String r5 = r5.getKey()
                        java.lang.String r6 = "mute"
                        boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r6)
                        if (r5 == 0) goto L6a
                        com.android.systemui.util.RingerModeTrackerImpl r8 = r2.ringerModeTracker
                        com.android.systemui.util.RingerModeLiveData r8 = r8.ringerModeInternal
                        com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceCoreStartable$observer$1 r9 = r2.observer
                        r8.observeForever(r9)
                        goto L95
                    L8c:
                        com.android.systemui.util.RingerModeTrackerImpl r8 = r2.ringerModeTracker
                        com.android.systemui.util.RingerModeLiveData r8 = r8.ringerModeInternal
                        com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceCoreStartable$observer$1 r9 = r2.observer
                        r8.removeObserver(r9)
                    L95:
                        r0.label = r4
                        kotlinx.coroutines.flow.FlowCollector r7 = r7.$this_unsafeFlow
                        java.lang.Object r7 = r7.emit(r3, r0)
                        if (r7 != r1) goto La0
                        return r1
                    La0:
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceCoreStartable$start$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, this.coroutineScope);
    }
}
