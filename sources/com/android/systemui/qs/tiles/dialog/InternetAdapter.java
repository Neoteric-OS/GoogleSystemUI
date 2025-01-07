package com.android.systemui.qs.tiles.dialog;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.app.viewcapture.data.ViewNode;
import com.android.settingslib.Utils;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.settingslib.wifi.WifiUtils;
import com.android.systemui.qs.tiles.dialog.InternetAdapter;
import com.android.systemui.qs.tiles.dialog.InternetDialogController;
import com.android.wifitrackerlib.WifiEntry;
import com.android.wm.shell.R;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InternetAdapter extends RecyclerView.Adapter {
    public final ContextScope mCoroutineScope;
    public View mHolderView;
    public final InternetDialogController mInternetDialogController;
    protected int mMaxEntriesCount = 3;
    public List mWifiEntries;
    protected int mWifiEntriesCount;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InternetViewHolder extends RecyclerView.ViewHolder {
        public final Context mContext;
        public final ContextScope mCoroutineScope;
        public final InternetDialogController mInternetDialogController;
        public StandaloneCoroutine mJob;
        public final ImageView mWifiEndIcon;
        public final ImageView mWifiIcon;
        public final LinearLayout mWifiListLayout;
        public final TextView mWifiSummaryText;
        public final TextView mWifiTitleText;

        public InternetViewHolder(View view, InternetDialogController internetDialogController, ContextScope contextScope) {
            super(view);
            this.mContext = view.getContext();
            this.mInternetDialogController = internetDialogController;
            this.mCoroutineScope = contextScope;
            this.mWifiListLayout = (LinearLayout) view.requireViewById(R.id.wifi_list);
            this.mWifiIcon = (ImageView) view.requireViewById(R.id.wifi_icon);
            this.mWifiTitleText = (TextView) view.requireViewById(R.id.wifi_title);
            this.mWifiSummaryText = (TextView) view.requireViewById(R.id.wifi_summary);
            this.mWifiEndIcon = (ImageView) view.requireViewById(R.id.wifi_end_icon);
        }

        public final void wifiConnect(WifiEntry wifiEntry, View view) {
            if (wifiEntry.shouldEditBeforeConnect()) {
                String key = wifiEntry.getKey();
                Intent intent = new Intent("com.android.settings.WIFI_DIALOG");
                intent.putExtra("key_chosen_wifientry_key", key);
                intent.putExtra("connect_for_caller", true);
                intent.addFlags(268435456);
                intent.addFlags(131072);
                this.mContext.startActivity(intent);
                return;
            }
            boolean canConnect = wifiEntry.canConnect();
            InternetDialogController internetDialogController = this.mInternetDialogController;
            if (!canConnect) {
                if (wifiEntry.isSaved()) {
                    Log.w("InternetAdapter", "The saved Wi-Fi network does not allow to connect. SSID:" + wifiEntry.getSsid());
                    internetDialogController.launchWifiDetailsSetting(view, wifiEntry.getKey());
                    return;
                }
                return;
            }
            internetDialogController.getClass();
            boolean z = InternetDialogController.DEBUG;
            if (wifiEntry.getWifiConfiguration() != null) {
                if (z) {
                    LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(new StringBuilder("connect networkId="), wifiEntry.getWifiConfiguration().networkId, "InternetDialogController");
                }
            } else if (z) {
                Log.d("InternetDialogController", "connect to unsaved network " + wifiEntry.getTitle());
            }
            wifiEntry.connect(new InternetDialogController.WifiEntryConnectCallback(internetDialogController.mActivityStarter, wifiEntry, internetDialogController));
        }
    }

    public InternetAdapter(InternetDialogController internetDialogController, ContextScope contextScope) {
        this.mInternetDialogController = internetDialogController;
        this.mCoroutineScope = contextScope;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.mWifiEntriesCount;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Drawable drawable;
        final int i2 = 1;
        final int i3 = 0;
        final InternetViewHolder internetViewHolder = (InternetViewHolder) viewHolder;
        List list = this.mWifiEntries;
        if (list == null || i >= this.mWifiEntriesCount) {
            return;
        }
        final WifiEntry wifiEntry = (WifiEntry) list.get(i);
        ImageView imageView = internetViewHolder.mWifiIcon;
        Drawable wifiDrawable = internetViewHolder.mInternetDialogController.getWifiDrawable(wifiEntry);
        Drawable drawable2 = null;
        if (wifiDrawable == null) {
            drawable = null;
        } else {
            wifiDrawable.setTint(Utils.getColorAttrDefaultColor(android.R.attr.textColorTertiary, 0, internetViewHolder.mContext));
            AtomicReference atomicReference = new AtomicReference();
            atomicReference.set(wifiDrawable);
            drawable = (Drawable) atomicReference.get();
        }
        imageView.setImageDrawable(drawable);
        String title = wifiEntry.getTitle();
        Spanned fromHtml = Html.fromHtml(wifiEntry.getSummary(false), 0);
        internetViewHolder.mWifiTitleText.setText(title);
        if (TextUtils.isEmpty(fromHtml)) {
            internetViewHolder.mWifiSummaryText.setVisibility(8);
        } else {
            internetViewHolder.mWifiSummaryText.setVisibility(0);
            internetViewHolder.mWifiSummaryText.setText(fromHtml);
        }
        int connectedState = wifiEntry.getConnectedState();
        char c = 3;
        switch (com.android.wifitrackerlib.Utils.getSingleSecurityTypeFromMultipleSecurityTypes(wifiEntry.getSecurityTypes())) {
            case 1:
                c = 1;
                break;
            case 2:
                c = 2;
                break;
            case 3:
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                break;
            case 4:
                c = 5;
                break;
            case 5:
                c = 6;
                break;
            case 6:
                c = 4;
                break;
            case 7:
            case 8:
            case 10:
            default:
                c = 0;
                break;
            case 9:
                c = 7;
                break;
        }
        if (connectedState != 0) {
            drawable2 = internetViewHolder.mContext.getDrawable(R.drawable.ic_settings_24dp);
        } else if (c != 0 && c != 4) {
            drawable2 = internetViewHolder.mContext.getDrawable(R.drawable.ic_friction_lock_closed);
        }
        if (drawable2 == null) {
            internetViewHolder.mWifiEndIcon.setVisibility(8);
        } else {
            internetViewHolder.mWifiEndIcon.setVisibility(0);
            internetViewHolder.mWifiEndIcon.setImageDrawable(drawable2);
        }
        internetViewHolder.mWifiListLayout.setEnabled(wifiEntry.canConnect() || wifiEntry.canDisconnect() || wifiEntry.isSaved());
        if (connectedState != 0) {
            internetViewHolder.mWifiListLayout.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.qs.tiles.dialog.InternetAdapter$InternetViewHolder$$ExternalSyntheticLambda0
                /* JADX WARN: Type inference failed for: r3v0, types: [com.android.systemui.qs.tiles.dialog.InternetAdapter$InternetViewHolder$$ExternalSyntheticLambda2] */
                /* JADX WARN: Type inference failed for: r4v0, types: [com.android.systemui.qs.tiles.dialog.InternetAdapter$InternetViewHolder$$ExternalSyntheticLambda3] */
                @Override // android.view.View.OnClickListener
                public final void onClick(final View view) {
                    switch (i3) {
                        case 0:
                            internetViewHolder.mInternetDialogController.launchWifiDetailsSetting(view, wifiEntry.getKey());
                            break;
                        default:
                            final InternetAdapter.InternetViewHolder internetViewHolder2 = internetViewHolder;
                            final WifiEntry wifiEntry2 = wifiEntry;
                            if (!wifiEntry2.getSecurityTypes().contains(1)) {
                                internetViewHolder2.wifiConnect(wifiEntry2, view);
                                break;
                            } else if (internetViewHolder2.mJob == null) {
                                internetViewHolder2.mJob = WifiUtils.checkWepAllowed(internetViewHolder2.mContext, internetViewHolder2.mCoroutineScope, wifiEntry2.getSsid(), new Function1() { // from class: com.android.systemui.qs.tiles.dialog.InternetAdapter$InternetViewHolder$$ExternalSyntheticLambda2
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        View view2 = view;
                                        InternetAdapter.InternetViewHolder.this.mInternetDialogController.startActivity((Intent) obj, view2);
                                        return null;
                                    }
                                }, new Function0() { // from class: com.android.systemui.qs.tiles.dialog.InternetAdapter$InternetViewHolder$$ExternalSyntheticLambda3
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        InternetAdapter.InternetViewHolder.this.wifiConnect(wifiEntry2, view);
                                        return null;
                                    }
                                });
                                break;
                            }
                            break;
                    }
                }
            });
        } else {
            internetViewHolder.mWifiListLayout.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.qs.tiles.dialog.InternetAdapter$InternetViewHolder$$ExternalSyntheticLambda0
                /* JADX WARN: Type inference failed for: r3v0, types: [com.android.systemui.qs.tiles.dialog.InternetAdapter$InternetViewHolder$$ExternalSyntheticLambda2] */
                /* JADX WARN: Type inference failed for: r4v0, types: [com.android.systemui.qs.tiles.dialog.InternetAdapter$InternetViewHolder$$ExternalSyntheticLambda3] */
                @Override // android.view.View.OnClickListener
                public final void onClick(final View view) {
                    switch (i2) {
                        case 0:
                            internetViewHolder.mInternetDialogController.launchWifiDetailsSetting(view, wifiEntry.getKey());
                            break;
                        default:
                            final InternetAdapter.InternetViewHolder internetViewHolder2 = internetViewHolder;
                            final WifiEntry wifiEntry2 = wifiEntry;
                            if (!wifiEntry2.getSecurityTypes().contains(1)) {
                                internetViewHolder2.wifiConnect(wifiEntry2, view);
                                break;
                            } else if (internetViewHolder2.mJob == null) {
                                internetViewHolder2.mJob = WifiUtils.checkWepAllowed(internetViewHolder2.mContext, internetViewHolder2.mCoroutineScope, wifiEntry2.getSsid(), new Function1() { // from class: com.android.systemui.qs.tiles.dialog.InternetAdapter$InternetViewHolder$$ExternalSyntheticLambda2
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj) {
                                        View view2 = view;
                                        InternetAdapter.InternetViewHolder.this.mInternetDialogController.startActivity((Intent) obj, view2);
                                        return null;
                                    }
                                }, new Function0() { // from class: com.android.systemui.qs.tiles.dialog.InternetAdapter$InternetViewHolder$$ExternalSyntheticLambda3
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        InternetAdapter.InternetViewHolder.this.wifiConnect(wifiEntry2, view);
                                        return null;
                                    }
                                });
                                break;
                            }
                            break;
                    }
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(int i, ViewGroup viewGroup) {
        this.mHolderView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.internet_list_item, viewGroup, false);
        return new InternetViewHolder(this.mHolderView, this.mInternetDialogController, this.mCoroutineScope);
    }
}
