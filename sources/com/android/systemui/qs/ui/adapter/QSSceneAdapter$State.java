package com.android.systemui.qs.ui.adapter;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface QSSceneAdapter$State {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CLOSED implements QSSceneAdapter$State {
        public static final CLOSED INSTANCE = new CLOSED();
        public static final Function0 expansion = new Function0() { // from class: com.android.systemui.qs.ui.adapter.QSSceneAdapter$State$CLOSED$expansion$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(0.0f);
            }
        };
        public static final Function0 squishiness = new Function0() { // from class: com.android.systemui.qs.ui.adapter.QSSceneAdapter$State$CLOSED$squishiness$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(1.0f);
            }
        };

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof CLOSED);
        }

        @Override // com.android.systemui.qs.ui.adapter.QSSceneAdapter$State
        public final Function0 getExpansion() {
            return expansion;
        }

        @Override // com.android.systemui.qs.ui.adapter.QSSceneAdapter$State
        public final Function0 getSquishiness() {
            return squishiness;
        }

        public final int hashCode() {
            return 2128253062;
        }

        @Override // com.android.systemui.qs.ui.adapter.QSSceneAdapter$State
        public final boolean isVisible() {
            return false;
        }

        public final String toString() {
            return "CLOSED";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final Expanding QQS = null;
        public static final Expanding QS;

        static {
            new Expanding(new Function0() { // from class: com.android.systemui.qs.ui.adapter.QSSceneAdapter$State$Companion$QQS$1
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return Float.valueOf(0.0f);
                }
            });
            QS = new Expanding(new Function0() { // from class: com.android.systemui.qs.ui.adapter.QSSceneAdapter$State$Companion$QS$1
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return Float.valueOf(1.0f);
                }
            });
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Expanding implements QSSceneAdapter$State {
        public final Lambda expansion;
        public final Function0 squishiness = new Function0() { // from class: com.android.systemui.qs.ui.adapter.QSSceneAdapter$State$Expanding$squishiness$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Float.valueOf(1.0f);
            }
        };

        /* JADX WARN: Multi-variable type inference failed */
        public Expanding(Function0 function0) {
            this.expansion = (Lambda) function0;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
        @Override // com.android.systemui.qs.ui.adapter.QSSceneAdapter$State
        public final Function0 getExpansion() {
            return this.expansion;
        }

        @Override // com.android.systemui.qs.ui.adapter.QSSceneAdapter$State
        public final Function0 getSquishiness() {
            return this.squishiness;
        }

        @Override // com.android.systemui.qs.ui.adapter.QSSceneAdapter$State
        public final boolean isVisible() {
            return true;
        }
    }

    Function0 getExpansion();

    Function0 getSquishiness();

    boolean isVisible();
}
