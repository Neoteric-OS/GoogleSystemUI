package com.android.systemui.statusbar;

import android.telephony.ServiceState;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.view.View;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor;
import com.android.systemui.statusbar.pipeline.mobile.util.SubscriptionManagerProxyImpl;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.CarrierConfigTracker;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.kotlin.JavaAdapter;
import java.util.ArrayList;
import java.util.function.Consumer;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OperatorNameViewController extends ViewController {
    public final AirplaneModeInteractor mAirplaneModeInteractor;
    public StandaloneCoroutine mAirplaneModeJob;
    public final CarrierConfigTracker mCarrierConfigTracker;
    public final DarkIconDispatcher mDarkIconDispatcher;
    public final OperatorNameViewController$$ExternalSyntheticLambda1 mDarkReceiver;
    public final JavaAdapter mJavaAdapter;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback;
    public final SubscriptionManagerProxyImpl mSubscriptionManagerProxy;
    public final TelephonyManager mTelephonyManager;
    public final OperatorNameViewController$$ExternalSyntheticLambda2 mTunable;
    public final TunerService mTunerService;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final AirplaneModeInteractor mAirplaneModeInteractor;
        public final CarrierConfigTracker mCarrierConfigTracker;
        public final DarkIconDispatcher mDarkIconDispatcher;
        public final JavaAdapter mJavaAdapter;
        public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
        public final SubscriptionManagerProxyImpl mSubscriptionManagerProxy;
        public final TelephonyManager mTelephonyManager;
        public final TunerService mTunerService;

        public Factory(DarkIconDispatcher darkIconDispatcher, TunerService tunerService, TelephonyManager telephonyManager, KeyguardUpdateMonitor keyguardUpdateMonitor, CarrierConfigTracker carrierConfigTracker, AirplaneModeInteractor airplaneModeInteractor, SubscriptionManagerProxyImpl subscriptionManagerProxyImpl, JavaAdapter javaAdapter) {
            this.mDarkIconDispatcher = darkIconDispatcher;
            this.mTunerService = tunerService;
            this.mTelephonyManager = telephonyManager;
            this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
            this.mCarrierConfigTracker = carrierConfigTracker;
            this.mAirplaneModeInteractor = airplaneModeInteractor;
            this.mSubscriptionManagerProxy = subscriptionManagerProxyImpl;
            this.mJavaAdapter = javaAdapter;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SubInfo {
        public final CharSequence mCarrierName;
        public final ServiceState mServiceState;
        public final int mSimState;
        public final int mSubId;

        public SubInfo(int i, CharSequence charSequence, int i2, ServiceState serviceState) {
            this.mSubId = i;
            this.mCarrierName = charSequence;
            this.mSimState = i2;
            this.mServiceState = serviceState;
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.statusbar.OperatorNameViewController$$ExternalSyntheticLambda1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.statusbar.OperatorNameViewController$$ExternalSyntheticLambda2] */
    public OperatorNameViewController(OperatorNameView operatorNameView, DarkIconDispatcher darkIconDispatcher, TunerService tunerService, TelephonyManager telephonyManager, KeyguardUpdateMonitor keyguardUpdateMonitor, CarrierConfigTracker carrierConfigTracker, AirplaneModeInteractor airplaneModeInteractor, SubscriptionManagerProxyImpl subscriptionManagerProxyImpl, JavaAdapter javaAdapter) {
        super(operatorNameView);
        this.mDarkReceiver = new DarkIconDispatcher.DarkReceiver() { // from class: com.android.systemui.statusbar.OperatorNameViewController$$ExternalSyntheticLambda1
            @Override // com.android.systemui.plugins.DarkIconDispatcher.DarkReceiver
            public final void onDarkChanged(ArrayList arrayList, float f, int i) {
                View view = OperatorNameViewController.this.mView;
                ((OperatorNameView) view).setTextColor(DarkIconDispatcher.getTint(arrayList, view, i));
            }
        };
        this.mTunable = new TunerService.Tunable() { // from class: com.android.systemui.statusbar.OperatorNameViewController$$ExternalSyntheticLambda2
            @Override // com.android.systemui.tuner.TunerService.Tunable
            public final void onTuningChanged(String str, String str2) {
                OperatorNameViewController.this.update$1$1();
            }
        };
        this.mKeyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.statusbar.OperatorNameViewController.1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onRefreshCarrierInfo() {
                OperatorNameViewController operatorNameViewController = OperatorNameViewController.this;
                ((OperatorNameView) operatorNameViewController.mView).updateText(operatorNameViewController.getDefaultSubInfo());
            }
        };
        this.mDarkIconDispatcher = darkIconDispatcher;
        this.mTunerService = tunerService;
        this.mTelephonyManager = telephonyManager;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mCarrierConfigTracker = carrierConfigTracker;
        this.mAirplaneModeInteractor = airplaneModeInteractor;
        this.mSubscriptionManagerProxy = subscriptionManagerProxyImpl;
        this.mJavaAdapter = javaAdapter;
    }

    public final SubInfo getDefaultSubInfo() {
        this.mSubscriptionManagerProxy.getClass();
        int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateMonitor;
        SubscriptionInfo subscriptionInfoForSubId = keyguardUpdateMonitor.getSubscriptionInfoForSubId(defaultDataSubscriptionId);
        return new SubInfo(subscriptionInfoForSubId.getSubscriptionId(), subscriptionInfoForSubId.getCarrierName(), keyguardUpdateMonitor.getSimState(defaultDataSubscriptionId), (ServiceState) keyguardUpdateMonitor.mServiceStates.get(Integer.valueOf(defaultDataSubscriptionId)));
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        this.mDarkIconDispatcher.addDarkReceiver(this.mDarkReceiver);
        this.mAirplaneModeJob = this.mJavaAdapter.alwaysCollectFlow(this.mAirplaneModeInteractor.isAirplaneMode, new Consumer() { // from class: com.android.systemui.statusbar.OperatorNameViewController$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                OperatorNameViewController.this.update$1$1();
            }
        });
        this.mTunerService.addTunable(this.mTunable, "show_operator_name");
        this.mKeyguardUpdateMonitor.registerCallback(this.mKeyguardUpdateMonitorCallback);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        this.mDarkIconDispatcher.removeDarkReceiver(this.mDarkReceiver);
        this.mAirplaneModeJob.cancel(null);
        this.mTunerService.removeTunable(this.mTunable);
        this.mKeyguardUpdateMonitor.removeCallback(this.mKeyguardUpdateMonitorCallback);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x001e, code lost:
    
        if (android.provider.Settings.Secure.getIntForUser(r0.mContentResolver, "show_operator_name", 1, r0.mCurrentUser) != 0) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void update$1$1() {
        /*
            r6 = this;
            com.android.systemui.statusbar.OperatorNameViewController$SubInfo r0 = r6.getDefaultSubInfo()
            com.android.systemui.util.CarrierConfigTracker r1 = r6.mCarrierConfigTracker
            int r0 = r0.mSubId
            boolean r0 = r1.getShowOperatorNameInStatusBarConfig(r0)
            r1 = 0
            if (r0 == 0) goto L21
            com.android.systemui.tuner.TunerService r0 = r6.mTunerService
            com.android.systemui.tuner.TunerServiceImpl r0 = (com.android.systemui.tuner.TunerServiceImpl) r0
            android.content.ContentResolver r2 = r0.mContentResolver
            int r0 = r0.mCurrentUser
            java.lang.String r3 = "show_operator_name"
            r4 = 1
            int r0 = android.provider.Settings.Secure.getIntForUser(r2, r3, r4, r0)
            if (r0 == 0) goto L21
            goto L22
        L21:
            r4 = r1
        L22:
            android.view.View r0 = r6.mView
            com.android.systemui.statusbar.OperatorNameView r0 = (com.android.systemui.statusbar.OperatorNameView) r0
            android.telephony.TelephonyManager r2 = r6.mTelephonyManager
            boolean r2 = r2.isDataCapable()
            com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor r3 = r6.mAirplaneModeInteractor
            kotlinx.coroutines.flow.ReadonlyStateFlow r3 = r3.isAirplaneMode
            java.lang.Object r3 = r3.getValue()
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            com.android.systemui.statusbar.OperatorNameViewController$SubInfo r6 = r6.getDefaultSubInfo()
            r0.getClass()
            r5 = 8
            if (r4 == 0) goto L46
            goto L47
        L46:
            r1 = r5
        L47:
            r0.setVisibility(r1)
            if (r2 == 0) goto L53
            if (r3 == 0) goto L4f
            goto L53
        L4f:
            r0.updateText(r6)
            goto L5a
        L53:
            r6 = 0
            r0.setText(r6)
            r0.setVisibility(r5)
        L5a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.OperatorNameViewController.update$1$1():void");
    }
}
