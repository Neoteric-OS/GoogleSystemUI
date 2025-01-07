package com.android.systemui.accessibility.floatingmenu;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.internal.accessibility.dialog.AccessibilityTargetHelper;
import com.android.systemui.util.settings.SecureSettings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MenuInfoRepository {
    public static final boolean DEBUG;
    public final MenuInfoRepository$$ExternalSyntheticLambda0 mA11yServicesStateChangeListener;
    final ComponentCallbacks mComponentCallbacks;
    public final Configuration mConfiguration;
    public final Context mContext;
    final ContentObserver mMenuFadeOutContentObserver;
    final ContentObserver mMenuSizeContentObserver;
    final ContentObserver mMenuTargetFeaturesContentObserver;
    public Position mPercentagePosition;
    public final SecureSettings mSecureSettings;
    public final MenuViewModel mSettingsContentsCallback;

    static {
        DEBUG = Log.isLoggable("MenuInfoRepository", 3) || Build.IS_DEBUGGABLE;
    }

    public MenuInfoRepository(Context context, AccessibilityManager accessibilityManager, MenuViewModel menuViewModel, SecureSettings secureSettings) {
        Position position;
        new AccessibilityManager.AccessibilityServicesStateChangeListener() { // from class: com.android.systemui.accessibility.floatingmenu.MenuInfoRepository$$ExternalSyntheticLambda0
            @Override // android.view.accessibility.AccessibilityManager.AccessibilityServicesStateChangeListener
            public final void onAccessibilityServicesStateChanged(AccessibilityManager accessibilityManager2) {
                MenuInfoRepository.this.onTargetFeaturesChanged();
            }
        };
        Handler handler = new Handler(Looper.getMainLooper());
        final int i = 0;
        this.mMenuTargetFeaturesContentObserver = new ContentObserver(this, handler) { // from class: com.android.systemui.accessibility.floatingmenu.MenuInfoRepository.1
            public final /* synthetic */ MenuInfoRepository this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i) {
                    case 0:
                        this.this$0.onTargetFeaturesChanged();
                        break;
                    case 1:
                        MenuInfoRepository menuInfoRepository = this.this$0;
                        menuInfoRepository.mSettingsContentsCallback.mSizeTypeData.setValue(Integer.valueOf(menuInfoRepository.mSecureSettings.getIntForUser("accessibility_floating_menu_size", 0, -2)));
                        break;
                    default:
                        MenuInfoRepository menuInfoRepository2 = this.this$0;
                        MenuViewModel menuViewModel2 = menuInfoRepository2.mSettingsContentsCallback;
                        SecureSettings secureSettings2 = menuInfoRepository2.mSecureSettings;
                        menuViewModel2.mFadeEffectInfoData.setValue(new MenuFadeEffectInfo(secureSettings2.getFloatForUser(0.55f, -2, "accessibility_floating_menu_opacity"), secureSettings2.getIntForUser("accessibility_floating_menu_fade_enabled", 1, -2) == 1));
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mMenuSizeContentObserver = new ContentObserver(this, handler) { // from class: com.android.systemui.accessibility.floatingmenu.MenuInfoRepository.1
            public final /* synthetic */ MenuInfoRepository this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i2) {
                    case 0:
                        this.this$0.onTargetFeaturesChanged();
                        break;
                    case 1:
                        MenuInfoRepository menuInfoRepository = this.this$0;
                        menuInfoRepository.mSettingsContentsCallback.mSizeTypeData.setValue(Integer.valueOf(menuInfoRepository.mSecureSettings.getIntForUser("accessibility_floating_menu_size", 0, -2)));
                        break;
                    default:
                        MenuInfoRepository menuInfoRepository2 = this.this$0;
                        MenuViewModel menuViewModel2 = menuInfoRepository2.mSettingsContentsCallback;
                        SecureSettings secureSettings2 = menuInfoRepository2.mSecureSettings;
                        menuViewModel2.mFadeEffectInfoData.setValue(new MenuFadeEffectInfo(secureSettings2.getFloatForUser(0.55f, -2, "accessibility_floating_menu_opacity"), secureSettings2.getIntForUser("accessibility_floating_menu_fade_enabled", 1, -2) == 1));
                        break;
                }
            }
        };
        final int i3 = 2;
        this.mMenuFadeOutContentObserver = new ContentObserver(this, handler) { // from class: com.android.systemui.accessibility.floatingmenu.MenuInfoRepository.1
            public final /* synthetic */ MenuInfoRepository this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                switch (i3) {
                    case 0:
                        this.this$0.onTargetFeaturesChanged();
                        break;
                    case 1:
                        MenuInfoRepository menuInfoRepository = this.this$0;
                        menuInfoRepository.mSettingsContentsCallback.mSizeTypeData.setValue(Integer.valueOf(menuInfoRepository.mSecureSettings.getIntForUser("accessibility_floating_menu_size", 0, -2)));
                        break;
                    default:
                        MenuInfoRepository menuInfoRepository2 = this.this$0;
                        MenuViewModel menuViewModel2 = menuInfoRepository2.mSettingsContentsCallback;
                        SecureSettings secureSettings2 = menuInfoRepository2.mSecureSettings;
                        menuViewModel2.mFadeEffectInfoData.setValue(new MenuFadeEffectInfo(secureSettings2.getFloatForUser(0.55f, -2, "accessibility_floating_menu_opacity"), secureSettings2.getIntForUser("accessibility_floating_menu_fade_enabled", 1, -2) == 1));
                        break;
                }
            }
        };
        this.mComponentCallbacks = new ComponentCallbacks() { // from class: com.android.systemui.accessibility.floatingmenu.MenuInfoRepository.4
            @Override // android.content.ComponentCallbacks
            public final void onConfigurationChanged(Configuration configuration) {
                int diff = configuration.diff(MenuInfoRepository.this.mConfiguration);
                if (MenuInfoRepository.DEBUG) {
                    Log.d("MenuInfoRepository", "onConfigurationChanged = " + Configuration.configurationDiffToString(diff));
                }
                if ((diff & 4) != 0) {
                    MenuInfoRepository.this.onTargetFeaturesChanged();
                }
                MenuInfoRepository.this.mConfiguration.setTo(configuration);
            }

            @Override // android.content.ComponentCallbacks
            public final void onLowMemory() {
            }
        };
        this.mContext = context;
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        this.mConfiguration = configuration;
        this.mSettingsContentsCallback = menuViewModel;
        this.mSecureSettings = secureSettings;
        String string = context.getSharedPreferences(context.getPackageName(), 0).getString("AccessibilityFloatingMenuPosition", null);
        float f = configuration.getLayoutDirection() == 1 ? 0.0f : 1.0f;
        if (TextUtils.isEmpty(string)) {
            position = new Position(f, 0.77f);
        } else {
            TextUtils.SimpleStringSplitter simpleStringSplitter = Position.sStringCommaSplitter;
            simpleStringSplitter.setString(string);
            if (!simpleStringSplitter.hasNext()) {
                throw new IllegalArgumentException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Invalid Position string: ", string));
            }
            position = new Position(Float.parseFloat(simpleStringSplitter.next()), Float.parseFloat(simpleStringSplitter.next()));
        }
        this.mPercentagePosition = position;
    }

    public final void onTargetFeaturesChanged() {
        this.mSettingsContentsCallback.mTargetFeaturesData.setValue(AccessibilityTargetHelper.getTargets(this.mContext, 1));
    }
}
