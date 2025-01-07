package com.android.systemui.controls.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.service.controls.actions.BooleanAction;
import android.service.controls.actions.CommandAction;
import android.service.controls.actions.ControlAction;
import android.service.controls.actions.FloatAction;
import android.service.controls.actions.ModeAction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import com.android.wm.shell.R;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ChallengeDialogs {
    public static final ChallengeDialogs INSTANCE = null;

    public static final ControlAction access$addChallengeValue(ControlAction controlAction, String str) {
        String templateId = controlAction.getTemplateId();
        if (controlAction instanceof BooleanAction) {
            return new BooleanAction(templateId, ((BooleanAction) controlAction).getNewState(), str);
        }
        if (controlAction instanceof FloatAction) {
            return new FloatAction(templateId, ((FloatAction) controlAction).getNewValue(), str);
        }
        if (controlAction instanceof CommandAction) {
            return new CommandAction(templateId, str);
        }
        if (controlAction instanceof ModeAction) {
            return new ModeAction(templateId, ((ModeAction) controlAction).getNewMode(), str);
        }
        throw new IllegalStateException("'action' is not a known type: " + controlAction);
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [android.app.AlertDialog, com.android.systemui.controls.ui.ChallengeDialogs$createPinDialog$1] */
    public static ChallengeDialogs$createPinDialog$1 createPinDialog(ControlViewHolder controlViewHolder, final boolean z, boolean z2, Function0 function0) {
        int i = 0;
        ControlAction controlAction = controlViewHolder.lastAction;
        if (controlAction == null) {
            Log.e("ControlsUiController", "PIN Dialog attempted but no last action is set. Will not show");
            return null;
        }
        Resources resources = controlViewHolder.context.getResources();
        Pair pair = z2 ? new Pair(resources.getString(R.string.controls_pin_wrong), Integer.valueOf(R.string.controls_pin_instructions_retry)) : new Pair(resources.getString(R.string.controls_pin_verify, controlViewHolder.title.getText()), Integer.valueOf(R.string.controls_pin_instructions));
        String str = (String) pair.component1();
        final int intValue = ((Number) pair.component2()).intValue();
        final Context context = controlViewHolder.context;
        final ?? r5 = new AlertDialog(context) { // from class: com.android.systemui.controls.ui.ChallengeDialogs$createPinDialog$1
            @Override // android.app.Dialog, android.content.DialogInterface
            public final void dismiss() {
                View decorView;
                InputMethodManager inputMethodManager;
                Window window = getWindow();
                if (window != null && (decorView = window.getDecorView()) != null && (inputMethodManager = (InputMethodManager) decorView.getContext().getSystemService(InputMethodManager.class)) != null) {
                    inputMethodManager.hideSoftInputFromWindow(decorView.getWindowToken(), 0);
                }
                super.dismiss();
            }
        };
        r5.setTitle(str);
        r5.setView(LayoutInflater.from(r5.getContext()).inflate(R.layout.controls_dialog_pin, (ViewGroup) null));
        r5.setButton(-1, r5.getContext().getText(android.R.string.ok), new ChallengeDialogs$createPinDialog$2$1(controlViewHolder, controlAction, i));
        r5.setButton(-2, r5.getContext().getText(android.R.string.cancel), new ChallengeDialogs$createPinDialog$2$2(0, function0));
        Window window = r5.getWindow();
        if (window != null) {
            window.setType(2020);
        }
        Window window2 = r5.getWindow();
        if (window2 != null) {
            window2.setSoftInputMode(4);
        }
        r5.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.systemui.controls.ui.ChallengeDialogs$createPinDialog$2$3
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                final EditText editText = (EditText) requireViewById(R.id.controls_pin_input);
                editText.setHint(intValue);
                final CheckBox checkBox = (CheckBox) requireViewById(R.id.controls_pin_use_alpha);
                checkBox.setChecked(z);
                if (checkBox.isChecked()) {
                    editText.setInputType(129);
                } else {
                    editText.setInputType(18);
                }
                ((CheckBox) requireViewById(R.id.controls_pin_use_alpha)).setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.controls.ui.ChallengeDialogs$createPinDialog$2$3.1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        EditText editText2 = editText;
                        if (checkBox.isChecked()) {
                            editText2.setInputType(129);
                        } else {
                            editText2.setInputType(18);
                        }
                    }
                });
                editText.requestFocus();
            }
        });
        return r5;
    }
}
