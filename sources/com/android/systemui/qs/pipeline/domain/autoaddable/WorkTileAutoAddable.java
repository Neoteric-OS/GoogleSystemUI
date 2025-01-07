package com.android.systemui.qs.pipeline.domain.autoaddable;

import android.os.UserHandle;
import com.android.systemui.qs.pipeline.data.restoreprocessors.WorkTileRestoreProcessor;
import com.android.systemui.qs.pipeline.data.restoreprocessors.WorkTileRestoreProcessor$removeTrackingForUser$$inlined$filter$1;
import com.android.systemui.qs.pipeline.data.restoreprocessors.WorkTileRestoreProcessor$removeTrackingForUser$$inlined$map$1;
import com.android.systemui.qs.pipeline.domain.model.AutoAddTracking;
import com.android.systemui.qs.pipeline.domain.model.AutoAddable;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WorkTileAutoAddable implements AutoAddable {
    public final AutoAddTracking.Always autoAddTracking;
    public final String description;
    public final TileSpec spec = TileSpec.Companion.create("work");
    public final UserTracker userTracker;
    public final WorkTileRestoreProcessor workTileRestoreProcessor;

    public WorkTileAutoAddable(UserTracker userTracker, WorkTileRestoreProcessor workTileRestoreProcessor) {
        this.userTracker = userTracker;
        this.workTileRestoreProcessor = workTileRestoreProcessor;
        AutoAddTracking.Always always = AutoAddTracking.Always.INSTANCE;
        this.autoAddTracking = always;
        this.description = "WorkTileAutoAddable (" + always + ")";
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final Flow autoAddSignal(final int i) {
        final WorkTileRestoreProcessor$removeTrackingForUser$$inlined$map$1 workTileRestoreProcessor$removeTrackingForUser$$inlined$map$1 = new WorkTileRestoreProcessor$removeTrackingForUser$$inlined$map$1(new WorkTileRestoreProcessor$removeTrackingForUser$$inlined$filter$1(this.workTileRestoreProcessor._removeTrackingForUser, UserHandle.of(i)));
        return FlowKt.merge(new Flow() { // from class: com.android.systemui.qs.pipeline.domain.autoaddable.WorkTileAutoAddable$autoAddSignal$$inlined$mapNotNull$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.pipeline.domain.autoaddable.WorkTileAutoAddable$autoAddSignal$$inlined$mapNotNull$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ int $userId$inlined;
                public final /* synthetic */ WorkTileAutoAddable this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.pipeline.domain.autoaddable.WorkTileAutoAddable$autoAddSignal$$inlined$mapNotNull$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, WorkTileAutoAddable workTileAutoAddable, int i) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = workTileAutoAddable;
                    this.$userId$inlined = i;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof com.android.systemui.qs.pipeline.domain.autoaddable.WorkTileAutoAddable$autoAddSignal$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        com.android.systemui.qs.pipeline.domain.autoaddable.WorkTileAutoAddable$autoAddSignal$$inlined$mapNotNull$1$2$1 r0 = (com.android.systemui.qs.pipeline.domain.autoaddable.WorkTileAutoAddable$autoAddSignal$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.qs.pipeline.domain.autoaddable.WorkTileAutoAddable$autoAddSignal$$inlined$mapNotNull$1$2$1 r0 = new com.android.systemui.qs.pipeline.domain.autoaddable.WorkTileAutoAddable$autoAddSignal$$inlined$mapNotNull$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L91
                    L27:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r8)
                        kotlin.Unit r7 = (kotlin.Unit) r7
                        com.android.systemui.qs.pipeline.domain.autoaddable.WorkTileAutoAddable r7 = r6.this$0
                        com.android.systemui.settings.UserTracker r8 = r7.userTracker
                        com.android.systemui.settings.UserTrackerImpl r8 = (com.android.systemui.settings.UserTrackerImpl) r8
                        java.util.List r8 = r8.getUserProfiles()
                        if (r8 == 0) goto L47
                        boolean r2 = r8.isEmpty()
                        if (r2 == 0) goto L47
                        goto L83
                    L47:
                        java.util.Iterator r2 = r8.iterator()
                    L4b:
                        boolean r4 = r2.hasNext()
                        if (r4 == 0) goto L83
                        java.lang.Object r4 = r2.next()
                        android.content.pm.UserInfo r4 = (android.content.pm.UserInfo) r4
                        int r4 = r4.id
                        int r5 = r6.$userId$inlined
                        if (r4 != r5) goto L4b
                        boolean r2 = r8.isEmpty()
                        if (r2 == 0) goto L64
                        goto L7b
                    L64:
                        java.util.Iterator r8 = r8.iterator()
                    L68:
                        boolean r2 = r8.hasNext()
                        if (r2 == 0) goto L7b
                        java.lang.Object r2 = r8.next()
                        android.content.pm.UserInfo r2 = (android.content.pm.UserInfo) r2
                        boolean r2 = r2.isManagedProfile()
                        if (r2 == 0) goto L68
                        goto L83
                    L7b:
                        com.android.systemui.qs.pipeline.domain.model.AutoAddSignal$RemoveTracking r8 = new com.android.systemui.qs.pipeline.domain.model.AutoAddSignal$RemoveTracking
                        com.android.systemui.qs.pipeline.shared.TileSpec r7 = r7.spec
                        r8.<init>(r7)
                        goto L84
                    L83:
                        r8 = 0
                    L84:
                        if (r8 == 0) goto L91
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r6 = r6.$this_unsafeFlow
                        java.lang.Object r6 = r6.emit(r8, r0)
                        if (r6 != r1) goto L91
                        return r1
                    L91:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.domain.autoaddable.WorkTileAutoAddable$autoAddSignal$$inlined$mapNotNull$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = WorkTileRestoreProcessor$removeTrackingForUser$$inlined$map$1.this.collect(new AnonymousClass2(flowCollector, this, i), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, FlowConflatedKt.conflatedCallbackFlow(new WorkTileAutoAddable$autoAddSignal$signalsFromCallback$1(this, i, null)));
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final AutoAddTracking getAutoAddTracking() {
        return this.autoAddTracking;
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final String getDescription() {
        return this.description;
    }
}
