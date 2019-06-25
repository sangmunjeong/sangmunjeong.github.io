import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Karenda extends JFrame implements ActionListener,KeyListener {

	Calendar cal = Calendar.getInstance();
	int start = 0;
	int end = 0;
	int year = 0;
	int month = 0;
	List HolidayList = new ArrayList();
	int[] date = new int[3];
	final static int START_YEAR = 1851; //カレンダー最初
	final static int END_YEAR = 2051; //カレンダー最後
	static String INFOR_YEAR = START_YEAR + "年から" + END_YEAR + "年まで入力してください。";
	final String INFOR_MONTH = "1月から12月まで入力してください。";
	final String TENNOU = "2/23"; // y >= 2020
	static String INFOR_M = "メッセージ";
	int[] URUDOSI_M = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	int[] HEINEN_M = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	JTextField yearT = new JTextField("");
	JTextField monthT = new JTextField("");
	JTextField[] txt = new JTextField[48];

	GridBagLayout grid;
	GridBagConstraints gridcon;
	JPanel topP;
	JPanel centerP;
	boolean flag = false;
	boolean iskongetu = false;
	boolean isDaitai = false;

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

	Karenda(String title)
	{
		setTitle(title);
		setBounds(100, 100, 630, 520);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		readfile();

		grid = new GridBagLayout();
		gridcon = new GridBagConstraints();
		topP = new JPanel();
		centerP = new JPanel();

		topP.setLayout(grid);
		centerP.setLayout(grid);

		JButton btn1 = new JButton("◀");
		JButton btn2 = new JButton("▶");
		JButton today = new JButton("Today");
		JButton mkfile = new JButton("祝日追加");
		mkfile.addActionListener(this);
		mkfile.setActionCommand("mkfile");
		btn1.addActionListener(this);
		btn1.setActionCommand("btn1");
		btn2.addActionListener(this);
		btn2.setActionCommand("btn2");
		today.addActionListener(this);
		today.setActionCommand("Today");

		yearT = new JTextField(Integer.toString(cal.get(Calendar.YEAR)), 4);
		monthT = new JTextField(Integer.toString(cal.get(Calendar.MONTH) + 1), 2);
		date[0] = cal.get(Calendar.YEAR);
		date[1] = cal.get(Calendar.MONTH) + 1;
		date[2] = cal.get(Calendar.DATE);
		yearT.setFont(new Font("arian", Font.PLAIN, 15));
		yearT.setHorizontalAlignment(JTextField.CENTER);
		monthT.setFont(new Font("arian", Font.PLAIN, 15));
		monthT.setHorizontalAlignment(JTextField.CENTER);

		JTextField nen = new JTextField("年");
		nen.setFont(new Font("arian", Font.PLAIN, 15));
		nen.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 2));
		nen.setEditable(false);
		JTextField getu = new JTextField("月");
		getu.setFont(new Font("arian", Font.PLAIN, 15));
		getu.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 2));
		getu.setEditable(false);
		JTextField sun = new JTextField("Sun");
		sun.setHorizontalAlignment(JTextField.CENTER);
		sun.setForeground(Color.RED);
		sun.setFont(new Font("arian", Font.BOLD, 15));
		sun.setEditable(false);
		JTextField mon = new JTextField("Mon");
		mon.setHorizontalAlignment(JTextField.CENTER);
		mon.setFont(new Font("arian", Font.BOLD, 15));
		mon.setEditable(false);
		JTextField tue = new JTextField("Tue");
		tue.setHorizontalAlignment(JTextField.CENTER);
		tue.setFont(new Font("arian", Font.BOLD, 15));
		tue.setEditable(false);
		JTextField wed = new JTextField("Wed");
		wed.setHorizontalAlignment(JTextField.CENTER);
		wed.setFont(new Font("arian", Font.BOLD, 15));
		wed.setEditable(false);
		JTextField thu = new JTextField("Thu");
		thu.setHorizontalAlignment(JTextField.CENTER);
		thu.setFont(new Font("arian", Font.BOLD, 15));
		thu.setEditable(false);
		JTextField fri = new JTextField("Fri");
		fri.setHorizontalAlignment(JTextField.CENTER);
		fri.setFont(new Font("arian", Font.BOLD, 15));
		fri.setEditable(false);
		JTextField sat = new JTextField("Sat");
		sat.setHorizontalAlignment(JTextField.CENTER);
		sat.setFont(new Font("arian", Font.BOLD, 15));
		sat.setForeground(Color.BLUE);
		sat.setEditable(false);

		gridset(gridcon, 0, 0, 0, 0);
		gridcon.insets.left = 8;
		gridcon.insets.top = 10;
		gridcon.insets.bottom = 10;
		grid.setConstraints(mkfile, gridcon);
		topP.add(mkfile);

		gridset(gridcon, 1, 0, 0, 0);
		gridcon.insets.left = 8;
		gridcon.insets.top = 10;
		gridcon.insets.bottom = 10;
		grid.setConstraints(btn1, gridcon);
		topP.add(btn1);

		gridset(gridcon, 2, 0, 0, 0);
		grid.setConstraints(yearT, gridcon);
		yearT.setNextFocusableComponent(monthT);
		topP.add(yearT);

		gridset(gridcon, 3, 0, 0, 0);
		grid.setConstraints(nen, gridcon);
		topP.add(nen);

		gridset(gridcon, 4, 0, 0, 0);
		grid.setConstraints(monthT, gridcon);
		monthT.setNextFocusableComponent(yearT);
		topP.add(monthT);

		gridset(gridcon, 5, 0, 0, 0);
		grid.setConstraints(getu, gridcon);
		topP.add(getu);

		gridset(gridcon, 6, 0, 0, 0);
		grid.setConstraints(btn2, gridcon);
		topP.add(btn2);

		gridset(gridcon, 7, 0, 0, 0);
		grid.setConstraints(today, gridcon);
		topP.add(today);

		gridcon.insets.left = 0;
		gridcon.insets.top = 0;
		gridcon.insets.bottom = 0;
		gridcon.fill = GridBagConstraints.BOTH;

		gridset(gridcon, 0, 0, 1, 0.2);
		centerP.add(sun, gridcon);
		gridset(gridcon, 1, 0, 1, 0.2);
		centerP.add(mon, gridcon);
		gridset(gridcon, 2, 0, 1, 0.2);
		centerP.add(tue, gridcon);
		gridset(gridcon, 3, 0, 1, 0.2);
		centerP.add(wed, gridcon);
		gridset(gridcon, 4, 0, 1, 0.2);
		centerP.add(thu, gridcon);
		gridset(gridcon, 5, 0, 1, 0.2);
		centerP.add(fri, gridcon);
		gridset(gridcon, 6, 0, 1, 0.2);
		centerP.add(sat, gridcon);


		year = Integer.parseInt(yearT.getText());
		month = Integer.parseInt(monthT.getText());

		gridcon.fill = GridBagConstraints.BOTH;

		create();

		setDay(year, month);

		monthT.addKeyListener(this);
		yearT.addKeyListener(this);
		getContentPane().setFocusable(true);
		getContentPane().add(topP, BorderLayout.NORTH);
		getContentPane().add(centerP, BorderLayout.CENTER);
	}
	//カレンダーの生成
	private void create()
	{
		int index = 0;
		for(int i = 1;i <= 6;i++)// iはカレンダーの行の数(6)
		{
			for(int j = 0;j < 7;j++)// jは曜日の値、0～6は日曜日～土曜日
			{
				gridset(gridcon, j, i, 1, 1);

				centerP.add(txt[index] = new JTextField("0"), gridcon);
				txt[index].setFont(new Font("arian", Font.PLAIN, 20));
				txt[index].setBackground(Color.WHITE);
				txt[index].setHorizontalAlignment(JTextField.CENTER);
				txt[index].setForeground(Color.GRAY);
				txt[index].setEditable(false);

				index++;
			}
		}
	}
	//正しくない年・月が入った時
	private boolean popup(int y, int m)
	{
		if(y < START_YEAR || y > END_YEAR)
		{
			JOptionPane.showMessageDialog(this, INFOR_YEAR, INFOR_M, JOptionPane.INFORMATION_MESSAGE);
			yearT.setText(Integer.toString(date[0]));
			monthT.setText(Integer.toString(date[1]));
			return true;
		}
		else if(m < 1 || m > 12 )
		{
			JOptionPane.showMessageDialog(this, INFOR_MONTH, INFOR_M, JOptionPane.INFORMATION_MESSAGE);
			yearT.setText(Integer.toString(date[0]));
			monthT.setText(Integer.toString(date[1]));
			return true;
		}
		return false;
	}
	private void gridset(GridBagConstraints con, int x, int y, double wx, double wy)
	{
		con.gridx = x;
		con.gridy = y;
		con.weightx = wx;
		con.weighty = wy;
	}
	//閏年判断
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
	//該当月の最初の曜日を計算
	private int yobi(int y,int m)
	{
		int yobi = 0;
		for(int i = 1;i < y;i++)
		{
			if(isUrudosi(i))
			{
				yobi += 366;
			}
			else
			{
				yobi += 365;
			}
		}
		if(isUrudosi(y))
		{
			for(int j = 0;j < m - 1;j++)
			{
				yobi += URUDOSI_M[j];
			}
		}
		else
		{
			for(int j = 0;j < m - 1;j++)
			{
				yobi += HEINEN_M[j];
			}
		}
		yobi = (yobi + 1) % 7;
		return yobi;
	}
	////今月の日を表示する前に先月を表示するため呼び出す。
	private int sengetu(int y,int m)
	{
		int dday = 0;
		if(isUrudosi(y)) //閏年の場合
		{
			end = URUDOSI_M[m - 1]; //先月の日を呼ぶ
			if(m == 1)
			{
				dday = URUDOSI_M[11] - start + 1; //先月が1月なら12月を呼ぶ
			}
			else
			{
				dday = URUDOSI_M[m - 2] - start + 1;
			}
		}
		else //閏年ではない場合
		{
			end = HEINEN_M[m - 1]; //先月の日を呼ぶ
			if(m == 1)
			{
				dday = HEINEN_M[11] - start + 1; //先月が1月なら12月を呼ぶ
			}
			else
			{
				dday = HEINEN_M[m - 2] - start + 1;
			}
		}
		return dday;
	}

	//春分の日
	private boolean day1(int y, int m, int d, int j, int count)
	{
		if(y < 1948)
		{
			return false;
		}
		else if(y == 1923 && m == 3 && d == 22)
		{
			if(j == 0)//日曜日
			{
				isDaitai = true;
			}
			return true;
		}
		else if(y == 2092 && m == 3 && d == 19)
		{
			if(j == 0)//日曜日
			{
				isDaitai = true;
			}
			return true;
		}
		else if(isUrudosi(y) == true && m == 3 && d == 20 || isUrudosi(y - 1) == true && m == 3 && d == 20)
		{
			if(j == 0)//日曜日
			{
				isDaitai = true;
			}
			return true;
		}
		else if(m == 3 && d == 21)
		{
			if(j == 0)//日曜日
			{
				isDaitai = true;
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	//秋分の日
	private boolean day2(int y, int m, int d, int j, int count)
	{
		if(y < 1948)
		{
			return false;
		}
		else if(y == 1979 && m == 9 && d == 24 || y == 2103 && m == 9 && d == 24)
		{
			if(j == 0)//日曜日
			{
				isDaitai = true;
			}
			return true;
		}
		else if(y < 2012 && m == 9 && d == 23)
		{
			if(j == 0)//日曜日
			{
				isDaitai = true;
			}
			return true;
		}
		else if(y <= 2044 && isUrudosi(y) == true && m == 9 && d == 22)
		{
			if(j == 0)//日曜日
			{
				isDaitai = true;
			}
			return true;
		}
		else if(y <= 2044 && isUrudosi(y) == false && m == 9 && d == 23)
		{
			if(j == 0)//日曜日
			{
				isDaitai = true;
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	//HolidayListの日付適用
	private boolean holiday(int y, int m, int d, int j, int count) {
		for (int i = 0; i < HolidayList.size(); i++) {
			String date = (String) HolidayList.get(i);
			String[] dy = date.split("/");
			;
			int first = Integer.parseInt(dy[0]);
			int last = Integer.parseInt(dy[1]);
			if (m == first && d == last) {
				if (j == 0)//日曜日
				{
					isDaitai = true;
				}
				return true;
			}
		}
		return false;

	}
	private void syukuzitu(int y, int m, int dday, int start)
	{
		int day = 0;//今月の日
		int index = 0;
		int count = 0;//何番目週
		for(int i = 1;i < 7;i++) // iはカレンダーの行の数(6)
		{
			for(int j = 0;j < 7;j++) // jは曜日の値、0～6は日曜日～土曜日
			{
				if(start == index)
				{
					flag = true;
					iskongetu = true;
				}
				if(flag == true)//今月の日
				{
					day++;
					txt[index].setText(Integer.toString(day));
					txt[index].setForeground(Color.BLACK);
					if(j == 1)
					{
						count++;
					}
				}
				else//先月・来月の日
				{
					txt[index].setText(Integer.toString(dday));
					txt[index].setForeground(Color.GRAY);
					dday++;
				}
				//color 日、土
				if(j == 0 && iskongetu == true)
				{
					txt[index].setForeground(Color.RED);
				}
				else if(j == 6 && iskongetu == true)
				{
					txt[index].setForeground(Color.BLUE);
				}
				//代替休日
				if(isDaitai == true && flag == true)
				{
					txt[index].setForeground(Color.RED);
					isDaitai = false;
				}
				//今月の日を全部表示したら
				if(end == day && iskongetu == true)
				{
					flag = false;
					iskongetu = false;
					dday = 1;//来月の表示
				}
				//color 祝日
				if(holiday(y, m, day, j, count) && flag == true)
				{
					txt[index].setForeground(Color.RED);
				}
				//春分、秋分(閏年の場合変わる日付では不正確)
				if(day1(y, m, day, j, count)&& flag || day2(y, m, day, j, count)&& flag)
				{
					txt[index].setForeground(Color.RED);
				}
				txt[index].setBackground(Color.WHITE);
				//今日の表示
				if(date[0] == y && date[1] == m && date[2] == day)
				{
					txt[index].setBackground(Color.LIGHT_GRAY);
				}
				index++;
			}
		}
	}
	//日の祝日や土曜日、日曜日を表示する
	private void setDay(int y, int m)
	{
		int dday = 0;//先月・来月の日
		start = yobi(y, m);
		dday = sengetu(y,m);//先月の日
		syukuzitu(y,m,dday,start);
		//画面 update
		this.update(this.getGraphics());
	}
	public void readfile() {
		try {
			String dirPath = "C:\\pleiades\\workspace\\Karenda\\date\\";
			File dir = new File(dirPath);
			File[] fileList = dir.listFiles();
			for(File file : fileList) {
//				System.out.println("file name :"+file.getName());

				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dirPath+file.getName()),"UTF-8"));

				String str = br.readLine();
				String[] day = null;
				day = str.split("\t");
				for(String date : day) {
					if(str.contains("/")) {
						HolidayList.add(date);
					}
					else if(str.contains("-")) {
						HolidayList.add(getdate(str));
					}
				}
				br.close();
				}
			}catch(Exception e) {
			e.getStackTrace();
		}
//		System.out.println(HolidayList);
	}
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
	private String getdate(String str) {

		String[] comboday;
		comboday = str.split("-");
		int numMonth = 0;
		int numCount = 0;
		int numDoW = 0;

		try {
			if(comboday[0].startsWith("uFEFF")) {
				numMonth = Integer.parseInt(comboday[0].substring(1));
			}
			else {
				numMonth = Integer.parseInt(comboday[0]);
			}
			numCount = Integer.parseInt(comboday[1]);
			numDoW = DowIndex.get(comboday[2]);
		}catch(Exception e){

		}

		int maxdate = getMaxDate(numMonth);
		int startDay = yobi(year, numMonth);
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=1;i<=7;i++) {
			map.put(startDay, i);
			startDay++;
			if(startDay==7) {
				startDay=0;
			}
		}
		int result = map.get(numDoW);//曜日の日を持って来る。
		result +=7*(numCount-1);
//		System.out.println(numMonth + "/" + result);

		return numMonth + "/" + result;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		int m = Integer.parseInt(monthT.getText());
		int y = Integer.parseInt(yearT.getText());
		boolean pop = false;
		//先月表示
		if(cmd.equals("btn1"))
		{
			if(m == 1)
			{
				y = y - 1;
				yearT.setText(Integer.toString(y));
				monthT.setText("12");
				m = 12;
				pop = popup(y, m);
			}
			else
			{
				m = m - 1;
				monthT.setText(Integer.toString(m));
				pop = popup(y, m);
			}
		}
		//来月表示
		else if(cmd.equals("btn2"))
		{
			if(m == 12)
			{
				y = y + 1;
				yearT.setText(Integer.toString(y));
				monthT.setText("1");
				m = 1;
				pop = popup(y, m);
			}
			else
			{
				m = m + 1;
				monthT.setText(Integer.toString(m));
				pop = popup(y, m);
			}
		}
		if(cmd.equals("Today")) {
			yearT.setText(Integer.toString(date[0]));
			monthT.setText(Integer.toString(date[1]));
			y = date[0];
			m = date[1];
			readfile();
		}
		if(cmd.equals("mkfile")) {
			MkfileWindow mkfile = new MkfileWindow();
			mkfile.setVisible(true);
			readfile();
		}
		getContentPane().requestFocusInWindow();
		if(pop == true)
		{
			y = date[0];
			m = date[1];
		}
		setDay(y, m);

	}
	@Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void keyPressed(KeyEvent e) {
		//入力制限
		int str = e.getKeyCode();
		if( (str > 95 && str < 106) || (str > 47 && str < 58) || str == 8 || str == 10 || str == 37 || str == 39)
		{
			monthT.setEditable(true);
			yearT.setEditable(true);
		}
		else
		{
			monthT.setEditable(false);
			yearT.setEditable(false);
		}
		//Enter
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			int y=date[0];
			int m=date[1];
			if(yearT.getText().equals("")) {
				y = 0;
			}
			else if(monthT.getText().equals("")) {
				m = 0;
			}
			else {
				y = Integer.parseInt(yearT.getText());
				m = Integer.parseInt(monthT.getText());
			}
			boolean pop = popup(y, m);
			if(pop == true)
			{
				y = date[0];
				m = date[1];
			}
			setDay(y, m);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {

	}
}
