package com.android.systemui.biometrics.ui.binder;

import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import com.android.systemui.biometrics.AuthContainerView;
import com.android.systemui.biometrics.AuthPanelController;
import com.android.systemui.biometrics.ui.CredentialPasswordView;
import com.android.systemui.biometrics.ui.CredentialPatternView;
import com.android.systemui.biometrics.ui.binder.Spaghetti;
import com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.wm.shell.R;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CredentialViewBinder {
    public static void bind$default(ViewGroup viewGroup, AuthContainerView authContainerView, CredentialViewModel credentialViewModel, AuthPanelController authPanelController, boolean z, Spaghetti.Callback callback) {
        final AuthContainerView authContainerView2;
        TextView textView = (TextView) viewGroup.requireViewById(R.id.title);
        TextView textView2 = (TextView) viewGroup.requireViewById(R.id.subtitle);
        TextView textView3 = (TextView) viewGroup.requireViewById(R.id.description);
        LinearLayout linearLayout = (LinearLayout) viewGroup.requireViewById(R.id.customized_view_container);
        ImageView imageView = (ImageView) viewGroup.findViewById(R.id.icon);
        TextView textView4 = (TextView) viewGroup.requireViewById(R.id.error);
        Button button = (Button) viewGroup.findViewById(R.id.cancel_button);
        RepeatWhenAttachedKt.repeatWhenAttached(viewGroup, EmptyCoroutineContext.INSTANCE, new CredentialViewBinder$bind$1(z, authPanelController, credentialViewModel, textView, viewGroup, textView2, textView3, linearLayout, callback, imageView, (Button) viewGroup.requireViewById(R.id.emergencyCallButton), new Ref$ObjectRef(), 3000L, textView4, button, authContainerView, null));
        if (button != null) {
            authContainerView2 = authContainerView;
            button.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.biometrics.ui.binder.CredentialViewBinder$bind$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AuthContainerView authContainerView3 = AuthContainerView.this;
                    authContainerView3.sendEarlyUserCanceled();
                    authContainerView3.animateAway(1, true);
                }
            });
        } else {
            authContainerView2 = authContainerView;
        }
        if (!(viewGroup instanceof CredentialPasswordView)) {
            if (!(viewGroup instanceof CredentialPatternView)) {
                throw new IllegalStateException("unexpected view type: ".concat(viewGroup.getClass().getName()));
            }
            CredentialPatternView credentialPatternView = (CredentialPatternView) viewGroup;
            RepeatWhenAttachedKt.repeatWhenAttached(credentialPatternView, EmptyCoroutineContext.INSTANCE, new CredentialPatternViewBinder$bind$1(credentialViewModel, credentialPatternView.requireViewById(R.id.lockPattern), authContainerView2, null));
            return;
        }
        CredentialPasswordView credentialPasswordView = (CredentialPasswordView) viewGroup;
        Object systemService = credentialPasswordView.getContext().getSystemService((Class<Object>) InputMethodManager.class);
        Intrinsics.checkNotNull(systemService);
        RepeatWhenAttachedKt.repeatWhenAttached(credentialPasswordView, EmptyCoroutineContext.INSTANCE, new CredentialPasswordViewBinder$bind$1(credentialViewModel, credentialPasswordView.requireViewById(R.id.lockPassword), true, new OnBackInvokedCallback() { // from class: com.android.systemui.biometrics.ui.binder.CredentialPasswordViewBinder$bind$onBackInvokedCallback$1
            @Override // android.window.OnBackInvokedCallback
            public final void onBackInvoked() {
                AuthContainerView authContainerView3 = AuthContainerView.this;
                authContainerView3.sendEarlyUserCanceled();
                authContainerView3.animateAway(1, true);
            }
        }, credentialPasswordView, (InputMethodManager) systemService, authContainerView, null));
    }
}
