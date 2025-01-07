package com.android.systemui.common.ui.compose;

import android.content.Context;
import androidx.compose.ui.text.AnnotatedString;
import com.android.systemui.common.shared.model.Text;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextExtKt {
    public static final AnnotatedString toAnnotatedString(Text text, Context context) {
        String string;
        if (text instanceof Text.Loaded) {
            string = ((Text.Loaded) text).text;
        } else {
            if (!(text instanceof Text.Resource)) {
                throw new NoWhenBranchMatchedException();
            }
            string = context.getString(((Text.Resource) text).res);
        }
        if (string != null) {
            return new AnnotatedString(string);
        }
        return null;
    }
}
