package com.android.systemui.decor;

import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.DisplayInfo;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl;
import com.android.systemui.log.ScreenDecorationsLogger;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FaceScanningProviderFactory extends DecorProviderFactory {
    public final AuthController authController;
    public final Context context;
    public final Display display;
    public final DisplayInfo displayInfo = new DisplayInfo();
    public final FacePropertyRepositoryImpl facePropertyRepository;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final ScreenDecorationsLogger logger;
    public final Executor mainExecutor;
    public final StatusBarStateController statusBarStateController;

    public FaceScanningProviderFactory(AuthController authController, Context context, StatusBarStateController statusBarStateController, KeyguardUpdateMonitor keyguardUpdateMonitor, Executor executor, ScreenDecorationsLogger screenDecorationsLogger, FacePropertyRepositoryImpl facePropertyRepositoryImpl) {
        this.authController = authController;
        this.context = context;
        this.statusBarStateController = statusBarStateController;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mainExecutor = executor;
        this.logger = screenDecorationsLogger;
        this.facePropertyRepository = facePropertyRepositoryImpl;
        this.display = context.getDisplay();
    }

    @Override // com.android.systemui.decor.DecorProviderFactory
    public final boolean getHasProviders() {
        if (this.facePropertyRepository.sensorLocation.getValue() == null) {
            return false;
        }
        Display display = this.display;
        if (display != null) {
            display.getDisplayInfo(this.displayInfo);
        } else {
            Log.w("FaceScanningProvider", "display is null, can't update displayInfo");
        }
        return DisplayCutout.getFillBuiltInDisplayCutout(this.context.getResources(), this.displayInfo.uniqueId);
    }

    @Override // com.android.systemui.decor.DecorProviderFactory
    public final List getProviders() {
        if (!getHasProviders()) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList();
        DisplayCutout displayCutout = this.displayInfo.displayCutout;
        if (displayCutout != null) {
            Iterator it = FaceScanningProviderFactoryKt.getBoundBaseOnCurrentRotation(displayCutout).iterator();
            while (it.hasNext()) {
                int baseOnRotation0 = FaceScanningProviderFactoryKt.baseOnRotation0(((Number) it.next()).intValue(), this.displayInfo.rotation);
                Executor executor = this.mainExecutor;
                arrayList.add(new FaceScanningOverlayProviderImpl(baseOnRotation0, this.authController, this.statusBarStateController, this.keyguardUpdateMonitor, executor, this.logger, this.facePropertyRepository));
            }
        }
        return arrayList;
    }
}
