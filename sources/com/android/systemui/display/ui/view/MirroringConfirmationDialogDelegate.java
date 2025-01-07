package com.android.systemui.display.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.widget.TextView;
import com.android.systemui.statusbar.phone.DialogDelegate;
import com.android.systemui.statusbar.phone.SystemUIBottomSheetDialog;
import com.android.wm.shell.R;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MirroringConfirmationDialogDelegate implements DialogDelegate {
    public View bottomSheet;
    public final int defaultDialogBottomInset;
    public boolean enabledPressed;
    public final MirroringConfirmationDialogDelegate$insetsAnimationCallback$1 insetsAnimationCallback = new WindowInsetsAnimation.Callback() { // from class: com.android.systemui.display.ui.view.MirroringConfirmationDialogDelegate$insetsAnimationCallback$1
        public WindowInsets lastInsets;

        {
            super(0);
        }

        @Override // android.view.WindowInsetsAnimation.Callback
        public final void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
            WindowInsets windowInsets = this.lastInsets;
            if (windowInsets != null) {
                int typeMask = windowInsetsAnimation.getTypeMask();
                int navigationBars = WindowInsets.Type.navigationBars();
                if ((typeMask & navigationBars) != 0) {
                    MirroringConfirmationDialogDelegate mirroringConfirmationDialogDelegate = MirroringConfirmationDialogDelegate.this;
                    int i = windowInsets.getInsets(navigationBars).bottom;
                    View view = mirroringConfirmationDialogDelegate.bottomSheet;
                    if (view == null) {
                        view = null;
                    }
                    view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), Math.max(i, mirroringConfirmationDialogDelegate.defaultDialogBottomInset));
                }
            }
        }

        @Override // android.view.WindowInsetsAnimation.Callback
        public final WindowInsets onProgress(WindowInsets windowInsets, List list) {
            this.lastInsets = windowInsets;
            Iterator it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                i |= ((WindowInsetsAnimation) it.next()).getTypeMask();
            }
            int navigationBars = WindowInsets.Type.navigationBars();
            if ((i & navigationBars) != 0) {
                MirroringConfirmationDialogDelegate mirroringConfirmationDialogDelegate = MirroringConfirmationDialogDelegate.this;
                int i2 = windowInsets.getInsets(navigationBars).bottom;
                View view = mirroringConfirmationDialogDelegate.bottomSheet;
                if (view == null) {
                    view = null;
                }
                view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), Math.max(i2, mirroringConfirmationDialogDelegate.defaultDialogBottomInset));
            }
            return windowInsets;
        }
    };
    public final Function0 navbarBottomInsetsProvider;
    public final View.OnClickListener onCancelMirroring;
    public final View.OnClickListener onStartMirroringClickListener;
    public final boolean showConcurrentDisplayInfo;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final Context context;
        public final SystemUIBottomSheetDialog.Factory dialogFactory;

        public Factory(Context context, SystemUIBottomSheetDialog.Factory factory) {
            this.context = context;
            this.dialogFactory = factory;
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.display.ui.view.MirroringConfirmationDialogDelegate$insetsAnimationCallback$1] */
    public MirroringConfirmationDialogDelegate(Context context, boolean z, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, Function0 function0) {
        this.showConcurrentDisplayInfo = z;
        this.onStartMirroringClickListener = onClickListener;
        this.onCancelMirroring = onClickListener2;
        this.navbarBottomInsetsProvider = function0;
        this.defaultDialogBottomInset = context.getResources().getDimensionPixelSize(R.dimen.dialog_bottom_padding);
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onConfigurationChanged(Dialog dialog, Configuration configuration) {
        int intValue = ((Number) this.navbarBottomInsetsProvider.invoke()).intValue();
        View view = this.bottomSheet;
        if (view == null) {
            view = null;
        }
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), Math.max(intValue, this.defaultDialogBottomInset));
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onCreate(Dialog dialog, Bundle bundle) {
        dialog.setContentView(R.layout.connected_display_dialog);
        ((TextView) dialog.requireViewById(R.id.enable_display)).setOnClickListener(this.onStartMirroringClickListener);
        this.enabledPressed = true;
        ((TextView) dialog.requireViewById(R.id.cancel)).setOnClickListener(this.onCancelMirroring);
        ((TextView) dialog.requireViewById(R.id.dual_display_warning)).setVisibility(this.showConcurrentDisplayInfo ? 0 : 8);
        this.bottomSheet = dialog.requireViewById(R.id.cd_bottom_sheet);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.systemui.display.ui.view.MirroringConfirmationDialogDelegate$onCreate$4
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                MirroringConfirmationDialogDelegate mirroringConfirmationDialogDelegate = MirroringConfirmationDialogDelegate.this;
                if (mirroringConfirmationDialogDelegate.enabledPressed) {
                    return;
                }
                mirroringConfirmationDialogDelegate.onCancelMirroring.onClick(null);
            }
        });
        int intValue = ((Number) this.navbarBottomInsetsProvider.invoke()).intValue();
        View view = this.bottomSheet;
        if (view == null) {
            view = null;
        }
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), Math.max(intValue, this.defaultDialogBottomInset));
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onStart(Dialog dialog) {
        View decorView;
        Window window = dialog.getWindow();
        if (window == null || (decorView = window.getDecorView()) == null) {
            return;
        }
        decorView.setWindowInsetsAnimationCallback(this.insetsAnimationCallback);
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onStop(Dialog dialog) {
        View decorView;
        Window window = dialog.getWindow();
        if (window == null || (decorView = window.getDecorView()) == null) {
            return;
        }
        decorView.setWindowInsetsAnimationCallback(null);
    }
}
