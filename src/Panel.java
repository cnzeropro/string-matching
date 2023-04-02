import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;

/**
 * 
 * @author Zero
 *
 */
public class Panel extends JPanel {

	private static final long serialVersionUID = -4081809557681930258L;

	private JLabel lblMainStrPath;
	private JLabel lblBF;
	private JLabel lblBFResult;
	private JLabel lblBFAnalysis;
	private JLabel lblBFPosition;
	private JLabel lblBFMicrosecond;
	private JLabel lblBM;
	private JLabel lblBMResult;
	private JLabel lblBMAnalysis;
	private JLabel lblBMPosition;
	private JLabel lblBMMicrosecond;
	private JLabel lblKMP;
	private JLabel lblKMPResult;
	private JLabel lblKMPAnalysis;
	private JLabel lblKMPPosition;
	private JLabel lblKMPMicrosecond;
	private JLabel lblSubStr;

	private JTextField textFieldMainStrPath;
	private JTextField textFieldBFResult;
	private JTextField textFieldBFAnalysis;
	private JTextField textFieldBMResult;
	private JTextField textFieldBMAnalysis;
	private JTextField textFieldKMPResult;
	private JTextField textFieldKMPAnalysis;
	private JTextField textFieldSubStr;

	private JButton btnOpen;
	private JButton btnBFMatch;
	private JButton btnBMMatch;
	private JButton btnKMPMatch;

	private String mainStr;
	private String subStr;

	public Panel() {
		setBorder(new EmptyBorder(5, 5, 5, 5));

		init();
		addListener();
		setLayout();
	}

	private void init() {
		lblMainStrPath = new JLabel("主串文件路径：");
		textFieldMainStrPath = new JTextField();
		btnOpen = new JButton("Open");

		lblBF = new JLabel("BF算法");
		lblBFResult = new JLabel("结果：");
		lblBFAnalysis = new JLabel("分析：");
		textFieldBFResult = new JTextField();
		textFieldBFResult.setEditable(false);
		textFieldBFAnalysis = new JTextField();
		textFieldBFAnalysis.setEditable(false);
		lblBFPosition = new JLabel("位置");
		lblBFMicrosecond = new JLabel("微秒");
		btnBFMatch = new JButton("匹配");

		lblBM = new JLabel("BM算法");
		lblBMResult = new JLabel("结果：");
		lblBMAnalysis = new JLabel("分析：");
		textFieldBMResult = new JTextField();
		textFieldBMResult.setEditable(false);
		textFieldBMAnalysis = new JTextField();
		textFieldBMAnalysis.setEditable(false);
		lblBMPosition = new JLabel("位置");
		lblBMMicrosecond = new JLabel("微秒");
		btnBMMatch = new JButton("匹配");

		lblKMP = new JLabel("KMP算法");
		lblKMPResult = new JLabel("结果：");
		lblKMPAnalysis = new JLabel("分析：");
		textFieldKMPResult = new JTextField();
		textFieldKMPResult.setEditable(false);
		textFieldKMPAnalysis = new JTextField();
		textFieldKMPAnalysis.setEditable(false);
		lblKMPPosition = new JLabel("位置");
		lblKMPMicrosecond = new JLabel("微秒");
		btnKMPMatch = new JButton("匹配");

		lblSubStr = new JLabel("待匹配子串：");
		textFieldSubStr = new JTextField();
	}

	// 添加监听
	private void addListener() {
		btnOpen.addMouseListener(new MouseAdapter() {
			// 点击Open按钮时生成文件选择器
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				chooser.setDialogTitle("请选择文本");
				// 过滤出txt文件
				chooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
				int result = chooser.showOpenDialog(Panel.this);

				// 选择了文件
				if (result == JFileChooser.APPROVE_OPTION) {
					// 获取当前选择文件的路径
					String path = chooser.getSelectedFile().getPath();

					// 判断用户是否选择了txt文件
					if (!path.substring(path.lastIndexOf('.') + 1).toLowerCase().equals("txt")) {
						JOptionPane.showMessageDialog(chooser, "请选择文本文件", "提示", JOptionPane.INFORMATION_MESSAGE);
						return;
					}

					textFieldMainStrPath.setText(path);
				}
			}
		});

