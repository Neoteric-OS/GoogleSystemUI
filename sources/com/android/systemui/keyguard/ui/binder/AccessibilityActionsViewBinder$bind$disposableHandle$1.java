package com.android.systemui.keyguard.ui.binder;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.shared.model.CommunalScenes;
import com.android.systemui.keyguard.ui.viewmodel.AccessibilityActionsViewModel;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.ReadonlySharedFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AccessibilityActionsViewBinder$bind$disposableHandle$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ View $view;
    final /* synthetic */ AccessibilityActionsViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.binder.AccessibilityActionsViewBinder$bind$disposableHandle$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ View $view;
        final /* synthetic */ AccessibilityActionsViewModel $viewModel;
        private /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.ui.binder.AccessibilityActionsViewBinder$bind$disposableHandle$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C00831 extends SuspendLambda implements Function2 {
            final /* synthetic */ View $view;
            final /* synthetic */ AccessibilityActionsViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00831(View view, AccessibilityActionsViewModel accessibilityActionsViewModel, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = accessibilityActionsViewModel;
                this.$view = view;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00831(this.$view, this.$viewModel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C00831) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow flow = this.$viewModel.isOnKeyguard;
                    final View view = this.$view;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyguard.ui.binder.AccessibilityActionsViewBinder.bind.disposableHandle.1.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            view.setImportantForAccessibility(((Boolean) obj2).booleanValue() ? 1 : 2);
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (flow.collect(flowCollector, this) == coroutineSingletons) {
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
        /* renamed from: com.android.systemui.keyguard.ui.binder.AccessibilityActionsViewBinder$bind$disposableHandle$1$1$2, reason: invalid class name */
        final class AnonymousClass2 extends SuspendLambda implements Function2 {
            final /* synthetic */ View $view;
            final /* synthetic */ AccessibilityActionsViewModel $viewModel;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(View view, AccessibilityActionsViewModel accessibilityActionsViewModel, Continuation continuation) {
                super(2, continuation);
                this.$viewModel = accessibilityActionsViewModel;
                this.$view = view;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass2(this.$view, this.$viewModel, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    final AccessibilityActionsViewModel accessibilityActionsViewModel = this.$viewModel;
                    ReadonlySharedFlow readonlySharedFlow = accessibilityActionsViewModel.isCommunalAvailable;
                    final View view = this.$view;
                    FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyguard.ui.binder.AccessibilityActionsViewBinder.bind.disposableHandle.1.1.2.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(Object obj2, Continuation continuation) {
                            final boolean booleanValue = ((Boolean) obj2).booleanValue();
                            View view2 = view;
                            final View view3 = view;
                            final AccessibilityActionsViewModel accessibilityActionsViewModel2 = accessibilityActionsViewModel;
                            view2.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.systemui.keyguard.ui.binder.AccessibilityActionsViewBinder.bind.disposableHandle.1.1.2.1.1
                                @Override // android.view.View.AccessibilityDelegate
                                public final void onInitializeAccessibilityNodeInfo(View view4, AccessibilityNodeInfo accessibilityNodeInfo) {
                                    super.onInitializeAccessibilityNodeInfo(view4, accessibilityNodeInfo);
                                    if (booleanValue) {
                                        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_open_communal_hub, view3.getResources().getString(R.string.accessibility_action_open_communal_hub)));
                                    }
                                }

                                @Override // android.view.View.AccessibilityDelegate
                                public final boolean performAccessibilityAction(View view4, int i2, Bundle bundle) {
                                    if (i2 != R.id.accessibility_action_open_communal_hub) {
                                        return super.performAccessibilityAction(view4, i2, bundle);
                                    }
                                    AccessibilityActionsViewModel accessibilityActionsViewModel3 = accessibilityActionsViewModel2;
                                    accessibilityActionsViewModel3.getClass();
                                    SceneKey sceneKey = CommunalScenes.Communal;
                                    int i3 = CommunalInteractor.$r8$clinit;
                                    CommunalSceneInteractor.changeScene$default(accessibilityActionsViewModel3.communalInteractor.communalSceneInteractor, sceneKey, "accessibility", null, 8);
                                    return true;
                                }
                            });
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (readonlySharedFlow.$$delegate_0.collect(flowCollector, this) == coroutineSingletons) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(View view, AccessibilityActionsViewModel accessibilityActionsViewModel, Continuation continuation) {
            super(2, continuation);
            this.$view = view;
            this.$viewModel = accessibilityActionsViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$view, this.$viewModel, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            View view = this.$view;
            view.setContentDescription(view.getResources().getString(R.string.accessibility_desc_lock_screen));
            BuildersKt.launch$default(coroutineScope, null, null, new C00831(this.$view, this.$viewModel, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.$view, this.$viewModel, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessibilityActionsViewBinder$bind$disposableHandle$1(View view, AccessibilityActionsViewModel accessibilityActionsViewModel, Continuation continuation) {
        super(3, continuation);
        this.$view = view;
        this.$viewModel = accessibilityActionsViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        AccessibilityActionsViewBinder$bind$disposableHandle$1 accessibilityActionsViewBinder$bind$disposableHandle$1 = new AccessibilityActionsViewBinder$bind$disposableHandle$1(this.$view, this.$viewModel, (Continuation) obj3);
        accessibilityActionsViewBinder$bind$disposableHandle$1.L$0 = (LifecycleOwner) obj;
        return accessibilityActionsViewBinder$bind$disposableHandle$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Lifecycle.State state = Lifecycle.State.STARTED;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$view, this.$viewModel, null);
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutineSingletons) {
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
