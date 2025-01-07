package com.android.systemui.scene.domain.interactor;

import com.android.compose.animation.scene.ContentKey;
import com.android.compose.animation.scene.ObservableTransitionState;
import com.android.systemui.keyguard.domain.interactor.KeyguardOcclusionInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardOcclusionInteractor$special$$inlined$map$1;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.scene.shared.model.Overlays;
import com.android.systemui.scene.shared.model.Scenes;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SceneContainerOcclusionInteractor {
    public final ReadonlyStateFlow invisibleDueToOcclusion;
    public final ReadonlyStateFlow isAodFullyOrPartiallyShown;
    public final ReadonlyStateFlow isOccludingActivityShown;

    public SceneContainerOcclusionInteractor(CoroutineScope coroutineScope, KeyguardOcclusionInteractor keyguardOcclusionInteractor, SceneInteractor sceneInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor) {
        KeyguardOcclusionInteractor$special$$inlined$map$1 keyguardOcclusionInteractor$special$$inlined$map$1 = keyguardOcclusionInteractor.isShowWhenLockedActivityOnTop;
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        Boolean bool = Boolean.FALSE;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(keyguardOcclusionInteractor$special$$inlined$map$1, coroutineScope, WhileSubscribed$default, bool);
        final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new SceneContainerOcclusionInteractor$isAodFullyOrPartiallyShown$1(2, null), keyguardTransitionInteractor.getTransitionValueFlow(KeyguardState.AOD));
        ReadonlyStateFlow stateIn2 = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.scene.domain.interactor.SceneContainerOcclusionInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.scene.domain.interactor.SceneContainerOcclusionInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.scene.domain.interactor.SceneContainerOcclusionInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.scene.domain.interactor.SceneContainerOcclusionInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.scene.domain.interactor.SceneContainerOcclusionInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.scene.domain.interactor.SceneContainerOcclusionInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.scene.domain.interactor.SceneContainerOcclusionInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.scene.domain.interactor.SceneContainerOcclusionInteractor$special$$inlined$map$1$2$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.scene.domain.interactor.SceneContainerOcclusionInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        ReadonlyStateFlow readonlyStateFlow = sceneInteractor.transitionState;
        this.invisibleDueToOcclusion = FlowKt.stateIn(FlowKt.combine(stateIn, readonlyStateFlow, stateIn2, new SceneContainerOcclusionInteractor$invisibleDueToOcclusion$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.valueOf(invisibleDueToOcclusion(((Boolean) stateIn.getValue()).booleanValue(), (ObservableTransitionState) ((StateFlowImpl) readonlyStateFlow.$$delegate_0).getValue(), ((Boolean) stateIn2.getValue()).booleanValue())));
    }

    public static boolean getCanBeOccluded(ContentKey contentKey) {
        if (Intrinsics.areEqual(contentKey, Overlays.NotificationsShade) || Intrinsics.areEqual(contentKey, Overlays.QuickSettingsShade) || Intrinsics.areEqual(contentKey, Scenes.Bouncer)) {
            return false;
        }
        if (Intrinsics.areEqual(contentKey, Scenes.Communal) || Intrinsics.areEqual(contentKey, Scenes.Gone) || Intrinsics.areEqual(contentKey, Scenes.Lockscreen)) {
            return true;
        }
        if (Intrinsics.areEqual(contentKey, Scenes.QuickSettings) || Intrinsics.areEqual(contentKey, Scenes.Shade)) {
            return false;
        }
        throw new IllegalStateException(("ContentKey \"" + contentKey + "\" doesn't have a mapping for canBeOccluded!").toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0052 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean invisibleDueToOcclusion(boolean r0, com.android.compose.animation.scene.ObservableTransitionState r1, boolean r2) {
        /*
            if (r0 == 0) goto L5a
            if (r2 != 0) goto L5a
            boolean r0 = r1 instanceof com.android.compose.animation.scene.ObservableTransitionState.Idle
            if (r0 == 0) goto L3c
            com.android.compose.animation.scene.ObservableTransitionState$Idle r1 = (com.android.compose.animation.scene.ObservableTransitionState.Idle) r1
            java.util.Set r0 = r1.currentOverlays
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r2 = r0 instanceof java.util.Collection
            if (r2 == 0) goto L1c
            r2 = r0
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L1c
            goto L33
        L1c:
            java.util.Iterator r0 = r0.iterator()
        L20:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L33
            java.lang.Object r2 = r0.next()
            com.android.compose.animation.scene.OverlayKey r2 = (com.android.compose.animation.scene.OverlayKey) r2
            boolean r2 = getCanBeOccluded(r2)
            if (r2 != 0) goto L20
            goto L5a
        L33:
            com.android.compose.animation.scene.SceneKey r0 = r1.currentScene
            boolean r0 = getCanBeOccluded(r0)
            if (r0 == 0) goto L5a
            goto L52
        L3c:
            boolean r0 = r1 instanceof com.android.compose.animation.scene.ObservableTransitionState.Transition
            if (r0 == 0) goto L54
            com.android.compose.animation.scene.ObservableTransitionState$Transition r1 = (com.android.compose.animation.scene.ObservableTransitionState.Transition) r1
            com.android.compose.animation.scene.ContentKey r0 = r1.fromContent
            boolean r0 = getCanBeOccluded(r0)
            if (r0 == 0) goto L5a
            com.android.compose.animation.scene.ContentKey r0 = r1.toContent
            boolean r0 = getCanBeOccluded(r0)
            if (r0 == 0) goto L5a
        L52:
            r0 = 1
            goto L5b
        L54:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        L5a:
            r0 = 0
        L5b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.scene.domain.interactor.SceneContainerOcclusionInteractor.invisibleDueToOcclusion(boolean, com.android.compose.animation.scene.ObservableTransitionState, boolean):boolean");
    }
}
