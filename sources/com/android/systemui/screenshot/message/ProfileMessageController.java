package com.android.systemui.screenshot.message;

import com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ProfileMessageController {
    public final ProfileFirstRunFileResourcesImpl fileResources;
    public final ProfileFirstRunSettingsImpl firstRunSettings;
    public final PackageLabelIconProviderImpl packageLabelIconProvider;
    public final ProfileTypeRepositoryImpl profileTypes;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FirstRunProfile {
        public static final /* synthetic */ FirstRunProfile[] $VALUES;
        public static final FirstRunProfile PRIVATE;
        public static final FirstRunProfile WORK;

        static {
            FirstRunProfile firstRunProfile = new FirstRunProfile("WORK", 0);
            WORK = firstRunProfile;
            FirstRunProfile firstRunProfile2 = new FirstRunProfile("PRIVATE", 1);
            PRIVATE = firstRunProfile2;
            FirstRunProfile[] firstRunProfileArr = {firstRunProfile, firstRunProfile2};
            $VALUES = firstRunProfileArr;
            EnumEntriesKt.enumEntries(firstRunProfileArr);
        }

        public static FirstRunProfile valueOf(String str) {
            return (FirstRunProfile) Enum.valueOf(FirstRunProfile.class, str);
        }

        public static FirstRunProfile[] values() {
            return (FirstRunProfile[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ProfileFirstRunData {
        public final LabeledIcon labeledIcon;
        public final FirstRunProfile profileType;

        public ProfileFirstRunData(LabeledIcon labeledIcon, FirstRunProfile firstRunProfile) {
            this.labeledIcon = labeledIcon;
            this.profileType = firstRunProfile;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ProfileFirstRunData)) {
                return false;
            }
            ProfileFirstRunData profileFirstRunData = (ProfileFirstRunData) obj;
            return Intrinsics.areEqual(this.labeledIcon, profileFirstRunData.labeledIcon) && this.profileType == profileFirstRunData.profileType;
        }

        public final int hashCode() {
            return this.profileType.hashCode() + (this.labeledIcon.hashCode() * 31);
        }

        public final String toString() {
            return "ProfileFirstRunData(labeledIcon=" + this.labeledIcon + ", profileType=" + this.profileType + ")";
        }
    }

    public ProfileMessageController(PackageLabelIconProviderImpl packageLabelIconProviderImpl, ProfileFirstRunFileResourcesImpl profileFirstRunFileResourcesImpl, ProfileFirstRunSettingsImpl profileFirstRunSettingsImpl, ProfileTypeRepositoryImpl profileTypeRepositoryImpl) {
        this.packageLabelIconProvider = packageLabelIconProviderImpl;
        this.fileResources = profileFirstRunFileResourcesImpl;
        this.firstRunSettings = profileFirstRunSettingsImpl;
        this.profileTypes = profileTypeRepositoryImpl;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x009d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object onScreenshotTaken(android.os.UserHandle r10, kotlin.coroutines.jvm.internal.ContinuationImpl r11) {
        /*
            Method dump skipped, instructions count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.message.ProfileMessageController.onScreenshotTaken(android.os.UserHandle, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
