package com.android.systemui.log.echo;

import android.provider.Settings;
import android.util.Log;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LogcatEchoTrackerDebug$loadEchoOverrides$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ LogcatEchoTrackerDebug this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogcatEchoTrackerDebug$loadEchoOverrides$1(LogcatEchoTrackerDebug logcatEchoTrackerDebug, Continuation continuation) {
        super(2, continuation);
        this.this$0 = logcatEchoTrackerDebug;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new LogcatEchoTrackerDebug$loadEchoOverrides$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        LogcatEchoTrackerDebug$loadEchoOverrides$1 logcatEchoTrackerDebug$loadEchoOverrides$1 = (LogcatEchoTrackerDebug$loadEchoOverrides$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        logcatEchoTrackerDebug$loadEchoOverrides$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        List<LogcatEchoOverride> list;
        EchoOverrideType echoOverrideType;
        LogLevel logLevel;
        LinkedHashMap linkedHashMap;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        String string = Settings.Global.getString(((GlobalSettingsImpl) this.this$0.globalSettings).mContentResolver, "systemui/logbuffer_echo_overrides");
        Unit unit = Unit.INSTANCE;
        if (string == null) {
            return unit;
        }
        this.this$0.settingFormat.getClass();
        List split$default = StringsKt.split$default(string, new String[]{";"}, 2, 2);
        if (split$default.size() != 2) {
            Log.e("EchoFormat", "Unrecognized echo override format: \"" + string + "\"");
            list = EmptyList.INSTANCE;
        } else {
            int i = 0;
            try {
                int parseInt = Integer.parseInt((String) split$default.get(0));
                if (parseInt == 0) {
                    String str = (String) split$default.get(1);
                    List arrayList = new ArrayList();
                    List split = new Regex("(?<!\\\\);").split(str);
                    while (i < split.size() && split.size() - i >= 3) {
                        String str2 = (String) split.get(i);
                        if (!Intrinsics.areEqual(str2, "b")) {
                            if (!Intrinsics.areEqual(str2, "t")) {
                                break;
                            }
                            echoOverrideType = EchoOverrideType.TAG;
                        } else {
                            echoOverrideType = EchoOverrideType.BUFFER;
                        }
                        String replace$default = StringsKt__StringsJVMKt.replace$default((String) split.get(i + 1), "\\;", ";");
                        String str3 = (String) split.get(i + 2);
                        int hashCode = str3.hashCode();
                        if (hashCode == 33) {
                            if (!str3.equals("!")) {
                                break;
                            }
                            logLevel = LogLevel.WTF;
                            i += 3;
                            arrayList.add(new LogcatEchoOverride(echoOverrideType, replace$default, logLevel));
                        } else if (hashCode == 105) {
                            if (!str3.equals("i")) {
                                break;
                            }
                            logLevel = LogLevel.INFO;
                            i += 3;
                            arrayList.add(new LogcatEchoOverride(echoOverrideType, replace$default, logLevel));
                        } else if (hashCode == 100) {
                            if (!str3.equals("d")) {
                                break;
                            }
                            logLevel = LogLevel.DEBUG;
                            i += 3;
                            arrayList.add(new LogcatEchoOverride(echoOverrideType, replace$default, logLevel));
                        } else if (hashCode == 101) {
                            if (!str3.equals("e")) {
                                break;
                            }
                            logLevel = LogLevel.ERROR;
                            i += 3;
                            arrayList.add(new LogcatEchoOverride(echoOverrideType, replace$default, logLevel));
                        } else if (hashCode != 118) {
                            if (hashCode != 119 || !str3.equals("w")) {
                                break;
                            }
                            logLevel = LogLevel.WARNING;
                            i += 3;
                            arrayList.add(new LogcatEchoOverride(echoOverrideType, replace$default, logLevel));
                        } else {
                            if (!str3.equals("v")) {
                                break;
                            }
                            logLevel = LogLevel.VERBOSE;
                            i += 3;
                            arrayList.add(new LogcatEchoOverride(echoOverrideType, replace$default, logLevel));
                        }
                    }
                    list = arrayList;
                } else {
                    ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Unrecognized echo override formation version: ", "EchoFormat", parseInt);
                    list = EmptyList.INSTANCE;
                }
            } catch (NumberFormatException unused) {
                Log.e("EchoFormat", "Unrecognized echo override formation version: " + split$default.get(0));
                list = EmptyList.INSTANCE;
            }
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        for (LogcatEchoOverride logcatEchoOverride : list) {
            int ordinal = logcatEchoOverride.type.ordinal();
            if (ordinal == 0) {
                linkedHashMap = linkedHashMap2;
            } else {
                if (ordinal != 1) {
                    throw new NoWhenBranchMatchedException();
                }
                linkedHashMap = linkedHashMap3;
            }
            linkedHashMap.put(logcatEchoOverride.name, logcatEchoOverride.level);
        }
        this.this$0.bufferOverrides = linkedHashMap2;
        this.this$0.tagOverrides = linkedHashMap3;
        return unit;
    }
}
