package com.android.compose.animation;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import android.view.ViewRootImpl;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.DialogCuj;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.animation.TransitionAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ExpandableControllerImpl implements ExpandableController {
    public final MutableState animatorState;
    public final BorderStroke borderStroke;
    public final MutableState boundsInComposeViewRoot;
    public final long color;
    public final View composeViewRoot;
    public final long contentColor;
    public final MutableState currentComposeViewInOverlay;
    public final Density density;
    public final ExpandableControllerImpl$expandable$1 expandable = new Expandable() { // from class: com.android.compose.animation.ExpandableControllerImpl$expandable$1
        @Override // com.android.systemui.animation.Expandable
        public final ActivityTransitionAnimator.Controller activityTransitionController(Integer num) {
            ExpandableControllerImpl expandableControllerImpl = ExpandableControllerImpl.this;
            if (((Boolean) expandableControllerImpl.isComposed.getValue()).booleanValue()) {
                return new ExpandableControllerImpl$activityController$1(new ExpandableControllerImpl$transitionController$1(expandableControllerImpl), num, expandableControllerImpl);
            }
            return null;
        }

        @Override // com.android.systemui.animation.Expandable
        public final DialogTransitionAnimator.Controller dialogTransitionController(DialogCuj dialogCuj) {
            ExpandableControllerImpl expandableControllerImpl = ExpandableControllerImpl.this;
            if (((Boolean) expandableControllerImpl.isComposed.getValue()).booleanValue()) {
                return new DialogTransitionAnimator.Controller(dialogCuj) { // from class: com.android.compose.animation.ExpandableControllerImpl$dialogController$1
                    public final /* synthetic */ DialogCuj $cuj;
                    public final DialogCuj cuj;
                    public final ExpandableControllerImpl sourceIdentity;
                    public final ViewRootImpl viewRoot;

                    {
                        this.$cuj = dialogCuj;
                        this.viewRoot = ExpandableControllerImpl.this.composeViewRoot.getViewRootImpl();
                        this.sourceIdentity = ExpandableControllerImpl.this;
                        this.cuj = dialogCuj;
                    }

                    @Override // com.android.systemui.animation.DialogTransitionAnimator.Controller
                    public final TransitionAnimator.Controller createExitController() {
                        ExpandableControllerImpl expandableControllerImpl2 = ExpandableControllerImpl.this;
                        expandableControllerImpl2.getClass();
                        return new ExpandableControllerImpl$dialogController$1$createExitController$1(new ExpandableControllerImpl$transitionController$1(expandableControllerImpl2), expandableControllerImpl2, 0);
                    }

                    @Override // com.android.systemui.animation.DialogTransitionAnimator.Controller
                    public final TransitionAnimator.Controller createTransitionController() {
                        ExpandableControllerImpl expandableControllerImpl2 = ExpandableControllerImpl.this;
                        expandableControllerImpl2.getClass();
                        return new ExpandableControllerImpl$dialogController$1$createExitController$1(new ExpandableControllerImpl$transitionController$1(expandableControllerImpl2), expandableControllerImpl2, 1);
                    }

                    @Override // com.android.systemui.animation.DialogTransitionAnimator.Controller
                    public final DialogCuj getCuj() {
                        return this.cuj;
                    }

                    @Override // com.android.systemui.animation.DialogTransitionAnimator.Controller
                    public final Object getSourceIdentity() {
                        return this.sourceIdentity;
                    }

                    @Override // com.android.systemui.animation.DialogTransitionAnimator.Controller
                    public final ViewRootImpl getViewRoot() {
                        return this.viewRoot;
                    }

                    @Override // com.android.systemui.animation.DialogTransitionAnimator.Controller
                    public final InteractionJankMonitor.Configuration.Builder jankConfigurationBuilder() {
                        DialogCuj dialogCuj2 = this.$cuj;
                        if (dialogCuj2 == null) {
                            return null;
                        }
                        return InteractionJankMonitor.Configuration.Builder.withView(dialogCuj2.cujType, ExpandableControllerImpl.this.composeViewRoot);
                    }

                    @Override // com.android.systemui.animation.DialogTransitionAnimator.Controller
                    public final void onExitAnimationCancelled() {
                        ExpandableControllerImpl.this.isDialogShowing.setValue(Boolean.FALSE);
                    }

                    @Override // com.android.systemui.animation.DialogTransitionAnimator.Controller
                    public final boolean shouldAnimateExit() {
                        ExpandableControllerImpl expandableControllerImpl2 = ExpandableControllerImpl.this;
                        return ((Boolean) expandableControllerImpl2.isComposed.getValue()).booleanValue() && expandableControllerImpl2.composeViewRoot.isAttachedToWindow() && expandableControllerImpl2.composeViewRoot.isShown();
                    }

                    @Override // com.android.systemui.animation.DialogTransitionAnimator.Controller
                    public final void startDrawingInOverlayOf(ViewGroup viewGroup) {
                        ViewGroupOverlay overlay = viewGroup.getOverlay();
                        ExpandableControllerImpl expandableControllerImpl2 = ExpandableControllerImpl.this;
                        if (overlay.equals(expandableControllerImpl2.overlay.getValue())) {
                            return;
                        }
                        expandableControllerImpl2.overlay.setValue(overlay);
                    }

                    @Override // com.android.systemui.animation.DialogTransitionAnimator.Controller
                    public final void stopDrawingInOverlay() {
                        ExpandableControllerImpl expandableControllerImpl2 = ExpandableControllerImpl.this;
                        if (expandableControllerImpl2.overlay.getValue() != null) {
                            expandableControllerImpl2.overlay.setValue(null);
                        }
                    }
                };
            }
            return null;
        }
    };
    public final MutableState isComposed;
    public final MutableState isDialogShowing;
    public final LayoutDirection layoutDirection;
    public final MutableState overlay;
    public final Shape shape;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.compose.animation.ExpandableControllerImpl$expandable$1] */
    public ExpandableControllerImpl(long j, long j2, Shape shape, BorderStroke borderStroke, View view, Density density, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, LayoutDirection layoutDirection, MutableState mutableState6) {
        this.color = j;
        this.contentColor = j2;
        this.shape = shape;
        this.borderStroke = borderStroke;
        this.composeViewRoot = view;
        this.density = density;
        this.animatorState = mutableState;
        this.isDialogShowing = mutableState2;
        this.overlay = mutableState3;
        this.currentComposeViewInOverlay = mutableState4;
        this.boundsInComposeViewRoot = mutableState5;
        this.layoutDirection = layoutDirection;
        this.isComposed = mutableState6;
    }
}
