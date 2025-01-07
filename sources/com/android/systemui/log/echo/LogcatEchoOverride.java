package com.android.systemui.log.echo;

import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.systemui.log.core.LogLevel;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LogcatEchoOverride {
    public final LogLevel level;
    public final String name;
    public final EchoOverrideType type;

    public LogcatEchoOverride(EchoOverrideType echoOverrideType, String str, LogLevel logLevel) {
        this.type = echoOverrideType;
        this.name = str;
        this.level = logLevel;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LogcatEchoOverride)) {
            return false;
        }
        LogcatEchoOverride logcatEchoOverride = (LogcatEchoOverride) obj;
        return this.type == logcatEchoOverride.type && Intrinsics.areEqual(this.name, logcatEchoOverride.name) && this.level == logcatEchoOverride.level;
    }

    public final int hashCode() {
        return this.level.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.name, this.type.hashCode() * 31, 31);
    }

    public final String toString() {
        return "LogcatEchoOverride(type=" + this.type + ", name=" + this.name + ", level=" + this.level + ")";
    }
}
