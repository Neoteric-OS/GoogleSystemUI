package com.android.systemui;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraManager;
import android.hardware.graphics.common.DisplayDecorationSupport;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.HandlerThread;
import android.os.SystemProperties;
import android.os.Trace;
import android.util.DisplayUtils;
import android.util.Log;
import android.util.Size;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.DisplayInfo;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import androidx.compose.runtime.collection.MutableVectorKt$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.util.Preconditions;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.settingslib.Utils;
import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl;
import com.android.systemui.decor.CutoutDecorProviderFactory;
import com.android.systemui.decor.DebugRoundedCornerDelegate;
import com.android.systemui.decor.DecorProvider;
import com.android.systemui.decor.DecorProviderFactory;
import com.android.systemui.decor.FaceScanningProviderFactory;
import com.android.systemui.decor.OverlayWindow;
import com.android.systemui.decor.PathDrawable;
import com.android.systemui.decor.PrivacyDotDecorProviderFactory;
import com.android.systemui.decor.RoundedCornerDecorProviderFactory;
import com.android.systemui.decor.RoundedCornerResDelegateImpl;
import com.android.systemui.decor.ScreenDecorCommand;
import com.android.systemui.log.ScreenDecorationsLogger;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.DisplayTrackerImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.commandline.CommandRegistry;
import com.android.systemui.statusbar.events.PrivacyDotViewController;
import com.android.systemui.statusbar.events.ViewState;
import com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.DumpUtilsKt;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.concurrency.ThreadFactoryImpl;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.leak.RotationUtils;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import dalvik.annotation.optimization.NeverCompile;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScreenDecorations implements CoreStartable, ConfigurationController.ConfigurationListener, Dumpable {
    public CameraAvailabilityListener mCameraListener;
    public final CameraProtectionLoaderImpl mCameraProtectionLoader;
    public final AnonymousClass1 mCameraTransitionCallback;
    public AnonymousClass4 mColorInversionSetting;
    public final CommandRegistry mCommandRegistry;
    public final Context mContext;
    public CutoutDecorProviderFactory mCutoutFactory;
    public boolean mDebug = DEBUG_SCREENSHOT_ROUNDED_CORNERS;
    public int mDebugColor = -65536;
    protected DebugRoundedCornerDelegate mDebugRoundedCornerDelegate;
    public RoundedCornerDecorProviderFactory mDebugRoundedCornerFactory;
    public DisplayCutout mDisplayCutout;
    protected DisplayInfo mDisplayInfo;
    DisplayTracker.Callback mDisplayListener;
    public final Point mDisplaySize;
    public final DisplayTracker mDisplayTracker;
    String mDisplayUniqueId;
    public final PrivacyDotDecorProviderFactory mDotFactory;
    public final PrivacyDotViewController mDotViewController;
    public ExecutorImpl mExecutor;
    public final FacePropertyRepositoryImpl mFacePropertyRepository;
    public final FaceScanningProviderFactory mFaceScanningFactory;
    public final int mFaceScanningViewId;
    public Handler mHandler;
    protected DisplayDecorationSupport mHwcScreenDecorationSupport;
    protected boolean mIsRegistered;
    public final JavaAdapter mJavaAdapter;
    public final ScreenDecorationsLogger mLogger;
    protected OverlayWindow[] mOverlays;
    public boolean mPendingConfigChange;
    public boolean mPendingManualConfigUpdate;
    PrivacyDotViewController.ShowingListener mPrivacyDotShowingListener;
    public int mProviderRefreshToken;
    public int mRotation;
    protected DecorProviderFactory mRoundedCornerFactory;
    protected RoundedCornerResDelegateImpl mRoundedCornerResDelegate;
    public final ScreenDecorations$$ExternalSyntheticLambda0 mScreenDecorCommandCallback;
    ScreenDecorHwcLayer mScreenDecorHwcLayer;
    ViewGroup mScreenDecorHwcWindow;
    public final SecureSettings mSecureSettings;
    public final ThreadFactoryImpl mThreadFactory;
    public int mTintColor;
    public final UserTracker.Callback mUserChangedCallback;
    public final UserTracker mUserTracker;
    public final ViewCaptureAwareWindowManager mWindowManager;
    public static final boolean DEBUG_DISABLE_SCREEN_DECORATIONS = SystemProperties.getBoolean("debug.disable_screen_decorations", false);
    public static final boolean DEBUG_SCREENSHOT_ROUNDED_CORNERS = SystemProperties.getBoolean("debug.screenshot_rounded_corners", false);
    public static final boolean sToolkitSetFrameRateReadOnly = true;
    public static final int[] DISPLAY_CUTOUT_IDS = {R.id.display_cutout, R.id.display_cutout_left, R.id.display_cutout_right, R.id.display_cutout_bottom};

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.ScreenDecorations$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public AnonymousClass1() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.ScreenDecorations$2, reason: invalid class name */
    public final class AnonymousClass2 implements PrivacyDotViewController.ShowingListener {
        public AnonymousClass2() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class DisplayCutoutView extends DisplayCutoutBaseView {
        public final Rect mBoundingRect;
        public final List mBounds;
        public int mColor;
        public final int mInitialPosition;
        public int mPosition;
        public int mRotation;
        public final Rect mTotalBounds;

        public DisplayCutoutView(int i, Context context) {
            super(context);
            this.mBounds = new ArrayList();
            this.mBoundingRect = new Rect();
            this.mTotalBounds = new Rect();
            this.mColor = DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
            this.mInitialPosition = i;
            this.paint.setColor(DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT);
            this.paint.setStyle(Paint.Style.FILL);
        }

        public static void boundsFromDirection(int i, Rect rect, DisplayCutout displayCutout) {
            if (i == 3) {
                rect.set(displayCutout.getBoundingRectLeft());
                return;
            }
            if (i == 5) {
                rect.set(displayCutout.getBoundingRectRight());
                return;
            }
            if (i == 48) {
                rect.set(displayCutout.getBoundingRectTop());
            } else if (i != 80) {
                rect.setEmpty();
            } else {
                rect.set(displayCutout.getBoundingRectBottom());
            }
        }

        public final int getGravity(DisplayCutout displayCutout) {
            int i = this.mPosition;
            return i == 0 ? !displayCutout.getBoundingRectLeft().isEmpty() ? 3 : 0 : i == 1 ? !displayCutout.getBoundingRectTop().isEmpty() ? 48 : 0 : i == 3 ? !displayCutout.getBoundingRectBottom().isEmpty() ? 80 : 0 : (i != 2 || displayCutout.getBoundingRectRight().isEmpty()) ? 0 : 5;
        }

        @Override // android.view.View
        public void onMeasure(int i, int i2) {
            if (this.mBounds.isEmpty()) {
                super.onMeasure(i, i2);
                return;
            }
            if (!this.showProtection) {
                setMeasuredDimension(View.resolveSizeAndState(this.mBoundingRect.width(), i, 0), View.resolveSizeAndState(this.mBoundingRect.height(), i2, 0));
                return;
            }
            this.mTotalBounds.set(this.mBoundingRect);
            Rect rect = this.mTotalBounds;
            RectF rectF = this.protectionRect;
            rect.union((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
            setMeasuredDimension(View.resolveSizeAndState(this.mTotalBounds.width(), i, 0), View.resolveSizeAndState(this.mTotalBounds.height(), i2, 0));
        }

        public void setColor$1(int i) {
            if (i == this.mColor) {
                return;
            }
            this.mColor = i;
            this.paint.setColor(i);
            invalidate();
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0084  */
        @Override // com.android.systemui.DisplayCutoutBaseView
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void updateCutout() {
            /*
                r5 = this;
                boolean r0 = r5.isAttachedToWindow()
                if (r0 == 0) goto Le1
                boolean r0 = r5.pendingConfigChange
                if (r0 == 0) goto Lc
                goto Le1
            Lc:
                int r0 = r5.mInitialPosition
                int r1 = r5.mRotation
                int r0 = com.android.systemui.ScreenDecorations.getBoundPositionFromRotation(r0, r1)
                r5.mPosition = r0
                r5.requestLayout()
                android.view.Display r0 = r5.getDisplay()
                android.view.DisplayInfo r1 = r5.displayInfo
                r0.getDisplayInfo(r1)
                java.util.List r0 = r5.mBounds
                r0.clear()
                android.graphics.Rect r0 = r5.mBoundingRect
                r0.setEmpty()
                android.graphics.Path r0 = r5.cutoutPath
                r0.reset()
                android.content.Context r0 = r5.getContext()
                android.content.res.Resources r1 = r0.getResources()
                android.view.Display r0 = r0.getDisplay()
                java.lang.String r0 = r0.getUniqueId()
                boolean r0 = android.view.DisplayCutout.getFillBuiltInDisplayCutout(r1, r0)
                if (r0 == 0) goto Ld2
                android.view.DisplayInfo r0 = r5.displayInfo
                android.view.DisplayCutout r0 = r0.displayCutout
                r1 = 0
                if (r0 != 0) goto L50
            L4e:
                r0 = r1
                goto L82
            L50:
                int r2 = r5.mPosition
                r3 = 1
                if (r2 != 0) goto L5f
                android.graphics.Rect r0 = r0.getBoundingRectLeft()
                boolean r0 = r0.isEmpty()
            L5d:
                r0 = r0 ^ r3
                goto L82
            L5f:
                if (r2 != r3) goto L6a
                android.graphics.Rect r0 = r0.getBoundingRectTop()
                boolean r0 = r0.isEmpty()
                goto L5d
            L6a:
                r4 = 3
                if (r2 != r4) goto L76
                android.graphics.Rect r0 = r0.getBoundingRectBottom()
                boolean r0 = r0.isEmpty()
                goto L5d
            L76:
                r4 = 2
                if (r2 != r4) goto L4e
                android.graphics.Rect r0 = r0.getBoundingRectRight()
                boolean r0 = r0.isEmpty()
                goto L5d
            L82:
                if (r0 == 0) goto Ld2
                java.util.List r0 = r5.mBounds
                android.view.DisplayInfo r2 = r5.displayInfo
                android.view.DisplayCutout r2 = r2.displayCutout
                java.util.List r2 = r2.getBoundingRects()
                r0.addAll(r2)
                android.graphics.Rect r0 = r5.mBoundingRect
                android.view.DisplayInfo r2 = r5.displayInfo
                android.view.DisplayCutout r2 = r2.displayCutout
                int r3 = r5.getGravity(r2)
                boundsFromDirection(r3, r0, r2)
                android.view.ViewGroup$LayoutParams r0 = r5.getLayoutParams()
                boolean r2 = r0 instanceof android.widget.FrameLayout.LayoutParams
                if (r2 == 0) goto Lb9
                android.widget.FrameLayout$LayoutParams r0 = (android.widget.FrameLayout.LayoutParams) r0
                android.view.DisplayInfo r2 = r5.displayInfo
                android.view.DisplayCutout r2 = r2.displayCutout
                int r2 = r5.getGravity(r2)
                int r3 = r0.gravity
                if (r3 == r2) goto Lb9
                r0.gravity = r2
                r5.setLayoutParams(r0)
            Lb9:
                android.view.DisplayInfo r0 = r5.displayInfo
                android.view.DisplayCutout r0 = r0.displayCutout
                android.graphics.Path r0 = r0.getCutoutPath()
                if (r0 == 0) goto Lc9
                android.graphics.Path r2 = r5.cutoutPath
                r2.set(r0)
                goto Lce
            Lc9:
                android.graphics.Path r0 = r5.cutoutPath
                r0.reset()
            Lce:
                r5.invalidate()
                goto Ld4
            Ld2:
                r1 = 8
            Ld4:
                boolean r0 = r5 instanceof com.android.systemui.FaceScanningOverlay
                if (r0 != 0) goto Le1
                int r0 = r5.getVisibility()
                if (r1 == r0) goto Le1
                r5.setVisibility(r1)
            Le1:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.ScreenDecorations.DisplayCutoutView.updateCutout():void");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RestartingPreDrawListener implements ViewTreeObserver.OnPreDrawListener {
        public final Point mTargetDisplaySize;
        public final int mTargetRotation;
        public final View mView;

        public RestartingPreDrawListener(View view, int i, Point point) {
            this.mView = view;
            this.mTargetRotation = i;
            this.mTargetDisplaySize = point;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public final boolean onPreDraw() {
            this.mView.getViewTreeObserver().removeOnPreDrawListener(this);
            int i = this.mTargetRotation;
            ScreenDecorations screenDecorations = ScreenDecorations.this;
            if (i == screenDecorations.mRotation && screenDecorations.mDisplaySize.equals(this.mTargetDisplaySize)) {
                return true;
            }
            ScreenDecorations screenDecorations2 = ScreenDecorations.this;
            screenDecorations2.mPendingConfigChange = false;
            screenDecorations2.updateConfiguration();
            this.mView.invalidate();
            return false;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ValidatingPreDrawListener implements ViewTreeObserver.OnPreDrawListener {
        public final View mView;

        public ValidatingPreDrawListener(View view) {
            this.mView = view;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public final boolean onPreDraw() {
            ScreenDecorations.this.mContext.getDisplay().getDisplayInfo(ScreenDecorations.this.mDisplayInfo);
            ScreenDecorations screenDecorations = ScreenDecorations.this;
            DisplayInfo displayInfo = screenDecorations.mDisplayInfo;
            if ((displayInfo.rotation == screenDecorations.mRotation && !ScreenDecorations.displaySizeChanged(screenDecorations.mDisplaySize, displayInfo)) || ScreenDecorations.this.mPendingConfigChange) {
                return true;
            }
            this.mView.invalidate();
            return false;
        }
    }

    public ScreenDecorations(Context context, SecureSettings secureSettings, CommandRegistry commandRegistry, UserTracker userTracker, DisplayTracker displayTracker, PrivacyDotViewController privacyDotViewController, ThreadFactoryImpl threadFactoryImpl, PrivacyDotDecorProviderFactory privacyDotDecorProviderFactory, FaceScanningProviderFactory faceScanningProviderFactory, ScreenDecorationsLogger screenDecorationsLogger, FacePropertyRepositoryImpl facePropertyRepositoryImpl, JavaAdapter javaAdapter, CameraProtectionLoaderImpl cameraProtectionLoaderImpl, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        DebugRoundedCornerDelegate debugRoundedCornerDelegate = new DebugRoundedCornerDelegate();
        debugRoundedCornerDelegate.topRoundedSize = new Size(0, 0);
        debugRoundedCornerDelegate.bottomRoundedSize = new Size(0, 0);
        debugRoundedCornerDelegate.physicalPixelDisplaySizeRatio = 1.0f;
        debugRoundedCornerDelegate.color = -65536;
        Paint paint = new Paint();
        paint.setColor(-65536);
        paint.setStyle(Paint.Style.FILL);
        debugRoundedCornerDelegate.paint = paint;
        this.mDebugRoundedCornerDelegate = debugRoundedCornerDelegate;
        this.mProviderRefreshToken = 0;
        this.mOverlays = null;
        this.mTintColor = DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
        this.mDisplaySize = new Point();
        this.mDisplayInfo = new DisplayInfo();
        this.mCameraTransitionCallback = new AnonymousClass1();
        this.mPrivacyDotShowingListener = new AnonymousClass2();
        this.mScreenDecorCommandCallback = new ScreenDecorations$$ExternalSyntheticLambda0(this);
        this.mUserChangedCallback = new UserTracker.Callback() { // from class: com.android.systemui.ScreenDecorations.6
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context2) {
                ScreenDecorations screenDecorations = ScreenDecorations.this;
                screenDecorations.mLogger.logUserSwitched(i);
                screenDecorations.mColorInversionSetting.setUserId(i);
                screenDecorations.updateColorInversion(screenDecorations.mColorInversionSetting.getValue());
            }
        };
        this.mContext = context;
        this.mSecureSettings = secureSettings;
        this.mCommandRegistry = commandRegistry;
        this.mUserTracker = userTracker;
        this.mDisplayTracker = displayTracker;
        this.mDotViewController = privacyDotViewController;
        this.mThreadFactory = threadFactoryImpl;
        this.mDotFactory = privacyDotDecorProviderFactory;
        this.mFaceScanningFactory = faceScanningProviderFactory;
        this.mCameraProtectionLoader = cameraProtectionLoaderImpl;
        this.mFaceScanningViewId = R.id.face_scanning_anim;
        this.mLogger = screenDecorationsLogger;
        this.mFacePropertyRepository = facePropertyRepositoryImpl;
        this.mJavaAdapter = javaAdapter;
        this.mWindowManager = viewCaptureAwareWindowManager;
    }

    public static boolean displaySizeChanged(Point point, DisplayInfo displayInfo) {
        return (point.x == displayInfo.getNaturalWidth() && point.y == displayInfo.getNaturalHeight()) ? false : true;
    }

    public static int getBoundPositionFromRotation(int i, int i2) {
        int i3 = i - i2;
        return i3 < 0 ? i3 + 4 : i3;
    }

    public static WindowManager.LayoutParams getWindowLayoutBaseParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2024, 545259832, -3);
        int i = layoutParams.privateFlags;
        layoutParams.privateFlags = 536870992 | i;
        if (!DEBUG_SCREENSHOT_ROUNDED_CORNERS) {
            layoutParams.privateFlags = i | 537919568;
        }
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.setFitInsetsTypes(0);
        layoutParams.privateFlags |= 16777216;
        return layoutParams;
    }

    public static String getWindowTitleByPos(int i) {
        if (i == 0) {
            return "ScreenDecorOverlayLeft";
        }
        if (i == 1) {
            return "ScreenDecorOverlay";
        }
        if (i == 2) {
            return "ScreenDecorOverlayRight";
        }
        if (i == 3) {
            return "ScreenDecorOverlayBottom";
        }
        throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "unknown bound position: "));
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    @NeverCompile
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("ScreenDecorations state:");
        PrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.increaseIndent();
        StringBuilder sb = new StringBuilder("DEBUG_DISABLE_SCREEN_DECORATIONS:");
        boolean z = DEBUG_DISABLE_SCREEN_DECORATIONS;
        sb.append(z);
        asIndenting.println(sb.toString());
        if (z) {
            return;
        }
        asIndenting.println("mDebug:" + this.mDebug);
        asIndenting.println("mIsPrivacyDotEnabled:" + this.mDotFactory.getHasProviders());
        asIndenting.println("shouldOptimizeOverlayVisibility:" + shouldOptimizeVisibility());
        FaceScanningProviderFactory faceScanningProviderFactory = this.mFaceScanningFactory;
        boolean hasProviders = faceScanningProviderFactory.getHasProviders();
        asIndenting.println("supportsShowingFaceScanningAnim:" + hasProviders);
        if (hasProviders) {
            asIndenting.increaseIndent();
            StringBuilder sb2 = new StringBuilder("canShowFaceScanningAnim:");
            boolean hasProviders2 = faceScanningProviderFactory.getHasProviders();
            KeyguardUpdateMonitor keyguardUpdateMonitor = faceScanningProviderFactory.keyguardUpdateMonitor;
            sb2.append(hasProviders2 && keyguardUpdateMonitor.isFaceEnabledAndEnrolled());
            asIndenting.println(sb2.toString());
            StringBuilder sb3 = new StringBuilder("shouldShowFaceScanningAnim (at time dump was taken):");
            sb3.append(faceScanningProviderFactory.getHasProviders() && keyguardUpdateMonitor.isFaceEnabledAndEnrolled() && (keyguardUpdateMonitor.isFaceDetectionRunning() || faceScanningProviderFactory.authController.isShowing()));
            asIndenting.println(sb3.toString());
            asIndenting.decreaseIndent();
        }
        FaceScanningOverlay faceScanningOverlay = (FaceScanningOverlay) getOverlayView(this.mFaceScanningViewId);
        if (faceScanningOverlay != null) {
            faceScanningOverlay.dump(asIndenting);
        }
        asIndenting.println("mPendingConfigChange:" + this.mPendingConfigChange);
        if (this.mHwcScreenDecorationSupport != null) {
            asIndenting.increaseIndent();
            asIndenting.println("mHwcScreenDecorationSupport:");
            asIndenting.increaseIndent();
            asIndenting.println("format=" + PixelFormat.formatToString(this.mHwcScreenDecorationSupport.format));
            StringBuilder sb4 = new StringBuilder("alphaInterpretation=");
            int i = this.mHwcScreenDecorationSupport.alphaInterpretation;
            sb4.append(i != 0 ? i != 1 ? AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unknown: ") : "MASK" : "COVERAGE");
            asIndenting.println(sb4.toString());
            asIndenting.decreaseIndent();
            asIndenting.decreaseIndent();
        } else {
            asIndenting.increaseIndent();
            printWriter.println("mHwcScreenDecorationSupport: null");
            asIndenting.decreaseIndent();
        }
        if (this.mScreenDecorHwcLayer != null) {
            asIndenting.increaseIndent();
            this.mScreenDecorHwcLayer.dump(asIndenting);
            asIndenting.decreaseIndent();
        } else {
            asIndenting.println("mScreenDecorHwcLayer: null");
        }
        if (this.mOverlays != null) {
            StringBuilder sb5 = new StringBuilder("mOverlays(left,top,right,bottom)=(");
            sb5.append(this.mOverlays[0] != null);
            sb5.append(",");
            sb5.append(this.mOverlays[1] != null);
            sb5.append(",");
            sb5.append(this.mOverlays[2] != null);
            sb5.append(",");
            sb5.append(this.mOverlays[3] != null);
            sb5.append(")");
            asIndenting.println(sb5.toString());
            for (int i2 = 0; i2 < 4; i2++) {
                OverlayWindow overlayWindow = this.mOverlays[i2];
                if (overlayWindow != null) {
                    printWriter.println("  " + getWindowTitleByPos(i2) + "=");
                    RegionInterceptingFrameLayout regionInterceptingFrameLayout = overlayWindow.rootView;
                    printWriter.println("    rootView=" + regionInterceptingFrameLayout);
                    int childCount = regionInterceptingFrameLayout.getChildCount();
                    for (int i3 = 0; i3 < childCount; i3++) {
                        View childAt = regionInterceptingFrameLayout.getChildAt(i3);
                        Pair pair = (Pair) overlayWindow.viewProviderMap.get(Integer.valueOf(childAt.getId()));
                        printWriter.println("    child[" + i3 + "]=" + childAt + " " + (pair != null ? (DecorProvider) pair.getSecond() : null));
                    }
                }
            }
        }
        this.mRoundedCornerResDelegate.dump(printWriter, strArr);
        DebugRoundedCornerDelegate debugRoundedCornerDelegate = this.mDebugRoundedCornerDelegate;
        debugRoundedCornerDelegate.getClass();
        printWriter.println("DebugRoundedCornerDelegate state:");
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  hasTop=", debugRoundedCornerDelegate.hasTop, printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("  hasBottom=", debugRoundedCornerDelegate.hasBottom, printWriter);
        printWriter.println(MutableVectorKt$$ExternalSyntheticOutline0.m(debugRoundedCornerDelegate.topRoundedSize.getWidth(), debugRoundedCornerDelegate.topRoundedSize.getHeight(), "  topRoundedSize(w,h)=(", ",", ")"));
        printWriter.println(MutableVectorKt$$ExternalSyntheticOutline0.m(debugRoundedCornerDelegate.bottomRoundedSize.getWidth(), debugRoundedCornerDelegate.bottomRoundedSize.getHeight(), "  bottomRoundedSize(w,h)=(", ",", ")"));
        printWriter.println("  physicalPixelDisplaySizeRatio=" + debugRoundedCornerDelegate.physicalPixelDisplaySizeRatio);
    }

    public final WindowManager.LayoutParams getHwcWindowLayoutParams() {
        WindowManager.LayoutParams windowLayoutBaseParams = getWindowLayoutBaseParams();
        windowLayoutBaseParams.width = -1;
        windowLayoutBaseParams.height = -1;
        windowLayoutBaseParams.setTitle("ScreenDecorHwcOverlay");
        if (sToolkitSetFrameRateReadOnly) {
            windowLayoutBaseParams.setFrameRateBoostOnTouchEnabled(false);
            windowLayoutBaseParams.setFrameRatePowerSavingsBalanced(false);
        }
        windowLayoutBaseParams.gravity = 8388659;
        if (!this.mDebug) {
            windowLayoutBaseParams.setColorMode(4);
        }
        return windowLayoutBaseParams;
    }

    public View getOverlayView(int i) {
        View view;
        OverlayWindow[] overlayWindowArr = this.mOverlays;
        if (overlayWindowArr == null) {
            return null;
        }
        for (OverlayWindow overlayWindow : overlayWindowArr) {
            if (overlayWindow != null && (view = overlayWindow.getView(i)) != null) {
                return view;
            }
        }
        return null;
    }

    public float getPhysicalPixelDisplaySizeRatio() {
        this.mContext.getDisplay().getDisplayInfo(this.mDisplayInfo);
        Display.Mode maximumResolutionDisplayMode = DisplayUtils.getMaximumResolutionDisplayMode(this.mDisplayInfo.supportedModes);
        if (maximumResolutionDisplayMode == null) {
            return 1.0f;
        }
        return DisplayUtils.getPhysicalPixelDisplaySizeRatio(maximumResolutionDisplayMode.getPhysicalWidth(), maximumResolutionDisplayMode.getPhysicalHeight(), this.mDisplayInfo.getNaturalWidth(), this.mDisplayInfo.getNaturalHeight());
    }

    public List getProviders(boolean z) {
        ArrayList arrayList = new ArrayList(this.mDotFactory.getProviders());
        arrayList.addAll(this.mFaceScanningFactory.getProviders());
        if (!z) {
            if (this.mDebug && this.mDebugRoundedCornerFactory.getHasProviders()) {
                arrayList.addAll(this.mDebugRoundedCornerFactory.getProviders());
            } else {
                arrayList.addAll(this.mRoundedCornerFactory.getProviders());
            }
            arrayList.addAll(this.mCutoutFactory.getProviders());
        }
        return arrayList;
    }

    public WindowManager.LayoutParams getWindowLayoutParams(int i) {
        WindowManager.LayoutParams windowLayoutBaseParams = getWindowLayoutBaseParams();
        int boundPositionFromRotation = getBoundPositionFromRotation(i, this.mRotation);
        int i2 = 3;
        windowLayoutBaseParams.width = (boundPositionFromRotation == 1 || boundPositionFromRotation == 3) ? -1 : -2;
        int boundPositionFromRotation2 = getBoundPositionFromRotation(i, this.mRotation);
        windowLayoutBaseParams.height = (boundPositionFromRotation2 == 1 || boundPositionFromRotation2 == 3) ? -2 : -1;
        windowLayoutBaseParams.setTitle(getWindowTitleByPos(i));
        int boundPositionFromRotation3 = getBoundPositionFromRotation(i, this.mRotation);
        if (boundPositionFromRotation3 != 0) {
            if (boundPositionFromRotation3 == 1) {
                i2 = 48;
            } else if (boundPositionFromRotation3 == 2) {
                i2 = 5;
            } else {
                if (boundPositionFromRotation3 != 3) {
                    throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "unknown bound position: "));
                }
                i2 = 80;
            }
        }
        windowLayoutBaseParams.gravity = i2;
        return windowLayoutBaseParams;
    }

    public final int getWindowVisibility(OverlayWindow overlayWindow, boolean z) {
        if (!z) {
            return 0;
        }
        int[] iArr = {R.id.privacy_dot_top_left_container, R.id.privacy_dot_top_right_container, R.id.privacy_dot_bottom_left_container, R.id.privacy_dot_bottom_right_container, this.mFaceScanningViewId};
        for (int i = 0; i < 5; i++) {
            View view = overlayWindow.getView(iArr[i]);
            if (view != null && view.getVisibility() == 0) {
                return 0;
            }
        }
        return 4;
    }

    public boolean hasOverlays() {
        if (this.mOverlays == null) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (this.mOverlays[i] != null) {
                return true;
            }
        }
        this.mOverlays = null;
        return false;
    }

    public boolean hasSameProviders(List list) {
        ArrayList arrayList = new ArrayList();
        OverlayWindow[] overlayWindowArr = this.mOverlays;
        if (overlayWindowArr != null) {
            for (OverlayWindow overlayWindow : overlayWindowArr) {
                if (overlayWindow != null) {
                    arrayList.addAll(CollectionsKt.toList(overlayWindow.viewProviderMap.keySet()));
                }
            }
        }
        if (arrayList.size() != list.size()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (!arrayList.contains(Integer.valueOf(((DecorProvider) it.next()).getViewId()))) {
                return false;
            }
        }
        return true;
    }

    public void hideCameraProtection() {
        FaceScanningOverlay faceScanningOverlay = (FaceScanningOverlay) getOverlayView(this.mFaceScanningViewId);
        if (faceScanningOverlay != null) {
            faceScanningOverlay.hideOverlayRunnable = new ScreenDecorations$$ExternalSyntheticLambda7(this, faceScanningOverlay, 2);
            faceScanningOverlay.enableShowProtection(false);
        }
        ScreenDecorHwcLayer screenDecorHwcLayer = this.mScreenDecorHwcLayer;
        if (screenDecorHwcLayer != null) {
            screenDecorHwcLayer.enableShowProtection(false);
            return;
        }
        int i = 0;
        for (int i2 : DISPLAY_CUTOUT_IDS) {
            View overlayView = getOverlayView(i2);
            if (overlayView instanceof DisplayCutoutView) {
                i++;
                ((DisplayCutoutView) overlayView).enableShowProtection(false);
            }
        }
        if (i == 0) {
            Log.e("ScreenDecorations", "CutoutView not initialized hideCameraProtection");
        }
    }

    public final void initOverlay(final OverlayWindow overlayWindow, List list, boolean z) {
        overlayWindow.getClass();
        if (list.size() == overlayWindow.viewProviderMap.size()) {
            if (!list.isEmpty()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (overlayWindow.getView(((DecorProvider) it.next()).getViewId()) != null) {
                    }
                }
            }
            overlayWindow.rootView.setVisibility(getWindowVisibility(overlayWindow, z));
        }
        list.forEach(new Consumer() { // from class: com.android.systemui.ScreenDecorations$$ExternalSyntheticLambda8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                View view;
                ScreenDecorations screenDecorations = ScreenDecorations.this;
                OverlayWindow overlayWindow2 = overlayWindow;
                DecorProvider decorProvider = (DecorProvider) obj;
                screenDecorations.getClass();
                if (overlayWindow2.getView(decorProvider.getViewId()) != null) {
                    return;
                }
                int viewId = decorProvider.getViewId();
                OverlayWindow[] overlayWindowArr = screenDecorations.mOverlays;
                if (overlayWindowArr != null) {
                    for (OverlayWindow overlayWindow3 : overlayWindowArr) {
                        if (overlayWindow3 != null && (view = overlayWindow3.getView(viewId)) != null) {
                            overlayWindow3.rootView.removeView(view);
                            overlayWindow3.viewProviderMap.remove(Integer.valueOf(viewId));
                        }
                    }
                }
                overlayWindow2.viewProviderMap.put(Integer.valueOf(decorProvider.getViewId()), new Pair(decorProvider.inflateView(overlayWindow2.context, overlayWindow2.rootView, screenDecorations.mRotation, screenDecorations.mTintColor), decorProvider));
            }
        });
        overlayWindow.rootView.setVisibility(getWindowVisibility(overlayWindow, z));
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onConfigChanged(Configuration configuration) {
        if (DEBUG_DISABLE_SCREEN_DECORATIONS) {
            Log.i("ScreenDecorations", "ScreenDecorations is disabled");
        } else {
            this.mExecutor.execute(new ScreenDecorations$$ExternalSyntheticLambda2(this, 0));
        }
    }

    public void onFaceSensorLocationChanged(Point point) {
        ScreenDecorationsLogger screenDecorationsLogger = this.mLogger;
        screenDecorationsLogger.getClass();
        screenDecorationsLogger.logBuffer.log("ScreenDecorationsLog", LogLevel.DEBUG, "AuthControllerCallback in ScreenDecorations triggered", null);
        ExecutorImpl executorImpl = this.mExecutor;
        if (executorImpl != null) {
            executorImpl.execute(new ScreenDecorations$$ExternalSyntheticLambda2(this, 2));
        }
    }

    public final void removeAllOverlays() {
        if (this.mOverlays == null) {
            return;
        }
        for (int i = 0; i < 4; i++) {
            OverlayWindow[] overlayWindowArr = this.mOverlays;
            OverlayWindow overlayWindow = overlayWindowArr[i];
            if (overlayWindow != null && overlayWindowArr != null && overlayWindow != null) {
                this.mWindowManager.removeViewImmediate(overlayWindow.rootView);
                this.mOverlays[i] = null;
            }
        }
        this.mOverlays = null;
    }

    public final void removeHwcOverlay() {
        ViewGroup viewGroup = this.mScreenDecorHwcWindow;
        if (viewGroup == null) {
            return;
        }
        this.mWindowManager.removeViewImmediate(viewGroup);
        this.mScreenDecorHwcWindow = null;
        this.mScreenDecorHwcLayer = null;
    }

    public void setDebug(boolean z) {
        if (this.mDebug == z) {
            return;
        }
        this.mDebug = z;
        if (!z) {
            DebugRoundedCornerDelegate debugRoundedCornerDelegate = this.mDebugRoundedCornerDelegate;
            debugRoundedCornerDelegate.hasTop = false;
            debugRoundedCornerDelegate.topRoundedDrawable = null;
            debugRoundedCornerDelegate.topRoundedSize = new Size(0, 0);
            debugRoundedCornerDelegate.hasBottom = false;
            debugRoundedCornerDelegate.bottomRoundedDrawable = null;
            debugRoundedCornerDelegate.bottomRoundedSize = new Size(0, 0);
        }
        this.mExecutor.execute(new ScreenDecorations$$ExternalSyntheticLambda2(this, 4));
    }

    public void setSize(View view, Size size) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = size.getWidth();
        layoutParams.height = size.getHeight();
        view.setLayoutParams(layoutParams);
    }

    /* JADX WARN: Code restructure failed: missing block: B:144:0x02d3, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r9 != null ? r9 : null, r7) != false) goto L157;
     */
    /* JADX WARN: Type inference failed for: r3v37, types: [com.android.systemui.ScreenDecorations$4] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setupDecorations() {
        /*
            Method dump skipped, instructions count: 947
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.ScreenDecorations.setupDecorations():void");
    }

    public final boolean shouldOptimizeVisibility() {
        return (this.mDotFactory.getHasProviders() || this.mFaceScanningFactory.getHasProviders()) && !(this.mHwcScreenDecorationSupport == null && (this.mRoundedCornerFactory.getHasProviders() || this.mDebugRoundedCornerFactory.getHasProviders() || this.mCutoutFactory.getHasProviders()));
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void showCameraProtection(android.graphics.Path r10, android.graphics.Rect r11) {
        /*
            r9 = this;
            com.android.systemui.decor.FaceScanningProviderFactory r0 = r9.mFaceScanningFactory
            boolean r1 = r0.getHasProviders()
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L22
            com.android.keyguard.KeyguardUpdateMonitor r1 = r0.keyguardUpdateMonitor
            boolean r4 = r1.isFaceEnabledAndEnrolled()
            if (r4 == 0) goto L22
            boolean r1 = r1.isFaceDetectionRunning()
            if (r1 != 0) goto L20
            com.android.systemui.biometrics.AuthController r0 = r0.authController
            boolean r0 = r0.isShowing()
            if (r0 == 0) goto L22
        L20:
            r0 = r3
            goto L23
        L22:
            r0 = r2
        L23:
            com.android.systemui.log.ScreenDecorationsLogger r1 = r9.mLogger
            if (r0 == 0) goto L42
            int r0 = r9.mFaceScanningViewId
            android.view.View r4 = r9.getOverlayView(r0)
            com.android.systemui.ScreenDecorations$DisplayCutoutView r4 = (com.android.systemui.ScreenDecorations.DisplayCutoutView) r4
            if (r4 == 0) goto L42
            r1.cameraProtectionBoundsForScanningOverlay(r11)
            r4.setProtection(r10, r11)
            r4.enableShowProtection(r3)
            android.view.View r10 = r4.findViewById(r0)
            r9.updateOverlayWindowVisibilityIfViewExists(r10)
            return
        L42:
            com.android.systemui.ScreenDecorHwcLayer r0 = r9.mScreenDecorHwcLayer
            if (r0 == 0) goto L54
            r1.hwcLayerCameraProtectionBounds(r11)
            com.android.systemui.ScreenDecorHwcLayer r0 = r9.mScreenDecorHwcLayer
            r0.setProtection(r10, r11)
            com.android.systemui.ScreenDecorHwcLayer r9 = r9.mScreenDecorHwcLayer
            r9.enableShowProtection(r3)
            return
        L54:
            int[] r0 = com.android.systemui.ScreenDecorations.DISPLAY_CUTOUT_IDS
            int r4 = r0.length
            r5 = r2
        L58:
            if (r2 >= r4) goto L75
            r6 = r0[r2]
            android.view.View r7 = r9.getOverlayView(r6)
            boolean r8 = r7 instanceof com.android.systemui.ScreenDecorations.DisplayCutoutView
            if (r8 != 0) goto L65
            goto L72
        L65:
            int r5 = r5 + 1
            com.android.systemui.ScreenDecorations$DisplayCutoutView r7 = (com.android.systemui.ScreenDecorations.DisplayCutoutView) r7
            r1.dcvCameraBounds(r6, r11)
            r7.setProtection(r10, r11)
            r7.enableShowProtection(r3)
        L72:
            int r2 = r2 + 1
            goto L58
        L75:
            if (r5 != 0) goto L86
            r1.getClass()
            com.android.systemui.log.core.LogLevel r9 = com.android.systemui.log.core.LogLevel.ERROR
            r10 = 0
            com.android.systemui.log.LogBuffer r11 = r1.logBuffer
            java.lang.String r0 = "ScreenDecorationsLog"
            java.lang.String r1 = "CutoutView not initialized showCameraProtection"
            r11.log(r0, r9, r1, r10)
        L86:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.ScreenDecorations.showCameraProtection(android.graphics.Path, android.graphics.Rect):void");
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (DEBUG_DISABLE_SCREEN_DECORATIONS) {
            Log.i("ScreenDecorations", "ScreenDecorations is disabled");
            return;
        }
        ThreadFactoryImpl threadFactoryImpl = this.mThreadFactory;
        threadFactoryImpl.getClass();
        HandlerThread handlerThread = new HandlerThread("ScreenDecorations");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.mHandler = handler;
        threadFactoryImpl.getClass();
        ExecutorImpl executorImpl = new ExecutorImpl(handler.getLooper());
        this.mExecutor = executorImpl;
        executorImpl.execute(new ScreenDecorations$$ExternalSyntheticLambda2(this, 3));
        this.mDotViewController.uiExecutor = this.mExecutor;
        this.mCommandRegistry.registerCommand("screen-decor", new Function0() { // from class: com.android.systemui.ScreenDecorations$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ScreenDecorations screenDecorations = ScreenDecorations.this;
                screenDecorations.getClass();
                return new ScreenDecorCommand(screenDecorations.mScreenDecorCommandCallback);
            }
        });
    }

    public final void startOnScreenDecorationsThread() {
        Trace.beginSection("ScreenDecorations#startOnScreenDecorationsThread");
        this.mContext.getDisplay().getDisplayInfo(this.mDisplayInfo);
        DisplayInfo displayInfo = this.mDisplayInfo;
        this.mRotation = displayInfo.rotation;
        this.mDisplaySize.x = displayInfo.getNaturalWidth();
        this.mDisplaySize.y = this.mDisplayInfo.getNaturalHeight();
        DisplayInfo displayInfo2 = this.mDisplayInfo;
        this.mDisplayUniqueId = displayInfo2.uniqueId;
        this.mDisplayCutout = displayInfo2.displayCutout;
        RoundedCornerResDelegateImpl roundedCornerResDelegateImpl = new RoundedCornerResDelegateImpl(this.mContext.getResources(), this.mDisplayUniqueId);
        this.mRoundedCornerResDelegate = roundedCornerResDelegateImpl;
        float physicalPixelDisplaySizeRatio = getPhysicalPixelDisplaySizeRatio();
        if (roundedCornerResDelegateImpl.physicalPixelDisplaySizeRatio != physicalPixelDisplaySizeRatio) {
            roundedCornerResDelegateImpl.physicalPixelDisplaySizeRatio = physicalPixelDisplaySizeRatio;
            roundedCornerResDelegateImpl.reloadMeasures();
        }
        this.mRoundedCornerFactory = new RoundedCornerDecorProviderFactory(this.mRoundedCornerResDelegate);
        this.mDebugRoundedCornerFactory = new RoundedCornerDecorProviderFactory(this.mDebugRoundedCornerDelegate);
        this.mCutoutFactory = new CutoutDecorProviderFactory(this.mContext.getResources(), this.mContext.getDisplay());
        this.mHwcScreenDecorationSupport = this.mContext.getDisplay().getDisplayDecorationSupport();
        updateHwLayerRoundedCornerDrawable();
        setupDecorations();
        if (this.mContext.getResources().getBoolean(R.bool.config_enableDisplayCutoutProtection)) {
            Context context = this.mContext;
            ExecutorImpl executorImpl = this.mExecutor;
            CameraAvailabilityListener cameraAvailabilityListener = new CameraAvailabilityListener((CameraManager) context.getSystemService("camera"), this.mCameraProtectionLoader.loadCameraProtectionInfoList(), context.getResources().getString(R.string.config_cameraProtectionExcludedPackages), executorImpl);
            this.mCameraListener = cameraAvailabilityListener;
            cameraAvailabilityListener.listeners.add(this.mCameraTransitionCallback);
            CameraAvailabilityListener cameraAvailabilityListener2 = this.mCameraListener;
            cameraAvailabilityListener2.cameraManager.registerAvailabilityCallback(cameraAvailabilityListener2.executor, cameraAvailabilityListener2.availabilityCallback);
        }
        DisplayTracker.Callback callback = new DisplayTracker.Callback() { // from class: com.android.systemui.ScreenDecorations.3
            @Override // com.android.systemui.settings.DisplayTracker.Callback
            public final void onDisplayChanged() {
                ScreenDecorations screenDecorations = ScreenDecorations.this;
                screenDecorations.mContext.getDisplay().getDisplayInfo(screenDecorations.mDisplayInfo);
                DisplayInfo displayInfo3 = screenDecorations.mDisplayInfo;
                int i = displayInfo3.rotation;
                if ((screenDecorations.mOverlays != null || screenDecorations.mScreenDecorHwcWindow != null) && (screenDecorations.mRotation != i || ScreenDecorations.displaySizeChanged(screenDecorations.mDisplaySize, displayInfo3))) {
                    Point point = new Point();
                    point.x = screenDecorations.mDisplayInfo.getNaturalWidth();
                    point.y = screenDecorations.mDisplayInfo.getNaturalHeight();
                    screenDecorations.mPendingConfigChange = true;
                    int i2 = screenDecorations.mRotation;
                    ScreenDecorationsLogger screenDecorationsLogger = screenDecorations.mLogger;
                    if (i2 != i) {
                        screenDecorationsLogger.logRotationChangeDeferred(i2, i);
                    }
                    if (!screenDecorations.mDisplaySize.equals(point)) {
                        screenDecorationsLogger.logDisplaySizeChanged(screenDecorations.mDisplaySize, point);
                    }
                    if (screenDecorations.mOverlays != null) {
                        for (int i3 = 0; i3 < 4; i3++) {
                            OverlayWindow overlayWindow = screenDecorations.mOverlays[i3];
                            if (overlayWindow != null) {
                                RegionInterceptingFrameLayout regionInterceptingFrameLayout = overlayWindow.rootView;
                                regionInterceptingFrameLayout.getViewTreeObserver().addOnPreDrawListener(screenDecorations.new RestartingPreDrawListener(regionInterceptingFrameLayout, i, point));
                            }
                        }
                    }
                    ViewGroup viewGroup = screenDecorations.mScreenDecorHwcWindow;
                    if (viewGroup != null) {
                        viewGroup.getViewTreeObserver().addOnPreDrawListener(screenDecorations.new RestartingPreDrawListener(screenDecorations.mScreenDecorHwcWindow, i, point));
                    }
                    ScreenDecorHwcLayer screenDecorHwcLayer = screenDecorations.mScreenDecorHwcLayer;
                    if (screenDecorHwcLayer != null) {
                        screenDecorHwcLayer.pendingConfigChange = true;
                    }
                }
                String str = screenDecorations.mDisplayInfo.uniqueId;
                if (Objects.equals(str, screenDecorations.mDisplayUniqueId)) {
                    return;
                }
                screenDecorations.mDisplayUniqueId = str;
                DisplayDecorationSupport displayDecorationSupport = screenDecorations.mContext.getDisplay().getDisplayDecorationSupport();
                screenDecorations.mRoundedCornerResDelegate.updateDisplayUniqueId(null, str);
                if (screenDecorations.hasSameProviders(screenDecorations.getProviders(displayDecorationSupport != null))) {
                    DisplayDecorationSupport displayDecorationSupport2 = screenDecorations.mHwcScreenDecorationSupport;
                    if (displayDecorationSupport != null ? displayDecorationSupport2 != null && displayDecorationSupport.format == displayDecorationSupport2.format && displayDecorationSupport.alphaInterpretation == displayDecorationSupport2.alphaInterpretation : displayDecorationSupport2 == null) {
                        if (screenDecorations.mPendingManualConfigUpdate) {
                            screenDecorations.mPendingManualConfigUpdate = false;
                            screenDecorations.onConfigChanged(screenDecorations.mContext.getResources().getConfiguration());
                            return;
                        }
                        return;
                    }
                }
                screenDecorations.mHwcScreenDecorationSupport = displayDecorationSupport;
                screenDecorations.removeAllOverlays();
                screenDecorations.setupDecorations();
            }
        };
        this.mDisplayListener = callback;
        ((DisplayTrackerImpl) this.mDisplayTracker).addDisplayChangeCallback(callback, new HandlerExecutor(this.mHandler));
        updateConfiguration();
        this.mJavaAdapter.alwaysCollectFlow(this.mFacePropertyRepository.sensorLocation, new Consumer() { // from class: com.android.systemui.ScreenDecorations$$ExternalSyntheticLambda9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ScreenDecorations.this.onFaceSensorLocationChanged((Point) obj);
            }
        });
        Trace.endSection();
    }

    public final void updateColorInversion(int i) {
        this.mTintColor = i != 0 ? -1 : DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
        if (this.mDebug) {
            int i2 = this.mDebugColor;
            this.mTintColor = i2;
            DebugRoundedCornerDelegate debugRoundedCornerDelegate = this.mDebugRoundedCornerDelegate;
            if (debugRoundedCornerDelegate.color != i2) {
                debugRoundedCornerDelegate.color = i2;
                debugRoundedCornerDelegate.paint.setColor(i2);
            }
        }
        updateOverlayProviderViews(new Integer[]{Integer.valueOf(this.mFaceScanningViewId), Integer.valueOf(R.id.display_cutout), Integer.valueOf(R.id.display_cutout_left), Integer.valueOf(R.id.display_cutout_right), Integer.valueOf(R.id.display_cutout_bottom), Integer.valueOf(R.id.rounded_corner_top_left), Integer.valueOf(R.id.rounded_corner_top_right), Integer.valueOf(R.id.rounded_corner_bottom_left), Integer.valueOf(R.id.rounded_corner_bottom_right)});
    }

    public void updateConfiguration() {
        Object obj;
        Preconditions.checkState(this.mHandler.getLooper().getThread() == Thread.currentThread(), "must call on " + this.mHandler.getLooper().getThread() + ", but was " + Thread.currentThread());
        this.mContext.getDisplay().getDisplayInfo(this.mDisplayInfo);
        int i = this.mDisplayInfo.rotation;
        if (this.mRotation != i) {
            PrivacyDotViewController privacyDotViewController = this.mDotViewController;
            synchronized (privacyDotViewController.lock) {
                ViewState viewState = privacyDotViewController.nextViewState;
                if (i != viewState.rotation) {
                    boolean z = viewState.layoutRtl;
                    privacyDotViewController.setCornerVisibilities();
                    View selectDesignatedCorner = privacyDotViewController.selectDesignatedCorner(i, z);
                    int cornerForView = selectDesignatedCorner != null ? privacyDotViewController.cornerForView(selectDesignatedCorner) : -1;
                    StatusBarContentInsetsProvider statusBarContentInsetsProvider = privacyDotViewController.contentInsetsProvider;
                    statusBarContentInsetsProvider.getClass();
                    Resources resourcesForRotation = RotationUtils.getResourcesForRotation(i, statusBarContentInsetsProvider.context);
                    if (resourcesForRotation == null) {
                        resourcesForRotation = statusBarContentInsetsProvider.context.getResources();
                    }
                    int dimensionPixelSize = resourcesForRotation.getDimensionPixelSize(R.dimen.status_bar_padding_top);
                    Object obj2 = privacyDotViewController.lock;
                    synchronized (obj2) {
                        try {
                            obj = obj2;
                        } catch (Throwable th) {
                            th = th;
                            obj = obj2;
                        }
                        try {
                            privacyDotViewController.setNextViewState(ViewState.copy$default(privacyDotViewController.nextViewState, false, false, false, null, null, null, null, false, i, dimensionPixelSize, cornerForView, selectDesignatedCorner, null, 8703));
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    }
                }
            }
        }
        DisplayInfo displayInfo = this.mDisplayInfo;
        DisplayCutout displayCutout = displayInfo.displayCutout;
        if (!this.mPendingConfigChange && (i != this.mRotation || displaySizeChanged(this.mDisplaySize, displayInfo) || !Objects.equals(displayCutout, this.mDisplayCutout))) {
            this.mRotation = i;
            this.mDisplaySize.x = this.mDisplayInfo.getNaturalWidth();
            this.mDisplaySize.y = this.mDisplayInfo.getNaturalHeight();
            this.mDisplayCutout = displayCutout;
            float physicalPixelDisplaySizeRatio = getPhysicalPixelDisplaySizeRatio();
            RoundedCornerResDelegateImpl roundedCornerResDelegateImpl = this.mRoundedCornerResDelegate;
            if (roundedCornerResDelegateImpl.physicalPixelDisplaySizeRatio != physicalPixelDisplaySizeRatio) {
                roundedCornerResDelegateImpl.physicalPixelDisplaySizeRatio = physicalPixelDisplaySizeRatio;
                roundedCornerResDelegateImpl.reloadMeasures();
            }
            DebugRoundedCornerDelegate debugRoundedCornerDelegate = this.mDebugRoundedCornerDelegate;
            if (debugRoundedCornerDelegate.physicalPixelDisplaySizeRatio != physicalPixelDisplaySizeRatio) {
                debugRoundedCornerDelegate.physicalPixelDisplaySizeRatio = physicalPixelDisplaySizeRatio;
                PathDrawable pathDrawable = debugRoundedCornerDelegate.topRoundedDrawable;
                if (pathDrawable != null) {
                    debugRoundedCornerDelegate.topRoundedSize = new Size(pathDrawable.width, pathDrawable.height);
                }
                PathDrawable pathDrawable2 = debugRoundedCornerDelegate.bottomRoundedDrawable;
                if (pathDrawable2 != null) {
                    debugRoundedCornerDelegate.bottomRoundedSize = new Size(pathDrawable2.width, pathDrawable2.height);
                }
                if (debugRoundedCornerDelegate.physicalPixelDisplaySizeRatio != 1.0f) {
                    if (debugRoundedCornerDelegate.topRoundedSize.getWidth() != 0) {
                        debugRoundedCornerDelegate.topRoundedSize = new Size((int) ((debugRoundedCornerDelegate.physicalPixelDisplaySizeRatio * debugRoundedCornerDelegate.topRoundedSize.getWidth()) + 0.5f), (int) ((debugRoundedCornerDelegate.physicalPixelDisplaySizeRatio * debugRoundedCornerDelegate.topRoundedSize.getHeight()) + 0.5f));
                    }
                    if (debugRoundedCornerDelegate.bottomRoundedSize.getWidth() != 0) {
                        debugRoundedCornerDelegate.bottomRoundedSize = new Size((int) ((debugRoundedCornerDelegate.physicalPixelDisplaySizeRatio * debugRoundedCornerDelegate.bottomRoundedSize.getWidth()) + 0.5f), (int) ((debugRoundedCornerDelegate.physicalPixelDisplaySizeRatio * debugRoundedCornerDelegate.bottomRoundedSize.getHeight()) + 0.5f));
                    }
                }
            }
            ScreenDecorHwcLayer screenDecorHwcLayer = this.mScreenDecorHwcLayer;
            if (screenDecorHwcLayer != null) {
                screenDecorHwcLayer.pendingConfigChange = false;
                screenDecorHwcLayer.updateConfiguration(this.mDisplayUniqueId);
                updateHwLayerRoundedCornerExistAndSize();
                updateHwLayerRoundedCornerDrawable();
            }
            updateLayoutParams();
            updateOverlayProviderViews(null);
        }
        FaceScanningOverlay faceScanningOverlay = (FaceScanningOverlay) getOverlayView(this.mFaceScanningViewId);
        if (faceScanningOverlay != null) {
            faceScanningOverlay.faceScanningAnimColor = Utils.getColorAttrDefaultColor(R.attr.wallpaperTextColorAccent, 0, faceScanningOverlay.getContext());
        }
    }

    public final void updateHwLayerRoundedCornerDrawable() {
        if (this.mScreenDecorHwcLayer == null) {
            return;
        }
        RoundedCornerResDelegateImpl roundedCornerResDelegateImpl = this.mRoundedCornerResDelegate;
        Drawable drawable = roundedCornerResDelegateImpl.topRoundedDrawable;
        Drawable drawable2 = roundedCornerResDelegateImpl.bottomRoundedDrawable;
        if (this.mDebug && this.mDebugRoundedCornerFactory.getHasProviders()) {
            DebugRoundedCornerDelegate debugRoundedCornerDelegate = this.mDebugRoundedCornerDelegate;
            drawable = debugRoundedCornerDelegate.topRoundedDrawable;
            drawable2 = debugRoundedCornerDelegate.bottomRoundedDrawable;
        }
        if (drawable == null && drawable2 == null) {
            return;
        }
        ScreenDecorHwcLayer screenDecorHwcLayer = this.mScreenDecorHwcLayer;
        screenDecorHwcLayer.roundedCornerDrawableTop = drawable;
        screenDecorHwcLayer.roundedCornerDrawableBottom = drawable2;
        if (drawable != null) {
            int i = screenDecorHwcLayer.roundedCornerTopSize;
            drawable.setBounds(0, 0, i, i);
        }
        Drawable drawable3 = screenDecorHwcLayer.roundedCornerDrawableBottom;
        if (drawable3 != null) {
            int i2 = screenDecorHwcLayer.roundedCornerBottomSize;
            drawable3.setBounds(0, 0, i2, i2);
        }
        screenDecorHwcLayer.invalidate();
        screenDecorHwcLayer.invalidate();
    }

    public final void updateHwLayerRoundedCornerExistAndSize() {
        if (this.mScreenDecorHwcLayer == null) {
            return;
        }
        if (this.mDebug && this.mDebugRoundedCornerFactory.getHasProviders()) {
            ScreenDecorHwcLayer screenDecorHwcLayer = this.mScreenDecorHwcLayer;
            DebugRoundedCornerDelegate debugRoundedCornerDelegate = this.mDebugRoundedCornerDelegate;
            screenDecorHwcLayer.updateRoundedCornerExistenceAndSize(debugRoundedCornerDelegate.topRoundedSize.getWidth(), this.mDebugRoundedCornerDelegate.bottomRoundedSize.getWidth(), debugRoundedCornerDelegate.hasTop, debugRoundedCornerDelegate.hasBottom);
            return;
        }
        ScreenDecorHwcLayer screenDecorHwcLayer2 = this.mScreenDecorHwcLayer;
        RoundedCornerResDelegateImpl roundedCornerResDelegateImpl = this.mRoundedCornerResDelegate;
        screenDecorHwcLayer2.updateRoundedCornerExistenceAndSize(roundedCornerResDelegateImpl.topRoundedSize.getWidth(), this.mRoundedCornerResDelegate.bottomRoundedSize.getWidth(), roundedCornerResDelegateImpl.hasTop, roundedCornerResDelegateImpl.hasBottom);
    }

    public final void updateLayoutParams() {
        Trace.beginSection("ScreenDecorations#updateLayoutParams");
        ViewGroup viewGroup = this.mScreenDecorHwcWindow;
        ViewCaptureAwareWindowManager viewCaptureAwareWindowManager = this.mWindowManager;
        if (viewGroup != null) {
            viewCaptureAwareWindowManager.updateViewLayout(viewGroup, getHwcWindowLayoutParams());
        }
        if (this.mOverlays != null) {
            for (int i = 0; i < 4; i++) {
                OverlayWindow overlayWindow = this.mOverlays[i];
                if (overlayWindow != null) {
                    viewCaptureAwareWindowManager.updateViewLayout(overlayWindow.rootView, getWindowLayoutParams(i));
                }
            }
        }
        Trace.endSection();
    }

    public void updateOverlayProviderViews(Integer[] numArr) {
        String str;
        Unit unit;
        int i;
        int i2;
        String str2;
        OverlayWindow[] overlayWindowArr = this.mOverlays;
        if (overlayWindowArr == null || this.mPendingConfigChange) {
            return;
        }
        this.mProviderRefreshToken++;
        for (OverlayWindow overlayWindow : overlayWindowArr) {
            if (overlayWindow != null) {
                int i3 = this.mProviderRefreshToken;
                int i4 = this.mRotation;
                int i5 = this.mTintColor;
                String str3 = this.mDisplayUniqueId;
                if (numArr != null) {
                    int length = numArr.length;
                    int i6 = 0;
                    while (i6 < length) {
                        Pair pair = (Pair) overlayWindow.viewProviderMap.get(Integer.valueOf(numArr[i6].intValue()));
                        if (pair != null) {
                            i = i6;
                            i2 = length;
                            str2 = str3;
                            ((DecorProvider) pair.getSecond()).onReloadResAndMeasure((View) pair.getFirst(), i3, i4, i5, str3);
                        } else {
                            i = i6;
                            i2 = length;
                            str2 = str3;
                        }
                        i6 = i + 1;
                        length = i2;
                        str3 = str2;
                    }
                    str = str3;
                    unit = Unit.INSTANCE;
                } else {
                    str = str3;
                    unit = null;
                }
                if (unit == null) {
                    for (Pair pair2 : overlayWindow.viewProviderMap.values()) {
                        ((DecorProvider) pair2.getSecond()).onReloadResAndMeasure((View) pair2.getFirst(), i3, i4, i5, str);
                    }
                }
            }
        }
    }

    public void updateOverlayWindowVisibilityIfViewExists(View view) {
        if (view == null) {
            return;
        }
        this.mExecutor.execute(new ScreenDecorations$$ExternalSyntheticLambda7(this, view, 0));
    }
}
