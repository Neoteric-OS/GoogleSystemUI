package com.android.systemui.controls.ui;

import android.app.PendingIntent;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.taskview.TaskView;
import com.android.wm.shell.taskview.TaskViewFactoryController;
import com.android.wm.shell.taskview.TaskViewFactoryController$TaskViewFactoryImpl$$ExternalSyntheticLambda0;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsUiControllerImpl$createPanelView$1 implements Runnable {
    public final /* synthetic */ Object $container;
    public final /* synthetic */ Object $pendingIntent;
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ ControlsUiControllerImpl this$0;

    public ControlsUiControllerImpl$createPanelView$1(ControlsUiControllerImpl controlsUiControllerImpl, PendingIntent pendingIntent, FrameLayout frameLayout) {
        this.this$0 = controlsUiControllerImpl;
        this.$pendingIntent = pendingIntent;
        this.$container = frameLayout;
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.FunctionReferenceImpl] */
    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                TaskViewFactoryController.TaskViewFactoryImpl taskViewFactoryImpl = (TaskViewFactoryController.TaskViewFactoryImpl) this.this$0.taskViewFactory.get();
                final ControlsUiControllerImpl controlsUiControllerImpl = this.this$0;
                Context context = controlsUiControllerImpl.activityContext;
                if (context == null) {
                    context = null;
                }
                final PendingIntent pendingIntent = (PendingIntent) this.$pendingIntent;
                final FrameLayout frameLayout = (FrameLayout) this.$container;
                Consumer consumer = new Consumer() { // from class: com.android.systemui.controls.ui.ControlsUiControllerImpl$createPanelView$1.1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.controls.ui.ControlsUiControllerImpl$createPanelView$1$1$1, reason: invalid class name and collision with other inner class name */
                    final /* synthetic */ class C00681 extends FunctionReferenceImpl implements Function0 {
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            ((Runnable) this.receiver).run();
                            return Unit.INSTANCE;
                        }
                    }

                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        TaskView taskView = (TaskView) obj;
                        ControlsUiControllerImpl controlsUiControllerImpl2 = ControlsUiControllerImpl.this;
                        Context context2 = controlsUiControllerImpl2.activityContext;
                        Context context3 = context2 == null ? null : context2;
                        PendingIntent pendingIntent2 = pendingIntent;
                        Intrinsics.checkNotNull(taskView);
                        Runnable runnable = ControlsUiControllerImpl.this.onDismiss;
                        C00681 c00681 = new C00681(0, runnable == null ? null : runnable, Runnable.class, "run", "run()V", 0);
                        DelayableExecutor delayableExecutor = controlsUiControllerImpl2.uiExecutor;
                        PanelTaskViewController panelTaskViewController = new PanelTaskViewController(context3, delayableExecutor, pendingIntent2, taskView, c00681);
                        frameLayout.addView(taskView);
                        taskView.setListener(delayableExecutor, panelTaskViewController.stateCallback);
                        controlsUiControllerImpl2.taskViewController = panelTaskViewController;
                    }
                };
                ((HandlerExecutor) TaskViewFactoryController.this.mShellExecutor).execute(new TaskViewFactoryController$TaskViewFactoryImpl$$ExternalSyntheticLambda0(taskViewFactoryImpl, context, controlsUiControllerImpl.uiExecutor, consumer));
                break;
            default:
                ViewGroup viewGroup = this.this$0.parent;
                if (viewGroup == null) {
                    viewGroup = null;
                }
                viewGroup.removeAllViews();
                if (((ArrayList) ((List) this.$pendingIntent)).size() > 0) {
                    ((FunctionReferenceImpl) this.$container).invoke((List) this.$pendingIntent);
                    break;
                }
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ControlsUiControllerImpl$createPanelView$1(ControlsUiControllerImpl controlsUiControllerImpl, List list, Function1 function1) {
        this.this$0 = controlsUiControllerImpl;
        this.$pendingIntent = list;
        this.$container = (FunctionReferenceImpl) function1;
    }
}
