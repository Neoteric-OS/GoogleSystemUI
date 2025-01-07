package com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SuggestParcelables$IntentInfo {
    public String action;
    public String className;
    public int flags;
    public List intentParams;
    public SuggestParcelables$IntentType intentType;
    public String mimeType;
    public String packageName;
    public String uri;

    public static SuggestParcelables$IntentInfo create(Bundle bundle) {
        SuggestParcelables$IntentInfo suggestParcelables$IntentInfo = new SuggestParcelables$IntentInfo();
        int i = 7;
        int i2 = 6;
        SuggestParcelables$IntentType suggestParcelables$IntentType = null;
        if (bundle.containsKey("intentParams")) {
            ArrayList parcelableArrayList = bundle.getParcelableArrayList("intentParams");
            if (parcelableArrayList == null) {
                suggestParcelables$IntentInfo.intentParams = null;
            } else {
                suggestParcelables$IntentInfo.intentParams = new ArrayList(parcelableArrayList.size());
                Iterator it = parcelableArrayList.iterator();
                while (it.hasNext()) {
                    Bundle bundle2 = (Bundle) it.next();
                    if (bundle2 == null) {
                        suggestParcelables$IntentInfo.intentParams.add(null);
                    } else {
                        List list = suggestParcelables$IntentInfo.intentParams;
                        SuggestParcelables$IntentParam suggestParcelables$IntentParam = new SuggestParcelables$IntentParam();
                        if (bundle2.containsKey("name")) {
                            suggestParcelables$IntentParam.name = bundle2.getString("name");
                        }
                        if (bundle2.containsKey("type")) {
                            Bundle bundle3 = bundle2.getBundle("type");
                            if (bundle3 == null) {
                                suggestParcelables$IntentParam.type = null;
                            } else {
                                int i3 = bundle3.getInt("value");
                                suggestParcelables$IntentParam.type = i3 == 0 ? SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_UNKNOWN : i3 == 1 ? SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_STRING : i3 == 2 ? SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_INT : i3 == 3 ? SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_FLOAT : i3 == 4 ? SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_LONG : i3 == 5 ? SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_INTENT : i3 == i2 ? SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_CONTENT_URI : i3 == i ? SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_BOOL : null;
                            }
                        }
                        if (bundle2.containsKey("strValue")) {
                            suggestParcelables$IntentParam.strValue = bundle2.getString("strValue");
                        }
                        if (bundle2.containsKey("intValue")) {
                            suggestParcelables$IntentParam.intValue = bundle2.getInt("intValue");
                        }
                        if (bundle2.containsKey("floatValue")) {
                            suggestParcelables$IntentParam.floatValue = bundle2.getFloat("floatValue");
                        }
                        if (bundle2.containsKey("longValue")) {
                            suggestParcelables$IntentParam.longValue = bundle2.getLong("longValue");
                        }
                        if (bundle2.containsKey("boolValue")) {
                            suggestParcelables$IntentParam.boolValue = bundle2.getBoolean("boolValue");
                        }
                        if (bundle2.containsKey("intentValue")) {
                            Bundle bundle4 = bundle2.getBundle("intentValue");
                            if (bundle4 == null) {
                                suggestParcelables$IntentParam.intentValue = null;
                            } else {
                                suggestParcelables$IntentParam.intentValue = create(bundle4);
                            }
                        }
                        if (bundle2.containsKey("contentUri")) {
                            suggestParcelables$IntentParam.contentUri = bundle2.getString("contentUri");
                        }
                        list.add(suggestParcelables$IntentParam);
                        i = 7;
                        i2 = 6;
                    }
                }
            }
        }
        if (bundle.containsKey("packageName")) {
            suggestParcelables$IntentInfo.packageName = bundle.getString("packageName");
        }
        if (bundle.containsKey("className")) {
            suggestParcelables$IntentInfo.className = bundle.getString("className");
        }
        if (bundle.containsKey("action")) {
            suggestParcelables$IntentInfo.action = bundle.getString("action");
        }
        if (bundle.containsKey("uri")) {
            suggestParcelables$IntentInfo.uri = bundle.getString("uri");
        }
        if (bundle.containsKey("mimeType")) {
            suggestParcelables$IntentInfo.mimeType = bundle.getString("mimeType");
        }
        if (bundle.containsKey("flags")) {
            suggestParcelables$IntentInfo.flags = bundle.getInt("flags");
        }
        if (bundle.containsKey("intentType")) {
            Bundle bundle5 = bundle.getBundle("intentType");
            if (bundle5 == null) {
                suggestParcelables$IntentInfo.intentType = null;
            } else {
                int i4 = bundle5.getInt("value");
                if (i4 == 0) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.DEFAULT;
                } else if (i4 == 1) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.COPY_TEXT;
                } else if (i4 == 2) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.SHARE_IMAGE;
                } else if (i4 == 3) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.LENS;
                } else if (i4 == 4) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.SAVE;
                } else if (i4 == 5) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.COPY_IMAGE;
                } else if (i4 == 6) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.SMART_REC;
                } else if (i4 == 7) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.IMAGE_SEARCH;
                } else if (i4 == 8) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.WIFI_CONNECT;
                } else if (i4 == 9) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.FEEDBACK_MSG;
                }
                suggestParcelables$IntentInfo.intentType = suggestParcelables$IntentType;
            }
        }
        return suggestParcelables$IntentInfo;
    }

    public final Bundle writeToBundle() {
        Bundle bundle = new Bundle();
        if (this.intentParams == null) {
            bundle.putParcelableArrayList("intentParams", null);
        } else {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(((ArrayList) this.intentParams).size());
            for (SuggestParcelables$IntentParam suggestParcelables$IntentParam : this.intentParams) {
                if (suggestParcelables$IntentParam == null) {
                    arrayList.add(null);
                } else {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("name", suggestParcelables$IntentParam.name);
                    SuggestParcelables$IntentParamType suggestParcelables$IntentParamType = suggestParcelables$IntentParam.type;
                    if (suggestParcelables$IntentParamType == null) {
                        bundle2.putBundle("type", null);
                    } else {
                        Bundle bundle3 = new Bundle();
                        bundle3.putInt("value", suggestParcelables$IntentParamType.value);
                        bundle2.putBundle("type", bundle3);
                    }
                    bundle2.putString("strValue", suggestParcelables$IntentParam.strValue);
                    bundle2.putInt("intValue", suggestParcelables$IntentParam.intValue);
                    bundle2.putFloat("floatValue", suggestParcelables$IntentParam.floatValue);
                    bundle2.putLong("longValue", suggestParcelables$IntentParam.longValue);
                    bundle2.putBoolean("boolValue", suggestParcelables$IntentParam.boolValue);
                    SuggestParcelables$IntentInfo suggestParcelables$IntentInfo = suggestParcelables$IntentParam.intentValue;
                    if (suggestParcelables$IntentInfo == null) {
                        bundle2.putBundle("intentValue", null);
                    } else {
                        bundle2.putBundle("intentValue", suggestParcelables$IntentInfo.writeToBundle());
                    }
                    bundle2.putString("contentUri", suggestParcelables$IntentParam.contentUri);
                    arrayList.add(bundle2);
                }
            }
            bundle.putParcelableArrayList("intentParams", arrayList);
        }
        bundle.putString("packageName", this.packageName);
        bundle.putString("className", this.className);
        bundle.putString("action", this.action);
        bundle.putString("uri", this.uri);
        bundle.putString("mimeType", this.mimeType);
        bundle.putInt("flags", this.flags);
        SuggestParcelables$IntentType suggestParcelables$IntentType = this.intentType;
        if (suggestParcelables$IntentType == null) {
            bundle.putBundle("intentType", null);
        } else {
            suggestParcelables$IntentType.getClass();
            Bundle bundle4 = new Bundle();
            bundle4.putInt("value", suggestParcelables$IntentType.value);
            bundle.putBundle("intentType", bundle4);
        }
        return bundle;
    }
}
