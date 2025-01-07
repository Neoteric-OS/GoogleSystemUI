package com.android.systemui.controls.management;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.service.controls.Control;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.compose.runtime.collection.MutableVectorKt$$ExternalSyntheticOutline0;
import com.android.systemui.controls.controller.ControlInfo;
import com.android.systemui.controls.controller.ControlsController;
import com.android.systemui.controls.controller.ControlsControllerImpl;
import com.android.systemui.controls.controller.ControlsControllerImpl$addFavorite$1;
import com.android.systemui.controls.controller.Favorites;
import com.android.systemui.controls.controller.StructureInfo;
import com.android.systemui.controls.management.ControlsListingController;
import com.android.systemui.controls.ui.RenderInfo;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.wm.shell.R;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ControlsRequestDialog extends ComponentActivity implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener {
    public Control control;
    public ComponentName controlComponent;
    public final ControlsController controller;
    public final ControlsListingController controlsListingController;
    public Dialog dialog;
    public final Executor mainExecutor;
    public final UserTracker userTracker;
    public final ControlsRequestDialog$callback$1 callback = new ControlsRequestDialog$callback$1();
    public final ControlsRequestDialog$userTrackerCallback$1 userTrackerCallback = new UserTracker.Callback() { // from class: com.android.systemui.controls.management.ControlsRequestDialog$userTrackerCallback$1
        public final int startingUser;

        {
            this.startingUser = ((ControlsControllerImpl) ControlsRequestDialog.this.controller).currentUser.getIdentifier();
        }

        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            if (i != this.startingUser) {
                ControlsRequestDialog controlsRequestDialog = ControlsRequestDialog.this;
                ((UserTrackerImpl) controlsRequestDialog.userTracker).removeCallback(this);
                controlsRequestDialog.finish();
            }
        }
    };

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.controls.management.ControlsRequestDialog$userTrackerCallback$1] */
    public ControlsRequestDialog(Executor executor, ControlsController controlsController, UserTracker userTracker, ControlsListingController controlsListingController) {
        this.mainExecutor = executor;
        this.controller = controlsController;
        this.userTracker = userTracker;
        this.controlsListingController = controlsListingController;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            ControlsController controlsController = this.controller;
            ComponentName componentName = this.controlComponent;
            if (componentName == null) {
                componentName = null;
            }
            Control control = this.control;
            if (control == null) {
                control = null;
            }
            CharSequence structure = control.getStructure();
            if (structure == null) {
                structure = "";
            }
            Control control2 = this.control;
            if (control2 == null) {
                control2 = null;
            }
            String controlId = control2.getControlId();
            Control control3 = this.control;
            if (control3 == null) {
                control3 = null;
            }
            CharSequence title = control3.getTitle();
            Control control4 = this.control;
            if (control4 == null) {
                control4 = null;
            }
            CharSequence subtitle = control4.getSubtitle();
            Control control5 = this.control;
            ControlInfo controlInfo = new ControlInfo(controlId, title, subtitle, (control5 != null ? control5 : null).getDeviceType());
            ControlsControllerImpl controlsControllerImpl = (ControlsControllerImpl) controlsController;
            if (controlsControllerImpl.confirmAvailability()) {
                ((ExecutorImpl) controlsControllerImpl.executor).execute(new ControlsControllerImpl$addFavorite$1(componentName, structure, controlInfo, controlsControllerImpl));
            }
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((UserTrackerImpl) this.userTracker).addCallback(this.userTrackerCallback, this.mainExecutor);
        ControlsListingController controlsListingController = this.controlsListingController;
        ControlsRequestDialog$callback$1 controlsRequestDialog$callback$1 = this.callback;
        ControlsListingControllerImpl controlsListingControllerImpl = (ControlsListingControllerImpl) controlsListingController;
        controlsListingControllerImpl.getClass();
        controlsListingControllerImpl.addCallback((ControlsListingController.ControlsListingCallback) controlsRequestDialog$callback$1);
        int intExtra = getIntent().getIntExtra("android.intent.extra.USER_ID", -10000);
        int identifier = ((ControlsControllerImpl) this.controller).currentUser.getIdentifier();
        if (intExtra != identifier) {
            Log.w("ControlsRequestDialog", MutableVectorKt$$ExternalSyntheticOutline0.m(identifier, intExtra, "Current user (", ") different from request user (", ")"));
            finish();
        }
        ComponentName componentName = (ComponentName) getIntent().getParcelableExtra("android.intent.extra.COMPONENT_NAME");
        if (componentName == null) {
            Log.e("ControlsRequestDialog", "Request did not contain componentName");
            finish();
            return;
        }
        this.controlComponent = componentName;
        Control control = (Control) getIntent().getParcelableExtra("android.service.controls.extra.CONTROL");
        if (control != null) {
            this.control = control;
        } else {
            Log.e("ControlsRequestDialog", "Request did not contain control");
            finish();
        }
    }

    @Override // android.app.Activity
    public final void onDestroy() {
        Dialog dialog = this.dialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        ((UserTrackerImpl) this.userTracker).removeCallback(this.userTrackerCallback);
        ((ControlsListingControllerImpl) this.controlsListingController).removeCallback(this.callback);
        super.onDestroy();
    }

    @Override // android.app.Activity
    public final void onResume() {
        super.onResume();
        ControlsListingController controlsListingController = this.controlsListingController;
        ComponentName componentName = this.controlComponent;
        if (componentName == null) {
            componentName = null;
        }
        CharSequence appLabel = ((ControlsListingControllerImpl) controlsListingController).getAppLabel(componentName);
        if (appLabel == null) {
            ComponentName componentName2 = this.controlComponent;
            Log.e("ControlsRequestDialog", "The component specified (" + (componentName2 != null ? componentName2 : null).flattenToString() + " is not a valid ControlsProviderService");
            finish();
            return;
        }
        ControlsController controlsController = this.controller;
        ComponentName componentName3 = this.controlComponent;
        if (componentName3 == null) {
            componentName3 = null;
        }
        ((ControlsControllerImpl) controlsController).getClass();
        List structuresForComponent = Favorites.getStructuresForComponent(componentName3);
        if (structuresForComponent == null || !structuresForComponent.isEmpty()) {
            Iterator it = structuresForComponent.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                List list = ((StructureInfo) it.next()).controls;
                if (list == null || !list.isEmpty()) {
                    Iterator it2 = list.iterator();
                    while (it2.hasNext()) {
                        String str = ((ControlInfo) it2.next()).controlId;
                        Control control = this.control;
                        if (control == null) {
                            control = null;
                        }
                        if (Intrinsics.areEqual(str, control.getControlId())) {
                            Control control2 = this.control;
                            if (control2 == null) {
                                control2 = null;
                            }
                            Log.w("ControlsRequestDialog", "The control " + ((Object) control2.getTitle()) + " is already a favorite");
                            finish();
                        }
                    }
                }
            }
        }
        RenderInfo.Companion companion = RenderInfo.Companion;
        ComponentName componentName4 = this.controlComponent;
        if (componentName4 == null) {
            componentName4 = null;
        }
        Control control3 = this.control;
        if (control3 == null) {
            control3 = null;
        }
        RenderInfo lookup = RenderInfo.Companion.lookup(this, componentName4, control3.getDeviceType(), 0);
        View inflate = LayoutInflater.from(this).inflate(R.layout.controls_dialog, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.requireViewById(R.id.icon);
        imageView.setImageDrawable(lookup.icon);
        imageView.setImageTintList(imageView.getContext().getResources().getColorStateList(lookup.foreground, imageView.getContext().getTheme()));
        TextView textView = (TextView) inflate.requireViewById(R.id.title);
        Control control4 = this.control;
        if (control4 == null) {
            control4 = null;
        }
        textView.setText(control4.getTitle());
        TextView textView2 = (TextView) inflate.requireViewById(R.id.subtitle);
        Control control5 = this.control;
        if (control5 == null) {
            control5 = null;
        }
        textView2.setText(control5.getSubtitle());
        inflate.requireViewById(R.id.control).setElevation(inflate.getResources().getFloat(R.dimen.control_card_elevation));
        AlertDialog create = new AlertDialog.Builder(this).setTitle(getString(R.string.controls_dialog_title)).setMessage(getString(R.string.controls_dialog_message, new Object[]{appLabel})).setPositiveButton(R.string.controls_dialog_ok, this).setNegativeButton(android.R.string.cancel, this).setOnCancelListener(this).setView(inflate).create();
        SystemUIDialog.registerDismissListener(create, null);
        create.setCanceledOnTouchOutside(true);
        this.dialog = create;
        create.show();
    }
}
