package com.android.systemui.communal.widgets;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.communal.shared.model.CommunalScenes;
import com.android.systemui.communal.ui.viewmodel.CommunalEditModeViewModel$special$$inlined$map$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class EditWidgetsActivity$listenForTransitionAndChangeScene$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ EditWidgetsActivity this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.communal.widgets.EditWidgetsActivity$listenForTransitionAndChangeScene$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ EditWidgetsActivity this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.communal.widgets.EditWidgetsActivity$listenForTransitionAndChangeScene$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C00661 extends SuspendLambda implements Function2 {
            /* synthetic */ Object L$0;
            int label;

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                C00661 c00661 = new C00661(2, continuation);
                c00661.L$0 = obj;
                return c00661;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C00661) create((SceneKey) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Boolean.valueOf(Intrinsics.areEqual((SceneKey) this.L$0, CommunalScenes.Blank));
            }
        }

        public AnonymousClass1(EditWidgetsActivity editWidgetsActivity) {
            this.this$0 = editWidgetsActivity;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
            return emit(((Boolean) obj).booleanValue(), continuation);
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0075  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(boolean r9, kotlin.coroutines.Continuation r10) {
            /*
                r8 = this;
                boolean r9 = r10 instanceof com.android.systemui.communal.widgets.EditWidgetsActivity$listenForTransitionAndChangeScene$1$1$emit$1
                if (r9 == 0) goto L13
                r9 = r10
                com.android.systemui.communal.widgets.EditWidgetsActivity$listenForTransitionAndChangeScene$1$1$emit$1 r9 = (com.android.systemui.communal.widgets.EditWidgetsActivity$listenForTransitionAndChangeScene$1$1$emit$1) r9
                int r0 = r9.label
                r1 = -2147483648(0xffffffff80000000, float:-0.0)
                r2 = r0 & r1
                if (r2 == 0) goto L13
                int r0 = r0 - r1
                r9.label = r0
                goto L18
            L13:
                com.android.systemui.communal.widgets.EditWidgetsActivity$listenForTransitionAndChangeScene$1$1$emit$1 r9 = new com.android.systemui.communal.widgets.EditWidgetsActivity$listenForTransitionAndChangeScene$1$1$emit$1
                r9.<init>(r8, r10)
            L18:
                java.lang.Object r10 = r9.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r9.label
                r2 = 0
                r3 = 1
                if (r1 == 0) goto L34
                if (r1 != r3) goto L2c
                java.lang.Object r8 = r9.L$0
                com.android.systemui.communal.widgets.EditWidgetsActivity$listenForTransitionAndChangeScene$1$1 r8 = (com.android.systemui.communal.widgets.EditWidgetsActivity$listenForTransitionAndChangeScene$1.AnonymousClass1) r8
                kotlin.ResultKt.throwOnFailure(r10)
                goto L5d
            L2c:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r9)
                throw r8
            L34:
                kotlin.ResultKt.throwOnFailure(r10)
                com.android.systemui.communal.widgets.EditWidgetsActivity r10 = r8.this$0
                com.android.systemui.communal.ui.viewmodel.CommunalEditModeViewModel r1 = r10.communalViewModel
                com.android.compose.animation.scene.SceneKey r4 = com.android.systemui.communal.shared.model.CommunalScenes.Blank
                com.android.compose.animation.scene.TransitionKey r5 = com.android.systemui.communal.shared.model.CommunalTransitionKeys.ToEditMode
                com.android.systemui.keyguard.shared.model.KeyguardState r6 = com.android.systemui.keyguard.shared.model.KeyguardState.GONE
                com.android.systemui.communal.domain.interactor.CommunalSceneInteractor r1 = r1.communalSceneInteractor
                java.lang.String r7 = "edit mode opening"
                r1.changeScene(r4, r7, r5, r6)
                com.android.systemui.communal.ui.viewmodel.CommunalEditModeViewModel r10 = r10.communalViewModel
                kotlinx.coroutines.flow.ReadonlyStateFlow r10 = r10.currentScene
                com.android.systemui.communal.widgets.EditWidgetsActivity$listenForTransitionAndChangeScene$1$1$1 r1 = new com.android.systemui.communal.widgets.EditWidgetsActivity$listenForTransitionAndChangeScene$1$1$1
                r4 = 2
                r1.<init>(r4, r2)
                r9.L$0 = r8
                r9.label = r3
                java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.first(r10, r1, r9)
                if (r9 != r0) goto L5d
                return r0
            L5d:
                com.android.systemui.communal.widgets.EditWidgetsActivity r9 = r8.this$0
                com.android.systemui.communal.ui.viewmodel.CommunalEditModeViewModel r9 = r9.communalViewModel
                com.android.systemui.communal.shared.model.EditModeState r10 = com.android.systemui.communal.shared.model.EditModeState.SHOWING
                com.android.systemui.communal.domain.interactor.CommunalSceneInteractor r9 = r9.communalSceneInteractor
                kotlinx.coroutines.flow.StateFlowImpl r9 = r9._editModeState
                r9.setValue(r10)
                com.android.systemui.communal.widgets.EditWidgetsActivity r8 = r8.this$0
                com.android.systemui.communal.widgets.EditWidgetsActivity$NopActivityController r9 = r8.activityController
                r9.getClass()
                boolean r9 = r8.shouldOpenWidgetPickerOnStart
                if (r9 == 0) goto L89
                androidx.lifecycle.Lifecycle r9 = r8.getLifecycle()
                androidx.lifecycle.LifecycleCoroutineScopeImpl r9 = androidx.lifecycle.LifecycleKt.getCoroutineScope(r9)
                com.android.systemui.communal.widgets.EditWidgetsActivity$onOpenWidgetPicker$1 r10 = new com.android.systemui.communal.widgets.EditWidgetsActivity$onOpenWidgetPicker$1
                r10.<init>(r8, r2)
                r0 = 3
                kotlinx.coroutines.BuildersKt.launch$default(r9, r2, r2, r10, r0)
                r9 = 0
                r8.shouldOpenWidgetPickerOnStart = r9
            L89:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.widgets.EditWidgetsActivity$listenForTransitionAndChangeScene$1.AnonymousClass1.emit(boolean, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditWidgetsActivity$listenForTransitionAndChangeScene$1(EditWidgetsActivity editWidgetsActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = editWidgetsActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new EditWidgetsActivity$listenForTransitionAndChangeScene$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((EditWidgetsActivity$listenForTransitionAndChangeScene$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            EditWidgetsActivity editWidgetsActivity = this.this$0;
            CommunalEditModeViewModel$special$$inlined$map$1 communalEditModeViewModel$special$$inlined$map$1 = editWidgetsActivity.communalViewModel.canShowEditMode;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(editWidgetsActivity);
            this.label = 1;
            if (communalEditModeViewModel$special$$inlined$map$1.collect(anonymousClass1, this) == coroutineSingletons) {
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
