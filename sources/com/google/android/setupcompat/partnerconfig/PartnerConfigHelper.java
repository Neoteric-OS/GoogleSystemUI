package com.google.android.setupcompat.partnerconfig;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import androidx.window.embedding.ActivityEmbeddingController;
import androidx.window.embedding.EmbeddingCompat;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.util.BuildCompatUtils;
import java.util.EnumMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PartnerConfigHelper {
    public static final String EMBEDDED_ACTIVITY_RESOURCE_SUFFIX = "_embedded_activity";
    static final String FORCE_TWO_PANE_SUFFIX = "_two_pane";
    public static final String GET_SUW_DEFAULT_THEME_STRING_METHOD = "suwDefaultThemeString";
    public static final String IS_DYNAMIC_COLOR_ENABLED_METHOD = "isDynamicColorEnabled";
    public static final String IS_EMBEDDED_ACTIVITY_ONE_PANE_ENABLED_METHOD = "isEmbeddedActivityOnePaneEnabled";
    public static final String IS_EXTENDED_PARTNER_CONFIG_ENABLED_METHOD = "isExtendedPartnerConfigEnabled";
    public static final String IS_FONT_WEIGHT_ENABLED_METHOD = "isFontWeightEnabled";
    public static final String IS_FORCE_TWO_PANE_ENABLED_METHOD = "isForceTwoPaneEnabled";
    public static final String IS_FULL_DYNAMIC_COLOR_ENABLED_METHOD = "isFullDynamicColorEnabled";
    public static final String IS_MATERIAL_YOU_STYLE_ENABLED_METHOD = "IsMaterialYouStyleEnabled";
    public static final String IS_NEUTRAL_BUTTON_STYLE_ENABLED_METHOD = "isNeutralButtonStyleEnabled";
    public static final String IS_SUW_DAY_NIGHT_ENABLED_METHOD = "isSuwDayNightEnabled";
    public static final String KEY_FALLBACK_CONFIG = "fallbackConfig";
    public static final String MATERIAL_YOU_RESOURCE_SUFFIX = "_material_you";
    public static final String SUW_GET_PARTNER_CONFIG_METHOD = "getOverlayConfig";
    public static final String SUW_PACKAGE_NAME = "com.google.android.setupwizard";
    public static Bundle applyDynamicColorBundle = null;
    public static Bundle applyEmbeddedActivityOnePaneBundle = null;
    public static Bundle applyExtendedPartnerConfigBundle = null;
    public static Bundle applyFontWeightBundle = null;
    public static Bundle applyForceTwoPaneBundle = null;
    public static Bundle applyFullDynamicColorBundle = null;
    public static Bundle applyMaterialYouConfigBundle = null;
    public static Bundle applyNeutralButtonStyleBundle = null;
    static Bundle applyTransitionBundle = null;
    public static AnonymousClass1 contentObserver = null;
    public static PartnerConfigHelper instance = null;
    public static boolean savedConfigEmbeddedActivityMode = false;
    public static int savedConfigUiMode = 0;
    public static int savedOrientation = 1;
    public static int savedScreenHeight;
    public static int savedScreenWidth;
    static Bundle suwDayNightEnabledBundle;
    public static Bundle suwDefaultThemeBundle;
    final EnumMap partnerResourceCache;
    Bundle resultBundle;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.setupcompat.partnerconfig.PartnerConfigHelper$1, reason: invalid class name */
    public final class AnonymousClass1 extends ContentObserver {
        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            super.onChange(z);
            PartnerConfigHelper.resetInstance();
        }
    }

    public PartnerConfigHelper(Context context) {
        this.resultBundle = null;
        EnumMap enumMap = new EnumMap(PartnerConfig.class);
        this.partnerResourceCache = enumMap;
        Bundle bundle = this.resultBundle;
        if (bundle == null || bundle.isEmpty()) {
            try {
                this.resultBundle = context.getContentResolver().call(getContentUri(), SUW_GET_PARTNER_CONFIG_METHOD, (String) null, (Bundle) null);
                enumMap.clear();
                StringBuilder sb = new StringBuilder("PartnerConfigsBundle=");
                Bundle bundle2 = this.resultBundle;
                sb.append(bundle2 != null ? Integer.valueOf(bundle2.size()) : "(null)");
                Log.i("PartnerConfigHelper", sb.toString());
            } catch (IllegalArgumentException | SecurityException unused) {
                Log.w("PartnerConfigHelper", "Fail to get config from suw provider");
            }
        }
        if (isSetupWizardDayNightEnabled(context)) {
            if (contentObserver != null) {
                try {
                    context.getContentResolver().unregisterContentObserver(contentObserver);
                    contentObserver = null;
                } catch (IllegalArgumentException | NullPointerException | SecurityException e) {
                    Log.w("PartnerConfigHelper", "Failed to unregister content observer: " + e);
                }
            }
            Uri contentUri = getContentUri();
            try {
                contentObserver = new AnonymousClass1(null);
                context.getContentResolver().registerContentObserver(contentUri, true, contentObserver);
            } catch (IllegalArgumentException | NullPointerException | SecurityException e2) {
                Log.w("PartnerConfigHelper", "Failed to register content observer for " + contentUri + ": " + e2);
            }
        }
    }

    public static synchronized PartnerConfigHelper get(Context context) {
        PartnerConfigHelper partnerConfigHelper;
        synchronized (PartnerConfigHelper.class) {
            try {
                if (!isValidInstance(context)) {
                    instance = new PartnerConfigHelper(context);
                }
                partnerConfigHelper = instance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return partnerConfigHelper;
    }

    public static Uri getContentUri() {
        return new Uri.Builder().scheme("content").authority("com.google.android.setupwizard.partner").build();
    }

    public static TypedValue getTypedValueFromResource(int i, Resources resources) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i, typedValue, true);
        if (typedValue.type == 5) {
            return typedValue;
        }
        throw new Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(i) + " type #0x" + Integer.toHexString(typedValue.type) + " is not valid");
    }

    public static boolean isEmbeddedActivityOnePaneEnabled(Context context) {
        if (applyEmbeddedActivityOnePaneBundle == null) {
            try {
                applyEmbeddedActivityOnePaneBundle = context.getContentResolver().call(getContentUri(), IS_EMBEDDED_ACTIVITY_ONE_PANE_ENABLED_METHOD, (String) null, (Bundle) null);
            } catch (IllegalArgumentException | SecurityException unused) {
                Log.w("PartnerConfigHelper", "SetupWizard one-pane support in embedded activity status unknown; return as false.");
                applyEmbeddedActivityOnePaneBundle = null;
                return false;
            }
        }
        Bundle bundle = applyEmbeddedActivityOnePaneBundle;
        return bundle != null && bundle.getBoolean(IS_EMBEDDED_ACTIVITY_ONE_PANE_ENABLED_METHOD, false);
    }

    public static boolean isSetupWizardDayNightEnabled(Context context) {
        if (suwDayNightEnabledBundle == null) {
            try {
                suwDayNightEnabledBundle = context.getContentResolver().call(getContentUri(), IS_SUW_DAY_NIGHT_ENABLED_METHOD, (String) null, (Bundle) null);
            } catch (IllegalArgumentException | SecurityException unused) {
                Log.w("PartnerConfigHelper", "SetupWizard DayNight supporting status unknown; return as false.");
                suwDayNightEnabledBundle = null;
                return false;
            }
        }
        Bundle bundle = suwDayNightEnabledBundle;
        return bundle != null && bundle.getBoolean(IS_SUW_DAY_NIGHT_ENABLED_METHOD, false);
    }

    public static boolean isValidInstance(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        if (instance == null) {
            savedConfigEmbeddedActivityMode = isEmbeddedActivityOnePaneEnabled(context) && BuildCompatUtils.isAtLeastU();
            savedConfigUiMode = configuration.uiMode & 48;
            savedOrientation = configuration.orientation;
            savedScreenWidth = configuration.screenWidthDp;
            savedScreenHeight = configuration.screenHeightDp;
            return false;
        }
        boolean z = isSetupWizardDayNightEnabled(context) && (configuration.uiMode & 48) != savedConfigUiMode;
        boolean z2 = isEmbeddedActivityOnePaneEnabled(context) && BuildCompatUtils.isAtLeastU();
        if (!z && z2 == savedConfigEmbeddedActivityMode && configuration.orientation == savedOrientation && configuration.screenWidthDp == savedScreenWidth && configuration.screenHeightDp == savedScreenHeight) {
            return true;
        }
        savedConfigUiMode = configuration.uiMode & 48;
        savedOrientation = configuration.orientation;
        savedScreenHeight = configuration.screenHeightDp;
        savedScreenWidth = configuration.screenWidthDp;
        resetInstance();
        return false;
    }

    public static Activity lookupActivityFromContext(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return lookupActivityFromContext(((ContextWrapper) context).getBaseContext());
        }
        throw new IllegalArgumentException("Cannot find instance of Activity in parent tree");
    }

    public static synchronized void resetInstance() {
        synchronized (PartnerConfigHelper.class) {
            instance = null;
            suwDayNightEnabledBundle = null;
            applyExtendedPartnerConfigBundle = null;
            applyMaterialYouConfigBundle = null;
            applyDynamicColorBundle = null;
            applyFullDynamicColorBundle = null;
            applyNeutralButtonStyleBundle = null;
            applyEmbeddedActivityOnePaneBundle = null;
            suwDefaultThemeBundle = null;
            applyTransitionBundle = null;
            applyForceTwoPaneBundle = null;
        }
    }

    public final float getDimension(Context context, PartnerConfig partnerConfig) {
        if (partnerConfig.getResourceType() != PartnerConfig.ResourceType.DIMENSION) {
            throw new IllegalArgumentException("Not a dimension resource");
        }
        if (this.partnerResourceCache.containsKey(partnerConfig)) {
            return ((TypedValue) this.partnerResourceCache.get(partnerConfig)).getDimension(context.getResources().getDisplayMetrics());
        }
        float f = 0.0f;
        try {
            ResourceEntry resourceEntryFromKey = getResourceEntryFromKey(context, partnerConfig.getResourceName());
            Resources resources = resourceEntryFromKey.resources;
            int i = resourceEntryFromKey.resourceId;
            f = resources.getDimension(i);
            this.partnerResourceCache.put((EnumMap) partnerConfig, (PartnerConfig) getTypedValueFromResource(i, resources));
            return ((TypedValue) this.partnerResourceCache.get(partnerConfig)).getDimension(context.getResources().getDisplayMetrics());
        } catch (Resources.NotFoundException | NullPointerException unused) {
            return f;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0179  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.google.android.setupcompat.partnerconfig.ResourceEntry getResourceEntryFromKey(android.content.Context r7, java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 399
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.getResourceEntryFromKey(android.content.Context, java.lang.String):com.google.android.setupcompat.partnerconfig.ResourceEntry");
    }

    public boolean isActivityEmbedded(Context context) {
        try {
            Activity lookupActivityFromContext = lookupActivityFromContext(context);
            if (!isEmbeddedActivityOnePaneEnabled(context)) {
                return false;
            }
            EmbeddingCompat embeddingCompat = ActivityEmbeddingController.getInstance(lookupActivityFromContext).backend.embeddingExtension;
            return embeddingCompat != null ? embeddingCompat.embeddingExtension.isActivityEmbedded(lookupActivityFromContext) : false;
        } catch (IllegalArgumentException unused) {
            Log.w("PartnerConfigHelper", "Not a Activity instance in parent tree");
            return false;
        }
    }

    public final boolean isPartnerConfigAvailable(PartnerConfig partnerConfig) {
        Bundle bundle = this.resultBundle;
        return (bundle == null || bundle.isEmpty() || !this.resultBundle.containsKey(partnerConfig.getResourceName())) ? false : true;
    }
}
