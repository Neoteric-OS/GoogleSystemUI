package com.android.keyguard;

import android.R;
import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.settingslib.Utils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class PinShapeHintingView extends LinearLayout implements PinShapeInput {
    public int mColor;
    public final int mDotDiameter;
    public final int mPinLength;
    public final PinShapeAdapter mPinShapeAdapter;
    public int mPosition;

    public PinShapeHintingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mColor = Utils.getColorAttr(R.^attr-private.materialColorOnSurfaceVariant, getContext()).getDefaultColor();
        this.mPosition = 0;
        this.mPinShapeAdapter = new PinShapeAdapter(context);
        this.mPinLength = 6;
        this.mDotDiameter = context.getResources().getDimensionPixelSize(com.android.wm.shell.R.dimen.password_shape_size);
        for (int i = 0; i < this.mPinLength; i++) {
            ImageView imageView = new ImageView(context, attributeSet);
            int i2 = this.mDotDiameter;
            imageView.setLayoutParams(new LinearLayout.LayoutParams(i2, i2));
            imageView.setImageResource(com.android.wm.shell.R.drawable.pin_dot_avd);
            if (imageView.getDrawable() != null) {
                imageView.getDrawable().setTint(this.mColor);
            }
            addView(imageView);
        }
    }

    @Override // com.android.keyguard.PinShapeInput
    public final void append() {
        int i = this.mPosition;
        if (i == 6) {
            return;
        }
        setAnimatedDrawable(i, this.mPinShapeAdapter.getShape(i));
        this.mPosition++;
    }

    @Override // com.android.keyguard.PinShapeInput
    public final void delete() {
        int i = this.mPosition;
        if (i == 0) {
            return;
        }
        int i2 = i - 1;
        this.mPosition = i2;
        setAnimatedDrawable(i2, com.android.wm.shell.R.drawable.pin_dot_delete_avd);
    }

    @Override // com.android.keyguard.PinShapeInput
    public final void reset() {
        int i = this.mPosition;
        for (int i2 = 0; i2 < i; i2++) {
            delete();
        }
        this.mPosition = 0;
    }

    public final void setAnimatedDrawable(int i, int i2) {
        ImageView imageView = (ImageView) getChildAt(i);
        imageView.setImageResource(i2);
        if (imageView.getDrawable() != null) {
            imageView.getDrawable().setTint(this.mColor);
        }
        if (imageView.getDrawable() instanceof AnimatedVectorDrawable) {
            ((AnimatedVectorDrawable) imageView.getDrawable()).start();
        }
    }

    @Override // com.android.keyguard.PinShapeInput
    public final void setDrawColor(int i) {
        this.mColor = i;
    }

    @Override // com.android.keyguard.PinShapeInput
    public final View getView() {
        return this;
    }
}
