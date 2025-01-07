package com.android.wm.shell.compatui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class LetterboxEduDialogLayout extends ConstraintLayout implements DialogContainerSupplier {
    public static final /* synthetic */ int $r8$clinit = 0;
    public Drawable mBackgroundDim;
    public View mDialogContainer;
    public TextView mDialogTitle;

    public LetterboxEduDialogLayout(Context context) {
        this(context, null);
    }

    @Override // com.android.wm.shell.compatui.DialogContainerSupplier
    public final Drawable getBackgroundDimDrawable() {
        return this.mBackgroundDim;
    }

    @Override // com.android.wm.shell.compatui.DialogContainerSupplier
    public final View getDialogContainerView() {
        return this.mDialogContainer;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mDialogContainer = findViewById(R.id.letterbox_education_dialog_container);
        this.mDialogTitle = (TextView) findViewById(R.id.letterbox_education_dialog_title);
        Drawable mutate = getBackground().mutate();
        this.mBackgroundDim = mutate;
        mutate.setAlpha(0);
    }

    public final void setDismissOnClickListener(Runnable runnable) {
        View.OnClickListener onClickListener;
        if (runnable == null) {
            onClickListener = null;
        } else {
            final LetterboxEduWindowManager$$ExternalSyntheticLambda0 letterboxEduWindowManager$$ExternalSyntheticLambda0 = (LetterboxEduWindowManager$$ExternalSyntheticLambda0) runnable;
            onClickListener = new View.OnClickListener() { // from class: com.android.wm.shell.compatui.LetterboxEduDialogLayout$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LetterboxEduWindowManager$$ExternalSyntheticLambda0 letterboxEduWindowManager$$ExternalSyntheticLambda02 = LetterboxEduWindowManager$$ExternalSyntheticLambda0.this;
                    int i = LetterboxEduDialogLayout.$r8$clinit;
                    letterboxEduWindowManager$$ExternalSyntheticLambda02.run();
                }
            };
        }
        findViewById(R.id.letterbox_education_dialog_dismiss_button).setOnClickListener(onClickListener);
        setOnClickListener(onClickListener);
        this.mDialogContainer.setOnClickListener(runnable != null ? new LetterboxEduDialogLayout$$ExternalSyntheticLambda1() : null);
    }

    public LetterboxEduDialogLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LetterboxEduDialogLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public LetterboxEduDialogLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
