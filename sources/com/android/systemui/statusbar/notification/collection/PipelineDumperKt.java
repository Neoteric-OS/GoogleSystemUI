package com.android.systemui.statusbar.notification.collection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class PipelineDumperKt {
    public static final String getBareClassName(Object obj) {
        String name;
        String name2 = obj.getClass().getName();
        Package r1 = obj.getClass().getPackage();
        return name2.substring((r1 == null || (name = r1.getName()) == null) ? 0 : name.length() + 1);
    }
}
