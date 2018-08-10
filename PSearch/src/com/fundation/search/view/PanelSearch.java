/*
 * @(#)PanelSearch.java
 *
 * Copyright (c) 2018 Jala Foundation.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */
package com.fundation.search.view;


import com.fundation.search.utils.LoggerSearch;
import com.toedter.calendar.JDateChooser;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import static org.apache.commons.lang3.StringUtils.isNoneEmpty;

/**
 * This class Asset can be FileResult SearchFolder.
 *
 * @author ketty camacho Vasquez- AT-[07].
 * @author Franz Elmer Silva Milan.
 * @author Denis camacho Camacho- AT-[07].
 * @version 1.0.
 */
public class PanelSearch extends JPanel {
    //these variables are for text fields.
    private JTextField textFile;
    private JTextField textPath;
    private JTextField textExtension;
    private JTextField textOwner;
    private JTextField textContain;

    //these variables are for the dates.
    private JDateChooser dateCreate;
    private JDateChooser dateCreateTo;
    private JDateChooser dateModified;
    private JDateChooser dateModifiedTo;
    private JDateChooser dateLastAccess;
    private JDateChooser dateLastAccessTo;
    //these variables are for button of select path and search.
    private JButton buttonSearch;
    private JButton btSelect;
    //these variables are for text fields.
    private String[] operatorOptions;
    private JComboBox<String> operator;
    //array that contains units of the bytes,kb,Mb and Gb.
    private String[] listUnitSize;
    private JComboBox<String> optionUnitsSize;

    private JLabel LabelSize;
    private JLabel labelFile;
    private JLabel labelPhat;
    private JLabel labelHidden;
    private JLabel labelOwner;
    private JLabel labelSearchExtension;
    private JLabel labelSearchOthers;
    private JLabel labelTo;
    private JLabel labelToM;
    private JLabel labelToL;


    private PanelTable panelTable;
    private JSpinner spinnerSize;
    public ButtonGroup radioGruop;

    private JCheckBox hiddenCheck;
    private JCheckBox pdf;
    private JCheckBox docx;
    private JCheckBox exe;
    private JCheckBox txt;
    private JCheckBox ppt;
    private JCheckBox zip;
    private JCheckBox xlsx;
    private JCheckBox rar;
    private JCheckBox enableCreate;
    private JCheckBox enableModified;
    private JCheckBox enableLastAccess;
    private JCheckBox enableOnlyRead;
    private JCheckBox enableKeySensitive;
    private JCheckBox folder;
    private JCheckBox endWith;
    private JCheckBox startWith;

    private Border blacking;
    private Border loweredetched;

    private boolean changeCreate;
    private boolean changeModified;
    private boolean changeLastAccess;
    //private URL imagen1;
    //Logger
    private static final Logger LOGGER = LoggerSearch.getInstance().getLogger();

    /**
     * this is constructor of the class PanelSearch.
     */

    public PanelSearch() {
        LOGGER.info("PanelSearch : enter");
        settingPanelSearch();
        initComponent();
        initComponentTable();
        settingPanel();
        addComponents();
        Sletras(textPath);
        numbers(spinnerSize);
        LOGGER.info("PanelSearch : exit");

    }

