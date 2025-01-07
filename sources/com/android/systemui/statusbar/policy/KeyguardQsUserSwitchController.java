package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.KeyguardConstants;
import com.android.keyguard.KeyguardVisibilityHelper;
import com.android.settingslib.drawable.CircleFramedDrawable;
import com.android.settingslib.drawable.UserIconDrawable;
import com.android.systemui.animation.DialogCuj;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.DialogTransitionAnimator$createActivityTransitionController$1;
import com.android.systemui.animation.Expandable$Companion$fromView$1;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.PseudoGridView;
import com.android.systemui.qs.QSUserSwitcherEvent;
import com.android.systemui.qs.tiles.UserDetailView$Adapter;
import com.android.systemui.qs.user.UserSwitchDialogController;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.LockscreenGestureLogger;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.phone.UserAvatarView;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.user.data.source.UserRecord;
import com.android.systemui.user.ui.dialog.DialogShowerImpl;
import com.android.systemui.util.ViewController;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardQsUserSwitchController extends ViewController {
    public static final AnimationProperties ANIMATION_PROPERTIES;
    public static final boolean DEBUG = KeyguardConstants.DEBUG;
    public AnonymousClass4 mAdapter;
    public final ConfigurationController mConfigurationController;
    public final AnonymousClass2 mConfigurationListener;
    public final Context mContext;
    public UserRecord mCurrentUser;
    public final AnonymousClass6 mDataSetObserver;
    public final FalsingManager mFalsingManager;
    public boolean mIsKeyguardShowing;
    public final AnonymousClass3 mKeyguardStateCallback;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardVisibilityHelper mKeyguardVisibilityHelper;
    public final Resources mResources;
    public final SysuiStatusBarStateController mStatusBarStateController;
    public final AnonymousClass1 mStatusBarStateListener;
    public final UiEventLogger mUiEventLogger;
    UserAvatarView mUserAvatarView;
    public View mUserAvatarViewWithBackground;
    public final UserSwitchDialogController mUserSwitchDialogController;
    public final UserSwitcherController mUserSwitcherController;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController$4, reason: invalid class name */
    public final class AnonymousClass4 extends BaseUserSwitcherAdapter {
        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController$6, reason: invalid class name */
    public final class AnonymousClass6 extends DataSetObserver {
        public AnonymousClass6() {
        }

        @Override // android.database.DataSetObserver
        public final void onChanged() {
            if (!KeyguardQsUserSwitchController.this.updateCurrentUser()) {
                KeyguardQsUserSwitchController keyguardQsUserSwitchController = KeyguardQsUserSwitchController.this;
                if (!keyguardQsUserSwitchController.mIsKeyguardShowing) {
                    return;
                }
                UserIconDrawable userIconDrawable = keyguardQsUserSwitchController.mUserAvatarView.mDrawable;
                if (userIconDrawable.getUserIcon() != null || userIconDrawable.getUserDrawable() != null) {
                    return;
                }
            }
            KeyguardQsUserSwitchController.this.updateView$2();
        }
    }

    static {
        AnimationProperties animationProperties = new AnimationProperties();
        animationProperties.duration = 360L;
        ANIMATION_PROPERTIES = animationProperties;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController$1] */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController$2] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController$3] */
    public KeyguardQsUserSwitchController(FrameLayout frameLayout, Context context, Resources resources, UserSwitcherController userSwitcherController, KeyguardStateController keyguardStateController, FalsingManager falsingManager, ConfigurationController configurationController, SysuiStatusBarStateController sysuiStatusBarStateController, ScreenOffAnimationController screenOffAnimationController, UserSwitchDialogController userSwitchDialogController, UiEventLogger uiEventLogger) {
        super(frameLayout);
        this.mStatusBarStateListener = new StatusBarStateController.StateListener() { // from class: com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController.1
            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onStateChanged(int i) {
                KeyguardQsUserSwitchController keyguardQsUserSwitchController = KeyguardQsUserSwitchController.this;
                ((StatusBarStateControllerImpl) keyguardQsUserSwitchController.mStatusBarStateController).getClass();
                boolean z = ((KeyguardStateControllerImpl) keyguardQsUserSwitchController.mKeyguardStateController).mKeyguardFadingAway;
                keyguardQsUserSwitchController.mKeyguardVisibilityHelper.log("Ignoring KeyguardVisibilityelper, migrateClocksToBlueprint flag on");
            }
        };
        this.mConfigurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController.2
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onUiModeChanged() {
                KeyguardQsUserSwitchController keyguardQsUserSwitchController = KeyguardQsUserSwitchController.this;
                if (keyguardQsUserSwitchController.mIsKeyguardShowing) {
                    keyguardQsUserSwitchController.updateView$2();
                }
            }
        };
        this.mKeyguardStateCallback = new KeyguardStateController.Callback() { // from class: com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController.3
            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onKeyguardFadingAwayChanged() {
                KeyguardQsUserSwitchController.this.updateKeyguardShowing(false);
            }

            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onKeyguardShowingChanged() {
                KeyguardQsUserSwitchController.this.updateKeyguardShowing(false);
            }

            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onUnlockedChanged() {
                KeyguardQsUserSwitchController.this.updateKeyguardShowing(false);
            }
        };
        this.mDataSetObserver = new AnonymousClass6();
        if (DEBUG) {
            Log.d("KeyguardQsUserSwitchController", "New KeyguardQsUserSwitchController");
        }
        this.mContext = context;
        this.mResources = resources;
        this.mUserSwitcherController = userSwitcherController;
        this.mKeyguardStateController = keyguardStateController;
        this.mFalsingManager = falsingManager;
        this.mConfigurationController = configurationController;
        this.mStatusBarStateController = sysuiStatusBarStateController;
        this.mKeyguardVisibilityHelper = new KeyguardVisibilityHelper(frameLayout, keyguardStateController, screenOffAnimationController, false, null);
        this.mUserSwitchDialogController = userSwitchDialogController;
        this.mUiEventLogger = uiEventLogger;
    }

    public final int getUserIconHeight() {
        return this.mUserAvatarView.getHeight();
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        if (DEBUG) {
            Log.d("KeyguardQsUserSwitchController", "onInit");
        }
        this.mUserAvatarView = (UserAvatarView) ((FrameLayout) this.mView).findViewById(R.id.kg_multi_user_avatar);
        this.mUserAvatarViewWithBackground = ((FrameLayout) this.mView).findViewById(R.id.kg_multi_user_avatar_with_background);
        this.mAdapter = new AnonymousClass4(this.mUserSwitcherController);
        this.mUserAvatarView.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KeyguardQsUserSwitchController keyguardQsUserSwitchController = KeyguardQsUserSwitchController.this;
                if (keyguardQsUserSwitchController.mFalsingManager.isFalseTap(1)) {
                    return;
                }
                keyguardQsUserSwitchController.mKeyguardVisibilityHelper.getClass();
                keyguardQsUserSwitchController.mUiEventLogger.log(LockscreenGestureLogger.LockscreenUiEvent.LOCKSCREEN_SWITCH_USER_TAP);
                keyguardQsUserSwitchController.mUserAvatarViewWithBackground.getContext();
                Expandable$Companion$fromView$1 expandable$Companion$fromView$1 = new Expandable$Companion$fromView$1(keyguardQsUserSwitchController.mUserAvatarViewWithBackground);
                final UserSwitchDialogController userSwitchDialogController = keyguardQsUserSwitchController.mUserSwitchDialogController;
                final SystemUIDialog create = userSwitchDialogController.dialogFactory.create();
                SystemUIDialog.setShowForAllUsers(create);
                create.setCanceledOnTouchOutside(true);
                create.setTitle(R.string.qs_user_switch_dialog_title);
                create.setPositiveButton(R.string.quick_settings_done, new DialogInterface.OnClickListener() { // from class: com.android.systemui.qs.user.UserSwitchDialogController$showDialog$1$1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        UserSwitchDialogController.this.uiEventLogger.log(QSUserSwitcherEvent.QS_USER_DETAIL_CLOSE);
                    }
                });
                create.setNeutralButton(R.string.quick_settings_more_user_settings, new DialogInterface.OnClickListener() { // from class: com.android.systemui.qs.user.UserSwitchDialogController$showDialog$1$2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        if (UserSwitchDialogController.this.falsingManager.isFalseTap(1)) {
                            return;
                        }
                        UserSwitchDialogController.this.uiEventLogger.log(QSUserSwitcherEvent.QS_USER_MORE_SETTINGS);
                        DialogTransitionAnimator$createActivityTransitionController$1 createActivityTransitionController$default = DialogTransitionAnimator.createActivityTransitionController$default(UserSwitchDialogController.this.dialogTransitionAnimator, create.getButton(-3));
                        if (createActivityTransitionController$default == null) {
                            create.dismiss();
                        }
                        UserSwitchDialogController.this.activityStarter.postStartActivityDismissingKeyguard(UserSwitchDialogController.USER_SETTINGS_INTENT, 0, createActivityTransitionController$default);
                    }
                }, false);
                View inflate = LayoutInflater.from(create.getContext()).inflate(R.layout.qs_user_dialog_content, (ViewGroup) null);
                create.setView(inflate);
                UserDetailView$Adapter userDetailView$Adapter = (UserDetailView$Adapter) userSwitchDialogController.userDetailViewAdapterProvider.get();
                ViewGroup viewGroup = (ViewGroup) inflate.findViewById(R.id.grid);
                userDetailView$Adapter.getClass();
                new PseudoGridView.ViewGroupAdapterBridge(viewGroup, userDetailView$Adapter);
                DialogTransitionAnimator.Controller dialogTransitionController = expandable$Companion$fromView$1.dialogTransitionController(new DialogCuj(58, "switch_user"));
                DialogTransitionAnimator dialogTransitionAnimator = userSwitchDialogController.dialogTransitionAnimator;
                if (dialogTransitionController != null) {
                    TransitionAnimator.Timings timings = DialogTransitionAnimator.TIMINGS;
                    dialogTransitionAnimator.show(create, dialogTransitionController, false);
                } else {
                    create.show();
                }
                userSwitchDialogController.uiEventLogger.log(QSUserSwitcherEvent.QS_USER_DETAIL_OPEN);
                userDetailView$Adapter.mDialogShower = new DialogShowerImpl(create, dialogTransitionAnimator);
            }
        });
        this.mUserAvatarView.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.android.systemui.statusbar.policy.KeyguardQsUserSwitchController.5
            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, KeyguardQsUserSwitchController.this.mContext.getString(R.string.accessibility_quick_settings_choose_user_action)));
            }
        });
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        if (DEBUG) {
            Log.d("KeyguardQsUserSwitchController", "onViewAttached");
        }
        AnonymousClass4 anonymousClass4 = this.mAdapter;
        AnonymousClass6 anonymousClass6 = this.mDataSetObserver;
        anonymousClass4.registerDataSetObserver(anonymousClass6);
        anonymousClass6.onChanged();
        ((StatusBarStateControllerImpl) this.mStatusBarStateController).addCallback((StatusBarStateController.StateListener) this.mStatusBarStateListener);
        ((ConfigurationControllerImpl) this.mConfigurationController).addCallback(this.mConfigurationListener);
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).addCallback(this.mKeyguardStateCallback);
        updateCurrentUser();
        updateKeyguardShowing(true);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        if (DEBUG) {
            Log.d("KeyguardQsUserSwitchController", "onViewDetached");
        }
        this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
        ((StatusBarStateControllerImpl) this.mStatusBarStateController).removeCallback((StatusBarStateController.StateListener) this.mStatusBarStateListener);
        ((ConfigurationControllerImpl) this.mConfigurationController).removeCallback(this.mConfigurationListener);
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).removeCallback(this.mKeyguardStateCallback);
    }

    public final boolean updateCurrentUser() {
        UserRecord userRecord = this.mCurrentUser;
        this.mCurrentUser = null;
        for (int i = 0; i < this.mAdapter.getCount(); i++) {
            UserRecord item = this.mAdapter.getItem(i);
            if (item.isCurrent) {
                this.mCurrentUser = item;
                return !item.equals(userRecord);
            }
        }
        return this.mCurrentUser == null && userRecord != null;
    }

    public void updateKeyguardShowing(boolean z) {
        boolean z2 = this.mIsKeyguardShowing;
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        boolean z3 = keyguardStateControllerImpl.mShowing || keyguardStateControllerImpl.mKeyguardGoingAway;
        this.mIsKeyguardShowing = z3;
        if (z2 != z3 || z) {
            boolean z4 = DEBUG;
            if (z4) {
                Log.d("KeyguardQsUserSwitchController", "updateKeyguardShowing: mIsKeyguardShowing=" + this.mIsKeyguardShowing + " forceViewUpdate=" + z);
            }
            if (this.mIsKeyguardShowing) {
                updateView$2();
                return;
            }
            if (z4) {
                Log.d("KeyguardQsUserSwitchController", "clearAvatar");
            }
            UserAvatarView userAvatarView = this.mUserAvatarView;
            userAvatarView.mDrawable.setIcon(null);
            userAvatarView.mDrawable.setBadge(null);
        }
    }

    public final void updateView$2() {
        Drawable drawable;
        UserInfo userInfo;
        UserInfo userInfo2;
        if (DEBUG) {
            Log.d("KeyguardQsUserSwitchController", "updateView");
        }
        UserAvatarView userAvatarView = this.mUserAvatarView;
        UserRecord userRecord = this.mCurrentUser;
        userAvatarView.setContentDescription((userRecord == null || (userInfo2 = userRecord.info) == null || TextUtils.isEmpty(userInfo2.name)) ? this.mContext.getString(R.string.accessibility_multi_user_switch_switcher) : this.mContext.getString(R.string.accessibility_quick_settings_user, this.mCurrentUser.info.name));
        UserRecord userRecord2 = this.mCurrentUser;
        int i = -10000;
        if (userRecord2 != null && !userRecord2.isGuest && (userInfo = userRecord2.info) != null) {
            i = userInfo.id;
        }
        UserAvatarView userAvatarView2 = this.mUserAvatarView;
        if (userRecord2 == null || userRecord2.picture == null) {
            drawable = (userRecord2 == null || !userRecord2.isGuest) ? this.mContext.getDrawable(R.drawable.ic_avatar_user) : this.mContext.getDrawable(R.drawable.ic_avatar_guest_user);
            drawable.setTint(this.mResources.getColor(R.color.kg_user_switcher_avatar_icon_color, this.mContext.getTheme()));
        } else {
            drawable = new CircleFramedDrawable(this.mCurrentUser.picture, (int) this.mResources.getDimension(R.dimen.kg_framed_avatar_size));
        }
        userAvatarView2.setDrawableWithBadge(i, new LayerDrawable(new Drawable[]{this.mContext.getDrawable(R.drawable.user_avatar_bg), drawable}).mutate());
    }
}
