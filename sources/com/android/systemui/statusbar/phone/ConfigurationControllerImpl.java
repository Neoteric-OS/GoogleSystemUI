package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.LocaleList;
import com.android.systemui.statusbar.policy.ConfigurationController;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConfigurationControllerImpl implements ConfigurationController {
    public final Context context;
    public int density;
    public float fontScale;
    public final boolean inCarMode;
    public int layoutDirection;
    public LocaleList localeList;
    public final Rect maxBounds;
    public int orientation;
    public int smallestScreenWidth;
    public int uiMode;
    public final List listeners = new ArrayList();
    public final Configuration lastConfig = new Configuration();

    public ConfigurationControllerImpl(Context context) {
        this.context = context;
        Rect rect = new Rect();
        this.maxBounds = rect;
        Configuration configuration = context.getResources().getConfiguration();
        this.fontScale = configuration.fontScale;
        this.density = configuration.densityDpi;
        this.smallestScreenWidth = configuration.smallestScreenWidthDp;
        rect.set(configuration.windowConfiguration.getMaxBounds());
        int i = configuration.uiMode;
        this.inCarMode = (i & 15) == 3;
        this.uiMode = i & 48;
        this.localeList = configuration.getLocales();
        this.layoutDirection = configuration.getLayoutDirection();
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        ConfigurationController.ConfigurationListener configurationListener = (ConfigurationController.ConfigurationListener) obj;
        synchronized (this.listeners) {
            this.listeners.add(configurationListener);
        }
        configurationListener.onDensityOrFontScaleChanged();
    }

    public final String getNightModeName() {
        int i = this.uiMode & 48;
        return i != 0 ? i != 16 ? i != 32 ? "err" : "night" : "day" : "undefined";
    }

    public final void onConfigurationChanged(Configuration configuration) {
        ArrayList<ConfigurationController.ConfigurationListener> arrayList;
        synchronized (this.listeners) {
            arrayList = new ArrayList(this.listeners);
        }
        for (ConfigurationController.ConfigurationListener configurationListener : arrayList) {
            if (this.listeners.contains(configurationListener)) {
                configurationListener.onConfigChanged(configuration);
            }
        }
        float f = configuration.fontScale;
        int i = configuration.densityDpi;
        int i2 = configuration.uiMode & 48;
        boolean z = i2 != this.uiMode;
        if (i != this.density || f != this.fontScale || (this.inCarMode && z)) {
            for (ConfigurationController.ConfigurationListener configurationListener2 : arrayList) {
                if (this.listeners.contains(configurationListener2)) {
                    configurationListener2.onDensityOrFontScaleChanged();
                }
            }
            this.density = i;
            this.fontScale = f;
        }
        int i3 = configuration.smallestScreenWidthDp;
        if (i3 != this.smallestScreenWidth) {
            this.smallestScreenWidth = i3;
            for (ConfigurationController.ConfigurationListener configurationListener3 : arrayList) {
                if (this.listeners.contains(configurationListener3)) {
                    configurationListener3.onSmallestScreenWidthChanged();
                }
            }
        }
        Rect maxBounds = configuration.windowConfiguration.getMaxBounds();
        if (!maxBounds.equals(this.maxBounds)) {
            this.maxBounds.set(maxBounds);
            for (ConfigurationController.ConfigurationListener configurationListener4 : arrayList) {
                if (this.listeners.contains(configurationListener4)) {
                    configurationListener4.onMaxBoundsChanged();
                }
            }
        }
        LocaleList locales = configuration.getLocales();
        if (!locales.equals(this.localeList)) {
            this.localeList = locales;
            for (ConfigurationController.ConfigurationListener configurationListener5 : arrayList) {
                if (this.listeners.contains(configurationListener5)) {
                    configurationListener5.onLocaleListChanged();
                }
            }
        }
        if (z) {
            this.context.getTheme().applyStyle(this.context.getThemeResId(), true);
            this.uiMode = i2;
            for (ConfigurationController.ConfigurationListener configurationListener6 : arrayList) {
                if (this.listeners.contains(configurationListener6)) {
                    configurationListener6.onUiModeChanged();
                }
            }
        }
        if (this.layoutDirection != configuration.getLayoutDirection()) {
            this.layoutDirection = configuration.getLayoutDirection();
            for (ConfigurationController.ConfigurationListener configurationListener7 : arrayList) {
                if (this.listeners.contains(configurationListener7)) {
                    configurationListener7.onLayoutDirectionChanged(this.layoutDirection == 1);
                }
            }
        }
        if ((this.lastConfig.updateFrom(configuration) & Integer.MIN_VALUE) != 0) {
            for (ConfigurationController.ConfigurationListener configurationListener8 : arrayList) {
                if (this.listeners.contains(configurationListener8)) {
                    configurationListener8.onThemeChanged();
                }
            }
        }
        int i4 = configuration.orientation;
        if (this.orientation != i4) {
            this.orientation = i4;
            for (ConfigurationController.ConfigurationListener configurationListener9 : arrayList) {
                if (this.listeners.contains(configurationListener9)) {
                    configurationListener9.onOrientationChanged();
                }
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        ConfigurationController.ConfigurationListener configurationListener = (ConfigurationController.ConfigurationListener) obj;
        synchronized (this.listeners) {
            this.listeners.remove(configurationListener);
        }
    }
}
