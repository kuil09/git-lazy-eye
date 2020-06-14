package hwang.gg.gitLazyEye;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.vcs.BranchChangeListener;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import org.jetbrains.annotations.NotNull;

public class GitEventListener implements BranchChangeListener {

  @Override
  public void branchWillChange(@NotNull final String branchName) {
  }

  @Override
  public void branchHasChanged(@NotNull final String branchName) {
    PropertiesComponent prop = PropertiesComponent.getInstance(ProjectUtil.getActiveProject());
    String imagePath = prop.getValue(branchName);
    prop.setValue(IdeBackgroundUtil.EDITOR_PROP, imagePath);
    IdeBackgroundUtil.repaintAllWindows();
  }
}
