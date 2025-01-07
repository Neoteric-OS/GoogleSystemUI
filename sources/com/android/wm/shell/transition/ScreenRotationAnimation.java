package com.android.wm.shell.transition;

import android.view.SurfaceControl;
import android.view.animation.Animation;
import com.android.wm.shell.shared.TransactionPool;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenRotationAnimation {
    public final SurfaceControl mAnimLeash;
    public final SurfaceControl mBackColorSurface;
    public final int mEndHeight;
    public final int mEndRotation;
    public final int mEndWidth;
    public Animation mRotateAlphaAnimation;
    public Animation mRotateEnterAnimation;
    public Animation mRotateExitAnimation;
    public final SurfaceControl mScreenshotLayer;
    public final int mStartHeight;
    public final float mStartLuma;
    public final int mStartRotation;
    public final int mStartWidth;
    public final SurfaceControl mSurfaceControl;
    public final float[] mTmpFloats = new float[9];
    public final TransactionPool mTransactionPool;

    /* JADX WARN: Removed duplicated region for block: B:13:0x0125 A[Catch: OutOfResourcesException -> 0x0089, TRY_LEAVE, TryCatch #0 {OutOfResourcesException -> 0x0089, blocks: (B:3:0x0072, B:5:0x0078, B:6:0x0109, B:13:0x0125, B:44:0x008c, B:46:0x00b8, B:48:0x00be, B:55:0x00fc, B:56:0x0106), top: B:2:0x0072 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00fc A[Catch: OutOfResourcesException -> 0x0089, TryCatch #0 {OutOfResourcesException -> 0x0089, blocks: (B:3:0x0072, B:5:0x0078, B:6:0x0109, B:13:0x0125, B:44:0x008c, B:46:0x00b8, B:48:0x00be, B:55:0x00fc, B:56:0x0106), top: B:2:0x0072 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ScreenRotationAnimation(android.content.Context r17, com.android.wm.shell.shared.TransactionPool r18, android.view.SurfaceControl.Transaction r19, android.window.TransitionInfo.Change r20, android.view.SurfaceControl r21, int r22) {
        /*
            Method dump skipped, instructions count: 491
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.transition.ScreenRotationAnimation.<init>(android.content.Context, com.android.wm.shell.shared.TransactionPool, android.view.SurfaceControl$Transaction, android.window.TransitionInfo$Change, android.view.SurfaceControl, int):void");
    }
}
