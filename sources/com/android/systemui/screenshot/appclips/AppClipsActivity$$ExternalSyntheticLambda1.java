package com.android.systemui.screenshot.appclips;

import android.content.pm.PackageManager;
import android.view.View;
import android.widget.ListPopupWindow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class AppClipsActivity$$ExternalSyntheticLambda1 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ AppClipsActivity$$ExternalSyntheticLambda1(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                AppClipsActivity.$r8$lambda$stcjMVf1JwQ0vVO3T941Qunv0VY((AppClipsActivity) obj, view);
                break;
            default:
                PackageManager.ApplicationInfoFlags applicationInfoFlags = AppClipsActivity.APPLICATION_INFO_FLAGS;
                ((ListPopupWindow) obj).show();
                break;
        }
    }
}
