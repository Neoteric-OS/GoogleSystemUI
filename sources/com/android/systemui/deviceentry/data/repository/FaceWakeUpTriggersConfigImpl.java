package com.android.systemui.deviceentry.data.repository;

import android.content.res.Resources;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.power.shared.model.WakeSleepReason;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FaceWakeUpTriggersConfigImpl implements Dumpable, FaceWakeUpTriggersConfig {
    public final Set triggerFaceAuthOnWakeUpFrom;
    public final Set wakeSleepReasonsToTriggerFaceAuth;

    public FaceWakeUpTriggersConfigImpl(Resources resources, GlobalSettings globalSettings, DumpManager dumpManager) {
        Set set = ArraysKt.toSet(resources.getIntArray(R.array.config_face_auth_wake_up_triggers));
        if (Build.IS_DEBUGGABLE) {
            String string = Settings.Global.getString(((GlobalSettingsImpl) globalSettings).mContentResolver, "face_wake_triggers");
            Set set2 = string != null ? (Set) StringsKt.split$default(string, new String[]{"|"}, 0, 6).stream().map(FaceWakeUpTriggersConfigImpl$processStringArray$1$1.INSTANCE).collect(Collectors.toSet()) : null;
            if (set2 != null) {
                set = set2;
            }
        }
        this.triggerFaceAuthOnWakeUpFrom = set;
        Set set3 = set;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(set3, 10));
        Iterator it = set3.iterator();
        while (it.hasNext()) {
            int intValue = ((Number) it.next()).intValue();
            WakeSleepReason.Companion.getClass();
            arrayList.add(WakeSleepReason.Companion.fromPowerManagerWakeReason(intValue));
        }
        this.wakeSleepReasonsToTriggerFaceAuth = CollectionsKt.toSet(arrayList);
        dumpManager.registerDumpable(this);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("FaceWakeUpTriggers:");
        Iterator it = this.triggerFaceAuthOnWakeUpFrom.iterator();
        while (it.hasNext()) {
            FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0.m(printWriter, "    ", PowerManager.wakeReasonToString(((Number) it.next()).intValue()));
        }
    }
}
