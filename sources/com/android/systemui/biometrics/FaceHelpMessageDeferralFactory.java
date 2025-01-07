package com.android.systemui.biometrics;

import android.content.res.Resources;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.BiometricMessageDeferralLogger;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.util.HashSet;
import java.util.UUID;
import kotlin.collections.MapsKt__MapsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FaceHelpMessageDeferralFactory {
    public final DumpManager dumpManager;
    public final LogBuffer logBuffer;
    public final Resources resources;
    public final Lazy systemClock;

    public FaceHelpMessageDeferralFactory(Resources resources, LogBuffer logBuffer, DumpManager dumpManager, Lazy lazy) {
        this.resources = resources;
        this.logBuffer = logBuffer;
        this.dumpManager = dumpManager;
        this.systemClock = lazy;
    }

    public final FaceHelpMessageDeferral create() {
        String uuid = UUID.randomUUID().toString();
        Resources resources = this.resources;
        BiometricMessageDeferralLogger biometricMessageDeferralLogger = new BiometricMessageDeferralLogger(this.logBuffer, DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("FaceHelpMessageDeferral[", uuid, "]"));
        int[] intArray = resources.getIntArray(R.array.config_face_help_msgs_defer_until_timeout);
        HashSet hashSet = new HashSet(MapsKt__MapsJVMKt.mapCapacity(intArray.length));
        for (int i : intArray) {
            hashSet.add(Integer.valueOf(i));
        }
        int[] intArray2 = resources.getIntArray(R.array.config_face_help_msgs_ignore);
        HashSet hashSet2 = new HashSet(MapsKt__MapsJVMKt.mapCapacity(intArray2.length));
        for (int i2 : intArray2) {
            hashSet2.add(Integer.valueOf(i2));
        }
        return new FaceHelpMessageDeferral(hashSet, hashSet2, resources.getFloat(R.dimen.config_face_help_msgs_defer_until_timeout_threshold), resources.getInteger(R.integer.config_face_help_msgs_defer_analyze_timeframe), biometricMessageDeferralLogger, this.dumpManager, uuid, this.systemClock);
    }
}
