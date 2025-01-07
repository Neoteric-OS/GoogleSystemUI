package com.android.systemui.people;

import android.app.people.ConversationStatus;
import android.app.people.PeopleSpaceTile;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.IconDrawableFactory;
import android.util.Log;
import android.util.Pair;
import android.util.SizeF;
import android.widget.RemoteViews;
import android.widget.TextView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable21;
import androidx.core.math.MathUtils;
import com.android.launcher3.icons.FastBitmapDrawable;
import com.android.settingslib.Utils;
import com.android.systemui.people.PeopleStoryIconFactory;
import com.android.systemui.people.widget.PeopleTileKey;
import com.android.wm.shell.R;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PeopleTileViewHelper {
    public int mAppWidgetId;
    public Context mContext;
    public float mDensity;
    public int mHeight;
    public NumberFormat mIntegerFormat;
    public boolean mIsLeftToRight;
    public PeopleTileKey mKey;
    public int mLayoutSize;
    public Locale mLocale;
    public int mMediumVerticalPadding;
    public PeopleSpaceTile mTile;
    public int mWidth;
    public static final Pattern DOUBLE_EXCLAMATION_PATTERN = Pattern.compile("[!][!]+");
    public static final Pattern DOUBLE_QUESTION_PATTERN = Pattern.compile("[?][?]+");
    public static final Pattern ANY_DOUBLE_MARK_PATTERN = Pattern.compile("[!?][!?]+");
    public static final Pattern MIXED_MARK_PATTERN = Pattern.compile("![?].*|.*[?]!");

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RemoteViewsAndSizes {
        public final int mAvatarSize;
        public final RemoteViews mRemoteViews;

        public RemoteViewsAndSizes(RemoteViews remoteViews, int i) {
            this.mRemoteViews = remoteViews;
            this.mAvatarSize = i;
        }
    }

    public static RemoteViews createRemoteViews(final Context context, final PeopleSpaceTile peopleSpaceTile, final int i, Bundle bundle, final PeopleTileKey peopleTileKey) {
        float f = context.getResources().getDisplayMetrics().density;
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("appWidgetSizes");
        if (parcelableArrayList == null || parcelableArrayList.isEmpty()) {
            int dimension = (int) (context.getResources().getDimension(R.dimen.default_width) / f);
            int dimension2 = (int) (context.getResources().getDimension(R.dimen.default_height) / f);
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(new SizeF(bundle.getInt("appWidgetMinWidth", dimension), bundle.getInt("appWidgetMaxHeight", dimension2)));
            arrayList.add(new SizeF(bundle.getInt("appWidgetMaxWidth", dimension), bundle.getInt("appWidgetMinHeight", dimension2)));
            parcelableArrayList = arrayList;
        }
        return new RemoteViews((Map<SizeF, RemoteViews>) parcelableArrayList.stream().distinct().collect(Collectors.toMap(Function.identity(), new Function() { // from class: com.android.systemui.people.PeopleTileViewHelper$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Context context2 = context;
                PeopleSpaceTile peopleSpaceTile2 = peopleSpaceTile;
                int i2 = i;
                PeopleTileKey peopleTileKey2 = peopleTileKey;
                SizeF sizeF = (SizeF) obj;
                int width = (int) sizeF.getWidth();
                int height = (int) sizeF.getHeight();
                PeopleTileViewHelper peopleTileViewHelper = new PeopleTileViewHelper();
                peopleTileViewHelper.mContext = context2;
                peopleTileViewHelper.mTile = peopleSpaceTile2;
                peopleTileViewHelper.mKey = peopleTileKey2;
                peopleTileViewHelper.mAppWidgetId = i2;
                peopleTileViewHelper.mDensity = context2.getResources().getDisplayMetrics().density;
                peopleTileViewHelper.mWidth = width;
                peopleTileViewHelper.mHeight = height;
                int i3 = 2;
                if (height < peopleTileViewHelper.getSizeInDp(R.dimen.required_height_for_large) || width < peopleTileViewHelper.getSizeInDp(R.dimen.required_width_for_large)) {
                    if (height < peopleTileViewHelper.getSizeInDp(R.dimen.required_height_for_medium) || width < peopleTileViewHelper.getSizeInDp(R.dimen.required_width_for_medium)) {
                        i3 = 0;
                    } else {
                        peopleTileViewHelper.mMediumVerticalPadding = Math.max(4, Math.min(Math.floorDiv(height - (peopleTileViewHelper.getLineHeightFromResource(R.dimen.name_text_size_for_medium_content) + (peopleTileViewHelper.getSizeInDp(R.dimen.avatar_size_for_medium) + 4)), 2), 16));
                        i3 = 1;
                    }
                }
                peopleTileViewHelper.mLayoutSize = i3;
                peopleTileViewHelper.mIsLeftToRight = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 0;
                return peopleTileViewHelper.getViews();
            }
        })));
    }

    public static Bitmap getPersonIconBitmap(Context context, int i, boolean z, Icon icon, String str, int i2, boolean z2, boolean z3) {
        Drawable defaultActivityIcon;
        if (icon == null) {
            Drawable mutate = context.getDrawable(R.drawable.ic_avatar_with_badge).mutate();
            mutate.setColorFilter(FastBitmapDrawable.getDisabledColorFilter(1.0f));
            return PeopleSpaceUtils.convertDrawableToBitmap(mutate);
        }
        PackageManager packageManager = context.getPackageManager();
        IconDrawableFactory.newInstance(context, false);
        PeopleStoryIconFactory peopleStoryIconFactory = new PeopleStoryIconFactory(context, packageManager, i);
        RoundedBitmapDrawable21 roundedBitmapDrawable21 = new RoundedBitmapDrawable21(context.getResources(), icon.getBitmap());
        try {
            defaultActivityIcon = Utils.getBadgedIcon(peopleStoryIconFactory.mContext, peopleStoryIconFactory.mPackageManager.getApplicationInfoAsUser(str, 128, i2));
        } catch (PackageManager.NameNotFoundException unused) {
            defaultActivityIcon = peopleStoryIconFactory.mPackageManager.getDefaultActivityIcon();
        }
        int i3 = peopleStoryIconFactory.mIconBitmapSize;
        int i4 = peopleStoryIconFactory.mImportantConversationColor;
        float f = peopleStoryIconFactory.mIconSize;
        float f2 = peopleStoryIconFactory.mDensity;
        int i5 = peopleStoryIconFactory.mAccentColor;
        PeopleStoryIconFactory.PeopleStoryIconDrawable peopleStoryIconDrawable = new PeopleStoryIconFactory.PeopleStoryIconDrawable();
        roundedBitmapDrawable21.mIsCircular = true;
        roundedBitmapDrawable21.mApplyGravity = true;
        roundedBitmapDrawable21.mCornerRadius = Math.min(roundedBitmapDrawable21.mBitmapHeight, roundedBitmapDrawable21.mBitmapWidth) / 2;
        roundedBitmapDrawable21.mPaint.setShader(roundedBitmapDrawable21.mBitmapShader);
        roundedBitmapDrawable21.invalidateSelf();
        peopleStoryIconDrawable.mAvatar = roundedBitmapDrawable21;
        peopleStoryIconDrawable.mBadgeIcon = defaultActivityIcon;
        peopleStoryIconDrawable.mIconSize = i3;
        peopleStoryIconDrawable.mShowImportantRing = z2;
        Paint paint = new Paint();
        peopleStoryIconDrawable.mPriorityRingPaint = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(i4);
        peopleStoryIconDrawable.mShowStoryRing = z;
        Paint paint2 = new Paint();
        peopleStoryIconDrawable.mStoryPaint = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(i5);
        peopleStoryIconDrawable.mFullIconSize = f;
        peopleStoryIconDrawable.mDensity = f2;
        if (z3) {
            peopleStoryIconDrawable.setColorFilter(FastBitmapDrawable.getDisabledColorFilter(1.0f));
        }
        return PeopleSpaceUtils.convertDrawableToBitmap(peopleStoryIconDrawable);
    }

    public static boolean isDndBlockingTileData(PeopleSpaceTile peopleSpaceTile) {
        if (peopleSpaceTile == null) {
            return false;
        }
        int notificationPolicyState = peopleSpaceTile.getNotificationPolicyState();
        if ((notificationPolicyState & 1) != 0) {
            return false;
        }
        if ((notificationPolicyState & 4) != 0 && peopleSpaceTile.isImportantConversation()) {
            return false;
        }
        if ((notificationPolicyState & 8) != 0 && peopleSpaceTile.getContactAffinity() == 1.0f) {
            return false;
        }
        if ((notificationPolicyState & 16) == 0 || !(peopleSpaceTile.getContactAffinity() == 0.5f || peopleSpaceTile.getContactAffinity() == 1.0f)) {
            return !peopleSpaceTile.canBypassDnd();
        }
        return false;
    }

    public static void setEmojiBackground(RemoteViews remoteViews, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            remoteViews.setViewVisibility(R.id.emojis, 8);
            return;
        }
        remoteViews.setTextViewText(R.id.emoji1, charSequence);
        remoteViews.setTextViewText(R.id.emoji2, charSequence);
        remoteViews.setTextViewText(R.id.emoji3, charSequence);
        remoteViews.setViewVisibility(R.id.emojis, 0);
    }

    public static void setPunctuationBackground(RemoteViews remoteViews, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            remoteViews.setViewVisibility(R.id.punctuations, 8);
            return;
        }
        remoteViews.setTextViewText(R.id.punctuation1, charSequence);
        remoteViews.setTextViewText(R.id.punctuation2, charSequence);
        remoteViews.setTextViewText(R.id.punctuation3, charSequence);
        remoteViews.setTextViewText(R.id.punctuation4, charSequence);
        remoteViews.setTextViewText(R.id.punctuation5, charSequence);
        remoteViews.setTextViewText(R.id.punctuation6, charSequence);
        remoteViews.setViewVisibility(R.id.punctuations, 0);
    }

    public final RemoteViewsAndSizes createDndRemoteViews() {
        int i;
        StaticLayout staticLayout;
        PeopleTileViewHelper peopleTileViewHelper;
        String packageName = this.mContext.getPackageName();
        int i2 = this.mLayoutSize;
        if (i2 == 1) {
            i = R.layout.people_tile_with_suppression_detail_content_horizontal;
        } else if (i2 != 2) {
            i = this.mHeight >= getSizeInDp(R.dimen.required_height_for_medium) ? R.layout.people_tile_small : R.layout.people_tile_small_horizontal;
        } else {
            i = R.layout.people_tile_with_suppression_detail_content_vertical;
        }
        RemoteViews remoteViews = new RemoteViews(packageName, i);
        int sizeInDp = getSizeInDp(R.dimen.avatar_size_for_medium_empty);
        int sizeInDp2 = getSizeInDp(R.dimen.max_people_avatar_size);
        String string = this.mContext.getString(R.string.paused_by_dnd);
        remoteViews.setTextViewText(R.id.text_content, string);
        int i3 = i2 == 2 ? R.dimen.content_text_size_for_large : R.dimen.content_text_size_for_medium;
        remoteViews.setTextViewTextSize(R.id.text_content, 0, this.mContext.getResources().getDimension(i3));
        int lineHeightFromResource = getLineHeightFromResource(i3);
        int i4 = this.mHeight;
        if (i2 == 1) {
            remoteViews.setInt(R.id.text_content, "setMaxLines", (i4 - 16) / lineHeightFromResource);
        } else {
            float f = this.mDensity;
            int i5 = (int) (16 * f);
            int i6 = (int) (14 * f);
            int sizeInDp3 = getSizeInDp(i2 == 0 ? R.dimen.regular_predefined_icon : R.dimen.largest_predefined_icon);
            int i7 = (i4 - 32) - sizeInDp3;
            int sizeInDp4 = getSizeInDp(R.dimen.padding_between_suppressed_layout_items);
            int i8 = this.mWidth - 32;
            int i9 = sizeInDp4 * 2;
            int i10 = (i7 - sizeInDp) - i9;
            try {
                TextView textView = new TextView(this.mContext);
                textView.setTextSize(0, this.mContext.getResources().getDimension(i3));
                textView.setTextAppearance(android.R.style.TextAppearance.DeviceDefault);
                staticLayout = StaticLayout.Builder.obtain(string, 0, string.length(), textView.getPaint(), (int) (i8 * f)).setBreakStrategy(0).build();
            } catch (Exception e) {
                Log.e("PeopleTileView", "Could not create static layout: " + e);
                staticLayout = null;
            }
            int height = staticLayout == null ? Integer.MAX_VALUE : (int) (staticLayout.getHeight() / f);
            if (height > i10 || i2 != 2) {
                if (i2 != 0) {
                    peopleTileViewHelper = this;
                    remoteViews = new RemoteViews(peopleTileViewHelper.mContext.getPackageName(), R.layout.people_tile_small);
                } else {
                    peopleTileViewHelper = this;
                }
                sizeInDp = peopleTileViewHelper.getMaxAvatarSize(remoteViews);
                remoteViews.setViewVisibility(R.id.messages_count, 8);
                remoteViews.setViewVisibility(R.id.name, 8);
                remoteViews.setContentDescription(R.id.predefined_icon, string);
            } else {
                remoteViews.setViewVisibility(R.id.text_content, 0);
                remoteViews.setInt(R.id.text_content, "setMaxLines", i10 / lineHeightFromResource);
                remoteViews.setContentDescription(R.id.predefined_icon, null);
                sizeInDp = MathUtils.clamp(Math.min(i8, (i7 - height) - i9), (int) (10.0f * f), sizeInDp2);
                remoteViews.setViewPadding(android.R.id.background, i5, i6, i5, i5);
                float f2 = sizeInDp3;
                remoteViews.setViewLayoutWidth(R.id.predefined_icon, f2, 1);
                remoteViews.setViewLayoutHeight(R.id.predefined_icon, f2, 1);
            }
            remoteViews.setViewVisibility(R.id.predefined_icon, 0);
            remoteViews.setImageViewResource(R.id.predefined_icon, R.drawable.ic_qs_dnd_on);
        }
        return new RemoteViewsAndSizes(remoteViews, sizeInDp);
    }

    public final RemoteViews createStatusRemoteViews(ConversationStatus conversationStatus) {
        int i;
        int i2;
        String packageName = this.mContext.getPackageName();
        int i3 = this.mLayoutSize;
        if (i3 == 1) {
            i = R.layout.people_tile_medium_with_content;
        } else if (i3 != 2) {
            i = this.mHeight >= getSizeInDp(R.dimen.required_height_for_medium) ? R.layout.people_tile_small : R.layout.people_tile_small_horizontal;
        } else {
            i = R.layout.people_tile_large_with_status_content;
        }
        RemoteViews remoteViews = new RemoteViews(packageName, i);
        setViewForContentLayout(remoteViews);
        CharSequence description = conversationStatus.getDescription();
        CharSequence charSequence = "";
        if (TextUtils.isEmpty(description)) {
            switch (conversationStatus.getActivity()) {
                case 1:
                    description = this.mContext.getString(R.string.birthday_status);
                    break;
                case 2:
                    description = this.mContext.getString(R.string.anniversary_status);
                    break;
                case 3:
                    description = this.mContext.getString(R.string.new_story_status);
                    break;
                case 4:
                    description = this.mContext.getString(R.string.audio_status);
                    break;
                case 5:
                    description = this.mContext.getString(R.string.video_status);
                    break;
                case 6:
                    description = this.mContext.getString(R.string.game_status);
                    break;
                case 7:
                    description = this.mContext.getString(R.string.location_status);
                    break;
                case 8:
                    description = this.mContext.getString(R.string.upcoming_birthday_status);
                    break;
                default:
                    description = "";
                    break;
            }
        }
        setPredefinedIconVisible(remoteViews);
        int i4 = R.id.text_content;
        remoteViews.setTextViewText(R.id.text_content, description);
        if (conversationStatus.getActivity() == 1 || conversationStatus.getActivity() == 8) {
            Pattern pattern = EmojiHelper.EMOJI_PATTERN;
            setEmojiBackground(remoteViews, "🎂");
        }
        Icon icon = conversationStatus.getIcon();
        int i5 = this.mLayoutSize;
        if (icon != null) {
            remoteViews.setViewVisibility(R.id.scrim_layout, 0);
            remoteViews.setImageViewIcon(R.id.status_icon, icon);
            if (i5 == 2) {
                remoteViews.setInt(R.id.content, "setGravity", 80);
                remoteViews.setViewVisibility(R.id.name, 8);
                remoteViews.setColorAttr(R.id.text_content, "setTextColor", android.R.attr.textColorPrimary);
            } else if (i5 == 1) {
                remoteViews.setViewVisibility(R.id.text_content, 8);
                remoteViews.setTextViewText(R.id.name, description);
            }
        } else {
            remoteViews.setColorAttr(R.id.text_content, "setTextColor", android.R.attr.textColorSecondary);
            setMaxLines(remoteViews, false);
        }
        setAvailabilityDotPadding(remoteViews, R.dimen.availability_dot_status_padding);
        switch (conversationStatus.getActivity()) {
            case 1:
                i2 = R.drawable.ic_cake;
                break;
            case 2:
                i2 = R.drawable.ic_celebration;
                break;
            case 3:
                i2 = R.drawable.ic_pages;
                break;
            case 4:
                i2 = R.drawable.ic_music_note;
                break;
            case 5:
                i2 = R.drawable.ic_video;
                break;
            case 6:
                i2 = R.drawable.ic_play_games;
                break;
            case 7:
                i2 = R.drawable.ic_location;
                break;
            case 8:
                i2 = R.drawable.ic_gift;
                break;
            default:
                i2 = R.drawable.ic_person;
                break;
        }
        remoteViews.setImageViewResource(R.id.predefined_icon, i2);
        CharSequence userName = this.mTile.getUserName();
        if (TextUtils.isEmpty(conversationStatus.getDescription())) {
            switch (conversationStatus.getActivity()) {
                case 1:
                    charSequence = this.mContext.getString(R.string.birthday_status_content_description, userName);
                    break;
                case 2:
                    charSequence = this.mContext.getString(R.string.anniversary_status_content_description, userName);
                    break;
                case 3:
                    charSequence = this.mContext.getString(R.string.new_story_status_content_description, userName);
                    break;
                case 4:
                    charSequence = this.mContext.getString(R.string.audio_status);
                    break;
                case 5:
                    charSequence = this.mContext.getString(R.string.video_status);
                    break;
                case 6:
                    charSequence = this.mContext.getString(R.string.game_status);
                    break;
                case 7:
                    charSequence = this.mContext.getString(R.string.location_status_content_description, userName);
                    break;
                case 8:
                    charSequence = this.mContext.getString(R.string.upcoming_birthday_status_content_description, userName);
                    break;
            }
        } else {
            charSequence = conversationStatus.getDescription();
        }
        String string = this.mContext.getString(R.string.new_status_content_description, this.mTile.getUserName(), charSequence);
        if (i5 == 0) {
            remoteViews.setContentDescription(R.id.predefined_icon, string);
        } else if (i5 == 1) {
            if (icon != null) {
                i4 = R.id.name;
            }
            remoteViews.setContentDescription(i4, string);
        } else if (i5 == 2) {
            remoteViews.setContentDescription(R.id.text_content, string);
        }
        return remoteViews;
    }

    public CharSequence getDoubleEmoji(CharSequence charSequence) {
        Matcher matcher = EmojiHelper.EMOJI_PATTERN.matcher(charSequence);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            arrayList.add(new Pair(Integer.valueOf(start), Integer.valueOf(end)));
            arrayList2.add(charSequence.subSequence(start, end));
        }
        if (arrayList.size() < 2) {
            return null;
        }
        for (int i = 1; i < arrayList.size(); i++) {
            int i2 = i - 1;
            if (Objects.equals(((Pair) arrayList.get(i)).first, ((Pair) arrayList.get(i2)).second) && Objects.equals(arrayList2.get(i), arrayList2.get(i2))) {
                return (CharSequence) arrayList2.get(i);
            }
        }
        return null;
    }

    public CharSequence getDoublePunctuation(CharSequence charSequence) {
        if (!ANY_DOUBLE_MARK_PATTERN.matcher(charSequence).find()) {
            return null;
        }
        if (MIXED_MARK_PATTERN.matcher(charSequence).find()) {
            return "!?";
        }
        Matcher matcher = DOUBLE_QUESTION_PATTERN.matcher(charSequence);
        if (!matcher.find()) {
            return "!";
        }
        Matcher matcher2 = DOUBLE_EXCLAMATION_PATTERN.matcher(charSequence);
        return (matcher2.find() && matcher.start() >= matcher2.start()) ? "!" : "?";
    }

    public final int getLineHeightFromResource(int i) {
        try {
            TextView textView = new TextView(this.mContext);
            textView.setTextSize(0, this.mContext.getResources().getDimension(i));
            textView.setTextAppearance(android.R.style.TextAppearance.DeviceDefault);
            return (int) (textView.getLineHeight() / this.mDensity);
        } catch (Exception e) {
            Log.e("PeopleTileView", "Could not create text view: " + e);
            return this.getSizeInDp(R.dimen.content_text_size_for_medium);
        }
    }

    public final int getMaxAvatarSize(RemoteViews remoteViews) {
        int layoutId = remoteViews.getLayoutId();
        int sizeInDp = getSizeInDp(R.dimen.avatar_size_for_medium);
        if (layoutId == R.layout.people_tile_medium_empty) {
            return getSizeInDp(R.dimen.max_people_avatar_size_for_large_content);
        }
        if (layoutId == R.layout.people_tile_medium_with_content) {
            return getSizeInDp(R.dimen.avatar_size_for_medium);
        }
        int i = this.mWidth;
        int i2 = this.mHeight;
        if (layoutId == R.layout.people_tile_small) {
            sizeInDp = Math.min(i2 - (Math.max(18, getLineHeightFromResource(R.dimen.name_text_size_for_small)) + 18), i - 8);
        }
        if (layoutId == R.layout.people_tile_small_horizontal) {
            sizeInDp = Math.min(i2 - 10, i - 16);
        }
        if (layoutId == R.layout.people_tile_large_with_notification_content) {
            return Math.min(i2 - ((getLineHeightFromResource(R.dimen.content_text_size_for_large) * 3) + 62), getSizeInDp(R.dimen.max_people_avatar_size_for_large_content));
        }
        if (layoutId == R.layout.people_tile_large_with_status_content) {
            return Math.min(i2 - ((getLineHeightFromResource(R.dimen.content_text_size_for_large) * 3) + 76), getSizeInDp(R.dimen.max_people_avatar_size_for_large_content));
        }
        if (layoutId == R.layout.people_tile_large_empty) {
            sizeInDp = Math.min(i2 - ((getLineHeightFromResource(R.dimen.content_text_size_for_large) + (getLineHeightFromResource(R.dimen.name_text_size_for_large) + 28)) + 42), i - 28);
        }
        if (isDndBlockingTileData(this.mTile) && this.mLayoutSize != 0) {
            sizeInDp = createDndRemoteViews().mAvatarSize;
        }
        return Math.min(sizeInDp, getSizeInDp(R.dimen.max_people_avatar_size));
    }

    public final int getSizeInDp(int i) {
        Context context = this.mContext;
        return (int) (context.getResources().getDimension(i) / this.mDensity);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:0|1|(3:200|(1:206)(1:204)|205)(2:7|(1:9)(2:62|(7:64|(1:(2:67|(1:69)(1:78))(1:79))(1:80)|70|(1:72)(1:77)|73|(1:75)|76)(2:81|(17:83|(1:(2:86|(1:88)(1:141))(1:142))(1:143)|89|(3:91|92|93)(9:125|(1:127)(1:140)|128|(1:130)(1:139)|131|(1:133)(1:138)|134|(1:136)|137)|94|(6:(4:97|(1:99)(1:104)|(1:101)(1:103)|102)|105|(1:107)(3:112|(1:114)|115)|108|109|(1:111))|116|(1:118)(1:121)|119|120|11|12|13|(13:31|(2:33|(10:35|36|37|(1:39)(1:56)|(1:41)(1:55)|(1:43)(1:54)|44|(1:53)(1:48)|49|(1:51)(1:52)))(1:58)|57|36|37|(0)(0)|(0)(0)|(0)(0)|44|(1:46)|53|49|(0)(0))(1:15)|16|(4:20|21|(1:23)|24)|29)(5:144|(1:146)(1:199)|147|(1:149)(2:195|(1:197)(1:198))|(1:151)(2:152|(1:154)(11:155|(1:(2:158|(1:160)(1:192))(1:193))(1:194)|161|(1:163)|164|(1:166)|167|(1:169)(2:177|(4:179|(1:181)(2:182|(1:184)(2:185|(1:187)(2:188|(1:190)(1:191))))|171|(1:173)(2:174|(1:176))))|170|171|(0)(0)))))))|10|11|12|13|(0)(0)|16|(5:18|20|21|(0)|24)|29) */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x04a3, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x053d, code lost:
    
        android.util.Log.e("PeopleTileView", "Failed to set common fields: " + r0);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0464  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0408  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0588 A[Catch: Exception -> 0x0592, TryCatch #2 {Exception -> 0x0592, blocks: (B:21:0x055b, B:23:0x0588, B:24:0x0594), top: B:20:0x055b }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0466 A[Catch: Exception -> 0x04a3, TryCatch #0 {Exception -> 0x04a3, blocks: (B:13:0x0460, B:31:0x0466, B:33:0x046f, B:35:0x0485, B:37:0x04b8, B:44:0x04cf, B:46:0x04e0, B:49:0x04f5, B:51:0x0521, B:52:0x0538, B:57:0x04a7), top: B:12:0x0460 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x04c2  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x04c7  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x04cc  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0521 A[Catch: Exception -> 0x04a3, TryCatch #0 {Exception -> 0x04a3, blocks: (B:13:0x0460, B:31:0x0466, B:33:0x046f, B:35:0x0485, B:37:0x04b8, B:44:0x04cf, B:46:0x04e0, B:49:0x04f5, B:51:0x0521, B:52:0x0538, B:57:0x04a7), top: B:12:0x0460 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0538 A[Catch: Exception -> 0x04a3, TRY_LEAVE, TryCatch #0 {Exception -> 0x04a3, blocks: (B:13:0x0460, B:31:0x0466, B:33:0x046f, B:35:0x0485, B:37:0x04b8, B:44:0x04cf, B:46:0x04e0, B:49:0x04f5, B:51:0x0521, B:52:0x0538, B:57:0x04a7), top: B:12:0x0460 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x04ce  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x04c9  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x04c4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.widget.RemoteViews getViews() {
        /*
            Method dump skipped, instructions count: 1462
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.people.PeopleTileViewHelper.getViews():android.widget.RemoteViews");
    }

    public final void setAvailabilityDotPadding(RemoteViews remoteViews, int i) {
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(i);
        int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(R.dimen.medium_content_padding_above_name);
        boolean z = this.mIsLeftToRight;
        remoteViews.setViewPadding(R.id.medium_content, z ? dimensionPixelSize : 0, 0, z ? 0 : dimensionPixelSize, dimensionPixelSize2);
    }

    public final void setMaxLines(RemoteViews remoteViews, boolean z) {
        int lineHeightFromResource;
        int i;
        int i2;
        int i3 = this.mLayoutSize;
        if (i3 == 2) {
            lineHeightFromResource = getLineHeightFromResource(R.dimen.name_text_size_for_large_content);
            i = R.dimen.content_text_size_for_large;
        } else {
            lineHeightFromResource = getLineHeightFromResource(R.dimen.name_text_size_for_medium_content);
            i = R.dimen.content_text_size_for_medium;
        }
        boolean z2 = remoteViews.getLayoutId() == R.layout.people_tile_large_with_status_content;
        int i4 = this.mHeight;
        if (i3 == 1) {
            i2 = i4 - ((this.mMediumVerticalPadding * 2) + (lineHeightFromResource + 12));
        } else if (i3 != 2) {
            i2 = -1;
        } else {
            i2 = i4 - ((getSizeInDp(R.dimen.max_people_avatar_size_for_large_content) + lineHeightFromResource) + (z2 ? 76 : 62));
        }
        int max = Math.max(2, Math.floorDiv(i2, getLineHeightFromResource(i)));
        if (z) {
            max--;
        }
        remoteViews.setInt(R.id.text_content, "setMaxLines", max);
    }

    public final void setPredefinedIconVisible(RemoteViews remoteViews) {
        remoteViews.setViewVisibility(R.id.predefined_icon, 0);
        if (this.mLayoutSize == 1) {
            int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.before_predefined_icon_padding);
            boolean z = this.mIsLeftToRight;
            remoteViews.setViewPadding(R.id.name, z ? 0 : dimensionPixelSize, 0, z ? dimensionPixelSize : 0, 0);
        }
    }

    public final RemoteViews setViewForContentLayout(RemoteViews remoteViews) {
        CharSequence doubleEmoji = getDoubleEmoji("");
        if (TextUtils.isEmpty(doubleEmoji)) {
            CharSequence doublePunctuation = getDoublePunctuation("");
            setEmojiBackground(remoteViews, null);
            setPunctuationBackground(remoteViews, doublePunctuation);
        } else {
            setEmojiBackground(remoteViews, doubleEmoji);
            setPunctuationBackground(remoteViews, null);
        }
        remoteViews.setContentDescription(R.id.predefined_icon, null);
        remoteViews.setContentDescription(R.id.text_content, null);
        remoteViews.setContentDescription(R.id.name, null);
        remoteViews.setContentDescription(R.id.image, null);
        remoteViews.setAccessibilityTraversalAfter(R.id.text_content, R.id.name);
        int i = this.mLayoutSize;
        if (i == 0) {
            remoteViews.setViewVisibility(R.id.predefined_icon, 0);
            remoteViews.setViewVisibility(R.id.name, 8);
        } else {
            remoteViews.setViewVisibility(R.id.predefined_icon, 8);
            remoteViews.setViewVisibility(R.id.name, 0);
            remoteViews.setViewVisibility(R.id.text_content, 0);
            remoteViews.setViewVisibility(R.id.subtext, 8);
            remoteViews.setViewVisibility(R.id.image, 8);
            remoteViews.setViewVisibility(R.id.scrim_layout, 8);
        }
        if (i == 1) {
            float f = this.mDensity;
            int floor = (int) Math.floor(16.0f * f);
            int floor2 = (int) Math.floor(this.mMediumVerticalPadding * f);
            remoteViews.setViewPadding(R.id.content, floor, floor2, floor, floor2);
            remoteViews.setViewPadding(R.id.name, 0, 0, 0, 0);
            if (this.mHeight > ((int) (this.mContext.getResources().getDimension(R.dimen.medium_height_for_max_name_text_size) / f))) {
                remoteViews.setTextViewTextSize(R.id.name, 0, (int) this.mContext.getResources().getDimension(R.dimen.max_name_text_size_for_medium));
            }
        }
        if (i == 2) {
            remoteViews.setViewPadding(R.id.name, 0, 0, 0, this.mContext.getResources().getDimensionPixelSize(R.dimen.below_name_text_padding));
            remoteViews.setInt(R.id.content, "setGravity", 48);
        }
        remoteViews.setViewLayoutHeightDimen(R.id.predefined_icon, R.dimen.regular_predefined_icon);
        remoteViews.setViewLayoutWidthDimen(R.id.predefined_icon, R.dimen.regular_predefined_icon);
        remoteViews.setViewVisibility(R.id.messages_count, 8);
        if (this.mTile.getUserName() != null) {
            remoteViews.setTextViewText(R.id.name, this.mTile.getUserName());
        }
        return remoteViews;
    }
}
