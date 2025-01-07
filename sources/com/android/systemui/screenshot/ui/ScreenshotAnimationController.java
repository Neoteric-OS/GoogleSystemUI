package com.android.systemui.screenshot.ui;

import android.animation.Animator;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import com.android.systemui.screenshot.ui.viewmodel.ScreenshotViewModel;
import com.android.wm.shell.R;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenshotAnimationController {
    public final View actionContainer;
    public Animator animator;
    public final List fadeUI;
    public final Interpolator fastOutSlowIn;
    public final View flashView;
    public final ImageView screenshotPreview;
    public final ImageView scrollTransitionPreview;
    public final ImageView scrollingScrim;
    public final List staticUI;
    public final ScreenshotShelfView view;
    public final ScreenshotViewModel viewModel;

    public ScreenshotAnimationController(ScreenshotShelfView screenshotShelfView, ScreenshotViewModel screenshotViewModel) {
        this.view = screenshotShelfView;
        this.viewModel = screenshotViewModel;
        this.screenshotPreview = (ImageView) screenshotShelfView.requireViewById(R.id.screenshot_preview);
        this.scrollingScrim = (ImageView) screenshotShelfView.requireViewById(R.id.screenshot_scrolling_scrim);
        this.scrollTransitionPreview = (ImageView) screenshotShelfView.requireViewById(R.id.screenshot_scrollable_preview);
        this.flashView = screenshotShelfView.requireViewById(R.id.screenshot_flash);
        this.actionContainer = screenshotShelfView.requireViewById(R.id.actions_container_background);
        this.fastOutSlowIn = AnimationUtils.loadInterpolator(screenshotShelfView.getContext(), android.R.interpolator.fast_out_slow_in);
        this.staticUI = CollectionsKt__CollectionsKt.listOf(screenshotShelfView.requireViewById(R.id.screenshot_preview_border), screenshotShelfView.requireViewById(R.id.screenshot_badge), screenshotShelfView.requireViewById(R.id.screenshot_dismiss_button));
        this.fadeUI = CollectionsKt__CollectionsKt.listOf(screenshotShelfView.requireViewById(R.id.screenshot_preview_border), screenshotShelfView.requireViewById(R.id.actions_container_background), screenshotShelfView.requireViewById(R.id.screenshot_badge), screenshotShelfView.requireViewById(R.id.screenshot_dismiss_button), screenshotShelfView.requireViewById(R.id.screenshot_message_container));
    }
}
