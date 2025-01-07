package com.android.systemui.notetask;

import androidx.lifecycle.LifecycleService;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NoteTaskControllerUpdateService extends LifecycleService {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final NoteTaskController controller;

    public NoteTaskControllerUpdateService(NoteTaskController noteTaskController) {
        this.controller = noteTaskController;
    }

    @Override // androidx.lifecycle.LifecycleService, android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.controller.launchUpdateNoteTaskAsUser(getUser());
        stopSelf();
    }
}
