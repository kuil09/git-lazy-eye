package hwang.gg.gitLazyEye;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class SimpleDialog extends DialogWrapper {

  private final ArrayList<JPanel> cardList = new ArrayList<>();
  private final JPanel cardLayout = new JPanel(new CardLayout());

  PropertiesComponent prop;
    Project activeProject;
    String currentBranch;
    String selectedBranch;
    String currentImagePath;
    String selectedImagePath;

  protected SimpleDialog() {
    super(true);
    init();
    setTitle("GitLazyEye");
    getOKAction().setEnabled(true);
  }

  @Nullable
  @Override
  protected JComponent createCenterPanel() {
      this.initProject();

    JPanel mainLayout = new JPanel(new GridLayout(0,1));
    JLabel label = new JLabel("Select branch for background setting: ");
    JLabel label2 = new JLabel("Select an image from disk:");

    TextFieldWithBrowseButton imageFolder = new TextFieldWithBrowseButton();
    FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();
    imageFolder.addBrowseFolderListener(new TextBrowseFolderListener(descriptor) {

      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        String current = imageFolder.getText();

          if (!current.isEmpty()) {
              fc.setCurrentDirectory(new File(current));
          }

          fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
          fc.showOpenDialog(mainLayout);

          File file = fc.getSelectedFile();
          String path = file == null ? "" : file.getAbsolutePath();

          if (!ImageUtil.validate(path)) {
              showMessageDialog(null, "Image file is not suitable. (ex: jpg|jpeg|png|gif|bmp)");
              path = "";
          } else {
              imageFolder.setText(path);
              selectedImagePath = path;
              setImagePath(selectedBranch, selectedImagePath);
          }
      }
    });

      List<String> branchList = ProjectUtil.getBranchList();

      ComboBox branches = new ComboBox(branchList.toArray(new String[0]));

      this.setCurrentBranch();
      if (this.currentBranch != null) {
          branches.setSelectedItem(this.currentBranch);
          this.currentImagePath = prop.getValue(this.currentBranch);
          imageFolder.setText(this.currentImagePath);
      }

    branches.addItemListener(e -> {
      if (e.getStateChange() == ItemEvent.SELECTED) {
          Object item = e.getItem();
          this.selectedBranch = item.toString();
          this.selectedImagePath = prop.getValue(selectedBranch);
          imageFolder.setText(selectedImagePath);
      }
    });

    this.applyCards();

      mainLayout.add(label);
      mainLayout.add(branches);
      mainLayout.add(label2);
      mainLayout.add(cardLayout);
      mainLayout.add(imageFolder);

      return mainLayout;
  }

    /**
     * Find and set activated project. It must load current(activated) project's property.
     */
    private void initProject() {
        this.activeProject = ProjectUtil.getActiveProject();
        prop = PropertiesComponent.getInstance(this.activeProject);
    }

    private void setCurrentBranch() {
        this.currentBranch = ProjectUtil.getCurrentBranchName();
    }

    private void applyCards() {
        if (cardList.isEmpty()) {
            this.addCardPanel();
        }

        for (JPanel item : cardList) {
            cardLayout.add(item);
        }
    }

    private void addCardPanel() {
        cardList.add(new JPanel());
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();
    }

    private void setImagePath(final String selectedBranch, final String selectedImagePath) {
        if (selectedBranch != null) {
            prop.setValue(selectedBranch, selectedImagePath);
        }
    }
}
