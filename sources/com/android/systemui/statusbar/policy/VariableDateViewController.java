package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.DateFormat;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.UserHandle;
import android.view.View;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.shade.ShadeLogger;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import java.util.Date;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VariableDateViewController extends ViewController {
    public final BroadcastDispatcher broadcastDispatcher;
    public final Date currentTime;
    public DateFormat dateFormat;
    public String datePattern;
    public final VariableDateViewController$intentReceiver$1 intentReceiver;
    public boolean isQsExpanded;
    public String lastText;
    public int lastWidth;
    public final VariableDateViewController$onMeasureListener$1 onMeasureListener;
    public final ShadeInteractor shadeInteractor;
    public final ShadeLogger shadeLogger;
    public final SystemClock systemClock;
    public final Handler timeTickHandler;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final BroadcastDispatcher broadcastDispatcher;
        public final Handler handler;
        public final ShadeInteractor shadeInteractor;
        public final ShadeLogger shadeLogger;
        public final SystemClock systemClock;

        public Factory(SystemClock systemClock, BroadcastDispatcher broadcastDispatcher, ShadeInteractor shadeInteractor, ShadeLogger shadeLogger, Handler handler) {
            this.systemClock = systemClock;
            this.broadcastDispatcher = broadcastDispatcher;
            this.shadeInteractor = shadeInteractor;
            this.shadeLogger = shadeLogger;
            this.handler = handler;
        }
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [com.android.systemui.statusbar.policy.VariableDateViewController$intentReceiver$1] */
    public VariableDateViewController(SystemClock systemClock, BroadcastDispatcher broadcastDispatcher, ShadeInteractor shadeInteractor, ShadeLogger shadeLogger, Handler handler, VariableDateView variableDateView) {
        super(variableDateView);
        this.systemClock = systemClock;
        this.broadcastDispatcher = broadcastDispatcher;
        this.shadeInteractor = shadeInteractor;
        this.shadeLogger = shadeLogger;
        this.timeTickHandler = handler;
        this.datePattern = variableDateView.longerPattern;
        this.lastWidth = Integer.MAX_VALUE;
        this.lastText = "";
        this.currentTime = new Date();
        this.intentReceiver = new BroadcastReceiver() { // from class: com.android.systemui.statusbar.policy.VariableDateViewController$intentReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ("android.intent.action.LOCALE_CHANGED".equals(action) || "android.intent.action.TIMEZONE_CHANGED".equals(action)) {
                    VariableDateViewController variableDateViewController = VariableDateViewController.this;
                    variableDateViewController.dateFormat = null;
                    variableDateViewController.shadeLogger.d("VariableDateViewController received intent to refresh date format");
                }
                Handler handler2 = ((VariableDateView) VariableDateViewController.this.mView).getHandler();
                if (handler2 == null) {
                    VariableDateViewController.this.shadeLogger.d("VariableDateViewController received intent but handler was null");
                    return;
                }
                if ("android.intent.action.TIME_TICK".equals(action) || "android.intent.action.TIME_SET".equals(action) || "android.intent.action.TIMEZONE_CHANGED".equals(action) || "android.intent.action.LOCALE_CHANGED".equals(action)) {
                    final VariableDateViewController variableDateViewController2 = VariableDateViewController.this;
                    handler2.post(new Runnable() { // from class: com.android.systemui.statusbar.policy.VariableDateViewController$intentReceiver$1$onReceive$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            VariableDateViewController.access$updateClock(VariableDateViewController.this);
                        }
                    });
                }
            }
        };
        this.onMeasureListener = new VariableDateViewController$onMeasureListener$1(this);
    }

    public static final void access$updateClock(VariableDateViewController variableDateViewController) {
        if (variableDateViewController.dateFormat == null) {
            variableDateViewController.dateFormat = VariableDateViewControllerKt.getFormatFromPattern(variableDateViewController.datePattern);
        }
        Date date = variableDateViewController.currentTime;
        ((SystemClockImpl) variableDateViewController.systemClock).getClass();
        date.setTime(System.currentTimeMillis());
        Date date2 = variableDateViewController.currentTime;
        DateFormat dateFormat = variableDateViewController.dateFormat;
        Intrinsics.checkNotNull(dateFormat);
        String textForFormat = VariableDateViewControllerKt.getTextForFormat(date2, dateFormat);
        if (Intrinsics.areEqual(textForFormat, variableDateViewController.lastText)) {
            return;
        }
        ((VariableDateView) variableDateViewController.mView).setText(textForFormat);
        variableDateViewController.lastText = textForFormat;
    }

    public final void changePattern(String str) {
        if (str.equals(this.datePattern) || Intrinsics.areEqual(this.datePattern, str)) {
            return;
        }
        this.datePattern = str;
        this.dateFormat = null;
        View view = this.mView;
        if (view == null || !view.isAttachedToWindow()) {
            return;
        }
        post(new VariableDateViewController$datePattern$1(0, this, VariableDateViewController.class, "updateClock", "updateClock()V", 0));
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
        BroadcastDispatcher.registerReceiver$default(this.broadcastDispatcher, this.intentReceiver, intentFilter, new HandlerExecutor(this.timeTickHandler), UserHandle.SYSTEM, 0, 48);
        RepeatWhenAttachedKt.repeatWhenAttached(this.mView, EmptyCoroutineContext.INSTANCE, new VariableDateViewController$onViewAttached$1(this, null));
        post(new VariableDateViewController$onViewAttached$2(0, this, VariableDateViewController.class, "updateClock", "updateClock()V", 0));
        ((VariableDateView) this.mView).onMeasureListener = this.onMeasureListener;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        this.dateFormat = null;
        ((VariableDateView) this.mView).onMeasureListener = null;
        this.broadcastDispatcher.unregisterReceiver(this.intentReceiver);
    }

    public final void post(final Function0 function0) {
        Handler handler = ((VariableDateView) this.mView).getHandler();
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.android.systemui.statusbar.policy.VariableDateViewControllerKt$sam$java_lang_Runnable$0
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    Function0.this.invoke();
                }
            });
        }
    }
}
