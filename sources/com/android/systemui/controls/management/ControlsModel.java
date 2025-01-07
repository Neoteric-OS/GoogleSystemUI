package com.android.systemui.controls.management;

import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ControlsModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ControlsModelCallback {
        void onChange();

        void onFirstChange();
    }

    void changeFavoriteStatus(String str, boolean z);

    List getElements();

    FavoritesModel$moveHelper$1 getMoveHelper();
}
