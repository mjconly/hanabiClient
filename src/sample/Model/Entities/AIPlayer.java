//package sample.Model.Entities;
//import sample.Model.DuringModel;
//import sample.Model.Suit;
//
//import java.util.ArrayList;
//
//public class AIPlayer extends HumanPlayer{
//    private int bestPlay=-1; // Index of best card to play
//    private int bestDiscard; // Index of best card to discard
//    private int[] bestInfo = new int[3];  //MAX SIZE = 3 [playerSeat, indexOfCard, 0-suit/1-rank]
//    private DuringModel currentBoardState;
//    private int bestMove; // Indicates which move is the best out of the 3 actions
//    final int PLAY_CARD_INDICATOR = 5000;
//    // Scores -> Burned plays = 0, Invalid plays = -1, Valid =1-50
//
//    public AIPlayer(int seat, Hand startHand){
//        super(seat,startHand);
//    }
//
//    /*
//        Checks to see which is the best card to play
//        Sets the index of the best card to play
//        Returns the value of the highest score
//     */
//    public int setBestPlay(){
//        int[] score = new int[this.pHand.getHandSize()];  // keeps the total score
//
//        for(int i=0; i<this.pHand.getHandSize(); i++){
//            // Grab a card of each hint array one by one
//            Suit curSuit = pHand.getRevealedSuits().get(i);
//            Integer curRank = pHand.getRevealedRanks().get(i);
//
//            ArrayList<Firework> fireworks = currentBoardState.getCurrentBoard().getFireworks(); // Grab the board from the during model
//
//            for(int j=0; j<currentBoardState.getCurrentBoard().getFireworks().size();j++){
//                Firework curFirework = fireworks.get(j);
//
//                // If colours dont match
//                if(curSuit != null && curSuit != curFirework.getColor()){
//                    score=score;
//                }
//                else if(curSuit==null){ // We don't know colour
//
//                    //Checking ranks
//                    if(curRank==null){  // We dont know both suit and rank, keep it
//                        score[i] +=1;
//                    }
//                    else if(curRank - 1 != curFirework.getTop().getRank()){ //Check if the rank is one more than the top
//                        score[i]+=0;
//                    }
//                    else{ // rank if correct
//                        score[i] += 10;
//                    }
//                }
//                else{   // Colours do match
//
//                    //Checking ranks
//                    if(curRank==null){  // We dont know rank, keep it
//                        score[i] +=10;
//                    }
//                    else if(curRank - 1 != curFirework.getTop().getRank()){ //Know colours match but the rank doesnt
//                        score[i]+=0;
//                    }
//                    else{ // rank if correct
//                        score[i]=PLAY_CARD_INDICATOR;   // 5000 means play this card and set the firework to play this card at
//                    }
//
//                }
//            }
//        }
//
//        // Set the bestPlay instance variable
//        int highestScore=0;
//        for(int i=0;i<score.length; i++){
//            if(score[i]>highestScore){
//                highestScore = score[i];
//                bestPlay = i;   // set the best play
//            }
//        }
//        return highestScore;
//    }
//
//    /*
//        Sets the index of the best card to discard
//        returns the score
//     */
//    public int setBestDiscard(){
//        int[] score = new int[this.pHand.getHandSize()];  // keeps the total score
//
//        //Tokens are full
//        if(currentBoardState.getInfoToken().getCurrent() == 8){
//            return 1;
//        }
//
//        // Making discard decisions
//        for(int i=0; i<this.pHand.getHandSize(); i++){
//            // Grab a card of each hint array one by one
//            Suit curSuit = pHand.getRevealedSuits().get(i);
//            Integer curRank = pHand.getRevealedRanks().get(i);
//
//            ArrayList<Firework> fireworks = currentBoardState.getCurrentBoard().getFireworks(); // Grab the board from the during model
//
//            for(int j=0; j<currentBoardState.getCurrentBoard().getFireworks().size();j++){
//                Firework curFirework = fireworks.get(j);
//
//                // If colours dont match
//                if(curSuit != null && curSuit != curFirework.getColor()){
//                    score[i]+= 0;
//                }
//                else if(curSuit==null){ // We don't know colour
//
//                    //Checking ranks
//                    if(curRank==null){  // We dont know both suit and rank, keep it
//                        score[i] +=1;
//                    }
//                    else if(curRank <= curFirework.getTop().getRank() && curRank!=5){ //Check if the rank is lower than the top card
//                        score[i]+=10;
//                    }
//                    else{ // rank if correct, greater than the top card or if the rank is a 5
//                        score[i] += 0;  // Don't want to discard
//                    }
//                }
//                else{   // Colours do match
//                    if(fireworks.get(j).getTop().getRank() == 5){
//                        score[i] += 50; // Don't risk it if top card is a 5
//                    }
//                    //Checking ranks
//                    else if(curRank==null){  // We dont know rank
//                        score[i] +=0;
//                    }
//                    else if(curRank <= curFirework.getTop().getRank() && curRank!=5){ //Know colours match but the rank doesnt
//                        score[i]+=50;
//                    }
//                    else{ // rank if correct
//                        score[i]+=0;   // 5000 means play this card and set the firework to play this card at
//                    }
//
//                }
//            }
//        }
//
//        // Set the bestDiscard instance variable
//        int highestScore=0;
//        for(int i=0;i<score.length; i++){
//            if(score[i]>highestScore){
//                highestScore = score[i];
//                bestDiscard = i;   // set the best play
//            }
//        }
//        return highestScore;
//    }
//
//    /*
//        Determines information to give if AI chooses to give info
//     */
//    public int checkInfo(){
//        int score = 0;
//        this.bestInfo[2] = -1;
//        if(this.currentBoardState.getInfoToken().getCurrent() == 0){
//            //NO MORE TOKENS
//            return -1;
//        }
//        //check for unknown playable cards
//        for(int i =0; i<this.currentBoardState.getNumOfPlayers();i++){
//            if(i != this.currentBoardState.getCurrentPlayer()){
//                HumanPlayer curPlayer = this.currentBoardState.getPlayer(i);
//                for(int j = 0; j<curPlayer.pHand.getHandSize();j++){
//                    Card curCard = curPlayer.pHand.getCard(j);
//                    int unknown = -1;
//                    if(curPlayer.pHand.getRevealedRanks().get(j) == null){
//                        unknown = 1;
//                    }
//                    if(curPlayer.pHand.getRevealedSuits().get(j) == null){
//                        unknown = 0;
//                    }
//                    if(unknown !=-1){
//                        if(curCard.getRank() == this.currentBoardState.getCurrentBoard().getTopFirework(curCard.getSuit()).getRank()-1){
//                            this.bestInfo[0] = curPlayer.getSeat();
//                            this.bestInfo[1] = j;
//                            this.bestInfo[2] = unknown;
//                            return 30;
//                        }else if(unknown != 1 && curCard.getRank() == 5){
//                            this.bestInfo[0] = curPlayer.getSeat();
//                            this.bestInfo[1] = j;
//                            this.bestInfo[2] = unknown;
//                            return 30;
//                        }else{
//                            score+=1;
//                            this.bestInfo[0] = curPlayer.getSeat();
//                            this.bestInfo[1] = j;
//                            this.bestInfo[2] = unknown;
//                        }
//
//                    }
//                }
//            }
//        }
//        if(this.currentBoardState.getInfoToken().getCurrent() <= this.currentBoardState.getInfoToken().getMax()/2){
//            score = score/2;
//        }
//        if(this.bestInfo[2] == -1){
//            return -1;
//        }
//        return score;
//    }
//
//    public int chooseMove(DuringModel boardState){
//        this.currentBoardState = boardState;
//        int playScore = this.setBestPlay();
//        int discardScore = this.setBestDiscard();
//        int infoScore = this.checkInfo();
//        if(playScore>discardScore && playScore>infoScore){
//            return 1;
//        }else if(discardScore>infoScore && discardScore>playScore){
//            return 2;
//        }else if(infoScore>playScore && infoScore>discardScore){
//            return 3;
//        }else if(infoScore == playScore && playScore == discardScore){
//            return 3;
//        }else if(playScore == discardScore){
//            return 1;
//        }else if(playScore == infoScore){
//            return 3;
//        }else if(discardScore == infoScore){
//            return 3;
//        }else{
//            return 3;
//        }
//    }
//
//}
