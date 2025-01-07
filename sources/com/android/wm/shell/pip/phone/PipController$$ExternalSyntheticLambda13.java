package com.android.wm.shell.pip.phone;

import android.content.ComponentName;
import android.graphics.Rect;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.onehanded.OneHandedController;
import com.android.wm.shell.onehanded.OneHandedTransitionCallback;
import com.android.wm.shell.pip.PipTaskOrganizer;
import com.android.wm.shell.pip.PipTransitionState;
import com.android.wm.shell.pip.phone.PipController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipController$$ExternalSyntheticLambda13 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ PipController$$ExternalSyntheticLambda13(int i, ComponentName componentName) {
        this.$r8$classId = 2;
        this.f$0 = componentName;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                final PipController pipController = (PipController) obj2;
                pipController.getClass();
                ((OneHandedController) obj).mDisplayAreaOrganizer.mTransitionCallbacks.add(new OneHandedTransitionCallback() { // from class: com.android.wm.shell.pip.phone.PipController.5
                    public AnonymousClass5() {
                    }

                    @Override // com.android.wm.shell.onehanded.OneHandedTransitionCallback
                    public final void onStartFinished(Rect rect) {
                        PipController.this.mTouchHandler.mPipResizeGestureHandler.mOhmOffset = rect.top;
                    }

                    @Override // com.android.wm.shell.onehanded.OneHandedTransitionCallback
                    public final void onStopFinished(Rect rect) {
                        PipController.this.mTouchHandler.mPipResizeGestureHandler.mOhmOffset = rect.top;
                    }
                });
                break;
            case 1:
                ((PipController) obj).setPinnedStackAnimationListener(((PipController.IPipImpl) obj2).mPipAnimationListener);
                break;
            default:
                ComponentName componentName = (ComponentName) obj2;
                PipTaskOrganizer pipTaskOrganizer = ((PipController) obj).mPipTaskOrganizer;
                PipTransitionState pipTransitionState = pipTaskOrganizer.mPipTransitionState;
                if (pipTransitionState.mInSwipePipToHomeTransition) {
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -308783681684358284L, 0, String.valueOf(componentName));
                    }
                    pipTaskOrganizer.mPipTransitionController.sendOnPipTransitionCancelled(2);
                    pipTransitionState.mInSwipePipToHomeTransition = false;
                    pipTaskOrganizer.mPictureInPictureParams = null;
                    pipTransitionState.setTransitionState(0);
                    break;
                }
                break;
        }
    }

    public /* synthetic */ PipController$$ExternalSyntheticLambda13(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }
}
