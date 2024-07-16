package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ImagePanel extends JPanel {
    private BufferedImage backgroundImage;

    public ImagePanel()
    {
        try
        {
            backgroundImage = ImageIO.read(new File("src/graphics2/competitionBackground.png"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}