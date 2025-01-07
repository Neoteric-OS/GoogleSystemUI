package com.android.systemui.statusbar.notification.stack;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.ArrayMap;
import android.util.Property;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class AnimationProperties {
    public long delay;
    public long duration;
    public Consumer mAnimationCancelAction;
    public Consumer mAnimationEndAction;
    public ArrayMap mInterpolatorMap;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.AnimationProperties$1, reason: invalid class name */
    public final class AnonymousClass1 extends AnimationFilter {
        @Override // com.android.systemui.statusbar.notification.stack.AnimationFilter
        public final boolean shouldAnimateProperty(Property property) {
            return true;
        }
    }

    public AnimationFilter getAnimationFilter() {
        return new AnonymousClass1();
    }

    public AnimatorListenerAdapter getAnimationFinishListener(final Property property) {
        final Consumer consumer = this.mAnimationEndAction;
        if (consumer == null && this.mAnimationCancelAction == null) {
            return null;
        }
        final Consumer consumer2 = this.mAnimationCancelAction;
        return new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.notification.stack.AnimationProperties.2
            public boolean mCancelled;

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                this.mCancelled = true;
                Consumer consumer3 = consumer2;
                if (consumer3 != null) {
                    consumer3.accept(property);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                Consumer consumer3;
                if (this.mCancelled || (consumer3 = consumer) == null) {
                    return;
                }
                consumer3.accept(property);
            }
        };
    }

    public final void setCustomInterpolator(Property property, Interpolator interpolator) {
        if (this.mInterpolatorMap == null) {
            this.mInterpolatorMap = new ArrayMap();
        }
        this.mInterpolatorMap.put(property, interpolator);
    }

    public boolean wasAdded(View view) {
        return false;
    }
}
