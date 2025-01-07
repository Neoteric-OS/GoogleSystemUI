package org.tensorflow.lite;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InterpreterApi$Options$TfLiteRuntime {
    public static final /* synthetic */ InterpreterApi$Options$TfLiteRuntime[] $VALUES = {new InterpreterApi$Options$TfLiteRuntime("FROM_APPLICATION_ONLY", 0), new InterpreterApi$Options$TfLiteRuntime("FROM_SYSTEM_ONLY", 1), new InterpreterApi$Options$TfLiteRuntime("PREFER_SYSTEM_OVER_APPLICATION", 2)};
    public static final InterpreterApi$Options$TfLiteRuntime FROM_APPLICATION_ONLY = null;

    /* JADX INFO: Fake field, exist only in values array */
    InterpreterApi$Options$TfLiteRuntime EF5;

    public static InterpreterApi$Options$TfLiteRuntime valueOf(String str) {
        return (InterpreterApi$Options$TfLiteRuntime) Enum.valueOf(InterpreterApi$Options$TfLiteRuntime.class, str);
    }

    public static InterpreterApi$Options$TfLiteRuntime[] values() {
        return (InterpreterApi$Options$TfLiteRuntime[]) $VALUES.clone();
    }
}
