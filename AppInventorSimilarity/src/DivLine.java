import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class DivLine extends JFrame {

 private double width = 950.;
 private double height = 700.;
 private double rate = 2.;
 private int k = 3;

 private Vector<String> nameList = new Vector<String>();
 private ArrayList<XMLDoc> xmlList_save = new ArrayList<XMLDoc>();

 private JList xmlList_name; // 비교할 파일 목록
 private JButton addBtn = new JButton("파일 추가"); // 파일 추가
 private JButton deleteBtn = new JButton("파일 제거"); // 파일 빼기
 private JButton clearBtn = new JButton("모두 삭제"); // 모든 파일 제거
 private JButton startBtn = new JButton("유사도"); // 유사도 구하기
 private JButton setKBtn = new JButton("k값 재설정");
 private JTextArea resultScreen = new JTextArea(); // 유사도 결과창
 private JLabel getK = new JLabel("K = 3",JLabel.CENTER);
 private String cols[] = { "Target 1\n(Line)", "Target 2\n(Line)","Line" ,"Similarity" };
 private JTable resultTable = new JTable();
 private JScrollPane resultScreenScroll = new JScrollPane(resultScreen);
 private JScrollPane listScroll;
 private JFileChooser docOpen = new JFileChooser();
 private JLabel listname = new JLabel("File List",JLabel.CENTER);
 private Container c;

 DivLine() {
  setTitle("앱 인벤터 유사도 비교 ver.1.0");
  c = getContentPane();
  c.setLayout(null);
  setVisible(true);
  setSize((int) (width * rate), (int) (height * rate));
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  xmlList_name = new JList<String>();
  listScroll = new JScrollPane(xmlList_name);

  createList();
  createScreen();
  addEvent();

 }

 public void createList() {
  c.add(listScroll);
  xmlList_name.setSize((int) (300 * rate), (int) (520 * rate));
  listScroll.setSize((int) (250 * rate), (int) (470 * rate));
  listScroll.setLocation((int) (650 * rate), (int)(50*rate));
  xmlList_name.setVisible(true);
  listScroll.setVisible(true);

 }

 public void createScreen() {

  addBtn.setLocation((int) (640 * rate), (int) (530 * rate));
  deleteBtn.setLocation((int) (730 * rate), (int) (530 * rate));
  clearBtn.setLocation((int) (820 * rate), (int) (530 * rate));
  startBtn.setLocation((int) (820 * rate), (int) (580 * rate));
  getK.setLocation((int) (640 * rate), (int) (580 * rate));
  setKBtn.setLocation((int) (730 * rate), (int) (580 * rate));
  resultScreenScroll.setLocation((int) (50), 50);
  resultScreenScroll.setViewportView(resultScreen);
  listname.setLocation((int)(640 * rate),(int)(25*rate));

  xmlList_name.setFont(new Font("궁서", Font.CENTER_BASELINE, (int) (rate * 30.0)));
  getK.setFont(new Font("궁서", Font.CENTER_BASELINE, (int) (rate * 20.0)));
  resultScreen.setFont(new Font("궁서", Font.CENTER_BASELINE, (int) (rate * 20.0)));
  resultScreen.setSize((int) (rate * 900), (int) (rate * 600));
  resultTable.setSize((int) (rate * 600), (int) (rate * 600));
  resultScreenScroll.setSize((int) (rate * 600), (int) (rate * 600));
  listname.setSize((int)(rate * 250),(int)(25*rate));
  resultScreen.setEditable(false);

  addBtn.setSize((int) (90 * rate), (int) (50 * rate));
  deleteBtn.setSize((int) (90 * rate), (int) (50 * rate));
  clearBtn.setSize((int) (90 * rate), (int) (50 * rate));
  startBtn.setSize((int) (90 * rate), (int) (50 * rate));
  getK.setSize((int) (90 * rate), (int) (50 * rate));
  setKBtn.setSize((int) (90 * rate), (int) (50 * rate));
     
  listname.setFont(new Font("궁서", Font.CENTER_BASELINE, (int) (rate * 20.0)));
  addBtn.setFont(new Font("궁서", Font.CENTER_BASELINE, (int) (rate * 10.0)));
  deleteBtn.setFont(new Font("궁서", Font.CENTER_BASELINE, (int) (rate * 10.0)));
  clearBtn.setFont(new Font("궁서", Font.CENTER_BASELINE, (int) (rate * 10.0)));
  startBtn.setFont(new Font("궁서", Font.CENTER_BASELINE, (int) (rate * 10.0)));
  getK.setFont(new Font("궁서", Font.CENTER_BASELINE, (int) (rate * 20.0)));
  setKBtn.setFont(new Font("궁서", Font.CENTER_BASELINE, (int) (rate * 10.0)));

  c.add(resultScreenScroll, BorderLayout.CENTER);
  c.add(addBtn, BorderLayout.CENTER);
  c.add(deleteBtn, BorderLayout.CENTER);
  c.add(clearBtn, BorderLayout.CENTER);
  c.add(startBtn, BorderLayout.CENTER);
  c.add(getK, BorderLayout.CENTER);
  c.add(setKBtn, BorderLayout.CENTER);
  c.add(listname,BorderLayout.CENTER);

  addBtn.setVisible(true);
  deleteBtn.setVisible(true);

  clearBtn.setVisible(true);
  startBtn.setVisible(true);
  getK.setVisible(true);
  setKBtn.setVisible(true);

 }

 public void addEvent() {
  addBtn.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    if (docOpen.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
     File file = docOpen.getSelectedFile();
     String name = JOptionPane.showInputDialog("파일을 구분하기 위한 이름을 입력하시오");

     if (!name.equals(JOptionPane.CANCEL_OPTION)) {
      nameList.add(name);
      XMLDoc a = new XMLDoc(name, k);
      xmlList_save.add(a);
      try {
       makeLine(file.getAbsolutePath(), a);
       xmlList_name.setListData(nameList);

      } catch (IOException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
      }
     }
    }
   }
  });
  startBtn.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

    resultScreen.setText("K값 : " + k + "\n");
    String result[][] = new String[(xmlList_save.size() * (xmlList_save.size() - 1)) / 2][4];
    int length = xmlList_save.size() - 1;
    System.out.println("K값  : " + k);

    for (int i = 0, index = 0; i < xmlList_save.size(); i++) {
     for (int j = i + 1; j < xmlList_save.size(); j++, index++) {
      
      double son = similar(xmlList_save.get(i), xmlList_save.get(j));
      result[index][2] = String.format("%.0f", son);
      result[index][3] = String
        .format("%.2f",(son / Math.min(xmlList_save.get(i).getKLineLength(),xmlList_save.get(j).getKLineLength())) * 100);
      result[index][0] = xmlList_save.get(i).getName() + "\n(" + xmlList_save.get(i).getKLineLength() + ")";
      result[index][1] = xmlList_save.get(j).getName() +  "\n(" + xmlList_save.get(j).getKLineLength() + ")";
     }
    }

    DefaultTableCellRenderer dctr = new DefaultTableCellRenderer();
    dctr.setHorizontalAlignment(SwingConstants.CENTER);

    resultTable = new JTable(result, cols);
    TableColumnModel tcm = resultTable.getColumnModel();

    for (int i = 0; i < tcm.getColumnCount(); i++)
     tcm.getColumn(i).setCellRenderer(dctr);
    resultTable.setFont(new Font("궁서", Font.CENTER_BASELINE, (int) (rate * 25.0)));
    resultTable.getTableHeader().setFont(new Font("궁서", Font.CENTER_BASELINE, (int) (rate * 20.0)));
    resultTable.setRowHeight((int) (rate * 50.0));
    resultTable.getTableHeader().setSize((int) (rate * 50.0),(int) (rate * 100.0));
    
    resultScreenScroll.setViewportView(resultTable);
    resultTable.setVisible(true);
    System.out.println("\n\n---------------------------------------------------");

   }

  });

  setKBtn.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    k = Integer.parseInt(JOptionPane.showInputDialog("k값을 입력하세요."));
    getK.setText("K = " + Integer.toString(k));
    for (int i = 0; i < xmlList_save.size(); i++) {
     xmlList_save.get(i).setK(k);
     xmlList_save.get(i).setK();
    }
   }

  });

  deleteBtn.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    if (!xmlList_name.isSelectionEmpty()) {
     int x = xmlList_name.getSelectedIndex();

     xmlList_save.remove(x);
     nameList.remove(x);
     xmlList_name.setListData(nameList);
    }
   }
  });

  clearBtn.addActionListener(new ActionListener() {

   @Override
   public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

    xmlList_save.clear();
    nameList.clear();
    xmlList_name.setListData(nameList);
   }
  });
 }

 public void similarIgnoreCase(XMLDoc doc_1, XMLDoc doc_2) {
  doc_1.usedSet();
  doc_2.usedSet();

  double son = 0.;

  for (int i = 0; i < doc_1.getKLineLength(); i++) {
   String doc1[] = doc_1.getKLine(i).split(",");
   for (int j = 0; j < doc_2.getKLineLength(); j++) {
    if (!doc_2.getUsedElement(j)) {
     String doc2[] = doc_2.getKLine(j).split(",");

     if (doc1.length == doc2.length) {

     }

    }
   }
  }
 }

 public double similar(XMLDoc doc_1, XMLDoc doc_2) {
  double son = 0.;
  doc_1.usedSet();
  doc_2.usedSet();

  for (int i = 0; i < doc_1.getKLineLength(); i++) {
   for (int j = 0; j < doc_2.getKLineLength(); j++) {
    if (!doc_2.getUsedElement(j) && doc_1.getKLine(i).equals(doc_2.getKLine(j))) {
     son += 1.;
     doc_2.used(j);
     break;
    }
   }
  }

  return (son);
 }

 public void makeLine(String path, XMLDoc doc) throws IOException {
  BufferedReader xml_1 = new BufferedReader(new FileReader(path));
  ArrayList<String> xmlList = new ArrayList<String>();
  String line = xml_1.readLine();

  while ((line = xml_1.readLine()) != null) {

   if (line.contains("<block ")) {
    line = removeUseless(line, "id=\"");
   }
   if (line.contains(" instance_name=\"")) {
    line = removeUseless(line, "instance_name=\"");
   }
   if (line.contains("x=\"")) {
    line = removeUseless(line, "x=\"");
   }
   if (line.contains("y=\"")) {
    line = removeUseless(line, "y=\"");
   }
   if (line.contains("<field name=\"")) {
    if (line.contains("COMPONENT_SELECTOR") || line.contains("NAME") || line.contains("PROCNAME")
      || line.contains("VAR") || line.contains("NUM")) {
     line = line.replace(removeVarName(line), "");
    }
   }
   if (line.contains("inline=\"")) {
    line = removeUseless(line, "inline=\"");
   }
   if (line.contains("<mutation name=") || line.contains("<arg name=")) {
    line = line.replace(removeUselessName(line), "");
   }
   if (!line.contains("  </") && !line.contains("</xml>") && !line.contains("  <next>")) {
    xmlList.add(line.trim());
   }
  }

  doc.setLength(xmlList.size());
  doc.setK(k);
  for (int i = 0; i < xmlList.size(); i++) {
   doc.addElement(xmlList.get(i), i);

  }
  doc.setK();

  for (int i = 0; i < xmlList.size() - k + 1; i++) {
   // System.out.println(doc.getLine(i));
   System.out.println(doc.getKLine(i));
   resultScreen.append(doc.getKLine(i) + "\n");
  }
 }

 private String removeUseless(String line, String uselessTag) {
  StringTokenizer token = new StringTokenizer(line);
  String useless = new String();
  while (token.hasMoreTokens()) {
   useless = token.nextElement().toString();

   if (useless.contains(uselessTag)) {
    line = line.replace(useless + " ", "");
    line = line.replace(useless, "");
    if (!line.endsWith(">")) {
     line = line.substring(0, line.length() - 1);
     line = line.concat(">");
    }
    return line;
   }
  }
  return line;
 }

 private String removeVarName(String line) {
  String useless = "";
  StringTokenizer token = new StringTokenizer(line, "<>", true);
  String previous = "";
  String next = "";
  while (token.hasMoreTokens()) {
   previous = next;
   next = token.nextToken();

   if (previous.equals(">")) {
    useless = next;
    break;
   }
  }
  return useless;
 }

 private String removeUselessName(String line) {
  String useless = "";
  StringTokenizer token = new StringTokenizer(line, "\"", true);
  String previous;
  String next = "";
  while (token.hasMoreTokens()) {
   previous = next;
   next = token.nextToken();
   if (previous.equals("\"")) {
    useless = next;
    break;
   }
  }
  return useless;
 }
}