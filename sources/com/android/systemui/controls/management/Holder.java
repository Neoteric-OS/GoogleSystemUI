package com.android.systemui.controls.management;

import androidx.recyclerview.widget.RecyclerView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Holder extends RecyclerView.ViewHolder {
    public abstract void bindData(ElementWrapper elementWrapper);

    public void updateFavorite(boolean z) {
    }
}
