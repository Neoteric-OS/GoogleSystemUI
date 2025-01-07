package com.android.systemui.theme;

import android.R;
import android.app.ActivityManager;
import android.app.UiModeManager;
import android.app.WallpaperColors;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.om.FabricatedOverlay;
import android.content.om.OverlayIdentifier;
import android.content.om.OverlayInfo;
import android.content.om.OverlayManagerTransaction;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Color;
import android.os.Handler;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.internal.graphics.ColorUtils;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.systemui.CoreStartable;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$isFinishedIn$$inlined$map$2;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.monet.ColorScheme;
import com.android.systemui.monet.CustomDynamicColors;
import com.android.systemui.monet.CustomDynamicColors$$ExternalSyntheticLambda0;
import com.android.systemui.monet.CustomDynamicColors$$ExternalSyntheticLambda2;
import com.android.systemui.monet.CustomDynamicColors$$ExternalSyntheticLambda5;
import com.android.systemui.monet.Style;
import com.android.systemui.monet.TonalPalette;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SecureSettingsImpl;
import com.google.ux.material.libmonet.dynamiccolor.ContrastCurve;
import com.google.ux.material.libmonet.dynamiccolor.DynamicColor;
import com.google.ux.material.libmonet.dynamiccolor.MaterialDynamicColors;
import com.google.ux.material.libmonet.dynamiccolor.MaterialDynamicColors$$ExternalSyntheticLambda0;
import com.google.ux.material.libmonet.dynamiccolor.MaterialDynamicColors$$ExternalSyntheticLambda1;
import com.google.ux.material.libmonet.dynamiccolor.MaterialDynamicColors$$ExternalSyntheticLambda4;
import com.google.ux.material.libmonet.dynamiccolor.MaterialDynamicColors$$ExternalSyntheticLambda5;
import com.google.ux.material.libmonet.dynamiccolor.MaterialDynamicColors$$ExternalSyntheticLambda7;
import com.google.ux.material.libmonet.dynamiccolor.MaterialDynamicColors$$ExternalSyntheticLambda9;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ThemeOverlayController implements CoreStartable, Dumpable {
    public final ActivityManager mActivityManager;
    public final Executor mBgExecutor;
    public final Handler mBgHandler;
    public final BroadcastDispatcher mBroadcastDispatcher;
    protected ColorScheme mColorScheme;
    public final Context mContext;
    public ColorScheme mDarkColorScheme;
    public boolean mDeferredThemeEvaluation;
    public final DeviceProvisionedController mDeviceProvisionedController;
    public FabricatedOverlay mDynamicOverlay;
    public final ReadonlyStateFlow mIsKeyguardOnAsleepState;
    public final boolean mIsMonetEnabled;
    public ColorScheme mLightColorScheme;
    public final Executor mMainExecutor;
    public boolean mNeedsOverlayCreation;
    public FabricatedOverlay mNeutralOverlay;
    public final Resources mResources;
    public FabricatedOverlay mSecondaryOverlay;
    public final SecureSettings mSecureSettings;
    public boolean mSkipSettingChange;
    public final ThemeOverlayApplier mThemeManager;
    public final UiModeManager mUiModeManager;
    public final UserManager mUserManager;
    public final UserTracker mUserTracker;
    public final WakefulnessLifecycle mWakefulnessLifecycle;
    public final WallpaperManager mWallpaperManager;
    public final SparseArray mCurrentColors = new SparseArray();
    public int mMainWallpaperColor = 0;
    public double mContrast = 0.0d;
    protected Style mThemeStyle = Style.TONAL_SPOT;
    public boolean mAcceptColorEvents = true;
    public final SparseArray mDeferredWallpaperColors = new SparseArray();
    public final SparseIntArray mDeferredWallpaperColorsFlags = new SparseIntArray();
    public final AnonymousClass1 mDeviceProvisionedListener = new DeviceProvisionedController.DeviceProvisionedListener() { // from class: com.android.systemui.theme.ThemeOverlayController.1
        @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
        public final void onUserSetupChanged() {
            ThemeOverlayController themeOverlayController = ThemeOverlayController.this;
            if (((DeviceProvisionedControllerImpl) themeOverlayController.mDeviceProvisionedController).isCurrentUserSetup() && themeOverlayController.mDeferredThemeEvaluation) {
                Log.i("ThemeOverlayController", "Applying deferred theme");
                themeOverlayController.mDeferredThemeEvaluation = false;
                themeOverlayController.reevaluateSystemTheme(true);
            }
        }
    };
    public final AnonymousClass2 mOnColorsChangedListener = new WallpaperManager.OnColorsChangedListener() { // from class: com.android.systemui.theme.ThemeOverlayController.2
        @Override // android.app.WallpaperManager.OnColorsChangedListener
        public final void onColorsChanged(WallpaperColors wallpaperColors, int i) {
            throw new IllegalStateException("This should never be invoked, all messages should arrive on the overload that has a user id");
        }

        public final void onColorsChanged(WallpaperColors wallpaperColors, int i, int i2) {
            WallpaperColors wallpaperColors2 = (WallpaperColors) ThemeOverlayController.this.mCurrentColors.get(i2);
            if (wallpaperColors == null || !wallpaperColors.equals(wallpaperColors2)) {
                boolean z = i2 == ((UserTrackerImpl) ThemeOverlayController.this.mUserTracker).getUserId();
                ThemeOverlayController themeOverlayController = ThemeOverlayController.this;
                boolean z2 = themeOverlayController.mWakefulnessLifecycle.mWakefulness != 0;
                if (!z || themeOverlayController.mAcceptColorEvents || !z2) {
                    if (z && wallpaperColors != null) {
                        themeOverlayController.mAcceptColorEvents = false;
                        themeOverlayController.mDeferredWallpaperColors.put(i2, null);
                        ThemeOverlayController.this.mDeferredWallpaperColorsFlags.put(i2, 0);
                    }
                    ThemeOverlayController.this.handleWallpaperColors(wallpaperColors, i, i2);
                    return;
                }
                themeOverlayController.mDeferredWallpaperColors.put(i2, wallpaperColors);
                ThemeOverlayController.this.mDeferredWallpaperColorsFlags.put(i2, i);
                Log.i("ThemeOverlayController", "colors received; processing deferred until screen off: " + wallpaperColors + " user: " + i2);
            }
        }
    };
    public final UserTracker.Callback mUserTrackerCallback = new UserTracker.Callback() { // from class: com.android.systemui.theme.ThemeOverlayController.3
        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            ThemeOverlayController themeOverlayController = ThemeOverlayController.this;
            boolean isManagedProfile = themeOverlayController.mUserManager.isManagedProfile(i);
            if (((DeviceProvisionedControllerImpl) themeOverlayController.mDeviceProvisionedController).isCurrentUserSetup() || !isManagedProfile) {
                Log.d("ThemeOverlayController", "Updating overlays for user switch / profile added.");
                themeOverlayController.reevaluateSystemTheme(true);
            } else {
                Log.i("ThemeOverlayController", "User setup not finished when new user event was received. Deferring... Managed profile? " + isManagedProfile);
            }
        }
    };
    public final AnonymousClass4 mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.theme.ThemeOverlayController.4
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (!"android.intent.action.PROFILE_ADDED".equals(intent.getAction())) {
                if ("android.intent.action.WALLPAPER_CHANGED".equals(intent.getAction())) {
                    if (intent.getBooleanExtra("android.service.wallpaper.extra.FROM_FOREGROUND_APP", false)) {
                        ThemeOverlayController.this.mAcceptColorEvents = true;
                        Log.i("ThemeOverlayController", "Wallpaper changed, allowing color events again");
                        return;
                    } else {
                        Log.i("ThemeOverlayController", "Wallpaper changed from background app, keep deferring color events. Accepting: " + ThemeOverlayController.this.mAcceptColorEvents);
                        return;
                    }
                }
                return;
            }
            UserHandle userHandle = (UserHandle) intent.getParcelableExtra("android.intent.extra.USER", UserHandle.class);
            boolean isManagedProfile = ThemeOverlayController.this.mUserManager.isManagedProfile(userHandle.getIdentifier());
            if (!((DeviceProvisionedControllerImpl) ThemeOverlayController.this.mDeviceProvisionedController).isUserSetup(userHandle.getIdentifier()) && isManagedProfile) {
                Log.i("ThemeOverlayController", "User setup not finished when " + intent.getAction() + " was received. Deferring... Managed profile? " + isManagedProfile);
                return;
            }
            if (ThemeOverlayController.this.isPrivateProfile(userHandle)) {
                ThemeOverlayController.this.mDeferredThemeEvaluation = true;
                Log.i("ThemeOverlayController", "Deferring theme for private profile till user setup is complete");
            } else {
                Log.d("ThemeOverlayController", "Updating overlays for user switch / profile added.");
                ThemeOverlayController.this.reevaluateSystemTheme(true);
            }
        }
    };

    /* JADX WARN: Type inference failed for: r1v10, types: [com.android.systemui.theme.ThemeOverlayController$4] */
    /* JADX WARN: Type inference failed for: r1v7, types: [com.android.systemui.theme.ThemeOverlayController$1] */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.android.systemui.theme.ThemeOverlayController$2] */
    public ThemeOverlayController(Context context, BroadcastDispatcher broadcastDispatcher, Handler handler, Executor executor, Executor executor2, ThemeOverlayApplier themeOverlayApplier, SecureSettings secureSettings, WallpaperManager wallpaperManager, UserManager userManager, DeviceProvisionedController deviceProvisionedController, UserTracker userTracker, DumpManager dumpManager, FeatureFlags featureFlags, Resources resources, WakefulnessLifecycle wakefulnessLifecycle, JavaAdapter javaAdapter, KeyguardTransitionInteractor keyguardTransitionInteractor, UiModeManager uiModeManager, ActivityManager activityManager) {
        this.mContext = context;
        this.mIsMonetEnabled = ((FeatureFlagsClassicRelease) featureFlags).isEnabled(Flags.MONET);
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mUserManager = userManager;
        this.mBgExecutor = executor2;
        this.mMainExecutor = executor;
        this.mBgHandler = handler;
        this.mThemeManager = themeOverlayApplier;
        this.mSecureSettings = secureSettings;
        this.mWallpaperManager = wallpaperManager;
        this.mUserTracker = userTracker;
        this.mResources = resources;
        this.mWakefulnessLifecycle = wakefulnessLifecycle;
        this.mUiModeManager = uiModeManager;
        this.mActivityManager = activityManager;
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "ThemeOverlayController", this);
        final KeyguardState.Companion companion = KeyguardState.Companion;
        Objects.requireNonNull(companion);
        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(new KeyguardTransitionInteractor$isFinishedIn$$inlined$map$2(keyguardTransitionInteractor.finishedKeyguardState, new Function1() { // from class: com.android.systemui.theme.ThemeOverlayController$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyguardState.Companion.this.getClass();
                return Boolean.valueOf(KeyguardState.Companion.deviceIsAsleepInState((KeyguardState) obj));
            }
        }, 1));
        Boolean bool = Boolean.FALSE;
        javaAdapter.getClass();
        FlowKt.stateIn(distinctUntilChanged, javaAdapter.scope, SharingStarted.Companion.Eagerly, bool);
    }

    public static void assignTonalPaletteToOverlay(String str, final FabricatedOverlay fabricatedOverlay, TonalPalette tonalPalette) {
        final String concat = "android:color/system_".concat(str);
        tonalPalette.allShadesMapped.forEach(new BiConsumer() { // from class: com.android.systemui.theme.ThemeOverlayController$$ExternalSyntheticLambda6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                String str2 = concat;
                fabricatedOverlay.setResourceValue(str2 + "_" + ((Integer) obj), 28, ColorUtils.setAlphaComponent(((Integer) obj2).intValue(), 255), (String) null);
            }
        });
    }

    public static boolean isSeedColorSet(JSONObject jSONObject, WallpaperColors wallpaperColors) {
        String str;
        if (wallpaperColors == null || (str = (String) jSONObject.opt("android.theme.customization.system_palette")) == null) {
            return false;
        }
        if (!str.startsWith("#")) {
            str = "#".concat(str);
        }
        int parseColor = Color.parseColor(str);
        Iterator it = ColorScheme.getSeedColors(wallpaperColors, true).iterator();
        while (it.hasNext()) {
            if (((Integer) it.next()).intValue() == parseColor) {
                FragmentManagerViewModel$$ExternalSyntheticOutline0.m("Same as previous set system palette: ", str, "ThemeOverlayController");
                return true;
            }
        }
        return false;
    }

    public final void createOverlays(int i) {
        this.mDarkColorScheme = new ColorScheme(i, true, this.mThemeStyle, this.mContrast);
        this.mLightColorScheme = new ColorScheme(i, false, this.mThemeStyle, this.mContrast);
        this.mColorScheme = isNightMode() ? this.mDarkColorScheme : this.mLightColorScheme;
        FabricatedOverlay newFabricatedOverlay = newFabricatedOverlay("neutral");
        assignTonalPaletteToOverlay("neutral1", newFabricatedOverlay, this.mColorScheme.mNeutral1);
        assignTonalPaletteToOverlay("neutral2", newFabricatedOverlay, this.mColorScheme.mNeutral2);
        this.mNeutralOverlay = newFabricatedOverlay;
        FabricatedOverlay newFabricatedOverlay2 = newFabricatedOverlay("accent");
        assignTonalPaletteToOverlay("accent1", newFabricatedOverlay2, this.mColorScheme.mAccent1);
        assignTonalPaletteToOverlay("accent2", newFabricatedOverlay2, this.mColorScheme.mAccent2);
        assignTonalPaletteToOverlay("accent3", newFabricatedOverlay2, this.mColorScheme.mAccent3);
        this.mSecondaryOverlay = newFabricatedOverlay2;
        final FabricatedOverlay newFabricatedOverlay3 = newFabricatedOverlay("dynamic");
        MaterialDynamicColors materialDynamicColors = new MaterialDynamicColors();
        ArrayList arrayList = new ArrayList();
        arrayList.add(Pair.create("primary_container", materialDynamicColors.primaryContainer()));
        arrayList.add(Pair.create("on_primary_container", new DynamicColor("on_primary_container", new MaterialDynamicColors$$ExternalSyntheticLambda0(2), new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors, 2), false, new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors, 3), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null)));
        arrayList.add(Pair.create("primary", materialDynamicColors.primary()));
        arrayList.add(Pair.create("on_primary", new DynamicColor("on_primary", new MaterialDynamicColors$$ExternalSyntheticLambda1(21), new MaterialDynamicColors$$ExternalSyntheticLambda1(29), false, new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors, 0), null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null)));
        arrayList.add(Pair.create("secondary_container", materialDynamicColors.secondaryContainer()));
        arrayList.add(Pair.create("on_secondary_container", new DynamicColor("on_secondary_container", new MaterialDynamicColors$$ExternalSyntheticLambda0(29), new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors, 15), false, new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors, 16), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null)));
        arrayList.add(Pair.create("secondary", materialDynamicColors.secondary()));
        arrayList.add(Pair.create("on_secondary", new DynamicColor("on_secondary", new MaterialDynamicColors$$ExternalSyntheticLambda1(20), new MaterialDynamicColors$$ExternalSyntheticLambda1(22), false, new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors, 21), null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null)));
        arrayList.add(Pair.create("tertiary_container", materialDynamicColors.tertiaryContainer()));
        arrayList.add(Pair.create("on_tertiary_container", new DynamicColor("on_tertiary_container", new MaterialDynamicColors$$ExternalSyntheticLambda9(6), new MaterialDynamicColors$$ExternalSyntheticLambda7(materialDynamicColors, 7), false, new MaterialDynamicColors$$ExternalSyntheticLambda7(materialDynamicColors, 8), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null)));
        arrayList.add(Pair.create("tertiary", materialDynamicColors.tertiary()));
        arrayList.add(Pair.create("on_tertiary", new DynamicColor("on_tertiary", new MaterialDynamicColors$$ExternalSyntheticLambda5(18), new MaterialDynamicColors$$ExternalSyntheticLambda5(19), false, new MaterialDynamicColors$$ExternalSyntheticLambda7(materialDynamicColors, 3), null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null)));
        arrayList.add(Pair.create("background", new DynamicColor("background", new MaterialDynamicColors$$ExternalSyntheticLambda5(2), new MaterialDynamicColors$$ExternalSyntheticLambda5(3), true, null, null, null, null)));
        arrayList.add(Pair.create("on_background", new DynamicColor("on_background", new MaterialDynamicColors$$ExternalSyntheticLambda9(15), new MaterialDynamicColors$$ExternalSyntheticLambda0(1), false, new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors, 1), null, new ContrastCurve(3.0d, 3.0d, 4.5d, 7.0d), null)));
        arrayList.add(Pair.create("surface", new DynamicColor("surface", new MaterialDynamicColors$$ExternalSyntheticLambda1(8), new MaterialDynamicColors$$ExternalSyntheticLambda1(9), true, null, null, null, null)));
        arrayList.add(Pair.create("on_surface", new DynamicColor("on_surface", new MaterialDynamicColors$$ExternalSyntheticLambda1(25), new MaterialDynamicColors$$ExternalSyntheticLambda1(26), false, new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors, 22), null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null)));
        arrayList.add(Pair.create("surface_container_low", MaterialDynamicColors.surfaceContainerLow()));
        arrayList.add(Pair.create("surface_container_lowest", new DynamicColor("surface_container_lowest", new MaterialDynamicColors$$ExternalSyntheticLambda0(23), new MaterialDynamicColors$$ExternalSyntheticLambda1(1), true, null, null, null, null)));
        arrayList.add(Pair.create("surface_container", new DynamicColor("surface_container", new MaterialDynamicColors$$ExternalSyntheticLambda0(5), new MaterialDynamicColors$$ExternalSyntheticLambda0(6), true, null, null, null, null)));
        arrayList.add(Pair.create("surface_container_high", new DynamicColor("surface_container_high", new MaterialDynamicColors$$ExternalSyntheticLambda5(20), new MaterialDynamicColors$$ExternalSyntheticLambda5(21), true, null, null, null, null)));
        arrayList.add(Pair.create("surface_container_highest", new DynamicColor("surface_container_highest", new MaterialDynamicColors$$ExternalSyntheticLambda5(16), new MaterialDynamicColors$$ExternalSyntheticLambda5(17), true, null, null, null, null)));
        arrayList.add(Pair.create("surface_bright", new DynamicColor("surface_bright", new MaterialDynamicColors$$ExternalSyntheticLambda1(16), new MaterialDynamicColors$$ExternalSyntheticLambda1(17), true, null, null, null, null)));
        arrayList.add(Pair.create("surface_dim", new DynamicColor("surface_dim", new MaterialDynamicColors$$ExternalSyntheticLambda0(20), new MaterialDynamicColors$$ExternalSyntheticLambda0(21), true, null, null, null, null)));
        arrayList.add(Pair.create("surface_variant", new DynamicColor("surface_variant", new MaterialDynamicColors$$ExternalSyntheticLambda5(14), new MaterialDynamicColors$$ExternalSyntheticLambda5(15), true, null, null, null, null)));
        arrayList.add(Pair.create("on_surface_variant", new DynamicColor("on_surface_variant", new MaterialDynamicColors$$ExternalSyntheticLambda9(7), new MaterialDynamicColors$$ExternalSyntheticLambda9(8), false, new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors, 22), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null)));
        arrayList.add(Pair.create("outline", new DynamicColor("outline", new MaterialDynamicColors$$ExternalSyntheticLambda1(4), new MaterialDynamicColors$$ExternalSyntheticLambda1(5), false, new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors, 22), null, new ContrastCurve(1.5d, 3.0d, 4.5d, 7.0d), null)));
        arrayList.add(Pair.create("outline_variant", materialDynamicColors.outlineVariant()));
        arrayList.add(Pair.create("error", materialDynamicColors.error()));
        arrayList.add(Pair.create("on_error", new DynamicColor("on_error", new MaterialDynamicColors$$ExternalSyntheticLambda0(24), new MaterialDynamicColors$$ExternalSyntheticLambda0(25), false, new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors, 12), null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null)));
        arrayList.add(Pair.create("error_container", materialDynamicColors.errorContainer()));
        arrayList.add(Pair.create("on_error_container", new DynamicColor("on_error_container", new MaterialDynamicColors$$ExternalSyntheticLambda1(14), new MaterialDynamicColors$$ExternalSyntheticLambda1(15), false, new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors, 20), null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null)));
        arrayList.add(Pair.create("control_activated", DynamicColor.fromPalette("control_activated", new MaterialDynamicColors$$ExternalSyntheticLambda5(25), new MaterialDynamicColors$$ExternalSyntheticLambda5(26))));
        arrayList.add(Pair.create("control_normal", DynamicColor.fromPalette("control_normal", new MaterialDynamicColors$$ExternalSyntheticLambda0(7), new MaterialDynamicColors$$ExternalSyntheticLambda0(8))));
        arrayList.add(Pair.create("control_highlight", new DynamicColor(new MaterialDynamicColors$$ExternalSyntheticLambda0(13), new MaterialDynamicColors$$ExternalSyntheticLambda0(14), new MaterialDynamicColors$$ExternalSyntheticLambda0(15))));
        arrayList.add(Pair.create("text_primary_inverse", DynamicColor.fromPalette("text_primary_inverse", new MaterialDynamicColors$$ExternalSyntheticLambda5(8), new MaterialDynamicColors$$ExternalSyntheticLambda5(9))));
        arrayList.add(Pair.create("text_secondary_and_tertiary_inverse", DynamicColor.fromPalette("text_secondary_and_tertiary_inverse", new MaterialDynamicColors$$ExternalSyntheticLambda0(26), new MaterialDynamicColors$$ExternalSyntheticLambda0(27))));
        arrayList.add(Pair.create("text_primary_inverse_disable_only", DynamicColor.fromPalette("text_primary_inverse_disable_only", new MaterialDynamicColors$$ExternalSyntheticLambda1(6), new MaterialDynamicColors$$ExternalSyntheticLambda1(7))));
        arrayList.add(Pair.create("text_secondary_and_tertiary_inverse_disabled", DynamicColor.fromPalette("text_secondary_and_tertiary_inverse_disabled", new MaterialDynamicColors$$ExternalSyntheticLambda0(0), new MaterialDynamicColors$$ExternalSyntheticLambda1(0))));
        arrayList.add(Pair.create("text_hint_inverse", DynamicColor.fromPalette("text_hint_inverse", new MaterialDynamicColors$$ExternalSyntheticLambda1(23), new MaterialDynamicColors$$ExternalSyntheticLambda1(24))));
        arrayList.add(Pair.create("palette_key_color_primary", DynamicColor.fromPalette("primary_palette_key_color", new MaterialDynamicColors$$ExternalSyntheticLambda9(2), new MaterialDynamicColors$$ExternalSyntheticLambda9(3))));
        arrayList.add(Pair.create("palette_key_color_secondary", DynamicColor.fromPalette("secondary_palette_key_color", new MaterialDynamicColors$$ExternalSyntheticLambda1(12), new MaterialDynamicColors$$ExternalSyntheticLambda1(13))));
        arrayList.add(Pair.create("palette_key_color_tertiary", DynamicColor.fromPalette("tertiary_palette_key_color", new MaterialDynamicColors$$ExternalSyntheticLambda9(4), new MaterialDynamicColors$$ExternalSyntheticLambda9(5))));
        arrayList.add(Pair.create("palette_key_color_neutral", DynamicColor.fromPalette("neutral_palette_key_color", new MaterialDynamicColors$$ExternalSyntheticLambda5(27), new MaterialDynamicColors$$ExternalSyntheticLambda5(28))));
        arrayList.add(Pair.create("palette_key_color_neutral_variant", DynamicColor.fromPalette("neutral_variant_palette_key_color", new MaterialDynamicColors$$ExternalSyntheticLambda0(11), new MaterialDynamicColors$$ExternalSyntheticLambda0(12))));
        final Boolean bool = Boolean.FALSE;
        arrayList.forEach(new Consumer() { // from class: com.android.systemui.theme.ThemeOverlayController$$ExternalSyntheticLambda8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ThemeOverlayController themeOverlayController = ThemeOverlayController.this;
                Boolean bool2 = bool;
                FabricatedOverlay fabricatedOverlay = newFabricatedOverlay3;
                Pair pair = (Pair) obj;
                themeOverlayController.getClass();
                String str = "android:color/system_" + ((String) pair.first);
                if (bool2.booleanValue()) {
                    fabricatedOverlay.setResourceValue(str, 28, ((DynamicColor) pair.second).getArgb(themeOverlayController.mLightColorScheme.mMaterialScheme), (String) null);
                } else {
                    fabricatedOverlay.setResourceValue(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "_light"), 28, ((DynamicColor) pair.second).getArgb(themeOverlayController.mLightColorScheme.mMaterialScheme), (String) null);
                    fabricatedOverlay.setResourceValue(ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), str, "_dark"), 28, ((DynamicColor) pair.second).getArgb(themeOverlayController.mDarkColorScheme.mMaterialScheme), (String) null);
                }
            }
        });
        MaterialDynamicColors materialDynamicColors2 = new MaterialDynamicColors();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Pair.create("primary_fixed", materialDynamicColors2.primaryFixed()));
        arrayList2.add(Pair.create("primary_fixed_dim", materialDynamicColors2.primaryFixedDim()));
        arrayList2.add(Pair.create("on_primary_fixed", new DynamicColor("on_primary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda1(27), new MaterialDynamicColors$$ExternalSyntheticLambda1(28), false, new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors2, 23), new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors2, 24), new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null)));
        arrayList2.add(Pair.create("on_primary_fixed_variant", new DynamicColor("on_primary_fixed_variant", new MaterialDynamicColors$$ExternalSyntheticLambda5(0), new MaterialDynamicColors$$ExternalSyntheticLambda5(22), false, new MaterialDynamicColors$$ExternalSyntheticLambda7(materialDynamicColors2, 0), new MaterialDynamicColors$$ExternalSyntheticLambda7(materialDynamicColors2, 9), new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null)));
        arrayList2.add(Pair.create("secondary_fixed", materialDynamicColors2.secondaryFixed()));
        arrayList2.add(Pair.create("secondary_fixed_dim", materialDynamicColors2.secondaryFixedDim()));
        arrayList2.add(Pair.create("on_secondary_fixed", new DynamicColor("on_secondary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda9(0), new CustomDynamicColors$$ExternalSyntheticLambda5(8), false, new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors2, 5), new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors2, 7), new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null)));
        arrayList2.add(Pair.create("on_secondary_fixed_variant", new DynamicColor("on_secondary_fixed_variant", new MaterialDynamicColors$$ExternalSyntheticLambda5(29), new MaterialDynamicColors$$ExternalSyntheticLambda9(1), false, new MaterialDynamicColors$$ExternalSyntheticLambda7(materialDynamicColors2, 5), new MaterialDynamicColors$$ExternalSyntheticLambda7(materialDynamicColors2, 6), new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null)));
        arrayList2.add(Pair.create("tertiary_fixed", materialDynamicColors2.tertiaryFixed()));
        arrayList2.add(Pair.create("tertiary_fixed_dim", materialDynamicColors2.tertiaryFixedDim()));
        arrayList2.add(Pair.create("on_tertiary_fixed", new DynamicColor("on_tertiary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda5(4), new MaterialDynamicColors$$ExternalSyntheticLambda5(5), false, new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors2, 28), new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors2, 29), new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), null)));
        arrayList2.add(Pair.create("on_tertiary_fixed_variant", new DynamicColor("on_tertiary_fixed_variant", new MaterialDynamicColors$$ExternalSyntheticLambda1(10), new MaterialDynamicColors$$ExternalSyntheticLambda1(11), false, new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors2, 18), new MaterialDynamicColors$$ExternalSyntheticLambda4(materialDynamicColors2, 19), new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), null)));
        final Boolean bool2 = Boolean.TRUE;
        arrayList2.forEach(new Consumer() { // from class: com.android.systemui.theme.ThemeOverlayController$$ExternalSyntheticLambda8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ThemeOverlayController themeOverlayController = ThemeOverlayController.this;
                Boolean bool22 = bool2;
                FabricatedOverlay fabricatedOverlay = newFabricatedOverlay3;
                Pair pair = (Pair) obj;
                themeOverlayController.getClass();
                String str = "android:color/system_" + ((String) pair.first);
                if (bool22.booleanValue()) {
                    fabricatedOverlay.setResourceValue(str, 28, ((DynamicColor) pair.second).getArgb(themeOverlayController.mLightColorScheme.mMaterialScheme), (String) null);
                } else {
                    fabricatedOverlay.setResourceValue(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "_light"), 28, ((DynamicColor) pair.second).getArgb(themeOverlayController.mLightColorScheme.mMaterialScheme), (String) null);
                    fabricatedOverlay.setResourceValue(ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), str, "_dark"), 28, ((DynamicColor) pair.second).getArgb(themeOverlayController.mDarkColorScheme.mMaterialScheme), (String) null);
                }
            }
        });
        CustomDynamicColors customDynamicColors = new CustomDynamicColors();
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(Pair.create("widget_background", CustomDynamicColors.widgetBackground()));
        arrayList3.add(Pair.create("clock_hour", customDynamicColors.clockHour()));
        arrayList3.add(Pair.create("clock_minute", customDynamicColors.clockMinute()));
        arrayList3.add(Pair.create("clock_second", new DynamicColor("weather_temp", new CustomDynamicColors$$ExternalSyntheticLambda5(5), new CustomDynamicColors$$ExternalSyntheticLambda5(6), false, new CustomDynamicColors$$ExternalSyntheticLambda2(customDynamicColors, 23), null, new ContrastCurve(1.0d, 5.0d, 70.0d, 11.0d), null)));
        arrayList3.add(Pair.create("theme_app", new DynamicColor("theme_app", new CustomDynamicColors$$ExternalSyntheticLambda5(1), new CustomDynamicColors$$ExternalSyntheticLambda5(2), true, null, null, null, null)));
        arrayList3.add(Pair.create("on_theme_app", new DynamicColor("on_theme_app", new CustomDynamicColors$$ExternalSyntheticLambda0(21), new CustomDynamicColors$$ExternalSyntheticLambda0(22), false, new CustomDynamicColors$$ExternalSyntheticLambda2(customDynamicColors, 18), null, new ContrastCurve(1.0d, 3.0d, 7.0d, 10.0d), null)));
        arrayList3.add(Pair.create("theme_app_ring", CustomDynamicColors.themeAppRing()));
        arrayList3.add(Pair.create("theme_notif", customDynamicColors.themeNotif()));
        arrayList3.add(Pair.create("brand_a", customDynamicColors.brandA()));
        arrayList3.add(Pair.create("brand_b", customDynamicColors.brandB()));
        arrayList3.add(Pair.create("brand_c", customDynamicColors.brandC()));
        arrayList3.add(Pair.create("brand_d", customDynamicColors.brandD()));
        arrayList3.add(Pair.create("under_surface", CustomDynamicColors.underSurface()));
        arrayList3.add(Pair.create("shade_active", customDynamicColors.shadeActive()));
        arrayList3.add(Pair.create("on_shade_active", customDynamicColors.onShadeActive()));
        arrayList3.add(Pair.create("on_shade_active_variant", customDynamicColors.onShadeActiveVariant()));
        arrayList3.add(Pair.create("shade_inactive", customDynamicColors.shadeInactive()));
        arrayList3.add(Pair.create("on_shade_inactive", customDynamicColors.onShadeInactive()));
        arrayList3.add(Pair.create("on_shade_inactive_variant", customDynamicColors.onShadeInactiveVariant()));
        arrayList3.add(Pair.create("shade_disabled", customDynamicColors.shadeDisabled()));
        arrayList3.add(Pair.create("overview_background", new DynamicColor("overview_background", new CustomDynamicColors$$ExternalSyntheticLambda5(3), new CustomDynamicColors$$ExternalSyntheticLambda5(4), true, null, null, null, null)));
        arrayList3.forEach(new Consumer() { // from class: com.android.systemui.theme.ThemeOverlayController$$ExternalSyntheticLambda8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ThemeOverlayController themeOverlayController = ThemeOverlayController.this;
                Boolean bool22 = bool;
                FabricatedOverlay fabricatedOverlay = newFabricatedOverlay3;
                Pair pair = (Pair) obj;
                themeOverlayController.getClass();
                String str = "android:color/system_" + ((String) pair.first);
                if (bool22.booleanValue()) {
                    fabricatedOverlay.setResourceValue(str, 28, ((DynamicColor) pair.second).getArgb(themeOverlayController.mLightColorScheme.mMaterialScheme), (String) null);
                } else {
                    fabricatedOverlay.setResourceValue(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "_light"), 28, ((DynamicColor) pair.second).getArgb(themeOverlayController.mLightColorScheme.mMaterialScheme), (String) null);
                    fabricatedOverlay.setResourceValue(ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), str, "_dark"), 28, ((DynamicColor) pair.second).getArgb(themeOverlayController.mDarkColorScheme.mMaterialScheme), (String) null);
                }
            }
        });
        this.mDynamicOverlay = newFabricatedOverlay3;
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("mSystemColors=" + this.mCurrentColors);
        printWriter.println("mMainWallpaperColor=" + Integer.toHexString(this.mMainWallpaperColor));
        printWriter.println("mSecondaryOverlay=" + this.mSecondaryOverlay);
        printWriter.println("mNeutralOverlay=" + this.mNeutralOverlay);
        printWriter.println("mDynamicOverlay=" + this.mDynamicOverlay);
        printWriter.println("mIsMonetEnabled=" + this.mIsMonetEnabled);
        printWriter.println("mIsFidelityEnabled=false");
        printWriter.println("mColorScheme=" + this.mColorScheme);
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("mNeedsOverlayCreation="), this.mNeedsOverlayCreation, printWriter, "mAcceptColorEvents="), this.mAcceptColorEvents, printWriter, "mDeferredThemeEvaluation="), this.mDeferredThemeEvaluation, printWriter, "mThemeStyle=");
        m.append(this.mThemeStyle);
        printWriter.println(m.toString());
    }

    public final void handleWallpaperColors(WallpaperColors wallpaperColors, int i, int i2) {
        String str = "lock_wallpaper";
        int userId = ((UserTrackerImpl) this.mUserTracker).getUserId();
        boolean z = this.mCurrentColors.get(i2) != null;
        boolean z2 = ((this.mWallpaperManager.getWallpaperIdForUser(2, i2) > this.mWallpaperManager.getWallpaperIdForUser(1, i2) ? 2 : 1) & i) != 0;
        if (z2) {
            this.mCurrentColors.put(i2, wallpaperColors);
            Log.d("ThemeOverlayController", "got new colors: " + wallpaperColors + " where: " + i);
        }
        if (i2 != userId) {
            StringBuilder sb = new StringBuilder("Colors ");
            sb.append(wallpaperColors);
            sb.append(" for user ");
            sb.append(i2);
            sb.append(". Not for current user: ");
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(sb, userId, "ThemeOverlayController");
            return;
        }
        DeviceProvisionedController deviceProvisionedController = this.mDeviceProvisionedController;
        if (deviceProvisionedController != null && !((DeviceProvisionedControllerImpl) deviceProvisionedController).isCurrentUserSetup()) {
            if (z) {
                Log.i("ThemeOverlayController", "Wallpaper color event deferred until setup is finished: " + wallpaperColors);
                this.mDeferredThemeEvaluation = true;
                return;
            }
            if (this.mDeferredThemeEvaluation) {
                Log.i("ThemeOverlayController", "Wallpaper color event received, but we already were deferring eval: " + wallpaperColors);
                return;
            } else {
                StringBuilder sb2 = new StringBuilder("During user setup, but allowing first color event: had? ");
                sb2.append(z);
                sb2.append(" has? ");
                sb2.append(this.mCurrentColors.get(i2) != null);
                Log.i("ThemeOverlayController", sb2.toString());
            }
        }
        SecureSettings secureSettings = this.mSecureSettings;
        String stringForUser = ((SecureSettingsImpl) secureSettings).getStringForUser(userId, "theme_customization_overlay_packages");
        boolean z3 = i == 3;
        boolean z4 = i == 1;
        try {
            JSONObject jSONObject = stringForUser == null ? new JSONObject() : new JSONObject(stringForUser);
            String optString = jSONObject.optString("android.theme.customization.color_source");
            boolean equals = "preset".equals(optString);
            boolean z5 = z4 && "lock_wallpaper".equals(optString);
            if (!equals && !z5 && z2 && !isSeedColorSet(jSONObject, wallpaperColors)) {
                this.mSkipSettingChange = true;
                if (jSONObject.has("android.theme.customization.accent_color") || jSONObject.has("android.theme.customization.system_palette")) {
                    jSONObject.remove("android.theme.customization.dynamic_color");
                    jSONObject.remove("android.theme.customization.accent_color");
                    jSONObject.remove("android.theme.customization.system_palette");
                    jSONObject.remove("android.theme.customization.color_index");
                }
                jSONObject.put("android.theme.customization.color_both", z3 ? "1" : "0");
                if (i != 2) {
                    str = "home_wallpaper";
                }
                jSONObject.put("android.theme.customization.color_source", str);
                jSONObject.put("_applied_timestamp", System.currentTimeMillis());
                Log.d("ThemeOverlayController", "Updating theme setting from " + stringForUser + " to " + jSONObject.toString());
                ((SecureSettingsImpl) secureSettings).putStringForUser("theme_customization_overlay_packages", jSONObject.toString(), -2);
            }
        } catch (JSONException e) {
            Log.i("ThemeOverlayController", "Failed to parse THEME_CUSTOMIZATION_OVERLAY_PACKAGES.", e);
        }
        reevaluateSystemTheme(false);
    }

    public boolean isNightMode() {
        return (this.mResources.getConfiguration().uiMode & 48) == 32;
    }

    public boolean isPrivateProfile(UserHandle userHandle) {
        return ((UserManager) this.mContext.createContextAsUser(userHandle, 0).getSystemService(UserManager.class)).isPrivateProfile();
    }

    public FabricatedOverlay newFabricatedOverlay(String str) {
        return new FabricatedOverlay.Builder("com.android.systemui", str, "android").build();
    }

    /* JADX WARN: Type inference failed for: r2v10, types: [com.android.systemui.theme.ThemeOverlayController$$ExternalSyntheticLambda3] */
    public final void reevaluateSystemTheme(boolean z) {
        final FabricatedOverlay[] fabricatedOverlayArr;
        FabricatedOverlay fabricatedOverlay;
        FabricatedOverlay fabricatedOverlay2;
        FabricatedOverlay fabricatedOverlay3;
        SparseArray sparseArray = this.mCurrentColors;
        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) this.mUserTracker;
        WallpaperColors wallpaperColors = (WallpaperColors) sparseArray.get(userTrackerImpl.getUserId());
        int intValue = wallpaperColors == null ? 0 : ((Integer) ColorScheme.getSeedColors(wallpaperColors, true).get(0)).intValue();
        if (this.mMainWallpaperColor != intValue || z) {
            this.mMainWallpaperColor = intValue;
            SecureSettings secureSettings = this.mSecureSettings;
            boolean z2 = this.mIsMonetEnabled;
            if (z2) {
                Style style = Style.EXPRESSIVE;
                Style style2 = Style.SPRITZ;
                Style style3 = Style.TONAL_SPOT;
                ArrayList arrayList = new ArrayList(Arrays.asList(style, style2, style3, Style.FRUIT_SALAD, Style.RAINBOW, Style.VIBRANT, Style.MONOCHROMATIC));
                Style style4 = this.mThemeStyle;
                String stringForUser = ((SecureSettingsImpl) secureSettings).getStringForUser(userTrackerImpl.getUserId(), "theme_customization_overlay_packages");
                if (!TextUtils.isEmpty(stringForUser)) {
                    try {
                        Style valueOf = Style.valueOf(new JSONObject(stringForUser).getString("android.theme.customization.theme_style"));
                        if (arrayList.contains(valueOf)) {
                            style3 = valueOf;
                        }
                    } catch (IllegalArgumentException | JSONException e) {
                        Log.i("ThemeOverlayController", "Failed to parse THEME_CUSTOMIZATION_OVERLAY_PACKAGES.", e);
                    }
                    style4 = style3;
                }
                this.mThemeStyle = style4;
                createOverlays(this.mMainWallpaperColor);
                this.mNeedsOverlayCreation = true;
                Log.d("ThemeOverlayController", "fetched overlays. accent: " + this.mSecondaryOverlay + " neutral: " + this.mNeutralOverlay + " dynamic: " + this.mDynamicOverlay);
            }
            final int userId = userTrackerImpl.getUserId();
            String stringForUser2 = ((SecureSettingsImpl) secureSettings).getStringForUser(userId, "theme_customization_overlay_packages");
            Log.d("ThemeOverlayController", "updateThemeOverlays. Setting: " + stringForUser2);
            final ArrayMap arrayMap = new ArrayMap();
            if (!TextUtils.isEmpty(stringForUser2)) {
                try {
                    JSONObject jSONObject = new JSONObject(stringForUser2);
                    for (String str : ThemeOverlayApplier.THEME_CATEGORIES) {
                        if (jSONObject.has(str)) {
                            arrayMap.put(str, new OverlayIdentifier(jSONObject.getString(str)));
                        }
                    }
                } catch (JSONException e2) {
                    Log.i("ThemeOverlayController", "Failed to parse THEME_CUSTOMIZATION_OVERLAY_PACKAGES.", e2);
                }
            }
            OverlayIdentifier overlayIdentifier = (OverlayIdentifier) arrayMap.get("android.theme.customization.system_palette");
            if (z2 && overlayIdentifier != null && overlayIdentifier.getPackageName() != null) {
                try {
                    String lowerCase = overlayIdentifier.getPackageName().toLowerCase();
                    if (!lowerCase.startsWith("#")) {
                        lowerCase = "#" + lowerCase;
                    }
                    createOverlays(Color.parseColor(lowerCase));
                    this.mNeedsOverlayCreation = true;
                    arrayMap.remove("android.theme.customization.system_palette");
                    arrayMap.remove("android.theme.customization.accent_color");
                    arrayMap.remove("android.theme.customization.dynamic_color");
                } catch (Exception e3) {
                    Log.w("ThemeOverlayController", "Invalid color definition: " + overlayIdentifier.getPackageName(), e3);
                }
            } else if (!z2 && overlayIdentifier != null) {
                try {
                    arrayMap.remove("android.theme.customization.system_palette");
                    arrayMap.remove("android.theme.customization.accent_color");
                    arrayMap.remove("android.theme.customization.dynamic_color");
                } catch (NumberFormatException unused) {
                }
            }
            if (!arrayMap.containsKey("android.theme.customization.system_palette") && (fabricatedOverlay3 = this.mNeutralOverlay) != null) {
                arrayMap.put("android.theme.customization.system_palette", fabricatedOverlay3.getIdentifier());
            }
            if (!arrayMap.containsKey("android.theme.customization.accent_color") && (fabricatedOverlay2 = this.mSecondaryOverlay) != null) {
                arrayMap.put("android.theme.customization.accent_color", fabricatedOverlay2.getIdentifier());
            }
            if (!arrayMap.containsKey("android.theme.customization.dynamic_color") && (fabricatedOverlay = this.mDynamicOverlay) != null) {
                arrayMap.put("android.theme.customization.dynamic_color", fabricatedOverlay.getIdentifier());
            }
            final HashSet hashSet = new HashSet();
            for (UserInfo userInfo : this.mUserManager.getEnabledProfiles(userId)) {
                if (userInfo.isProfile()) {
                    hashSet.add(userInfo.getUserHandle());
                }
            }
            final ?? r2 = new Runnable() { // from class: com.android.systemui.theme.ThemeOverlayController$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    ThemeOverlayController themeOverlayController = ThemeOverlayController.this;
                    int i = userId;
                    themeOverlayController.getClass();
                    Log.d("ThemeOverlayController", "ThemeHomeDelay: ThemeOverlayController ready with user " + i);
                    themeOverlayController.mActivityManager.setThemeOverlayReady(i);
                }
            };
            ArraySet arraySet = new ArraySet(hashSet);
            arraySet.add(UserHandle.SYSTEM);
            Iterator it = arraySet.iterator();
            while (it.hasNext()) {
                UserHandle userHandle = (UserHandle) it.next();
                Resources resources = userHandle.isSystem() ? this.mResources : this.mContext.createContextAsUser(userHandle, 0).getResources();
                Resources.Theme theme = this.mContext.getTheme();
                MaterialDynamicColors materialDynamicColors = new MaterialDynamicColors();
                if (resources.getColor(R.color.system_accent1_500, theme) != this.mColorScheme.mAccent1.getS500() || resources.getColor(R.color.system_accent2_500, theme) != this.mColorScheme.mAccent2.getS500() || resources.getColor(R.color.system_accent3_500, theme) != this.mColorScheme.mAccent3.getS500() || resources.getColor(R.color.system_neutral1_500, theme) != this.mColorScheme.mNeutral1.getS500() || resources.getColor(R.color.system_neutral2_500, theme) != this.mColorScheme.mNeutral2.getS500() || resources.getColor(R.color.system_outline_variant_dark, theme) != materialDynamicColors.outlineVariant().getArgb(this.mDarkColorScheme.mMaterialScheme) || resources.getColor(R.color.system_outline_variant_light, theme) != materialDynamicColors.outlineVariant().getArgb(this.mLightColorScheme.mMaterialScheme) || resources.getColor(R.color.system_primary_container_dark, theme) != materialDynamicColors.primaryContainer().getArgb(this.mDarkColorScheme.mMaterialScheme) || resources.getColor(R.color.system_primary_container_light, theme) != materialDynamicColors.primaryContainer().getArgb(this.mLightColorScheme.mMaterialScheme) || resources.getColor(R.color.system_primary_fixed, theme) != materialDynamicColors.primaryFixed().getArgb(this.mLightColorScheme.mMaterialScheme)) {
                    Log.d("ThemeOverlayController", "Applying overlays: " + ((String) arrayMap.keySet().stream().map(new Function() { // from class: com.android.systemui.theme.ThemeOverlayController$$ExternalSyntheticLambda4
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            Map map = arrayMap;
                            String str2 = (String) obj;
                            StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str2, " -> ");
                            m.append(map.get(str2));
                            return m.toString();
                        }
                    }).collect(Collectors.joining(", "))));
                    if (this.mNeedsOverlayCreation) {
                        this.mNeedsOverlayCreation = false;
                        fabricatedOverlayArr = new FabricatedOverlay[]{this.mSecondaryOverlay, this.mNeutralOverlay, this.mDynamicOverlay};
                    } else {
                        fabricatedOverlayArr = null;
                    }
                    final ThemeOverlayApplier themeOverlayApplier = this.mThemeManager;
                    themeOverlayApplier.mBgExecutor.execute(new Runnable() { // from class: com.android.systemui.theme.ThemeOverlayApplier$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            final ThemeOverlayApplier themeOverlayApplier2 = ThemeOverlayApplier.this;
                            final Map map = arrayMap;
                            FabricatedOverlay[] fabricatedOverlayArr2 = fabricatedOverlayArr;
                            int i = userId;
                            Set set = hashSet;
                            ThemeOverlayController$$ExternalSyntheticLambda3 themeOverlayController$$ExternalSyntheticLambda3 = r2;
                            themeOverlayApplier2.getClass();
                            final HashSet hashSet2 = new HashSet(ThemeOverlayApplier.THEME_CATEGORIES);
                            Set set2 = (Set) hashSet2.stream().map(new Function() { // from class: com.android.systemui.theme.ThemeOverlayApplier$$ExternalSyntheticLambda1
                                @Override // java.util.function.Function
                                public final Object apply(Object obj) {
                                    return (String) ((ArrayMap) ThemeOverlayApplier.this.mCategoryToTargetPackage).get((String) obj);
                                }
                            }).collect(Collectors.toSet());
                            final ArrayList arrayList2 = new ArrayList();
                            set2.forEach(new Consumer() { // from class: com.android.systemui.theme.ThemeOverlayApplier$$ExternalSyntheticLambda2
                                @Override // java.util.function.Consumer
                                public final void accept(Object obj) {
                                    ThemeOverlayApplier themeOverlayApplier3 = ThemeOverlayApplier.this;
                                    arrayList2.addAll(themeOverlayApplier3.mOverlayManager.getOverlayInfosForTarget((String) obj, UserHandle.SYSTEM));
                                }
                            });
                            final int i2 = 0;
                            Stream filter = arrayList2.stream().filter(new Predicate() { // from class: com.android.systemui.theme.ThemeOverlayApplier$$ExternalSyntheticLambda3
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    int i3 = i2;
                                    Object obj2 = themeOverlayApplier2;
                                    switch (i3) {
                                        case 0:
                                            OverlayInfo overlayInfo = (OverlayInfo) obj;
                                            return ((Set) ((ArrayMap) ((ThemeOverlayApplier) obj2).mTargetPackageToCategories).get(overlayInfo.targetPackageName)).contains(overlayInfo.category);
                                        case 1:
                                            return ((Set) obj2).contains(((OverlayInfo) obj).category);
                                        default:
                                            return !((Map) obj2).containsValue(new OverlayIdentifier(((OverlayInfo) obj).packageName));
                                    }
                                }
                            });
                            final int i3 = 1;
                            final int i4 = 2;
                            List<Pair> list = (List) filter.filter(new Predicate() { // from class: com.android.systemui.theme.ThemeOverlayApplier$$ExternalSyntheticLambda3
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    int i32 = i3;
                                    Object obj2 = hashSet2;
                                    switch (i32) {
                                        case 0:
                                            OverlayInfo overlayInfo = (OverlayInfo) obj;
                                            return ((Set) ((ArrayMap) ((ThemeOverlayApplier) obj2).mTargetPackageToCategories).get(overlayInfo.targetPackageName)).contains(overlayInfo.category);
                                        case 1:
                                            return ((Set) obj2).contains(((OverlayInfo) obj).category);
                                        default:
                                            return !((Map) obj2).containsValue(new OverlayIdentifier(((OverlayInfo) obj).packageName));
                                    }
                                }
                            }).filter(new Predicate() { // from class: com.android.systemui.theme.ThemeOverlayApplier$$ExternalSyntheticLambda3
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    int i32 = i4;
                                    Object obj2 = map;
                                    switch (i32) {
                                        case 0:
                                            OverlayInfo overlayInfo = (OverlayInfo) obj;
                                            return ((Set) ((ArrayMap) ((ThemeOverlayApplier) obj2).mTargetPackageToCategories).get(overlayInfo.targetPackageName)).contains(overlayInfo.category);
                                        case 1:
                                            return ((Set) obj2).contains(((OverlayInfo) obj).category);
                                        default:
                                            return !((Map) obj2).containsValue(new OverlayIdentifier(((OverlayInfo) obj).packageName));
                                    }
                                }
                            }).filter(new ThemeOverlayApplier$$ExternalSyntheticLambda6()).map(new ThemeOverlayApplier$$ExternalSyntheticLambda7()).collect(Collectors.toList());
                            OverlayManagerTransaction.Builder transactionBuilder = themeOverlayApplier2.getTransactionBuilder();
                            HashSet hashSet3 = new HashSet();
                            if (fabricatedOverlayArr2 != null) {
                                for (FabricatedOverlay fabricatedOverlay4 : fabricatedOverlayArr2) {
                                    hashSet3.add(fabricatedOverlay4.getIdentifier());
                                    transactionBuilder.registerFabricatedOverlay(fabricatedOverlay4);
                                }
                            }
                            for (Pair pair : list) {
                                OverlayIdentifier overlayIdentifier2 = new OverlayIdentifier((String) pair.second);
                                themeOverlayApplier2.setEnabled(transactionBuilder, overlayIdentifier2, (String) pair.first, i, set, false, hashSet3.contains(overlayIdentifier2));
                            }
                            for (String str2 : ThemeOverlayApplier.THEME_CATEGORIES) {
                                ArrayMap arrayMap2 = (ArrayMap) map;
                                if (arrayMap2.containsKey(str2)) {
                                    OverlayIdentifier overlayIdentifier3 = (OverlayIdentifier) arrayMap2.get(str2);
                                    themeOverlayApplier2.setEnabled(transactionBuilder, overlayIdentifier3, str2, i, set, true, hashSet3.contains(overlayIdentifier3));
                                }
                            }
                            try {
                                themeOverlayApplier2.mOverlayManager.commit(transactionBuilder.build());
                                Log.d("ThemeOverlayApplier", "Executing onComplete runnable");
                                themeOverlayApplier2.mMainExecutor.execute(themeOverlayController$$ExternalSyntheticLambda3);
                            } catch (IllegalStateException | SecurityException e4) {
                                Log.e("ThemeOverlayApplier", "setEnabled failed", e4);
                            }
                        }
                    });
                    return;
                }
            }
            Log.d("ThemeOverlayController", "Skipping overlay creation. Theme was already: " + this.mColorScheme);
            r2.run();
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        Log.d("ThemeOverlayController", "Start");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PROFILE_ADDED");
        intentFilter.addAction("android.intent.action.WALLPAPER_CHANGED");
        this.mBroadcastDispatcher.registerReceiver(this.mBroadcastReceiver, intentFilter, this.mMainExecutor, UserHandle.ALL);
        this.mSecureSettings.registerContentObserverForUserSync("theme_customization_overlay_packages", false, new ContentObserver(this.mBgHandler) { // from class: com.android.systemui.theme.ThemeOverlayController.5
            public final void onChange(boolean z, Collection collection, int i, int i2) {
                ExifInterface$$ExternalSyntheticOutline0.m("Overlay changed for user: ", "ThemeOverlayController", i2);
                if (((UserTrackerImpl) ThemeOverlayController.this.mUserTracker).getUserId() != i2) {
                    return;
                }
                if (!((DeviceProvisionedControllerImpl) ThemeOverlayController.this.mDeviceProvisionedController).isUserSetup(i2)) {
                    Log.i("ThemeOverlayController", "Theme application deferred when setting changed.");
                    ThemeOverlayController.this.mDeferredThemeEvaluation = true;
                    return;
                }
                ThemeOverlayController themeOverlayController = ThemeOverlayController.this;
                if (!themeOverlayController.mSkipSettingChange) {
                    themeOverlayController.reevaluateSystemTheme(true);
                } else {
                    Log.d("ThemeOverlayController", "Skipping setting change");
                    ThemeOverlayController.this.mSkipSettingChange = false;
                }
            }
        }, -1);
        this.mContrast = this.mUiModeManager.getContrast();
        this.mUiModeManager.addContrastChangeListener(this.mMainExecutor, new UiModeManager.ContrastChangeListener() { // from class: com.android.systemui.theme.ThemeOverlayController$$ExternalSyntheticLambda0
            @Override // android.app.UiModeManager.ContrastChangeListener
            public final void onContrastChanged(float f) {
                ThemeOverlayController themeOverlayController = ThemeOverlayController.this;
                themeOverlayController.mContrast = f;
                themeOverlayController.reevaluateSystemTheme(true);
            }
        });
        if (this.mIsMonetEnabled) {
            ((UserTrackerImpl) this.mUserTracker).addCallback(this.mUserTrackerCallback, this.mMainExecutor);
            DeviceProvisionedControllerImpl deviceProvisionedControllerImpl = (DeviceProvisionedControllerImpl) this.mDeviceProvisionedController;
            deviceProvisionedControllerImpl.addCallback(this.mDeviceProvisionedListener);
            ThemeOverlayController$$ExternalSyntheticLambda1 themeOverlayController$$ExternalSyntheticLambda1 = new ThemeOverlayController$$ExternalSyntheticLambda1(this, 0);
            if (deviceProvisionedControllerImpl.isCurrentUserSetup()) {
                this.mBgExecutor.execute(themeOverlayController$$ExternalSyntheticLambda1);
            } else {
                themeOverlayController$$ExternalSyntheticLambda1.run();
            }
            this.mWallpaperManager.addOnColorsChangedListener(this.mOnColorsChangedListener, null, -1);
            final ThemeOverlayController$$ExternalSyntheticLambda1 themeOverlayController$$ExternalSyntheticLambda12 = new ThemeOverlayController$$ExternalSyntheticLambda1(this, 1);
            this.mWakefulnessLifecycle.mObservers.add(new WakefulnessLifecycle.Observer() { // from class: com.android.systemui.theme.ThemeOverlayController.6
                @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
                public final void onFinishedGoingToSleep$1() {
                    ThemeOverlayController$$ExternalSyntheticLambda1.this.run();
                }
            });
        }
    }
}
