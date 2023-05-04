package dsw.rudok.app.gui.swing.view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class CustomJTabbedPane extends JTabbedPane {
    private static boolean showTabsHeader = true;

    public CustomJTabbedPane() {
        setUI(new MyTabbedPaneUI());
    }

    private class MyTabbedPaneUI extends BasicTabbedPaneUI {

        @Override
        protected int calculateTabAreaHeight(
                int tabPlacement, int horizRunCount, int maxTabHeight) {
            if (showTabsHeader) {
                return super.calculateTabAreaHeight(
                        tabPlacement, horizRunCount, maxTabHeight);
            } else {
                return 0;
            }
        }

        @Override
        protected void paintTab(
                Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex,
                Rectangle iconRect, Rectangle textRect) {
            if (showTabsHeader) {
                super.paintTab(
                        g, tabPlacement, rects, tabIndex, iconRect, textRect);
            }
        }

        @Override
        protected void paintContentBorder(
                Graphics g, int tabPlacement, int selectedIndex) {
            if (showTabsHeader) {
                super.paintContentBorder(g, tabPlacement, selectedIndex);
            }
        }

        @Override
        public int tabForCoordinate(JTabbedPane pane, int x, int y) {
            if (showTabsHeader) {
                return super.tabForCoordinate(pane, x, y);
            } else {
                return -1;
            }
        }
    }
}
