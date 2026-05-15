package gui;

import processor.FileOrganizer;
import processor.FileProcessor;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.File;
import java.util.*;

public class OrganizerGUI extends JFrame {

    // =========================================
    // COMPONENTS
    // =========================================

    private JTextField folderField;

    private JLabel selectedFolderLabel;

    private JButton browseButton;
    private JButton organizeButton;
    private JButton addCategoryButton;

    private JPanel categoryContainer;

    private File selectedFolder;



    // =========================================
    // CATEGORY DATA
    // =========================================

    private HashMap<String, HashSet<String>>
            categoryExtensions =
            new HashMap<>();



    private HashSet<String>
            usedExtensions =
            new HashSet<>();



    // =========================================
    // CONSTRUCTOR
    // =========================================

    public OrganizerGUI() {

        setTitle("Smart Media Organizer");

        setSize(1200, 750);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());



        BackgroundPanel background =
                new BackgroundPanel();

        background.setLayout(
                new GridBagLayout()
        );

        setContentPane(background);



        initializeDefaultCategories();

        initializeUI();
    }



    // =========================================
    // DEFAULT CATEGORIES
    // =========================================

    private void initializeDefaultCategories() {

        addDefaultCategory(
                "Photos",
                new String[]{
                        "jpg",
                        "jpeg",
                        "png",
                        "gif",
                        "bmp",
                        "webp"
                }
        );



        addDefaultCategory(
                "Videos",
                new String[]{
                        "mp4",
                        "mkv",
                        "avi",
                        "mov"
                }
        );



        addDefaultCategory(
                "Documents",
                new String[]{
                        "pdf",
                        "docx",
                        "txt",
                        "pptx",
                        "xlsx"
                }
        );



        addDefaultCategory(
                "Music",
                new String[]{
                        "mp3",
                        "wav",
                        "aac"
                }
        );



        addDefaultCategory(
                "Unorganized",
                new String[]{}
        );
    }



    // =========================================
    // ADD DEFAULT CATEGORY
    // =========================================

    private void addDefaultCategory(
            String categoryName,
            String[] extensions
    ) {

        HashSet<String> extSet =
                new HashSet<>();



        for(String ext : extensions) {

            ext = ext.toLowerCase();

            extSet.add(ext);

            usedExtensions.add(ext);
        }



        categoryExtensions.put(
                categoryName,
                extSet
        );
    }



    // =========================================
    // UI
    // =========================================

    private void initializeUI() {

        JPanel mainCard =
                new JPanel();

        mainCard.setPreferredSize(
                new Dimension(850, 550)
        );

        mainCard.setBackground(
                new Color(248,245,240)
        );

        mainCard.setLayout(
                new GridBagLayout()
        );



        mainCard.setBorder(

                new CompoundBorder(

                        new LineBorder(
                                new Color(
                                        220,
                                        220,
                                        220
                                ),
                                1,
                                true
                        ),

                        new EmptyBorder(
                                35,
                                45,
                                35,
                                45
                        )
                )
        );



        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        10,
                        10,
                        10,
                        10
                );

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;



        // TITLE

        JLabel title =
                new JLabel(
                        "SMART MEDIA ORGANIZER"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30
                )
        );



        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;

        mainCard.add(title, gbc);



        // SUBTITLE

        JLabel subtitle =
                new JLabel(
                        "Organize your digital space peacefully."
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        16
                )
        );



        gbc.gridy = 1;

        mainCard.add(subtitle, gbc);



        // SELECT FOLDER

        JLabel folderTitle =
                new JLabel(
                        "SELECT FOLDER:"
                );

        folderTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );



        gbc.gridy = 2;

        mainCard.add(folderTitle, gbc);



        // FOLDER PANEL

        JPanel folderPanel =
                new JPanel();

        folderPanel.setOpaque(false);

        folderPanel.setLayout(
                new BorderLayout(
                        10,
                        0
                )
        );



        folderField =
                new JTextField();

        folderField.setPreferredSize(
                new Dimension(
                        500,
                        40
                )
        );



        browseButton =
                createStyledButton(
                        "Browse"
                );



        // BROWSE ACTION

        browseButton.addActionListener(e -> {

            JFileChooser chooser =
                    new JFileChooser();

            chooser.setFileSelectionMode(
                    JFileChooser.DIRECTORIES_ONLY
            );



            int result =
                    chooser.showOpenDialog(
                            null
                    );



            if(result ==
                    JFileChooser.APPROVE_OPTION) {

                selectedFolder =
                        chooser.getSelectedFile();

                folderField.setText(
                        selectedFolder.getAbsolutePath()
                );



                selectedFolderLabel.setText(
                        "Selected Folder: "
                                + selectedFolder.getName()
                );
            }
        });



        folderPanel.add(
                folderField,
                BorderLayout.CENTER
        );

        folderPanel.add(
                browseButton,
                BorderLayout.EAST
        );



        gbc.gridy = 3;

        mainCard.add(folderPanel, gbc);



        // SELECTED LABEL

        selectedFolderLabel =
                new JLabel(
                        "Selected Folder: None"
                );



        gbc.gridy = 4;

        mainCard.add(
                selectedFolderLabel,
                gbc
        );



        // ORGANIZE TITLE

        JLabel organizeTitle =
                new JLabel(
                        "ORGANIZE AS:"
                );

        organizeTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );



        gbc.gridy = 5;

        mainCard.add(
                organizeTitle,
                gbc
        );



        // CATEGORY GRID

        categoryContainer =
                new JPanel();

        categoryContainer.setOpaque(false);



        categoryContainer.setLayout(

                new GridLayout(
                        0,
                        3,
                        25,
                        20
                )
        );



        for(String category :
                categoryExtensions.keySet()) {

            categoryContainer.add(
                    createCategoryItem(category)
            );
        }



        gbc.gridy = 6;

        mainCard.add(
                categoryContainer,
                gbc
        );



        // ADD CATEGORY BUTTON

        addCategoryButton =
                new JButton(
                        "+ Add Category"
                );

        addCategoryButton.setFocusPainted(false);

        addCategoryButton.setBackground(
                new Color(
                        236,
                        125,
                        102
                )
        );

        addCategoryButton.setForeground(
                Color.WHITE
        );



        addCategoryButton.addActionListener(e -> {

            showAddCategoryDialog();
        });



        gbc.gridy = 7;
        gbc.gridwidth = 1;

        mainCard.add(
                addCategoryButton,
                gbc
        );



        // ORGANIZE BUTTON

        organizeButton =
                createStyledButton(
                        "ORGANIZE FILES"
                );



        organizeButton.addActionListener(e -> {

            if(selectedFolder == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a folder first."
                );

                return;
            }



            FileProcessor.operationLogs.clear();



            FileOrganizer organizer =
                    new FileOrganizer(
                            categoryExtensions
                    );



            organizer.organizeFiles(
                    selectedFolder
            );



            try {

                Thread.sleep(500);

            }

            catch(Exception ex) {

                ex.printStackTrace();
            }



            if(FileProcessor.operationLogs.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "No files found in selected folder.",
                        "Organization Summary",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }



            StringBuilder summary =
                    new StringBuilder();

            summary.append(
                    "Organization Summary\n\n"
            );



            for(String log :
                    FileProcessor.operationLogs) {

                summary.append(log)
                        .append("\n");
            }



            JOptionPane.showMessageDialog(
                    this,
                    summary.toString(),
                    "Organization Summary",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });



        JPanel buttonPanel =
                new JPanel();

        buttonPanel.setOpaque(false);

        buttonPanel.add(
                organizeButton
        );



        gbc.gridy = 8;
        gbc.gridwidth = 3;
        gbc.anchor =
                GridBagConstraints.CENTER;

        mainCard.add(
                buttonPanel,
                gbc
        );



        add(mainCard);
    }



    // =========================================
    // CATEGORY ITEM
    // =========================================

    private JPanel createCategoryItem(
            String categoryName
    ) {

        JPanel panel =
                new JPanel();

        panel.setOpaque(false);



        panel.setLayout(

                new FlowLayout(
                        FlowLayout.LEFT,
                        5,
                        0
                )
        );



        JCheckBox checkBox =
                new JCheckBox();

        checkBox.setSelected(true);

        checkBox.setOpaque(false);



        JLabel label =
                new JLabel(categoryName);

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );



        JButton editButton =
                new JButton("✎");

        editButton.setBorderPainted(false);

        editButton.setContentAreaFilled(false);

        editButton.setFocusPainted(false);



        editButton.addActionListener(e -> {

            showEditExtensionsDialog(
                    categoryName
            );
        });



        panel.add(checkBox);

        panel.add(label);

        panel.add(editButton);



        boolean isDefaultCategory =

                categoryName.equals("Photos")
                        || categoryName.equals("Videos")
                        || categoryName.equals("Documents")
                        || categoryName.equals("Music")
                        || categoryName.equals("Unorganized");



        if(!isDefaultCategory) {

            JButton deleteButton =
                    new JButton("🗑");



            deleteButton.setBorderPainted(false);

            deleteButton.setContentAreaFilled(false);

            deleteButton.setFocusPainted(false);



            deleteButton.addActionListener(e -> {

                int confirm =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Delete category '"
                                        + categoryName
                                        + "' ?",
                                "Delete Category",
                                JOptionPane.YES_NO_OPTION
                        );



                if(confirm ==
                        JOptionPane.YES_OPTION) {

                    HashSet<String> extensions =
                            categoryExtensions.get(
                                    categoryName
                            );



                    if(extensions != null) {

                        usedExtensions.removeAll(
                                extensions
                        );
                    }



                    categoryExtensions.remove(
                            categoryName
                    );



                    categoryContainer.remove(
                            panel
                    );



                    categoryContainer.revalidate();

                    categoryContainer.repaint();
                }
            });



            panel.add(deleteButton);
        }



        return panel;
    }



    // =========================================
    // EDIT EXTENSIONS
    // =========================================

    private void showEditExtensionsDialog(
            String categoryName
    ) {

        HashSet<String> extensions =
                categoryExtensions.get(
                        categoryName
                );



        JPanel panel =
                new JPanel();

        panel.setLayout(

                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );



        ArrayList<JCheckBox> checkBoxes =
                new ArrayList<>();



        for(String ext : extensions) {

            JCheckBox cb =
                    new JCheckBox(ext);

            cb.setSelected(true);

            checkBoxes.add(cb);

            panel.add(cb);
        }



        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Edit Extensions - "
                                + categoryName,
                        JOptionPane.OK_CANCEL_OPTION
                );



        if(result ==
                JOptionPane.OK_OPTION) {

            HashSet<String> updated =
                    new HashSet<>();



            for(JCheckBox cb : checkBoxes) {

                if(cb.isSelected()) {

                    updated.add(
                            cb.getText()
                                    .toLowerCase()
                    );
                }
            }



            if(updated.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Category must contain at least one extension."
                );

                return;
            }



            for(String oldExt : extensions) {

                usedExtensions.remove(
                        oldExt
                );
            }



            usedExtensions.addAll(
                    updated
            );



            categoryExtensions.put(
                    categoryName,
                    updated
            );
        }
    }



    // =========================================
    // ADD CATEGORY
    // =========================================

    private void showAddCategoryDialog() {

        JTextField nameField =
                new JTextField();

        JTextField extensionField =
                new JTextField();



        JPanel panel =
                new JPanel();

        panel.setLayout(
                new GridLayout(
                        4,
                        1,
                        5,
                        5
                )
        );



        panel.add(
                new JLabel(
                        "Category Name:"
                )
        );

        panel.add(nameField);

        panel.add(
                new JLabel(
                        "Extensions (comma separated):"
                )
        );

        panel.add(extensionField);



        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Add Category",
                        JOptionPane.OK_CANCEL_OPTION
                );



        if(result ==
                JOptionPane.OK_OPTION) {

            String categoryName =
                    nameField.getText()
                            .trim();



            if(categoryName.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Category name cannot be empty."
                );

                return;
            }



            if(categoryExtensions.containsKey(
                    categoryName
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Category already exists."
                );

                return;
            }



            String[] extensions =
                    extensionField.getText()
                            .split(",");



            HashSet<String> newSet =
                    new HashSet<>();



            for(String ext : extensions) {

                ext = ext.trim();

                ext = ext.toLowerCase();



                if(ext.isEmpty()) {

                    continue;
                }



                if(usedExtensions.contains(ext)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Extension already used: "
                                    + ext
                    );

                    return;
                }



                newSet.add(ext);
            }



            if(newSet.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter at least one extension."
                );

                return;
            }



            categoryExtensions.put(
                    categoryName,
                    newSet
            );



            usedExtensions.addAll(
                    newSet
            );



            categoryContainer.add(
                    createCategoryItem(
                            categoryName
                    )
            );



            categoryContainer.revalidate();

            categoryContainer.repaint();
        }
    }



    // =========================================
    // BUTTON STYLE
    // =========================================

    private JButton createStyledButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFocusPainted(false);

        button.setBackground(
                new Color(
                        236,
                        125,
                        102
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        return button;
    }



    // =========================================
    // BACKGROUND PANEL
    // =========================================

    class BackgroundPanel
            extends JPanel {

        private Image backgroundImage;



        public BackgroundPanel() {

            backgroundImage =
                    new ImageIcon(
                            "resources/background.png"
                    ).getImage();
        }



        @Override
        protected void paintComponent(
                Graphics g
        ) {

            super.paintComponent(g);



            g.drawImage(
                    backgroundImage,
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    this
            );
        }
    }
}