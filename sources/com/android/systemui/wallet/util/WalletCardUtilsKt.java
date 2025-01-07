package com.android.systemui.wallet.util;

import android.service.quickaccesswallet.WalletCard;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WalletCardUtilsKt {
    public static final List getPaymentCards(List list) {
        if (list.isEmpty()) {
            return list;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((WalletCard) it.next()).getCardType() != 0) {
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    if (((WalletCard) obj).getCardType() == 1) {
                        arrayList.add(obj);
                    }
                }
                return arrayList;
            }
        }
        return list;
    }
}
