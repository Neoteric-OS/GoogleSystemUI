package com.android.systemui.qs.external;

import android.service.quicksettings.Tile;
import com.android.systemui.plugins.clocks.WeatherData;
import org.json.JSONObject;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CustomTileStatePersisterKt {
    public static final Tile readTileFromString(String str) {
        JSONObject jSONObject = new JSONObject(str);
        Tile tile = new Tile();
        tile.setState(jSONObject.getInt(WeatherData.STATE_KEY));
        tile.setLabel(jSONObject.has("label") ? jSONObject.getString("label") : null);
        tile.setSubtitle(jSONObject.has("subtitle") ? jSONObject.getString("subtitle") : null);
        tile.setContentDescription(jSONObject.has("content_description") ? jSONObject.getString("content_description") : null);
        tile.setStateDescription(jSONObject.has("state_description") ? jSONObject.getString("state_description") : null);
        return tile;
    }

    public static final String writeToString(Tile tile) {
        return new JSONObject().put(WeatherData.STATE_KEY, tile.getState()).put("label", tile.getCustomLabel()).put("subtitle", tile.getSubtitle()).put("content_description", tile.getContentDescription()).put("state_description", tile.getStateDescription()).toString();
    }
}
