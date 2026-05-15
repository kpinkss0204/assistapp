package androidx.compose.foundation.text;

import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.PropertyReference1Impl;

/* JADX INFO: compiled from: KeyMapping.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u001a\u001c\u0010\u0004\u001a\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\t"}, d2 = {"defaultKeyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "getDefaultKeyMapping", "()Landroidx/compose/foundation/text/KeyMapping;", "commonKeyMapping", "shortcutModifier", "Lkotlin/Function1;", "Landroidx/compose/ui/input/key/KeyEvent;", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
public final class KeyMappingKt {
    private static final KeyMapping defaultKeyMapping;

    public static final KeyMapping commonKeyMapping(final Function1<? super KeyEvent, Boolean> function1) {
        return new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt.commonKeyMapping.1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* JADX INFO: renamed from: map-ZmokQxo */
            public KeyCommand mo1281mapZmokQxo(android.view.KeyEvent event) {
                if (function1.invoke(KeyEvent.m5615boximpl(event)).booleanValue() && KeyEvent_androidKt.m5638isShiftPressedZmokQxo(event)) {
                    if (Key.m5324equalsimpl0(KeyEvent_androidKt.m5632getKeyZmokQxo(event), MappedKeys.INSTANCE.m1335getZEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    return null;
                }
                if (function1.invoke(KeyEvent.m5615boximpl(event)).booleanValue()) {
                    long jM5632getKeyZmokQxo = KeyEvent_androidKt.m5632getKeyZmokQxo(event);
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo, MappedKeys.INSTANCE.m1315getCEK5gGoQ()) ? true : Key.m5324equalsimpl0(jM5632getKeyZmokQxo, MappedKeys.INSTANCE.m1325getInsertEK5gGoQ())) {
                        return KeyCommand.COPY;
                    }
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo, MappedKeys.INSTANCE.m1332getVEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo, MappedKeys.INSTANCE.m1333getXEK5gGoQ())) {
                        return KeyCommand.CUT;
                    }
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo, MappedKeys.INSTANCE.m1312getAEK5gGoQ())) {
                        return KeyCommand.SELECT_ALL;
                    }
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo, MappedKeys.INSTANCE.m1334getYEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo, MappedKeys.INSTANCE.m1335getZEK5gGoQ())) {
                        return KeyCommand.UNDO;
                    }
                    return null;
                }
                if (KeyEvent_androidKt.m5636isCtrlPressedZmokQxo(event)) {
                    return null;
                }
                if (KeyEvent_androidKt.m5638isShiftPressedZmokQxo(event)) {
                    long jM5632getKeyZmokQxo2 = KeyEvent_androidKt.m5632getKeyZmokQxo(event);
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1320getDirectionLeftEK5gGoQ())) {
                        return KeyCommand.SELECT_LEFT_CHAR;
                    }
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1321getDirectionRightEK5gGoQ())) {
                        return KeyCommand.SELECT_RIGHT_CHAR;
                    }
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1322getDirectionUpEK5gGoQ())) {
                        return KeyCommand.SELECT_UP;
                    }
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1319getDirectionDownEK5gGoQ())) {
                        return KeyCommand.SELECT_DOWN;
                    }
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1329getPageUpEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_UP;
                    }
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1328getPageDownEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_DOWN;
                    }
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1327getMoveHomeEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_START;
                    }
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1326getMoveEndEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_END;
                    }
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1325getInsertEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    return null;
                }
                long jM5632getKeyZmokQxo3 = KeyEvent_androidKt.m5632getKeyZmokQxo(event);
                if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1320getDirectionLeftEK5gGoQ())) {
                    return KeyCommand.LEFT_CHAR;
                }
                if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1321getDirectionRightEK5gGoQ())) {
                    return KeyCommand.RIGHT_CHAR;
                }
                if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1322getDirectionUpEK5gGoQ())) {
                    return KeyCommand.UP;
                }
                if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1319getDirectionDownEK5gGoQ())) {
                    return KeyCommand.DOWN;
                }
                if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1329getPageUpEK5gGoQ())) {
                    return KeyCommand.PAGE_UP;
                }
                if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1328getPageDownEK5gGoQ())) {
                    return KeyCommand.PAGE_DOWN;
                }
                if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1327getMoveHomeEK5gGoQ())) {
                    return KeyCommand.LINE_START;
                }
                if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1326getMoveEndEK5gGoQ())) {
                    return KeyCommand.LINE_END;
                }
                if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1323getEnterEK5gGoQ())) {
                    return KeyCommand.NEW_LINE;
                }
                if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1314getBackspaceEK5gGoQ())) {
                    return KeyCommand.DELETE_PREV_CHAR;
                }
                if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1318getDeleteEK5gGoQ())) {
                    return KeyCommand.DELETE_NEXT_CHAR;
                }
                if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1330getPasteEK5gGoQ())) {
                    return KeyCommand.PASTE;
                }
                if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1317getCutEK5gGoQ())) {
                    return KeyCommand.CUT;
                }
                if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1316getCopyEK5gGoQ())) {
                    return KeyCommand.COPY;
                }
                if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1331getTabEK5gGoQ())) {
                    return KeyCommand.TAB;
                }
                return null;
            }
        };
    }

    public static final KeyMapping getDefaultKeyMapping() {
        return defaultKeyMapping;
    }

    static {
        final KeyMapping keyMappingCommonKeyMapping = commonKeyMapping(new PropertyReference1Impl() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return Boolean.valueOf(KeyEvent_androidKt.m5636isCtrlPressedZmokQxo(((KeyEvent) obj).m5621unboximpl()));
            }
        });
        defaultKeyMapping = new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$2$1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* JADX INFO: renamed from: map-ZmokQxo */
            public KeyCommand mo1281mapZmokQxo(android.view.KeyEvent event) {
                KeyCommand keyCommand = null;
                if (KeyEvent_androidKt.m5638isShiftPressedZmokQxo(event) && KeyEvent_androidKt.m5636isCtrlPressedZmokQxo(event)) {
                    long jM5632getKeyZmokQxo = KeyEvent_androidKt.m5632getKeyZmokQxo(event);
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo, MappedKeys.INSTANCE.m1320getDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LEFT_WORD;
                    } else if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo, MappedKeys.INSTANCE.m1321getDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_RIGHT_WORD;
                    } else if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo, MappedKeys.INSTANCE.m1322getDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_PREV_PARAGRAPH;
                    } else if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo, MappedKeys.INSTANCE.m1319getDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_NEXT_PARAGRAPH;
                    }
                } else if (KeyEvent_androidKt.m5636isCtrlPressedZmokQxo(event)) {
                    long jM5632getKeyZmokQxo2 = KeyEvent_androidKt.m5632getKeyZmokQxo(event);
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1320getDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.LEFT_WORD;
                    } else if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1321getDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.RIGHT_WORD;
                    } else if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1322getDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.PREV_PARAGRAPH;
                    } else if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1319getDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.NEXT_PARAGRAPH;
                    } else if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1324getHEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_CHAR;
                    } else if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1318getDeleteEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_NEXT_WORD;
                    } else if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1314getBackspaceEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_WORD;
                    } else if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo2, MappedKeys.INSTANCE.m1313getBackslashEK5gGoQ())) {
                        keyCommand = KeyCommand.DESELECT;
                    }
                } else if (KeyEvent_androidKt.m5638isShiftPressedZmokQxo(event)) {
                    long jM5632getKeyZmokQxo3 = KeyEvent_androidKt.m5632getKeyZmokQxo(event);
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1327getMoveHomeEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_LEFT;
                    } else if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo3, MappedKeys.INSTANCE.m1326getMoveEndEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_RIGHT;
                    }
                } else if (KeyEvent_androidKt.m5635isAltPressedZmokQxo(event)) {
                    long jM5632getKeyZmokQxo4 = KeyEvent_androidKt.m5632getKeyZmokQxo(event);
                    if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo4, MappedKeys.INSTANCE.m1314getBackspaceEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_FROM_LINE_START;
                    } else if (Key.m5324equalsimpl0(jM5632getKeyZmokQxo4, MappedKeys.INSTANCE.m1318getDeleteEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_TO_LINE_END;
                    }
                }
                return keyCommand == null ? keyMappingCommonKeyMapping.mo1281mapZmokQxo(event) : keyCommand;
            }
        };
    }
}
