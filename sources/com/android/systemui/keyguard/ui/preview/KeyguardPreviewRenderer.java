package com.android.systemui.keyguard.ui.preview;

import android.app.WallpaperColors;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.Display;
import android.view.SurfaceControlViewHost;
import android.view.View;
import android.view.WindowManager;
import android.window.InputTransferToken;
import com.android.app.tracing.coroutines.TraceContextElementKt;
import com.android.app.tracing.coroutines.TraceDataThreadLocal;
import com.android.keyguard.ClockEventController;
import com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.communal.ui.viewmodel.CommunalTutorialIndicatorViewModel;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultShortcutsSection;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardBottomAreaViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardPreviewClockViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardPreviewSmartspaceViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.keyguard.ui.viewmodel.OccludingAppDeviceEntryMessageViewModel;
import com.android.systemui.monet.Style;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.systemui.plugins.clocks.WeatherData;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shared.clocks.ClockRegistry;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator;
import com.android.systemui.util.kotlin.DisposableHandles;
import com.android.systemui.util.settings.SecureSettings;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.JobImpl;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardPreviewRenderer {
    public final CoroutineDispatcher backgroundDispatcher;
    public final BroadcastDispatcher broadcastDispatcher;
    public final ClockEventController clockController;
    public final ClockRegistry clockRegistry;
    public final KeyguardPreviewClockViewModel clockViewModel;
    public final CommunalTutorialIndicatorViewModel communalTutorialViewModel;
    public final Context context;
    public final ContextScope coroutineScope;
    public final DefaultShortcutsSection defaultShortcutsSection;
    public final Display display;
    public final DisposableHandles disposables;
    public final int height;
    public SurfaceControlViewHost host;
    public final IBinder hostToken;
    public final Pair id;
    public final KeyguardIndicationController indicationController;
    public boolean isDestroyed;
    public final KeyguardQuickAffordanceViewBinder keyguardQuickAffordanceViewBinder;
    public final LockscreenSmartspaceController lockscreenSmartspaceController;
    public final Handler mainHandler;
    public final KeyguardQuickAffordancesCombinedViewModel quickAffordancesCombinedViewModel;
    public final SecureSettings secureSettings;
    public final Set shortcutsBindings;
    public final boolean shouldHideClock;
    public final boolean shouldHighlightSelectedAffordance;
    public View smartSpaceView;
    public final KeyguardPreviewSmartspaceViewModel smartspaceViewModel;
    public Style themeStyle;
    public final UdfpsOverlayInteractor udfpsOverlayInteractor;
    public final WallpaperColors wallpaperColors;
    public final int width;
    public final WindowManager windowManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$1, reason: invalid class name */
    public final class AnonymousClass1 implements DisposableHandle {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ KeyguardPreviewRenderer this$0;

        public /* synthetic */ AnonymousClass1(KeyguardPreviewRenderer keyguardPreviewRenderer, int i) {
            this.$r8$classId = i;
            this.this$0 = keyguardPreviewRenderer;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            switch (this.$r8$classId) {
                case 0:
                    CoroutineScopeKt.cancel(this.this$0.coroutineScope, null);
                    break;
                default:
                    this.this$0.host.release();
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ DisplayManager $displayManager;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(DisplayManager displayManager, Continuation continuation) {
            super(2, continuation);
            this.$displayManager = displayManager;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return KeyguardPreviewRenderer.this.new AnonymousClass2(this.$displayManager, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass2 anonymousClass2 = (AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass2.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            KeyguardPreviewRenderer.this.host = new SurfaceControlViewHost(KeyguardPreviewRenderer.this.context, this.$displayManager.getDisplay(0), KeyguardPreviewRenderer.this.hostToken == null ? null : new InputTransferToken(KeyguardPreviewRenderer.this.hostToken), "KeyguardPreviewRenderer");
            KeyguardPreviewRenderer keyguardPreviewRenderer = KeyguardPreviewRenderer.this;
            keyguardPreviewRenderer.disposables.plusAssign(new AnonymousClass1(keyguardPreviewRenderer, 1));
            return Unit.INSTANCE;
        }
    }

    public KeyguardPreviewRenderer(Context context, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, Handler handler, CoroutineDispatcher coroutineDispatcher2, KeyguardPreviewClockViewModel keyguardPreviewClockViewModel, KeyguardPreviewSmartspaceViewModel keyguardPreviewSmartspaceViewModel, KeyguardBottomAreaViewModel keyguardBottomAreaViewModel, KeyguardQuickAffordancesCombinedViewModel keyguardQuickAffordancesCombinedViewModel, DisplayManager displayManager, WindowManager windowManager, ConfigurationState configurationState, ClockEventController clockEventController, ClockRegistry clockRegistry, BroadcastDispatcher broadcastDispatcher, LockscreenSmartspaceController lockscreenSmartspaceController, UdfpsOverlayInteractor udfpsOverlayInteractor, KeyguardIndicationController keyguardIndicationController, KeyguardRootViewModel keyguardRootViewModel, KeyguardBlueprintViewModel keyguardBlueprintViewModel, Bundle bundle, OccludingAppDeviceEntryMessageViewModel occludingAppDeviceEntryMessageViewModel, ChipbarCoordinator chipbarCoordinator, ScreenOffAnimationController screenOffAnimationController, ShadeInteractor shadeInteractor, SecureSettings secureSettings, CommunalTutorialIndicatorViewModel communalTutorialIndicatorViewModel, DefaultShortcutsSection defaultShortcutsSection, KeyguardClockInteractor keyguardClockInteractor, KeyguardClockViewModel keyguardClockViewModel, KeyguardQuickAffordanceViewBinder keyguardQuickAffordanceViewBinder) {
        this.context = context;
        this.mainHandler = handler;
        this.backgroundDispatcher = coroutineDispatcher2;
        this.clockViewModel = keyguardPreviewClockViewModel;
        this.smartspaceViewModel = keyguardPreviewSmartspaceViewModel;
        this.quickAffordancesCombinedViewModel = keyguardQuickAffordancesCombinedViewModel;
        this.windowManager = windowManager;
        this.clockController = clockEventController;
        this.clockRegistry = clockRegistry;
        this.broadcastDispatcher = broadcastDispatcher;
        this.lockscreenSmartspaceController = lockscreenSmartspaceController;
        this.udfpsOverlayInteractor = udfpsOverlayInteractor;
        this.indicationController = keyguardIndicationController;
        this.secureSettings = secureSettings;
        this.communalTutorialViewModel = communalTutorialIndicatorViewModel;
        this.defaultShortcutsSection = defaultShortcutsSection;
        this.keyguardQuickAffordanceViewBinder = keyguardQuickAffordanceViewBinder;
        IBinder binder = bundle.getBinder("host_token");
        this.hostToken = binder;
        this.width = bundle.getInt("width");
        this.height = bundle.getInt("height");
        boolean z = bundle.getBoolean("highlight_quick_affordances", false);
        this.shouldHighlightSelectedAffordance = z;
        int i = bundle.getInt("display_id", 0);
        this.display = displayManager.getDisplay(i);
        this.id = new Pair(binder, Integer.valueOf(i));
        this.shouldHideClock = bundle.getBoolean("hide_clock", false);
        this.wallpaperColors = (WallpaperColors) bundle.getParcelable("wallpaper_colors");
        DisposableHandles disposableHandles = new DisposableHandles();
        this.disposables = disposableHandles;
        this.shortcutsBindings = new LinkedHashSet();
        CoroutineContext plus = coroutineScope.getCoroutineContext().plus(new JobImpl(null));
        TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(plus.plus(EmptyCoroutineContext.INSTANCE));
        disposableHandles.plusAssign(new AnonymousClass1(this, 0));
        WeatherData placeholderWeatherData = WeatherData.Companion.getPlaceholderWeatherData();
        if (clockEventController.weatherData == null) {
            clockEventController.weatherData = placeholderWeatherData;
            ClockController clockController = clockEventController.clock;
            if (clockController != null) {
                clockController.getEvents().onWeatherDataChanged(placeholderWeatherData);
            }
        }
        String string = bundle.getString("initially_selected_slot_id");
        keyguardQuickAffordancesCombinedViewModel.enablePreviewMode(string == null ? "bottom_start" : string, z);
        keyguardPreviewClockViewModel.shouldHighlightSelectedAffordance = z;
        BuildersKt.runBlocking(coroutineDispatcher, new AnonymousClass2(displayManager, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$updateClockAppearance(com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer r6, com.android.systemui.plugins.clocks.ClockController r7, kotlin.coroutines.Continuation r8) {
        /*
            r6.getClass()
            boolean r0 = r8 instanceof com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$updateClockAppearance$1
            if (r0 == 0) goto L16
            r0 = r8
            com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$updateClockAppearance$1 r0 = (com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$updateClockAppearance$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$updateClockAppearance$1 r0 = new com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$updateClockAppearance$1
            r0.<init>(r6, r8)
        L1b:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L42
            if (r2 != r3) goto L3a
            java.lang.Object r6 = r0.L$2
            android.app.WallpaperColors r6 = (android.app.WallpaperColors) r6
            java.lang.Object r7 = r0.L$1
            com.android.systemui.plugins.clocks.ClockController r7 = (com.android.systemui.plugins.clocks.ClockController) r7
            java.lang.Object r0 = r0.L$0
            com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer r0 = (com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer) r0
            kotlin.ResultKt.throwOnFailure(r8)
            r5 = r8
            r8 = r6
            r6 = r0
            r0 = r5
            goto L6a
        L3a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L42:
            kotlin.ResultKt.throwOnFailure(r8)
            android.app.WallpaperColors r8 = r6.wallpaperColors
            com.android.systemui.shared.clocks.ClockRegistry r2 = r6.clockRegistry
            com.android.systemui.plugins.clocks.ClockSettings r2 = r2.settings
            if (r2 == 0) goto L52
            java.lang.Integer r2 = r2.getSeedColor()
            goto L53
        L52:
            r2 = 0
        L53:
            if (r2 != 0) goto La4
            if (r8 == 0) goto La4
            com.android.systemui.monet.Style r2 = r6.themeStyle
            if (r2 != 0) goto L6f
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.label = r3
            java.lang.Object r0 = r6.fetchThemeStyleFromSetting(r0)
            if (r0 != r1) goto L6a
            goto Lab
        L6a:
            r2 = r0
            com.android.systemui.monet.Style r2 = (com.android.systemui.monet.Style) r2
            r6.themeStyle = r2
        L6f:
            com.android.systemui.monet.ColorScheme r0 = new com.android.systemui.monet.ColorScheme
            r1 = 0
            r0.<init>(r8, r1, r2)
            com.android.systemui.monet.TonalPalette r2 = r0.mAccent1
            int r2 = r2.getS100()
            com.android.systemui.monet.TonalPalette r0 = r0.mAccent2
            java.util.List r0 = r0.allShades
            r4 = 8
            java.lang.Object r0 = r0.get(r4)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            int r8 = r8.getColorHints()
            r8 = r8 & r3
            if (r8 != 0) goto L93
            goto L94
        L93:
            r3 = r1
        L94:
            com.android.systemui.plugins.clocks.ClockEvents r8 = r7.getEvents()
            if (r3 == 0) goto L9b
            goto L9c
        L9b:
            r2 = r0
        L9c:
            java.lang.Integer r0 = new java.lang.Integer
            r0.<init>(r2)
            r8.onSeedColorChanged(r0)
        La4:
            com.android.keyguard.ClockEventController r6 = r6.clockController
            r6.setClock(r7)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        Lab:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer.access$updateClockAppearance(com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer, com.android.systemui.plugins.clocks.ClockController, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchThemeStyleFromSetting(kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            r6 = this;
            java.lang.String r0 = "Failed to parse THEME_CUSTOMIZATION_OVERLAY_PACKAGES."
            java.lang.String r1 = "KeyguardPreviewRenderer"
            boolean r2 = r7 instanceof com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$fetchThemeStyleFromSetting$1
            if (r2 == 0) goto L17
            r2 = r7
            com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$fetchThemeStyleFromSetting$1 r2 = (com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$fetchThemeStyleFromSetting$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.label = r3
            goto L1c
        L17:
            com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$fetchThemeStyleFromSetting$1 r2 = new com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$fetchThemeStyleFromSetting$1
            r2.<init>(r6, r7)
        L1c:
            java.lang.Object r7 = r2.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r3 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L33
            if (r4 != r5) goto L2b
            kotlin.ResultKt.throwOnFailure(r7)
            goto L47
        L2b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L33:
            kotlin.ResultKt.throwOnFailure(r7)
            com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$fetchThemeStyleFromSetting$overlayPackageJson$1 r7 = new com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer$fetchThemeStyleFromSetting$overlayPackageJson$1
            r4 = 0
            r7.<init>(r6, r4)
            r2.label = r5
            kotlinx.coroutines.CoroutineDispatcher r6 = r6.backgroundDispatcher
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r6, r7, r2)
            if (r7 != r3) goto L47
            return r3
        L47:
            java.lang.String r7 = (java.lang.String) r7
            com.android.systemui.monet.Style r6 = com.android.systemui.monet.Style.TONAL_SPOT
            if (r7 == 0) goto L6f
            int r2 = r7.length()
            if (r2 != 0) goto L54
            goto L6f
        L54:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.IllegalArgumentException -> L64 org.json.JSONException -> L66
            r2.<init>(r7)     // Catch: java.lang.IllegalArgumentException -> L64 org.json.JSONException -> L66
            java.lang.String r7 = "android.theme.customization.theme_style"
            java.lang.String r7 = r2.getString(r7)     // Catch: java.lang.IllegalArgumentException -> L64 org.json.JSONException -> L66
            com.android.systemui.monet.Style r6 = com.android.systemui.monet.Style.valueOf(r7)     // Catch: java.lang.IllegalArgumentException -> L64 org.json.JSONException -> L66
            goto L6f
        L64:
            r7 = move-exception
            goto L68
        L66:
            r7 = move-exception
            goto L6c
        L68:
            android.util.Log.i(r1, r0, r7)
            goto L6f
        L6c:
            android.util.Log.i(r1, r0, r7)
        L6f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.ui.preview.KeyguardPreviewRenderer.fetchThemeStyleFromSetting(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
