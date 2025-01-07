package com.android.systemui.statusbar.phone;

import android.os.Handler;
import com.android.systemui.doze.DozeLog;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.doze.DozeUi;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.phone.DozeServiceHost;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DozeScrimController implements StatusBarStateController.StateListener {
    public final DozeLog mDozeLog;
    public final DozeParameters mDozeParameters;
    public boolean mDozing;
    public DozeServiceHost.AnonymousClass1 mPulseCallback;
    public int mPulseReason;
    public final Handler mHandler = new Handler();
    public final AnonymousClass1 mScrimCallback = new ScrimController.Callback() { // from class: com.android.systemui.statusbar.phone.DozeScrimController.1
        @Override // com.android.systemui.statusbar.phone.ScrimController.Callback
        public final void onCancelled() {
            DozeScrimController.this.pulseFinished();
        }

        @Override // com.android.systemui.statusbar.phone.ScrimController.Callback
        public final void onDisplayBlanked() {
            DozeScrimController dozeScrimController = DozeScrimController.this;
            boolean z = dozeScrimController.mDozing;
            DozeLog dozeLog = dozeScrimController.mDozeLog;
            if (!z) {
                dozeLog.tracePulseDropped("onDisplayBlanked - not dozing");
                return;
            }
            if (dozeScrimController.mPulseCallback != null) {
                dozeLog.tracePulseStart(dozeScrimController.mPulseReason);
                DozeServiceHost.AnonymousClass1 anonymousClass1 = dozeScrimController.mPulseCallback;
                DozeUi.AnonymousClass2 anonymousClass2 = anonymousClass1.val$callback;
                anonymousClass2.getClass();
                try {
                    DozeUi.this.mMachine.requestState(anonymousClass2.val$reason == 8 ? DozeMachine.State.DOZE_PULSING_BRIGHT : DozeMachine.State.DOZE_PULSING);
                } catch (IllegalStateException unused) {
                }
                DozeServiceHost.this.mCentralSurfaces.updateNotificationPanelTouchState();
                anonymousClass1.setPulsing(true);
            }
        }

        @Override // com.android.systemui.statusbar.phone.ScrimController.Callback
        public final void onFinished() {
            int i;
            DozeScrimController dozeScrimController = DozeScrimController.this;
            dozeScrimController.mDozeLog.tracePulseEvent(dozeScrimController.mPulseReason, "scrimCallback-onFinished", dozeScrimController.mDozing);
            if (!dozeScrimController.mDozing || (i = dozeScrimController.mPulseReason) == 1 || i == 6) {
                return;
            }
            Handler handler = dozeScrimController.mHandler;
            DozeParameters dozeParameters = dozeScrimController.mDozeParameters;
            handler.postDelayed(dozeScrimController.mPulseOut, dozeParameters.getInt(R.integer.doze_pulse_duration_visible, "doze.pulse.duration.visible"));
            handler.postDelayed(dozeScrimController.mPulseOutExtended, dozeParameters.getInt(R.integer.doze_pulse_duration_visible, "doze.pulse.duration.visible") * 2);
        }
    };
    public final AnonymousClass2 mPulseOutExtended = new AnonymousClass2(this, 0);
    public final AnonymousClass2 mPulseOut = new AnonymousClass2(this, 1);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.DozeScrimController$2, reason: invalid class name */
    public final class AnonymousClass2 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ DozeScrimController this$0;

        public /* synthetic */ AnonymousClass2(DozeScrimController dozeScrimController, int i) {
            this.$r8$classId = i;
            this.this$0 = dozeScrimController;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    DozeScrimController dozeScrimController = this.this$0;
                    dozeScrimController.mHandler.removeCallbacks(dozeScrimController.mPulseOut);
                    this.this$0.mPulseOut.run();
                    break;
                default:
                    DozeScrimController dozeScrimController2 = this.this$0;
                    dozeScrimController2.mHandler.removeCallbacks(dozeScrimController2.mPulseOut);
                    DozeScrimController dozeScrimController3 = this.this$0;
                    dozeScrimController3.mHandler.removeCallbacks(dozeScrimController3.mPulseOutExtended);
                    DozeScrimController dozeScrimController4 = this.this$0;
                    dozeScrimController4.mDozeLog.tracePulseEvent(dozeScrimController4.mPulseReason, "out", dozeScrimController4.mDozing);
                    DozeScrimController dozeScrimController5 = this.this$0;
                    if (dozeScrimController5.mDozing) {
                        dozeScrimController5.pulseFinished();
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.statusbar.phone.DozeScrimController$1] */
    public DozeScrimController(DozeParameters dozeParameters, DozeLog dozeLog, StatusBarStateController statusBarStateController) {
        this.mDozeParameters = dozeParameters;
        statusBarStateController.addCallback(this);
        this.mDozeLog = dozeLog;
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onDozingChanged(boolean z) {
        if (this.mDozing != z) {
            this.mDozeLog.traceDozingChanged(z);
        }
        setDozing(z);
    }

    public final void pulseFinished() {
        if (this.mPulseCallback != null) {
            this.mDozeLog.tracePulseFinish();
            this.mPulseCallback.onPulseFinished();
            this.mPulseCallback = null;
        }
    }

    public void setDozing(boolean z) {
        if (this.mDozing == z) {
            return;
        }
        this.mDozing = z;
        if (z || this.mPulseCallback == null) {
            return;
        }
        this.mDozeLog.tracePulseEvent(this.mPulseReason, "cancel", z);
        Handler handler = this.mHandler;
        handler.removeCallbacks(this.mPulseOut);
        handler.removeCallbacks(this.mPulseOutExtended);
        pulseFinished();
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onStateChanged(int i) {
    }
}
