package com.android.systemui.accessibility.floatingmenu;

import android.content.Context;
import android.view.accessibility.AccessibilityManager;
import androidx.lifecycle.MutableLiveData;
import com.android.systemui.util.settings.SecureSettings;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MenuViewModel {
    public final MenuInfoRepository mInfoRepository;
    public final MutableLiveData mTargetFeaturesData = new MutableLiveData();
    public final MutableLiveData mSizeTypeData = new MutableLiveData();
    public final MutableLiveData mFadeEffectInfoData = new MutableLiveData();
    public final MutableLiveData mMoveToTuckedData = new MutableLiveData();
    public final MutableLiveData mDockTooltipData = new MutableLiveData();
    public final MutableLiveData mMigrationTooltipData = new MutableLiveData();
    public final MutableLiveData mPercentagePositionData = new MutableLiveData();

    public MenuViewModel(Context context, AccessibilityManager accessibilityManager, SecureSettings secureSettings) {
        this.mInfoRepository = new MenuInfoRepository(context, accessibilityManager, this, secureSettings);
    }

    public final MutableLiveData getMigrationTooltipVisibilityData() {
        MutableLiveData mutableLiveData = this.mMigrationTooltipData;
        Objects.requireNonNull(mutableLiveData);
        mutableLiveData.setValue(Boolean.valueOf(this.mInfoRepository.mSecureSettings.getIntForUser("accessibility_floating_menu_migration_tooltip_prompt", 0, -2) == 1));
        return mutableLiveData;
    }
}
