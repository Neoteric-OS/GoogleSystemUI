package com.google.android.systemui.dreams;

import android.service.dreams.DreamService;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class LockscreenWallpaperDreamService extends DreamService {
    public final FeatureFlagsClassic mFeatureFlags;

    public LockscreenWallpaperDreamService(KeyguardInteractor keyguardInteractor, KeyguardViewMediator keyguardViewMediator, FeatureFlagsClassic featureFlagsClassic) {
        this.mFeatureFlags = featureFlagsClassic;
    }

    @Override // android.service.dreams.DreamService, android.app.Service
    public final void onCreate() {
        super.onCreate();
        setWindowless(true);
    }

    @Override // android.service.dreams.DreamService
    public final void onDreamingStarted() {
        super.onDreamingStarted();
        FeatureFlagsClassic featureFlagsClassic = this.mFeatureFlags;
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        featureFlagsClassic.getClass();
    }

    @Override // android.service.dreams.DreamService
    public final void onDreamingStopped() {
        super.onDreamingStopped();
        FeatureFlagsClassic featureFlagsClassic = this.mFeatureFlags;
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        featureFlagsClassic.getClass();
    }
}
