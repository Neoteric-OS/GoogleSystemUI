package com.android.systemui.statusbar.notification.stack.domain.interactor;

import android.content.Context;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.common.ui.data.repository.ConfigurationRepository;
import com.android.systemui.common.ui.data.repository.ConfigurationRepositoryImpl;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryUdfpsInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import dagger.Lazy;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SharedNotificationContainerInteractor {
    public final StateFlowImpl _notificationStackChanged;
    public final StateFlowImpl _topPosition;
    public final Flow configurationBasedDimensions;
    public final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 configurationChangeEvents;
    public final Context context;
    public final SharedNotificationContainerInteractor$special$$inlined$map$1 dimensionsUpdateEventsWithShouldUseSplitShade;
    public final Flow isSplitShadeEnabled;
    public final Flow notificationStackChanged;
    public final Lazy splitShadeStateController;
    public final ReadonlyStateFlow topPosition;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 useExtraShelfSpace;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ConfigurationBasedDimensions {
        public final int keyguardSplitShadeTopMargin;
        public final int marginBottom;
        public final int marginHorizontal;
        public final int marginTop;
        public final int marginTopLargeScreen;
        public final boolean useLargeScreenHeader;
        public final boolean useSplitShade;

        public ConfigurationBasedDimensions(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2) {
            this.useSplitShade = z;
            this.useLargeScreenHeader = z2;
            this.marginHorizontal = i;
            this.marginBottom = i2;
            this.marginTop = i3;
            this.marginTopLargeScreen = i4;
            this.keyguardSplitShadeTopMargin = i5;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ConfigurationBasedDimensions)) {
                return false;
            }
            ConfigurationBasedDimensions configurationBasedDimensions = (ConfigurationBasedDimensions) obj;
            return this.useSplitShade == configurationBasedDimensions.useSplitShade && this.useLargeScreenHeader == configurationBasedDimensions.useLargeScreenHeader && this.marginHorizontal == configurationBasedDimensions.marginHorizontal && this.marginBottom == configurationBasedDimensions.marginBottom && this.marginTop == configurationBasedDimensions.marginTop && this.marginTopLargeScreen == configurationBasedDimensions.marginTopLargeScreen && this.keyguardSplitShadeTopMargin == configurationBasedDimensions.keyguardSplitShadeTopMargin;
        }

        public final int hashCode() {
            return Integer.hashCode(this.keyguardSplitShadeTopMargin) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.marginTopLargeScreen, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.marginTop, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.marginBottom, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.marginHorizontal, TransitionData$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.useSplitShade) * 31, 31, this.useLargeScreenHeader), 31), 31), 31), 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("ConfigurationBasedDimensions(useSplitShade=");
            sb.append(this.useSplitShade);
            sb.append(", useLargeScreenHeader=");
            sb.append(this.useLargeScreenHeader);
            sb.append(", marginHorizontal=");
            sb.append(this.marginHorizontal);
            sb.append(", marginBottom=");
            sb.append(this.marginBottom);
            sb.append(", marginTop=");
            sb.append(this.marginTop);
            sb.append(", marginTopLargeScreen=");
            sb.append(this.marginTopLargeScreen);
            sb.append(", keyguardSplitShadeTopMargin=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.keyguardSplitShadeTopMargin, ")");
        }
    }

    public SharedNotificationContainerInteractor(ConfigurationRepository configurationRepository, Context context, Lazy lazy, Lazy lazy2, KeyguardInteractor keyguardInteractor, DeviceEntryUdfpsInteractor deviceEntryUdfpsInteractor, final Lazy lazy3) {
        this.context = context;
        this.splitShadeStateController = lazy;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(Float.valueOf(0.0f));
        this._topPosition = MutableStateFlow;
        this.topPosition = new ReadonlyStateFlow(MutableStateFlow);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(0L);
        this._notificationStackChanged = MutableStateFlow2;
        this.notificationStackChanged = FlowKt.debounce(MutableStateFlow2, 20L);
        final SharedNotificationContainerInteractor$special$$inlined$map$1 sharedNotificationContainerInteractor$special$$inlined$map$1 = new SharedNotificationContainerInteractor$special$$inlined$map$1(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new SharedNotificationContainerInteractor$configurationChangeEvents$1(2, null), ((ConfigurationRepositoryImpl) configurationRepository).onAnyConfigurationChange), this);
        final Flow distinctUntilChanged = FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ Lazy $largeScreenHeaderHelperLazy$inlined;
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ SharedNotificationContainerInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$2$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, SharedNotificationContainerInteractor sharedNotificationContainerInteractor, Lazy lazy) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = sharedNotificationContainerInteractor;
                    this.$largeScreenHeaderHelperLazy$inlined = lazy;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r13, kotlin.coroutines.Continuation r14) {
                    /*
                        r12 = this;
                        boolean r0 = r14 instanceof com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r14
                        com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$2$2$1 r0 = (com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$2$2$1 r0 = new com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$2$2$1
                        r0.<init>(r14)
                    L18:
                        java.lang.Object r14 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r14)
                        goto L91
                    L27:
                        java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                        java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
                        r12.<init>(r13)
                        throw r12
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r14)
                        java.lang.Boolean r13 = (java.lang.Boolean) r13
                        boolean r10 = r13.booleanValue()
                        com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor r13 = r12.this$0
                        android.content.Context r13 = r13.context
                        android.content.res.Resources r13 = r13.getResources()
                        com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$ConfigurationBasedDimensions r14 = new com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$ConfigurationBasedDimensions
                        r2 = 2131034202(0x7f05005a, float:1.7678915E38)
                        boolean r11 = r13.getBoolean(r2)
                        r2 = 2131167343(0x7f07086f, float:1.7948957E38)
                        int r5 = r13.getDimensionPixelSize(r2)
                        r2 = 2131167342(0x7f07086e, float:1.7948955E38)
                        int r6 = r13.getDimensionPixelSize(r2)
                        r2 = 2131167344(0x7f070870, float:1.7948959E38)
                        int r7 = r13.getDimensionPixelSize(r2)
                        dagger.Lazy r2 = r12.$largeScreenHeaderHelperLazy$inlined
                        java.lang.Object r2 = r2.get()
                        com.android.systemui.shade.LargeScreenHeaderHelper r2 = (com.android.systemui.shade.LargeScreenHeaderHelper) r2
                        android.content.Context r2 = r2.context
                        android.content.res.Resources r4 = r2.getResources()
                        r8 = 2131166140(0x7f0703bc, float:1.7946517E38)
                        int r4 = r4.getDimensionPixelSize(r8)
                        int r2 = com.android.internal.policy.SystemBarUtils.getStatusBarHeight(r2)
                        int r8 = java.lang.Math.max(r4, r2)
                        r2 = 2131166118(0x7f0703a6, float:1.7946472E38)
                        int r9 = r13.getDimensionPixelSize(r2)
                        r4 = r14
                        r4.<init>(r5, r6, r7, r8, r9, r10, r11)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r12 = r12.$this_unsafeFlow
                        java.lang.Object r12 = r12.emit(r14, r0)
                        if (r12 != r1) goto L91
                        return r1
                    L91:
                        kotlin.Unit r12 = kotlin.Unit.INSTANCE
                        return r12
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = SharedNotificationContainerInteractor$special$$inlined$map$1.this.collect(new AnonymousClass2(flowCollector, this, lazy3), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        });
        this.configurationBasedDimensions = distinctUntilChanged;
        this.useExtraShelfSpace = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(keyguardInteractor.ambientIndicationVisible, deviceEntryUdfpsInteractor.isUdfpsSupported, new SharedNotificationContainerInteractor$useExtraShelfSpace$1(3, null));
        this.isSplitShadeEnabled = FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$3

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$3$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$3$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$3.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$3$2$1 r0 = (com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$3.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$3$2$1 r0 = new com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$3$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L45
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$ConfigurationBasedDimensions r5 = (com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor.ConfigurationBasedDimensions) r5
                        boolean r5 = r5.useSplitShade
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L45
                        return r1
                    L45:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$special$$inlined$map$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        });
    }
}
