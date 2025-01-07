package com.android.systemui.statusbar.events;

import android.graphics.Rect;
import android.view.View;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.events.PrivacyDotViewController;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.StatusBarContentInsetsChangedListener;
import com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PrivacyDotViewController {
    public final SystemStatusAnimationSchedulerImpl animationScheduler;
    public View bl;
    public View br;
    public ExecutorImpl.ExecutionToken cancelRunnable;
    public final ConfigurationController configurationController;
    public final StatusBarContentInsetsProvider contentInsetsProvider;
    public ViewState currentViewState;
    public final Object lock;
    public final Executor mainExecutor;
    public ViewState nextViewState;
    public ShowingListener showingListener;
    public final StatusBarStateController stateController;
    public final PrivacyDotViewController$systemStatusAnimationCallback$1 systemStatusAnimationCallback;
    public View tl;
    public View tr;
    public ExecutorImpl uiExecutor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.events.PrivacyDotViewController$4, reason: invalid class name */
    final class AnonymousClass4 extends SuspendLambda implements Function2 {
        final /* synthetic */ ShadeInteractor $shadeInteractor;
        int label;
        final /* synthetic */ PrivacyDotViewController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(ShadeInteractor shadeInteractor, PrivacyDotViewController privacyDotViewController, Continuation continuation) {
            super(2, continuation);
            this.$shadeInteractor = shadeInteractor;
            this.this$0 = privacyDotViewController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass4(this.$shadeInteractor, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            StateFlow isQsExpanded;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ShadeInteractor shadeInteractor = this.$shadeInteractor;
                if (shadeInteractor == null || (isQsExpanded = ((ShadeInteractorImpl) shadeInteractor).baseShadeInteractor.isQsExpanded()) == null) {
                    return Unit.INSTANCE;
                }
                final PrivacyDotViewController privacyDotViewController = this.this$0;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.statusbar.events.PrivacyDotViewController.4.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        Object obj3;
                        boolean booleanValue = ((Boolean) obj2).booleanValue();
                        PrivacyDotViewController privacyDotViewController2 = PrivacyDotViewController.this;
                        Object obj4 = privacyDotViewController2.lock;
                        synchronized (obj4) {
                            try {
                                obj3 = obj4;
                            } catch (Throwable th) {
                                th = th;
                                obj3 = obj4;
                            }
                            try {
                                privacyDotViewController2.setNextViewState(ViewState.copy$default(privacyDotViewController2.nextViewState, false, false, booleanValue, null, null, null, null, false, 0, 0, 0, null, null, 16375));
                                return Unit.INSTANCE;
                            } catch (Throwable th2) {
                                th = th2;
                                throw th;
                            }
                        }
                    }
                };
                this.label = 1;
                if (isQsExpanded.collect(flowCollector, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ShowingListener {
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.statusbar.events.PrivacyDotViewController$systemStatusAnimationCallback$1] */
    public PrivacyDotViewController(Executor executor, CoroutineScope coroutineScope, StatusBarStateController statusBarStateController, ConfigurationController configurationController, StatusBarContentInsetsProvider statusBarContentInsetsProvider, SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl, ShadeInteractor shadeInteractor) {
        this.mainExecutor = executor;
        this.stateController = statusBarStateController;
        this.configurationController = configurationController;
        this.contentInsetsProvider = statusBarContentInsetsProvider;
        this.animationScheduler = systemStatusAnimationSchedulerImpl;
        ViewState viewState = new ViewState(false, false, false, false, null, null, null, null, false, 0, 0, -1, null, null);
        this.currentViewState = viewState;
        this.nextViewState = ViewState.copy$default(viewState, false, false, false, null, null, null, null, false, 0, 0, 0, null, null, 16383);
        this.lock = new Object();
        statusBarContentInsetsProvider.listeners.add(new StatusBarContentInsetsChangedListener() { // from class: com.android.systemui.statusbar.events.PrivacyDotViewController.1
            @Override // com.android.systemui.statusbar.phone.StatusBarContentInsetsChangedListener
            public final void onStatusBarContentInsetsChanged() {
                PrivacyDotViewController privacyDotViewController = PrivacyDotViewController.this;
                StatusBarContentInsetsProvider statusBarContentInsetsProvider2 = privacyDotViewController.contentInsetsProvider;
                List listOf = CollectionsKt__CollectionsKt.listOf(statusBarContentInsetsProvider2.getStatusBarContentAreaForRotation(3), statusBarContentInsetsProvider2.getStatusBarContentAreaForRotation(0), statusBarContentInsetsProvider2.getStatusBarContentAreaForRotation(1), statusBarContentInsetsProvider2.getStatusBarContentAreaForRotation(2));
                synchronized (privacyDotViewController.lock) {
                    privacyDotViewController.setNextViewState(ViewState.copy$default(privacyDotViewController.nextViewState, false, false, false, (Rect) listOf.get(1), (Rect) listOf.get(2), (Rect) listOf.get(3), (Rect) listOf.get(0), false, 0, 0, 0, null, null, 16143));
                }
            }
        });
        ((ConfigurationControllerImpl) configurationController).addCallback(new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.events.PrivacyDotViewController.2
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onLayoutDirectionChanged(final boolean z) {
                final PrivacyDotViewController privacyDotViewController = PrivacyDotViewController.this;
                ExecutorImpl executorImpl = privacyDotViewController.uiExecutor;
                if (executorImpl != null) {
                    executorImpl.execute(new Runnable() { // from class: com.android.systemui.statusbar.events.PrivacyDotViewController$2$onLayoutDirectionChanged$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            PrivacyDotViewController.this.setCornerVisibilities();
                            PrivacyDotViewController.AnonymousClass2 anonymousClass2 = this;
                            PrivacyDotViewController privacyDotViewController2 = PrivacyDotViewController.this;
                            boolean z2 = z;
                            synchronized (anonymousClass2) {
                                privacyDotViewController2.setNextViewState(ViewState.copy$default(privacyDotViewController2.nextViewState, false, false, false, null, null, null, null, z2, 0, 0, 0, privacyDotViewController2.selectDesignatedCorner(privacyDotViewController2.nextViewState.rotation, z2), null, 12031));
                            }
                        }
                    });
                }
            }
        });
        statusBarStateController.addCallback(new StatusBarStateController.StateListener() { // from class: com.android.systemui.statusbar.events.PrivacyDotViewController.3
            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onExpandedChanged(boolean z) {
                PrivacyDotViewController.access$updateStatusBarState(PrivacyDotViewController.this);
            }

            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onStateChanged(int i) {
                PrivacyDotViewController.access$updateStatusBarState(PrivacyDotViewController.this);
            }
        });
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(shadeInteractor, this, null), 3);
        this.systemStatusAnimationCallback = new SystemStatusAnimationCallback() { // from class: com.android.systemui.statusbar.events.PrivacyDotViewController$systemStatusAnimationCallback$1
            @Override // com.android.systemui.statusbar.events.SystemStatusAnimationCallback
            public final void onHidePersistentDot() {
                PrivacyDotViewController privacyDotViewController = PrivacyDotViewController.this;
                synchronized (privacyDotViewController.lock) {
                    privacyDotViewController.setNextViewState(ViewState.copy$default(privacyDotViewController.nextViewState, false, false, false, null, null, null, null, false, 0, 0, 0, null, null, 16381));
                }
            }

            @Override // com.android.systemui.statusbar.events.SystemStatusAnimationCallback
            public final void onSystemStatusAnimationTransitionToPersistentDot(String str) {
                PrivacyDotViewController privacyDotViewController = PrivacyDotViewController.this;
                synchronized (privacyDotViewController.lock) {
                    privacyDotViewController.setNextViewState(ViewState.copy$default(privacyDotViewController.nextViewState, true, false, false, null, null, null, null, false, 0, 0, 0, null, str, 8189));
                }
            }
        };
    }

    public static final void access$updateStatusBarState(PrivacyDotViewController privacyDotViewController) {
        synchronized (privacyDotViewController.lock) {
            ViewState viewState = privacyDotViewController.nextViewState;
            StatusBarStateController statusBarStateController = privacyDotViewController.stateController;
            privacyDotViewController.setNextViewState(ViewState.copy$default(viewState, false, (statusBarStateController.isExpanded() && statusBarStateController.getState() == 0) || statusBarStateController.getState() == 2, false, null, null, null, null, false, 0, 0, 0, null, null, 16379));
        }
    }

    public final int activeRotationForCorner(View view, boolean z) {
        View view2 = this.tr;
        if (view2 == null) {
            view2 = null;
        }
        if (!Intrinsics.areEqual(view, view2)) {
            View view3 = this.tl;
            if (view3 == null) {
                view3 = null;
            }
            if (!Intrinsics.areEqual(view, view3)) {
                View view4 = this.br;
                if (Intrinsics.areEqual(view, view4 != null ? view4 : null)) {
                    if (z) {
                        return 2;
                    }
                } else if (!z) {
                    return 2;
                }
            } else if (z) {
                return 0;
            }
            return 3;
        }
        if (!z) {
            return 0;
        }
        return 1;
    }

    public final int cornerForView(View view) {
        View view2 = this.tl;
        if (view2 == null) {
            view2 = null;
        }
        if (Intrinsics.areEqual(view, view2)) {
            return 0;
        }
        View view3 = this.tr;
        if (view3 == null) {
            view3 = null;
        }
        if (Intrinsics.areEqual(view, view3)) {
            return 1;
        }
        View view4 = this.bl;
        if (view4 == null) {
            view4 = null;
        }
        if (Intrinsics.areEqual(view, view4)) {
            return 3;
        }
        View view5 = this.br;
        if (Intrinsics.areEqual(view, view5 != null ? view5 : null)) {
            return 2;
        }
        throw new IllegalArgumentException("not a corner view");
    }

    public final Sequence getViews() {
        View view = this.tl;
        if (view == null) {
            return SequencesKt.sequenceOf(new View[0]);
        }
        View[] viewArr = new View[4];
        if (view == null) {
            view = null;
        }
        viewArr[0] = view;
        View view2 = this.tr;
        if (view2 == null) {
            view2 = null;
        }
        viewArr[1] = view2;
        View view3 = this.br;
        if (view3 == null) {
            view3 = null;
        }
        viewArr[2] = view3;
        View view4 = this.bl;
        viewArr[3] = view4 != null ? view4 : null;
        return SequencesKt.sequenceOf(viewArr);
    }

    public final View selectDesignatedCorner(int i, boolean z) {
        View view = this.tl;
        if (view == null) {
            return null;
        }
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        throw new IllegalStateException("unknown rotation");
                    }
                    if (z) {
                        View view2 = this.bl;
                        if (view2 != null) {
                            return view2;
                        }
                    } else if (view != null) {
                        return view;
                    }
                } else if (z) {
                    View view3 = this.br;
                    if (view3 != null) {
                        return view3;
                    }
                } else {
                    View view4 = this.bl;
                    if (view4 != null) {
                        return view4;
                    }
                }
            } else if (z) {
                View view5 = this.tr;
                if (view5 != null) {
                    return view5;
                }
            } else {
                View view6 = this.br;
                if (view6 != null) {
                    return view6;
                }
            }
        } else if (!z) {
            View view7 = this.tr;
            if (view7 != null) {
                return view7;
            }
        } else if (view != null) {
            return view;
        }
        return null;
    }

    public final void setCornerVisibilities() {
        for (View view : getViews()) {
            view.setVisibility(4);
            ShowingListener showingListener = this.showingListener;
            if (showingListener != null) {
                ScreenDecorations.this.updateOverlayWindowVisibilityIfViewExists(view);
            }
        }
    }

    public final void setNextViewState(ViewState viewState) {
        this.nextViewState = viewState;
        ExecutorImpl.ExecutionToken executionToken = this.cancelRunnable;
        if (executionToken != null) {
            executionToken.run();
        }
        ExecutorImpl executorImpl = this.uiExecutor;
        this.cancelRunnable = executorImpl != null ? executorImpl.executeDelayed(new PrivacyDotViewController$initialize$1(this, 1), 100L, TimeUnit.MILLISECONDS) : null;
    }
}
