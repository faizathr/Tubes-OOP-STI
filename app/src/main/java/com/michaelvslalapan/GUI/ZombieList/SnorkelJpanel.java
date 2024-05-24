/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.michaelvslalapan.GUI.ZombieList;

import com.michaelvslalapan.GUI.StartUpFrame;

/**
 *
 * @author David
 */
public class SnorkelJpanel extends javax.swing.JPanel {
    StartUpFrame mainframe;
    /**
     * Creates new form SunflowerJPanel
     * @param mainframe
     */
    public SnorkelJPanel(StartUpFrame mainframe) {
        initComponents();
        this.mainframe = mainframe;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ZombieResized/SnorkelZombie.gif"))); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(280, 110, 60, 100);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BackButton.png"))); // NOI18N
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SnorkelZombie.png"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 600, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        mainframe.clickZombieListMenu();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
