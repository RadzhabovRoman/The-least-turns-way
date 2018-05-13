package CourseWork;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

import static CourseWork.LabyrinthGUI.Coord;
import static CourseWork.LabyrinthGUI.obstaclesCoord;
import static CourseWork.LabyrinthGUI.wayCoord;

public class RenderFirst extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (Coord.contains(new Point(row,column)))cell.setBackground(Color.BLUE);
        else cell.setBackground(Color.WHITE);

        return cell;
    }


}
