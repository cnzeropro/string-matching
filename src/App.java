import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
/**
 * 
 * @author Zero
 *
 */
public class App {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
			EventQueue.invokeLater(() -> new Frame());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "皮肤设置错误", "错误", JOptionPane.WARNING_MESSAGE);
		}
	}
}
