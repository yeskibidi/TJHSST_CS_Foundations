package Lab17;   //Name:    Date:

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
public class PrizePanel extends JPanel
{
      private static final int FRAME = 400;
      private static final Color BACKGROUND = new Color(204, 204, 204);
      private BufferedImage myImage;
      private Graphics myBuffer;
      private Ball ball;
      private Polkadot pd;
      private Timer t;
      private int hits = 0;
		//constructor
       public PrizePanel()
      {
          myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
          myBuffer = myImage.getGraphics();
          myBuffer.setColor(BACKGROUND);
          myBuffer.fillRect(0, 0, FRAME,FRAME);
          int xPos = (int)(Math.random()*(FRAME-100) + 50);
          int yPos = (int)(Math.random()*(FRAME-100)+ 50);
          ball = new Ball(xPos, yPos, 50, Color.BLACK);
          pd= new Polkadot(xPos, yPos, 50, Color.RED);

          addKeyListener(new Key());
          t = new Timer(5, new Listener());
          t.start();
            setFocusable(true);
      }
       private class Key extends KeyAdapter {
              public void keyPressed(KeyEvent e){
                    if(e.getKeyCode()==KeyEvent.VK_UP){
                        if(pd.getY()-10>0){
                            pd.setY(pd.getY()-10);
                        }
                    }
                    if(e.getKeyCode()==KeyEvent.VK_DOWN){
                        if(pd.getY()+10<FRAME){
                            pd.setY(pd.getY()+10);
                        }
                    }
                    if(e.getKeyCode()==KeyEvent.VK_LEFT){
                        if(pd.getX()-10>0){
                            pd.setX(pd.getX()-10);
                        }
                    }
                    if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                        if(pd.getX()+10<FRAME){
                            pd.setX(pd.getX()+10);
                        }
                    }
              }
       }
       public void paintComponent(Graphics g)
      {
          g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      }
       private class Listener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
         {
             myBuffer.setColor(BACKGROUND);
             myBuffer.fillRect(0,0,FRAME,FRAME);

             ball.move(FRAME,FRAME);
             collide(ball, pd);

             ball.draw(myBuffer);
             pd.draw(myBuffer);

             myBuffer.setColor(Color.BLACK);
             myBuffer.setFont(new Font("Monospaced", Font.BOLD, 24));
             myBuffer.drawString("Count: "+hits, FRAME-150, 25);
             repaint();
         }
      }
       private double distance(double x1, double y1, double x2, double y2){
           return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
       }
       private void collide(Ball b, Polkadot pd){
           double d=distance(b.getX(), b.getY(), pd.getX(), pd.getY());
           if(d<=b.getRadius()+pd.getRadius()){
               b.setdx(-b.getdx());
               b.setdy(-b.getdy());
               hits++;
               pd.jump(FRAME, FRAME);
           }


       }
   }

