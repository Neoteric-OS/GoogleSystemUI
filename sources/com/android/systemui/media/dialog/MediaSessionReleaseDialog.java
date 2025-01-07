package com.android.systemui.media.dialog;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaSessionReleaseDialog extends SystemUIDialog {
    public final ColorFilter mButtonColorFilter;
    public final Context mContext;
    public View mDialogView;
    public final int mIconColor;
    public final MediaSessionReleaseDialog$$ExternalSyntheticLambda0 mPositiveButtonListener;

    /* JADX WARN: Type inference failed for: r3v2, types: [com.android.systemui.media.dialog.MediaSessionReleaseDialog$$ExternalSyntheticLambda0] */
    public MediaSessionReleaseDialog(Context context, final MediaOutputAdapter$MediaDeviceViewHolder$$ExternalSyntheticLambda0 mediaOutputAdapter$MediaDeviceViewHolder$$ExternalSyntheticLambda0, int i, int i2) {
        super(context, R.style.Theme_SystemUI_Dialog_Media, true);
        this.mContext = getContext();
        this.mPositiveButtonListener = new View.OnClickListener() { // from class: com.android.systemui.media.dialog.MediaSessionReleaseDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MediaSessionReleaseDialog mediaSessionReleaseDialog = MediaSessionReleaseDialog.this;
                MediaOutputAdapter$MediaDeviceViewHolder$$ExternalSyntheticLambda0 mediaOutputAdapter$MediaDeviceViewHolder$$ExternalSyntheticLambda02 = mediaOutputAdapter$MediaDeviceViewHolder$$ExternalSyntheticLambda0;
                mediaSessionReleaseDialog.getClass();
                mediaOutputAdapter$MediaDeviceViewHolder$$ExternalSyntheticLambda02.run();
                mediaSessionReleaseDialog.dismiss();
            }
        };
        this.mButtonColorFilter = new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN);
        this.mIconColor = i2;
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog, android.app.AlertDialog, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDialogView = LayoutInflater.from(this.mContext).inflate(R.layout.media_session_end_dialog, (ViewGroup) null);
        Window window = getWindow();
        window.setType(2017);
        window.setContentView(this.mDialogView);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = (int) (this.mContext.getResources().getDisplayMetrics().widthPixels * 0.9d);
        ImageView imageView = (ImageView) this.mDialogView.requireViewById(R.id.end_icon);
        imageView.setImageDrawable(this.mContext.getDrawable(R.drawable.media_output_status_failed));
        imageView.setImageTintList(ColorStateList.valueOf(this.mIconColor));
        Button button = (Button) this.mDialogView.requireViewById(R.id.stop_button);
        button.setOnClickListener(this.mPositiveButtonListener);
        button.getBackground().setColorFilter(this.mButtonColorFilter);
        Button button2 = (Button) this.mDialogView.requireViewById(R.id.cancel_button);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.media.dialog.MediaSessionReleaseDialog$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MediaSessionReleaseDialog.this.dismiss();
            }
        });
        button2.getBackground().setColorFilter(this.mButtonColorFilter);
    }
}
