package com.company;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.UnresolvedPermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import javax.swing.JButton;

import static javax.swing.JLayeredPane.DRAG_LAYER;
import static javax.swing.JLayeredPane.LAYER_PROPERTY;


public class Main extends JFrame {
    public static void main(String[] args) {
        StartWind realTimeActSW = new StartWind();
        realTimeActSW.StartWind();                          // (добавить в конце рестарт)

    }
}

class StartWind {
    List<Component> compList = new ArrayList<Component>();
    File file1 = new File("ArrayBoard.txt");
    File file2 = new File("TimeSecond.txt");
    String whiteWay = "WhitePoint.png";
    String blackWay = "BlackPoint.png";
    Icon whiteWayICON = new ImageIcon(whiteWay);
    Icon blackWayICON = new ImageIcon(blackWay);
    ArrayBoard firstArrayBoard = new ArrayBoard();
    static int firstreplace = 1;
    static Integer[][] standartBoard = new Integer[8][8];
    Integer[][] bolvanchikBoard = standartBoard;
    public JPanel firstPanel;
    static JFrame mainWinJF = new JFrame();
    static Integer timerSecond = 1;

    public void StartWind() {
        firstArrayBoard.NGBoard();


        mainWinJF.setTitle("REVERSI 3.0.1 ");
        mainWinJF.setSize(new Dimension(400, 600));
        mainWinJF.setLocation(900, 100);
        mainWinJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWinJF.setSize(550, 550);

        Board replaceBoard = new Board();
        firstPanel = replaceBoard.Board(firstArrayBoard.bolvanchikArrayBoard);
        JLayeredPane jLayersfSP = new JLayeredPane();

        mainWinJF.add(firstPanel);


        try {
            if (!file1.exists()) {
                file1.createNewFile();
            }
            PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
            try {
                for (int i = 0; i < firstArrayBoard.bolvanchikArrayBoard.length; i++) {
                    for (int j = 0; j < firstArrayBoard.bolvanchikArrayBoard[i].length; j++) {
                        if ((j == 7)) {
                            out.print(firstArrayBoard.bolvanchikArrayBoard[i][j]);
                        } else {
                            out.print(firstArrayBoard.bolvanchikArrayBoard[i][j] + " ");
                        }
                    }
                    out.print("\n");
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if (!file2.exists()) {
                file2.createNewFile();
            }
            PrintWriter out = new PrintWriter(file2.getAbsoluteFile());
            try {
                out.print("-1");
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        JMenuBar mainWindMB = new JMenuBar();

        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("New game");
        JMenu menu3 = new JMenu("Restart");
        JMenu menu4 = new JMenu("Exit");

        JMenuItem menuItem1Load = new JMenuItem("Load(Beta)");
        JMenuItem menuItem1Save = new JMenuItem("Save(Beta)");
        JMenuItem menuItem1TestRead = new JMenuItem("TestRead");
        JMenuItem menuItem1Manual = new JMenuItem("Manual(beta)");
        JMenuItem menuItem1New = new JMenuItem("New Game");
        JMenuItem menuItem1Close = new JMenuItem("Close");                 //!
        JMenuItem menuItem2Standart = new JMenuItem("Standart Game");
        JMenuItem menuItem2Custome = new JMenuItem("Custom Game(beta)");
        JMenuItem menuItem3Close = new JMenuItem("Restart");
        JMenuItem menuItem4Exit = new JMenuItem("Exit");                   //!
        menu1.add(menuItem1Load);
        menu1.add(menuItem1Save);
        menu1.add(menuItem1TestRead);
        menu1.add(menuItem1Manual);
        menu1.add(menuItem1New);
        menu1.add(menuItem1Close);
        menu2.add(menuItem2Standart);
        menu2.add(menuItem2Custome);
        menu3.add(menuItem3Close);
        menu4.add(menuItem4Exit);


        mainWindMB.add(menu1);
        mainWindMB.add(menu2);
        mainWindMB.add(menu3);
        mainWindMB.add(menu4);

        mainWinJF.setJMenuBar(mainWindMB);
        mainWinJF.setVisible(true);


        menuItem1New.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menuItem1Close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuItem4Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


    }

    static class ListenerAction implements ActionListener {
        private final ArrayBoard secondArrayBoard = new ArrayBoard();
        JPanel secondPanel;

        public void actionPerformed(ActionEvent e) {            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

            mainWinJF.getContentPane().removeAll();
            for (int i = 0; i < secondArrayBoard.bolvanchikArrayBoard.length; i++) {
                System.out.print("\n");
                for (int j = 0; j < secondArrayBoard.bolvanchikArrayBoard[i].length; j++) {
                    System.out.print(secondArrayBoard.bolvanchikArrayBoard[i][j] + "=");
                }
            }


            Integer[][] arrBig = new Integer[8][8];                     // поиск обновленного массива чисел
            Integer timeLine = 0;
            String line1 = null;
            String line2 = null;
            String line3 = null;
            String line4 = null;
            String line5 = null;
            String line6 = null;
            String line7 = null;
            String line8 = null;
            try {
                Scanner sc = new Scanner(new File("ArrayBoard.txt"));
                while (sc.hasNextLine()) {
                    String time = sc.nextLine();
                    if (timeLine == 0) line1 = time;
                    if (timeLine == 1) line2 = time;
                    if (timeLine == 2) line3 = time;
                    if (timeLine == 3) line4 = time;
                    if (timeLine == 4) line5 = time;
                    if (timeLine == 5) line6 = time;
                    if (timeLine == 6) line7 = time;
                    if (timeLine == 7) line8 = time;
                    timeLine++;
                }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            System.out.print("\n" + line1 + " 1111111111 " + "\n");
            System.out.print(line2 + " 2222222222 " + "\n");
            System.out.print(line3 + " 3333333333 " + "\n");
            System.out.print(line4 + " 4444444444 " + "\n");
            System.out.print(line5 + " 5555555555 " + "\n");
            System.out.print(line6 + " 6666666666 " + "\n");
            System.out.print(line7 + " 7777777777 " + "\n");
            System.out.print(line8 + " 8888888888 " + "\n");
            int[][] arr = new int[8][8];
            for (int ik = 0; ik <= 7; ik++) {
                if (ik == 0) {
                    String[] time = line1.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);

                }
                if (ik == 1) {
                    String[] time = line2.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);

                }
                if (ik == 2) {
                    String[] time = line3.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);

                }
                if (ik == 3) {
                    String[] time = line4.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);

                }
                if (ik == 4) {
                    String[] time = line5.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);

                }
                if (ik == 5) {
                    String[] time = line6.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);

                }
                if (ik == 6) {
                    String[] time = line7.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);

                }
                if (ik == 7) {
                    String[] time = line8.split(" ");
                    for (int it = 0; it < time.length; it++) arr[ik][it] = Integer.parseInt(time[it]);

                }
            }


