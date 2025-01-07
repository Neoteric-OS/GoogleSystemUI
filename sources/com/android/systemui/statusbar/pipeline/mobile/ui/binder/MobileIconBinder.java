package com.android.systemui.statusbar.pipeline.mobile.ui.binder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Space;
import com.android.settingslib.graph.SignalDrawable;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.pipeline.mobile.ui.MobileViewLogger;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.LocationBasedMobileViewModel;
import com.android.systemui.statusbar.pipeline.shared.ui.binder.ModernStatusBarViewBinding;
import com.android.wm.shell.R;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class MobileIconBinder {
    /* JADX WARN: Type inference failed for: r0v31, types: [com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$2] */
    public static final MobileIconBinder$bind$2 bind(ViewGroup viewGroup, final LocationBasedMobileViewModel locationBasedMobileViewModel, int i, MobileViewLogger mobileViewLogger) {
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.requireViewById(R.id.mobile_group);
        View requireViewById = viewGroup.requireViewById(R.id.inout_container);
        ImageView imageView = (ImageView) viewGroup.requireViewById(R.id.mobile_in);
        ImageView imageView2 = (ImageView) viewGroup.requireViewById(R.id.mobile_out);
        ImageView imageView3 = (ImageView) viewGroup.requireViewById(R.id.mobile_type);
        FrameLayout frameLayout = (FrameLayout) viewGroup.requireViewById(R.id.mobile_type_container);
        ImageView imageView4 = (ImageView) viewGroup.requireViewById(R.id.mobile_signal);
        SignalDrawable signalDrawable = new SignalDrawable(viewGroup.getContext());
        ImageView imageView5 = (ImageView) viewGroup.requireViewById(R.id.mobile_roaming);
        Space space = (Space) viewGroup.requireViewById(R.id.mobile_roaming_space);
        StatusBarIconView statusBarIconView = (StatusBarIconView) viewGroup.requireViewById(R.id.status_bar_dot);
        viewGroup.setVisibility(((Boolean) locationBasedMobileViewModel.isVisible().getValue()).booleanValue() ? 0 : 8);
        imageView4.setVisibility(0);
        final StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(Integer.valueOf(i));
        final StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(new Colors(-1, DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT));
        final StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(-1);
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        RepeatWhenAttachedKt.repeatWhenAttached(viewGroup, EmptyCoroutineContext.INSTANCE, new MobileIconBinder$bind$1(requireViewById, viewGroup, viewGroup2, frameLayout, imageView4, imageView3, imageView5, imageView, imageView2, space, signalDrawable, statusBarIconView, mobileViewLogger, locationBasedMobileViewModel, null, ref$BooleanRef, MutableStateFlow, MutableStateFlow2, MutableStateFlow3));
        return new ModernStatusBarViewBinding() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconBinder$bind$2
            @Override // com.android.systemui.statusbar.pipeline.shared.ui.binder.ModernStatusBarViewBinding
            public final boolean getShouldIconBeVisible() {
                return ((Boolean) LocationBasedMobileViewModel.this.isVisible().getValue()).booleanValue();
            }

            @Override // com.android.systemui.statusbar.pipeline.shared.ui.binder.ModernStatusBarViewBinding
            public final boolean isCollecting() {
                return ref$BooleanRef.element;
            }

            @Override // com.android.systemui.statusbar.pipeline.shared.ui.binder.ModernStatusBarViewBinding
            public final void onDecorTintChanged(int i2) {
                MutableStateFlow3.updateState(null, Integer.valueOf(i2));
            }

            @Override // com.android.systemui.statusbar.pipeline.shared.ui.binder.ModernStatusBarViewBinding
            public final void onIconTintChanged(int i2, int i3) {
                MutableStateFlow2.updateState(null, new Colors(i2, i3));
            }

            @Override // com.android.systemui.statusbar.pipeline.shared.ui.binder.ModernStatusBarViewBinding
            public final void onVisibilityStateChanged(int i2) {
                MutableStateFlow.updateState(null, Integer.valueOf(i2));
            }
        };
    }
}
