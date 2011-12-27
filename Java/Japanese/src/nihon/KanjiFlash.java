package nihon;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nihon.util.FrameUtils;
import nihon.util.TsvFileReader;

public class KanjiFlash
{
    private static final int SLEEP_MSEC = 5000;
    
    private JFrame              frame        = new JFrame();
    private JLabel              kanjiLabel   = new JLabel();
    private JLabel              onLabel      = new JLabel();
    private JLabel              kunLabel     = new JLabel();
    private JLabel              englishLabel = new JLabel();
    private ArrayList<String[]> kanjiList;

    private KanjiFlash(final String filename) throws Exception
    {
        setupFrame();

        TsvFileReader reader = new TsvFileReader(filename);
        kanjiList = reader.getArrayList();
        Collections.shuffle(kanjiList);

        for (String[] item : kanjiList)
        {
            clearLabels();
            
            kanjiLabel.setText(item[0]);
            Thread.sleep(SLEEP_MSEC);
            
            onLabel.setText(item[1]);
            fixText(onLabel);
            Thread.sleep(SLEEP_MSEC);
            
            kunLabel.setText(item[2]);
            fixText(kunLabel);
            Thread.sleep(SLEEP_MSEC);
            
            englishLabel.setText(item[3]);
            fixText(englishLabel);
            Thread.sleep(SLEEP_MSEC);
        }
    }
    
    private void fixText(JLabel label)
    {
        int labelwidth = label.getWidth();
        int framewidth = frame.getWidth();
        String labeltext = label.getText();
        StringBuilder builder = new StringBuilder(labeltext);
        
        if (labelwidth > framewidth)
        {
            int mult = (int) Math.ceil(labelwidth / framewidth);
            int numchars = (int) Math.floor(labeltext.length() / mult);
            
            for (int i = numchars; i <= labelwidth; i += numchars)
            {
                builder.insert(i, "\n");
            }
            
            label.setText(builder.toString());
        }
    }
    
    private void clearLabels()
    {
        kanjiLabel.setText("");
        onLabel.setText("");
        kunLabel.setText("");
        englishLabel.setText("");
    }
    
    private void setupFrame()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setPreferredSize(new Dimension(600, 400));

        kanjiLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 96));
        kanjiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        onLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 36));
        onLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        kunLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 36));
        kunLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        englishLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 36));
        englishLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(kanjiLabel);
        panel.add(onLabel);
        panel.add(kunLabel);
        panel.add(englishLabel);

        frame.setTitle("Kanji Flash");
        frame.add(panel);
        frame.pack();

        FrameUtils.centerFrame(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception
    {
        String dir = System.getProperty("user.dir");
        new KanjiFlash(dir + "\\src\\resources\\Kanji_n5.tsv");
    }
}
