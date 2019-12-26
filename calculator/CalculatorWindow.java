package com.lcl.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CalculatorWindow extends JFrame {
	private JTextField resultTextField = null;
	private JPanel panel = null, panel2 = null;
	private String textField = new String(" ");
	private String history = new String(" ");

	public CalculatorWindow() {
		Init();
	}

	/**
	 * 初始化窗口
	 */
	private void Init() {
		// 1.初始化窗口
		this.setBounds(152, 70, 1610, 920);
		this.setBackground(new Color(241, 241, 241));
		this.setIconImage(new ImageIcon("img/icon.png").getImage());
		this.setTitle("计算器");
		this.setLayout(new BorderLayout());
		//2.設置文本域
		resultTextField = new JTextField();
		resultTextField.setPreferredSize(new Dimension(this.getWidth(), 80));
		resultTextField.setHorizontalAlignment(JTextField.RIGHT);
		resultTextField.setEditable(false);
		resultTextField.setFont(new Font(resultTextField.getFont().getFontName(), resultTextField.getFont().getStyle(), 36));
		resultTextField.setBackground(new Color(241, 241, 241));
		// 顶层面板
		panel = new JPanel();
		panel.add(resultTextField);

		//
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(6, 4));
		JButton[] buttons = new JButton[24];

		String[] text = { "%", "CE", "C", " ",
				"1/X", "X²", "√x", "÷",
				"7", "8", "9", "x",
				"4", "5", "6", "-",
				"7", "8","9", "+",
				"-/+", "0", ".", "=" };
		String[] values = { "%", " ", " ", "clear",
				"1/", "pow(x,2)", "sqrt", "/",
				"7", "8", "9", "*", 
				"4", "5", "6","-",
				"7", "8", "9", "+",
				"-/+", "0", ".", "=" };
		HashMap<String, String> map = new HashMap<String, String>();
		//初始按钮
		for (int i = 0; i < 24; ++i) {
			if (!text[i].equals(" ")) {
				buttons[i] = new JButton(text[i]);
				buttons[i].setFont(new Font(buttons[i].getFont().getFontName(), buttons[i].getFont().getStyle(), 36));
			} else {
				buttons[i] = new JButton(new ImageIcon("img/x.jpg"));
			}
			buttons[i].setBackground(new Color(246, 246, 246));
			panel2.add(buttons[i]);
			buttons[i].setFocusable(false);
			map.put(text[i], values[i]);
		}
		//按钮监控
		for(int i =0;i<24;++i) {
			buttons[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String actionCommand = map.get(e.getActionCommand()); //映射按钮对应的值
					boolean flag = true;
					
					if(actionCommand.equals("-/+")) {
						flag = false;
						textField = " "+-Double.parseDouble(textField);
						history =textField;
						resultTextField.setText(textField);
					}else if(actionCommand.equals("1/") || actionCommand.equals("pow(x,2)")
							|| actionCommand.equals("sqrt") || actionCommand.equals("%")){
						flag = false;
						Double num = Double.parseDouble(textField.split(" ")[1]);
						System.out.println("textField.split(\" \")[1]:"+textField.split(" ")[1]);
						if(actionCommand.equals("1/")) {
							history = " "+1/num;
							textField = "1"+" "+"/"+" "+num+" "+"="+" "+history;
						}else if(actionCommand.equals("pow(x,2)")){
							history =" "+Math.pow(num, 2);
							textField = "pow("+num+",2)"+" " +"="+" "+ history;	
						}else if(actionCommand.equals("sqrt")){
							history = " "+ Math.sqrt(num);
							textField = "sqrt("+num+")"+" "+"="+" "+history;
						}else {
							history = " " + num/100;
							textField =" "+ num +"%" +" "+ "="+" "+history;
						}
						resultTextField.setText(textField);
						textField = history;
					}

					if (actionCommand.equals("+") || actionCommand.equals("-") || actionCommand.equals("*")
							|| actionCommand.equals("/")) {
						if (textField.equals(" ")) {
							if(!history.equals(" ")) {
								textField = " "+history + " "+actionCommand+" ";
							}else {
								textField = " "+"0" + " " + actionCommand + " ";
							}
						} else {
							textField = textField + " " + actionCommand + " ";
						}

					} else if (actionCommand.equals("=")) {
						flag = false;
						history = CalculatorWindow.calculate(textField);
						textField = textField + " " + map.get(actionCommand) + history;
						resultTextField.setText(textField+" ");
						textField = " ";

					}else if (actionCommand.equals(" ")) {
						history = " ";
						textField = " ";
						resultTextField.setText(" ");
					}
					else if(actionCommand =="." || Double.parseDouble(actionCommand)>=0&&Double.parseDouble(actionCommand)<=9) {
						textField = textField + actionCommand;
						System.out.println("textField"+textField);
					}
					
					if (flag) {
						System.out.println("输出到屏幕……");
						resultTextField.setText(textField+" ");
					}
				}
			});
		}
		this.add(panel, BorderLayout.NORTH);
		this.add(panel2, BorderLayout.CENTER);
		this.setVisible(true);
		resultTextField.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * date:2019/12/23
	 * @param expression
	 * @return 计算结果 String
	 */
	protected static String calculate(String expression) {
		System.out.println("expression:"+expression);
		String[] comput = expression.split(" ");// 分割操作符与数字
		Stack<Double> stack = new Stack<Double>();// 存储数据
		stack.push(Double.parseDouble(comput[1]));
		for (int i = 2; i < comput.length; i++) {
			if (i % 2 == 0) {
				if (comput[i].equals("+")) {
					stack.push(Double.parseDouble(comput[i + 1]));// 把字符串转成数字
				}
				if (comput[i].equals("-")) {
					stack.push(-Double.parseDouble(comput[i + 1]));
				}
				if (comput[i].equals("*")) {
					Double m = stack.peek();
					stack.pop();
					Double n = Double.parseDouble(comput[i + 1]);// 把*前后两个数相乘再压栈
					stack.push(m * n);
				}
				if (comput[i].equals("/")) {
					Double m = stack.peek();
					stack.pop();
					Double n = Double.parseDouble(comput[i + 1]);
					stack.push(m / n);
				}
			}
		}
		double sum = 0;
		while (!stack.isEmpty()) {
			sum = sum + stack.peek();
			stack.pop();
		}
		String result = String.valueOf(sum);// 转换成字符串型
		return result;
	}
}
