package com.android.wm.shell.pip.phone;

import android.app.ActivityTaskManager;
import android.app.PictureInPictureParams;
import android.app.PictureInPictureUiState;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.SurfaceControl;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.pip.PipBoundsAlgorithm;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.pip.PipSurfaceTransactionHelper;
import com.android.wm.shell.pip.PipTaskOrganizer;
import com.android.wm.shell.pip.PipTaskOrganizer$$ExternalSyntheticLambda9;
import com.android.wm.shell.pip.PipTransitionController;
import com.android.wm.shell.pip.PipTransitionState;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.transition.Transitions;
import java.lang.ref.WeakReference;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipController$IPipImpl$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ int f$0;
    public final /* synthetic */ ComponentName f$1;
    public final /* synthetic */ Rect f$2;
    public final /* synthetic */ Object f$3;
    public final /* synthetic */ Object f$4;
    public final /* synthetic */ Object f$5;

    public /* synthetic */ PipController$IPipImpl$$ExternalSyntheticLambda0(int i, ComponentName componentName, Rect rect, SurfaceControl surfaceControl, Rect rect2, Rect rect3) {
        this.f$0 = i;
        this.f$1 = componentName;
        this.f$2 = rect;
        this.f$3 = surfaceControl;
        this.f$4 = rect2;
        this.f$5 = rect3;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                int i = this.f$0;
                ComponentName componentName = this.f$1;
                Rect rect = this.f$2;
                SurfaceControl surfaceControl = (SurfaceControl) this.f$3;
                Rect rect2 = (Rect) this.f$4;
                Rect rect3 = (Rect) this.f$5;
                PipTaskOrganizer pipTaskOrganizer = ((PipController) obj).mPipTaskOrganizer;
                pipTaskOrganizer.getClass();
                boolean z = ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0];
                PipTransitionState pipTransitionState = pipTaskOrganizer.mPipTransitionState;
                if (z) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -193681310390012114L, 0, String.valueOf(componentName), String.valueOf(pipTransitionState));
                }
                if (pipTransitionState.mInSwipePipToHomeTransition) {
                    pipTaskOrganizer.mPipBoundsState.setBounds(rect);
                    pipTaskOrganizer.mPipOverlay = surfaceControl;
                    if (surfaceControl != null) {
                        pipTaskOrganizer.mAppBounds.set(rect2);
                    } else {
                        pipTaskOrganizer.mAppBounds.setEmpty();
                    }
                    pipTaskOrganizer.mSwipeSourceRectHint = rect3;
                    if (Transitions.ENABLE_SHELL_TRANSITIONS && surfaceControl != null) {
                        SurfaceControl.Transaction transaction = ((PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory) pipTaskOrganizer.mSurfaceControlTransactionFactory).getTransaction();
                        pipTaskOrganizer.mTaskOrganizer.reparentChildSurfaceToTask(i, transaction, surfaceControl);
                        transaction.setLayer(surfaceControl, Integer.MAX_VALUE);
                        transaction.apply();
                        ((HandlerExecutor) pipTaskOrganizer.mMainExecutor).executeDelayed(new PipTaskOrganizer$$ExternalSyntheticLambda9(pipTaskOrganizer, new WeakReference(surfaceControl), 2), (pipTaskOrganizer.mEnterAnimationDuration + 500 + PipTaskOrganizer.EXTRA_CONTENT_OVERLAY_FADE_OUT_DELAY_MS) * 2);
                        break;
                    }
                }
                break;
            default:
                Rect[] rectArr = (Rect[]) this.f$4;
                ComponentName componentName2 = this.f$1;
                ActivityInfo activityInfo = (ActivityInfo) this.f$5;
                PictureInPictureParams pictureInPictureParams = (PictureInPictureParams) this.f$3;
                int i2 = this.f$0;
                Rect rect4 = this.f$2;
                PipController pipController = (PipController) obj;
                PipBoundsState pipBoundsState = pipController.mPipBoundsState;
                pipBoundsState.setNamedUnrestrictedKeepClearArea(0, rect4);
                pipController.mPipDisplayLayoutState.rotateTo(i2);
                Point point = pipBoundsState.mMinSize;
                Point point2 = pipBoundsState.mMaxSize;
                pipBoundsState.updateMinMaxSize(pictureInPictureParams.hasSetAspectRatio() ? pictureInPictureParams.getAspectRatioFloat() : pipController.mPipBoundsAlgorithm.mDefaultAspectRatio);
                PipTaskOrganizer pipTaskOrganizer2 = pipController.mPipTaskOrganizer;
                pipTaskOrganizer2.getClass();
                boolean z2 = ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0];
                PipTransitionState pipTransitionState2 = pipTaskOrganizer2.mPipTransitionState;
                if (z2) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 8997930464045709859L, 0, String.valueOf(componentName2), String.valueOf(pipTransitionState2));
                }
                pipTransitionState2.mInSwipePipToHomeTransition = true;
                boolean z3 = Transitions.ENABLE_SHELL_TRANSITIONS;
                PipTransitionController pipTransitionController = pipTaskOrganizer2.mPipTransitionController;
                if (z3) {
                    pipTransitionController.getClass();
                    try {
                        ActivityTaskManager.getService().onPictureInPictureUiStateChanged(new PictureInPictureUiState.Builder().setTransitioningToPip(true).build());
                    } catch (RemoteException | IllegalStateException unused) {
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                            ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -8189372673897383003L, 0, null);
                        }
                    }
                } else {
                    pipTaskOrganizer2.mPipTransitionState.setTransitionState(3);
                    pipTransitionController.sendOnPipTransitionStarted(2);
                }
                PipBoundsState pipBoundsState2 = pipTaskOrganizer2.mPipBoundsState;
                PipBoundsAlgorithm pipBoundsAlgorithm = pipTaskOrganizer2.mPipBoundsAlgorithm;
                pipBoundsState2.setBoundsStateForEntry(componentName2, activityInfo, pictureInPictureParams, pipBoundsAlgorithm);
                Rect entryDestinationBounds = pipBoundsAlgorithm.getEntryDestinationBounds();
                pipBoundsState.mMinSize.set(point.x, point.y);
                pipBoundsState.mMaxSize.set(point2.x, point2.y);
                pipBoundsState.mNormalBounds.set(entryDestinationBounds);
                rectArr[0] = entryDestinationBounds;
                break;
        }
    }

    public /* synthetic */ PipController$IPipImpl$$ExternalSyntheticLambda0(Rect[] rectArr, ComponentName componentName, ActivityInfo activityInfo, PictureInPictureParams pictureInPictureParams, int i, Rect rect) {
        this.f$4 = rectArr;
        this.f$1 = componentName;
        this.f$5 = activityInfo;
        this.f$3 = pictureInPictureParams;
        this.f$0 = i;
        this.f$2 = rect;
    }
}
