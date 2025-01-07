package com.android.systemui.common.data.repository;

import android.content.Context;
import android.os.Handler;
import android.os.UserHandle;
import com.android.internal.content.PackageMonitor;
import com.android.systemui.common.shared.model.PackageChangeModel;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.internal.SubscriptionCountStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PackageUpdateMonitor extends PackageMonitor {
    public final SharedFlowImpl _packageChanged;
    public final Handler bgHandler;
    public final Context context;
    public boolean isActive;
    public final PackageUpdateLogger logger;
    public final SystemClock systemClock;
    public final UserHandle user;

    public PackageUpdateMonitor(UserHandle userHandle, CoroutineDispatcher coroutineDispatcher, Handler handler, Context context, CoroutineScope coroutineScope, PackageUpdateLogger packageUpdateLogger, SystemClock systemClock) {
        this.user = userHandle;
        this.bgHandler = handler;
        this.context = context;
        this.logger = packageUpdateLogger;
        this.systemClock = systemClock;
        SharedFlowImpl MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 100, null, 4);
        final SubscriptionCountStateFlow subscriptionCount = MutableSharedFlow$default.getSubscriptionCount();
        FlowKt.launchIn(FlowKt.flowOn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.common.data.repository.PackageUpdateMonitor$_packageChanged$lambda$1$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.common.data.repository.PackageUpdateMonitor$_packageChanged$lambda$1$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.common.data.repository.PackageUpdateMonitor$_packageChanged$lambda$1$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
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
                        boolean r0 = r6 instanceof com.android.systemui.common.data.repository.PackageUpdateMonitor$_packageChanged$lambda$1$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.common.data.repository.PackageUpdateMonitor$_packageChanged$lambda$1$$inlined$map$1$2$1 r0 = (com.android.systemui.common.data.repository.PackageUpdateMonitor$_packageChanged$lambda$1$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.common.data.repository.PackageUpdateMonitor$_packageChanged$lambda$1$$inlined$map$1$2$1 r0 = new com.android.systemui.common.data.repository.PackageUpdateMonitor$_packageChanged$lambda$1$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4c
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Number r5 = (java.lang.Number) r5
                        int r5 = r5.intValue()
                        if (r5 <= 0) goto L3c
                        r5 = r3
                        goto L3d
                    L3c:
                        r5 = 0
                    L3d:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4c
                        return r1
                    L4c:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.common.data.repository.PackageUpdateMonitor$_packageChanged$lambda$1$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                SubscriptionCountStateFlow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }), new PackageUpdateMonitor$_packageChanged$1$2(this, null), 0), coroutineDispatcher), coroutineScope);
        this._packageChanged = MutableSharedFlow$default;
    }

    public final void onPackageAdded(String str, int i) {
        super.onPackageAdded(str, i);
        SharedFlowImpl sharedFlowImpl = this._packageChanged;
        ((SystemClockImpl) this.systemClock).getClass();
        sharedFlowImpl.tryEmit(new PackageChangeModel.Installed(i, System.currentTimeMillis(), str));
    }

    public final boolean onPackageChanged(String str, int i, String[] strArr) {
        super.onPackageChanged(str, i, strArr);
        SharedFlowImpl sharedFlowImpl = this._packageChanged;
        ((SystemClockImpl) this.systemClock).getClass();
        sharedFlowImpl.tryEmit(new PackageChangeModel.Changed(i, System.currentTimeMillis(), str));
        return false;
    }

    public final void onPackageRemoved(String str, int i) {
        super.onPackageRemoved(str, i);
        SharedFlowImpl sharedFlowImpl = this._packageChanged;
        ((SystemClockImpl) this.systemClock).getClass();
        sharedFlowImpl.tryEmit(new PackageChangeModel.Uninstalled(i, System.currentTimeMillis(), str));
    }

    public final void onPackageUpdateFinished(String str, int i) {
        super.onPackageUpdateFinished(str, i);
        SharedFlowImpl sharedFlowImpl = this._packageChanged;
        ((SystemClockImpl) this.systemClock).getClass();
        sharedFlowImpl.tryEmit(new PackageChangeModel.UpdateFinished(i, System.currentTimeMillis(), str));
    }

    public final void onPackageUpdateStarted(String str, int i) {
        super.onPackageUpdateStarted(str, i);
        SharedFlowImpl sharedFlowImpl = this._packageChanged;
        ((SystemClockImpl) this.systemClock).getClass();
        sharedFlowImpl.tryEmit(new PackageChangeModel.UpdateStarted(i, System.currentTimeMillis(), str));
    }
}
