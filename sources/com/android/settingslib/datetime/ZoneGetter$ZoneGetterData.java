package com.android.settingslib.datetime;

import com.android.i18n.timezone.CountryTimeZones;
import com.android.i18n.timezone.TimeZoneFinder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ZoneGetter$ZoneGetterData {
    public List lookupTimeZoneIdsByCountry(String str) {
        CountryTimeZones lookupCountryTimeZones = TimeZoneFinder.getInstance().lookupCountryTimeZones(str);
        if (lookupCountryTimeZones == null) {
            return null;
        }
        List timeZoneMappings = lookupCountryTimeZones.getTimeZoneMappings();
        ArrayList arrayList = new ArrayList(timeZoneMappings.size());
        Iterator it = timeZoneMappings.iterator();
        while (it.hasNext()) {
            arrayList.add(((CountryTimeZones.TimeZoneMapping) it.next()).getTimeZoneId());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
