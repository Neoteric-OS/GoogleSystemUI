package com.android.systemui.statusbar.notification.stack;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Property;
import android.view.View;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StackStateAnimator {
    public final AnonymousClass1 mAnimationProperties;
    public ValueAnimator mBottomOverScrollAnimator;
    public long mCurrentAdditionalDelay;
    public long mCurrentLength;
    int mGoToFullShadeAppearingTranslation;
    public int mHeadsUpAppearHeightBottom;
    float mHeadsUpAppearStartAboveScreen;
    public float mHeadsUpCyclingPadding;
    public final NotificationStackScrollLayout mHostLayout;
    public StackStateLogger mLogger;
    public boolean mShadeExpanded;
    public NotificationShelf mShelf;
    public int mStackTopMargin;
    public ValueAnimator mTopOverScrollAnimator;
    public final ExpandableViewState mTmpState = new ExpandableViewState();
    public final ArrayList mNewEvents = new ArrayList();
    public final ArrayList mNewAddChildren = new ArrayList();
    public final HashSet mHeadsUpAppearChildren = new HashSet();
    public final HashSet mHeadsUpDisappearChildren = new HashSet();
    public final HashSet mAnimatorSet = new HashSet();
    public final Stack mAnimationListenerPool = new Stack();
    public final AnimationFilter mAnimationFilter = new AnimationFilter();
    public final ArrayList mTransientViewsToRemove = new ArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.StackStateAnimator$1, reason: invalid class name */
    public final class AnonymousClass1 extends AnimationProperties {
        public AnonymousClass1() {
        }

        @Override // com.android.systemui.statusbar.notification.stack.AnimationProperties
        public final AnimationFilter getAnimationFilter() {
            return StackStateAnimator.this.mAnimationFilter;
        }

        @Override // com.android.systemui.statusbar.notification.stack.AnimationProperties
        public final AnimatorListenerAdapter getAnimationFinishListener(Property property) {
            return StackStateAnimator.this.getGlobalAnimationFinishedListener();
        }

        @Override // com.android.systemui.statusbar.notification.stack.AnimationProperties
        public final boolean wasAdded(View view) {
            return StackStateAnimator.this.mNewAddChildren.contains(view);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.StackStateAnimator$2, reason: invalid class name */
    public final class AnonymousClass2 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId = 0;
        public boolean mWasCancelled;

        public AnonymousClass2() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    this.mWasCancelled = true;
                    break;
                default:
                    super.onAnimationCancel(animator);
                    break;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    StackStateAnimator.this.mAnimatorSet.remove(animator);
                    if (StackStateAnimator.this.mAnimatorSet.isEmpty() && !this.mWasCancelled) {
                        StackStateAnimator.this.onAnimationFinished();
                    }
                    StackStateAnimator.this.mAnimationListenerPool.push(this);
                    break;
                default:
                    if (!this.mWasCancelled) {
                        StackStateAnimator.this.mBottomOverScrollAnimator = null;
                        break;
                    } else {
                        StackStateAnimator.this.mTopOverScrollAnimator = null;
                        break;
                    }
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    this.mWasCancelled = false;
                    StackStateAnimator.this.mAnimatorSet.add(animator);
                    break;
                default:
                    super.onAnimationStart(animator);
                    break;
            }
        }

        public AnonymousClass2(boolean z) {
            this.mWasCancelled = z;
        }
    }

    public StackStateAnimator(Context context, NotificationStackScrollLayout notificationStackScrollLayout) {
        this.mHostLayout = notificationStackScrollLayout;
        initView(context);
        this.mAnimationProperties = new AnonymousClass1();
    }

    public final AnimatorListenerAdapter getGlobalAnimationFinishedListener() {
        return !this.mAnimationListenerPool.empty() ? (AnimatorListenerAdapter) this.mAnimationListenerPool.pop() : new AnonymousClass2();
    }

    public final void initView(Context context) {
        this.mGoToFullShadeAppearingTranslation = context.getResources().getDimensionPixelSize(R.dimen.go_to_full_shade_appearing_translation);
        this.mHeadsUpAppearStartAboveScreen = context.getResources().getDimensionPixelSize(R.dimen.heads_up_appear_y_above_screen);
        this.mHeadsUpCyclingPadding = context.getResources().getDimensionPixelSize(R.dimen.heads_up_cycling_padding);
    }

    public final void onAnimationFinished() {
        NotificationStackScrollLayout notificationStackScrollLayout = this.mHostLayout;
        notificationStackScrollLayout.setAnimationRunning(false);
        notificationStackScrollLayout.requestChildrenUpdate();
        Iterator it = notificationStackScrollLayout.mAnimationFinishedRunnables.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        notificationStackScrollLayout.mAnimationFinishedRunnables.clear();
        Iterator it2 = notificationStackScrollLayout.mClearTransientViewsWhenFinished.iterator();
        while (it2.hasNext()) {
            ((ExpandableView) it2.next()).removeFromTransientContainer();
        }
        notificationStackScrollLayout.mClearTransientViewsWhenFinished.clear();
        for (int i = 0; i < notificationStackScrollLayout.getChildCount(); i++) {
            View childAt = notificationStackScrollLayout.getChildAt(i);
            if (childAt instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) childAt;
                expandableNotificationRow.setHeadsUpAnimatingAway(false);
                if (expandableNotificationRow.mIsSummaryWithChildren) {
                    Iterator it3 = expandableNotificationRow.getAttachedChildren().iterator();
                    while (it3.hasNext()) {
                        ((ExpandableNotificationRow) it3.next()).setHeadsUpAnimatingAway(false);
                    }
                }
            }
        }
        notificationStackScrollLayout.finalizeClearAllAnimation();
        Iterator it4 = this.mTransientViewsToRemove.iterator();
        while (it4.hasNext()) {
            ((ExpandableView) it4.next()).removeFromTransientContainer();
        }
        this.mTransientViewsToRemove.clear();
    }
}
