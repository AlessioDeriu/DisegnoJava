
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Disegno extends JFrame implements MouseListener {

    private PaintPanel paintPanel;
    private Color colore = Color.RED;
    private boolean premuto = false;
    private boolean clear = false;
    private int oldX, oldY, newX, newY;

    public Disegno(){
        super("Eventi del mouse");
        this.setSize(400, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initGui();
        this.setVisible(true);
        addMouseListener(this);
    }

    private void initGui(){
        paintPanel = new PaintPanel();
        JPanel southPane1 = new JPanel(new GridLayout(1,3));
        JButton rosso = new JButton("RED");
        JButton blu = new JButton("BLU");
        JButton pulisci = new JButton("CLEAR");

        rosso.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                colore = Color.RED;
            }
        });

        blu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                colore = Color.BLUE;
            }
        });
        
        pulisci.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clear = true;
                paintPanel.repaint();
                premuto = false;
            }
        });
        
        southPane1.add(rosso);
        southPane1.add(blu);
        southPane1.add(pulisci);
        this.getContentPane().add(southPane1, BorderLayout.SOUTH);
        this.getContentPane().add(paintPanel, BorderLayout.CENTER);
    }
    
        public void mouseClicked(MouseEvent e){
            if(!premuto){
                oldX = e.getX();
                oldY = e.getY() - getInsets().top;
                premuto = true;
            } else {
                newX = e.getX();
                newY = e.getY() - getInsets().top;
                paintPanel.repaint();
                premuto = false;
            }
        }

        public void mousePressed(MouseEvent e){
        }

        public void mouseReleased(MouseEvent e){
        }

        public void mouseEntered(MouseEvent e){ 
        }

        public void mouseExited(MouseEvent e){
        }

        public static void main (String[]args){
            new Disegno();
        }

        private class PaintPanel extends JPanel{
            public void paintComponent(Graphics g){
                if (clear){
                    super.paintComponent(g);
                    clear = false;
                }else{
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(colore);
                    g2.drawLine(oldX, oldY, newX, newY);
                }
            }
        }
    }
