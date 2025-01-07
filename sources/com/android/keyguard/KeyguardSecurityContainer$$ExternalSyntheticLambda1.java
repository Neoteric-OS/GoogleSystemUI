package com.android.keyguard;

import android.view.MotionEvent;
import com.android.systemui.Gefingerpoken;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardSecurityContainer$$ExternalSyntheticLambda1 implements Predicate {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ MotionEvent f$0;

    public /* synthetic */ KeyguardSecurityContainer$$ExternalSyntheticLambda1(MotionEvent motionEvent, int i) {
        this.$r8$classId = i;
        this.f$0 = motionEvent;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        int i = this.$r8$classId;
        MotionEvent motionEvent = this.f$0;
        Gefingerpoken gefingerpoken = (Gefingerpoken) obj;
        switch (i) {
            case 0:
                float f = KeyguardSecurityContainer.MIN_BACK_SCALE;
                return gefingerpoken.onTouchEvent(motionEvent);
            default:
                float f2 = KeyguardSecurityContainer.MIN_BACK_SCALE;
                return gefingerpoken.onInterceptTouchEvent(motionEvent);
        }
    }
}
