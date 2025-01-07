package com.android.systemui.statusbar.notification.stack;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;
import com.android.app.animation.Interpolators;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ExpandableViewState extends ViewState {
    public static final /* synthetic */ int $r8$clinit = 0;
    public int clipBottomAmount;
    public int clipTopAmount;
    public boolean headsUpIsVisible;
    public int height;
    public boolean hideSensitive;
    public boolean inShelf;
    public int location;
    public int notGoneIndex;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.ExpandableViewState$2, reason: invalid class name */
    public final class AnonymousClass2 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId = 0;
        public boolean mWasCancelled;
        public final /* synthetic */ ExpandableView val$child;

        public AnonymousClass2(ExpandableView expandableView) {
            this.val$child = expandableView;
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
            int i;
            switch (this.$r8$classId) {
                case 0:
                    ExpandableView expandableView = this.val$child;
                    int i2 = ExpandableViewState.$r8$clinit;
                    expandableView.setTag(R.id.height_animator_tag, null);
                    this.val$child.setTag(R.id.height_animator_start_value_tag, null);
                    this.val$child.setTag(R.id.height_animator_end_value_tag, null);
                    this.val$child.setActualHeightAnimating(false);
                    if (!this.mWasCancelled) {
                        ExpandableView expandableView2 = this.val$child;
                        if (expandableView2 instanceof ExpandableNotificationRow) {
                            ((ExpandableNotificationRow) expandableView2).mGroupExpansionChanging = false;
                            break;
                        }
                    }
                    break;
                default:
                    ExpandableView expandableView3 = this.val$child;
                    if (this.mWasCancelled) {
                        int i3 = ExpandableViewState.$r8$clinit;
                        i = R.id.top_inset_animator_tag;
                    } else {
                        int i4 = ExpandableViewState.$r8$clinit;
                        i = R.id.bottom_inset_animator_tag;
                    }
                    expandableView3.setTag(i, null);
                    this.val$child.setTag(this.mWasCancelled ? R.id.top_inset_animator_start_value_tag : R.id.bottom_inset_animator_start_value_tag, null);
                    this.val$child.setTag(this.mWasCancelled ? R.id.top_inset_animator_end_value_tag : R.id.bottom_inset_animator_end_value_tag, null);
                    break;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    this.mWasCancelled = false;
                    break;
                default:
                    super.onAnimationStart(animator);
                    break;
            }
        }

        public AnonymousClass2(ExpandableView expandableView, boolean z) {
            this.val$child = expandableView;
            this.mWasCancelled = z;
        }
    }

    @Override // com.android.systemui.statusbar.notification.stack.ViewState
    public void animateTo(View view, AnimationProperties animationProperties) {
        super.animateTo(view, animationProperties);
        if (view instanceof ExpandableView) {
            final ExpandableView expandableView = (ExpandableView) view;
            AnimationFilter animationFilter = animationProperties.getAnimationFilter();
            if (this.height != expandableView.mActualHeight) {
                Integer num = (Integer) expandableView.getTag(R.id.height_animator_start_value_tag);
                Integer num2 = (Integer) expandableView.getTag(R.id.height_animator_end_value_tag);
                int i = this.height;
                if (num2 == null || num2.intValue() != i) {
                    ValueAnimator valueAnimator = (ValueAnimator) expandableView.getTag(R.id.height_animator_tag);
                    if (animationProperties.getAnimationFilter().animateHeight) {
                        ValueAnimator ofInt = ValueAnimator.ofInt(expandableView.mActualHeight, i);
                        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.notification.stack.ExpandableViewState.1
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                                ExpandableView.this.setActualHeight(((Integer) valueAnimator2.getAnimatedValue()).intValue(), false);
                            }
                        });
                        ofInt.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
                        ofInt.setDuration(ViewState.cancelAnimatorAndGetNewDuration(animationProperties.duration, valueAnimator));
                        if (animationProperties.delay > 0 && (valueAnimator == null || valueAnimator.getAnimatedFraction() == 0.0f)) {
                            ofInt.setStartDelay(animationProperties.delay);
                        }
                        AnimatorListenerAdapter animationFinishListener = animationProperties.getAnimationFinishListener(null);
                        if (animationFinishListener != null) {
                            ofInt.addListener(animationFinishListener);
                        }
                        ofInt.addListener(new AnonymousClass2(expandableView));
                        ViewState.startAnimator(ofInt, animationFinishListener);
                        expandableView.setTag(R.id.height_animator_tag, ofInt);
                        expandableView.setTag(R.id.height_animator_start_value_tag, Integer.valueOf(expandableView.mActualHeight));
                        expandableView.setTag(R.id.height_animator_end_value_tag, Integer.valueOf(i));
                        expandableView.setActualHeightAnimating(true);
                    } else if (valueAnimator != null) {
                        PropertyValuesHolder[] values = valueAnimator.getValues();
                        int intValue = num.intValue() + (i - num2.intValue());
                        values[0].setIntValues(intValue, i);
                        expandableView.setTag(R.id.height_animator_start_value_tag, Integer.valueOf(intValue));
                        expandableView.setTag(R.id.height_animator_end_value_tag, Integer.valueOf(i));
                        valueAnimator.setCurrentPlayTime(valueAnimator.getCurrentPlayTime());
                    } else {
                        expandableView.setActualHeight(i, false);
                    }
                }
            } else {
                ViewState.abortAnimation(view, R.id.height_animator_tag);
            }
            if (this.clipTopAmount != expandableView.mClipTopAmount) {
                startClipAnimation(expandableView, animationProperties, true);
            } else {
                ViewState.abortAnimation(view, R.id.top_inset_animator_tag);
            }
            if (this.clipBottomAmount != expandableView.mClipBottomAmount) {
                startClipAnimation(expandableView, animationProperties, false);
            } else {
                ViewState.abortAnimation(view, R.id.bottom_inset_animator_tag);
            }
            expandableView.setHideSensitive(this.hideSensitive, animationFilter.animateHideSensitive, animationProperties.delay, animationProperties.duration);
            if (animationProperties.wasAdded(view) && !this.hidden) {
                expandableView.performAddAnimation(animationProperties.delay, animationProperties.duration);
            }
            if (!expandableView.mInShelf && this.inShelf) {
                expandableView.mTransformingInShelf = true;
            }
            expandableView.mInShelf = this.inShelf;
            if (this.headsUpIsVisible) {
                expandableView.markHeadsUpSeen();
            }
        }
    }

    @Override // com.android.systemui.statusbar.notification.stack.ViewState
    public void applyToView(View view) {
        super.applyToView(view);
        if (view instanceof ExpandableView) {
            ExpandableView expandableView = (ExpandableView) view;
            int i = expandableView.mActualHeight;
            int i2 = this.height;
            if (i != i2) {
                expandableView.setActualHeight(i2, false);
            }
            expandableView.setHideSensitive(this.hideSensitive, false, 0L, 0L);
            float f = expandableView.mClipTopAmount;
            int i3 = this.clipTopAmount;
            if (f != i3) {
                expandableView.setClipTopAmount(i3);
            }
            float f2 = expandableView.mClipBottomAmount;
            int i4 = this.clipBottomAmount;
            if (f2 != i4) {
                expandableView.setClipBottomAmount(i4);
            }
            expandableView.mTransformingInShelf = false;
            expandableView.mInShelf = this.inShelf;
            if (this.headsUpIsVisible) {
                expandableView.markHeadsUpSeen();
            }
        }
    }

    @Override // com.android.systemui.statusbar.notification.stack.ViewState
    public final void cancelAnimations(View view) {
        super.cancelAnimations(view);
        Animator animator = (Animator) view.getTag(R.id.height_animator_tag);
        if (animator != null) {
            animator.cancel();
        }
        Animator animator2 = (Animator) view.getTag(R.id.top_inset_animator_tag);
        if (animator2 != null) {
            animator2.cancel();
        }
    }

    @Override // com.android.systemui.statusbar.notification.stack.ViewState
    public void copyFrom(ViewState viewState) {
        super.copyFrom(viewState);
        if (viewState instanceof ExpandableViewState) {
            ExpandableViewState expandableViewState = (ExpandableViewState) viewState;
            this.height = expandableViewState.height;
            this.hideSensitive = expandableViewState.hideSensitive;
            this.clipTopAmount = expandableViewState.clipTopAmount;
            this.notGoneIndex = expandableViewState.notGoneIndex;
            this.location = expandableViewState.location;
            this.headsUpIsVisible = expandableViewState.headsUpIsVisible;
        }
    }

    public final void startClipAnimation(final ExpandableView expandableView, AnimationProperties animationProperties, final boolean z) {
        int i = R.id.bottom_inset_animator_start_value_tag;
        Integer num = (Integer) expandableView.getTag(z ? R.id.top_inset_animator_start_value_tag : R.id.bottom_inset_animator_start_value_tag);
        int i2 = R.id.bottom_inset_animator_end_value_tag;
        Integer num2 = (Integer) expandableView.getTag(z ? R.id.top_inset_animator_end_value_tag : R.id.bottom_inset_animator_end_value_tag);
        int i3 = z ? this.clipTopAmount : this.clipBottomAmount;
        if (num2 == null || num2.intValue() != i3) {
            int i4 = R.id.bottom_inset_animator_tag;
            ValueAnimator valueAnimator = (ValueAnimator) expandableView.getTag(z ? R.id.top_inset_animator_tag : R.id.bottom_inset_animator_tag);
            AnimationFilter animationFilter = animationProperties.getAnimationFilter();
            if ((z && !animationFilter.animateTopInset) || !z) {
                if (valueAnimator == null) {
                    if (z) {
                        expandableView.setClipTopAmount(i3);
                        return;
                    } else {
                        expandableView.setClipBottomAmount(i3);
                        return;
                    }
                }
                PropertyValuesHolder[] values = valueAnimator.getValues();
                int intValue = num.intValue() + (i3 - num2.intValue());
                values[0].setIntValues(intValue, i3);
                if (z) {
                    i = R.id.top_inset_animator_start_value_tag;
                }
                expandableView.setTag(i, Integer.valueOf(intValue));
                if (z) {
                    i2 = R.id.top_inset_animator_end_value_tag;
                }
                expandableView.setTag(i2, Integer.valueOf(i3));
                valueAnimator.setCurrentPlayTime(valueAnimator.getCurrentPlayTime());
                return;
            }
            ValueAnimator ofInt = ValueAnimator.ofInt(z ? expandableView.mClipTopAmount : expandableView.mClipBottomAmount, i3);
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.notification.stack.ExpandableViewState$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    boolean z2 = z;
                    ExpandableView expandableView2 = expandableView;
                    if (z2) {
                        expandableView2.setClipTopAmount(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                    } else {
                        expandableView2.setClipBottomAmount(((Integer) valueAnimator2.getAnimatedValue()).intValue());
                    }
                }
            });
            ofInt.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
            ofInt.setDuration(ViewState.cancelAnimatorAndGetNewDuration(animationProperties.duration, valueAnimator));
            if (animationProperties.delay > 0 && (valueAnimator == null || valueAnimator.getAnimatedFraction() == 0.0f)) {
                ofInt.setStartDelay(animationProperties.delay);
            }
            AnimatorListenerAdapter animationFinishListener = animationProperties.getAnimationFinishListener(null);
            if (animationFinishListener != null) {
                ofInt.addListener(animationFinishListener);
            }
            ofInt.addListener(new AnonymousClass2(expandableView, z));
            ViewState.startAnimator(ofInt, animationFinishListener);
            if (z) {
                i4 = R.id.top_inset_animator_tag;
            }
            expandableView.setTag(i4, ofInt);
            if (z) {
                i = R.id.top_inset_animator_start_value_tag;
            }
            expandableView.setTag(i, Integer.valueOf(z ? expandableView.mClipTopAmount : expandableView.mClipBottomAmount));
            if (z) {
                i2 = R.id.top_inset_animator_end_value_tag;
            }
            expandableView.setTag(i2, Integer.valueOf(i3));
        }
    }
}
