package hwang.gg.gitLazyEye;

import com.intellij.openapi.project.Project;
import git4idea.GitReference;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryManager;

import java.util.Collections;
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
    if (project == null) return false;
    List<GitRepository> gitRepositories = GitRepositoryManager.getInstance(project).getRepositories();
    return gitRepositories != null && !gitRepositories.isEmpty();
  }

  protected static String getCurrentBranchName(final Project project) {
    if (project == null) return "";
    List<GitRepository> repos = GitRepositoryManager.getInstance(project).getRepositories();
    if (repos == null || repos.isEmpty()) return "";
    String name = repos.get(0).getCurrentBranchName();
    return name == null ? "" : name;
  }

  protected static List<String> getBranchList(final Project project) {
    if (project == null) return Collections.emptyList();
    List<GitRepository> gitRepositories = GitRepositoryManager.getInstance(project).getRepositories();
    if (gitRepositories == null || gitRepositories.isEmpty()) return Collections.emptyList();
    return gitRepositories.get(0)
            .getBranches()
            .getLocalBranches()
            .stream()
            .map(GitReference::getName)
            .collect(Collectors.toList());
  }
}
