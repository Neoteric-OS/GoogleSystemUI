package com.android.systemui.keyguard.ui.binder;

import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardIndicationAreaViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.util.kotlin.DisposableHandles;
import com.android.wm.shell.R;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardIndicationAreaBinder {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ConfigurationBasedDimensions {
        public final int defaultBurnInPreventionYOffsetPx;
        public final int indicationAreaPaddingPx;
        public final int indicationTextSizePx;

        public ConfigurationBasedDimensions(int i, int i2, int i3) {
            this.defaultBurnInPreventionYOffsetPx = i;
            this.indicationAreaPaddingPx = i2;
            this.indicationTextSizePx = i3;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ConfigurationBasedDimensions)) {
                return false;
            }
            ConfigurationBasedDimensions configurationBasedDimensions = (ConfigurationBasedDimensions) obj;
            return this.defaultBurnInPreventionYOffsetPx == configurationBasedDimensions.defaultBurnInPreventionYOffsetPx && this.indicationAreaPaddingPx == configurationBasedDimensions.indicationAreaPaddingPx && this.indicationTextSizePx == configurationBasedDimensions.indicationTextSizePx;
        }

        public final int hashCode() {
            return Integer.hashCode(this.indicationTextSizePx) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.indicationAreaPaddingPx, Integer.hashCode(this.defaultBurnInPreventionYOffsetPx) * 31, 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("ConfigurationBasedDimensions(defaultBurnInPreventionYOffsetPx=");
            sb.append(this.defaultBurnInPreventionYOffsetPx);
            sb.append(", indicationAreaPaddingPx=");
            sb.append(this.indicationAreaPaddingPx);
            sb.append(", indicationTextSizePx=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.indicationTextSizePx, ")");
        }
    }

    public static final DisposableHandles bind(ViewGroup viewGroup, KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel, final KeyguardIndicationController keyguardIndicationController) {
        DisposableHandles disposableHandles = new DisposableHandles();
        final ViewGroup viewGroup2 = keyguardIndicationController.mIndicationArea;
        keyguardIndicationController.setIndicationArea(viewGroup);
        disposableHandles.plusAssign(new DisposableHandle() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardIndicationAreaBinder$bind$1
            @Override // kotlinx.coroutines.DisposableHandle
            public final void dispose() {
                ViewGroup viewGroup3 = viewGroup2;
                if (viewGroup3 != null) {
                    keyguardIndicationController.setIndicationArea(viewGroup3);
                }
            }
        });
        TextView textView = (TextView) viewGroup.requireViewById(R.id.keyguard_indication_text);
        TextView textView2 = (TextView) viewGroup.requireViewById(R.id.keyguard_indication_text_bottom);
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
        disposableHandles.plusAssign(RepeatWhenAttachedKt.repeatWhenAttached(viewGroup, EmptyCoroutineContext.INSTANCE, new KeyguardIndicationAreaBinder$bind$2(keyguardIndicationAreaViewModel, viewGroup, StateFlowKt.MutableStateFlow(new ConfigurationBasedDimensions(viewGroup.getResources().getDimensionPixelOffset(R.dimen.default_burn_in_prevention_offset), viewGroup.getResources().getDimensionPixelOffset(R.dimen.keyguard_indication_area_padding), viewGroup.getResources().getDimensionPixelSize(android.R.dimen.timepicker_center_dot_radius))), textView, textView2, keyguardIndicationController, null)));
        return disposableHandles;
    }
}
