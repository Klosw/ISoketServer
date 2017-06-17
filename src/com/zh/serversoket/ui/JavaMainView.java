package com.zh.serversoket.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.zh.serversoket.listener.OnWindowListener;
import com.zh.serversoket.soket.utils.Log;

public class JavaMainView extends JFrame implements WindowListener, MouseListener, ListSelectionListener {
	private static final long serialVersionUID = -3685572950519252232L;
	private OnWindowListener onWindowListener;
	private JTextArea mTextView;
	private JList<String> mListView;
	private DefaultListModel<String> mlistModel;

	public OnWindowListener getOnWindowListener() {
		return onWindowListener;
	}

	public void setOnWindowListener(OnWindowListener onWindowListener) {
		this.onWindowListener = onWindowListener;
	}

	public JavaMainView() {
		setTitle("Java - SocketTest");
		this.setSize(600, 300);
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		this.setLocation((displaySize.width - frameSize.width) / 2, (displaySize.height - frameSize.height) / 2);
		// this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		initView();
	}

	public void shows() {
		this.setVisible(true);
	}

	public void hides() {
		this.setVisible(false);
	}

	public void setText(String t) {
		mTextView.setText(t);

	}

	public void append(String t) {
		t = mTextView.getText() + "\n" + t;
		mTextView.setText(t);
	}

	private void initView() {
		initMenuView();

		mTextView = new JTextArea();
		// mTextView.setBounds(0, 0, 300, 300);
		mListView = new JList<String>();
		mListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mListView.addListSelectionListener(this);
		mlistModel = new DefaultListModel<String>();
		mListView.setBackground(new Color(0xffFFEEEE));
		mListView.setModel(mlistModel);

		JScrollPane mScrollPane = new JScrollPane(mTextView);
		mScrollPane.setPreferredSize(new Dimension(0, 100));
		// mScrollPane.setSize(new Dimension(0, 100));
		mTextView.setBackground(new Color(0xffDDEEEE));
		JScrollPane mScrollList = new JScrollPane(mListView);
		mScrollList.setPreferredSize(new java.awt.Dimension(150, 0));

		// mScrollPane.setBounds(10, 0, 300, 300);

		this.add(mScrollPane, BorderLayout.SOUTH);// 下边
		this.add(mScrollList, BorderLayout.EAST);// 右边

		// JFrame js = new JFrame();

		// FlowLayout fLayout = new FlowLayout();
		// fLayout.

		this.add(getTextView(), BorderLayout.CENTER);// 中间
	}

	private Component getTextView() {
		JLabel mIP = new JLabel();
		mIP.setText("测试");

		JButton mIpButtom = new JButton("发送");
		mIpButtom.setFocusPainted(false);

		GridBagLayout fLayout = new GridBagLayout();
		 
		
		JPanel mjPanel = new JPanel();
		mjPanel.setLayout(fLayout);
		mjPanel.add(mIP);
		mjPanel.add(mIpButtom);
		return mjPanel;
	}

	private void initMenuView() {
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("设置 O");
		// 设置助记符为F，按下ALT + F 可以触发该菜单
		file.setMnemonic('o');
		JMenuItem open = new JMenuItem("打开");
		JMenuItem quit = new JMenuItem("退出");

		file.add(open);
		// 设置菜单分隔符
		file.addSeparator();
		file.add(quit);
		menuBar.add(file);
		// 设置菜单栏，使用这种方式设置菜单栏可以不占用布局空间
		setJMenuBar(menuBar);

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (onWindowListener != null) {
			if (onWindowListener.onClosed()) {
				this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Log.d("dadad ");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			Log.d("点击了 :" + mListView.getSelectedValue());
			// mlistModel.addElement("shhhhh");
			// mlistModel.removeElement("100.100.100.101");
		}

	}
}
