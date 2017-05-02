package videopoker.userinterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import videopoker.game.Card;
import videopoker.game.Game;


public class GraphicUI extends JFrame implements UserInterface{
	private Game mGame;
	private boolean[] keepArray = {false,false,false,false,false};
	private JLabel[] keepText = new JLabel[5];
	private int mBet = 1;
	private HashMap<String,Image> cardsImgs = new HashMap<String,Image>();

	private JPanel[] cardsOuterpannel = new JPanel[5];	
	
	private void cleanVars(){
		mBet = 1;
		keepArray = new boolean[] {false,false,false,false,false};
		for(JLabel kt : keepText){
			kt.setText("");
		}
		for(JPanel jp : cardsOuterpannel){
			CardLayout cl = (CardLayout) jp.getLayout();
			cl.show(jp,"Default");
		}
		
	}
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
	    setLayout( new BorderLayout());

	    //Winning Prizes Table
	    JLabel winningsLabel = new JLabel();
	    BoxLayout winningsLayout = new BoxLayout(winningsLabel,BoxLayout.Y_AXIS);
	    add(winningsLabel);
	    
	    for( String s : mGame.getWinningPrizes().getKeySet() ){
	    	JLabel handPrizesLabel = new JLabel();
	    	BoxLayout handPrizesLayout = new BoxLayout(handPrizesLabel,BoxLayout.	X_AXIS);
	    	
	    	int[] retArray = mGame.getWinningPrizes().getPrizeArray(s);
	    	
	    }
	   
	    
	    //CARDS Panel

	    
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
	    add(cardsBox,BorderLayout.PAGE_END);
	    
	    JPanel holdBox = new JPanel();
	    holdBox.setLayout(new BoxLayout(holdBox, BoxLayout.X_AXIS));
	    holdBox.setOpaque(false);
	    add(holdBox,BorderLayout.CENTER);
	    
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
	    
	    
	    JPanel buttonsPanel = new JPanel();
	    BoxLayout buttonsLayout = new BoxLayout(buttonsPanel,BoxLayout.Y_AXIS);
	    buttonsPanel.setLayout(buttonsLayout);
	    buttonsPanel.setOpaque(false);
	    cardsBox.add(buttonsPanel);
	    
	    // Bet button  
	    JButton betButton = new JButton("Bet");
	    betButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  if(mBet == 5) mBet = 0;
	    	  mBet++;
	    	  displayBet(mBet);
	      }
	    });
	    buttonsPanel.add(betButton);
	    
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
	    buttonsPanel.add(dealButton);
	    
	    // Bet button  
	    JButton Draw = new JButton("Draw");
	    betButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  if(mBet == 5) mBet = 0;
	    	  mBet++;
	    	  displayBet(mBet);
	      }
	    });
	    buttonsPanel.add(betButton);
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
	    	JPanel innerPanel = new JPanel(new BorderLayout()){
		        @Override public void paintComponent(Graphics g) {
		            g.drawImage(cardResized, 0, 0, null);
		        }    		
	    	};
	    	innerPanel.setOpaque(false);
	    	
	    	keepText[i] = new JLabel("");
	    	keepText[i].setOpaque(false);
	    	innerPanel.add(keepText[i],BorderLayout.NORTH);
	    	final int a = i;
	    	innerPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	if(keepArray[a] == true){
                		keepArray[a] = false;
                		keepText[a].setText("");
            	    	keepText[a].setOpaque(false);
                	}
                	else{
                		keepArray[a] = true;
                		keepText[a].setText("HOLD");
                		keepText[a].setOpaque(false);
                	}	
                }

            });
	    	cardsOuterpannel[i].add(innerPanel ,"Card");
	    
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
