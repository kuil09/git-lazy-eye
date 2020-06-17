package hwang.gg.gitLazyEye;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.WindowManager;
import git4idea.GitReference;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryManager;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectUtil {

  private ProjectUtil() {
  }

  protected static boolean isGitProject() {
    Project project = getActiveProject();
    assert project != null;
    List<GitRepository> gitRepositories = GitRepositoryManager.getInstance(project).getRepositories();
    return !gitRepositories.isEmpty();
  }

  protected static String getCurrentBranchName() {
    return GitRepositoryManager.getInstance(getActiveProject())
            .getRepositories()
            .get(0)
            .getCurrentBranchName();
  }

  protected static List<String> getBranchList() {
    List<GitRepository> gitRepositories = GitRepositoryManager.getInstance(getActiveProject()).getRepositories();
    return gitRepositories.get(0)
            .getBranches()
            .getLocalBranches()
            .stream()
            .map(GitReference::getName)
            .collect(Collectors.toList());
  }

  protected static Project getActiveProject() {
    Project[] projects = ProjectManager.getInstance()
            .getOpenProjects();

    for (Project project : projects) {

      Window window = WindowManager.getInstance()
              .suggestParentWindow(project);

      if (window != null && window.isActive()) {
        return project;
      }
    }
    throw new RuntimeException("No active project found.");
  }
}
