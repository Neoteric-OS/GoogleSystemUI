package com.android.wm.shell.windowdecor;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.SurfaceControl;
import android.view.VelocityTracker;
import com.android.wm.shell.R;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MoveToDesktopAnimator {
    public boolean allowSurfaceChangesOnMove;
    public final ValueAnimator dragToDesktopAnimator;
    public final PointF mostRecentInput;
    public final PointF position;
    public final Rect startBounds;
    public final SurfaceControl taskSurface;
    public final Function0 transactionFactory;
    public final VelocityTracker velocityTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.windowdecor.MoveToDesktopAnimator$1, reason: invalid class name */
    final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0, SurfaceControl.Transaction.class, "<init>", "<init>()V", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new SurfaceControl.Transaction();
        }
    }

    public MoveToDesktopAnimator(Context context, Rect rect, SurfaceControl surfaceControl) {
        AnonymousClass1 anonymousClass1 = AnonymousClass1.INSTANCE;
        this.startBounds = rect;
        this.taskSurface = surfaceControl;
        this.transactionFactory = anonymousClass1;
        this.mostRecentInput = new PointF();
        this.velocityTracker = VelocityTracker.obtain();
        ValueAnimator duration = ValueAnimator.ofFloat(1.0f, 0.4f).setDuration(336L);
        final SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        final float dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.desktop_mode_dragged_task_radius);
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.windowdecor.MoveToDesktopAnimator$dragToDesktopAnimator$1$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                MoveToDesktopAnimator moveToDesktopAnimator = MoveToDesktopAnimator.this;
                PointF pointF = moveToDesktopAnimator.mostRecentInput;
                moveToDesktopAnimator.setTaskPosition(pointF.x, pointF.y);
                SurfaceControl.Transaction transaction2 = transaction;
                MoveToDesktopAnimator moveToDesktopAnimator2 = MoveToDesktopAnimator.this;
                SurfaceControl.Transaction cornerRadius = transaction2.setScale(moveToDesktopAnimator2.taskSurface, moveToDesktopAnimator2.getScale(), MoveToDesktopAnimator.this.getScale()).setCornerRadius(MoveToDesktopAnimator.this.taskSurface, dimensionPixelSize);
                MoveToDesktopAnimator moveToDesktopAnimator3 = MoveToDesktopAnimator.this;
                SurfaceControl.Transaction cornerRadius2 = cornerRadius.setScale(moveToDesktopAnimator3.taskSurface, moveToDesktopAnimator3.getScale(), MoveToDesktopAnimator.this.getScale()).setCornerRadius(MoveToDesktopAnimator.this.taskSurface, dimensionPixelSize);
                MoveToDesktopAnimator moveToDesktopAnimator4 = MoveToDesktopAnimator.this;
                SurfaceControl surfaceControl2 = moveToDesktopAnimator4.taskSurface;
                PointF pointF2 = moveToDesktopAnimator4.position;
                cornerRadius2.setPosition(surfaceControl2, pointF2.x, pointF2.y).apply();
            }
        });
        this.dragToDesktopAnimator = duration;
        this.position = new PointF(0.0f, 0.0f);
    }

    public final float getScale() {
        return ((Float) this.dragToDesktopAnimator.getAnimatedValue()).floatValue();
    }

    public final void setTaskPosition(float f, float f2) {
        this.position.x = f - ((((Float) this.dragToDesktopAnimator.getAnimatedValue()).floatValue() * this.startBounds.width()) / 2);
        this.position.y = f2;
    }
}
