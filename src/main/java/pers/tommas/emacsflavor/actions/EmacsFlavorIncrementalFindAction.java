package pers.tommas.emacsflavor.actions;

import com.intellij.find.EditorSearchSession;
import com.intellij.find.SearchReplaceComponent;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actions.IncrementalFindAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EmacsFlavorIncrementalFindAction extends IncrementalFindAction {
    public EmacsFlavorIncrementalFindAction() {
        super();
        setupHandler(new Handler(false));
    }

    public static class Handler extends IncrementalFindAction.Handler {
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