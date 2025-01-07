package com.android.systemui.statusbar.notification.stack;

import android.content.Context;
import android.util.MathUtils;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.shade.transition.LargeScreenShadeInterpolator;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.StatusBarState;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator$setStackScroller$1;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.AvalancheController;
import com.android.wm.shell.R;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AmbientState implements Dumpable {
    public float mAppearFraction;
    public final KeyguardBypassController mBypassController;
    public boolean mClearAllInProgress;
    public int mContentHeight;
    public float mCurrentScrollVelocity;
    public boolean mDozing;
    public float mExpandingVelocity;
    public boolean mExpansionChanging;
    public float mExpansionFraction;
    public float mFractionToShade;
    public float mHideAmount;
    public boolean mHideSensitive;
    public boolean mIsClosing;
    public boolean mIsFlinging;
    public boolean mIsSmallScreen;
    public boolean mIsSwipingUp;
    public final LargeScreenShadeInterpolator mLargeScreenShadeInterpolator;
    public ExpandableView mLastVisibleBackgroundChild;
    public int mLayoutHeight;
    public int mLayoutMaxHeight;
    public int mLayoutMinHeight;
    public float mMaxHeadsUpTranslation;
    public NotificationWakeUpCoordinator$setStackScroller$1 mOnPulseHeightChangedListener;
    public float mOverExpansion;
    public float mOverScrollBottomAmount;
    public float mOverScrollTopAmount;
    public boolean mPanelTracking;
    public boolean mPulsing;
    public ExpandableNotificationRow mPulsingRow;
    public int mScrollY;
    public final NotificationSectionsManager mSectionProvider;
    public boolean mShadeExpanded;
    public NotificationShelf mShelf;
    public float mStackEndHeight;
    public int mStackTopMargin;
    public float mStackTranslation;
    public StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    public int mStatusBarState;
    public int mTopPadding;
    public ExpandableNotificationRow mTrackedHeadsUpRow;
    public boolean mUseSplitShade;
    public int mZDistanceBetweenElements;
    public float mPulseHeight = 100000.0f;
    public float mDozeAmount = 0.0f;
    public float mStackY = 0.0f;
    public float mStackHeight = 0.0f;
    public boolean mIsFlingRequiredAfterLockScreenSwipeUp = false;

    public AmbientState(Context context, DumpManager dumpManager, NotificationSectionsManager notificationSectionsManager, KeyguardBypassController keyguardBypassController, StatusBarKeyguardViewManager statusBarKeyguardViewManager, LargeScreenShadeInterpolator largeScreenShadeInterpolator, AvalancheController avalancheController) {
        this.mSectionProvider = notificationSectionsManager;
        this.mBypassController = keyguardBypassController;
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.mLargeScreenShadeInterpolator = largeScreenShadeInterpolator;
        this.mZDistanceBetweenElements = Math.max(1, context.getResources().getDimensionPixelSize(R.dimen.z_distance_between_notifications));
        dumpManager.registerDumpable(this);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("mStackTop=0.0");
        printWriter.println("mStackCutoff=0.0");
        printWriter.println("mHeadsUpTop=0.0");
        printWriter.println("mHeadsUpBottom=0.0");
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("mTopPadding="), this.mTopPadding, printWriter, "mStackTopMargin="), this.mStackTopMargin, printWriter, "mStackTranslation="), this.mStackTranslation, printWriter, "mLayoutMinHeight="), this.mLayoutMinHeight, printWriter, "mLayoutMaxHeight="), this.mLayoutMaxHeight, printWriter, "mLayoutHeight="), this.mLayoutHeight, printWriter, "mContentHeight="), this.mContentHeight, printWriter, "mHideSensitive="), this.mHideSensitive, printWriter, "mShadeExpanded="), this.mShadeExpanded, printWriter, "mClearAllInProgress="), this.mClearAllInProgress, printWriter, "mStatusBarState=");
        m.append(StatusBarState.toString(this.mStatusBarState));
        printWriter.println(m.toString());
        StringBuilder m2 = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("mExpansionChanging="), this.mExpansionChanging, printWriter, "mPanelFullWidth="), this.mIsSmallScreen, printWriter, "mPulsing="), this.mPulsing, printWriter, "mPulseHeight="), this.mPulseHeight, printWriter, "mTrackedHeadsUpRow.key=");
        ExpandableNotificationRow expandableNotificationRow = this.mTrackedHeadsUpRow;
        m2.append(expandableNotificationRow == null ? "null" : NotificationUtils.logKey(expandableNotificationRow.mEntry));
        printWriter.println(m2.toString());
        StringBuilder m3 = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("mMaxHeadsUpTranslation="), this.mMaxHeadsUpTranslation, printWriter, "mDozeAmount="), this.mDozeAmount, printWriter, "mDozing="), this.mDozing, printWriter, "mFractionToShade="), this.mFractionToShade, printWriter, "mHideAmount="), this.mHideAmount, printWriter, "mAppearFraction="), this.mAppearFraction, printWriter, "mExpansionFraction=");
        m3.append(this.mExpansionFraction);
        printWriter.println(m3.toString());
        printWriter.println("mQsExpansionFraction=0.0");
        StringBuilder m4 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("mExpandingVelocity="), this.mExpandingVelocity, printWriter, "mOverScrollTopAmount="), this.mOverScrollTopAmount, printWriter, "mOverScrollBottomAmount="), this.mOverScrollBottomAmount, printWriter, "mOverExpansion="), this.mOverExpansion, printWriter, "mStackHeight="), this.mStackHeight, printWriter, "mStackEndHeight="), this.mStackEndHeight, printWriter, "mStackY="), this.mStackY, printWriter, "mScrollY="), this.mScrollY, printWriter, "mCurrentScrollVelocity="), this.mCurrentScrollVelocity, printWriter, "mIsSwipingUp="), this.mIsSwipingUp, printWriter, "mPanelTracking="), this.mPanelTracking, printWriter, "mIsFlinging="), this.mIsFlinging, printWriter, "mIsFlingRequiredAfterLockScreenSwipeUp="), this.mIsFlingRequiredAfterLockScreenSwipeUp, printWriter, "mZDistanceBetweenElements=");
        m4.append(this.mZDistanceBetweenElements);
        printWriter.println(m4.toString());
        printWriter.println("mBaseZHeight=0");
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("mIsClosing="), this.mIsClosing, printWriter);
    }

    public final int getInnerHeight(boolean z) {
        if (this.mDozeAmount == 1.0f && !isPulseExpanding()) {
            return this.mShelf.getHeight();
        }
        int max = Math.max(this.mLayoutMinHeight, Math.min(this.mLayoutHeight, this.mContentHeight) - this.mTopPadding);
        if (z) {
            return max;
        }
        float f = max;
        return (int) MathUtils.lerp(f, Math.min(this.mPulseHeight, f), this.mDozeAmount);
    }

    public final ExpandableNotificationRow getTrackedHeadsUpRow() {
        ExpandableNotificationRow expandableNotificationRow = this.mTrackedHeadsUpRow;
        if (expandableNotificationRow == null || !expandableNotificationRow.isAboveShelf()) {
            return null;
        }
        return this.mTrackedHeadsUpRow;
    }

    public boolean isFlingRequiredAfterLockScreenSwipeUp() {
        return this.mIsFlingRequiredAfterLockScreenSwipeUp;
    }

    public final boolean isFullyHidden() {
        return this.mHideAmount == 1.0f;
    }

    public final boolean isHiddenAtAll() {
        return this.mHideAmount != 0.0f;
    }

    public final boolean isOnKeyguard() {
        return this.mStatusBarState == 1;
    }

    public final boolean isPulseExpanding() {
        return (this.mPulseHeight == 100000.0f || this.mDozeAmount == 0.0f || this.mHideAmount == 1.0f) ? false : true;
    }

    public void setFlingRequiredAfterLockScreenSwipeUp(boolean z) {
        this.mIsFlingRequiredAfterLockScreenSwipeUp = z;
    }

    public final void setPulseHeight(float f) {
        if (f != this.mPulseHeight) {
            this.mPulseHeight = f;
            NotificationWakeUpCoordinator$setStackScroller$1 notificationWakeUpCoordinator$setStackScroller$1 = this.mOnPulseHeightChangedListener;
            if (notificationWakeUpCoordinator$setStackScroller$1 != null) {
                notificationWakeUpCoordinator$setStackScroller$1.run();
            }
        }
    }
}
