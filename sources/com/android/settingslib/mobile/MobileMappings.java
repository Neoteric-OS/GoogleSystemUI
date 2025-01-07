package com.android.settingslib.mobile;

import android.content.Context;
import android.content.res.Resources;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class MobileMappings {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Config {
        public boolean alwaysShowCdmaRssi;
        public boolean alwaysShowDataRatIcon;
        public boolean hideLtePlus;
        public boolean hspaDataDistinguishable;
        public boolean show4gFor3g;
        public boolean show4gForLte;
        public boolean show4glteForLte;
        public boolean showAtLeast3G;

        public static Config readConfig(Context context) {
            Config config = new Config();
            config.showAtLeast3G = false;
            config.show4gFor3g = false;
            config.alwaysShowCdmaRssi = false;
            config.show4gForLte = false;
            config.show4glteForLte = false;
            config.hideLtePlus = false;
            config.alwaysShowDataRatIcon = false;
            Resources resources = context.getResources();
            config.showAtLeast3G = resources.getBoolean(R.bool.config_showMin3G);
            config.alwaysShowCdmaRssi = resources.getBoolean(android.R.bool.config_alwaysUseCdmaRssi);
            config.hspaDataDistinguishable = resources.getBoolean(R.bool.config_hspa_data_distinguishable);
            CarrierConfigManager carrierConfigManager = (CarrierConfigManager) context.getSystemService("carrier_config");
            SubscriptionManager.from(context);
            PersistableBundle configForSubId = carrierConfigManager == null ? null : carrierConfigManager.getConfigForSubId(SubscriptionManager.getDefaultDataSubscriptionId());
            if (configForSubId != null) {
                config.alwaysShowDataRatIcon = configForSubId.getBoolean("always_show_data_rat_icon_bool");
                config.show4gForLte = configForSubId.getBoolean("show_4g_for_lte_data_icon_bool");
                config.show4glteForLte = configForSubId.getBoolean("show_4glte_for_lte_data_icon_bool");
                config.show4gFor3g = configForSubId.getBoolean("show_4g_for_3g_data_icon_bool");
                config.hideLtePlus = configForSubId.getBoolean("hide_lte_plus_data_icon_bool");
            }
            return config;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Map mapIconSets(com.android.settingslib.mobile.MobileMappings.Config r9) {
        /*
            Method dump skipped, instructions count: 362
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.mobile.MobileMappings.mapIconSets(com.android.settingslib.mobile.MobileMappings$Config):java.util.Map");
    }

    public static String toDisplayIconKey(int i) {
        if (i == 1) {
            return Integer.toString(13) + "_CA";
        }
        if (i == 2) {
            return Integer.toString(13) + "_CA_Plus";
        }
        if (i == 3) {
            return Integer.toString(20);
        }
        if (i != 5) {
            return "unsupported";
        }
        return Integer.toString(20) + "_Plus";
    }
}
