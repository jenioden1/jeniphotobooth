import java.awt.*;
import javax.swing.*;

public class lalala extends JPanel {

    private String[] lyrics = {  
        "About your new messiah 'cause your theories catch fire",  
        "I can't find your silver lining",  
        "I don't mean to judge",  
        "But when you read your speech, it's tiring",  
        "Enough is enough"  
    }; 

    private int[] delays = {
            50, 50, 50, 100,
            50, 50, 50, 100
    };

    private int currentIndex = 0;
    private String currentLine = "";
    private int currentCharIndex = 0;
    private ImageIcon backgroundGif;

    public lalala() {
        setPreferredSize(new Dimension(1280, 720));
        backgroundGif = new ImageIcon("bg.png");
        
        new Thread(() -> {
            try {
                while (currentIndex < lyrics.length) {
                    if (currentCharIndex < lyrics[currentIndex].length()) {
                        currentLine += lyrics[currentIndex].charAt(currentCharIndex);
                        currentCharIndex++;
                        repaint();
                        Thread.sleep(80); // Delay antar karakter
                    } else {
                        Thread.sleep(delays[currentIndex]); // Delay antar baris
                        currentIndex++;
                        currentLine = "";
                        currentCharIndex = 0;
                        repaint();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundGif != null) {
            g.drawImage(backgroundGif.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        g.setFont(new Font("Serif", Font.PLAIN, 42));
        g.setColor(Color.WHITE);

        // Hitung posisi tengah untuk lirik
        int stringWidth = g.getFontMetrics().stringWidth(currentLine);
        int x = (getWidth() - stringWidth) / 2;
        int y = getHeight() / 2;

        // Tampilkan lirik di tengah
        g.drawString(currentLine, x, y);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lyrics Display");
        lalala panel = new lalala();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
