package com.android.systemui.keyguard;

import android.view.IRemoteAnimationFinishedCallback;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowManagerOcclusionManager$occludeAnimationRunner$1$onAnimationStart$1 extends IRemoteAnimationFinishedCallback.Stub {
    public final /* synthetic */ IRemoteAnimationFinishedCallback $finishedCallback;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ WindowManagerOcclusionManager this$0;

    public /* synthetic */ WindowManagerOcclusionManager$occludeAnimationRunner$1$onAnimationStart$1(IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback, WindowManagerOcclusionManager windowManagerOcclusionManager, int i) {
        this.$r8$classId = i;
        this.$finishedCallback = iRemoteAnimationFinishedCallback;
        this.this$0 = windowManagerOcclusionManager;
    }

    public final void onAnimationFinished() {
        switch (this.$r8$classId) {
            case 0:
                IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback = this.$finishedCallback;
                if (iRemoteAnimationFinishedCallback != null) {
                    iRemoteAnimationFinishedCallback.onAnimationFinished();
                }
                this.this$0.occludeAnimationFinishedCallback = null;
                break;
            default:
                IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback2 = this.$finishedCallback;
                if (iRemoteAnimationFinishedCallback2 != null) {
                    iRemoteAnimationFinishedCallback2.onAnimationFinished();
                }
                this.this$0.unoccludeAnimationFinishedCallback = null;
                break;
        }
    }
}
