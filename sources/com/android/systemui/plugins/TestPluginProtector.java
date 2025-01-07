package com.android.systemui.plugins;

import android.content.Context;
import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class TestPluginProtector implements TestPlugin, PluginWrapper {
    private static final String CLASS = "TestPlugin";
    private boolean mHasError = false;
    private TestPlugin mInstance;
    private ProtectedPluginListener mListener;

    private TestPluginProtector(TestPlugin testPlugin, ProtectedPluginListener protectedPluginListener) {
        this.mInstance = testPlugin;
        this.mListener = protectedPluginListener;
    }

    public static TestPluginProtector protect(TestPlugin testPlugin, ProtectedPluginListener protectedPluginListener) {
        return testPlugin instanceof TestPluginProtector ? (TestPluginProtector) testPlugin : new TestPluginProtector(testPlugin, protectedPluginListener);
    }

    @Override // com.android.systemui.plugins.Plugin
    public int getVersion() {
        if (this.mHasError) {
            return -1;
        }
        try {
            return this.mInstance.getVersion();
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: getVersion", e);
            this.mHasError = this.mListener.onFail(CLASS, "getVersion", e);
            return -1;
        }
    }

    @Override // com.android.systemui.plugins.TestPlugin
    public Object methodThrowsError() {
        if (this.mHasError) {
            return new Object();
        }
        try {
            return this.mInstance.methodThrowsError();
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: methodThrowsError", e);
            this.mHasError = this.mListener.onFail(CLASS, "methodThrowsError", e);
            return new Object();
        }
    }

    @Override // com.android.systemui.plugins.Plugin
    public void onCreate(Context context, Context context2) {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onCreate(context, context2);
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onCreate", e);
            this.mHasError = this.mListener.onFail(CLASS, "onCreate", e);
        }
    }

    @Override // com.android.systemui.plugins.Plugin
    public void onDestroy() {
        if (this.mHasError) {
            return;
        }
        try {
            this.mInstance.onDestroy();
        } catch (LinkageError e) {
            Log.wtf(CLASS, "Failed to execute: onDestroy", e);
            this.mHasError = this.mListener.onFail(CLASS, "onDestroy", e);
        }
    }

    @Override // com.android.systemui.plugins.PluginWrapper
    public TestPlugin getPlugin() {
        return this.mInstance;
    }
}
