package com.android.systemui.biometrics.ui.viewmodel;

import android.content.Context;
import android.view.WindowManager;
import com.android.systemui.biometrics.domain.interactor.DisplayStateInteractor;
import com.android.systemui.biometrics.domain.interactor.DisplayStateInteractorImpl;
import com.android.systemui.biometrics.domain.interactor.SideFpsSensorInteractor;
import com.android.systemui.keyguard.domain.interactor.DeviceEntrySideFpsOverlayInteractor;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SideFpsOverlayViewModel {
    public final StateFlowImpl _lottieBounds;
    public final Context applicationContext;
    public final ReadonlyStateFlow displayRotation;
    public final SafeFlow lottieCallbacks;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 overlayViewParams;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 overlayViewProperties;
    public final FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 sensorLocation;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OverlayViewProperties {
        public final int indicatorAsset;
        public final float overlayViewRotation;

        public OverlayViewProperties(int i, float f) {
            this.indicatorAsset = i;
            this.overlayViewRotation = f;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof OverlayViewProperties)) {
                return false;
            }
            OverlayViewProperties overlayViewProperties = (OverlayViewProperties) obj;
            return this.indicatorAsset == overlayViewProperties.indicatorAsset && Float.compare(this.overlayViewRotation, overlayViewProperties.overlayViewRotation) == 0;
        }

        public final int hashCode() {
            return Float.hashCode(this.overlayViewRotation) + (Integer.hashCode(this.indicatorAsset) * 31);
        }

        public final String toString() {
            return "OverlayViewProperties(indicatorAsset=" + this.indicatorAsset + ", overlayViewRotation=" + this.overlayViewRotation + ")";
        }
    }

    public SideFpsOverlayViewModel(Context context, DeviceEntrySideFpsOverlayInteractor deviceEntrySideFpsOverlayInteractor, DisplayStateInteractor displayStateInteractor, SideFpsSensorInteractor sideFpsSensorInteractor) {
        this.applicationContext = context;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._lottieBounds = MutableStateFlow;
        ReadonlyStateFlow readonlyStateFlow = ((DisplayStateInteractorImpl) displayStateInteractor).currentRotation;
        FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1 = sideFpsSensorInteractor.sensorLocation;
        this.overlayViewProperties = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(readonlyStateFlow, flowKt__TransformKt$onEach$$inlined$unsafeTransform$1, new SideFpsOverlayViewModel$indicatorAsset$1(3, null))), FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(readonlyStateFlow, flowKt__TransformKt$onEach$$inlined$unsafeTransform$1, new SideFpsOverlayViewModel$overlayViewRotation$1(3, null))), new SideFpsOverlayViewModel$overlayViewProperties$1(3, null));
        this.overlayViewParams = FlowKt.combine(MutableStateFlow, flowKt__TransformKt$onEach$$inlined$unsafeTransform$1, readonlyStateFlow, new SideFpsOverlayViewModel$overlayViewParams$1(this, null));
        this.lottieCallbacks = com.android.systemui.util.kotlin.FlowKt.sample(MutableStateFlow, deviceEntrySideFpsOverlayInteractor.showIndicatorForDeviceEntry, new SideFpsOverlayViewModel$lottieCallbacks$1(this, null));
    }

    public static WindowManager.LayoutParams getDefaultOverlayViewParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-2, -2, 2024, 16777512, -3);
        layoutParams.setTitle("SideFpsOverlayViewModel");
        layoutParams.setFitInsetsTypes(0);
        layoutParams.gravity = 51;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.privateFlags = 536870976;
        return layoutParams;
    }
}
