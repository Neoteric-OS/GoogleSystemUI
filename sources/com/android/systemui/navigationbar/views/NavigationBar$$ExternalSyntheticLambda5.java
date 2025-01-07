package com.android.systemui.navigationbar.views;

import android.util.Log;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class NavigationBar$$ExternalSyntheticLambda5 implements Consumer {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ NavigationBar$$ExternalSyntheticLambda5(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                ((CentralSurfacesImpl) ((CentralSurfaces) obj)).awakenDreams();
                break;
            case 1:
                ((CentralSurfacesImpl) ((CentralSurfaces) obj)).checkBarModes$1();
                break;
            case 2:
                Log.d("NavigationBar", "Use duration override: " + ((Long) obj));
                break;
            default:
                Log.d("NavigationBar", "Use slop multiplier override: " + ((Float) obj));
                break;
        }
    }
}
