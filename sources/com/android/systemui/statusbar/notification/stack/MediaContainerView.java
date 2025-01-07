package com.android.systemui.statusbar.notification.stack;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MediaContainerView extends ExpandableView {
    public int clipHeight;
    public final Path clipPath;
    public final RectF clipRect;
    public float cornerRadius;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MediaContainerViewState extends ExpandableViewState {
        public boolean shouldBeVisible;

        @Override // com.android.systemui.statusbar.notification.stack.ExpandableViewState, com.android.systemui.statusbar.notification.stack.ViewState
        public final void copyFrom(ViewState viewState) {
            super.copyFrom(viewState);
            if (viewState instanceof MediaContainerViewState) {
                this.shouldBeVisible = ((MediaContainerViewState) viewState).shouldBeVisible;
            }
        }
    }

    public MediaContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.clipRect = new RectF();
        this.clipPath = new Path();
        setWillNotDraw(false);
        this.cornerRadius = getContext().getResources().getDimensionPixelSize(R.dimen.notification_corner_radius);
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final ExpandableViewState createExpandableViewState() {
        return new MediaContainerViewState();
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView, com.android.systemui.statusbar.notification.Roundable
    public final int getClipHeight() {
        return this.clipHeight;
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.cornerRadius = getContext().getResources().getDimensionPixelSize(R.dimen.notification_corner_radius);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect clipBounds = canvas.getClipBounds();
        clipBounds.bottom = this.clipHeight;
        this.clipRect.set(clipBounds);
        this.clipPath.reset();
        Path path = this.clipPath;
        RectF rectF = this.clipRect;
        float f = this.cornerRadius;
        path.addRoundRect(rectF, f, f, Path.Direction.CW);
        canvas.clipPath(this.clipPath);
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final long performRemoveAnimation(long j, float f, boolean z, Runnable runnable, Runnable runnable2, AnimatorListenerAdapter animatorListenerAdapter, ExpandableView.ClipSide clipSide) {
        return 0L;
    }

    @Override // android.view.View
    public final void setVisibility(int i) {
        ExpandableViewState expandableViewState = this.mViewState;
        MediaContainerViewState mediaContainerViewState = expandableViewState instanceof MediaContainerViewState ? (MediaContainerViewState) expandableViewState : null;
        if (mediaContainerViewState == null || (mediaContainerViewState.shouldBeVisible ? i != 8 : i == 8)) {
            super.setVisibility(i);
        }
        ExpandableViewState expandableViewState2 = this.mViewState;
        if ((expandableViewState2 instanceof MediaContainerViewState) && !((MediaContainerViewState) expandableViewState2).shouldBeVisible && i == 0) {
            Log.wtf("MediaContainerView", "MediaContainerView should be GONE but its visibility changed to VISIBLE");
        }
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final void updateClipping$1() {
        int i = this.clipHeight;
        int i2 = this.mActualHeight;
        if (i != i2) {
            this.clipHeight = i2;
        }
        invalidate();
    }

    @Override // com.android.systemui.statusbar.notification.row.ExpandableView
    public final void performAddAnimation(long j, long j2, boolean z) {
    }
}
