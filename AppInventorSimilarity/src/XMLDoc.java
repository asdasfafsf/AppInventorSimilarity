class XMLDoc {
 private String docName = "";
 private String[] Line;
 private String[] kLine;
 private boolean[] used;
 private int k = 3;

 XMLDoc(String docName, int k) {
  this.docName = docName;
  this.k = k;
 }

 public void setLength(int length) {
  Line = new String[length];

 }

 public void setK(int k) {
  this.k = k;
  kLine = new String[Line.length - (k - 1)];
 }

 public void setK() {
  String a = "";
  for (int i = 0; i < kLine.length; i++) {
   a = Line[i];
   for (int j = 1; j < k; j++) {
    a = a.concat("," + Line[i + j]);
   }
   kLine[i] = a;
  }
 }

 public void addElement(String line, int index) {
  Line[index] = line;
 }

 public String getKLine(int index) {
  return kLine[index];
 }

 public void setLine(String[] Line) {
  this.Line = Line;
 }

 public String[] getLine() {
  return Line;
 }

 public String getLine(int i) {
  return Line[i];
 }

 public int getKLineLength() {
  return kLine.length;
 }

 public String getName() {
  return docName;
 }

 public void usedSet() {
  used = new boolean[kLine.length];
 }

 public void used(int index) {
  used[index] = true;
 }

 public boolean getUsedElement(int index) {
  return used[index];
 }

}