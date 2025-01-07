package com.android.systemui.qs.tiles;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.accessibility.fontscaling.FontScalingDialogDelegate;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.animation.Expandable;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import kotlin.Unit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FontScalingTile extends QSTileImpl {
    public final DialogTransitionAnimator dialogTransitionAnimator;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider fontScalingDialogDelegateProvider;
    public final QSTile.Icon icon;
    public final KeyguardStateController keyguardStateController;
    public final Handler mainHandler;

    public FontScalingTile(QSHost qSHost, QsEventLoggerImpl qsEventLoggerImpl, Looper looper, Handler handler, FalsingManager falsingManager, MetricsLogger metricsLogger, StatusBarStateController statusBarStateController, ActivityStarter activityStarter, QSLogger qSLogger, KeyguardStateController keyguardStateController, DialogTransitionAnimator dialogTransitionAnimator, DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        super(qSHost, qsEventLoggerImpl, looper, handler, falsingManager, metricsLogger, statusBarStateController, activityStarter, qSLogger);
        this.mainHandler = handler;
        this.keyguardStateController = keyguardStateController;
        this.dialogTransitionAnimator = dialogTransitionAnimator;
        this.fontScalingDialogDelegateProvider = switchingProvider;
        this.icon = QSTileImpl.ResourceIcon.get(R.drawable.ic_qs_font_scaling);
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final Intent getLongClickIntent() {
        return new Intent("android.settings.TEXT_READING_SETTINGS");
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl, com.android.systemui.plugins.qs.QSTile
    public final CharSequence getTileLabel() {
        return this.mContext.getString(R.string.quick_settings_font_scaling_label);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.systemui.qs.tiles.FontScalingTile$handleClick$runnable$1] */
    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleClick(final Expandable expandable) {
        final boolean z = (expandable == null || ((KeyguardStateControllerImpl) this.keyguardStateController).mShowing) ? false : true;
        final ?? r1 = new Runnable() { // from class: com.android.systemui.qs.tiles.FontScalingTile$handleClick$runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                SystemUIDialog createDialog = ((FontScalingDialogDelegate) FontScalingTile.this.fontScalingDialogDelegateProvider.get()).createDialog();
                if (!z) {
                    createDialog.show();
                    return;
                }
                Expandable expandable2 = expandable;
                Unit unit = null;
                DialogTransitionAnimator.Controller m = expandable2 != null ? BluetoothTileDialogViewModel$showDialog$1$$ExternalSyntheticOutline0.m(58, "font_scaling", expandable2) : null;
                if (m != null) {
                    DialogTransitionAnimator dialogTransitionAnimator = FontScalingTile.this.dialogTransitionAnimator;
                    TransitionAnimator.Timings timings = DialogTransitionAnimator.TIMINGS;
                    dialogTransitionAnimator.show(createDialog, m, false);
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    createDialog.show();
                }
            }
        };
        this.mainHandler.post(new Runnable() { // from class: com.android.systemui.qs.tiles.FontScalingTile$handleClick$1
            @Override // java.lang.Runnable
            public final void run() {
                FontScalingTile.this.mActivityStarter.executeRunnableDismissingKeyguard(r1, null, true, true, false);
            }
        });
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final void handleUpdateState(QSTile.State state, Object obj) {
        if (state != null) {
            state.label = this.mContext.getString(R.string.quick_settings_font_scaling_label);
        }
        if (state != null) {
            state.icon = this.icon;
        }
        if (state == null) {
            return;
        }
        state.contentDescription = state.label;
    }

    @Override // com.android.systemui.qs.tileimpl.QSTileImpl
    public final QSTile.State newTileState() {
        return new QSTile.State();
    }
}
