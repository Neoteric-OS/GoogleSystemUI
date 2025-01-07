package com.android.systemui.mediaprojection.appselector.view;

import android.app.IActivityTaskManager;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorActivity;
import com.android.systemui.mediaprojection.appselector.view.TaskPreviewSizeProvider;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$1;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$2;
import java.util.List;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaProjectionRecentsViewController implements TaskPreviewSizeProvider.TaskPreviewSizeListener {
    public final IActivityTaskManager activityTaskManager;
    public List lastBoundData;
    public final DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$1 recentTasksAdapterFactory;
    public final MediaProjectionAppSelectorActivity resultHandler;
    public final Optional splitScreen;
    public final TaskPreviewSizeProvider taskViewSizeProvider;
    public Views views;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Views {
        public final View progress;
        public final View recentsContainer;
        public final RecyclerView recycler;
        public final ViewGroup root;

        public Views(ViewGroup viewGroup, View view, View view2, RecyclerView recyclerView) {
            this.root = viewGroup;
            this.recentsContainer = view;
            this.progress = view2;
            this.recycler = recyclerView;
        }
    }

    public MediaProjectionRecentsViewController(DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$1 daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$1, TaskPreviewSizeProvider taskPreviewSizeProvider, IActivityTaskManager iActivityTaskManager, MediaProjectionAppSelectorActivity mediaProjectionAppSelectorActivity, Optional optional) {
        this.recentTasksAdapterFactory = daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$1;
        this.taskViewSizeProvider = taskPreviewSizeProvider;
        this.activityTaskManager = iActivityTaskManager;
        this.resultHandler = mediaProjectionAppSelectorActivity;
        this.splitScreen = optional;
        taskPreviewSizeProvider.listeners.add(this);
    }

    public final void bind(List list) {
        Views views = this.views;
        if (views != null) {
            if (list.isEmpty()) {
                views.root.setVisibility(8);
                return;
            }
            views.progress.setVisibility(8);
            RecyclerView recyclerView = views.recycler;
            recyclerView.setVisibility(0);
            views.root.setVisibility(0);
            recyclerView.setAdapter(new RecentTasksAdapter(list, this, (DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$2) ((DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl) this.recentTasksAdapterFactory.this$0.dozeComponentImpl).factoryProvider.get()));
        }
        this.lastBoundData = list;
    }

    @Override // com.android.systemui.mediaprojection.appselector.view.TaskPreviewSizeProvider.TaskPreviewSizeListener
    public final void onTaskSizeChanged() {
        Views views = this.views;
        if (views != null) {
            setTaskHeightSize(views.recentsContainer);
        }
    }

    public final void setTaskHeightSize(View view) {
        int dimensionPixelSize = (view.getContext().getResources().getDimensionPixelSize(R.dimen.media_projection_app_selector_task_icon_margin) * 2) + view.getContext().getResources().getDimensionPixelSize(R.dimen.media_projection_app_selector_task_icon_size) + this.taskViewSizeProvider.size.height();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = dimensionPixelSize;
        view.setLayoutParams(layoutParams);
    }
}
