package videopoker.userinterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
	private boolean canHold = false;
	private HashMap<String,Image> cardsImgs = new HashMap<String,Image>();
	private JLabel[][] winningsTable ;
	private JPanel messagesPanel;
	private JLabel alertMsgLabel;
	private JPanel[] cardsOuterpannel = new JPanel[5];	
	
	private JButton betButton, dealButton, drawButton, adviceButton, statsButton;
	
	private void cleanVars(){
		mBet = 1;
		displayBet(mBet);
		keepArray = new boolean[] {false,false,false,false,false};
		for(JLabel kt : keepText){
			kt.setText("");
		}
		betButton.setEnabled(true);
		dealButton.setEnabled(true);
		drawButton.setEnabled(false);
		canHold = false;
		
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
	    JPanel winningsLabel = new JPanel();
	    BoxLayout winningsLayout = new BoxLayout(winningsLabel,BoxLayout.Y_AXIS);
	    winningsLabel.setLayout(winningsLayout);
	    winningsLabel.setOpaque(false);
	    winningsLabel.setPreferredSize(new Dimension(750, 300));
	    winningsLabel.setMaximumSize(new Dimension(750, 300)); 
	    winningsLabel.setMinimumSize(new Dimension(750, 300));
	    add(winningsLabel,BorderLayout.NORTH);
	    
	    int it = 0;
	    List<String> iterable = mGame.getWinningPrizes().getKeySet();
    	winningsTable = new JLabel[iterable.size()][];
	    for( String s : iterable ){	 
	    	
	    	JPanel handPrizesLabel = new JPanel();
	    	BoxLayout handPrizesLayout = new BoxLayout(handPrizesLabel,BoxLayout.X_AXIS);
	    	handPrizesLabel.setLayout(handPrizesLayout);
	    	handPrizesLabel.setPreferredSize(new Dimension(730, 25));
	    	handPrizesLabel.setMaximumSize(new Dimension(730, 25)); 
	    	handPrizesLabel.setMinimumSize(new Dimension(730, 25));
	    	
	    	handPrizesLabel.setBackground((it % 2 == 0)? Color.WHITE : Color.LIGHT_GRAY);
	    	
	    	winningsLabel.add(handPrizesLabel);
	    	
	    	JLabel handPowerLabel = new JLabel(s);
	    	handPowerLabel.setOpaque(false);
	    	handPowerLabel.setPreferredSize(new Dimension(180, 30));
	    	handPowerLabel.setMaximumSize(new Dimension(180, 30)); 
	    	handPowerLabel.setMinimumSize(new Dimension(180, 30));
	    	handPrizesLabel.add(handPowerLabel);		
	    	
	    	int[] retArray = mGame.getWinningPrizes().getPrizeArray(s);
	    	winningsTable[it] = new JLabel[retArray.length];
	    	for(int it2 = 0; it2 < retArray.length ; it2++){
	    		winningsTable[it][it2] = new JLabel(String.valueOf(retArray[it2]));
	    		winningsTable[it][it2].setPreferredSize(new Dimension(110, 30));
	    		winningsTable[it][it2].setMaximumSize(new Dimension(110, 30)); 
	    		winningsTable[it][it2].setMinimumSize(new Dimension(110, 30));
	    		winningsTable[it][it2].setOpaque(true);
	    		if(it2 == 0){
		    		winningsTable[it][it2].setBackground((it % 2 == 0) ? 
		    				new Color(51,204,255) : new Color(107,125,148));			
	    		}
	    		else winningsTable[it][it2].setBackground(new Color(0,0,0,0));
	    		handPrizesLabel.add(winningsTable[it][it2]);  

	    	}
	    	it++;
	    }
	   
	    //Messages Panel
	    messagesPanel = new JPanel(new FlowLayout());
	    messagesPanel.setVisible(true);
	    messagesPanel.setPreferredSize(new Dimension(800, 100));
	    messagesPanel.setMaximumSize(new Dimension(800, 100)); 
	    messagesPanel.setMinimumSize(new Dimension(800, 100));
	    messagesPanel.setBackground(new Color(0,0,0,0));
	    add(messagesPanel,BorderLayout.WEST);	 
	    
	    //Alert icon
	    ImageIcon alertIcon = new ImageIcon(this.getClass().getResource("/alert-icon.png"));
	    JLabel iconLabel = new JLabel(alertIcon);
	    iconLabel.setBackground(new Color(0,0,0,0));
	    messagesPanel.setOpaque(false);
	    messagesPanel.add(iconLabel);
	    
	    //Alert text message
	    alertMsgLabel = new JLabel("Good Luck!");
	    alertMsgLabel.setBackground(new Color(0,0,0,0));
	    messagesPanel.add(alertMsgLabel);

	    
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
	    betButton = new JButton("Bet");
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
	    dealButton = new JButton("Deal");
	    dealButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  mGame.bet(mBet, new Game.ActionListener() {
				
				@Override
				public void onSuccess() {
					mGame.deal(new Game.ActionListener() {
						@Override
						public void onSuccess() {
							canHold = true;
							betButton.setEnabled(false);
							dealButton.setEnabled(false);
							drawButton.setEnabled(true);
							displayHand(mGame.getPlayer().getHand().getHandStrArr());
						}
						@Override
						public void onFailure(String reason) {
							displayError(reason);
						}
					});
				}
				
				@Override
				public void onFailure(String reason) {
					displayError(reason);
				}
			});
	    	  
	      }
	    });
	    buttonsPanel.add(dealButton);
	    
	    // Draw button  
	    drawButton = new JButton("Draw");
	    drawButton.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  mGame.keep(keepArray, new Game.ActionListener() {
				
				@Override
				public void onSuccess() {
					canHold = false;
					drawButton.setEnabled(false);
					displayHand(mGame.getPlayer().getHand().getHandStrArr());
					displayWin(mGame.getWinStatus(), mGame.getWinningPrizes().
							getHandPower(mGame.getPlayer().getHand()),
							mGame.getPlayer().getCredit() );
					cleanVars();
				}
				
				@Override
				public void onFailure(String reason) {
					displayError(reason);
				}
			});
	      }
	    });
	    drawButton.setEnabled(false);
	    buttonsPanel.add(drawButton);

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
		for(int it = 0; it < winningsTable.length; it++){
			for(int it2 = 0 ; it2 < winningsTable[it].length; it2++){
	    		if(it2 == bet - 1){
		    		winningsTable[it][it2].setBackground((it % 2 == 0) ? 
		    				new Color(51,204,255) : new Color(107,125,148));			
	    		}
	    		else {
	    			winningsTable[it][it2].setBackground((it % 2 == 0)? Color.WHITE : Color.LIGHT_GRAY);
	    		}

			}
		}
		
	}

	@Override
	public void displayHand(String[] hand) {
	    for(int i = 0; i < 5; i++){
	    	if(keepArray[i] == true){
	    		continue;
	    	} 
	    	
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
                	if(canHold){
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
                }

            });
	    	
	    	cardsOuterpannel[i].add(innerPanel ,(canHold)? "Card" : "DrawCard");
	    
	    	CardLayout cl = (CardLayout)(cardsOuterpannel[i].getLayout());
	 	    cl.show(cardsOuterpannel[i], (canHold)? "Card" : "DrawCard");
	    }
	   
	}

	@Override
	public void displayAdvice(String[] hand, boolean[] advice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayError(String reason) {
		// TODO Auto-generated method stub
		alertMsgLabel.setText(reason);
		messagesPanel.setVisible(true);
	}

	@Override
	public void displayWin(boolean wins, String handPower, int credit) {
		String msg = "";
		if(wins){
			msg = "Player wins with " + handPower + " and his credit is " +
					String.valueOf(credit);
		}
		else msg = "Player loses and his credit is " + String.valueOf(credit);
		
		alertMsgLabel.setText(msg);
		messagesPanel.setBackground(new Color(0,0,0,0));
		
	}

	@Override
	public void run() {
		setVisible(true);
	}

}
