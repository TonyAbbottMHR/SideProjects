import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

import java.util.Random;

public class Porn extends JFrame{

  static JLabel pic;
  static JButton gen;
  static Porn porn;

  public Porn(){
    super("Random Porn Generator");
    this.gen = new JButton("GENERATE");
    setSize(400,500);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(gen);
    setLayout(new GridLayout(2, 2));
  }

  public void generate(){
    try{
      if (pic != null){
        remove(pic);
      }
      Random rand = new Random();
      Integer i = rand.nextInt(4);
      BufferedImage myPicture = ImageIO.read(this.getClass().getResource(String.format("%s.jpg", i.toString())));
      pic = new JLabel(new ImageIcon(myPicture));
      add(pic);
      revalidate();
      repaint();
    }
    catch (IOException e){
      System.out.println(e);
    }
  }

  public static void main(String[] args){
    porn = new Porn();
    porn.gen.addActionListener(new Generate());
  }

  static class Generate implements ActionListener{
    public void actionPerformed(ActionEvent e){
      porn.generate();
    }
  }
}
