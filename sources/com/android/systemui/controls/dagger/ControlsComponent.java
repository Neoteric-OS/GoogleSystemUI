package com.android.systemui.controls.dagger;

import com.android.internal.widget.LockPatternUtils;
import com.android.systemui.controls.controller.ControlsTileResourceConfiguration;
import com.android.systemui.controls.controller.ControlsTileResourceConfigurationImpl;
import com.android.systemui.controls.settings.ControlsSettingsRepository;
import com.android.systemui.controls.settings.ControlsSettingsRepositoryImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import dagger.Lazy;
import java.util.Optional;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsComponent {
    public final ReadonlyStateFlow canShowWhileLockedSetting;
    public final Optional controlsController;
    public final Optional controlsListingController;
    public final ControlsTileResourceConfiguration controlsTileResourceConfiguration;
    public final Optional controlsUiController;
    public final boolean featureEnabled;
    public final KeyguardStateController keyguardStateController;
    public final LockPatternUtils lockPatternUtils;
    public final UserTracker userTracker;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Visibility {
        public static final /* synthetic */ Visibility[] $VALUES;
        public static final Visibility AVAILABLE;
        public static final Visibility AVAILABLE_AFTER_UNLOCK;
        public static final Visibility UNAVAILABLE;

        static {
            Visibility visibility = new Visibility("AVAILABLE", 0);
            AVAILABLE = visibility;
            Visibility visibility2 = new Visibility("AVAILABLE_AFTER_UNLOCK", 1);
            AVAILABLE_AFTER_UNLOCK = visibility2;
            Visibility visibility3 = new Visibility("UNAVAILABLE", 2);
            UNAVAILABLE = visibility3;
            Visibility[] visibilityArr = {visibility, visibility2, visibility3};
            $VALUES = visibilityArr;
            EnumEntriesKt.enumEntries(visibilityArr);
        }

        public static Visibility valueOf(String str) {
            return (Visibility) Enum.valueOf(Visibility.class, str);
        }

        public static Visibility[] values() {
            return (Visibility[]) $VALUES.clone();
        }
    }

    public ControlsComponent(boolean z, Lazy lazy, Lazy lazy2, Lazy lazy3, LockPatternUtils lockPatternUtils, KeyguardStateController keyguardStateController, UserTracker userTracker, ControlsSettingsRepository controlsSettingsRepository, Optional optional) {
        this.featureEnabled = z;
        this.lockPatternUtils = lockPatternUtils;
        this.keyguardStateController = keyguardStateController;
        this.userTracker = userTracker;
        this.controlsController = optionalIf(z, lazy);
        this.controlsUiController = optionalIf(z, lazy2);
        this.controlsListingController = optionalIf(z, lazy3);
        this.canShowWhileLockedSetting = ((ControlsSettingsRepositoryImpl) controlsSettingsRepository).canShowControlsInLockscreen;
        this.controlsTileResourceConfiguration = (ControlsTileResourceConfiguration) optional.orElse(new ControlsTileResourceConfigurationImpl());
    }

    public static Optional optionalIf(boolean z, Lazy lazy) {
        if (z) {
            Optional of = Optional.of(lazy.get());
            Intrinsics.checkNotNull(of);
            return of;
        }
        Optional empty = Optional.empty();
        Intrinsics.checkNotNull(empty);
        return empty;
    }

    public final Visibility getVisibility() {
        return !this.featureEnabled ? Visibility.UNAVAILABLE : this.lockPatternUtils.getStrongAuthForUser(((UserTrackerImpl) this.userTracker).getUserHandle().getIdentifier()) == 1 ? Visibility.AVAILABLE_AFTER_UNLOCK : (((Boolean) this.canShowWhileLockedSetting.getValue()).booleanValue() || this.keyguardStateController.isUnlocked()) ? Visibility.AVAILABLE : Visibility.AVAILABLE_AFTER_UNLOCK;
    }
}
