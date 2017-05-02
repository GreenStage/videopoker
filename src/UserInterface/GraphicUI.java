package UserInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import videopoker.Card;
import videopoker.Game;

public class GraphicUI extends JFrame implements UserInterface{
	private Game mGame;
	private int mBet = 0;
	private JPanel contentPane;
	private HashMap<String,Image> cardsImgs = new HashMap<String,Image>();

	private JPanel[] cardsOuterpannel = new JPanel[5];	
	
	public GraphicUI(Game game) {
		mGame = game;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
	   
		//SET BACKGROUND
		Image bg = null;
	    if( (bg = imageReader("bg.jpg")) == null){
	    	throw new RuntimeException();
	    }
	    Image bgResized = bg.getScaledInstance(800, 600, Image.SCALE_DEFAULT);
	    setContentPane(new JComponent() {
	        @Override public void paintComponent(Graphics g) {
	            g.drawImage(bgResized, 0, 0, null);
	        }
	    });
	    setLayout( new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

	
	    JButton betButton = new JButton("Bet");
	    // Deal button
	    betButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  if(mBet == 5) mBet = 0;
	    	  mBet++;
	    	  displayBet(mBet);
	      }
	    });
	    add(betButton);
	    
	    // Deal button    
	    JButton dealButton = new JButton("Deal");
	    dealButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  mGame.bet(mBet, new Game.ActionListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void onFailure(String reason) {
					displayError(reason);
				}
			});
	    	  mGame.deal(new Game.ActionListener() {
				@Override
				public void onSuccess() {
					displayHand(mGame.getPlayer().getHand().getHandStrArr());
				}
				@Override
				public void onFailure(String reason) {
					displayError(reason);
				}
			});
	      }
	    });
	    add(dealButton);
	    
	    //CARDS Panel
	    JPanel cardsPanel = new JPanel();
	    cardsPanel.setOpaque(false);
	    cardsPanel.setLayout(new BoxLayout(cardsPanel,BoxLayout.X_AXIS));

	    add(cardsPanel);
	    
	    //Load Cards
	    for(Card.Value vl: Card.Value.values()){
	    	for(Card.Suit st: Card.Suit.values()){
	    		String filename = vl.getValue() + "" + st.getSuit();
	    		cardsImgs.put(filename,imageReader("cards/" + filename +".png"));
	    	}
	    }

	    JPanel cardsBox = new JPanel();
	    cardsBox.setLayout(new BoxLayout(cardsBox, BoxLayout.X_AXIS));
	    cardsBox.setOpaque(false);
	    add(cardsBox);
	
	    
	    Image spot = imageReader("cards/spot.png");
	    Image spotResized = spot.getScaledInstance(140, 197, Image.SCALE_DEFAULT);
	    
	    CardLayout CardsOuterLayout = new CardLayout();
	    for(int i = 0; i < 5; i++){
	    	final int a = i;
		    cardsOuterpannel[i] = new JPanel( CardsOuterLayout );
		    cardsOuterpannel[i].setOpaque(false);
	    	cardsOuterpannel[i].setPreferredSize(new Dimension(140, 197));
	    	cardsOuterpannel[i].setMaximumSize(new Dimension(140, 197)); 
	    	cardsOuterpannel[i].setMinimumSize(new Dimension(140, 197));
	    	cardsBox.add(cardsOuterpannel[i]);

	    	cardsOuterpannel[i].add( new JPanel(){
		        @Override public void paintComponent(Graphics g) {
		            g.drawImage(spotResized, 0, 0, null);
		        }    		
	    	},"Default");
	    	
	    	CardsOuterLayout.show(cardsOuterpannel[i],"Default");
	    }

	    
	}
	
	//JUST FOR DEBUGING
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicUI frame = new GraphicUI(new Game(1000));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//Test 
		
	}
	
	private Image imageReader(String filepath){
		Image retval;
		try{
			retval = javax.imageio.ImageIO.read(getClass().getClassLoader().getResource(filepath));
		} catch (IOException e) {
		    return null;
		} catch (IllegalArgumentException e){
			System.out.println("Error loading image " + filepath);
			return null;
		}
		return retval;
	}
	/**
	 * Create the frame.
	 */	

	
	private void setPane(){
		
	}
	@Override
	public void displayCredit(int credit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayBet(int bet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayHand(String[] hand) {

	    for(int i = 0; i < 5; i++){
			JPanel cardsPanel = new JPanel();
			cardsPanel.setOpaque(false);
	    	Image cardImg = imageReader("cards/" + hand[i] + ".png");
	    	Image cardResized = cardImg.getScaledInstance(140, 197, Image.SCALE_DEFAULT);
	    	cardsOuterpannel[i].add( new JPanel(){
		        @Override public void paintComponent(Graphics g) {
		            g.drawImage(cardResized, 0, 0, null);
		        }    		
	    	},"Card");
	    	
	    	CardLayout cl = (CardLayout)(cardsOuterpannel[i].getLayout());
	 	    cl.show(cardsOuterpannel[i], "Card");
	    }
	   
	}

	@Override
	public void displayAdvice(String[] hand, boolean[] advice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayError(String reason) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayWin(boolean wins, String handPower, int credit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		setVisible(true);
	}

}
