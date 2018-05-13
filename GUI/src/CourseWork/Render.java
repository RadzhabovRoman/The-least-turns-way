package CourseWork;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static CourseWork.LabyrinthGUI.obstaclesCoord;
import static CourseWork.LabyrinthGUI.wayCoord;

public class Render extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (wayCoord.contains(new Point(column,row)))cell.setBackground(Color.BLUE);
        else
            if(obstaclesCoord.contains(new Point(column,row))) cell.setBackground(Color.BLACK);
            else cell.setBackground(Color.WHITE); //делаем final public координаты
        return cell;
    }


}
