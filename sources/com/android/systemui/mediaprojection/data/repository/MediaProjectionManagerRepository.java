package com.android.systemui.mediaprojection.data.repository;

import android.hardware.display.DisplayManager;
import android.media.projection.MediaProjectionInfo;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.view.ContentRecordingSession;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.mediaprojection.MediaProjectionServiceHelper;
import com.android.systemui.mediaprojection.data.model.MediaProjectionState;
import com.android.systemui.mediaprojection.taskswitcher.data.repository.ActivityTaskManagerTasksRepository;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaProjectionManagerRepository {
    public final CoroutineDispatcher backgroundDispatcher;
    public final DisplayManager displayManager;
    public final Handler handler;
    public final LogBuffer logger;
    public final MediaProjectionManager mediaProjectionManager;
    public final MediaProjectionServiceHelper mediaProjectionServiceHelper;
    public final ReadonlyStateFlow mediaProjectionState;
    public final ActivityTaskManagerTasksRepository tasksRepository;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface CallbackEvent {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class OnRecordingSessionSet implements CallbackEvent {
            public final MediaProjectionInfo info;
            public final ContentRecordingSession session;

            public OnRecordingSessionSet(MediaProjectionInfo mediaProjectionInfo, ContentRecordingSession contentRecordingSession) {
                this.info = mediaProjectionInfo;
                this.session = contentRecordingSession;
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof OnRecordingSessionSet)) {
                    return false;
                }
                OnRecordingSessionSet onRecordingSessionSet = (OnRecordingSessionSet) obj;
                return Intrinsics.areEqual(this.info, onRecordingSessionSet.info) && Intrinsics.areEqual(this.session, onRecordingSessionSet.session);
            }

            public final int hashCode() {
                int hashCode = this.info.hashCode() * 31;
                ContentRecordingSession contentRecordingSession = this.session;
                return hashCode + (contentRecordingSession == null ? 0 : contentRecordingSession.hashCode());
            }

            public final String toString() {
                return "OnRecordingSessionSet(info=" + this.info + ", session=" + this.session + ")";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class OnStart implements CallbackEvent {
            public static final OnStart INSTANCE = new OnStart();

            public final boolean equals(Object obj) {
                return this == obj || (obj instanceof OnStart);
            }

            public final int hashCode() {
                return 650369722;
            }

            public final String toString() {
                return "OnStart";
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class OnStop implements CallbackEvent {
            public static final OnStop INSTANCE = new OnStop();

            public final boolean equals(Object obj) {
                return this == obj || (obj instanceof OnStop);
            }

            public final int hashCode() {
                return 1406453418;
            }

            public final String toString() {
                return "OnStop";
            }
        }
    }

    public MediaProjectionManagerRepository(MediaProjectionManager mediaProjectionManager, DisplayManager displayManager, Handler handler, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, ActivityTaskManagerTasksRepository activityTaskManagerTasksRepository, MediaProjectionServiceHelper mediaProjectionServiceHelper, LogBuffer logBuffer) {
        this.mediaProjectionManager = mediaProjectionManager;
        this.displayManager = displayManager;
        this.handler = handler;
        this.backgroundDispatcher = coroutineDispatcher;
        this.tasksRepository = activityTaskManagerTasksRepository;
        this.logger = logBuffer;
        this.mediaProjectionState = FlowKt.stateIn(FlowKt.mapLatest(new MediaProjectionManagerRepository$mediaProjectionState$2(this, null), FlowConflatedKt.conflatedCallbackFlow(new MediaProjectionManagerRepository$mediaProjectionState$1(this, null))), coroutineScope, SharingStarted.Companion.Lazily, MediaProjectionState.NotProjecting.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$stateForSession(com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository r7, android.media.projection.MediaProjectionInfo r8, android.view.ContentRecordingSession r9, kotlin.coroutines.jvm.internal.ContinuationImpl r10) {
        /*
            r7.getClass()
            boolean r0 = r10 instanceof com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository$stateForSession$1
            if (r0 == 0) goto L16
            r0 = r10
            com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository$stateForSession$1 r0 = (com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository$stateForSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository$stateForSession$1 r0 = new com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository$stateForSession$1
            r0.<init>(r7, r10)
        L1b:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L53
            if (r2 == r5) goto L3f
            if (r2 != r4) goto L37
            java.lang.Object r7 = r0.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r0.L$0
            java.lang.String r8 = (java.lang.String) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L9f
        L37:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3f:
            java.lang.Object r7 = r0.L$2
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r0.L$1
            r9 = r8
            android.view.ContentRecordingSession r9 = (android.view.ContentRecordingSession) r9
            java.lang.Object r8 = r0.L$0
            com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository r8 = (com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository) r8
            kotlin.ResultKt.throwOnFailure(r10)
            r6 = r8
            r8 = r7
            r7 = r6
            goto L76
        L53:
            kotlin.ResultKt.throwOnFailure(r10)
            if (r9 != 0) goto L5c
            com.android.systemui.mediaprojection.data.model.MediaProjectionState$NotProjecting r1 = com.android.systemui.mediaprojection.data.model.MediaProjectionState.NotProjecting.INSTANCE
            goto Lc5
        L5c:
            java.lang.String r8 = r8.getPackageName()
            com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository$stateForSession$hostDeviceName$1 r10 = new com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository$stateForSession$hostDeviceName$1
            r10.<init>(r7, r9, r3)
            r0.L$0 = r7
            r0.L$1 = r9
            r0.L$2 = r8
            r0.label = r5
            kotlinx.coroutines.CoroutineDispatcher r2 = r7.backgroundDispatcher
            java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r2, r10, r0)
            if (r10 != r1) goto L76
            goto Lc5
        L76:
            java.lang.String r10 = (java.lang.String) r10
            int r2 = r9.getContentToRecord()
            if (r2 == 0) goto Lbd
            android.os.IBinder r2 = r9.getTokenToRecord()
            if (r2 != 0) goto L85
            goto Lbd
        L85:
            com.android.systemui.mediaprojection.taskswitcher.data.repository.ActivityTaskManagerTasksRepository r7 = r7.tasksRepository
            android.os.IBinder r9 = r9.getTokenToRecord()
            if (r9 == 0) goto Lb5
            r0.L$0 = r8
            r0.L$1 = r10
            r0.L$2 = r3
            r0.label = r4
            java.lang.Object r7 = r7.findRunningTaskFromWindowContainerToken(r9, r0)
            if (r7 != r1) goto L9c
            goto Lc5
        L9c:
            r6 = r10
            r10 = r7
            r7 = r6
        L9f:
            android.app.ActivityManager$RunningTaskInfo r10 = (android.app.ActivityManager.RunningTaskInfo) r10
            if (r10 != 0) goto Lac
            com.android.systemui.mediaprojection.data.model.MediaProjectionState$Projecting$EntireScreen r1 = new com.android.systemui.mediaprojection.data.model.MediaProjectionState$Projecting$EntireScreen
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            r1.<init>(r8, r7)
            goto Lc5
        Lac:
            com.android.systemui.mediaprojection.data.model.MediaProjectionState$Projecting$SingleTask r1 = new com.android.systemui.mediaprojection.data.model.MediaProjectionState$Projecting$SingleTask
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            r1.<init>(r8, r7, r10)
            goto Lc5
        Lb5:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "Required value was null."
            r7.<init>(r8)
            throw r7
        Lbd:
            com.android.systemui.mediaprojection.data.model.MediaProjectionState$Projecting$EntireScreen r1 = new com.android.systemui.mediaprojection.data.model.MediaProjectionState$Projecting$EntireScreen
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            r1.<init>(r8, r10)
        Lc5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository.access$stateForSession(com.android.systemui.mediaprojection.data.repository.MediaProjectionManagerRepository, android.media.projection.MediaProjectionInfo, android.view.ContentRecordingSession, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final Object stopProjecting(Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.backgroundDispatcher, new MediaProjectionManagerRepository$stopProjecting$2(this, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }
}
