package com.android.systemui.keyguard.ui.viewmodel;

import android.content.res.Resources;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.shared.model.ClockSize;
import com.android.systemui.keyguard.ui.view.layout.sections.AodBurnInLayer;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.systemui.plugins.clocks.ClockFaceConfig;
import com.android.systemui.plugins.clocks.ClockFaceController;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel;
import com.android.systemui.statusbar.ui.SystemBarUtilsProxyImpl;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.enums.EnumEntriesKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StartedWhileSubscribed;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardClockViewModel {
    public AodBurnInLayer burnInLayer;
    public final ReadonlyStateFlow clockShouldBeCentered;
    public final ReadonlyStateFlow clockSize;
    public final ReadonlyStateFlow currentClock;
    public final ReadonlyStateFlow currentClockLayout;
    public final ReadonlyStateFlow hasAodIcons;
    public final ReadonlyStateFlow hasCustomPositionUpdatedAnimation;
    public final ReadonlyStateFlow hasCustomWeatherDataDisplay;
    public final ReadonlyStateFlow isLargeClockVisible;
    public final Resources resources;
    public final ShadeInteractor shadeInteractor;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 smallClockTopMargin;
    public final SystemBarUtilsProxyImpl systemBarUtils;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ClockLayout {
        public static final /* synthetic */ ClockLayout[] $VALUES;
        public static final ClockLayout LARGE_CLOCK;
        public static final ClockLayout SMALL_CLOCK;
        public static final ClockLayout SPLIT_SHADE_LARGE_CLOCK;
        public static final ClockLayout SPLIT_SHADE_SMALL_CLOCK;
        public static final ClockLayout SPLIT_SHADE_WEATHER_LARGE_CLOCK;
        public static final ClockLayout WEATHER_LARGE_CLOCK;

        static {
            ClockLayout clockLayout = new ClockLayout("LARGE_CLOCK", 0);
            LARGE_CLOCK = clockLayout;
            ClockLayout clockLayout2 = new ClockLayout("SMALL_CLOCK", 1);
            SMALL_CLOCK = clockLayout2;
            ClockLayout clockLayout3 = new ClockLayout("SPLIT_SHADE_LARGE_CLOCK", 2);
            SPLIT_SHADE_LARGE_CLOCK = clockLayout3;
            ClockLayout clockLayout4 = new ClockLayout("SPLIT_SHADE_SMALL_CLOCK", 3);
            SPLIT_SHADE_SMALL_CLOCK = clockLayout4;
            ClockLayout clockLayout5 = new ClockLayout("WEATHER_LARGE_CLOCK", 4);
            WEATHER_LARGE_CLOCK = clockLayout5;
            ClockLayout clockLayout6 = new ClockLayout("SPLIT_SHADE_WEATHER_LARGE_CLOCK", 5);
            SPLIT_SHADE_WEATHER_LARGE_CLOCK = clockLayout6;
            ClockLayout[] clockLayoutArr = {clockLayout, clockLayout2, clockLayout3, clockLayout4, clockLayout5, clockLayout6};
            $VALUES = clockLayoutArr;
            EnumEntriesKt.enumEntries(clockLayoutArr);
        }

        public static ClockLayout valueOf(String str) {
            return (ClockLayout) Enum.valueOf(ClockLayout.class, str);
        }

        public static ClockLayout[] values() {
            return (ClockLayout[]) $VALUES.clone();
        }
    }

    public KeyguardClockViewModel(KeyguardClockInteractor keyguardClockInteractor, CoroutineScope coroutineScope, NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel, ShadeInteractor shadeInteractor, SystemBarUtilsProxyImpl systemBarUtilsProxyImpl, ConfigurationInteractor configurationInteractor, Resources resources) {
        ClockFaceController largeClock;
        ClockFaceConfig config;
        this.shadeInteractor = shadeInteractor;
        this.systemBarUtils = systemBarUtilsProxyImpl;
        this.resources = resources;
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(keyguardClockInteractor.selectedClockSize, keyguardClockInteractor.clockSize, new KeyguardClockViewModel$clockSize$1(3, null));
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        final ReadonlyStateFlow stateIn = FlowKt.stateIn(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, coroutineScope, startedEagerly, ClockSize.LARGE);
        this.clockSize = stateIn;
        final int i = 0;
        ReadonlyStateFlow stateIn2 = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4a
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.keyguard.shared.model.ClockSize r5 = (com.android.systemui.keyguard.shared.model.ClockSize) r5
                        com.android.systemui.keyguard.shared.model.ClockSize r6 = com.android.systemui.keyguard.shared.model.ClockSize.LARGE
                        if (r5 != r6) goto L3a
                        r5 = r3
                        goto L3b
                    L3a:
                        r5 = 0
                    L3b:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        ((ReadonlyStateFlow) stateIn).collect(new AnonymousClass2(flowCollector), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = stateIn.collect(new KeyguardClockViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        }, coroutineScope, startedEagerly, Boolean.TRUE);
        this.isLargeClockVisible = stateIn2;
        ReadonlyStateFlow readonlyStateFlow = keyguardClockInteractor.currentClock;
        this.currentClock = readonlyStateFlow;
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$12 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(stateIn2, readonlyStateFlow, new KeyguardClockViewModel$hasCustomWeatherDataDisplay$1(3, null));
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        ClockController clockController = (ClockController) readonlyStateFlow.getValue();
        this.hasCustomWeatherDataDisplay = FlowKt.stateIn(flowKt__ZipKt$combine$$inlined$unsafeFlow$12, coroutineScope, WhileSubscribed$default, Boolean.valueOf((clockController == null || (largeClock = clockController.getLargeClock()) == null || (config = largeClock.getConfig()) == null) ? false : config.getHasCustomWeatherDataDisplay()));
        StartedWhileSubscribed WhileSubscribed$default2 = SharingStarted.Companion.WhileSubscribed$default(3);
        Boolean bool = Boolean.FALSE;
        ReadonlyStateFlow stateIn3 = FlowKt.stateIn(keyguardClockInteractor.clockShouldBeCentered, coroutineScope, WhileSubscribed$default2, bool);
        this.clockShouldBeCentered = stateIn3;
        final Flow flow = notificationIconContainerAlwaysOnDisplayViewModel.icons;
        final int i2 = 1;
        this.hasAodIcons = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4a
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.keyguard.shared.model.ClockSize r5 = (com.android.systemui.keyguard.shared.model.ClockSize) r5
                        com.android.systemui.keyguard.shared.model.ClockSize r6 = com.android.systemui.keyguard.shared.model.ClockSize.LARGE
                        if (r5 != r6) goto L3a
                        r5 = r3
                        goto L3b
                    L3a:
                        r5 = 0
                    L3b:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        ((ReadonlyStateFlow) flow).collect(new AnonymousClass2(flowCollector), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    default:
                        Object collect = flow.collect(new KeyguardClockViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        ShadeInteractorImpl shadeInteractorImpl = (ShadeInteractorImpl) shadeInteractor;
        FlowKt.stateIn(FlowKt.combine(stateIn2, stateIn3, shadeInteractorImpl.$$delegate_1.isShadeLayoutWide(), readonlyStateFlow, new KeyguardClockViewModel$currentClockLayout$1(5, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ClockLayout.SMALL_CLOCK);
        FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(readonlyStateFlow, stateIn2, new KeyguardClockViewModel$hasCustomPositionUpdatedAnimation$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(configurationInteractor.onAnyConfigurationChange, shadeInteractorImpl.$$delegate_1.isShadeLayoutWide(), new KeyguardClockViewModel$smallClockTopMargin$1(this, null));
    }

    public final ShadeInteractor getShadeInteractor() {
        return this.shadeInteractor;
    }

    public final int getSmallClockTopMargin() {
        return ((Boolean) ((ShadeInteractorImpl) this.shadeInteractor).$$delegate_1.isShadeLayoutWide().getValue()).booleanValue() ? this.resources.getDimensionPixelSize(R.dimen.keyguard_split_shade_top_margin) : this.resources.getDimensionPixelSize(R.dimen.keyguard_clock_top_margin) + this.systemBarUtils.getStatusBarHeaderHeightKeyguard();
    }
}
