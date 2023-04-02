import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 
 * @author Zero
 *
 */
public class Frame extends JFrame {

	private static final long serialVersionUID = -5151041547543472432L;

	public Frame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("res/Icon.jpg"));
//		setTitle("\u4E32\u5339\u914D");
		setTitle("串匹配");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(560, 400);
		// 居中显示
		setLocationRelativeTo(null);
		// 禁用最大化按钮
		setResizable(false);
		setContentPane(new Panel());
		setVisible(true);
	}
}