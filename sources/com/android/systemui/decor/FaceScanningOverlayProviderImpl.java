package com.android.systemui.decor;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.FaceScanningOverlay;
import com.android.systemui.RegionInterceptingFrameLayout;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl;
import com.android.systemui.log.ScreenDecorationsLogger;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.wm.shell.R;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FaceScanningOverlayProviderImpl extends BoundDecorProvider {
    public final int alignedBound;
    public final AuthController authController;
    public final FacePropertyRepositoryImpl facePropertyRepository;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final ScreenDecorationsLogger logger;
    public final Executor mainExecutor;
    public final StatusBarStateController statusBarStateController;
    public final int viewId = R.id.face_scanning_anim;

    public FaceScanningOverlayProviderImpl(int i, AuthController authController, StatusBarStateController statusBarStateController, KeyguardUpdateMonitor keyguardUpdateMonitor, Executor executor, ScreenDecorationsLogger screenDecorationsLogger, FacePropertyRepositoryImpl facePropertyRepositoryImpl) {
        this.alignedBound = i;
        this.authController = authController;
        this.statusBarStateController = statusBarStateController;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.logger = screenDecorationsLogger;
        this.facePropertyRepository = facePropertyRepositoryImpl;
    }

    @Override // com.android.systemui.decor.BoundDecorProvider
    public final int getAlignedBound() {
        return this.alignedBound;
    }

    @Override // com.android.systemui.decor.DecorProvider
    public final int getViewId() {
        return this.viewId;
    }

    @Override // com.android.systemui.decor.DecorProvider
    public final View inflateView(Context context, RegionInterceptingFrameLayout regionInterceptingFrameLayout, int i, int i2) {
        FaceScanningOverlay faceScanningOverlay = new FaceScanningOverlay(context, this.alignedBound, this.statusBarStateController, this.keyguardUpdateMonitor, this.logger, this.authController);
        faceScanningOverlay.setId(this.viewId);
        faceScanningOverlay.setColor$1(i2);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        updateLayoutParams(layoutParams, i);
        regionInterceptingFrameLayout.addView(faceScanningOverlay, layoutParams);
        return faceScanningOverlay;
    }

    @Override // com.android.systemui.decor.DecorProvider
    public final void onReloadResAndMeasure(View view, int i, int i2, int i3, String str) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        updateLayoutParams(layoutParams, i2);
        view.setLayoutParams(layoutParams);
        FaceScanningOverlay faceScanningOverlay = view instanceof FaceScanningOverlay ? (FaceScanningOverlay) view : null;
        if (faceScanningOverlay != null) {
            faceScanningOverlay.setColor$1(i3);
            faceScanningOverlay.updateConfiguration(str);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x002a, code lost:
    
        if (r6 != 3) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateLayoutParams(android.widget.FrameLayout.LayoutParams r5, int r6) {
        /*
            r4 = this;
            r0 = -1
            r5.width = r0
            r5.height = r0
            com.android.systemui.biometrics.data.repository.FacePropertyRepositoryImpl r1 = r4.facePropertyRepository
            kotlinx.coroutines.flow.ReadonlyStateFlow r2 = r1.sensorLocation
            java.lang.Object r2 = r2.getValue()
            android.graphics.Point r2 = (android.graphics.Point) r2
            com.android.systemui.log.ScreenDecorationsLogger r4 = r4.logger
            r4.faceSensorLocation(r2)
            kotlinx.coroutines.flow.ReadonlyStateFlow r4 = r1.sensorLocation
            java.lang.Object r4 = r4.getValue()
            android.graphics.Point r4 = (android.graphics.Point) r4
            r1 = 3
            r2 = 1
            r3 = 2
            if (r4 == 0) goto L32
            int r4 = r4.y
            int r4 = r4 * r3
            if (r6 == 0) goto L30
            if (r6 == r2) goto L2d
            if (r6 == r3) goto L30
            if (r6 == r1) goto L2d
            goto L32
        L2d:
            r5.width = r4
            goto L32
        L30:
            r5.height = r4
        L32:
            if (r6 == 0) goto L47
            if (r6 == r2) goto L43
            if (r6 == r3) goto L3f
            if (r6 == r1) goto L3b
            goto L4a
        L3b:
            r0 = 8388613(0x800005, float:1.175495E-38)
            goto L4a
        L3f:
            r0 = 8388693(0x800055, float:1.1755063E-38)
            goto L4a
        L43:
            r0 = 8388611(0x800003, float:1.1754948E-38)
            goto L4a
        L47:
            r0 = 8388659(0x800033, float:1.1755015E-38)
        L4a:
            r5.gravity = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.decor.FaceScanningOverlayProviderImpl.updateLayoutParams(android.widget.FrameLayout$LayoutParams, int):void");
    }
}
