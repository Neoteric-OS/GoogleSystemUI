package com.android.systemui.mediaprojection.appselector.view;

import android.R;
import android.app.ActivityOptions;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.DisplayInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.window.RemoteTransition;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.mediaprojection.appselector.data.ActivityTaskManagerLabelLoader;
import com.android.systemui.mediaprojection.appselector.data.ActivityTaskManagerThumbnailLoader;
import com.android.systemui.mediaprojection.appselector.data.BadgedAppIconLoader;
import com.android.systemui.mediaprojection.appselector.data.BasicPackageManagerAppIconLoader;
import com.android.systemui.mediaprojection.appselector.data.RecentTask;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.shared.split.SplitBounds;
import com.android.wm.shell.splitscreen.SplitScreen;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$2;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RecentTasksAdapter extends RecyclerView.Adapter {
    public final List items;
    public final MediaProjectionRecentsViewController listener;
    public final DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$2 viewHolderFactory;

    public RecentTasksAdapter(List list, MediaProjectionRecentsViewController mediaProjectionRecentsViewController, DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$2 daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$2) {
        this.items = list;
        this.listener = mediaProjectionRecentsViewController;
        this.viewHolderFactory = daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        final RecentTaskViewHolder recentTaskViewHolder = (RecentTaskViewHolder) viewHolder;
        final RecentTask recentTask = (RecentTask) this.items.get(i);
        final Function1 function1 = new Function1() { // from class: com.android.systemui.mediaprojection.appselector.view.RecentTasksAdapter$onBindViewHolder$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ActivityOptions makeScaleUpAnimation;
                final MediaProjectionRecentsViewController mediaProjectionRecentsViewController = RecentTasksAdapter.this.listener;
                RecentTask recentTask2 = recentTask;
                View view = recentTaskViewHolder.itemView;
                mediaProjectionRecentsViewController.getClass();
                final ActivityOptions.LaunchCookie launchCookie = new ActivityOptions.LaunchCookie();
                boolean z = recentTask2.isForegroundTask;
                SplitBounds splitBounds = recentTask2.splitBounds;
                if (z) {
                    makeScaleUpAnimation = ActivityOptions.makeCustomTaskAnimation(view.getContext(), 0, R.anim.screen_rotate_180_enter, null, null, null);
                    Intrinsics.checkNotNull(makeScaleUpAnimation);
                } else if (!mediaProjectionRecentsViewController.splitScreen.isPresent() || splitBounds == null) {
                    makeScaleUpAnimation = ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.getWidth(), view.getHeight());
                    Intrinsics.checkNotNull(makeScaleUpAnimation);
                } else {
                    makeScaleUpAnimation = ActivityOptions.makeBasic();
                    Intrinsics.checkNotNull(makeScaleUpAnimation);
                }
                makeScaleUpAnimation.setPendingIntentBackgroundActivityStartMode(1);
                makeScaleUpAnimation.setLaunchDisplayId(recentTask2.displayId);
                makeScaleUpAnimation.setLaunchCookie(launchCookie);
                final int i2 = recentTask2.taskId;
                Function0 function0 = new Function0() { // from class: com.android.systemui.mediaprojection.appselector.view.MediaProjectionRecentsViewController$onRecentAppClicked$handleResult$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        MediaProjectionRecentsViewController.this.resultHandler.returnSelectedApp(launchCookie, i2);
                        return Unit.INSTANCE;
                    }
                };
                if (!mediaProjectionRecentsViewController.splitScreen.isPresent() || splitBounds == null || z) {
                    mediaProjectionRecentsViewController.activityTaskManager.startActivityFromRecents(i2, makeScaleUpAnimation.toBundle());
                    function0.invoke();
                } else {
                    Intrinsics.checkNotNull(splitBounds);
                    int i3 = splitBounds.leftTopTaskId;
                    int i4 = i2 == i3 ? 1 : 0;
                    if (i4 != 0) {
                        i3 = splitBounds.rightBottomTaskId;
                    }
                    final int i5 = i3;
                    final int i6 = i4 ^ 1;
                    int[] locationOnScreen = view.getLocationOnScreen();
                    Display display = view.getContext().getDisplay();
                    DisplayInfo displayInfo = new DisplayInfo();
                    display.getDisplayInfo(displayInfo);
                    final RemoteTransition remoteTransition = new RemoteTransition(new RemoteRecentSplitTaskTransitionRunner(i2, i5, locationOnScreen, new Rect(0, 0, displayInfo.getNaturalWidth(), displayInfo.getNaturalHeight()), function0), view.getContext().getIApplicationThread(), "startSplitScreenTask");
                    SplitScreen splitScreen = (SplitScreen) mediaProjectionRecentsViewController.splitScreen.get();
                    final Bundle bundle = makeScaleUpAnimation.toBundle();
                    final int i7 = splitBounds.snapPosition;
                    final SplitScreenController.SplitScreenImpl splitScreenImpl = (SplitScreenController.SplitScreenImpl) splitScreen;
                    ((HandlerExecutor) SplitScreenController.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$SplitScreenImpl$$ExternalSyntheticLambda7
                        @Override // java.lang.Runnable
                        public final void run() {
                            SplitScreenController.SplitScreenImpl splitScreenImpl2 = SplitScreenController.SplitScreenImpl.this;
                            SplitScreenController.this.mStageCoordinator.startTasks(i2, bundle, i5, null, i6, i7, remoteTransition, null);
                        }
                    });
                }
                return Unit.INSTANCE;
            }
        };
        recentTaskViewHolder.taskViewSizeProvider.listeners.add(recentTaskViewHolder);
        StandaloneCoroutine standaloneCoroutine = recentTaskViewHolder.job;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        recentTaskViewHolder.job = BuildersKt.launch$default(recentTaskViewHolder.scope, null, null, new RecentTaskViewHolder$bind$1(recentTask, recentTaskViewHolder, null), 3);
        recentTaskViewHolder.root.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.mediaprojection.appselector.view.RecentTaskViewHolder$sam$android_view_View_OnClickListener$0
            @Override // android.view.View.OnClickListener
            public final /* synthetic */ void onClick(View view) {
                Function1.this.invoke(view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(int i, ViewGroup viewGroup) {
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(com.android.wm.shell.R.layout.media_projection_task_item, viewGroup, false);
        DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$2 daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$2 = this.viewHolderFactory;
        daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$2.getClass();
        DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$2.this$0.dozeComponentImpl;
        return new RecentTaskViewHolder(viewGroup2, new BadgedAppIconLoader((BasicPackageManagerAppIconLoader) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.bindAppIconLoaderProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.sysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.bindIconFactoryProvider), (ActivityTaskManagerThumbnailLoader) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.bindRecentTaskThumbnailLoaderProvider.get(), (ActivityTaskManagerLabelLoader) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.bindRecentTaskLabelLoaderProvider.get(), (TaskPreviewSizeProvider) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.taskPreviewSizeProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.provideCoroutineScopeProvider.get());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        RecentTaskViewHolder recentTaskViewHolder = (RecentTaskViewHolder) viewHolder;
        recentTaskViewHolder.taskViewSizeProvider.listeners.remove(recentTaskViewHolder);
        recentTaskViewHolder.iconView.setImageDrawable(null);
        recentTaskViewHolder.thumbnailView.bindTask(null, null);
        StandaloneCoroutine standaloneCoroutine = recentTaskViewHolder.job;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        recentTaskViewHolder.job = null;
    }
}
