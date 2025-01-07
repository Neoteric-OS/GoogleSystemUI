package com.google.android.systemui.columbus.legacy;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusTargetRequestDialog extends SystemUIDialog {
    public TextView mContent;
    public Button mNegativeButton;
    public Button mPositiveButton;
    public TextView mTitle;

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog, android.app.AlertDialog, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.columbus_target_request_dialog);
        this.mTitle = (TextView) requireViewById(R.id.title);
        this.mContent = (TextView) requireViewById(R.id.content);
        this.mPositiveButton = (Button) requireViewById(R.id.positive_button);
        this.mNegativeButton = (Button) requireViewById(R.id.negative_button);
    }

    @Override // android.app.AlertDialog
    public final void setMessage(CharSequence charSequence) {
        this.mContent.setText(charSequence);
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog
    public final void setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
        this.mNegativeButton.setText(i);
        this.mNegativeButton.setOnClickListener(new ColumbusTargetRequestDialog$$ExternalSyntheticLambda0(this, onClickListener, 0));
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog
    public final void setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
        this.mPositiveButton.setText(i);
        this.mPositiveButton.setOnClickListener(new ColumbusTargetRequestDialog$$ExternalSyntheticLambda0(this, onClickListener, 1));
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        getWindow().setTitle(charSequence);
        getWindow().getAttributes().setTitle(charSequence);
        this.mTitle.setText(charSequence);
    }
}
