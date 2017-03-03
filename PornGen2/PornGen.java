import java.io.File;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import javax.imageio.ImageIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import java.util.ArrayList;
import java.util.Random;

public class PornGen extends JFrame{

  private JButton generate;
  private JPanel panel;
  private JLabel image;
  private ArrayList<JLabel> images;
  
  public PornGen(){
    super("PornGen0.2");
    panel = new JPanel(new BorderLayout());
    generate = new JButton("Generate Porn");
    generate.addActionListener(new Generate());
    images = new ArrayList<JLabel>();
    try{
      image = new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource(String.format("merchant.jpg")))));
    }
    catch(Exception e){
      System.err.printf("%s\n", e);
    }
    panel.add(image, BorderLayout.CENTER);
    panel.add(generate, BorderLayout.SOUTH);
    add(panel);

    setSize(400, 500);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public boolean addImages(String s){
    try{
      images.add(new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResource(String.format("Random/%s", s))))));
      return true;
    }
    catch(Exception e){
      System.err.printf("%s\n", e);
    }
    return false;
  }

  private void generate(){
    Random rand = new Random();
    panel.remove(image);
    image = images.get(rand.nextInt(images.size()));
    panel.add(image, BorderLayout.CENTER);
    panel.repaint();
    panel.revalidate();
  }

  public static void main(String[] args){
    PornGen porn = new PornGen();
    File[] files = new File("Random/").listFiles();
    for (File f : files){
      if (f.getName().contains(".jpg") || f.getName().contains(".png")){
        System.out.printf("%s\n", f.getName());
        porn.addImages(f.getName().toString());
      }
    }
  }

  class Generate implements ActionListener{
    public void actionPerformed(ActionEvent e){
      generate();
    }
  }

}
