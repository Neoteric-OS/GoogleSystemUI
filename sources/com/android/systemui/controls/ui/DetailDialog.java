package com.android.systemui.controls.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Insets;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.ImageView;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.wm.shell.R;
import com.android.wm.shell.taskview.TaskView;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DetailDialog extends Dialog {
    public final Context activityContext;
    public final ActivityStarter activityStarter;
    public final BroadcastSender broadcastSender;
    public final Intent fillInIntent;
    public final KeyguardStateController keyguardStateController;
    public final PendingIntent pendingIntent;
    public final DetailDialog$4$1$action$1 stateCallback;
    public final TaskView taskView;
    public final View taskViewContainer;
    public final float taskWidthPercentWidth;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.controls.ui.DetailDialog$5, reason: invalid class name */
    public final class AnonymousClass5 implements View.OnApplyWindowInsetsListener {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        @Override // android.view.View.OnApplyWindowInsetsListener
        public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            int paddingLeft = view.getPaddingLeft();
            int paddingRight = view.getPaddingRight();
            Insets insets = windowInsets.getInsets(WindowInsets.Type.systemBars());
            view.setPadding(paddingLeft, insets.top, paddingRight, insets.bottom);
            return WindowInsets.CONSUMED;
        }
    }

    public DetailDialog(Context context, BroadcastSender broadcastSender, TaskView taskView, PendingIntent pendingIntent, ControlViewHolder controlViewHolder, KeyguardStateController keyguardStateController, ActivityStarter activityStarter) {
        super(context, R.style.Theme_SystemUI_Dialog_Control_DetailPanel);
        this.activityContext = context;
        this.broadcastSender = broadcastSender;
        this.taskView = taskView;
        this.pendingIntent = pendingIntent;
        this.keyguardStateController = keyguardStateController;
        this.activityStarter = activityStarter;
        this.taskWidthPercentWidth = context.getResources().getFloat(R.dimen.controls_task_view_width_percentage);
        Intent intent = new Intent();
        final int i = 1;
        intent.putExtra("controls.DISPLAY_IN_PANEL", true);
        intent.addFlags(524288);
        intent.addFlags(134217728);
        this.fillInIntent = intent;
        DetailDialog$4$1$action$1 detailDialog$4$1$action$1 = new DetailDialog$4$1$action$1(this);
        Window window = getWindow();
        if (window != null) {
            window.addFlags(32);
        }
        Window window2 = getWindow();
        if (window2 != null) {
            window2.addPrivateFlags(536870912);
        }
        setContentView(R.layout.controls_detail_dialog);
        this.taskViewContainer = requireViewById(R.id.control_task_view_container);
        final int i2 = 0;
        requireViewById(R.id.control_detail_root).setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.controls.ui.DetailDialog$1$1
            public final /* synthetic */ DetailDialog this$0;

            {
                this.this$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i2) {
                    case 0:
                        this.this$0.dismiss();
                        break;
                    case 1:
                        this.this$0.dismiss();
                        break;
                    default:
                        this.this$0.dismiss();
                        DetailDialog detailDialog = this.this$0;
                        DetailDialog$4$1$action$1 detailDialog$4$1$action$12 = new DetailDialog$4$1$action$1(detailDialog);
                        if (!detailDialog.keyguardStateController.isUnlocked()) {
                            this.this$0.activityStarter.dismissKeyguardThenExecute(detailDialog$4$1$action$12, null, true);
                            break;
                        } else {
                            detailDialog$4$1$action$12.onDismiss();
                            break;
                        }
                }
            }
        });
        ViewGroup viewGroup = (ViewGroup) requireViewById(R.id.controls_activity_view);
        viewGroup.addView(taskView);
        viewGroup.setAlpha(0.0f);
        ((ImageView) requireViewById(R.id.control_detail_close)).setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.controls.ui.DetailDialog$1$1
            public final /* synthetic */ DetailDialog this$0;

            {
                this.this$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i) {
                    case 0:
                        this.this$0.dismiss();
                        break;
                    case 1:
                        this.this$0.dismiss();
                        break;
                    default:
                        this.this$0.dismiss();
                        DetailDialog detailDialog = this.this$0;
                        DetailDialog$4$1$action$1 detailDialog$4$1$action$12 = new DetailDialog$4$1$action$1(detailDialog);
                        if (!detailDialog.keyguardStateController.isUnlocked()) {
                            this.this$0.activityStarter.dismissKeyguardThenExecute(detailDialog$4$1$action$12, null, true);
                            break;
                        } else {
                            detailDialog$4$1$action$12.onDismiss();
                            break;
                        }
                }
            }
        });
        final int i3 = 2;
        ((ImageView) requireViewById(R.id.control_detail_open_in_app)).setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.controls.ui.DetailDialog$1$1
            public final /* synthetic */ DetailDialog this$0;

            {
                this.this$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i3) {
                    case 0:
                        this.this$0.dismiss();
                        break;
                    case 1:
                        this.this$0.dismiss();
                        break;
                    default:
                        this.this$0.dismiss();
                        DetailDialog detailDialog = this.this$0;
                        DetailDialog$4$1$action$1 detailDialog$4$1$action$12 = new DetailDialog$4$1$action$1(detailDialog);
                        if (!detailDialog.keyguardStateController.isUnlocked()) {
                            this.this$0.activityStarter.dismissKeyguardThenExecute(detailDialog$4$1$action$12, null, true);
                            break;
                        } else {
                            detailDialog$4$1$action$12.onDismiss();
                            break;
                        }
                }
            }
        });
        Window window3 = getWindow();
        if (window3 == null) {
            throw new IllegalStateException("Required value was null.");
        }
        window3.getDecorView().setOnApplyWindowInsetsListener(AnonymousClass5.INSTANCE);
        if (ScreenDecorationsUtils.supportsRoundedCornersOnWindows(getContext().getResources())) {
            taskView.setCornerRadius(getContext().getResources().getDimensionPixelSize(R.dimen.controls_activity_view_corner_radius));
        }
        taskView.setListener(controlViewHolder.uiExecutor, detailDialog$4$1$action$1);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public final void dismiss() {
        if (isShowing()) {
            this.taskView.removeTask();
            Context context = this.activityContext;
            Boolean bool = null;
            Activity activity = context instanceof Activity ? (Activity) context : null;
            if (activity != null) {
                bool = Boolean.valueOf(activity.isFinishing() || activity.isDestroyed());
            }
            if (Intrinsics.areEqual(bool, Boolean.TRUE)) {
                return;
            }
            super.dismiss();
        }
    }

    public static /* synthetic */ void getStateCallback$annotations() {
    }
}
