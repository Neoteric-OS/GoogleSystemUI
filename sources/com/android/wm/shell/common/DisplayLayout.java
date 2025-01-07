package com.android.wm.shell.common;

import android.R;
import android.content.res.Resources;
import android.graphics.Insets;
import android.graphics.Rect;
import android.view.DisplayCutout;
import android.view.InsetsState;
import android.view.WindowInsets;
import com.android.internal.policy.SystemBarUtils;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisplayLayout {
    public boolean mAllowSeamlessRotationDespiteNavBarMoving;
    public DisplayCutout mCutout;
    public int mDensityDpi;
    public boolean mHasNavigationBar;
    public boolean mHasStatusBar;
    public int mHeight;
    public InsetsState mInsetsState;
    public int mNavBarFrameHeight;
    public boolean mNavigationBarCanMove;
    public final Rect mNonDecorInsets;
    public boolean mReverseDefaultRotation;
    public int mRotation;
    public final Rect mStableInsets;
    public int mTaskbarFrameHeight;
    public int mUiMode;
    public int mWidth;

    public DisplayLayout() {
        this.mNonDecorInsets = new Rect();
        this.mStableInsets = new Rect();
        this.mHasNavigationBar = false;
        this.mHasStatusBar = false;
        this.mNavBarFrameHeight = 0;
        this.mTaskbarFrameHeight = 0;
        this.mAllowSeamlessRotationDespiteNavBarMoving = false;
        this.mNavigationBarCanMove = false;
        this.mReverseDefaultRotation = false;
        this.mInsetsState = new InsetsState();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DisplayLayout)) {
            return false;
        }
        DisplayLayout displayLayout = (DisplayLayout) obj;
        return this.mUiMode == displayLayout.mUiMode && this.mWidth == displayLayout.mWidth && this.mHeight == displayLayout.mHeight && Objects.equals(this.mCutout, displayLayout.mCutout) && this.mRotation == displayLayout.mRotation && this.mDensityDpi == displayLayout.mDensityDpi && Objects.equals(this.mNonDecorInsets, displayLayout.mNonDecorInsets) && Objects.equals(this.mStableInsets, displayLayout.mStableInsets) && this.mHasNavigationBar == displayLayout.mHasNavigationBar && this.mHasStatusBar == displayLayout.mHasStatusBar && this.mAllowSeamlessRotationDespiteNavBarMoving == displayLayout.mAllowSeamlessRotationDespiteNavBarMoving && this.mNavigationBarCanMove == displayLayout.mNavigationBarCanMove && this.mReverseDefaultRotation == displayLayout.mReverseDefaultRotation && this.mNavBarFrameHeight == displayLayout.mNavBarFrameHeight && this.mTaskbarFrameHeight == displayLayout.mTaskbarFrameHeight && Objects.equals(this.mInsetsState, displayLayout.mInsetsState);
    }

    public final void getStableBounds(Rect rect) {
        rect.set(0, 0, this.mWidth, this.mHeight);
        rect.inset(this.mStableInsets);
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.mUiMode), Integer.valueOf(this.mWidth), Integer.valueOf(this.mHeight), this.mCutout, Integer.valueOf(this.mRotation), Integer.valueOf(this.mDensityDpi), this.mNonDecorInsets, this.mStableInsets, Boolean.valueOf(this.mHasNavigationBar), Boolean.valueOf(this.mHasStatusBar), Integer.valueOf(this.mNavBarFrameHeight), Integer.valueOf(this.mTaskbarFrameHeight), Boolean.valueOf(this.mAllowSeamlessRotationDespiteNavBarMoving), Boolean.valueOf(this.mNavigationBarCanMove), Boolean.valueOf(this.mReverseDefaultRotation), this.mInsetsState);
    }

    public void recalcInsets(Resources resources) {
        int dimensionPixelSize;
        int i = this.mRotation;
        int i2 = this.mWidth;
        int i3 = this.mHeight;
        DisplayCutout displayCutout = this.mCutout;
        InsetsState insetsState = this.mInsetsState;
        int i4 = this.mUiMode;
        Rect rect = this.mNonDecorInsets;
        boolean z = this.mHasNavigationBar;
        rect.setEmpty();
        if (z) {
            Insets calculateInsets = insetsState.calculateInsets(insetsState.getDisplayFrame(), WindowInsets.Type.navigationBars(), false);
            char c = (i2 == i3 || !resources.getBoolean(R.bool.config_notificationReviewPermissions) || i2 <= i3) ? (char) 4 : i == 1 ? (char) 2 : (char) 1;
            boolean z2 = i2 > i3;
            if ((i4 & 15) == 3) {
                if (c == 4) {
                    dimensionPixelSize = resources.getDimensionPixelSize(z2 ? R.dimen.notification_action_emphasized_height : R.dimen.notification_action_disabled_container_alpha);
                } else {
                    dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_actions_collapsed_priority_width);
                }
            } else if (c == 4) {
                dimensionPixelSize = resources.getDimensionPixelSize(z2 ? R.dimen.notification_action_disabled_content_alpha : R.dimen.notification_action_disabled_alpha);
            } else {
                dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_action_list_margin_top);
            }
            if (c == 4) {
                rect.bottom = Math.max(calculateInsets.bottom, dimensionPixelSize);
            } else if (c == 2) {
                rect.right = Math.max(calculateInsets.right, dimensionPixelSize);
            } else if (c == 1) {
                rect.left = Math.max(calculateInsets.left, dimensionPixelSize);
            }
        }
        if (displayCutout != null) {
            rect.left = displayCutout.getSafeInsetLeft() + rect.left;
            rect.top = displayCutout.getSafeInsetTop() + rect.top;
            rect.right = displayCutout.getSafeInsetRight() + rect.right;
            rect.bottom = displayCutout.getSafeInsetBottom() + rect.bottom;
        }
        this.mStableInsets.set(this.mNonDecorInsets);
        boolean z3 = this.mHasStatusBar;
        if (z3) {
            Rect rect2 = this.mStableInsets;
            DisplayCutout displayCutout2 = this.mCutout;
            if (z3) {
                rect2.top = Math.max(rect2.top, SystemBarUtils.getStatusBarHeight(resources, displayCutout2));
            }
        }
        this.mNavBarFrameHeight = resources.getDimensionPixelSize(this.mWidth > this.mHeight ? R.dimen.navigation_bar_width_car_mode : R.dimen.navigation_bar_width);
        this.mTaskbarFrameHeight = SystemBarUtils.getTaskbarHeight(resources);
    }

    public final void rotateTo(int i, Resources resources) {
        int i2 = this.mWidth;
        int i3 = this.mHeight;
        int i4 = this.mRotation;
        boolean z = (((i - i4) + 4) % 4) % 2 != 0;
        this.mRotation = i;
        if (z) {
            this.mWidth = i3;
            this.mHeight = i2;
        }
        DisplayCutout displayCutout = this.mCutout;
        if (displayCutout != null) {
            this.mCutout = displayCutout.getRotated(i2, i3, i4, i);
        }
        recalcInsets(resources);
    }

    public final void set(DisplayLayout displayLayout) {
        this.mUiMode = displayLayout.mUiMode;
        this.mWidth = displayLayout.mWidth;
        this.mHeight = displayLayout.mHeight;
        this.mCutout = displayLayout.mCutout;
        this.mRotation = displayLayout.mRotation;
        this.mDensityDpi = displayLayout.mDensityDpi;
        this.mHasNavigationBar = displayLayout.mHasNavigationBar;
        this.mHasStatusBar = displayLayout.mHasStatusBar;
        this.mAllowSeamlessRotationDespiteNavBarMoving = displayLayout.mAllowSeamlessRotationDespiteNavBarMoving;
        this.mNavigationBarCanMove = displayLayout.mNavigationBarCanMove;
        this.mReverseDefaultRotation = displayLayout.mReverseDefaultRotation;
        this.mNavBarFrameHeight = displayLayout.mNavBarFrameHeight;
        this.mTaskbarFrameHeight = displayLayout.mTaskbarFrameHeight;
        this.mNonDecorInsets.set(displayLayout.mNonDecorInsets);
        this.mStableInsets.set(displayLayout.mStableInsets);
        this.mInsetsState.set(displayLayout.mInsetsState, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0085, code lost:
    
        if (r4 != false) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DisplayLayout(android.content.Context r7, android.view.Display r8) {
        /*
            r6 = this;
            r6.<init>()
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r6.mNonDecorInsets = r0
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            r6.mStableInsets = r0
            r0 = 0
            r6.mHasNavigationBar = r0
            r6.mHasStatusBar = r0
            r6.mNavBarFrameHeight = r0
            r6.mTaskbarFrameHeight = r0
            r6.mAllowSeamlessRotationDespiteNavBarMoving = r0
            r6.mNavigationBarCanMove = r0
            r6.mReverseDefaultRotation = r0
            android.view.InsetsState r1 = new android.view.InsetsState
            r1.<init>()
            r6.mInsetsState = r1
            int r1 = r8.getDisplayId()
            android.view.DisplayInfo r2 = new android.view.DisplayInfo
            r2.<init>()
            r8.getDisplayInfo(r2)
            android.content.res.Resources r8 = r7.getResources()
            r3 = 1
            if (r1 != 0) goto L60
            java.lang.String r4 = "qemu.hw.mainkeys"
            java.lang.String r4 = android.os.SystemProperties.get(r4)
            java.lang.String r5 = "1"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L4a
        L48:
            r7 = r0
            goto L88
        L4a:
            java.lang.String r5 = "0"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L54
        L52:
            r7 = r3
            goto L88
        L54:
            android.content.res.Resources r7 = r7.getResources()
            r4 = 17891872(0x1110220, float:2.6633818E-38)
            boolean r7 = r7.getBoolean(r4)
            goto L88
        L60:
            int r4 = r2.type
            r5 = 5
            if (r4 != r5) goto L6d
            int r4 = r2.ownerUid
            r5 = 1000(0x3e8, float:1.401E-42)
            if (r4 == r5) goto L6d
            r4 = r3
            goto L6e
        L6d:
            r4 = r0
        L6e:
            android.content.ContentResolver r7 = r7.getContentResolver()
            java.lang.String r5 = "force_desktop_mode_on_external_displays"
            int r7 = android.provider.Settings.Global.getInt(r7, r5, r0)
            if (r7 == 0) goto L7c
            r7 = r3
            goto L7d
        L7c:
            r7 = r0
        L7d:
            int r5 = r2.flags
            r5 = r5 & 64
            if (r5 != 0) goto L52
            if (r7 == 0) goto L48
            if (r4 != 0) goto L48
            goto L52
        L88:
            if (r1 != 0) goto L8b
            r0 = r3
        L8b:
            android.content.res.Configuration r1 = r8.getConfiguration()
            int r1 = r1.uiMode
            r6.mUiMode = r1
            int r1 = r2.logicalWidth
            r6.mWidth = r1
            int r1 = r2.logicalHeight
            r6.mHeight = r1
            int r1 = r2.rotation
            r6.mRotation = r1
            android.view.DisplayCutout r1 = r2.displayCutout
            r6.mCutout = r1
            int r1 = r2.logicalDensityDpi
            r6.mDensityDpi = r1
            r6.mHasNavigationBar = r7
            r6.mHasStatusBar = r0
            r7 = 17891360(0x1110020, float:2.6632384E-38)
            boolean r7 = r8.getBoolean(r7)
            r6.mAllowSeamlessRotationDespiteNavBarMoving = r7
            r7 = 17891811(0x11101e3, float:2.6633648E-38)
            boolean r7 = r8.getBoolean(r7)
            r6.mNavigationBarCanMove = r7
            r7 = 17891851(0x111020b, float:2.663376E-38)
            boolean r7 = r8.getBoolean(r7)
            r6.mReverseDefaultRotation = r7
            r6.recalcInsets(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.common.DisplayLayout.<init>(android.content.Context, android.view.Display):void");
    }

    public DisplayLayout(DisplayLayout displayLayout) {
        this.mNonDecorInsets = new Rect();
        this.mStableInsets = new Rect();
        this.mHasNavigationBar = false;
        this.mHasStatusBar = false;
        this.mNavBarFrameHeight = 0;
        this.mTaskbarFrameHeight = 0;
        this.mAllowSeamlessRotationDespiteNavBarMoving = false;
        this.mNavigationBarCanMove = false;
        this.mReverseDefaultRotation = false;
        this.mInsetsState = new InsetsState();
        set(displayLayout);
    }
}