            System.out.print(arr[0][0] + "\n");
            System.out.print(arr[0][1] + "\n");
            System.out.print(arr[4][3] + "\n");
            System.out.print(arr[7][7] + "\n");
            System.out.print(arr[4][4] + "\n");
            System.out.print(arr[0][7] + "\n");
            System.out.print(arr[7][0] + "\n");




 /*                      int[][] arr = new int[8][8];
            try {
                arr = Files.lines(Paths.get("ArrayBoard.txt"))
                        .map(item -> item.chars().filter(i -> (char) i != ' ').map(Character::getNumericValue).toArray())
                        .toArray(int[][]::new);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }      */

            for (int i = 0; i < arr.length; i++) {
                if (i == 0) System.out.print("\n");
                System.out.print("");
                for (int j = 0; j < arr[i].length; j++) {

                }

            }


            for (int i = 0; i < arr.length; i++) {

                for (int j = 0; j < arr[i].length; j++) {
                    arrBig[i][j] = arr[i][j];
                }

            }
            //FINISH!


            String[] timeInString;                      // определение нажатой кнопки
            TimeNumber bolvanchikTN = new TimeNumber();
            bolvanchikTN.TestString(e.getActionCommand());
            timeInString = bolvanchikTN.ReplaceToArray();

            int tIS1 = Integer.parseInt(timeInString[0]);
            int tIS2 = Integer.parseInt(timeInString[1]);
            System.out.print("___" + tIS1 + "___" + tIS2 + "___" + "\n");


