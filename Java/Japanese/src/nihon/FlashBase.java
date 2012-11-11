package nihon;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nihon.util.FrameUtils;
import nihon.util.TsvFileReader;

public abstract class FlashBase
{
    protected static final int  SLEEP_MSEC = 2000;

    private JFrame              frame      = new JFrame();
    private JLabel[]            labels;
    private ArrayList<String[]> wordlist;
    private int                 numLabels;

    public FlashBase(final String filename) throws Exception
    {
        TsvFileReader reader = new TsvFileReader(filename);
        wordlist = reader.getArrayList();
        Collections.shuffle(wordlist);

        numLabels = wordlist.get(0).length;
        labels = new JLabel[numLabels];

        setupFrame();
    }

    public void iterateItems() throws InterruptedException
    {
        for (String[] item : wordlist)
        {
            clearLabels();

            for (int i = 0; i < numLabels; i++)
            {
                labels[i].setText(item[i]);
                fixText(labels[i]);
                Thread.sleep(SLEEP_MSEC);
            }
        }
    }

    public void fixText(JLabel label)
    {
        Font curFont = new Font("Arial Unicode MS", Font.PLAIN, label.getFont().getSize());
        FontMetrics fm = label.getFontMetrics(curFont);
        String labeltext = label.getText();

        int labelwidth = fm.stringWidth(labeltext);
        int framewidth = frame.getWidth();

        if (labelwidth > framewidth)
        {
            label.setText("<html>" + labeltext + "</html>");
        }
    }

    public void clearLabels()
    {
        for (JLabel curLabel : labels)
        {
            curLabel.setText("");
        }
    }

    protected void setupFrame()
    {
        final int SMALL_FONT_SIZE = 36;
        final int BIG_FONT_SIZE = 96;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setPreferredSize(new Dimension(800, 400));

        boolean firstAdded = false;
        for (int i = 0; i < numLabels; i++)
        {
            int fontsize = firstAdded ? SMALL_FONT_SIZE : BIG_FONT_SIZE;
            firstAdded = true;

            JLabel curLabel = new JLabel();
            curLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, fontsize));
            curLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            labels[i] = curLabel;
            panel.add(curLabel);
        }

        frame.setTitle("Kanji Flash");
        frame.add(panel);
        frame.pack();

        FrameUtils.centerFrame(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}