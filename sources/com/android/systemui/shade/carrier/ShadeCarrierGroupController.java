package com.android.systemui.shade.carrier;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.android.keyguard.CarrierTextManager;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.android.settingslib.AccessibilityContentDescriptions;
import com.android.settingslib.mobile.TelephonyIcons;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.shade.ShadeHeaderController;
import com.android.systemui.shade.ShadeHeaderController$updateListeners$1;
import com.android.systemui.shade.carrier.ShadeCarrierGroupController;
import com.android.systemui.statusbar.connectivity.IconState;
import com.android.systemui.statusbar.connectivity.MobileDataIndicators;
import com.android.systemui.statusbar.connectivity.NetworkController;
import com.android.systemui.statusbar.connectivity.SignalCallback;
import com.android.systemui.statusbar.connectivity.ui.MobileContextProvider;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.pipeline.StatusBarPipelineFlags;
import com.android.systemui.statusbar.pipeline.mobile.ui.MobileUiAdapter;
import com.android.systemui.statusbar.pipeline.mobile.ui.MobileViewLogger;
import com.android.systemui.statusbar.pipeline.mobile.ui.binder.MobileIconsBinder;
import com.android.systemui.statusbar.pipeline.mobile.ui.view.ModernShadeCarrierGroupMobileView;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconsViewModel;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.ShadeCarrierGroupMobileIconViewModel;
import com.android.systemui.util.CarrierConfigTracker;
import com.android.wm.shell.R;
import java.util.List;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeCarrierGroupController {
    public final ActivityStarter mActivityStarter;
    public final Handler mBgHandler;
    public final Callback mCallback;
    public final View[] mCarrierDividers;
    public final ShadeCarrier[] mCarrierGroups;
    public final CarrierTextManager mCarrierTextManager;
    public final Context mContext;
    public boolean mIsSingleCarrier;
    public boolean mListening;
    public final ShadeCarrierGroupControllerLogger mLogger;
    public final H mMainHandler;
    public final MobileContextProvider mMobileContextProvider;
    public final MobileIconsViewModel mMobileIconsViewModel;
    public final NetworkController mNetworkController;
    public final TextView mNoSimTextView;
    public ShadeHeaderController$updateListeners$1 mOnSingleCarrierChangedListener;
    public final SubscriptionManagerSlotIndexResolver mSlotIndexResolver;
    public final StatusBarPipelineFlags mStatusBarPipelineFlags;
    public final CellSignalState[] mInfos = new CellSignalState[3];
    public final int[] mLastSignalLevel = new int[3];
    public final String[] mLastSignalLevelDescription = new String[3];
    public final AnonymousClass1 mSignalCallback = new SignalCallback() { // from class: com.android.systemui.shade.carrier.ShadeCarrierGroupController.1
        @Override // com.android.systemui.statusbar.connectivity.SignalCallback
        public final void setMobileDataIndicators(MobileDataIndicators mobileDataIndicators) {
            ShadeCarrierGroupController shadeCarrierGroupController = ShadeCarrierGroupController.this;
            int i = mobileDataIndicators.subId;
            int slotIndex = shadeCarrierGroupController.getSlotIndex(i);
            if (slotIndex >= 3) {
                RecordingInputConnection$$ExternalSyntheticOutline0.m("setMobileDataIndicators - slot: ", "ShadeCarrierGroup", slotIndex);
                return;
            }
            if (slotIndex == -1) {
                ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Invalid SIM slot index for subscription: ", "ShadeCarrierGroup", i);
                return;
            }
            CellSignalState[] cellSignalStateArr = shadeCarrierGroupController.mInfos;
            IconState iconState = mobileDataIndicators.statusIcon;
            cellSignalStateArr[slotIndex] = new CellSignalState(iconState.visible, iconState.icon, iconState.contentDescription, ((String) mobileDataIndicators.typeContentDescription).toString(), mobileDataIndicators.roaming);
            shadeCarrierGroupController.mMainHandler.obtainMessage(1).sendToTarget();
        }

        @Override // com.android.systemui.statusbar.connectivity.SignalCallback
        public final void setNoSims(boolean z, boolean z2) {
            ShadeCarrierGroupController shadeCarrierGroupController = ShadeCarrierGroupController.this;
            if (z) {
                for (int i = 0; i < 3; i++) {
                    CellSignalState[] cellSignalStateArr = shadeCarrierGroupController.mInfos;
                    cellSignalStateArr[i] = cellSignalStateArr[i].changeVisibility(false);
                }
            }
            shadeCarrierGroupController.mMainHandler.obtainMessage(1).sendToTarget();
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public final ActivityStarter mActivityStarter;
        public final CarrierConfigTracker mCarrierConfigTracker;
        public final CarrierTextManager.Builder mCarrierTextControllerBuilder;
        public final Context mContext;
        public final Handler mHandler;
        public final ShadeCarrierGroupControllerLogger mLogger;
        public final Looper mLooper;
        public final MobileContextProvider mMobileContextProvider;
        public final MobileUiAdapter mMobileUiAdapter;
        public final NetworkController mNetworkController;
        public final SubscriptionManagerSlotIndexResolver mSlotIndexResolver;
        public final StatusBarPipelineFlags mStatusBarPipelineFlags;

        public Builder(ActivityStarter activityStarter, Handler handler, Looper looper, ShadeCarrierGroupControllerLogger shadeCarrierGroupControllerLogger, NetworkController networkController, CarrierTextManager.Builder builder, Context context, CarrierConfigTracker carrierConfigTracker, SubscriptionManagerSlotIndexResolver subscriptionManagerSlotIndexResolver, MobileUiAdapter mobileUiAdapter, MobileContextProvider mobileContextProvider, StatusBarPipelineFlags statusBarPipelineFlags) {
            this.mActivityStarter = activityStarter;
            this.mHandler = handler;
            this.mLooper = looper;
            this.mLogger = shadeCarrierGroupControllerLogger;
            this.mNetworkController = networkController;
            this.mCarrierTextControllerBuilder = builder;
            this.mContext = context;
            this.mSlotIndexResolver = subscriptionManagerSlotIndexResolver;
            this.mMobileUiAdapter = mobileUiAdapter;
            this.mMobileContextProvider = mobileContextProvider;
            this.mStatusBarPipelineFlags = statusBarPipelineFlags;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Callback implements CarrierTextManager.CarrierTextCallback {
        public H mHandler;

        @Override // com.android.keyguard.CarrierTextManager.CarrierTextCallback
        public final void updateCarrierInfo(CarrierTextManager.CarrierTextCallbackInfo carrierTextCallbackInfo) {
            this.mHandler.obtainMessage(0, carrierTextCallbackInfo).sendToTarget();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class H extends Handler {
        public ShadeCarrierGroupController$$ExternalSyntheticLambda4 mUpdateCarrierInfo;
        public ShadeCarrierGroupController$$ExternalSyntheticLambda0 mUpdateState;

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                this.mUpdateCarrierInfo.accept((CarrierTextManager.CarrierTextCallbackInfo) message.obj);
            } else if (i != 1) {
                super.handleMessage(message);
            } else {
                this.mUpdateState.run();
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class IconData {
        public final int slotIndex;
        public final int subId;

        public IconData(int i, int i2) {
            this.subId = i;
            this.slotIndex = i2;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SubscriptionManagerSlotIndexResolver {
    }

    /* JADX WARN: Type inference failed for: r11v2, types: [com.android.systemui.shade.carrier.ShadeCarrierGroupController$1] */
    public ShadeCarrierGroupController(ShadeCarrierGroup shadeCarrierGroup, ActivityStarter activityStarter, Handler handler, Looper looper, ShadeCarrierGroupControllerLogger shadeCarrierGroupControllerLogger, NetworkController networkController, CarrierTextManager.Builder builder, Context context, SubscriptionManagerSlotIndexResolver subscriptionManagerSlotIndexResolver, MobileUiAdapter mobileUiAdapter, MobileContextProvider mobileContextProvider, StatusBarPipelineFlags statusBarPipelineFlags) {
        View[] viewArr = new View[2];
        this.mCarrierDividers = viewArr;
        this.mCarrierGroups = new ShadeCarrier[]{(ShadeCarrier) shadeCarrierGroup.findViewById(R.id.carrier1), (ShadeCarrier) shadeCarrierGroup.findViewById(R.id.carrier2), (ShadeCarrier) shadeCarrierGroup.findViewById(R.id.carrier3)};
        this.mContext = context;
        this.mActivityStarter = activityStarter;
        this.mBgHandler = handler;
        this.mLogger = shadeCarrierGroupControllerLogger;
        this.mNetworkController = networkController;
        this.mStatusBarPipelineFlags = statusBarPipelineFlags;
        builder.mShowAirplaneMode = false;
        builder.mShowMissingSim = false;
        builder.mDebugLocation = "Shade";
        this.mCarrierTextManager = builder.build();
        this.mSlotIndexResolver = subscriptionManagerSlotIndexResolver;
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.android.systemui.shade.carrier.ShadeCarrierGroupController$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShadeCarrierGroupController shadeCarrierGroupController = ShadeCarrierGroupController.this;
                shadeCarrierGroupController.getClass();
                if (view.isVisibleToUser()) {
                    shadeCarrierGroupController.mActivityStarter.postStartActivityDismissingKeyguard(new Intent("android.settings.WIRELESS_SETTINGS"), 0);
                }
            }
        };
        TextView textView = (TextView) shadeCarrierGroup.findViewById(R.id.no_carrier_text);
        this.mNoSimTextView = textView;
        textView.setOnClickListener(onClickListener);
        ShadeCarrierGroupController$$ExternalSyntheticLambda4 shadeCarrierGroupController$$ExternalSyntheticLambda4 = new ShadeCarrierGroupController$$ExternalSyntheticLambda4(this);
        ShadeCarrierGroupController$$ExternalSyntheticLambda0 shadeCarrierGroupController$$ExternalSyntheticLambda0 = new ShadeCarrierGroupController$$ExternalSyntheticLambda0(this, 1);
        H h = new H(looper);
        h.mUpdateCarrierInfo = shadeCarrierGroupController$$ExternalSyntheticLambda4;
        h.mUpdateState = shadeCarrierGroupController$$ExternalSyntheticLambda0;
        this.mMainHandler = h;
        Callback callback = new Callback();
        callback.mHandler = h;
        this.mCallback = callback;
        this.mMobileContextProvider = mobileContextProvider;
        MobileIconsViewModel mobileIconsViewModel = mobileUiAdapter.mobileIconsViewModel;
        this.mMobileIconsViewModel = mobileIconsViewModel;
        statusBarPipelineFlags.getClass();
        if (((FeatureFlagsClassicRelease) statusBarPipelineFlags.featureFlags).isEnabled(Flags.NEW_SHADE_CARRIER_GROUP_MOBILE_ICONS)) {
            mobileUiAdapter.shadeCarrierGroupController = this;
            MobileIconsBinder.bind(shadeCarrierGroup, mobileIconsViewModel);
        }
        viewArr[0] = shadeCarrierGroup.findViewById(R.id.shade_carrier_divider1);
        viewArr[1] = shadeCarrierGroup.findViewById(R.id.shade_carrier_divider2);
        for (int i = 0; i < 3; i++) {
            this.mInfos[i] = new CellSignalState(false, R.drawable.ic_shade_no_calling_sms, context.getText(R.string.accessibility_no_calling).toString(), "", false);
            this.mLastSignalLevel[i] = TelephonyIcons.MOBILE_CALL_STRENGTH_ICONS[0];
            this.mLastSignalLevelDescription[i] = context.getText(AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH[0]).toString();
            this.mCarrierGroups[i].setOnClickListener(onClickListener);
        }
        this.mIsSingleCarrier = computeIsSingleCarrier();
        shadeCarrierGroup.setImportantForAccessibility(1);
        shadeCarrierGroup.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.systemui.shade.carrier.ShadeCarrierGroupController.2
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view) {
                ShadeCarrierGroupController shadeCarrierGroupController = ShadeCarrierGroupController.this;
                if (shadeCarrierGroupController.mListening) {
                    shadeCarrierGroupController.mListening = false;
                    shadeCarrierGroupController.mBgHandler.post(new ShadeCarrierGroupController$$ExternalSyntheticLambda0(shadeCarrierGroupController, 0));
                }
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view) {
            }
        });
    }

    public final boolean computeIsSingleCarrier() {
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            if (this.mInfos[i2].visible) {
                i++;
            }
        }
        return i == 1;
    }

    public int getShadeCarrierVisibility(int i) {
        return this.mCarrierGroups[i].getVisibility();
    }

    public int getSlotIndex(int i) {
        this.mSlotIndexResolver.getClass();
        return SubscriptionManager.getSlotIndex(i);
    }

    public final void handleUpdateState() {
        H h = this.mMainHandler;
        if (!h.getLooper().isCurrentThread()) {
            h.obtainMessage(1).sendToTarget();
            return;
        }
        boolean computeIsSingleCarrier = computeIsSingleCarrier();
        CellSignalState[] cellSignalStateArr = this.mInfos;
        int i = 0;
        if (computeIsSingleCarrier) {
            for (int i2 = 0; i2 < 3; i2++) {
                CellSignalState cellSignalState = cellSignalStateArr[i2];
                if (cellSignalState.visible && cellSignalState.mobileSignalIconId == R.drawable.ic_shade_sim_card) {
                    cellSignalStateArr[i2] = new CellSignalState(true, R.drawable.ic_blank, "", "", false);
                }
            }
        }
        StatusBarPipelineFlags statusBarPipelineFlags = this.mStatusBarPipelineFlags;
        statusBarPipelineFlags.getClass();
        if (!((FeatureFlagsClassicRelease) statusBarPipelineFlags.featureFlags).isEnabled(Flags.NEW_SHADE_CARRIER_GROUP_MOBILE_ICONS)) {
            for (int i3 = 0; i3 < 3; i3++) {
                this.mCarrierGroups[i3].updateState(cellSignalStateArr[i3], computeIsSingleCarrier);
            }
        }
        this.mCarrierDividers[0].setVisibility((cellSignalStateArr[0].visible && cellSignalStateArr[1].visible) ? 0 : 8);
        View view = this.mCarrierDividers[1];
        if ((!cellSignalStateArr[1].visible || !cellSignalStateArr[2].visible) && (!cellSignalStateArr[0].visible || !cellSignalStateArr[2].visible)) {
            i = 8;
        }
        view.setVisibility(i);
        if (this.mIsSingleCarrier != computeIsSingleCarrier) {
            this.mIsSingleCarrier = computeIsSingleCarrier;
            ShadeHeaderController$updateListeners$1 shadeHeaderController$updateListeners$1 = this.mOnSingleCarrierChangedListener;
            if (shadeHeaderController$updateListeners$1 != null) {
                ShadeHeaderController shadeHeaderController = shadeHeaderController$updateListeners$1.this$0;
                shadeHeaderController.singleCarrier = computeIsSingleCarrier;
                shadeHeaderController.updateIgnoredSlots();
            }
        }
    }

    public List processSubIdList(List list) {
        return list.stream().limit(3L).map(new Function() { // from class: com.android.systemui.shade.carrier.ShadeCarrierGroupController$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Integer num = (Integer) obj;
                return new ShadeCarrierGroupController.IconData(num.intValue(), ShadeCarrierGroupController.this.getSlotIndex(num.intValue()));
            }
        }).filter(new ShadeCarrierGroupController$$ExternalSyntheticLambda2()).toList();
    }

    public final void showSingleText(CharSequence charSequence) {
        for (int i = 0; i < 3; i++) {
            CellSignalState[] cellSignalStateArr = this.mInfos;
            cellSignalStateArr[i] = cellSignalStateArr[i].changeVisibility(false);
            ShadeCarrier[] shadeCarrierArr = this.mCarrierGroups;
            shadeCarrierArr[i].mCarrierText.setText("");
            shadeCarrierArr[i].setVisibility(8);
        }
        this.mNoSimTextView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        this.mNoSimTextView.setVisibility(0);
    }

    public final void updateModernMobileIcons(List list) {
        StatusBarPipelineFlags statusBarPipelineFlags = this.mStatusBarPipelineFlags;
        statusBarPipelineFlags.getClass();
        if (!((FeatureFlagsClassicRelease) statusBarPipelineFlags.featureFlags).isEnabled(Flags.NEW_SHADE_CARRIER_GROUP_MOBILE_ICONS)) {
            Log.d("ShadeCarrierGroup", "ignoring new pipeline callback because new mobile icon is disabled");
            return;
        }
        ShadeCarrier[] shadeCarrierArr = this.mCarrierGroups;
        for (ShadeCarrier shadeCarrier : shadeCarrierArr) {
            ModernShadeCarrierGroupMobileView modernShadeCarrierGroupMobileView = shadeCarrier.mModernMobileView;
            if (modernShadeCarrierGroupMobileView != null) {
                shadeCarrier.removeView(modernShadeCarrierGroupMobileView);
                shadeCarrier.mModernMobileView = null;
            }
        }
        for (IconData iconData : processSubIdList(list)) {
            ShadeCarrier shadeCarrier2 = shadeCarrierArr[iconData.slotIndex];
            Context context = this.mContext;
            MobileContextProvider mobileContextProvider = this.mMobileContextProvider;
            int i = iconData.subId;
            Context mobileContextForSub = mobileContextProvider.getMobileContextForSub(i, context);
            MobileIconsViewModel mobileIconsViewModel = this.mMobileIconsViewModel;
            MobileViewLogger mobileViewLogger = mobileIconsViewModel.logger;
            ShadeCarrierGroupMobileIconViewModel shadeCarrierGroupMobileIconViewModel = (ShadeCarrierGroupMobileIconViewModel) mobileIconsViewModel.viewModelForSub(i, StatusBarLocation.SHADE_CARRIER_GROUP);
            int i2 = ModernShadeCarrierGroupMobileView.$r8$clinit;
            ModernShadeCarrierGroupMobileView constructAndBind = ModernShadeCarrierGroupMobileView.Companion.constructAndBind(mobileContextForSub, mobileViewLogger, shadeCarrierGroupMobileIconViewModel);
            shadeCarrier2.mModernMobileView = constructAndBind;
            shadeCarrier2.mMobileGroup.setVisibility(8);
            shadeCarrier2.mSpacer.setVisibility(8);
            shadeCarrier2.mCarrierText.setVisibility(8);
            shadeCarrier2.addView(constructAndBind);
        }
    }
}
