package com.android.wm.shell.common.pip;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Size;
import android.util.TypedValue;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import com.android.wm.shell.common.DisplayLayout;
import java.io.PrintWriter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipDisplayLayoutState {
    public Context mContext;
    public int mDisplayId;
    public DisplayLayout mDisplayLayout;
    public Point mScreenEdgeInsets;

    public final void dump(PrintWriter printWriter) {
        StringBuilder m = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "  PipDisplayLayoutState", "    mDisplayId="), this.mDisplayId, printWriter, "    getDisplayBounds=");
        m.append(getDisplayBounds());
        printWriter.println(m.toString());
        printWriter.println("    mScreenEdgeInsets=" + this.mScreenEdgeInsets);
    }

    public final Rect getDisplayBounds() {
        DisplayLayout displayLayout = this.mDisplayLayout;
        return new Rect(0, 0, displayLayout.mWidth, displayLayout.mHeight);
    }

    public final DisplayLayout getDisplayLayout() {
        return new DisplayLayout(this.mDisplayLayout);
    }

    public final Rect getInsetBounds() {
        Rect rect = new Rect();
        Rect rect2 = getDisplayLayout().mStableInsets;
        int i = rect2.left;
        Point point = this.mScreenEdgeInsets;
        rect.set(i + point.x, rect2.top + point.y, (getDisplayLayout().mWidth - rect2.right) - this.mScreenEdgeInsets.x, (getDisplayLayout().mHeight - rect2.bottom) - this.mScreenEdgeInsets.y);
        return rect;
    }

    public final void reloadResources() {
        Resources resources = this.mContext.getResources();
        String string = resources.getString(R.string.config_defaultPictureInPictureScreenEdgeInsets);
        this.mScreenEdgeInsets = (!string.isEmpty() ? Size.parseSize(string) : null) == null ? new Point() : new Point((int) TypedValue.applyDimension(1, r1.getWidth(), resources.getDisplayMetrics()), (int) TypedValue.applyDimension(1, r1.getHeight(), resources.getDisplayMetrics()));
    }

    public final void rotateTo(int i) {
        this.mDisplayLayout.rotateTo(i, this.mContext.getResources());
    }
}
