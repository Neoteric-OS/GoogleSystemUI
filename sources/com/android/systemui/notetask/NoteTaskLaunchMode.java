package com.android.systemui.notetask;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class NoteTaskLaunchMode {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Activity extends NoteTaskLaunchMode {
        public static final Activity INSTANCE = new Activity();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AppBubble extends NoteTaskLaunchMode {
        public static final AppBubble INSTANCE = new AppBubble();
    }
}
