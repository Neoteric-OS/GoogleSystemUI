package com.android.systemui.statusbar.commandline;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CommandParser {
    public final List _flags;
    public final List _params;
    public final List _subCommands;
    public final List flags;
    public final List params;
    public final List subCommands;
    public final Set tokenSet;

    public CommandParser() {
        ArrayList arrayList = new ArrayList();
        this._flags = arrayList;
        this.flags = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this._params = arrayList2;
        this.params = arrayList2;
        ArrayList arrayList3 = new ArrayList();
        this._subCommands = arrayList3;
        this.subCommands = arrayList3;
        this.tokenSet = new LinkedHashSet();
    }

    public final String checkCliNames(String str, String str2) {
        if (str != null && this.tokenSet.contains(str)) {
            return str;
        }
        if (this.tokenSet.contains(str2)) {
            return str2;
        }
        return null;
    }

    public final List getUnhandledParams() {
        List list = this.params;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            Param param = (Param) obj;
            if ((param instanceof SingleArgParam) && !((SingleArgParam) param).handled) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final List getUnhandledSubCmds() {
        List<OptionalSubCommand> list = this.subCommands;
        ArrayList arrayList = new ArrayList();
        for (OptionalSubCommand optionalSubCommand : list) {
        }
        return arrayList;
    }

    public final boolean parse(List list) {
        Object obj;
        boolean z;
        Object obj2;
        Object obj3;
        boolean validateRequiredParams;
        Object obj4;
        boolean z2;
        Object obj5;
        if (list.isEmpty()) {
            return false;
        }
        ListIterator listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String str = (String) listIterator.next();
            Iterator it = this.flags.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((Flag) obj).matches(str)) {
                    break;
                }
            }
            Flag flag = (Flag) obj;
            boolean z3 = true;
            if (flag != null) {
                flag.inner = true;
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                Iterator it2 = this.params.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        obj2 = null;
                        break;
                    }
                    obj2 = it2.next();
                    if (((Param) obj2).matches(str)) {
                        break;
                    }
                }
                Param param = (Param) obj2;
                if (param != null) {
                    param.parseArgsFromIter(listIterator);
                    z = true;
                }
                if (z) {
                    continue;
                } else {
                    Iterator it3 = this.subCommands.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            obj3 = null;
                            break;
                        }
                        obj3 = it3.next();
                        if (((OptionalSubCommand) obj3).matches(str)) {
                            break;
                        }
                    }
                    OptionalSubCommand optionalSubCommand = (OptionalSubCommand) obj3;
                    if (optionalSubCommand != null) {
                        CommandParser commandParser = optionalSubCommand.cmd.parser;
                        if (commandParser.flags.isEmpty() && commandParser.params.isEmpty()) {
                            validateRequiredParams = commandParser.validateRequiredParams();
                        } else {
                            while (true) {
                                if (!listIterator.hasNext()) {
                                    break;
                                }
                                String str2 = (String) listIterator.next();
                                Iterator it4 = commandParser.flags.iterator();
                                while (true) {
                                    if (!it4.hasNext()) {
                                        obj4 = null;
                                        break;
                                    }
                                    obj4 = it4.next();
                                    if (((Flag) obj4).matches(str2)) {
                                        break;
                                    }
                                }
                                Flag flag2 = (Flag) obj4;
                                if (flag2 != null) {
                                    flag2.inner = true;
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                if (!z2) {
                                    Iterator it5 = commandParser.params.iterator();
                                    while (true) {
                                        if (!it5.hasNext()) {
                                            obj5 = null;
                                            break;
                                        }
                                        obj5 = it5.next();
                                        if (((Param) obj5).matches(str2)) {
                                            break;
                                        }
                                    }
                                    Param param2 = (Param) obj5;
                                    if (param2 != null) {
                                        param2.parseArgsFromIter(listIterator);
                                        z2 = true;
                                    }
                                    if (!z2) {
                                        listIterator.previous();
                                        break;
                                    }
                                }
                            }
                            validateRequiredParams = commandParser.validateRequiredParams();
                        }
                        optionalSubCommand.validationStatus = validateRequiredParams;
                        optionalSubCommand.isPresent = true;
                    } else {
                        z3 = z;
                    }
                    if (!z3) {
                        throw new ArgParseError(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Unknown token: ", str));
                    }
                }
            }
        }
        return validateRequiredParams();
    }

    public final boolean validateRequiredParams() {
        if (getUnhandledParams().isEmpty() && getUnhandledSubCmds().isEmpty()) {
            List list = this.subCommands;
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (!((OptionalSubCommand) obj).validationStatus) {
                    arrayList.add(obj);
                }
            }
            if (arrayList.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
