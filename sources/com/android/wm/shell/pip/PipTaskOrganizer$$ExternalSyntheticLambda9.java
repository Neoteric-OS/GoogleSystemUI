package com.android.wm.shell.pip;

import android.graphics.Rect;
import android.view.SurfaceControl;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.lang.ref.WeakReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipTaskOrganizer$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ PipTaskOrganizer f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ PipTaskOrganizer$$ExternalSyntheticLambda9(PipTaskOrganizer pipTaskOrganizer, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = pipTaskOrganizer;
        this.f$1 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                PipTaskOrganizer pipTaskOrganizer = this.f$0;
                ((HandlerExecutor) pipTaskOrganizer.mMainExecutor).execute((PipTaskOrganizer$1$$ExternalSyntheticLambda2) this.f$1);
                break;
            case 1:
                PipTaskOrganizer pipTaskOrganizer2 = this.f$0;
                pipTaskOrganizer2.finishResizeForMenu$1((Rect) this.f$1);
                pipTaskOrganizer2.sendOnPipTransitionFinished(2);
                break;
            default:
                PipTaskOrganizer pipTaskOrganizer3 = this.f$0;
                WeakReference weakReference = (WeakReference) this.f$1;
                pipTaskOrganizer3.getClass();
                SurfaceControl surfaceControl = (SurfaceControl) weakReference.get();
                if (surfaceControl != null && surfaceControl.isValid() && surfaceControl == pipTaskOrganizer3.mPipOverlay) {
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
                        ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -4618800347912193311L, 0, String.valueOf(surfaceControl));
                    }
                    pipTaskOrganizer3.removeContentOverlay(surfaceControl);
                    break;
                }
                break;
        }
    }
}
