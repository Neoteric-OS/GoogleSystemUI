package com.google.android.systemui.dreamliner;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.phone.KeyguardIndicationTextView;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.wm.shell.R;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DockIndicationController implements StatusBarStateController.StateListener, View.OnClickListener, View.OnAttachStateChangeListener, ConfigurationController.ConfigurationListener {
    static final String ACTION_ASSISTANT_POODLE = "com.google.android.systemui.dreamliner.ASSISTANT_POODLE";
    public static final long KEYGUARD_INDICATION_TIMEOUT_MILLIS;
    public static final long PROMO_SHOWING_TIME_MILLIS;
    public final AccessibilityManager mAccessibilityManager;
    public LinearLayout mAmbientIndicationContainer;
    public final BroadcastSender mBroadcastSender;
    public final Context mContext;
    public final DockIndicationController$$ExternalSyntheticLambda0 mDisableLiveRegionRunnable;
    FrameLayout mDockPromo;
    ImageView mDockedTopIcon;
    public boolean mDocking;
    public boolean mDozing;
    public final Animation mHidePromoAnimation;
    public final DockIndicationController$$ExternalSyntheticLambda0 mHidePromoRunnable;
    boolean mIconViewsValidated;
    public final KeyguardIndicationController mKeyguardIndicationController;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public TextView mPromoText;
    public boolean mShowPromo;
    public final Animation mShowPromoAnimation;
    public int mShowPromoTimes;
    public int mStatusBarState;
    public boolean mTopIconShowing;
    public KeyguardIndicationTextView mTopIndicationView;
    public boolean mViewAttached = true;

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        PROMO_SHOWING_TIME_MILLIS = timeUnit.toMillis(2L);
        KEYGUARD_INDICATION_TIMEOUT_MILLIS = timeUnit.toMillis(15L);
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.google.android.systemui.dreamliner.DockIndicationController$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.google.android.systemui.dreamliner.DockIndicationController$$ExternalSyntheticLambda0] */
    public DockIndicationController(Context context, BroadcastSender broadcastSender, KeyguardIndicationController keyguardIndicationController, SysuiStatusBarStateController sysuiStatusBarStateController, NotificationShadeWindowController notificationShadeWindowController) {
        final int i = 1;
        this.mContext = context;
        this.mBroadcastSender = broadcastSender;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mKeyguardIndicationController = keyguardIndicationController;
        ((StatusBarStateControllerImpl) sysuiStatusBarStateController).addCallback((StatusBarStateController.StateListener) this);
        final int i2 = 0;
        this.mHidePromoRunnable = new Runnable(this) { // from class: com.google.android.systemui.dreamliner.DockIndicationController$$ExternalSyntheticLambda0
            public final /* synthetic */ DockIndicationController f$0;

            {
                this.f$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i3 = i2;
                DockIndicationController dockIndicationController = this.f$0;
                switch (i3) {
                    case 0:
                        if (dockIndicationController.mDozing && dockIndicationController.mDocking) {
                            dockIndicationController.mDockPromo.startAnimation(dockIndicationController.mHidePromoAnimation);
                            break;
                        }
                        break;
                    default:
                        if (dockIndicationController.mDocking && dockIndicationController.mDozing) {
                            dockIndicationController.mTopIndicationView.setAccessibilityLiveRegion(0);
                            break;
                        }
                        break;
                }
            }
        };
        this.mDisableLiveRegionRunnable = new Runnable(this) { // from class: com.google.android.systemui.dreamliner.DockIndicationController$$ExternalSyntheticLambda0
            public final /* synthetic */ DockIndicationController f$0;

            {
                this.f$0 = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i3 = i;
                DockIndicationController dockIndicationController = this.f$0;
                switch (i3) {
                    case 0:
                        if (dockIndicationController.mDozing && dockIndicationController.mDocking) {
                            dockIndicationController.mDockPromo.startAnimation(dockIndicationController.mHidePromoAnimation);
                            break;
                        }
                        break;
                    default:
                        if (dockIndicationController.mDocking && dockIndicationController.mDozing) {
                            dockIndicationController.mTopIndicationView.setAccessibilityLiveRegion(0);
                            break;
                        }
                        break;
                }
            }
        };
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.dock_promo_animation);
        this.mShowPromoAnimation = loadAnimation;
        loadAnimation.setAnimationListener(new Animation.AnimationListener(this) { // from class: com.google.android.systemui.dreamliner.DockIndicationController.1
            public final /* synthetic */ DockIndicationController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public final void onAnimationEnd(Animation animation) {
                switch (i2) {
                    case 0:
                        DockIndicationController dockIndicationController = this.this$0;
                        FrameLayout frameLayout = dockIndicationController.mDockPromo;
                        DockIndicationController$$ExternalSyntheticLambda0 dockIndicationController$$ExternalSyntheticLambda0 = dockIndicationController.mHidePromoRunnable;
                        long j = DockIndicationController.PROMO_SHOWING_TIME_MILLIS;
                        AccessibilityManager accessibilityManager = dockIndicationController.mAccessibilityManager;
                        if (accessibilityManager != null) {
                            j = accessibilityManager.getRecommendedTimeoutMillis(Math.toIntExact(j), 2);
                        }
                        frameLayout.postDelayed(dockIndicationController$$ExternalSyntheticLambda0, j);
                        break;
                    default:
                        DockIndicationController dockIndicationController2 = this.this$0;
                        if (dockIndicationController2.mShowPromoTimes >= 3) {
                            dockIndicationController2.mKeyguardIndicationController.setVisible(true);
                            this.this$0.mDockPromo.setVisibility(8);
                            break;
                        } else {
                            dockIndicationController2.showPromoInner();
                            break;
                        }
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public final void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public final void onAnimationStart(Animation animation) {
            }
        });
        Animation loadAnimation2 = AnimationUtils.loadAnimation(context, R.anim.dock_promo_fade_out);
        this.mHidePromoAnimation = loadAnimation2;
        loadAnimation2.setAnimationListener(new Animation.AnimationListener(this) { // from class: com.google.android.systemui.dreamliner.DockIndicationController.1
            public final /* synthetic */ DockIndicationController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public final void onAnimationEnd(Animation animation) {
                switch (i) {
                    case 0:
                        DockIndicationController dockIndicationController = this.this$0;
                        FrameLayout frameLayout = dockIndicationController.mDockPromo;
                        DockIndicationController$$ExternalSyntheticLambda0 dockIndicationController$$ExternalSyntheticLambda0 = dockIndicationController.mHidePromoRunnable;
                        long j = DockIndicationController.PROMO_SHOWING_TIME_MILLIS;
                        AccessibilityManager accessibilityManager = dockIndicationController.mAccessibilityManager;
                        if (accessibilityManager != null) {
                            j = accessibilityManager.getRecommendedTimeoutMillis(Math.toIntExact(j), 2);
                        }
                        frameLayout.postDelayed(dockIndicationController$$ExternalSyntheticLambda0, j);
                        break;
                    default:
                        DockIndicationController dockIndicationController2 = this.this$0;
                        if (dockIndicationController2.mShowPromoTimes >= 3) {
                            dockIndicationController2.mKeyguardIndicationController.setVisible(true);
                            this.this$0.mDockPromo.setVisibility(8);
                            break;
                        } else {
                            dockIndicationController2.showPromoInner();
                            break;
                        }
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public final void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public final void onAnimationStart(Animation animation) {
            }
        });
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
    }

    public void initializeIconViews() {
        Log.d("DLIndicator", "initializeIconViews()");
        NotificationShadeWindowView notificationShadeWindowView = ((NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController).mWindowRootView;
        ImageView imageView = (ImageView) notificationShadeWindowView.findViewById(R.id.docked_top_icon);
        this.mDockedTopIcon = imageView;
        imageView.setImageResource(R.drawable.ic_assistant_logo);
        this.mDockedTopIcon.setContentDescription(this.mContext.getString(R.string.accessibility_assistant_poodle));
        this.mDockedTopIcon.setTooltipText(this.mContext.getString(R.string.accessibility_assistant_poodle));
        this.mDockedTopIcon.setOnClickListener(this);
        this.mDockPromo = (FrameLayout) notificationShadeWindowView.findViewById(R.id.dock_promo);
        TextView textView = (TextView) notificationShadeWindowView.findViewById(R.id.photo_promo_text);
        this.mPromoText = textView;
        textView.setAutoSizeTextTypeUniformWithConfiguration(10, 16, 1, 2);
        notificationShadeWindowView.findViewById(R.id.ambient_indication).addOnAttachStateChangeListener(this);
        this.mTopIndicationView = (KeyguardIndicationTextView) notificationShadeWindowView.findViewById(R.id.keyguard_indication_text);
        this.mAmbientIndicationContainer = (LinearLayout) notificationShadeWindowView.findViewById(R.id.ambient_indication_info_container);
        this.mIconViewsValidated = true;
        this.mViewAttached = true;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (view.getId() == R.id.docked_top_icon) {
            Intent intent = new Intent(ACTION_ASSISTANT_POODLE);
            intent.addFlags(1073741824);
            try {
                this.mBroadcastSender.sendBroadcastAsUser(intent, UserHandle.ALL);
            } catch (SecurityException e) {
                Log.w("DLIndicator", "Cannot send event for intent= " + intent, e);
            }
        }
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onDozingChanged(boolean z) {
        Log.d("DLIndicator", "onDozingChanged(" + z + ")");
        this.mDozing = z;
        updateVisibility$8();
        updateLiveRegionIfNeeded();
        if (this.mDozing) {
            showPromoInner();
        } else {
            this.mShowPromo = false;
        }
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onLocaleListChanged() {
        if (!this.mIconViewsValidated && this.mViewAttached) {
            initializeIconViews();
        }
        this.mPromoText.setText(this.mContext.getResources().getString(R.string.dock_promo_text));
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onStateChanged(int i) {
        Log.d("DLIndicator", "onStateChanged(" + i + ")");
        this.mStatusBarState = i;
        updateVisibility$8();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        Log.d("DLIndicator", "onViewAttachedToWindow()");
        this.mViewAttached = true;
        updateVisibility$8();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        Log.d("DLIndicator", "onViewDetachedFromWindow()");
        this.mViewAttached = false;
        view.removeOnAttachStateChangeListener(this);
        this.mIconViewsValidated = false;
        this.mDockedTopIcon = null;
    }

    public final void showPromoInner() {
        if (this.mDozing && this.mDocking && this.mShowPromo) {
            this.mKeyguardIndicationController.setVisible(false);
            this.mDockPromo.setVisibility(0);
            this.mDockPromo.startAnimation(this.mShowPromoAnimation);
            this.mShowPromoTimes++;
        }
    }

    public final void updateLiveRegionIfNeeded() {
        int accessibilityLiveRegion = this.mTopIndicationView.getAccessibilityLiveRegion();
        if (!this.mDozing || !this.mDocking) {
            if (accessibilityLiveRegion != 1) {
                this.mTopIndicationView.setAccessibilityLiveRegion(1);
                return;
            }
            return;
        }
        this.mTopIndicationView.removeCallbacks(this.mDisableLiveRegionRunnable);
        KeyguardIndicationTextView keyguardIndicationTextView = this.mTopIndicationView;
        DockIndicationController$$ExternalSyntheticLambda0 dockIndicationController$$ExternalSyntheticLambda0 = this.mDisableLiveRegionRunnable;
        long j = KEYGUARD_INDICATION_TIMEOUT_MILLIS;
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        if (accessibilityManager != null) {
            j = accessibilityManager.getRecommendedTimeoutMillis(Math.toIntExact(j), 2);
        }
        keyguardIndicationTextView.postDelayed(dockIndicationController$$ExternalSyntheticLambda0, j);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateVisibility$8() {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "updateVisibility(), mDozing="
            r0.<init>(r1)
            boolean r1 = r5.mDozing
            r0.append(r1)
            java.lang.String r1 = ", mDocking="
            r0.append(r1)
            boolean r1 = r5.mDocking
            r0.append(r1)
            java.lang.String r1 = ", mTopIconShowing="
            r0.append(r1)
            boolean r1 = r5.mTopIconShowing
            r0.append(r1)
            java.lang.String r1 = ", mViewAttached="
            r0.append(r1)
            boolean r1 = r5.mViewAttached
            java.lang.String r2 = "DLIndicator"
            com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0.m(r0, r1, r2)
            boolean r0 = r5.mViewAttached
            if (r0 != 0) goto L3b
            r5.initializeIconViews()     // Catch: java.lang.Exception -> L34
            goto L3b
        L34:
            r5 = move-exception
            java.lang.String r0 = "tryInitializeIconView() failed"
            android.util.Log.w(r2, r0, r5)
            return
        L3b:
            boolean r0 = r5.mIconViewsValidated
            if (r0 != 0) goto L42
            r5.initializeIconViews()
        L42:
            boolean r0 = r5.mDozing
            r1 = 1
            r2 = 8
            r3 = 0
            if (r0 == 0) goto L60
            boolean r0 = r5.mDocking
            if (r0 != 0) goto L4f
            goto L60
        L4f:
            boolean r0 = r5.mTopIconShowing
            if (r0 != 0) goto L5a
            android.widget.ImageView r0 = r5.mDockedTopIcon
            r0.setVisibility(r2)
        L58:
            r1 = r3
            goto L79
        L5a:
            android.widget.ImageView r0 = r5.mDockedTopIcon
            r0.setVisibility(r3)
            goto L79
        L60:
            android.widget.FrameLayout r0 = r5.mDockPromo
            r0.setVisibility(r2)
            android.widget.ImageView r0 = r5.mDockedTopIcon
            r0.setVisibility(r2)
            int r0 = r5.mStatusBarState
            if (r0 == r1) goto L73
            r2 = 2
            if (r0 != r2) goto L72
            goto L73
        L72:
            r1 = r3
        L73:
            com.android.systemui.statusbar.KeyguardIndicationController r0 = r5.mKeyguardIndicationController
            r0.setVisible(r1)
            goto L58
        L79:
            android.widget.LinearLayout r0 = r5.mAmbientIndicationContainer
            if (r0 != 0) goto L7e
            goto Lb2
        L7e:
            if (r1 == 0) goto L8b
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131165330(0x7f070092, float:1.7944874E38)
            int r3 = r0.getDimensionPixelSize(r1)
        L8b:
            android.widget.LinearLayout r0 = r5.mAmbientIndicationContainer
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            boolean r0 = r0 instanceof android.view.ViewGroup.MarginLayoutParams
            if (r0 == 0) goto Lb2
            android.widget.LinearLayout r0 = r5.mAmbientIndicationContainer
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r0 = (android.view.ViewGroup.MarginLayoutParams) r0
            if (r0 == 0) goto Lb2
            int r1 = r0.topMargin
            if (r1 != r3) goto La4
            goto Lb2
        La4:
            int r1 = r0.leftMargin
            int r2 = r0.rightMargin
            int r4 = r0.bottomMargin
            r0.setMargins(r1, r3, r2, r4)
            android.widget.LinearLayout r5 = r5.mAmbientIndicationContainer
            r5.requestLayout()
        Lb2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.DockIndicationController.updateVisibility$8():void");
    }
}
