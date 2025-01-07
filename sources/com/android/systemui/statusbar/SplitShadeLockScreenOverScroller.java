package com.android.systemui.statusbar;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.MathUtils;
import android.view.animation.PathInterpolator;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import com.android.app.animation.Interpolators;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.scrim.ScrimView;
import com.android.systemui.statusbar.LockscreenShadeTransitionController$splitShadeOverScroller$2;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt__IndentKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplitShadeLockScreenOverScroller implements LockScreenShadeOverScroller {
    public static final PathInterpolator RELEASE_OVER_SCROLL_INTERPOLATOR = new PathInterpolator(0.17f, 0.0f, 0.0f, 1.0f);
    public final Context context;
    public float expansionDragDownAmount;
    public int maxOverScrollAmount;
    public final Function0 nsslControllerProvider;
    public int previousOverscrollAmount;
    public final Function0 qSProvider;
    public Animator releaseOverScrollAnimator;
    public long releaseOverScrollDuration;
    public final ScrimController scrimController;
    public final SysuiStatusBarStateController statusBarStateController;
    public int transitionToFullShadeDistance;

    public SplitShadeLockScreenOverScroller(ConfigurationController configurationController, DumpManager dumpManager, Context context, ScrimController scrimController, SysuiStatusBarStateController sysuiStatusBarStateController, Function0 function0, Function0 function02) {
        this.context = context;
        this.scrimController = scrimController;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.qSProvider = function0;
        this.nsslControllerProvider = function02;
        Resources resources = context.getResources();
        this.transitionToFullShadeDistance = resources.getDimensionPixelSize(R.dimen.lockscreen_shade_full_transition_distance);
        this.maxOverScrollAmount = resources.getDimensionPixelSize(R.dimen.lockscreen_shade_max_over_scroll_amount);
        this.releaseOverScrollDuration = resources.getInteger(R.integer.lockscreen_shade_over_scroll_release_duration);
        ((ConfigurationControllerImpl) configurationController).addCallback(new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.SplitShadeLockScreenOverScroller.1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                SplitShadeLockScreenOverScroller splitShadeLockScreenOverScroller = SplitShadeLockScreenOverScroller.this;
                Resources resources2 = splitShadeLockScreenOverScroller.context.getResources();
                splitShadeLockScreenOverScroller.transitionToFullShadeDistance = resources2.getDimensionPixelSize(R.dimen.lockscreen_shade_full_transition_distance);
                splitShadeLockScreenOverScroller.maxOverScrollAmount = resources2.getDimensionPixelSize(R.dimen.lockscreen_shade_max_over_scroll_amount);
                splitShadeLockScreenOverScroller.releaseOverScrollDuration = resources2.getInteger(R.integer.lockscreen_shade_over_scroll_release_duration);
            }
        });
        dumpManager.registerCriticalDumpable("SplitShadeLockscreenOverScroller", new Dumpable() { // from class: com.android.systemui.statusbar.SplitShadeLockScreenOverScroller.2
            @Override // com.android.systemui.Dumpable
            public final void dump(PrintWriter printWriter, String[] strArr) {
                SplitShadeLockScreenOverScroller splitShadeLockScreenOverScroller = SplitShadeLockScreenOverScroller.this;
                int i = splitShadeLockScreenOverScroller.transitionToFullShadeDistance;
                int i2 = splitShadeLockScreenOverScroller.maxOverScrollAmount;
                long j = splitShadeLockScreenOverScroller.releaseOverScrollDuration;
                int i3 = splitShadeLockScreenOverScroller.previousOverscrollAmount;
                float f = splitShadeLockScreenOverScroller.expansionDragDownAmount;
                StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(i, i2, "\n            SplitShadeLockScreenOverScroller:\n                Resources:\n                    transitionToFullShadeDistance: ", "\n                    maxOverScrollAmount: ", "\n                    releaseOverScrollDuration: ");
                m.append(j);
                m.append("\n                State:\n                    previousOverscrollAmount: ");
                m.append(i3);
                m.append("\n                    expansionDragDownAmount: ");
                m.append(f);
                m.append("\n            ");
                printWriter.println(StringsKt__IndentKt.trimIndent(m.toString()));
            }
        });
    }

    public final void finishAnimations$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        Animator animator = this.releaseOverScrollAnimator;
        if (animator != null) {
            animator.end();
        }
        this.releaseOverScrollAnimator = null;
    }

    @Override // com.android.systemui.statusbar.LockScreenShadeOverScroller
    public final void setExpansionDragDownAmount(float f) {
        int i;
        if (this.expansionDragDownAmount == f) {
            return;
        }
        this.expansionDragDownAmount = f;
        SysuiStatusBarStateController sysuiStatusBarStateController = this.statusBarStateController;
        if (((StatusBarStateControllerImpl) sysuiStatusBarStateController).mState != 1) {
            if (((StatusBarStateControllerImpl) sysuiStatusBarStateController).mState == 1 || (i = this.previousOverscrollAmount) == 0) {
                return;
            }
            ValueAnimator ofInt = ValueAnimator.ofInt(i, 0);
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.SplitShadeLockScreenOverScroller$releaseOverScroll$1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    QS qs = (QS) ((LockscreenShadeTransitionController$splitShadeOverScroller$2.AnonymousClass1) SplitShadeLockScreenOverScroller.this.qSProvider).invoke();
                    if (qs != null) {
                        qs.setOverScrollAmount(intValue);
                    }
                    ScrimView scrimView = SplitShadeLockScreenOverScroller.this.scrimController.mNotificationsScrim;
                    if (scrimView != null) {
                        scrimView.setTranslationY(intValue);
                    }
                    ((NotificationStackScrollLayoutController) ((LockscreenShadeTransitionController$splitShadeOverScroller$2.AnonymousClass2) SplitShadeLockScreenOverScroller.this.nsslControllerProvider).invoke()).setOverScrollAmount(intValue);
                }
            });
            ofInt.setInterpolator(RELEASE_OVER_SCROLL_INTERPOLATOR);
            ofInt.setDuration(this.releaseOverScrollDuration);
            ofInt.start();
            this.releaseOverScrollAnimator = ofInt;
            this.previousOverscrollAmount = 0;
            return;
        }
        Function0 function0 = this.nsslControllerProvider;
        float height = ((NotificationStackScrollLayoutController) ((LockscreenShadeTransitionController$splitShadeOverScroller$2.AnonymousClass2) function0).invoke()).mView.getHeight();
        int overshootInterpolation = (int) (Interpolators.getOvershootInterpolation(MathUtils.saturate(f / height), this.transitionToFullShadeDistance / height) * this.maxOverScrollAmount);
        QS qs = (QS) ((LockscreenShadeTransitionController$splitShadeOverScroller$2.AnonymousClass1) this.qSProvider).invoke();
        if (qs != null) {
            qs.setOverScrollAmount(overshootInterpolation);
        }
        ScrimView scrimView = this.scrimController.mNotificationsScrim;
        if (scrimView != null) {
            scrimView.setTranslationY(overshootInterpolation);
        }
        ((NotificationStackScrollLayoutController) ((LockscreenShadeTransitionController$splitShadeOverScroller$2.AnonymousClass2) function0).invoke()).setOverScrollAmount(overshootInterpolation);
        this.previousOverscrollAmount = overshootInterpolation;
    }
}
