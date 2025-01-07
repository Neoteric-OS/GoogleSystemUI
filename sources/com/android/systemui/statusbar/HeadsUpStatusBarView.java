package com.android.systemui.statusbar;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.android.keyguard.AlphaOptimizedLinearLayout;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.phone.HeadsUpAppearanceController$$ExternalSyntheticLambda4;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class HeadsUpStatusBarView extends AlphaOptimizedLinearLayout {
    public final Rect mIconDrawingRect;
    public View mIconPlaceholder;
    public final Rect mLayoutedIconRect;
    public HeadsUpAppearanceController$$ExternalSyntheticLambda4 mOnDrawingRectChangedListener;
    public final HeadsUpStatusBarView$$ExternalSyntheticLambda0 mOnSensitivityChangedListener;
    public NotificationEntry mShowingEntry;
    public TextView mTextView;
    public final int[] mTmpPosition;

    public HeadsUpStatusBarView(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mIconPlaceholder = findViewById(R.id.icon_placeholder);
        this.mTextView = (TextView) findViewById(R.id.text);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        HeadsUpAppearanceController$$ExternalSyntheticLambda4 headsUpAppearanceController$$ExternalSyntheticLambda4;
        super.onLayout(z, i, i2, i3, i4);
        this.mIconPlaceholder.getLocationOnScreen(this.mTmpPosition);
        int[] iArr = this.mTmpPosition;
        int i5 = iArr[0];
        int i6 = iArr[1];
        this.mLayoutedIconRect.set(i5, i6, this.mIconPlaceholder.getWidth() + i5, this.mIconPlaceholder.getHeight() + i6);
        Rect rect = this.mIconDrawingRect;
        float f = rect.left;
        rect.set(this.mLayoutedIconRect);
        if (f == this.mIconDrawingRect.left || (headsUpAppearanceController$$ExternalSyntheticLambda4 = this.mOnDrawingRectChangedListener) == null) {
            return;
        }
        headsUpAppearanceController$$ExternalSyntheticLambda4.run();
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("heads_up_status_bar_view_super_parcelable"));
        if (bundle.containsKey("visibility")) {
            setVisibility(bundle.getInt("visibility"));
        }
        if (bundle.containsKey("alpha")) {
            setAlpha(bundle.getFloat("alpha"));
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("heads_up_status_bar_view_super_parcelable", super.onSaveInstanceState());
        bundle.putInt("visibility", getVisibility());
        bundle.putFloat("alpha", getAlpha());
        return bundle;
    }

    public final void setEntry(NotificationEntry notificationEntry) {
        NotificationEntry notificationEntry2 = this.mShowingEntry;
        if (notificationEntry2 != null) {
            notificationEntry2.mOnSensitivityChangedListeners.remove(this.mOnSensitivityChangedListener);
        }
        this.mShowingEntry = notificationEntry;
        if (notificationEntry != null) {
            CharSequence charSequence = (CharSequence) notificationEntry.mHeadsUpStatusBarText.getValue();
            if (((Boolean) notificationEntry.mSensitive.getValue()).booleanValue()) {
                charSequence = (CharSequence) notificationEntry.mHeadsUpStatusBarTextPublic.getValue();
            }
            this.mTextView.setText(charSequence);
            NotificationEntry notificationEntry3 = this.mShowingEntry;
            notificationEntry3.mOnSensitivityChangedListeners.addIfAbsent(this.mOnSensitivityChangedListener);
        }
    }

    public HeadsUpStatusBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HeadsUpStatusBarView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [com.android.systemui.statusbar.HeadsUpStatusBarView$$ExternalSyntheticLambda0] */
    public HeadsUpStatusBarView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mLayoutedIconRect = new Rect();
        this.mTmpPosition = new int[2];
        this.mIconDrawingRect = new Rect();
        this.mOnSensitivityChangedListener = new NotificationEntry.OnSensitivityChangedListener() { // from class: com.android.systemui.statusbar.HeadsUpStatusBarView$$ExternalSyntheticLambda0
            @Override // com.android.systemui.statusbar.notification.collection.NotificationEntry.OnSensitivityChangedListener
            public final void onSensitivityChanged(NotificationEntry notificationEntry) {
                HeadsUpStatusBarView headsUpStatusBarView = HeadsUpStatusBarView.this;
                if (notificationEntry == headsUpStatusBarView.mShowingEntry) {
                    headsUpStatusBarView.setEntry(notificationEntry);
                    return;
                }
                throw new IllegalStateException("Got a sensitivity change for " + notificationEntry + " but mShowingEntry is " + headsUpStatusBarView.mShowingEntry);
            }
        };
    }

    public HeadsUpStatusBarView(Context context, View view, TextView textView) {
        this(context);
        this.mIconPlaceholder = view;
        this.mTextView = textView;
    }
}
