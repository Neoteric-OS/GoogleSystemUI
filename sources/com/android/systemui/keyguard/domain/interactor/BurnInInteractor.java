package com.android.systemui.keyguard.domain.interactor;

import android.content.Context;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.doze.util.BurnInHelperKt;
import com.android.systemui.doze.util.BurnInHelperWrapper;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BurnInInteractor {
    public final BurnInHelperWrapper burnInHelperWrapper;
    public final ConfigurationInteractor configurationInteractor;
    public final Context context;
    public final ReadonlyStateFlow deviceEntryIconXOffset;
    public final ReadonlyStateFlow deviceEntryIconYOffset;
    public final KeyguardInteractor keyguardInteractor;
    public final ReadonlyStateFlow udfpsProgress;

    public BurnInInteractor(Context context, BurnInHelperWrapper burnInHelperWrapper, CoroutineScope coroutineScope, ConfigurationInteractor configurationInteractor, KeyguardInteractor keyguardInteractor) {
        this.context = context;
        this.burnInHelperWrapper = burnInHelperWrapper;
        this.configurationInteractor = configurationInteractor;
        this.keyguardInteractor = keyguardInteractor;
        this.deviceEntryIconXOffset = FlowKt.stateIn(FlowKt.transformLatest(configurationInteractor.scaleForResolution, new BurnInInteractor$burnInOffsetDefinedInPixels$$inlined$flatMapLatest$1(R.dimen.udfps_burn_in_offset_x, this, null, true)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 0);
        this.deviceEntryIconYOffset = FlowKt.stateIn(FlowKt.transformLatest(configurationInteractor.scaleForResolution, new BurnInInteractor$burnInOffsetDefinedInPixels$$inlined$flatMapLatest$1(R.dimen.udfps_burn_in_offset_y, this, null, false)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 0);
        this.udfpsProgress = FlowKt.stateIn(FlowKt.mapLatest(new BurnInInteractor$udfpsProgress$1(this, null), keyguardInteractor.dozeTimeTick), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Float.valueOf(BurnInHelperKt.getBurnInProgressOffset()));
    }

    public final Flow burnIn(final int i) {
        ConfigurationInteractor configurationInteractor = this.configurationInteractor;
        ChannelFlowTransformLatest transformLatest = FlowKt.transformLatest(configurationInteractor.onAnyConfigurationChange, new BurnInInteractor$burnInOffset$$inlined$flatMapLatest$1(R.dimen.burn_in_prevention_offset_x, this, null, true));
        final ChannelFlowTransformLatest transformLatest2 = FlowKt.transformLatest(configurationInteractor.onAnyConfigurationChange, new BurnInInteractor$burnInOffset$$inlined$flatMapLatest$1(i, this, null, false));
        return FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(transformLatest, new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.BurnInInteractor$burnIn$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.BurnInInteractor$burnIn$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ int $yDimenResourceId$inlined;
                public final /* synthetic */ BurnInInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.BurnInInteractor$burnIn$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, BurnInInteractor burnInInteractor, int i) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = burnInInteractor;
                    this.$yDimenResourceId$inlined = i;
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
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.domain.interactor.BurnInInteractor$burnIn$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.domain.interactor.BurnInInteractor$burnIn$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.BurnInInteractor$burnIn$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.BurnInInteractor$burnIn$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.BurnInInteractor$burnIn$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L59
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Number r5 = (java.lang.Number) r5
                        int r5 = r5.intValue()
                        int r5 = r5 * 2
                        com.android.systemui.keyguard.domain.interactor.BurnInInteractor r6 = r4.this$0
                        android.content.Context r6 = r6.context
                        android.content.res.Resources r6 = r6.getResources()
                        int r2 = r4.$yDimenResourceId$inlined
                        int r6 = r6.getDimensionPixelSize(r2)
                        int r5 = r5 - r6
                        java.lang.Integer r6 = new java.lang.Integer
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L59
                        return r1
                    L59:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.BurnInInteractor$burnIn$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = ChannelFlowTransformLatest.this.collect(new AnonymousClass2(flowCollector, this, i), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, new BurnInInteractor$burnIn$2(this, null)));
    }
}
