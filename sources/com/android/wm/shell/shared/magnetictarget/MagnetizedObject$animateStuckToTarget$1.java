package com.android.wm.shell.shared.magnetictarget;

import androidx.dynamicanimation.animation.FloatPropertyCompat;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.android.wm.shell.shared.magnetictarget.MagnetizedObject;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final /* synthetic */ class MagnetizedObject$animateStuckToTarget$1 extends FunctionReferenceImpl implements Function5 {
    @Override // kotlin.jvm.functions.Function5
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        MagnetizedObject.MagneticTarget magneticTarget = (MagnetizedObject.MagneticTarget) obj;
        float floatValue = ((Number) obj2).floatValue();
        float floatValue2 = ((Number) obj3).floatValue();
        boolean booleanValue = ((Boolean) obj4).booleanValue();
        Function0 function0 = (Function0) obj5;
        MagnetizedObject magnetizedObject = (MagnetizedObject) this.receiver;
        magnetizedObject.getClass();
        magneticTarget.targetView.post(new MagnetizedObject$MagneticTarget$updateLocationOnScreen$1(magneticTarget));
        Object obj6 = magnetizedObject.underlyingObject;
        magnetizedObject.getLocationOnScreen(obj6, magnetizedObject.objectLocationOnScreen);
        float width = (magneticTarget.centerOnScreen.x - (magnetizedObject.getWidth(obj6) / 2.0f)) - r1[0];
        float height = (magneticTarget.centerOnScreen.y - (magnetizedObject.getHeight(obj6) / 2.0f)) - r1[1];
        PhysicsAnimator.SpringConfig springConfig = booleanValue ? magnetizedObject.flungIntoTargetSpringConfig : magnetizedObject.springConfig;
        magnetizedObject.cancelAnimations$frameworks__base__libs__WindowManager__Shell__android_common__WindowManager_Shell_shared();
        FloatPropertyCompat floatPropertyCompat = magnetizedObject.xProperty;
        float value = floatPropertyCompat.getValue(obj6) + width;
        PhysicsAnimator physicsAnimator = magnetizedObject.animator;
        physicsAnimator.spring(floatPropertyCompat, value, floatValue, springConfig);
        FloatPropertyCompat floatPropertyCompat2 = magnetizedObject.yProperty;
        physicsAnimator.spring(floatPropertyCompat2, floatPropertyCompat2.getValue(obj6) + height, floatValue2, springConfig);
        if (function0 != null) {
            physicsAnimator.withEndActions(function0);
        }
        physicsAnimator.start();
        return Unit.INSTANCE;
    }
}
