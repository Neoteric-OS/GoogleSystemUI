package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.icu.lang.UCharacter;
import android.icu.text.DateTimePatternGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.UserHandle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.systemui.Dependency;
import com.android.systemui.FontSizeUtils;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.demomode.DemoModeCommandReceiver;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.res.R$styleable;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.tuner.TunerService;
import com.android.wm.shell.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class Clock extends TextView implements DemoModeCommandReceiver, TunerService.Tunable, CommandQueue.Callbacks, DarkIconDispatcher.DarkReceiver, ConfigurationController.ConfigurationListener {
    public final int mAmPmStyle;
    public boolean mAttached;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public int mCachedWidth;
    public Calendar mCalendar;
    public int mCharsAtCurrentWidth;
    public SimpleDateFormat mClockFormat;
    public boolean mClockVisibleByPolicy;
    public boolean mClockVisibleByUser;
    public final CommandQueue mCommandQueue;
    public SimpleDateFormat mContentDescriptionFormat;
    public String mContentDescriptionFormatString;
    public int mCurrentUserId;
    public DateTimePatternGenerator mDateTimePatternGenerator;
    public boolean mDemoMode;
    public final AnonymousClass2 mIntentReceiver;
    public Locale mLocale;
    public final AnonymousClass2 mScreenReceiver;
    public boolean mScreenReceiverRegistered;
    public final AnonymousClass4 mSecondTick;
    public Handler mSecondsHandler;
    public boolean mShowSeconds;
    public final UserTracker.Callback mUserChangedCallback;
    public final UserTracker mUserTracker;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.policy.Clock$2, reason: invalid class name */
    public final class AnonymousClass2 extends BroadcastReceiver {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Clock this$0;

        public /* synthetic */ AnonymousClass2(Clock clock, int i) {
            this.$r8$classId = i;
            this.this$0 = clock;
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            Clock clock;
            Handler handler;
            switch (this.$r8$classId) {
                case 0:
                    Handler handler2 = this.this$0.getHandler();
                    if (handler2 != null) {
                        String action = intent.getAction();
                        if (action.equals("android.intent.action.TIMEZONE_CHANGED")) {
                            handler2.post(new Clock$2$$ExternalSyntheticLambda0(this, intent.getStringExtra("time-zone")));
                        } else if (action.equals("android.intent.action.CONFIGURATION_CHANGED")) {
                            handler2.post(new Clock$2$$ExternalSyntheticLambda0(this, this.this$0.getResources().getConfiguration().locale));
                        }
                        handler2.post(new Runnable() { // from class: com.android.systemui.statusbar.policy.Clock$2$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                Clock.AnonymousClass2.this.this$0.updateClock();
                            }
                        });
                        break;
                    }
                    break;
                default:
                    String action2 = intent.getAction();
                    if (!"android.intent.action.SCREEN_OFF".equals(action2)) {
                        if ("android.intent.action.SCREEN_ON".equals(action2) && (handler = (clock = this.this$0).mSecondsHandler) != null) {
                            handler.postAtTime(clock.mSecondTick, ((SystemClock.uptimeMillis() / 1000) * 1000) + 1000);
                            break;
                        }
                    } else {
                        Clock clock2 = this.this$0;
                        Handler handler3 = clock2.mSecondsHandler;
                        if (handler3 != null) {
                            handler3.removeCallbacks(clock2.mSecondTick);
                            break;
                        }
                    }
                    break;
            }
        }
    }

    public Clock(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void disable(int i, int i2, int i3, boolean z) {
        if (i != getDisplay().getDisplayId()) {
            return;
        }
        boolean z2 = (8388608 & i2) == 0;
        if (z2 != this.mClockVisibleByPolicy) {
            this.mClockVisibleByPolicy = z2;
            updateClockVisibility();
        }
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void dispatchDemoCommand(Bundle bundle, String str) {
        String string = bundle.getString("millis");
        String string2 = bundle.getString("hhmm");
        if (string != null) {
            this.mCalendar.setTimeInMillis(Long.parseLong(string));
        } else if (string2 != null && string2.length() == 4) {
            int parseInt = Integer.parseInt(string2.substring(0, 2));
            int parseInt2 = Integer.parseInt(string2.substring(2));
            if (DateFormat.is24HourFormat(getContext(), this.mCurrentUserId)) {
                this.mCalendar.set(11, parseInt);
            } else {
                this.mCalendar.set(10, parseInt);
            }
            this.mCalendar.set(12, parseInt2);
        }
        setText(getSmallTime());
        setContentDescription(this.mContentDescriptionFormat.format(this.mCalendar.getTime()));
    }

    public final CharSequence getSmallTime() {
        Context context = getContext();
        boolean is24HourFormat = DateFormat.is24HourFormat(context, this.mCurrentUserId);
        if (this.mDateTimePatternGenerator == null) {
            this.mDateTimePatternGenerator = DateTimePatternGenerator.getInstance(context.getResources().getConfiguration().locale);
        }
        String bestPattern = this.mDateTimePatternGenerator.getBestPattern(this.mShowSeconds ? is24HourFormat ? "Hms" : "hms" : is24HourFormat ? "Hm" : "hm");
        if (!bestPattern.equals(this.mContentDescriptionFormatString)) {
            this.mContentDescriptionFormatString = bestPattern;
            this.mContentDescriptionFormat = new SimpleDateFormat(bestPattern);
            if (this.mAmPmStyle != 0) {
                int i = 0;
                boolean z = false;
                while (true) {
                    if (i >= bestPattern.length()) {
                        i = -1;
                        break;
                    }
                    char charAt = bestPattern.charAt(i);
                    if (charAt == '\'') {
                        z = !z;
                    }
                    if (!z && charAt == 'a') {
                        break;
                    }
                    i++;
                }
                if (i >= 0) {
                    int i2 = i;
                    while (i2 > 0 && UCharacter.isUWhiteSpace(bestPattern.charAt(i2 - 1))) {
                        i2--;
                    }
                    bestPattern = bestPattern.substring(0, i2) + (char) 61184 + bestPattern.substring(i2, i) + "a\uef01" + bestPattern.substring(i + 1);
                }
            }
            this.mClockFormat = new SimpleDateFormat(bestPattern);
        }
        String format = this.mClockFormat.format(this.mCalendar.getTime());
        if (this.mAmPmStyle != 0) {
            int indexOf = format.indexOf(61184);
            int indexOf2 = format.indexOf(61185);
            if (indexOf >= 0 && indexOf2 > indexOf) {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format);
                int i3 = this.mAmPmStyle;
                if (i3 == 2) {
                    spannableStringBuilder.delete(indexOf, indexOf2 + 1);
                } else {
                    if (i3 == 1) {
                        spannableStringBuilder.setSpan(new RelativeSizeSpan(0.7f), indexOf, indexOf2, 34);
                    }
                    spannableStringBuilder.delete(indexOf2, indexOf2 + 1);
                    spannableStringBuilder.delete(indexOf, indexOf + 1);
                }
                return spannableStringBuilder;
            }
        }
        return format;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.mAttached) {
            this.mAttached = true;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_TICK");
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
            BroadcastDispatcher broadcastDispatcher = this.mBroadcastDispatcher;
            AnonymousClass2 anonymousClass2 = this.mIntentReceiver;
            Handler handler = (Handler) Dependency.sDependency.getDependencyInner(Dependency.TIME_TICK_HANDLER);
            UserHandle userHandle = UserHandle.ALL;
            broadcastDispatcher.getClass();
            BroadcastDispatcher.registerReceiverWithHandler$default(broadcastDispatcher, anonymousClass2, intentFilter, handler, userHandle, 48);
            ((TunerService) Dependency.sDependency.getDependencyInner(TunerService.class)).addTunable(this, "clock_seconds", "icon_blacklist");
            this.mCommandQueue.addCallback((CommandQueue.Callbacks) this);
            ((UserTrackerImpl) this.mUserTracker).addCallback(this.mUserChangedCallback, ((TextView) this).mContext.getMainExecutor());
            this.mCurrentUserId = ((UserTrackerImpl) this.mUserTracker).getUserId();
        }
        this.mCalendar = Calendar.getInstance(TimeZone.getDefault());
        this.mContentDescriptionFormatString = "";
        this.mDateTimePatternGenerator = null;
        updateClock();
        updateClockVisibility();
        updateShowSeconds();
    }

    @Override // com.android.systemui.plugins.DarkIconDispatcher.DarkReceiver
    public final void onDarkChanged(ArrayList arrayList, float f, int i) {
        setTextColor(DarkIconDispatcher.getTint(arrayList, this, i));
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void onDemoModeFinished() {
        this.mDemoMode = false;
        updateClock();
    }

    @Override // com.android.systemui.demomode.DemoModeCommandReceiver
    public final void onDemoModeStarted() {
        this.mDemoMode = true;
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onDensityOrFontScaleChanged() {
        this.mCachedWidth = -1;
        FontSizeUtils.updateFontSize(this, R.dimen.status_bar_clock_size);
        setPaddingRelative(((TextView) this).mContext.getResources().getDimensionPixelSize(R.dimen.status_bar_clock_starting_padding), 0, ((TextView) this).mContext.getResources().getDimensionPixelSize(R.dimen.status_bar_clock_end_padding), 0);
        float fontMetricsInt = getPaint().getFontMetricsInt(null);
        setLineHeight(0, fontMetricsInt);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = (int) Math.ceil(fontMetricsInt);
            setLayoutParams(layoutParams);
        }
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mScreenReceiverRegistered) {
            this.mScreenReceiverRegistered = false;
            this.mBroadcastDispatcher.unregisterReceiver(this.mScreenReceiver);
            Handler handler = this.mSecondsHandler;
            if (handler != null) {
                handler.removeCallbacks(this.mSecondTick);
                this.mSecondsHandler = null;
            }
        }
        if (this.mAttached) {
            this.mBroadcastDispatcher.unregisterReceiver(this.mIntentReceiver);
            this.mAttached = false;
            ((TunerService) Dependency.sDependency.getDependencyInner(TunerService.class)).removeTunable(this);
            this.mCommandQueue.removeCallback((CommandQueue.Callbacks) this);
            ((UserTrackerImpl) this.mUserTracker).removeCallback(this.mUserChangedCallback);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int length = getText().length();
        if (length != this.mCharsAtCurrentWidth) {
            this.mCharsAtCurrentWidth = length;
            this.mCachedWidth = getMeasuredWidth();
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int i3 = this.mCachedWidth;
        if (i3 > measuredWidth) {
            setMeasuredDimension(i3, getMeasuredHeight());
        } else {
            this.mCachedWidth = measuredWidth;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable == null || !(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("clock_super_parcelable"));
        if (bundle.containsKey("current_user_id")) {
            this.mCurrentUserId = bundle.getInt("current_user_id");
        }
        this.mClockVisibleByPolicy = bundle.getBoolean("visible_by_policy", true);
        this.mClockVisibleByUser = bundle.getBoolean("visible_by_user", true);
        this.mShowSeconds = bundle.getBoolean("show_seconds", false);
        if (bundle.containsKey("visibility")) {
            super.setVisibility(bundle.getInt("visibility"));
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("clock_super_parcelable", super.onSaveInstanceState());
        bundle.putInt("current_user_id", this.mCurrentUserId);
        bundle.putBoolean("visible_by_policy", this.mClockVisibleByPolicy);
        bundle.putBoolean("visible_by_user", this.mClockVisibleByUser);
        bundle.putBoolean("show_seconds", this.mShowSeconds);
        bundle.putInt("visibility", getVisibility());
        return bundle;
    }

    @Override // com.android.systemui.tuner.TunerService.Tunable
    public final void onTuningChanged(String str, String str2) {
        if ("clock_seconds".equals(str)) {
            this.mShowSeconds = TunerService.parseIntegerSwitch(str2, false);
            updateShowSeconds();
        } else if ("icon_blacklist".equals(str)) {
            this.mClockVisibleByUser = !StatusBarIconController.getIconHideList(getContext(), str2).contains("clock");
            updateClockVisibility();
            updateClockVisibility();
        }
    }

    @Override // android.view.View
    public final void setVisibility(int i) {
        if (i != 0 || (this.mClockVisibleByPolicy && this.mClockVisibleByUser)) {
            super.setVisibility(i);
        }
    }

    public final void updateClock() {
        if (this.mDemoMode) {
            return;
        }
        this.mCalendar.setTimeInMillis(System.currentTimeMillis());
        CharSequence smallTime = getSmallTime();
        if (!TextUtils.equals(smallTime, getText())) {
            setText(smallTime);
        }
        setContentDescription(this.mContentDescriptionFormat.format(this.mCalendar.getTime()));
    }

    public final void updateClockVisibility() {
        super.setVisibility((this.mClockVisibleByPolicy && this.mClockVisibleByUser) ? 0 : 8);
    }

    public final void updateShowSeconds() {
        if (!this.mShowSeconds) {
            if (this.mSecondsHandler != null) {
                this.mScreenReceiverRegistered = false;
                this.mBroadcastDispatcher.unregisterReceiver(this.mScreenReceiver);
                this.mSecondsHandler.removeCallbacks(this.mSecondTick);
                this.mSecondsHandler = null;
                updateClock();
                return;
            }
            return;
        }
        if (this.mSecondsHandler != null || getDisplay() == null) {
            return;
        }
        this.mSecondsHandler = new Handler();
        if (getDisplay().getState() == 2) {
            this.mSecondsHandler.postAtTime(this.mSecondTick, ((SystemClock.uptimeMillis() / 1000) * 1000) + 1000);
        }
        this.mScreenReceiverRegistered = true;
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        this.mBroadcastDispatcher.registerReceiver(this.mScreenReceiver, intentFilter);
    }

    /* JADX WARN: Type inference failed for: r5v2, types: [com.android.systemui.statusbar.policy.Clock$4] */
    public Clock(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mClockVisibleByPolicy = true;
        this.mClockVisibleByUser = true;
        this.mCharsAtCurrentWidth = -1;
        this.mCachedWidth = -1;
        this.mUserChangedCallback = new UserTracker.Callback() { // from class: com.android.systemui.statusbar.policy.Clock.1
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i2, Context context2) {
                Clock clock = Clock.this;
                clock.mCurrentUserId = i2;
                clock.updateClock();
            }
        };
        this.mIntentReceiver = new AnonymousClass2(this, 0);
        this.mScreenReceiver = new AnonymousClass2(this, 1);
        this.mSecondTick = new Runnable() { // from class: com.android.systemui.statusbar.policy.Clock.4
            @Override // java.lang.Runnable
            public final void run() {
                Clock clock = Clock.this;
                if (clock.mCalendar != null) {
                    clock.updateClock();
                }
                Clock.this.mSecondsHandler.postAtTime(this, ((SystemClock.uptimeMillis() / 1000) * 1000) + 1000);
            }
        };
        this.mCommandQueue = (CommandQueue) Dependency.sDependency.getDependencyInner(CommandQueue.class);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.Clock, 0, 0);
        try {
            this.mAmPmStyle = obtainStyledAttributes.getInt(0, 2);
            getCurrentTextColor();
            obtainStyledAttributes.recycle();
            this.mBroadcastDispatcher = (BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class);
            this.mUserTracker = (UserTracker) Dependency.sDependency.getDependencyInner(UserTracker.class);
            setIncludeFontPadding(false);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
}
