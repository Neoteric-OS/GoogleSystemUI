package com.android.wm.shell.common.pip;

import android.app.PictureInPictureParams;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Size;
import android.view.Gravity;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.R;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.io.PrintWriter;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipBoundsAlgorithm {
    public float mDefaultAspectRatio;
    public int mDefaultStackGravity;
    public float mMaxAspectRatio;
    public float mMinAspectRatio;
    public final PipBoundsState mPipBoundsState;
    public final PipDisplayLayoutState mPipDisplayLayoutState;
    public final PhonePipKeepClearAlgorithm mPipKeepClearAlgorithm;
    public final PhoneSizeSpecSource mSizeSpecSource;
    public final PipSnapAlgorithm mSnapAlgorithm;

    public PipBoundsAlgorithm(Context context, PipBoundsState pipBoundsState, PipSnapAlgorithm pipSnapAlgorithm, PhonePipKeepClearAlgorithm phonePipKeepClearAlgorithm, PipDisplayLayoutState pipDisplayLayoutState, PhoneSizeSpecSource phoneSizeSpecSource) {
        this.mPipBoundsState = pipBoundsState;
        this.mSnapAlgorithm = pipSnapAlgorithm;
        this.mPipKeepClearAlgorithm = phonePipKeepClearAlgorithm;
        this.mPipDisplayLayoutState = pipDisplayLayoutState;
        this.mSizeSpecSource = phoneSizeSpecSource;
        reloadResources(context);
        pipBoundsState.setAspectRatio(this.mDefaultAspectRatio);
    }

    public static Rect getValidSourceHintRect(PictureInPictureParams pictureInPictureParams, Rect rect) {
        Rect sourceRectHint = (pictureInPictureParams == null || !pictureInPictureParams.hasSourceBoundsHint()) ? null : pictureInPictureParams.getSourceRectHint();
        if (sourceRectHint == null || !rect.contains(sourceRectHint)) {
            return null;
        }
        return sourceRectHint;
    }

    public static boolean isSourceRectHintValidForEnterPip(Rect rect, Rect rect2) {
        if (rect == null || rect.isEmpty()) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 195527804713777749L, 0, null);
            }
            return false;
        }
        if (rect.width() <= rect2.width() || rect.height() <= rect2.height()) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -3421291399359087650L, 0, String.valueOf(rect), String.valueOf(rect2));
            }
            return false;
        }
        if (Math.abs((rect2.width() / rect2.height()) - (rect.width() / rect.height())) <= 0.01f) {
            return true;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 4209504781583464887L, 0, String.valueOf(rect), String.valueOf(rect2));
        }
        return false;
    }

    public final Rect adjustNormalBoundsToFitMenu(Rect rect, Size size) {
        int height;
        int round;
        if (size == null) {
            return rect;
        }
        if (rect.width() >= size.getWidth() && rect.height() >= size.getHeight()) {
            return rect;
        }
        Rect rect2 = new Rect();
        boolean z = size.getWidth() > rect.width();
        boolean z2 = size.getHeight() > rect.height();
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        if (z && z2) {
            if (size.getWidth() / rect.width() > size.getHeight() / rect.height()) {
                round = size.getWidth();
                height = Math.round(round / pipBoundsState.mAspectRatio);
            } else {
                height = size.getHeight();
                round = Math.round(height * pipBoundsState.mAspectRatio);
            }
        } else if (z) {
            round = size.getWidth();
            height = Math.round(round / pipBoundsState.mAspectRatio);
        } else {
            height = size.getHeight();
            round = Math.round(height * pipBoundsState.mAspectRatio);
        }
        rect2.set(0, 0, round, height);
        transformBoundsToAspectRatio(rect2, pipBoundsState.mAspectRatio, true, true);
        return rect2;
    }

    public final void dump(PrintWriter printWriter, String str) {
        String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "  ");
        printWriter.println(str + "PipBoundsAlgorithm");
        printWriter.println(m + "mDefaultAspectRatio=" + this.mDefaultAspectRatio);
        printWriter.println(m + "mMinAspectRatio=" + this.mMinAspectRatio);
        printWriter.println(m + "mMaxAspectRatio=" + this.mMaxAspectRatio);
        printWriter.println(m + "mDefaultStackGravity=" + this.mDefaultStackGravity);
        printWriter.println(m + "mSnapAlgorithm" + this.mSnapAlgorithm);
    }

    public final Rect getDefaultBounds(float f, Size size) {
        Rect rect = new Rect();
        PipSnapAlgorithm pipSnapAlgorithm = this.mSnapAlgorithm;
        if (f != -1.0f && size != null) {
            rect.set(0, 0, size.getWidth(), size.getHeight());
            Rect movementBounds = getMovementBounds(rect, true);
            pipSnapAlgorithm.getClass();
            PipSnapAlgorithm.applySnapFraction(rect, movementBounds, f);
            return rect;
        }
        Rect rect2 = new Rect();
        getInsetBounds(rect2);
        Size defaultSize = this.mSizeSpecSource.getDefaultSize(this.mDefaultAspectRatio);
        if (f != -1.0f) {
            rect.set(0, 0, defaultSize.getWidth(), defaultSize.getHeight());
            Rect movementBounds2 = getMovementBounds(rect, true);
            pipSnapAlgorithm.getClass();
            PipSnapAlgorithm.applySnapFraction(rect, movementBounds2, f);
        } else {
            int i = this.mDefaultStackGravity;
            int width = defaultSize.getWidth();
            int height = defaultSize.getHeight();
            PipBoundsState pipBoundsState = this.mPipBoundsState;
            Gravity.apply(i, width, height, rect2, 0, Math.max(pipBoundsState.mIsImeShowing ? pipBoundsState.mImeHeight : 0, 0), rect);
        }
        return rect;
    }

    public final Rect getEntryDestinationBounds() {
        Rect entryDestinationBoundsIgnoringKeepClearAreas = getEntryDestinationBoundsIgnoringKeepClearAreas();
        Rect rect = new Rect();
        getInsetBounds(rect);
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        return this.mPipKeepClearAlgorithm.findUnoccludedPosition(entryDestinationBoundsIgnoringKeepClearAreas, pipBoundsState.mRestrictedKeepClearAreas, pipBoundsState.getUnrestrictedKeepClearAreas(), rect);
    }

    public final Rect getEntryDestinationBoundsIgnoringKeepClearAreas() {
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        PipBoundsState.PipReentryState pipReentryState = pipBoundsState.mPipReentryState;
        Rect defaultBounds = getDefaultBounds(-1.0f, null);
        if (pipReentryState != null) {
            float f = pipBoundsState.mMaxSize.x;
            float f2 = pipReentryState.mBoundsScale;
            defaultBounds.set(getDefaultBounds(pipReentryState.mSnapFraction, new Size(Math.round(f * f2), Math.round(pipBoundsState.mMaxSize.y * f2))));
        }
        return transformBoundsToAspectRatioIfValid(defaultBounds, pipBoundsState.mAspectRatio, false, pipReentryState != null);
    }

    public final void getInsetBounds(Rect rect) {
        rect.set(this.mPipDisplayLayoutState.getInsetBounds());
    }

    public final Size getMinimalSize(ActivityInfo activityInfo) {
        ActivityInfo.WindowLayout windowLayout;
        if (activityInfo == null || (windowLayout = activityInfo.windowLayout) == null || windowLayout.minWidth <= 0 || windowLayout.minHeight <= 0) {
            return null;
        }
        int i = windowLayout.minWidth;
        PhoneSizeSpecSource phoneSizeSpecSource = this.mSizeSpecSource;
        return new Size(Math.max(i, phoneSizeSpecSource.getOverrideMinEdgeSize()), Math.max(windowLayout.minHeight, phoneSizeSpecSource.getOverrideMinEdgeSize()));
    }

    public final Rect getMovementBounds(Rect rect, boolean z) {
        int i;
        Rect rect2 = new Rect();
        getInsetBounds(rect2);
        if (z) {
            PipBoundsState pipBoundsState = this.mPipBoundsState;
            if (pipBoundsState.mIsImeShowing) {
                i = pipBoundsState.mImeHeight;
                getMovementBounds(rect, rect2, rect2, i);
                return rect2;
            }
        }
        i = 0;
        getMovementBounds(rect, rect2, rect2, i);
        return rect2;
    }

    public final void reloadResources(Context context) {
        Resources resources = context.getResources();
        this.mDefaultAspectRatio = resources.getFloat(R.dimen.config_pictureInPictureDefaultAspectRatio);
        this.mDefaultStackGravity = resources.getInteger(R.integer.config_defaultPictureInPictureGravity);
        this.mMinAspectRatio = resources.getFloat(android.R.dimen.config_qsTileStrokeWidthInactive);
        this.mMaxAspectRatio = resources.getFloat(android.R.dimen.config_qsTileStrokeWidthActive);
    }

    public final void transformBoundsToAspectRatio(Rect rect, float f, boolean z, boolean z2) {
        Size size;
        float snapFraction = this.mSnapAlgorithm.getSnapFraction(this.mPipBoundsState.mStashedState, rect, getMovementBounds(rect, true));
        PhoneSizeSpecSource phoneSizeSpecSource = this.mSizeSpecSource;
        if (z || z2) {
            if (new Size(rect.width(), rect.height()).equals(phoneSizeSpecSource.mOverrideMinSize)) {
                size = phoneSizeSpecSource.adjustOverrideMinSizeToAspectRatio(f);
                Intrinsics.checkNotNull(size);
            } else {
                float width = r8.getWidth() / phoneSizeSpecSource.getMaxSize(r8.getWidth() / r8.getHeight()).getWidth();
                Size maxSize = phoneSizeSpecSource.getMaxSize(f);
                int round = Math.round(maxSize.getWidth() * width);
                int round2 = Math.round(maxSize.getHeight() * width);
                int overrideMinEdgeSize = phoneSizeSpecSource.mOverrideMinSize == null ? phoneSizeSpecSource.mDefaultMinSize : phoneSizeSpecSource.getOverrideMinEdgeSize();
                if (round < overrideMinEdgeSize && f <= 1.0f) {
                    round2 = Math.round(overrideMinEdgeSize / f);
                    round = overrideMinEdgeSize;
                } else if (round2 < overrideMinEdgeSize && f > 1.0f) {
                    round = Math.round(overrideMinEdgeSize * f);
                    round2 = overrideMinEdgeSize;
                }
                size = new Size(round, round2);
            }
        } else {
            size = phoneSizeSpecSource.getDefaultSize(f);
        }
        int centerX = (int) (rect.centerX() - (size.getWidth() / 2.0f));
        int centerY = (int) (rect.centerY() - (size.getHeight() / 2.0f));
        rect.set(centerX, centerY, size.getWidth() + centerX, size.getHeight() + centerY);
        PipSnapAlgorithm.applySnapFraction(rect, getMovementBounds(rect, true), snapFraction);
    }

    public final Rect transformBoundsToAspectRatioIfValid(Rect rect, float f, boolean z, boolean z2) {
        Rect rect2 = new Rect(rect);
        if (Float.compare(this.mMinAspectRatio, f) <= 0 && Float.compare(f, this.mMaxAspectRatio) <= 0) {
            transformBoundsToAspectRatio(rect2, f, z, z2);
        }
        return rect2;
    }

    public static void getMovementBounds(Rect rect, Rect rect2, Rect rect3, int i) {
        rect3.set(rect2);
        rect3.right = Math.max(rect2.left, rect2.right - rect.width());
        rect3.bottom = Math.max(rect2.top, rect2.bottom - rect.height()) - i;
    }
}
