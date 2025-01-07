package com.android.systemui.shade;

import android.content.Context;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shared.animation.UnfoldConstantTranslateAnimator;
import com.android.systemui.unfold.util.NaturalRotationUnfoldProgressProvider;
import com.android.wm.shell.R;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationPanelUnfoldAnimationController {
    public final Context context;
    public final Function0 filterShade;
    public final Lazy translateAnimator$delegate;
    public final Lazy translateAnimatorStatusBar$delegate;

    public NotificationPanelUnfoldAnimationController(Context context, final StatusBarStateController statusBarStateController, final NaturalRotationUnfoldProgressProvider naturalRotationUnfoldProgressProvider) {
        this.context = context;
        this.filterShade = new Function0() { // from class: com.android.systemui.shade.NotificationPanelUnfoldAnimationController$filterShade$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(StatusBarStateController.this.getState() == 0 || StatusBarStateController.this.getState() == 2);
            }
        };
        this.translateAnimator$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.shade.NotificationPanelUnfoldAnimationController$translateAnimator$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                UnfoldConstantTranslateAnimator.Direction direction = UnfoldConstantTranslateAnimator.Direction.START;
                Function0 function0 = NotificationPanelUnfoldAnimationController.this.filterShade;
                return new UnfoldConstantTranslateAnimator(SetsKt.setOf(new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.quick_settings_panel, direction, function0), new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.qs_footer_actions, direction, function0), new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.notification_stack_scroller, UnfoldConstantTranslateAnimator.Direction.END, function0)), naturalRotationUnfoldProgressProvider);
            }
        });
        this.translateAnimatorStatusBar$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.shade.NotificationPanelUnfoldAnimationController$translateAnimatorStatusBar$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                UnfoldConstantTranslateAnimator.Direction direction = UnfoldConstantTranslateAnimator.Direction.END;
                Function0 function0 = NotificationPanelUnfoldAnimationController.this.filterShade;
                UnfoldConstantTranslateAnimator.ViewIdToTranslate viewIdToTranslate = new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.shade_header_system_icons, direction, function0);
                UnfoldConstantTranslateAnimator.ViewIdToTranslate viewIdToTranslate2 = new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.privacy_container, direction, function0);
                UnfoldConstantTranslateAnimator.ViewIdToTranslate viewIdToTranslate3 = new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.carrier_group, direction, function0);
                UnfoldConstantTranslateAnimator.Direction direction2 = UnfoldConstantTranslateAnimator.Direction.START;
                return new UnfoldConstantTranslateAnimator(SetsKt.setOf(viewIdToTranslate, viewIdToTranslate2, viewIdToTranslate3, new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.clock, direction2, function0), new UnfoldConstantTranslateAnimator.ViewIdToTranslate(R.id.date, direction2, function0)), naturalRotationUnfoldProgressProvider);
            }
        });
    }
}
