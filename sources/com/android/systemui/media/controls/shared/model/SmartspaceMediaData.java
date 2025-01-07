package com.android.systemui.media.controls.shared.model;

import android.app.smartspace.SmartspaceAction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import android.text.TextUtils;
import android.util.Log;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.internal.logging.InstanceId;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SmartspaceMediaData {
    public final SmartspaceAction cardAction;
    public final Intent dismissIntent;
    public final long expiryTimeMs;
    public final long headphoneConnectionTimeMillis;
    public final InstanceId instanceId;
    public final boolean isActive;
    public final String packageName;
    public final List recommendations;
    public final String targetId;

    public SmartspaceMediaData(String str, boolean z, String str2, SmartspaceAction smartspaceAction, List list, Intent intent, long j, InstanceId instanceId, long j2) {
        this.targetId = str;
        this.isActive = z;
        this.packageName = str2;
        this.cardAction = smartspaceAction;
        this.recommendations = list;
        this.dismissIntent = intent;
        this.headphoneConnectionTimeMillis = j;
        this.instanceId = instanceId;
        this.expiryTimeMs = j2;
    }

    public static SmartspaceMediaData copy$default(SmartspaceMediaData smartspaceMediaData, String str, Intent intent, long j, InstanceId instanceId, long j2, int i) {
        boolean z = (i & 2) != 0 ? smartspaceMediaData.isActive : true;
        String str2 = smartspaceMediaData.packageName;
        SmartspaceAction smartspaceAction = smartspaceMediaData.cardAction;
        List list = smartspaceMediaData.recommendations;
        Intent intent2 = (i & 32) != 0 ? smartspaceMediaData.dismissIntent : intent;
        long j3 = (i & 64) != 0 ? smartspaceMediaData.headphoneConnectionTimeMillis : j;
        long j4 = (i & 256) != 0 ? smartspaceMediaData.expiryTimeMs : j2;
        smartspaceMediaData.getClass();
        smartspaceMediaData.getClass();
        return new SmartspaceMediaData(str, z, str2, smartspaceAction, list, intent2, j3, instanceId, j4);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SmartspaceMediaData)) {
            return false;
        }
        SmartspaceMediaData smartspaceMediaData = (SmartspaceMediaData) obj;
        return Intrinsics.areEqual(this.targetId, smartspaceMediaData.targetId) && this.isActive == smartspaceMediaData.isActive && Intrinsics.areEqual(this.packageName, smartspaceMediaData.packageName) && Intrinsics.areEqual(this.cardAction, smartspaceMediaData.cardAction) && Intrinsics.areEqual(this.recommendations, smartspaceMediaData.recommendations) && Intrinsics.areEqual(this.dismissIntent, smartspaceMediaData.dismissIntent) && this.headphoneConnectionTimeMillis == smartspaceMediaData.headphoneConnectionTimeMillis && Intrinsics.areEqual(this.instanceId, smartspaceMediaData.instanceId) && this.expiryTimeMs == smartspaceMediaData.expiryTimeMs;
    }

    public final CharSequence getAppName(Context context) {
        Intent intent;
        Bundle extras;
        SmartspaceAction smartspaceAction = this.cardAction;
        String string = (smartspaceAction == null || (intent = smartspaceAction.getIntent()) == null || (extras = intent.getExtras()) == null) ? null : extras.getString("KEY_SMARTSPACE_APP_NAME");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        PackageManager packageManager = context.getPackageManager();
        String str = this.packageName;
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            return launchIntentForPackage.resolveActivityInfo(packageManager, 0).loadLabel(packageManager);
        }
        Log.w(SmartspaceMediaDataKt.TAG, "Package " + str + " does not have a main launcher activity. Fallback to full app name");
        try {
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0));
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public final int getUid(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(this.packageName, 0).uid;
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(SmartspaceMediaDataKt.TAG, "Fail to get media recommendation's app info", e);
            return -1;
        }
    }

    public final List getValidRecommendations() {
        List list = this.recommendations;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((SmartspaceAction) obj).getIcon() != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final int hashCode() {
        int m = PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.packageName, TransitionData$$ExternalSyntheticOutline0.m(this.targetId.hashCode() * 31, 31, this.isActive), 31);
        SmartspaceAction smartspaceAction = this.cardAction;
        int m2 = PropertyValuesHolder2D$$ExternalSyntheticOutline0.m((m + (smartspaceAction == null ? 0 : smartspaceAction.hashCode())) * 31, 31, this.recommendations);
        Intent intent = this.dismissIntent;
        int m3 = Scale$$ExternalSyntheticOutline0.m((m2 + (intent == null ? 0 : intent.hashCode())) * 31, 31, this.headphoneConnectionTimeMillis);
        InstanceId instanceId = this.instanceId;
        return Boolean.hashCode(false) + Scale$$ExternalSyntheticOutline0.m((m3 + (instanceId == null ? 0 : instanceId.hashCode())) * 31, 31, this.expiryTimeMs);
    }

    public final boolean isValid() {
        return ((ArrayList) getValidRecommendations()).size() >= 3;
    }

    public final String toString() {
        SmartspaceAction smartspaceAction = this.cardAction;
        List list = this.recommendations;
        Intent intent = this.dismissIntent;
        InstanceId instanceId = this.instanceId;
        StringBuilder sb = new StringBuilder("SmartspaceMediaData(targetId=");
        sb.append(this.targetId);
        sb.append(", isActive=");
        sb.append(this.isActive);
        sb.append(", packageName=");
        sb.append(this.packageName);
        sb.append(", cardAction=");
        sb.append(smartspaceAction);
        sb.append(", recommendations=");
        sb.append(list);
        sb.append(", dismissIntent=");
        sb.append(intent);
        sb.append(", headphoneConnectionTimeMillis=");
        sb.append(this.headphoneConnectionTimeMillis);
        sb.append(", instanceId=");
        sb.append(instanceId);
        sb.append(", expiryTimeMs=");
        return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(this.expiryTimeMs, ", isImpressed=false)", sb);
    }

    public SmartspaceMediaData(String str, boolean z, String str2, SmartspaceAction smartspaceAction, List list, Intent intent, long j, InstanceId instanceId, long j2, int i) {
        this((i & 1) != 0 ? "INVALID" : str, (i & 2) != 0 ? false : z, (i & 4) == 0 ? str2 : "INVALID", (i & 8) != 0 ? null : smartspaceAction, (i & 16) != 0 ? EmptyList.INSTANCE : list, (i & 32) != 0 ? null : intent, (i & 64) != 0 ? 0L : j, (i & 128) == 0 ? instanceId : null, (i & 256) == 0 ? j2 : 0L);
    }
}
