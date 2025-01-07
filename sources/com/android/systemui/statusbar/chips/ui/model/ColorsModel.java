package com.android.systemui.statusbar.chips.ui.model;

import android.content.Context;
import android.content.res.ColorStateList;
import com.android.settingslib.Utils;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ColorsModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Red implements ColorsModel {
        public static final Red INSTANCE = new Red();

        @Override // com.android.systemui.statusbar.chips.ui.model.ColorsModel
        public final ColorStateList background(Context context) {
            return ColorStateList.valueOf(context.getColor(R.color.GM2_red_700));
        }

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Red);
        }

        public final int hashCode() {
            return 1272928144;
        }

        @Override // com.android.systemui.statusbar.chips.ui.model.ColorsModel
        public final int text(Context context) {
            return context.getColor(android.R.color.white);
        }

        public final String toString() {
            return "Red";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Themed implements ColorsModel {
        public static final Themed INSTANCE = new Themed();

        @Override // com.android.systemui.statusbar.chips.ui.model.ColorsModel
        public final ColorStateList background(Context context) {
            return Utils.getColorAttr(android.R.attr.colorAccent, context);
        }

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Themed);
        }

        public final int hashCode() {
            return 1596248156;
        }

        @Override // com.android.systemui.statusbar.chips.ui.model.ColorsModel
        public final int text(Context context) {
            return Utils.getColorAttrDefaultColor(android.R.attr.colorPrimary, 0, context);
        }

        public final String toString() {
            return "Themed";
        }
    }

    ColorStateList background(Context context);

    int text(Context context);
}
