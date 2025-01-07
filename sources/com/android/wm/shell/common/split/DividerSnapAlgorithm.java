package com.android.wm.shell.common.split;

import android.R;
import android.content.res.Resources;
import android.graphics.Rect;
import androidx.appcompat.view.menu.CascadingMenuPopup$$ExternalSyntheticOutline0;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DividerSnapAlgorithm {
    public final SnapTarget mDismissEndTarget;
    public final SnapTarget mDismissStartTarget;
    public final int mDisplayHeight;
    public final int mDisplayWidth;
    public final int mDividerSize;
    public final SnapTarget mFirstSplitTarget;
    public final boolean mFreeSnapMode;
    public final Rect mInsets;
    public final boolean mIsHorizontalDivision;
    public final SnapTarget mLastSplitTarget;
    public final SnapTarget mMiddleTarget;
    public final float mMinDismissVelocityPxPerSecond;
    public final float mMinFlingVelocityPxPerSecond;
    public final int mMinimalSizeResizableTask;
    public final ArrayList mTargets;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SnapTarget {
        public final float distanceMultiplier;
        public final int position;
        public final int snapPosition;

        public SnapTarget(int i, int i2) {
            this(i, 1.0f, i2);
        }

        public SnapTarget(int i, float f, int i2) {
            this.position = i;
            this.snapPosition = i2;
            this.distanceMultiplier = f;
        }
    }

    public DividerSnapAlgorithm(Resources resources, int i, int i2, int i3, boolean z, Rect rect, int i4) {
        int i5;
        ArrayList arrayList = new ArrayList();
        this.mTargets = arrayList;
        Rect rect2 = new Rect();
        this.mInsets = rect2;
        this.mMinFlingVelocityPxPerSecond = resources.getDisplayMetrics().density * 400.0f;
        this.mMinDismissVelocityPxPerSecond = resources.getDisplayMetrics().density * 600.0f;
        this.mDividerSize = i3;
        this.mDisplayWidth = i;
        this.mDisplayHeight = i2;
        this.mIsHorizontalDivision = z;
        rect2.set(rect);
        int integer = resources.getInteger(R.integer.config_doublePressOnPowerBehavior);
        this.mFreeSnapMode = resources.getBoolean(R.bool.config_dontPreferApn);
        float fraction = resources.getFraction(R.fraction.docked_stack_divider_fixed_ratio, 1, 1);
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.dialog_no_title_padding_top);
        this.mMinimalSizeResizableTask = dimensionPixelSize;
        boolean z2 = resources.getBoolean(R.bool.config_guestUserAllowEphemeralStateChange);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.text_size_display_2_material);
        arrayList.clear();
        int i6 = -i3;
        int i7 = z ? i2 : i;
        arrayList.add(new SnapTarget(i4 == 3 ? i6 + rect2.left : i6, 0.35f, 11));
        if (integer == 0) {
            i5 = i7;
            int i8 = z ? rect2.top : rect2.left;
            int i9 = z ? i2 - rect2.bottom : i - rect2.right;
            int floor = (int) Math.floor(((z ? i - rect2.right : i2 - rect2.bottom) - (z ? rect2.left : rect2.top)) * 0.5625f);
            addNonDismissingTargets(i8 + floor, (i9 - floor) - i3, i5, z);
        } else if (integer != 1) {
            if (integer == 2) {
                addMiddleTarget(z);
            } else if (integer == 3) {
                int i10 = dimensionPixelSize2 + rect2.top;
                if (!z) {
                    if (i4 == 1) {
                        i10 += rect2.left;
                    } else if (i4 == 3) {
                        i10 = ((i - i10) - rect2.right) - i3;
                    }
                }
                arrayList.add(new SnapTarget(i10, 13));
            }
            i5 = i7;
        } else {
            int i11 = z ? rect2.top : rect2.left;
            int i12 = z ? i2 - rect2.bottom : i - rect2.right;
            int i13 = ((int) (fraction * (i12 - i11))) - (i3 / 2);
            i13 = z2 ? Math.max(i13, dimensionPixelSize) : i13;
            i5 = i7;
            addNonDismissingTargets(i11 + i13, (i12 - i13) - i3, i5, z);
        }
        arrayList.add(new SnapTarget(i5, 0.35f, 12));
        this.mFirstSplitTarget = (SnapTarget) arrayList.get(1);
        this.mLastSplitTarget = (SnapTarget) CascadingMenuPopup$$ExternalSyntheticOutline0.m(arrayList, 2);
        this.mDismissStartTarget = (SnapTarget) arrayList.get(0);
        this.mDismissEndTarget = (SnapTarget) CascadingMenuPopup$$ExternalSyntheticOutline0.m(arrayList, 1);
        SnapTarget snapTarget = (SnapTarget) arrayList.get(arrayList.size() / 2);
        this.mMiddleTarget = snapTarget;
        snapTarget.getClass();
    }

    public final void addMiddleTarget(boolean z) {
        int i;
        int i2;
        Rect rect = this.mInsets;
        int i3 = z ? rect.top : rect.left;
        if (z) {
            i = rect.bottom;
            i2 = this.mDisplayHeight;
        } else {
            i = rect.right;
            i2 = this.mDisplayWidth;
        }
        this.mTargets.add(new SnapTarget(((((i2 - i) - i3) / 2) + i3) - (this.mDividerSize / 2), 1));
    }

    public final void addNonDismissingTargets(int i, int i2, int i3, boolean z) {
        boolean z2 = this.mIsHorizontalDivision;
        int i4 = i - (z2 ? this.mInsets.top : this.mInsets.left);
        int i5 = this.mMinimalSizeResizableTask;
        if (i4 >= i5) {
            this.mTargets.add(new SnapTarget(i, 0));
        }
        addMiddleTarget(z);
        if ((i3 - (z2 ? this.mInsets.bottom : this.mInsets.right)) - (this.mDividerSize + i2) >= i5) {
            this.mTargets.add(new SnapTarget(i2, 2));
        }
    }

    public final SnapTarget snap(int i, boolean z) {
        SnapTarget snapTarget;
        SnapTarget snapTarget2;
        SnapTarget snapTarget3;
        if (this.mFreeSnapMode && (snapTarget = this.mFirstSplitTarget) != (snapTarget2 = this.mMiddleTarget) && (snapTarget3 = this.mLastSplitTarget) != snapTarget2 && snapTarget.position < i && i < snapTarget3.position) {
            return new SnapTarget(i, 10);
        }
        int size = this.mTargets.size();
        int i2 = -1;
        float f = Float.MAX_VALUE;
        for (int i3 = 0; i3 < size; i3++) {
            SnapTarget snapTarget4 = (SnapTarget) this.mTargets.get(i3);
            float abs = Math.abs(i - snapTarget4.position);
            if (z) {
                abs /= snapTarget4.distanceMultiplier;
            }
            if (abs < f) {
                i2 = i3;
                f = abs;
            }
        }
        return (SnapTarget) this.mTargets.get(i2);
    }
}
