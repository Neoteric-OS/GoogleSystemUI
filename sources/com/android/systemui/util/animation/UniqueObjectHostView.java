package com.android.systemui.util.animation;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.media.controls.ui.view.MediaHost$init$2;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UniqueObjectHostView extends FrameLayout {
    public MediaHost$init$2 measurementManager;

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view == null) {
            throw new IllegalArgumentException("child must be non-null");
        }
        if (view.getMeasuredWidth() != 0 && getMeasuredWidth() != 0) {
            Object tag = view.getTag(R.id.requires_remeasuring);
            if (!(tag != null ? tag.equals(Boolean.TRUE) : false)) {
                invalidate();
                addViewInLayout(view, i, layoutParams, true);
                view.resolveRtlPropertiesIfNeeded();
                int paddingLeft = getPaddingLeft();
                int paddingTop = getPaddingTop();
                view.layout(paddingLeft, paddingTop, (getMeasuredWidth() + paddingLeft) - (getPaddingEnd() + getPaddingStart()), (getMeasuredHeight() + paddingTop) - (getPaddingBottom() + getPaddingTop()));
                return;
            }
        }
        super.addView(view, i, layoutParams);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int paddingEnd = getPaddingEnd() + getPaddingStart();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        MeasurementInput measurementInput = new MeasurementInput(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i) - paddingEnd, View.MeasureSpec.getMode(i)), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2) - paddingBottom, View.MeasureSpec.getMode(i2)));
        MediaHost$init$2 mediaHost$init$2 = this.measurementManager;
        if (mediaHost$init$2 == null) {
            mediaHost$init$2 = null;
        }
        mediaHost$init$2.getClass();
        if (View.MeasureSpec.getMode(measurementInput.widthMeasureSpec) == Integer.MIN_VALUE) {
            measurementInput.widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(measurementInput.widthMeasureSpec), 1073741824);
        }
        MediaHost mediaHost = mediaHost$init$2.this$0;
        mediaHost.state.setMeasurementInput(measurementInput);
        MeasurementOutput updateCarouselDimensions = mediaHost.mediaHostStatesManager.updateCarouselDimensions(mediaHost$init$2.$location, mediaHost.state);
        int i3 = updateCarouselDimensions.measuredWidth;
        int i4 = updateCarouselDimensions.measuredHeight;
        if (getChildCount() != 0) {
            super.onMeasure(i, i2);
            View childAt = getChildAt(0);
            if (childAt != null) {
                childAt.setTag(R.id.requires_remeasuring, Boolean.FALSE);
            }
        }
        setMeasuredDimension(i3 + paddingEnd, i4 + paddingBottom);
    }
}
