import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MkfileWindow extends JFrame implements ItemListener, ActionListener {

	GridBagLayout grid;
	GridBagConstraints gridcon;
	String[] month = {"-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
	String[] count = {"-", "1", "2", "3", "4", "5"};
	String[] DoW = {"-", "月", "火", "水", "木", "金", "土", "日"};
	int[] URUDOSI_M = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	int[] HEINEN_M = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	int year = Calendar.getInstance().get(Calendar.YEAR);
	static String INFOR_M = "メッセージ";
	static String INFOR_NAME = "休日の名称を入力してください。";
	static String INFOR_DAY = "日付を正しく入力してください。";
	JTextField month1 = new JTextField("", 4);
	JTextField day1 = new JTextField("", 4);
	JTextField month2 = new JTextField("", 4);
	JTextField day2 = new JTextField("", 4);
	JTextField holiName = new JTextField("", 10);
	JComboBox ComboMonth = new JComboBox(month);
	JComboBox ComboCount = new JComboBox(count);
	JComboBox ComboDoW = new JComboBox(DoW);
	JLabel labelTitle = new JLabel("休日を追加します。日付を入力してください。");
	JLabel labelMonth = new JLabel("月");
	JLabel labelDay = new JLabel("日 ～ ");
	JLabel labelMonth2 = new JLabel("月");
	JLabel labelDay2 = new JLabel("日");
	JLabel labelMonth3 = new JLabel("月");
	JLabel labelCount = new JLabel("番目");
	JLabel labelDoW = new JLabel("曜日");
	JLabel labelName = new JLabel("祝日名称:");
	JLabel labelHoli = new JLabel("連休有無");
	JRadioButton radioHoli1 = new JRadioButton("有");
	JRadioButton radioHoli2 = new JRadioButton("無");
	JRadioButton radiotype1 = new JRadioButton("日付");
	JRadioButton radiotype2 = new JRadioButton("特殊");
	ButtonGroup Holiday = new ButtonGroup();
	ButtonGroup type = new ButtonGroup();

	JPanel top;
	JPanel center;
	private HashMap<String, Integer> DowIndex = new HashMap<>();
	{
		DowIndex.put("月",1);
		DowIndex.put("火",2);
		DowIndex.put("水",3);
		DowIndex.put("木",4);
		DowIndex.put("金",5);
		DowIndex.put("土",6);
		DowIndex.put("日",0);
	};
	MkfileWindow() {

		setTitle("休日SET");
		setBounds(200, 200, 450, 250);
		setResizable(false);
		setVisible(true);

		top = new JPanel();
		center = new JPanel();
		grid = new GridBagLayout();
		gridcon = new GridBagConstraints();

		JButton addbtn = new JButton("追加");
		addbtn.addActionListener(this);
		addbtn.setActionCommand("addfile");

		Holiday.add(radioHoli1);
		Holiday.add(radioHoli2);
		type.add(radiotype1);
		type.add(radiotype2);

		radiotype1.addItemListener(this);
		radiotype2.addItemListener(this);
		radioHoli1.addItemListener(this);
		radioHoli2.addItemListener(this);

		radiotype1.setSelected(true);
		radioHoli2.setSelected(true);
		month2.setEditable(false);
		day2.setEditable(false);
		day1.setNextFocusableComponent(holiName);

		top.setLayout(grid);
		center.setLayout(grid);

		gridset(gridcon, 0, 0, 0, 0.1);
		grid.setConstraints(labelTitle, gridcon);
		top.add(labelTitle);

		gridset(gridcon, 0, 0, 0, 0.1);
		grid.setConstraints(radiotype1, gridcon);
		center.add(radiotype1);

		gridset(gridcon, 1, 0, 0, 0.1);
		grid.setConstraints(month1, gridcon);
		center.add(month1);

		gridset(gridcon, 2, 0, 0, 0);
		grid.setConstraints(labelMonth, gridcon);
		center.add(labelMonth);

		gridset(gridcon, 3, 0, 0, 0);
		grid.setConstraints(day1, gridcon);
		center.add(day1);

		gridset(gridcon, 4, 0, 0, 0);
		grid.setConstraints(labelDay, gridcon);
		center.add(labelDay);

		gridset(gridcon, 5, 0, 0, 0);
		grid.setConstraints(month2, gridcon);
		center.add(month2);

		gridset(gridcon, 6, 0, 0, 0);
		grid.setConstraints(labelMonth2, gridcon);
		center.add(labelMonth2);

		gridset(gridcon, 7, 0, 0, 0);
		grid.setConstraints(day2, gridcon);
		center.add(day2);

		gridset(gridcon, 8, 0, 0, 0);
		grid.setConstraints(labelDay2, gridcon);
		center.add(labelDay2);

		gridset(gridcon, 0, 1, 0, 0.1);
		grid.setConstraints(radiotype2, gridcon);
		center.add(radiotype2);

		gridset(gridcon, 1, 1, 0, 0.1);
		grid.setConstraints(ComboMonth, gridcon);
		center.add(ComboMonth);

		gridset(gridcon, 2, 1, 0, 0);
		grid.setConstraints(labelMonth3, gridcon);
		center.add(labelMonth3);

		gridset(gridcon, 3, 1, 0, 0);
		grid.setConstraints(ComboCount, gridcon);
		center.add(ComboCount);

		gridset(gridcon, 4, 1, 0, 0);
		grid.setConstraints(labelCount, gridcon);
		center.add(labelCount);

		gridset(gridcon, 5, 1, 0, 0);
		grid.setConstraints(ComboDoW, gridcon);
		center.add(ComboDoW);

		gridset(gridcon, 6, 1, 0, 0);
		grid.setConstraints(labelDoW, gridcon);
		center.add(labelDoW);

		gridset(gridcon, 0, 2, 0, 0.1);
		grid.setConstraints(labelName, gridcon);
		center.add(labelName);

		gridset(gridcon, 1, 2, 0, 0);
		gridcon.gridwidth = 3;
		grid.setConstraints(holiName, gridcon);
		center.add(holiName);

		gridset(gridcon, 4, 2, 0, 0);
		gridcon.gridwidth = 2;
		grid.setConstraints(labelHoli, gridcon);
		center.add(labelHoli);

		gridset(gridcon, 6, 2, 0, 0);
		gridcon.gridwidth = 1;
		grid.setConstraints(radioHoli1, gridcon);
		center.add(radioHoli1);

		gridset(gridcon, 7, 2, 0, 0);
		grid.setConstraints(radioHoli2, gridcon);
		center.add(radioHoli2);


		gridset(gridcon, 0, 3, 0, 0.1);
		grid.setConstraints(addbtn, gridcon);
		center.add(addbtn);

		getContentPane().add(top, BorderLayout.NORTH);
		getContentPane().add(center, BorderLayout.CENTER);
	}
	//祝日名称の入力チェック
	public boolean isNameCheck() {
		boolean result = true;
		if(holiName.getText() == null || holiName.getText().equals("")) {
			JOptionPane.showMessageDialog(this, INFOR_NAME, INFOR_M, JOptionPane.INFORMATION_MESSAGE);
			result = false;
		}
		return result;
	}
	//入力値の数字チェック
	private boolean isNotString(String str) {
		boolean result=true;
		try {
			Integer.parseInt(str);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
	//日付のnullチェック
	public boolean isNullCheck() {
		boolean result = true;
		String message= "";
		if(radiotype1.isSelected()) {
			if(isNotString(month1.getText()) && isNotString(day1.getText())) {
				if(month1.getText() == null || month1.getText().equals("")) {
					result = false;
				}
				else if(day1.getText() == null || day1.getText().equals("")) {
					result = false;
				}
				else if (Integer.parseInt(day1.getText()) < 1 || getMaxDate(Integer.parseInt(month1.getText())) < Integer.parseInt(day1.getText())) {
					message = month1.getText() + "月は1日から" + getMaxDate(Integer.parseInt(month1.getText())) +"日までです。";
					result = false;
				}
				if(radioHoli1.isSelected()) {
					if(isNotString(month2.getText()) && isNotString(day2.getText())) {
						if(month2.getText() == null || month2.getText().equals("")) {
							result = false;
						}
						else if(day2.getText() == null || day2.getText().equals("")) {
							result = false;
						}
						else if (Integer.parseInt(day2.getText()) < 1 || getMaxDate(Integer.parseInt(month2.getText())) < Integer.parseInt(day2.getText())) {
							message = month2.getText() + "月は1日から" + getMaxDate(Integer.parseInt(month2.getText())) +"日までです。";
							result = false;
						}
					}
					else {
						message = "数字で入力してください。";
						result = false;
					}
				}
				if(Integer.parseInt(month1.getText()) < 1 || Integer.parseInt(month1.getText()) >12) {
					message = "月は1から12までです。";
					result = false;
				}
			}
			else {
				message = "数字で入力してください。";
				result = false;
			}
		}
		else if(radiotype2.isSelected()) {

			String mon = ComboMonth.getSelectedItem().toString();
			String count = ComboCount.getSelectedItem().toString();
			String DoW = ComboDoW.getSelectedItem().toString();
			int dowdate = DowIndex.get(DoW);

			System.out.println(mon + "月" + count + "番目" + DoW);
			if(mon.equals("-") ||count.equals("-") ||DoW.equals("-")){
				result = false;
			}
			int date = DoWdate(Integer.parseInt(mon), dowdate, Integer.parseInt(count));
			if(getMaxDate(Integer.parseInt(mon)) < date) {
				message = mon + "月の" + DoW + "は" + (Integer.parseInt(count)-1) + "番目までです。";
				result = false;
			}
		}
		if(result == false) {
			JOptionPane.showMessageDialog(this, message + "\n" +INFOR_DAY, INFOR_M, JOptionPane.INFORMATION_MESSAGE);
		}
		return result;
	}
	//○月○番目○日の日付計算
	private int DoWdate(int month, int DoW, int count){
		int maxdate = getMaxDate(month);
		int startDay = yobi(year, month);
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=1;i<=7;i++) {
			map.put(startDay, i);
			startDay++;
			if(startDay==7) {
				startDay=0;
			}
		}
		int result = map.get(DoW);//曜日の日を持って来る。
		result +=7*(count-1);
		return result;
	}
	private void gridset(GridBagConstraints con, int x, int y, double wx, double wy)
	{
		con.gridx = x;
		con.gridy = y;
		con.weightx = wx;
		con.weighty = wy;
	}
	//閏年の判別
	private boolean isUrudosi(int n)
	{
		if(n % 400 == 0)
		{
			return true;
		}
		else if(n % 100 == 0)
		{
			return false;
		}
		else if(n % 4 == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//該当月の最初の曜日を計算0～6(日～土)
	private int yobi(int y, int m) {
		int yobi = 0;
		for (int i = 1; i < y; i++) {
			if (isUrudosi(i)) {
				yobi += 366;
			} else {
				yobi += 365;
			}
		}
		if (isUrudosi(y)) {
			for (int j = 0; j < m - 1; j++) {
				yobi += URUDOSI_M[j];
			}
		} else {
			for (int j = 0; j < m - 1; j++) {
				yobi += HEINEN_M[j];
			}
		}
		yobi = (yobi + 1) % 7;
		return yobi;
	}
	//入力した月のmax日計算
	private int getMaxDate(int month) {
		int maxdate=0;
		if(isUrudosi(year)) {
			maxdate = URUDOSI_M[month-1];
		}
		else {
			maxdate = HEINEN_M[month-1];
		}
		return maxdate;
	}
	private String getDate() {
		//日付選択
		String result = "";
		if(radiotype1.isSelected()) {
			int maxdate = getMaxDate(Integer.parseInt(month1.getText()));
			if(radioHoli1.isSelected()) {
				if(Integer.parseInt(month1.getText()) < Integer.parseInt(month2.getText())){
					for(int i=Integer.parseInt(day1.getText()); i<=maxdate; i++) {
						result += month1.getText() + "/" + i + "\t";
					}
					for(int i=1; i<=Integer.parseInt(day2.getText()); i++) {
						result += month2.getText() + "/" + i + "\t";
					}
				}
				else {
					for(int i=Integer.parseInt(day1.getText()); i<=Integer.parseInt(day2.getText()); i++) {
						result += month1.getText() + "/" + i + "\t";
					}
				}
			}else if(radioHoli2.isSelected()) {
				result = month1.getText() + "/" + day1.getText();
			}
			result = Normalizer.normalize(result, Normalizer.Form.NFKC);
		}else if(radiotype2.isSelected()){
			result = ComboMonth.getSelectedItem().toString() + "-" + ComboCount.getSelectedItem().toString() + "-" + DowIndex.get(ComboDoW.getSelectedItem().toString());
		}
		return result;
	}
	public String changeHalf(String str) {
		String result = null;
		StringBuilder sb = new StringBuilder(str);
		for(int i = 0; i< sb.length(); i++) {
			int c = (int)sb.charAt(i);
			if(c >= 0xFF10 && c <= 0xFF19) {
				sb.setCharAt(i, (char)(c - 0xFEE0));
			}
		}
		result = sb.toString();
		return result;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(radiotype1.isSelected()) {
			ComboMonth.setEnabled(false);
			ComboCount.setEnabled(false);
			ComboDoW.setEnabled(false);
			ComboMonth.setSelectedIndex(0);
			ComboCount.setSelectedIndex(0);
			ComboDoW.setSelectedIndex(0);
			month1.setEditable(true);
			day1.setEditable(true);
			radioHoli1.setEnabled(true);
			radioHoli2.setEnabled(true);

		}else if(radiotype2.isSelected()) {
			ComboMonth.setEnabled(true);
			ComboCount.setEnabled(true);
			ComboDoW.setEnabled(true);
			month1.setEditable(false);
			day1.setEditable(false);
			month1.setText("");
			day1.setText("");
			radioHoli2.setSelected(true);
			radioHoli1.setEnabled(false);
			radioHoli2.setEnabled(false);
		}
		if(radioHoli1.isSelected()) {
			month2.setEditable(true);
			day2.setEditable(true);
			day1.setNextFocusableComponent(month2);
		}
		else if(radioHoli2.isSelected()){
			month2.setEditable(false);
			day2.setEditable(false);
			month2.setText("");
			day2.setText("");
			day1.setNextFocusableComponent(holiName);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("addfile") && isNameCheck() == true && isNullCheck() == true) {
			BufferedOutputStream bs = null;

			try {
				String fileName = holiName.getText();
				String str = getDate();
				bs = new BufferedOutputStream(new FileOutputStream("C:\\pleiades\\workspace\\Karenda\\date\\" + fileName + ".txt"));
				bs.write(str.getBytes());
				dispose();
			} catch(Exception e1) {
				e1.getStackTrace();
			}finally {
				try {
					bs.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}
}
