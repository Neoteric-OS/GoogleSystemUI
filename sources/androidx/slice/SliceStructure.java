package androidx.slice;

import android.net.Uri;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliceStructure {
    public final String mStructure;
    public final Uri mUri;

    public SliceStructure(Slice slice) {
        StringBuilder sb = new StringBuilder();
        getStructure(slice, sb);
        this.mStructure = sb.toString();
        this.mUri = Uri.parse(slice.mUri);
    }

    public static void getStructure(Slice slice, StringBuilder sb) {
        sb.append("s{");
        Iterator it = Arrays.asList(slice.mItems).iterator();
        while (it.hasNext()) {
            getStructure((SliceItem) it.next(), sb);
        }
        sb.append("}");
    }

    public final boolean equals(Object obj) {
        if (obj instanceof SliceStructure) {
            return this.mStructure.equals(((SliceStructure) obj).mStructure);
        }
        return false;
    }

    public final int hashCode() {
        return this.mStructure.hashCode();
    }

    public SliceStructure(SliceItem sliceItem) {
        StringBuilder sb = new StringBuilder();
        getStructure(sliceItem, sb);
        this.mStructure = sb.toString();
        if (!"action".equals(sliceItem.mFormat) && !"slice".equals(sliceItem.mFormat)) {
            this.mUri = null;
        } else {
            this.mUri = Uri.parse(sliceItem.getSlice().mUri);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void getStructure(SliceItem sliceItem, StringBuilder sb) {
        char c;
        String str = sliceItem.mFormat;
        switch (str.hashCode()) {
            case -1422950858:
                if (str.equals("action")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1377881982:
                if (str.equals("bundle")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 104431:
                if (str.equals("int")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3327612:
                if (str.equals("long")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 3556653:
                if (str.equals("text")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 100313435:
                if (str.equals("image")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 100358090:
                if (str.equals("input")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 109526418:
                if (str.equals("slice")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            getStructure(sliceItem.getSlice(), sb);
            return;
        }
        if (c == 1) {
            sb.append('a');
            if ("range".equals(sliceItem.mSubType)) {
                sb.append('r');
            }
            getStructure(sliceItem.getSlice(), sb);
            return;
        }
        if (c == 2) {
            sb.append('t');
        } else {
            if (c != 3) {
                return;
            }
            sb.append('i');
        }
    }
}
