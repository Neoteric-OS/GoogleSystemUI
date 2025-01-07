package androidx.profileinstaller;

import android.content.pm.PackageInfo;
import android.util.Log;
import com.android.app.viewcapture.data.ViewNode;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ProfileInstaller {
    public static final AnonymousClass1 EMPTY_DIAGNOSTICS;
    public static final AnonymousClass1 LOG_DIAGNOSTICS = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface DiagnosticsCallback {
        void onDiagnosticReceived();

        void onResultReceived(int i, Object obj);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.profileinstaller.ProfileInstaller$1] */
    static {
        final int i = 0;
        EMPTY_DIAGNOSTICS = new DiagnosticsCallback() { // from class: androidx.profileinstaller.ProfileInstaller.1
            @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
            public final void onDiagnosticReceived() {
                switch (i) {
                    case 0:
                        break;
                    default:
                        Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
                        break;
                }
            }

            @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
            public final void onResultReceived(int i2, Object obj) {
                String str;
                switch (i) {
                    case 0:
                        break;
                    default:
                        switch (i2) {
                            case 1:
                                str = "RESULT_INSTALL_SUCCESS";
                                break;
                            case 2:
                                str = "RESULT_ALREADY_INSTALLED";
                                break;
                            case 3:
                                str = "RESULT_UNSUPPORTED_ART_VERSION";
                                break;
                            case 4:
                                str = "RESULT_NOT_WRITABLE";
                                break;
                            case 5:
                                str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                                break;
                            case 6:
                                str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                                break;
                            case 7:
                                str = "RESULT_IO_EXCEPTION";
                                break;
                            case 8:
                                str = "RESULT_PARSE_EXCEPTION";
                                break;
                            case 9:
                            default:
                                str = "";
                                break;
                            case 10:
                                str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                                break;
                            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                                str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                                break;
                        }
                        if (i2 != 6 && i2 != 7 && i2 != 8) {
                            Log.d("ProfileInstaller", str);
                            break;
                        } else {
                            Log.e("ProfileInstaller", str, (Throwable) obj);
                            break;
                        }
                        break;
                }
            }

            private final void onDiagnosticReceived$androidx$profileinstaller$ProfileInstaller$1() {
            }

            private final void onResultReceived$androidx$profileinstaller$ProfileInstaller$1(int i2, Object obj) {
            }
        };
        final int i2 = 1;
        new DiagnosticsCallback() { // from class: androidx.profileinstaller.ProfileInstaller.1
            @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
            public final void onDiagnosticReceived() {
                switch (i2) {
                    case 0:
                        break;
                    default:
                        Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
                        break;
                }
            }

            @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
            public final void onResultReceived(int i22, Object obj) {
                String str;
                switch (i2) {
                    case 0:
                        break;
                    default:
                        switch (i22) {
                            case 1:
                                str = "RESULT_INSTALL_SUCCESS";
                                break;
                            case 2:
                                str = "RESULT_ALREADY_INSTALLED";
                                break;
                            case 3:
                                str = "RESULT_UNSUPPORTED_ART_VERSION";
                                break;
                            case 4:
                                str = "RESULT_NOT_WRITABLE";
                                break;
                            case 5:
                                str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                                break;
                            case 6:
                                str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                                break;
                            case 7:
                                str = "RESULT_IO_EXCEPTION";
                                break;
                            case 8:
                                str = "RESULT_PARSE_EXCEPTION";
                                break;
                            case 9:
                            default:
                                str = "";
                                break;
                            case 10:
                                str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                                break;
                            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                                str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                                break;
                        }
                        if (i22 != 6 && i22 != 7 && i22 != 8) {
                            Log.d("ProfileInstaller", str);
                            break;
                        } else {
                            Log.e("ProfileInstaller", str, (Throwable) obj);
                            break;
                        }
                        break;
                }
            }

            private final void onDiagnosticReceived$androidx$profileinstaller$ProfileInstaller$1() {
            }

            private final void onResultReceived$androidx$profileinstaller$ProfileInstaller$1(int i22, Object obj) {
            }
        };
    }

    public static void noteProfileWrittenFor(PackageInfo packageInfo, File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat")));
            try {
                dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                dataOutputStream.close();
            } finally {
            }
        } catch (IOException unused) {
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(28:0|1|2|3|4|(4:6|(9:13|14|15|16|17|18|(1:20)(1:23)|(1:22)|(2:10|11))|8|(0))|36|(2:38|(5:40|41|(1:47)(1:44)|45|46))(3:258|259|(6:261|41|(0)|47|45|46))|48|49|50|51|52|53|(3:210|211|(4:213|214|215|216)(2:220|221))|55|(3:177|178|(3:185|186|(4:188|189|190|(1:184))(2:191|192))(3:(1:181)|182|(0)))|57|(2:59|(5:63|64|65|66|(2:68|69)(3:70|71|72))(2:61|62))|88|(3:93|94|(11:98|99|100|101|102|103|104|105|(3:109|110|(13:112|(2:113|(1:115)(1:116))|117|118|119|120|121|122|(1:92)|(0)|47|45|46))|107|108)(2:96|97))|90|(0)|(0)|47|45|46|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:253:0x00eb, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x00ec, code lost:
    
        r20.onResultReceived(6, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x00f0, code lost:
    
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x00e4, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x00e5, code lost:
    
        r20.onResultReceived(7, r0);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0156 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x00f9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x02ba A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0208  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void writeProfile(android.content.Context r18, java.util.concurrent.Executor r19, androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback r20, boolean r21) {
        /*
            Method dump skipped, instructions count: 718
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.profileinstaller.ProfileInstaller.writeProfile(android.content.Context, java.util.concurrent.Executor, androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback, boolean):void");
    }
}
