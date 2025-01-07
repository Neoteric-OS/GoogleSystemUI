package com.android.wm.shell.apptoweb;

import android.content.Context;
import android.provider.DeviceConfig;
import android.webkit.URLUtil;
import com.android.wm.shell.R;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AppToWebGenericLinksParser {
    public final Context context;
    public final Map genericLinksMap = new LinkedHashMap();
    public final ShellExecutor mainExecutor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DeviceConfigListener implements DeviceConfig.OnPropertiesChangedListener {
        public DeviceConfigListener() {
            DeviceConfig.addOnPropertiesChangedListener("app_compat_overrides", AppToWebGenericLinksParser.this.mainExecutor, this);
        }

        public final void onPropertiesChanged(DeviceConfig.Properties properties) {
            if (properties.getKeyset().contains("generic_links_flag")) {
                AppToWebGenericLinksParser.this.updateGenericLinksMap();
            }
        }
    }

    public AppToWebGenericLinksParser(Context context, ShellExecutor shellExecutor) {
        this.context = context;
        this.mainExecutor = shellExecutor;
        if (!DesktopModeStatus.USE_APP_TO_WEB_BUILD_TIME_GENERIC_LINKS) {
            new DeviceConfigListener();
        }
        updateGenericLinksMap();
    }

    public final void updateGenericLinksMap() {
        String string = DesktopModeStatus.USE_APP_TO_WEB_BUILD_TIME_GENERIC_LINKS ? this.context.getResources().getString(R.string.generic_links_list) : DeviceConfig.getString("app_compat_overrides", "generic_links_flag", "");
        if (string == null) {
            return;
        }
        List split$default = StringsKt.split$default(string, new String[]{" "}, 0, 6);
        ArrayList arrayList = new ArrayList();
        for (Object obj : split$default) {
            if (StringsKt.contains$default((CharSequence) obj, ':')) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            List split$default2 = StringsKt.split$default((String) it.next(), new char[]{':'});
            arrayList2.add(new Pair((String) split$default2.get(0), (String) split$default2.get(1)));
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : arrayList2) {
            if (URLUtil.isNetworkUrl((String) ((Pair) obj2).getSecond())) {
                arrayList3.add(obj2);
            }
        }
        this.genericLinksMap.clear();
        MapsKt.putAll(this.genericLinksMap, arrayList3);
    }
}
