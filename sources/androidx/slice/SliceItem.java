package androidx.slice;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.format.DateUtils;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.compose.animation.AnimatedVisibilityScope$animateEnterExit$$inlined$debugInspectorInfo$1$$ExternalSyntheticOutline0;
import androidx.compose.ui.text.input.EditProcessor$generateBatchErrorMessage$1$1$$ExternalSyntheticOutline0;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Pair;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.util.Calendar;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliceItem extends CustomVersionedParcelable {
    public String mFormat;
    public String[] mHints;
    public SliceItemHolder mHolder;
    public Object mObj;
    public CharSequence mSanitizedText;
    public String mSubType;

    public SliceItem(Object obj, String str, String str2, String[] strArr) {
        this.mHints = strArr;
        this.mFormat = str;
        this.mSubType = str2;
        this.mObj = obj;
    }

    public static void fixSpannableText(Spannable spannable) {
        for (Object obj : spannable.getSpans(0, spannable.length(), Object.class)) {
            Object obj2 = ((obj instanceof AlignmentSpan) || (obj instanceof ForegroundColorSpan) || (obj instanceof RelativeSizeSpan) || (obj instanceof StyleSpan)) ? obj : null;
            if (obj2 != obj) {
                if (obj2 != null) {
                    spannable.setSpan(obj2, spannable.getSpanStart(obj), spannable.getSpanEnd(obj), spannable.getSpanFlags(obj));
                }
                spannable.removeSpan(obj);
            }
        }
    }

    public final void fireActionInternal(Context context, Intent intent) {
        ObjectsCompat.requireNonNull(this.mObj, "Object must be non-null for FORMAT_ACTION");
        Object obj = ((Pair) this.mObj).first;
        if (!(obj instanceof PendingIntent)) {
            throw AnimatedVisibilityScope$animateEnterExit$$inlined$debugInspectorInfo$1$$ExternalSyntheticOutline0.m(obj);
        }
        ((PendingIntent) obj).send(context, 0, intent, null, null);
    }

    public final PendingIntent getAction() {
        ObjectsCompat.requireNonNull(this.mObj, "Object must be non-null");
        Object obj = ((Pair) this.mObj).first;
        if (obj instanceof PendingIntent) {
            return (PendingIntent) obj;
        }
        return null;
    }

    public final int getInt() {
        ObjectsCompat.requireNonNull(this.mObj, "Object must be non-null for FORMAT_INT");
        return ((Integer) this.mObj).intValue();
    }

    public final long getLong() {
        ObjectsCompat.requireNonNull(this.mObj, "Object must be non-null for FORMAT_LONG");
        return ((Long) this.mObj).longValue();
    }

    public final CharSequence getSanitizedText() {
        if (this.mSanitizedText == null) {
            CharSequence charSequence = (CharSequence) this.mObj;
            if (charSequence instanceof Spannable) {
                fixSpannableText((Spannable) charSequence);
            } else if (charSequence instanceof Spanned) {
                Spanned spanned = (Spanned) charSequence;
                int i = 0;
                Object[] spans = spanned.getSpans(0, spanned.length(), Object.class);
                int length = spans.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    Object obj = spans[i];
                    if (!(obj instanceof AlignmentSpan) && !(obj instanceof ForegroundColorSpan) && !(obj instanceof RelativeSizeSpan) && !(obj instanceof StyleSpan)) {
                        SpannableString spannableString = new SpannableString(charSequence);
                        fixSpannableText(spannableString);
                        charSequence = spannableString;
                        break;
                    }
                    i++;
                }
            }
            this.mSanitizedText = charSequence;
        }
        return this.mSanitizedText;
    }

    public final Slice getSlice() {
        ObjectsCompat.requireNonNull(this.mObj, "Object must be non-null for FORMAT_SLICE");
        return "action".equals(this.mFormat) ? (Slice) ((Pair) this.mObj).second : (Slice) this.mObj;
    }

    public final boolean hasAnyHints(String... strArr) {
        for (String str : strArr) {
            if (ArrayUtils.contains(this.mHints, str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final String toString(String str) {
        char c;
        char c2;
        String str2;
        StringBuilder m = EditProcessor$generateBatchErrorMessage$1$1$$ExternalSyntheticOutline0.m(str);
        m.append(this.mFormat);
        if (this.mSubType != null) {
            m.append('<');
            m.append(this.mSubType);
            m.append('>');
        }
        m.append(' ');
        String[] strArr = this.mHints;
        if (strArr.length > 0) {
            Slice.appendHints(m, strArr);
            m.append(' ');
        }
        String m2 = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "  ");
        String str3 = this.mFormat;
        str3.getClass();
        switch (str3.hashCode()) {
            case -1422950858:
                if (str3.equals("action")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 104431:
                if (str3.equals("int")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3327612:
                if (str3.equals("long")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 3556653:
                if (str3.equals("text")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 100313435:
                if (str3.equals("image")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 109526418:
                if (str3.equals("slice")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                Slice slice = getSlice();
                ObjectsCompat.requireNonNull(this.mObj, "Object must be non-null for FORMAT_ACTION");
                ObjectsCompat.requireNonNull(slice, "Slice must be non-null for FORMAT_SLICE");
                Object obj = ((Pair) this.mObj).first;
                m.append('[');
                m.append(obj);
                m.append("] ");
                m.append("{\n");
                m.append(getSlice().toString(m2));
                m.append('\n');
                m.append(str);
                m.append('}');
                break;
            case 1:
                if (!"color".equals(this.mSubType)) {
                    if (!"layout_direction".equals(this.mSubType)) {
                        m.append(getInt());
                        break;
                    } else {
                        int i = getInt();
                        m.append(i != 0 ? i != 1 ? i != 2 ? i != 3 ? Integer.toString(i) : "LOCALE" : "INHERIT" : "RTL" : "LTR");
                        break;
                    }
                } else {
                    int i2 = getInt();
                    m.append(String.format("a=0x%02x r=0x%02x g=0x%02x b=0x%02x", Integer.valueOf(Color.alpha(i2)), Integer.valueOf(Color.red(i2)), Integer.valueOf(Color.green(i2)), Integer.valueOf(Color.blue(i2))));
                    break;
                }
            case 2:
                if (!"millis".equals(this.mSubType)) {
                    m.append(getLong());
                    m.append('L');
                    break;
                } else if (getLong() != -1) {
                    m.append(DateUtils.getRelativeTimeSpanString(getLong(), Calendar.getInstance().getTimeInMillis(), 1000L, 262144));
                    break;
                } else {
                    m.append("INFINITY");
                    break;
                }
            case 3:
                m.append('\"');
                m.append((CharSequence) this.mObj);
                m.append('\"');
                break;
            case 4:
                m.append((IconCompat) this.mObj);
                break;
            case 5:
                Slice slice2 = getSlice();
                ObjectsCompat.requireNonNull(slice2, "Slice must be non-null for FORMAT_SLICE");
                m.append("{\n");
                m.append(slice2.toString(m2));
                m.append('\n');
                m.append(str);
                m.append('}');
                break;
            default:
                String str4 = this.mFormat;
                str4.getClass();
                switch (str4.hashCode()) {
                    case -1422950858:
                        if (str4.equals("action")) {
                            c2 = 0;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 104431:
                        if (str4.equals("int")) {
                            c2 = 1;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 3327612:
                        if (str4.equals("long")) {
                            c2 = 2;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 3556653:
                        if (str4.equals("text")) {
                            c2 = 3;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 100313435:
                        if (str4.equals("image")) {
                            c2 = 4;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 100358090:
                        if (str4.equals("input")) {
                            c2 = 5;
                            break;
                        }
                        c2 = 65535;
                        break;
                    case 109526418:
                        if (str4.equals("slice")) {
                            c2 = 6;
                            break;
                        }
                        c2 = 65535;
                        break;
                    default:
                        c2 = 65535;
                        break;
                }
                switch (c2) {
                    case 0:
                        str2 = "Action";
                        break;
                    case 1:
                        str2 = "Int";
                        break;
                    case 2:
                        str2 = "Long";
                        break;
                    case 3:
                        str2 = "Text";
                        break;
                    case 4:
                        str2 = "Image";
                        break;
                    case 5:
                        str2 = "RemoteInput";
                        break;
                    case 6:
                        str2 = "Slice";
                        break;
                    default:
                        str2 = "Unrecognized format: ".concat(str4);
                        break;
                }
                m.append(str2);
                break;
        }
        m.append("\n");
        return m.toString();
    }

    public SliceItem(Object obj, String str, String str2, List list) {
        this(obj, str, str2, (String[]) list.toArray(new String[list.size()]));
    }

    public SliceItem() {
        this.mHints = Slice.NO_HINTS;
        this.mFormat = "text";
        this.mSubType = null;
    }

    public SliceItem(PendingIntent pendingIntent, Slice slice, String str, String[] strArr) {
        this(new Pair(pendingIntent, slice), "action", str, strArr);
    }

    public final String toString() {
        return toString("");
    }
}
