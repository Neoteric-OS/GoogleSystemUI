package com.android.wm.shell.common.pip;

import android.app.ActivityTaskManager;
import android.app.PictureInPictureParams;
import android.app.PictureInPictureUiState;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.ArraySet;
import android.util.Size;
import android.util.SparseArray;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import com.android.wm.shell.pip.phone.PipController$$ExternalSyntheticLambda3;
import com.android.wm.shell.pip.phone.PipController$$ExternalSyntheticLambda9;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipBoundsState {
    public float mAspectRatio;
    public float mBoundsScale;
    public final Context mContext;
    public boolean mHasUserMovedPip;
    public boolean mHasUserResizedPip;
    public int mImeHeight;
    public boolean mIsImeShowing;
    public ComponentName mLastPipComponentName;
    public PipController$$ExternalSyntheticLambda3 mOnMinimalSizeChangeCallback;
    public PipController$$ExternalSyntheticLambda9 mOnShelfVisibilityChangeCallback;
    public final PipDisplayLayoutState mPipDisplayLayoutState;
    public PipReentryState mPipReentryState;
    public final PhoneSizeSpecSource mSizeSpecSource;
    public int mStashOffset;
    public final Rect mBounds = new Rect();
    public final Rect mMovementBounds = new Rect();
    public final Rect mNormalBounds = new Rect();
    public final Rect mExpandedBounds = new Rect();
    public final Rect mNormalMovementBounds = new Rect();
    public final Rect mExpandedMovementBounds = new Rect();
    public final Rect mRestoreBounds = new Rect();
    public final Point mMaxSize = new Point();
    public final Point mMinSize = new Point();
    public int mStashedState = 0;
    public final LauncherState mLauncherState = new LauncherState();
    public final MotionBoundsState mMotionBoundsState = new MotionBoundsState();
    public final Set mRestrictedKeepClearAreas = new ArraySet();
    public final Set mUnrestrictedKeepClearAreas = new ArraySet();
    public final SparseArray mNamedUnrestrictedKeepClearAreas = new SparseArray();
    public final List mOnPipExclusionBoundsChangeCallbacks = new ArrayList();
    public final List mOnAspectRatioChangedCallbacks = new ArrayList();
    public final Rect mCachedLauncherShelfHeightKeepClearArea = new Rect();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LauncherState {
        public int mAppIconSizePx;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MotionBoundsState {
        public final Rect mBoundsInMotion = new Rect();
        public final Rect mAnimatingToBounds = new Rect();

        public final boolean isInMotion() {
            return !this.mBoundsInMotion.isEmpty();
        }

        public final void setBoundsInMotion(Rect rect) {
            this.mBoundsInMotion.set(rect);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PipReentryState {
        public final float mBoundsScale;
        public final float mSnapFraction;

        public PipReentryState(float f, float f2) {
            this.mBoundsScale = f;
            this.mSnapFraction = f2;
        }
    }

    public PipBoundsState(Context context, PhoneSizeSpecSource phoneSizeSpecSource, PipDisplayLayoutState pipDisplayLayoutState) {
        this.mContext = context;
        this.mStashOffset = context.getResources().getDimensionPixelSize(R.dimen.pip_stash_offset);
        this.mSizeSpecSource = phoneSizeSpecSource;
        this.mPipDisplayLayoutState = pipDisplayLayoutState;
        addPipExclusionBoundsChangeCallback(new Consumer() { // from class: com.android.wm.shell.common.pip.PipBoundsState$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                PipBoundsState.this.mBoundsScale = Math.min(r1.mBounds.width() / r1.mMaxSize.x, 1.0f);
            }
        });
    }

    public final void addPipExclusionBoundsChangeCallback(Consumer consumer) {
        this.mOnPipExclusionBoundsChangeCallbacks.add(consumer);
        Iterator it = this.mOnPipExclusionBoundsChangeCallbacks.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(getBounds());
        }
    }

    public void clearReentryState() {
        this.mPipReentryState = null;
    }

    public final void dump(PrintWriter printWriter) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "  PipBoundsState", "    mBounds=");
        m.append(this.mBounds);
        printWriter.println(m.toString());
        printWriter.println("    mNormalBounds=" + this.mNormalBounds);
        printWriter.println("    mExpandedBounds=" + this.mExpandedBounds);
        printWriter.println("    mMovementBounds=" + this.mMovementBounds);
        printWriter.println("    mNormalMovementBounds=" + this.mNormalMovementBounds);
        printWriter.println("    mExpandedMovementBounds=" + this.mExpandedMovementBounds);
        printWriter.println("    mLastPipComponentName=" + this.mLastPipComponentName);
        StringBuilder m2 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("    mAspectRatio="), this.mAspectRatio, printWriter, "    mStashedState="), this.mStashedState, printWriter, "    mStashOffset="), this.mStashOffset, printWriter, "    mIsImeShowing="), this.mIsImeShowing, printWriter, "    mImeHeight=");
        m2.append(this.mImeHeight);
        printWriter.println(m2.toString());
        printWriter.println("    mIsShelfShowing=false");
        printWriter.println("    mShelfHeight=0");
        StringBuilder m3 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("    mHasUserMovedPip="), this.mHasUserMovedPip, printWriter, "    mHasUserResizedPip="), this.mHasUserResizedPip, printWriter, "    mMinSize=");
        m3.append(this.mMinSize);
        printWriter.println(m3.toString());
        printWriter.println("    mMaxSize=" + this.mMaxSize);
        printWriter.println("    mBoundsScale" + this.mBoundsScale);
        PipReentryState pipReentryState = this.mPipReentryState;
        if (pipReentryState == null) {
            printWriter.println("    mPipReentryState=null");
        } else {
            StringBuilder m4 = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "    PipBoundsState$PipReentryState", "      mBoundsScale="), pipReentryState.mBoundsScale, printWriter, "      mSnapFraction=");
            m4.append(pipReentryState.mSnapFraction);
            printWriter.println(m4.toString());
        }
        LauncherState launcherState = this.mLauncherState;
        launcherState.getClass();
        printWriter.println("    ".concat(LauncherState.class.getSimpleName()));
        printWriter.println("        getAppIconSizePx=" + launcherState.mAppIconSizePx);
        MotionBoundsState motionBoundsState = this.mMotionBoundsState;
        motionBoundsState.getClass();
        printWriter.println("    ".concat(MotionBoundsState.class.getSimpleName()));
        printWriter.println("      mBoundsInMotion=" + motionBoundsState.mBoundsInMotion);
        printWriter.println("      mAnimatingToBounds=" + motionBoundsState.mAnimatingToBounds);
        PhoneSizeSpecSource phoneSizeSpecSource = this.mSizeSpecSource;
        phoneSizeSpecSource.getClass();
        String concat = "    ".concat("  ");
        printWriter.println(concat + "mOverrideMinSize=" + phoneSizeSpecSource.mOverrideMinSize);
        printWriter.println(concat + "mOverridableMinSize=" + phoneSizeSpecSource.mOverridableMinSize);
        printWriter.println(concat + "mDefaultMinSize=" + phoneSizeSpecSource.mDefaultMinSize);
        printWriter.println(concat + "mDefaultSizePercent=" + (phoneSizeSpecSource.getMIsSquareDisplay() ? phoneSizeSpecSource.mSystemPreferredDefaultSizePercentForSquareDisplay : phoneSizeSpecSource.mSystemPreferredDefaultSizePercent));
        printWriter.println(concat + "mMinimumSizePercent=" + (phoneSizeSpecSource.getMIsSquareDisplay() ? phoneSizeSpecSource.mSystemPreferredMinimumSizePercentForSquareDisplay : phoneSizeSpecSource.mSystemPreferredMinimumSizePercent));
        printWriter.println(concat + "mOptimizedAspectRatio=" + phoneSizeSpecSource.mOptimizedAspectRatio);
    }

    public final Rect getBounds() {
        return new Rect(this.mBounds);
    }

    public final Set getUnrestrictedKeepClearAreas() {
        if (this.mNamedUnrestrictedKeepClearAreas.size() == 0) {
            return this.mUnrestrictedKeepClearAreas;
        }
        ArraySet arraySet = new ArraySet(this.mUnrestrictedKeepClearAreas);
        for (int i = 0; i < this.mNamedUnrestrictedKeepClearAreas.size(); i++) {
            arraySet.add((Rect) this.mNamedUnrestrictedKeepClearAreas.get(this.mNamedUnrestrictedKeepClearAreas.keyAt(i)));
        }
        return arraySet;
    }

    public final boolean isStashed() {
        return this.mStashedState != 0;
    }

    public final void setAspectRatio(float f) {
        if (Float.compare(this.mAspectRatio, f) != 0) {
            this.mAspectRatio = f;
            Iterator it = this.mOnAspectRatioChangedCallbacks.iterator();
            while (it.hasNext()) {
                ((Consumer) it.next()).accept(Float.valueOf(this.mAspectRatio));
            }
        }
    }

    public final void setBounds(Rect rect) {
        this.mBounds.set(rect);
        Iterator it = this.mOnPipExclusionBoundsChangeCallbacks.iterator();
        while (it.hasNext()) {
            ((Consumer) it.next()).accept(rect);
        }
    }

    public final void setBoundsStateForEntry(ComponentName componentName, ActivityInfo activityInfo, PictureInPictureParams pictureInPictureParams, PipBoundsAlgorithm pipBoundsAlgorithm) {
        float f;
        boolean equals;
        PipController$$ExternalSyntheticLambda3 pipController$$ExternalSyntheticLambda3;
        setLastPipComponentName(componentName);
        if (pictureInPictureParams != null) {
            pipBoundsAlgorithm.getClass();
            if (pictureInPictureParams.hasSetAspectRatio()) {
                f = pictureInPictureParams.getAspectRatioFloat();
                setAspectRatio(f);
                Size minimalSize = pipBoundsAlgorithm.getMinimalSize(activityInfo);
                PhoneSizeSpecSource phoneSizeSpecSource = this.mSizeSpecSource;
                equals = Objects.equals(minimalSize, phoneSizeSpecSource.getOverrideMinSize());
                phoneSizeSpecSource.mOverrideMinSize = minimalSize;
                if (!equals || (pipController$$ExternalSyntheticLambda3 = this.mOnMinimalSizeChangeCallback) == null) {
                }
                pipController$$ExternalSyntheticLambda3.run();
                return;
            }
        }
        f = pipBoundsAlgorithm.mDefaultAspectRatio;
        setAspectRatio(f);
        Size minimalSize2 = pipBoundsAlgorithm.getMinimalSize(activityInfo);
        PhoneSizeSpecSource phoneSizeSpecSource2 = this.mSizeSpecSource;
        equals = Objects.equals(minimalSize2, phoneSizeSpecSource2.getOverrideMinSize());
        phoneSizeSpecSource2.mOverrideMinSize = minimalSize2;
        if (equals) {
        }
    }

    public final void setLastPipComponentName(ComponentName componentName) {
        boolean equals = Objects.equals(this.mLastPipComponentName, componentName);
        this.mLastPipComponentName = componentName;
        if (equals) {
            return;
        }
        clearReentryState();
        this.mHasUserResizedPip = false;
        this.mHasUserMovedPip = false;
    }

    public final void setNamedUnrestrictedKeepClearArea(int i, Rect rect) {
        if (rect == null) {
            this.mNamedUnrestrictedKeepClearAreas.remove(i);
            return;
        }
        this.mNamedUnrestrictedKeepClearAreas.put(i, rect);
        if (i == 0) {
            this.mCachedLauncherShelfHeightKeepClearArea.set(rect);
        }
    }

    public final void setStashed(int i) {
        if (this.mStashedState == i) {
            return;
        }
        this.mStashedState = i;
        try {
            ActivityTaskManager.getService().onPictureInPictureUiStateChanged(new PictureInPictureUiState(i != 0));
        } catch (RemoteException | IllegalStateException unused) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 6744113849991380724L, 0, "PipBoundsState");
            }
        }
    }

    public final void updateMinMaxSize(float f) {
        PhoneSizeSpecSource phoneSizeSpecSource = this.mSizeSpecSource;
        Size minSize = phoneSizeSpecSource.getMinSize(f);
        this.mMinSize.set(minSize.getWidth(), minSize.getHeight());
        Size maxSize = phoneSizeSpecSource.getMaxSize(f);
        this.mMaxSize.set(maxSize.getWidth(), maxSize.getHeight());
    }
}
