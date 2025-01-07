package com.android.systemui.log;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.android.compose.PlatformSliderColors$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LogMessageImpl implements LogMessage {
    public boolean bool1;
    public boolean bool2;
    public boolean bool3;
    public boolean bool4;
    public double double1;
    public Throwable exception;
    public int int1;
    public int int2;
    public LogLevel level;
    public long long1;
    public long long2;
    public Function1 messagePrinter;
    public String str1;
    public String str2;
    public String str3;
    public String tag;
    public long timestamp;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LogMessageImpl)) {
            return false;
        }
        LogMessageImpl logMessageImpl = (LogMessageImpl) obj;
        return this.level == logMessageImpl.level && Intrinsics.areEqual(this.tag, logMessageImpl.tag) && this.timestamp == logMessageImpl.timestamp && Intrinsics.areEqual(this.messagePrinter, logMessageImpl.messagePrinter) && Intrinsics.areEqual(this.exception, logMessageImpl.exception) && Intrinsics.areEqual(this.str1, logMessageImpl.str1) && Intrinsics.areEqual(this.str2, logMessageImpl.str2) && Intrinsics.areEqual(this.str3, logMessageImpl.str3) && this.int1 == logMessageImpl.int1 && this.int2 == logMessageImpl.int2 && this.long1 == logMessageImpl.long1 && this.long2 == logMessageImpl.long2 && Double.compare(this.double1, logMessageImpl.double1) == 0 && this.bool1 == logMessageImpl.bool1 && this.bool2 == logMessageImpl.bool2 && this.bool3 == logMessageImpl.bool3 && this.bool4 == logMessageImpl.bool4;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final boolean getBool1() {
        return this.bool1;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final boolean getBool2() {
        return this.bool2;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final boolean getBool3() {
        return this.bool3;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final boolean getBool4() {
        return this.bool4;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final double getDouble1() {
        return this.double1;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final Throwable getException() {
        return this.exception;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final int getInt1() {
        return this.int1;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final int getInt2() {
        return this.int2;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final LogLevel getLevel() {
        return this.level;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final long getLong1() {
        return this.long1;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final long getLong2() {
        return this.long2;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final Function1 getMessagePrinter() {
        return this.messagePrinter;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final String getStr1() {
        return this.str1;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final String getStr2() {
        return this.str2;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final String getStr3() {
        return this.str3;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final String getTag() {
        return this.tag;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final long getTimestamp() {
        return this.timestamp;
    }

    public final int hashCode() {
        int m = ChangeSize$$ExternalSyntheticOutline0.m(this.messagePrinter, Scale$$ExternalSyntheticOutline0.m(PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.tag, this.level.hashCode() * 31, 31), 31, this.timestamp), 31);
        Throwable th = this.exception;
        int hashCode = (m + (th == null ? 0 : th.hashCode())) * 31;
        String str = this.str1;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.str2;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.str3;
        return Boolean.hashCode(this.bool4) + TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((Double.hashCode(this.double1) + Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.int2, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.int1, (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31, 31), 31), 31, this.long1), 31, this.long2)) * 31, 31, this.bool1), 31, this.bool2), 31, this.bool3);
    }

    public final void reset(String str, LogLevel logLevel, long j, Function1 function1, Throwable th) {
        this.level = logLevel;
        this.tag = str;
        this.timestamp = j;
        this.messagePrinter = function1;
        this.exception = th;
        this.str1 = null;
        this.str2 = null;
        this.str3 = null;
        this.int1 = 0;
        this.int2 = 0;
        this.long1 = 0L;
        this.long2 = 0L;
        this.double1 = 0.0d;
        this.bool1 = false;
        this.bool2 = false;
        this.bool3 = false;
        this.bool4 = false;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final void setBool1(boolean z) {
        this.bool1 = z;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final void setBool2(boolean z) {
        this.bool2 = z;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final void setBool3(boolean z) {
        this.bool3 = z;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final void setBool4(boolean z) {
        this.bool4 = z;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final void setDouble1(double d) {
        this.double1 = d;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final void setInt1(int i) {
        this.int1 = i;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final void setInt2(int i) {
        this.int2 = i;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final void setLong1(long j) {
        this.long1 = j;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final void setLong2(long j) {
        this.long2 = j;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final void setStr1(String str) {
        this.str1 = str;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final void setStr2(String str) {
        this.str2 = str;
    }

    @Override // com.android.systemui.log.core.LogMessage
    public final void setStr3(String str) {
        this.str3 = str;
    }

    public final String toString() {
        LogLevel logLevel = this.level;
        String str = this.tag;
        long j = this.timestamp;
        Function1 function1 = this.messagePrinter;
        Throwable th = this.exception;
        String str2 = this.str1;
        String str3 = this.str2;
        String str4 = this.str3;
        int i = this.int1;
        int i2 = this.int2;
        long j2 = this.long1;
        long j3 = this.long2;
        double d = this.double1;
        boolean z = this.bool1;
        boolean z2 = this.bool2;
        boolean z3 = this.bool3;
        boolean z4 = this.bool4;
        StringBuilder sb = new StringBuilder("LogMessageImpl(level=");
        sb.append(logLevel);
        sb.append(", tag=");
        sb.append(str);
        sb.append(", timestamp=");
        sb.append(j);
        sb.append(", messagePrinter=");
        sb.append(function1);
        sb.append(", exception=");
        sb.append(th);
        sb.append(", str1=");
        sb.append(str2);
        PlatformSliderColors$$ExternalSyntheticOutline0.m(sb, ", str2=", str3, ", str3=", str4);
        sb.append(", int1=");
        sb.append(i);
        sb.append(", int2=");
        sb.append(i2);
        sb.append(", long1=");
        sb.append(j2);
        sb.append(", long2=");
        sb.append(j3);
        sb.append(", double1=");
        sb.append(d);
        sb.append(", bool1=");
        BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(sb, z, ", bool2=", z2, ", bool3=");
        sb.append(z3);
        sb.append(", bool4=");
        sb.append(z4);
        sb.append(")");
        return sb.toString();
    }
}
