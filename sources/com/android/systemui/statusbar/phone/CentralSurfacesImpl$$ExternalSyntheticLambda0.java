package com.android.systemui.statusbar.phone;

import android.os.Build;
import com.android.systemui.notetask.NoteTaskController;
import com.android.systemui.notetask.NoteTaskEventLogger;
import com.android.systemui.notetask.NoteTaskInfo;
import com.android.systemui.notetask.NoteTaskLaunchMode;
import com.android.wm.shell.bubbles.Bubble;
import com.android.wm.shell.bubbles.Bubbles;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class CentralSurfacesImpl$$ExternalSyntheticLambda0 implements Bubbles.BubbleExpandListener {
    public final /* synthetic */ Object f$0;

    @Override // com.android.wm.shell.bubbles.Bubbles.BubbleExpandListener
    public void onBubbleExpandChanged(final String str, final boolean z) {
        final CentralSurfacesImpl centralSurfacesImpl = (CentralSurfacesImpl) this.f$0;
        centralSurfacesImpl.mContext.getMainExecutor().execute(new Runnable() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda34
            @Override // java.lang.Runnable
            public final void run() {
                NoteTaskInfo noteTaskInfo;
                CentralSurfacesImpl centralSurfacesImpl2 = CentralSurfacesImpl.this;
                boolean z2 = z;
                String str2 = str;
                centralSurfacesImpl2.updateScrimController();
                NoteTaskController noteTaskController = (NoteTaskController) centralSurfacesImpl2.mNoteTaskControllerLazy.get();
                if (noteTaskController.isEnabled && (noteTaskInfo = (NoteTaskInfo) noteTaskController.infoReference.getAndSet(null)) != null) {
                    if (Intrinsics.areEqual(str2, Bubble.getAppBubbleKeyForApp(noteTaskInfo.packageName, noteTaskInfo.user))) {
                        if (Intrinsics.areEqual(noteTaskInfo.launchMode, NoteTaskLaunchMode.AppBubble.INSTANCE)) {
                            NoteTaskEventLogger noteTaskEventLogger = noteTaskController.eventLogger;
                            if (z2) {
                                boolean z3 = Build.IS_DEBUGGABLE;
                                Reflection.getOrCreateKotlinClass(NoteTaskController.class).getSimpleName();
                                noteTaskEventLogger.logNoteTaskOpened(noteTaskInfo);
                            } else {
                                boolean z4 = Build.IS_DEBUGGABLE;
                                Reflection.getOrCreateKotlinClass(NoteTaskController.class).getSimpleName();
                                noteTaskEventLogger.logNoteTaskClosed(noteTaskInfo);
                            }
                        }
                    }
                }
            }
        });
    }
}
