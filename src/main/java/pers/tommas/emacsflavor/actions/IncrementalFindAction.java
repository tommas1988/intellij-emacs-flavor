package pers.tommas.emacsflavor.actions;

import com.intellij.find.EditorSearchSession;
import com.intellij.find.SearchReplaceComponent;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IncrementalFindAction extends com.intellij.openapi.editor.actions.IncrementalFindAction {
    public IncrementalFindAction() {
        super();
        setupHandler(new Handler(false));
    }

    public static class Handler extends com.intellij.openapi.editor.actions.IncrementalFindAction.Handler {
        public Handler(boolean isReplace) {
            super(isReplace);
        }

        @Override
        protected void doExecute(@NotNull Editor editor, @Nullable Caret caret, DataContext dataContext) {
            super.doExecute(editor, caret, dataContext);

            SearchReplaceComponent component = EditorSearchSession.get(editor).getComponent();
            new FindCloseAction(component).registerCustomShortcutSet(CommonShortcuts.ENTER, component.getSearchTextComponent());
        }
    }
}