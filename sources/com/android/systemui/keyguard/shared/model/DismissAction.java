package com.android.systemui.keyguard.shared.model;

import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface DismissAction {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class None implements DismissAction {
        public static final None INSTANCE = new None();
        public static final Function0 onDismissAction = new Function0() { // from class: com.android.systemui.keyguard.shared.model.DismissAction$None$onDismissAction$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return KeyguardDone.IMMEDIATE;
            }
        };

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof None);
        }

        public final int hashCode() {
            return 1219445227;
        }

        public final String toString() {
            return "None";
        }
    }
}