		// BF匹配算法按钮监听
		btnBFMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!check()) {
					return;
				}

				long startTime = System.nanoTime();
				int value = Algorithm.bf(mainStr.toCharArray(), subStr.toCharArray());
				long endTime = System.nanoTime();

				textFieldBFResult.setText(Integer.toString(value));
				textFieldBFAnalysis.setText(Double.toString((endTime - startTime) / 1000.0));
			}
		});

		// BM匹配算法按钮监听
		btnBMMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!check()) {
					return;
				}

				long startTime = System.nanoTime();
				int value = Algorithm.bm(mainStr.toCharArray(), subStr.toCharArray());
				long endTime = System.nanoTime();

				textFieldBMResult.setText(Integer.toString(value));
				textFieldBMAnalysis.setText(Double.toString((endTime - startTime) / 1000.0));
			}
		});

		// KMP匹配算法按钮监听
		btnKMPMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!check()) {
					return;
				}

				long startTime = System.nanoTime();
				int value = Algorithm.kmp(mainStr.toCharArray(), subStr.toCharArray());
				long endTime = System.nanoTime();

				textFieldKMPResult.setText(Integer.toString(value));
				textFieldKMPAnalysis.setText(Double.toString((endTime - startTime) / 1000.0));
			}
		});
	}

	// 设置面板布局
	private void setLayout() {
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addGap(10).addComponent(lblMainStrPath)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textFieldMainStrPath, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblBFAnalysis).addComponent(lblBFResult))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldBFAnalysis, GroupLayout.DEFAULT_SIZE, 165,
												Short.MAX_VALUE)
										.addComponent(textFieldBFResult, GroupLayout.DEFAULT_SIZE, 156,
												Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblBFPosition, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
										.addComponent(lblBFMicrosecond, GroupLayout.PREFERRED_SIZE, 46,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnBFMatch, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
				.addGap(0))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblBF, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(312, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblSubStr, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textFieldSubStr, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblBM, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 312, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout
								.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblKMPResult, GroupLayout.PREFERRED_SIZE, 42,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblKMPAnalysis, GroupLayout.PREFERRED_SIZE, 42,
														GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(textFieldKMPResult, GroupLayout.DEFAULT_SIZE, 160,
														Short.MAX_VALUE)
												.addComponent(textFieldKMPAnalysis, GroupLayout.DEFAULT_SIZE, 160,
														Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblBMAnalysis, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblBMResult, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(textFieldBMAnalysis, GroupLayout.DEFAULT_SIZE, 160,
														Short.MAX_VALUE)
												.addComponent(textFieldBMResult, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(groupLayout.createSequentialGroup().addGap(0)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
														.addComponent(lblBMPosition, GroupLayout.DEFAULT_SIZE, 52,
																Short.MAX_VALUE)
														.addComponent(lblBMMicrosecond, GroupLayout.DEFAULT_SIZE, 52,
																Short.MAX_VALUE))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnBMMatch, GroupLayout.PREFERRED_SIZE, 98,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(lblKMPMicrosecond, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(lblKMPPosition, GroupLayout.PREFERRED_SIZE, 53,
																GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnKMPMatch,
														GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
						.addComponent(lblKMP, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)).addGap(0)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(14).addComponent(lblMainStrPath))
								.addGroup(groupLayout.createSequentialGroup().addGap(11)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(textFieldMainStrPath, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnOpen))))
						.addGap(18).addComponent(lblBF, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE).addGap(18)
						.addComponent(btnBFMatch).addGap(13))
						.addGroup(groupLayout.createSequentialGroup().addGap(73)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldBFResult, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblBFResult).addComponent(lblBFPosition))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldBFAnalysis, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblBFAnalysis).addComponent(lblBFMicrosecond))
								.addGap(2)))
				.addGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup().addGap(18).addComponent(lblBM)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(textFieldBMResult, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblBMPosition).addComponent(lblBMResult))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(textFieldBMAnalysis, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblBMMicrosecond).addComponent(lblBMAnalysis))
										.addGap(18).addComponent(lblKMP, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(
										groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnBMMatch).addGap(47)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(2)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldKMPResult, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblKMPPosition).addComponent(lblKMPResult))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(textFieldKMPAnalysis, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblKMPMicrosecond).addComponent(lblKMPAnalysis)))
						.addGroup(groupLayout.createSequentialGroup().addGap(13)
								.addComponent(btnKMPMatch, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE).addGap(12)))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblSubStr).addComponent(
						textFieldSubStr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(77)));
		setLayout(groupLayout);
	}

	private boolean check() {
		String path = textFieldMainStrPath.getText();

		// 是否输入主串路径
		if (path.isBlank()) {
			JOptionPane.showMessageDialog(Panel.this, "请输入主串路径", "提示", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		// 主串为空时，从文件中读取主串
		if (mainStr == null) {
			// 判断是否为txt文件
			if (!path.substring(path.lastIndexOf('.') + 1).toLowerCase().equals("txt")) {
				JOptionPane.showMessageDialog(Panel.this, "请选择文本文件", "提示", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}

			try {
				mainStr = FileUtils.readFileToString(new File(path), "utf-8");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(Panel.this, "请输入正确的主串路径", "错误提示", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}

		subStr = textFieldSubStr.getText();

		if (mainStr.isBlank() || subStr.isBlank()) {
			JOptionPane.showMessageDialog(Panel.this, "请输入主串或待匹配的子串", "提示", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		return true;
	}
}
