package Interface;

import Classes.IsPhrase;
import Classes.IsWord;
import Classes.NewIsPhrase;
import Classes.Tokens;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.SwingConstants;

public class Tela extends javax.swing.JFrame {

    /**
     * Creates new form Tela
     */
    public Tela() {
        initComponents();
        _lastIndex = 0;
        _lastIndexDot = 0;
        _check = false;
        _tokens = new ArrayList<>();
        
        //new IsWord("space", 0, 0).run();
    }
    
    int checking (String getText, String type){
        getText = txtField.getText();
         _subs = getText.substring( _lastIndex, getText.lastIndexOf(type) );
         
        if (type.equals("."))
            return getText.lastIndexOf(_dot) + 1;
        else
            return getText.lastIndexOf(_space);
            
    }
    
    
    
    void printExceptionError(char evt){
        
        String exception = "<html>Erro encontrado.<br/>";
        exception += "lastIndex: "+ _lastIndex + "<br/>";
        exception += "lastIndexDot: "+ _lastIndexDot + "<br/>";
        exception += "Frase: " + txtField.getText() + "<br/>";
        exception += "Tamanho da frase: " + txtField.getText().length() + "<br/>";
        exception += "Char do teclado: " + evt + "<br/>";
        lblResult.setHorizontalTextPosition(SwingConstants.CENTER);
        lblResult.setText(exception);

        
    }
    
    public static void addToken(String token, String classification, String grammar){
        
        _tokens.add(new Tokens(token, classification, grammar));
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblResult = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFieldKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Projeto final - Construção de Compiladores I");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblResult, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtField, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblResult, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFieldKeyReleased
        keyReleasedEvent(evt);
    }//GEN-LAST:event_txtFieldKeyReleased
    
    void keyReleasedEvent(java.awt.event.KeyEvent evt){
        try{
            if(evt.getKeyChar() == '.'){

                _subs = txtField.getText().substring(_lastIndexDot, txtField.getText().lastIndexOf(_dot)) + " .";
                
                NewIsPhrase ip = new NewIsPhrase(_subs);
                ip.run();
                
                if(_check){
                    System.out.println("Deu certo a frase. Recebendo indice do ponto: " + txtField.getText().lastIndexOf(_dot));
                    _lastIndexDot = txtField.getText().lastIndexOf(_dot) + 1;
                    txtField.setText("");
                    
                }
            }
        }catch(Exception e){
            printExceptionError(evt.getKeyChar());
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Tela().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel lblResult;
    private javax.swing.JTextField txtField;
    // End of variables declaration//GEN-END:variables
    public static int _lastIndex;
    public static int _lastIndexDot;
    private final String _dot = ".";
    private final String _space = " ";
    private String _subs;
    public static ArrayList<Tokens> _tokens;
    public static boolean _check;
}