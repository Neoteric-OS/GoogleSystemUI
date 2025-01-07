package com.android.systemui.controls.management;

import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Pair;
import com.android.systemui.controls.ControlsServiceInfo;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class ControlsProviderSelectorActivity$onStart$1 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ControlsServiceInfo controlsServiceInfo = (ControlsServiceInfo) obj;
        final ControlsProviderSelectorActivity controlsProviderSelectorActivity = (ControlsProviderSelectorActivity) this.receiver;
        SystemUIDialog systemUIDialog = controlsProviderSelectorActivity.dialog;
        if (systemUIDialog != null) {
            systemUIDialog.cancel();
        }
        if (controlsServiceInfo.panelActivity == null) {
            final ComponentName componentName = controlsServiceInfo.componentName;
            controlsProviderSelectorActivity.executor.execute(new Runnable() { // from class: com.android.systemui.controls.management.ControlsProviderSelectorActivity$launchFavoritingActivity$1
                @Override // java.lang.Runnable
                public final void run() {
                    ComponentName componentName2 = componentName;
                    if (componentName2 != null) {
                        ControlsProviderSelectorActivity controlsProviderSelectorActivity2 = controlsProviderSelectorActivity;
                        Intent intent = new Intent(controlsProviderSelectorActivity2.getApplicationContext(), (Class<?>) ControlsFavoritingActivity.class);
                        intent.putExtra("extra_app_label", ((ControlsListingControllerImpl) controlsProviderSelectorActivity2.listingController).getAppLabel(componentName2));
                        intent.putExtra("android.intent.extra.COMPONENT_NAME", componentName2);
                        intent.putExtra("extra_source", (byte) 1);
                        controlsProviderSelectorActivity2.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(controlsProviderSelectorActivity2, new Pair[0]).toBundle());
                    }
                }
            });
        } else {
            CharSequence loadLabel = controlsServiceInfo.loadLabel();
            final ControlsProviderSelectorActivity$onAppSelected$1 controlsProviderSelectorActivity$onAppSelected$1 = new ControlsProviderSelectorActivity$onAppSelected$1(controlsProviderSelectorActivity, controlsServiceInfo, loadLabel);
            PanelConfirmationDialogFactory panelConfirmationDialogFactory = controlsProviderSelectorActivity.panelConfirmationDialogFactory;
            panelConfirmationDialogFactory.getClass();
            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.android.systemui.controls.management.PanelConfirmationDialogFactory$createConfirmationDialog$listener$1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    ControlsProviderSelectorActivity$onAppSelected$1.this.accept(Boolean.valueOf(i == -1));
                }
            };
            SystemUIDialog.Factory factory = panelConfirmationDialogFactory.dialogFactory;
            factory.getClass();
            SystemUIDialog.AnonymousClass1 anonymousClass1 = new SystemUIDialog.AnonymousClass1();
            int i = SystemUIDialog.$r8$clinit;
            SystemUIDialog create = factory.create(anonymousClass1, controlsProviderSelectorActivity, R.style.Theme_SystemUI_Dialog);
            create.setTitle(create.getContext().getString(R.string.controls_panel_authorization_title, loadLabel));
            create.setMessage(create.getContext().getString(R.string.controls_panel_authorization, loadLabel));
            create.setCanceledOnTouchOutside(true);
            create.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.systemui.controls.management.PanelConfirmationDialogFactory$createConfirmationDialog$1$1
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    ControlsProviderSelectorActivity$onAppSelected$1.this.accept(Boolean.FALSE);
                }
            });
            create.setPositiveButton(R.string.controls_dialog_ok, onClickListener);
            create.setNeutralButton(R.string.cancel, onClickListener, true);
            create.show();
            controlsProviderSelectorActivity.dialog = create;
        }
        return Unit.INSTANCE;
    }
}
