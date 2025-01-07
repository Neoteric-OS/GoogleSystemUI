package com.android.systemui.qs.composefragment.viewmodel;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.IndentingPrintWriter;
import androidx.lifecycle.LifecycleCoroutineScopeImpl;
import com.android.systemui.Dumpable;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.qs.FooterActionsController;
import com.android.systemui.qs.footer.ui.viewmodel.FooterActionsViewModel;
import com.android.systemui.qs.ui.viewmodel.QuickSettingsContainerViewModel;
import com.android.systemui.shade.LargeScreenHeaderHelper;
import com.android.systemui.statusbar.StatusBarState;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.disableflags.data.model.DisableFlagsModel;
import com.android.systemui.statusbar.disableflags.data.repository.DisableFlagsRepositoryImpl;
import com.android.systemui.util.DumpUtilsKt;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.io.PrintWriter;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.CombineKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSFragmentComposeViewModel implements Dumpable {
    public final StateFlowImpl _heightOverride;
    public final StateFlowImpl _isSmallScreen;
    public final StateFlowImpl _panelFraction;
    public final StateFlowImpl _qsExpanded;
    public final StateFlowImpl _qsExpansion;
    public final StateFlowImpl _qsVisible;
    public final StateFlowImpl _squishinessFraction;
    public final StateFlowImpl _stackScrollerOverscrolling;
    public Runnable collapseExpandAccessibilityAction;
    public final QuickSettingsContainerViewModel containerViewModel;
    public final ReadonlyStateFlow expansionState;
    public final FooterActionsController footerActionsController;
    public final FooterActionsViewModel footerActionsViewModel;
    public final ReadonlyStateFlow heightOverride;
    public final LargeScreenHeaderHelper largeScreenHeaderHelper;
    public final ReadonlyStateFlow qqsHeaderHeight;
    public final ReadonlyStateFlow qsEnabled;
    public final ReadonlyStateFlow qsVisible;
    public final Resources resources;
    public final ReadonlyStateFlow statusBarState;
    public final SysuiStatusBarStateController sysuiStatusBarStateController;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class QSExpansionState {
        public final float progress;

        public QSExpansionState(float f) {
            this.progress = f;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof QSExpansionState) && Float.compare(this.progress, ((QSExpansionState) obj).progress) == 0;
        }

        public final int hashCode() {
            return Float.hashCode(this.progress);
        }

        public final String toString() {
            return "QSExpansionState(progress=" + this.progress + ")";
        }
    }

    public QSFragmentComposeViewModel(QuickSettingsContainerViewModel quickSettingsContainerViewModel, Resources resources, FooterActionsViewModel.Factory factory, FooterActionsController footerActionsController, SysuiStatusBarStateController sysuiStatusBarStateController, DisableFlagsRepositoryImpl disableFlagsRepositoryImpl, ConfigurationInteractor configurationInteractor, LargeScreenHeaderHelper largeScreenHeaderHelper, LifecycleCoroutineScopeImpl lifecycleCoroutineScopeImpl) {
        this.containerViewModel = quickSettingsContainerViewModel;
        this.resources = resources;
        this.footerActionsController = footerActionsController;
        this.sysuiStatusBarStateController = sysuiStatusBarStateController;
        this.largeScreenHeaderHelper = largeScreenHeaderHelper;
        FooterActionsViewModel create = factory.create(lifecycleCoroutineScopeImpl);
        BuildersKt.launch$default(lifecycleCoroutineScopeImpl, null, null, new QSFragmentComposeViewModel$footerActionsViewModel$1$1(this, null), 3);
        this.footerActionsViewModel = create;
        StateFlowKt.MutableStateFlow(new Rect());
        Boolean bool = Boolean.FALSE;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._qsExpanded = MutableStateFlow;
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this._qsVisible = MutableStateFlow2;
        this.qsVisible = new ReadonlyStateFlow(MutableStateFlow2);
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(Float.valueOf(-1.0f));
        this._qsExpansion = MutableStateFlow3;
        Float valueOf = Float.valueOf(0.0f);
        this._panelFraction = StateFlowKt.MutableStateFlow(valueOf);
        this._squishinessFraction = StateFlowKt.MutableStateFlow(valueOf);
        final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = configurationInteractor.onAnyConfigurationChange;
        this.qqsHeaderHeight = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ QSFragmentComposeViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, QSFragmentComposeViewModel qSFragmentComposeViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = qSFragmentComposeViewModel;
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
                        boolean r0 = r6 instanceof com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L6a
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        kotlin.Unit r5 = (kotlin.Unit) r5
                        com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel r5 = r4.this$0
                        android.content.res.Resources r6 = r5.resources
                        r2 = 2131034202(0x7f05005a, float:1.7678915E38)
                        boolean r6 = r6.getBoolean(r2)
                        if (r6 == 0) goto L43
                        r5 = 0
                        goto L5a
                    L43:
                        com.android.systemui.shade.LargeScreenHeaderHelper r5 = r5.largeScreenHeaderHelper
                        android.content.Context r5 = r5.context
                        android.content.res.Resources r6 = r5.getResources()
                        r2 = 2131166140(0x7f0703bc, float:1.7946517E38)
                        int r6 = r6.getDimensionPixelSize(r2)
                        int r5 = com.android.internal.policy.SystemBarUtils.getStatusBarHeight(r5)
                        int r5 = java.lang.Math.max(r6, r5)
                    L5a:
                        java.lang.Integer r6 = new java.lang.Integer
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L6a
                        return r1
                    L6a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, lifecycleCoroutineScopeImpl, SharingStarted.Companion.WhileSubscribed$default(3), 0);
        StateFlowKt.MutableStateFlow(bool);
        StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(bool);
        this._stackScrollerOverscrolling = MutableStateFlow4;
        final ReadonlyStateFlow readonlyStateFlow = disableFlagsRepositoryImpl.disableFlags;
        final int i = 0;
        this.qsEnabled = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2$2$1 r0 = (com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2$2$1 r0 = new com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4b
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.disableflags.data.model.DisableFlagsModel r5 = (com.android.systemui.statusbar.disableflags.data.model.DisableFlagsModel) r5
                        int r5 = r5.disable2
                        r5 = r5 & r3
                        if (r5 != 0) goto L3b
                        r5 = r3
                        goto L3c
                    L3b:
                        r5 = 0
                    L3c:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4b
                        return r1
                    L4b:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((StateFlow) readonlyStateFlow).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        final Flow[] flowArr = (Flow[]) readonlyStateFlow;
                        Object combineInternal = CombineKt.combineInternal(continuation, new Function0() { // from class: com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$combine$1$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return new Object[flowArr.length];
                            }
                        }, new QSFragmentComposeViewModel$special$$inlined$combine$1$3(3, null), flowCollector, flowArr);
                        if (combineInternal != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, lifecycleCoroutineScopeImpl, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.valueOf((((DisableFlagsModel) ((StateFlowImpl) disableFlagsRepositoryImpl.disableFlags.$$delegate_0).getValue()).disable2 & 1) == 0));
        StateFlowKt.MutableStateFlow(bool);
        StateFlowKt.MutableStateFlow(bool);
        this.statusBarState = FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new QSFragmentComposeViewModel$statusBarState$1(this, null)), lifecycleCoroutineScopeImpl, SharingStarted.Companion.WhileSubscribed$default(3), Integer.valueOf(((StatusBarStateControllerImpl) sysuiStatusBarStateController).mState));
        StateFlowKt.MutableStateFlow(0);
        StateFlowKt.MutableStateFlow(valueOf);
        StateFlowKt.MutableStateFlow(bool);
        StateFlowKt.MutableStateFlow(bool);
        StateFlowKt.MutableStateFlow(bool);
        StateFlowKt.MutableStateFlow(bool);
        this._isSmallScreen = StateFlowKt.MutableStateFlow(bool);
        StateFlowKt.MutableStateFlow(bool);
        StateFlowImpl MutableStateFlow5 = StateFlowKt.MutableStateFlow(-1);
        this._heightOverride = MutableStateFlow5;
        this.heightOverride = new ReadonlyStateFlow(MutableStateFlow5);
        final Flow[] flowArr = {MutableStateFlow4, MutableStateFlow, MutableStateFlow3};
        final int i2 = 1;
        this.expansionState = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2$2$1 r0 = (com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2$2$1 r0 = new com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4b
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.disableflags.data.model.DisableFlagsModel r5 = (com.android.systemui.statusbar.disableflags.data.model.DisableFlagsModel) r5
                        int r5 = r5.disable2
                        r5 = r5 & r3
                        if (r5 != 0) goto L3b
                        r5 = r3
                        goto L3c
                    L3b:
                        r5 = 0
                    L3c:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4b
                        return r1
                    L4b:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((StateFlow) flowArr).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        final Flow[] flowArr2 = (Flow[]) flowArr;
                        Object combineInternal = CombineKt.combineInternal(continuation, new Function0() { // from class: com.android.systemui.qs.composefragment.viewmodel.QSFragmentComposeViewModel$special$$inlined$combine$1$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return new Object[flowArr2.length];
                            }
                        }, new QSFragmentComposeViewModel$special$$inlined$combine$1$3(3, null), flowCollector, flowArr2);
                        if (combineInternal != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, lifecycleCoroutineScopeImpl, SharingStarted.Companion.WhileSubscribed$default(3), new QSExpansionState(0.0f));
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.append("Quick Settings state").println(":");
        asIndenting.increaseIndent();
        try {
            Boolean bool = (Boolean) this._qsExpanded.getValue();
            bool.getClass();
            DumpUtilsKt.println(asIndenting, "isQSExpanded", bool);
            Boolean bool2 = (Boolean) ((StateFlowImpl) this.qsVisible.$$delegate_0).getValue();
            bool2.getClass();
            DumpUtilsKt.println(asIndenting, "isQSVisible", bool2);
            DumpUtilsKt.println(asIndenting, "isQSEnabled", ((StateFlowImpl) this.qsEnabled.$$delegate_0).getValue());
            DumpUtilsKt.println(asIndenting, "isCustomizing", ((StateFlowImpl) this.containerViewModel.editModeViewModel.isEditing.$$delegate_0).getValue());
            asIndenting.decreaseIndent();
            asIndenting.append("Expansion state").println(":");
            asIndenting.increaseIndent();
            try {
                DumpUtilsKt.println(asIndenting, "qsExpansion", Float.valueOf(((Number) this._qsExpansion.getValue()).floatValue()));
                DumpUtilsKt.println(asIndenting, "panelExpansionFraction", Float.valueOf(((Number) this._panelFraction.getValue()).floatValue()));
                DumpUtilsKt.println(asIndenting, "squishinessFraction", Float.valueOf(((Number) this._squishinessFraction.getValue()).floatValue()));
                DumpUtilsKt.println(asIndenting, "expansionState", ((StateFlowImpl) this.expansionState.$$delegate_0).getValue());
                asIndenting.decreaseIndent();
                asIndenting.append("Shade state").println(":");
                asIndenting.increaseIndent();
                try {
                    Boolean bool3 = (Boolean) this._stackScrollerOverscrolling.getValue();
                    bool3.getClass();
                    DumpUtilsKt.println(asIndenting, "stackOverscrolling", bool3);
                    DumpUtilsKt.println(asIndenting, "statusBarState", StatusBarState.toString(((Number) ((StateFlowImpl) this.statusBarState.$$delegate_0).getValue()).intValue()));
                    Boolean bool4 = (Boolean) this._isSmallScreen.getValue();
                    bool4.getClass();
                    DumpUtilsKt.println(asIndenting, "isSmallScreen", bool4);
                    DumpUtilsKt.println(asIndenting, "heightOverride", ((Number) ((StateFlowImpl) this.heightOverride.$$delegate_0).getValue()).intValue() + "px");
                    DumpUtilsKt.println(asIndenting, "qqsHeaderHeight", ((StateFlowImpl) this.qqsHeaderHeight.$$delegate_0).getValue() + "px");
                } finally {
                }
            } finally {
            }
        } finally {
        }
    }

    public final StateFlow getStatusBarState() {
        return this.statusBarState;
    }
}
