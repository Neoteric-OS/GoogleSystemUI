package com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2;

import android.os.Bundle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SuggestParcelables$Action {
    public String dEPRECATEDIconBitmapId;
    public SuggestParcelables$IntentInfo dEPRECATEDIntentInfo;
    public String displayName;
    public String fullDisplayName;
    public boolean hasProxiedIntentInfo;
    public String id;
    public String opaquePayload;
    public SuggestParcelables$IntentInfo proxiedIntentInfo;

    public static SuggestParcelables$Action create(Bundle bundle) {
        SuggestParcelables$Action suggestParcelables$Action = new SuggestParcelables$Action();
        if (bundle.containsKey("id")) {
            suggestParcelables$Action.id = bundle.getString("id");
        }
        if (bundle.containsKey("displayName")) {
            suggestParcelables$Action.displayName = bundle.getString("displayName");
        }
        if (bundle.containsKey("dEPRECATEDIconBitmapId")) {
            suggestParcelables$Action.dEPRECATEDIconBitmapId = bundle.getString("dEPRECATEDIconBitmapId");
        }
        if (bundle.containsKey("fullDisplayName")) {
            suggestParcelables$Action.fullDisplayName = bundle.getString("fullDisplayName");
        }
        if (bundle.containsKey("dEPRECATEDIntentInfo")) {
            Bundle bundle2 = bundle.getBundle("dEPRECATEDIntentInfo");
            if (bundle2 == null) {
                suggestParcelables$Action.dEPRECATEDIntentInfo = null;
            } else {
                suggestParcelables$Action.dEPRECATEDIntentInfo = SuggestParcelables$IntentInfo.create(bundle2);
            }
        }
        if (bundle.containsKey("proxiedIntentInfo")) {
            suggestParcelables$Action.hasProxiedIntentInfo = true;
            Bundle bundle3 = bundle.getBundle("proxiedIntentInfo");
            if (bundle3 == null) {
                suggestParcelables$Action.proxiedIntentInfo = null;
            } else {
                suggestParcelables$Action.proxiedIntentInfo = SuggestParcelables$IntentInfo.create(bundle3);
            }
        } else {
            suggestParcelables$Action.hasProxiedIntentInfo = false;
        }
        if (bundle.containsKey("opaquePayload")) {
            suggestParcelables$Action.opaquePayload = bundle.getString("opaquePayload");
        }
        return suggestParcelables$Action;
    }

    public final Bundle writeToBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("id", this.id);
        bundle.putString("displayName", this.displayName);
        bundle.putString("dEPRECATEDIconBitmapId", this.dEPRECATEDIconBitmapId);
        bundle.putString("fullDisplayName", this.fullDisplayName);
        SuggestParcelables$IntentInfo suggestParcelables$IntentInfo = this.dEPRECATEDIntentInfo;
        if (suggestParcelables$IntentInfo == null) {
            bundle.putBundle("dEPRECATEDIntentInfo", null);
        } else {
            bundle.putBundle("dEPRECATEDIntentInfo", suggestParcelables$IntentInfo.writeToBundle());
        }
        SuggestParcelables$IntentInfo suggestParcelables$IntentInfo2 = this.proxiedIntentInfo;
        if (suggestParcelables$IntentInfo2 == null) {
            bundle.putBundle("proxiedIntentInfo", null);
        } else {
            bundle.putBundle("proxiedIntentInfo", suggestParcelables$IntentInfo2.writeToBundle());
        }
        bundle.putString("opaquePayload", this.opaquePayload);
        return bundle;
    }
}
