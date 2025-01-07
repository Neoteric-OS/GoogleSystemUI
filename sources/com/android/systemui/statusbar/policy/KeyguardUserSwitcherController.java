package com.android.systemui.statusbar.policy;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.graphics.ColorUtils;
import com.android.keyguard.KeyguardConstants;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.KeyguardVisibilityHelper;
import com.android.settingslib.drawable.CircleFramedDrawable;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.stack.AnimationProperties;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.user.data.model.UserSwitcherSettingsModel;
import com.android.systemui.user.data.source.UserRecord;
import com.android.systemui.util.ViewController;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardUserSwitcherController extends ViewController {
    public static final AnimationProperties ANIMATION_PROPERTIES;
    public static final boolean DEBUG = KeyguardConstants.DEBUG;
    public final KeyguardUserAdapter mAdapter;
    public final KeyguardUserSwitcherScrim mBackground;
    public ObjectAnimator mBgAnimator;
    public float mDarkAmount;
    public final AnonymousClass4 mDataSetObserver;
    public final KeyguardUpdateMonitorCallback mInfoCallback;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final KeyguardVisibilityHelper mKeyguardVisibilityHelper;
    public KeyguardUserSwitcherListView mListView;
    public final ScreenLifecycle mScreenLifecycle;
    public final AnonymousClass2 mScreenObserver;
    public final SysuiStatusBarStateController mStatusBarStateController;
    public final AnonymousClass3 mStatusBarStateListener;
    public final UserSwitcherController mUserSwitcherController;
    public boolean mUserSwitcherOpen;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class KeyguardUserAdapter extends BaseUserSwitcherAdapter implements View.OnClickListener {
        public final Context mContext;
        public final KeyguardUserSwitcherController mKeyguardUserSwitcherController;
        public final LayoutInflater mLayoutInflater;
        public final Resources mResources;
        public ArrayList mUsersOrdered;

        public KeyguardUserAdapter(Context context, Resources resources, LayoutInflater layoutInflater, UserSwitcherController userSwitcherController, KeyguardUserSwitcherController keyguardUserSwitcherController) {
            super(userSwitcherController);
            this.mUsersOrdered = new ArrayList();
            this.mContext = context;
            this.mResources = resources;
            this.mLayoutInflater = layoutInflater;
            this.mKeyguardUserSwitcherController = keyguardUserSwitcherController;
        }

        @Override // com.android.systemui.statusbar.policy.BaseUserSwitcherAdapter
        public final List getUsers() {
            return this.mUsersOrdered;
        }

        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            UserInfo userInfo;
            UserRecord item = getItem(i);
            if (!(view instanceof KeyguardUserDetailItemView) || !(view.getTag() instanceof UserRecord)) {
                view = this.mLayoutInflater.inflate(R.layout.keyguard_user_switcher_item, viewGroup, false);
            }
            KeyguardUserDetailItemView keyguardUserDetailItemView = (KeyguardUserDetailItemView) view;
            keyguardUserDetailItemView.setOnClickListener(this);
            String name = getName(this.mContext, item);
            Bitmap bitmap = item.picture;
            boolean z = item.isCurrent;
            boolean z2 = item.isSwitchToEnabled;
            if (bitmap == null) {
                boolean z3 = item.isGuest;
                Drawable drawable = (z && z3) ? this.mContext.getDrawable(R.drawable.ic_avatar_guest_user) : BaseUserSwitcherAdapter.getIconDrawable(this.mContext, item);
                drawable.setTint(this.mResources.getColor(z2 ? R.color.kg_user_switcher_avatar_icon_color : R.color.kg_user_switcher_restricted_avatar_icon_color, this.mContext.getTheme()));
                Drawable mutate = new LayerDrawable(new Drawable[]{this.mContext.getDrawable(R.drawable.user_avatar_bg), drawable}).mutate();
                int i2 = (z3 || (userInfo = item.info) == null) ? -10000 : userInfo.id;
                keyguardUserDetailItemView.mName.setText(name);
                keyguardUserDetailItemView.mAvatar.setDrawableWithBadge(i2, mutate);
            } else {
                CircleFramedDrawable circleFramedDrawable = new CircleFramedDrawable(item.picture, (int) this.mResources.getDimension(R.dimen.kg_framed_avatar_size));
                circleFramedDrawable.setColorFilter(z2 ? null : (ColorFilter) BaseUserSwitcherAdapter.disabledUserAvatarColorFilter$delegate.getValue());
                int i3 = item.info.id;
                keyguardUserDetailItemView.mName.setText(name);
                keyguardUserDetailItemView.mAvatar.setDrawableWithBadge(i3, circleFramedDrawable);
            }
            keyguardUserDetailItemView.setActivated(z);
            boolean z4 = item.enforcedAdmin != null;
            View view2 = keyguardUserDetailItemView.mRestrictedPadlock;
            if (view2 != null) {
                view2.setVisibility(z4 ? 0 : 8);
            }
            keyguardUserDetailItemView.setEnabled(!z4);
            keyguardUserDetailItemView.setEnabled(z2);
            keyguardUserDetailItemView.setAlpha(keyguardUserDetailItemView.isEnabled() ? 1.0f : 0.38f);
            keyguardUserDetailItemView.setTag(item);
            return keyguardUserDetailItemView;
        }

        @Override // android.widget.BaseAdapter
        public final void notifyDataSetChanged() {
            ArrayList arrayList = (ArrayList) super.getUsers();
            this.mUsersOrdered = new ArrayList(arrayList.size());
            for (int i = 0; i < arrayList.size(); i++) {
                UserRecord userRecord = (UserRecord) arrayList.get(i);
                if (userRecord.isCurrent) {
                    this.mUsersOrdered.add(0, userRecord);
                } else {
                    this.mUsersOrdered.add(userRecord);
                }
            }
            super.notifyDataSetChanged();
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            UserRecord userRecord = (UserRecord) view.getTag();
            KeyguardUserSwitcherController keyguardUserSwitcherController = this.mKeyguardUserSwitcherController;
            keyguardUserSwitcherController.mKeyguardVisibilityHelper.getClass();
            if (keyguardUserSwitcherController.mListView.mAnimating) {
                return;
            }
            KeyguardUserSwitcherController keyguardUserSwitcherController2 = this.mKeyguardUserSwitcherController;
            if (!keyguardUserSwitcherController2.mUserSwitcherOpen) {
                keyguardUserSwitcherController2.setUserSwitcherOpened(true, true);
            } else if (!userRecord.isCurrent || userRecord.isGuest) {
                onUserListItemClicked(userRecord, null);
            } else {
                keyguardUserSwitcherController2.closeSwitcherIfOpenAndNotSimple(true);
            }
        }
    }

    static {
        AnimationProperties animationProperties = new AnimationProperties();
        animationProperties.duration = 360L;
        ANIMATION_PROPERTIES = animationProperties;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.statusbar.policy.KeyguardUserSwitcherController$2] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.statusbar.policy.KeyguardUserSwitcherController$3] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.systemui.statusbar.policy.KeyguardUserSwitcherController$4] */
    public KeyguardUserSwitcherController(KeyguardUserSwitcherView keyguardUserSwitcherView, Context context, Resources resources, LayoutInflater layoutInflater, ScreenLifecycle screenLifecycle, UserSwitcherController userSwitcherController, KeyguardStateController keyguardStateController, SysuiStatusBarStateController sysuiStatusBarStateController, KeyguardUpdateMonitor keyguardUpdateMonitor, ScreenOffAnimationController screenOffAnimationController) {
        super(keyguardUserSwitcherView);
        this.mInfoCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.statusbar.policy.KeyguardUserSwitcherController.1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onKeyguardVisibilityChanged(boolean z) {
                if (KeyguardUserSwitcherController.DEBUG) {
                    MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("onKeyguardVisibilityChanged ", "KeyguardUserSwitcherController", z);
                }
                if (z) {
                    return;
                }
                KeyguardUserSwitcherController.this.closeSwitcherIfOpenAndNotSimple(false);
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onUserSwitching(int i) {
                KeyguardUserSwitcherController.this.closeSwitcherIfOpenAndNotSimple(false);
            }
        };
        this.mScreenObserver = new ScreenLifecycle.Observer() { // from class: com.android.systemui.statusbar.policy.KeyguardUserSwitcherController.2
            @Override // com.android.systemui.keyguard.ScreenLifecycle.Observer
            public final void onScreenTurnedOff() {
                if (KeyguardUserSwitcherController.DEBUG) {
                    Log.d("KeyguardUserSwitcherController", "onScreenTurnedOff");
                }
                KeyguardUserSwitcherController.this.closeSwitcherIfOpenAndNotSimple(false);
            }
        };
        this.mStatusBarStateListener = new StatusBarStateController.StateListener() { // from class: com.android.systemui.statusbar.policy.KeyguardUserSwitcherController.3
            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onDozeAmountChanged(float f, float f2) {
                if (KeyguardUserSwitcherController.DEBUG) {
                    Log.d("KeyguardUserSwitcherController", String.format("onDozeAmountChanged: linearAmount=%f amount=%f", Float.valueOf(f), Float.valueOf(f2)));
                }
                KeyguardUserSwitcherController keyguardUserSwitcherController = KeyguardUserSwitcherController.this;
                keyguardUserSwitcherController.getClass();
                boolean z = f2 == 1.0f;
                if (f2 == keyguardUserSwitcherController.mDarkAmount) {
                    return;
                }
                keyguardUserSwitcherController.mDarkAmount = f2;
                KeyguardUserSwitcherListView keyguardUserSwitcherListView = keyguardUserSwitcherController.mListView;
                int childCount = keyguardUserSwitcherListView.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = keyguardUserSwitcherListView.getChildAt(i);
                    if (childAt instanceof KeyguardUserDetailItemView) {
                        KeyguardUserDetailItemView keyguardUserDetailItemView = (KeyguardUserDetailItemView) childAt;
                        if (keyguardUserDetailItemView.mDarkAmount != f2) {
                            keyguardUserDetailItemView.mDarkAmount = f2;
                            keyguardUserDetailItemView.mName.setTextColor(ColorUtils.blendARGB(keyguardUserDetailItemView.mTextColor, f2, -1));
                        }
                    }
                }
                if (z) {
                    keyguardUserSwitcherController.closeSwitcherIfOpenAndNotSimple(false);
                }
            }

            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onStateChanged(int i) {
                if (KeyguardUserSwitcherController.DEBUG) {
                    Log.d("KeyguardUserSwitcherController", String.format("onStateChanged: newState=%d", Integer.valueOf(i)));
                }
                KeyguardUserSwitcherController keyguardUserSwitcherController = KeyguardUserSwitcherController.this;
                ((StatusBarStateControllerImpl) keyguardUserSwitcherController.mStatusBarStateController).getClass();
                KeyguardStateController keyguardStateController2 = keyguardUserSwitcherController.mKeyguardStateController;
                boolean z = ((KeyguardStateControllerImpl) keyguardStateController2).mKeyguardFadingAway;
                if (((StatusBarStateControllerImpl) keyguardUserSwitcherController.mStatusBarStateController).goingToFullShade() || ((KeyguardStateControllerImpl) keyguardStateController2).mKeyguardFadingAway) {
                    keyguardUserSwitcherController.closeSwitcherIfOpenAndNotSimple(true);
                }
                keyguardUserSwitcherController.mKeyguardVisibilityHelper.log("Ignoring KeyguardVisibilityelper, migrateClocksToBlueprint flag on");
            }
        };
        this.mDataSetObserver = new DataSetObserver() { // from class: com.android.systemui.statusbar.policy.KeyguardUserSwitcherController.4
            @Override // android.database.DataSetObserver
            public final void onChanged() {
                KeyguardUserSwitcherController keyguardUserSwitcherController = KeyguardUserSwitcherController.this;
                int childCount = keyguardUserSwitcherController.mListView.getChildCount();
                KeyguardUserAdapter keyguardUserAdapter = keyguardUserSwitcherController.mAdapter;
                int count = keyguardUserAdapter.getCount();
                int max = Math.max(childCount, count);
                if (KeyguardUserSwitcherController.DEBUG) {
                    Log.d("KeyguardUserSwitcherController", String.format("refreshUserList childCount=%d adapterCount=%d", Integer.valueOf(childCount), Integer.valueOf(count)));
                }
                int i = 0;
                boolean z = false;
                while (i < max) {
                    if (i < count) {
                        View childAt = i < childCount ? keyguardUserSwitcherController.mListView.getChildAt(i) : null;
                        KeyguardUserDetailItemView keyguardUserDetailItemView = (KeyguardUserDetailItemView) keyguardUserAdapter.getView(i, childAt, keyguardUserSwitcherController.mListView);
                        UserRecord userRecord = (UserRecord) keyguardUserDetailItemView.getTag();
                        if (userRecord.isCurrent) {
                            if (i != 0) {
                                Log.w("KeyguardUserSwitcherController", "Current user is not the first view in the list");
                            }
                            int i2 = userRecord.info.id;
                            keyguardUserDetailItemView.updateVisibilities(true, keyguardUserSwitcherController.mUserSwitcherOpen, false);
                            z = true;
                        } else {
                            keyguardUserDetailItemView.updateVisibilities(keyguardUserSwitcherController.mUserSwitcherOpen, true, false);
                        }
                        float f = keyguardUserSwitcherController.mDarkAmount;
                        if (keyguardUserDetailItemView.mDarkAmount != f) {
                            keyguardUserDetailItemView.mDarkAmount = f;
                            keyguardUserDetailItemView.mName.setTextColor(ColorUtils.blendARGB(keyguardUserDetailItemView.mTextColor, f, -1));
                        }
                        if (childAt == null) {
                            keyguardUserSwitcherController.mListView.addView(keyguardUserDetailItemView);
                        } else if (childAt != keyguardUserDetailItemView) {
                            KeyguardUserSwitcherListView keyguardUserSwitcherListView = keyguardUserSwitcherController.mListView;
                            keyguardUserSwitcherListView.removeViewAt(i);
                            keyguardUserSwitcherListView.addView(keyguardUserDetailItemView, i);
                        }
                    } else {
                        KeyguardUserSwitcherListView keyguardUserSwitcherListView2 = keyguardUserSwitcherController.mListView;
                        keyguardUserSwitcherListView2.removeViewAt(keyguardUserSwitcherListView2.getChildCount() - 1);
                    }
                    i++;
                }
                if (z) {
                    return;
                }
                Log.w("KeyguardUserSwitcherController", "Current user is not listed");
            }
        };
        if (DEBUG) {
            Log.d("KeyguardUserSwitcherController", "New KeyguardUserSwitcherController");
        }
        this.mScreenLifecycle = screenLifecycle;
        this.mUserSwitcherController = userSwitcherController;
        this.mKeyguardStateController = keyguardStateController;
        this.mStatusBarStateController = sysuiStatusBarStateController;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mAdapter = new KeyguardUserAdapter(context, resources, layoutInflater, userSwitcherController, this);
        this.mKeyguardVisibilityHelper = new KeyguardVisibilityHelper(keyguardUserSwitcherView, keyguardStateController, screenOffAnimationController, false, null);
        KeyguardUserSwitcherScrim keyguardUserSwitcherScrim = new KeyguardUserSwitcherScrim();
        keyguardUserSwitcherScrim.mAlpha = 255;
        keyguardUserSwitcherScrim.mRadialGradientPaint = new Paint();
        keyguardUserSwitcherScrim.mDarkColor = context.getColor(R.color.keyguard_user_switcher_background_gradient_color);
        this.mBackground = keyguardUserSwitcherScrim;
    }

    public final boolean closeSwitcherIfOpenAndNotSimple(boolean z) {
        if (!this.mUserSwitcherOpen || ((UserSwitcherSettingsModel) this.mUserSwitcherController.getMUserSwitcherInteractor().repository._userSwitcherSettings.getValue()).isSimpleUserSwitcher) {
            return false;
        }
        setUserSwitcherOpened(false, z);
        return true;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        if (DEBUG) {
            Log.d("KeyguardUserSwitcherController", "onInit");
        }
        this.mListView = (KeyguardUserSwitcherListView) ((KeyguardUserSwitcherView) this.mView).findViewById(R.id.keyguard_user_switcher_list);
        ((KeyguardUserSwitcherView) this.mView).setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.statusbar.policy.KeyguardUserSwitcherController$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                KeyguardUserSwitcherController keyguardUserSwitcherController = KeyguardUserSwitcherController.this;
                keyguardUserSwitcherController.mKeyguardVisibilityHelper.getClass();
                if (keyguardUserSwitcherController.mListView.mAnimating) {
                    return false;
                }
                return keyguardUserSwitcherController.closeSwitcherIfOpenAndNotSimple(true);
            }
        });
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        if (DEBUG) {
            Log.d("KeyguardUserSwitcherController", "onViewAttached");
        }
        KeyguardUserAdapter keyguardUserAdapter = this.mAdapter;
        keyguardUserAdapter.registerDataSetObserver(this.mDataSetObserver);
        keyguardUserAdapter.notifyDataSetChanged();
        this.mKeyguardUpdateMonitor.registerCallback(this.mInfoCallback);
        ((StatusBarStateControllerImpl) this.mStatusBarStateController).addCallback((StatusBarStateController.StateListener) this.mStatusBarStateListener);
        this.mScreenLifecycle.addObserver(this.mScreenObserver);
        if (((UserSwitcherSettingsModel) this.mUserSwitcherController.getMUserSwitcherInteractor().repository._userSwitcherSettings.getValue()).isSimpleUserSwitcher) {
            setUserSwitcherOpened(true, true);
            return;
        }
        KeyguardUserSwitcherView keyguardUserSwitcherView = (KeyguardUserSwitcherView) this.mView;
        KeyguardUserSwitcherScrim keyguardUserSwitcherScrim = this.mBackground;
        keyguardUserSwitcherView.addOnLayoutChangeListener(keyguardUserSwitcherScrim);
        ((KeyguardUserSwitcherView) this.mView).setBackground(keyguardUserSwitcherScrim);
        keyguardUserSwitcherScrim.setAlpha(0);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        if (DEBUG) {
            Log.d("KeyguardUserSwitcherController", "onViewDetached");
        }
        closeSwitcherIfOpenAndNotSimple(false);
        this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
        this.mKeyguardUpdateMonitor.removeCallback(this.mInfoCallback);
        ((StatusBarStateControllerImpl) this.mStatusBarStateController).removeCallback((StatusBarStateController.StateListener) this.mStatusBarStateListener);
        this.mScreenLifecycle.removeObserver(this.mScreenObserver);
        KeyguardUserSwitcherView keyguardUserSwitcherView = (KeyguardUserSwitcherView) this.mView;
        KeyguardUserSwitcherScrim keyguardUserSwitcherScrim = this.mBackground;
        keyguardUserSwitcherView.removeOnLayoutChangeListener(keyguardUserSwitcherScrim);
        ((KeyguardUserSwitcherView) this.mView).setBackground(null);
        keyguardUserSwitcherScrim.setAlpha(0);
    }

    /*  JADX ERROR: NullPointerException in pass: LoopRegionVisitor
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.use(jadx.core.dex.instructions.args.RegisterArg)" because "ssaVar" is null
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:493)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        */
    public final void setUserSwitcherOpened(boolean r20, boolean r21) {
        /*
            Method dump skipped, instructions count: 389
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.KeyguardUserSwitcherController.setUserSwitcherOpened(boolean, boolean):void");
    }
}
