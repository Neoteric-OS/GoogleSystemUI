package com.android.systemui.log.echo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface Outcome {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Failure implements Outcome {
        public final String message;

        public Failure(String str) {
            this.message = str;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Success implements Outcome {
        public final ParsedOverride value;

        public Success(ParsedOverride parsedOverride) {
            this.value = parsedOverride;
        }
    }
}
