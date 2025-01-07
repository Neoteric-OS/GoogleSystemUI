package com.android.systemui.controls.ui;

import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ResolveInfo;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.taskview.TaskView;
import com.android.wm.shell.taskview.TaskViewFactoryController;
import com.android.wm.shell.taskview.TaskViewFactoryController$TaskViewFactoryImpl$$ExternalSyntheticLambda0;
import java.util.List;
import java.util.function.Consumer;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlActionCoordinatorImpl$showDetail$1 implements Runnable {
    public final /* synthetic */ ControlViewHolder $cvh;
    public final /* synthetic */ PendingIntent $pendingIntent;
    public final /* synthetic */ ControlActionCoordinatorImpl this$0;

    public ControlActionCoordinatorImpl$showDetail$1(ControlActionCoordinatorImpl controlActionCoordinatorImpl, PendingIntent pendingIntent, ControlViewHolder controlViewHolder) {
        this.this$0 = controlActionCoordinatorImpl;
        this.$pendingIntent = pendingIntent;
        this.$cvh = controlViewHolder;
    }

    @Override // java.lang.Runnable
    public final void run() {
        final List<ResolveInfo> queryIntentActivities = this.this$0.context.getPackageManager().queryIntentActivities(this.$pendingIntent.getIntent(), 65536);
        final ControlActionCoordinatorImpl controlActionCoordinatorImpl = this.this$0;
        DelayableExecutor delayableExecutor = controlActionCoordinatorImpl.uiExecutor;
        final ControlViewHolder controlViewHolder = this.$cvh;
        final PendingIntent pendingIntent = this.$pendingIntent;
        ((ExecutorImpl) delayableExecutor).execute(new Runnable() { // from class: com.android.systemui.controls.ui.ControlActionCoordinatorImpl$showDetail$1.1
            @Override // java.lang.Runnable
            public final void run() {
                if (queryIntentActivities.isEmpty() || !controlActionCoordinatorImpl.taskViewFactory.isPresent()) {
                    controlViewHolder.setErrorStatus();
                    return;
                }
                TaskViewFactoryController.TaskViewFactoryImpl taskViewFactoryImpl = (TaskViewFactoryController.TaskViewFactoryImpl) controlActionCoordinatorImpl.taskViewFactory.get();
                final ControlActionCoordinatorImpl controlActionCoordinatorImpl2 = controlActionCoordinatorImpl;
                Context context = controlActionCoordinatorImpl2.context;
                final PendingIntent pendingIntent2 = pendingIntent;
                final ControlViewHolder controlViewHolder2 = controlViewHolder;
                Consumer consumer = new Consumer() { // from class: com.android.systemui.controls.ui.ControlActionCoordinatorImpl.showDetail.1.1.1
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        TaskView taskView = (TaskView) obj;
                        ControlActionCoordinatorImpl controlActionCoordinatorImpl3 = ControlActionCoordinatorImpl.this;
                        ControlActionCoordinatorImpl controlActionCoordinatorImpl4 = ControlActionCoordinatorImpl.this;
                        Context context2 = controlActionCoordinatorImpl4.activityContext;
                        if (context2 == null) {
                            context2 = null;
                        }
                        Intrinsics.checkNotNull(taskView);
                        PendingIntent pendingIntent3 = pendingIntent2;
                        ControlViewHolder controlViewHolder3 = controlViewHolder2;
                        ControlActionCoordinatorImpl controlActionCoordinatorImpl5 = ControlActionCoordinatorImpl.this;
                        DetailDialog detailDialog = new DetailDialog(context2, controlActionCoordinatorImpl4.broadcastSender, taskView, pendingIntent3, controlViewHolder3, controlActionCoordinatorImpl5.keyguardStateController, controlActionCoordinatorImpl5.activityStarter);
                        final ControlActionCoordinatorImpl controlActionCoordinatorImpl6 = ControlActionCoordinatorImpl.this;
                        detailDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.systemui.controls.ui.ControlActionCoordinatorImpl$showDetail$1$1$1$1$1
                            @Override // android.content.DialogInterface.OnDismissListener
                            public final void onDismiss(DialogInterface dialogInterface) {
                                ControlActionCoordinatorImpl.this.dialog = null;
                            }
                        });
                        detailDialog.show();
                        controlActionCoordinatorImpl3.dialog = detailDialog;
                    }
                };
                ((HandlerExecutor) TaskViewFactoryController.this.mShellExecutor).execute(new TaskViewFactoryController$TaskViewFactoryImpl$$ExternalSyntheticLambda0(taskViewFactoryImpl, context, controlActionCoordinatorImpl2.uiExecutor, consumer));
            }
        });
    }
}
