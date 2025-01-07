package com.android.systemui.mediaprojection.permission;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.android.systemui.mediaprojection.MediaProjectionMetricsLogger;
import com.android.systemui.statusbar.phone.DialogDelegate;
import com.android.wm.shell.R;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.collections.CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BaseMediaProjectionPermissionDialogDelegate implements DialogDelegate, AdapterView.OnItemSelectedListener {
    public final String appName;
    public TextView cancelButton;
    public final int defaultSelectedMode;
    public AlertDialog dialog;
    public final Integer dialogIconDrawable;
    public final Integer dialogIconTint;
    public TextView dialogTitle;
    public final int hostUid;
    public final MediaProjectionMetricsLogger mediaProjectionMetricsLogger;
    public Spinner screenShareModeSpinner;
    public final List screenShareOptions;
    public ScreenShareOption selectedScreenShareOption;
    public boolean shouldLogCancel;
    public TextView startButton;
    public TextView warning;

    public BaseMediaProjectionPermissionDialogDelegate(List list, String str, int i, MediaProjectionMetricsLogger mediaProjectionMetricsLogger, Integer num, Integer num2, int i2) {
        this.screenShareOptions = list;
        this.appName = str;
        this.hostUid = i;
        this.mediaProjectionMetricsLogger = mediaProjectionMetricsLogger;
        this.dialogIconDrawable = num;
        this.dialogIconTint = num2;
        this.defaultSelectedMode = i2;
        this.shouldLogCancel = true;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ScreenShareOption screenShareOption = (ScreenShareOption) it.next();
            if (screenShareOption.mode == this.defaultSelectedMode) {
                this.selectedScreenShareOption = screenShareOption;
                return;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public Integer getOptionsViewLayoutId() {
        return null;
    }

    public void onCreate(AlertDialog alertDialog) {
        this.dialog = alertDialog;
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.addPrivateFlags(16);
        }
        Window window2 = alertDialog.getWindow();
        if (window2 != null) {
            window2.setGravity(17);
        }
        alertDialog.setContentView(R.layout.screen_share_dialog);
        this.dialogTitle = (TextView) alertDialog.requireViewById(R.id.screen_share_dialog_title);
        this.warning = (TextView) alertDialog.requireViewById(R.id.text_warning);
        this.startButton = (TextView) alertDialog.requireViewById(android.R.id.button1);
        this.cancelButton = (TextView) alertDialog.requireViewById(android.R.id.button2);
        AlertDialog alertDialog2 = this.dialog;
        if (alertDialog2 == null) {
            alertDialog2 = null;
        }
        ImageView imageView = (ImageView) alertDialog2.requireViewById(R.id.screen_share_dialog_icon);
        if (this.dialogIconTint != null) {
            AlertDialog alertDialog3 = this.dialog;
            if (alertDialog3 == null) {
                alertDialog3 = null;
            }
            imageView.setColorFilter(alertDialog3.getContext().getColor(this.dialogIconTint.intValue()));
        }
        if (this.dialogIconDrawable != null) {
            AlertDialog alertDialog4 = this.dialog;
            if (alertDialog4 == null) {
                alertDialog4 = null;
            }
            imageView.setImageDrawable(alertDialog4.getContext().getDrawable(this.dialogIconDrawable.intValue()));
        }
        for (ScreenShareOption screenShareOption : this.screenShareOptions) {
            if (screenShareOption.mode == this.defaultSelectedMode) {
                this.selectedScreenShareOption = screenShareOption;
                setOptionSpecificFields();
                AlertDialog alertDialog5 = this.dialog;
                if (alertDialog5 == null) {
                    alertDialog5 = null;
                }
                OptionsAdapter optionsAdapter = new OptionsAdapter(alertDialog5.getContext().getApplicationContext(), this.screenShareOptions);
                AlertDialog alertDialog6 = this.dialog;
                if (alertDialog6 == null) {
                    alertDialog6 = null;
                }
                Spinner spinner = (Spinner) alertDialog6.requireViewById(R.id.screen_share_mode_options);
                this.screenShareModeSpinner = spinner;
                spinner.setAdapter((SpinnerAdapter) optionsAdapter);
                Spinner spinner2 = this.screenShareModeSpinner;
                if (spinner2 == null) {
                    spinner2 = null;
                }
                spinner2.setOnItemSelectedListener(this);
                Spinner spinner3 = this.screenShareModeSpinner;
                if (spinner3 == null) {
                    spinner3 = null;
                }
                spinner3.setAccessibilityDelegate(new BaseMediaProjectionPermissionDialogDelegate$initScreenShareSpinner$1());
                Spinner spinner4 = this.screenShareModeSpinner;
                if (spinner4 == null) {
                    spinner4 = null;
                }
                spinner4.setLongClickable(false);
                Iterator it = this.screenShareOptions.iterator();
                int i = 0;
                while (true) {
                    if (!it.hasNext()) {
                        i = -1;
                        break;
                    } else if (((ScreenShareOption) it.next()).mode == this.defaultSelectedMode) {
                        break;
                    } else {
                        i++;
                    }
                }
                Spinner spinner5 = this.screenShareModeSpinner;
                if (spinner5 == null) {
                    spinner5 = null;
                }
                spinner5.setSelection(i, false);
                Integer optionsViewLayoutId = getOptionsViewLayoutId();
                if (optionsViewLayoutId == null) {
                    return;
                }
                AlertDialog alertDialog7 = this.dialog;
                ViewStub viewStub = (ViewStub) (alertDialog7 != null ? alertDialog7 : null).requireViewById(R.id.options_stub);
                viewStub.setLayoutResource(optionsViewLayoutId.intValue());
                viewStub.inflate();
                return;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        this.selectedScreenShareOption = (ScreenShareOption) this.screenShareOptions.get(i);
        setOptionSpecificFields();
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onStop(Dialog dialog) {
        if (this.shouldLogCancel) {
            MediaProjectionMetricsLogger mediaProjectionMetricsLogger = this.mediaProjectionMetricsLogger;
            int i = this.hostUid;
            mediaProjectionMetricsLogger.getClass();
            try {
                mediaProjectionMetricsLogger.service.notifyPermissionRequestCancelled(i);
            } catch (RemoteException e) {
                Log.e("MediaProjectionMetricsLogger", "Error notifying server of projection cancelled", e);
            }
            this.shouldLogCancel = false;
        }
    }

    public final void setDialogTitle(int i) {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog == null) {
            alertDialog = null;
        }
        String string = alertDialog.getContext().getString(i, this.appName);
        TextView textView = this.dialogTitle;
        (textView != null ? textView : null).setText(string);
    }

    public final void setOptionSpecificFields() {
        TextView textView = this.warning;
        if (textView == null) {
            textView = null;
        }
        AlertDialog alertDialog = this.dialog;
        if (alertDialog == null) {
            alertDialog = null;
        }
        textView.setText(alertDialog.getContext().getString(this.selectedScreenShareOption.warningText, this.appName));
        TextView textView2 = this.startButton;
        if (textView2 == null) {
            textView2 = null;
        }
        AlertDialog alertDialog2 = this.dialog;
        textView2.setText((alertDialog2 != null ? alertDialog2 : null).getContext().getString(this.selectedScreenShareOption.startButtonText));
    }

    public BaseMediaProjectionPermissionDialogDelegate(List list, String str, int i, MediaProjectionMetricsLogger mediaProjectionMetricsLogger, Integer num) {
        this(list, str, i, mediaProjectionMetricsLogger, num, null, ((ScreenShareOption) CollectionsKt.first(list)).mode);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView adapterView) {
    }
}
