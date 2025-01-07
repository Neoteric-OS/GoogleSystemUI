package com.android.wm.shell.onehanded;

import android.content.ContentResolver;
import android.provider.Settings;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.wmshell.WMShell;
import com.android.systemui.wmshell.WMShell$10$$ExternalSyntheticLambda0;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.onehanded.OneHandedController;
import com.android.wm.shell.sysui.ShellController;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class OneHandedController$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ OneHandedController$$ExternalSyntheticLambda7(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                final OneHandedController oneHandedController = (OneHandedController) obj;
                oneHandedController.mShellCommandHandler.addDumpCallback(new BiConsumer() { // from class: com.android.wm.shell.onehanded.OneHandedController$$ExternalSyntheticLambda8
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj2, Object obj3) {
                        OneHandedController oneHandedController2 = OneHandedController.this;
                        PrintWriter printWriter = (PrintWriter) obj2;
                        printWriter.println();
                        printWriter.println("OneHandedController");
                        printWriter.print("  mOffSetFraction=");
                        printWriter.println(oneHandedController2.mOffSetFraction);
                        printWriter.print("  mLockedDisabled=");
                        printWriter.println(oneHandedController2.mLockedDisabled);
                        printWriter.print("  mUserId=");
                        printWriter.println(oneHandedController2.mUserId);
                        printWriter.print("  isShortcutEnabled=");
                        printWriter.println(oneHandedController2.isShortcutEnabled());
                        printWriter.print("  mIsSwipeToNotificationEnabled=");
                        printWriter.println(oneHandedController2.mIsSwipeToNotificationEnabled);
                        OneHandedDisplayAreaOrganizer oneHandedDisplayAreaOrganizer = oneHandedController2.mDisplayAreaOrganizer;
                        if (oneHandedDisplayAreaOrganizer != null) {
                            printWriter.println("OneHandedDisplayAreaOrganizer");
                            printWriter.print("  mDisplayLayout.rotation()=");
                            printWriter.println(oneHandedDisplayAreaOrganizer.mDisplayLayout.mRotation);
                            printWriter.print("  mDisplayAreaTokenMap=");
                            printWriter.println(oneHandedDisplayAreaOrganizer.mDisplayAreaTokenMap);
                            printWriter.print("  mDefaultDisplayBounds=");
                            printWriter.println(oneHandedDisplayAreaOrganizer.mDefaultDisplayBounds);
                            printWriter.print("  mIsReady=");
                            printWriter.println(oneHandedDisplayAreaOrganizer.mIsReady);
                            printWriter.print("  mLastVisualDisplayBounds=");
                            printWriter.println(oneHandedDisplayAreaOrganizer.mLastVisualDisplayBounds);
                            printWriter.print("  mLastVisualOffset=");
                            printWriter.println(oneHandedDisplayAreaOrganizer.mLastVisualOffset);
                            OneHandedAnimationController oneHandedAnimationController = oneHandedDisplayAreaOrganizer.mAnimationController;
                            if (oneHandedAnimationController != null) {
                                printWriter.println("OneHandedAnimationControllerstates: ");
                                printWriter.print("  mAnimatorMap=");
                                printWriter.println(oneHandedAnimationController.mAnimatorMap);
                                OneHandedSurfaceTransactionHelper oneHandedSurfaceTransactionHelper = oneHandedAnimationController.mSurfaceTransactionHelper;
                                if (oneHandedSurfaceTransactionHelper != null) {
                                    printWriter.println("OneHandedSurfaceTransactionHelperstates: ");
                                    printWriter.print("  mEnableCornerRadius=");
                                    printWriter.println(oneHandedSurfaceTransactionHelper.mEnableCornerRadius);
                                    printWriter.print("  mCornerRadiusAdjustment=");
                                    printWriter.println(oneHandedSurfaceTransactionHelper.mCornerRadiusAdjustment);
                                    printWriter.print("  mCornerRadius=");
                                    printWriter.println(oneHandedSurfaceTransactionHelper.mCornerRadius);
                                }
                            }
                        }
                        OneHandedTouchHandler oneHandedTouchHandler = oneHandedController2.mTouchHandler;
                        if (oneHandedTouchHandler != null) {
                            printWriter.println("OneHandedTouchHandler");
                            printWriter.print("  mLastUpdatedBounds=");
                            printWriter.println(oneHandedTouchHandler.mLastUpdatedBounds);
                        }
                        OneHandedTimeoutHandler oneHandedTimeoutHandler = oneHandedController2.mTimeoutHandler;
                        if (oneHandedTimeoutHandler != null) {
                            printWriter.println("OneHandedTimeoutHandler");
                            printWriter.print("  sTimeout=");
                            printWriter.println(oneHandedTimeoutHandler.mTimeout);
                            printWriter.print("  sListeners=");
                            printWriter.println(oneHandedTimeoutHandler.mListeners);
                        }
                        if (oneHandedController2.mState != null) {
                            StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "OneHandedState", "  sCurrentState=");
                            m.append(OneHandedState.sCurrentState);
                            printWriter.println(m.toString());
                        }
                        OneHandedTutorialHandler oneHandedTutorialHandler = oneHandedController2.mTutorialHandler;
                        if (oneHandedTutorialHandler != null) {
                            printWriter.println("OneHandedTutorialHandler");
                            printWriter.print("  isAttached=");
                            printWriter.println(oneHandedTutorialHandler.isAttached());
                            printWriter.print("  mCurrentState=");
                            printWriter.println(oneHandedTutorialHandler.mCurrentState);
                            printWriter.print("  mDisplayBounds=");
                            printWriter.println(oneHandedTutorialHandler.mDisplayBounds);
                            printWriter.print("  mTutorialAreaHeight=");
                            printWriter.println(oneHandedTutorialHandler.mTutorialAreaHeight);
                            printWriter.print("  mAlphaTransitionStart=");
                            printWriter.println(oneHandedTutorialHandler.mAlphaTransitionStart);
                            printWriter.print("  mAlphaAnimationDurationMs=");
                            printWriter.println(oneHandedTutorialHandler.mAlphaAnimationDurationMs);
                            BackgroundWindowManager backgroundWindowManager = oneHandedTutorialHandler.mBackgroundWindowManager;
                            printWriter.println("BackgroundWindowManager");
                            printWriter.print("  mDisplayBounds=");
                            printWriter.println(backgroundWindowManager.mDisplayBounds);
                            printWriter.print("  mViewHost=");
                            printWriter.println(backgroundWindowManager.mViewHost);
                            printWriter.print("  mLeash=");
                            printWriter.println(backgroundWindowManager.mLeash);
                            printWriter.print("  mBackgroundView=");
                            printWriter.println(backgroundWindowManager.mBackgroundView);
                        }
                        OneHandedAccessibilityUtil oneHandedAccessibilityUtil = oneHandedController2.mOneHandedAccessibilityUtil;
                        if (oneHandedAccessibilityUtil != null) {
                            printWriter.println("OneHandedAccessibilityUtil");
                            printWriter.print("  mPackageName=");
                            printWriter.println(oneHandedAccessibilityUtil.mPackageName);
                            printWriter.print("  mDescription=");
                            printWriter.println(oneHandedAccessibilityUtil.mDescription);
                        }
                        ContentResolver contentResolver = oneHandedController2.mContext.getContentResolver();
                        int i2 = oneHandedController2.mUserId;
                        oneHandedController2.mOneHandedSettingsUtil.getClass();
                        printWriter.println("OneHandedSettingsUtil");
                        printWriter.print("  isOneHandedModeEnable=");
                        printWriter.println(OneHandedSettingsUtil.getSettingsOneHandedModeEnabled(contentResolver, i2));
                        printWriter.print("  isSwipeToNotificationEnabled=");
                        printWriter.println(OneHandedSettingsUtil.getSettingsSwipeToNotificationEnabled(contentResolver, i2));
                        printWriter.print("  oneHandedTimeOut=");
                        printWriter.println(Settings.Secure.getIntForUser(contentResolver, "one_handed_mode_timeout", 8, i2));
                        printWriter.print("  tapsAppToExit=");
                        printWriter.println(Settings.Secure.getIntForUser(contentResolver, "taps_app_to_exit", 1, i2) == 1);
                        printWriter.print("  shortcutActivated=");
                        printWriter.println(Settings.Secure.getIntForUser(contentResolver, "one_handed_mode_activated", 0, i2) == 1);
                        printWriter.print("  tutorialShownCounts=");
                        printWriter.println(Settings.Secure.getIntForUser(contentResolver, "one_handed_tutorial_show_count", 0, i2));
                    }
                }, oneHandedController);
                OneHandedController.AnonymousClass1 anonymousClass1 = oneHandedController.mDisplaysChangedListener;
                DisplayController displayController = oneHandedController.mDisplayController;
                displayController.addDisplayWindowListener(anonymousClass1);
                displayController.addDisplayChangingController(oneHandedController);
                OneHandedController$$ExternalSyntheticLambda11 oneHandedController$$ExternalSyntheticLambda11 = new OneHandedController$$ExternalSyntheticLambda11(oneHandedController);
                OneHandedTouchHandler oneHandedTouchHandler = oneHandedController.mTouchHandler;
                oneHandedTouchHandler.mTouchEventCallback = oneHandedController$$ExternalSyntheticLambda11;
                OneHandedDisplayAreaOrganizer oneHandedDisplayAreaOrganizer = oneHandedController.mDisplayAreaOrganizer;
                oneHandedDisplayAreaOrganizer.mTransitionCallbacks.add(oneHandedTouchHandler);
                List list = oneHandedDisplayAreaOrganizer.mTransitionCallbacks;
                OneHandedTutorialHandler oneHandedTutorialHandler = oneHandedController.mTutorialHandler;
                list.add(oneHandedTutorialHandler);
                oneHandedDisplayAreaOrganizer.mTransitionCallbacks.add(oneHandedController.mTransitionCallBack);
                if (oneHandedController.mTaskChangeToExit) {
                    oneHandedController.mTaskStackListener.addListener(oneHandedController.mTaskStackListenerCallback);
                }
                oneHandedController.registerSettingObservers(oneHandedController.mUserId);
                oneHandedController.mTimeoutHandler.mListeners.add(new OneHandedController$$ExternalSyntheticLambda10(oneHandedController));
                oneHandedController.updateSettings();
                oneHandedController.updateDisplayLayout(oneHandedController.mContext.getDisplayId());
                oneHandedController.mAccessibilityManager.addAccessibilityStateChangeListener(oneHandedController.mAccessibilityStateChangeListener);
                oneHandedController.mState.mStateChangeListeners.add(oneHandedTutorialHandler);
                ShellController shellController = oneHandedController.mShellController;
                shellController.addConfigurationChangeListener(oneHandedController);
                shellController.addKeyguardChangeListener(oneHandedController);
                shellController.mUserChangeListeners.remove(oneHandedController);
                shellController.mUserChangeListeners.add(oneHandedController);
                shellController.addExternalInterface("extra_shell_one_handed", new Supplier() { // from class: com.android.wm.shell.onehanded.OneHandedController$$ExternalSyntheticLambda9
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        OneHandedController oneHandedController2 = OneHandedController.this;
                        oneHandedController2.getClass();
                        OneHandedController.IOneHandedImpl iOneHandedImpl = new OneHandedController.IOneHandedImpl();
                        iOneHandedImpl.attachInterface(iOneHandedImpl, "com.android.wm.shell.onehanded.IOneHanded");
                        iOneHandedImpl.mController = oneHandedController2;
                        return iOneHandedImpl;
                    }
                }, oneHandedController);
                break;
            case 1:
                ((OneHandedController) obj).startOneHanded();
                break;
            case 2:
                ((OneHandedController) obj).stopOneHanded();
                break;
            case 3:
                WMShell.AnonymousClass9 anonymousClass9 = ((OneHandedController) obj).mEventCallback;
                WMShell.this.mSysUiMainExecutor.execute(new WMShell$10$$ExternalSyntheticLambda0(3, anonymousClass9));
                break;
            case 4:
                ((OneHandedController) obj).onActivatedActionChanged();
                break;
            case 5:
                ((OneHandedController) obj).onEnabledSettingChanged();
                break;
            case 6:
                ((OneHandedController) obj).onSwipeToNotificationEnabledChanged();
                break;
            case 7:
                ((OneHandedController) obj).onShortcutEnabledChanged();
                break;
            default:
                OneHandedController.this.stopOneHanded();
                break;
        }
    }
}
