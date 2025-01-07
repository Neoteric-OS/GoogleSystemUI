package com.android.systemui.statusbar.phone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Insets;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import com.android.systemui.Dependency;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.model.SysUiState;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.util.DialogKt;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class SystemUIDialog extends AlertDialog implements ViewRootImpl.ConfigChangedCallback {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Context mContext;
    public final DialogDelegate mDelegate;
    public final SystemUIDialogManager mDialogManager;
    public final DismissReceiver mDismissReceiver;
    public final Handler mHandler;
    public int mLastConfigurationHeightDp;
    public int mLastConfigurationWidthDp;
    public int mLastHeight;
    public int mLastWidth;
    public final List mOnCreateRunnables;
    public final SysUiState mSysUiState;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.SystemUIDialog$1, reason: invalid class name */
    public final class AnonymousClass1 implements DialogDelegate {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Delegate extends DialogDelegate {
        SystemUIDialog createDialog();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DismissReceiver extends BroadcastReceiver {
        public static final IntentFilter INTENT_FILTER;
        public final BroadcastDispatcher mBroadcastDispatcher;
        public final Dialog mDialog;
        public final DialogTransitionAnimator mDialogTransitionAnimator;
        public boolean mRegistered;

        static {
            IntentFilter intentFilter = new IntentFilter();
            INTENT_FILTER = intentFilter;
            intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
        }

        public DismissReceiver(Dialog dialog, BroadcastDispatcher broadcastDispatcher, DialogTransitionAnimator dialogTransitionAnimator) {
            this.mDialog = dialog;
            this.mBroadcastDispatcher = broadcastDispatcher;
            this.mDialogTransitionAnimator = dialogTransitionAnimator;
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            this.mDialogTransitionAnimator.disableAllCurrentDialogsExitAnimations();
            this.mDialog.dismiss();
        }
    }

    public SystemUIDialog(Context context) {
        this(context, R.style.Theme_SystemUI_Dialog, true);
    }

    public static void applyFlags(AlertDialog alertDialog, boolean z) {
        Window window = alertDialog.getWindow();
        window.setType(2017);
        window.addFlags(131072);
        if (z) {
            window.addFlags(524288);
        }
        window.getAttributes().setFitInsetsTypes(window.getAttributes().getFitInsetsTypes() & (~WindowInsets.Type.statusBars()));
    }

    public static int calculateDialogWidthWithInsets(Dialog dialog, int i) {
        return Math.round(TypedValue.applyDimension(1, i, dialog.getContext().getResources().getDisplayMetrics()) + getHorizontalInsets(dialog));
    }

    public static int getDefaultDialogWidth(Dialog dialog) {
        Context context = dialog.getContext();
        int i = SystemProperties.getInt("persist.systemui.flag_tablet_dialog_width", 0);
        if (i == -1) {
            return calculateDialogWidthWithInsets(dialog, 624);
        }
        if (i == -2) {
            return calculateDialogWidthWithInsets(dialog, 348);
        }
        if (i > 0) {
            return calculateDialogWidthWithInsets(dialog, i);
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.large_dialog_width);
        return dimensionPixelSize > 0 ? dimensionPixelSize + getHorizontalInsets(dialog) : dimensionPixelSize;
    }

    public static int getHorizontalInsets(Dialog dialog) {
        View decorView = dialog.getWindow().getDecorView();
        if (decorView == null) {
            return 0;
        }
        View findViewByPredicate = decorView.findViewByPredicate(new SystemUIDialog$$ExternalSyntheticLambda3());
        Drawable background = findViewByPredicate != null ? findViewByPredicate.getBackground() : decorView.getBackground();
        Insets opticalInsets = background != null ? background.getOpticalInsets() : Insets.NONE;
        return opticalInsets.left + opticalInsets.right;
    }

    public static void registerDismissListener(Dialog dialog, final Runnable runnable) {
        final DismissReceiver dismissReceiver = new DismissReceiver(dialog, (BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class), (DialogTransitionAnimator) Dependency.sDependency.getDependencyInner(DialogTransitionAnimator.class));
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.systemui.statusbar.phone.SystemUIDialog$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                SystemUIDialog.DismissReceiver dismissReceiver2 = SystemUIDialog.DismissReceiver.this;
                Runnable runnable2 = runnable;
                if (dismissReceiver2.mRegistered) {
                    dismissReceiver2.mBroadcastDispatcher.unregisterReceiver(dismissReceiver2);
                    dismissReceiver2.mRegistered = false;
                }
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        });
        dismissReceiver.mBroadcastDispatcher.registerReceiver(dismissReceiver, DismissReceiver.INTENT_FILTER, null, UserHandle.CURRENT);
        dismissReceiver.mRegistered = true;
    }

    public static void setDialogSize(Dialog dialog) {
        dialog.create();
        dialog.getWindow().setLayout(getDefaultDialogWidth(dialog), -2);
    }

    public static void setShowForAllUsers(Dialog dialog) {
        dialog.getWindow().getAttributes().privateFlags |= 16;
    }

    public static void setWindowOnTop(Dialog dialog, boolean z) {
        Window window = dialog.getWindow();
        window.setType(2017);
        if (z) {
            window.getAttributes().setFitInsetsTypes(window.getAttributes().getFitInsetsTypes() & (~WindowInsets.Type.statusBars()));
        }
    }

    public int getHeight() {
        return this.mDelegate.getHeight(this);
    }

    public int getWidth() {
        return this.mDelegate.getWidth(this);
    }

    public final void onConfigurationChanged(Configuration configuration) {
        int i = this.mLastConfigurationWidthDp;
        int i2 = configuration.screenWidthDp;
        if (i != i2 || this.mLastConfigurationHeightDp != configuration.screenHeightDp) {
            this.mLastConfigurationWidthDp = i2;
            this.mLastConfigurationHeightDp = configuration.compatScreenWidthDp;
            updateWindowSize();
        }
        this.mDelegate.onConfigurationChanged(this, configuration);
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        this.mDelegate.beforeCreate(this);
        super.onCreate(bundle);
        this.mDelegate.onCreate(this, bundle);
        Configuration configuration = getContext().getResources().getConfiguration();
        this.mLastConfigurationWidthDp = configuration.screenWidthDp;
        this.mLastConfigurationHeightDp = configuration.screenHeightDp;
        updateWindowSize();
        for (int i = 0; i < ((ArrayList) this.mOnCreateRunnables).size(); i++) {
            ((Runnable) ((ArrayList) this.mOnCreateRunnables).get(i)).run();
        }
        View decorView = getWindow().getDecorView();
        DialogKt.registerAnimationOnBackInvoked(this, decorView, this.mDelegate.getBackAnimationSpec(new SystemUIDialog$$ExternalSyntheticLambda1(decorView)));
    }

    @Override // android.app.Dialog
    public final void onStart() {
        super.onStart();
        DismissReceiver dismissReceiver = this.mDismissReceiver;
        if (dismissReceiver != null) {
            dismissReceiver.mBroadcastDispatcher.registerReceiver(dismissReceiver, DismissReceiver.INTENT_FILTER, null, UserHandle.CURRENT);
            dismissReceiver.mRegistered = true;
        }
        ViewRootImpl.addConfigCallback(this);
        this.mDialogManager.setShowing(this, true);
        SysUiState sysUiState = this.mSysUiState;
        sysUiState.setFlag(32768L, true);
        sysUiState.commitUpdate(this.mContext.getDisplayId());
        this.mDelegate.onStart(this);
        start();
    }

    @Override // android.app.Dialog
    public final void onStop() {
        super.onStop();
        DismissReceiver dismissReceiver = this.mDismissReceiver;
        if (dismissReceiver != null && dismissReceiver.mRegistered) {
            dismissReceiver.mBroadcastDispatcher.unregisterReceiver(dismissReceiver);
            dismissReceiver.mRegistered = false;
        }
        ViewRootImpl.removeConfigCallback(this);
        this.mDialogManager.setShowing(this, false);
        SysUiState sysUiState = this.mSysUiState;
        sysUiState.setFlag(32768L, false);
        sysUiState.commitUpdate(this.mContext.getDisplayId());
        this.mDelegate.onStop(this);
        stop();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.mDelegate.onWindowFocusChanged(this, z);
    }

    public final void setButton(final int i, int i2, final DialogInterface.OnClickListener onClickListener, boolean z) {
        if (z) {
            setButton(i, this.mContext.getString(i2), onClickListener);
        } else {
            setButton(i, this.mContext.getString(i2), (DialogInterface.OnClickListener) null);
            this.mOnCreateRunnables.add(new Runnable() { // from class: com.android.systemui.statusbar.phone.SystemUIDialog$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    final SystemUIDialog systemUIDialog = this;
                    final int i3 = i;
                    final DialogInterface.OnClickListener onClickListener2 = onClickListener;
                    systemUIDialog.getButton(i3).setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.statusbar.phone.SystemUIDialog$$ExternalSyntheticLambda5
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            SystemUIDialog systemUIDialog2 = systemUIDialog;
                            DialogInterface.OnClickListener onClickListener3 = onClickListener2;
                            int i4 = i3;
                            systemUIDialog2.getClass();
                            onClickListener3.onClick(systemUIDialog2, i4);
                        }
                    });
                }
            });
        }
    }

    public final void setMessage(int i) {
        setMessage(this.mContext.getString(i));
    }

    public void setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
        setNegativeButton$1(i, onClickListener);
    }

    public final void setNegativeButton$1(int i, DialogInterface.OnClickListener onClickListener) {
        setButton(-2, i, onClickListener, true);
    }

    public final void setNeutralButton(int i, DialogInterface.OnClickListener onClickListener, boolean z) {
        setButton(-3, i, onClickListener, z);
    }

    public void setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
        setButton(-1, i, onClickListener, true);
    }

    public final void updateWindowSize() {
        if (Looper.myLooper() != this.mHandler.getLooper()) {
            this.mHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.phone.SystemUIDialog$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SystemUIDialog.this.updateWindowSize();
                }
            });
            return;
        }
        int width = getWidth();
        int height = getHeight();
        if (width == this.mLastWidth && height == this.mLastHeight) {
            return;
        }
        this.mLastWidth = width;
        this.mLastHeight = height;
        getWindow().setLayout(width, height);
    }

    public SystemUIDialog(Context context, int i, boolean z, SystemUIDialogManager systemUIDialogManager, SysUiState sysUiState, BroadcastDispatcher broadcastDispatcher, DialogTransitionAnimator dialogTransitionAnimator, DialogDelegate dialogDelegate) {
        super(context, i);
        this.mHandler = new Handler();
        this.mLastWidth = Integer.MIN_VALUE;
        this.mLastHeight = Integer.MIN_VALUE;
        this.mLastConfigurationWidthDp = -1;
        this.mLastConfigurationHeightDp = -1;
        this.mOnCreateRunnables = new ArrayList();
        this.mContext = context;
        this.mDelegate = dialogDelegate;
        applyFlags(this, true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.setTitle(getClass().getSimpleName());
        getWindow().setAttributes(attributes);
        this.mDismissReceiver = z ? new DismissReceiver(this, broadcastDispatcher, dialogTransitionAnimator) : null;
        this.mDialogManager = systemUIDialogManager;
        this.mSysUiState = sysUiState;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final BroadcastDispatcher mBroadcastDispatcher;
        public final Context mContext;
        public final DialogTransitionAnimator mDialogTransitionAnimator;
        public final SysUiState mSysUiState;
        public final SystemUIDialogManager mSystemUIDialogManager;

        public Factory(Context context, SystemUIDialogManager systemUIDialogManager, SysUiState sysUiState, BroadcastDispatcher broadcastDispatcher, DialogTransitionAnimator dialogTransitionAnimator) {
            this.mContext = context;
            this.mSystemUIDialogManager = systemUIDialogManager;
            this.mSysUiState = sysUiState;
            this.mBroadcastDispatcher = broadcastDispatcher;
            this.mDialogTransitionAnimator = dialogTransitionAnimator;
        }

        public final SystemUIDialog create() {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            Context context = this.mContext;
            int i = SystemUIDialog.$r8$clinit;
            return create(anonymousClass1, context, R.style.Theme_SystemUI_Dialog);
        }

        public final SystemUIDialog create(Delegate delegate, Context context) {
            int i = SystemUIDialog.$r8$clinit;
            return create(delegate, context, R.style.Theme_SystemUI_Dialog);
        }

        public final SystemUIDialog create(DialogDelegate dialogDelegate, Context context, int i) {
            return new SystemUIDialog(context, i, true, this.mSystemUIDialogManager, this.mSysUiState, this.mBroadcastDispatcher, this.mDialogTransitionAnimator, dialogDelegate);
        }
    }

    public SystemUIDialog(Context context, int i, boolean z) {
        this(context, i, z, (SystemUIDialogManager) Dependency.sDependency.getDependencyInner(SystemUIDialogManager.class), (SysUiState) Dependency.sDependency.getDependencyInner(SysUiState.class), (BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class), (DialogTransitionAnimator) Dependency.sDependency.getDependencyInner(DialogTransitionAnimator.class), new AnonymousClass1());
    }

    public void start() {
    }

    public void stop() {
    }
}
