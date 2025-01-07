package com.android.systemui.statusbar.notification;

import android.graphics.PorterDuffXfermode;
import android.util.Log;
import android.util.MathUtils;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import com.android.app.animation.Interpolators;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.domain.interactor.NotificationLaunchAnimationInteractor;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationBackgroundView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationTransitionAnimatorController implements ActivityTransitionAnimator.Controller {
    public final HeadsUpManager headsUpManager;
    public final boolean isLaunching;
    public final InteractionJankMonitor jankMonitor;
    public final ExpandableNotificationRow notification;
    public final NotificationEntry notificationEntry;
    public final String notificationKey;
    public final NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor;
    public final NotificationStackScrollLayoutController.NotificationListContainerImpl notificationListContainer;

    public NotificationTransitionAnimatorController(NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor, NotificationStackScrollLayoutController.NotificationListContainerImpl notificationListContainerImpl, HeadsUpManager headsUpManager, ExpandableNotificationRow expandableNotificationRow, InteractionJankMonitor interactionJankMonitor) {
        this.notificationLaunchAnimationInteractor = notificationLaunchAnimationInteractor;
        this.notificationListContainer = notificationListContainerImpl;
        this.headsUpManager = headsUpManager;
        this.notification = expandableNotificationRow;
        this.jankMonitor = interactionJankMonitor;
        NotificationEntry notificationEntry = expandableNotificationRow.mEntry;
        this.notificationEntry = notificationEntry;
        this.notificationKey = notificationEntry.mSbn.getKey();
        this.isLaunching = true;
    }

    public final void applyParams(LaunchAnimationParameters launchAnimationParameters) {
        int i;
        boolean z = true;
        ExpandableNotificationRow expandableNotificationRow = this.notification;
        if (launchAnimationParameters == null) {
            ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow.mNotificationParent;
            if (expandableNotificationRow2 != null) {
                expandableNotificationRow2.setClipTopAmount(0);
            }
            expandableNotificationRow.setTranslationX(0.0f);
        } else {
            expandableNotificationRow.getClass();
            if (launchAnimationParameters.visible) {
                Interpolator interpolator = Interpolators.FAST_OUT_SLOW_IN;
                PorterDuffXfermode porterDuffXfermode = TransitionAnimator.SRC_MODE;
                TransitionAnimator.Timings timings = ActivityTransitionAnimator.TIMINGS;
                PathInterpolator pathInterpolator = (PathInterpolator) interpolator;
                float lerp = MathUtils.lerp(launchAnimationParameters.startTranslationZ, expandableNotificationRow.mNotificationLaunchHeight, pathInterpolator.getInterpolation(TransitionAnimator.Companion.getProgress(timings, launchAnimationParameters.linearProgress, 0L, 50L)));
                expandableNotificationRow.setTranslationZ(lerp);
                float width = launchAnimationParameters.getWidth() - expandableNotificationRow.getWidth();
                expandableNotificationRow.mExtraWidthForClipping = width;
                expandableNotificationRow.invalidate();
                if (launchAnimationParameters.startRoundedTopClipping > 0) {
                    float interpolation = pathInterpolator.getInterpolation(TransitionAnimator.Companion.getProgress(timings, launchAnimationParameters.linearProgress, 0L, 100L));
                    int i2 = launchAnimationParameters.startNotificationTop;
                    i = (int) Math.min(MathUtils.lerp(i2, launchAnimationParameters.top, interpolation), i2);
                } else {
                    i = launchAnimationParameters.top;
                }
                int i3 = launchAnimationParameters.bottom - i;
                expandableNotificationRow.setActualHeight(i3, true);
                int i4 = launchAnimationParameters.notificationParentTop;
                int i5 = i - i4;
                int i6 = launchAnimationParameters.startClipTopAmount;
                int lerp2 = (int) MathUtils.lerp(i6, 0, launchAnimationParameters.progress);
                ExpandableNotificationRow expandableNotificationRow3 = expandableNotificationRow.mNotificationParent;
                if (expandableNotificationRow3 != null) {
                    float translationY = expandableNotificationRow3.getTranslationY();
                    i5 = (int) (i5 - translationY);
                    expandableNotificationRow.mNotificationParent.setTranslationZ(lerp);
                    expandableNotificationRow.mNotificationParent.setClipTopAmount(Math.min(launchAnimationParameters.parentStartClipTopAmount, lerp2 + i5));
                    ExpandableNotificationRow expandableNotificationRow4 = expandableNotificationRow.mNotificationParent;
                    expandableNotificationRow4.mExtraWidthForClipping = width;
                    expandableNotificationRow4.invalidate();
                    float f = launchAnimationParameters.bottom - i4;
                    ExpandableNotificationRow expandableNotificationRow5 = expandableNotificationRow.mNotificationParent;
                    int max = (int) (Math.max(f, (expandableNotificationRow5.mActualHeight + translationY) - expandableNotificationRow5.mClipBottomAmount) - Math.min(launchAnimationParameters.top - i4, translationY));
                    ExpandableNotificationRow expandableNotificationRow6 = expandableNotificationRow.mNotificationParent;
                    expandableNotificationRow6.mMinimumHeightForClipping = max;
                    expandableNotificationRow6.updateClipping$1();
                    expandableNotificationRow6.invalidate();
                } else if (i6 != 0) {
                    expandableNotificationRow.setClipTopAmount(lerp2);
                }
                expandableNotificationRow.setTranslationY(i5);
                expandableNotificationRow.setTranslationX(((launchAnimationParameters.getWidth() / 2.0f) + launchAnimationParameters.left) - (((expandableNotificationRow.getWidth() / 2.0f) + expandableNotificationRow.getLocationOnScreen()[0]) - expandableNotificationRow.getTranslationX()));
                float f2 = ((ActivatableNotificationView) expandableNotificationRow).mRoundableState.maxRadius;
                expandableNotificationRow.invalidateOutline();
                NotificationBackgroundView notificationBackgroundView = expandableNotificationRow.mBackgroundNormal;
                int width2 = launchAnimationParameters.getWidth();
                notificationBackgroundView.mExpandAnimationHeight = i3;
                notificationBackgroundView.mExpandAnimationWidth = width2;
                notificationBackgroundView.invalidate();
            } else if (expandableNotificationRow.getVisibility() == 0) {
                expandableNotificationRow.setVisibility(4);
            }
        }
        NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayoutController.this.mView;
        notificationStackScrollLayout.mLaunchAnimationParams = launchAnimationParameters;
        boolean z2 = launchAnimationParameters != null;
        if (z2 != notificationStackScrollLayout.mLaunchingNotification) {
            notificationStackScrollLayout.mLaunchingNotification = z2;
            if (launchAnimationParameters == null || (launchAnimationParameters.startRoundedTopClipping <= 0 && launchAnimationParameters.parentStartRoundedTopClipping <= 0)) {
                z = false;
            }
            notificationStackScrollLayout.mLaunchingNotificationNeedsToBeClipped = z;
            if (!z || !z2) {
                notificationStackScrollLayout.mLaunchedNotificationClipPath.reset();
            }
            notificationStackScrollLayout.invalidate();
        }
        notificationStackScrollLayout.updateLaunchedNotificationClipPath();
        notificationStackScrollLayout.requestChildrenUpdate();
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final TransitionAnimator.State createAnimatorState() {
        ExpandableNotificationRow expandableNotificationRow = this.notification;
        int max = Math.max(0, expandableNotificationRow.mActualHeight - expandableNotificationRow.mClipBottomAmount);
        int[] locationOnScreen = expandableNotificationRow.getLocationOnScreen();
        NotificationStackScrollLayoutController.NotificationListContainerImpl notificationListContainerImpl = this.notificationListContainer;
        NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayoutController.this.mView;
        int i = notificationStackScrollLayout.mIsExpanded ? notificationStackScrollLayout.mQsScrollBoundaryPosition : 0;
        int i2 = locationOnScreen[1];
        int i3 = i - i2;
        if (i3 < 0) {
            i3 = 0;
        }
        int i4 = i2 + i3;
        float topCornerRadius = i3 > 0 ? 0.0f : expandableNotificationRow.getTopCornerRadius();
        int i5 = locationOnScreen[1] + max;
        int i6 = locationOnScreen[0];
        LaunchAnimationParameters launchAnimationParameters = new LaunchAnimationParameters(i4, i5, i6, expandableNotificationRow.getWidth() + i6, topCornerRadius, expandableNotificationRow.getBottomCornerRadius());
        launchAnimationParameters.startTranslationZ = expandableNotificationRow.getTranslationZ();
        launchAnimationParameters.startNotificationTop = locationOnScreen[1];
        NotificationStackScrollLayout notificationStackScrollLayout2 = NotificationStackScrollLayoutController.this.mView;
        notificationStackScrollLayout2.getClass();
        launchAnimationParameters.notificationParentTop = notificationStackScrollLayout2.getLocationOnScreen()[1];
        launchAnimationParameters.startRoundedTopClipping = i3;
        launchAnimationParameters.startClipTopAmount = expandableNotificationRow.mClipTopAmount;
        if (expandableNotificationRow.isChildInGroup()) {
            int i7 = i - expandableNotificationRow.mNotificationParent.getLocationOnScreen()[1];
            if (i7 < 0) {
                i7 = 0;
            }
            launchAnimationParameters.parentStartRoundedTopClipping = i7;
            int i8 = expandableNotificationRow.mNotificationParent.mClipTopAmount;
            launchAnimationParameters.parentStartClipTopAmount = i8;
            if (i8 != 0) {
                float translationY = i8 - expandableNotificationRow.getTranslationY();
                if (translationY > 0.0f) {
                    launchAnimationParameters.startClipTopAmount = (int) Math.ceil(translationY);
                }
            }
        }
        return launchAnimationParameters;
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final ViewGroup getTransitionContainer() {
        return (ViewGroup) this.notification.getRootView();
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final boolean isLaunching() {
        return this.isLaunching;
    }

    @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
    public final void onIntentStarted(boolean z) {
        String str = "onIntentStarted(willAnimate=" + z + ")";
        if (ActivityTransitionAnimator.DEBUG_TRANSITION_ANIMATION) {
            Log.d("NotificationLaunchAnimatorController", str);
        }
        this.notificationLaunchAnimationInteractor.setIsLaunchAnimationRunning(z);
        this.notificationEntry.mExpandAnimationRunning = z;
        if (z) {
            return;
        }
        removeHun(str, true);
    }

    @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
    public final void onTransitionAnimationCancelled() {
        if (ActivityTransitionAnimator.DEBUG_TRANSITION_ANIMATION) {
            Log.d("NotificationLaunchAnimatorController", "onLaunchAnimationCancelled()");
        }
        this.notificationLaunchAnimationInteractor.setIsLaunchAnimationRunning(false);
        this.notificationEntry.mExpandAnimationRunning = false;
        removeHun("onLaunchAnimationCancelled()", true);
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationEnd(boolean z) {
        if (ActivityTransitionAnimator.DEBUG_TRANSITION_ANIMATION) {
            Log.d("NotificationLaunchAnimatorController", "onLaunchAnimationEnd()");
        }
        this.jankMonitor.end(16);
        this.notification.setExpandAnimationRunning(false);
        this.notificationLaunchAnimationInteractor.setIsLaunchAnimationRunning(false);
        this.notificationEntry.mExpandAnimationRunning = false;
        this.notificationListContainer.setExpandingNotification(null);
        applyParams(null);
        removeHun("onLaunchAnimationEnd()", false);
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
        LaunchAnimationParameters launchAnimationParameters = (LaunchAnimationParameters) state;
        launchAnimationParameters.progress = f;
        launchAnimationParameters.linearProgress = f2;
        applyParams(launchAnimationParameters);
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationStart(boolean z) {
        ExpandableNotificationRow expandableNotificationRow = this.notification;
        expandableNotificationRow.setExpandAnimationRunning(true);
        this.notificationListContainer.setExpandingNotification(expandableNotificationRow);
        this.jankMonitor.begin(expandableNotificationRow, 16);
    }

    public final void removeHun(String str, boolean z) {
        GroupEntry groupEntry = this.notificationEntry.mAttachState.parent;
        NotificationEntry notificationEntry = groupEntry != null ? groupEntry.mSummary : null;
        HeadsUpManager headsUpManager = this.headsUpManager;
        BaseHeadsUpManager baseHeadsUpManager = (BaseHeadsUpManager) headsUpManager;
        boolean isHeadsUpEntry = baseHeadsUpManager.isHeadsUpEntry(this.notificationKey);
        ExpandableNotificationRow expandableNotificationRow = this.notification;
        ExpandableNotificationRow expandableNotificationRow2 = isHeadsUpEntry ? expandableNotificationRow : (notificationEntry != null && baseHeadsUpManager.isHeadsUpEntry(notificationEntry.mKey)) ? notificationEntry.row : null;
        if (expandableNotificationRow2 == null) {
            return;
        }
        expandableNotificationRow.setTag(R.id.is_clicked_heads_up_tag, z ? Boolean.TRUE : null);
        ((HeadsUpManagerPhone) headsUpManager).removeNotification(expandableNotificationRow2.mEntry.mKey, str, z);
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void setTransitionContainer(ViewGroup viewGroup) {
    }
}