    private void initComponent() {
        LOGGER.info("initComponent : enter");
        listUnitSize = new String[]{"bytes", "Kb", "Mb", "Gb"};
        operatorOptions = new String[]{">", "<", "="};

        dateCreate = new JDateChooser(null, "dd/MM/yyyy");
        dateCreateTo = new JDateChooser(null, "dd/MM/yyyy");
        dateModified = new JDateChooser(null, "dd/MM/yyyy");
        dateModifiedTo = new JDateChooser(null, "dd/MM/yyyy");
        dateLastAccess = new JDateChooser(null, "dd/MM/yyyy");
        dateLastAccessTo = new JDateChooser(null, "dd/MM/yyyy");

        labelFile = new JLabel("FILE NAME:");
        textFile = new JTextField();
        textPath = new JTextField("C:\\");
        textExtension = new JTextField();
        textContain = new JTextField();
        textOwner = new JTextField();

        labelPhat = new JLabel("PATH:");
        LabelSize = new JLabel("SIZE:");
        labelHidden = new JLabel("HIDDEN");
        labelOwner = new JLabel("OWNER:");
        labelSearchExtension = new JLabel("EXTENSION:");
        labelSearchOthers = new JLabel("CONTAIN:");
        labelTo = new JLabel("TO:");
        labelToM = new JLabel("TO:");
        labelToL = new JLabel("TO:");


        operator = new JComboBox<>(operatorOptions);
        optionUnitsSize = new JComboBox<>(listUnitSize);
        spinnerSize = new JSpinner();

        radioGruop = new ButtonGroup();
        btSelect = new JButton();
        buttonSearch = new JButton("SEARCH");

        hiddenCheck = new JCheckBox("Hidden");
        folder = new JCheckBox("Folder");
        pdf = new JCheckBox(".pdf");
        docx = new JCheckBox(".docx");
        exe = new JCheckBox(".exe");
        txt = new JCheckBox(".txt");
        ppt = new JCheckBox(".pptx");
        zip = new JCheckBox(".zip");
        xlsx = new JCheckBox(".xlsx");
        rar = new JCheckBox(".rar");
        enableCreate = new JCheckBox("Date Create");
        enableModified = new JCheckBox("Date Modified");
        enableLastAccess = new JCheckBox("Date Last Access");
        enableOnlyRead = new JCheckBox("ReadOnly");
        enableKeySensitive = new JCheckBox("Key Sensitive");
        endWith = new JCheckBox("ENDWITH");
        startWith = new JCheckBox("STARTWITH");

        blacking = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        changeCreate = false;
        changeModified = false;
        changeLastAccess = false;
       // imagen1 = getClass().getResource("/view/432ee7c1-ffcd-4873-aed5-4ec30b3a5dc3.png");
        //Image image = new ImageIcon(imagen1).getImage();
        //imagen = this.getClass().getResource("/src/com/fundation/search/view/432ee7c1-ffcd-4873-aed5-4ec30b3a5dc3.png");
        LOGGER.info("initComponent : exit");
    }
    /**
     * it is method contain configuration.
     */
    public void settingPanelSearch() {
        LOGGER.info("settingPanelSearch : enter");
        setLayout(null);
        setVisible(true);
        LOGGER.info("settingPanelSearch : exit");
    }

    /**
     * this method containTable contain table of information list archive.
     */
    public void initComponentTable() {
        panelTable = new PanelTable();
    }

