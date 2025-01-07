package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.graphics.Rect;
import android.view.InsetsFlags;
import android.view.ViewDebug;
import com.android.internal.view.AppearanceRegion;
import com.android.systemui.CoreStartable;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.statusbar.data.model.StatusBarAppearance;
import com.android.systemui.statusbar.data.repository.StatusBarModeRepositoryImpl;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.util.kotlin.JavaAdapter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LightBarController implements BatteryController.BatteryStateChangeCallback, Dumpable, CoreStartable {
    public int mAppearance;
    public BiometricUnlockController mBiometricUnlockController;
    public boolean mBouncerVisible;
    public boolean mDirectReplying;
    public boolean mForceDarkForScrim;
    public boolean mForceLightForScrim;
    public boolean mGlobalActionsVisible;
    public boolean mHasLightNavigationBar;
    public boolean mIsCustomizingForBackNav;
    public final JavaAdapter mJavaAdapter;
    public boolean mNavbarColorManagedByIme;
    public LightBarTransitionsController mNavigationBarController;
    public int mNavigationBarMode;
    public boolean mNavigationLight;
    public int mNavigationMode;
    public boolean mQsCustomizing;
    public boolean mQsExpanded;
    public final DarkIconDispatcherImpl mStatusBarIconController;
    public int mStatusBarMode;
    public final StatusBarModeRepositoryImpl mStatusBarModeRepository;
    public AppearanceRegion[] mAppearanceRegions = new AppearanceRegion[0];
    public BoundsPair mStatusBarBounds = new BoundsPair(new Rect(), new Rect());

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final BatteryController mBatteryController;
        public final DarkIconDispatcher mDarkIconDispatcher;
        public final DisplayTracker mDisplayTracker;
        public final DumpManager mDumpManager;
        public final JavaAdapter mJavaAdapter;
        public final NavigationModeController mNavModeController;
        public final StatusBarModeRepositoryImpl mStatusBarModeRepository;

        public Factory(JavaAdapter javaAdapter, DarkIconDispatcher darkIconDispatcher, BatteryController batteryController, NavigationModeController navigationModeController, StatusBarModeRepositoryImpl statusBarModeRepositoryImpl, DumpManager dumpManager, DisplayTracker displayTracker) {
            this.mJavaAdapter = javaAdapter;
            this.mDarkIconDispatcher = darkIconDispatcher;
            this.mBatteryController = batteryController;
            this.mNavModeController = navigationModeController;
            this.mStatusBarModeRepository = statusBarModeRepositoryImpl;
            this.mDumpManager = dumpManager;
            this.mDisplayTracker = displayTracker;
        }
    }

    public LightBarController(Context context, JavaAdapter javaAdapter, DarkIconDispatcher darkIconDispatcher, BatteryController batteryController, NavigationModeController navigationModeController, StatusBarModeRepositoryImpl statusBarModeRepositoryImpl, DumpManager dumpManager, DisplayTracker displayTracker) {
        this.mJavaAdapter = javaAdapter;
        this.mStatusBarIconController = (DarkIconDispatcherImpl) darkIconDispatcher;
        batteryController.addCallback(this);
        this.mStatusBarModeRepository = statusBarModeRepositoryImpl;
        this.mNavigationMode = navigationModeController.addListener(new NavigationModeController.ModeChangedListener() { // from class: com.android.systemui.statusbar.phone.LightBarController$$ExternalSyntheticLambda0
            @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
            public final void onNavigationModeChanged(int i) {
                LightBarController.this.mNavigationMode = i;
            }
        });
        int displayId = context.getDisplayId();
        displayTracker.getClass();
        if (displayId == 0) {
            dumpManager.getClass();
            DumpManager.registerDumpable$default(dumpManager, "LightBarController", this);
        }
    }

    public static boolean isLight(int i, int i2, int i3) {
        return (i2 == 0 || i2 == 6) && ((i & i3) != 0);
    }

    public final boolean animateChange() {
        int i;
        BiometricUnlockController biometricUnlockController = this.mBiometricUnlockController;
        return (biometricUnlockController == null || (i = biometricUnlockController.mMode) == 2 || i == 1) ? false : true;
    }

    @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback, com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("LightBarController: ");
        printWriter.print(" mAppearance=");
        printWriter.println(ViewDebug.flagsToString(InsetsFlags.class, "appearance", this.mAppearance));
        int length = this.mAppearanceRegions.length;
        for (int i = 0; i < length; i++) {
            boolean isLight = isLight(this.mAppearanceRegions[i].getAppearance(), this.mStatusBarMode, 8);
            printWriter.print(" stack #");
            printWriter.print(i);
            printWriter.print(": ");
            printWriter.print(this.mAppearanceRegions[i].toString());
            printWriter.print(" isLight=");
            printWriter.println(isLight);
        }
        printWriter.print(" mNavigationLight=");
        printWriter.println(this.mNavigationLight);
        printWriter.print(" mHasLightNavigationBar=");
        printWriter.println(this.mHasLightNavigationBar);
        printWriter.println();
        printWriter.print(" mStatusBarMode=");
        printWriter.print(this.mStatusBarMode);
        printWriter.print(" mNavigationBarMode=");
        printWriter.println(this.mNavigationBarMode);
        printWriter.println();
        printWriter.print(" mForceDarkForScrim=");
        printWriter.println(this.mForceDarkForScrim);
        printWriter.print(" mForceLightForScrim=");
        printWriter.println(this.mForceLightForScrim);
        printWriter.println();
        printWriter.print(" mQsCustomizing=");
        printWriter.println(this.mQsCustomizing);
        printWriter.print(" mQsExpanded=");
        printWriter.println(this.mQsExpanded);
        printWriter.print(" mBouncerVisible=");
        printWriter.println(this.mBouncerVisible);
        printWriter.print(" mGlobalActionsVisible=");
        printWriter.println(this.mGlobalActionsVisible);
        printWriter.print(" mDirectReplying=");
        printWriter.println(this.mDirectReplying);
        printWriter.print(" mNavbarColorManagedByIme=");
        printWriter.println(this.mNavbarColorManagedByIme);
        printWriter.println();
        printWriter.println(" Recent Calculation Logs:");
        printWriter.print("   ");
        printWriter.println((String) null);
        printWriter.print("   ");
        printWriter.println((String) null);
        printWriter.println();
        LightBarTransitionsController lightBarTransitionsController = this.mStatusBarIconController.mTransitionsController;
        if (lightBarTransitionsController != null) {
            printWriter.println(" StatusBarTransitionsController:");
            lightBarTransitionsController.dump(printWriter, strArr);
            printWriter.println();
        }
        if (this.mNavigationBarController != null) {
            printWriter.println(" NavigationBarTransitionsController:");
            this.mNavigationBarController.dump(printWriter, strArr);
            printWriter.println();
        }
    }

    public final void onNavigationBarAppearanceChanged(int i, int i2, boolean z, boolean z2) {
        if (((this.mAppearance ^ i) & 16) != 0 || z) {
            boolean z3 = this.mNavigationLight;
            boolean isLight = isLight(i, i2, 16);
            this.mHasLightNavigationBar = isLight;
            boolean z4 = false;
            boolean z5 = this.mDirectReplying && this.mNavbarColorManagedByIme;
            boolean z6 = this.mForceDarkForScrim && !z5;
            boolean z7 = this.mForceLightForScrim && !z5;
            boolean z8 = ((this.mQsCustomizing || this.mQsExpanded) && !this.mBouncerVisible) || this.mGlobalActionsVisible;
            if (((isLight && !z6) || z7) && !z8) {
                z4 = true;
            }
            this.mNavigationLight = z4;
            if (z4 != z3) {
                updateNavigation();
            }
        }
        this.mAppearance = i;
        this.mNavigationBarMode = i2;
        this.mNavbarColorManagedByIme = z2;
    }

    @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
    public final void onPowerSaveChanged(boolean z) {
        reevaluate();
    }

    public final void onStatusBarAppearanceChanged(AppearanceRegion[] appearanceRegionArr, boolean z, boolean z2, boolean z3) {
        int length = appearanceRegionArr.length;
        boolean z4 = this.mAppearanceRegions.length != length;
        for (int i = 0; i < length && !z4; i++) {
            z4 |= !appearanceRegionArr[i].equals(this.mAppearanceRegions[i]);
        }
        if (z4 || z || z2 || this.mIsCustomizingForBackNav) {
            this.mAppearanceRegions = appearanceRegionArr;
            updateStatus(appearanceRegionArr);
            this.mIsCustomizingForBackNav = false;
        }
        this.mNavbarColorManagedByIme = z3;
    }

    public final void reevaluate() {
        onStatusBarAppearanceChanged(this.mAppearanceRegions, true, true, this.mNavbarColorManagedByIme);
        onNavigationBarAppearanceChanged(this.mAppearance, this.mNavigationBarMode, true, this.mNavbarColorManagedByIme);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mJavaAdapter.alwaysCollectFlow(this.mStatusBarModeRepository.defaultDisplay.statusBarAppearance, new Consumer() { // from class: com.android.systemui.statusbar.phone.LightBarController$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                LightBarController lightBarController = LightBarController.this;
                StatusBarAppearance statusBarAppearance = (StatusBarAppearance) obj;
                lightBarController.getClass();
                if (statusBarAppearance == null) {
                    return;
                }
                int transitionModeInt = statusBarAppearance.mode.toTransitionModeInt();
                boolean z = lightBarController.mStatusBarMode != transitionModeInt;
                lightBarController.mStatusBarMode = transitionModeInt;
                BoundsPair boundsPair = lightBarController.mStatusBarBounds;
                BoundsPair boundsPair2 = statusBarAppearance.bounds;
                boolean z2 = !boundsPair.equals(boundsPair2);
                lightBarController.mStatusBarBounds = boundsPair2;
                lightBarController.onStatusBarAppearanceChanged((AppearanceRegion[]) statusBarAppearance.appearanceRegions.toArray(new AppearanceRegion[0]), z, z2, statusBarAppearance.navbarColorManagedByIme);
            }
        });
    }

    public final void updateNavigation() {
        LightBarTransitionsController lightBarTransitionsController = this.mNavigationBarController;
        if (lightBarTransitionsController != null) {
            if (!QuickStepContract.isGesturalMode(this.mNavigationMode)) {
                lightBarTransitionsController.getClass();
            } else if (!lightBarTransitionsController.mNavigationButtonsForcedVisible) {
                return;
            }
            this.mNavigationBarController.setIconsDark(this.mNavigationLight, animateChange());
        }
    }

    public final void updateStatus(AppearanceRegion[] appearanceRegionArr) {
        int length = appearanceRegionArr.length;
        ArrayList arrayList = new ArrayList();
        for (AppearanceRegion appearanceRegion : appearanceRegionArr) {
            if (isLight(appearanceRegion.getAppearance(), this.mStatusBarMode, 8)) {
                arrayList.add(appearanceRegion.getBounds());
            }
        }
        boolean isEmpty = arrayList.isEmpty();
        DarkIconDispatcherImpl darkIconDispatcherImpl = this.mStatusBarIconController;
        if (isEmpty) {
            darkIconDispatcherImpl.mTransitionsController.setIconsDark(false, animateChange());
        } else if (arrayList.size() == length) {
            darkIconDispatcherImpl.setIconsDarkArea(null);
            darkIconDispatcherImpl.mTransitionsController.setIconsDark(true, animateChange());
        } else {
            darkIconDispatcherImpl.setIconsDarkArea(arrayList);
            darkIconDispatcherImpl.mTransitionsController.setIconsDark(true, animateChange());
        }
    }
}
