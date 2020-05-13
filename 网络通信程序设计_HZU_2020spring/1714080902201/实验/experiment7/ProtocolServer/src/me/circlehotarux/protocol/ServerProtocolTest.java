/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.circlehotarux.protocol;

/**
 *
 * @author circlehotarux
 */
public class ServerProtocolTest {
  private static final int WAITING = 0;     //等待
  private static final int SENTKNOCKKNOCK = 1;      //敲门完成
  private static final int SENTCLUE = 2;        //第一遍线索回答完成
  private static final int ANOTHER = 3;     //第二遍回答完成
  private static final int NUMJOKES = 5;        //游戏总局数
  private int state = WAITING;      //状态
  private int currentJoke = 0;      //计数
  //以下两个数组分别存储敲门人的两次回答
  private String[] clues = {"1", "2", "3", "4", "5"};
  private String[] answers = {"6","7","8","9","10"};
  public String processInput(String theInput) {     //门内人的问话
    String theOutput = null;        //敲门人的回答
    if (state == WAITING) {
      theOutput = "Holle";
      state = SENTKNOCKKNOCK;
    }
    else if (state == SENTKNOCKKNOCK) {
      if (theInput.equalsIgnoreCase("hello")) {
        theOutput = clues[currentJoke];
        state = SENTCLUE;
      }
      else {
        theOutput = "8888888";
      }
    }
    else if (state == SENTCLUE) {
      if (theInput.equalsIgnoreCase(clues[currentJoke] + " string98")) {
        theOutput = answers[currentJoke] + " yyyyy";
        state = ANOTHER;
      }
      else {
        theOutput = "rrrrrr" +
            "hhhhhhhhhh";
        state = SENTKNOCKKNOCK;
      }
    }
    else if (state == ANOTHER) {
      if (theInput.equalsIgnoreCase("y")) {
        theOutput = "565656";
        if (currentJoke == (NUMJOKES - 1)) {
          currentJoke = 0;
        }
        else {
          currentJoke++;
        }
        state = SENTKNOCKKNOCK;
      }
      else {
        theOutput = "Bye.";
        state = WAITING;
      }
    }
    return theOutput;
  }
}
