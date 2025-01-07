package com.android.systemui.keyguard.ui.viewmodel;

import android.content.Context;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryUdfpsInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.ui.view.DeviceEntryIconView;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntryForegroundViewModel {
    public final Context context;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isShowingAodOrDozing;
    public final ChannelFlowTransformLatest padding;
    public final ChannelFlowTransformLatest useAodIconVariant;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 viewModel;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ForegroundIconViewModel {
        public final int padding;
        public final int tint;
        public final DeviceEntryIconView.IconType type;
        public final boolean useAodVariant;

        public ForegroundIconViewModel(DeviceEntryIconView.IconType iconType, boolean z, int i, int i2) {
            this.type = iconType;
            this.useAodVariant = z;
            this.tint = i;
            this.padding = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ForegroundIconViewModel)) {
                return false;
            }
            ForegroundIconViewModel foregroundIconViewModel = (ForegroundIconViewModel) obj;
            return this.type == foregroundIconViewModel.type && this.useAodVariant == foregroundIconViewModel.useAodVariant && this.tint == foregroundIconViewModel.tint && this.padding == foregroundIconViewModel.padding;
        }

        public final int hashCode() {
            return Integer.hashCode(this.padding) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.tint, TransitionData$$ExternalSyntheticOutline0.m(this.type.hashCode() * 31, 31, this.useAodVariant), 31);
        }

        public final String toString() {
            return "ForegroundIconViewModel(type=" + this.type + ", useAodVariant=" + this.useAodVariant + ", tint=" + this.tint + ", padding=" + this.padding + ")";
        }
    }

    public DeviceEntryForegroundViewModel(Context context, ConfigurationInteractor configurationInteractor, DeviceEntryUdfpsInteractor deviceEntryUdfpsInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, DeviceEntryIconViewModel deviceEntryIconViewModel, UdfpsOverlayInteractor udfpsOverlayInteractor) {
        this.context = context;
        this.isShowingAodOrDozing = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(keyguardTransitionInteractor.startedKeyguardTransitionStep, keyguardTransitionInteractor.getTransitionValueFlow(KeyguardState.DOZING), new DeviceEntryForegroundViewModel$isShowingAodOrDozing$1(3, null));
        ChannelFlowTransformLatest transformLatest = FlowKt.transformLatest(deviceEntryUdfpsInteractor.isUdfpsEnrolledAndEnabled, new DeviceEntryForegroundViewModel$special$$inlined$flatMapLatest$1(null, this));
        this.viewModel = FlowKt.combine(deviceEntryIconViewModel.iconType, transformLatest, FlowKt.distinctUntilChanged(FlowKt.transformLatest(transformLatest, new DeviceEntryForegroundViewModel$special$$inlined$flatMapLatest$2(null, deviceEntryIconViewModel, configurationInteractor, this))), FlowKt.transformLatest(deviceEntryUdfpsInteractor.isUdfpsSupported, new DeviceEntryForegroundViewModel$special$$inlined$flatMapLatest$3(null, udfpsOverlayInteractor, configurationInteractor, this)), new DeviceEntryForegroundViewModel$viewModel$1(5, null));
    }
}
