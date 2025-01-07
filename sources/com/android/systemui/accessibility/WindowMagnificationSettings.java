package com.android.systemui.accessibility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.MathUtils;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.accessibility.common.MagnificationConstants;
import com.android.internal.graphics.SfVsyncFrameCallbackProvider;
import com.android.systemui.accessibility.AccessibilityLogger;
import com.android.systemui.accessibility.MagnificationGestureDetector;
import com.android.systemui.accessibility.MagnificationImpl;
import com.android.systemui.accessibility.MagnificationSettingsController;
import com.android.systemui.common.ui.view.SeekBarWithIconButtonsView;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class WindowMagnificationSettings implements MagnificationGestureDetector.OnGestureListener {
    public final boolean mAllowDiagonalScrolling;
    public Switch mAllowDiagonalScrollingSwitch;
    public LinearLayout mAllowDiagonalScrollingView;
    public final WindowMagnificationSettingsCallback mCallback;
    public final Context mContext;
    public Button mDoneButton;
    public Button mEditButton;
    public ImageButton mFullScreenButton;
    public final MagnificationGestureDetector mGestureDetector;
    public ImageButton mLargeButton;
    public final AnonymousClass1 mMagnificationCapabilityObserver;
    public ImageButton mMediumButton;
    final WindowManager.LayoutParams mParams;
    public final SecureSettings mSecureSettings;
    public int mSeekBarMagnitude;
    public LinearLayout mSettingView;
    public final SfVsyncFrameCallbackProvider mSfVsyncFrameProvider;
    public ImageButton mSmallButton;
    public final ViewCaptureAwareWindowManager mViewCaptureAwareWindowManager;
    public final WindowMagnificationSettings$$ExternalSyntheticLambda0 mWindowInsetChangeRunnable;
    public final WindowManager mWindowManager;
    public SeekBarWithIconButtonsView mZoomSeekbar;
    final Rect mDraggableWindowBounds = new Rect();
    public boolean mIsVisible = false;
    public boolean mSingleTapDetected = false;
    public int mLastSelectedButtonIndex = 2;
    public float mScale = 1.0f;
    public final AnonymousClass2 mPanelDelegate = new View.AccessibilityDelegate() { // from class: com.android.systemui.accessibility.WindowMagnificationSettings.2
        @Override // android.view.View.AccessibilityDelegate
        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_move_up, WindowMagnificationSettings.this.mContext.getString(R.string.accessibility_control_move_up)));
            accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_move_down, WindowMagnificationSettings.this.mContext.getString(R.string.accessibility_control_move_down)));
            accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_move_left, WindowMagnificationSettings.this.mContext.getString(R.string.accessibility_control_move_left)));
            accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_move_right, WindowMagnificationSettings.this.mContext.getString(R.string.accessibility_control_move_right)));
        }

        @Override // android.view.View.AccessibilityDelegate
        public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            Rect bounds = WindowMagnificationSettings.this.mWindowManager.getCurrentWindowMetrics().getBounds();
            if (i == R.id.accessibility_action_move_up) {
                WindowMagnificationSettings.this.moveButton$1(0.0f, -bounds.height());
                return true;
            }
            if (i == R.id.accessibility_action_move_down) {
                WindowMagnificationSettings.this.moveButton$1(0.0f, bounds.height());
                return true;
            }
            if (i == R.id.accessibility_action_move_left) {
                WindowMagnificationSettings.this.moveButton$1(-bounds.width(), 0.0f);
                return true;
            }
            if (i != R.id.accessibility_action_move_right) {
                return super.performAccessibilityAction(view, i, bundle);
            }
            WindowMagnificationSettings.this.moveButton$1(bounds.width(), 0.0f);
            return true;
        }
    };
    public final AnonymousClass3 mButtonClickListener = new View.OnClickListener() { // from class: com.android.systemui.accessibility.WindowMagnificationSettings.3
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            int id = view.getId();
            if (id == R.id.magnifier_small_button) {
                WindowMagnificationSettings.m779$$Nest$msetMagnifierSize(WindowMagnificationSettings.this, 1);
                return;
            }
            if (id == R.id.magnifier_medium_button) {
                WindowMagnificationSettings.m779$$Nest$msetMagnifierSize(WindowMagnificationSettings.this, 2);
                return;
            }
            if (id == R.id.magnifier_large_button) {
                WindowMagnificationSettings.m779$$Nest$msetMagnifierSize(WindowMagnificationSettings.this, 3);
                return;
            }
            if (id == R.id.magnifier_full_button) {
                WindowMagnificationSettings.m779$$Nest$msetMagnifierSize(WindowMagnificationSettings.this, 4);
                return;
            }
            if (id != R.id.magnifier_edit_button) {
                if (id == R.id.magnifier_done_button) {
                    WindowMagnificationSettings.this.hideSettingPanel(true);
                    return;
                }
                return;
            }
            WindowMagnificationSettings windowMagnificationSettings = WindowMagnificationSettings.this;
            MagnificationSettingsController magnificationSettingsController = MagnificationSettingsController.this;
            MagnificationSettingsController.Callback callback = magnificationSettingsController.mSettingsControllerCallback;
            int i = magnificationSettingsController.mDisplayId;
            MagnificationImpl.AnonymousClass3 anonymousClass3 = (MagnificationImpl.AnonymousClass3) callback;
            MagnificationImpl magnificationImpl = MagnificationImpl.this;
            magnificationImpl.mHandler.post(new MagnificationImpl$$ExternalSyntheticLambda2(i, 1, anonymousClass3));
            magnificationImpl.mA11yLogger.uiEventLogger.log(AccessibilityLogger.MagnificationSettingsEvent.MAGNIFICATION_SETTINGS_SIZE_EDITING_ACTIVATED);
            windowMagnificationSettings.updateSelectedButton(0);
            windowMagnificationSettings.hideSettingPanel(true);
        }
    };
    public final AnonymousClass4 mScreenOffReceiver = new BroadcastReceiver() { // from class: com.android.systemui.accessibility.WindowMagnificationSettings.4
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            WindowMagnificationSettings.this.hideSettingPanel(true);
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.accessibility.WindowMagnificationSettings$1, reason: invalid class name */
    public final class AnonymousClass1 extends ContentObserver {
        public AnonymousClass1(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            WindowMagnificationSettings.this.mSettingView.post(new WindowMagnificationSettings$$ExternalSyntheticLambda0(2, this));
        }
    }

    /* renamed from: -$$Nest$msetMagnifierSize, reason: not valid java name */
    public static void m779$$Nest$msetMagnifierSize(WindowMagnificationSettings windowMagnificationSettings, int i) {
        if (i == 4) {
            MagnificationSettingsController magnificationSettingsController = MagnificationSettingsController.this;
            MagnificationSettingsController.Callback callback = magnificationSettingsController.mSettingsControllerCallback;
            int i2 = magnificationSettingsController.mDisplayId;
            MagnificationImpl.AnonymousClass3 anonymousClass3 = (MagnificationImpl.AnonymousClass3) callback;
            MagnificationImpl magnificationImpl = MagnificationImpl.this;
            magnificationImpl.mHandler.post(new MagnificationImpl$4$$ExternalSyntheticLambda2(anonymousClass3, i2, 1, 0));
        } else {
            if (i == 0) {
                return;
            }
            MagnificationSettingsController magnificationSettingsController2 = MagnificationSettingsController.this;
            MagnificationSettingsController.Callback callback2 = magnificationSettingsController2.mSettingsControllerCallback;
            int i3 = magnificationSettingsController2.mDisplayId;
            MagnificationImpl.AnonymousClass3 anonymousClass32 = (MagnificationImpl.AnonymousClass3) callback2;
            MagnificationImpl magnificationImpl2 = MagnificationImpl.this;
            magnificationImpl2.mHandler.post(new MagnificationImpl$4$$ExternalSyntheticLambda2(anonymousClass32, i3, i, 1));
            magnificationImpl2.mA11yLogger.uiEventLogger.logWithPosition(AccessibilityLogger.MagnificationSettingsEvent.MAGNIFICATION_SETTINGS_WINDOW_SIZE_SELECTED, 0, (String) null, i);
            MagnificationSettingsController magnificationSettingsController3 = MagnificationSettingsController.this;
            MagnificationSettingsController.Callback callback3 = magnificationSettingsController3.mSettingsControllerCallback;
            int i4 = magnificationSettingsController3.mDisplayId;
            MagnificationImpl.AnonymousClass3 anonymousClass33 = (MagnificationImpl.AnonymousClass3) callback3;
            MagnificationImpl magnificationImpl3 = MagnificationImpl.this;
            magnificationImpl3.mHandler.post(new MagnificationImpl$4$$ExternalSyntheticLambda2(anonymousClass33, i4, 2, 0));
        }
        windowMagnificationSettings.updateSelectedButton(i);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.accessibility.WindowMagnificationSettings$2] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.accessibility.WindowMagnificationSettings$3] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.accessibility.WindowMagnificationSettings$4] */
    public WindowMagnificationSettings(Context context, WindowMagnificationSettingsCallback windowMagnificationSettingsCallback, SfVsyncFrameCallbackProvider sfVsyncFrameCallbackProvider, SecureSettings secureSettings, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        this.mAllowDiagonalScrolling = false;
        this.mContext = context;
        this.mWindowManager = (WindowManager) context.getSystemService(WindowManager.class);
        this.mViewCaptureAwareWindowManager = viewCaptureAwareWindowManager;
        this.mSfVsyncFrameProvider = sfVsyncFrameCallbackProvider;
        this.mCallback = windowMagnificationSettingsCallback;
        this.mSecureSettings = secureSettings;
        this.mAllowDiagonalScrolling = secureSettings.getIntForUser("accessibility_allow_diagonal_scrolling", 1, -2) == 1;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(getPanelWidth(context), -2, 2024, 8, -2);
        layoutParams.gravity = 8388659;
        layoutParams.accessibilityTitle = context.getString(android.R.string.android_upgrading_complete);
        layoutParams.layoutInDisplayCutoutMode = 3;
        this.mParams = layoutParams;
        this.mWindowInsetChangeRunnable = new WindowMagnificationSettings$$ExternalSyntheticLambda0(0, this);
        inflateView();
        this.mGestureDetector = new MagnificationGestureDetector(context, context.getMainThreadHandler(), this);
        this.mMagnificationCapabilityObserver = new AnonymousClass1(context.getMainThreadHandler());
    }

    public final Rect getDraggableWindowBounds$1() {
        WindowMetrics currentWindowMetrics = this.mWindowManager.getCurrentWindowMetrics();
        Insets insetsIgnoringVisibility = currentWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout());
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mSettingView.measure(makeMeasureSpec, makeMeasureSpec);
        Rect rect = new Rect(currentWindowMetrics.getBounds());
        rect.offsetTo(0, 0);
        rect.inset(0, 0, this.mSettingView.getMeasuredWidth(), this.mSettingView.getMeasuredHeight());
        rect.inset(insetsIgnoringVisibility);
        return rect;
    }

    public final int getPanelWidth(Context context) {
        return Math.min(this.mWindowManager.getCurrentWindowMetrics().getBounds().width(), (context.getResources().getDimensionPixelSize(R.dimen.magnification_setting_background_padding) * 2) + context.getResources().getDimensionPixelSize(R.dimen.magnification_setting_button_done_width));
    }

    public ViewGroup getSettingView() {
        return this.mSettingView;
    }

    public final void hideSettingPanel(boolean z) {
        if (this.mIsVisible) {
            this.mSecureSettings.unregisterContentObserverSync(this.mMagnificationCapabilityObserver);
            this.mViewCaptureAwareWindowManager.removeView(this.mSettingView);
            boolean z2 = false;
            this.mIsVisible = false;
            if (z) {
                WindowManager.LayoutParams layoutParams = this.mParams;
                layoutParams.x = 0;
                layoutParams.y = 0;
            }
            this.mContext.unregisterReceiver(this.mScreenOffReceiver);
            MagnificationSettingsController magnificationSettingsController = MagnificationSettingsController.this;
            MagnificationSettingsController.Callback callback = magnificationSettingsController.mSettingsControllerCallback;
            int i = magnificationSettingsController.mDisplayId;
            MagnificationImpl.AnonymousClass3 anonymousClass3 = (MagnificationImpl.AnonymousClass3) callback;
            MagnificationImpl magnificationImpl = MagnificationImpl.this;
            magnificationImpl.mHandler.post(new MagnificationImpl$4$$ExternalSyntheticLambda0(anonymousClass3, i, z2, 0));
        }
    }

    public final void inflateView() {
        AnonymousClass3 anonymousClass3 = this.mButtonClickListener;
        LinearLayout linearLayout = (LinearLayout) View.inflate(this.mContext, R.layout.window_magnification_settings_view, null);
        this.mSettingView = linearLayout;
        linearLayout.setFocusable(true);
        this.mSettingView.setFocusableInTouchMode(true);
        this.mSettingView.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.accessibility.WindowMagnificationSettings$$ExternalSyntheticLambda1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                WindowMagnificationSettings windowMagnificationSettings = WindowMagnificationSettings.this;
                if (windowMagnificationSettings.mIsVisible) {
                    return windowMagnificationSettings.mGestureDetector.onTouch(view, motionEvent);
                }
                return false;
            }
        });
        this.mSettingView.setAccessibilityDelegate(this.mPanelDelegate);
        this.mSmallButton = (ImageButton) this.mSettingView.findViewById(R.id.magnifier_small_button);
        this.mMediumButton = (ImageButton) this.mSettingView.findViewById(R.id.magnifier_medium_button);
        this.mLargeButton = (ImageButton) this.mSettingView.findViewById(R.id.magnifier_large_button);
        this.mDoneButton = (Button) this.mSettingView.findViewById(R.id.magnifier_done_button);
        this.mEditButton = (Button) this.mSettingView.findViewById(R.id.magnifier_edit_button);
        this.mFullScreenButton = (ImageButton) this.mSettingView.findViewById(R.id.magnifier_full_button);
        SeekBarWithIconButtonsView seekBarWithIconButtonsView = (SeekBarWithIconButtonsView) this.mSettingView.findViewById(R.id.magnifier_zoom_slider);
        this.mZoomSeekbar = seekBarWithIconButtonsView;
        seekBarWithIconButtonsView.mSeekbar.setMax((int) ((MagnificationConstants.SCALE_MAX_VALUE - 1.0f) * seekBarWithIconButtonsView.mSeekBarChangeMagnitude));
        this.mSeekBarMagnitude = this.mZoomSeekbar.mSeekBarChangeMagnitude;
        setScaleSeekbar(this.mScale);
        this.mZoomSeekbar.mSeekBarListener.mOnSeekBarChangeListener = new ZoomSeekbarChangeListener();
        this.mAllowDiagonalScrollingView = (LinearLayout) this.mSettingView.findViewById(R.id.magnifier_horizontal_lock_view);
        Switch r1 = (Switch) this.mSettingView.findViewById(R.id.magnifier_horizontal_lock_switch);
        this.mAllowDiagonalScrollingSwitch = r1;
        r1.setChecked(this.mAllowDiagonalScrolling);
        this.mAllowDiagonalScrollingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.systemui.accessibility.WindowMagnificationSettings$$ExternalSyntheticLambda2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WindowMagnificationSettings windowMagnificationSettings = WindowMagnificationSettings.this;
                windowMagnificationSettings.setDiagonalScrolling(!(windowMagnificationSettings.mSecureSettings.getIntForUser("accessibility_allow_diagonal_scrolling", 1, -2) == 1));
            }
        });
        this.mSmallButton.setOnClickListener(anonymousClass3);
        this.mMediumButton.setOnClickListener(anonymousClass3);
        this.mLargeButton.setOnClickListener(anonymousClass3);
        this.mDoneButton.setOnClickListener(anonymousClass3);
        this.mFullScreenButton.setOnClickListener(anonymousClass3);
        this.mEditButton.setOnClickListener(anonymousClass3);
        this.mSettingView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.android.systemui.accessibility.WindowMagnificationSettings$$ExternalSyntheticLambda3
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                WindowMagnificationSettings windowMagnificationSettings = WindowMagnificationSettings.this;
                if (windowMagnificationSettings.mSettingView.isAttachedToWindow()) {
                    Handler handler = windowMagnificationSettings.mSettingView.getHandler();
                    WindowMagnificationSettings$$ExternalSyntheticLambda0 windowMagnificationSettings$$ExternalSyntheticLambda0 = windowMagnificationSettings.mWindowInsetChangeRunnable;
                    if (!handler.hasCallbacks(windowMagnificationSettings$$ExternalSyntheticLambda0)) {
                        windowMagnificationSettings.mSettingView.getHandler().post(windowMagnificationSettings$$ExternalSyntheticLambda0);
                    }
                }
                return view.onApplyWindowInsets(windowInsets);
            }
        });
        updateSelectedButton(this.mLastSelectedButtonIndex);
    }

    public boolean isDiagonalScrollingEnabled() {
        return this.mAllowDiagonalScrolling;
    }

    public final void moveButton$1(final float f, final float f2) {
        this.mSfVsyncFrameProvider.postFrameCallback(new Choreographer.FrameCallback() { // from class: com.android.systemui.accessibility.WindowMagnificationSettings$$ExternalSyntheticLambda5
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                WindowMagnificationSettings windowMagnificationSettings = WindowMagnificationSettings.this;
                float f3 = f;
                float f4 = f2;
                WindowManager.LayoutParams layoutParams = windowMagnificationSettings.mParams;
                layoutParams.x = (int) (layoutParams.x + f3);
                layoutParams.y = (int) (layoutParams.y + f4);
                windowMagnificationSettings.updateButtonViewLayoutIfNeeded();
            }
        });
    }

    @Override // com.android.systemui.accessibility.MagnificationGestureDetector.OnGestureListener
    public final boolean onDrag(View view, float f, float f2) {
        moveButton$1(f, f2);
        return true;
    }

    @Override // com.android.systemui.accessibility.MagnificationGestureDetector.OnGestureListener
    public final boolean onFinish() {
        if (!this.mSingleTapDetected) {
            showSettingPanel(true);
        }
        this.mSingleTapDetected = false;
        return true;
    }

    @Override // com.android.systemui.accessibility.MagnificationGestureDetector.OnGestureListener
    public final void onSingleTap(View view) {
        this.mSingleTapDetected = true;
    }

    public void setDiagonalScrolling(boolean z) {
        this.mSecureSettings.putIntForUser("accessibility_allow_diagonal_scrolling", z ? 1 : 0, -2);
        MagnificationSettingsController magnificationSettingsController = MagnificationSettingsController.this;
        MagnificationSettingsController.Callback callback = magnificationSettingsController.mSettingsControllerCallback;
        int i = magnificationSettingsController.mDisplayId;
        MagnificationImpl.AnonymousClass3 anonymousClass3 = (MagnificationImpl.AnonymousClass3) callback;
        MagnificationImpl magnificationImpl = MagnificationImpl.this;
        magnificationImpl.mHandler.post(new MagnificationImpl$4$$ExternalSyntheticLambda0(anonymousClass3, i, z, 1));
    }

    public final void setScaleSeekbar(float f) {
        int i = (int) ((f - 1.0f) * this.mSeekBarMagnitude);
        if (i < 0) {
            i = 0;
        } else if (i > this.mZoomSeekbar.mSeekbar.getMax()) {
            i = this.mZoomSeekbar.mSeekbar.getMax();
        }
        this.mZoomSeekbar.setProgress(i);
    }

    public final void showSettingPanel(boolean z) {
        boolean z2 = true;
        if (!this.mIsVisible) {
            updateUIControlsIfNeeded();
            setScaleSeekbar(this.mScale);
            if (z) {
                this.mDraggableWindowBounds.set(getDraggableWindowBounds$1());
                WindowManager.LayoutParams layoutParams = this.mParams;
                Rect rect = this.mDraggableWindowBounds;
                layoutParams.x = rect.right;
                layoutParams.y = rect.bottom;
            }
            this.mViewCaptureAwareWindowManager.addView(this.mSettingView, this.mParams);
            this.mSecureSettings.registerContentObserverForUserSync("accessibility_magnification_capability", this.mMagnificationCapabilityObserver, -2);
            this.mSettingView.post(new WindowMagnificationSettings$$ExternalSyntheticLambda0(1, this));
            this.mIsVisible = true;
            MagnificationSettingsController magnificationSettingsController = MagnificationSettingsController.this;
            MagnificationSettingsController.Callback callback = magnificationSettingsController.mSettingsControllerCallback;
            int i = magnificationSettingsController.mDisplayId;
            MagnificationImpl.AnonymousClass3 anonymousClass3 = (MagnificationImpl.AnonymousClass3) callback;
            MagnificationImpl magnificationImpl = MagnificationImpl.this;
            magnificationImpl.mHandler.post(new MagnificationImpl$4$$ExternalSyntheticLambda0(anonymousClass3, i, z2, 0));
            if (z) {
                this.mSettingView.announceForAccessibility(this.mContext.getResources().getString(R.string.accessibility_magnification_settings_panel_description));
            }
        }
        this.mContext.registerReceiver(this.mScreenOffReceiver, new IntentFilter("android.intent.action.SCREEN_OFF"));
    }

    public void updateButtonViewLayoutIfNeeded() {
        if (this.mIsVisible) {
            WindowManager.LayoutParams layoutParams = this.mParams;
            int i = layoutParams.x;
            Rect rect = this.mDraggableWindowBounds;
            layoutParams.x = MathUtils.constrain(i, rect.left, rect.right);
            WindowManager.LayoutParams layoutParams2 = this.mParams;
            int i2 = layoutParams2.y;
            Rect rect2 = this.mDraggableWindowBounds;
            layoutParams2.y = MathUtils.constrain(i2, rect2.top, rect2.bottom);
            this.mWindowManager.updateViewLayout(this.mSettingView, this.mParams);
        }
    }

    public final void updateSelectedButton(int i) {
        int i2 = this.mLastSelectedButtonIndex;
        if (i2 == 1) {
            this.mSmallButton.setSelected(false);
        } else if (i2 == 2) {
            this.mMediumButton.setSelected(false);
        } else if (i2 == 3) {
            this.mLargeButton.setSelected(false);
        } else if (i2 == 4) {
            this.mFullScreenButton.setSelected(false);
        }
        if (i == 1) {
            this.mSmallButton.setSelected(true);
        } else if (i == 2) {
            this.mMediumButton.setSelected(true);
        } else if (i == 3) {
            this.mLargeButton.setSelected(true);
        } else if (i == 4) {
            this.mFullScreenButton.setSelected(true);
        }
        this.mLastSelectedButtonIndex = i;
    }

    public final void updateUIControlsIfNeeded() {
        int i;
        SecureSettings secureSettings = this.mSecureSettings;
        int intForUser = secureSettings.getIntForUser("accessibility_magnification_capability", 1, -2);
        int i2 = this.mLastSelectedButtonIndex;
        Context context = this.mContext;
        SharedPreferences sharedPreferences = context.getSharedPreferences("window_magnification_preferences", 0);
        if (intForUser != 1) {
            if (intForUser == 2) {
                this.mEditButton.setVisibility(0);
                this.mAllowDiagonalScrollingView.setVisibility(0);
                this.mFullScreenButton.setVisibility(8);
                if (i2 == 4) {
                    String string = sharedPreferences.getString(String.valueOf(context.getResources().getConfiguration().smallestScreenWidthDp), null);
                    if (string != null) {
                        i = WindowMagnificationFrameSpec.deserialize(string).index;
                        i2 = i;
                    }
                    i2 = 2;
                }
                updateSelectedButton(i2);
                this.mSettingView.requestLayout();
            }
            if (intForUser == 3) {
                int intForUser2 = secureSettings.getIntForUser("accessibility_magnification_mode", secureSettings.getIntForUser("accessibility_magnification_capability", 1, -2) == 2 ? 2 : 1, -2);
                this.mFullScreenButton.setVisibility(0);
                if (intForUser2 == 1) {
                    this.mEditButton.setVisibility(4);
                    this.mAllowDiagonalScrollingView.setVisibility(8);
                } else {
                    this.mEditButton.setVisibility(0);
                    this.mAllowDiagonalScrollingView.setVisibility(0);
                    String string2 = sharedPreferences.getString(String.valueOf(context.getResources().getConfiguration().smallestScreenWidthDp), null);
                    if (string2 != null) {
                        i = WindowMagnificationFrameSpec.deserialize(string2).index;
                        i2 = i;
                    }
                    i2 = 2;
                }
            }
            updateSelectedButton(i2);
            this.mSettingView.requestLayout();
        }
        this.mFullScreenButton.setVisibility(0);
        this.mEditButton.setVisibility(4);
        this.mAllowDiagonalScrollingView.setVisibility(8);
        i2 = 4;
        updateSelectedButton(i2);
        this.mSettingView.requestLayout();
    }

    @Override // com.android.systemui.accessibility.MagnificationGestureDetector.OnGestureListener
    public final void onStart() {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ZoomSeekbarChangeListener implements SeekBarWithIconButtonsView.OnSeekBarWithIconButtonsChangeListener {
        public ZoomSeekbarChangeListener() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                ((MagnificationSettingsController.AnonymousClass1) WindowMagnificationSettings.this.mCallback).onMagnifierScale((i / r0.mSeekBarMagnitude) + 1.0f, false);
            }
        }

        @Override // com.android.systemui.common.ui.view.SeekBarWithIconButtonsView.OnSeekBarWithIconButtonsChangeListener
        public final void onUserInteractionFinalized(SeekBar seekBar, int i) {
            float progress = seekBar.getProgress();
            ((MagnificationSettingsController.AnonymousClass1) WindowMagnificationSettings.this.mCallback).onMagnifierScale((progress / r0.mSeekBarMagnitude) + 1.0f, true);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onStopTrackingTouch(SeekBar seekBar) {
        }
    }
}
