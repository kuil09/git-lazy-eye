package hwang.gg.gitLazyEye;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.BranchChangeListener;
import org.jetbrains.annotations.NotNull;

public class GitEventListener implements BranchChangeListener {

  private final Project project;

  public GitEventListener(@NotNull Project project) {
    this.project = project;
  }

  @Override
  public void branchWillChange(@NotNull final String branchName) {
  }

  @Override
  public void branchHasChanged(@NotNull final String branchName) {
    ImageUtil.repaint(project);
  }
}