            secondArrayBoard.ReplaceToNew(arr);                                     // клик обработка
            String whiteOrBlack = null;

            try {
                Scanner timesc = new Scanner(new File("TimeSecond.txt"));
                whiteOrBlack = timesc.next();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }


            secondArrayBoard.NewClick(tIS2, tIS1, whiteOrBlack);
            Board replaceBoard = new Board();
            secondPanel = replaceBoard.Board(secondArrayBoard.bolvanchikArrayBoard);

            Integer[][] newTimeArrayBoard = secondArrayBoard.bolvanchikArrayBoard;
            Board newTimeBoard = new Board();
            newTimeBoard.Board(newTimeArrayBoard);

            for (int i = 0; i < secondArrayBoard.bolvanchikArrayBoard.length; i++) {
                System.out.print("\n");
                for (int j = 0; j < secondArrayBoard.bolvanchikArrayBoard[i].length; j++) {
                    System.out.print(secondArrayBoard.bolvanchikArrayBoard[i][j] + "_");
                }
            }
            File file2 = new File("ArrayBoard.txt");
            try {
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                PrintWriter out = new PrintWriter(file2.getAbsoluteFile());
                try {
                    for (int i = 0; i < secondArrayBoard.bolvanchikArrayBoard.length; i++) {
                        for (int j = 0; j < secondArrayBoard.bolvanchikArrayBoard[i].length; j++) {
                            if ((j == 7)) {
                                out.print(secondArrayBoard.bolvanchikArrayBoard[i][j]);
                            } else {
                                out.print(secondArrayBoard.bolvanchikArrayBoard[i][j] + " ");
                            }
                        }
                        out.print("\n");
                    }
                } finally {
                    out.close();
                }
            } catch (IOException exce) {
                throw new RuntimeException(exce);
            }

            mainWinJF.add(secondPanel);
            mainWinJF.validate();
        }
    }
}

class ArrayBoard {
    Integer[][] bolvanchikArrayBoard = new Integer[8][8];
    int sideUP = 0;
    int sideLEFT = 0;
    int sideRIGH = 0;
    int sideDOWN = 0;
    int sideUR = 0;
    int sideDL = 0;
    int sideRD = 0;
    int sideLU = 0;

    void ReplaceToNew(int[][] time) {
        for (int i = 0; i < time.length; i++) {

            for (int j = 0; j < time[i].length; j++) {
                this.bolvanchikArrayBoard[i][j] = time[i][j];
            }
        }
    }

    public void TestUP(int m, int n) {

        if (this.bolvanchikArrayBoard[n + 1][m] == 1) {
            for (int i = n + 1; i < bolvanchikArrayBoard[n].length; i++) {
                System.out.print(n);
            }
            if (this.bolvanchikArrayBoard[n + 2][m] == -1) {
            }
        }
    }

