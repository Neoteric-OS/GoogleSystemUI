package com.google.android.systemui.dreamliner;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.app.animation.Interpolators;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import com.google.android.systemui.dreamliner.DockObserver;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DockGestureController extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener, StatusBarStateController.StateListener, KeyguardStateController.Callback, ConfigurationController.ConfigurationListener {
    public static final long GEAR_VISIBLE_TIME_MILLIS;
    public static final long PREVIEW_DELAY_MILLIS;
    public final AccessibilityManager mAccessibilityManager;
    public final DockObserver.ProtectedBroadcastSender mBroadcastSender;
    public final Context mContext;
    public float mDiffX;
    public final DockIndicationController mDockIndicationController;
    public float mFirstTouchX;
    public float mFirstTouchY;
    public boolean mFromRight;
    GestureDetector mGestureDetector;
    public final KeyguardStateController mKeyguardStateController;
    public float mLastTouchX;
    public boolean mLaunchedPhoto;
    public final int mPhotoDiffThreshold;
    public boolean mPhotoEnabled;
    public final FrameLayout mPhotoPreview;
    public final TextView mPhotoPreviewText;
    public final PhysicsAnimator mPreviewTargetAnimator;
    public final ImageView mSettingsGear;
    public boolean mShouldConsumeTouch;
    public final DreamlinerA11yAction mShowPhotoFrameA11yAction;
    public final StatusBarStateController mStatusBarStateController;
    public PendingIntent mTapAction;
    public final View mTouchDelegateView;
    public boolean mTriggerPhoto;
    public VelocityTracker mVelocityTracker;
    public float mVelocityX;
    public final PhysicsAnimator.SpringConfig mTargetSpringConfig = new PhysicsAnimator.SpringConfig(1500.0f, 1.0f);
    public final AnonymousClass1 mKeyguardMonitorCallback = new KeyguardStateController.Callback() { // from class: com.google.android.systemui.dreamliner.DockGestureController.1
        @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
        public final void onKeyguardShowingChanged() {
            DockGestureController dockGestureController = DockGestureController.this;
            if (((KeyguardStateControllerImpl) dockGestureController.mKeyguardStateController).mOccluded) {
                dockGestureController.hidePhotoPreview(false);
            }
        }
    };
    public final DockGestureController$$ExternalSyntheticLambda0 mHideGearRunnable = new DockGestureController$$ExternalSyntheticLambda0(this, 0);

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        GEAR_VISIBLE_TIME_MILLIS = timeUnit.toMillis(15L);
        PREVIEW_DELAY_MILLIS = timeUnit.toMillis(1L);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.systemui.dreamliner.DockGestureController$1] */
    public DockGestureController(Context context, DockObserver.ProtectedBroadcastSender protectedBroadcastSender, ImageView imageView, FrameLayout frameLayout, View view, DockIndicationController dockIndicationController, StatusBarStateController statusBarStateController, KeyguardStateController keyguardStateController) {
        this.mDockIndicationController = dockIndicationController;
        this.mContext = context;
        this.mBroadcastSender = protectedBroadcastSender;
        this.mGestureDetector = new GestureDetector(context, this);
        this.mTouchDelegateView = view;
        this.mSettingsGear = imageView;
        this.mPhotoPreview = frameLayout;
        TextView textView = (TextView) frameLayout.findViewById(R.id.photo_preview_text);
        this.mPhotoPreviewText = textView;
        textView.setText(context.getResources().getString(R.string.dock_photo_preview_text));
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.systemui.dreamliner.DockGestureController$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DockGestureController dockGestureController = DockGestureController.this;
                dockGestureController.hideGear();
                dockGestureController.sendProtectedBroadcast(new Intent("com.google.android.systemui.dreamliner.SETTINGS"));
            }
        });
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        this.mPhotoDiffThreshold = context.getResources().getDimensionPixelSize(R.dimen.dock_photo_diff);
        this.mStatusBarStateController = statusBarStateController;
        this.mKeyguardStateController = keyguardStateController;
        Function2 function2 = PhysicsAnimator.onAnimatorCreated;
        this.mPreviewTargetAnimator = PhysicsAnimator.Companion.getInstance(frameLayout);
        this.mShowPhotoFrameA11yAction = new DreamlinerA11yAction(context.getString(R.string.dock_a11y_action_show_photo_text), frameLayout.getRootView(), new DockGestureController$$ExternalSyntheticLambda0(this, 1));
    }

    public final void hideGear() {
        if (this.mSettingsGear.isVisibleToUser() || this.mSettingsGear.getVisibility() == 0) {
            this.mSettingsGear.removeCallbacks(this.mHideGearRunnable);
            this.mSettingsGear.animate().setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN).alpha(0.0f).withEndAction(new DockGestureController$$ExternalSyntheticLambda0(this, 6)).start();
        }
    }

    public final void hidePhotoPreview(boolean z) {
        if (this.mPhotoPreview.getVisibility() != 0) {
            return;
        }
        if (z) {
            this.mPhotoPreview.post(new DockGestureController$$ExternalSyntheticLambda0(this, 3));
        } else {
            this.mPhotoPreview.setAlpha(0.0f);
            this.mPhotoPreview.setVisibility(4);
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onDown(MotionEvent motionEvent) {
        sendProtectedBroadcast(new Intent("com.google.android.systemui.dreamliner.TOUCH_EVENT"));
        return false;
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onDozingChanged(boolean z) {
        if (z) {
            this.mTouchDelegateView.setOnTouchListener(this);
            showGear();
            final DreamlinerA11yAction dreamlinerA11yAction = this.mShowPhotoFrameA11yAction;
            int i = dreamlinerA11yAction.mActionId;
            String str = dreamlinerA11yAction.mActionLabel;
            if (i != -1) {
                Log.d("DreamlnierA11yAction", "enable action: " + str + " already enabled, skip enable");
                return;
            }
            FragmentManagerViewModel$$ExternalSyntheticOutline0.m("enable action: ", str, "DreamlnierA11yAction");
            View view = dreamlinerA11yAction.mViewForAction;
            AccessibilityViewCommand accessibilityViewCommand = new AccessibilityViewCommand() { // from class: com.google.android.systemui.dreamliner.DreamlinerA11yAction$$ExternalSyntheticLambda0
                @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                public final boolean perform(View view2) {
                    DreamlinerA11yAction.this.mCustomAction.run();
                    return true;
                }
            };
            dreamlinerA11yAction.mActionWrapper.getClass();
            dreamlinerA11yAction.mActionId = ViewCompat.addAccessibilityAction(view, str, accessibilityViewCommand);
            return;
        }
        this.mTouchDelegateView.setOnTouchListener(null);
        hideGear();
        if (this.mLaunchedPhoto) {
            this.mPhotoPreview.postDelayed(new DockGestureController$$ExternalSyntheticLambda0(this, 4), PREVIEW_DELAY_MILLIS);
        } else {
            hidePhotoPreview(true);
        }
        DreamlinerA11yAction dreamlinerA11yAction2 = this.mShowPhotoFrameA11yAction;
        int i2 = dreamlinerA11yAction2.mActionId;
        String str2 = dreamlinerA11yAction2.mActionLabel;
        if (i2 == -1) {
            StringBuilder m = ActivityResultRegistry$$ExternalSyntheticOutline0.m("disable action: ", str2, " already disable, skip disable(actionId:");
            m.append(dreamlinerA11yAction2.mActionId);
            m.append(", viewForAction==null:");
            m.append(dreamlinerA11yAction2.mViewForAction == null);
            m.append(")");
            Log.d("DreamlnierA11yAction", m.toString());
            return;
        }
        FragmentManagerViewModel$$ExternalSyntheticOutline0.m("disable action: ", str2, "DreamlnierA11yAction");
        View view2 = dreamlinerA11yAction2.mViewForAction;
        int i3 = dreamlinerA11yAction2.mActionId;
        dreamlinerA11yAction2.mActionWrapper.getClass();
        ViewCompat.removeActionWithId(view2, i3);
        ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(view2, 0);
        dreamlinerA11yAction2.mActionId = -1;
        ((Runnable) Optional.ofNullable(null).orElseGet(new DreamlinerA11yAction$$ExternalSyntheticLambda1())).run();
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onLocaleListChanged() {
        this.mPhotoPreviewText.setText(this.mContext.getResources().getString(R.string.dock_photo_preview_text));
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        PendingIntent pendingIntent = this.mTapAction;
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                Log.w("DLGestureController", "Tap action pending intent cancelled", e);
            }
        }
        showGear();
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0131  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouch(android.view.View r10, android.view.MotionEvent r11) {
        /*
            Method dump skipped, instructions count: 331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.DockGestureController.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public final void sendProtectedBroadcast(Intent intent) {
        try {
            this.mBroadcastSender.sendBroadcastAsUser(intent, UserHandle.ALL);
        } catch (SecurityException e) {
            Log.w("DLGestureController", "Cannot send event", e);
        }
    }

    public final void showGear() {
        if (this.mTapAction != null) {
            return;
        }
        if (!this.mSettingsGear.isVisibleToUser()) {
            this.mSettingsGear.setVisibility(0);
            this.mSettingsGear.animate().setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN).alpha(1.0f).start();
        }
        this.mSettingsGear.removeCallbacks(this.mHideGearRunnable);
        this.mSettingsGear.postDelayed(this.mHideGearRunnable, this.mAccessibilityManager == null ? GEAR_VISIBLE_TIME_MILLIS : r4.getRecommendedTimeoutMillis(Math.toIntExact(GEAR_VISIBLE_TIME_MILLIS), 5));
    }

    public final void stopMonitoring() {
        this.mStatusBarStateController.removeCallback(this);
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).removeCallback(this.mKeyguardMonitorCallback);
        onDozingChanged(false);
        this.mSettingsGear.setVisibility(8);
    }
}
