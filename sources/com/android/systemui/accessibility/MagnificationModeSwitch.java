package com.android.systemui.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.MathUtils;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.graphics.SfVsyncFrameCallbackProvider;
import com.android.systemui.accessibility.MagnificationGestureDetector;
import com.android.wm.shell.R;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class MagnificationModeSwitch implements MagnificationGestureDetector.OnGestureListener, ComponentCallbacks {
    static final int DEFAULT_FADE_OUT_ANIMATION_DELAY_MS = 5000;
    static final long FADING_ANIMATION_DURATION_MS = 300;
    public final AccessibilityManager mAccessibilityManager;
    public final ClickListener mClickListener;
    public final Configuration mConfiguration;
    public final Context mContext;
    public final MagnificationModeSwitch$$ExternalSyntheticLambda2 mFadeInAnimationTask;
    public final MagnificationModeSwitch$$ExternalSyntheticLambda2 mFadeOutAnimationTask;
    public final MagnificationGestureDetector mGestureDetector;
    public final ImageView mImageView;
    public final WindowManager.LayoutParams mParams;
    public final SfVsyncFrameCallbackProvider mSfVsyncFrameProvider;
    public int mUiTimeout;
    public final ViewCaptureAwareWindowManager mViewCaptureAwareWindowManager;
    public final MagnificationModeSwitch$$ExternalSyntheticLambda2 mWindowInsetChangeRunnable;
    public final WindowManager mWindowManager;
    boolean mIsFadeOutAnimating = false;
    public int mMagnificationMode = 0;
    final Rect mDraggableWindowBounds = new Rect();
    public boolean mIsVisible = false;
    public boolean mSingleTapDetected = false;
    public boolean mToLeftScreenEdge = false;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ClickListener {
        void onClick(int i);
    }

    public MagnificationModeSwitch(Context context, ImageView imageView, SfVsyncFrameCallbackProvider sfVsyncFrameCallbackProvider, ClickListener clickListener, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        this.mContext = context;
        this.mConfiguration = new Configuration(context.getResources().getConfiguration());
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService(AccessibilityManager.class);
        this.mWindowManager = (WindowManager) context.getSystemService(WindowManager.class);
        this.mViewCaptureAwareWindowManager = viewCaptureAwareWindowManager;
        this.mSfVsyncFrameProvider = sfVsyncFrameCallbackProvider;
        this.mClickListener = clickListener;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.magnification_switch_button_size);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(dimensionPixelSize, dimensionPixelSize, 2039, 8, -2);
        layoutParams.gravity = 51;
        layoutParams.accessibilityTitle = context.getString(android.R.string.android_upgrading_complete);
        layoutParams.layoutInDisplayCutoutMode = 3;
        this.mParams = layoutParams;
        this.mImageView = imageView;
        imageView.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.accessibility.MagnificationModeSwitch$$ExternalSyntheticLambda1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                MagnificationModeSwitch magnificationModeSwitch = MagnificationModeSwitch.this;
                if (magnificationModeSwitch.mIsVisible) {
                    return magnificationModeSwitch.mGestureDetector.onTouch(view, motionEvent);
                }
                return false;
            }
        });
        imageView.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.systemui.accessibility.MagnificationModeSwitch.1
            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                MagnificationModeSwitch magnificationModeSwitch = MagnificationModeSwitch.this;
                accessibilityNodeInfo.setStateDescription(magnificationModeSwitch.mContext.getResources().getString(magnificationModeSwitch.mMagnificationMode == 2 ? R.string.magnification_mode_switch_state_window : R.string.magnification_mode_switch_state_full_screen));
                accessibilityNodeInfo.setContentDescription(MagnificationModeSwitch.this.mContext.getResources().getString(R.string.magnification_mode_switch_description));
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK.getId(), MagnificationModeSwitch.this.mContext.getResources().getString(R.string.magnification_open_settings_click_label)));
                accessibilityNodeInfo.setClickable(true);
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_move_up, MagnificationModeSwitch.this.mContext.getString(R.string.accessibility_control_move_up)));
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_move_down, MagnificationModeSwitch.this.mContext.getString(R.string.accessibility_control_move_down)));
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_move_left, MagnificationModeSwitch.this.mContext.getString(R.string.accessibility_control_move_left)));
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibility_action_move_right, MagnificationModeSwitch.this.mContext.getString(R.string.accessibility_control_move_right)));
            }

            @Override // android.view.View.AccessibilityDelegate
            public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                Rect bounds = MagnificationModeSwitch.this.mWindowManager.getCurrentWindowMetrics().getBounds();
                if (i == AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK.getId()) {
                    MagnificationModeSwitch magnificationModeSwitch = MagnificationModeSwitch.this;
                    magnificationModeSwitch.removeButton();
                    magnificationModeSwitch.mClickListener.onClick(magnificationModeSwitch.mContext.getDisplayId());
                    return true;
                }
                if (i == R.id.accessibility_action_move_up) {
                    MagnificationModeSwitch.this.moveButton(0.0f, -bounds.height());
                    return true;
                }
                if (i == R.id.accessibility_action_move_down) {
                    MagnificationModeSwitch.this.moveButton(0.0f, bounds.height());
                    return true;
                }
                if (i == R.id.accessibility_action_move_left) {
                    MagnificationModeSwitch.this.moveButton(-bounds.width(), 0.0f);
                    return true;
                }
                if (i != R.id.accessibility_action_move_right) {
                    return super.performAccessibilityAction(view, i, bundle);
                }
                MagnificationModeSwitch.this.moveButton(bounds.width(), 0.0f);
                return true;
            }
        });
        this.mWindowInsetChangeRunnable = new MagnificationModeSwitch$$ExternalSyntheticLambda2(this, 0);
        imageView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.android.systemui.accessibility.MagnificationModeSwitch$$ExternalSyntheticLambda3
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                MagnificationModeSwitch magnificationModeSwitch = MagnificationModeSwitch.this;
                if (!magnificationModeSwitch.mImageView.getHandler().hasCallbacks(magnificationModeSwitch.mWindowInsetChangeRunnable)) {
                    magnificationModeSwitch.mImageView.getHandler().post(magnificationModeSwitch.mWindowInsetChangeRunnable);
                }
                return view.onApplyWindowInsets(windowInsets);
            }
        });
        this.mFadeInAnimationTask = new MagnificationModeSwitch$$ExternalSyntheticLambda2(this, 3);
        this.mFadeOutAnimationTask = new MagnificationModeSwitch$$ExternalSyntheticLambda2(this, 1);
        this.mGestureDetector = new MagnificationGestureDetector(context, context.getMainThreadHandler(), this);
    }

    public static int getIconResId(int i) {
        return R.drawable.ic_open_in_new_window;
    }

    public final Rect getDraggableWindowBounds() {
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.magnification_switch_button_margin);
        WindowMetrics currentWindowMetrics = this.mWindowManager.getCurrentWindowMetrics();
        Insets insetsIgnoringVisibility = currentWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout());
        Rect rect = new Rect(currentWindowMetrics.getBounds());
        rect.offsetTo(0, 0);
        WindowManager.LayoutParams layoutParams = this.mParams;
        rect.inset(0, 0, layoutParams.width, layoutParams.height);
        rect.inset(insetsIgnoringVisibility);
        rect.inset(dimensionPixelSize, dimensionPixelSize);
        return rect;
    }

    public final void moveButton(final float f, final float f2) {
        this.mSfVsyncFrameProvider.postFrameCallback(new Choreographer.FrameCallback() { // from class: com.android.systemui.accessibility.MagnificationModeSwitch$$ExternalSyntheticLambda10
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j) {
                MagnificationModeSwitch magnificationModeSwitch = MagnificationModeSwitch.this;
                float f3 = f;
                float f4 = f2;
                WindowManager.LayoutParams layoutParams = magnificationModeSwitch.mParams;
                layoutParams.x = (int) (layoutParams.x + f3);
                layoutParams.y = (int) (layoutParams.y + f4);
                magnificationModeSwitch.updateButtonViewLayoutIfNeeded$1();
            }
        });
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        int diff = configuration.diff(this.mConfiguration);
        this.mConfiguration.setTo(configuration);
        if (diff == 0) {
            return;
        }
        if ((diff & 1152) != 0) {
            Rect rect = new Rect(this.mDraggableWindowBounds);
            this.mDraggableWindowBounds.set(getDraggableWindowBounds());
            this.mParams.y = ((int) (((this.mParams.y - rect.top) / rect.height()) * this.mDraggableWindowBounds.height())) + this.mDraggableWindowBounds.top;
            stickToScreenEdge(this.mToLeftScreenEdge);
            return;
        }
        if ((diff & 4096) == 0) {
            if ((diff & 4) != 0) {
                this.mParams.accessibilityTitle = this.mContext.getString(android.R.string.android_upgrading_complete);
                if (this.mIsVisible) {
                    this.mWindowManager.updateViewLayout(this.mImageView, this.mParams);
                    return;
                }
                return;
            }
            return;
        }
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.magnification_switch_button_size);
        WindowManager.LayoutParams layoutParams = this.mParams;
        layoutParams.height = dimensionPixelSize;
        layoutParams.width = dimensionPixelSize;
        if (this.mIsVisible) {
            stickToScreenEdge(this.mToLeftScreenEdge);
            removeButton();
            showButton(this.mMagnificationMode, false);
        }
    }

    @Override // com.android.systemui.accessibility.MagnificationGestureDetector.OnGestureListener
    public final boolean onDrag(View view, float f, float f2) {
        moveButton(f, f2);
        return true;
    }

    @Override // com.android.systemui.accessibility.MagnificationGestureDetector.OnGestureListener
    public final boolean onFinish() {
        if (this.mIsVisible) {
            boolean z = this.mParams.x < this.mWindowManager.getCurrentWindowMetrics().getBounds().width() / 2;
            this.mToLeftScreenEdge = z;
            stickToScreenEdge(z);
        }
        if (!this.mSingleTapDetected) {
            showButton(this.mMagnificationMode, true);
        }
        this.mSingleTapDetected = false;
        return true;
    }

    @Override // com.android.systemui.accessibility.MagnificationGestureDetector.OnGestureListener
    public final void onSingleTap(View view) {
        this.mSingleTapDetected = true;
        removeButton();
        this.mClickListener.onClick(this.mContext.getDisplayId());
    }

    @Override // com.android.systemui.accessibility.MagnificationGestureDetector.OnGestureListener
    public final void onStart() {
        stopFadeOutAnimation();
    }

    public final void removeButton() {
        if (this.mIsVisible) {
            this.mImageView.removeCallbacks(this.mFadeInAnimationTask);
            this.mImageView.removeCallbacks(this.mFadeOutAnimationTask);
            this.mImageView.animate().cancel();
            this.mIsFadeOutAnimating = false;
            this.mImageView.setAlpha(0.0f);
            this.mViewCaptureAwareWindowManager.removeView(this.mImageView);
            this.mContext.unregisterComponentCallbacks(this);
            this.mIsVisible = false;
        }
    }

    public final void showButton(int i, boolean z) {
        final int i2 = 2;
        final int i3 = 0;
        final int i4 = 1;
        if (i != 1) {
            return;
        }
        if (this.mMagnificationMode != i) {
            this.mMagnificationMode = i;
            this.mImageView.setImageResource(getIconResId(i));
        }
        if (!this.mIsVisible) {
            onConfigurationChanged(this.mContext.getResources().getConfiguration());
            this.mContext.registerComponentCallbacks(this);
            if (z) {
                this.mDraggableWindowBounds.set(getDraggableWindowBounds());
                WindowManager.LayoutParams layoutParams = this.mParams;
                Rect rect = this.mDraggableWindowBounds;
                layoutParams.x = rect.right;
                layoutParams.y = rect.bottom;
                this.mToLeftScreenEdge = false;
            }
            this.mViewCaptureAwareWindowManager.addView(this.mImageView, this.mParams);
            this.mImageView.post(new MagnificationModeSwitch$$ExternalSyntheticLambda2(this, 2));
            this.mIsVisible = true;
            this.mImageView.postOnAnimation(this.mFadeInAnimationTask);
            this.mUiTimeout = this.mAccessibilityManager.getRecommendedTimeoutMillis(DEFAULT_FADE_OUT_ANIMATION_DELAY_MS, 5);
            try {
                String[] stringArray = this.mContext.getResources().getStringArray(R.array.services_always_show_magnification_settings);
                if (stringArray.length != 0) {
                    Set of = Set.of((Object[]) stringArray);
                    Iterator<AccessibilityServiceInfo> it = this.mAccessibilityManager.getEnabledAccessibilityServiceList(-1).iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        String str = (String) Optional.ofNullable(it.next()).map(new Function() { // from class: com.android.systemui.accessibility.MagnificationModeSwitch$$ExternalSyntheticLambda6
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                switch (i3) {
                                    case 0:
                                        return ((AccessibilityServiceInfo) obj).getResolveInfo();
                                    case 1:
                                        return ((ResolveInfo) obj).serviceInfo;
                                    default:
                                        return ((ServiceInfo) obj).name;
                                }
                            }
                        }).map(new Function() { // from class: com.android.systemui.accessibility.MagnificationModeSwitch$$ExternalSyntheticLambda6
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                switch (i4) {
                                    case 0:
                                        return ((AccessibilityServiceInfo) obj).getResolveInfo();
                                    case 1:
                                        return ((ResolveInfo) obj).serviceInfo;
                                    default:
                                        return ((ServiceInfo) obj).name;
                                }
                            }
                        }).map(new Function() { // from class: com.android.systemui.accessibility.MagnificationModeSwitch$$ExternalSyntheticLambda6
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                switch (i2) {
                                    case 0:
                                        return ((AccessibilityServiceInfo) obj).getResolveInfo();
                                    case 1:
                                        return ((ResolveInfo) obj).serviceInfo;
                                    default:
                                        return ((ServiceInfo) obj).name;
                                }
                            }
                        }).orElse(null);
                        if (str != null && of.contains(str)) {
                            this.mUiTimeout = -1;
                            break;
                        }
                    }
                }
            } catch (Resources.NotFoundException unused) {
            }
        }
        stopFadeOutAnimation();
        int i5 = this.mUiTimeout;
        if (i5 >= 0) {
            this.mImageView.postOnAnimationDelayed(this.mFadeOutAnimationTask, i5);
        }
    }

    public final void stickToScreenEdge(boolean z) {
        this.mParams.x = z ? this.mDraggableWindowBounds.left : this.mDraggableWindowBounds.right;
        updateButtonViewLayoutIfNeeded$1();
    }

    public final void stopFadeOutAnimation() {
        this.mImageView.removeCallbacks(this.mFadeOutAnimationTask);
        if (this.mIsFadeOutAnimating) {
            this.mImageView.animate().cancel();
            this.mImageView.setAlpha(1.0f);
            this.mIsFadeOutAnimating = false;
        }
    }

    public final void updateButtonViewLayoutIfNeeded$1() {
        if (this.mIsVisible) {
            WindowManager.LayoutParams layoutParams = this.mParams;
            int i = layoutParams.x;
            Rect rect = this.mDraggableWindowBounds;
            layoutParams.x = MathUtils.constrain(i, rect.left, rect.right);
            WindowManager.LayoutParams layoutParams2 = this.mParams;
            int i2 = layoutParams2.y;
            Rect rect2 = this.mDraggableWindowBounds;
            layoutParams2.y = MathUtils.constrain(i2, rect2.top, rect2.bottom);
            this.mWindowManager.updateViewLayout(this.mImageView, this.mParams);
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }
}
