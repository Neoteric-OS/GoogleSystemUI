package com.android.systemui.log.echo;

import com.android.systemui.log.core.LogLevel;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LogcatEchoTrackerDebug$setEchoLevel$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ LogLevel $level;
    final /* synthetic */ String $name;
    final /* synthetic */ EchoOverrideType $type;
    int label;
    final /* synthetic */ LogcatEchoTrackerDebug this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogcatEchoTrackerDebug$setEchoLevel$1(LogcatEchoTrackerDebug logcatEchoTrackerDebug, EchoOverrideType echoOverrideType, LogLevel logLevel, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = logcatEchoTrackerDebug;
        this.$type = echoOverrideType;
        this.$level = logLevel;
        this.$name = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new LogcatEchoTrackerDebug$setEchoLevel$1(this.this$0, this.$type, this.$level, this.$name, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        LogcatEchoTrackerDebug$setEchoLevel$1 logcatEchoTrackerDebug$setEchoLevel$1 = (LogcatEchoTrackerDebug$setEchoLevel$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        logcatEchoTrackerDebug$setEchoLevel$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        LinkedHashMap linkedHashMap;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(this.this$0.bufferOverrides);
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(this.this$0.tagOverrides);
        int ordinal = this.$type.ordinal();
        if (ordinal == 0) {
            linkedHashMap = linkedHashMap2;
        } else {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            linkedHashMap = linkedHashMap3;
        }
        LogLevel logLevel = this.$level;
        if (logLevel != null) {
            linkedHashMap.put(this.$name, logLevel);
        } else {
            linkedHashMap.remove(this.$name);
        }
        this.this$0.bufferOverrides = linkedHashMap2;
        this.this$0.tagOverrides = linkedHashMap3;
        List listEchoOverrides = this.this$0.listEchoOverrides();
        LogcatEchoTrackerDebug logcatEchoTrackerDebug = this.this$0;
        GlobalSettings globalSettings = logcatEchoTrackerDebug.globalSettings;
        logcatEchoTrackerDebug.settingFormat.getClass();
        ((GlobalSettingsImpl) globalSettings).putString("systemui/logbuffer_echo_overrides", LogcatEchoSettingFormat.stringifyOverrides(listEchoOverrides));
        return Unit.INSTANCE;
    }
}
