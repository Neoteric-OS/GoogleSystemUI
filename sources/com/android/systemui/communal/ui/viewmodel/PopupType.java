package com.android.systemui.communal.ui.viewmodel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PopupType {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CtaTile extends PopupType {
        public static final CtaTile INSTANCE = new CtaTile();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof CtaTile);
        }

        public final int hashCode() {
            return 1618960433;
        }

        public final String toString() {
            return "CtaTile";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CustomizeWidgetButton extends PopupType {
        public static final CustomizeWidgetButton INSTANCE = new CustomizeWidgetButton();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof CustomizeWidgetButton);
        }

        public final int hashCode() {
            return -1708912500;
        }

        public final String toString() {
            return "CustomizeWidgetButton";
        }
    }
}
