package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static JFrame jFrame = getjFrame();
    static JPanel jPanel = new JPanel();
    static JLabel[] labels = new JLabel[3];
    static JSlider[] sliders = new JSlider[3];

    public static void main(String[] args) {
        Border border = BorderFactory.createLineBorder(Color.red);

        JMenuBar jMenuBar = new JMenuBar();
        jFrame.setJMenuBar(jMenuBar);
        JMenu file = new JMenu("File");
        file.setMnemonic('F');
        JMenu edit = new JMenu("Edit");
        edit.setMnemonic('E');
        jMenuBar.add(file);
        jMenuBar.add(edit);
        JMenuItem open = new JMenuItem("Open", 'O');
        file.add(open);
        JMenuItem save = new JMenuItem("Save", 'S');
        file.add(save);
        JMenu options = new JMenu("Options");
        JMenuItem settings = new JMenuItem("Settings");
        options.add(settings);
        settings.setEnabled(false);
        file.add(options);
        save.setEnabled(false);
        file.addSeparator();
        JMenuItem close = file.add(new JMenuItem("Close"));
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        close.setAccelerator(KeyStroke.getKeyStroke("ctrl alt E"));
        JMenuItem cut = edit.add(new JMenuItem("Cut"));
        JMenuItem copy = edit.add(new JMenuItem("Copy"));
        cut.setEnabled(false);
        copy.setEnabled(false);

        JPopupMenu jPopupMenu = new JPopupMenu();
        jPopupMenu.add(open);
        jPopupMenu.add(save);
        jPopupMenu.add(settings);
        jPopupMenu.add(cut);
        jPopupMenu.add(copy);
        jPopupMenu.add(close);
        jPanel.setComponentPopupMenu(jPopupMenu);

        JButton one = new JButton("One");
        one.setToolTipText("First");
        JToolBar jToolBar = new JToolBar("Tool Bar");
        jToolBar.add(one);
        jToolBar.addSeparator();
        jToolBar.add(new JButton("Two"));
        jToolBar.add(new JButton("Three"));
        jPanel.add(jToolBar);


        jFrame.add(jPanel);
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < 3; i++) {
            labels[i] = new JLabel("000");
            sliders[i] = createSlider();
            jPanel.add(labels[i]);
            jPanel.add(sliders[i]);

            final int index = i;
            sliders[i].addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    updateLabelAndColor(index);
                }
            });
        }

        jPanel.setBorder(border);
        jFrame.revalidate();
    }

    static JFrame getjFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setBounds(750, 250, 500, 500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return jFrame;
    }

    static JSlider createSlider() {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        slider.setPreferredSize(new Dimension(400, 100));
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(50);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setPaintTrack(true);
        return slider;
    }

    static void updateLabelAndColor(int index) {
        String abc = String.format("%03d", sliders[index].getValue());
        labels[index].setText(abc);

        int[] colorValues = new int[3];
        for (int i = 0; i < 3; i++) {
            colorValues[i] = sliders[i].getValue();
        }

        Color color = new Color(colorValues[0], colorValues[1], colorValues[2]);
        jPanel.setBackground(color);
    }
}
