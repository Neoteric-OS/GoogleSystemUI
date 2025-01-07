package com.android.wm.shell.windowdecor.viewholder;

import android.animation.ObjectAnimator;
import android.app.ActivityManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Handler;
import android.util.Property;
import android.view.View;
import android.widget.ImageButton;
import com.android.internal.policy.SystemBarUtils;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.animation.Interpolators;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel;
import com.android.wm.shell.windowdecor.WindowManagerWrapper;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AppHandleViewHolder extends WindowDecorationViewHolder {
    public final ImageButton captionHandle;
    public final Handler handler;
    public boolean statusBarInputLayerExists;

    public AppHandleViewHolder(View view, DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener desktopModeTouchEventListener, DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener desktopModeTouchEventListener2, WindowManagerWrapper windowManagerWrapper, Handler handler) {
        super(view);
        this.handler = handler;
        View requireViewById = view.requireViewById(R.id.desktop_mode_caption);
        ImageButton imageButton = (ImageButton) view.requireViewById(R.id.caption_handle);
        this.captionHandle = imageButton;
        requireViewById.setOnTouchListener(desktopModeTouchEventListener);
        imageButton.setOnTouchListener(desktopModeTouchEventListener);
        imageButton.setOnClickListener(desktopModeTouchEventListener2);
    }

    @Override // com.android.wm.shell.windowdecor.viewholder.WindowDecorationViewHolder
    public final void bindData(ActivityManager.RunningTaskInfo runningTaskInfo, final Point point, int i, int i2, boolean z) {
        ImageButton imageButton = this.captionHandle;
        ActivityManager.TaskDescription taskDescription = runningTaskInfo.taskDescription;
        imageButton.setImageTintList(ColorStateList.valueOf((taskDescription == null || (Color.alpha(taskDescription.getStatusBarColor()) == 0 || runningTaskInfo.getWindowingMode() != 5 ? (taskDescription.getSystemBarsAppearance() & 8) != 0 : ((double) Color.valueOf(taskDescription.getStatusBarColor()).luminance()) >= 0.5d)) ? this.context.getColor(R.color.desktop_mode_caption_handle_bar_dark) : this.context.getColor(R.color.desktop_mode_caption_handle_bar_light)));
        if (point.y >= SystemBarUtils.getStatusBarHeight(this.context)) {
            return;
        }
        Handler handler = this.handler;
        if (!z && this.statusBarInputLayerExists) {
            this.statusBarInputLayerExists = false;
            handler.post(new AppHandleViewHolder$bindData$2(this));
        } else if (this.statusBarInputLayerExists) {
            handler.post(new Runnable(point) { // from class: com.android.wm.shell.windowdecor.viewholder.AppHandleViewHolder$bindData$1
                public final /* synthetic */ Point $position;

                @Override // java.lang.Runnable
                public final void run() {
                    AppHandleViewHolder.this.getClass();
                }
            });
        } else {
            this.statusBarInputLayerExists = true;
            handler.post(new AppHandleViewHolder$bindData$2(this, point, i, i2));
        }
    }

    @Override // com.android.wm.shell.windowdecor.viewholder.WindowDecorationViewHolder
    public final void onHandleMenuClosed() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.captionHandle, (Property<ImageButton, Float>) View.ALPHA, 0.0f, 1.0f);
        ofFloat.setDuration(100L);
        ofFloat.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        ofFloat.start();
    }

    @Override // com.android.wm.shell.windowdecor.viewholder.WindowDecorationViewHolder
    public final void onHandleMenuOpened() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.captionHandle, (Property<ImageButton, Float>) View.ALPHA, 1.0f, 0.0f);
        ofFloat.setDuration(100L);
        ofFloat.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        ofFloat.start();
    }
}
