package com.android.systemui.screenrecord;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenRecordingAdapter extends ArrayAdapter {
    public LinearLayout mSelectedInternal;
    public LinearLayout mSelectedMic;
    public LinearLayout mSelectedMicAndInternal;

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public final View getDropDownView(int i, View view, ViewGroup viewGroup) {
        int ordinal = ((ScreenRecordingAudioSource) getItem(i)).ordinal();
        return ordinal != 1 ? ordinal != 2 ? ordinal != 3 ? super.getDropDownView(i, view, viewGroup) : getOption(R.string.screenrecord_device_audio_and_mic_label, 0) : getOption(R.string.screenrecord_mic_label, 0) : getOption(R.string.screenrecord_device_audio_label, R.string.screenrecord_device_audio_description);
    }

    public final LinearLayout getOption(int i, int i2) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.screen_record_dialog_audio_source, (ViewGroup) null, false);
        ((TextView) linearLayout.findViewById(R.id.screen_recording_dialog_source_text)).setText(i);
        TextView textView = (TextView) linearLayout.findViewById(R.id.screen_recording_dialog_source_description);
        if (i2 != 0) {
            textView.setText(i2);
        } else {
            textView.setVisibility(8);
        }
        return linearLayout;
    }

    public final LinearLayout getSelected(int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.screen_record_dialog_audio_source_selected, (ViewGroup) null, false);
        ((TextView) linearLayout.findViewById(R.id.screen_recording_dialog_source_text)).setText(i);
        return linearLayout;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        int ordinal = ((ScreenRecordingAudioSource) getItem(i)).ordinal();
        return ordinal != 1 ? ordinal != 2 ? ordinal != 3 ? super.getView(i, view, viewGroup) : this.mSelectedMicAndInternal : this.mSelectedMic : this.mSelectedInternal;
    }
}
