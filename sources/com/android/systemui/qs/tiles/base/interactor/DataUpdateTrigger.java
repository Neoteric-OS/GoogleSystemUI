package com.android.systemui.qs.tiles.base.interactor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface DataUpdateTrigger {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ForceUpdate implements DataUpdateTrigger {
        public static final ForceUpdate INSTANCE = new ForceUpdate();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof ForceUpdate);
        }

        public final int hashCode() {
            return 1800360925;
        }

        public final String toString() {
            return "ForceUpdate";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InitialRequest implements DataUpdateTrigger {
        public static final InitialRequest INSTANCE = new InitialRequest();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof InitialRequest);
        }

        public final int hashCode() {
            return -1932546622;
        }

        public final String toString() {
            return "InitialRequest";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UserInput implements DataUpdateTrigger {
        public final QSTileInput input;

        public UserInput(QSTileInput qSTileInput) {
            this.input = qSTileInput;
        }
    }
}
