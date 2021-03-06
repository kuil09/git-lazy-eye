package hwang.gg.gitLazyEye;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.DocumentAdapter;
import hwang.gg.gitLazyEye.property.FillType;
import hwang.gg.gitLazyEye.property.ImageProperty;
import hwang.gg.gitLazyEye.property.Opacity;
import hwang.gg.gitLazyEye.property.PlacementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.List;
import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

public class SimpleDialog extends DialogWrapper {

  List<String> branchList;
  ComboBox branchCombo;
  final JSlider opacitySlider = new JSlider(JSlider.HORIZONTAL, Opacity.MIN, Opacity.MAX, 0);
  final ComboBox fillTypeCombo = new ComboBox(FillType.values());
  final ComboBox placementTypeCombo = new ComboBox(PlacementType.values());

  PropertiesComponent prop;
  String currentBranch;

  ImageProperty imageProperty = new ImageProperty("",
          100, FillType.PLAIN, PlacementType.CENTER);

  protected SimpleDialog() {
    super(true);
    init();
    setTitle("GitLazyEye");
    setCancelButtonText("Clear");
    getCancelAction().setEnabled(true);
    getOKAction().setEnabled(true);
  }

  @Nullable
  @Override
  protected JComponent createCenterPanel() {
    this.initProject();

    JPanel mainLayout = new JPanel(new GridLayout(0, 1));
    JLabel labelBranch = new JLabel("Select branch for background setting: ");
    JLabel labelImage = new JLabel("Select an image from disk(or paste image URL): ");
    JLabel labelOpacity = new JLabel("Select opacity (invisible to visible): ");
    JLabel labelFillType = new JLabel("Select fill type:");
    JLabel labelPlacement = new JLabel("Select placement:");

    TextFieldWithBrowseButton imageFolder = new TextFieldWithBrowseButton();
    this.initBranchCombo(imageFolder);
    this.initImageFolderChooser(mainLayout, imageFolder);
    this.initOpacitySlider();
    this.initFillTypeCombo();
    this.initPlacementTypeCombo();

    mainLayout.add(labelBranch);
    mainLayout.add(branchCombo);

    mainLayout.add(labelImage);
    mainLayout.add(imageFolder);

    mainLayout.add(labelOpacity);
    mainLayout.add(opacitySlider);

    mainLayout.add(labelFillType);
    mainLayout.add(fillTypeCombo);

    mainLayout.add(labelPlacement);
    mainLayout.add(placementTypeCombo);

    return mainLayout;
  }

  private void initOpacitySlider() {
    this.opacitySlider.setValue(imageProperty.getOpacity());
    this.opacitySlider.addChangeListener(e -> {
      imageProperty.setOpacity(this.opacitySlider.getValue());
      setImageProperty();
    });
  }

  private void initFillTypeCombo() {
    this.fillTypeCombo.setSelectedItem(imageProperty.getFillType());
    this.fillTypeCombo.addItemListener(e -> {
      imageProperty.setFillType((FillType) this.fillTypeCombo.getSelectedItem());
      setImageProperty();
    });
  }

  private void initPlacementTypeCombo() {
    this.placementTypeCombo.setSelectedItem(imageProperty.getPlacementType());
    this.placementTypeCombo.addItemListener(e -> {
      imageProperty.setPlacementType((PlacementType) this.placementTypeCombo.getSelectedItem());
      setImageProperty();
    });
  }

  private void initBranchCombo(TextFieldWithBrowseButton imageFolder) {
    this.setCurrentBranch();

    this.branchList = ProjectUtil.getBranchList(ProjectUtil.getCurrentProject());
    this.branchCombo = new ComboBox(branchList.toArray(new String[0]));

    if (this.imageProperty.getImagePath() != null) {
      branchCombo.setSelectedItem(this.currentBranch);
      imageFolder.setText(this.imageProperty.getImagePath());
    }

    branchCombo.addItemListener(e -> {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        var selectedBranch = Objects.requireNonNull(this.branchCombo.getSelectedItem()).toString();
        this.setCurrentBranch(selectedBranch);

        imageFolder.setText(this.imageProperty.getImagePath());
        this.opacitySlider.setValue(this.imageProperty.getOpacity());
        this.fillTypeCombo.setSelectedItem(this.imageProperty.getFillType());
        this.placementTypeCombo.setSelectedItem(this.imageProperty.getPlacementType());

        this.setImageProperty();
      }
    });
  }

  private void initImageFolderChooser(final JPanel mainLayout,
                                      final TextFieldWithBrowseButton imageFolder) {
    FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();

    imageFolder.getTextField().getDocument().addDocumentListener(new DocumentAdapter() {
      @Override
      protected void textChanged(@NotNull DocumentEvent e) {
        var path = imageFolder.getTextField().getText();
        imageProperty.setImagePath(path);
        setImageProperty(Objects.requireNonNull(currentBranch));
      }
    });


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

        setImagePath(path, imageFolder);
      }
    });
  }

  private void setImagePath(String path, TextFieldWithBrowseButton imageFolder) {
    if (!ImageUtil.validate(path)) {
      showMessageDialog(null, "Image file is not suitable. (ex: jpg|jpeg|png|gif|bmp or valid image url)");
    } else {
      imageFolder.setText(path);
      imageProperty.setImagePath(path);
      setImageProperty(Objects.requireNonNull(branchCombo.getSelectedItem()).toString());
    }
  }

  /**
   * Find and set activated project. It must load current(activated) project's property.
   */
  private void initProject() {
    prop = PropertiesComponent.getInstance(ProjectUtil.getCurrentProject());
  }

  private void setCurrentBranch() {
    this.currentBranch = ProjectUtil.getCurrentBranchName(ProjectUtil.getCurrentProject());
    String props = prop.getValue(this.currentBranch);
    this.imageProperty = new ImageProperty(props);
  }

  private void setCurrentBranch(String currentBranch) {
    this.currentBranch = currentBranch;
    String props = prop.getValue(this.currentBranch);
    this.imageProperty = new ImageProperty(props);
  }

  @Override
  protected void doOKAction() {
    super.doOKAction();
    var currentProject = ProjectUtil.getCurrentProject();
    ImageUtil.repaint(currentProject);
  }

  @Override
  public void doCancelAction() {
    super.doCancelAction();
    var currentProject = ProjectUtil.getCurrentProject();
    this.imageProperty = ImageProperty.DEFAULT;
    this.setImageProperty();
    ImageUtil.repaint(currentProject);
  }

  private void setImageProperty() {
    this.setImageProperty(this.currentBranch);
  }

  private void setImageProperty(final String selectedBranch) {
    if (selectedBranch != null) {
      prop.setValue(selectedBranch, imageProperty.toString());
    }
  }
}
