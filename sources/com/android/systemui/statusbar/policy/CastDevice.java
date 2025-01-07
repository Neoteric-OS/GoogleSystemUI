package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.projection.MediaProjectionInfo;
import android.text.TextUtils;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.util.Utils;
import com.android.wm.shell.R;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CastDevice {
    public final String description;
    public final String id;
    public final boolean isCasting;
    public final String name;
    public final CastOrigin origin;
    public final String shortLogString;
    public final CastState state;
    public final Object tag;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CastOrigin {
        public static final /* synthetic */ CastOrigin[] $VALUES;
        public static final CastOrigin MediaProjection;
        public static final CastOrigin MediaRouter;

        static {
            CastOrigin castOrigin = new CastOrigin("MediaRouter", 0);
            MediaRouter = castOrigin;
            CastOrigin castOrigin2 = new CastOrigin("MediaProjection", 1);
            MediaProjection = castOrigin2;
            CastOrigin[] castOriginArr = {castOrigin, castOrigin2};
            $VALUES = castOriginArr;
            EnumEntriesKt.enumEntries(castOriginArr);
        }

        public static CastOrigin valueOf(String str) {
            return (CastOrigin) Enum.valueOf(CastOrigin.class, str);
        }

        public static CastOrigin[] values() {
            return (CastOrigin[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CastState {
        public static final /* synthetic */ CastState[] $VALUES;
        public static final CastState Connected;
        public static final CastState Connecting;
        public static final CastState Disconnected;

        static {
            CastState castState = new CastState("Disconnected", 0);
            Disconnected = castState;
            CastState castState2 = new CastState("Connecting", 1);
            Connecting = castState2;
            CastState castState3 = new CastState("Connected", 2);
            Connected = castState3;
            CastState[] castStateArr = {castState, castState2, castState3};
            $VALUES = castStateArr;
            EnumEntriesKt.enumEntries(castStateArr);
        }

        public static CastState valueOf(String str) {
            return (CastState) Enum.valueOf(CastState.class, str);
        }

        public static CastState[] values() {
            return (CastState[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static CastDevice toCastDevice(MediaProjectionInfo mediaProjectionInfo, Context context, PackageManager packageManager, CastControllerLogger castControllerLogger) {
            String str;
            CharSequence loadLabel;
            String obj;
            LogBuffer logBuffer = castControllerLogger.logger;
            String packageName = mediaProjectionInfo.getPackageName();
            String packageName2 = mediaProjectionInfo.getPackageName();
            if (Utils.isHeadlessRemoteDisplayProvider(packageManager, packageName2)) {
                obj = "";
            } else {
                try {
                    loadLabel = packageManager.getApplicationInfo(packageName2, 0).loadLabel(packageManager);
                } catch (PackageManager.NameNotFoundException e) {
                    LogMessage obtain = logBuffer.obtain("#getAppName", LogLevel.WARNING, new Function1() { // from class: com.android.systemui.statusbar.policy.CastDevice$Companion$getAppName$4
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Error getting appName for package=", ((LogMessage) obj2).getStr1());
                        }
                    }, e);
                    ((LogMessageImpl) obtain).str1 = packageName2;
                    logBuffer.commit(obtain);
                }
                if (TextUtils.isEmpty(loadLabel)) {
                    LogMessage obtain2 = logBuffer.obtain("#getAppName", LogLevel.WARNING, new Function1() { // from class: com.android.systemui.statusbar.policy.CastDevice$Companion$getAppName$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("No label found for package: ", ((LogMessage) obj2).getStr1());
                        }
                    }, null);
                    ((LogMessageImpl) obtain2).str1 = packageName2;
                    logBuffer.commit(obtain2);
                    str = packageName2;
                    String string = context.getString(R.string.quick_settings_casting);
                    CastState castState = CastState.Connected;
                    CastOrigin castOrigin = CastOrigin.MediaProjection;
                    Intrinsics.checkNotNull(packageName);
                    return new CastDevice(packageName, str, string, castState, castOrigin, mediaProjectionInfo);
                }
                obj = loadLabel.toString();
            }
            str = obj;
            String string2 = context.getString(R.string.quick_settings_casting);
            CastState castState2 = CastState.Connected;
            CastOrigin castOrigin2 = CastOrigin.MediaProjection;
            Intrinsics.checkNotNull(packageName);
            return new CastDevice(packageName, str, string2, castState2, castOrigin2, mediaProjectionInfo);
        }
    }

    public CastDevice(String str, String str2, String str3, CastState castState, CastOrigin castOrigin, Object obj) {
        this.id = str;
        this.name = str2;
        this.description = str3;
        this.state = castState;
        this.origin = castOrigin;
        this.tag = obj;
        this.isCasting = castState == CastState.Connecting || castState == CastState.Connected;
        StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("CastDevice(id=", str, " name=", str2, " description=");
        m.append(str3);
        m.append(" state=");
        m.append(castState);
        m.append(" origin=");
        m.append(castOrigin);
        m.append(")");
        this.shortLogString = m.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CastDevice)) {
            return false;
        }
        CastDevice castDevice = (CastDevice) obj;
        return Intrinsics.areEqual(this.id, castDevice.id) && Intrinsics.areEqual(this.name, castDevice.name) && Intrinsics.areEqual(this.description, castDevice.description) && this.state == castDevice.state && this.origin == castDevice.origin && Intrinsics.areEqual(this.tag, castDevice.tag);
    }

    public final int hashCode() {
        int hashCode = this.id.hashCode() * 31;
        String str = this.name;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.description;
        int hashCode3 = (this.origin.hashCode() + ((this.state.hashCode() + ((hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31)) * 31)) * 31;
        Object obj = this.tag;
        return hashCode3 + (obj != null ? obj.hashCode() : 0);
    }

    public final String toString() {
        return "CastDevice(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", state=" + this.state + ", origin=" + this.origin + ", tag=" + this.tag + ")";
    }
}
