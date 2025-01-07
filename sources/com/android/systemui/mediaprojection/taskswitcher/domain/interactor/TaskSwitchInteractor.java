package com.android.systemui.mediaprojection.taskswitcher.domain.interactor;

import com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository;
import com.android.systemui.mediaprojection.taskswitcher.data.repository.ActivityTaskManagerTasksRepository;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TaskSwitchInteractor {
    public final MediaProjectionManagerRepository mediaProjectionRepository;
    public final ChannelFlowTransformLatest taskSwitchChanges;
    public final ActivityTaskManagerTasksRepository tasksRepository;

    public TaskSwitchInteractor(MediaProjectionManagerRepository mediaProjectionManagerRepository, ActivityTaskManagerTasksRepository activityTaskManagerTasksRepository) {
        this.tasksRepository = activityTaskManagerTasksRepository;
        this.taskSwitchChanges = FlowKt.transformLatest(mediaProjectionManagerRepository.mediaProjectionState, new TaskSwitchInteractor$special$$inlined$flatMapLatest$1(null, this));
    }
}
