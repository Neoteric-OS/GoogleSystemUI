package com.android.wm.shell.shared.magnetictarget;

import android.content.Context;
import android.graphics.PointF;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.VelocityTracker;
import android.view.View;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import java.util.ArrayList;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class MagnetizedObject {
    public Function5 animateStuckToTarget;
    public final PhysicsAnimator animator;
    public final ArrayList associatedTargets;
    public boolean flingToTargetEnabled;
    public float flingToTargetMinVelocity;
    public float flingToTargetWidthPercent;
    public final float flingUnstuckFromTargetMinVelocity;
    public final PhysicsAnimator.SpringConfig flungIntoTargetSpringConfig;
    public boolean hapticsEnabled;
    public MagnetListener magnetListener;
    public boolean movedBeyondSlop;
    public final int[] objectLocationOnScreen;
    public final PhysicsAnimator.SpringConfig springConfig;
    public final float stickToTargetMaxXVelocity;
    public MagneticTarget targetObjectIsStuckTo;
    public final PointF touchDown;
    public int touchSlop;
    public final Object underlyingObject;
    public final VelocityTracker velocityTracker;
    public final VibrationAttributes vibrationAttributes;
    public final Vibrator vibrator;
    public final FloatPropertyCompat xProperty;
    public final FloatPropertyCompat yProperty;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface MagnetListener {
        void onReleasedInTarget(MagneticTarget magneticTarget, MagnetizedObject magnetizedObject);

        void onStuckToTarget(MagneticTarget magneticTarget, MagnetizedObject magnetizedObject);

        void onUnstuckFromTarget(MagneticTarget magneticTarget, MagnetizedObject magnetizedObject, float f, float f2, boolean z);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MagneticTarget {
        public int magneticFieldRadiusPx;
        public int screenVerticalOffset;
        public final View targetView;
        public final PointF centerOnScreen = new PointF();
        public final int[] tempLoc = new int[2];

        public MagneticTarget(View view, int i) {
            this.targetView = view;
            this.magneticFieldRadiusPx = i;
        }
    }

    public MagnetizedObject(Context context, Object obj, FloatPropertyCompat floatPropertyCompat, FloatPropertyCompat floatPropertyCompat2) {
        this.underlyingObject = obj;
        this.xProperty = floatPropertyCompat;
        this.yProperty = floatPropertyCompat2;
        Function2 function2 = PhysicsAnimator.onAnimatorCreated;
        this.animator = PhysicsAnimator.Companion.getInstance(obj);
        this.objectLocationOnScreen = new int[2];
        this.associatedTargets = new ArrayList();
        this.velocityTracker = VelocityTracker.obtain();
        this.vibrator = (Vibrator) context.getSystemService("vibrator");
        this.vibrationAttributes = VibrationAttributes.createForUsage(18);
        this.touchDown = new PointF();
        this.animateStuckToTarget = new MagnetizedObject$animateStuckToTarget$1(5, this, MagnetizedObject.class, "animateStuckToTargetInternal", "animateStuckToTargetInternal(Lcom/android/wm/shell/shared/magnetictarget/MagnetizedObject$MagneticTarget;FFZLkotlin/jvm/functions/Function0;)V", 0);
        this.flingToTargetEnabled = true;
        this.flingToTargetWidthPercent = 3.0f;
        this.flingToTargetMinVelocity = 4000.0f;
        this.flingUnstuckFromTargetMinVelocity = 4000.0f;
        this.stickToTargetMaxXVelocity = 2000.0f;
        this.hapticsEnabled = true;
        PhysicsAnimator.SpringConfig springConfig = new PhysicsAnimator.SpringConfig(1500.0f, 1.0f);
        this.springConfig = springConfig;
        this.flungIntoTargetSpringConfig = springConfig;
    }

    public final void cancelAnimations$frameworks__base__libs__WindowManager__Shell__android_common__WindowManager_Shell_shared() {
        this.animator.cancel(this.xProperty, this.yProperty);
    }

    public abstract float getHeight(Object obj);

    public abstract void getLocationOnScreen(Object obj, int[] iArr);

    public final boolean getObjectStuckToTarget() {
        return this.targetObjectIsStuckTo != null;
    }

    public abstract float getWidth(Object obj);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:79:0x021e  */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean maybeConsumeMotionEvent(android.view.MotionEvent r18) {
        /*
            Method dump skipped, instructions count: 624
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.shared.magnetictarget.MagnetizedObject.maybeConsumeMotionEvent(android.view.MotionEvent):boolean");
    }

    public final void vibrateIfEnabled(int i) {
        if (this.hapticsEnabled) {
            this.vibrator.vibrate(VibrationEffect.createPredefined(i), this.vibrationAttributes);
        }
    }
}
