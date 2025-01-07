package com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2;

import android.os.Bundle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SuggestParcelables$ContentRect {
    public int beginChar;
    public int contentGroupIndex;
    public SuggestParcelables$ContentType contentType;
    public String contentUri;
    public int endChar;
    public int lineId;
    public SuggestParcelables$OnScreenRect rect;
    public String text;

    public static SuggestParcelables$ContentRect create(Bundle bundle) {
        SuggestParcelables$ContentRect suggestParcelables$ContentRect = new SuggestParcelables$ContentRect();
        SuggestParcelables$ContentType suggestParcelables$ContentType = null;
        if (bundle.containsKey("rect")) {
            Bundle bundle2 = bundle.getBundle("rect");
            if (bundle2 == null) {
                suggestParcelables$ContentRect.rect = null;
            } else {
                SuggestParcelables$OnScreenRect suggestParcelables$OnScreenRect = new SuggestParcelables$OnScreenRect();
                if (bundle2.containsKey("left")) {
                    suggestParcelables$OnScreenRect.left = bundle2.getFloat("left");
                }
                if (bundle2.containsKey("top")) {
                    suggestParcelables$OnScreenRect.top = bundle2.getFloat("top");
                }
                if (bundle2.containsKey("width")) {
                    suggestParcelables$OnScreenRect.width = bundle2.getFloat("width");
                }
                if (bundle2.containsKey("height")) {
                    suggestParcelables$OnScreenRect.height = bundle2.getFloat("height");
                }
                suggestParcelables$ContentRect.rect = suggestParcelables$OnScreenRect;
            }
        }
        if (bundle.containsKey("text")) {
            suggestParcelables$ContentRect.text = bundle.getString("text");
        }
        if (bundle.containsKey("contentType")) {
            Bundle bundle3 = bundle.getBundle("contentType");
            if (bundle3 == null) {
                suggestParcelables$ContentRect.contentType = null;
            } else {
                int i = bundle3.getInt("value");
                if (i == 0) {
                    suggestParcelables$ContentType = SuggestParcelables$ContentType.CONTENT_TYPE_UNKNOWN;
                } else if (i == 1) {
                    suggestParcelables$ContentType = SuggestParcelables$ContentType.CONTENT_TYPE_TEXT;
                } else if (i == 2) {
                    suggestParcelables$ContentType = SuggestParcelables$ContentType.CONTENT_TYPE_IMAGE;
                } else if (i == 3) {
                    suggestParcelables$ContentType = SuggestParcelables$ContentType.CONTENT_TYPE_BARCODE;
                }
                suggestParcelables$ContentRect.contentType = suggestParcelables$ContentType;
            }
        }
        if (bundle.containsKey("lineId")) {
            suggestParcelables$ContentRect.lineId = bundle.getInt("lineId");
        }
        if (bundle.containsKey("contentUri")) {
            suggestParcelables$ContentRect.contentUri = bundle.getString("contentUri");
        }
        if (bundle.containsKey("contentGroupIndex")) {
            suggestParcelables$ContentRect.contentGroupIndex = bundle.getInt("contentGroupIndex");
        }
        if (bundle.containsKey("beginChar")) {
            suggestParcelables$ContentRect.beginChar = bundle.getInt("beginChar");
        }
        if (bundle.containsKey("endChar")) {
            suggestParcelables$ContentRect.endChar = bundle.getInt("endChar");
        }
        return suggestParcelables$ContentRect;
    }

    public final Bundle writeToBundle() {
        Bundle bundle = new Bundle();
        SuggestParcelables$OnScreenRect suggestParcelables$OnScreenRect = this.rect;
        if (suggestParcelables$OnScreenRect == null) {
            bundle.putBundle("rect", null);
        } else {
            Bundle bundle2 = new Bundle();
            bundle2.putFloat("left", suggestParcelables$OnScreenRect.left);
            bundle2.putFloat("top", suggestParcelables$OnScreenRect.top);
            bundle2.putFloat("width", suggestParcelables$OnScreenRect.width);
            bundle2.putFloat("height", suggestParcelables$OnScreenRect.height);
            bundle.putBundle("rect", bundle2);
        }
        bundle.putString("text", this.text);
        SuggestParcelables$ContentType suggestParcelables$ContentType = this.contentType;
        if (suggestParcelables$ContentType == null) {
            bundle.putBundle("contentType", null);
        } else {
            Bundle bundle3 = new Bundle();
            bundle3.putInt("value", suggestParcelables$ContentType.value);
            bundle.putBundle("contentType", bundle3);
        }
        bundle.putInt("lineId", this.lineId);
        bundle.putString("contentUri", this.contentUri);
        bundle.putInt("contentGroupIndex", this.contentGroupIndex);
        bundle.putInt("beginChar", this.beginChar);
        bundle.putInt("endChar", this.endChar);
        return bundle;
    }
}
