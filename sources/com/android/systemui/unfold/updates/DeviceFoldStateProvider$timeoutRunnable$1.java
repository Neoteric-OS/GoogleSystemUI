package com.android.systemui.unfold.updates;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceFoldStateProvider$timeoutRunnable$1 implements Runnable {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Object this$0;

    public DeviceFoldStateProvider$timeoutRunnable$1(DeviceFoldStateProvider deviceFoldStateProvider) {
        this.this$0 = deviceFoldStateProvider;
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                DeviceFoldStateProvider deviceFoldStateProvider = (DeviceFoldStateProvider) this.this$0;
                deviceFoldStateProvider.notifyFoldUpdate(2, deviceFoldStateProvider.lastHingeAngle);
                break;
            default:
                ((Lambda) this.this$0).invoke();
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DeviceFoldStateProvider$timeoutRunnable$1(Function0 function0) {
        this.this$0 = (Lambda) function0;
    }
}
