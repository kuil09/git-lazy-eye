package hwang.gg.gitLazyEye;

import com.intellij.openapi.project.Project;
import git4idea.GitReference;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryManager;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectUtil {

  static Project project;

  static void setCurrentProject(final Project currentProject) {
    project = currentProject;
  }

  static Project getCurrentProject() {
    return project;
  }

  protected static boolean isGitProject(final Project project) {
    assert project != null;
    List<GitRepository> gitRepositories = GitRepositoryManager.getInstance(project).getRepositories();
    return !gitRepositories.isEmpty();
  }

  protected static String getCurrentBranchName(final Project project) {
    return GitRepositoryManager.getInstance(project)
            .getRepositories()
            .get(0)
            .getCurrentBranchName();
  }

  protected static List<String> getBranchList(final Project project) {
    List<GitRepository> gitRepositories = GitRepositoryManager.getInstance(project).getRepositories();
    return gitRepositories.get(0)
            .getBranches()
            .getLocalBranches()
            .stream()
            .map(GitReference::getName)
            .collect(Collectors.toList());
  }
}
