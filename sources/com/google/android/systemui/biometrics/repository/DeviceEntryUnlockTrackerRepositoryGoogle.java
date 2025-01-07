package com.google.android.systemui.biometrics.repository;

import android.hardware.biometrics.BiometricManager;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceEntryUnlockTrackerRepositoryGoogle {
    public final BiometricManager biometricManager;
    public final ReadonlySharedFlow deviceEntryUnlockEvent;
    public final KeyguardUnlockAnimationController keyguardUnlockAnimationController;

    public DeviceEntryUnlockTrackerRepositoryGoogle(CoroutineScope coroutineScope, BiometricManager biometricManager, KeyguardUnlockAnimationController keyguardUnlockAnimationController) {
        this.biometricManager = biometricManager;
        this.keyguardUnlockAnimationController = keyguardUnlockAnimationController;
        this.deviceEntryUnlockEvent = FlowKt.shareIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$2(2, null), FlowConflatedKt.conflatedCallbackFlow(new DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$1(this, null))), new DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$3(3, null)), new DeviceEntryUnlockTrackerRepositoryGoogle$deviceEntryUnlockEvent$4(2, null), 0), coroutineScope, SharingStarted.Companion.Lazily, 1);
    }
}
