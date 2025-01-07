package com.android.keyguard;

import android.content.Context;
import android.view.View;
import com.android.systemui.keyguard.ui.view.KeyguardRootView;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shared.animation.UnfoldConstantTranslateAnimator;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.wm.shell.R;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardUnfoldTransition {
    public final Context context;
    public final Function0 filterKeyguard;
    public final Function0 filterKeyguardAndSplitShadeOnly;
    public final KeyguardRootView keyguardRootView;
    public final NotificationShadeWindowView shadeWindowView;
    public final Lazy shortcutButtonsAnimator$delegate;
    public boolean statusViewCentered;
    public final Lazy translateAnimator$delegate;

    public KeyguardUnfoldTransition(Context context, KeyguardRootView keyguardRootView, NotificationShadeWindowView notificationShadeWindowView, final StatusBarStateController statusBarStateController, final UnfoldTransitionProgressProvider unfoldTransitionProgressProvider) {
        this.context = context;
        this.keyguardRootView = keyguardRootView;
        this.shadeWindowView = notificationShadeWindowView;
        this.filterKeyguardAndSplitShadeOnly = new Function0() { // from class: com.android.keyguard.KeyguardUnfoldTransition$filterKeyguardAndSplitShadeOnly$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(StatusBarStateController.this.getState() == 1 && !this.statusViewCentered);
            }
        };
        this.filterKeyguard = new Function0() { // from class: com.android.keyguard.KeyguardUnfoldTransition$filterKeyguard$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(StatusBarStateController.this.getState() == 1);
            }
        };
        this.translateAnimator$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.keyguard.KeyguardUnfoldTransition$translateAnimator$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                KeyguardUnfoldTransition$translateAnimator$2$smartSpaceViews$scrollXTranslation$1 keyguardUnfoldTransition$translateAnimator$2$smartSpaceViews$scrollXTranslation$1 = new Function2() { // from class: com.android.keyguard.KeyguardUnfoldTransition$translateAnimator$2$smartSpaceViews$scrollXTranslation$1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        ((View) obj).setScrollX(-((int) ((Number) obj2).floatValue()));
                        return Unit.INSTANCE;
                    }
                };
                UnfoldConstantTranslateAnimator.Direction direction = UnfoldConstantTranslateAnimator.Direction.START;
                Function0 function0 = KeyguardUnfoldTransition.this.filterKeyguard;
                Set of = SetsKt.setOf(new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.date_smartspace_view, direction, function0, keyguardUnfoldTransition$translateAnimator$2$smartSpaceViews$scrollXTranslation$1), new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.bc_smartspace_view, direction, function0, keyguardUnfoldTransition$translateAnimator$2$smartSpaceViews$scrollXTranslation$1), new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.weather_smartspace_view, direction, function0, keyguardUnfoldTransition$translateAnimator$2$smartSpaceViews$scrollXTranslation$1));
                KeyguardUnfoldTransition keyguardUnfoldTransition = KeyguardUnfoldTransition.this;
                Function0 function02 = keyguardUnfoldTransition.filterKeyguardAndSplitShadeOnly;
                return new UnfoldConstantTranslateAnimator(SetsKt.plus(SetsKt.setOf(new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.lockscreen_clock_view_large, direction, function02), new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.lockscreen_clock_view, direction, keyguardUnfoldTransition.filterKeyguard), new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.notification_stack_scroller, UnfoldConstantTranslateAnimator.Direction.END, function02)), (Iterable) of), unfoldTransitionProgressProvider);
            }
        });
        this.shortcutButtonsAnimator$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.keyguard.KeyguardUnfoldTransition$shortcutButtonsAnimator$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                UnfoldConstantTranslateAnimator.Direction direction = UnfoldConstantTranslateAnimator.Direction.START;
                Function0 function0 = KeyguardUnfoldTransition.this.filterKeyguard;
                return new UnfoldConstantTranslateAnimator(SetsKt.setOf(new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.start_button, direction, function0), new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.end_button, UnfoldConstantTranslateAnimator.Direction.END, function0)), unfoldTransitionProgressProvider);
            }
        });
    }
}