    /**
     * this method contain is have the location and other property as color and text
     * for look for archive.
     */
    public void settingPanel() {
        LOGGER.info("settingPanel : enter");

        /**
         * In this field you search by file name.
         */
        labelFile.setBounds(10, 20, 80, 30);
        labelFile.setBorder(blacking);
        textFile.setBounds(90, 20, 320, 30);
        textFile.setBackground(new Color(250, 252, 252));
        textFile.setBorder(blacking);

        //In this field you enter the path of file,

        labelPhat.setBounds(10, 70, 80, 30);
        labelPhat.setBorder(blacking);
        textPath.setBounds(90, 70, 320, 30);
        textPath.setBorder(blacking);
        textPath.setBackground(new Color(250, 252, 252));

        //Search by Calendar for date create.
        enableCreate.setEnabled(true);
        enableCreate.setBounds(235, 140, 100, 20);
        enableCreate.setBackground(new Color(250, 252, 252));

        dateCreate.setBounds(5, 140, 100, 20);
        dateCreate.setBorder(loweredetched);
        dateCreate.setEnabled(false);

        labelTo.setBounds(105, 140, 30, 20);
        labelTo.setForeground(new Color(1, 1, 33));
        labelTo.setBorder(loweredetched);

        dateCreateTo.setBounds(135, 140, 100, 20);
        dateCreateTo.setBorder(loweredetched);
        dateCreateTo.setEnabled(false);

        //Search by Calendar for date modified.

        enableModified.setEnabled(true);
        enableModified.setBounds(235, 160, 110, 20);
        enableModified.setBackground(new Color(250, 252, 252));

        dateModified.setBounds(5, 160, 100, 20);
        dateModified.setBorder(loweredetched);
        dateModified.setEnabled(false);

        labelToM.setBounds(105, 160, 30, 20);
        labelToM.setForeground(new Color(1, 1, 33));
        labelToM.setBorder(loweredetched);

        dateModifiedTo.setBounds(135, 160, 100, 20);
        dateModifiedTo.setBorder(loweredetched);
        dateModifiedTo.setEnabled(false);


        //Search by Calendar for date last access.

        enableLastAccess.setEnabled(true);
        enableLastAccess.setBounds(235, 180, 130, 20);
        enableLastAccess.setBackground(new Color(250, 252, 252));

        dateLastAccess.setBounds(5, 180, 100, 20);
        dateLastAccess.setBorder(loweredetched);
        dateLastAccess.setEnabled(false);

        labelToL.setBounds(105, 180, 30, 20);
        labelToL.setForeground(new Color(1, 1, 33));
        labelToL.setBorder(loweredetched);

        dateLastAccessTo.setBounds(135, 180, 100, 20);
        dateLastAccessTo.setBorder(loweredetched);
        dateLastAccessTo.setEnabled(false);

        //Button and operators.

        btSelect.setText("Select Path");
        btSelect.setBounds(290, 100, 120, 30);
        //btSelect.setBackground(Color.blue);
        btSelect.setForeground(Color.blue);


        //btSelect.setBackground(new Color(9, 244, 184));
        btSelect.setBorder(blacking);

        LabelSize.setBounds(10, 100, 100, 30);
        operator.setBounds(230, 100, 50, 30);
        operator.setBackground(new Color(250, 252, 252));
        operator.setBorder(blacking);
        optionUnitsSize.setBounds(160, 100, 70, 30);
        optionUnitsSize.setBackground(new Color(250, 252, 252));
        optionUnitsSize.setBorder(blacking);
       spinnerSize.setValue(0);
        spinnerSize.setBounds(90, 100, 70, 30);
        spinnerSize.setBackground(new Color(250, 252, 252));
        spinnerSize.setBorder(blacking);
        buttonSearch.setBounds(1000, 180, 150, 30);
        buttonSearch.setBackground(new Color(9, 244, 184));
        buttonSearch.setBorder(blacking);

        //Search by extension.

        labelSearchExtension.setBounds(425, 80, 80, 30);
        labelSearchExtension.setForeground(new Color(1, 1, 33));

        textExtension.setBounds(505, 80, 150, 30);
        textExtension.setBackground(new Color(250, 252, 252));
        textExtension.setBorder(blacking);
        labelSearchExtension.setBorder(blacking);

        pdf.setEnabled(true);
        pdf.setBounds(495, 115, 60, 23);
        pdf.setBackground(new Color(250, 252, 252));

        docx.setEnabled(true);
        docx.setBounds(565, 115, 60, 23);
        docx.setBackground(new Color(250, 252, 252));

        exe.setEnabled(true);
        exe.setBounds(495, 135, 60, 23);
        exe.setBackground(new Color(250, 252, 252));

        txt.setEnabled(true);
        txt.setBounds(565, 135, 60, 23);
        txt.setBackground(new Color(250, 252, 252));

        ppt.setEnabled(true);
        ppt.setBounds(495, 155, 60, 23);
        ppt.setBackground(new Color(250, 252, 252));

        zip.setEnabled(true);
        zip.setBounds(565, 155, 60, 23);
        zip.setBackground(new Color(250, 252, 252));

        xlsx.setEnabled(true);
        xlsx.setBounds(495, 175, 60, 23);
        xlsx.setBackground(new Color(250, 252, 252));

        rar.setEnabled(true);
        rar.setBounds(565, 175, 60, 23);
        rar.setBackground(new Color(250, 252, 252));

        //Search by owner of files.

        labelOwner.setBounds(425, 50, 80, 30);
        labelOwner.setForeground(new Color(1, 1, 33));
        labelOwner.setBorder(blacking);

        textOwner.setBounds(505, 50, 150, 30);
        textOwner.setBackground(new Color(250, 252, 252));
        textOwner.setBorder(blacking);

        //Search by contain of files.

        labelSearchOthers.setBounds(425, 20, 80, 30);
        labelSearchOthers.setForeground(new Color(1, 1, 33));
        labelSearchOthers.setBorder(blacking);

        textContain.setBounds(505, 20, 150, 30);
        textContain.setBackground(new Color(250, 252, 252));
        textContain.setBorder(blacking);

        endWith.setBounds(505, 2, 100, 18);
        endWith.setBackground(new Color(250, 252, 252));
        startWith.setBounds(605, 2, 100, 18);
        startWith.setBackground(new Color(250, 252, 252));

        //Search by only read,hidden and sensitive file.

        enableOnlyRead.setEnabled(true);
        enableOnlyRead.setBounds(10, 50, 90, 20);
        enableOnlyRead.setBackground(new Color(250, 252, 252));

        hiddenCheck.setBounds(100, 50, 80, 20);
        hiddenCheck.setBackground(new Color(250, 252, 252));

        enableKeySensitive.setBounds(180, 50, 120, 20);
        enableKeySensitive.setBackground(new Color(250, 252, 252));

        folder.setEnabled(true);
        folder.setBounds(300, 50, 80, 20);
        folder.setBackground(new Color(250, 252, 252));

        actionBottom();

        //Events of check.

        enableCreate.addChangeListener(evt -> chSearchTextStateChanged(evt));
        enableModified.addChangeListener(evt -> chSearchTextStateChanged(evt));
        enableLastAccess.addChangeListener(evt -> chSearchTextStateChanged(evt));
        LOGGER.info("settingPanel : exit");
    }

