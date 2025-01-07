package com.android.systemui.statusbar.notification.stack.ui.viewmodel;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.common.shared.model.NotificationContainerBounds;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerToGoneTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AodBurnInViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AodToGoneTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AodToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AodToOccludedTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DozingToGlanceableHubTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DozingToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DozingToOccludedTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.GlanceableHubToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.GoneToAodTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.GoneToDozingTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.GoneToDreamingTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.GoneToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToDreamingTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToGlanceableHubTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToGoneTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToOccludedTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToPrimaryBouncerTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.OccludedToAodTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.OccludedToGoneTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.OccludedToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.PrimaryBouncerToGoneTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.PrimaryBouncerToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.ViewStateAccessor;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.notification.stack.domain.interactor.NotificationStackAppearanceInteractor;
import com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor;
import com.android.systemui.util.kotlin.BooleanFlowOperators$not$$inlined$map$1;
import com.android.systemui.util.kotlin.FlowDumperImpl;
import com.android.systemui.util.kotlin.Utils;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.EmptyFlow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$4;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SharedNotificationContainerViewModel extends FlowDumperImpl {
    public final SafeFlow alphaForShadeAndQsExpansion;
    public final AlternateBouncerToGoneTransitionViewModel alternateBouncerToGoneTransitionViewModel;
    public final AodToGoneTransitionViewModel aodToGoneTransitionViewModel;
    public final AodToLockscreenTransitionViewModel aodToLockscreenTransitionViewModel;
    public final AodToOccludedTransitionViewModel aodToOccludedTransitionViewModel;
    public final SafeFlow availableHeight;
    public final Lazy bounds$delegate;
    public final CommunalSceneInteractor communalSceneInteractor;
    public final SafeFlow configurationBasedDimensions;
    public final DozingToLockscreenTransitionViewModel dozingToLockscreenTransitionViewModel;
    public final DozingToOccludedTransitionViewModel dozingToOccludedTransitionViewModel;
    public final DreamingToLockscreenTransitionViewModel dreamingToLockscreenTransitionViewModel;
    public final SafeFlow glanceableHubAlpha;
    public final GlanceableHubToLockscreenTransitionViewModel glanceableHubToLockscreenTransitionViewModel;
    public final GoneToAodTransitionViewModel goneToAodTransitionViewModel;
    public final GoneToDozingTransitionViewModel goneToDozingTransitionViewModel;
    public final GoneToDreamingTransitionViewModel goneToDreamingTransitionViewModel;
    public final GoneToLockscreenTransitionViewModel goneToLockscreenTransitionViewModel;
    public final SharedNotificationContainerInteractor interactor;
    public final ReadonlyStateFlow isAnyExpanded;
    public final ReadonlyStateFlow isDreamingWithoutShade;
    public final SafeFlow isOnGlanceableHub;
    public final ReadonlyStateFlow isOnGlanceableHubWithoutShade;
    public final ReadonlyStateFlow isOnLockscreen;
    public final ReadonlyStateFlow isOnLockscreenWithoutShade;
    public final SafeFlow isShadeLocked;
    public final KeyguardInteractor keyguardInteractor;
    public final KeyguardTransitionInteractor keyguardTransitionInteractor;
    public final LockscreenToDreamingTransitionViewModel lockscreenToDreamingTransitionViewModel;
    public final LockscreenToGlanceableHubTransitionViewModel lockscreenToGlanceableHubTransitionViewModel;
    public final LockscreenToGoneTransitionViewModel lockscreenToGoneTransitionViewModel;
    public final LockscreenToOccludedTransitionViewModel lockscreenToOccludedTransitionViewModel;
    public final LockscreenToPrimaryBouncerTransitionViewModel lockscreenToPrimaryBouncerTransitionViewModel;
    public final OccludedToAodTransitionViewModel occludedToAodTransitionViewModel;
    public final OccludedToGoneTransitionViewModel occludedToGoneTransitionViewModel;
    public final OccludedToLockscreenTransitionViewModel occludedToLockscreenTransitionViewModel;
    public final SafeFlow paddingTopDimen;
    public final ReadonlyStateFlow panelAlpha;
    public final PrimaryBouncerToGoneTransitionViewModel primaryBouncerToGoneTransitionViewModel;
    public final PrimaryBouncerToLockscreenTransitionViewModel primaryBouncerToLockscreenTransitionViewModel;
    public final ReadonlyStateFlow shadeCollapseFadeIn;
    public final ShadeInteractor shadeInteractor;
    public final SafeFlow translationX;
    public final SafeFlow translationY;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ConfigurationBasedDimensions {
        public final int marginBottom;
        public final int marginEnd;
        public final int marginStart;
        public final int marginTop;
        public final boolean useSplitShade;

        public ConfigurationBasedDimensions(boolean z, int i, int i2, int i3, int i4) {
            this.marginStart = i;
            this.marginTop = i2;
            this.marginEnd = i3;
            this.marginBottom = i4;
            this.useSplitShade = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ConfigurationBasedDimensions)) {
                return false;
            }
            ConfigurationBasedDimensions configurationBasedDimensions = (ConfigurationBasedDimensions) obj;
            return this.marginStart == configurationBasedDimensions.marginStart && this.marginTop == configurationBasedDimensions.marginTop && this.marginEnd == configurationBasedDimensions.marginEnd && this.marginBottom == configurationBasedDimensions.marginBottom && this.useSplitShade == configurationBasedDimensions.useSplitShade;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.useSplitShade) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.marginBottom, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.marginEnd, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.marginTop, Integer.hashCode(this.marginStart) * 31, 31), 31), 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("ConfigurationBasedDimensions(marginStart=");
            sb.append(this.marginStart);
            sb.append(", marginTop=");
            sb.append(this.marginTop);
            sb.append(", marginEnd=");
            sb.append(this.marginEnd);
            sb.append(", marginBottom=");
            sb.append(this.marginBottom);
            sb.append(", useSplitShade=");
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.useSplitShade, ")");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SharedNotificationContainerViewModel(SharedNotificationContainerInteractor sharedNotificationContainerInteractor, DumpManager dumpManager, final CoroutineScope coroutineScope, KeyguardInteractor keyguardInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, ShadeInteractor shadeInteractor, NotificationStackAppearanceInteractor notificationStackAppearanceInteractor, AlternateBouncerToGoneTransitionViewModel alternateBouncerToGoneTransitionViewModel, AodToGoneTransitionViewModel aodToGoneTransitionViewModel, AodToLockscreenTransitionViewModel aodToLockscreenTransitionViewModel, AodToOccludedTransitionViewModel aodToOccludedTransitionViewModel, DozingToGlanceableHubTransitionViewModel dozingToGlanceableHubTransitionViewModel, DozingToLockscreenTransitionViewModel dozingToLockscreenTransitionViewModel, DozingToOccludedTransitionViewModel dozingToOccludedTransitionViewModel, DreamingToLockscreenTransitionViewModel dreamingToLockscreenTransitionViewModel, GlanceableHubToLockscreenTransitionViewModel glanceableHubToLockscreenTransitionViewModel, GoneToAodTransitionViewModel goneToAodTransitionViewModel, GoneToDozingTransitionViewModel goneToDozingTransitionViewModel, GoneToDreamingTransitionViewModel goneToDreamingTransitionViewModel, GoneToLockscreenTransitionViewModel goneToLockscreenTransitionViewModel, LockscreenToDreamingTransitionViewModel lockscreenToDreamingTransitionViewModel, LockscreenToGlanceableHubTransitionViewModel lockscreenToGlanceableHubTransitionViewModel, LockscreenToGoneTransitionViewModel lockscreenToGoneTransitionViewModel, LockscreenToPrimaryBouncerTransitionViewModel lockscreenToPrimaryBouncerTransitionViewModel, LockscreenToOccludedTransitionViewModel lockscreenToOccludedTransitionViewModel, OccludedToAodTransitionViewModel occludedToAodTransitionViewModel, OccludedToGoneTransitionViewModel occludedToGoneTransitionViewModel, OccludedToLockscreenTransitionViewModel occludedToLockscreenTransitionViewModel, PrimaryBouncerToGoneTransitionViewModel primaryBouncerToGoneTransitionViewModel, PrimaryBouncerToLockscreenTransitionViewModel primaryBouncerToLockscreenTransitionViewModel, AodBurnInViewModel aodBurnInViewModel, CommunalSceneInteractor communalSceneInteractor) {
        super(dumpManager);
        final int i = 1;
        final int i2 = 0;
        final int i3 = 2;
        this.interactor = sharedNotificationContainerInteractor;
        this.keyguardInteractor = keyguardInteractor;
        this.keyguardTransitionInteractor = keyguardTransitionInteractor;
        this.shadeInteractor = shadeInteractor;
        this.alternateBouncerToGoneTransitionViewModel = alternateBouncerToGoneTransitionViewModel;
        this.aodToGoneTransitionViewModel = aodToGoneTransitionViewModel;
        this.aodToLockscreenTransitionViewModel = aodToLockscreenTransitionViewModel;
        this.aodToOccludedTransitionViewModel = aodToOccludedTransitionViewModel;
        this.dozingToLockscreenTransitionViewModel = dozingToLockscreenTransitionViewModel;
        this.dozingToOccludedTransitionViewModel = dozingToOccludedTransitionViewModel;
        this.dreamingToLockscreenTransitionViewModel = dreamingToLockscreenTransitionViewModel;
        this.glanceableHubToLockscreenTransitionViewModel = glanceableHubToLockscreenTransitionViewModel;
        this.goneToAodTransitionViewModel = goneToAodTransitionViewModel;
        this.goneToDozingTransitionViewModel = goneToDozingTransitionViewModel;
        this.goneToDreamingTransitionViewModel = goneToDreamingTransitionViewModel;
        this.goneToLockscreenTransitionViewModel = goneToLockscreenTransitionViewModel;
        this.lockscreenToDreamingTransitionViewModel = lockscreenToDreamingTransitionViewModel;
        this.lockscreenToGlanceableHubTransitionViewModel = lockscreenToGlanceableHubTransitionViewModel;
        this.lockscreenToGoneTransitionViewModel = lockscreenToGoneTransitionViewModel;
        this.lockscreenToPrimaryBouncerTransitionViewModel = lockscreenToPrimaryBouncerTransitionViewModel;
        this.lockscreenToOccludedTransitionViewModel = lockscreenToOccludedTransitionViewModel;
        this.occludedToAodTransitionViewModel = occludedToAodTransitionViewModel;
        this.occludedToGoneTransitionViewModel = occludedToGoneTransitionViewModel;
        this.occludedToLockscreenTransitionViewModel = occludedToLockscreenTransitionViewModel;
        this.primaryBouncerToGoneTransitionViewModel = primaryBouncerToGoneTransitionViewModel;
        this.primaryBouncerToLockscreenTransitionViewModel = primaryBouncerToLockscreenTransitionViewModel;
        this.communalSceneInteractor = communalSceneInteractor;
        ShadeInteractorImpl shadeInteractorImpl = (ShadeInteractorImpl) shadeInteractor;
        final StateFlow shadeExpansion = shadeInteractorImpl.baseShadeInteractor.getShadeExpansion();
        Flow flow = new Flow() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4f
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Number r5 = (java.lang.Number) r5
                        float r5 = r5.floatValue()
                        r6 = 0
                        int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                        if (r5 <= 0) goto L3f
                        r5 = r3
                        goto L40
                    L3f:
                        r5 = 0
                    L40:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4f
                        return r1
                    L4f:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = shadeExpansion.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = shadeExpansion.collect(new SharedNotificationContainerViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = shadeExpansion.collect(new SharedNotificationContainerViewModel$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect4 = shadeExpansion.collect(new SharedNotificationContainerViewModel$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        };
        final StateFlow qsExpansion = shadeInteractorImpl.baseShadeInteractor.getQsExpansion();
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(flow, new Flow() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4f
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Number r5 = (java.lang.Number) r5
                        float r5 = r5.floatValue()
                        r6 = 0
                        int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                        if (r5 <= 0) goto L3f
                        r5 = r3
                        goto L40
                    L3f:
                        r5 = 0
                    L40:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4f
                        return r1
                    L4f:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = qsExpansion.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = qsExpansion.collect(new SharedNotificationContainerViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = qsExpansion.collect(new SharedNotificationContainerViewModel$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect4 = qsExpansion.collect(new SharedNotificationContainerViewModel$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, new SharedNotificationContainerViewModel$isAnyExpanded$3(3, null));
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        Boolean bool = Boolean.FALSE;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, coroutineScope, startedEagerly, bool);
        final StateFlow stateFlow = keyguardInteractor.statusBarState;
        this.isShadeLocked = (SafeFlow) dumpWhileCollecting(FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(new Flow() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4f
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Number r5 = (java.lang.Number) r5
                        float r5 = r5.floatValue()
                        r6 = 0
                        int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                        if (r5 <= 0) goto L3f
                        r5 = r3
                        goto L40
                    L3f:
                        r5 = 0
                    L40:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4f
                        return r1
                    L4f:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = stateFlow.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = stateFlow.collect(new SharedNotificationContainerViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = stateFlow.collect(new SharedNotificationContainerViewModel$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect4 = stateFlow.collect(new SharedNotificationContainerViewModel$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, stateIn, new SharedNotificationContainerViewModel$isShadeLocked$2(3, null)), coroutineScope, startedEagerly, bool), "isShadeLocked");
        final Flow flow2 = sharedNotificationContainerInteractor.configurationBasedDimensions;
        final int i4 = 0;
        this.paddingTopDimen = (SafeFlow) dumpWhileCollecting(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4$2$1 r0 = (com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4$2$1 r0 = new com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4d
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$ConfigurationBasedDimensions r5 = (com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor.ConfigurationBasedDimensions) r5
                        boolean r6 = r5.useLargeScreenHeader
                        if (r6 == 0) goto L3b
                        int r5 = r5.marginTopLargeScreen
                        goto L3d
                    L3b:
                        int r5 = r5.marginTop
                    L3d:
                        java.lang.Integer r6 = new java.lang.Integer
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L4d
                        return r1
                    L4d:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i4) {
                    case 0:
                        Object collect = flow2.collect(new AnonymousClass2(flowCollector), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    case 1:
                        Object collect2 = flow2.collect(new SharedNotificationContainerViewModel$special$$inlined$map$5$2(flowCollector), continuation);
                        return collect2 == CoroutineSingletons.COROUTINE_SUSPENDED ? collect2 : Unit.INSTANCE;
                    default:
                        ((ReadonlyStateFlow) flow2).collect(new SharedNotificationContainerViewModel$special$$inlined$map$7$2(flowCollector), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                }
            }
        }), "paddingTopDimen");
        final int i5 = 1;
        this.configurationBasedDimensions = (SafeFlow) dumpWhileCollecting(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4$2$1 r0 = (com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4$2$1 r0 = new com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4d
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$ConfigurationBasedDimensions r5 = (com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor.ConfigurationBasedDimensions) r5
                        boolean r6 = r5.useLargeScreenHeader
                        if (r6 == 0) goto L3b
                        int r5 = r5.marginTopLargeScreen
                        goto L3d
                    L3b:
                        int r5 = r5.marginTop
                    L3d:
                        java.lang.Integer r6 = new java.lang.Integer
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L4d
                        return r1
                    L4d:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i5) {
                    case 0:
                        Object collect = flow2.collect(new AnonymousClass2(flowCollector), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    case 1:
                        Object collect2 = flow2.collect(new SharedNotificationContainerViewModel$special$$inlined$map$5$2(flowCollector), continuation);
                        return collect2 == CoroutineSingletons.COROUTINE_SUSPENDED ? collect2 : Unit.INSTANCE;
                    default:
                        ((ReadonlyStateFlow) flow2).collect(new SharedNotificationContainerViewModel$special$$inlined$map$7$2(flowCollector), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                }
            }
        }), "configurationBasedDimensions");
        Flow isFinishedIn$1 = keyguardTransitionInteractor.isFinishedIn$1(KeyguardState.AOD);
        Flow isFinishedIn$12 = keyguardTransitionInteractor.isFinishedIn$1(KeyguardState.DOZING);
        Flow isFinishedIn$13 = keyguardTransitionInteractor.isFinishedIn$1(KeyguardState.ALTERNATE_BOUNCER);
        SceneKey sceneKey = Scenes.Bouncer;
        ReadonlyStateFlow stateIn2 = FlowKt.stateIn(FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(2, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{isFinishedIn$1, isFinishedIn$12, isFinishedIn$13, keyguardTransitionInteractor.isFinishedIn(KeyguardState.PRIMARY_BOUNCER), new SharedNotificationContainerViewModel$special$$inlined$map$6(keyguardTransitionInteractor.getTransitionValueFlow(KeyguardState.LOCKSCREEN), 0)})).toArray(new Flow[0]))), coroutineScope, startedEagerly, bool);
        dumpValue(stateIn2, "isOnLockscreen");
        this.isOnLockscreen = stateIn2;
        ReadonlyStateFlow stateIn3 = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(stateIn2, stateIn, new SharedNotificationContainerViewModel$isOnLockscreenWithoutShade$1(3, null)), coroutineScope, startedEagerly, bool);
        dumpValue(stateIn3, "isOnLockscreenWithoutShade");
        this.isOnLockscreenWithoutShade = stateIn3;
        SceneKey sceneKey2 = Scenes.Communal;
        KeyguardState keyguardState = KeyguardState.GLANCEABLE_HUB;
        Flow isFinishedIn = keyguardTransitionInteractor.isFinishedIn(keyguardState);
        Edge.Companion companion = Edge.Companion;
        StateFlow stateIn4 = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(dumpWhileCollecting(FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(isFinishedIn, FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(2, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{keyguardTransitionInteractor.isInTransition(Edge.Companion.create$default(sceneKey2), Edge.Companion.create$default(null, keyguardState, 1)), keyguardTransitionInteractor.isInTransition(new Edge.SceneToState(sceneKey2, null), Edge.Companion.create$default(keyguardState, null, 2))})).toArray(new Flow[0]))), new SharedNotificationContainerViewModel$isOnGlanceableHub$1(3, null))), "isOnGlanceableHub"), stateIn, new SharedNotificationContainerViewModel$isOnGlanceableHubWithoutShade$1(3, null)), coroutineScope, startedEagerly, bool);
        dumpValue(stateIn4, "isOnGlanceableHubWithoutShade");
        StateFlow stateIn5 = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(keyguardTransitionInteractor.isFinishedIn$1(KeyguardState.DREAMING), stateIn, new SharedNotificationContainerViewModel$isDreamingWithoutShade$1(3, null)), coroutineScope, startedEagerly, bool);
        dumpValue(stateIn5, "isDreamingWithoutShade");
        ReadonlyStateFlow stateIn6 = FlowKt.stateIn(new SafeFlow(new SharedNotificationContainerViewModel$shadeCollapseFadeIn$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        dumpValue(stateIn6, "shadeCollapseFadeIn");
        this.shadeCollapseFadeIn = stateIn6;
        Lazy lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$bounds$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$bounds$2$1, reason: invalid class name */
            final class AnonymousClass1 extends SuspendLambda implements Function2 {
                private /* synthetic */ Object L$0;
                int label;

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(2, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        FlowCollector flowCollector = (FlowCollector) this.L$0;
                        Triple triple = new Triple(new Float(0.0f), Boolean.FALSE, new Float(0.0f));
                        this.label = 1;
                        if (flowCollector.emit(triple, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$bounds$2$2, reason: invalid class name */
            final class AnonymousClass2 extends SuspendLambda implements Function5 {
                /* synthetic */ int I$0;
                /* synthetic */ Object L$0;
                /* synthetic */ Object L$1;
                /* synthetic */ boolean Z$0;
                int label;

                @Override // kotlin.jvm.functions.Function5
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    int intValue = ((Number) obj3).intValue();
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(5, (Continuation) obj5);
                    anonymousClass2.Z$0 = booleanValue;
                    anonymousClass2.L$0 = (NotificationContainerBounds) obj2;
                    anonymousClass2.I$0 = intValue;
                    anonymousClass2.L$1 = (Triple) obj4;
                    return anonymousClass2.invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    boolean z = this.Z$0;
                    NotificationContainerBounds notificationContainerBounds = (NotificationContainerBounds) this.L$0;
                    int i = this.I$0;
                    Triple triple = (Triple) this.L$1;
                    float floatValue = ((Number) triple.component1()).floatValue();
                    boolean booleanValue = ((Boolean) triple.component2()).booleanValue();
                    float floatValue2 = ((Number) triple.component3()).floatValue();
                    boolean z2 = false;
                    if (z) {
                        return NotificationContainerBounds.copy$default(notificationContainerBounds, notificationContainerBounds.top - i, 0.0f, false, 6);
                    }
                    if (floatValue2 == 0.0f && !booleanValue) {
                        z2 = true;
                    }
                    return NotificationContainerBounds.copy$default(notificationContainerBounds, floatValue, 0.0f, z2, 2);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                SharedNotificationContainerViewModel sharedNotificationContainerViewModel = SharedNotificationContainerViewModel.this;
                ReadonlyStateFlow readonlyStateFlow = sharedNotificationContainerViewModel.isOnLockscreenWithoutShade;
                StateFlow stateFlow2 = (StateFlow) sharedNotificationContainerViewModel.keyguardInteractor.notificationContainerBounds$delegate.getValue();
                SharedNotificationContainerViewModel sharedNotificationContainerViewModel2 = SharedNotificationContainerViewModel.this;
                ReadonlyStateFlow stateIn7 = FlowKt.stateIn(FlowKt.combine(readonlyStateFlow, stateFlow2, sharedNotificationContainerViewModel2.paddingTopDimen, new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new AnonymousClass1(2, null), Utils.Companion.sample(sharedNotificationContainerViewModel2.interactor.topPosition, sharedNotificationContainerViewModel2.keyguardTransitionInteractor.isInTransition, ((ShadeInteractorImpl) sharedNotificationContainerViewModel2.shadeInteractor).baseShadeInteractor.getQsExpansion())), new AnonymousClass2(5, null)), coroutineScope, SharingStarted.Companion.Lazily, new NotificationContainerBounds());
                sharedNotificationContainerViewModel.dumpValue(stateIn7, "bounds");
                return stateIn7;
            }
        });
        this.bounds$delegate = lazy;
        this.alphaForShadeAndQsExpansion = (SafeFlow) dumpWhileCollecting(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new SharedNotificationContainerViewModel$alphaForShadeAndQsExpansion$2(2, null), FlowKt.transformLatest(flow2, new SharedNotificationContainerViewModel$special$$inlined$flatMapLatest$1(this, null))), "alphaForShadeAndQsExpansion");
        this.panelAlpha = keyguardInteractor.panelAlpha;
        this.glanceableHubAlpha = (SafeFlow) dumpWhileCollecting(FlowKt.distinctUntilChanged(new SafeFlow(new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$4(new Flow[]{stateIn4, stateIn2, stateIn5, new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new SharedNotificationContainerViewModel$glanceableHubAlpha$1(2, null), FlowKt.merge(lockscreenToGlanceableHubTransitionViewModel.notificationAlpha, glanceableHubToLockscreenTransitionViewModel.notificationAlpha, dozingToGlanceableHubTransitionViewModel.notificationAlpha))}, null, new SharedNotificationContainerViewModel$glanceableHubAlpha$2(null)))), "glanceableHubAlpha");
        final ReadonlyStateFlow readonlyStateFlow = aodBurnInViewModel.movement;
        final int i6 = 2;
        this.translationY = (SafeFlow) dumpWhileCollecting(FlowKt.combine(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new SharedNotificationContainerViewModel$translationY$2(2, null), new Flow() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4$2$1 r0 = (com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4$2$1 r0 = new com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4d
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor$ConfigurationBasedDimensions r5 = (com.android.systemui.statusbar.notification.stack.domain.interactor.SharedNotificationContainerInteractor.ConfigurationBasedDimensions) r5
                        boolean r6 = r5.useLargeScreenHeader
                        if (r6 == 0) goto L3b
                        int r5 = r5.marginTopLargeScreen
                        goto L3d
                    L3b:
                        int r5 = r5.marginTop
                    L3d:
                        java.lang.Integer r6 = new java.lang.Integer
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L4d
                        return r1
                    L4d:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$4.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i6) {
                    case 0:
                        Object collect = readonlyStateFlow.collect(new AnonymousClass2(flowCollector), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    case 1:
                        Object collect2 = readonlyStateFlow.collect(new SharedNotificationContainerViewModel$special$$inlined$map$5$2(flowCollector), continuation);
                        return collect2 == CoroutineSingletons.COROUTINE_SUSPENDED ? collect2 : Unit.INSTANCE;
                    default:
                        ((ReadonlyStateFlow) readonlyStateFlow).collect(new SharedNotificationContainerViewModel$special$$inlined$map$7$2(flowCollector), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                }
            }
        }), stateIn3, FlowKt.merge(keyguardInteractor.keyguardTranslationY, occludedToLockscreenTransitionViewModel.lockscreenTranslationY), new SharedNotificationContainerViewModel$translationY$3(4, null)), "translationY");
        this.translationX = (SafeFlow) dumpWhileCollecting(FlowKt.merge(lockscreenToGlanceableHubTransitionViewModel.notificationTranslationX, glanceableHubToLockscreenTransitionViewModel.notificationTranslationX, EmptyFlow.INSTANCE), "translationX");
        final StateFlow stateFlow2 = (StateFlow) lazy.getValue();
        final int i7 = 3;
        this.availableHeight = (SafeFlow) dumpWhileCollecting(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4f
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Number r5 = (java.lang.Number) r5
                        float r5 = r5.floatValue()
                        r6 = 0
                        int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                        if (r5 <= 0) goto L3f
                        r5 = r3
                        goto L40
                    L3f:
                        r5 = 0
                    L40:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4f
                        return r1
                    L4f:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i7) {
                    case 0:
                        Object collect = stateFlow2.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = stateFlow2.collect(new SharedNotificationContainerViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 2:
                        Object collect3 = stateFlow2.collect(new SharedNotificationContainerViewModel$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect4 = stateFlow2.collect(new SharedNotificationContainerViewModel$special$$inlined$map$9$2(flowCollector), continuation);
                        if (collect4 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }), "availableHeight");
    }

    public final SafeFlow getMaxNotifications(Function2 function2) {
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 combine = FlowKt.combine(this.isOnLockscreen, this.keyguardInteractor.statusBarState, new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new SharedNotificationContainerViewModel$getMaxNotifications$showUnlimitedNotifications$1(2, null), FlowKt.merge(this.primaryBouncerToGoneTransitionViewModel.showAllNotifications, this.alternateBouncerToGoneTransitionViewModel.showAllNotifications)), new SharedNotificationContainerViewModel$getMaxNotifications$showUnlimitedNotifications$2(4, null));
        ReadonlyStateFlow readonlyStateFlow = ((ShadeInteractorImpl) this.shadeInteractor).isUserInteracting;
        SharedNotificationContainerInteractor sharedNotificationContainerInteractor = this.interactor;
        return (SafeFlow) dumpWhileCollecting(FlowKt.distinctUntilChanged(new SafeFlow(new SharedNotificationContainerViewModel$getMaxNotifications$$inlined$combineTransform$1(new Flow[]{this.isOnLockscreenWithoutShade, combine, readonlyStateFlow, this.availableHeight, sharedNotificationContainerInteractor.notificationStackChanged, sharedNotificationContainerInteractor.useExtraShelfSpace}, null, function2))), "maxNotifications");
    }

    public final SafeFlow keyguardAlpha(ViewStateAccessor viewStateAccessor, CoroutineScope coroutineScope) {
        int i = 2;
        KeyguardState keyguardState = KeyguardState.OCCLUDED;
        KeyguardTransitionInteractor keyguardTransitionInteractor = this.keyguardTransitionInteractor;
        SharedNotificationContainerViewModel$special$$inlined$map$6 sharedNotificationContainerViewModel$special$$inlined$map$6 = new SharedNotificationContainerViewModel$special$$inlined$map$6(keyguardTransitionInteractor.getTransitionValueFlow(keyguardState), 1);
        SceneKey sceneKey = Scenes.Bouncer;
        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(i, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{sharedNotificationContainerViewModel$special$$inlined$map$6, new SharedNotificationContainerViewModel$special$$inlined$map$6(keyguardTransitionInteractor.getTransitionValueFlow(KeyguardState.GONE), i)})).toArray(new Flow[0])));
        KeyguardInteractor keyguardInteractor = this.keyguardInteractor;
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(distinctUntilChanged, keyguardInteractor.statusBarState, new SharedNotificationContainerViewModel$keyguardAlpha$isKeyguardNotVisible$3(3, null));
        ReadonlyStateFlow stateIn = FlowKt.stateIn(FlowKt.merge(FlowKt.merge(dumpWhileCollecting(keyguardInteractor.dismissAlpha, "keyguardInteractor.dismissAlpha"), dumpWhileCollecting(com.android.systemui.util.kotlin.FlowKt.sample(FlowKt.merge(this.primaryBouncerToGoneTransitionViewModel.notificationAlpha, this.alternateBouncerToGoneTransitionViewModel.notificationAlpha(viewStateAccessor)), this.communalSceneInteractor.isCommunalVisible, new SharedNotificationContainerViewModel$bouncerToGoneNotificationAlpha$1(3, null)), "bouncerToGoneNotificationAlpha"), this.aodToGoneTransitionViewModel.notificationAlpha(viewStateAccessor), this.aodToLockscreenTransitionViewModel.notificationAlpha, this.aodToOccludedTransitionViewModel.lockscreenAlpha(viewStateAccessor), this.dozingToLockscreenTransitionViewModel.lockscreenAlpha, this.dozingToOccludedTransitionViewModel.lockscreenAlpha(viewStateAccessor), this.dreamingToLockscreenTransitionViewModel.lockscreenAlpha, this.goneToAodTransitionViewModel.notificationAlpha, this.goneToDreamingTransitionViewModel.lockscreenAlpha, this.goneToDozingTransitionViewModel.notificationAlpha, this.goneToLockscreenTransitionViewModel.lockscreenAlpha, this.lockscreenToDreamingTransitionViewModel.lockscreenAlpha, this.lockscreenToGoneTransitionViewModel.notificationAlpha(viewStateAccessor), this.lockscreenToOccludedTransitionViewModel.lockscreenAlpha, this.lockscreenToPrimaryBouncerTransitionViewModel.lockscreenAlpha, this.occludedToAodTransitionViewModel.lockscreenAlpha, this.occludedToGoneTransitionViewModel.notificationAlpha(viewStateAccessor), this.occludedToLockscreenTransitionViewModel.lockscreenAlpha, this.primaryBouncerToLockscreenTransitionViewModel.lockscreenAlpha(viewStateAccessor), this.glanceableHubToLockscreenTransitionViewModel.keyguardAlpha, this.lockscreenToGlanceableHubTransitionViewModel.keyguardAlpha), this.alphaForShadeAndQsExpansion), coroutineScope, SharingStarted.Companion.Eagerly, Float.valueOf(1.0f));
        dumpValue(stateIn, "alphaForTransitionsAndShade");
        return (SafeFlow) dumpWhileCollecting(FlowKt.distinctUntilChanged(FlowKt.transformLatest(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, new SharedNotificationContainerViewModel$keyguardAlpha$$inlined$flatMapLatest$1(null, this, stateIn))), "keyguardAlpha");
    }

    public final void notificationStackChanged() {
        StateFlowImpl stateFlowImpl = this.interactor._notificationStackChanged;
        stateFlowImpl.updateState(null, Long.valueOf(((Number) stateFlowImpl.getValue()).longValue() + 1));
    }

    public static /* synthetic */ void getPaddingTopDimen$annotations() {
    }
}
