/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;

/**
 *
 * @author shadowx
 */
public class StudentInfo extends javax.swing.JPanel {


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1, jButton2, jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;


    /**
     * Creates new form StudentInfo
     */
    public StudentInfo() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            MainFrame.students.toArray(),
            new String [] {
                //"First Name", "Last name", "VID", "Grade", "Major GPA", "Total GPA", "Major Credits", "Upper Level Credits", "Total Credits", "Qualified to Graduate", "Last Advising","Graduation Application"
                    "First Name", "Last Name", "VID", "Grade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Integer.class,java.lang.Integer.class,java.lang.Integer.class,java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });

        jTable1.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Save Changes");
        jButton1.addActionListener(evt ->
                        FileIO.writeFile("studentList.csv", MainFrame.students)
        );

        jButton2.setText("Reload");
        jButton2.addActionListener(evt -> {
            MainFrame.students = new StudentList(new File("studentList.csv"));
            loadList();
        });

        jButton3.setText("Add Student");
        jButton3.addActionListener(evt -> {
            MainFrame.students.add(new Student());
            loadList();
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadList() {
        //firstName, lastName, idNum, grade, majorGPA, totalGPA, majorCrd, upperCrd, totalCrd,gradQualified,advDate,subDate
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            MainFrame.students.toArray(),
            new String [] {
                //"First Name", "Last name", "VID", "Grade", "Major GPA", "Total GPA", "Major Credits", "Upper Level Credits", "Total Credits", "Qualified to Graduate", "Last Advising","Graduation Application"
                    "First Name", "Last Name", "VID", "Grade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Integer.class,java.lang.Integer.class,java.lang.Integer.class,java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
    }
    // End of variables declaration//GEN-END:variables
}