    void NewClick(int m, int n, String blackOrWhite) {

        if (this.bolvanchikArrayBoard[n][m] == 9) {                                              // ч или б ход


            if (blackOrWhite.equals("-1")) {                                                     // white

                if (m - 1 >= 0) {
                    if (this.bolvanchikArrayBoard[n][m - 1] == 1) {                                          //Right
                        for (int i = m - 1; i >-1; i--) {
                            if (this.bolvanchikArrayBoard[n][i] == -1) sideRIGH = -1;
                            if (this.bolvanchikArrayBoard[n][i] == 9) break;
                        }
                    }
                }
                if (n + 1 <= 7) {
                    if (this.bolvanchikArrayBoard[n + 1][m] == 1) {                                       //UP
                        for (int i = n + 1; i <=7; i++) {
                            if (this.bolvanchikArrayBoard[i][m] == -1) sideUP = -1;
                            if (this.bolvanchikArrayBoard[i][m] == 9) break;
                        }
                    }
                }
                if (m + 1 <= 7) {
                    if (this.bolvanchikArrayBoard[n][m + 1] == 1) {                                          //LEFT
                        for (int i = m + 1; i <=7; i++) {
                            if (this.bolvanchikArrayBoard[n][i] == -1) sideLEFT = -1;
                            if (this.bolvanchikArrayBoard[n][i] == 9) break;
                        }
                    }
                }
                if (n - 1 >= 0) {
                    if (this.bolvanchikArrayBoard[n - 1][m] == 1) {                                       //DOWN
                        for (int i = n - 1;  i >-1; i--) {
                            if (this.bolvanchikArrayBoard[i][m] == -1) sideDOWN = -1;
                            if (this.bolvanchikArrayBoard[i][m] == 9) break;

                        }
                    }
                }
                if ((m - 1 >= 0) && (n + 1 <= 7)) {                                          // UP-RIGHT
                    if (this.bolvanchikArrayBoard[n+ 1][m - 1] == 1) {
                        int i = n + 1;
                        int j = m - 1;
                        while ((i <= 7) && (j>= 0)) {
                            if (this.bolvanchikArrayBoard[i][j] == -1) sideUR = -1;
                            if (this.bolvanchikArrayBoard[i][j] == 9) break;
                            i++;
                            j--;

                        }
                    }
                }
                if ((m + 1 <= 7) && (n + 1 <= 7)) {                                          // LEFT_UP
                    if (this.bolvanchikArrayBoard[n+ 1][m + 1] == 1) {
                        int i = n + 1;
                        int j = m + 1;
                        while ((i <= 7) && (j<=7)) {
                            if (this.bolvanchikArrayBoard[i][j] == -1) sideLU = -1;
                            if (this.bolvanchikArrayBoard[i][j] == 9) break;
                            i++;
                            j++;

                        }
                    }
                }
                if ((m + 1 <= 7) && (n- 1 >= 0 )) {                                          // Down-left
                    if (this.bolvanchikArrayBoard[n- 1][m+ 1] == 1) {
                        int i = n - 1;
                        int j = m + 1;
                        while ((i >= 0) && (j<= 7)) {
                            if (this.bolvanchikArrayBoard[i][j] == -1) sideDL = -1;
                            if (this.bolvanchikArrayBoard[i][j] == 9) break;
                            i--;
                            j++;

                        }
                    }
                }
                if ((m + 1 <= 7) && (n + 1 <= 7)) {                                          // RIGHT-Down
                    if (this.bolvanchikArrayBoard[n- 1][m - 1] == 1) {
                        int i = n - 1;
                        int j = m - 1;
                        while ((i <= 7) && (j <= 7)) {
                            if (this.bolvanchikArrayBoard[i][j] == -1) sideRD = -1;
                            if (this.bolvanchikArrayBoard[i][j] == 9) break;
                            i--;
                            j--;

                        }
                    }
                }
                if (sideRIGH == -1) {                                                               //Right
                    for (int i = m; i > -1; i--) {
                        if (this.bolvanchikArrayBoard[n][i] == 1) {
                            this.bolvanchikArrayBoard[n][i] = -1;
                            this.bolvanchikArrayBoard[n][m] = -1;
                            continue;
                        }
                        if (this.bolvanchikArrayBoard[n][i] == -1) break;

                    }
                }
                if (sideUP == -1) {                                                               //UP
                    for (int i = n; i <= 7; i++) {
                        if (this.bolvanchikArrayBoard[i][m] == 1) {
                            this.bolvanchikArrayBoard[i][m] = -1;
                            continue;
                        }
                        if (this.bolvanchikArrayBoard[i][m] == -1) break;
                        this.bolvanchikArrayBoard[n][m] = -1;
                    }
                }
                if (sideLEFT == -1) {                                                               //Left
                    for (int i = m; i < 7; i++) {
                        if (this.bolvanchikArrayBoard[n][i] == 1) {
                            this.bolvanchikArrayBoard[n][i] = -1;
                            this.bolvanchikArrayBoard[n][m] = -1;
                            continue;
                        }
                        if (this.bolvanchikArrayBoard[n][i] == -1) break;

                    }
                }
                if (sideDOWN == -1) {                                                               //DOWN
                    for (int i = n - 1; i > -1; i--) {
                        if (this.bolvanchikArrayBoard[i][m] == 1) {
                            this.bolvanchikArrayBoard[i][m] = -1;
                            this.bolvanchikArrayBoard[n][m] = -1;
                            continue;
                        }
                        if (this.bolvanchikArrayBoard[i][m] == -1) break;

                    }
                }
                if (sideRD == -1) {                                                             //RIGHT-Down
                    System.out.print("RDRDRDRDRDRDRDRDRDRDRDRDRDRDRDRD");
                    int j = m-1;
                    int i = n-1;
                    while ((j >-1) && (i >-1) ) {

                        if (this.bolvanchikArrayBoard[i][j] == 1) {
                            this.bolvanchikArrayBoard[i][j] = -1;
                            this.bolvanchikArrayBoard[n][m] = -1;
                        }
                        j--;
                        i--;
                        if (j == -1) break;
                        if (i == 8) break;
                        if (i == -1) break;
                        if (j == 8) break;
                        if (this.bolvanchikArrayBoard[i][j] == -1) break;
                    }
                }
                if (sideUR == -1) {                                                             //UP-Right
                    System.out.print("UPUPUPUPUPUPUPUPUUPUPUPUPUPUPU");
                    int j = m-1;
                    int i = n+1;
                    while ((j >-1) && (i <=7) ) {


                        if (this.bolvanchikArrayBoard[i][j] == 1) {
                            this.bolvanchikArrayBoard[i][j] = -1;
                            this.bolvanchikArrayBoard[n][m] = -1;
                        }
                        j--;
                        i++;
                        if (j == -1) break;
                        if (i == 8) break;
                        if (i == -1) break;
                        if (j == 8) break;
                        if (this.bolvanchikArrayBoard[i][j] == -1) break;
                    }
                }
                if (sideLU == -1) {                                                             //LEFT_UP
                    System.out.print("LULULULULULULULULULULULULULULU");
                    int j = m+1;
                    int i = n+1;
                    while ((j <=7) && (i <=7) ) {


                        if (this.bolvanchikArrayBoard[i][j] == 1) {
                            this.bolvanchikArrayBoard[i][j] = -1;
                            this.bolvanchikArrayBoard[n][m] = -1;
                        }
                        j--;
                        i++;
                        if (j == -1) break;
                        if (i == 8) break;
                        if (i == -1) break;
                        if (j == 8) break;
                        if (this.bolvanchikArrayBoard[i][j] == -1) break;
                    }
                }
                if (sideDL == -1) {                                                             //down-left
                    System.out.print("DLDLDLDLDLDLDLDLDLDLDLDLDLDLDLDLDL");
                    int j = m+1;
                    int i = n-1;
                    while ((j <=7) && (i >-1) ) {


                        if (this.bolvanchikArrayBoard[i][j] == 1) {
                            this.bolvanchikArrayBoard[i][j] = -1;
                            this.bolvanchikArrayBoard[n][m] = -1;
                        }
                        j++;
                        i--;
                        if (j == -1) break;
                        if (i == 8) break;
                        if (i == -1) break;
                        if (j == 8) break;
                        if (this.bolvanchikArrayBoard[i][j] == -1) break;
                    }
                }

                int sumSide;
                sumSide = sideDOWN + sideLEFT + sideUP + sideRIGH + sideUR+sideRD+sideDL+sideLU;
                if (sumSide < 0) {
                    File file3 = new File("TimeSecond.txt");

                    try {
                        if (!file3.exists()) {
                            file3.createNewFile();
                        }
                        PrintWriter out = new PrintWriter(file3.getAbsoluteFile());
                        try {
                            out.print("1");
                        } finally {
                            out.close();
                        }
                    } catch (IOException ecept) {
                        throw new RuntimeException(ecept);
                    }

                }


            } else {                                                                                 //black
                if (m - 1 >= 0) {
                    if (this.bolvanchikArrayBoard[n][m - 1] == -1) {                                          //Right
                        for (int i = m - 1; i >-1; i--) {
                            if (this.bolvanchikArrayBoard[n][i] == 1) sideRIGH = 1;
                            if (this.bolvanchikArrayBoard[n][i] == 9) break;
                        }
                    }
                }
                if (n + 1 <= 7) {
                    if (this.bolvanchikArrayBoard[n + 1][m] == -1) {                                       //UP
                        for (int i = n + 1; i <=7; i++) {
                            if (this.bolvanchikArrayBoard[i][m] == 1) sideUP = 1;
                            if (this.bolvanchikArrayBoard[i][m] == 9) break;
                        }
                    }
                }
                if (m + 1 <= 7) {
                    if (this.bolvanchikArrayBoard[n][m + 1] == -1) {                                          //LEFT
                        for (int i = m + 1; i <=7; i++) {
                            if (this.bolvanchikArrayBoard[n][i] == 1) sideLEFT = 1;
                            if (this.bolvanchikArrayBoard[n][i] == 9) break;
                        }
                    }
                }
                if (n - 1 >= 0) {
                    if (this.bolvanchikArrayBoard[n - 1][m] == -1) {                                       //DOWN
                        for (int i = n - 1;  i >-1; i--) {
                            if (this.bolvanchikArrayBoard[i][m] == 1) sideDOWN = 1;
                            if (this.bolvanchikArrayBoard[i][m] == 9) break;

                        }
                    }
                }
                if ((m - 1 >= 0) && (n + 1 <= 7)) {                                          // UP-RIGHT
                    if (this.bolvanchikArrayBoard[n+ 1][m - 1] == -1) {
                        int i = n + 1;
                        int j = m - 1;
                        while ((i <= 7) && (j>= 0)) {
                            if (this.bolvanchikArrayBoard[i][j] == 1) sideUR = 1;
                            if (this.bolvanchikArrayBoard[i][j] == 9) break;
                            i++;
                            j--;

                        }
                    }
                }
                if ((m + 1 <= 7) && (n + 1 <= 7)) {                                          // LEFT_UP
                    if (this.bolvanchikArrayBoard[n+ 1][m + 1] == -1) {
                        int i = n + 1;
                        int j = m + 1;
                        while ((i <= 7) && (j<=7)) {
                            if (this.bolvanchikArrayBoard[i][j] == 1) sideLU = 1;
                            if (this.bolvanchikArrayBoard[i][j] == 9) break;
                            i++;
                            j++;

                        }
                    }
                }
                if ((m + 1 <= 7) && (n- 1 >= 0 )) {                                          // Down-left
                    if (this.bolvanchikArrayBoard[n- 1][m+ 1] == -1) {
                        int i = n - 1;
                        int j = m + 1;
                        while ((i >= 0) && (j<= 7)) {
                            if (this.bolvanchikArrayBoard[i][j] == 1) sideDL = 1;
                            if (this.bolvanchikArrayBoard[i][j] == 9) break;
                            i--;
                            j++;

                        }
                    }
                }
                if ((m + 1 <= 7) && (n + 1 <= 7)) {                                          // RIGHT-Down
                    if (this.bolvanchikArrayBoard[n- 1][m - 1] == -1) {
                        int i = n - 1;
                        int j = m - 1;
                        while ((i <= 7) && (j <= 7)) {
                            if (this.bolvanchikArrayBoard[i][j] == 1) sideRD = 1;
                            if (this.bolvanchikArrayBoard[i][j] == 9) break;
                            i--;
                            j--;

                        }
                    }
                }
                if (sideRIGH == 1) {                                                               //Right
                    for (int i = m; i > -1; i--) {
                        if (this.bolvanchikArrayBoard[n][i] == -1) {
                            this.bolvanchikArrayBoard[n][i] = 1;
                            this.bolvanchikArrayBoard[n][m] = 1;
                            continue;
                        }
                        if (this.bolvanchikArrayBoard[n][i] == 1) break;

                    }
                }
                if (sideUP == 1) {                                                               //UP
                    for (int i = n; i <= 7; i++) {
                        if (this.bolvanchikArrayBoard[i][m] == -1) {
                            this.bolvanchikArrayBoard[i][m] = 1;
                            continue;
                        }
                        if (this.bolvanchikArrayBoard[i][m] == 1) break;
                        this.bolvanchikArrayBoard[n][m] = 1;
                    }
                }
                if (sideLEFT == 1) {                                                               //Left
                    for (int i = m; i < 7; i++) {
                        if (this.bolvanchikArrayBoard[n][i] == -1) {
                            this.bolvanchikArrayBoard[n][i] = 1;
                            this.bolvanchikArrayBoard[n][m] = 1;
                            continue;
                        }
                        if (this.bolvanchikArrayBoard[n][i] == 1) break;

                    }
                }
                if (sideDOWN == 1) {                                                               //DOWN
                    for (int i = n - 1; i > -1; i--) {
                        if (this.bolvanchikArrayBoard[i][m] == -1) {
                            this.bolvanchikArrayBoard[i][m] = 1;
                            this.bolvanchikArrayBoard[n][m] = 1;
                            continue;
                        }
                        if (this.bolvanchikArrayBoard[i][m] == 1) break;

                    }
                }
                if (sideRD == 1) {                                                             //RIGHT-Down
                    System.out.print("RDRDRDRDRDRDRDRDRDRDRDRDRDRDRDRD");
                    int j = m-1;
                    int i = n-1;
                    while ((j >-1) && (i >-1) ) {

                        if (this.bolvanchikArrayBoard[i][j] == -1) {
                            this.bolvanchikArrayBoard[i][j] = 1;
                            this.bolvanchikArrayBoard[n][m] = 1;
                        }
                        j--;
                        i--;
                        if (j == -1) break;
                        if (i == 8) break;
                        if (i == -1) break;
                        if (j == 8) break;
                        if (this.bolvanchikArrayBoard[i][j] == 1) break;
                    }
                }
                if (sideUR == 1) {                                                             //UP-Right
                    System.out.print("UPUPUPUPUPUPUPUPUUPUPUPUPUPUPU");
                    int j = m-1;
                    int i = n+1;
                    while ((j >-1) && (i <=7) ) {


                        if (this.bolvanchikArrayBoard[i][j] == -1) {
                            this.bolvanchikArrayBoard[i][j] = 1;
                            this.bolvanchikArrayBoard[n][m] = 1;
                        }
                        j--;
                        i++;
                        if (j == -1) break;
                        if (i == 8) break;
                        if (i == -1) break;
                        if (j == 8) break;
                        if (this.bolvanchikArrayBoard[i][j] == 1) break;
                    }
                }
                if (sideLU == 1) {                                                             //LEFT_UP
                    System.out.print("LULULULULULULULULULULULULULULU");
                    int j = m+1;
                    int i = n+1;
                    while ((j <=7) && (i <=7) ) {


                        if (this.bolvanchikArrayBoard[i][j] == -1) {
                            this.bolvanchikArrayBoard[i][j] = 1;
                            this.bolvanchikArrayBoard[n][m] = 1;
                        }
                        j--;
                        i++;
                        if (j == -1) break;
                        if (i == 8) break;
                        if (i == -1) break;
                        if (j == 8) break;
                        if (this.bolvanchikArrayBoard[i][j] == 1) break;
                    }
                }
                if (sideDL == 1) {                                                             //down-left
                    System.out.print("DLDLDLDLDLDLDLDLDLDLDLDLDLDLDLDLDL");
                    int j = m+1;
                    int i = n-1;
                    while ((j <=7) && (i >-1) ) {


                        if (this.bolvanchikArrayBoard[i][j] == -1) {
                            this.bolvanchikArrayBoard[i][j] = 1;
                            this.bolvanchikArrayBoard[n][m] = 1;
                        }
                        j++;
                        i--;
                        if (j == -1) break;
                        if (i == 8) break;
                        if (i == -1) break;
                        if (j == 8) break;
                        if (this.bolvanchikArrayBoard[i][j] == 1) break;
                    }
                }








                int sumSide;
                sumSide = sideDOWN + sideLEFT + sideUP + sideRIGH + sideUR+sideRD+sideDL+sideLU;


                if (sumSide > 0) {

                    File file3 = new File("TimeSecond.txt");

                    try {
                        if (!file3.exists()) {
                            file3.createNewFile();
                        }
                        PrintWriter out = new PrintWriter(file3.getAbsoluteFile());
                        try {
                            out.print("-1");
                        } finally {
                            out.close();
                        }
                    } catch (IOException ecept) {
                        throw new RuntimeException(ecept);
                    }
                }
            }
        }
    }

