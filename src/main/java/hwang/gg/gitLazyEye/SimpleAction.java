package hwang.gg.gitLazyEye;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class SimpleAction extends AnAction {

  @Override
  public void update(@NotNull AnActionEvent e) {
    super.update(e);
    e.getPresentation().setEnabled(false);
    if (ProjectUtil.isGitProject()) {
      e.getPresentation().setEnabled(true);
    }
  }

  @Override
  public void actionPerformed(@NotNull final AnActionEvent e) {
    new SimpleDialog().showAndGet();
  }
}
