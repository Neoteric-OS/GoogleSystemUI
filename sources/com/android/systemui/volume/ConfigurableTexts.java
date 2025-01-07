package com.android.systemui.volume;

import android.content.Context;
import android.content.res.Resources;
import android.util.ArrayMap;
import android.widget.TextView;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConfigurableTexts {
    public final Context mContext;
    public final ArrayMap mTexts = new ArrayMap();
    public final ArrayMap mTextLabels = new ArrayMap();
    public final AnonymousClass2 mUpdateAll = new Runnable() { // from class: com.android.systemui.volume.ConfigurableTexts.2
        @Override // java.lang.Runnable
        public final void run() {
            for (int i = 0; i < ConfigurableTexts.this.mTexts.size(); i++) {
                ((TextView) ConfigurableTexts.this.mTexts.keyAt(i)).setTextSize(2, ((Integer) ConfigurableTexts.this.mTexts.valueAt(i)).intValue());
            }
            for (int i2 = 0; i2 < ConfigurableTexts.this.mTextLabels.size(); i2++) {
                ConfigurableTexts configurableTexts = ConfigurableTexts.this;
                TextView textView = (TextView) configurableTexts.mTextLabels.keyAt(i2);
                int intValue = ((Integer) ConfigurableTexts.this.mTextLabels.valueAt(i2)).intValue();
                if (intValue >= 0) {
                    try {
                        String string = configurableTexts.mContext.getString(intValue);
                        CharSequence text = textView.getText();
                        String str = null;
                        if (text == null || text.length() == 0) {
                            text = null;
                        }
                        if (string != null && string.length() != 0) {
                            str = string;
                        }
                        if (!Objects.equals(text, str)) {
                            textView.setText(string);
                        }
                    } catch (Resources.NotFoundException unused) {
                    }
                }
            }
        }
    };

    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.volume.ConfigurableTexts$2] */
    public ConfigurableTexts(Context context) {
        this.mContext = context;
    }
}
