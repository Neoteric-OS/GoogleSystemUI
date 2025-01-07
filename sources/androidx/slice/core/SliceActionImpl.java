package androidx.slice.core;

import android.app.PendingIntent;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.ArrayUtils;
import androidx.slice.Slice;
import androidx.slice.SliceItem;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliceActionImpl implements SliceAction {
    public final PendingIntent mAction;
    public final SliceItem mActionItem;
    public final String mActionKey;
    public final ActionType mActionType;
    public final CharSequence mContentDescription;
    public final long mDateTimeMillis;
    public final IconCompat mIcon;
    public final int mImageMode;
    public boolean mIsActivity;
    public final boolean mIsChecked;
    public final int mPriority;
    public final SliceItem mSliceItem;
    public final CharSequence mTitle;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class ActionType {
        public static final /* synthetic */ ActionType[] $VALUES;
        public static final ActionType DATE_PICKER;
        public static final ActionType DEFAULT;
        public static final ActionType TIME_PICKER;
        public static final ActionType TOGGLE;

        static {
            ActionType actionType = new ActionType("DEFAULT", 0);
            DEFAULT = actionType;
            ActionType actionType2 = new ActionType("TOGGLE", 1);
            TOGGLE = actionType2;
            ActionType actionType3 = new ActionType("DATE_PICKER", 2);
            DATE_PICKER = actionType3;
            ActionType actionType4 = new ActionType("TIME_PICKER", 3);
            TIME_PICKER = actionType4;
            $VALUES = new ActionType[]{actionType, actionType2, actionType3, actionType4};
        }

        public static ActionType valueOf(String str) {
            return (ActionType) Enum.valueOf(ActionType.class, str);
        }

        public static ActionType[] values() {
            return (ActionType[]) $VALUES.clone();
        }
    }

    public SliceActionImpl(PendingIntent pendingIntent, IconCompat iconCompat, int i, CharSequence charSequence) {
        this.mActionType = ActionType.DEFAULT;
        this.mPriority = -1;
        this.mDateTimeMillis = -1L;
        this.mAction = pendingIntent;
        this.mIcon = iconCompat;
        this.mTitle = charSequence;
        this.mImageMode = i;
    }

    public static int parseImageMode(SliceItem sliceItem) {
        if (ArrayUtils.contains(sliceItem.mHints, "show_label")) {
            return 6;
        }
        if (ArrayUtils.contains(sliceItem.mHints, "no_tint")) {
            return ArrayUtils.contains(sliceItem.mHints, "raw") ? ArrayUtils.contains(sliceItem.mHints, "large") ? 4 : 3 : ArrayUtils.contains(sliceItem.mHints, "large") ? 2 : 1;
        }
        return 0;
    }

    public final Slice.Builder buildSliceContent(Slice.Builder builder) {
        Slice.Builder builder2 = new Slice.Builder(builder);
        IconCompat iconCompat = this.mIcon;
        if (iconCompat != null) {
            int i = this.mImageMode;
            builder2.addIcon(iconCompat, null, i == 6 ? new String[]{"show_label"} : i == 0 ? new String[0] : new String[]{"no_tint"});
        }
        CharSequence charSequence = this.mTitle;
        if (charSequence != null) {
            builder2.addText(charSequence, null, "title");
        }
        CharSequence charSequence2 = this.mContentDescription;
        if (charSequence2 != null) {
            builder2.addText(charSequence2, "content_description", new String[0]);
        }
        long j = this.mDateTimeMillis;
        if (j != -1) {
            builder2.mItems.add(new SliceItem(Long.valueOf(j), "long", "millis", new String[0]));
        }
        if (this.mActionType == ActionType.TOGGLE && this.mIsChecked) {
            builder2.addHints("selected");
        }
        int i2 = this.mPriority;
        if (i2 != -1) {
            builder2.addInt("priority", i2, new String[0]);
        }
        String str = this.mActionKey;
        if (str != null) {
            builder2.addText(str, "action_key", new String[0]);
        }
        if (this.mIsActivity) {
            builder.addHints("activity");
        }
        return builder2;
    }

    @Override // androidx.slice.core.SliceAction
    public final int getPriority() {
        return this.mPriority;
    }

    public final String getSubtype() {
        int ordinal = this.mActionType.ordinal();
        if (ordinal == 1) {
            return "toggle";
        }
        if (ordinal == 2) {
            return "date_picker";
        }
        if (ordinal != 3) {
            return null;
        }
        return "time_picker";
    }

    public final boolean isDefaultToggle() {
        return this.mActionType == ActionType.TOGGLE && this.mIcon == null;
    }

    @Override // androidx.slice.core.SliceAction
    public final boolean isToggle() {
        return this.mActionType == ActionType.TOGGLE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public SliceActionImpl(SliceItem sliceItem) {
        char c;
        this.mImageMode = 5;
        ActionType actionType = ActionType.DEFAULT;
        this.mActionType = actionType;
        this.mPriority = -1;
        this.mDateTimeMillis = -1L;
        this.mSliceItem = sliceItem;
        SliceItem find = SliceQuery.find(sliceItem, "action", (String[]) null, (String[]) null);
        if (find == null) {
            return;
        }
        this.mActionItem = find;
        this.mAction = find.getAction();
        SliceItem find2 = SliceQuery.find(find.getSlice(), "image", (String[]) null, (String[]) null);
        if (find2 != null) {
            this.mIcon = (IconCompat) find2.mObj;
            this.mImageMode = parseImageMode(find2);
        }
        SliceItem find3 = SliceQuery.find(find.getSlice(), "text", "title");
        if (find3 != null) {
            this.mTitle = find3.getSanitizedText();
        }
        SliceItem findSubtype = SliceQuery.findSubtype(find.getSlice(), "text", "content_description");
        if (findSubtype != null) {
            this.mContentDescription = (CharSequence) findSubtype.mObj;
        }
        String str = find.mSubType;
        if (str == null) {
            this.mActionType = actionType;
        } else {
            switch (str.hashCode()) {
                case -868304044:
                    if (str.equals("toggle")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 759128640:
                    if (str.equals("time_picker")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1250407999:
                    if (str.equals("date_picker")) {
                        c = 2;
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
                    this.mActionType = ActionType.TOGGLE;
                    this.mIsChecked = ArrayUtils.contains(find.mHints, "selected");
                    break;
                case 1:
                    this.mActionType = ActionType.TIME_PICKER;
                    SliceItem findSubtype2 = SliceQuery.findSubtype(find, "long", "millis");
                    if (findSubtype2 != null) {
                        this.mDateTimeMillis = findSubtype2.getLong();
                        break;
                    }
                    break;
                case 2:
                    this.mActionType = ActionType.DATE_PICKER;
                    SliceItem findSubtype3 = SliceQuery.findSubtype(find, "long", "millis");
                    if (findSubtype3 != null) {
                        this.mDateTimeMillis = findSubtype3.getLong();
                        break;
                    }
                    break;
                default:
                    this.mActionType = actionType;
                    break;
            }
        }
        this.mIsActivity = ArrayUtils.contains(sliceItem.mHints, "activity");
        SliceItem findSubtype4 = SliceQuery.findSubtype(find.getSlice(), "int", "priority");
        this.mPriority = findSubtype4 != null ? findSubtype4.getInt() : -1;
        SliceItem findSubtype5 = SliceQuery.findSubtype(find.getSlice(), "text", "action_key");
        if (findSubtype5 != null) {
            this.mActionKey = ((CharSequence) findSubtype5.mObj).toString();
        }
    }
}
