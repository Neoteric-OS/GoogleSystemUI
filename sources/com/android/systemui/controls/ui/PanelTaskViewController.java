package com.android.systemui.controls.ui;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Trace;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.wm.shell.R;
import com.android.wm.shell.taskview.TaskView;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PanelTaskViewController {
    public final Context activityContext;
    public final Intent fillInIntent;
    public final Function0 hide;
    public final PendingIntent pendingIntent;
    public final PanelTaskViewController$stateCallback$1 stateCallback;
    public final TaskView taskView;

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.controls.ui.PanelTaskViewController$stateCallback$1] */
    public PanelTaskViewController(Context context, Executor executor, PendingIntent pendingIntent, TaskView taskView, Function0 function0) {
        this.activityContext = context;
        this.pendingIntent = pendingIntent;
        this.taskView = taskView;
        this.hide = function0;
        taskView.setAlpha(0.0f);
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.addFlags(134217728);
        this.fillInIntent = intent;
        this.stateCallback = new TaskView.Listener() { // from class: com.android.systemui.controls.ui.PanelTaskViewController$stateCallback$1
            @Override // com.android.wm.shell.taskview.TaskView.Listener
            public final void onBackPressedOnTaskRoot(int i) {
                PanelTaskViewController.this.hide.invoke();
            }

            @Override // com.android.wm.shell.taskview.TaskView.Listener
            public final void onInitialized() {
                final PanelTaskViewController panelTaskViewController = PanelTaskViewController.this;
                final ActivityOptions makeCustomAnimation = ActivityOptions.makeCustomAnimation(panelTaskViewController.activityContext, 0, 0);
                makeCustomAnimation.setTaskAlwaysOnTop(true);
                panelTaskViewController.taskView.post(new Runnable() { // from class: com.android.systemui.controls.ui.PanelTaskViewController$stateCallback$1$onInitialized$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        int dimensionPixelSize = PanelTaskViewController.this.activityContext.getResources().getDimensionPixelSize(R.dimen.controls_panel_corner_radius);
                        float[] fArr = new float[8];
                        for (int i = 0; i < 8; i++) {
                            fArr[i] = dimensionPixelSize;
                        }
                        TaskView taskView2 = PanelTaskViewController.this.taskView;
                        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, null, null));
                        shapeDrawable.setTint(0);
                        taskView2.setBackground(shapeDrawable);
                        PanelTaskViewController.this.taskView.setClipToOutline(true);
                        PanelTaskViewController panelTaskViewController2 = PanelTaskViewController.this;
                        TaskView taskView3 = panelTaskViewController2.taskView;
                        taskView3.startActivity(panelTaskViewController2.pendingIntent, panelTaskViewController2.fillInIntent, makeCustomAnimation, ConvenienceExtensionsKt.getBoundsOnScreen(taskView3));
                        Trace.instant(4096L, "PanelTaskViewController - startActivity");
                    }
                });
            }

            @Override // com.android.wm.shell.taskview.TaskView.Listener
            public final void onTaskCreated(int i, ComponentName componentName) {
                PanelTaskViewController.this.taskView.setAlpha(1.0f);
            }

            @Override // com.android.wm.shell.taskview.TaskView.Listener
            public final void onTaskRemovalStarted(int i) {
                PanelTaskViewController.this.release();
            }
        };
    }

    public final void release() {
        TaskView taskView = this.taskView;
        taskView.getHolder().removeCallback(taskView);
        taskView.mTaskViewTaskController.performRelease();
    }
}
