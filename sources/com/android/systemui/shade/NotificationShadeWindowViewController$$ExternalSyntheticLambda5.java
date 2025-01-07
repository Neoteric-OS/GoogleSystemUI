package com.android.systemui.shade;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.ambient.touch.TouchMonitor;
import com.android.systemui.keyguard.shared.model.TransitionState;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.log.core.Logger;
import com.android.systemui.shade.GlanceableHubContainerController;
import com.android.systemui.shared.animation.UnfoldConstantTranslateAnimator;
import com.android.wm.shell.R;
import java.util.function.Consumer;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationShadeWindowViewController$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ NotificationShadeWindowViewController f$0;

    public /* synthetic */ NotificationShadeWindowViewController$$ExternalSyntheticLambda5(NotificationShadeWindowViewController notificationShadeWindowViewController, int i) {
        this.$r8$classId = i;
        this.f$0 = notificationShadeWindowViewController;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        NotificationShadeWindowViewController notificationShadeWindowViewController = this.f$0;
        switch (i) {
            case 0:
                boolean booleanValue = ((Boolean) obj).booleanValue();
                GlanceableHubContainerController glanceableHubContainerController = notificationShadeWindowViewController.mGlanceableHubContainerController;
                if (!booleanValue) {
                    View view = glanceableHubContainerController.communalContainerView;
                    LifecycleRegistry lifecycleRegistry = glanceableHubContainerController.lifecycleRegistry;
                    if (view != null) {
                        ((ViewGroup) view.getParent()).removeView(view);
                        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
                        glanceableHubContainerController.communalContainerView = null;
                    }
                    GlanceableHubContainerController.CommunalWrapper communalWrapper = glanceableHubContainerController.communalContainerWrapper;
                    if (communalWrapper != null) {
                        ((ViewGroup) communalWrapper.getParent()).removeView(communalWrapper);
                        glanceableHubContainerController.communalContainerWrapper = null;
                    }
                    lifecycleRegistry.removeObserver(glanceableHubContainerController.touchLifecycleLogger);
                    TouchMonitor touchMonitor = glanceableHubContainerController.touchMonitor;
                    if (touchMonitor != null) {
                        touchMonitor.destroy();
                        glanceableHubContainerController.touchMonitor = null;
                    }
                    Logger.d$default(glanceableHubContainerController.logger, "Hub container disposed", null, 2, null);
                    break;
                } else {
                    NotificationShadeWindowView notificationShadeWindowView = notificationShadeWindowViewController.mView;
                    int indexOfChild = notificationShadeWindowView.indexOfChild(notificationShadeWindowView.findViewById(R.id.communal_ui_stub));
                    Context context = notificationShadeWindowView.getContext();
                    glanceableHubContainerController.getClass();
                    ComposeView composeView = new ComposeView(context, null, 0, 6, null);
                    RepeatWhenAttachedKt.repeatWhenAttached(composeView, EmptyCoroutineContext.INSTANCE, new GlanceableHubContainerController$initView$1$1(composeView, glanceableHubContainerController, null));
                    notificationShadeWindowView.addView(glanceableHubContainerController.initView$frameworks__base__packages__SystemUI__android_common__SystemUI_core(composeView), indexOfChild);
                    break;
                }
            case 1:
                notificationShadeWindowViewController.getClass();
                notificationShadeWindowViewController.mIsOcclusionTransitionRunning = ((TransitionStep) obj).transitionState == TransitionState.RUNNING;
                break;
            case 2:
                notificationShadeWindowViewController.setExpandAnimationRunning(((Boolean) obj).booleanValue());
                break;
            default:
                NotificationPanelUnfoldAnimationController notificationPanelUnfoldAnimationController = (NotificationPanelUnfoldAnimationController) obj;
                notificationShadeWindowViewController.getClass();
                float dimensionPixelSize = notificationPanelUnfoldAnimationController.context.getResources().getDimensionPixelSize(R.dimen.notification_side_paddings);
                UnfoldConstantTranslateAnimator unfoldConstantTranslateAnimator = (UnfoldConstantTranslateAnimator) notificationPanelUnfoldAnimationController.translateAnimator$delegate.getValue();
                NotificationShadeWindowView notificationShadeWindowView2 = notificationShadeWindowViewController.mView;
                unfoldConstantTranslateAnimator.init(notificationShadeWindowView2, dimensionPixelSize);
                ViewGroup viewGroup = (ViewGroup) notificationShadeWindowView2.findViewById(R.id.split_shade_status_bar);
                if (viewGroup != null) {
                    ((UnfoldConstantTranslateAnimator) notificationPanelUnfoldAnimationController.translateAnimatorStatusBar$delegate.getValue()).init(viewGroup, dimensionPixelSize);
                    break;
                }
                break;
        }
    }
}
