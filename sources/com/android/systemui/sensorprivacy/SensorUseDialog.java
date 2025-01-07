package com.android.systemui.sensorprivacy;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.wm.shell.R;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SensorUseDialog extends SystemUIDialog {
    public final SensorUseStartedActivity clickListener;
    public final SensorUseStartedActivity dismissListener;

    public SensorUseDialog(SensorUseStartedActivity sensorUseStartedActivity, int i, SensorUseStartedActivity sensorUseStartedActivity2, SensorUseStartedActivity sensorUseStartedActivity3) {
        super(sensorUseStartedActivity);
        Window window = getWindow();
        Intrinsics.checkNotNull(window);
        window.addFlags(524288);
        Window window2 = getWindow();
        Intrinsics.checkNotNull(window2);
        window2.addSystemFlags(524288);
        View inflate = LayoutInflater.from(sensorUseStartedActivity).inflate(R.layout.sensor_use_started_title, (ViewGroup) null);
        inflate.requireViewById(R.id.sensor_use_started_title_message).setText(i != 1 ? i != 2 ? i != Integer.MAX_VALUE ? 0 : R.string.sensor_privacy_start_use_mic_camera_dialog_title : R.string.sensor_privacy_start_use_camera_dialog_title : R.string.sensor_privacy_start_use_mic_dialog_title);
        ((ImageView) inflate.requireViewById(R.id.sensor_use_microphone_icon)).setVisibility((i == 1 || i == Integer.MAX_VALUE) ? 0 : 8);
        ((ImageView) inflate.requireViewById(R.id.sensor_use_camera_icon)).setVisibility((i == 2 || i == Integer.MAX_VALUE) ? 0 : 8);
        setCustomTitle(inflate);
        setMessage(Html.fromHtml(sensorUseStartedActivity.getString(i != 1 ? i != 2 ? i != Integer.MAX_VALUE ? 0 : R.string.sensor_privacy_start_use_mic_camera_dialog_content : R.string.sensor_privacy_start_use_camera_dialog_content : R.string.sensor_privacy_start_use_mic_dialog_content), 0));
        setButton(-1, sensorUseStartedActivity.getString(android.R.string.shortcut_restored_on_lower_version), sensorUseStartedActivity2);
        setButton(-2, sensorUseStartedActivity.getString(android.R.string.cancel), sensorUseStartedActivity2);
        setOnDismissListener(sensorUseStartedActivity3);
        setCancelable(false);
    }
}
