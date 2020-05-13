package me.circlehotarux;

public class Protocol {
    //游戏包含敲门人和门内人两个角色。门内人的问话和回答有固定格式，敲门人的回答是变化的，所以用以下常量表示敲门人会话进程和状态
    private static final int WAITING=0; //等待
    private static final int SENTKNOCKKNOCK=1; //敲门完成
    private static final int SENTCLUE=2; //第一遍线索回答完成
    private static final int SENTANSWER=3; //第二遍终极回答完成
 
    private static final int NUMJOKES=8; //游戏总局数
    
    private int state=WAITING; //会话状态
    private int currentJoke=0; //计数
    //以下两个数组分别存储敲门人的两次回答
    private String[] clues={"Buster","Orange","Ice cream","Tunis","Old lady","Yah","Dishes","Amish"};
    private String[] answers={"Buster Cherry! Is your daughter home?","Orange you going to answer the door?","Ice cream if you don't let me in!","Tunis company, three's a crowd!","Wow I didn't know you could yodel.","Naaah, bro, I prefer google.","Dishes the Police come out with your hands up.","Awwww How sweet. I miss you too."};
    public String protocolWorking(String question) { //question门内人的问话
        String answer=null; //敲门人的回答
        switch (state) {
            case WAITING: //开始敲门
                answer="Knock! Knock!";
                state=SENTKNOCKKNOCK;
                break;
            case SENTKNOCKKNOCK: //谁在敲门？问答
                if (question.equalsIgnoreCase("Who's there?")) {
                    answer=clues[currentJoke];
                    state=SENTCLUE;
                }else {
                    answer="你应该问：\"Who's there?\""+"重新开始：Knock! Knock!";
                }
                break;
            case SENTCLUE: //追问敲门人问答
                if (question.equalsIgnoreCase(clues[currentJoke]+" Who?")) {
                    answer=answers[currentJoke]+" 是否继续 ？(y / n ?)";
                    state=SENTANSWER;
                }else {
                    answer="你应该问：\""+clues[currentJoke]+" Who?\""+"重新开始：Knock! Knock!";
                    state=SENTKNOCKKNOCK;
                }
                break;
            case SENTANSWER: //询问门内人是否继续游戏？
                if (question.equalsIgnoreCase("y")) {
                    answer="Knock! Knock!";
                    if (currentJoke==NUMJOKES-1) {
                        currentJoke=0;
                    }else {
                        currentJoke++;
                    }
                    state=SENTKNOCKKNOCK;
                }else {
                    answer="Game Over! Goodbye!";
                    state=WAITING;
                }
                break;                
        }
        return answer;   
    }
}

