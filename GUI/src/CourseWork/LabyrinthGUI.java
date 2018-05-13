package CourseWork;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class LabyrinthGUI {

    static public ArrayList<Point> obstaclesCoord;
//    static public Point start;
//    static public Point finish;
    static public ArrayList<Point> wayCoord;

    static  public ArrayList<Point> Coord;

    private final JFrame frame;
    private final JPanel panelLeft;
    private final JPanel panelRight;


    public LabyrinthGUI(){

        //int SIZE=300/5; //нужно будет для определения размера ячейки, чтобы не выходили за границы
        Coord=new ArrayList<>();
        obstaclesCoord = new ArrayList<>();//он как бы нам подастстя в конструктор(это для теста)
        wayCoord = new ArrayList<>();
        wayCoord.add(new Point(0,0));
        wayCoord.add(new Point(1,0));
        wayCoord.add(new Point(2,0));
        wayCoord.add(new Point(3,0));
        wayCoord.add(new Point(4,0));
        wayCoord.add(new Point(0,1));
        wayCoord.add(new Point(0,2));
        wayCoord.add(new Point(0,3));
        wayCoord.add(new Point(0,4));
        obstaclesCoord.add(new Point(1,2));
        obstaclesCoord.add(new Point(2,4));
        obstaclesCoord.add(new Point(3,3));




        //ГЛАВНОЕ ОКНО
        this.frame = new JFrame("Поиск пути в лабиринте");
        this.frame.setSize(1200,600);
        this.frame.setLayout(new GridLayout(1,2, 15,30));//число строк, столбцов, расстояния между ними

        Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.black, Color.darkGray);//рамка
        this.panelLeft = new JPanel();
        this.panelLeft.setBorder(BorderFactory.createTitledBorder(border, "Поиск с минимальным числом изгибов", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("arial",Font.PLAIN,20), Color.black));
        this.panelRight = new JPanel();
        this.panelRight.setBorder(BorderFactory.createTitledBorder(border, "Поиск в ширину", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("arial",Font.PLAIN,20), Color.black));

        this.panelLeft.setLayout(new FlowLayout(FlowLayout.CENTER));



        DefaultTableModel model = new DefaultTableModel(5,5) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }





        };
        JTable table = new JTable(model);


        int index = 0;
        while (index < 5) {
            TableColumn a = table.getColumnModel().getColumn(index);
            a.setPreferredWidth(40);
            a.setCellRenderer(new Render());
            index++;
        }

        JTable table1 = new JTable(new DefaultTableModel(5,5));

//        int index1= 0;
//        while (index1 < 5) {
//            TableColumn a = table1.getColumnModel().getColumn(index1);
//            a.setPreferredWidth(40);
//            a.setCellRenderer(new Render());
//            index++;
//        }

        table1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getButton()==1) {
//                    table1.setValueAt("check", table1.getSelectedRow(), table1.getSelectedColumn());
                    Coord.add(new Point(table1.getSelectedRow(),table1.getSelectedColumn()));
                    //for (int i=0;i<5;i++)
                        table1.getColumnModel().getColumn(table1.getSelectedColumn()).setCellRenderer(new RenderFirst());
                }
            }
        });




        this.panelRight.add(table1);







        table.setRowHeight(40);
        table.setEnabled(false);
        this.panelLeft.add(table);


        this.frame.add(this.panelLeft);
        this.frame.add(this.panelRight);


        this.frame.setVisible(true);
        this.frame.setLocationRelativeTo(null);//расположение в центре
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

}
