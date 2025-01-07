package com.android.systemui.accessibility;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.util.Property;
import android.util.Range;
import android.util.Size;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.AttachedSurfaceControl;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.IMagnificationConnectionCallback;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.math.MathUtils;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.accessibility.common.MagnificationConstants;
import com.android.internal.graphics.SfVsyncFrameCallbackProvider;
import com.android.systemui.accessibility.MagnificationGestureDetector;
import com.android.systemui.accessibility.MagnificationImpl;
import com.android.systemui.model.SysUiState;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Locale;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowMagnificationController implements View.OnTouchListener, SurfaceHolder.Callback, MagnificationGestureDetector.OnGestureListener, ComponentCallbacks {
    public static final Range A11Y_ACTION_SCALE_RANGE;
    public static final float[] COLOR_BLACK_ARRAY;
    public static final boolean DEBUG;
    static final double HORIZONTAL_LOCK_BASE;
    public boolean mAllowDiagonalScrolling;
    public final WindowMagnificationAnimationController mAnimationController;
    public int mBorderDragSize;
    public View mBottomDrag;
    public ImageView mBottomLeftCornerView;
    public ImageView mBottomRightCornerView;
    public float mBounceEffectAnimationScale;
    public final int mBounceEffectDuration;
    public int mButtonRepositionThresholdFromEdge;
    public ImageView mCloseView;
    public final Configuration mConfiguration;
    public final Context mContext;
    public final int mDisplayId;
    public ImageView mDragView;
    public boolean mEditSizeEnable;
    public final MagnificationGestureDetector mGestureDetector;
    public final MagnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda1 mGlobalWindowSessionSupplier;
    public final MagnificationImpl.AnonymousClass1 mHandler;
    public boolean mIsDragging;
    public View mLeftDrag;
    public Locale mLocale;
    public final Rect mMagnificationFrame;
    public final Rect mMagnificationFrameBoundary;
    public int mMagnificationFrameOffsetX;
    public int mMagnificationFrameOffsetY;
    public final SparseArray mMagnificationSizeScaleOptions;
    public int mMinWindowSize;
    public View mMirrorBorderView;
    public SurfaceControl mMirrorSurface;
    public int mMirrorSurfaceMargin;
    public SurfaceView mMirrorSurfaceView;
    public final WindowMagnificationController$$ExternalSyntheticLambda3 mMirrorSurfaceViewLayoutChangeListener;
    public View mMirrorView;
    public final Rect mMirrorViewBounds;
    public final WindowMagnificationController$$ExternalSyntheticLambda3 mMirrorViewLayoutChangeListener;
    public final AnonymousClass1 mMirrorViewRunnable;
    public int mOuterBorderSize;
    public boolean mOverlapWithGestureInsets;
    public NumberFormat mPercentFormat;
    public final Resources mResources;
    public View mRightDrag;
    int mRotation;
    public float mScale;
    public final MagnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda0 mScvhSupplier;
    public int mSettingsButtonIndex;
    public boolean mSettingsPanelVisibility;
    public final Rect mSourceBounds;
    public SurfaceControlViewHost mSurfaceControlViewHost;
    public final SysUiState mSysUiState;
    public int mSystemGestureTop;
    public final Rect mTmpRect;
    public View mTopDrag;
    public ImageView mTopLeftCornerView;
    public ImageView mTopRightCornerView;
    public final SurfaceControl.Transaction mTransaction;
    public final WindowMagnificationController$$ExternalSyntheticLambda0 mUpdateStateDescriptionRunnable;
    public final Rect mWindowBounds;
    public final WindowMagnificationController$$ExternalSyntheticLambda0 mWindowInsetChangeRunnable;
    WindowMagnificationFrameSizePrefs mWindowMagnificationFrameSizePrefs;
    public final MagnificationImpl.AnonymousClass3 mWindowMagnifierCallback;
    public final WindowManager mWm;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.accessibility.WindowMagnificationController$1, reason: invalid class name */
    public final class AnonymousClass1 implements Runnable {
        public final Rect mPreviousBounds = new Rect();

        public AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            IMagnificationConnectionCallback iMagnificationConnectionCallback;
            if (WindowMagnificationController.this.mMirrorView != null) {
                if (this.mPreviousBounds.width() != WindowMagnificationController.this.mMirrorViewBounds.width() || this.mPreviousBounds.height() != WindowMagnificationController.this.mMirrorViewBounds.height()) {
                    WindowMagnificationController.this.mMirrorView.setSystemGestureExclusionRects(Collections.singletonList(new Rect(0, 0, WindowMagnificationController.this.mMirrorViewBounds.width(), WindowMagnificationController.this.mMirrorViewBounds.height())));
                    this.mPreviousBounds.set(WindowMagnificationController.this.mMirrorViewBounds);
                }
                WindowMagnificationController.this.updateSysUIState(false);
                WindowMagnificationController windowMagnificationController = WindowMagnificationController.this;
                MagnificationImpl.AnonymousClass3 anonymousClass3 = windowMagnificationController.mWindowMagnifierCallback;
                int i = windowMagnificationController.mDisplayId;
                Rect rect = windowMagnificationController.mMirrorViewBounds;
                MagnificationConnectionImpl magnificationConnectionImpl = MagnificationImpl.this.mMagnificationConnectionImpl;
                if (magnificationConnectionImpl == null || (iMagnificationConnectionCallback = magnificationConnectionImpl.mConnectionCallback) == null) {
                    return;
                }
                try {
                    iMagnificationConnectionCallback.onWindowMagnifierBoundsChanged(i, rect);
                } catch (RemoteException e) {
                    Log.e("WindowMagnificationConnectionImpl", "Failed to inform bounds changed", e);
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MirrorWindowA11yDelegate extends View.AccessibilityDelegate {
        public MirrorWindowA11yDelegate() {
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            int id = AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK.getId();
            WindowMagnificationController windowMagnificationController = WindowMagnificationController.this;
            accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(id, windowMagnificationController.mEditSizeEnable ? windowMagnificationController.mContext.getResources().getString(R.string.magnification_exit_edit_mode_click_label) : windowMagnificationController.mSettingsPanelVisibility ? windowMagnificationController.mContext.getResources().getString(R.string.magnification_close_settings_click_label) : windowMagnificationController.mContext.getResources().getString(R.string.magnification_open_settings_click_label)));
            accessibilityNodeInfo.setClickable(true);
            accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_zoom_in, WindowMagnificationController.this.mContext.getString(R.string.accessibility_control_zoom_in)));
            accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_zoom_out, WindowMagnificationController.this.mContext.getString(R.string.accessibility_control_zoom_out)));
            WindowMagnificationController windowMagnificationController2 = WindowMagnificationController.this;
            if (windowMagnificationController2.mEditSizeEnable) {
                int width = windowMagnificationController2.mMagnificationFrame.width();
                WindowMagnificationController windowMagnificationController3 = WindowMagnificationController.this;
                if ((windowMagnificationController3.mMirrorSurfaceMargin * 2) + width < windowMagnificationController3.mWindowBounds.width()) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_increase_window_width, WindowMagnificationController.this.mContext.getString(R.string.accessibility_control_increase_window_width)));
                }
                int height = WindowMagnificationController.this.mMagnificationFrame.height();
                WindowMagnificationController windowMagnificationController4 = WindowMagnificationController.this;
                if ((windowMagnificationController4.mMirrorSurfaceMargin * 2) + height < windowMagnificationController4.mWindowBounds.height()) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_increase_window_height, WindowMagnificationController.this.mContext.getString(R.string.accessibility_control_increase_window_height)));
                }
                int width2 = WindowMagnificationController.this.mMagnificationFrame.width();
                WindowMagnificationController windowMagnificationController5 = WindowMagnificationController.this;
                if ((windowMagnificationController5.mMirrorSurfaceMargin * 2) + width2 > windowMagnificationController5.mMinWindowSize) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_decrease_window_width, WindowMagnificationController.this.mContext.getString(R.string.accessibility_control_decrease_window_width)));
                }
                int height2 = WindowMagnificationController.this.mMagnificationFrame.height();
                WindowMagnificationController windowMagnificationController6 = WindowMagnificationController.this;
                if ((windowMagnificationController6.mMirrorSurfaceMargin * 2) + height2 > windowMagnificationController6.mMinWindowSize) {
                    accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_decrease_window_height, WindowMagnificationController.this.mContext.getString(R.string.accessibility_control_decrease_window_height)));
                }
            } else {
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_move_up, WindowMagnificationController.this.mContext.getString(R.string.accessibility_control_move_up)));
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_move_down, WindowMagnificationController.this.mContext.getString(R.string.accessibility_control_move_down)));
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_move_left, WindowMagnificationController.this.mContext.getString(R.string.accessibility_control_move_left)));
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_move_right, WindowMagnificationController.this.mContext.getString(R.string.accessibility_control_move_right)));
            }
            accessibilityNodeInfo.setContentDescription(WindowMagnificationController.this.mContext.getString(R.string.magnification_window_title));
            WindowMagnificationController windowMagnificationController7 = WindowMagnificationController.this;
            accessibilityNodeInfo.setStateDescription(windowMagnificationController7.formatStateDescription(windowMagnificationController7.isActivated() ? windowMagnificationController7.mScale : Float.NaN));
        }

        @Override // android.view.View.AccessibilityDelegate
        public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            IMagnificationConnectionCallback iMagnificationConnectionCallback;
            float fraction = WindowMagnificationController.this.mContext.getResources().getFraction(R.fraction.magnification_resize_window_size_amount, 1, 1);
            if (i == AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK.getId()) {
                WindowMagnificationController windowMagnificationController = WindowMagnificationController.this;
                if (windowMagnificationController.mEditSizeEnable) {
                    windowMagnificationController.setEditMagnifierSizeMode(false);
                } else {
                    windowMagnificationController.handleSingleTap(windowMagnificationController.mDragView);
                }
            } else if (i == R.id.accessibility_action_zoom_in) {
                performScale(WindowMagnificationController.this.mScale + 1.0f);
            } else if (i == R.id.accessibility_action_zoom_out) {
                performScale(WindowMagnificationController.this.mScale - 1.0f);
            } else if (i == R.id.accessibility_action_move_up) {
                WindowMagnificationController windowMagnificationController2 = WindowMagnificationController.this;
                windowMagnificationController2.move(0, -windowMagnificationController2.mSourceBounds.height());
            } else if (i == R.id.accessibility_action_move_down) {
                WindowMagnificationController windowMagnificationController3 = WindowMagnificationController.this;
                windowMagnificationController3.move(0, windowMagnificationController3.mSourceBounds.height());
            } else if (i == R.id.accessibility_action_move_left) {
                WindowMagnificationController windowMagnificationController4 = WindowMagnificationController.this;
                windowMagnificationController4.move(-windowMagnificationController4.mSourceBounds.width(), 0);
            } else if (i == R.id.accessibility_action_move_right) {
                WindowMagnificationController windowMagnificationController5 = WindowMagnificationController.this;
                windowMagnificationController5.move(windowMagnificationController5.mSourceBounds.width(), 0);
            } else if (i == R.id.accessibility_action_increase_window_width) {
                WindowMagnificationController windowMagnificationController6 = WindowMagnificationController.this;
                WindowMagnificationController.m778$$Nest$msetMagnificationFrameSize(windowMagnificationController6, (int) ((fraction + 1.0f) * WindowMagnificationController.this.mMagnificationFrame.width()), windowMagnificationController6.mMagnificationFrame.height());
            } else if (i == R.id.accessibility_action_increase_window_height) {
                WindowMagnificationController windowMagnificationController7 = WindowMagnificationController.this;
                WindowMagnificationController.m778$$Nest$msetMagnificationFrameSize(windowMagnificationController7, windowMagnificationController7.mMagnificationFrame.width(), (int) ((fraction + 1.0f) * WindowMagnificationController.this.mMagnificationFrame.height()));
            } else if (i == R.id.accessibility_action_decrease_window_width) {
                WindowMagnificationController windowMagnificationController8 = WindowMagnificationController.this;
                WindowMagnificationController.m778$$Nest$msetMagnificationFrameSize(windowMagnificationController8, (int) ((1.0f - fraction) * WindowMagnificationController.this.mMagnificationFrame.width()), windowMagnificationController8.mMagnificationFrame.height());
            } else {
                if (i != R.id.accessibility_action_decrease_window_height) {
                    return super.performAccessibilityAction(view, i, bundle);
                }
                WindowMagnificationController windowMagnificationController9 = WindowMagnificationController.this;
                WindowMagnificationController.m778$$Nest$msetMagnificationFrameSize(windowMagnificationController9, windowMagnificationController9.mMagnificationFrame.width(), (int) ((1.0f - fraction) * WindowMagnificationController.this.mMagnificationFrame.height()));
            }
            WindowMagnificationController windowMagnificationController10 = WindowMagnificationController.this;
            MagnificationImpl.AnonymousClass3 anonymousClass3 = windowMagnificationController10.mWindowMagnifierCallback;
            int i2 = windowMagnificationController10.mDisplayId;
            MagnificationConnectionImpl magnificationConnectionImpl = MagnificationImpl.this.mMagnificationConnectionImpl;
            if (magnificationConnectionImpl != null && (iMagnificationConnectionCallback = magnificationConnectionImpl.mConnectionCallback) != null) {
                try {
                    iMagnificationConnectionCallback.onAccessibilityActionPerformed(i2);
                } catch (RemoteException e) {
                    Log.e("WindowMagnificationConnectionImpl", "Failed to inform an accessibility action is already performed", e);
                }
            }
            return true;
        }

        public final void performScale(float f) {
            IMagnificationConnectionCallback iMagnificationConnectionCallback;
            float floatValue = ((Float) WindowMagnificationController.A11Y_ACTION_SCALE_RANGE.clamp(Float.valueOf(f))).floatValue();
            WindowMagnificationController windowMagnificationController = WindowMagnificationController.this;
            MagnificationImpl.AnonymousClass3 anonymousClass3 = windowMagnificationController.mWindowMagnifierCallback;
            int i = windowMagnificationController.mDisplayId;
            MagnificationConnectionImpl magnificationConnectionImpl = MagnificationImpl.this.mMagnificationConnectionImpl;
            if (magnificationConnectionImpl == null || (iMagnificationConnectionCallback = magnificationConnectionImpl.mConnectionCallback) == null) {
                return;
            }
            try {
                iMagnificationConnectionCallback.onPerformScaleAction(i, floatValue, true);
            } catch (RemoteException e) {
                Log.e("WindowMagnificationConnectionImpl", "Failed to inform performing scale action", e);
            }
        }
    }

    /* renamed from: -$$Nest$msetMagnificationFrameSize, reason: not valid java name */
    public static void m778$$Nest$msetMagnificationFrameSize(WindowMagnificationController windowMagnificationController, int i, int i2) {
        int i3 = windowMagnificationController.mMirrorSurfaceMargin * 2;
        windowMagnificationController.setWindowSizeAndCenter(Float.NaN, Float.NaN, i + i3, i3 + i2);
    }

    static {
        DEBUG = Log.isLoggable("WindowMagnificationController", 3) || Build.IS_DEBUGGABLE;
        A11Y_ACTION_SCALE_RANGE = new Range(Float.valueOf(1.0f), Float.valueOf(MagnificationConstants.SCALE_MAX_VALUE));
        COLOR_BLACK_ARRAY = new float[]{0.0f, 0.0f, 0.0f};
        HORIZONTAL_LOCK_BASE = Math.tan(Math.toRadians(50.0d));
    }

    /* JADX WARN: Type inference failed for: r4v3, types: [com.android.systemui.accessibility.WindowMagnificationController$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r4v4, types: [com.android.systemui.accessibility.WindowMagnificationController$$ExternalSyntheticLambda3] */
    public WindowMagnificationController(Context context, MagnificationImpl.AnonymousClass1 anonymousClass1, WindowMagnificationAnimationController windowMagnificationAnimationController, SurfaceControl.Transaction transaction, MagnificationImpl.AnonymousClass3 anonymousClass3, SysUiState sysUiState, SecureSettings secureSettings, MagnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda0 magnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda0, SfVsyncFrameCallbackProvider sfVsyncFrameCallbackProvider, MagnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda1 magnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda1, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        SparseArray sparseArray = new SparseArray();
        this.mMagnificationSizeScaleOptions = sparseArray;
        this.mSettingsButtonIndex = 2;
        this.mMagnificationFrame = new Rect();
        this.mTmpRect = new Rect();
        this.mMirrorViewBounds = new Rect();
        this.mSourceBounds = new Rect();
        final int i = 0;
        this.mMagnificationFrameOffsetX = 0;
        this.mMagnificationFrameOffsetY = 0;
        this.mMagnificationFrameBoundary = new Rect();
        this.mSystemGestureTop = -1;
        this.mAllowDiagonalScrolling = false;
        this.mEditSizeEnable = false;
        this.mSettingsPanelVisibility = false;
        this.mContext = context;
        this.mHandler = anonymousClass1;
        this.mAnimationController = windowMagnificationAnimationController;
        final int i2 = 1;
        windowMagnificationAnimationController.mOnAnimationEndRunnable = new WindowMagnificationController$$ExternalSyntheticLambda0(this, 1);
        windowMagnificationAnimationController.mController = this;
        this.mWindowMagnifierCallback = anonymousClass3;
        this.mSysUiState = sysUiState;
        this.mScvhSupplier = magnificationImpl$WindowMagnificationControllerSupplier$$ExternalSyntheticLambda0;
        this.mConfiguration = new Configuration(context.getResources().getConfiguration());
        WindowMagnificationFrameSizePrefs windowMagnificationFrameSizePrefs = new WindowMagnificationFrameSizePrefs();
        windowMagnificationFrameSizePrefs.mContext = context;
        windowMagnificationFrameSizePrefs.mWindowMagnificationSizePreferences = context.getSharedPreferences("window_magnification_preferences", 0);
        this.mWindowMagnificationFrameSizePrefs = windowMagnificationFrameSizePrefs;
        Display display = context.getDisplay();
        this.mDisplayId = context.getDisplayId();
        this.mRotation = display.getRotation();
        WindowManager windowManager = (WindowManager) context.getSystemService(WindowManager.class);
        this.mWm = windowManager;
        Rect rect = new Rect(windowManager.getCurrentWindowMetrics().getBounds());
        this.mWindowBounds = rect;
        Resources resources = context.getResources();
        this.mResources = resources;
        this.mScale = secureSettings.getFloatForUser(resources.getInteger(R.integer.magnification_default_scale), -2, "accessibility_display_magnification_scale");
        this.mAllowDiagonalScrolling = secureSettings.getIntForUser("accessibility_allow_diagonal_scrolling", 1, -2) == 1;
        sparseArray.clear();
        sparseArray.put(1, Float.valueOf(1.4f));
        sparseArray.put(2, Float.valueOf(1.8f));
        sparseArray.put(3, Float.valueOf(2.5f));
        this.mBounceEffectDuration = resources.getInteger(android.R.integer.config_shortAnimTime);
        updateDimensions();
        Size restoreMagnificationWindowFrameSizeIfPossible = restoreMagnificationWindowFrameSizeIfPossible();
        setMagnificationFrame(restoreMagnificationWindowFrameSizeIfPossible.getWidth(), restoreMagnificationWindowFrameSizeIfPossible.getHeight(), rect.width() / 2, rect.height() / 2);
        computeBounceAnimationScale();
        this.mTransaction = transaction;
        this.mGestureDetector = new MagnificationGestureDetector(context, anonymousClass1, this);
        this.mWindowInsetChangeRunnable = new WindowMagnificationController$$ExternalSyntheticLambda0(this, 2);
        this.mMirrorViewRunnable = new AnonymousClass1();
        this.mMirrorSurfaceViewLayoutChangeListener = new View.OnLayoutChangeListener(this) { // from class: com.android.systemui.accessibility.WindowMagnificationController$$ExternalSyntheticLambda3
            public final /* synthetic */ WindowMagnificationController f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                int i11 = i;
                WindowMagnificationController windowMagnificationController = this.f$0;
                switch (i11) {
                    case 0:
                        windowMagnificationController.mMirrorView.post(new WindowMagnificationController$$ExternalSyntheticLambda0(windowMagnificationController, 0));
                        break;
                    default:
                        if (!windowMagnificationController.mHandler.hasCallbacks(windowMagnificationController.mMirrorViewRunnable)) {
                            windowMagnificationController.mHandler.post(windowMagnificationController.mMirrorViewRunnable);
                            break;
                        }
                        break;
                }
            }
        };
        this.mMirrorViewLayoutChangeListener = new View.OnLayoutChangeListener(this) { // from class: com.android.systemui.accessibility.WindowMagnificationController$$ExternalSyntheticLambda3
            public final /* synthetic */ WindowMagnificationController f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                int i11 = i2;
                WindowMagnificationController windowMagnificationController = this.f$0;
                switch (i11) {
                    case 0:
                        windowMagnificationController.mMirrorView.post(new WindowMagnificationController$$ExternalSyntheticLambda0(windowMagnificationController, 0));
                        break;
                    default:
                        if (!windowMagnificationController.mHandler.hasCallbacks(windowMagnificationController.mMirrorViewRunnable)) {
                            windowMagnificationController.mHandler.post(windowMagnificationController.mMirrorViewRunnable);
                            break;
                        }
                        break;
                }
            }
        };
        this.mUpdateStateDescriptionRunnable = new WindowMagnificationController$$ExternalSyntheticLambda0(this, 3);
    }

    public final void applyResourcesValues() {
        this.mMirrorBorderView.setBackground(this.mResources.getDrawable(this.mEditSizeEnable ? R.drawable.accessibility_window_magnification_background_change : R.drawable.accessibility_window_magnification_background));
        this.mMirrorSurfaceView.setCornerRadius(TypedValue.applyDimension(1, this.mEditSizeEnable ? 16.0f : 28.0f, this.mContext.getResources().getDisplayMetrics()));
        if (this.mEditSizeEnable) {
            this.mDragView.setVisibility(8);
            this.mCloseView.setVisibility(0);
            this.mTopRightCornerView.setVisibility(0);
            this.mTopLeftCornerView.setVisibility(0);
            this.mBottomRightCornerView.setVisibility(0);
            this.mBottomLeftCornerView.setVisibility(0);
            return;
        }
        this.mDragView.setVisibility(0);
        this.mCloseView.setVisibility(8);
        this.mTopRightCornerView.setVisibility(8);
        this.mTopLeftCornerView.setVisibility(8);
        this.mBottomRightCornerView.setVisibility(8);
        this.mBottomLeftCornerView.setVisibility(8);
    }

    public final void applyTapExcludeRegion() {
        if (this.mMirrorView == null) {
            return;
        }
        AttachedSurfaceControl rootSurfaceControl = this.mSurfaceControlViewHost.getRootSurfaceControl();
        Region region = new Region(0, 0, this.mMirrorView.getWidth(), this.mMirrorView.getHeight());
        int i = this.mBorderDragSize;
        region.op(new Region(i, i, this.mMirrorView.getWidth() - this.mBorderDragSize, this.mMirrorView.getHeight() - this.mBorderDragSize), Region.Op.DIFFERENCE);
        Rect rect = new Rect();
        this.mDragView.getHitRect(rect);
        Rect rect2 = new Rect();
        this.mTopLeftCornerView.getHitRect(rect2);
        Rect rect3 = new Rect();
        this.mTopRightCornerView.getHitRect(rect3);
        Rect rect4 = new Rect();
        this.mBottomLeftCornerView.getHitRect(rect4);
        Rect rect5 = new Rect();
        this.mBottomRightCornerView.getHitRect(rect5);
        Rect rect6 = new Rect();
        this.mCloseView.getHitRect(rect6);
        Region.Op op = Region.Op.UNION;
        region.op(rect, op);
        region.op(rect2, op);
        region.op(rect3, op);
        region.op(rect4, op);
        region.op(rect5, op);
        region.op(rect6, op);
        rootSurfaceControl.setTouchableRegion(region);
    }

    public final void calculateMagnificationFrameBoundary() {
        int width = this.mMagnificationFrame.width() / 2;
        int height = this.mMagnificationFrame.height() / 2;
        float f = this.mScale;
        int i = width - ((int) (width / f));
        int i2 = height - ((int) (height / f));
        this.mMagnificationFrameBoundary.set(-Math.max(i - this.mMagnificationFrameOffsetX, 0), -Math.max(i2 - this.mMagnificationFrameOffsetY, 0), this.mWindowBounds.width() + Math.max(i + this.mMagnificationFrameOffsetX, 0), this.mWindowBounds.height() + Math.max(i2 + this.mMagnificationFrameOffsetY, 0));
    }

    public final void changeMagnificationFrameSize(float f, float f2, float f3, float f4) {
        Configuration configuration = this.mContext.getResources().getConfiguration();
        boolean z = false;
        if (configuration != null && (configuration.screenLayout & 192) == 128) {
            z = true;
        }
        int min = Math.min(this.mWindowBounds.width(), this.mWindowBounds.height()) / 3;
        int height = this.mWindowBounds.height() - (this.mMirrorSurfaceMargin * 2);
        int width = this.mWindowBounds.width() - (this.mMirrorSurfaceMargin * 2);
        Rect rect = new Rect();
        rect.set(this.mMagnificationFrame);
        if (z) {
            rect.left += (int) f3;
            rect.right += (int) f;
        } else {
            rect.right += (int) f3;
            rect.left += (int) f;
        }
        rect.top += (int) f2;
        rect.bottom += (int) f4;
        if (rect.width() < min || rect.height() < min || rect.width() > width || rect.height() > height) {
            return;
        }
        this.mMagnificationFrame.set(rect);
        computeBounceAnimationScale();
        calculateMagnificationFrameBoundary();
        modifyWindowMagnification(true);
    }

    public final void computeBounceAnimationScale() {
        float width = (this.mMirrorSurfaceMargin * 2) + this.mMagnificationFrame.width();
        this.mBounceEffectAnimationScale = Math.min(width / (width - (this.mOuterBorderSize * 2)), 1.05f);
    }

    public final void deleteWindowMagnification$1() {
        IMagnificationConnectionCallback iMagnificationConnectionCallback;
        if (isActivated()) {
            SurfaceControl surfaceControl = this.mMirrorSurface;
            if (surfaceControl != null) {
                this.mTransaction.remove(surfaceControl).apply();
                this.mMirrorSurface = null;
            }
            SurfaceView surfaceView = this.mMirrorSurfaceView;
            if (surfaceView != null) {
                surfaceView.removeOnLayoutChangeListener(this.mMirrorSurfaceViewLayoutChangeListener);
            }
            if (this.mMirrorView != null) {
                removeCallbacks(this.mMirrorViewRunnable);
                this.mMirrorView.removeOnLayoutChangeListener(this.mMirrorViewLayoutChangeListener);
                this.mMirrorView = null;
            }
            SurfaceControlViewHost surfaceControlViewHost = this.mSurfaceControlViewHost;
            if (surfaceControlViewHost != null) {
                surfaceControlViewHost.release();
                this.mSurfaceControlViewHost = null;
            }
            this.mMirrorViewBounds.setEmpty();
            this.mSourceBounds.setEmpty();
            updateSysUIState(false);
            setEditMagnifierSizeMode(false);
            this.mContext.unregisterComponentCallbacks(this);
            MagnificationImpl.AnonymousClass3 anonymousClass3 = this.mWindowMagnifierCallback;
            int i = this.mDisplayId;
            Rect rect = new Rect();
            MagnificationConnectionImpl magnificationConnectionImpl = MagnificationImpl.this.mMagnificationConnectionImpl;
            if (magnificationConnectionImpl == null || (iMagnificationConnectionCallback = magnificationConnectionImpl.mConnectionCallback) == null) {
                return;
            }
            try {
                iMagnificationConnectionCallback.onSourceBoundsChanged(i, rect);
            } catch (RemoteException e) {
                Log.e("WindowMagnificationConnectionImpl", "Failed to inform source bounds changed", e);
            }
        }
    }

    public final CharSequence formatStateDescription(float f) {
        Locale locale = this.mContext.getResources().getConfiguration().getLocales().get(0);
        if (!locale.equals(this.mLocale)) {
            this.mLocale = locale;
            this.mPercentFormat = NumberFormat.getPercentInstance(locale);
        }
        return this.mPercentFormat.format(f);
    }

    public final int getMagnificationWindowSizeFromIndex(int i) {
        int min = (int) ((Math.min(this.mWindowBounds.width(), this.mWindowBounds.height()) / 3) * ((Float) this.mMagnificationSizeScaleOptions.get(i, Float.valueOf(1.0f))).floatValue());
        return min - (min % 2);
    }

    public final void handleSingleTap(View view) {
        int id = view.getId();
        if (id == R.id.drag_handle) {
            MagnificationImpl.AnonymousClass3 anonymousClass3 = this.mWindowMagnifierCallback;
            MagnificationImpl.this.mHandler.post(new MagnificationImpl$$ExternalSyntheticLambda2(this.mDisplayId, 2, anonymousClass3));
            return;
        }
        if (id == R.id.close_button) {
            setEditMagnifierSizeMode(false);
            return;
        }
        View view2 = this.mMirrorView;
        if (view2 == null) {
            return;
        }
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view2, PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_X, 1.0f, this.mBounceEffectAnimationScale, 1.0f), PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_Y, 1.0f, this.mBounceEffectAnimationScale, 1.0f));
        ofPropertyValuesHolder.setDuration(this.mBounceEffectDuration);
        ofPropertyValuesHolder.start();
    }

    public final boolean isActivated() {
        return this.mMirrorView != null;
    }

    public boolean isDiagonalScrollingEnabled() {
        return this.mAllowDiagonalScrolling;
    }

    public final void maybeRepositionButton() {
        if (this.mMirrorView == null) {
            return;
        }
        float f = this.mWindowBounds.right - this.mButtonRepositionThresholdFromEdge;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mDragView.getLayoutParams();
        int i = ((float) this.mMirrorViewBounds.right) >= f ? 83 : 85;
        if (i != layoutParams.gravity) {
            layoutParams.gravity = i;
            this.mDragView.setLayoutParams(layoutParams);
            this.mDragView.post(new WindowMagnificationController$$ExternalSyntheticLambda0(this, 0));
        }
    }

    public final void modifyWindowMagnification(boolean z) {
        IMagnificationConnectionCallback iMagnificationConnectionCallback;
        if (isActivated() && this.mMirrorSurface != null) {
            Rect rect = this.mMagnificationFrame;
            float f = this.mScale;
            Rect rect2 = this.mTmpRect;
            rect2.set(this.mSourceBounds);
            int width = rect.width() / 2;
            int height = rect.height() / 2;
            int i = width - ((int) (width / f));
            int i2 = height - ((int) (height / f));
            this.mSourceBounds.set(rect.left + i, rect.top + i2, rect.right - i, rect.bottom - i2);
            this.mSourceBounds.offset(-this.mMagnificationFrameOffsetX, -this.mMagnificationFrameOffsetY);
            Rect rect3 = this.mSourceBounds;
            if (rect3.left < 0) {
                rect3.offsetTo(0, rect3.top);
            } else if (rect3.right > this.mWindowBounds.width()) {
                this.mSourceBounds.offsetTo(this.mWindowBounds.width() - this.mSourceBounds.width(), this.mSourceBounds.top);
            }
            Rect rect4 = this.mSourceBounds;
            if (rect4.top < 0) {
                rect4.offsetTo(rect4.left, 0);
            } else if (rect4.bottom > this.mWindowBounds.height()) {
                Rect rect5 = this.mSourceBounds;
                rect5.offsetTo(rect5.left, this.mWindowBounds.height() - this.mSourceBounds.height());
            }
            if (!this.mSourceBounds.equals(rect2)) {
                this.mTmpRect.set(0, 0, this.mMagnificationFrame.width(), this.mMagnificationFrame.height());
                this.mTransaction.setGeometry(this.mMirrorSurface, this.mSourceBounds, this.mTmpRect, 0);
                if (!this.mAnimationController.mValueAnimator.isRunning()) {
                    MagnificationImpl.AnonymousClass3 anonymousClass3 = this.mWindowMagnifierCallback;
                    int i3 = this.mDisplayId;
                    Rect rect6 = this.mSourceBounds;
                    MagnificationConnectionImpl magnificationConnectionImpl = MagnificationImpl.this.mMagnificationConnectionImpl;
                    if (magnificationConnectionImpl != null && (iMagnificationConnectionCallback = magnificationConnectionImpl.mConnectionCallback) != null) {
                        try {
                            iMagnificationConnectionCallback.onSourceBoundsChanged(i3, rect6);
                        } catch (RemoteException e) {
                            Log.e("WindowMagnificationConnectionImpl", "Failed to inform source bounds changed", e);
                        }
                    }
                }
            }
        }
        if (isActivated()) {
            int width2 = (this.mMirrorSurfaceMargin * 2) + this.mMagnificationFrame.width();
            int height2 = this.mMagnificationFrame.height();
            int i4 = this.mMirrorSurfaceMargin;
            int i5 = (i4 * 2) + height2;
            int i6 = this.mOuterBorderSize;
            int clamp = MathUtils.clamp(this.mMagnificationFrame.left - i4, -i6, (this.mWindowBounds.right - width2) + i6);
            int i7 = this.mOuterBorderSize;
            int clamp2 = MathUtils.clamp(this.mMagnificationFrame.top - this.mMirrorSurfaceMargin, -i7, (this.mWindowBounds.bottom - i5) + i7);
            if (z) {
                WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.mMirrorView.getLayoutParams();
                layoutParams.width = width2;
                layoutParams.height = i5;
                this.mSurfaceControlViewHost.relayout(layoutParams);
                this.mTransaction.setCrop(this.mSurfaceControlViewHost.getSurfacePackage().getSurfaceControl(), new Rect(0, 0, width2, i5));
            }
            this.mMirrorViewBounds.set(clamp, clamp2, width2 + clamp, i5 + clamp2);
            this.mTransaction.setPosition(this.mSurfaceControlViewHost.getSurfacePackage().getSurfaceControl(), clamp, clamp2);
            if (z) {
                this.mSurfaceControlViewHost.getRootSurfaceControl().applyTransactionOnDraw(this.mTransaction);
            } else {
                this.mTransaction.apply();
            }
            if (!this.mIsDragging) {
                this.mMirrorView.post(new WindowMagnificationController$$ExternalSyntheticLambda0(this, 4));
            }
            this.mMirrorViewRunnable.run();
        }
    }

    public final void move(int i, int i2) {
        IMagnificationConnectionCallback iMagnificationConnectionCallback;
        moveWindowMagnifier(i, i2);
        MagnificationImpl.AnonymousClass3 anonymousClass3 = this.mWindowMagnifierCallback;
        int i3 = this.mDisplayId;
        MagnificationConnectionImpl magnificationConnectionImpl = MagnificationImpl.this.mMagnificationConnectionImpl;
        if (magnificationConnectionImpl == null || (iMagnificationConnectionCallback = magnificationConnectionImpl.mConnectionCallback) == null) {
            return;
        }
        try {
            iMagnificationConnectionCallback.onMove(i3);
        } catch (RemoteException e) {
            Log.e("WindowMagnificationConnectionImpl", "Failed to inform taking control by a user", e);
        }
    }

    public final void moveWindowMagnifier(float f, float f2) {
        if (this.mAnimationController.mValueAnimator.isRunning() || this.mMirrorSurfaceView == null) {
            return;
        }
        if (!this.mAllowDiagonalScrolling) {
            if (Math.abs(f2) / Math.abs(f) <= HORIZONTAL_LOCK_BASE) {
                f2 = 0.0f;
            } else {
                f = 0.0f;
            }
        }
        if (updateMagnificationFramePosition((int) f, (int) f2)) {
            modifyWindowMagnification(false);
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        boolean z;
        int diff = configuration.diff(this.mConfiguration);
        this.mConfiguration.setTo(configuration);
        boolean z2 = DEBUG;
        if (z2) {
            Log.d("WindowMagnificationController", "onConfigurationChanged = " + Configuration.configurationDiffToString(diff));
        }
        if (diff == 0) {
            return;
        }
        if ((diff & 128) != 0) {
            Display display = this.mContext.getDisplay();
            int i = this.mRotation;
            int rotation = display.getRotation();
            this.mRotation = rotation;
            int i2 = (((i - rotation) + 4) % 4) * 90;
            if (i2 == 0 || i2 == 180) {
                Log.w("WindowMagnificationController", "onRotate -- rotate with the device. skip it");
            } else {
                Rect rect = new Rect(this.mWm.getCurrentWindowMetrics().getBounds());
                if (rect.width() == this.mWindowBounds.height() && rect.height() == this.mWindowBounds.width()) {
                    this.mWindowBounds.set(rect);
                    Matrix matrix = new Matrix();
                    matrix.setRotate(i2);
                    if (i2 == 90) {
                        matrix.postTranslate(this.mWindowBounds.width(), 0.0f);
                    } else if (i2 == 270) {
                        matrix.postTranslate(0.0f, this.mWindowBounds.height());
                    }
                    RectF rectF = new RectF(this.mMagnificationFrame);
                    float f = -this.mMirrorSurfaceMargin;
                    rectF.inset(f, f);
                    matrix.mapRect(rectF);
                    setWindowSizeAndCenter((int) rectF.centerX(), (int) rectF.centerY(), (int) rectF.width(), (int) rectF.height());
                } else {
                    Log.w("WindowMagnificationController", "onRotate -- unexpected window height/width");
                }
            }
        }
        if ((diff & 4) != 0 && isActivated()) {
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.mMirrorView.getLayoutParams();
            layoutParams.accessibilityTitle = this.mResources.getString(android.R.string.android_upgrading_complete);
            this.mSurfaceControlViewHost.relayout(layoutParams);
        }
        boolean z3 = true;
        if ((diff & 4096) != 0) {
            updateDimensions();
            computeBounceAnimationScale();
            z = true;
        } else {
            z = false;
        }
        if ((diff & 1024) != 0) {
            Rect rect2 = new Rect(this.mWindowBounds);
            Rect bounds = this.mWm.getCurrentWindowMetrics().getBounds();
            if (bounds.equals(rect2)) {
                if (z2) {
                    Log.d("WindowMagnificationController", "handleScreenSizeChanged -- window bounds is not changed");
                }
                z3 = false;
            } else {
                this.mWindowBounds.set(bounds);
                Size restoreMagnificationWindowFrameSizeIfPossible = restoreMagnificationWindowFrameSizeIfPossible();
                setMagnificationFrame(restoreMagnificationWindowFrameSizeIfPossible.getWidth(), restoreMagnificationWindowFrameSizeIfPossible.getHeight(), (int) (((isActivated() ? this.mMagnificationFrame.exactCenterX() : Float.NaN) * this.mWindowBounds.width()) / rect2.width()), (int) (((isActivated() ? this.mMagnificationFrame.exactCenterY() : Float.NaN) * this.mWindowBounds.height()) / rect2.height()));
                calculateMagnificationFrameBoundary();
            }
            z |= z3;
        }
        if (isActivated() && z) {
            deleteWindowMagnification$1();
            updateWindowMagnificationInternal(Float.NaN);
        }
    }

    @Override // com.android.systemui.accessibility.MagnificationGestureDetector.OnGestureListener
    public final boolean onDrag(View view, float f, float f2) {
        if (!this.mEditSizeEnable) {
            move((int) f, (int) f2);
            return true;
        }
        if (view == this.mLeftDrag) {
            changeMagnificationFrameSize(f, 0.0f, 0.0f, 0.0f);
            return true;
        }
        if (view == this.mRightDrag) {
            changeMagnificationFrameSize(0.0f, 0.0f, f, 0.0f);
            return true;
        }
        if (view == this.mTopDrag) {
            changeMagnificationFrameSize(0.0f, f2, 0.0f, 0.0f);
            return true;
        }
        if (view == this.mBottomDrag) {
            changeMagnificationFrameSize(0.0f, 0.0f, 0.0f, f2);
            return true;
        }
        if (view == this.mTopLeftCornerView) {
            changeMagnificationFrameSize(f, f2, 0.0f, 0.0f);
            return true;
        }
        if (view == this.mTopRightCornerView) {
            changeMagnificationFrameSize(0.0f, f2, f, 0.0f);
            return true;
        }
        if (view == this.mBottomLeftCornerView) {
            changeMagnificationFrameSize(f, 0.0f, 0.0f, f2);
            return true;
        }
        if (view != this.mBottomRightCornerView) {
            return false;
        }
        changeMagnificationFrameSize(0.0f, 0.0f, f, f2);
        return true;
    }

    @Override // com.android.systemui.accessibility.MagnificationGestureDetector.OnGestureListener
    public final boolean onFinish() {
        maybeRepositionButton();
        this.mIsDragging = false;
        return false;
    }

    @Override // com.android.systemui.accessibility.MagnificationGestureDetector.OnGestureListener
    public final void onSingleTap(View view) {
        handleSingleTap(view);
    }

    @Override // com.android.systemui.accessibility.MagnificationGestureDetector.OnGestureListener
    public final void onStart() {
        this.mIsDragging = true;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (view == this.mDragView || view == this.mLeftDrag || view == this.mTopDrag || view == this.mRightDrag || view == this.mBottomDrag || view == this.mTopLeftCornerView || view == this.mTopRightCornerView || view == this.mBottomLeftCornerView || view == this.mBottomRightCornerView || view == this.mCloseView) {
            return this.mGestureDetector.onTouch(view, motionEvent);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.util.Size restoreMagnificationWindowFrameSizeIfPossible() {
        /*
            r8 = this;
            com.android.systemui.accessibility.WindowMagnificationFrameSizePrefs r0 = r8.mWindowMagnificationFrameSizePrefs
            android.content.SharedPreferences r1 = r0.mWindowMagnificationSizePreferences
            java.lang.String r0 = r0.getKey()
            boolean r0 = r1.contains(r0)
            r1 = 2
            if (r0 != 0) goto L36
            r8.mSettingsButtonIndex = r1
            boolean r0 = r8.isActivated()
            if (r0 == 0) goto L28
            com.android.systemui.accessibility.MagnificationImpl$3 r0 = r8.mWindowMagnifierCallback
            int r2 = r8.mDisplayId
            com.android.systemui.accessibility.MagnificationImpl r3 = com.android.systemui.accessibility.MagnificationImpl.this
            com.android.systemui.accessibility.MagnificationImpl$4$$ExternalSyntheticLambda2 r4 = new com.android.systemui.accessibility.MagnificationImpl$4$$ExternalSyntheticLambda2
            r5 = 2
            r4.<init>(r0, r2, r1, r5)
            com.android.systemui.accessibility.MagnificationImpl$1 r0 = r3.mHandler
            r0.post(r4)
        L28:
            int r0 = r8.getMagnificationWindowSizeFromIndex(r1)
            int r8 = r8.mMirrorSurfaceMargin
            int r8 = r8 * r1
            int r0 = r0 - r8
            android.util.Size r8 = new android.util.Size
            r8.<init>(r0, r0)
            goto L88
        L36:
            com.android.systemui.accessibility.WindowMagnificationFrameSizePrefs r0 = r8.mWindowMagnificationFrameSizePrefs
            android.content.SharedPreferences r2 = r0.mWindowMagnificationSizePreferences
            java.lang.String r0 = r0.getKey()
            r3 = 0
            java.lang.String r0 = r2.getString(r0, r3)
            if (r0 != 0) goto L47
        L45:
            r0 = r1
            goto L4d
        L47:
            com.android.systemui.accessibility.WindowMagnificationFrameSpec r0 = com.android.systemui.accessibility.WindowMagnificationFrameSpec.deserialize(r0)     // Catch: java.lang.NumberFormatException -> L45
            int r0 = r0.index     // Catch: java.lang.NumberFormatException -> L45
        L4d:
            r8.mSettingsButtonIndex = r0
            boolean r2 = r8.isActivated()
            if (r2 == 0) goto L66
            com.android.systemui.accessibility.MagnificationImpl$3 r2 = r8.mWindowMagnifierCallback
            int r4 = r8.mDisplayId
            com.android.systemui.accessibility.MagnificationImpl r5 = com.android.systemui.accessibility.MagnificationImpl.this
            com.android.systemui.accessibility.MagnificationImpl$4$$ExternalSyntheticLambda2 r6 = new com.android.systemui.accessibility.MagnificationImpl$4$$ExternalSyntheticLambda2
            r7 = 2
            r6.<init>(r2, r4, r0, r7)
            com.android.systemui.accessibility.MagnificationImpl$1 r2 = r5.mHandler
            r2.post(r6)
        L66:
            if (r0 != 0) goto L7b
            com.android.systemui.accessibility.WindowMagnificationFrameSizePrefs r8 = r8.mWindowMagnificationFrameSizePrefs
            android.content.SharedPreferences r0 = r8.mWindowMagnificationSizePreferences
            java.lang.String r8 = r8.getKey()
            java.lang.String r8 = r0.getString(r8, r3)
            com.android.systemui.accessibility.WindowMagnificationFrameSpec r8 = com.android.systemui.accessibility.WindowMagnificationFrameSpec.deserialize(r8)
            android.util.Size r8 = r8.size
            goto L88
        L7b:
            int r0 = r8.getMagnificationWindowSizeFromIndex(r0)
            int r8 = r8.mMirrorSurfaceMargin
            int r8 = r8 * r1
            int r0 = r0 - r8
            android.util.Size r8 = new android.util.Size
            r8.<init>(r0, r0)
        L88:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.accessibility.WindowMagnificationController.restoreMagnificationWindowFrameSizeIfPossible():android.util.Size");
    }

    public final void setEditMagnifierSizeMode(boolean z) {
        this.mEditSizeEnable = z;
        applyResourcesValues();
        if (isActivated()) {
            updateDimensions();
            applyTapExcludeRegion();
        }
        if (z) {
            this.mSettingsButtonIndex = 0;
        } else {
            this.mWindowMagnificationFrameSizePrefs.saveIndexAndSizeForCurrentDensity(this.mSettingsButtonIndex, new Size(this.mMagnificationFrame.width(), this.mMagnificationFrame.height()));
        }
    }

    public final void setMagnificationFrame(int i, int i2, int i3, int i4) {
        this.mWindowMagnificationFrameSizePrefs.saveIndexAndSizeForCurrentDensity(this.mSettingsButtonIndex, new Size(i, i2));
        int i5 = i3 - (i / 2);
        int i6 = i4 - (i2 / 2);
        this.mMagnificationFrame.set(i5, i6, i + i5, i2 + i6);
    }

    public final void setWindowSizeAndCenter(float f, float f2, int i, int i2) {
        int clamp = MathUtils.clamp(i, this.mMinWindowSize, this.mWindowBounds.width());
        int clamp2 = MathUtils.clamp(i2, this.mMinWindowSize, this.mWindowBounds.height());
        if (Float.isNaN(f)) {
            f = this.mMagnificationFrame.centerX();
        }
        if (Float.isNaN(f2)) {
            f2 = this.mMagnificationFrame.centerY();
        }
        int i3 = this.mMirrorSurfaceMargin;
        setMagnificationFrame(clamp - (i3 * 2), clamp2 - (i3 * 2), (int) f, (int) f2);
        calculateMagnificationFrameBoundary();
        updateMagnificationFramePosition(0, 0);
        modifyWindowMagnification(true);
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        SurfaceControl surfaceControl;
        int i = this.mDisplayId;
        try {
            surfaceControl = new SurfaceControl();
            WindowManagerGlobal.getWindowManagerService().mirrorDisplay(i, surfaceControl);
        } catch (RemoteException e) {
            Log.e("WindowMagnificationController", "Unable to reach window manager", e);
            surfaceControl = null;
        }
        this.mMirrorSurface = surfaceControl;
        if (surfaceControl.isValid()) {
            this.mTransaction.setColor(this.mMirrorSurfaceView.getSurfaceControl(), COLOR_BLACK_ARRAY);
            this.mTransaction.show(this.mMirrorSurface).reparent(this.mMirrorSurface, this.mMirrorSurfaceView.getSurfaceControl());
            modifyWindowMagnification(false);
        }
    }

    public final void updateDimensions() {
        this.mMirrorSurfaceMargin = this.mResources.getDimensionPixelSize(R.dimen.magnification_mirror_surface_margin);
        this.mBorderDragSize = this.mResources.getDimensionPixelSize(R.dimen.magnification_border_drag_size);
        this.mOuterBorderSize = this.mResources.getDimensionPixelSize(R.dimen.magnification_outer_border_margin);
        this.mButtonRepositionThresholdFromEdge = this.mResources.getDimensionPixelSize(R.dimen.magnification_button_reposition_threshold_from_edge);
        this.mMinWindowSize = this.mResources.getDimensionPixelSize(android.R.dimen.action_bar_button_margin);
    }

    public final boolean updateMagnificationFramePosition(int i, int i2) {
        this.mTmpRect.set(this.mMagnificationFrame);
        this.mTmpRect.offset(i, i2);
        Rect rect = this.mTmpRect;
        int i3 = rect.left;
        Rect rect2 = this.mMagnificationFrameBoundary;
        int i4 = rect2.left;
        if (i3 < i4) {
            rect.offsetTo(i4, rect.top);
        } else {
            int i5 = rect.right;
            int i6 = rect2.right;
            if (i5 > i6) {
                int width = i6 - this.mMagnificationFrame.width();
                Rect rect3 = this.mTmpRect;
                rect3.offsetTo(width, rect3.top);
            }
        }
        Rect rect4 = this.mTmpRect;
        int i7 = rect4.top;
        Rect rect5 = this.mMagnificationFrameBoundary;
        int i8 = rect5.top;
        if (i7 < i8) {
            rect4.offsetTo(rect4.left, i8);
        } else {
            int i9 = rect4.bottom;
            int i10 = rect5.bottom;
            if (i9 > i10) {
                int height = i10 - this.mMagnificationFrame.height();
                Rect rect6 = this.mTmpRect;
                rect6.offsetTo(rect6.left, height);
            }
        }
        if (this.mTmpRect.equals(this.mMagnificationFrame)) {
            return false;
        }
        this.mMagnificationFrame.set(this.mTmpRect);
        return true;
    }

    public final void updateSysUIState(boolean z) {
        int i;
        boolean z2 = isActivated() && (i = this.mSystemGestureTop) > 0 && this.mMirrorViewBounds.bottom > i;
        if (z || z2 != this.mOverlapWithGestureInsets) {
            this.mOverlapWithGestureInsets = z2;
            SysUiState sysUiState = this.mSysUiState;
            sysUiState.setFlag(524288L, z2);
            sysUiState.commitUpdate(this.mDisplayId);
        }
    }

    public final void updateWindowMagnificationInternal(float f) {
        updateWindowMagnificationInternal(f, Float.NaN, Float.NaN, Float.NaN, Float.NaN);
    }

    public final void updateWindowMagnificationInternal(float f, float f2, float f3, float f4, float f5) {
        if (Float.compare(f, 1.0f) < 0) {
            deleteWindowMagnification$1();
            return;
        }
        if (!isActivated()) {
            onConfigurationChanged(this.mResources.getConfiguration());
            this.mContext.registerComponentCallbacks(this);
        }
        this.mMagnificationFrameOffsetX = Float.isNaN(f4) ? this.mMagnificationFrameOffsetX : (int) ((this.mMagnificationFrame.width() / 2) * f4);
        int height = Float.isNaN(f5) ? this.mMagnificationFrameOffsetY : (int) ((this.mMagnificationFrame.height() / 2) * f5);
        this.mMagnificationFrameOffsetY = height;
        float f6 = height + f3;
        float exactCenterX = Float.isNaN(f2) ? 0.0f : (this.mMagnificationFrameOffsetX + f2) - this.mMagnificationFrame.exactCenterX();
        float exactCenterY = Float.isNaN(f3) ? 0.0f : f6 - this.mMagnificationFrame.exactCenterY();
        if (Float.isNaN(f)) {
            f = this.mScale;
        }
        this.mScale = f;
        calculateMagnificationFrameBoundary();
        updateMagnificationFramePosition((int) exactCenterX, (int) exactCenterY);
        if (isActivated()) {
            modifyWindowMagnification(false);
            return;
        }
        int width = (this.mMirrorSurfaceMargin * 2) + this.mMagnificationFrame.width();
        int height2 = (this.mMirrorSurfaceMargin * 2) + this.mMagnificationFrame.height();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(width, height2, 2039, 40, -2);
        layoutParams.receiveInsetsIgnoringZOrder = true;
        layoutParams.setTitle(this.mContext.getString(R.string.magnification_window_title));
        layoutParams.accessibilityTitle = this.mResources.getString(android.R.string.android_upgrading_complete);
        layoutParams.setTrustedOverlay();
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.window_magnifier_view, (ViewGroup) null);
        this.mMirrorView = inflate;
        this.mMirrorSurfaceView = (SurfaceView) inflate.findViewById(R.id.surface_view);
        this.mMirrorBorderView = this.mMirrorView.findViewById(R.id.magnification_inner_border);
        this.mMirrorSurfaceView.addOnLayoutChangeListener(this.mMirrorSurfaceViewLayoutChangeListener);
        this.mMirrorView.addOnLayoutChangeListener(this.mMirrorViewLayoutChangeListener);
        this.mMirrorView.setAccessibilityDelegate(new MirrorWindowA11yDelegate());
        this.mMirrorView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.android.systemui.accessibility.WindowMagnificationController$$ExternalSyntheticLambda7
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                WindowMagnificationController windowMagnificationController = WindowMagnificationController.this;
                if (!windowMagnificationController.mHandler.hasCallbacks(windowMagnificationController.mWindowInsetChangeRunnable)) {
                    windowMagnificationController.mHandler.post(windowMagnificationController.mWindowInsetChangeRunnable);
                }
                return view.onApplyWindowInsets(windowInsets);
            }
        });
        SurfaceControlViewHost surfaceControlViewHost = (SurfaceControlViewHost) this.mScvhSupplier.get();
        this.mSurfaceControlViewHost = surfaceControlViewHost;
        surfaceControlViewHost.setView(this.mMirrorView, layoutParams);
        SurfaceControl surfaceControl = this.mSurfaceControlViewHost.getSurfacePackage().getSurfaceControl();
        Rect rect = this.mMagnificationFrame;
        int i = rect.left;
        int i2 = this.mMirrorSurfaceMargin;
        int i3 = i - i2;
        int i4 = rect.top - i2;
        this.mTransaction.setCrop(surfaceControl, new Rect(0, 0, width, height2)).setPosition(surfaceControl, i3, i4).setLayer(surfaceControl, Integer.MAX_VALUE).show(surfaceControl).apply();
        this.mMirrorViewBounds.set(i3, i4, width + i3, height2 + i4);
        ((AccessibilityManager) this.mContext.getSystemService(AccessibilityManager.class)).attachAccessibilityOverlayToDisplay(this.mDisplayId, surfaceControl);
        SurfaceHolder holder = this.mMirrorSurfaceView.getHolder();
        holder.addCallback(this);
        holder.setFormat(1);
        this.mDragView = (ImageView) this.mMirrorView.findViewById(R.id.drag_handle);
        this.mLeftDrag = this.mMirrorView.findViewById(R.id.left_handle);
        this.mTopDrag = this.mMirrorView.findViewById(R.id.top_handle);
        this.mRightDrag = this.mMirrorView.findViewById(R.id.right_handle);
        this.mBottomDrag = this.mMirrorView.findViewById(R.id.bottom_handle);
        this.mCloseView = (ImageView) this.mMirrorView.findViewById(R.id.close_button);
        this.mTopRightCornerView = (ImageView) this.mMirrorView.findViewById(R.id.top_right_corner);
        this.mTopLeftCornerView = (ImageView) this.mMirrorView.findViewById(R.id.top_left_corner);
        this.mBottomRightCornerView = (ImageView) this.mMirrorView.findViewById(R.id.bottom_right_corner);
        this.mBottomLeftCornerView = (ImageView) this.mMirrorView.findViewById(R.id.bottom_left_corner);
        this.mDragView.setOnTouchListener(this);
        this.mLeftDrag.setOnTouchListener(this);
        this.mTopDrag.setOnTouchListener(this);
        this.mRightDrag.setOnTouchListener(this);
        this.mBottomDrag.setOnTouchListener(this);
        this.mCloseView.setOnTouchListener(this);
        this.mTopLeftCornerView.setOnTouchListener(this);
        this.mTopRightCornerView.setOnTouchListener(this);
        this.mBottomLeftCornerView.setOnTouchListener(this);
        this.mBottomRightCornerView.setOnTouchListener(this);
        applyResourcesValues();
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }
}
