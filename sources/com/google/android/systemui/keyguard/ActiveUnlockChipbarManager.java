package com.google.android.systemui.keyguard;

import android.text.TextUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.TrustGrantFlags;
import com.android.systemui.CoreStartable;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.common.shared.model.TintedIcon;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.temporarydisplay.ViewPriority;
import com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator;
import com.android.systemui.temporarydisplay.chipbar.ChipbarInfo;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActiveUnlockChipbarManager implements CoreStartable {
    public final ChipbarCoordinator chipbarCoordinator;
    public final GlobalSettings globalSettings;
    public final KeyguardStateController keyguardStateController;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final SessionTracker sessionTracker;
    public final ActiveUnlockChipbarManager$keyguardStateControllerCallback$1 keyguardStateControllerCallback = new ActiveUnlockChipbarManager$keyguardStateControllerCallback$1();
    public final ActiveUnlockChipbarManager$keyguardUpdateMonitorCallback$1 keyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.google.android.systemui.keyguard.ActiveUnlockChipbarManager$keyguardUpdateMonitorCallback$1
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onTrustGrantedForCurrentUser(boolean z, boolean z2, TrustGrantFlags trustGrantFlags, String str) {
            if (TextUtils.isEmpty(str) || !z2) {
                return;
            }
            ActiveUnlockChipbarManager activeUnlockChipbarManager = ActiveUnlockChipbarManager.this;
            if (activeUnlockChipbarManager.globalSettings.getInt(0, "chip_all_watch_unlocks") == 0 && !z) {
                return;
            }
            Intrinsics.checkNotNull(str);
            TintedIcon tintedIcon = new TintedIcon(new Icon.Resource(R.drawable.ic_watch, null), Integer.valueOf(android.R.^attr-private.materialColorOnSecondaryFixed));
            Text.Loaded loaded = new Text.Loaded(str);
            int i = activeUnlockChipbarManager.globalSettings.getInt(1500, "chip_duration");
            ViewPriority viewPriority = ViewPriority.NORMAL;
            activeUnlockChipbarManager.chipbarCoordinator.displayView(new ChipbarInfo(tintedIcon, loaded, "Unlock Chip", "UNLOCK_CHIP", i, "active_unlock", activeUnlockChipbarManager.sessionTracker.getSessionId(1)));
        }
    };

    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.android.systemui.keyguard.ActiveUnlockChipbarManager$keyguardUpdateMonitorCallback$1] */
    public ActiveUnlockChipbarManager(KeyguardUpdateMonitor keyguardUpdateMonitor, ChipbarCoordinator chipbarCoordinator, KeyguardStateController keyguardStateController, GlobalSettings globalSettings, SessionTracker sessionTracker) {
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.chipbarCoordinator = chipbarCoordinator;
        this.keyguardStateController = keyguardStateController;
        this.globalSettings = globalSettings;
        this.sessionTracker = sessionTracker;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        ((KeyguardStateControllerImpl) this.keyguardStateController).addCallback(this.keyguardStateControllerCallback);
        this.keyguardUpdateMonitor.registerCallback(this.keyguardUpdateMonitorCallback);
    }
}
