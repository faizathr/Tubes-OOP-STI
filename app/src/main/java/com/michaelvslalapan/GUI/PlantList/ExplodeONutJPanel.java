/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.michaelvslalapan.GUI.PlantList;

import com.michaelvslalapan.GUI.StartUpFrame;

/**
 *
 * @author David
 */
public class ExplodeONutJPanel extends javax.swing.JPanel {
    StartUpFrame mainframe;
    /**
     * Creates new form SunflowerJPanel
     * @param mainframe
     */
    public ExplodeONutJPanel(StartUpFrame mainframe) {
        initComponents();
        this.mainframe = mainframe;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ExplodeONut.gif"))); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(260, 110, 80, 100);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StartAndMenuButton/BackButton.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(20, 20, 90, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ExplodeONut.png"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 600, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        mainframe.clickPlantListMenu();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
