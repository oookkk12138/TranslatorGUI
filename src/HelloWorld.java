import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class HelloWorld extends Thread{
	
	static File oldFolderLocationFile;
	static File newFolderLocationFile;
	
	//win10风格的swing
	static {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
	}
	
	//背景图片
	public static void backgroundImage(JFrame frame) {
		//加载图片
		ImageIcon icon = new ImageIcon("image/DragonBall.jpg");
		//将图片放入Label中
		JLabel label = new JLabel(icon);
		//设置Label大小和位置，Label大小就是图片的大小
		label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		//把label加入到主窗体中
		frame.add(label);
	}
	
	//主窗体前部分
	public static JFrame getJFrame() {
		//名字
		JFrame frame = new JFrame("编码格式转换器");
		//大小
		frame.setSize(766,479);
		//位置
		frame.setLocation(400,200);
		//绝对定位
		frame.setLayout(null);
		return frame;
	}
	
	//主窗体的后置操作
	public static void closeJFrame(JFrame frame) {
		//不可改变大小
		frame.setResizable(false);
		//当退出时关闭主窗体
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//让窗体可见
		frame.setVisible(true);
	}
	
	//两个头上的按钮的button
	public static Map<String,JButton> topButton(JFrame frame) {
		JButton button1 = new JButton("UTF-8转换成GBK");
		button1.setBounds(0,5,200,30);
		JButton button2 = new JButton("GBK转换成UTF-8");
		button2.setBounds(250,5,200,30);
		JPanel panel = new JPanel();
		//不使用布局器
		panel.setLayout(null);
		panel.setBounds(150,20,450,40);
		panel.add(button1);
		panel.add(button2);
		//设置为透明（否则看不到背景图片）
		panel.setOpaque(false);
		frame.add(panel);
		//把两个按钮返回
		Map<String,JButton> map = new TreeMap<String,JButton>();
		map.put("button1",button1);
		map.put("button2",button2);
		return map;
	}
	
	//标题提示信息
	public static JLabel setTitle(JFrame frame,JPanel panel) {
		panel.setBorder(BorderFactory.createLineBorder(Color.red));
		panel.setLayout(null);
		panel.setBounds(150,60,450,300);
		JLabel label = new JLabel();
		label.setBounds(0,0,panel.getWidth(),40);
		//文字居中
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.pink));
		label.setText("请选择点击上面的两个按钮");
		label.setForeground(Color.white);
		label.setOpaque(true);
		label.setBackground(Color.pink);
		panel.add(label);
		frame.add(panel);
		return label;
	}
	
	//添加选择文件和选择位置那两个东西
	public static Map<String,Object> setTwoChoose(JFrame frame,JPanel panel){
		JLabel label1 = new JLabel();
		label1.setBounds(20,50,100,50);
		label1.setText("文件位置：");
		label1.setForeground(Color.white);
		JTextField tf1 = new JTextField();
		tf1.setBounds(80,60,250,30);
		JButton bChoose = new JButton("选择文件");
		bChoose.setBounds(335,60,100,30);
		panel.add(label1);
		panel.add(tf1);
		panel.add(bChoose);
		
		JLabel label2 = new JLabel();
		label2.setBounds(20,100,100,50);
		label2.setText("保存位置：");
		label2.setForeground(Color.white);
		JTextField tf2 = new JTextField();
		tf2.setBounds(80,110,250,30);
		JButton bLocation = new JButton("选择位置");
		bLocation.setBounds(335,110,100,30);
		panel.add(bLocation);
		panel.add(label2);
		panel.add(tf2);
		
		Map<String,Object> map = new TreeMap<String,Object>();
		map.put("tf1",tf1);
		map.put("tf2",tf2);
		map.put("bChoose",bChoose);
		map.put("bLocation",bLocation);
		frame.add(panel);
		return map;
	}
	
	//添加开始转换和使用规则两个按钮
	public static Map<String,JButton> setTwoButton(JFrame frame,JPanel panel){
		JButton startTransform = new JButton("开始转换");
		startTransform.setBounds((panel.getWidth()-100)/2,150,100,50);
		panel.add(startTransform);
		
		JButton help = new JButton("使用规则");
		help.setBounds((panel.getWidth()-100)/2,220,100,50);
		panel.add(help);
		
		Map<String,JButton> map = new TreeMap<String,JButton>();
		map.put("startTransform",startTransform);
		map.put("help",help);
		frame.add(panel);
		return map;
	}
	
	//点击按钮修改提示信息（UTF-8转换成GBK）
	public static void button1Action(JButton button1,JLabel title) {
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				title.setText("UTF-8转换成GBK(编码格式选择不正确会导致转换失败)");
			}
		});
	}
	
	//点击按钮修改提示信息（GBK转换成UTF-8）
	public static void button2Action(JButton button2,JLabel title) {
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				title.setText("GBK转换成UTF-8(编码格式选择不正确会导致转换失败)");
			}
		});
	}
	
	//选择文件按钮事件
	public static void bChooseAction(JButton bChoose,JTextField tf1) {
		bChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取文件选择器
				JFileChooser fc = new JFileChooser();
				//打开文件或文件夹
		        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				//打开视图
		        fc.showOpenDialog(null);
		        //获取文件路径
				File file = fc.getSelectedFile();
				tf1.setText(file.getAbsolutePath());
			}
		});
	}
	
	//选择位置按钮事件
	public static void bLocationAction(JButton bLocation,JTextField tf2) {
		bLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取文件选择器
				JFileChooser fc = new JFileChooser();
				//打开文件或文件夹
			    fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				//打开视图
			    fc.showOpenDialog(null);
			    //获取文件路径
				File file = fc.getSelectedFile();
				tf2.setText(file.getAbsolutePath());
			}
		});
	}
	
	/**
	 * @param startTransform：开始转换按钮
	 * @param frame：主窗体
	 * @param tf1：选择文件按钮
	 * @param tf2：选择位置按钮
	 * @param title：小标题
	 */
	public static void startTransform(JButton startTransform,JFrame frame,JTextField tf1,JTextField tf2,JLabel title) {
		startTransform.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oldFolderLocationFile = new File(tf1.getText());
				//用于获取oldFolderLocation的最后一个文件路径
				int index = oldFolderLocationFile.toString().lastIndexOf("\\");
				String FileForNeed = oldFolderLocationFile.toString().substring(index + 1,oldFolderLocationFile.toString().length());
				newFolderLocationFile = new File(tf2.getText() + "\\" + FileForNeed);
				//使用多线程才能显示出进度条
				System.out.println(tf2.getText().equals(""));
				new Thread() {
					public void run() {
						//判断是否执行UTF-8转换诚GBK
						if(title.toString().contains("UTF-8转换成GBK")) {
							//没有填写正确路径则提示信息
							if(tf1.getText().equals("") || tf2.getText().equals("")) {
								JOptionPane.showMessageDialog(frame, "路径填写不正确！");
								Thread.interrupted();
							}else {
								//路径信息正确则开始转换
								UTF8ToGBK.TheMainTranslator(oldFolderLocationFile,newFolderLocationFile);
								JOptionPane.showMessageDialog(frame, "转换完成！");
								Thread.interrupted();
							}
						}
						//判断是否执行GBK转换成UTF-8
						else if(title.toString().contains("GBK转换成UTF-8")) {
							//判断路径是否完整填写了
							if(tf1.getText().equals("") || tf2.getText().equals("")) {
								JOptionPane.showMessageDialog(frame, "路径填写不正确！");
								Thread.interrupted();
							}else {
								//开始转换
								GBKToUTF8.TheMainTranslator(oldFolderLocationFile,newFolderLocationFile);
								JOptionPane.showMessageDialog(frame, "转换完成！");
								Thread.interrupted();
							}
						}else {
							//没有选择按钮时提示的信息
							JOptionPane.showMessageDialog(frame, "请选择点击上面两个按钮","未选定转换格式",JOptionPane.WARNING_MESSAGE);
						}
					}
				}.start();
				//输出测试
				System.out.println(tf1.getText());
				System.out.println(tf2.getText());
				System.out.println(newFolderLocationFile.toString());
			}
		});
	}
	
	//点击使用规则按钮
	public static void helpAction(JFrame frame,JButton help) {
		JDialog dialog = new JDialog(frame,"使用帮助");
		dialog.setBounds(556,250,450,400);
		JLabel label = new JLabel();
		String message0 = "/**";
		String message1 = "&nbsp;* 1.先点击[UTF-8转换成GBK]或者[GBK转换成UTF-8]按钮";
		String message2 = "&nbsp;* 2.点击选择文件，选择你需要转换的文件/文件夹";
		String message3 = "&nbsp;* 3.点击选择位置作为文件转换后的存储位置，若与原文件一样则会覆盖原文件";
		String message4 = "&nbsp;* 4.点击开始转换，等待转换完毕即可";
		String message5 = "&nbsp;* 5.如需帮助，点击使用规则";
		String message6 = "&nbsp;* 6.若转换后出现问题就是BUG 没有售后 哈哈哈";
		String message7 = "**/";
		String messageTotal = "<html><body>" + message0 + "<br>" + message1 + "<br>" + message2 + "<br>" + message3 + "<br>" + message4 + "<br>" + message5 + "<br>" + message6 + "<br>" + message7 + "</body></html>";
		label.setText(messageTotal);
		dialog.add(label);
		
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
			}
		});
	}
	
	
	public static void main(String[] args) {
		//获取主窗体
		JFrame frame = getJFrame();
		//头顶的两个按钮
		Map<String,JButton> map1 = topButton(frame);
		//获取panel
		JPanel panel = new JPanel();
		//设置透明程度
		Color color = new Color(0f,0f,0f,0.5f);
		panel.setBackground(color);
		//设置提示信息
		JLabel title = setTitle(frame,panel);
		//选择文件和选择位置
		Map<String,Object> map2 = setTwoChoose(frame,panel);
		//开始转换和使用规则
		Map<String,JButton> map3 = setTwoButton(frame,panel);
		//点击按钮修改提示信息
		JButton button1 = map1.get("button1");
		JButton button2 = map1.get("button2");
		button1Action(button1,title);
		button2Action(button2,title);
		//获取两个按钮和两个信息框
		JButton bChoose = (JButton) map2.get("bChoose");
		JButton bLocation = (JButton) map2.get("bLocation");
		JTextField tf1 = (JTextField) map2.get("tf1");
		JTextField tf2 = (JTextField) map2.get("tf2");
		bChooseAction(bChoose,tf1);
		bLocationAction(bLocation,tf2);
		//点击开始转换按钮
		JButton startTransform = map3.get("startTransform");
		//具体执行转换的方法
		startTransform(startTransform,frame,tf1,tf2,title);
		//点击使用规则按钮
		JButton help = map3.get("help");
		helpAction(frame,help);
		//背景
		backgroundImage(frame);
		//关闭
		closeJFrame(frame);
	}
}	
