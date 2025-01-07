package com.android.wm.shell.startingsurface;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Slog;
import android.util.SparseArray;
import android.view.Choreographer;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.WindowManagerGlobal;
import android.window.SplashScreenView;
import android.window.StartingWindowRemovalInfo;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda33;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.startingsurface.StartingSurfaceDrawer;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplashscreenWindowCreator extends AbsSplashWindowCreator {
    public final SparseArray mAnimatedSplashScreenSurfaceHosts;
    public Choreographer mChoreographer;
    public final WindowManagerGlobal mWindowManagerGlobal;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SplashScreenViewSupplier implements Supplier {
        public boolean mIsViewSet;
        public Runnable mUiThreadInitTask;
        public SplashScreenView mView;

        @Override // java.util.function.Supplier
        public final SplashScreenView get() {
            SplashScreenView splashScreenView;
            synchronized (this) {
                while (!this.mIsViewSet) {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                    }
                }
                Runnable runnable = this.mUiThreadInitTask;
                if (runnable != null) {
                    runnable.run();
                    this.mUiThreadInitTask = null;
                }
                splashScreenView = this.mView;
            }
            return splashScreenView;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SplashWindowRecord extends StartingSurfaceDrawer.StartingWindowRecord {
        public final IBinder mAppToken;
        public final long mCreateTime = SystemClock.uptimeMillis();
        public final View mRootView;
        public boolean mSetSplashScreen;
        public SplashScreenView mSplashView;
        public final int mSuggestType;

        public SplashWindowRecord(IBinder iBinder, View view, int i) {
            this.mAppToken = iBinder;
            this.mRootView = view;
            this.mSuggestType = i;
        }

        @Override // com.android.wm.shell.startingsurface.StartingSurfaceDrawer.StartingWindowRecord
        public final boolean removeIfPossible(StartingWindowRemovalInfo startingWindowRemovalInfo, boolean z) {
            View view = this.mRootView;
            SplashScreenView splashScreenView = this.mSplashView;
            SplashscreenWindowCreator splashscreenWindowCreator = SplashscreenWindowCreator.this;
            if (splashScreenView == null) {
                Slog.e("ShellStartingWindow", "Found empty splash screen, remove!");
                SplashscreenWindowCreator.m908$$Nest$mremoveWindowInner(splashscreenWindowCreator, this.mRootView, false);
                return true;
            }
            if (z || this.mSuggestType == 4) {
                SplashscreenWindowCreator.m908$$Nest$mremoveWindowInner(splashscreenWindowCreator, view, false);
            } else if (startingWindowRemovalInfo.playRevealAnimation) {
                splashscreenWindowCreator.mSplashscreenContentDrawer.applyExitAnimation(splashScreenView, startingWindowRemovalInfo.windowAnimationLeash, startingWindowRemovalInfo.mainFrame, new SplashscreenWindowCreator$$ExternalSyntheticLambda0(1, this), this.mCreateTime, startingWindowRemovalInfo.roundedCornerRadius);
            } else {
                SplashscreenWindowCreator.m908$$Nest$mremoveWindowInner(splashscreenWindowCreator, view, true);
            }
            return true;
        }
    }

    /* renamed from: -$$Nest$mremoveWindowInner, reason: not valid java name */
    public static void m908$$Nest$mremoveWindowInner(SplashscreenWindowCreator splashscreenWindowCreator, View view, boolean z) {
        CentralSurfacesImpl$$ExternalSyntheticLambda0 centralSurfacesImpl$$ExternalSyntheticLambda0 = splashscreenWindowCreator.mSysuiProxy;
        boolean z2 = false;
        if (centralSurfacesImpl$$ExternalSyntheticLambda0 != null) {
            CentralSurfacesImpl centralSurfacesImpl = (CentralSurfacesImpl) centralSurfacesImpl$$ExternalSyntheticLambda0.f$0;
            centralSurfacesImpl.getClass();
            ((ExecutorImpl) centralSurfacesImpl.mMainExecutor).execute(new CentralSurfacesImpl$$ExternalSyntheticLambda33(centralSurfacesImpl, z2));
        }
        if (view.isAttachedToWindow()) {
            if (z) {
                view.setVisibility(8);
            }
            splashscreenWindowCreator.mWindowManagerGlobal.removeView(view, false);
        }
    }

    public SplashscreenWindowCreator(SplashscreenContentDrawer splashscreenContentDrawer, Context context, ShellExecutor shellExecutor, DisplayManager displayManager, StartingSurfaceDrawer.StartingWindowRecordManager startingWindowRecordManager) {
        super(splashscreenContentDrawer, context, shellExecutor, displayManager, startingWindowRecordManager);
        this.mAnimatedSplashScreenSurfaceHosts = new SparseArray(1);
        ((HandlerExecutor) shellExecutor).execute(new SplashscreenWindowCreator$$ExternalSyntheticLambda0(0, this));
        this.mWindowManagerGlobal = WindowManagerGlobal.getInstance();
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x006e, code lost:
    
        if (r21.getParent() != null) goto L22;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0090  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean addWindow(int r19, android.os.IBinder r20, android.view.View r21, android.view.Display r22, android.view.WindowManager.LayoutParams r23, int r24) {
        /*
            r18 = this;
            r1 = r18
            r2 = r19
            r3 = r20
            r10 = r21
            java.lang.String r11 = "view not successfully added to wm, removing view"
            java.lang.String r12 = "ShellStartingWindow"
            android.content.Context r0 = r21.getContext()
            r13 = 1
            r14 = 0
            r8 = 32
            java.lang.String r4 = "addRootView"
            android.os.Trace.traceBegin(r8, r4)     // Catch: java.lang.Throwable -> L45 android.view.WindowManager.BadTokenException -> L49
            android.view.WindowManagerGlobal r4 = r1.mWindowManagerGlobal     // Catch: java.lang.Throwable -> L45 android.view.WindowManager.BadTokenException -> L49
            int r0 = r0.getUserId()     // Catch: java.lang.Throwable -> L45 android.view.WindowManager.BadTokenException -> L49
            r15 = 0
            r5 = r21
            r6 = r23
            r7 = r22
            r16 = r8
            r8 = r15
            r9 = r0
            r4.addView(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L41 android.view.WindowManager.BadTokenException -> L43
            android.os.Trace.traceEnd(r16)
            android.view.ViewParent r0 = r21.getParent()
            if (r0 != 0) goto L3f
        L36:
            android.util.Slog.w(r12, r11)
            android.view.WindowManagerGlobal r0 = r1.mWindowManagerGlobal
            r0.removeView(r10, r13)
            goto L71
        L3f:
            r14 = r13
            goto L71
        L41:
            r0 = move-exception
            goto L87
        L43:
            r0 = move-exception
            goto L4c
        L45:
            r0 = move-exception
            r16 = r8
            goto L87
        L49:
            r0 = move-exception
            r16 = r8
        L4c:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L41
            r4.<init>()     // Catch: java.lang.Throwable -> L41
            r4.append(r3)     // Catch: java.lang.Throwable -> L41
            java.lang.String r5 = " already running, starting window not displayed. "
            r4.append(r5)     // Catch: java.lang.Throwable -> L41
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L41
            r4.append(r0)     // Catch: java.lang.Throwable -> L41
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Throwable -> L41
            android.util.Slog.w(r12, r0)     // Catch: java.lang.Throwable -> L41
            android.os.Trace.traceEnd(r16)
            android.view.ViewParent r0 = r21.getParent()
            if (r0 != 0) goto L71
            goto L36
        L71:
            if (r14 == 0) goto L86
            com.android.wm.shell.startingsurface.StartingSurfaceDrawer$StartingWindowRecordManager r0 = r1.mStartingWindowRecordManager
            android.window.StartingWindowRemovalInfo r4 = r0.mTmpRemovalInfo
            r4.taskId = r2
            r0.removeWindow(r4, r13)
            com.android.wm.shell.startingsurface.SplashscreenWindowCreator$SplashWindowRecord r4 = new com.android.wm.shell.startingsurface.SplashscreenWindowCreator$SplashWindowRecord
            r5 = r24
            r4.<init>(r3, r10, r5)
            r0.addRecord(r2, r4)
        L86:
            return r14
        L87:
            android.os.Trace.traceEnd(r16)
            android.view.ViewParent r2 = r21.getParent()
            if (r2 != 0) goto L98
            android.util.Slog.w(r12, r11)
            android.view.WindowManagerGlobal r1 = r1.mWindowManagerGlobal
            r1.removeView(r10, r13)
        L98:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.startingsurface.SplashscreenWindowCreator.addWindow(int, android.os.IBinder, android.view.View, android.view.Display, android.view.WindowManager$LayoutParams, int):boolean");
    }

    public final void onAppSplashScreenViewRemoved(int i, boolean z) {
        SurfaceControlViewHost surfaceControlViewHost = (SurfaceControlViewHost) this.mAnimatedSplashScreenSurfaceHosts.get(i);
        if (surfaceControlViewHost == null) {
            return;
        }
        this.mAnimatedSplashScreenSurfaceHosts.remove(i);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_STARTING_WINDOW_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_STARTING_WINDOW, -840513847677537573L, 4, z ? "Server cleaned up" : "App removed", Long.valueOf(i));
        }
        SplashScreenView.releaseIconHost(surfaceControlViewHost);
    }
}
