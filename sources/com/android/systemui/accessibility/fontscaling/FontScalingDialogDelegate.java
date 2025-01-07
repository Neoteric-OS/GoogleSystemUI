package com.android.systemui.accessibility.fontscaling;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import com.android.systemui.common.ui.view.SeekBarWithIconButtonsView;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SystemSettingsImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FontScalingDialogDelegate implements SystemUIDialog.Delegate {
    public static boolean fontSizeHasBeenChangedFromTile;
    public final DelayableExecutor backgroundDelayableExecutor;
    public ExecutorImpl.ExecutionToken cancelUpdateFontScaleRunnable;
    public final Configuration configuration;
    public final Context context;
    public Button doneButton;
    public final FontScalingDialogDelegate$fontSizeObserver$1 fontSizeObserver;
    public final AtomicInteger lastProgress = new AtomicInteger(-1);
    public long lastUpdateTime;
    public final LayoutInflater layoutInflater;
    public final SecureSettings secureSettings;
    public SeekBarWithIconButtonsView seekBarWithIconButtonsView;
    public final String[] strEntryValues;
    public final SystemClock systemClock;
    public final SystemSettingsImpl systemSettings;
    public final SystemUIDialog.Factory systemUIDialogFactory;
    public TextView title;
    public final UserTracker userTracker;

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.accessibility.fontscaling.FontScalingDialogDelegate$fontSizeObserver$1] */
    public FontScalingDialogDelegate(Context context, SystemUIDialog.Factory factory, LayoutInflater layoutInflater, SystemSettingsImpl systemSettingsImpl, SecureSettings secureSettings, SystemClock systemClock, UserTracker userTracker, final Handler handler, DelayableExecutor delayableExecutor) {
        this.context = context;
        this.systemUIDialogFactory = factory;
        this.layoutInflater = layoutInflater;
        this.systemSettings = systemSettingsImpl;
        this.secureSettings = secureSettings;
        this.systemClock = systemClock;
        this.userTracker = userTracker;
        this.backgroundDelayableExecutor = delayableExecutor;
        this.strEntryValues = context.getResources().getStringArray(R.array.entryvalues_font_size);
        this.configuration = new Configuration(context.getResources().getConfiguration());
        this.fontSizeObserver = new ContentObserver(handler) { // from class: com.android.systemui.accessibility.fontscaling.FontScalingDialogDelegate$fontSizeObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                FontScalingDialogDelegate fontScalingDialogDelegate = this;
                ((SystemClockImpl) fontScalingDialogDelegate.systemClock).getClass();
                fontScalingDialogDelegate.lastUpdateTime = android.os.SystemClock.elapsedRealtime();
            }
        };
    }

    public static final void access$changeFontSize(FontScalingDialogDelegate fontScalingDialogDelegate, int i, long j) {
        if (i != fontScalingDialogDelegate.lastProgress.get()) {
            fontScalingDialogDelegate.lastProgress.set(i);
            boolean z = fontSizeHasBeenChangedFromTile;
            DelayableExecutor delayableExecutor = fontScalingDialogDelegate.backgroundDelayableExecutor;
            if (!z) {
                ((ExecutorImpl) delayableExecutor).execute(new FontScalingDialogDelegate$changeFontSize$1(fontScalingDialogDelegate, 0));
                fontSizeHasBeenChangedFromTile = true;
            }
            Button button = fontScalingDialogDelegate.doneButton;
            if (button == null) {
                button = null;
            }
            button.setEnabled(false);
            ((SystemClockImpl) fontScalingDialogDelegate.systemClock).getClass();
            if (android.os.SystemClock.elapsedRealtime() - fontScalingDialogDelegate.lastUpdateTime < 800) {
                j += 800;
            }
            ExecutorImpl.ExecutionToken executionToken = fontScalingDialogDelegate.cancelUpdateFontScaleRunnable;
            if (executionToken != null) {
                executionToken.run();
            }
            fontScalingDialogDelegate.cancelUpdateFontScaleRunnable = delayableExecutor.executeDelayed(new FontScalingDialogDelegate$changeFontSize$1(fontScalingDialogDelegate, 3), j);
        }
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void beforeCreate(Dialog dialog) {
        SystemUIDialog systemUIDialog = (SystemUIDialog) dialog;
        systemUIDialog.setTitle(R.string.font_scaling_dialog_title);
        systemUIDialog.setView(this.layoutInflater.inflate(R.layout.font_scaling_dialog, (ViewGroup) null));
        systemUIDialog.setButton(-1, R.string.quick_settings_done, null, true);
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog.Delegate
    public final SystemUIDialog createDialog() {
        SystemUIDialog.Factory factory = this.systemUIDialogFactory;
        return factory.create(this, factory.mContext);
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onConfigurationChanged(Dialog dialog, Configuration configuration) {
        int diff = configuration.diff(this.configuration);
        this.configuration.setTo(configuration);
        if ((diff & 1073741824) != 0) {
            TextView textView = this.title;
            if (textView == null) {
                textView = null;
            }
            textView.post(new FontScalingDialogDelegate$changeFontSize$1(this, 1));
        }
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onCreate(Dialog dialog, Bundle bundle) {
        int length;
        final SystemUIDialog systemUIDialog = (SystemUIDialog) dialog;
        this.title = (TextView) systemUIDialog.requireViewById(android.R.id.ampm_layout);
        this.doneButton = (Button) systemUIDialog.requireViewById(android.R.id.button1);
        this.seekBarWithIconButtonsView = (SeekBarWithIconButtonsView) systemUIDialog.requireViewById(R.id.font_scaling_slider);
        String[] strArr = this.strEntryValues;
        String[] strArr2 = new String[strArr.length];
        int length2 = strArr.length;
        for (int i = 0; i < length2; i++) {
            strArr2[i] = this.context.getResources().getString(R.string.font_scale_percentage, Integer.valueOf(MathKt.roundToInt(Float.parseFloat(strArr[i]) * 100)));
        }
        SeekBarWithIconButtonsView seekBarWithIconButtonsView = this.seekBarWithIconButtonsView;
        if (seekBarWithIconButtonsView == null) {
            seekBarWithIconButtonsView = null;
        }
        seekBarWithIconButtonsView.mStateLabels = strArr2;
        SeekBar seekBar = seekBarWithIconButtonsView.mSeekbar;
        int progress = seekBar.getProgress();
        String[] strArr3 = seekBarWithIconButtonsView.mStateLabels;
        seekBar.setStateDescription(progress < strArr3.length ? strArr3[seekBarWithIconButtonsView.mSeekbar.getProgress()] : "");
        SeekBarWithIconButtonsView seekBarWithIconButtonsView2 = this.seekBarWithIconButtonsView;
        if (seekBarWithIconButtonsView2 == null) {
            seekBarWithIconButtonsView2 = null;
        }
        seekBarWithIconButtonsView2.mSeekbar.setMax(strArr.length - 1);
        int userId = ((UserTrackerImpl) this.userTracker).getUserId();
        SystemSettingsImpl systemSettingsImpl = this.systemSettings;
        float floatForUser = systemSettingsImpl.getFloatForUser(1.0f, userId, "font_scale");
        AtomicInteger atomicInteger = this.lastProgress;
        float parseFloat = Float.parseFloat(strArr[0]);
        int length3 = strArr.length;
        int i2 = 1;
        while (true) {
            if (i2 >= length3) {
                length = strArr.length - 1;
                break;
            }
            float parseFloat2 = Float.parseFloat(strArr[i2]);
            if (floatForUser < AndroidFlingSpline$$ExternalSyntheticOutline0.m(parseFloat2, parseFloat, 0.5f, parseFloat)) {
                length = i2 - 1;
                break;
            } else {
                i2++;
                parseFloat = parseFloat2;
            }
        }
        atomicInteger.set(length);
        SeekBarWithIconButtonsView seekBarWithIconButtonsView3 = this.seekBarWithIconButtonsView;
        if (seekBarWithIconButtonsView3 == null) {
            seekBarWithIconButtonsView3 = null;
        }
        seekBarWithIconButtonsView3.setProgress(this.lastProgress.get());
        SeekBarWithIconButtonsView seekBarWithIconButtonsView4 = this.seekBarWithIconButtonsView;
        if (seekBarWithIconButtonsView4 == null) {
            seekBarWithIconButtonsView4 = null;
        }
        seekBarWithIconButtonsView4.mSeekBarListener.mOnSeekBarChangeListener = new SeekBarWithIconButtonsView.OnSeekBarWithIconButtonsChangeListener() { // from class: com.android.systemui.accessibility.fontscaling.FontScalingDialogDelegate$onCreate$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public final void onProgressChanged(SeekBar seekBar2, int i3, boolean z) {
                FontScalingDialogDelegate fontScalingDialogDelegate = FontScalingDialogDelegate.this;
                fontScalingDialogDelegate.getClass();
                Configuration configuration = new Configuration(fontScalingDialogDelegate.configuration);
                configuration.fontScale = Float.parseFloat(fontScalingDialogDelegate.strEntryValues[i3]);
                Context createConfigurationContext = fontScalingDialogDelegate.context.createConfigurationContext(configuration);
                createConfigurationContext.getTheme().setTo(fontScalingDialogDelegate.context.getTheme());
                TextView textView = fontScalingDialogDelegate.title;
                if (textView == null) {
                    textView = null;
                }
                textView.setTextSize(0, createConfigurationContext.getResources().getDimension(R.dimen.dialog_title_text_size));
            }

            @Override // com.android.systemui.common.ui.view.SeekBarWithIconButtonsView.OnSeekBarWithIconButtonsChangeListener
            public final void onUserInteractionFinalized(SeekBar seekBar2, int i3) {
                if (i3 == 1) {
                    FontScalingDialogDelegate fontScalingDialogDelegate = FontScalingDialogDelegate.this;
                    int progress2 = seekBar2.getProgress();
                    FontScalingDialogDelegate.this.getClass();
                    FontScalingDialogDelegate.access$changeFontSize(fontScalingDialogDelegate, progress2, 300L);
                    return;
                }
                FontScalingDialogDelegate fontScalingDialogDelegate2 = FontScalingDialogDelegate.this;
                int progress3 = seekBar2.getProgress();
                FontScalingDialogDelegate.this.getClass();
                FontScalingDialogDelegate.access$changeFontSize(fontScalingDialogDelegate2, progress3, 100L);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public final void onStartTrackingTouch(SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public final void onStopTrackingTouch(SeekBar seekBar2) {
            }
        };
        Button button = this.doneButton;
        (button != null ? button : null).setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.accessibility.fontscaling.FontScalingDialogDelegate$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SystemUIDialog.this.dismiss();
            }
        });
        systemSettingsImpl.registerContentObserverSync("font_scale", this.fontSizeObserver);
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onStop(Dialog dialog) {
        ExecutorImpl.ExecutionToken executionToken = this.cancelUpdateFontScaleRunnable;
        if (executionToken != null) {
            executionToken.run();
        }
        this.cancelUpdateFontScaleRunnable = null;
        this.systemSettings.unregisterContentObserverSync(this.fontSizeObserver);
    }
}
