package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.activity.ComponentDialog;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.policy.ConfigurationController;
import dagger.Lazy;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemUIBottomSheetDialog extends ComponentDialog {
    public final ConfigurationController configurationController;
    public final CoroutineScope coroutineScope;
    public final DialogDelegate delegate;
    public StandaloneCoroutine job;
    public final WindowLayout windowLayout;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final ConfigurationController configurationController;
        public final Context context;
        public final CoroutineScope coroutineScope;
        public final Lazy defaultWindowLayout;

        public Factory(Context context, CoroutineScope coroutineScope, Lazy lazy, ConfigurationController configurationController) {
            this.context = context;
            this.coroutineScope = coroutineScope;
            this.defaultWindowLayout = lazy;
            this.configurationController = configurationController;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface WindowLayout {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Layout {
            public final int width;

            public Layout(int i) {
                this.width = i;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj instanceof Layout) {
                    return this.width == ((Layout) obj).width;
                }
                return false;
            }

            public final int hashCode() {
                return Integer.hashCode(-2) + (Integer.hashCode(this.width) * 31);
            }

            public final String toString() {
                return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("Layout(width="), this.width, ", height=-2)");
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class LimitedEdgeToEdge implements WindowLayout {
            public final ConfigurationController configurationController;
            public final Context context;

            public LimitedEdgeToEdge(Context context, ConfigurationController configurationController) {
                this.context = context;
                this.configurationController = configurationController;
            }
        }
    }

    public SystemUIBottomSheetDialog(Context context, CoroutineScope coroutineScope, ConfigurationController configurationController, DialogDelegate dialogDelegate, WindowLayout windowLayout, int i) {
        super(i, context);
        this.coroutineScope = coroutineScope;
        this.configurationController = configurationController;
        this.delegate = dialogDelegate;
        this.windowLayout = windowLayout;
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public final void onCreate(Bundle bundle) {
        this.delegate.beforeCreate(this);
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.setType(2017);
            window.addPrivateFlags(80);
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.setGravity(80);
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.setFitInsetsSides(0);
            attributes.horizontalMargin = 0.0f;
            window.setAttributes(attributes);
        }
        setCanceledOnTouchOutside(true);
        this.delegate.onCreate(this, bundle);
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public final void onStart() {
        super.onStart();
        StandaloneCoroutine standaloneCoroutine = this.job;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        this.job = BuildersKt.launch$default(this.coroutineScope, null, null, new SystemUIBottomSheetDialog$onStart$1(this, null), 3);
        this.delegate.onStart(this);
    }

    @Override // androidx.activity.ComponentDialog, android.app.Dialog
    public final void onStop() {
        StandaloneCoroutine standaloneCoroutine = this.job;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        this.delegate.onStop(this);
        super.onStop();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.delegate.onWindowFocusChanged(this, z);
    }
}
