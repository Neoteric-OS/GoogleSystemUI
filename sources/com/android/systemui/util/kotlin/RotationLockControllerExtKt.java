package com.android.systemui.util.kotlin;

import com.android.systemui.statusbar.policy.RotationLockController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class RotationLockControllerExtKt {
    public static final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 isRotationLockEnabled(RotationLockController rotationLockController) {
        return new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new RotationLockControllerExtKt$isRotationLockEnabled$2(rotationLockController, null), FlowConflatedKt.conflatedCallbackFlow(new RotationLockControllerExtKt$isRotationLockEnabled$1(rotationLockController, null)));
    }
}
