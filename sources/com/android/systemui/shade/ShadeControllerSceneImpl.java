package com.android.systemui.shade;

import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.ExpandHelper;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import com.android.systemui.scene.domain.resolver.HomeSceneFamilyResolver;
import com.android.systemui.scene.domain.resolver.SceneResolver;
import com.android.systemui.scene.shared.model.SceneFamilies;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.scene.shared.model.TransitionKeys;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.StatusBarRemoteInputCallback$$ExternalSyntheticLambda0;
import com.google.android.systemui.assist.AssistManagerGoogle;
import dagger.Lazy;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeControllerSceneImpl extends BaseShadeControllerImpl {
    public final CoroutineDispatcher mainDispatcher;
    public final NotificationStackScrollLayout notificationStackScrollLayout;
    public final SceneInteractor sceneInteractor;
    public final CoroutineScope scope;
    public final ShadeInteractor shadeInteractor;
    public final VibratorHelper vibratorHelper;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.shade.ShadeControllerSceneImpl$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return ShadeControllerSceneImpl.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow isAnyExpanded = ((ShadeInteractorImpl) ShadeControllerSceneImpl.this.shadeInteractor).baseShadeInteractor.isAnyExpanded();
                final ShadeControllerSceneImpl shadeControllerSceneImpl = ShadeControllerSceneImpl.this;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.shade.ShadeControllerSceneImpl.1.1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.shade.ShadeControllerSceneImpl$1$1$1, reason: invalid class name and collision with other inner class name */
                    final class C01861 extends SuspendLambda implements Function2 {
                        int label;
                        final /* synthetic */ ShadeControllerSceneImpl this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public C01861(ShadeControllerSceneImpl shadeControllerSceneImpl, Continuation continuation) {
                            super(2, continuation);
                            this.this$0 = shadeControllerSceneImpl;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object obj, Continuation continuation) {
                            return new C01861(this.this$0, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            C01861 c01861 = (C01861) create((CoroutineScope) obj, (Continuation) obj2);
                            Unit unit = Unit.INSTANCE;
                            c01861.invokeSuspend(unit);
                            return unit;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                            if (this.label != 0) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                            this.this$0.runPostCollapseActions();
                            return Unit.INSTANCE;
                        }
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        boolean booleanValue = ((Boolean) obj2).booleanValue();
                        Unit unit = Unit.INSTANCE;
                        if (booleanValue) {
                            return unit;
                        }
                        ShadeControllerSceneImpl shadeControllerSceneImpl2 = ShadeControllerSceneImpl.this;
                        Object withContext = BuildersKt.withContext(shadeControllerSceneImpl2.mainDispatcher, new C01861(shadeControllerSceneImpl2, null), continuation);
                        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : unit;
                    }
                };
                this.label = 1;
                if (isAnyExpanded.collect(flowCollector, this) == coroutineSingletons) {
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

    public ShadeControllerSceneImpl(CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope, ShadeInteractor shadeInteractor, SceneInteractor sceneInteractor, NotificationStackScrollLayout notificationStackScrollLayout, VibratorHelper vibratorHelper, CommandQueue commandQueue, StatusBarKeyguardViewManager statusBarKeyguardViewManager, NotificationShadeWindowController notificationShadeWindowController, Lazy lazy) {
        super(commandQueue, statusBarKeyguardViewManager, notificationShadeWindowController, lazy);
        this.mainDispatcher = coroutineDispatcher;
        this.scope = coroutineScope;
        this.shadeInteractor = shadeInteractor;
        this.sceneInteractor = sceneInteractor;
        this.notificationStackScrollLayout = notificationStackScrollLayout;
        this.vibratorHelper = vibratorHelper;
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(null), 3);
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void animateCollapseShade(int i, boolean z, boolean z2, float f) {
        ShadeInteractor shadeInteractor = this.shadeInteractor;
        if (!z && !((Boolean) ((ShadeInteractorImpl) shadeInteractor).baseShadeInteractor.isAnyExpanded().getValue()).booleanValue()) {
            runPostCollapseActions();
            return;
        }
        if (((Boolean) ((ShadeInteractorImpl) shadeInteractor).baseShadeInteractor.isAnyExpanded().getValue()).booleanValue() && (i & 4) == 0) {
            ((NotificationShadeWindowControllerImpl) this.notificationShadeWindowController).setNotificationShadeFocusable(false);
            ExpandHelper expandHelper = this.notificationStackScrollLayout.mExpandHelper;
            expandHelper.finishExpanding(0.0f, true, true);
            expandHelper.mResizedView = null;
            expandHelper.mSGD = new ScaleGestureDetector(expandHelper.mContext, expandHelper.mScaleGestureListener);
            if (z2) {
                BuildersKt.launch$default(this.scope, null, null, new ShadeControllerSceneImpl$animateCollapseShade$1(this, null), 3);
            } else {
                SceneInteractor.changeScene$default(this.sceneInteractor, SceneFamilies.Home, "ShadeController.animateCollapseShade", TransitionKeys.SlightlyFasterShadeCollapse, null, 8);
            }
        }
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void cancelExpansionAndCollapseShade() {
        animateCollapseShade(0);
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void closeShadeIfOpen() {
        if (((Boolean) ((ShadeInteractorImpl) this.shadeInteractor).baseShadeInteractor.isAnyExpanded().getValue()).booleanValue()) {
            this.commandQueue.animateCollapsePanels(2, true);
            ((AssistManagerGoogle) this.assistManagerLazy.get()).hideAssist();
        }
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void collapseOnMainThread() {
        animateCollapseShade(2, true, true, 1.0f);
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void collapseShade(boolean z) {
        if (z) {
            animateCollapseShade(0);
        } else {
            instantCollapseShade();
        }
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void collapseShadeForActivityStart() {
        if (((Boolean) ((ShadeInteractorImpl) this.shadeInteractor).baseShadeInteractor.isAnyExpanded().getValue()).booleanValue()) {
            animateCollapseShade(2, true, true, 1.0f);
        } else {
            runPostCollapseActions();
        }
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void collapseWithDuration(int i) {
        animateCollapseShade(0);
    }

    @Override // com.android.systemui.shade.BaseShadeControllerImpl
    public final void expandToNotifications() {
        ((ShadeInteractorImpl) this.shadeInteractor).expandNotificationShade("ShadeController.animateExpandShade");
    }

    @Override // com.android.systemui.shade.BaseShadeControllerImpl
    public final void expandToQs() {
        ((ShadeInteractorImpl) this.shadeInteractor).expandQuickSettingsShade();
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void instantCollapseShade() {
        SceneKey sceneKey = SceneFamilies.Home;
        SceneInteractor sceneInteractor = this.sceneInteractor;
        SceneKey sceneKey2 = (SceneKey) sceneInteractor.currentScene.getValue();
        SceneResolver sceneResolver = (SceneResolver) ((Map) sceneInteractor.sceneFamilyResolvers.get()).get(sceneKey);
        if (sceneResolver != null) {
            if (HomeSceneFamilyResolver.homeScenes.contains(sceneKey2)) {
                return;
            }
            SceneKey sceneKey3 = (SceneKey) ((StateFlowImpl) ((HomeSceneFamilyResolver) sceneResolver).resolvedScene.$$delegate_0).getValue();
            if (sceneKey3 != null) {
                sceneKey = sceneKey3;
            }
        }
        if (sceneInteractor.validateSceneChange(sceneKey2, sceneKey, "hide shade")) {
            sceneInteractor.logger.logSceneChanged(sceneKey2, sceneKey, "hide shade", true);
            sceneInteractor.repository.dataSource.snapToScene(sceneKey);
        }
    }

    @Override // com.android.systemui.shade.ShadeController
    public final boolean isExpandedVisible() {
        return !Intrinsics.areEqual(this.sceneInteractor.currentScene.getValue(), Scenes.Gone);
    }

    @Override // com.android.systemui.shade.ShadeController
    public final boolean isExpandingOrCollapsing() {
        return ((Boolean) ((StateFlowImpl) ((ShadeInteractorImpl) this.shadeInteractor).isUserInteracting.$$delegate_0).getValue()).booleanValue();
    }

    @Override // com.android.systemui.shade.ShadeController
    public final boolean isShadeEnabled() {
        return ((Boolean) ((ShadeInteractorImpl) this.shadeInteractor).isShadeEnabled.getValue()).booleanValue();
    }

    @Override // com.android.systemui.shade.ShadeController
    public final boolean isShadeFullyOpen() {
        return ((Boolean) ((ShadeInteractorImpl) this.shadeInteractor).isAnyFullyExpanded.getValue()).booleanValue();
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void onStatusBarTouch(MotionEvent motionEvent) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void performHapticFeedback() {
        this.vibratorHelper.getClass();
        this.notificationStackScrollLayout.performHapticFeedback(12);
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void postAnimateCollapseShade() {
        animateCollapseShade(0);
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void postAnimateForceCollapseShade() {
        animateCollapseShade(0, true, false, 1.0f);
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void postOnShadeExpanded(StatusBarRemoteInputCallback$$ExternalSyntheticLambda0 statusBarRemoteInputCallback$$ExternalSyntheticLambda0) {
        BuildersKt.launch$default(this.scope, null, null, new ShadeControllerSceneImpl$postOnShadeExpanded$1(this, statusBarRemoteInputCallback$$ExternalSyntheticLambda0, null), 3);
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void setVisibilityListener(CentralSurfacesImpl.AnonymousClass4 anonymousClass4) {
        BuildersKt.launch$default(this.scope, null, null, new ShadeControllerSceneImpl$setVisibilityListener$1(this, anonymousClass4, null), 3);
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void collapseShade() {
        animateCollapseShade(2, true, true, 1.0f);
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void instantExpandShade() {
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void makeExpandedInvisible() {
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void makeExpandedVisible(boolean z) {
    }
}
