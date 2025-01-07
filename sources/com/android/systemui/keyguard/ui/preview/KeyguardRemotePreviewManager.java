package com.android.systemui.keyguard.ui.preview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.ArrayMap;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.DisplayInfo;
import android.view.LayoutInflater;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.app.tracing.coroutines.TraceContextElementKt;
import com.android.app.tracing.coroutines.TraceDataThreadLocal;
import com.android.systemui.animation.view.LaunchableImageView;
import com.android.systemui.biometrics.shared.model.UdfpsOverlayParams;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.communal.ui.binder.CommunalTutorialIndicatorViewBinder;
import com.android.systemui.keyguard.ui.binder.KeyguardPreviewClockViewBinder;
import com.android.systemui.keyguard.ui.binder.KeyguardPreviewSmartspaceViewBinder;
import com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder;
import com.android.systemui.keyguard.ui.view.KeyguardRootView;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultShortcutsSection;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardPreviewSmartspaceViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$80;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardRemotePreviewManager {
    public final ArrayMap activePreviews = new ArrayMap();
    public final CoroutineScope applicationScope;
    public final Handler backgroundHandler;
    public final CoroutineDispatcher mainDispatcher;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$80 previewRendererFactory;

    public KeyguardRemotePreviewManager(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$80 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$80, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, Handler handler) {
        this.previewRendererFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$80;
        this.applicationScope = coroutineScope;
        this.mainDispatcher = coroutineDispatcher;
        this.backgroundHandler = handler;
    }

    public final void destroyObserver(PreviewLifecycleObserver previewLifecycleObserver) {
        Pair pair = null;
        if (!previewLifecycleObserver.isDestroyedOrDestroying) {
            if (Log.isLoggable("KeyguardRemotePreviewManager", 3)) {
                Log.d("KeyguardRemotePreviewManager", "Destroying " + previewLifecycleObserver);
            }
            previewLifecycleObserver.isDestroyedOrDestroying = true;
            KeyguardPreviewRenderer keyguardPreviewRenderer = previewLifecycleObserver.renderer;
            if (keyguardPreviewRenderer != null) {
                previewLifecycleObserver.renderer = null;
                previewLifecycleObserver.onDestroy = null;
                IBinder iBinder = keyguardPreviewRenderer.hostToken;
                if (iBinder != null) {
                    iBinder.unlinkToDeath(previewLifecycleObserver, 0);
                }
                BuildersKt.launch$default(previewLifecycleObserver.scope, previewLifecycleObserver.mainDispatcher, null, new PreviewLifecycleObserver$onDestroy$2$1(keyguardPreviewRenderer, null), 2);
                pair = keyguardPreviewRenderer.id;
            }
        }
        if (pair == null || this.activePreviews.get(pair) != previewLifecycleObserver) {
            return;
        }
        this.activePreviews.remove(pair);
    }

    public final Bundle preview(Bundle bundle) {
        PreviewLifecycleObserver previewLifecycleObserver;
        CoroutineDispatcher coroutineDispatcher = this.mainDispatcher;
        if (bundle == null) {
            return null;
        }
        try {
            KeyguardRemotePreviewManager$preview$renderer$1 keyguardRemotePreviewManager$preview$renderer$1 = new KeyguardRemotePreviewManager$preview$renderer$1(this, bundle, null);
            TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
            EmptyCoroutineContext.INSTANCE.getClass();
            final KeyguardPreviewRenderer keyguardPreviewRenderer = (KeyguardPreviewRenderer) BuildersKt.runBlocking(coroutineDispatcher, keyguardRemotePreviewManager$preview$renderer$1);
            previewLifecycleObserver = new PreviewLifecycleObserver(this.applicationScope, coroutineDispatcher, keyguardPreviewRenderer, new KeyguardRemotePreviewManager$preview$1(1, this, KeyguardRemotePreviewManager.class, "destroyObserver", "destroyObserver(Lcom/android/systemui/keyguard/ui/preview/PreviewLifecycleObserver;)V", 0));
            Pair pair = keyguardPreviewRenderer.id;
            try {
                if (Log.isLoggable("KeyguardRemotePreviewManager", 3)) {
                    Log.d("KeyguardRemotePreviewManager", "Created observer " + previewLifecycleObserver);
                }
                PreviewLifecycleObserver previewLifecycleObserver2 = (PreviewLifecycleObserver) this.activePreviews.get(pair);
                if (previewLifecycleObserver2 != null) {
                    destroyObserver(previewLifecycleObserver2);
                }
                this.activePreviews.put(pair, previewLifecycleObserver);
                keyguardPreviewRenderer.mainHandler.post(new Runnable() { // from class: com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$render$1
                    /* JADX WARN: Type inference failed for: r4v40, types: [android.content.BroadcastReceiver, com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$setUpClock$receiver$1] */
                    @Override // java.lang.Runnable
                    public final void run() {
                        DisplayInfo displayInfo;
                        KeyguardPreviewRenderer keyguardPreviewRenderer2 = KeyguardPreviewRenderer.this;
                        Display display = keyguardPreviewRenderer2.display;
                        Context contextThemeWrapper = display != null ? new ContextThemeWrapper(keyguardPreviewRenderer2.context.createDisplayContext(display), keyguardPreviewRenderer2.context.getTheme()) : keyguardPreviewRenderer2.context;
                        FrameLayout frameLayout = new FrameLayout(contextThemeWrapper);
                        final KeyguardPreviewRenderer keyguardPreviewRenderer3 = KeyguardPreviewRenderer.this;
                        KeyguardRootView keyguardRootView = new KeyguardRootView(contextThemeWrapper, null);
                        frameLayout.addView(keyguardRootView, new FrameLayout.LayoutParams(-1, -1));
                        Rect rect = ((UdfpsOverlayParams) ((StateFlowImpl) keyguardPreviewRenderer3.udfpsOverlayInteractor.udfpsOverlayParams.$$delegate_0).getValue()).sensorBounds;
                        if (!Intrinsics.areEqual(rect, new Rect())) {
                            View inflate = LayoutInflater.from(contextThemeWrapper).inflate(R.layout.udfps_keyguard_preview, (ViewGroup) keyguardRootView, false);
                            inflate.setId(R.id.lock_icon_view);
                            keyguardRootView.addView(inflate);
                            ConstraintSet constraintSet = new ConstraintSet();
                            constraintSet.clone(keyguardRootView);
                            constraintSet.constrainWidth(R.id.lock_icon_view, rect.width());
                            constraintSet.constrainHeight(R.id.lock_icon_view, rect.height());
                            constraintSet.connect(R.id.lock_icon_view, 3, 0, 3, rect.top);
                            constraintSet.connect(R.id.lock_icon_view, 6, 0, 6, rect.left);
                            constraintSet.applyTo(keyguardRootView);
                        }
                        ConstraintSet constraintSet2 = new ConstraintSet();
                        constraintSet2.clone(keyguardRootView);
                        DefaultShortcutsSection defaultShortcutsSection = keyguardPreviewRenderer3.defaultShortcutsSection;
                        defaultShortcutsSection.addViews(keyguardRootView);
                        defaultShortcutsSection.applyConstraints(constraintSet2);
                        constraintSet2.applyTo(keyguardRootView);
                        LaunchableImageView launchableImageView = (LaunchableImageView) keyguardRootView.findViewById(R.id.start_button);
                        Float valueOf = Float.valueOf(1.0f);
                        KeyguardQuickAffordancesCombinedViewModel keyguardQuickAffordancesCombinedViewModel = keyguardPreviewRenderer3.quickAffordancesCombinedViewModel;
                        KeyguardQuickAffordanceViewBinder keyguardQuickAffordanceViewBinder = keyguardPreviewRenderer3.keyguardQuickAffordanceViewBinder;
                        if (launchableImageView != null) {
                            keyguardPreviewRenderer3.shortcutsBindings.add(keyguardQuickAffordanceViewBinder.bind(launchableImageView, keyguardQuickAffordancesCombinedViewModel.startButton, new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(valueOf), new Function1() { // from class: com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$setupShortcuts$1$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    KeyguardPreviewRenderer.this.indicationController.showTransientIndication(((Number) obj).intValue());
                                    return Unit.INSTANCE;
                                }
                            }));
                        }
                        LaunchableImageView launchableImageView2 = (LaunchableImageView) keyguardRootView.findViewById(R.id.end_button);
                        if (launchableImageView2 != null) {
                            keyguardPreviewRenderer3.shortcutsBindings.add(keyguardQuickAffordanceViewBinder.bind(launchableImageView2, keyguardQuickAffordancesCombinedViewModel.endButton, new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(valueOf), new Function1() { // from class: com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$setupShortcuts$2$1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    KeyguardPreviewRenderer.this.indicationController.showTransientIndication(((Number) obj).intValue());
                                    return Unit.INSTANCE;
                                }
                            }));
                        }
                        if (!keyguardPreviewRenderer3.shouldHideClock) {
                            frameLayout.getResources();
                            final ?? r4 = new BroadcastReceiver() { // from class: com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$setUpClock$receiver$1
                                @Override // android.content.BroadcastReceiver
                                public final void onReceive(Context context, Intent intent) {
                                    ClockController clockController = KeyguardPreviewRenderer.this.clockController.clock;
                                    if (clockController != null) {
                                        clockController.getSmallClock().getEvents().onTimeTick();
                                        clockController.getLargeClock().getEvents().onTimeTick();
                                    }
                                }
                            };
                            IntentFilter intentFilter = new IntentFilter();
                            intentFilter.addAction("android.intent.action.TIME_TICK");
                            intentFilter.addAction("android.intent.action.TIME_SET");
                            BroadcastDispatcher.registerReceiver$default(keyguardPreviewRenderer3.broadcastDispatcher, r4, intentFilter, null, null, 0, 60);
                            keyguardPreviewRenderer3.disposables.plusAssign(new DisposableHandle() { // from class: com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$setUpClock$4
                                @Override // kotlinx.coroutines.DisposableHandle
                                public final void dispose() {
                                    KeyguardPreviewRenderer.this.broadcastDispatcher.unregisterReceiver(r4);
                                }
                            });
                            KeyguardPreviewClockViewBinder.bind(contextThemeWrapper, keyguardRootView, keyguardPreviewRenderer3.clockViewModel, keyguardPreviewRenderer3.clockRegistry, new KeyguardPreviewRenderer$setupKeyguardRootView$1(2, keyguardPreviewRenderer3, KeyguardPreviewRenderer.class, "updateClockAppearance", "updateClockAppearance(Lcom/android/systemui/plugins/clocks/ClockController;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0));
                        }
                        LockscreenSmartspaceController lockscreenSmartspaceController = keyguardPreviewRenderer3.lockscreenSmartspaceController;
                        boolean z = lockscreenSmartspaceController.isEnabled;
                        KeyguardPreviewSmartspaceViewModel keyguardPreviewSmartspaceViewModel = keyguardPreviewRenderer3.smartspaceViewModel;
                        int i = keyguardPreviewRenderer3.height;
                        int i2 = keyguardPreviewRenderer3.width;
                        if (z && lockscreenSmartspaceController.isDateWeatherDecoupled) {
                            View view = keyguardPreviewRenderer3.smartSpaceView;
                            if (view != null) {
                                frameLayout.removeView(view);
                            }
                            keyguardPreviewRenderer3.smartSpaceView = lockscreenSmartspaceController.buildAndConnectDateView(frameLayout);
                            int smallClockTopPadding = KeyguardPreviewSmartspaceViewModel.getSmallClockTopPadding(contextThemeWrapper, i2 > i);
                            int dimensionPixelSize = contextThemeWrapper.getResources().getDimensionPixelSize(R.dimen.status_view_margin_horizontal) + contextThemeWrapper.getResources().getDimensionPixelSize(R.dimen.below_clock_padding_start);
                            int dimensionPixelSize2 = contextThemeWrapper.getResources().getDimensionPixelSize(R.dimen.status_view_margin_horizontal) + contextThemeWrapper.getResources().getDimensionPixelSize(R.dimen.below_clock_padding_end);
                            View view2 = keyguardPreviewRenderer3.smartSpaceView;
                            if (view2 != null) {
                                view2.setPaddingRelative(dimensionPixelSize, smallClockTopPadding, dimensionPixelSize2, 0);
                                view2.setClickable(false);
                                view2.setVisibility(4);
                                frameLayout.addView(view2, new FrameLayout.LayoutParams(-1, -2));
                            }
                            View view3 = keyguardPreviewRenderer3.smartSpaceView;
                            if (view3 != null) {
                                view3.setAlpha(keyguardPreviewRenderer3.shouldHighlightSelectedAffordance ? 0.3f : 1.0f);
                            }
                        }
                        View view4 = keyguardPreviewRenderer3.smartSpaceView;
                        if (view4 != null) {
                            KeyguardPreviewSmartspaceViewBinder.bind(contextThemeWrapper, view4, keyguardPreviewSmartspaceViewModel, i2 > i);
                        }
                        TextView textView = (TextView) keyguardRootView.findViewById(R.id.communal_tutorial_indicator);
                        if (textView != null) {
                            CommunalTutorialIndicatorViewBinder.bind(textView, keyguardPreviewRenderer3.communalTutorialViewModel, true);
                        }
                        Display display2 = KeyguardPreviewRenderer.this.display;
                        if (display2 != null) {
                            displayInfo = new DisplayInfo();
                            display2.getDisplayInfo(displayInfo);
                        } else {
                            displayInfo = null;
                        }
                        frameLayout.measure(View.MeasureSpec.makeMeasureSpec(displayInfo != null ? displayInfo.logicalWidth : KeyguardPreviewRenderer.this.windowManager.getCurrentWindowMetrics().getBounds().width(), 1073741824), View.MeasureSpec.makeMeasureSpec(displayInfo != null ? displayInfo.logicalHeight : KeyguardPreviewRenderer.this.windowManager.getCurrentWindowMetrics().getBounds().height(), 1073741824));
                        frameLayout.layout(0, 0, frameLayout.getMeasuredWidth(), frameLayout.getMeasuredHeight());
                        float coerceAtMost = RangesKt.coerceAtMost(KeyguardPreviewRenderer.this.width / frameLayout.getMeasuredWidth(), KeyguardPreviewRenderer.this.height / frameLayout.getMeasuredHeight());
                        frameLayout.setScaleX(coerceAtMost);
                        frameLayout.setScaleY(coerceAtMost);
                        frameLayout.setPivotX(0.0f);
                        frameLayout.setPivotY(0.0f);
                        float f = 2;
                        frameLayout.setTranslationX((KeyguardPreviewRenderer.this.width - (frameLayout.getWidth() * coerceAtMost)) / f);
                        frameLayout.setTranslationY((KeyguardPreviewRenderer.this.height - (coerceAtMost * frameLayout.getHeight())) / f);
                        KeyguardPreviewRenderer keyguardPreviewRenderer4 = KeyguardPreviewRenderer.this;
                        if (keyguardPreviewRenderer4.isDestroyed) {
                            return;
                        }
                        keyguardPreviewRenderer4.host.setView(frameLayout, frameLayout.getMeasuredWidth(), frameLayout.getMeasuredHeight());
                    }
                });
                IBinder iBinder = keyguardPreviewRenderer.hostToken;
                if (iBinder != null) {
                    iBinder.linkToDeath(previewLifecycleObserver, 0);
                }
                Bundle bundle2 = new Bundle();
                SurfaceControlViewHost.SurfacePackage surfacePackage = keyguardPreviewRenderer.host.getSurfacePackage();
                if (surfacePackage == null) {
                    throw new IllegalStateException("Required value was null.");
                }
                bundle2.putParcelable("surface_package", surfacePackage);
                Messenger messenger = new Messenger(new Handler(this.backgroundHandler.getLooper(), previewLifecycleObserver));
                Message obtain = Message.obtain();
                obtain.replyTo = messenger;
                bundle2.putParcelable("callback", obtain);
                return bundle2;
            } catch (Exception e) {
                e = e;
                Log.e("KeyguardRemotePreviewManager", "Unable to generate preview", e);
                if (previewLifecycleObserver == null) {
                    return null;
                }
                destroyObserver(previewLifecycleObserver);
                return null;
            }
        } catch (Exception e2) {
            e = e2;
            previewLifecycleObserver = null;
        }
    }
}
