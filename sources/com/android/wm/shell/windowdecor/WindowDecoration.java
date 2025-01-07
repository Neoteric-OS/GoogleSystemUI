package com.android.wm.shell.windowdecor;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Binder;
import android.os.Trace;
import android.view.Display;
import android.view.InsetsSource;
import android.view.InsetsState;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.windowdecor.viewhost.DefaultWindowDecorViewHost;
import com.android.wm.shell.windowdecor.viewhost.DefaultWindowDecorViewHostSupplier;
import com.android.wm.shell.windowdecor.viewhost.WindowDecorViewHostSupplier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WindowDecoration implements AutoCloseable {
    public final Context mContext;
    public DefaultWindowDecorViewHost mDecorViewHost;
    public Context mDecorWindowContext;
    public SurfaceControl mDecorationContainerSurface;
    public Display mDisplay;
    public final DisplayController mDisplayController;
    public boolean mIsCaptionVisible;
    public boolean mIsKeyguardVisibleAndOccluded;
    public final boolean mIsStatusBarVisible;
    public int mLayoutResId;
    public final Supplier mSurfaceControlBuilderSupplier;
    public final Supplier mSurfaceControlTransactionSupplier;
    public final SurfaceControlViewHostFactory mSurfaceControlViewHostFactory;
    public TaskPositioner mTaskDragResizer;
    public ActivityManager.RunningTaskInfo mTaskInfo;
    public final ShellTaskOrganizer mTaskOrganizer;
    public final SurfaceControl mTaskSurface;
    public final Context mUserContext;
    public final Supplier mWindowContainerTransactionSupplier;
    public Configuration mWindowDecorConfig;
    public final WindowDecorViewHostSupplier mWindowDecorViewHostSupplier;
    public WindowDecorationInsets mWindowDecorationInsets;
    public final AnonymousClass1 mOnDisplaysChangedListener = new DisplayController.OnDisplaysChangedListener() { // from class: com.android.wm.shell.windowdecor.WindowDecoration.1
        @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
        public final void onDisplayAdded(int i) {
            WindowDecoration windowDecoration = WindowDecoration.this;
            if (windowDecoration.mTaskInfo.displayId != i) {
                return;
            }
            windowDecoration.mDisplayController.removeDisplayWindowListener(this);
            windowDecoration.relayout(windowDecoration.mTaskInfo);
        }
    };
    public final Binder mOwner = new Binder();
    public final float[] mTmpColor = new float[3];

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.windowdecor.WindowDecoration$2, reason: invalid class name */
    public final class AnonymousClass2 implements SurfaceControlViewHostFactory {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RelayoutParams {
        public boolean mApplyStartTransactionOnDraw;
        public boolean mAsyncViewHost;
        public int mCaptionHeightId;
        public int mCaptionTopPadding;
        public int mCaptionWidthId;
        public int mCornerRadius;
        public int mInputFeatures;
        public int mInsetSourceFlags;
        public int mLayoutResId;
        public final List mOccludingCaptionElements = new ArrayList();
        public ActivityManager.RunningTaskInfo mRunningTaskInfo;
        public boolean mSetTaskPositionAndCrop;
        public int mShadowRadiusId;
        public Configuration mWindowDecorConfig;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class OccludingCaptionElement {
            public Alignment mAlignment;
            public int mWidthResId;

            /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
            /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            final class Alignment {
                public static final /* synthetic */ Alignment[] $VALUES;
                public static final Alignment END;
                public static final Alignment START;

                static {
                    Alignment alignment = new Alignment("START", 0);
                    START = alignment;
                    Alignment alignment2 = new Alignment("END", 1);
                    END = alignment2;
                    $VALUES = new Alignment[]{alignment, alignment2};
                }

                public static Alignment valueOf(String str) {
                    return (Alignment) Enum.valueOf(Alignment.class, str);
                }

                public static Alignment[] values() {
                    return (Alignment[]) $VALUES.clone();
                }
            }
        }

        public final void reset() {
            this.mLayoutResId = 0;
            this.mCaptionHeightId = 0;
            this.mCaptionWidthId = 0;
            this.mOccludingCaptionElements.clear();
            this.mInputFeatures = 0;
            this.mInsetSourceFlags = 0;
            this.mShadowRadiusId = 0;
            this.mCornerRadius = 0;
            this.mCaptionTopPadding = 0;
            this.mApplyStartTransactionOnDraw = false;
            this.mSetTaskPositionAndCrop = false;
            this.mAsyncViewHost = false;
            this.mWindowDecorConfig = null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RelayoutResult {
        public int mCaptionHeight;
        public int mCaptionWidth;
        public int mCaptionX;
        public final Region mCustomizableCaptionRegion = Region.obtain();
        public int mHeight;
        public View mRootView;
        public int mWidth;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface SurfaceControlViewHostFactory {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WindowDecorationInsets {
        public final Rect[] mBoundingRects;
        public final int mFlags;
        public final Rect mFrame;
        public final Binder mOwner;
        public final WindowContainerToken mToken;

        public WindowDecorationInsets(WindowContainerToken windowContainerToken, Binder binder, Rect rect, Rect[] rectArr, int i) {
            this.mToken = windowContainerToken;
            this.mOwner = binder;
            this.mFrame = rect;
            this.mBoundingRects = rectArr;
            this.mFlags = i;
        }

        public final void addOrUpdate(WindowContainerTransaction windowContainerTransaction) {
            windowContainerTransaction.addInsetsSource(this.mToken, this.mOwner, 0, WindowInsets.Type.captionBar(), this.mFrame, this.mBoundingRects, this.mFlags);
            windowContainerTransaction.addInsetsSource(this.mToken, this.mOwner, 0, WindowInsets.Type.mandatorySystemGestures(), this.mFrame, this.mBoundingRects, 0);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof WindowDecorationInsets)) {
                return false;
            }
            WindowDecorationInsets windowDecorationInsets = (WindowDecorationInsets) obj;
            return Objects.equals(this.mToken, windowDecorationInsets.mToken) && Objects.equals(this.mOwner, windowDecorationInsets.mOwner) && Objects.equals(this.mFrame, windowDecorationInsets.mFrame) && Objects.deepEquals(this.mBoundingRects, windowDecorationInsets.mBoundingRects) && this.mFlags == windowDecorationInsets.mFlags;
        }

        public final int hashCode() {
            return Objects.hash(this.mToken, this.mOwner, this.mFrame, Integer.valueOf(Arrays.hashCode(this.mBoundingRects)), Integer.valueOf(this.mFlags));
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.wm.shell.windowdecor.WindowDecoration$1] */
    public WindowDecoration(Context context, Context context2, DisplayController displayController, ShellTaskOrganizer shellTaskOrganizer, ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl, Supplier supplier, Supplier supplier2, Supplier supplier3, Supplier supplier4, SurfaceControlViewHostFactory surfaceControlViewHostFactory, WindowDecorViewHostSupplier windowDecorViewHostSupplier) {
        boolean z;
        this.mContext = context;
        this.mUserContext = context2;
        this.mDisplayController = displayController;
        this.mTaskOrganizer = shellTaskOrganizer;
        this.mTaskInfo = runningTaskInfo;
        SurfaceControl surfaceControl2 = (SurfaceControl) supplier4.get();
        surfaceControl2.copyFrom(surfaceControl, "WindowDecoration");
        this.mTaskSurface = surfaceControl2;
        this.mSurfaceControlBuilderSupplier = supplier;
        this.mSurfaceControlTransactionSupplier = supplier2;
        this.mWindowContainerTransactionSupplier = supplier3;
        this.mSurfaceControlViewHostFactory = surfaceControlViewHostFactory;
        this.mWindowDecorViewHostSupplier = windowDecorViewHostSupplier;
        this.mDisplay = displayController.getDisplay(this.mTaskInfo.displayId);
        DisplayController.DisplayRecord displayRecord = (DisplayController.DisplayRecord) displayController.mDisplays.get(this.mTaskInfo.displayId);
        InsetsState insetsState = displayRecord != null ? displayRecord.mInsetsState : null;
        boolean z2 = false;
        if (insetsState != null) {
            int statusBars = WindowInsets.Type.statusBars();
            int sourceSize = insetsState.sourceSize();
            int i = 0;
            while (true) {
                if (i >= sourceSize) {
                    z = false;
                    break;
                }
                InsetsSource sourceAt = insetsState.sourceAt(i);
                if (sourceAt.getType() == statusBars) {
                    z = sourceAt.isVisible();
                    break;
                }
                i++;
            }
            if (z) {
                z2 = true;
            }
        }
        this.mIsStatusBarVisible = z2;
    }

    public static int loadDimensionPixelSize(int i, Resources resources) {
        if (i == 0) {
            return 0;
        }
        return resources.getDimensionPixelSize(i);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Trace.beginSection("WindowDecoration#close");
        this.mDisplayController.removeDisplayWindowListener(this.mOnDisplaysChangedListener);
        WindowContainerTransaction windowContainerTransaction = (WindowContainerTransaction) this.mWindowContainerTransactionSupplier.get();
        releaseViews(windowContainerTransaction);
        this.mTaskOrganizer.applyTransaction(windowContainerTransaction);
        this.mTaskSurface.release();
        Trace.endSection();
    }

    public abstract int getCaptionViewId();

    public View inflateLayout(Context context, int i) {
        return LayoutInflater.from(context).inflate(i, (ViewGroup) null);
    }

    public abstract void relayout(ActivityManager.RunningTaskInfo runningTaskInfo);

    /* JADX WARN: Removed duplicated region for block: B:129:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0377  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void relayout(com.android.wm.shell.windowdecor.WindowDecoration.RelayoutParams r22, android.view.SurfaceControl.Transaction r23, android.view.SurfaceControl.Transaction r24, android.window.WindowContainerTransaction r25, android.view.View r26, com.android.wm.shell.windowdecor.WindowDecoration.RelayoutResult r27) {
        /*
            Method dump skipped, instructions count: 1035
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.windowdecor.WindowDecoration.relayout(com.android.wm.shell.windowdecor.WindowDecoration$RelayoutParams, android.view.SurfaceControl$Transaction, android.view.SurfaceControl$Transaction, android.window.WindowContainerTransaction, android.view.View, com.android.wm.shell.windowdecor.WindowDecoration$RelayoutResult):void");
    }

    public void releaseViews(WindowContainerTransaction windowContainerTransaction) {
        boolean z;
        SurfaceControl.Transaction transaction = (SurfaceControl.Transaction) this.mSurfaceControlTransactionSupplier.get();
        DefaultWindowDecorViewHost defaultWindowDecorViewHost = this.mDecorViewHost;
        boolean z2 = true;
        if (defaultWindowDecorViewHost != null) {
            ((DefaultWindowDecorViewHostSupplier) this.mWindowDecorViewHostSupplier).getClass();
            StandaloneCoroutine standaloneCoroutine = defaultWindowDecorViewHost.currentUpdateJob;
            if (standaloneCoroutine != null) {
                standaloneCoroutine.cancel(null);
            }
            defaultWindowDecorViewHost.currentUpdateJob = null;
            SurfaceControlViewHost surfaceControlViewHost = defaultWindowDecorViewHost.viewHost;
            if (surfaceControlViewHost != null) {
                surfaceControlViewHost.release();
            }
            transaction.remove(defaultWindowDecorViewHost.rootSurface);
            this.mDecorViewHost = null;
            z = true;
        } else {
            z = false;
        }
        SurfaceControl surfaceControl = this.mDecorationContainerSurface;
        if (surfaceControl != null) {
            transaction.remove(surfaceControl);
            this.mDecorationContainerSurface = null;
        } else {
            z2 = z;
        }
        if (z2) {
            transaction.apply();
        }
        WindowDecorationInsets windowDecorationInsets = this.mWindowDecorationInsets;
        if (windowDecorationInsets != null) {
            windowContainerTransaction.removeInsetsSource(windowDecorationInsets.mToken, windowDecorationInsets.mOwner, 0, WindowInsets.Type.captionBar());
            windowContainerTransaction.removeInsetsSource(windowDecorationInsets.mToken, windowDecorationInsets.mOwner, 0, WindowInsets.Type.mandatorySystemGestures());
            this.mWindowDecorationInsets = null;
        }
    }
}
