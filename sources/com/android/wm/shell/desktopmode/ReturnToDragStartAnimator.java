package com.android.wm.shell.desktopmode;

import android.animation.Animator;
import android.animation.RectEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.view.SurfaceControl;
import android.widget.Toast;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.wm.shell.R;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecoration;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ReturnToDragStartAnimator {
    public Animator boundsAnimator;
    public final Context context;
    public final InteractionJankMonitor interactionJankMonitor;
    public DesktopModeWindowDecorViewModel.DragStartListenerImpl taskRepositionAnimationListener;

    public ReturnToDragStartAnimator(Context context, InteractionJankMonitor interactionJankMonitor) {
        this.context = context;
        this.interactionJankMonitor = interactionJankMonitor;
    }

    public final void start(final int i, final SurfaceControl surfaceControl, final Rect rect, final Rect rect2, final boolean z) {
        final SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        Animator animator = this.boundsAnimator;
        if (animator != null) {
            animator.cancel();
        }
        ValueAnimator duration = ValueAnimator.ofObject(new RectEvaluator(), rect, rect2).setDuration(300L);
        Intrinsics.checkNotNull(duration);
        duration.addListener(new Animator.AnimatorListener() { // from class: com.android.wm.shell.desktopmode.ReturnToDragStartAnimator$start$lambda$2$$inlined$addListener$default$1
            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator2) {
                ReturnToDragStartAnimator.this.getClass();
                SurfaceControl.Transaction transaction2 = new SurfaceControl.Transaction();
                SurfaceControl surfaceControl2 = surfaceControl;
                Rect rect3 = rect2;
                transaction2.setPosition(surfaceControl2, rect3.left, rect3.top).show(surfaceControl).apply();
                DesktopModeWindowDecorViewModel.DragStartListenerImpl dragStartListenerImpl = ReturnToDragStartAnimator.this.taskRepositionAnimationListener;
                if (dragStartListenerImpl == null) {
                    dragStartListenerImpl = null;
                }
                DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) DesktopModeWindowDecorViewModel.this.mWindowDecorByTaskId.get(i);
                if (desktopModeWindowDecoration != null) {
                    desktopModeWindowDecoration.setAnimatingTaskResizeOrReposition(false);
                }
                ReturnToDragStartAnimator returnToDragStartAnimator = ReturnToDragStartAnimator.this;
                returnToDragStartAnimator.boundsAnimator = null;
                if (!z) {
                    Toast.makeText(returnToDragStartAnimator.context, R.string.desktop_mode_non_resizable_snap_text, 0).show();
                }
                ReturnToDragStartAnimator.this.interactionJankMonitor.end(118);
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator2) {
                ReturnToDragStartAnimator.this.getClass();
                SurfaceControl.Transaction transaction2 = new SurfaceControl.Transaction();
                SurfaceControl surfaceControl2 = surfaceControl;
                Rect rect3 = rect;
                transaction2.setPosition(surfaceControl2, rect3.left, rect3.top).show(surfaceControl).apply();
                DesktopModeWindowDecorViewModel.DragStartListenerImpl dragStartListenerImpl = ReturnToDragStartAnimator.this.taskRepositionAnimationListener;
                if (dragStartListenerImpl == null) {
                    dragStartListenerImpl = null;
                }
                DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) DesktopModeWindowDecorViewModel.this.mWindowDecorByTaskId.get(i);
                if (desktopModeWindowDecoration != null) {
                    desktopModeWindowDecoration.setAnimatingTaskResizeOrReposition(true);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationRepeat(Animator animator2) {
            }
        });
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.desktopmode.ReturnToDragStartAnimator$start$1$3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                Rect rect3 = (Rect) valueAnimator.getAnimatedValue();
                transaction.setPosition(surfaceControl, rect3.left, rect3.top).show(surfaceControl).apply();
            }
        });
        duration.start();
        this.boundsAnimator = duration;
    }
}
