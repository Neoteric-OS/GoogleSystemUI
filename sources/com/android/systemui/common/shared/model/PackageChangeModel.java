package com.android.systemui.common.shared.model;

import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface PackageChangeModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Changed implements PackageChangeModel {
        public final String packageName;
        public final int packageUid;
        public final long timeMillis;

        public Changed(int i, long j, String str) {
            this.packageName = str;
            this.packageUid = i;
            this.timeMillis = j;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Changed)) {
                return false;
            }
            Changed changed = (Changed) obj;
            return Intrinsics.areEqual(this.packageName, changed.packageName) && this.packageUid == changed.packageUid && this.timeMillis == changed.timeMillis;
        }

        @Override // com.android.systemui.common.shared.model.PackageChangeModel
        public final String getPackageName() {
            return this.packageName;
        }

        @Override // com.android.systemui.common.shared.model.PackageChangeModel
        public final int getPackageUid() {
            return this.packageUid;
        }

        public final int hashCode() {
            return Long.hashCode(this.timeMillis) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.packageUid, this.packageName.hashCode() * 31, 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Changed(packageName=");
            sb.append(this.packageName);
            sb.append(", packageUid=");
            sb.append(this.packageUid);
            sb.append(", timeMillis=");
            return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(this.timeMillis, ")", sb);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Empty implements PackageChangeModel {
        public static final Empty INSTANCE = new Empty();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Empty);
        }

        @Override // com.android.systemui.common.shared.model.PackageChangeModel
        public final String getPackageName() {
            return "";
        }

        @Override // com.android.systemui.common.shared.model.PackageChangeModel
        public final int getPackageUid() {
            return 0;
        }

        public final int hashCode() {
            return 1254368764;
        }

        public final String toString() {
            return "Empty";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Installed implements PackageChangeModel {
        public final String packageName;
        public final int packageUid;
        public final long timeMillis;

        public Installed(int i, long j, String str) {
            this.packageName = str;
            this.packageUid = i;
            this.timeMillis = j;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Installed)) {
                return false;
            }
            Installed installed = (Installed) obj;
            return Intrinsics.areEqual(this.packageName, installed.packageName) && this.packageUid == installed.packageUid && this.timeMillis == installed.timeMillis;
        }

        @Override // com.android.systemui.common.shared.model.PackageChangeModel
        public final String getPackageName() {
            return this.packageName;
        }

        @Override // com.android.systemui.common.shared.model.PackageChangeModel
        public final int getPackageUid() {
            return this.packageUid;
        }

        public final int hashCode() {
            return Long.hashCode(this.timeMillis) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.packageUid, this.packageName.hashCode() * 31, 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Installed(packageName=");
            sb.append(this.packageName);
            sb.append(", packageUid=");
            sb.append(this.packageUid);
            sb.append(", timeMillis=");
            return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(this.timeMillis, ")", sb);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Uninstalled implements PackageChangeModel {
        public final String packageName;
        public final int packageUid;
        public final long timeMillis;

        public Uninstalled(int i, long j, String str) {
            this.packageName = str;
            this.packageUid = i;
            this.timeMillis = j;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Uninstalled)) {
                return false;
            }
            Uninstalled uninstalled = (Uninstalled) obj;
            return Intrinsics.areEqual(this.packageName, uninstalled.packageName) && this.packageUid == uninstalled.packageUid && this.timeMillis == uninstalled.timeMillis;
        }

        @Override // com.android.systemui.common.shared.model.PackageChangeModel
        public final String getPackageName() {
            return this.packageName;
        }

        @Override // com.android.systemui.common.shared.model.PackageChangeModel
        public final int getPackageUid() {
            return this.packageUid;
        }

        public final int hashCode() {
            return Long.hashCode(this.timeMillis) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.packageUid, this.packageName.hashCode() * 31, 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Uninstalled(packageName=");
            sb.append(this.packageName);
            sb.append(", packageUid=");
            sb.append(this.packageUid);
            sb.append(", timeMillis=");
            return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(this.timeMillis, ")", sb);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UpdateFinished implements PackageChangeModel {
        public final String packageName;
        public final int packageUid;
        public final long timeMillis;

        public UpdateFinished(int i, long j, String str) {
            this.packageName = str;
            this.packageUid = i;
            this.timeMillis = j;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof UpdateFinished)) {
                return false;
            }
            UpdateFinished updateFinished = (UpdateFinished) obj;
            return Intrinsics.areEqual(this.packageName, updateFinished.packageName) && this.packageUid == updateFinished.packageUid && this.timeMillis == updateFinished.timeMillis;
        }

        @Override // com.android.systemui.common.shared.model.PackageChangeModel
        public final String getPackageName() {
            return this.packageName;
        }

        @Override // com.android.systemui.common.shared.model.PackageChangeModel
        public final int getPackageUid() {
            return this.packageUid;
        }

        public final int hashCode() {
            return Long.hashCode(this.timeMillis) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.packageUid, this.packageName.hashCode() * 31, 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("UpdateFinished(packageName=");
            sb.append(this.packageName);
            sb.append(", packageUid=");
            sb.append(this.packageUid);
            sb.append(", timeMillis=");
            return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(this.timeMillis, ")", sb);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UpdateStarted implements PackageChangeModel {
        public final String packageName;
        public final int packageUid;
        public final long timeMillis;

        public UpdateStarted(int i, long j, String str) {
            this.packageName = str;
            this.packageUid = i;
            this.timeMillis = j;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof UpdateStarted)) {
                return false;
            }
            UpdateStarted updateStarted = (UpdateStarted) obj;
            return Intrinsics.areEqual(this.packageName, updateStarted.packageName) && this.packageUid == updateStarted.packageUid && this.timeMillis == updateStarted.timeMillis;
        }

        @Override // com.android.systemui.common.shared.model.PackageChangeModel
        public final String getPackageName() {
            return this.packageName;
        }

        @Override // com.android.systemui.common.shared.model.PackageChangeModel
        public final int getPackageUid() {
            return this.packageUid;
        }

        public final int hashCode() {
            return Long.hashCode(this.timeMillis) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.packageUid, this.packageName.hashCode() * 31, 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("UpdateStarted(packageName=");
            sb.append(this.packageName);
            sb.append(", packageUid=");
            sb.append(this.packageUid);
            sb.append(", timeMillis=");
            return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(this.timeMillis, ")", sb);
        }
    }

    String getPackageName();

    int getPackageUid();
}
