package com.android.systemui.keyguard.ui.binder;

import android.graphics.Point;
import android.os.VibrationAttributes;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.common.shared.model.NotificationContainerBounds;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.common.ui.view.ViewExtKt$onLayoutChanged$2;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryHapticsInteractor;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.ui.viewmodel.BurnInParameters;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.keyguard.ui.viewmodel.OccludingAppDeviceEntryMessageViewModel;
import com.android.systemui.keyguard.ui.viewmodel.TransitionData;
import com.android.systemui.keyguard.ui.viewmodel.ViewStateAccessor;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator;
import com.android.systemui.util.kotlin.DisposableHandles;
import com.android.wm.shell.R;
import com.google.android.msdl.domain.MSDLPlayer;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardRootViewBinder {
    public static final KeyguardRootViewBinder INSTANCE = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnLayoutChange implements View.OnLayoutChangeListener {
        public final KeyguardBlueprintViewModel blueprintViewModel;
        public final StateFlowImpl burnInParams;
        public final Map childViews;
        public TransitionData prevTransition;
        public final KeyguardRootViewModel viewModel;

        public OnLayoutChange(KeyguardRootViewModel keyguardRootViewModel, KeyguardBlueprintViewModel keyguardBlueprintViewModel, Map map, StateFlowImpl stateFlowImpl) {
            this.viewModel = keyguardRootViewModel;
            this.blueprintViewModel = keyguardBlueprintViewModel;
            this.childViews = map;
            this.burnInParams = stateFlowImpl;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            Object value;
            BurnInParameters burnInParameters;
            int i9;
            Map map = this.childViews;
            KeyguardRootViewBinder keyguardRootViewBinder = KeyguardRootViewBinder.INSTANCE;
            View view2 = (View) map.get(Integer.valueOf(R.id.nssl_placeholder));
            if (view2 != null) {
                TransitionData transitionData = (TransitionData) ((StateFlowImpl) this.blueprintViewModel.currentTransition.$$delegate_0).getValue();
                boolean z = transitionData != null && transitionData.config.type.getAnimateNotifChanges();
                if (Intrinsics.areEqual(this.prevTransition, transitionData) && z) {
                    return;
                }
                this.prevTransition = transitionData;
                KeyguardRootViewModel keyguardRootViewModel = this.viewModel;
                float top = view2.getTop();
                float bottom = view2.getBottom();
                keyguardRootViewModel.getClass();
                NotificationContainerBounds notificationContainerBounds = new NotificationContainerBounds(top, bottom, z);
                StateFlowImpl stateFlowImpl = keyguardRootViewModel.keyguardInteractor._notificationPlaceholderBounds;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, notificationContainerBounds);
            }
            StateFlowImpl stateFlowImpl2 = this.burnInParams;
            do {
                value = stateFlowImpl2.getValue();
                burnInParameters = (BurnInParameters) value;
                i9 = Integer.MAX_VALUE;
                for (Map.Entry entry : this.childViews.entrySet()) {
                    ((Number) entry.getKey()).intValue();
                    View view3 = (View) entry.getValue();
                    int id = view3.getId();
                    KeyguardRootViewBinder keyguardRootViewBinder2 = KeyguardRootViewBinder.INSTANCE;
                    i9 = Math.min(i9, (id == R.id.burn_in_layer || view3.getVisibility() != 0 || view3.getWidth() <= 0 || view3.getHeight() <= 0) ? Integer.MAX_VALUE : view3.getTop());
                }
            } while (!stateFlowImpl2.compareAndSet(value, BurnInParameters.copy$default(burnInParameters, 0, i9, null, 5)));
        }
    }

    static {
        VibrationAttributes.createForUsage(65);
    }

    public static final DisposableHandles bind(final ViewGroup viewGroup, final KeyguardRootViewModel keyguardRootViewModel, KeyguardBlueprintViewModel keyguardBlueprintViewModel, ConfigurationState configurationState, OccludingAppDeviceEntryMessageViewModel occludingAppDeviceEntryMessageViewModel, ChipbarCoordinator chipbarCoordinator, ScreenOffAnimationController screenOffAnimationController, ShadeInteractor shadeInteractor, KeyguardClockInteractor keyguardClockInteractor, KeyguardClockViewModel keyguardClockViewModel, InteractionJankMonitor interactionJankMonitor, DeviceEntryHapticsInteractor deviceEntryHapticsInteractor, VibratorHelper vibratorHelper, final FalsingManager falsingManager, KeyguardViewMediator keyguardViewMediator, final StatusBarKeyguardViewManager statusBarKeyguardViewManager, CoroutineDispatcher coroutineDispatcher, MSDLPlayer mSDLPlayer) {
        Object value;
        DisposableHandles disposableHandles = new DisposableHandles();
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        viewGroup.setOnTouchListener(new View.OnTouchListener(statusBarKeyguardViewManager, keyguardRootViewModel) { // from class: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$1
            public final /* synthetic */ KeyguardRootViewModel $viewModel;

            {
                this.$viewModel = keyguardRootViewModel;
            }

            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                FalsingManager falsingManager2 = FalsingManager.this;
                if (falsingManager2 == null || falsingManager2.isFalseTap(1)) {
                    return false;
                }
                KeyguardRootViewModel keyguardRootViewModel2 = this.$viewModel;
                Point point = new Point((int) motionEvent.getX(), (int) motionEvent.getY());
                StateFlowImpl stateFlowImpl = keyguardRootViewModel2.keyguardInteractor.repository.lastRootViewTapPosition;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, point);
                return false;
            }
        });
        final int i = 0;
        disposableHandles.plusAssign(new DisposableHandle() { // from class: com.android.systemui.common.ui.view.ViewExtKt$onTouchListener$1
            @Override // kotlinx.coroutines.DisposableHandle
            public final void dispose() {
                switch (i) {
                    case 0:
                        viewGroup.setOnTouchListener(null);
                        break;
                    default:
                        viewGroup.setOnApplyWindowInsetsListener(null);
                        break;
                }
            }
        });
        final StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(new BurnInParameters(0, 0, 7));
        ViewStateAccessor viewStateAccessor = new ViewStateAccessor(new Function0() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$viewState$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Float.valueOf(viewGroup.getAlpha());
            }
        });
        disposableHandles.plusAssign(RepeatWhenAttachedKt.repeatWhenAttached(viewGroup, coroutineDispatcher, new KeyguardRootViewBinder$bind$2(viewGroup, keyguardRootViewModel, viewStateAccessor, linkedHashMap, null)));
        disposableHandles.plusAssign(RepeatWhenAttachedKt.repeatWhenAttached(viewGroup, EmptyCoroutineContext.INSTANCE, new KeyguardRootViewBinder$bind$3(viewGroup, interactionJankMonitor, deviceEntryHapticsInteractor, vibratorHelper, occludingAppDeviceEntryMessageViewModel, chipbarCoordinator, keyguardRootViewModel, linkedHashMap, viewStateAccessor, keyguardBlueprintViewModel, configurationState, screenOffAnimationController, keyguardClockInteractor, keyguardViewMediator, shadeInteractor, MutableStateFlow, mSDLPlayer, null)));
        do {
            value = MutableStateFlow.getValue();
        } while (!MutableStateFlow.compareAndSet(value, BurnInParameters.copy$default((BurnInParameters) value, 0, 0, new Function0() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$4$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Map map = linkedHashMap;
                KeyguardRootViewBinder keyguardRootViewBinder = KeyguardRootViewBinder.INSTANCE;
                View view = (View) map.get(Integer.valueOf(R.id.burn_in_layer));
                if (view != null) {
                    return Float.valueOf(view.getTranslationY());
                }
                return null;
            }
        }, 3)));
        OnLayoutChange onLayoutChange = new OnLayoutChange(keyguardRootViewModel, keyguardBlueprintViewModel, linkedHashMap, MutableStateFlow);
        viewGroup.addOnLayoutChangeListener(onLayoutChange);
        disposableHandles.plusAssign(new ViewExtKt$onLayoutChanged$2(viewGroup, onLayoutChange));
        viewGroup.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$5
            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public final void onChildViewAdded(View view, View view2) {
                linkedHashMap.put(Integer.valueOf(view2.getId()), view2);
            }

            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public final void onChildViewRemoved(View view, View view2) {
                linkedHashMap.remove(Integer.valueOf(view2.getId()));
            }
        });
        disposableHandles.plusAssign(new DisposableHandle() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$6
            @Override // kotlinx.coroutines.DisposableHandle
            public final void dispose() {
                viewGroup.setOnHierarchyChangeListener(null);
                linkedHashMap.clear();
            }
        });
        viewGroup.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder$bind$7
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                Object value2;
                int systemBars = WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout();
                StateFlowImpl stateFlowImpl = StateFlowImpl.this;
                do {
                    value2 = stateFlowImpl.getValue();
                } while (!stateFlowImpl.compareAndSet(value2, BurnInParameters.copy$default((BurnInParameters) value2, windowInsets.getInsetsIgnoringVisibility(systemBars).top, 0, null, 6)));
                return windowInsets;
            }
        });
        final int i2 = 1;
        disposableHandles.plusAssign(new DisposableHandle() { // from class: com.android.systemui.common.ui.view.ViewExtKt$onTouchListener$1
            @Override // kotlinx.coroutines.DisposableHandle
            public final void dispose() {
                switch (i2) {
                    case 0:
                        viewGroup.setOnTouchListener(null);
                        break;
                    default:
                        viewGroup.setOnApplyWindowInsetsListener(null);
                        break;
                }
            }
        });
        return disposableHandles;
    }
}
