package com.mygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MyGame extends JFrame implements ActionListener {
    JLabel heading,clockLabel;
    Font font=new Font("",Font.PLAIN,40);
    JPanel mainPanel;
    JButton [] btns=new JButton[9];
    int gameChances[]={2,2,2,2,2,2,2,2,2};
    int activePlayer=0;
    int wps[][]={
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}

    };
    int winner=2;
    boolean gameOver=false;
    MyGame(){
        setTitle("TicTacToe");
        setSize(850,850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);
        
    }

    private void createGUI() {
        this.getContentPane().setBackground(Color.decode("#2196f3"));
        this.setLayout(new BorderLayout());
        heading=new JLabel("Tic tac Toe");
        heading.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setForeground(Color.white);

        this.add(heading,BorderLayout.NORTH);
        clockLabel=new JLabel("Clock");
        clockLabel.setFont(font);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(clockLabel,BorderLayout.SOUTH);
        Thread t=new Thread(){
            public void run(){
                try{
                    while(true){
                        Date datetime = new Date();
                        clockLabel.setText(String.valueOf(datetime));
                        Thread.sleep(1000);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        t.start();
        //////panel section.
        mainPanel=new JPanel();
        mainPanel.setLayout(new GridLayout(3,3));
        for(int i=1;i<=9;i++){
            JButton btn=new JButton();
            btn.setBackground(Color.decode("#90caf9"));
            //btn.setIcon(new ImageIcon("src/img/0.png"));
            btn.setFont(font);
            mainPanel.add(btn);
            btns[i-1]=btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));

        }
        this.add(mainPanel,BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton currentButton=(JButton) e.getSource();
        String nameStr=currentButton.getName();
        int name=Integer.parseInt(nameStr.trim());

        if (gameOver){
            JOptionPane.showMessageDialog(this,"game is already over");
        }
        if(gameChances[name]==2){
            if (activePlayer==1){
                currentButton.setIcon(new ImageIcon("src/img/cross.png"));
                gameChances[name]=1;
                activePlayer=0;
            }
            else{
                currentButton.setIcon(new ImageIcon("src/img/0.png"));
                gameChances[name]=0;
                activePlayer=1;
            }
            //find the winner.........
            for (int []temp:wps){
                if((gameChances[temp[0]]==gameChances[temp[1]]) && (gameChances[temp[1]]==gameChances[temp[2]])&& (gameChances[temp[2]]!=2 )){
                    winner=gameChances[temp[0]];
                    gameOver=true;
                    JOptionPane.showMessageDialog(null,"Player"+winner+"has won");
                   int i=JOptionPane.showConfirmDialog(this,"do you want to play more?");

                   if(i==0){
                       this.setVisible(false);
                       MyGame game=new MyGame();
                   }
                   else if (i==1){
                       System.exit(34234);
                   }
                   else{}
                    System.out.println(i);
                    break;
                }
            }
            //Draw logic
            int c=0;
            for(int x:gameChances){
                if(x==2){
                    c++;
                    break;
                }
            }
            if(c==0 && gameOver==false){
                JOptionPane.showMessageDialog(null,"Its draw");
                int i=JOptionPane.showConfirmDialog(this,"do you want to play more?");

                if(i==0){
                    this.setVisible(false);
                    MyGame game=new MyGame();
                }
                else if (i==1){
                    System.exit(1212);
                }
                else{}
            }
            gameOver=true;
        }
        else{
            JOptionPane.showMessageDialog(this,"position already occupied");
        }

    }
}
