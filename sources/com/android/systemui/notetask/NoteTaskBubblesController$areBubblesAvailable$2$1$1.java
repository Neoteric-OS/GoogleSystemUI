package com.android.systemui.notetask;

import com.android.internal.infra.ServiceConnector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NoteTaskBubblesController$areBubblesAvailable$2$1$1 implements ServiceConnector.Job {
    public static final NoteTaskBubblesController$areBubblesAvailable$2$1$1 INSTANCE = new NoteTaskBubblesController$areBubblesAvailable$2$1$1();

    public final Object run(Object obj) {
        return Boolean.valueOf(((INoteTaskBubblesService) obj).areBubblesAvailable());
    }
}
