package com.android.systemui.keyguard.data.quickaffordance;

import android.os.UserHandle;
import com.android.app.tracing.FlowTracing;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shared.customization.data.content.CustomizationProviderClient;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordanceRemoteUserSelectionManager implements KeyguardQuickAffordanceSelectionManager {
    public final ReadonlyStateFlow _selections;
    public final KeyguardQuickAffordanceProviderClientFactoryImpl clientFactory;
    public final ReadonlyStateFlow clientOrNull;
    public final CoroutineScope scope;
    public final ReadonlyStateFlow selections;
    public final UserHandle userHandle;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 userId;
    public final UserTracker userTracker;

    public KeyguardQuickAffordanceRemoteUserSelectionManager(CoroutineScope coroutineScope, UserTracker userTracker, KeyguardQuickAffordanceProviderClientFactoryImpl keyguardQuickAffordanceProviderClientFactoryImpl, UserHandle userHandle) {
        this.scope = coroutineScope;
        this.userTracker = userTracker;
        this.clientFactory = keyguardQuickAffordanceProviderClientFactoryImpl;
        this.userHandle = userHandle;
        FlowTracing flowTracing = FlowTracing.INSTANCE;
        final Flow distinctUntilChanged = FlowKt.distinctUntilChanged(FlowTracing.tracedConflatedCallbackFlow("userId", new KeyguardQuickAffordanceRemoteUserSelectionManager$userId$1(this, null)));
        Flow flow = new Flow() { // from class: com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceRemoteUserSelectionManager$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceRemoteUserSelectionManager$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ KeyguardQuickAffordanceRemoteUserSelectionManager this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceRemoteUserSelectionManager$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, KeyguardQuickAffordanceRemoteUserSelectionManager keyguardQuickAffordanceRemoteUserSelectionManager) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = keyguardQuickAffordanceRemoteUserSelectionManager;
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
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceRemoteUserSelectionManager$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceRemoteUserSelectionManager$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceRemoteUserSelectionManager$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceRemoteUserSelectionManager$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceRemoteUserSelectionManager$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L68
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Number r5 = (java.lang.Number) r5
                        int r5 = r5.intValue()
                        com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceRemoteUserSelectionManager r6 = r4.this$0
                        android.os.UserHandle r2 = r6.userHandle
                        boolean r2 = r2.isSystem()
                        if (r2 == 0) goto L5c
                        android.os.UserHandle r2 = r6.userHandle
                        int r2 = r2.getIdentifier()
                        if (r2 == r5) goto L5c
                        com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceProviderClientFactoryImpl r5 = r6.clientFactory
                        com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl r6 = new com.android.systemui.shared.customization.data.content.CustomizationProviderClientImpl
                        com.android.systemui.settings.UserTracker r2 = r5.userTracker
                        com.android.systemui.settings.UserTrackerImpl r2 = (com.android.systemui.settings.UserTrackerImpl) r2
                        android.content.Context r2 = r2.getUserContext()
                        kotlinx.coroutines.CoroutineDispatcher r5 = r5.backgroundDispatcher
                        r6.<init>(r2, r5)
                        goto L5d
                    L5c:
                        r6 = 0
                    L5d:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L68
                        return r1
                    L68:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceRemoteUserSelectionManager$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        };
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(flow, coroutineScope, startedEagerly, null);
        this.clientOrNull = stateIn;
        ReadonlyStateFlow stateIn2 = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new KeyguardQuickAffordanceRemoteUserSelectionManager$special$$inlined$flatMapLatest$1(3, null)), coroutineScope, startedEagerly, MapsKt.emptyMap());
        this._selections = stateIn2;
        this.selections = stateIn2;
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceSelectionManager
    public final Map getSelections() {
        return (Map) ((StateFlowImpl) this._selections.$$delegate_0).getValue();
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceSelectionManager
    public final void setSelections(String str, List list) {
        CustomizationProviderClient customizationProviderClient = (CustomizationProviderClient) ((StateFlowImpl) this.clientOrNull.$$delegate_0).getValue();
        if (customizationProviderClient != null) {
            BuildersKt.launch$default(this.scope, null, null, new KeyguardQuickAffordanceRemoteUserSelectionManager$setSelections$1$1(customizationProviderClient, str, list, null), 3);
        }
    }

    @Override // com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceSelectionManager
    /* renamed from: getSelections */
    public final Flow mo824getSelections() {
        return this.selections;
    }
}
