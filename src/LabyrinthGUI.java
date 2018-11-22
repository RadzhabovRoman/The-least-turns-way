package CourseWork;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class LabyrinthGUI {

    private final JFrame frame;

    private final JPanel panelLeft;
    private final JPanel panelRight;


    public LabyrinthGUI(){
        //ГЛАВНОЕ ОКНО
        this.frame = new JFrame("Поиск пути в лабиринте");
        this.frame.setSize(1200,800);
        this.frame.setLayout(new GridLayout(1,2, 15,30));//число стак, столбцов, расстояния между ними

        Border border = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.black, Color.darkGray);//рамка
        this.panelLeft = new JPanel();
        this.panelLeft.setBorder(BorderFactory.createTitledBorder(border, "Поиск с минимальным числом изгибов", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("arial",Font.PLAIN,20), Color.black));//опять
        this.panelRight = new JPanel();
        this.panelRight.setBorder(BorderFactory.createTitledBorder(border, "Поиск в ширину", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("arial",Font.PLAIN,20), Color.black));
        this.panelLeft.setLayout(new GridBagLayout());

        JButton b1 = new JButton();//элемент лабиринта - неактивная кнопка
        b1.setEnabled(false);
        b1.setBackground(new Color(155,105,96));


        JButton b2 = new JButton();
        b2.setEnabled(false);
        b2.setBackground(new Color(152,105,96));
        b2.setSize(20,20);



        this.panelLeft.add(b1, new GridBagConstraints(0,0,1,1,0,0,
                GridBagConstraints.PAGE_START, GridBagConstraints.VERTICAL,
                new Insets(0,0,0,0),-10,10));//чудо-конструктор для layout-а

        this.panelLeft.add(b2, new GridBagConstraints(1,0,1,1,0,0,
                GridBagConstraints.PAGE_START, GridBagConstraints.VERTICAL,
                new Insets(0,0,0,0),-10,10));



        this.frame.add(this.panelLeft);
        this.frame.add(this.panelRight);


        this.frame.setVisible(true);
        this.frame.setLocationRelativeTo(null);//расположение в центре
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //осталось написать метод с for-ом и решить проблемы в layout-ом в нем, если будут
        //на будущее:пошагавое изменение
    }

}
