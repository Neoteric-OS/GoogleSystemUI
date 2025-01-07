package com.android.keyguard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.LayerDrawable;
import android.os.Trace;
import android.os.UserManager;
import android.provider.Settings;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.MathUtils;
import android.util.Property;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import com.android.app.animation.Interpolators;
import com.android.app.animation.InterpolatorsAndroidX;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.UserIcons;
import com.android.keyguard.KeyguardSecurityContainer;
import com.android.keyguard.KeyguardSecurityContainerController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.settingslib.Utils;
import com.android.settingslib.drawable.CircleFramedDrawable;
import com.android.systemui.classifier.FalsingA11yDelegate;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.shade.TouchLogger;
import com.android.systemui.shared.system.SysUiStatsLog;
import com.android.systemui.statusbar.policy.BaseUserSwitcherAdapter;
import com.android.systemui.statusbar.policy.UserSwitcherController;
import com.android.systemui.user.data.source.UserRecord;
import com.android.systemui.user.legacyhelper.ui.LegacyUserUiHelper;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class KeyguardSecurityContainer extends ConstraintLayout {
    static final float MIN_BACK_SCALE = 0.9f;
    public int mActivePointerId;
    public AlertDialog mAlertDialog;
    public final AnonymousClass2 mBackCallback;
    public int mCurrentMode;
    public boolean mDisappearAnimRunning;
    public final GestureDetector mDoubleTapDetector;
    public FalsingA11yDelegate mFalsingA11yDelegate;
    public FalsingManager mFalsingManager;
    public GlobalSettings mGlobalSettings;
    public boolean mIsDragging;
    public boolean mIsInteractable;
    public float mLastTouchY;
    public final List mMotionEventListeners;
    KeyguardSecurityViewFlipper mSecurityViewFlipper;
    public final SpringAnimation mSpringAnimation;
    public float mStartTouchY;
    public KeyguardSecurityContainerController.AnonymousClass3 mSwipeListener;
    public boolean mSwipeUpToRetry;
    public UserSwitcherController mUserSwitcherController;
    public final VelocityTracker mVelocityTracker;
    public final ViewConfiguration mViewConfiguration;
    public KeyguardViewMediator.AnonymousClass4 mViewMediatorCallback;
    public ViewMode mViewMode;
    public int mWidth;
    public final AnonymousClass1 mWindowInsetsAnimationCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum BouncerUiEvent implements UiEventLogger.UiEventEnum {
        UNKNOWN(0),
        BOUNCER_DISMISS_EXTENDED_ACCESS(413),
        BOUNCER_DISMISS_BIOMETRIC(414),
        BOUNCER_DISMISS_NONE_SECURITY(415),
        BOUNCER_DISMISS_PASSWORD(416),
        BOUNCER_DISMISS_SIM(417),
        BOUNCER_PASSWORD_SUCCESS(418),
        BOUNCER_PASSWORD_FAILURE(419);

        private final int mId;

        BouncerUiEvent(int i) {
            this.mId = i;
        }

        public final int getId() {
            return this.mId;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DefaultViewMode implements ViewMode {
        public KeyguardSecurityContainer mView;
        public KeyguardSecurityViewFlipper mViewFlipper;

        @Override // com.android.keyguard.KeyguardSecurityContainer.ViewMode
        public final void init(KeyguardSecurityContainer keyguardSecurityContainer, GlobalSettings globalSettings, KeyguardSecurityViewFlipper keyguardSecurityViewFlipper, FalsingManager falsingManager, UserSwitcherController userSwitcherController, FalsingA11yDelegate falsingA11yDelegate) {
            this.mView = keyguardSecurityContainer;
            this.mViewFlipper = keyguardSecurityViewFlipper;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.connect(this.mViewFlipper.getId(), 6, 0, 6);
            constraintSet.connect(this.mViewFlipper.getId(), 7, 0, 7);
            constraintSet.connect(this.mViewFlipper.getId(), 4, 0, 4);
            constraintSet.connect(this.mViewFlipper.getId(), 3, 0, 3);
            constraintSet.constrainHeight(this.mViewFlipper.getId(), 0);
            constraintSet.constrainWidth(this.mViewFlipper.getId(), 0);
            constraintSet.applyTo(this.mView);
        }

        @Override // com.android.keyguard.KeyguardSecurityContainer.ViewMode
        public final void onDestroy() {
            if (this.mView == null) {
                return;
            }
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this.mView);
            constraintSet.mConstraints.remove(Integer.valueOf(this.mViewFlipper.getId()));
            constraintSet.applyTo(this.mView);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DoubleTapListener extends GestureDetector.SimpleOnGestureListener {
        public DoubleTapListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public final boolean onDoubleTap(MotionEvent motionEvent) {
            return KeyguardSecurityContainer.this.handleDoubleTap(motionEvent);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OneHandedViewMode extends SidedSecurityMode {
        public KeyguardSecurityContainer mView;
        public KeyguardSecurityViewFlipper mViewFlipper;

        @Override // com.android.keyguard.KeyguardSecurityContainer.ViewMode
        public final void init(KeyguardSecurityContainer keyguardSecurityContainer, GlobalSettings globalSettings, KeyguardSecurityViewFlipper keyguardSecurityViewFlipper, FalsingManager falsingManager, UserSwitcherController userSwitcherController, FalsingA11yDelegate falsingA11yDelegate) {
            super.mView = keyguardSecurityContainer;
            this.mGlobalSettings = globalSettings;
            this.mDefaultSideSetting = 0;
            this.mView = keyguardSecurityContainer;
            this.mViewFlipper = keyguardSecurityViewFlipper;
            updateSecurityViewLocation(isLeftAligned(), false);
        }

        @Override // com.android.keyguard.KeyguardSecurityContainer.ViewMode
        public final void onDestroy() {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this.mView);
            constraintSet.mConstraints.remove(Integer.valueOf(this.mViewFlipper.getId()));
            constraintSet.applyTo(this.mView);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.android.keyguard.KeyguardSecurityContainer.ViewMode
        public final void updatePositionByTouchX(float f) {
            boolean z = f <= ((float) this.mView.getWidth()) / 2.0f ? 1 : 0;
            GlobalSettings globalSettings = this.mGlobalSettings;
            globalSettings.getClass();
            Settings.Global.putString(((GlobalSettingsImpl) globalSettings).mContentResolver, "one_handed_keyguard_side", String.valueOf(!z));
            updateSecurityViewLocation(z, false);
        }

        @Override // com.android.keyguard.KeyguardSecurityContainer.ViewMode
        public final void updateSecurityViewLocation() {
            updateSecurityViewLocation(isLeftAligned(), false);
        }

        @Override // com.android.keyguard.KeyguardSecurityContainer.SidedSecurityMode
        public final void updateSecurityViewLocation(boolean z, boolean z2) {
            if (z2) {
                TransitionManager.beginDelayedTransition(this.mView, new KeyguardSecurityViewTransition());
            }
            ConstraintSet constraintSet = new ConstraintSet();
            if (z) {
                constraintSet.connect(this.mViewFlipper.getId(), 1, 0, 1);
            } else {
                constraintSet.connect(this.mViewFlipper.getId(), 2, 0, 2);
            }
            constraintSet.connect(this.mViewFlipper.getId(), 3, 0, 3);
            constraintSet.connect(this.mViewFlipper.getId(), 4, 0, 4);
            constraintSet.get(this.mViewFlipper.getId()).layout.widthPercent = 0.5f;
            constraintSet.applyTo(this.mView);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class SidedSecurityMode implements ViewMode {
        public int mDefaultSideSetting;
        public GlobalSettings mGlobalSettings;
        public KeyguardSecurityContainer mView;

        @Override // com.android.keyguard.KeyguardSecurityContainer.ViewMode
        public final void handleDoubleTap(MotionEvent motionEvent) {
            boolean isLeftAligned = isLeftAligned();
            if (isTouchOnTheOtherSideOfSecurity(motionEvent, isLeftAligned)) {
                boolean z = !isLeftAligned;
                GlobalSettings globalSettings = this.mGlobalSettings;
                globalSettings.getClass();
                Settings.Global.putString(((GlobalSettingsImpl) globalSettings).mContentResolver, "one_handed_keyguard_side", String.valueOf(isLeftAligned ? 1 : 0));
                SysUiStatsLog.write(63, !isLeftAligned ? 5 : 6);
                updateSecurityViewLocation(z, true);
            }
        }

        public final boolean isLeftAligned() {
            return this.mGlobalSettings.getInt(this.mDefaultSideSetting, "one_handed_keyguard_side") == 0;
        }

        public final boolean isTouchOnTheOtherSideOfSecurity(MotionEvent motionEvent, boolean z) {
            float x = motionEvent.getX();
            return (z && x > ((float) this.mView.getWidth()) / 2.0f) || (!z && x < ((float) this.mView.getWidth()) / 2.0f);
        }

        public abstract void updateSecurityViewLocation(boolean z, boolean z2);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UserSwitcherViewMode extends SidedSecurityMode {
        public FalsingA11yDelegate mFalsingA11yDelegate;
        public FalsingManager mFalsingManager;
        public KeyguardUserSwitcherPopupMenu mPopup;
        public Resources mResources;
        public KeyguardSecurityContainer$UserSwitcherViewMode$$ExternalSyntheticLambda0 mUserSwitchCallback;
        public TextView mUserSwitcher;
        public KeyguardSecurityContainerController$$ExternalSyntheticLambda5 mUserSwitcherCallback;
        public UserSwitcherController mUserSwitcherController;
        public ViewGroup mUserSwitcherViewGroup;
        public KeyguardSecurityContainer mView;
        public KeyguardSecurityViewFlipper mViewFlipper;

        public final void inflateUserSwitcher() {
            LayoutInflater.from(this.mView.getContext()).inflate(R.layout.keyguard_bouncer_user_switcher, (ViewGroup) this.mView, true);
            this.mUserSwitcherViewGroup = (ViewGroup) this.mView.findViewById(R.id.keyguard_bouncer_user_switcher);
            this.mUserSwitcher = (TextView) this.mView.findViewById(R.id.user_switcher_header);
        }

        @Override // com.android.keyguard.KeyguardSecurityContainer.ViewMode
        public final void init(KeyguardSecurityContainer keyguardSecurityContainer, GlobalSettings globalSettings, KeyguardSecurityViewFlipper keyguardSecurityViewFlipper, FalsingManager falsingManager, UserSwitcherController userSwitcherController, FalsingA11yDelegate falsingA11yDelegate) {
            super.mView = keyguardSecurityContainer;
            this.mGlobalSettings = globalSettings;
            this.mDefaultSideSetting = 1;
            this.mView = keyguardSecurityContainer;
            this.mViewFlipper = keyguardSecurityViewFlipper;
            this.mFalsingManager = falsingManager;
            this.mUserSwitcherController = userSwitcherController;
            this.mResources = keyguardSecurityContainer.getContext().getResources();
            this.mFalsingA11yDelegate = falsingA11yDelegate;
            if (this.mUserSwitcherViewGroup == null) {
                inflateUserSwitcher();
                setupUserSwitcher();
                this.mUserSwitcherController.addUserSwitchCallback(this.mUserSwitchCallback);
            }
            updateSecurityViewLocation();
        }

        @Override // com.android.keyguard.KeyguardSecurityContainer.ViewMode
        public final void onDensityOrFontScaleChanged() {
            this.mView.removeView(this.mUserSwitcherViewGroup);
            this.mView.removeView(this.mUserSwitcher);
            inflateUserSwitcher();
        }

        @Override // com.android.keyguard.KeyguardSecurityContainer.ViewMode
        public final void onDestroy() {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this.mView);
            constraintSet.mConstraints.remove(Integer.valueOf(this.mUserSwitcherViewGroup.getId()));
            constraintSet.mConstraints.remove(Integer.valueOf(this.mViewFlipper.getId()));
            constraintSet.applyTo(this.mView);
            this.mView.removeView(this.mUserSwitcherViewGroup);
            this.mView.removeView(this.mUserSwitcher);
        }

        @Override // com.android.keyguard.KeyguardSecurityContainer.ViewMode
        public final void reloadColors() {
            TextView textView = (TextView) this.mView.findViewById(R.id.user_switcher_header);
            if (textView != null) {
                textView.setTextColor(Utils.getColorAttrDefaultColor(android.R.attr.textColorPrimary, 0, this.mView.getContext()));
                textView.setBackground(this.mView.getContext().getDrawable(R.drawable.bouncer_user_switcher_header_bg));
                ((LayerDrawable) textView.getBackground().mutate()).findDrawableByLayerId(R.id.user_switcher_key_down).setTintList(Utils.getColorAttr(android.R.attr.textColorPrimary, this.mView.getContext()));
            }
        }

        @Override // com.android.keyguard.KeyguardSecurityContainer.ViewMode
        public final void reset() {
            KeyguardUserSwitcherPopupMenu keyguardUserSwitcherPopupMenu = this.mPopup;
            if (keyguardUserSwitcherPopupMenu != null) {
                keyguardUserSwitcherPopupMenu.dismiss();
                this.mPopup = null;
            }
            setupUserSwitcher();
        }

        /* JADX WARN: Type inference failed for: r2v14, types: [com.android.keyguard.KeyguardSecurityContainer$UserSwitcherViewMode$2] */
        public final void setupUserSwitcher() {
            String str;
            Drawable defaultUserIcon;
            final UserRecord userRecord = (UserRecord) ((StateFlowImpl) this.mUserSwitcherController.getMUserSwitcherInteractor().selectedUserRecord.$$delegate_0).getValue();
            if (userRecord == null) {
                Log.e("KeyguardSecurityView", "Current user in user switcher is null.");
                return;
            }
            UserSwitcherController userSwitcherController = this.mUserSwitcherController;
            UserRecord userRecord2 = (UserRecord) ((StateFlowImpl) userSwitcherController.getMUserSwitcherInteractor().selectedUserRecord.$$delegate_0).getValue();
            if (userRecord2 != null) {
                str = LegacyUserUiHelper.getUserRecordName(userSwitcherController.applicationContext, userRecord2, userSwitcherController.getMUserSwitcherInteractor().isGuestUserAutoCreated, userSwitcherController.getMUserSwitcherInteractor().isGuestUserResetting);
            } else {
                str = null;
            }
            int i = userRecord.info.id;
            Bitmap userIcon = UserManager.get(this.mView.getContext()).getUserIcon(i);
            if (userIcon != null) {
                int dimensionPixelSize = this.mResources.getDimensionPixelSize(R.dimen.bouncer_user_switcher_icon_size);
                Context context = this.mView.getContext();
                Bitmap scaleDownIfNecessary = Icon.scaleDownIfNecessary(userIcon, dimensionPixelSize, dimensionPixelSize);
                int i2 = CircleFramedDrawable.$r8$clinit;
                defaultUserIcon = new CircleFramedDrawable(scaleDownIfNecessary, context.getResources().getDimensionPixelSize(17105747));
            } else {
                defaultUserIcon = UserIcons.getDefaultUserIcon(this.mResources, i, false);
            }
            ((ImageView) this.mView.findViewById(R.id.user_icon)).setImageDrawable(defaultUserIcon);
            this.mUserSwitcher.setText(str);
            final KeyguardUserSwitcherAnchor keyguardUserSwitcherAnchor = (KeyguardUserSwitcherAnchor) this.mView.findViewById(R.id.user_switcher_anchor);
            keyguardUserSwitcherAnchor.setAccessibilityDelegate(this.mFalsingA11yDelegate);
            final ?? r2 = new BaseUserSwitcherAdapter(this.mUserSwitcherController) { // from class: com.android.keyguard.KeyguardSecurityContainer.UserSwitcherViewMode.2
                @Override // android.widget.Adapter
                public final View getView(int i3, View view, ViewGroup viewGroup) {
                    Drawable layerDrawable;
                    UserRecord item = getItem(i3);
                    FrameLayout frameLayout = (FrameLayout) view;
                    if (frameLayout == null) {
                        frameLayout = (FrameLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.keyguard_bouncer_user_switcher_item, viewGroup, false);
                    }
                    TextView textView = (TextView) frameLayout.getChildAt(0);
                    textView.setText(getName(viewGroup.getContext(), item));
                    Bitmap bitmap = item.picture;
                    boolean z = item.isSwitchToEnabled;
                    if (bitmap != null) {
                        layerDrawable = new BitmapDrawable(item.picture);
                    } else {
                        Context context2 = frameLayout.getContext();
                        Drawable drawable = (item.isCurrent && item.isGuest) ? context2.getDrawable(R.drawable.ic_avatar_guest_user) : BaseUserSwitcherAdapter.getIconDrawable(context2, item);
                        drawable.setTint(z ? Utils.getColorAttrDefaultColor(android.R.^attr-private.colorAccentPrimaryVariant, 0, context2) : context2.getResources().getColor(R.color.kg_user_switcher_restricted_avatar_icon_color, context2.getTheme()));
                        Drawable drawable2 = context2.getDrawable(R.drawable.user_avatar_bg);
                        drawable2.setTintBlendMode(BlendMode.DST);
                        drawable2.setTint(Utils.getColorAttrDefaultColor(android.R.^attr-private.colorSurfaceVariant, 0, context2));
                        layerDrawable = new LayerDrawable(new Drawable[]{drawable2, drawable});
                    }
                    int dimensionPixelSize2 = frameLayout.getResources().getDimensionPixelSize(R.dimen.bouncer_user_switcher_item_icon_size);
                    int dimensionPixelSize3 = frameLayout.getResources().getDimensionPixelSize(R.dimen.bouncer_user_switcher_item_icon_padding);
                    layerDrawable.setBounds(0, 0, dimensionPixelSize2, dimensionPixelSize2);
                    textView.setCompoundDrawablePadding(dimensionPixelSize3);
                    textView.setCompoundDrawablesRelative(layerDrawable, null, null, null);
                    if (item == userRecord) {
                        textView.setBackground(frameLayout.getContext().getDrawable(R.drawable.bouncer_user_switcher_item_selected_bg));
                    } else {
                        textView.setBackground(null);
                    }
                    textView.setSelected(item == userRecord);
                    frameLayout.setEnabled(z);
                    frameLayout.setAlpha(frameLayout.isEnabled() ? 1.0f : 0.38f);
                    return frameLayout;
                }
            };
            keyguardUserSwitcherAnchor.setOnClickListener(new View.OnClickListener() { // from class: com.android.keyguard.KeyguardSecurityContainer$UserSwitcherViewMode$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    final KeyguardSecurityContainer.UserSwitcherViewMode userSwitcherViewMode = KeyguardSecurityContainer.UserSwitcherViewMode.this;
                    KeyguardUserSwitcherAnchor keyguardUserSwitcherAnchor2 = keyguardUserSwitcherAnchor;
                    final KeyguardSecurityContainer.UserSwitcherViewMode.AnonymousClass2 anonymousClass2 = r2;
                    if (userSwitcherViewMode.mFalsingManager.isFalseTap(1)) {
                        return;
                    }
                    Context context2 = userSwitcherViewMode.mView.getContext();
                    FalsingManager falsingManager = userSwitcherViewMode.mFalsingManager;
                    KeyguardUserSwitcherPopupMenu keyguardUserSwitcherPopupMenu = new KeyguardUserSwitcherPopupMenu(context2);
                    keyguardUserSwitcherPopupMenu.mContext = context2;
                    keyguardUserSwitcherPopupMenu.mFalsingManager = falsingManager;
                    keyguardUserSwitcherPopupMenu.setBackgroundDrawable(context2.getResources().getDrawable(R.drawable.bouncer_user_switcher_popup_bg, context2.getTheme()));
                    keyguardUserSwitcherPopupMenu.setModal(true);
                    keyguardUserSwitcherPopupMenu.setOverlapAnchor(true);
                    userSwitcherViewMode.mPopup = keyguardUserSwitcherPopupMenu;
                    keyguardUserSwitcherPopupMenu.setAnchorView(keyguardUserSwitcherAnchor2);
                    userSwitcherViewMode.mPopup.setAdapter(anonymousClass2);
                    userSwitcherViewMode.mPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.android.keyguard.KeyguardSecurityContainer$UserSwitcherViewMode$$ExternalSyntheticLambda3
                        @Override // android.widget.AdapterView.OnItemClickListener
                        public final void onItemClick(AdapterView adapterView, View view2, int i3, long j) {
                            KeyguardSecurityContainer.UserSwitcherViewMode userSwitcherViewMode2 = KeyguardSecurityContainer.UserSwitcherViewMode.this;
                            KeyguardSecurityContainer.UserSwitcherViewMode.AnonymousClass2 anonymousClass22 = anonymousClass2;
                            if (userSwitcherViewMode2.mFalsingManager.isFalseTap(1) || !view2.isEnabled() || userSwitcherViewMode2.mPopup == null) {
                                return;
                            }
                            UserRecord item = anonymousClass22.getItem(i3 - 1);
                            if (item.isManageUsers || item.isAddSupervisedUser) {
                                KeyguardSecurityContainerController keyguardSecurityContainerController = (KeyguardSecurityContainerController) userSwitcherViewMode2.mUserSwitcherCallback.f$0;
                                String string = keyguardSecurityContainerController.mView.getContext().getString(R.string.keyguard_unlock_to_continue);
                                if (keyguardSecurityContainerController.mCurrentSecurityMode != KeyguardSecurityModel.SecurityMode.None) {
                                    keyguardSecurityContainerController.getCurrentSecurityController(new KeyguardSecurityContainerController$$ExternalSyntheticLambda2(string, null, true));
                                }
                            }
                            if (!item.isCurrent) {
                                anonymousClass22.onUserListItemClicked(item, null);
                            }
                            userSwitcherViewMode2.mPopup.dismiss();
                            userSwitcherViewMode2.mPopup = null;
                        }
                    });
                    userSwitcherViewMode.mPopup.show();
                }
            });
        }

        @Override // com.android.keyguard.KeyguardSecurityContainer.ViewMode
        public final void startAppearAnimation(KeyguardSecurityModel.SecurityMode securityMode) {
            if (securityMode == KeyguardSecurityModel.SecurityMode.Password) {
                return;
            }
            this.mUserSwitcherViewGroup.setAlpha(0.0f);
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            final int dimensionPixelSize = this.mView.getResources().getDimensionPixelSize(R.dimen.pin_view_trans_y_entry);
            ofFloat.setInterpolator(Interpolators.STANDARD_DECELERATE);
            ofFloat.setDuration(650L);
            ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.keyguard.KeyguardSecurityContainer.UserSwitcherViewMode.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    UserSwitcherViewMode.this.mUserSwitcherViewGroup.setAlpha(1.0f);
                    UserSwitcherViewMode.this.mUserSwitcherViewGroup.setTranslationY(0.0f);
                }
            });
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.keyguard.KeyguardSecurityContainer$UserSwitcherViewMode$$ExternalSyntheticLambda2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    KeyguardSecurityContainer.UserSwitcherViewMode userSwitcherViewMode = KeyguardSecurityContainer.UserSwitcherViewMode.this;
                    int i = dimensionPixelSize;
                    userSwitcherViewMode.getClass();
                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    userSwitcherViewMode.mUserSwitcherViewGroup.setAlpha(floatValue);
                    float f = i;
                    userSwitcherViewMode.mUserSwitcherViewGroup.setTranslationY(f - (floatValue * f));
                }
            });
            ofFloat.start();
        }

        @Override // com.android.keyguard.KeyguardSecurityContainer.ViewMode
        public final void startDisappearAnimation(KeyguardSecurityModel.SecurityMode securityMode) {
            if (securityMode == KeyguardSecurityModel.SecurityMode.Password) {
                return;
            }
            int dimensionPixelSize = this.mResources.getDimensionPixelSize(R.dimen.disappear_y_translation);
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mViewFlipper, (Property<KeyguardSecurityViewFlipper, Float>) View.TRANSLATION_Y, dimensionPixelSize);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mUserSwitcherViewGroup, (Property<ViewGroup, Float>) View.ALPHA, 0.0f);
            animatorSet.setInterpolator(Interpolators.STANDARD_ACCELERATE);
            animatorSet.playTogether(ofFloat2, ofFloat);
            animatorSet.start();
        }

        @Override // com.android.keyguard.KeyguardSecurityContainer.ViewMode
        public final void updateSecurityViewLocation() {
            updateSecurityViewLocation(isLeftAligned(), false);
        }

        @Override // com.android.keyguard.KeyguardSecurityContainer.SidedSecurityMode
        public final void updateSecurityViewLocation(boolean z, boolean z2) {
            if (z2) {
                TransitionManager.beginDelayedTransition(this.mView, new KeyguardSecurityViewTransition());
            }
            int dimensionPixelSize = this.mResources.getDimensionPixelSize(R.dimen.bouncer_user_switcher_y_trans);
            int dimensionPixelSize2 = this.mResources.getDimensionPixelSize(R.dimen.bouncer_user_switcher_view_mode_view_flipper_bottom_margin);
            int dimensionPixelSize3 = this.mResources.getDimensionPixelSize(R.dimen.bouncer_user_switcher_view_mode_user_switcher_bottom_margin);
            if (this.mResources.getConfiguration().orientation == 1) {
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.connect(this.mUserSwitcherViewGroup.getId(), 3, 0, 3, dimensionPixelSize);
                constraintSet.connect(this.mUserSwitcherViewGroup.getId(), 4, this.mViewFlipper.getId(), 3, dimensionPixelSize3);
                constraintSet.connect(this.mViewFlipper.getId(), 3, this.mUserSwitcherViewGroup.getId(), 4);
                constraintSet.connect(this.mViewFlipper.getId(), 4, 0, 4, dimensionPixelSize2);
                constraintSet.center(this.mViewFlipper.getId(), 1, 2);
                constraintSet.center(this.mUserSwitcherViewGroup.getId(), 1, 2);
                constraintSet.get(this.mViewFlipper.getId()).layout.verticalChainStyle = 0;
                constraintSet.get(this.mUserSwitcherViewGroup.getId()).layout.verticalChainStyle = 0;
                constraintSet.constrainHeight(this.mUserSwitcherViewGroup.getId(), -2);
                constraintSet.constrainWidth(this.mUserSwitcherViewGroup.getId(), -2);
                constraintSet.constrainHeight(this.mViewFlipper.getId(), 0);
                constraintSet.applyTo(this.mView);
                return;
            }
            int id = z ? this.mViewFlipper.getId() : this.mUserSwitcherViewGroup.getId();
            int id2 = z ? this.mUserSwitcherViewGroup.getId() : this.mViewFlipper.getId();
            ConstraintSet constraintSet2 = new ConstraintSet();
            constraintSet2.connect(id, 6, 0, 6);
            constraintSet2.connect(id, 7, id2, 6);
            constraintSet2.connect(id2, 6, id, 7);
            constraintSet2.connect(id2, 7, 0, 7);
            constraintSet2.connect(this.mUserSwitcherViewGroup.getId(), 3, 0, 3);
            constraintSet2.connect(this.mUserSwitcherViewGroup.getId(), 4, 0, 4);
            constraintSet2.connect(this.mViewFlipper.getId(), 3, 0, 3);
            constraintSet2.connect(this.mViewFlipper.getId(), 4, 0, 4);
            constraintSet2.get(this.mUserSwitcherViewGroup.getId()).layout.horizontalChainStyle = 0;
            constraintSet2.get(this.mViewFlipper.getId()).layout.horizontalChainStyle = 0;
            constraintSet2.constrainHeight(this.mUserSwitcherViewGroup.getId(), 0);
            constraintSet2.constrainWidth(this.mUserSwitcherViewGroup.getId(), 0);
            constraintSet2.constrainWidth(this.mViewFlipper.getId(), 0);
            constraintSet2.constrainHeight(this.mViewFlipper.getId(), 0);
            constraintSet2.applyTo(this.mView);
        }
    }

    public KeyguardSecurityContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static String modeToString(int i) {
        if (i == -1) {
            return "Uninitialized";
        }
        if (i == 0) {
            return "Default";
        }
        if (i == 1) {
            return "OneHanded";
        }
        if (i == 2) {
            return "UserSwitcher";
        }
        throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("mode: ", " not supported", i));
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        KeyguardViewMediator.AnonymousClass4 anonymousClass4 = this.mViewMediatorCallback;
        if (anonymousClass4 != null) {
            Trace.beginSection("KeyguardViewMediator.mViewMediatorCallback#keyguardDoneDrawing");
            KeyguardViewMediator.this.mHandler.sendEmptyMessage(8);
            Trace.endSection();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        TouchLogger.Companion.logDispatchTouch("KeyguardSecurityView", motionEvent, dispatchTouchEvent);
        return dispatchTouchEvent;
    }

    public boolean handleDoubleTap(MotionEvent motionEvent) {
        if (this.mIsDragging) {
            return false;
        }
        this.mViewMode.handleDoubleTap(motionEvent);
        return true;
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        int max = Integer.max(windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars()).bottom, windowInsets.getInsets(WindowInsets.Type.ime()).bottom);
        int max2 = Integer.max(max, getContext().getResources().getDimensionPixelSize(R.dimen.keyguard_security_view_bottom_margin));
        if (!this.mDisappearAnimRunning) {
            setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), max2);
        }
        return windowInsets.inset(0, 0, 0, max);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mViewMode.updateSecurityViewLocation();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        GlobalSettings globalSettings;
        FalsingManager falsingManager;
        UserSwitcherController userSwitcherController;
        super.onFinishInflate();
        KeyguardSecurityViewFlipper keyguardSecurityViewFlipper = (KeyguardSecurityViewFlipper) findViewById(R.id.view_flipper);
        this.mSecurityViewFlipper = keyguardSecurityViewFlipper;
        if (keyguardSecurityViewFlipper == null || (globalSettings = this.mGlobalSettings) == null || (falsingManager = this.mFalsingManager) == null || (userSwitcherController = this.mUserSwitcherController) == null) {
            return;
        }
        this.mViewMode.init(this, globalSettings, keyguardSecurityViewFlipper, falsingManager, userSwitcherController, this.mFalsingA11yDelegate);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
    
        if (r3 != 3) goto L39;
     */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.mIsInteractable
            r1 = 1
            if (r0 != 0) goto L6
            return r1
        L6:
            java.util.List r0 = r5.mMotionEventListeners
            java.util.stream.Stream r0 = r0.stream()
            com.android.keyguard.KeyguardSecurityContainer$$ExternalSyntheticLambda1 r2 = new com.android.keyguard.KeyguardSecurityContainer$$ExternalSyntheticLambda1
            r3 = 1
            r2.<init>(r6, r3)
            boolean r0 = r0.anyMatch(r2)
            r2 = 0
            if (r0 != 0) goto L22
            boolean r0 = super.onInterceptTouchEvent(r6)
            if (r0 == 0) goto L20
            goto L22
        L20:
            r0 = r2
            goto L23
        L22:
            r0 = r1
        L23:
            int r3 = r6.getActionMasked()
            if (r3 == 0) goto L75
            if (r3 == r1) goto L72
            r4 = 2
            if (r3 == r4) goto L32
            r6 = 3
            if (r3 == r6) goto L72
            goto L8a
        L32:
            boolean r3 = r5.mIsDragging
            if (r3 == 0) goto L37
            return r1
        L37:
            boolean r3 = r5.mSwipeUpToRetry
            if (r3 != 0) goto L3c
            return r2
        L3c:
            com.android.keyguard.KeyguardSecurityViewFlipper r3 = r5.mSecurityViewFlipper
            com.android.keyguard.KeyguardInputView r3 = r3.getSecurityView()
            if (r3 == 0) goto L51
            com.android.keyguard.KeyguardSecurityViewFlipper r3 = r5.mSecurityViewFlipper
            com.android.keyguard.KeyguardInputView r3 = r3.getSecurityView()
            boolean r3 = r3.disallowInterceptTouch(r6)
            if (r3 == 0) goto L51
            return r2
        L51:
            int r2 = r5.mActivePointerId
            int r2 = r6.findPointerIndex(r2)
            android.view.ViewConfiguration r3 = r5.mViewConfiguration
            int r3 = r3.getScaledTouchSlop()
            float r3 = (float) r3
            r4 = 1082130432(0x40800000, float:4.0)
            float r3 = r3 * r4
            r4 = -1
            if (r2 == r4) goto L8a
            float r4 = r5.mStartTouchY
            float r6 = r6.getY(r2)
            float r4 = r4 - r6
            int r6 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r6 <= 0) goto L8a
            r5.mIsDragging = r1
            return r1
        L72:
            r5.mIsDragging = r2
            goto L8a
        L75:
            int r1 = r6.getActionIndex()
            float r2 = r6.getY(r1)
            r5.mStartTouchY = r2
            int r6 = r6.getPointerId(r1)
            r5.mActivePointerId = r6
            android.view.VelocityTracker r5 = r5.mVelocityTracker
            r5.clear()
        L8a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.keyguard.KeyguardSecurityContainer.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = i3 - i;
        if (!z || this.mWidth == i5) {
            return;
        }
        this.mWidth = i5;
        this.mViewMode.updateSecurityViewLocation();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x008b  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.keyguard.KeyguardSecurityContainer.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void showAlmostAtWipeDialog(final int i, final int i2, int i3) {
        showDialog(i3 != 1 ? i3 != 2 ? i3 != 3 ? null : ((ViewGroup) this).mContext.getString(R.string.kg_failed_attempts_almost_at_erase_user, Integer.valueOf(i), Integer.valueOf(i2)) : ((DevicePolicyManager) ((ViewGroup) this).mContext.getSystemService(DevicePolicyManager.class)).getResources().getString("SystemUi.KEYGUARD_DIALOG_FAILED_ATTEMPTS_ALMOST_ERASING_PROFILE", new Supplier() { // from class: com.android.keyguard.KeyguardSecurityContainer$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                String string;
                string = ((ViewGroup) KeyguardSecurityContainer.this).mContext.getString(R.string.kg_failed_attempts_almost_at_erase_profile, Integer.valueOf(i), Integer.valueOf(i2));
                return string;
            }
        }, Integer.valueOf(i), Integer.valueOf(i2)) : ((ViewGroup) this).mContext.getString(R.string.kg_failed_attempts_almost_at_wipe, Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public final void showDialog(String str) {
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        AlertDialog create = new AlertDialog.Builder(((ViewGroup) this).mContext).setTitle((CharSequence) null).setMessage(str).setCancelable(false).setNeutralButton(R.string.ok, (DialogInterface.OnClickListener) null).create();
        this.mAlertDialog = create;
        if (!(((ViewGroup) this).mContext instanceof Activity)) {
            create.getWindow().setType(2009);
        }
        this.mAlertDialog.show();
    }

    public final void showWipeDialog(final int i, int i2) {
        showDialog(i2 != 1 ? i2 != 2 ? i2 != 3 ? null : ((ViewGroup) this).mContext.getString(R.string.kg_failed_attempts_now_erasing_user, Integer.valueOf(i)) : ((DevicePolicyManager) ((ViewGroup) this).mContext.getSystemService(DevicePolicyManager.class)).getResources().getString("SystemUi.KEYGUARD_DIALOG_FAILED_ATTEMPTS_ERASING_PROFILE", new Supplier() { // from class: com.android.keyguard.KeyguardSecurityContainer$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                String string;
                string = ((ViewGroup) KeyguardSecurityContainer.this).mContext.getString(R.string.kg_failed_attempts_now_erasing_profile, Integer.valueOf(i));
                return string;
            }
        }, Integer.valueOf(i)) : ((ViewGroup) this).mContext.getString(R.string.kg_failed_attempts_now_wiping, Integer.valueOf(i)));
    }

    public final void updateChildren(int i, float f) {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            childAt.setTranslationY(i);
            childAt.setAlpha(f);
        }
    }

    public KeyguardSecurityContainer(Context context) {
        this(context, null, 0);
    }

    /* JADX WARN: Type inference failed for: r3v5, types: [com.android.keyguard.KeyguardSecurityContainer$1] */
    public KeyguardSecurityContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mMotionEventListeners = new ArrayList();
        this.mLastTouchY = -1.0f;
        this.mActivePointerId = -1;
        this.mStartTouchY = -1.0f;
        this.mViewMode = new DefaultViewMode();
        this.mCurrentMode = -1;
        this.mWidth = -1;
        this.mWindowInsetsAnimationCallback = new WindowInsetsAnimation.Callback() { // from class: com.android.keyguard.KeyguardSecurityContainer.1
            public final Rect mInitialBounds = new Rect();
            public final Rect mFinalBounds = new Rect();

            @Override // android.view.WindowInsetsAnimation.Callback
            public final void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
                if (KeyguardSecurityContainer.this.mDisappearAnimRunning) {
                    InteractionJankMonitor.getInstance().end(20);
                    KeyguardSecurityContainer.this.setAlpha(0.0f);
                } else {
                    InteractionJankMonitor.getInstance().end(17);
                }
                KeyguardSecurityContainer.this.updateChildren(0, 1.0f);
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public final void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
                KeyguardSecurityContainer.this.mSecurityViewFlipper.getBoundsOnScreen(this.mInitialBounds);
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public final WindowInsets onProgress(WindowInsets windowInsets, List list) {
                boolean z = KeyguardSecurityContainer.this.mDisappearAnimRunning;
                float f = z ? -(this.mFinalBounds.bottom - this.mInitialBounds.bottom) : this.mInitialBounds.bottom - this.mFinalBounds.bottom;
                float f2 = z ? -((this.mFinalBounds.bottom - this.mInitialBounds.bottom) * 0.75f) : 0.0f;
                Iterator it = list.iterator();
                int i2 = 0;
                float f3 = 1.0f;
                while (it.hasNext()) {
                    WindowInsetsAnimation windowInsetsAnimation = (WindowInsetsAnimation) it.next();
                    if ((windowInsetsAnimation.getTypeMask() & WindowInsets.Type.ime()) != 0) {
                        f3 = windowInsetsAnimation.getInterpolatedFraction();
                        i2 += (int) MathUtils.lerp(f, f2, f3);
                    }
                }
                KeyguardSecurityContainer keyguardSecurityContainer = KeyguardSecurityContainer.this;
                KeyguardSecurityContainer.this.updateChildren(i2, keyguardSecurityContainer.mDisappearAnimRunning ? 1.0f - f3 : Math.max(f3, keyguardSecurityContainer.getAlpha()));
                return windowInsets;
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public final WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds) {
                KeyguardSecurityContainer keyguardSecurityContainer = KeyguardSecurityContainer.this;
                if (keyguardSecurityContainer.mDisappearAnimRunning) {
                    KeyguardInputView securityView = keyguardSecurityContainer.mSecurityViewFlipper.getSecurityView();
                    if (securityView != null) {
                        InteractionJankMonitor.getInstance().begin(securityView, 20);
                    }
                } else {
                    KeyguardInputView securityView2 = keyguardSecurityContainer.mSecurityViewFlipper.getSecurityView();
                    if (securityView2 != null) {
                        InteractionJankMonitor.getInstance().begin(securityView2, 17);
                    }
                }
                KeyguardSecurityContainer.this.mSecurityViewFlipper.getBoundsOnScreen(this.mFinalBounds);
                return bounds;
            }
        };
        this.mBackCallback = new AnonymousClass2();
        this.mSpringAnimation = new SpringAnimation(this, DynamicAnimation.TRANSLATION_Y);
        this.mViewConfiguration = ViewConfiguration.get(context);
        this.mDoubleTapDetector = new GestureDetector(context, new DoubleTapListener());
        setPadding(getPaddingLeft(), getResources().getDimensionPixelSize(R.dimen.keyguard_security_container_padding_top) + getPaddingTop(), getPaddingRight(), getPaddingBottom());
        setBackgroundColor(Utils.getColorAttrDefaultColor(android.R.^attr-private.materialColorSurfaceDim, 0, getContext()));
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.KeyguardSecurityContainer$2, reason: invalid class name */
    public final class AnonymousClass2 implements OnBackAnimationCallback {
        public AnonymousClass2() {
        }

        @Override // android.window.OnBackAnimationCallback
        public final void onBackCancelled() {
            KeyguardSecurityContainer keyguardSecurityContainer = KeyguardSecurityContainer.this;
            keyguardSecurityContainer.setScaleX(1.0f);
            keyguardSecurityContainer.setScaleY(1.0f);
        }

        @Override // android.window.OnBackAnimationCallback
        public final void onBackProgressed(BackEvent backEvent) {
            float interpolation = ((1.0f - InterpolatorsAndroidX.DECELERATE_QUINT.getInterpolation(backEvent.getProgress())) * 0.100000024f) + KeyguardSecurityContainer.MIN_BACK_SCALE;
            KeyguardSecurityContainer keyguardSecurityContainer = KeyguardSecurityContainer.this;
            float f = KeyguardSecurityContainer.MIN_BACK_SCALE;
            keyguardSecurityContainer.setScaleX(interpolation);
            keyguardSecurityContainer.setScaleY(interpolation);
        }

        @Override // android.window.OnBackInvokedCallback
        public final void onBackInvoked() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ViewMode {
        void init(KeyguardSecurityContainer keyguardSecurityContainer, GlobalSettings globalSettings, KeyguardSecurityViewFlipper keyguardSecurityViewFlipper, FalsingManager falsingManager, UserSwitcherController userSwitcherController, FalsingA11yDelegate falsingA11yDelegate);

        void onDestroy();

        default void onDensityOrFontScaleChanged() {
        }

        default void reloadColors() {
        }

        default void reset() {
        }

        default void updateSecurityViewLocation() {
        }

        default void handleDoubleTap(MotionEvent motionEvent) {
        }

        default void startAppearAnimation(KeyguardSecurityModel.SecurityMode securityMode) {
        }

        default void startDisappearAnimation(KeyguardSecurityModel.SecurityMode securityMode) {
        }

        default void updatePositionByTouchX(float f) {
        }
    }
}
