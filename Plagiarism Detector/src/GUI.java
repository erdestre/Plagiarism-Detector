import javax.swing.*;

public class GUI {
    private JPanel panelMain;
    private JButton scanButton;
    private JButton button2;
    private JTextArea aaTextArea;
    private JTree tree1;
    private JTree tree2;
    private JPanel panel1;

    public static void GUI(){
        JFrame frame = new JFrame("Plagiarism Detector");
        frame.setContentPane(new GUI().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
