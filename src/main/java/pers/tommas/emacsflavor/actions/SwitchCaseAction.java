package pers.tommas.emacsflavor.actions;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.VisualPosition;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import com.intellij.openapi.editor.actions.TextComponentEditorAction;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public class SwitchCaseAction extends TextComponentEditorAction {
    enum SwitchCase {
        upper,
        lower,
    }

    protected SwitchCaseAction(SwitchCase switchCase) {
        super(new Handler(SwitchCase.lower == switchCase));
    }

    public static class Handler extends EditorWriteActionHandler {
        private final boolean toLowerCase;

        public Handler(boolean toLowerCase) {
            this.toLowerCase = toLowerCase;
        }

        @Override
        public void executeWriteAction(final Editor editor, @Nullable Caret caret, DataContext dataContext) {
            runForCaret(editor, caret, c -> {
                VisualPosition caretPosition = c.getVisualPosition();
                int selectionStartOffset = c.getSelectionStart();
                int selectionEndOffset = c.getSelectionEnd();
                String originalText = editor.getDocument().getText(new TextRange(selectionStartOffset, selectionEndOffset));
                editor.getDocument().replaceString(selectionStartOffset, selectionEndOffset,
                        toLowerCase ? originalText.toLowerCase(Locale.ROOT) : originalText.toUpperCase(Locale.ROOT));
                c.moveToVisualPosition(caretPosition);
            });
        }

        private static void runForCaret(Editor editor, Caret caret, CaretAction action) {
            if (caret == null) {
                editor.getCaretModel().runForEachCaret(action);
            }
            else {
                action.perform(caret);
            }
        }
    }
}
