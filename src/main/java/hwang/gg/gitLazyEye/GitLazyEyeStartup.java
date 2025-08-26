package hwang.gg.gitLazyEye;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.vcs.BranchChangeListener;
import org.jetbrains.annotations.NotNull;

public class GitLazyEyeStartup implements StartupActivity {
  @Override
  public void runActivity(@NotNull Project project) {
    project
        .getMessageBus()
        .connect(project)
        .subscribe(BranchChangeListener.VCS_BRANCH_CHANGED, new GitEventListener(project));
  }
}