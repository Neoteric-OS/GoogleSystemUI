package com.android.wm.shell.compatui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class RestartDialogLayout extends ConstraintLayout implements DialogContainerSupplier {
    public static final /* synthetic */ int $r8$clinit = 0;
    public Drawable mBackgroundDim;
    public View mDialogContainer;
    public TextView mDialogTitle;

    public RestartDialogLayout(Context context) {
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
        View findViewById = findViewById(R.id.letterbox_restart_dialog_checkbox_container);
        CheckBox checkBox = (CheckBox) findViewById(R.id.letterbox_restart_dialog_checkbox);
        this.mDialogContainer = findViewById(R.id.letterbox_restart_dialog_container);
        this.mDialogTitle = (TextView) findViewById(R.id.letterbox_restart_dialog_title);
        Drawable mutate = getBackground().mutate();
        this.mBackgroundDim = mutate;
        mutate.setAlpha(0);
        this.mDialogContainer.setOnClickListener(new RestartDialogLayout$$ExternalSyntheticLambda0());
        findViewById.setOnClickListener(new RestartDialogLayout$$ExternalSyntheticLambda1(0, checkBox));
    }

    public final void setDismissOnClickListener(Runnable runnable) {
        RestartDialogLayout$$ExternalSyntheticLambda1 restartDialogLayout$$ExternalSyntheticLambda1;
        if (runnable == null) {
            restartDialogLayout$$ExternalSyntheticLambda1 = null;
        } else {
            restartDialogLayout$$ExternalSyntheticLambda1 = new RestartDialogLayout$$ExternalSyntheticLambda1(1, (RestartDialogWindowManager$$ExternalSyntheticLambda0) runnable);
        }
        findViewById(R.id.letterbox_restart_dialog_dismiss_button).setOnClickListener(restartDialogLayout$$ExternalSyntheticLambda1);
    }

    public RestartDialogLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RestartDialogLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public RestartDialogLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
