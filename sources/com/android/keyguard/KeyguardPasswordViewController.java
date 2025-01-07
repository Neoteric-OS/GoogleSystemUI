package com.android.keyguard;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.TextKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.internal.util.LatencyTracker;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.domain.interactor.KeyguardKeyboardInteractor;
import com.android.systemui.bouncer.ui.helper.BouncerHapticPlayer;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DevicePostureControllerImpl;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.kotlin.JavaAdapterKt;
import com.android.wm.shell.R;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardPasswordViewController extends KeyguardAbsKeyInputViewController {
    public final Drawable mDefaultPasswordFieldBackground;
    public final Drawable mFocusedPasswordFieldBackground;
    public final InputMethodManager mInputMethodManager;
    public final KeyguardPasswordViewController$$ExternalSyntheticLambda2 mKeyListener;
    public final KeyguardKeyboardInteractor mKeyguardKeyboardInteractor;
    public final KeyguardSecurityCallback mKeyguardSecurityCallback;
    public final StatusBarKeyguardViewManager mKeyguardViewController;
    public final KeyguardPasswordViewController$$ExternalSyntheticLambda1 mOnEditorActionListener;
    public final EditText mPasswordEntry;
    public boolean mPaused;
    public final KeyguardPasswordViewController$$ExternalSyntheticLambda0 mPostureCallback;
    public final DevicePostureController mPostureController;
    public final boolean mShowImeAtScreenOn;
    public final ImageView mSwitchImeButton;
    public final AnonymousClass1 mTextWatcher;

    public static /* synthetic */ void $r8$lambda$0yzGhmNax5FDJjTsqjtuu0xEx8Q(KeyguardPasswordViewController keyguardPasswordViewController) {
        keyguardPasswordViewController.mPasswordEntry.clearFocus();
        super.onPause();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.keyguard.KeyguardPasswordViewController$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.keyguard.KeyguardPasswordViewController$$ExternalSyntheticLambda1] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.keyguard.KeyguardPasswordViewController$$ExternalSyntheticLambda2] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.android.keyguard.KeyguardPasswordViewController$1] */
    public KeyguardPasswordViewController(KeyguardPasswordView keyguardPasswordView, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardSecurityModel.SecurityMode securityMode, LockPatternUtils lockPatternUtils, KeyguardSecurityCallback keyguardSecurityCallback, KeyguardMessageAreaController.Factory factory, LatencyTracker latencyTracker, InputMethodManager inputMethodManager, EmergencyButtonController emergencyButtonController, Resources resources, FalsingCollector falsingCollector, StatusBarKeyguardViewManager statusBarKeyguardViewManager, DevicePostureController devicePostureController, FeatureFlags featureFlags, SelectedUserInteractor selectedUserInteractor, KeyguardKeyboardInteractor keyguardKeyboardInteractor, BouncerHapticPlayer bouncerHapticPlayer, UserActivityNotifier userActivityNotifier) {
        super(keyguardPasswordView, keyguardUpdateMonitor, securityMode, lockPatternUtils, keyguardSecurityCallback, factory, latencyTracker, falsingCollector, emergencyButtonController, selectedUserInteractor, bouncerHapticPlayer, userActivityNotifier);
        this.mPostureCallback = new DevicePostureController.Callback() { // from class: com.android.keyguard.KeyguardPasswordViewController$$ExternalSyntheticLambda0
            @Override // com.android.systemui.statusbar.policy.DevicePostureController.Callback
            public final void onPostureChanged(int i) {
                ((KeyguardPasswordView) KeyguardPasswordViewController.this.mView).onDevicePostureChanged(i);
            }
        };
        this.mOnEditorActionListener = new TextView.OnEditorActionListener() { // from class: com.android.keyguard.KeyguardPasswordViewController$$ExternalSyntheticLambda1
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                KeyguardPasswordViewController keyguardPasswordViewController = KeyguardPasswordViewController.this;
                keyguardPasswordViewController.getClass();
                if (keyEvent != null || (i != 0 && i != 6 && i != 5)) {
                    return false;
                }
                keyguardPasswordViewController.verifyPasswordAndUnlock();
                return true;
            }
        };
        this.mKeyListener = new View.OnKeyListener() { // from class: com.android.keyguard.KeyguardPasswordViewController$$ExternalSyntheticLambda2
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                KeyguardPasswordViewController keyguardPasswordViewController = KeyguardPasswordViewController.this;
                keyguardPasswordViewController.getClass();
                if (keyEvent == null || !KeyEvent.isConfirmKey(i) || i == 62 || keyEvent.getAction() != 1) {
                    return false;
                }
                keyguardPasswordViewController.verifyPasswordAndUnlock();
                return true;
            }
        };
        this.mTextWatcher = new TextWatcher() { // from class: com.android.keyguard.KeyguardPasswordViewController.1
            @Override // android.text.TextWatcher
            public final void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    return;
                }
                KeyguardPasswordViewController.this.onUserInput();
            }

            @Override // android.text.TextWatcher
            public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                KeyguardPasswordViewController.this.mKeyguardSecurityCallback.userActivity();
            }

            @Override // android.text.TextWatcher
            public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        };
        this.mKeyguardSecurityCallback = keyguardSecurityCallback;
        this.mInputMethodManager = inputMethodManager;
        this.mPostureController = devicePostureController;
        this.mKeyguardViewController = statusBarKeyguardViewManager;
        this.mKeyguardKeyboardInteractor = keyguardKeyboardInteractor;
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        featureFlags.getClass();
        this.mShowImeAtScreenOn = resources.getBoolean(R.bool.kg_show_ime_at_screen_on);
        keyguardPasswordView.getClass();
        EditText editText = (EditText) keyguardPasswordView.findViewById(R.id.passwordEntry);
        this.mPasswordEntry = editText;
        this.mDefaultPasswordFieldBackground = editText.getBackground();
        this.mFocusedPasswordFieldBackground = keyguardPasswordView.getResources().getDrawable(R.drawable.bouncer_password_view_background);
        this.mSwitchImeButton = (ImageView) keyguardPasswordView.findViewById(R.id.switch_ime_button);
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public final int getInitialMessageResId() {
        return R.string.keyguard_enter_your_password;
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputViewController, com.android.keyguard.KeyguardInputViewController
    public final void onPause() {
        if (this.mPaused) {
            return;
        }
        this.mPaused = true;
        if (this.mPasswordEntry.isVisibleToUser()) {
            ((KeyguardPasswordView) this.mView).mOnFinishImeAnimationRunnable = new KeyguardPasswordViewController$$ExternalSyntheticLambda6(this);
        } else {
            super.onPause();
        }
        KeyguardPasswordView keyguardPasswordView = (KeyguardPasswordView) this.mView;
        keyguardPasswordView.getClass();
        keyguardPasswordView.post(new KeyguardPasswordView$$ExternalSyntheticLambda0(keyguardPasswordView, 1));
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public final void onResume(int i) {
        this.mResumed = true;
        this.mPaused = false;
        if ((i != 1 || this.mShowImeAtScreenOn) && this.mKeyguardViewController.isBouncerShowing() && ((KeyguardPasswordView) this.mView).isShown()) {
            KeyguardPasswordView keyguardPasswordView = (KeyguardPasswordView) this.mView;
            keyguardPasswordView.getClass();
            keyguardPasswordView.post(new KeyguardPasswordView$$ExternalSyntheticLambda0(keyguardPasswordView, 0));
        }
    }

    @Override // com.android.keyguard.KeyguardSecurityView
    public final void onStartingToHide() {
        KeyguardPasswordView keyguardPasswordView = (KeyguardPasswordView) this.mView;
        keyguardPasswordView.getClass();
        keyguardPasswordView.post(new KeyguardPasswordView$$ExternalSyntheticLambda0(keyguardPasswordView, 1));
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputViewController, com.android.keyguard.KeyguardInputViewController, com.android.systemui.util.ViewController
    public final void onViewAttached() {
        super.onViewAttached();
        EditText editText = this.mPasswordEntry;
        SelectedUserInteractor selectedUserInteractor = this.mSelectedUserInteractor;
        editText.setTextOperationUser(UserHandle.of(selectedUserInteractor.getSelectedUserId()));
        this.mPasswordEntry.setKeyListener(TextKeyListener.getInstance());
        this.mPasswordEntry.setInputType(129);
        KeyguardPasswordView keyguardPasswordView = (KeyguardPasswordView) this.mView;
        DevicePostureControllerImpl devicePostureControllerImpl = (DevicePostureControllerImpl) this.mPostureController;
        keyguardPasswordView.onDevicePostureChanged(devicePostureControllerImpl.getDevicePosture());
        devicePostureControllerImpl.addCallback(this.mPostureCallback);
        final int i = 1;
        this.mPasswordEntry.setSelected(true);
        this.mPasswordEntry.setOnEditorActionListener(this.mOnEditorActionListener);
        this.mPasswordEntry.setOnKeyListener(this.mKeyListener);
        this.mPasswordEntry.addTextChangedListener(this.mTextWatcher);
        this.mPasswordEntry.setOnClickListener(new View.OnClickListener() { // from class: com.android.keyguard.KeyguardPasswordViewController.2
            public final boolean mAutomotiveAndVisibleBackgroundUsers;

            {
                Context context = KeyguardPasswordViewController.this.mView.getContext();
                this.mAutomotiveAndVisibleBackgroundUsers = context.getPackageManager().hasSystemFeature("android.hardware.type.automotive") && UserManager.isVisibleBackgroundUsersEnabled() && context.getResources().getBoolean(android.R.bool.config_perDisplayFocusEnabled);
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (this.mAutomotiveAndVisibleBackgroundUsers) {
                    KeyguardPasswordViewController.this.mInputMethodManager.restartInput(view);
                }
                KeyguardPasswordViewController.this.mKeyguardSecurityCallback.userActivity();
            }
        });
        final int i2 = 0;
        this.mSwitchImeButton.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.keyguard.KeyguardPasswordViewController$$ExternalSyntheticLambda3
            public final /* synthetic */ KeyguardPasswordViewController f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i2;
                KeyguardPasswordViewController keyguardPasswordViewController = this.f$0;
                switch (i3) {
                    case 0:
                        keyguardPasswordViewController.mKeyguardSecurityCallback.userActivity();
                        keyguardPasswordViewController.mInputMethodManager.showInputMethodPickerFromSystem(false, ((KeyguardPasswordView) keyguardPasswordViewController.mView).getContext().getDisplayId());
                        break;
                    default:
                        KeyguardSecurityCallback keyguardSecurityCallback = keyguardPasswordViewController.mKeyguardSecurityCallback;
                        keyguardSecurityCallback.reset();
                        keyguardSecurityCallback.onCancelClicked();
                        break;
                }
            }
        });
        View findViewById = ((KeyguardPasswordView) this.mView).findViewById(R.id.cancel_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.keyguard.KeyguardPasswordViewController$$ExternalSyntheticLambda3
                public final /* synthetic */ KeyguardPasswordViewController f$0;

                {
                    this.f$0 = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i3 = i;
                    KeyguardPasswordViewController keyguardPasswordViewController = this.f$0;
                    switch (i3) {
                        case 0:
                            keyguardPasswordViewController.mKeyguardSecurityCallback.userActivity();
                            keyguardPasswordViewController.mInputMethodManager.showInputMethodPickerFromSystem(false, ((KeyguardPasswordView) keyguardPasswordViewController.mView).getContext().getDisplayId());
                            break;
                        default:
                            KeyguardSecurityCallback keyguardSecurityCallback = keyguardPasswordViewController.mKeyguardSecurityCallback;
                            keyguardSecurityCallback.reset();
                            keyguardSecurityCallback.onCancelClicked();
                            break;
                    }
                }
            });
        }
        int i3 = this.mSwitchImeButton.getVisibility() == 0 ? 1 : 0;
        InputMethodManager inputMethodManager = this.mInputMethodManager;
        Iterator it = inputMethodManager.getEnabledInputMethodListAsUser(UserHandle.of(selectedUserInteractor.getSelectedUserId())).iterator();
        int i4 = 0;
        while (true) {
            if (it.hasNext()) {
                InputMethodInfo inputMethodInfo = (InputMethodInfo) it.next();
                if (i4 > 1) {
                    break;
                }
                List<InputMethodSubtype> enabledInputMethodSubtypeList = inputMethodManager.getEnabledInputMethodSubtypeList(inputMethodInfo, true);
                if (!enabledInputMethodSubtypeList.isEmpty()) {
                    Iterator<InputMethodSubtype> it2 = enabledInputMethodSubtypeList.iterator();
                    int i5 = 0;
                    while (it2.hasNext()) {
                        if (it2.next().isAuxiliary()) {
                            i5++;
                        }
                    }
                    if (enabledInputMethodSubtypeList.size() - i5 <= 0) {
                    }
                }
                i4++;
            } else if (i4 <= 1 && inputMethodManager.getEnabledInputMethodSubtypeList(null, false).size() <= 1) {
                i = 0;
            }
        }
        if (i3 != i) {
            this.mSwitchImeButton.setVisibility(i != 0 ? 0 : 8);
        }
        if (this.mSwitchImeButton.getVisibility() != 0) {
            ViewGroup.LayoutParams layoutParams = this.mPasswordEntry.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ((ViewGroup.MarginLayoutParams) layoutParams).setMarginStart(0);
                this.mPasswordEntry.setLayoutParams(layoutParams);
            }
        }
        JavaAdapterKt.collectFlow(this.mPasswordEntry, this.mKeyguardKeyboardInteractor.isAnyKeyboardConnected, new Consumer() { // from class: com.android.keyguard.KeyguardPasswordViewController$$ExternalSyntheticLambda5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                KeyguardPasswordViewController keyguardPasswordViewController = KeyguardPasswordViewController.this;
                if (((Boolean) obj).booleanValue()) {
                    keyguardPasswordViewController.mPasswordEntry.setBackground(keyguardPasswordViewController.mFocusedPasswordFieldBackground);
                } else {
                    keyguardPasswordViewController.mPasswordEntry.setBackground(keyguardPasswordViewController.mDefaultPasswordFieldBackground);
                }
            }
        });
        ViewGroup.LayoutParams layoutParams2 = this.mPasswordEntry.getLayoutParams();
        layoutParams2.height = (int) this.mView.getResources().getDimension(R.dimen.keyguard_password_field_height);
        layoutParams2.width = (int) this.mView.getResources().getDimension(R.dimen.keyguard_password_field_width);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        this.mPasswordEntry.setOnEditorActionListener(null);
        ((DevicePostureControllerImpl) this.mPostureController).removeCallback(this.mPostureCallback);
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputViewController
    public final void resetState() {
        this.mPasswordEntry.setTextOperationUser(UserHandle.of(this.mSelectedUserInteractor.getSelectedUserId()));
        this.mMessageAreaController.setMessage(R.string.keyguard_enter_your_password);
        boolean isEnabled = this.mPasswordEntry.isEnabled();
        ((KeyguardPasswordView) this.mView).setPasswordEntryEnabled(true);
        ((KeyguardPasswordView) this.mView).setPasswordEntryInputEnabled(true);
        if (this.mResumed && this.mPasswordEntry.isVisibleToUser() && isEnabled && this.mKeyguardViewController.isBouncerShowing() && ((KeyguardPasswordView) this.mView).isShown()) {
            KeyguardPasswordView keyguardPasswordView = (KeyguardPasswordView) this.mView;
            keyguardPasswordView.getClass();
            keyguardPasswordView.post(new KeyguardPasswordView$$ExternalSyntheticLambda0(keyguardPasswordView, 0));
        }
    }
}
