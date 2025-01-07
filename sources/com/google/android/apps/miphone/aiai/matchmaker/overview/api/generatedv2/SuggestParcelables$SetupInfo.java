package com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SuggestParcelables$SetupInfo {
    public SuggestParcelables$ErrorCode errorCode;
    public String errorMesssage;
    public List setupFlags;

    public static SuggestParcelables$SetupInfo create(Bundle bundle) {
        SuggestParcelables$SetupInfo suggestParcelables$SetupInfo = new SuggestParcelables$SetupInfo();
        if (bundle.containsKey("errorCode")) {
            Bundle bundle2 = bundle.getBundle("errorCode");
            if (bundle2 == null) {
                suggestParcelables$SetupInfo.errorCode = null;
            } else {
                int i = bundle2.getInt("value");
                suggestParcelables$SetupInfo.errorCode = i == 0 ? SuggestParcelables$ErrorCode.ERROR_CODE_SUCCESS : i == 1 ? SuggestParcelables$ErrorCode.ERROR_CODE_UNKNOWN_ERROR : i == 2 ? SuggestParcelables$ErrorCode.ERROR_CODE_TIMEOUT : i == 3 ? SuggestParcelables$ErrorCode.ERROR_CODE_NO_SCREEN_CONTENT : i == 4 ? SuggestParcelables$ErrorCode.ERROR_CODE_NO_SUPPORTED_LOCALES : null;
            }
        }
        if (bundle.containsKey("errorMesssage")) {
            suggestParcelables$SetupInfo.errorMesssage = bundle.getString("errorMesssage");
        }
        if (bundle.containsKey("setupFlags")) {
            ArrayList parcelableArrayList = bundle.getParcelableArrayList("setupFlags");
            if (parcelableArrayList == null) {
                suggestParcelables$SetupInfo.setupFlags = null;
            } else {
                suggestParcelables$SetupInfo.setupFlags = new ArrayList(parcelableArrayList.size());
                Iterator it = parcelableArrayList.iterator();
                while (it.hasNext()) {
                    Bundle bundle3 = (Bundle) it.next();
                    if (bundle3 == null) {
                        suggestParcelables$SetupInfo.setupFlags.add(null);
                    } else {
                        List list = suggestParcelables$SetupInfo.setupFlags;
                        SuggestParcelables$Flag suggestParcelables$Flag = new SuggestParcelables$Flag();
                        if (bundle3.containsKey("name")) {
                            suggestParcelables$Flag.name = bundle3.getString("name");
                        }
                        if (bundle3.containsKey("value")) {
                            suggestParcelables$Flag.value = bundle3.getString("value");
                        }
                        list.add(suggestParcelables$Flag);
                    }
                }
            }
        }
        return suggestParcelables$SetupInfo;
    }

    public final Bundle writeToBundle() {
        Bundle bundle = new Bundle();
        SuggestParcelables$ErrorCode suggestParcelables$ErrorCode = this.errorCode;
        if (suggestParcelables$ErrorCode == null) {
            bundle.putBundle("errorCode", null);
        } else {
            suggestParcelables$ErrorCode.getClass();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("value", suggestParcelables$ErrorCode.value);
            bundle.putBundle("errorCode", bundle2);
        }
        bundle.putString("errorMesssage", this.errorMesssage);
        if (this.setupFlags == null) {
            bundle.putParcelableArrayList("setupFlags", null);
        } else {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(((ArrayList) this.setupFlags).size());
            for (SuggestParcelables$Flag suggestParcelables$Flag : this.setupFlags) {
                if (suggestParcelables$Flag == null) {
                    arrayList.add(null);
                } else {
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("name", suggestParcelables$Flag.name);
                    bundle3.putString("value", suggestParcelables$Flag.value);
                    arrayList.add(bundle3);
                }
            }
            bundle.putParcelableArrayList("setupFlags", arrayList);
        }
        return bundle;
    }
}