    void NGBoard() {
        for (int i = 0; i < this.bolvanchikArrayBoard.length; i++) {              //заполнение доски мусором без нуллов
            for (int j = 0; j < this.bolvanchikArrayBoard[i].length; j++) {
                this.bolvanchikArrayBoard[i][j] = 9;
            }
        }
        this.bolvanchikArrayBoard[3][4] = 1;
        this.bolvanchikArrayBoard[4][3] = 1;
        this.bolvanchikArrayBoard[3][3] = -1;
        this.bolvanchikArrayBoard[4][4] = -1;

    }
}


class TimeNumber {
    private String numberOfButton = "";

    String Test1(String time) {
        this.numberOfButton = time;
        return time;
    }

    void TestString(String time) {
        numberOfButton = time;
        System.out.print("\n" + "NUMBER " + numberOfButton);
    }

    String[] ReplaceToArray() {
        String[] timeStr;
        timeStr = numberOfButton.split("");
        return timeStr;
    }
}

class Board {
    String whiteWay = "WhitePoint.png";
    String blackWay = "BlackPoint.png";
    String noWay = "noPoint.png";
    Icon whiteWayICON = new ImageIcon(whiteWay);
    Icon blackWayICON = new ImageIcon(blackWay);
    Icon noWayICON = new ImageIcon(noWay);

    JPanel Board(Integer[][] bolvanchikBoard) {
        JPanel panelBoard = new JPanel();
        panelBoard.setLayout(new GridLayout(8, 8));
        for (int i = 0; i < bolvanchikBoard.length; i++) {
            for (int j = 0; j < bolvanchikBoard[i].length; j++) {
                JButton bottonTime = new JButton(Integer.toString(i) + j);
                bottonTime.setBackground(new Color(80, 50, 90));
                StartWind.ListenerAction timeOutStr = new StartWind.ListenerAction();
                bottonTime.addActionListener(timeOutStr);
                bottonTime.setFocusPainted(false);
                if (bolvanchikBoard[i][j] == null) {
                    System.out.print("как такое возможно?");
                    continue;
                }
                if (bolvanchikBoard[i][j] == -1) {
                    bottonTime.setIcon(whiteWayICON);
                }
                if (bolvanchikBoard[i][j] == 1) {
                    bottonTime.setIcon(blackWayICON);
                }
                if (bolvanchikBoard[i][j] == 9) {
                    bottonTime.setIcon(noWayICON);
                }
                panelBoard.add(bottonTime);
            }
        }
        return panelBoard;
    }
}
