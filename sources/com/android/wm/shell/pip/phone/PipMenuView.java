package com.android.wm.shell.pip.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Pair;
import android.util.Property;
import android.util.Slog;
import android.view.IWindow;
import android.view.IWindowSession;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManagerGlobal;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.window.InputTransferToken;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.R;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SystemWindows;
import com.android.wm.shell.common.pip.PipMediaController;
import com.android.wm.shell.common.pip.PipUiEventLogger;
import com.android.wm.shell.common.pip.PipUtils;
import com.android.wm.shell.pip.phone.PhonePipMenuController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.animation.Interpolators;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipMenuView extends FrameLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final AccessibilityManager mAccessibilityManager;
    public final List mActions;
    public final LinearLayout mActionsGroup;
    public boolean mAllowMenuTimeout;
    public boolean mAllowTouches;
    public final Drawable mBackgroundDrawable;
    public final int mBetweenActionPaddingLand;
    public RemoteAction mCloseAction;
    public final Context mContextForUser;
    public final PhonePipMenuController mController;
    public boolean mDidLastShowMenuResize;
    public final View mDismissButton;
    public final int mDismissFadeOutDurationMs;
    public final PipMenuView$$ExternalSyntheticLambda0 mHideMenuRunnable;
    public final ShellExecutor mMainExecutor;
    public final Handler mMainHandler;
    public final AnonymousClass1 mMenuBgUpdateListener;
    public final View mMenuContainer;
    public AnimatorSet mMenuContainerAnimator;
    public int mMenuState;
    public final int mPipForceCloseDelay;
    public final PipMenuIconsAlgorithm mPipMenuIconsAlgorithm;
    public final PipUiEventLogger mPipUiEventLogger;
    public final View mSettingsButton;

    /* renamed from: $r8$lambda$kh7O-2Rmd0Daiu_MbPcAw5t76mc, reason: not valid java name */
    public static void m905$r8$lambda$kh7O2Rmd0Daiu_MbPcAw5t76mc(PipMenuView pipMenuView, View view) {
        pipMenuView.getClass();
        if (view.getAlpha() != 0.0f) {
            Pair topPipActivity = PipUtils.getTopPipActivity(((FrameLayout) pipMenuView).mContext);
            if (topPipActivity.first != null) {
                Intent intent = new Intent("android.settings.PICTURE_IN_PICTURE_SETTINGS", Uri.fromParts("package", ((ComponentName) topPipActivity.first).getPackageName(), null));
                intent.setFlags(268468224);
                ((FrameLayout) pipMenuView).mContext.startActivityAsUser(intent, UserHandle.of(((Integer) topPipActivity.second).intValue()));
                pipMenuView.mPipUiEventLogger.log(PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_SHOW_SETTINGS);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.wm.shell.pip.phone.PipMenuView$1] */
    public PipMenuView(Context context, PhonePipMenuController phonePipMenuController, ShellExecutor shellExecutor, Handler handler, PipUiEventLogger pipUiEventLogger) {
        super(context, null, 0);
        final int i = 0;
        final int i2 = 1;
        this.mAllowMenuTimeout = true;
        this.mAllowTouches = true;
        this.mActions = new ArrayList();
        this.mMenuBgUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.pip.phone.PipMenuView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                PipMenuView.this.mBackgroundDrawable.setAlpha((int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 0.54f * 255.0f));
            }
        };
        this.mHideMenuRunnable = new PipMenuView$$ExternalSyntheticLambda0(0, this);
        ((FrameLayout) this).mContext = context;
        this.mController = phonePipMenuController;
        this.mMainExecutor = shellExecutor;
        this.mMainHandler = handler;
        this.mPipUiEventLogger = pipUiEventLogger;
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService(AccessibilityManager.class);
        FrameLayout.inflate(context, R.layout.pip_menu, this);
        this.mPipForceCloseDelay = context.getResources().getInteger(R.integer.config_pipForceCloseDelay);
        Drawable drawable = ((FrameLayout) this).mContext.getDrawable(R.drawable.pip_menu_background);
        this.mBackgroundDrawable = drawable;
        drawable.setAlpha(0);
        View findViewById = findViewById(R.id.background);
        findViewById.setBackground(drawable);
        View findViewById2 = findViewById(R.id.menu_container);
        this.mMenuContainer = findViewById2;
        findViewById2.setAlpha(0.0f);
        View findViewById3 = findViewById(R.id.top_end_container);
        View findViewById4 = findViewById(R.id.settings);
        this.mSettingsButton = findViewById4;
        findViewById4.setAlpha(0.0f);
        findViewById4.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.wm.shell.pip.phone.PipMenuView$$ExternalSyntheticLambda1
            public final /* synthetic */ PipMenuView f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i;
                PipMenuView pipMenuView = this.f$0;
                switch (i3) {
                    case 0:
                        PipMenuView.m905$r8$lambda$kh7O2Rmd0Daiu_MbPcAw5t76mc(pipMenuView, view);
                        break;
                    case 1:
                        if (pipMenuView.mMenuState != 0) {
                            pipMenuView.mController.mListeners.forEach(new PhonePipMenuController$$ExternalSyntheticLambda2(0));
                            pipMenuView.mPipUiEventLogger.log(PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_TAP_TO_REMOVE);
                            break;
                        }
                        break;
                    default:
                        if (pipMenuView.mMenuContainer.getAlpha() != 0.0f) {
                            PhonePipMenuController phonePipMenuController2 = pipMenuView.mController;
                            Objects.requireNonNull(phonePipMenuController2);
                            pipMenuView.hideMenu(new PipMenuView$$ExternalSyntheticLambda0(3, phonePipMenuController2), false, true, 1);
                            pipMenuView.mPipUiEventLogger.log(PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_EXPAND_TO_FULLSCREEN);
                            break;
                        }
                        break;
                }
            }
        });
        View findViewById5 = findViewById(R.id.dismiss);
        this.mDismissButton = findViewById5;
        findViewById5.setAlpha(0.0f);
        findViewById5.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.wm.shell.pip.phone.PipMenuView$$ExternalSyntheticLambda1
            public final /* synthetic */ PipMenuView f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i2;
                PipMenuView pipMenuView = this.f$0;
                switch (i3) {
                    case 0:
                        PipMenuView.m905$r8$lambda$kh7O2Rmd0Daiu_MbPcAw5t76mc(pipMenuView, view);
                        break;
                    case 1:
                        if (pipMenuView.mMenuState != 0) {
                            pipMenuView.mController.mListeners.forEach(new PhonePipMenuController$$ExternalSyntheticLambda2(0));
                            pipMenuView.mPipUiEventLogger.log(PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_TAP_TO_REMOVE);
                            break;
                        }
                        break;
                    default:
                        if (pipMenuView.mMenuContainer.getAlpha() != 0.0f) {
                            PhonePipMenuController phonePipMenuController2 = pipMenuView.mController;
                            Objects.requireNonNull(phonePipMenuController2);
                            pipMenuView.hideMenu(new PipMenuView$$ExternalSyntheticLambda0(3, phonePipMenuController2), false, true, 1);
                            pipMenuView.mPipUiEventLogger.log(PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_EXPAND_TO_FULLSCREEN);
                            break;
                        }
                        break;
                }
            }
        });
        final int i3 = 2;
        findViewById(R.id.expand_button).setOnClickListener(new View.OnClickListener(this) { // from class: com.android.wm.shell.pip.phone.PipMenuView$$ExternalSyntheticLambda1
            public final /* synthetic */ PipMenuView f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i32 = i3;
                PipMenuView pipMenuView = this.f$0;
                switch (i32) {
                    case 0:
                        PipMenuView.m905$r8$lambda$kh7O2Rmd0Daiu_MbPcAw5t76mc(pipMenuView, view);
                        break;
                    case 1:
                        if (pipMenuView.mMenuState != 0) {
                            pipMenuView.mController.mListeners.forEach(new PhonePipMenuController$$ExternalSyntheticLambda2(0));
                            pipMenuView.mPipUiEventLogger.log(PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_TAP_TO_REMOVE);
                            break;
                        }
                        break;
                    default:
                        if (pipMenuView.mMenuContainer.getAlpha() != 0.0f) {
                            PhonePipMenuController phonePipMenuController2 = pipMenuView.mController;
                            Objects.requireNonNull(phonePipMenuController2);
                            pipMenuView.hideMenu(new PipMenuView$$ExternalSyntheticLambda0(3, phonePipMenuController2), false, true, 1);
                            pipMenuView.mPipUiEventLogger.log(PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_EXPAND_TO_FULLSCREEN);
                            break;
                        }
                        break;
                }
            }
        });
        findViewById(R.id.resize_handle).setAlpha(0.0f);
        this.mActionsGroup = (LinearLayout) findViewById(R.id.actions_group);
        this.mBetweenActionPaddingLand = getResources().getDimensionPixelSize(R.dimen.pip_between_action_padding_land);
        this.mPipMenuIconsAlgorithm = new PipMenuIconsAlgorithm();
        findViewById(R.id.resize_handle);
        this.mDismissFadeOutDurationMs = context.getResources().getInteger(R.integer.config_pipExitAnimationDuration);
        setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.wm.shell.pip.phone.PipMenuView.2
            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, PipMenuView.this.getResources().getString(R.string.pip_menu_title)));
            }

            @Override // android.view.View.AccessibilityDelegate
            public final boolean performAccessibilityAction(View view, int i4, Bundle bundle) {
                if (i4 == 16) {
                    PipMenuView pipMenuView = PipMenuView.this;
                    if (pipMenuView.mMenuState != 1) {
                        pipMenuView.mController.mListeners.forEach(new PhonePipMenuController$$ExternalSyntheticLambda2(1));
                    }
                }
                return super.performAccessibilityAction(view, i4, bundle);
            }
        });
        int currentUser = ActivityManager.getCurrentUser();
        if (((FrameLayout) this).mContext.getUserId() == currentUser) {
            this.mContextForUser = ((FrameLayout) this).mContext;
            return;
        }
        try {
            Context context2 = ((FrameLayout) this).mContext;
            this.mContextForUser = context2.createPackageContextAsUser(context2.getPackageName(), 4, new UserHandle(currentUser));
        } catch (PackageManager.NameNotFoundException e) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
                ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 6852099901306191849L, 20, "PipMenuView", Long.valueOf(((FrameLayout) this).mContext.getUserId()), Long.valueOf(currentUser), String.valueOf(e));
            }
            this.mContextForUser = ((FrameLayout) this).mContext;
        }
    }

    @Override // android.view.View
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if (this.mAllowMenuTimeout) {
            repostDelayedHide(2000);
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.mAllowTouches) {
            return false;
        }
        if (this.mAllowMenuTimeout) {
            repostDelayedHide(2000);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void hideMenu(final PipMenuView$$ExternalSyntheticLambda0 pipMenuView$$ExternalSyntheticLambda0, final boolean z, boolean z2, int i) {
        long j;
        if (this.mMenuState != 0) {
            ((HandlerExecutor) this.mMainExecutor).removeCallbacks(this.mHideMenuRunnable);
            if (z) {
                notifyMenuStateChangeStart(0, z2, null);
            }
            this.mMenuContainerAnimator = new AnimatorSet();
            View view = this.mMenuContainer;
            Property property = View.ALPHA;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, view.getAlpha(), 0.0f);
            ofFloat.addUpdateListener(this.mMenuBgUpdateListener);
            View view2 = this.mSettingsButton;
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) property, view2.getAlpha(), 0.0f);
            View view3 = this.mDismissButton;
            this.mMenuContainerAnimator.playTogether(ofFloat, ofFloat2, ObjectAnimator.ofFloat(view3, (Property<View, Float>) property, view3.getAlpha(), 0.0f));
            this.mMenuContainerAnimator.setInterpolator(Interpolators.ALPHA_OUT);
            AnimatorSet animatorSet = this.mMenuContainerAnimator;
            if (i == 0) {
                j = 0;
            } else if (i == 1) {
                j = 125;
            } else {
                if (i != 2) {
                    throw new IllegalStateException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Invalid animation type "));
                }
                j = this.mDismissFadeOutDurationMs;
            }
            animatorSet.setDuration(j);
            this.mMenuContainerAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.pip.phone.PipMenuView.4
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    PipMenuView.this.setVisibility(8);
                    if (z) {
                        PipMenuView pipMenuView = PipMenuView.this;
                        int i2 = 0;
                        pipMenuView.mMenuState = 0;
                        PhonePipMenuController phonePipMenuController = pipMenuView.mController;
                        if (phonePipMenuController.mMenuState != 0) {
                            phonePipMenuController.mListeners.forEach(new PhonePipMenuController$$ExternalSyntheticLambda1(i2));
                        }
                        phonePipMenuController.mMenuState = 0;
                        phonePipMenuController.mSystemWindows.setShellRootAccessibilityWindow(null);
                    }
                    PipMenuView$$ExternalSyntheticLambda0 pipMenuView$$ExternalSyntheticLambda02 = pipMenuView$$ExternalSyntheticLambda0;
                    if (pipMenuView$$ExternalSyntheticLambda02 != null) {
                        pipMenuView$$ExternalSyntheticLambda02.run();
                    }
                }
            });
            this.mMenuContainerAnimator.start();
        }
    }

    public final void hideMenu$1() {
        hideMenu(null, true, this.mDidLastShowMenuResize, 1);
    }

    public final void notifyMenuStateChangeStart(final int i, final boolean z, final PipMenuView$$ExternalSyntheticLambda0 pipMenuView$$ExternalSyntheticLambda0) {
        InputTransferToken inputTransferToken;
        PhonePipMenuController phonePipMenuController = this.mController;
        if (i != phonePipMenuController.mMenuState) {
            phonePipMenuController.mListeners.forEach(new Consumer() { // from class: com.android.wm.shell.pip.phone.PhonePipMenuController$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    int rotation;
                    int i2 = i;
                    boolean z2 = z;
                    PipMenuView$$ExternalSyntheticLambda0 pipMenuView$$ExternalSyntheticLambda02 = pipMenuView$$ExternalSyntheticLambda0;
                    PipTouchHandler pipTouchHandler = PipTouchHandler.this;
                    int i3 = pipTouchHandler.mMenuState;
                    if (i3 != i2 || z2) {
                        if (i2 == 1 && i3 != 1) {
                            if (z2) {
                                pipTouchHandler.animateToNormalSize(pipMenuView$$ExternalSyntheticLambda02);
                            }
                        } else if (i2 == 0 && i3 == 1) {
                            if (!z2 || pipTouchHandler.mPipResizeGestureHandler.mAllowGesture) {
                                pipTouchHandler.mSavedSnapFraction = -1.0f;
                                return;
                            }
                            if (pipTouchHandler.mDeferResizeToNormalBoundsUntilRotation == -1 && pipTouchHandler.mDisplayRotation != (rotation = pipTouchHandler.mContext.getDisplay().getRotation())) {
                                pipTouchHandler.mDeferResizeToNormalBoundsUntilRotation = rotation;
                            }
                            if (pipTouchHandler.mDeferResizeToNormalBoundsUntilRotation == -1) {
                                pipTouchHandler.animateToUnexpandedState(pipTouchHandler.mPipResizeGestureHandler.mUserResizeBounds);
                            }
                        }
                    }
                }
            });
            PhonePipMenuController.AnonymousClass1 anonymousClass1 = phonePipMenuController.mMediaActionListener;
            PipMediaController pipMediaController = phonePipMenuController.mMediaController;
            boolean z2 = true;
            if (i != 1) {
                pipMediaController.getClass();
                anonymousClass1.onMediaActionsChanged(EmptyList.INSTANCE);
                pipMediaController.mActionListeners.remove(anonymousClass1);
            } else if (!pipMediaController.mActionListeners.contains(anonymousClass1)) {
                pipMediaController.mActionListeners.add(anonymousClass1);
                anonymousClass1.onMediaActionsChanged(pipMediaController.getMediaActions());
            }
            try {
                IWindowSession windowSession = WindowManagerGlobal.getWindowSession();
                SystemWindows systemWindows = phonePipMenuController.mSystemWindows;
                PipMenuView pipMenuView = phonePipMenuController.mPipMenuView;
                SurfaceControlViewHost surfaceControlViewHost = (SurfaceControlViewHost) systemWindows.mViewRoots.get(pipMenuView);
                if (surfaceControlViewHost == null) {
                    Slog.e("SystemWindows", "Couldn't get focus grant token since view does not exist in SystemWindow:" + pipMenuView);
                    inputTransferToken = null;
                } else {
                    inputTransferToken = surfaceControlViewHost.getInputTransferToken();
                }
                if (i == 0) {
                    z2 = false;
                }
                windowSession.grantEmbeddedWindowFocus((IWindow) null, inputTransferToken, z2);
            } catch (RemoteException e) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                    ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 961883302679884611L, 0, "PhonePipMenuController", String.valueOf(e));
                }
            }
        }
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 111) {
            return super.onKeyUp(i, keyEvent);
        }
        hideMenu$1();
        return true;
    }

    public final void repostDelayedHide(int i) {
        int recommendedTimeoutMillis = this.mAccessibilityManager.getRecommendedTimeoutMillis(i, 5);
        ((HandlerExecutor) this.mMainExecutor).removeCallbacks(this.mHideMenuRunnable);
        ((HandlerExecutor) this.mMainExecutor).executeDelayed(this.mHideMenuRunnable, recommendedTimeoutMillis);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return true;
    }

    public final void updateActionViews(int i, Rect rect) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.expand_container);
        ViewGroup viewGroup2 = (ViewGroup) findViewById(R.id.actions_container);
        viewGroup2.setOnTouchListener(new PipMenuView$$ExternalSyntheticLambda4());
        viewGroup.setVisibility(i == 1 ? 0 : 4);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewGroup.getLayoutParams();
        if (this.mActions.isEmpty() || i == 0) {
            viewGroup2.setVisibility(4);
            layoutParams.topMargin = 0;
            layoutParams.bottomMargin = 0;
        } else {
            viewGroup2.setVisibility(0);
            if (this.mActionsGroup != null) {
                LayoutInflater from = LayoutInflater.from(((FrameLayout) this).mContext);
                while (this.mActionsGroup.getChildCount() < ((ArrayList) this.mActions).size()) {
                    this.mActionsGroup.addView((PipMenuActionView) from.inflate(R.layout.pip_menu_action, (ViewGroup) this.mActionsGroup, false));
                }
                int i2 = 0;
                while (true) {
                    int i3 = 8;
                    if (i2 >= this.mActionsGroup.getChildCount()) {
                        break;
                    }
                    View childAt = this.mActionsGroup.getChildAt(i2);
                    if (i2 < ((ArrayList) this.mActions).size()) {
                        i3 = 0;
                    }
                    childAt.setVisibility(i3);
                    i2++;
                }
                boolean z = rect.width() > rect.height();
                int i4 = 0;
                while (i4 < ((ArrayList) this.mActions).size()) {
                    final RemoteAction remoteAction = (RemoteAction) ((ArrayList) this.mActions).get(i4);
                    final PipMenuActionView pipMenuActionView = (PipMenuActionView) this.mActionsGroup.getChildAt(i4);
                    RemoteAction remoteAction2 = this.mCloseAction;
                    final boolean z2 = remoteAction2 != null && Objects.equals(remoteAction2.getActionIntent(), remoteAction.getActionIntent());
                    int type = remoteAction.getIcon().getType();
                    if (type == 4 || type == 6) {
                        pipMenuActionView.mImageView.setImageDrawable(null);
                    } else {
                        remoteAction.getIcon().loadDrawableAsync(this.mContextForUser, new Icon.OnDrawableLoadedListener() { // from class: com.android.wm.shell.pip.phone.PipMenuView$$ExternalSyntheticLambda5
                            @Override // android.graphics.drawable.Icon.OnDrawableLoadedListener
                            public final void onDrawableLoaded(Drawable drawable) {
                                PipMenuActionView pipMenuActionView2 = PipMenuActionView.this;
                                if (drawable != null) {
                                    drawable.setTint(-1);
                                    pipMenuActionView2.mImageView.setImageDrawable(drawable);
                                }
                            }
                        }, this.mMainHandler);
                    }
                    pipMenuActionView.mCustomCloseBackground.setVisibility(z2 ? 0 : 8);
                    pipMenuActionView.setContentDescription(remoteAction.getContentDescription());
                    if (remoteAction.isEnabled()) {
                        pipMenuActionView.setOnClickListener(new View.OnClickListener() { // from class: com.android.wm.shell.pip.phone.PipMenuView$$ExternalSyntheticLambda6
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                PipMenuView pipMenuView = PipMenuView.this;
                                RemoteAction remoteAction3 = remoteAction;
                                boolean z3 = z2;
                                pipMenuView.getClass();
                                try {
                                    remoteAction3.getActionIntent().send();
                                } catch (PendingIntent.CanceledException e) {
                                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
                                        ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 4958524292074978807L, 0, "PipMenuView", String.valueOf(e));
                                    }
                                }
                                if (z3) {
                                    pipMenuView.mPipUiEventLogger.log(PipUiEventLogger.PipUiEventEnum.PICTURE_IN_PICTURE_CUSTOM_CLOSE);
                                    pipMenuView.mAllowTouches = false;
                                    ((HandlerExecutor) pipMenuView.mMainExecutor).executeDelayed(new PipMenuView$$ExternalSyntheticLambda0(1, pipMenuView), pipMenuView.mPipForceCloseDelay);
                                }
                            }
                        });
                    }
                    pipMenuActionView.setEnabled(remoteAction.isEnabled());
                    pipMenuActionView.setAlpha(remoteAction.isEnabled() ? 1.0f : 0.54f);
                    ((LinearLayout.LayoutParams) pipMenuActionView.getLayoutParams()).leftMargin = (!z || i4 <= 0) ? 0 : this.mBetweenActionPaddingLand;
                    i4++;
                }
            }
            layoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.pip_action_padding);
            layoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.pip_expand_container_edge_margin);
        }
        viewGroup.requestLayout();
    }
}
