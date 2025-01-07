package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.content.res.Resources;
import android.provider.DeviceConfig;
import android.text.TextUtils;
import android.util.KeyValueListParser;
import android.util.Log;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.wm.shell.R;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SmartReplyConstants {
    public final boolean mDefaultEditChoicesBeforeSending;
    public final boolean mDefaultEnabled;
    public final int mDefaultMaxNumActions;
    public final int mDefaultMaxSqueezeRemeasureAttempts;
    public final int mDefaultMinNumSystemGeneratedReplies;
    public final int mDefaultOnClickInitDelay;
    public final boolean mDefaultRequiresP;
    public final boolean mDefaultShowInHeadsUp;
    public final DeviceConfigProxy mDeviceConfig;
    public volatile boolean mEditChoicesBeforeSending;
    public volatile boolean mEnabled;
    public final Executor mMainExecutor;
    public volatile int mMaxNumActions;
    public volatile int mMaxSqueezeRemeasureAttempts;
    public volatile int mMinNumSystemGeneratedReplies;
    public volatile long mOnClickInitDelay;
    public final AnonymousClass1 mOnPropertiesChangedListener;
    public volatile boolean mRequiresTargetingP;
    public volatile boolean mShowInHeadsUp;

    public SmartReplyConstants(Executor executor, Context context, DeviceConfigProxy deviceConfigProxy) {
        new KeyValueListParser(',');
        DeviceConfig.OnPropertiesChangedListener onPropertiesChangedListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.systemui.statusbar.policy.SmartReplyConstants.1
            public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                if ("systemui".equals(properties.getNamespace())) {
                    SmartReplyConstants.this.updateConstants();
                    return;
                }
                Log.e("SmartReplyConstants", "Received update from DeviceConfig for unrelated namespace: " + properties.getNamespace());
            }
        };
        this.mMainExecutor = executor;
        Resources resources = context.getResources();
        this.mDefaultEnabled = resources.getBoolean(R.bool.config_smart_replies_in_notifications_enabled);
        this.mDefaultRequiresP = resources.getBoolean(R.bool.config_smart_replies_in_notifications_requires_targeting_p);
        this.mDefaultMaxSqueezeRemeasureAttempts = resources.getInteger(R.integer.config_smart_replies_in_notifications_max_squeeze_remeasure_attempts);
        this.mDefaultEditChoicesBeforeSending = resources.getBoolean(R.bool.config_smart_replies_in_notifications_edit_choices_before_sending);
        this.mDefaultShowInHeadsUp = resources.getBoolean(R.bool.config_smart_replies_in_notifications_show_in_heads_up);
        this.mDefaultMinNumSystemGeneratedReplies = resources.getInteger(R.integer.config_smart_replies_in_notifications_min_num_system_generated_replies);
        this.mDefaultMaxNumActions = resources.getInteger(R.integer.config_smart_replies_in_notifications_max_num_actions);
        this.mDefaultOnClickInitDelay = resources.getInteger(R.integer.config_smart_replies_in_notifications_onclick_init_delay);
        this.mDeviceConfig = deviceConfigProxy;
        Executor executor2 = new Executor() { // from class: com.android.systemui.statusbar.policy.SmartReplyConstants$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                SmartReplyConstants.this.mMainExecutor.execute(runnable);
            }
        };
        deviceConfigProxy.getClass();
        DeviceConfig.addOnPropertiesChangedListener("systemui", executor2, onPropertiesChangedListener);
        updateConstants();
    }

    public final boolean readDeviceConfigBooleanOrDefaultIfEmpty(String str, boolean z) {
        this.mDeviceConfig.getClass();
        String property = DeviceConfig.getProperty("systemui", str);
        if (TextUtils.isEmpty(property)) {
            return z;
        }
        if ("true".equals(property)) {
            return true;
        }
        if ("false".equals(property)) {
            return false;
        }
        return z;
    }

    public final void updateConstants() {
        synchronized (this) {
            this.mEnabled = readDeviceConfigBooleanOrDefaultIfEmpty("ssin_enabled", this.mDefaultEnabled);
            this.mRequiresTargetingP = readDeviceConfigBooleanOrDefaultIfEmpty("ssin_requires_targeting_p", this.mDefaultRequiresP);
            DeviceConfigProxy deviceConfigProxy = this.mDeviceConfig;
            int i = this.mDefaultMaxSqueezeRemeasureAttempts;
            deviceConfigProxy.getClass();
            this.mMaxSqueezeRemeasureAttempts = DeviceConfig.getInt("systemui", "ssin_max_squeeze_remeasure_attempts", i);
            this.mEditChoicesBeforeSending = readDeviceConfigBooleanOrDefaultIfEmpty("ssin_edit_choices_before_sending", this.mDefaultEditChoicesBeforeSending);
            this.mShowInHeadsUp = readDeviceConfigBooleanOrDefaultIfEmpty("ssin_show_in_heads_up", this.mDefaultShowInHeadsUp);
            DeviceConfigProxy deviceConfigProxy2 = this.mDeviceConfig;
            int i2 = this.mDefaultMinNumSystemGeneratedReplies;
            deviceConfigProxy2.getClass();
            this.mMinNumSystemGeneratedReplies = DeviceConfig.getInt("systemui", "ssin_min_num_system_generated_replies", i2);
            DeviceConfigProxy deviceConfigProxy3 = this.mDeviceConfig;
            int i3 = this.mDefaultMaxNumActions;
            deviceConfigProxy3.getClass();
            this.mMaxNumActions = DeviceConfig.getInt("systemui", "ssin_max_num_actions", i3);
            DeviceConfigProxy deviceConfigProxy4 = this.mDeviceConfig;
            int i4 = this.mDefaultOnClickInitDelay;
            deviceConfigProxy4.getClass();
            this.mOnClickInitDelay = DeviceConfig.getInt("systemui", "ssin_onclick_init_delay", i4);
        }
    }
}
