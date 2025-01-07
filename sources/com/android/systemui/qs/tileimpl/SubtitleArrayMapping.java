package com.android.systemui.qs.tileimpl;

import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.wm.shell.R;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SubtitleArrayMapping {
    public static final HashMap subtitleIdsMap;

    static {
        HashMap hashMap = new HashMap();
        subtitleIdsMap = hashMap;
        hashMap.put("internet", Integer.valueOf(R.array.tile_states_internet));
        hashMap.put("wifi", Integer.valueOf(R.array.tile_states_wifi));
        hashMap.put("cell", Integer.valueOf(R.array.tile_states_cell));
        hashMap.put("battery", Integer.valueOf(R.array.tile_states_battery));
        hashMap.put("dnd", Integer.valueOf(R.array.tile_states_dnd));
        hashMap.put("flashlight", Integer.valueOf(R.array.tile_states_flashlight));
        hashMap.put("rotation", Integer.valueOf(R.array.tile_states_rotation));
        hashMap.put("bt", Integer.valueOf(R.array.tile_states_bt));
        hashMap.put("airplane", Integer.valueOf(R.array.tile_states_airplane));
        hashMap.put("location", Integer.valueOf(R.array.tile_states_location));
        hashMap.put("hotspot", Integer.valueOf(R.array.tile_states_hotspot));
        hashMap.put("inversion", Integer.valueOf(R.array.tile_states_inversion));
        hashMap.put("saver", Integer.valueOf(R.array.tile_states_saver));
        hashMap.put("dark", Integer.valueOf(R.array.tile_states_dark));
        hashMap.put("work", Integer.valueOf(R.array.tile_states_work));
        hashMap.put("cast", Integer.valueOf(R.array.tile_states_cast));
        hashMap.put("night", Integer.valueOf(R.array.tile_states_night));
        hashMap.put("screenrecord", Integer.valueOf(R.array.tile_states_screenrecord));
        hashMap.put("record_issue", Integer.valueOf(R.array.tile_states_record_issue));
        hashMap.put("reverse", Integer.valueOf(R.array.tile_states_reverse));
        hashMap.put("reduce_brightness", Integer.valueOf(R.array.tile_states_reduce_brightness));
        hashMap.put("cameratoggle", Integer.valueOf(R.array.tile_states_cameratoggle));
        hashMap.put("mictoggle", Integer.valueOf(R.array.tile_states_mictoggle));
        hashMap.put("controls", Integer.valueOf(R.array.tile_states_controls));
        hashMap.put("wallet", Integer.valueOf(R.array.tile_states_wallet));
        hashMap.put("qr_code_scanner", Integer.valueOf(R.array.tile_states_qr_code_scanner));
        hashMap.put("alarm", Integer.valueOf(R.array.tile_states_alarm));
        hashMap.put("onehanded", Integer.valueOf(R.array.tile_states_onehanded));
        hashMap.put("color_correction", Integer.valueOf(R.array.tile_states_color_correction));
        hashMap.put(BcSmartspaceDataPlugin.UI_SURFACE_DREAM, Integer.valueOf(R.array.tile_states_dream));
        hashMap.put("font_scaling", Integer.valueOf(R.array.tile_states_font_scaling));
        hashMap.put("hearing_devices", Integer.valueOf(R.array.tile_states_hearing_devices));
    }

    public static int getSubtitleId(String str) {
        if (str == null) {
            return R.array.tile_states_default;
        }
        Integer num = (Integer) subtitleIdsMap.get(str);
        if (num == null) {
            num = Integer.valueOf(R.array.tile_states_default);
        }
        return num.intValue();
    }
}
