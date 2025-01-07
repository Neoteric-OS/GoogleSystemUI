package com.android.systemui.plugins.log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface TableLogBufferBase {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DefaultImpls {
        public static void logChange(TableLogBufferBase tableLogBufferBase, String str, String str2, String str3) {
            tableLogBufferBase.logChange(str, str2, str3, false);
        }

        public static void logChange(TableLogBufferBase tableLogBufferBase, String str, String str2, boolean z) {
            tableLogBufferBase.logChange(str, str2, z, false);
        }

        public static void logChange(TableLogBufferBase tableLogBufferBase, String str, String str2, Integer num) {
            tableLogBufferBase.logChange(str, str2, num, false);
        }
    }

    void logChange(String str, String str2, Integer num);

    void logChange(String str, String str2, Integer num, boolean z);

    void logChange(String str, String str2, String str3);

    void logChange(String str, String str2, String str3, boolean z);

    void logChange(String str, String str2, boolean z);

    void logChange(String str, String str2, boolean z, boolean z2);
}
