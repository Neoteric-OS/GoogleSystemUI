package com.android.systemui.navigationbar.gestural;

import com.android.internal.view.AppearanceRegion;
import com.android.systemui.statusbar.phone.LightBarController;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class EdgeBackGestureHandler$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ EdgeBackGestureHandler f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ EdgeBackGestureHandler$$ExternalSyntheticLambda6(EdgeBackGestureHandler edgeBackGestureHandler, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = edgeBackGestureHandler;
        this.f$1 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                EdgeBackGestureHandler edgeBackGestureHandler = this.f$0;
                Executor executor = (Executor) this.f$1;
                edgeBackGestureHandler.getClass();
                executor.execute(new EdgeBackGestureHandler$$ExternalSyntheticLambda3(edgeBackGestureHandler, 1));
                break;
            default:
                EdgeBackGestureHandler edgeBackGestureHandler2 = this.f$0;
                AppearanceRegion appearanceRegion = (AppearanceRegion) this.f$1;
                LightBarController lightBarController = (LightBarController) edgeBackGestureHandler2.mLightBarControllerProvider.get();
                int i = 0;
                if (appearanceRegion == null) {
                    lightBarController.mIsCustomizingForBackNav = false;
                    lightBarController.updateStatus(lightBarController.mAppearanceRegions);
                    break;
                } else {
                    lightBarController.getClass();
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(appearanceRegion);
                    while (true) {
                        AppearanceRegion[] appearanceRegionArr = lightBarController.mAppearanceRegions;
                        if (i >= appearanceRegionArr.length) {
                            lightBarController.updateStatus((AppearanceRegion[]) arrayList.toArray(new AppearanceRegion[arrayList.size()]));
                            lightBarController.mIsCustomizingForBackNav = true;
                            break;
                        } else {
                            AppearanceRegion appearanceRegion2 = appearanceRegionArr[i];
                            if (!appearanceRegion.getBounds().contains(appearanceRegion2.getBounds())) {
                                arrayList.add(appearanceRegion2);
                            }
                            i++;
                        }
                    }
                }
        }
    }
}
