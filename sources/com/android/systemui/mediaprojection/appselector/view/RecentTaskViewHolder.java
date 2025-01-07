package com.android.systemui.mediaprojection.appselector.view;

import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.mediaprojection.appselector.data.ActivityTaskManagerLabelLoader;
import com.android.systemui.mediaprojection.appselector.data.ActivityTaskManagerThumbnailLoader;
import com.android.systemui.mediaprojection.appselector.data.BadgedAppIconLoader;
import com.android.systemui.mediaprojection.appselector.view.TaskPreviewSizeProvider;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.wm.shell.R;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RecentTaskViewHolder extends RecyclerView.ViewHolder implements ConfigurationController.ConfigurationListener, TaskPreviewSizeProvider.TaskPreviewSizeListener {
    public final BadgedAppIconLoader iconLoader;
    public final ImageView iconView;
    public StandaloneCoroutine job;
    public final ActivityTaskManagerLabelLoader labelLoader;
    public final ViewGroup root;
    public final CoroutineScope scope;
    public final TaskPreviewSizeProvider taskViewSizeProvider;
    public final ActivityTaskManagerThumbnailLoader thumbnailLoader;
    public final MediaProjectionTaskView thumbnailView;

    public RecentTaskViewHolder(ViewGroup viewGroup, BadgedAppIconLoader badgedAppIconLoader, ActivityTaskManagerThumbnailLoader activityTaskManagerThumbnailLoader, ActivityTaskManagerLabelLoader activityTaskManagerLabelLoader, TaskPreviewSizeProvider taskPreviewSizeProvider, CoroutineScope coroutineScope) {
        super(viewGroup);
        this.root = viewGroup;
        this.iconLoader = badgedAppIconLoader;
        this.thumbnailLoader = activityTaskManagerThumbnailLoader;
        this.labelLoader = activityTaskManagerLabelLoader;
        this.taskViewSizeProvider = taskPreviewSizeProvider;
        this.scope = coroutineScope;
        MediaProjectionTaskView mediaProjectionTaskView = (MediaProjectionTaskView) viewGroup.requireViewById(R.id.task_thumbnail);
        this.thumbnailView = mediaProjectionTaskView;
        this.iconView = (ImageView) viewGroup.requireViewById(R.id.task_icon);
        ViewGroup.LayoutParams layoutParams = mediaProjectionTaskView.getLayoutParams();
        layoutParams.width = taskPreviewSizeProvider.size.width();
        layoutParams.height = taskPreviewSizeProvider.size.height();
        mediaProjectionTaskView.setLayoutParams(layoutParams);
    }

    @Override // com.android.systemui.mediaprojection.appselector.view.TaskPreviewSizeProvider.TaskPreviewSizeListener
    public final void onTaskSizeChanged() {
        MediaProjectionTaskView mediaProjectionTaskView = this.thumbnailView;
        ViewGroup.LayoutParams layoutParams = mediaProjectionTaskView.getLayoutParams();
        TaskPreviewSizeProvider taskPreviewSizeProvider = this.taskViewSizeProvider;
        layoutParams.width = taskPreviewSizeProvider.size.width();
        layoutParams.height = taskPreviewSizeProvider.size.height();
        mediaProjectionTaskView.setLayoutParams(layoutParams);
    }
}
