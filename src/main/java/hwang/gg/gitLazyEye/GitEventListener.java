package hwang.gg.gitLazyEye;

import com.intellij.openapi.vcs.BranchChangeListener;
import org.jetbrains.annotations.NotNull;

public class GitEventListener implements BranchChangeListener {

  @Override
  public void branchWillChange(@NotNull final String branchName) {
  }

  @Override
  public void branchHasChanged(@NotNull final String branchName) {
    ImageUtil.repaint(ProjectUtil.getCurrentProject());
  }
}