    /**
     * this method run when click on button path.
     */
    public void actionBottom() {
        btSelect.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                btSelectMouseClicked(evt);

            }
        });
    }

    /**
     * @param e is a event.
     */
    private void chSearchTextStateChanged(ChangeEvent e) {
        LOGGER.info("chSearchTextStateChanged : enter");
        if (this.changeCreate != enableCreate.isSelected()) {
            this.changeCreate = enableCreate.isSelected();
            dateCreate.setCalendar(null);
            dateCreateTo.setCalendar(null);
            dateCreate.setEnabled(changeCreate);
            dateCreateTo.setEnabled(changeCreate);

        }
        if (this.changeModified != enableModified.isSelected()) {
            this.changeModified = enableModified.isSelected();
            dateModified.setCalendar(null);
            dateModifiedTo.setCalendar(null);
            dateModified.setEnabled(changeModified);
            dateModifiedTo.setEnabled(changeModified);
        }
        if (this.changeLastAccess != enableLastAccess.isSelected()) {
            this.changeLastAccess = enableLastAccess.isSelected();
            dateLastAccess.setCalendar(null);
            dateLastAccessTo.setCalendar(null);
            dateLastAccess.setEnabled(changeLastAccess);
            dateLastAccessTo.setEnabled(changeLastAccess);
        }
        LOGGER.info("chSearchTextStateChanged : exit");
    }

    /**
     * this method add all components.
     */
    public void addComponents() {
        LOGGER.info("addComponents : enter");
        add(textFile);
        add(spinnerSize);
        add(textFile);
        add(textPath);
        add(textExtension);
        add(textContain);
        add(labelFile);
        add(labelPhat);
        add(buttonSearch);
        add(LabelSize);
        add(labelHidden);
        add(labelOwner);
        add(operator);
        add(optionUnitsSize);
        add(textPath);
        add(panelTable);
        add(hiddenCheck);
        add(btSelect);
        add(labelSearchExtension);
        add(labelSearchOthers);
        add(labelTo);
        add(labelToM);
        add(labelToL);
        add(enableCreate);
        add(enableModified);
        add(enableLastAccess);
        add(enableOnlyRead);
        add(enableKeySensitive);
        add(dateCreate);
        add(dateCreateTo);
        add(dateModified);
        add(dateModifiedTo);
        add(dateLastAccess);
        add(dateLastAccessTo);
        add(pdf);
        add(docx);
        add(exe);
        add(txt);
        add(ppt);
        add(zip);
        add(xlsx);
        add(rar);
        add(textOwner);
        add(folder);
        add(endWith);
        add(startWith);
        LOGGER.info("addComponents : exit");
    }
    public void keyTyped( KeyEvent ke){
        char c=ke.getKeyChar();
        if(Character.isLetter(c)){
            getToolkit().beep();
            ke.consume();
            JOptionPane.showMessageDialog(this, "Faltan dato.");

           // error.setText("ingrese path");
        }
    }
    public void textFileKeyTyped(java.awt.event.KeyEvent event){
        char c = event.getKeyChar();
        if(Character.isDefined(c)){
            event.consume();
            getToolkit().beep();
            JOptionPane.showMessageDialog(this,"permite texto");
        }
    }
    public void Sletras(JTextField textPath){
        textPath.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();

                //super.keyTyped(e);
                if(!Character.isLetter(c)){
                    getToolkit().beep();
                    e.consume();
                    //JOptionPane.showMessageDialog(this, "Faltan dato.");

                    // error.setText("ingrese path");
                }
                if(Character.isLetter(c)){
                    getToolkit().beep();
                    e.consume();
                    //JOptionPane.showMessageDialog(, "Faltan dato.");

                    // error.setText("ingrese path");
                }
            }
        });
    }
    public void numbers( JSpinner spinnerSize){
        spinnerSize.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c=e.getKeyChar();

                //super.keyTyped(e);
                if(!Character.isDigit(c)){
                    getToolkit().beep();
                    e.consume();
                    //JOptionPane.showMessageDialog(, "Faltan dato.");

                    // error.setText("ingrese path");
                }
            }
        });
    }

    /**
     * get value that is selected.
     *
     * @return value that selected
     */
    public String getOptionUnitsSize() {
        return optionUnitsSize.getSelectedItem().toString();
    }

    /**
     * this method get operator.
     *
     * @return operator selected
     */
    public String getOperator() {
        return operator.getSelectedItem().toString();
    }

    /**
     * this method get file text.
     *
     * @return value of file text
     */
    public String getTextFile() {
        return textFile.getText();
    }

    /**
     * this method get path.
     *
     * @return value of camp path
     */
    public String getTextPath() {
        return textPath.getText();
    }

    /**
     * @return the contain of file.
     */
    public String getContain() {
        return textContain.getText();
    }

    /**
     * @return the owner of file.
     */
    public String getOwner() {
        return textOwner.getText();
    }

    /**
     * @return the list od extension of the files.
     */
    public ArrayList<String> getExtensions() {
        ArrayList<String> extensions = new ArrayList<>();
        if (!textExtension.getText().isEmpty()) {
            extensions.add(textExtension.getText());
        }
        if (pdf.isSelected()) {
            extensions.add(pdf.getText());
        }
        if (docx.isSelected()) {
            extensions.add(docx.getText());
        }
        if (ppt.isSelected()) {
            extensions.add(ppt.getText());
        }
        if (zip.isSelected()) {
            extensions.add(zip.getText());
        }
        if (rar.isSelected()) {
            extensions.add(rar.getText());
        }
        if (xlsx.isSelected()) {
            extensions.add(xlsx.getText());
        }
        if (exe.isSelected()) {
            extensions.add(exe.getText());
        }
        if (txt.isSelected()) {
            extensions.add(txt.getText());
        }
        return extensions;
    }

    /**
     * this method get button
     *
     * @return button
     */
    public JButton getButtonSearch() {
        return buttonSearch;
    }

    /**
     * This method add row in the table.
     *
     * @param newRow
     */
    public void addRow(String[] newRow) {
        panelTable.addRow(newRow);
    }

    /**
     * this method get value of field size.
     *
     * @return value of size
     */
    public String getSizeFile() {
        return spinnerSize.getValue().toString();
    }

    /**
     * @return
     */
    public boolean getEndWith() {
        return endWith.isSelected();
    }

    /**
     * @return
     */
    public boolean getStartWith() {
        return startWith.isSelected();
    }

    /**
     * this method get value of radio button hidden.
     *
     * @return true if selected.
     */
    public boolean getHidden() {
        return hiddenCheck.isSelected();
    }

    /**
     * @return true if selected.
     */
    public boolean getOnlyRead() {
        return enableOnlyRead.isSelected();
    }

    /**
     * @return if the search is by folder.
     */
    public boolean getSearchFolder() {
        return folder.isSelected();
    }

    /**
     * @return true if selected.
     */
    public boolean getKeySensitive() {
        return enableKeySensitive.isSelected();
    }

    /**
     * @return
     */
    public Date getDateCreate() {
        return dateCreate.getDate();
    }

    /**
     * @return
     */
    public Date getDateCreateTo() {
        return dateCreateTo.getDate();
    }

    /**
     * @return
     */
    public Date getDateModified() {
        return dateModified.getDate();
    }

    /**
     * @return
     */
    public Date getDateModifiedTo() {
        return dateModifiedTo.getDate();
    }

    /**
     * @return
     */
    public Date getDateLastAccess() {
        return dateLastAccess.getDate();
    }

    /**
     * @return
     */
    public Date getDateLastAccessTo() {
        return dateLastAccessTo.getDate();
    }

    public void setTextFile(JTextField textFile) {
        this.textFile = textFile;
    }

    public void setTextPath(JTextField textPath) {
        this.textPath = textPath;
    }

    public void setTextExtension(JTextField textExtension) {
        this.textExtension = textExtension;
    }

    public void setTextOwner(JTextField textOwner) {
        this.textOwner = textOwner;
    }

    public void setTextContain(JTextField textContain) {
        this.textContain = textContain;
    }


    public void setOperator(JComboBox<String> operator) {
        this.operator = operator;
    }

    public void setOptionUnitsSize(JComboBox<String> optionUnitsSize) {
        this.optionUnitsSize = optionUnitsSize;
    }

    public void setDateCreate(JDateChooser dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setDateCreateTo(JDateChooser dateCreateTo) {
        this.dateCreateTo = dateCreateTo;
    }

    public void setDateModified(JDateChooser dateModified) {
        this.dateModified = dateModified;
    }

    public void setDateModifiedTo(JDateChooser dateModifiedTo) {
        this.dateModifiedTo = dateModifiedTo;
    }

    public void setDateLastAccess(JDateChooser dateLastAccess) {
        this.dateLastAccess = dateLastAccess;
    }

    public void setDateLastAccessTo(JDateChooser dateLastAccessTo) {
        this.dateLastAccessTo = dateLastAccessTo;
    }
    public void setSpinnerSize(JSpinner spinnerSize) {
        this.spinnerSize = spinnerSize;
    }
    public void setHiddenCheck(JCheckBox hiddenCheck) {
        this.hiddenCheck = hiddenCheck;
    }

    public void setPdf(JCheckBox pdf) {
        this.pdf = pdf;
    }

    public void setDocx(JCheckBox docx) {
        this.docx = docx;
    }

    public void setExe(JCheckBox exe) {
        this.exe = exe;
    }

    public void setTxt(JCheckBox txt) {
        this.txt = txt;
    }

    public void setPpt(JCheckBox ppt) {
        this.ppt = ppt;
    }

    public void setZip(JCheckBox zip) {
        this.zip = zip;
    }

    public void setXlsx(JCheckBox xlsx) {
        this.xlsx = xlsx;
    }

    public void setRar(JCheckBox rar) {
        this.rar = rar;
    }

    public void setEnableOnlyRead(JCheckBox enableOnlyRead) {
        this.enableOnlyRead = enableOnlyRead;
    }

    public void setEnableKeySensitive(JCheckBox enableKeySensitive) {
        this.enableKeySensitive = enableKeySensitive;
    }

    public void setFolder(JCheckBox folder) {
        this.folder = folder;
    }

    public void setEndWith(JCheckBox endWith) {
        this.endWith = endWith;
    }

    public void setStartWith(JCheckBox startWith) {
        this.startWith = startWith;
    }

    /**
     * this method clean table.
     */
    public void cleanTable() {
        panelTable.clean();
    }

    /**
     *
     * @return
     */
    public PanelTable getPanelTable() {
        return panelTable;
    }

    /**
     * this method
     *
     * @param evt
     */
    private void btSelectMouseClicked(MouseEvent evt) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            textPath.setText(selectedFile.getAbsolutePath());
        }

    }

    public  void enableComponents(boolean enable){
        endWith.setEnabled(enable);
        startWith.setEnabled(enable);
        pdf.setEnabled(enable);
        txt.setEnabled(enable);
        ppt.setEnabled(enable);
        rar.setEnabled(enable);
        zip.setEnabled(enable);
        exe.setEnabled(enable);
        docx.setEnabled(enable);
        xlsx.setEnabled(enable);

        textExtension.enable(enable);
        textOwner.enable(enable);
        textContain.enable(enable);
        folder.setEnabled(enable);
    }
}
